/*******************************************************************************
 * @(#)HeartBeat.java 2008-8-27
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.nio;

import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.nio.client.ICommunicateService;
import com.neusoft.clw.vncs.inside.msg.InsideMsgFactory;

public class ActiveTest {

    private Logger log = LoggerFactory.getLogger(ActiveTest.class);

    private ICommunicateService nioService;

	private byte[] activeTestMsg;

    private int activeTestCurrentNum;

    private Timer timer = new Timer("ActiveTestTimer");

    private ActiveTestTimerTask task;

    public ActiveTest(ICommunicateService nioService, byte[] activeTestMsg) {
        this.nioService = nioService;
        this.activeTestMsg = activeTestMsg;
    }

    public ActiveTest(ICommunicateService nioService) {
        this.nioService = nioService;
    }
    
    /**
     * start the heart beat timer
     */
    public void start() {
        task = new ActiveTestTimerTask();
        long interval = Long.parseLong(Config.props.getProperty("activeTestInterval")) * Constant.SECOND;
        timer.schedule(task, 0, interval);
        log.info(LogFormatter.formatMsg("CLW ActiveTest", "start the active message timer task."));
    }

    /**
     * cancel the heart beat timer task
     */
    public void cancel() {
        task.cancel();
        timer.cancel();
        log.info("CLW ActiveTest","stop the active message timer task");
    }

    /**
     * active test timer task, it will send active test message when there is no mesasge on the
     * connection.
     * @author <a href="mailto:pud@neusoft.com">pu dong </a>
     * @version $Revision 1.1 $ 2008-9-25 上午11:00:13
     */
    class ActiveTestTimerTask extends TimerTask {

        @Override
        public void run() {
            try {
                synchronized (this) {
                    int max_num = Integer.parseInt(Config.props.getProperty("activeTestMaxNum"));
                    if (activeTestCurrentNum >= max_num) {
                        nioService.close();
                        log.info(LogFormatter.formatMsg("Clw ActiveTest",
                                "there is no active test response message for "
                                        + Config.props.getProperty("activeTestMaxNum")
                                        + " times. the connection has been disconnected by me."));
                        this.cancel();
                        log.info("准备进入重连");
                        nioService.reconnect();
                    } else {
                    	activeTestMsg = InsideMsgFactory.createActiveTestReq().getBytes();
                        if (activeTestCurrentNum >= 0) {
                            nioService.send(activeTestMsg);
                            log.info(LogFormatter.formatMsg("Clw ActiveTest",
                                    "send active test message.currentNum=" + activeTestCurrentNum+",activemsg:"+new String(activeTestMsg)));
                        }
                        activeTestCurrentNum++;
                    }
                }
            } catch (Throwable t) {
                cancel();
                log.error(LogFormatter.formatMsg("Clw ActiveTest",
                        "activeTest has some problem."), t);
            }
        }
    }

    /**
     * deal with the active test response
     */
    public void doActiveTestResp() {
        log.info(LogFormatter.formatMsg("CLW ActiveTest",
                "receive a active test response message.currentNum=" + activeTestCurrentNum));
    }

    /**
     * clear the current number, it stands for there are some message on the connection
     */
    public synchronized void clear() {
        activeTestCurrentNum = 0;
    }
}
