/*******************************************************************************
 * @(#)IInsideProcessor.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.inside.processor;

//import com.neusoft.communicate.ICommunicateService;
//import com.neusoft.tag.app.inside.msg.InsideMsg;
import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.exception.InvalidMessageException;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.nio.client.ICommunicateService;
import com.neusoft.clw.vncs.inside.msg.InsideMsg;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午06:08:10
 */
public interface IInsideProcessor<E extends InsideMsg, V extends ICommunicateService> {

    /**
     * check whether the msg received is valid
     * @param msg
     * @throws InvalidMessageException
     */
    void valid(E msg) throws InvalidMessageException;

    /**
     * parse the message received
     * @param buf
     * @return
     * @throws ParseException
     */
    E parse(byte[] buf) throws ParseException;

    /**
     * handle the message received
     * @param msg
     * @param session
     * @throws HandleException
     */
    void handle(E msg, V communicateService) throws HandleException;
}
