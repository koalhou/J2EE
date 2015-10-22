package com.yutong.axxc.parents.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * session trace
 */
public class TrackFilter implements Filter {
	private static Logger logger = LoggerFactory
			.getLogger(TrackFilter.class);
	
    public TrackFilter() {
    }

	
	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String ip;
		
		HttpServletRequest req = (HttpServletRequest) request;
		String reqURI=req.getRequestURI();
		if("/xcpapi/".equals(reqURI)){
			return;
		}
		
		if (req.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			String ips = req.getHeader("x-forwarded-for");
			String [] tmp=ips.split(",");
			logger.info("{}",tmp);
			ip=tmp[0];
		}
		logger.info("URI={},Method={},IP={},user-agent={}",req.getRequestURI(),req.getMethod(),  ip,req.getHeader("user-agent"));
		//TODO 过滤非法的请求
		
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
