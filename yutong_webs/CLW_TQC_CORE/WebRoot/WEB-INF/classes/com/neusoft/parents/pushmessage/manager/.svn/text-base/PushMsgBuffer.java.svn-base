package com.neusoft.parents.pushmessage.manager;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.pushmessage.domain.PushMessageCls;
import com.neusoft.parents.utils.JacksonUtils;
import com.neusoft.smsplatform.configuration.MessageConfig;
import com.neusoft.smsplatform.message.buffer.sync.SyncThreadPool;


public class PushMsgBuffer implements Runnable{
	private static Logger log = LoggerFactory.getLogger(PushMsgBuffer.class);

    private static final PushMsgBuffer pushMsgBuffer = new PushMsgBuffer();
    
    private static final String NAME = "<PushMsgBuffer>";

    private Queue<PushMessageCls> pushMsgBufferQueue;

    private SyncThreadPool pool = SyncThreadPool.instance();
    
    private boolean shutdownFlag = true; 
    
    private IParentsDAO parentsDAO = null;

    private PushMsgBuffer() {
    	pushMsgBufferQueue = new LinkedList<PushMessageCls>();
        parentsDAO = (IParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
        shutdownFlag = false;
    }

    public static PushMsgBuffer getInstance() {
        return pushMsgBuffer;
    }

    /**
     * 向队列中加入一条推送消息
     * @param sql
     */
    public synchronized void add(PushMessageCls msgdata) {
    	pushMsgBufferQueue.offer(msgdata);
    }

    /**
     * 向队列中加入一批推送消息
     * @param sqlList
     */
    public synchronized void add(List<PushMessageCls> msgdatas) {
        for(PushMessageCls cc:msgdatas){
        	pushMsgBufferQueue.add(cc);
        }
    }


    class ExeSqlRunner implements Runnable {
        private List<PushMessageCls> msgs;

        public ExeSqlRunner(List<PushMessageCls> msgdatas) {
            this.msgs = msgdatas;
        }   
        public void run() {
            try {
                log.info(NAME+"开始将发送" + msgs.size() + "个同步信息到消息推送平台！");
                // 批量发送
                batchSend(msgs);
                log.info(NAME+"已成功将发送" + msgs.size() + "个同步信息到消息推送平台！");
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
        while(!shutdownFlag){
            List<PushMessageCls> msgs = getPushMessageDatasFromQueue();
            if (null == msgs || 0 == msgs.size()) {
                log.debug(NAME+"同步信息发送队列中暂时没有数据！");
                try { 
                    //Thread.sleep(Long.parseLong(MessageConfig.props.getProperty("sleepTimeWhenSyncQueueIsNull")));
                	//Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenSyncQueueIsNull")));
                	Thread.sleep(Long.parseLong("10"));
                } catch (InterruptedException e) {
                    log.error(NAME+"同步信息发送队列处理在休眠时出现异常，" + e);
                }
                continue;
            }
            ExeSqlRunner runner = new ExeSqlRunner(msgs);
            if (!pool.start(runner, pool.HIGH_PRIORITY)) {
                log.info(NAME+"用于发送同步信息的线程池已满！将该批待发送同步信息重新放入缓存中，并休眠"
                        + MessageConfig.props.getProperty("sleepTimeWhenSyncThreadPoolFull") + "毫秒!");
                add(msgs);
                try {
                    Thread.sleep(Long.parseLong(MessageConfig.props.getProperty("sleepTimeWhenSyncThreadPoolFull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"同步信息发送队列处理在休眠时出现异常，" + e);
                }
            }else{
    			//
            }
            log.warn(NAME+"当前同步信息发送队列大小为:" + pushMsgBufferQueue.size());
        }      
    }


	public void batchSend(List<PushMessageCls> msgs) throws UnsupportedEncodingException {
        for(PushMessageCls msg:msgs){
            PushMessageCls newcls=new PushMessageCls();
            newcls.setCld_id(msg.getCld_id());
            newcls.setContent(msg.getContent());
            newcls.setContentStr(msg.getContentStr());
            newcls.setEvent_time(msg.getEvent_time());
            newcls.setId(msg.getId());
            newcls.setRule_id(msg.getRule_id());
            newcls.setUser_code(msg.getUser_code());
            if(newcls.getUser_code()!=null&&newcls.getUser_code()!=""&& newcls.getContent()!=null ){
                try
                {
                    String jsonStr= JacksonUtils.toJson(newcls);
                    NotificationManager.getInstance().push2SingleUser(msg.getUser_code(), jsonStr);
                    newcls.setContent(null);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
        
            }else{
                log.info(NAME+",该条消息非法。消息内容为:"+msg.getContent());
            }
        }
    }


	private List<PushMessageCls> getPushMessageDatasFromQueue() {
    	
        int curQueueSize = pushMsgBufferQueue.size();
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
            
            List<PushMessageCls> msgs = new ArrayList<PushMessageCls>();
            
            synchronized (this) {
                for (int i = 0; i < count; i++) {
                	msgs.add(pushMsgBufferQueue.poll());
                }
            }
            
            log.info(NAME+"从同步信息发送队列中取出" + count + "个同步信息。");
            return msgs;

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
