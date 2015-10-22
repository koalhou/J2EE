package com.neusoft.clw.yw.qx.security.action;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 修改密码action
 * @author JinPeng
 */
public class ModifyPasswordAction extends BaseAction {
    private transient Service service;

    /** 页面提示信息 **/
    private String message;

    /** 旧密码 **/
    private String oldPwd;

    /** 新密码 **/
    private String newPwd;

    /** 确认新密码 **/
    private String confirmPwd;

    /**
     * 初始化页面
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("modify.password.location"));

        return SUCCESS;
    }

    /**
     * 修改密码执行
     * @return
     */
    public String updateUserPassword() {

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        if (null != currentUser) {
            String password = "";
            try {
                password = (String) service.getObject(
                        "ModifyPassword.getUserPassword", currentUser
                                .getUserID());
                if (password.equals(MD5digest.generatePassword(oldPwd))) {
                    // 加密后修改密码的判断
                    if (newPwd.equals(confirmPwd)) {
                        currentUser.setLoginPwd(MD5digest
                                .generatePassword(newPwd));
                    } else {
                        addActionError(getText("user.pwd.confirm"));
                        return ERROR;
                    }
                    try {
                        service.update("ModifyPassword.updateUserPassword",
                                currentUser);

                    } catch (BusinessException e) {
                        log.error(e.getMessage());
                        addActionError(getText("info.db.error"));
                        return ERROR;
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        addActionError(getText("info.db.error"));
                        return ERROR;
                    }
                } else {
                    addActionError(getText("old.password.error"));
                    return ERROR;
                }
            } catch (Exception e1) {
                addActionError(getText("modify.password.error"));
                return ERROR;
            } finally {
                setOperationType(Constants.UPDATE,
                        ModuleId.CLW_M_XS_MODIFYPWD_MID);
                addOperationLog("修改用户密码");
            }
        }
        addActionMessage(getText("modify.password.success"));
        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }

}
