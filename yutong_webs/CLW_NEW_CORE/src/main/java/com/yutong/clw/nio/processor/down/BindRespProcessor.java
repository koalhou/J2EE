/*******************************************************************************
 * @(#)BindRespProcessor.java 2008-11-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.processor.down;

//import com.neusoft.tag.app.inside.msg.resp.BindResp;
//import com.neusoft.tag.app.inside.processor.AbstractInsideProcessor;
import com.yutong.clw.beans.exceptions.HandleException;
import com.yutong.clw.beans.exceptions.InvalidMessageException;
import com.yutong.clw.beans.exceptions.ParseException;
import com.yutong.clw.nio.mina.impl.CommunicateService;
import com.yutong.clw.nio.msg.resp.BindResp;
import com.yutong.clw.nio.processor.AbstractInsideProcessor;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-11-24 下午03:30:48
 */
public final class BindRespProcessor extends AbstractInsideProcessor<BindResp, CommunicateService> {

    private static final BindRespProcessor PROCESSOR = new BindRespProcessor();

    private BindRespProcessor() {
    }

    public static BindRespProcessor getInstance() {
        return PROCESSOR;
    }

    public BindResp parse(byte[] buf) throws ParseException {
        try {
            BindResp resp = new BindResp();
            super.parseHeader(buf, resp);
            return resp;
        } catch (Throwable t) {
            throw new ParseException("parse bind response failed.", t);
        }
    }

    public void valid(BindResp msg) throws InvalidMessageException {
        super.validHeader(msg);
    }

    public void handle(BindResp msg, CommunicateService communicateService) throws HandleException {
    	msg = null;
    }
}
