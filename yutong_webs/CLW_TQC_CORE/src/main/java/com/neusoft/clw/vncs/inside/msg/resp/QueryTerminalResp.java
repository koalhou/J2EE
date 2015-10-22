/*******************************************************************************
 * @(#)UploadDataResp.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.inside.msg.resp;

import com.neusoft.clw.vncs.inside.msg.InsideMsg;


/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午06:13:28
 */
public class QueryTerminalResp extends InsideMsg {

	public static final String COMMAND = "1012";

	public static final String STATUS = "0000";

	public static final int TERMINALIDSIZE = 20;

	public static final int TERMINALCOUNTIZE = 4;

	private  String terminalCount;

	private  String[] terminalStatus;

	public String getTerminalCount() {
		return terminalCount;
	}

	public void setTerminalCount(String terminalCount) {
		this.terminalCount = terminalCount;
	}

    public String[] getTerminalStatus() {
        return terminalStatus;
    }

    public void setTerminalStatus(String[] terminalStatus) {
        this.terminalStatus = terminalStatus;
    }

	
}
