/*******************************************************************************
 * @(#)UserOperateLogVo.java Sep 16, 2008
 * 记录用户操作类
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util.log.service.impl;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.dao.Dao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.util.BatchIdHelper;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.log.ds.UserOperateLog;
import com.neusoft.clw.common.util.log.service.OperateLogRecorder;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;

/**
 * @author <a href="mailto:lihaiyan@neusoft.com">haiyan.li </a>
 * @version $Revision 1.1 $ Sep 16, 2008 2:05:25 PM
 */
public class OperateLogRecorderImpl implements OperateLogRecorder {

    private static final long serialVersionUID = -4108810191966829778L;

    // private final Log log = LogFactory.getLog(OperateLogRecorderImpl.class);
    private final Logger log = Logger.getLogger(OperateLogRecorderImpl.class);

    private Dao trackDao = null;

    /**
     * 操作日志记录
     */
    public void record(UserInfo user, String ipAddr, String actiondesc,
            String operType, String applyId, String moduleId) {

        if (log.isDebugEnabled()) {
            log.debug("record.............................");
        }
        try {
            UserOperateLog userOperateLog = null;
            if (null != user) {
                userOperateLog = assemble(user, ipAddr, actiondesc, operType,
                        applyId, moduleId);
            }
            if (null != userOperateLog) {
                write(userOperateLog);
            }
        } catch (Throwable t) {
            log.error("write operation log incur a exception" + t);
        }
    }

    private void write(UserOperateLog userOperateLog)
            throws DataAccessIntegrityViolationException, DataAccessException {
        // 日志信息数据库插入操作
        this.trackDao.insert(UserOperateLog.class, userOperateLog);
    }

    private UserOperateLog assemble(UserInfo user, String ipAddr,
            String actiondesc, String operType, String applyId, String moduleId) {
        UserOperateLog userOperateLog = assemble(user, ipAddr, actiondesc,
                applyId, moduleId);
        operType = (null == operType ? " " : operType);
        userOperateLog.setOperateType(operType);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user, String ipAddr,
            String actiondesc, String applyId, String moduleId) {
        UserOperateLog userOperateLog = assemble(user, ipAddr, applyId,
                moduleId);
        actiondesc = (null == actiondesc ? " " : actiondesc);
        // 设置操作日志对象属性操作描述
        userOperateLog.setOperateDesc(actiondesc);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user, String ipAddr,
            String applyId, String moduleId) {
        UserOperateLog userOperateLog = assemble(user, applyId, moduleId);
        ipAddr = (null == ipAddr ? " " : ipAddr);
        // 设置操作日志对象属性IP
        userOperateLog.setOperateIp(ipAddr);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user, String applyId,
            String moduleId) {
        UserOperateLog userOperateLog = assemble(user, moduleId);
        applyId = (null == applyId ? " " : applyId);
        // 设置操作日志对象属性所属应用系统
        userOperateLog.setApplyId(applyId);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user, String moduleId) {
        UserOperateLog userOperateLog = assemble(user);
        moduleId = (null == moduleId ? " " : moduleId);
        // 设置操作日志对象属性所属模块
        userOperateLog.setModuleId(moduleId);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user) {
        UserOperateLog userOperateLog = null;
        if (null != user) {
            userOperateLog = new UserOperateLog();
            // 设置操作日志对象企业ID
            userOperateLog.setEnterpriseId(user.getEntiID());
            // 设置操作日志对象属性企业名称
            userOperateLog.setEnterpriseName(user.getFullName());
            // 设置操作日志对象日志ID
            userOperateLog.setLogId(UUIDGenerator.getUUID());
            // 设置操作日志对象用户ID
            userOperateLog.setUserId(user.getUserID());
            // 设置操作日志对象用户名称
            userOperateLog.setUserName(user.getLoginName());
            // 设置操作日志对象操作时间
            userOperateLog.setOperateTime(BatchIdHelper.getInstance()
                    .getBatchId());
            //add by cj
            userOperateLog.setResolution(user.getResolution());
            userOperateLog.setOperatesys(user.getOperatesys());
            userOperateLog.setBrowser(user.getBrowser());
            userOperateLog.setFlashver(user.getFlashver());
        }
        return userOperateLog;
    }

    /**
     * @param trackDao The trackDao to set.
     */
    public void setTrackDao(Dao trackDao) {
        this.trackDao = trackDao;
    }

}
