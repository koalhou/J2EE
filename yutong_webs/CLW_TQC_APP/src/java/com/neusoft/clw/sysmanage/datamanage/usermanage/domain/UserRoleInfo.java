package com.neusoft.clw.sysmanage.datamanage.usermanage.domain;

import java.util.Date;

public class UserRoleInfo {

    private String USER_ID;

    private String ROLE_ID;

    private String ENTERPRISE_ID;

    private String APPLY_ID;

    private String CREATER;

    private Date CREATE_TIME;

    private String ROLE_NAME;

    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    public void setROLE_NAME(String role_name) {
        ROLE_NAME = role_name;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String user_id) {
        USER_ID = user_id;
    }

    public String getROLE_ID() {
        return ROLE_ID;
    }

    public void setROLE_ID(String role_id) {
        ROLE_ID = role_id;
    }

    public String getENTERPRISE_ID() {
        return ENTERPRISE_ID;
    }

    public void setENTERPRISE_ID(String enterprise_id) {
        ENTERPRISE_ID = enterprise_id;
    }

    public String getAPPLY_ID() {
        return APPLY_ID;
    }

    public void setAPPLY_ID(String apply_id) {
        APPLY_ID = apply_id;
    }

    public String getCREATER() {
        return CREATER;
    }

    public void setCREATER(String creater) {
        CREATER = creater;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date create_time) {
        CREATE_TIME = create_time;
    }

}
