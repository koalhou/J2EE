package com.neusoft.clw.common.aspects;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import org.aspectj.lang.JoinPoint;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

import com.neusoft.clw.common.util.Constants;

public class LogRecording {

    private Log log = LogFactory.getLog(getClass());

    @SuppressWarnings("unused")
    private void beforeMethodInvoke(JoinPoint joinPoint) {
        String funcName = joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName();
        
        /**
        HttpServletRequest request = ServletActionContext.getRequest();
        String sessionId = request.getSession().getId();
        **/
        HttpServletRequest request = ServletActionContext.getRequest();
        //HttpSession session = request.getSession();
        
        
        if (null != funcName && funcName != "") {
            
            if (log.isInfoEnabled()) {
                
                if(request != null){
                    if(request.getSession() != null){
                        if(null!=request.getSession().getAttribute(Constants.LOG_USE_ID)){
                             MDC.put("loginid", request.getSession().getAttribute(Constants.LOG_USE_ID));
                             MDC.put("modulename", "["+joinPoint.getTarget().getClass().getSimpleName()+"]");
                        }
                    }
                } else {
                    WebContext ctx = WebContextFactory.get();
                    if(ctx != null) {
                        request = ctx.getHttpServletRequest();
                    }
                    if(request != null) {
                        MDC.put("loginid", request.getSession().getAttribute(Constants.LOG_USE_ID));
                        MDC.put("modulename", "["+joinPoint.getTarget().getClass().getSimpleName()+"]");
                    }
                }

                
                log.info(funcName + "： is start");
            }
            
            if(log.isDebugEnabled()){
            	if(request != null){
                    if(request.getSession() != null){
                        if(null!=request.getSession().getAttribute(Constants.LOG_USE_ID)){
                             MDC.put("loginid", request.getSession().getAttribute(Constants.LOG_USE_ID));
                             MDC.put("modulename", "["+joinPoint.getTarget().getClass().getSimpleName()+"]");
                        }
                    }
                } else {
                    WebContext ctx = WebContextFactory.get();
                    if(ctx != null) {
                        request = ctx.getHttpServletRequest();
                    }
                    if(request != null) {
                        MDC.put("loginid", request.getSession().getAttribute(Constants.LOG_USE_ID));
                        MDC.put("modulename", "["+joinPoint.getTarget().getClass().getSimpleName()+"]");
                    }
                }
            	
                Object[] args=joinPoint.getArgs();
                StringBuffer sb = new StringBuffer();
                for(int i=0;i<args.length;i++)
                {
                  sb.append(args[i]).append(",");
                }
                log.debug(funcName + "： is start"); 
                log.debug("接收参数列表："+sb);
            }
        }
    }
    
    @SuppressWarnings("unused")
    private void afterMethodInvoke(JoinPoint joinPoint) {
        String funcName = joinPoint.getTarget().getClass().getName() + "."
                + joinPoint.getSignature().getName();
        HttpServletRequest request = ServletActionContext.getRequest();
        if (null != funcName && funcName != "") {
            if (log.isInfoEnabled()) {
                log.info(funcName + "： is end");
                
                //MDC.remove("modulename");
            }
            if(log.isDebugEnabled()){
                log.debug(funcName + "： is end"); 
                //MDC.remove("modulename");
            }
        }
    }
}
