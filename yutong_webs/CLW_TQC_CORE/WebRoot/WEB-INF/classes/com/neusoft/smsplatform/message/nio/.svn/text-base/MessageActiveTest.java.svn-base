/*******************************************************************************
 * @(#)HeartBeat.java 2008-8-27
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.nio;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.smsplatform.configuration.MessageConfig;
import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.resp.MessageActiveTestResp;
import com.neusoft.smsplatform.nio.client.ISmsCommunicateService;

public class MessageActiveTest {

    private Logger log = LoggerFactory.getLogger(MessageActiveTest.class);

    private ISmsCommunicateService nioService;

    private byte[] activeTestMsg;

    private int activeTestCurrentNum;

    private Timer timer = new Timer("SmsActiveTestTimer");

    private SmsActiveTestTimerTask task;

    public MessageActiveTest(ISmsCommunicateService nioService, byte[] activeTestMsg) {
        this.nioService = nioService;
        this.activeTestMsg = activeTestMsg;
    }

    /**
     * start the heart beat timer
     */
    public void start() {
        task = new SmsActiveTestTimerTask();
        long interval = Long.parseLong(MessageConfig.props.getProperty("smsactiveTestInterval"));
        timer.schedule(task, 0, interval * Constant.SECOND);
        log.info(LogFormatter
                .formatMsg("Sms ActiveTest", "start the sms active message timer task."));
    }

    /**
     * cancel the heart beat timer task
     */
    public void cancel() {
        task.cancel();
        timer.cancel();
    }

    /**
     * active test timer task, it will send active test message when there is no mesasge on the
     * connection.
     * @author <a href="mailto:pud@neusoft.com">pu dong </a>
     * @version $Revision 1.1 $ 2008-9-25 上午11:00:13
     */
    class SmsActiveTestTimerTask extends TimerTask {

        @Override
        public void run() {
            try {
                synchronized (this) {
                    int max_num = Integer.parseInt(MessageConfig.props
                            .getProperty("smsactiveTestMaxNum"));
                    if (activeTestCurrentNum >= max_num) {
                        nioService.close();
                        log.info(LogFormatter.formatMsg("Sms ActiveTest",
                                "there is no active test response message for "
                                        + MessageConfig.props.getProperty("smsactiveTestMaxNum")
                                        + " times. the connection has been disconnected by me."));
                        this.cancel();
                    } else {
                        if (activeTestCurrentNum >= 1) {
                            nioService.send(activeTestMsg);
                            log.info(LogFormatter.formatMsg("Sms ActiveTest",
                                    "send message active test message.currentNum=" + activeTestCurrentNum));
                        }
                        activeTestCurrentNum++;
                    }
                }
            } catch (Throwable t) {
                cancel();
                log.error(LogFormatter.formatMsg("Sms ActiveTest",
                        "sms activeTest has some problem."), t);
            }
        }
    }

    /**
     * deal with the active test response
     */
    public void doActiveTestResp(byte[] buf) {
    	String status = getResult(buf);
    	if(status.equals(MessageActiveTestResp.ACTIVETEST_SUCCESS)){
    		clear();
    	}
    	log.info(LogFormatter.formatMsg("Sms ActiveTest",
                "receive a sms active test response message.currentNum=" + activeTestCurrentNum));
        
    }

    /**
     * clear the current number, it stands for there are some message on the connection
     */
    public synchronized void clear() {
        activeTestCurrentNum = 0;
    }
    
    private String getResult(byte[] msg) {
		byte[] resultBuf = new byte[MessageActiveTestResp.RESULTSIZE];
		System.arraycopy(msg, MesssageInsideMsg.MSGHEADERSIZE, resultBuf, 0,
				MessageActiveTestResp.RESULTSIZE);
		return new String(resultBuf);
	}
}
