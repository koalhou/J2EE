/*******************************************************************************
 * @(#)OperationLogger.java 2008-4-18
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util.log.service;

import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;

/**
 * @author <a href="mailto:hanbj@neusoft.com">baojun.han </a>
 * @version $Revision 1.1 $ 2008-4-18 01:52:08
 */
public interface OperateLogRecorder {

    // 操作日志KEY
    String YTP_USER_OPERATION_LOG = "YTP_USER_OPERATION_LOG";

    // 事务ID KEY
    String YTP_USER_OPER_TYPE = "YTP_USER_OPER_TYPE";

    // 日志KEY - 所属应用系统
    String YTP_USER_OPER_APPLY_ID = "YTP_USER_OPER_APPLY_ID";

    // 日志KEY - 所属模块
    String YTP_USER_OPER_MODULE_ID = "YTP_USER_OPER_MODULE_ID";

    /**
     * 日志记录
     * @param user 用户信息
     * @param ipAddr IP地址
     * @param actiondesc 日志描述
     * @param type 操作类型
     * @param applyId 所属应用系统
     * @param moduleId 所属模块
     */
    void record(UserInfo user, String ipAddr, String actiondesc, String type,
            String applyId, String moduleId);
}
