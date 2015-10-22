/*******************************************************************************
 * @(#)ActiveTestResponse.java 2008-10-22
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.inside.msg.resp;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;


public class MessageActiveTestResp extends MesssageInsideMsg {

    public static final String COMMAND = "02";

    public static final String ACTIVETEST_SUCCESS="0";
    
    public static final String ACTIVETEST_ERROR="1";
    
    public static final int MSGSIZE = 15;
    
    public static final int RESULTSIZE = 1;   
}
