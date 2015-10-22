package com.yutong.clw.nio.msg.cache;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.dao.DataAccessException;

import com.yutong.clw.config.Config;
import com.yutong.clw.config.Constant;
import com.yutong.clw.utils.StringUtil;

public class CacheBuffer implements Runnable{

	private static Logger log = LoggerFactory.getLogger(CacheBuffer.class);

    private static final CacheBuffer dbBuffer = new CacheBuffer();
    
    private static final String NAME = "<CacheBuffer>";

	private Queue<CacheBean> downQueue;

    private CacheThreadPool pool = CacheThreadPool.instance();
    
    private boolean shutdownFlag = true; 

	private CacheBuffer() {
        downQueue = new LinkedList<CacheBean>();
        shutdownFlag = false;
    }

    public static CacheBuffer getInstance() {
        return dbBuffer;
    }

    /**
     * 向队列中加入一条下行指令
     * @param down
     */
    public synchronized void add(CacheBean down) {
        downQueue.offer(down);
    }

    /**
     * 向队列中加入一批缓存
     * @param downList
     */
//    public synchronized void add(List<CacheBean> downList) {
//        downQueue.addAll(downList);
//    }

    /**
     * 向队列中加入一批缓存
     * @param downArray
     */
    public synchronized void add(List<CacheBean> downArray) {
        for (CacheBean down : downArray) {
            downQueue.offer(down);
        }
    }

    class ExeSqlRunner implements Runnable {
        private List<CacheBean> sqls;

        public ExeSqlRunner(List<CacheBean> sqls) {
            this.sqls = sqls;
        }

        public void run() {
            try {
//                log.info(LogFormatter.formatMsg("DownBuffer", "开始将缓冲队列中的" + sqls.length + "个下行指令下发"));
                // 批量入库
//              commonDAO.batchUpdate(sqls);
                BatchDown(sqls);
                Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenCacheSuccess")));
//                log.info(LogFormatter.formatMsg("DownBuffer","已成功将缓冲队列中的" + sqls.length + "个下行指令下发！"));
            } catch (DataAccessException e) {
                log.error(NAME+"缓冲队列批量put缓存时出现数据库异常："+e);
            } catch (Exception e) {
                log.error(NAME+"缓冲队列批量put缓存时出现系统异常："+e);
            }
        }
    }

    @SuppressWarnings("static-access")
	public void run() {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[DownBuf]");
//        log.info(LogFormatter.formatMsg("DBBuffer","线程"+Thread.currentThread().getId()+"执行开始时间为:"+new Date()));
        while(!shutdownFlag){
        	List<CacheBean> sqls = getSqlsFromQueue();
            if (null == sqls) {
                log.debug(NAME+"缓存指令缓冲队列中暂时没有数据！");
                try { 
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenCacheQueueIsNull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"缓存指令缓冲队列处理在休眠时出现异常，" + e);
                }
                continue;
            }
            log.warn(NAME+"当前缓存指令队列大小为:" + downQueue.size());
            ExeSqlRunner runner = new ExeSqlRunner(sqls);
            if (!pool.start(runner, pool.HIGH_PRIORITY)) {
                log.info(NAME+"用于执行缓存指令队列的线程池已满！将该批待执行缓存指令重新放入缓存中，并休眠"
                        + Config.props.getProperty("sleepTimeWhenCacheThreadPoolFull") + "毫秒!");
                add(sqls);
                try {
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenCacheThreadPoolFull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"缓存指令缓冲队列处理在休眠时出现异常，" + e);
                }
            }else{
//            	log.info(NAME+"用于执行缓存指令的线程正常启动！");
            }
        }
//        log.warn(NAME+"缓存指令缓冲队列处理终止！");
    }

    private List<CacheBean> getSqlsFromQueue() {
        int curQueueSize = downQueue.size();
        if (curQueueSize > 0) {
            int count = 0;
            if (curQueueSize <= Integer.parseInt(Config.props.getProperty("countOfExeCachePerTime"))) { // 不够或刚够一页数据
                count = curQueueSize;
            } else { //多于一页数据
                count = Integer.parseInt(Config.props.getProperty("countOfExeCachePerTime"));
            }
            List<CacheBean> list = new ArrayList<CacheBean>();
            
            synchronized (this) {
                for (int i = 0; i < count; i++) {
                    list.add(downQueue.poll());
                }
            } 
            log.info(NAME+"从缓存队列中取出" + count + "个缓存语句。");
            return list;
        } else {
            log.debug(NAME+"缓存队列目前为空！");
            return null;
        }
    }
    
	private void BatchDown(List<CacheBean> list) throws Exception{
		for(CacheBean cb : list){
	    	if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
	    		Constant.getMemcachedClient().insert(cb.getKey(), cb.getValue());
	    	}
		}
    }

    public void shutdown() {
        log.info("<cacheQueue>开始执行线程池关闭操作");
        shutdownFlag = true;
        
        pool.shutdown();
        log.info("<cacheQueue>线程池关闭结束！");
    }

}
