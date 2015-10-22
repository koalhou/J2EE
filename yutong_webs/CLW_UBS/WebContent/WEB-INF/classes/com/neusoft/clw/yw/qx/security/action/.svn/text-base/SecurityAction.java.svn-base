/*******************************************************************************
 * @(#)SecurityAction.java 2008-2-28
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.qx.security.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.yw.qx.security.ds.PermissionInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="mailto:jin.p@neusoft.com">jinp </a>
 * @version $Revision 1.1 $ 2011-2-28 下午02:14:21
 */
public class SecurityAction extends BaseAction implements SessionAware {

    /**
     * 生成序列号
     */
    private static final long serialVersionUID = -7994936652239224404L;

    private transient Service service;

    private Map session;

    private String username;

    private String password;

    private String validateCode = "";

    public SecurityAction() {

    }

    /**
     * 登陆action
     * @return
     */
    public String login() {
        // 设定当前系统的编码集为UTF-8
        System.setProperty("file.encoding", "UTF-8");
        if (log.isDebugEnabled()) {
            log.debug("username is " + username + ", Password is " + password);
        }

        if (null == username || null == password) {
            return ERROR;
        }

        if (null == session) {
            return ERROR;
        }

        // 获取随机生成的验证码
        String validateCodeCrt = (String) session.get("randCheckCode");
        // 核对验证码
        if (null != validateCode && !validateCode.equals(validateCodeCrt)) {
            super.addActionError(getText("login.notValidate"));
            return ERROR;
        }

        try {
            Map < String, String > map = new HashMap < String, String >(2);
            map.put("loginname", username);
            map.put("loginpwd", MD5digest.generatePassword(password));

            // 查看是否存在该用户
            UserInfo admin = (UserInfo) service.getObject(
                    "Security.getUserInfo", map);
            if (null == admin) {
                // 用户名或密码错误，用户不存在时
                super.addActionError(getText("login.invalid"));
                return ERROR;
            } else {
                if (admin.getValideFlg().equals("2")) {
                    // 用户被禁用时
                    super.addActionError(getText("login.user.forbid"));
                    return ERROR;
                }
                // 用户存在时,保存用户信息到session
                session.put(Constants.USER_SESSION_KEY, admin);
            }

            // 获取用户操作权限
            if (setPermissionList(admin)) {
                setOperationType(Constants.LOGIN, ModuleId.CLW_M_LOGIN_MID);
                addOperationLog("登入管理系统");
            } else {
                // 无权限操作
                super.addActionError(getText("login.not.permission"));
                log.info(getText("login.not.permission"));
                return ERROR;
            }

        } catch (Exception ex) {
            super.addActionError(getText("login.db.error"));
            log.error(ex.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 获取当前用户操作权限
     * @param admin 用户信息
     * @return
     * @throws BusinessException
     */
    private boolean setPermissionList(UserInfo admin) throws BusinessException {
        if (admin.getLoginName().equals(Constants.USER_ADMIN_NAME)
                && admin.getEntiID().equals(Constants.YUTONG_ID)) {
            // 判断当前用户是否为超级管理员
            List < PermissionInfo > permissionList = (List < PermissionInfo >) service
                    .getObjects("Security.getAllPermissions",
                            //Constants.CLW_MENU_TOP_ID);
                            Constants.CLW_UB_TOP_ID);//zyong add
            if (!permissionList.isEmpty()) {
                List perPermissionList = new ArrayList();
                for (PermissionInfo permissionInfo : permissionList) {
                    perPermissionList.add(permissionInfo.getModuleId());
                }
                session.put(Constants.PER_URL_LIST, perPermissionList);
            } else {
                return false;
            }
        } else {
            // 非管理员用户，查询当前用户权限
            List < PermissionInfo > permissionList = (List < PermissionInfo >) service
                    .getObjects("Security.getUserPermissions", admin
                            .getUserID());
            if (!permissionList.isEmpty()) {
                List perPermissionList = new ArrayList();
                for (PermissionInfo permissionInfo : permissionList) {
                    perPermissionList.add(permissionInfo.getModuleId());
                }
                session.put(Constants.PER_URL_LIST, perPermissionList);
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * 注销
     * @return
     */
    public String logout() {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpSession httpSession = request.getSession();
        if (null != httpSession) {
            // 清空session对象，add by 庞皓源
            ActionContext.getContext().getSession().clear();
            setOperationType(Constants.LOGOUT, ModuleId.CLW_M_LOGOUT_MID);
            addOperationLog("退出管理系统");
        }
        return SUCCESS;
    }

    /**
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @return Returns the session.
     */
    public Map getSession() {
        return session;
    }

    /**
     * @param session The session to set.
     */
    public void setSession(Map session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return Returns the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取验证码
     * @return
     */
    public String getValidateCode() {
        return validateCode;
    }

    /**
     * 设定验证码
     * @param validateCode 验证码
     */
    public void setValidateCode(String validateCode) {
        this.validateCode = validateCode;
    }
}
