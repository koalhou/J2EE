/*******************************************************************************
 * @(#)TimeStamp.java 2009-2-17
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.util;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:zhangmq@neusoft.com">zhangmeiqiu </a>
 * @version $Revision 1.1 $ 2009-2-17 上午03:17:16
 */
public class TimeStamp implements Serializable {

    private static final long serialVersionUID = 8791489630561516044L;

    private Date date = null;

    private static TimeStamp timeStamp = new TimeStamp();

    public static TimeStamp getInstance() {
        return timeStamp;
    }

    private static SimpleDateFormat formatterTOyyMMddHHmmssSSS = new SimpleDateFormat(
            "yyMMddHHmmssSSS");

    private static SimpleDateFormat formatterTOyyMMddHHmmss = new SimpleDateFormat("yyMMddHHmmss");

    public String formatTOyyMMddHHmmssSSS() {
        date = new Date();
        return (formatterTOyyMMddHHmmssSSS.format(date));
    }

    public String formatTOyyMMddHHmmss() {
        date = new Date();
        return (formatterTOyyMMddHHmmss.format(date));
    }

}
