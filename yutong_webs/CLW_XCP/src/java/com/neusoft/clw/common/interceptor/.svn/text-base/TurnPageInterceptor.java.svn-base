/*******************************************************************************
 * @(#)TurnPageUrlInterceptor.java Oct 29, 2007
 *
 * Copyright 2007 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.interceptor;

import java.net.URLDecoder;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * @author <a href="mailto:hegq@neusoft.com">puras.he </a>
 * @version $Revision 1.1 $ Oct 29, 2007 11:23:34 AM
 */
@SuppressWarnings("serial")
public class TurnPageInterceptor implements Interceptor {
    // private static Log log = LogFactory.getLog(TurnPageInterceptor.class);
    private static final Logger log = Logger
            .getLogger(TurnPageInterceptor.class);

    /*
     * (non-Javadoc)
     * @see com.opensymphony.xwork2.interceptor.Interceptor#destroy()
     */
    public void destroy() {

    }

    /*
     * (non-Javadoc)
     * @see com.opensymphony.xwork2.interceptor.Interceptor#init()
     */
    public void init() {

    }

    /*
     * (non-Javadoc)
     * @see com.opensymphony.xwork2.interceptor.Interceptor
     * #intercept(com.opensymphony.xwork2.ActionInvocation)
     */
    @SuppressWarnings("unchecked")
    public String intercept(ActionInvocation invo) throws Exception {
        HttpServletRequest request = (HttpServletRequest) invo
                .getInvocationContext().get(ServletActionContext.HTTP_REQUEST);

        String url = request.getRequestURI();
        String param = "";

        if ("get".equalsIgnoreCase(request.getMethod())) {
            param = request.getQueryString();
            if (log.isDebugEnabled()) {
                log.debug("URL ==>" + url);
                log.debug("Param ==>" + param);
                log.debug("Method ==>" + request.getMethod());
            }
            if (null != param) {
                param = URLDecoder.decode(param, "utf-8");
            }
        } else if ("post".equalsIgnoreCase(request.getMethod())) {
            StringBuffer params = new StringBuffer();
            Enumeration paramNames = request.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String name = (String) paramNames.nextElement();
                Object obj = request.getParameter(name);
                String value = "";
                if (obj instanceof String) {
                    value = (String) obj;
                } else {
                    value = obj.toString();
                }
                params.append(name + "=" + value + "&");
            }
            param = params.toString();
            if (param.endsWith("&")) {
                param = param.substring(0, param.length() - 1);
            }
        }

        ActionContext.getContext().getParameters().put("url", url);
        ActionContext.getContext().getParameters().put("param", param);

        return invo.invoke();
    }
}
