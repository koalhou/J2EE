/*******************************************************************************
 * @(#)ActiveTestRequest.java 2008-10-22
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.inside.msg.req;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;




public class MessageActiveTestReq extends MesssageInsideMsg {

    public static final String COMMAND = "02";
        
    public static final int MSGSIZE = 14;

    public byte[] getBytes() {
        int location = 0;
        byte[] buf = new byte[MSGSIZE];
        System.arraycopy(this.getMsgLength().getBytes(), 0, buf, location, MSGLENSIZE);
        location += MSGLENSIZE;
        System.arraycopy(this.getCommand().getBytes(), 0, buf, location, COMMANDSIZE);
        location += COMMANDSIZE;
        System.arraycopy(this.getSeqLength().getBytes(), 0, buf, location, SEQUENCESIZE);
        location+=SEQUENCESIZE;
        return buf;
    }
}
