package com.neusoft.clw.yw.popup.owner.ds;

/**
 * 车主信息bean
 * @author JinPeng
 */
public class OwnerInfo {
    /** 车主ID **/
    private String userId = "";

    /** 车主登陆名 **/
    private String loginName = "";

    /** 车主名称 **/
    private String userName = "";

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
