/*******************************************************************************
 * @(#)MessageFactory.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.inside.msg;

import com.neusoft.smsplatform.configuration.MessageConfig;
import com.neusoft.smsplatform.message.inside.msg.req.MessageActiveTestReq;
import com.neusoft.smsplatform.message.inside.msg.req.MessageBindReq;
import com.neusoft.smsplatform.message.inside.msg.resp.MessageActiveTestResp;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;


public final class MessageInsideMsgFactory {

    private MessageInsideMsgFactory() {
    }

    public static MessageActiveTestReq createActiveTestReq() {
        MessageActiveTestReq activeTest = new MessageActiveTestReq();

        activeTest.setMsgLength(String.valueOf(MessageActiveTestReq.MSGSIZE));
        activeTest.setCommand(MessageActiveTestReq.COMMAND);
        activeTest.setSeqLength(SmsCommonMsgUtils.getSeq());
//        activeTest.setCommand(ActiveTestReq.COMMAND);
//        activeTest.setEndFlag(ActiveTestReq.ENDFLAG);
//        activeTest.setStartFlag(ActiveTestReq.STARTFLAG);
//        activeTest.setCenterId(ActiveTestReq.CENTERID);
        return activeTest;
    }

    public static MessageActiveTestResp createActiveTestResp(String seq) {
        MessageActiveTestResp resp = new MessageActiveTestResp();
        resp.setMsgLength(String.valueOf(MessageActiveTestResp.MSGSIZE));      
        return resp;
    }

    public static MessageBindReq createBindReq() {
        MessageBindReq req = new MessageBindReq();
        req.setSeqLength(SmsCommonMsgUtils.getSeq());
        req.setCommand(MessageBindReq.COMMAND);
        req.setMsgLength(String.valueOf(MessageBindReq.MSGHEADERSIZE + MessageBindReq.USERIDSIZE + MessageBindReq.PASSWORDSIZE));
        req.setUserId(MessageConfig.getProperties("smsusername"));
        req.setPassword(MessageConfig.getProperties("smspassword"));
        return req;
    }  
    public static void main(String[] args) {
       
    }
}
