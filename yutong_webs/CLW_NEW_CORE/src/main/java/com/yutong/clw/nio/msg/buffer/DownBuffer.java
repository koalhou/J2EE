package com.yutong.clw.nio.msg.buffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.dao.DataAccessException;

import com.yutong.clw.config.Config;
import com.yutong.clw.nio.msg.monitor.SendHistoryCmdBean;
import com.yutong.clw.nio.msg.value.CommandCode;
import com.yutong.clw.service.SendCmdService;
import com.yutong.clw.utils.StringUtil;

public class DownBuffer implements Runnable{

	private static Logger log = LoggerFactory.getLogger(DownBuffer.class);

    private static final DownBuffer dbBuffer = new DownBuffer();
    
    private static final String NAME = "<DownBuffer>";

    private Queue<SendHistoryCmdBean> downQueue;

//    private ICommonDAO commonDAO;

    private ThreadPool pool = ThreadPool.instance();
    
    private boolean shutdownFlag = true; 

    private DownBuffer() {
        downQueue = new LinkedList<SendHistoryCmdBean>();
//        commonDAO = (ICommonDAO) SpringBootStrap.getInstance().getBean("commonDAO");
        shutdownFlag = false;
    }

    public static DownBuffer getInstance() {
        return dbBuffer;
    }

    /**
     * 向队列中加入一条下行指令
     * @param down
     */
    public synchronized void add(SendHistoryCmdBean down) {
        downQueue.offer(down);
    }

    /**
     * 向队列中加入一批下行指令
     * @param downList
     */
    public synchronized void add(List<SendHistoryCmdBean> downList) {
        downQueue.addAll(downList);
    }

    /**
     * 向队列中加入一批下行指令
     * @param downArray
     */
//    public synchronized void add(String[] downArray) {
//        for (String down : downArray) {
//            downQueue.offer(down);
//        }
//    }

    class ExeSqlRunner implements Runnable {
        private List<SendHistoryCmdBean> sqls;

        public ExeSqlRunner(List<SendHistoryCmdBean> sqls) {
            this.sqls = sqls;
        }

        public void run() {
            try {
//                log.info(LogFormatter.formatMsg("DownBuffer", "开始将缓冲队列中的" + sqls.length + "个下行指令下发"));
                // 批量入库
//              commonDAO.batchUpdate(sqls);
                BatchDown(sqls);
                Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDownSuccess")));
//                log.info(LogFormatter.formatMsg("DownBuffer","已成功将缓冲队列中的" + sqls.length + "个下行指令下发！"));
            } catch (DataAccessException e) {
                log.error(NAME+"缓冲队列批量下发时出现数据库异常："+e);
            } catch (Exception e) {
                log.error(NAME+"缓冲队列批量下发时出现系统异常："+e);
            }
        }
    }

    @SuppressWarnings("static-access")
	public void run() {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[DownBuf]");
//        log.info(LogFormatter.formatMsg("DBBuffer","线程"+Thread.currentThread().getId()+"执行开始时间为:"+new Date()));
        while(!shutdownFlag){
            List<SendHistoryCmdBean> sqls = getSqlsFromQueue();
            if (null == sqls) {
                log.debug(NAME+"下行指令缓冲队列中暂时没有数据！");
                try { 
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDownQueueIsNull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"下行指令缓冲队列处理在休眠时出现异常，" + e);
                }
                continue;
            }
            log.warn(NAME+"当前下行指令队列大小为:" + downQueue.size());
            ExeSqlRunner runner = new ExeSqlRunner(sqls);
            if (!pool.start(runner, pool.HIGH_PRIORITY)) {
                log.info(NAME+"用于执行下行指令队列的线程池已满！将该批待执行下行指令重新放入缓存中，并休眠"
                        + Config.props.getProperty("sleepTimeWhenDownThreadPoolFull") + "毫秒!");
                add(sqls);
                try {
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDownThreadPoolFull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"下行指令缓冲队列处理在休眠时出现异常，" + e);
                }
            }else{
//            	log.info(NAME+"用于执行下行指令的线程正常启动！");
            }
        }
        
//        log.warn(NAME+"下行指令缓冲队列处理终止！");
    }

    private List<SendHistoryCmdBean> getSqlsFromQueue() {
        int curQueueSize = downQueue.size();
        if (curQueueSize > 0) {
            int count = 0;
            if (curQueueSize <= Integer.parseInt(Config.props.getProperty("countOfExeDownPerTime"))) { // 不够或刚够一页数据
                count = curQueueSize;
            } else { //多于一页数据
                count = Integer.parseInt(Config.props.getProperty("countOfExeDownPerTime"));
            }
            List<SendHistoryCmdBean> list = new ArrayList<SendHistoryCmdBean>();
            synchronized (this) {
                for (int i = 0; i < count; i++) {
                    list.add(downQueue.poll());
                }
            }
//            log.info(NAME+"从下行指令缓冲队列中取出" + count + "个下行指令语句。"));
            return list;

        } else {
            log.debug(NAME+"下行指令缓冲队列目前为空！");
            return null;
        }
    }
    
    private void BatchDown(List<SendHistoryCmdBean> list) throws Exception{
    	for(SendHistoryCmdBean sqls:list){
	    	if(sqls.getQuery_field().equals(CommandCode.down2006_22)){
	    		log.info(NAME+"下行车联网秒数据命令串："+new String(sqls.getBytes()));
			}else if(sqls.getQuery_field().equals(CommandCode.down2006_23)){
				log.info(NAME+"下行车联网1分钟数据命令串："+new String(sqls.getBytes()));
			}else if(sqls.getQuery_field().equals(CommandCode.down2006_24)){
				log.info(NAME+"下行车联网5分钟数据命令串："+new String(sqls.getBytes()));
			}else if(sqls.getQuery_field().equals(CommandCode.down2006_15)){
				log.info(NAME+"下行车联网急加速历史数据命令串："+new String(sqls.getBytes()));
			}else if(sqls.getQuery_field().equals(CommandCode.down2006_14)){
				log.info(NAME+"下行车联网开关量历史数据命令串："+new String(sqls.getBytes()));
			}else{
				System.out.println("==================================");
			}
	    	SendCmdService.cycleSendCommand(sqls);
    	}
    }

    public void shutdown() {
        log.info("<downQueue>开始执行线程池关闭操作");
        shutdownFlag = true;
        
        pool.shutdown();
        log.info("<downQueue>线程池关闭结束！");
    }

}
