/*******************************************************************************
 * @(#)BaseAction.java 2008-1-9
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.action;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;
import com.neusoft.clw.common.util.log.service.OperateLogRecorder;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author <a href="mailto:hegq@neusoft.com">Puras.He </a>
 * @version $Revision 1.1 $ 2008-1-9 11:15:12
 */
public class BaseAction extends ActionSupport implements Externalizable {

    /**
     * 生成序列号
     */
    private static final long serialVersionUID = 5655419318535345313L;

    public static final String CANCEL = "cancel";

    public static final String NEXT = "next";

    public static final String NO_PERMISSION = "no_permission";

    protected final Logger log = Logger.getLogger(getClass());

    public String cancel() {
        return CANCEL;
    }

    public String noPermission() {
        return NO_PERMISSION;
    }

    /**
     * 具体操作信息内容
     * @param operLog
     */
    protected void addOperationLog(String operLog) {
        setLogInRequest(OperateLogRecorder.CLW_M_OPERATION_INFO, operLog);
    }

    /**
     * 设置日志参数
     * @param operType 操作类型
     * @param modulId 模块ID
     */
    protected void setOperationType(String operType, String modulId) {
        setLogInRequest(OperateLogRecorder.CLW_M_OPERATION_TYPE, operType);
        setLogInRequest(OperateLogRecorder.CLW_M_MODULE_ID, modulId);
    }

    private void setLogInRequest(String key, String msg) {
        HttpServletRequest request = (HttpServletRequest) ServletActionContext
                .getRequest();
        
        /**
        if(null == request) {
            WebContext ctx = WebContextFactory.get();
            request = ctx.getHttpServletRequest();
        }**/
        
        request.setAttribute(key, msg);
    }

    public Logger getLog() {
        return log;
    }

    public void readExternal(ObjectInput in) throws IOException,
            ClassNotFoundException {

    }

    public void writeExternal(ObjectOutput out) throws IOException {

    }
}
