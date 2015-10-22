package com.yutong.clw.service.buffer;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.yutong.clw.config.Config;
import com.yutong.clw.dao.ICommonDAO;
import com.yutong.clw.service.buffer.error.ErrorBuffer;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.StringUtil;

public class DBBuffer implements Runnable{

	private static Logger log = LoggerFactory.getLogger(DBBuffer.class);

    private static final DBBuffer dbBuffer = new DBBuffer();
    
    private static final String NAME = "<DBBuffer>";

    private Queue<String> sqlQueue;

    private ICommonDAO commonDAO;

    private ThreadPool pool = ThreadPool.instance();
    
    private boolean shutdownFlag = true; 

    private DBBuffer() {
        sqlQueue = new LinkedList<String>();
        commonDAO = (ICommonDAO) SpringBootStrap.getInstance().getBean("commonDAO");
        shutdownFlag = false;
    }

    public static DBBuffer getInstance() {
        return dbBuffer;
    }

    /**
     * 向队列中加入一条sql
     * @param sql
     */
    public synchronized void add(String sql) {
        sqlQueue.offer(sql);
    }

    /**
     * 向队列中加入一批sql
     * @param sqlList
     */
    public synchronized void add(List<String> sqlList) {
        sqlQueue.addAll(sqlList);
    }

    /**
     * 向队列中加入一批sql
     * @param sqlList
     */
    public synchronized void add(String[] sqlArray) {
        for (String sql : sqlArray) {
            sqlQueue.offer(sql);
        }
    }

    class ExeSqlRunner implements Runnable {
        private String[] sqls;

        public ExeSqlRunner(String[] sqls) {
            this.sqls = sqls;
        }

        public void run() {
            try {
                //log.info(NAME+"开始将缓冲队列中的" + sqls.length + "个sql入库");
                // 批量入库
                commonDAO.batchUpdate(sqls);
                //log.info(NAME+"已成功将缓冲队列中的" + sqls.length + "个sql入库！");
            } catch (DataAccessException e) {
                log.error(NAME+"缓冲队列批量入库时出现数据库异常："+e);
                ErrorBuffer.getInstance().add(sqls);
            } catch (Exception e) {
                log.error(NAME+"缓冲队列批量入库时出现系统异常："+e);
                ErrorBuffer.getInstance().add(sqls);
            }
        }
    }

    @SuppressWarnings("static-access")
	public void run() {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[DBBuf]");
//        log.warn(NAME+"当前入库队列大小为:" + sqlQueue.size());
//        log.info(NAME+"线程"+Thread.currentThread().getId()+"执行开始时间为:"+new Date()));
        while(!shutdownFlag){
            String[] sqls = getSqlsFromQueue();
            if (null == sqls || 0 == sqls.length) {
                //log.debug(NAME+"数据库缓冲队列中暂时没有数据！");
                try { 
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDBQueueIsNull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"数据库缓冲队列处理在休眠时出现异常，" + e);
                }
                continue;
            }
            ExeSqlRunner runner = new ExeSqlRunner(sqls);
            if (!pool.start(runner, pool.HIGH_PRIORITY)) {
                //log.info(NAME+"用于执行DB数据入库的线程池已满！将该批待执行sql重新放入缓存中，并休眠"
                  //      + Config.props.getProperty("sleepTimeWhenDBThreadPoolFull") + "毫秒!");
                add(sqls);
                try {
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDBThreadPoolFull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"数据库缓冲队列处理在休眠时出现异常，" + e);
                }
            }else{
//            	log.info(NAME+"用于执行DB数据入库的线程正常启动！");
            }
            //log.warn(NAME+"当前入库队列大小为:" + sqlQueue.size());
        }      
//        log.warn(NAME+"数据库缓冲队列处理终止！");
    }

    private String[] getSqlsFromQueue() {
    	
        int curQueueSize = sqlQueue.size();
        if (curQueueSize > 0) {
            int count = 0;
            if (curQueueSize <= Integer.parseInt(Config.props.getProperty("countOfExeSqlPerTime"))) { // 不够或刚够一页数据
                count = curQueueSize;
            } else { //多于一页数据
                count = Integer.parseInt(Config.props.getProperty("countOfExeSqlPerTime"));
            }
            String[] sqls = new String[count];
            
            synchronized (this) {
                for (int i = 0; i < count; i++) {
                    sqls[i] = sqlQueue.poll();
                }
            }
            
            //log.info(NAME+"从数据库缓冲队列中取出" + count + "个sql语句。");
            return sqls;

        } else {
            log.debug(NAME+"数据库缓冲队列目前为空！");
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
