/*******************************************************************************
 * @(#)ActiveTestRequestProcessor.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.inside.processor.req;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.impl.ActiveCoreDAO;
import com.neusoft.clw.vncs.inside.msg.InsideMsgFactory;
import com.neusoft.clw.vncs.inside.msg.req.ActiveTestReq;
import com.neusoft.clw.vncs.inside.processor.AbstractInsideProcessor;
import com.neusoft.clw.vncs.nio.CommunicateService;

//import com.neusoft.log.Logformater;
//import com.neusoft.tag.app.inside.msg.InsideMsgFactory;
//import com.neusoft.tag.app.inside.msg.req.ActiveTestReq;
//import com.neusoft.tag.app.inside.processor.AbstractInsideProcessor;
//import com.neusoft.tag.exception.HandleException;
//import com.neusoft.tag.exception.InvalidMessageException;
//import com.neusoft.tag.exception.ParseException;
//import com.neusoft.tag.ota.communicate.CommunicateService;
//import com.neusoft.tag.ota.constant.OtaModule;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午05:24:18
 */
public final class ActiveTestReqProcessor extends
        AbstractInsideProcessor<ActiveTestReq, CommunicateService> {

    private Logger log = LoggerFactory.getLogger(ActiveTestReqProcessor.class);

    public static final ActiveTestReqProcessor PROCESSOR = new ActiveTestReqProcessor();

    private ActiveTestReqProcessor() {
    }

    public static ActiveTestReqProcessor getInstance() {
        return PROCESSOR;
    }

    public ActiveTestReq parse(byte[] buf) throws ParseException {
        try {
            ActiveTestReq req = new ActiveTestReq();
            super.parseHeader(buf, req);
            return req;
        } catch (Throwable t) {
            throw new ParseException("parse active test requset failed.", t);
        }
    }

    public void valid(ActiveTestReq msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(ActiveTestReq msg, CommunicateService nioService) throws HandleException {
        try {
            log.info(LogFormatter.formatMsg("ActiveTestReqProcessor", "receive a active test request."));
            nioService.send(InsideMsgFactory.createActiveTestResp(msg.getSeq()).getBytes());
            log.info(LogFormatter.formatMsg("ActiveTestReqProcessor", "send a active test response."));
            //更新活跃时间
            ActiveCoreDAO activeCoreDAO = (ActiveCoreDAO) SpringBootStrap.getInstance().getBean("activeCoreDAO"); 
            activeCoreDAO.operateParamTable();
            
            msg = null;
        } catch (Throwable t) {
            throw new HandleException("handle active test request failed.", t);
        }
    }
}
