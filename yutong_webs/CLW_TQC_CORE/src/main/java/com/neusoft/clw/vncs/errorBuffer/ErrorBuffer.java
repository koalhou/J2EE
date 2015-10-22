package com.neusoft.clw.vncs.errorBuffer;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.ICommonDAO;
import com.neusoft.clw.vncs.util.StringUtil;

public class ErrorBuffer implements Runnable{

	private static Logger log = LoggerFactory.getLogger(ErrorBuffer.class);

    private static final ErrorBuffer dbBuffer = new ErrorBuffer();
    
    private static final String NAME = "<ErrorBuffer>";

    private BlockingQueue<String> sqlQueue;

    private ICommonDAO commonDAO;

    private ErrorBufferThreadPool pool = ErrorBufferThreadPool.instance();
    
    private boolean shutdownFlag = true; 

    private ErrorBuffer() {
        sqlQueue = new LinkedBlockingQueue<String>(Integer.parseInt(Constant.errorQueuesize));
        commonDAO = (ICommonDAO) SpringBootStrap.getInstance().getBean("commonDAO");
        shutdownFlag = false;
    }

    public static ErrorBuffer getInstance() {
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
                log.info(NAME+"开始将错误队列中的" + sqls.length + "个sql处理");
                // 批量入库
                commonDAO.batchUpdate(sqls);
                log.info(NAME+"已成功将错误队列中的" + sqls.length + "个sql处理！");
            } catch (DataAccessException e) {
                log.error(NAME+"错误队列批量入库时出现数据库异常："+e+",sql="+sqls[0]);
            } catch (Exception e) {
                log.error(NAME+"错误队列批量入库时出现系统异常："+e+",sql="+sqls[0]);
            }
        }
    }

    @SuppressWarnings("static-access")
	public void run() {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[ErrorBuf]");
//        log.warn(NAME+"当前入库队列大小为:" + sqlQueue.size());
//        log.info(NAME+"线程"+Thread.currentThread().getId()+"执行开始时间为:"+new Date()));
        while(!shutdownFlag){
        	String[] sqls = new String[1];
            
            try {
				sqls[0]=sqlQueue.take();
				log.info(NAME+"从错误数据队列中取出" + sqls.length + "个sql语句,sql="+sqls[0]);
			} catch (InterruptedException e1) {
				log.error(NAME+"runnable 发生异常,sql="+sqls[0]);
			}
//            if (null == sqls || 0 == sqls.length) {
//                log.debug(NAME+"数据库缓冲队列中暂时没有数据！");
//                try { 
//                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDBQueueIsNull")));
//                } catch (InterruptedException e) {
//                    log.error(NAME+"数据库缓冲队列处理在休眠时出现异常，" + e);
//                }
//                continue;
//            }
            ExeSqlRunner runner = new ExeSqlRunner(sqls);
            if (!pool.start(runner, pool.HIGH_PRIORITY)) {
                log.info(NAME+"用于执行错误数据入库的线程池已满！将该批待执行sql重新放入错误队列中，并休眠"
                        + Config.props.getProperty("sleepTimeWhenErrorThreadPoolFull") + "毫秒!");
                add(sqls);
                try {
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenErrorThreadPoolFull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"错误队列处理在休眠时出现异常，" + e);
                }
            }else{
//            	log.info(NAME+"用于执行DB数据入库的线程正常启动！");
            }
            log.warn(NAME+"当前错误队列大小为:" + sqlQueue.size());
        }
    }

//    private String[] getSqlsFromQueue() throws InterruptedException {
    	
//        int curQueueSize = sqlQueue.size();
//        if (curQueueSize > 0) {
//            int count = 0;
//            if (curQueueSize <= Integer.parseInt(Config.props.getProperty("countOfExeSqlPerTime"))) { // 不够或刚够一页数据
//                count = curQueueSize;
//            } else { //多于一页数据
//                count = Integer.parseInt(Config.props.getProperty("countOfExeSqlPerTime"));
//            }
//            String[] sqls = new String[1];
//            
//            sqls[0]=sqlQueue.take();
//            
//            log.info(NAME+"从错误数据队列中取出" + sqls.length + "个sql语句。");
//            return sqls;

//        } else {
//            log.debug(NAME+"数据库缓冲队列目前为空！");
//            return null;
//        }
//    }

    public void shutdown() {
        log.info("<BlockingQueue sqlQueue>开始执行线程池关闭操作");
        shutdownFlag = true;
        
        pool.shutdown();
        log.info("<BlockingQueue sqlQueue>sqlQueue","线程池关闭结束！");
    }

}
