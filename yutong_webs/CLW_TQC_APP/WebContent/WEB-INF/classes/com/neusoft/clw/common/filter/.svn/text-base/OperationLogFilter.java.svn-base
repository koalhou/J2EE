package com.neusoft.clw.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.springframework.web.context.support.WebApplicationContextUtils;
import com.neusoft.clw.common.interceptor.AuthenticationInterceptor;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.log.service.OperateLogRecorder;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;

public class OperationLogFilter implements Filter {

    // private static final Log LOG =
    // LogFactory.getLog(OperationLogFilter.class);
    private static final Logger LOG = Logger
            .getLogger(OperationLogFilter.class);

    private OperateLogRecorder recorder = null;

    /**
     * 初始方法
     */
    public void init(FilterConfig conf) throws ServletException {
        recorder = (OperateLogRecorder) (WebApplicationContextUtils
                .getWebApplicationContext(conf.getServletContext()))
                .getBean("operateLogRecorder");
        if (LOG.isInfoEnabled()) {
            LOG.info("OperationLogFilter init is successful.");
        }
    }

    /**
     * 销毁方法
     */
    public void destroy() {
        recorder = null;
        if (LOG.isInfoEnabled()) {
            LOG.info("OperationLogFilter destroy is done.");
        }
    }

    /**
     * 过滤方法-获取USER信息
     */
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        Object user = null;
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(false);
        try {
            if (null != session) {
                user = session
                        .getAttribute(AuthenticationInterceptor.USER_SESSION_KEY);
//                if(null!=session.getAttribute(Constants.LOG_USE_ID)){
//                	 MDC.put("loginid", session.getAttribute(Constants.LOG_USE_ID));
//                }
               
            }
            chain.doFilter(req, res);
        } finally {
            if (null != session && null == user) {
            	try{
                if (!session.isNew()) {
                    user = session
                            .getAttribute(AuthenticationInterceptor.USER_SESSION_KEY);
                }}catch(Exception e){
                	LOG.error("SESSION失效",e);
                }
            }
            if (null != user) {
                record(httpReq, user);
            }
        }
    }

    /**
     * 日志记录
     * @param req 请求
     * @param user 操作用户
     */
    private void record(HttpServletRequest req, Object user) {

        try {
            // 获取请求中操作描述
            Object actionDesc = req
                    .getAttribute(OperateLogRecorder.YTP_USER_OPERATION_LOG);
            // 获取请求中操作类型
            Object operType = req
                    .getAttribute(OperateLogRecorder.YTP_USER_OPER_TYPE);
            // 获取请求中的所属应用系统
            Object applyId = req
                    .getAttribute(OperateLogRecorder.YTP_USER_OPER_APPLY_ID);
            // 获取请求中的所属模块信息
            Object moduleId = req
                    .getAttribute(OperateLogRecorder.YTP_USER_OPER_MODULE_ID);
            // 获取操作ip
            String ipAddr = req.getRemoteAddr();

            if (null != actionDesc && !actionDesc.equals("") && null != applyId
                    && !applyId.equals("") && null != moduleId
                    && !moduleId.equals("") && null != operType
                    && !operType.equals("")) {
                if (LOG.isInfoEnabled()) {
                    LOG.info("Recording user operation log............");
                }
                // 记录日志信息
                recorder.record((UserInfo) user, ipAddr, (String) actionDesc,
                        (String) operType, (String) applyId, (String) moduleId);
            }
        } catch (Throwable t) {
            LOG.error("Incured a exception when recorded operation log " + t);
        }
    }

}
