/*******************************************************************************
 * @(#)UserOperateLogVo.java Sep 12, 2008
 * �û�������־VO��
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util.log.ds;

/**
 * @author <a href="mailto:lihaiyan@neusoft.com">haiyan.li </a>
 * @version $Revision 1.1 $ Sep 12, 2008 3:05:25 PM
 */
public class UserOperateLog {

    /**
     * 日志ID
     */
    private String logId;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 操作IP
     */
    private String operateIp;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 企业名称
     */
    private String enterpriseName;

    /**
     * 所属应用系统
     */
    private String applyId;

    /**
     * 所属模块
     */
    private String moduleId;

    /**
     * 操作时间
     */
    private String operateTime;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 操作描述
     */
    private String OperateDesc;
    
    /**分辨率**/
    private String resolution;
    /**操作系统**/
    private String operatesys;
    /**浏览器**/
    private String browser;
    /**FLASH版本**/
    private String flashver;

    public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getOperatesys() {
		return operatesys;
	}

	public void setOperatesys(String operatesys) {
		this.operatesys = operatesys;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getFlashver() {
		return flashver;
	}

	public void setFlashver(String flashver) {
		this.flashver = flashver;
	}

	/**
     * 获取日志ID
     * @return 日志ID
     */
    public String getLogId() {
        return logId;
    }

    /**
     * 设置日志ID
     * @param logId 日志ID
     */
    public void setLogId(String logId) {
        this.logId = logId;
    }

    /**
     * 获取用户编码
     * @return userId 用户编码
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编码
     * @param userId 用户编码
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名称
     * @return userName 用户名称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名称
     * @param userName 用户名称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取操作IP
     * @return operateIp 操作IP
     */
    public String getOperateIp() {
        return operateIp;
    }

    /**
     * 设置操作IP
     * @param operateIp 操作IP
     */
    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    /**
     * 获取企业ID
     * @return enterpriseId 企业ID
     */
    public String getEnterpriseId() {
        return enterpriseId;
    }

    /**
     * 设置企业ID
     * @param enterpriseId 企业ID
     */
    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    /**
     * 获取企业名称
     * @return enterpriseName 企业名称
     */
    public String getEnterpriseName() {
        return enterpriseName;
    }

    /**
     * 设置企业名称
     * @param enterpriseName 企业名称
     */
    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    /**
     * 获取所属应用系统
     * @return applyId 所属应用系统
     */
    public String getApplyId() {
        return applyId;
    }

    /**
     * 设置所属应用系统
     * @param applyId 所属应用系统
     */
    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * 获取所属模块
     * @return moduleId 所属模块
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * 设置所属模块
     * @param moduleId 所属模块
     */
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    /**
     * 获取操作时间
     * @return operateTime 操作时间
     */
    public String getOperateTime() {
        return operateTime;
    }

    /**
     * 设置操作时间
     * @param operateTime 操作时间
     */
    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    /**
     * 获取操作类型
     * @return operateType 操作类型
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取操作描述
     * @return OperateDesc 操作描述
     */
    public String getOperateDesc() {
        return OperateDesc;
    }

    /**
     * 设置操作描述
     * @param operateDesc 操作描述
     */
    public void setOperateDesc(String operateDesc) {
        OperateDesc = operateDesc;
    }

}
