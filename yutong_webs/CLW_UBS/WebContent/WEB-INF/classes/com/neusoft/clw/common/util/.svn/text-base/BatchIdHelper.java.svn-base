/*******************************************************************************
 * @(#)BatchIdHelper.java Sep 12, 2008
 * 生成表主键工具类
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author <a href="mailto:lihaiyan@neusoft.com">haiyan.li </a>
 * @version $Revision 1.1 $ Sep 12, 2008 2:05:25 PM
 */
public final class BatchIdHelper {
    private static final int O = 10;

    private static final int OO = 100;

    private static final int MAX_INT = 999999999;

    private static int squ = 0;

    private Date date = null;

    private static SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(
            "yyyyMMddHHmmss");

    private static SimpleDateFormat dateTimeFormatterForLog = new SimpleDateFormat(
            "yyyyMMddHHmmssSSS");

    private BatchIdHelper() {
    }

    private static BatchIdHelper messageIdHelper = new BatchIdHelper();

    public static BatchIdHelper getInstance() {
        return messageIdHelper;
    }

    /**
     * 普通表主键生成器
     * @return String
     */
    public synchronized String getBatchId() {
        date = new Date();
        return (dateTimeFormatter.format(date));
    }

    /**
     * 日志表主键生成器
     * @return
     */
    public synchronized String getLogBatchId() {
        date = new Date();
        return (dateTimeFormatterForLog.format(date));
    }

    /**
     * 告警表主键生成器
     * @return
     */
    public synchronized String getWarnBatchId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        Date now = new Date();
        String timeStamp = "nm" + sdf.format(now);
        squ++;
        if (squ > MAX_INT) {
            squ = 0;
        }
        String warnID = timeStamp + squ / OO % O + squ / O % O + squ % O;
        return warnID;
    }
}
