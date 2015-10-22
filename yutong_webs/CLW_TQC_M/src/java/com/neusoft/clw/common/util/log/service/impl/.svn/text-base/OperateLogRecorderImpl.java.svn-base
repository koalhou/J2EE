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
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.log.ds.UserOperateLog;
import com.neusoft.clw.common.util.log.service.OperateLogRecorder;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;

/**
 * @author JinPeng
 */
public class OperateLogRecorderImpl implements OperateLogRecorder {

    private static final long serialVersionUID = -4108810191966829778L;

    // private final Log log = LogFactory.getLog(OperateLogRecorderImpl.class);

    private final Logger log = Logger.getLogger(getClass());

    private Dao trackDao = null;

    public void record(UserInfo user, String ipAddr, String actionModule,
            String actionType, String actionDesc) {
        if (log.isDebugEnabled()) {
            log.debug("record.............................");
        }
        try {
            UserOperateLog userOperateLog = null;
            if (null != user) {
                userOperateLog = assemble(user, ipAddr, actionModule,
                        actionType, actionDesc);
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
        this.trackDao.insert(UserOperateLog.class, userOperateLog);
    }

    private UserOperateLog assemble(UserInfo user, String ipAddr,
            String actionModule, String actionType, String actiondesc) {
        UserOperateLog userOperateLog = assemble(user, ipAddr, actionModule,
                actionType);
        actiondesc = (null == actiondesc ? " " : actiondesc);
        userOperateLog.setOperDesc(actiondesc);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo admin, String ipAddr,
            String actionModule, String actionType) {
        UserOperateLog userOperateLog = assemble(admin, ipAddr, actionModule);
        actionType = (null == actionType ? "" : actionType);
        userOperateLog.setOperType(actionType);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user, String ipAddr,
            String actionModule) {
        UserOperateLog userOperateLog = assemble(user, ipAddr);
        actionModule = (null == actionModule ? "" : actionModule);
        userOperateLog.setOperateModule(actionModule);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user, String ipAddr) {
        UserOperateLog userOperateLog = assemble(user);
        ipAddr = (null == ipAddr ? " " : ipAddr);
        userOperateLog.setHostIp(ipAddr);
        return userOperateLog;
    }

    private UserOperateLog assemble(UserInfo user) {
        UserOperateLog userOperateLog = null;
        if (null != user) {
            userOperateLog = new UserOperateLog();
            userOperateLog.setId(UUIDGenerator.getUUID());
            userOperateLog.setUserId(user.getUserID());
            userOperateLog.setLoginName(user.getLoginName());
            userOperateLog.setEnterpriseId(user.getEntiID());
            userOperateLog.setEnterpriseName((null == user.getEntiName() ? " "
                    : user.getEntiName()));
            userOperateLog.setApplyId(Constants.CLW_M_CODE);
            userOperateLog
                    .setOperTime(BatchIdHelper.getInstance().getBatchId());
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
