/*******************************************************************************
 * @(#)UpLoadDataReq.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.msg.req;

import com.yutong.clw.nio.msg.up.InsideMsg;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午06:13:02
 */
public class QueryTerminalReq extends InsideMsg {
    public static final String COMMAND = "0012";

    public static final String STATUS = "0000";

    public static final int TERMINALIDSIZE = 20;

    public static final int PACKETLENSIZE = 8;

    public static final int COUNTSIZE = 4;

    private String terminalId;

    private String terminalCount;

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getTerminalCount() {
        return terminalCount;
    }

    public void setTerminalCount(String terminalCount) {
        this.terminalCount = terminalCount;
    }

    @Override
    public byte[] getBytes() {
        int location = 0;
        int len = Integer.parseInt(this.getMsgLength());
        byte[] buf = new byte[len];
        byte[] header = super.getBytes();
        System.arraycopy(header, 0, buf, location, header.length);
        location += header.length;
        System.arraycopy(this.getTerminalCount().getBytes(), 0, buf, location, COUNTSIZE);
        location += COUNTSIZE;
        System.arraycopy(this.getTerminalId().getBytes(), 0, buf, location, Integer.parseInt(this
                .getTerminalCount())
                * TERMINALIDSIZE);
        return buf;
    }
}
