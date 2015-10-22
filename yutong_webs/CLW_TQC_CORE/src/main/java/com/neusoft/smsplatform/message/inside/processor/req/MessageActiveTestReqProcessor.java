/*******************************************************************************
 * @(#)ActiveTestRequestProcessor.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.inside.processor.req;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.smsplatform.message.inside.msg.req.MessageActiveTestReq;
import com.neusoft.smsplatform.message.inside.processor.MessageAbstractInsideProcessor;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;

/**
 * @author chenqiong
 *
 */
public final class MessageActiveTestReqProcessor extends
        MessageAbstractInsideProcessor<MessageActiveTestReq, SmsCommunicateService> {

//    private Logger log = LoggerFactory.getLogger(ActiveTestReqProcessor.class);

    public static final MessageActiveTestReqProcessor PROCESSOR = new MessageActiveTestReqProcessor();

    private MessageActiveTestReqProcessor() {
    }

    public static MessageActiveTestReqProcessor getInstance() {
        return PROCESSOR;
    }

    public MessageActiveTestReq parse(byte[] buf) throws ParseException {
        try {
            MessageActiveTestReq req = new MessageActiveTestReq();
            super.parseHeader(buf, req);
            return req;
        } catch (Throwable t) {
            throw new ParseException("parse active test requset failed.", t);
        }
    }

    public void valid(MessageActiveTestReq msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(MessageActiveTestReq msg, SmsCommunicateService nioService) throws HandleException {
        
    }
}
