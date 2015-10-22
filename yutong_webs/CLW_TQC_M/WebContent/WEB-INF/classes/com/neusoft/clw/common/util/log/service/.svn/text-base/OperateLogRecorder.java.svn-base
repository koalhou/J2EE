/*******************************************************************************
 * @(#)OperationLogger.java 2008-4-18
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util.log.service;

import com.neusoft.clw.yw.qx.security.ds.UserInfo;

/**
 * @author JinPeng
 */
public interface OperateLogRecorder {

    /** 具体操作信息KEY **/
    String CLW_M_OPERATION_INFO = "CLW_M_OPERATION_INFO";

    /** 具体操作类型KEY **/
    String CLW_M_OPERATION_TYPE = "CLW_M_OPERATION_TYPE";

    /** 具体模块ID **/
    String CLW_M_MODULE_ID = "CLW_M_MODULE_ID";

    void record(UserInfo user, String ipAddr, String actionObj,
            String actionType, String actiondesc);
}
