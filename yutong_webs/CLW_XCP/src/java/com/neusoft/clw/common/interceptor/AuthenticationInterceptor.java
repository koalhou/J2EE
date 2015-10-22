/*******************************************************************************
 * @(#)AuthenticationInterceptor.java 2008-2-28
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.log.ds.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author <a href="mailto:hegq@neusoft.com">Puras.He </a>
 * @version $Revision 1.1 $ 2008-2-28 下午02:02:54
 */
public class AuthenticationInterceptor extends MethodFilterInterceptor {
    /**
     * 序列号
     */
    private static final long serialVersionUID = 4135153948643784435L;

    public static final String USER_SESSION_KEY = "adminProfile";

    public void destroy() {

    }

    public void init() {

    }

    @Override
    protected String doIntercept(ActionInvocation inter) throws Exception {

        SessionMap sessionMap = (SessionMap) ActionContext.getContext().get(
                ActionContext.SESSION);

        Object object = sessionMap.get(Constants.USER_SESSION_KEY);

        if (null == object) {
            return Action.LOGIN;
        }
        return inter.invoke();
    }

}
