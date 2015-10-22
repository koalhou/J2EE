/*******************************************************************************
 * @(#)IInsideProcessor.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.processor;

//import com.neusoft.communicate.CommunicateService;
//import com.neusoft.tag.app.inside.msg.InsideMsg;
import com.yutong.clw.beans.exceptions.HandleException;
import com.yutong.clw.beans.exceptions.InvalidMessageException;
import com.yutong.clw.beans.exceptions.ParseException;
import com.yutong.clw.nio.mina.impl.CommunicateService;
import com.yutong.clw.nio.msg.up.InsideMsg;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午06:08:10
 */
public interface IInsideProcessor<E extends InsideMsg, V extends CommunicateService> {

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
