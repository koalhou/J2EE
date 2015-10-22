/*******************************************************************************
 * @(#)HeartBeat.java 2008-8-27
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.msg.monitor;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.nio.mina.impl.CommunicateService;
import com.yutong.clw.utils.LogFormatter;
 
/**
 * @author <a href="mailto:tianmc@neusoft.com">tianmc </a>
 * @version $Revision 1.0 $ 2010-5-27 下午01:00:06
 */
public class VgSendDataService {
	private UtilInfoList utilInfoList = UtilInfoList.getInstance(); 
    private Logger log = LoggerFactory.getLogger(VgSendDataService.class);
	private SendInfoList sendInfo = SendInfoList.getInstance();
    private CommunicateService nioService;
    @SuppressWarnings("unused")
	private byte[] activeTestMsg;
    @SuppressWarnings("unused")
	private int activeTestCurrentNum;
    private Timer timer = new Timer("SendTask");
    private SendTask task;

    public VgSendDataService(CommunicateService nioService, byte[] activeTestMsg) {
        this.nioService = nioService;
        this.activeTestMsg = activeTestMsg;
    }

    /**
     * start the heart beat timer
     */
    public void start() {
        task = new SendTask();    
        timer.schedule(task, 0, 100);
        log.info(LogFormatter
                .formatMsg("VgSendDataService SendTask", "start the active message timer task."));
    }

    /**
     * cancel the heart beat timer task
     */
    public void cancel() {
        task.cancel();
        timer.cancel();
    }

    /**
     * @author <a href="mailto:tianmc@neusoft.com">tianmc </a>
     * @version $Revision 1.0 $ 2010-5-27 下午01:00:06
     */
    class SendTask extends TimerTask {
        @Override
        public void run() {
        	SendBean sendBean = null;
            try {
                synchronized (this) {
                	boolean sflag = true;
                	
        			while (sflag) {  				
//        				log.info("---------取数据---------");
        				sendBean = sendInfo.remove("SENDCMD");// 取得一条数据
    					if (sendBean == null) {
    						sflag = false;
    						break;
    					}
    					 nioService.send(sendBean.getSendData()); 		 
    					 log.info("向vg发送终端数据:"+new String(sendBean.getSendData(),0,sendBean.getSendData().length));
//    					 发送成功 处理  	
    					 utilInfoList.add(newsql(0,sendBean), "UPDATESQL");
        			}
                    }
                
            } catch (Throwable t) {
//            	发送失败
            	utilInfoList.add(newsql(-1,sendBean), "UPDATESQL");
                cancel();
                log.error(LogFormatter.formatMsg("VgSendDataService SendTask",
                        "activeTest has some problem."), t);
            }
        }
    }
    /**
     * make update sql  type (0:success,-1:fail)
     */
    public String newsql(int flag,SendBean sendBean) {
    	String sql=null;
    	if(sendBean!=null){
	    	if(flag==0){
	    		if(sendBean.sendData.equals("admin")){
	    			sql="update LNJT_CR_SEND_COMMAND set IF_SEND='1010' ,SENDDATE=sysdate where id='"+sendBean.getCmdbean().getID()+"'";
	    		}else{
	    			//sql="update LNJT_CR_SEND_COMMAND set IF_SEND=1 ,SENDDATE=sysdate where id='"+sendbean.getCmdbean().getID()+"'";   			 
	    		}  		
	    	}else{
	    		if(sendBean.sendData.equals("admin")){
	    			sql="update LNJT_CR_SEND_COMMAND set IF_SEND='1020' ,SENDDATE=sysdate where id='"+sendBean.getCmdbean().getID()+"'";
	    		}else{
	    			//sql="update LNJT_CR_SEND_COMMAND set IF_SEND=1 ,SENDDATE=sysdate where id='"+sendbean.getCmdbean().getID()+"'";   			 
	    		}
	    	}
    	}
       return sql;
    }
    /**
     * deal with the active test response
     */
    public void doActiveTestResp() {
        log.info(LogFormatter.formatMsg("VgSendDataService SendTask",
                "receive a active test response message.currentNum="));
    }

    /**
     * clear the current number, it stands for there are some message on the connection
     */
    public synchronized void clear() {
        activeTestCurrentNum = 0;
    }
}
