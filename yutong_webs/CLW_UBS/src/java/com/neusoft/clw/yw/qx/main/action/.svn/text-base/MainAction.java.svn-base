/*******************************************************************************
 * @(#)MainAction.java 2008-1-9
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.qx.main.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.opensymphony.xwork2.ActionContext;

/**
 * 首页action
 * @author JinPeng
 */
@SuppressWarnings("unchecked")
public class MainAction extends BaseAction implements SessionAware {

    private transient Service service;

    private Map session;

    @Override
    public String execute() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                "");

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
}
