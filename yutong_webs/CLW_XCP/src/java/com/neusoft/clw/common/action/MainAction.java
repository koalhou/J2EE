/*******************************************************************************
 * @(#)MainAction.java 2008-1-9
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

/**
 * @author <a href="mailto:hegq@neusoft.com">Puras.He </a>
 * @version $Revision 1.1 $ 2008-1-9 上午11:13:33
 */
@SuppressWarnings("unchecked")
public class MainAction extends BaseAction implements SessionAware {

    private transient Service service;

    private List perRootList = new ArrayList();

    private List per2SecList = new ArrayList();

    private static final String REPORT_ADDRESS = "REPORT_ADDRESS";

    private static final int STARTPOSTION = 0;

    private static final int FIRSTFINALPOSTION = 2;

    private static final int SECONDFINALPOSTION = 5;

    public String topbar() {
        return SUCCESS;
    }

    public String hide() {
        return SUCCESS;
    }

    public String toolbar() {
        return SUCCESS;
    }

    public String welcome() {
        return SUCCESS;
    }

    public String footer() {
        return SUCCESS;
    }

    public String execute_new() {
        // 获得保存用户信息到session
        return SUCCESS;
    }

    private List setPermissionList(String id) throws BusinessException {
        return null;
    }

    @Override
    public String execute() {
        // 判断角色获得list
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

    public List getPerRootList() {
        return perRootList;
    }

    public void setPerRootList(List perRootList) {
        this.perRootList = perRootList;
    }

    public List getPer2SecList() {
        return per2SecList;
    }

    public void setPer2SecList(List per2SecList) {
        this.per2SecList = per2SecList;
    }

    private Map session;

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
