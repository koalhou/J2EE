package com.neusoft.clw.sysmanage.sysset.pawset.action;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

public class PawSetAction extends PaginationAction {

    private String oldPass;

    private String newPass1;

    private String newPass2;

    /** 显示消息 * */
    private String message;

    /** service共通类 */
    private transient Service service;

    public String pawSetBefore() {
        return SUCCESS;
    }

    /**
     * 修改密碼
     * @return
     */
    public String pawSet() {

        if (!newPass1.equals(newPass2)) {
            addActionError(getText("password.neq"));
            return ERROR;
        }

        final String title = getText("password.browse.title");

        try {
            UserInfo userInfo = getCurrentUser();
            UserInfo user = new UserInfo();
            user = (UserInfo) service.getObject("login.getUserPass", userInfo);

            if (user == null) {
                addActionError(getText("password.user_notexists"));
                return ERROR;
            }

            if (!MD5digest.validatePassword(user.getLoginPwd(), oldPass)) {
                addActionError(getText("password.oldpass_incorrect"));
                return ERROR;
            }
            user.setLoginPwd(MD5digest.generatePassword(newPass1));
            service.update("login.updateUserPass", user);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("password.update_success");
        addActionMessage(getText(message));
        // 设置操作描述
        this.addOperationLog(formatLog(title, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_UPDATE_USER_ID);
        return SUCCESS;

    }
    
    /**
     * 修改模块密碼
     * @return
     */
    public String pawSetST() {

        if (!newPass1.equals(newPass2)) {
            addActionError(getText("password.neq"));
            return ERROR;
        }

        final String title = getText("password.browse.title");

        try {
            UserInfo userInfo = getCurrentUser();
            UserInfo user = new UserInfo();
            user = (UserInfo) service.getObject("login.getUserPass", userInfo);

            if (user == null) {
                addActionError(getText("password.user_notexists"));
                return ERROR;
            }

            if (user.getStudent_pwd()!=null && !MD5digest.validatePassword(user.getStudent_pwd(), oldPass)) {
                addActionError(getText("password.oldpass_incorrect"));
                return ERROR;
            }
            user.setStudent_pwd(MD5digest.generatePassword(newPass1));
            service.update("login.updateUserPassST", user);

        } catch (BusinessException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("password.update_success");
        addActionMessage(getText(message));
        // 设置操作描述
        this.addOperationLog(formatLog(title, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_PASSWORD_UPDATE_ID);
        return SUCCESS;

    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, VehcileInfo vehObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != vehObj) {
            if (null != vehObj.getVehicle_id()) {
                OperateLogFormator.format(sb, "vehicleid", vehObj
                        .getVehicle_id());
            }
        }
        return sb.toString();
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public String getOldPass() {
        return oldPass;
    }

    public void setOldPass(String oldPass) {
        this.oldPass = oldPass;
    }

    public String getNewPass1() {
        return newPass1;
    }

    public void setNewPass1(String newPass1) {
        this.newPass1 = newPass1;
    }

    public String getNewPass2() {
        return newPass2;
    }

    public void setNewPass2(String newPass2) {
        this.newPass2 = newPass2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
