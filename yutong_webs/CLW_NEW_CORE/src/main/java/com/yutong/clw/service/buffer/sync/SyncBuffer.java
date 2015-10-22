package com.yutong.clw.service.buffer.sync;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.yutong.clw.beans.app.MessageConfig;
import com.yutong.clw.beans.sms.MessageBackMap;
import com.yutong.clw.config.Config;
import com.yutong.clw.utils.StringUtil;
/**
 * 安芯平台信息向翔东同步的队列
 * @author chenqiong
 *
 */
public class SyncBuffer implements Runnable{

	private static Logger log = LoggerFactory.getLogger(SyncBuffer.class);

    private static final SyncBuffer dbBuffer = new SyncBuffer();
    
    private static final String NAME = "<SyncBuffer>";

    private Queue<SyncClass> sqlQueue;

    private SyncThreadPool pool = SyncThreadPool.instance();
    
    private boolean shutdownFlag = true; 

    private SyncBuffer() {
        sqlQueue = new LinkedList<SyncClass>();
        shutdownFlag = false;
    }

    public static SyncBuffer getInstance() {
        return dbBuffer;
    }

    /**
     * 向队列中加入一条sql
     * @param sql
     */
    public synchronized void add(SyncClass sql) {
        sqlQueue.offer(sql);
    }

    /**
     * 向队列中加入一批sql
     * @param sqlList
     */
    public synchronized void add(List<SyncClass> sqlList) {
        for(SyncClass cc:sqlList){
        	sqlQueue.add(cc);
        }
    }


    class ExeSqlRunner implements Runnable {
        private List<SyncClass> sqls;

        public ExeSqlRunner(List<SyncClass> sqls) {
            this.sqls = sqls;
        }

        public void run() {
            try {
                log.info(NAME+"开始将发送" + sqls.size() + "个同步信息到短信平台！");
                // 批量发送
                batchSend(sqls);
                log.info(NAME+"已成功将发送" + sqls.size() + "个同步信息到短信平台！");
            } catch (DataAccessException e) {
                log.error(NAME+"同步信息发送队列批量入库时出现数据库异常："+e);
            } catch (Exception e) {
                log.error(NAME+"同步信息发送队列批量入库时出现系统异常："+e);
            }
        }
    }

    @SuppressWarnings("static-access")
	public void run() {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[SyncBuf]");
        log.info(NAME+"MessageBackMap2:"+MessageBackMap.getInstance());
        while(!shutdownFlag){
            List<SyncClass> sqls = getSqlsFromQueue();
            if (null == sqls || 0 == sqls.size()) {
                log.debug(NAME+"同步信息发送队列中暂时没有数据！");
                try { 
                    Thread.sleep(Long.parseLong(MessageConfig.props.getProperty("sleepTimeWhenSyncQueueIsNull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"同步信息发送队列处理在休眠时出现异常，" + e);
                }
                continue;
            }
            ExeSqlRunner runner = new ExeSqlRunner(sqls);
            if (!pool.start(runner, pool.HIGH_PRIORITY)) {
                log.info(NAME+"用于发送同步信息的线程池已满！将该批待发送同步信息重新放入缓存中，并休眠"
                        + MessageConfig.props.getProperty("sleepTimeWhenSyncThreadPoolFull") + "毫秒!");
                add(sqls);
                try {
                    Thread.sleep(Long.parseLong(MessageConfig.props.getProperty("sleepTimeWhenSyncThreadPoolFull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"同步信息发送队列处理在休眠时出现异常，" + e);
                }
            }else{
//            	log.info(NAME+"用于执行DB数据入库的线程正常启动！");
            }
            log.warn(NAME+"当前同步信息发送队列大小为:" + sqlQueue.size());
        }      
//        log.info(NAME+"MessageBackMap22:"+MessageBackMap.getInstance());
//        log.warn(NAME+"数据库缓冲队列处理终止！");
    }


	public void batchSend(List<SyncClass> sqls) throws UnsupportedEncodingException {
		for(SyncClass sql:sqls){
//			System.out.println("stu_id:"+sql.getId());
//			byte[] b = sql.getReq().getBytes("GBK");
//			for(byte bb : b){
//				System.out.println(bb);
//			}
			if(sql.getReq().getBytes("GBK").length==sql.getTotallen()){
//				SendMethod.SendCommand(sql.getReq().getBytes("GBK"));
				log.info("消息"+sql.getReq()+"已发送至短信平台！");
//				if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
//					Constant.getMemcachedClient().insert(sql.getCommand()+sql.getSeq(), sql.getId());
//				}
//				Constant.ytbsendcmdMap.put(sql.getCommand()+sql.getSeq(), sql.getId());
//				try {
//					Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenSendTime")));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
			}else{
				log.info(NAME+",该条消息长度非法。消息内容为:"+sql.getReq());
			}
		}
	}


	private List<SyncClass> getSqlsFromQueue() {
    	
        int curQueueSize = sqlQueue.size();
        if (curQueueSize > 0) {
            int count = 0;
            if (curQueueSize <= Integer.parseInt(MessageConfig.props.getProperty("countOfExeSyncPerTime"))) { // 不够或刚够一页数据
                count = curQueueSize;
            } else { //多于一页数据
                count = Integer.parseInt(MessageConfig.props.getProperty("countOfExeSyncPerTime"));
            }
            
            try {
				Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenSyncGetTime")));
			} catch (Exception e) {
				e.printStackTrace();
			} 
            
            List<SyncClass> sqls = new ArrayList<SyncClass>();
            
            synchronized (this) {
                for (int i = 0; i < count; i++) {
                    sqls.add(sqlQueue.poll());
                }
            }
            
            log.info(NAME+"从同步信息发送队列中取出" + count + "个同步信息。");
            return sqls;

        } else {
            log.debug(NAME+"同步信息发送缓冲队列目前为空！");
            return null;
        }
    }

    public void shutdown() {
        log.info("<sqlQueue>开始执行线程池关闭操作");
        shutdownFlag = true;
        
        pool.shutdown();
        log.info("<sqlQueue>sqlQueue","线程池关闭结束！");
    }
}
