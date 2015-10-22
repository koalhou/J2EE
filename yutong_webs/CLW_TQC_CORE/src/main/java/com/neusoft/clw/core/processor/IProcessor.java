/*******************************************************************************
 * @(#)IInsideProcessor.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.core.processor;

//import com.neusoft.communicate.ICommunicateService;
//import com.neusoft.tag.app.inside.msg.InsideMsg;
import com.neusoft.clw.core.nio.NioCommunicateService;
import com.neusoft.clw.core.xmlbean.OlxDocument;
import com.neusoft.clw.exception.HandleException;
import com.neusoft.clw.nio.client.ICommunicateService;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午06:08:10
 */
@SuppressWarnings("hiding")
public interface IProcessor<String , V extends ICommunicateService> {
 
    /**
     * handle the message received
     * @param msg
     * @param session
     * @throws HandleException
     */
    void handle(OlxDocument doc , NioCommunicateService communicateService) throws HandleException;

	void valid(OlxDocument doc);
 

	 
	 
}
