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
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.log.service.OperateLogRecorder;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;

/**
 * @author JinPeng
 */
public class OperationLogFilter implements Filter {

    // private static final Log LOG =
    // LogFactory.getLog(OperationLogFilter.class);

    protected static final Logger LOG = Logger
            .getLogger(OperationLogFilter.class);

    private OperateLogRecorder recorder = null;

    public void init(FilterConfig conf) throws ServletException {
        recorder = (OperateLogRecorder) (WebApplicationContextUtils
                .getWebApplicationContext(conf.getServletContext()))
                .getBean("operateLogRecorder");
        if (LOG.isInfoEnabled()) {
            LOG.info("OperationLogFilter init is successful.");
        }
    }

    public void destroy() {
        recorder = null;
        if (LOG.isInfoEnabled()) {
            LOG.info("OperationLogFilter destroy is done.");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        Object user = null;
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpSession session = httpReq.getSession(false);
        try {
            if (null != session) {
                user = session.getAttribute(Constants.USER_SESSION_KEY);
            }
            chain.doFilter(req, res);
        } finally {
            if (null != session && null == user) {
                if (!session.isNew()) {
                    user = session.getAttribute(Constants.USER_SESSION_KEY);
                }
            }
            if (null != user) {
                record(httpReq, user);
            }
        }
    }

    private void record(HttpServletRequest req, Object user) {
        try {
            Object actionDesc = req
                    .getAttribute(OperateLogRecorder.CLW_M_OPERATION_INFO);
            Object actionType = req
                    .getAttribute(OperateLogRecorder.CLW_M_OPERATION_TYPE);
            Object actionModule = req
                    .getAttribute(OperateLogRecorder.CLW_M_MODULE_ID);

            String ipAddr = req.getRemoteAddr();
            if (null != actionModule && !actionModule.equals("")
                    && null != actionType && !actionType.equals("")
                    && null != actionDesc && !actionDesc.equals("")) {
                if (LOG.isInfoEnabled()) {
                    LOG.info("Recording user operation log............");
                }
                recorder.record((UserInfo) user, ipAddr, (String) actionModule,
                        (String) actionType, (String) actionDesc);
            }
        } catch (Throwable t) {
            LOG.error("Incured a exception when recorded operation log " + t);
        }
    }

}
