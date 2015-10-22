package com.neusoft.clw.common.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

public abstract class ExtendActionSupport extends BaseAction implements
        ServletRequestAware, ServletResponseAware, SessionAware {
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    private String action_path;

    protected String msg;

    private String target;

    protected Map att;

    public String getActionPath() {

        String all_path[] = request.getRequestURI().split(".action");
        String action[] = all_path[0].split("/");
        action_path = action[action.length - 1];
        return action_path;
    }

    public void setSession(Map att) {
        this.att = att;
    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
