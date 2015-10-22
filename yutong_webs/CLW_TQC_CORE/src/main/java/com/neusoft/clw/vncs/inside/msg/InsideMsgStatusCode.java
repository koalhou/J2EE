/*******************************************************************************
 * @(#)StatusCode.java 2008-9-12
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.inside.msg;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-9-12 下午02:34:55
 */
public final class InsideMsgStatusCode extends ConcurrentLinkedQueue<String> {

    private static final long serialVersionUID = -5614074991876594794L;

    public static final String STATUS_CODE_SUCCESS = "0000";

    public static final String STATUS_COMMAND_WRONG = "0001";

    public static final String STATUS_NO_THIS_COMMAND = "0002";

    public static final String STATUS_INVALID_TERMINAL = "0003";

    public static final String STATUS_TERMINAL_NOT_SUBSCRIBE_BUSINESS = "0004";

    public static final String STATUS_TERMINAL_OFFLINE = "0005";

    public static final String STATUS_TERMINAL_NOT_SUPPORT_COMMAND = "0006";

    public static final String STATUS_CANNOT_SEND = "0007";
    
    public static final String STATUS_NOT_THIS_ELEMENT = "0008";

    private static final InsideMsgStatusCode STATUS = new InsideMsgStatusCode();

    public static InsideMsgStatusCode getInstance() {
        return STATUS;
    }

    private InsideMsgStatusCode() {
        this.add(STATUS_CODE_SUCCESS);
        this.add(STATUS_COMMAND_WRONG);
        this.add(STATUS_NO_THIS_COMMAND);
        this.add(STATUS_INVALID_TERMINAL);
        this.add(STATUS_TERMINAL_NOT_SUBSCRIBE_BUSINESS);
        this.add(STATUS_TERMINAL_OFFLINE);
        this.add(STATUS_TERMINAL_NOT_SUPPORT_COMMAND);
        this.add(STATUS_CANNOT_SEND);
    }
}
