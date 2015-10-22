package com.yutong.axxc.tqc.interceptor;

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
		if (req.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			String ips = req.getHeader("x-forwarded-for");
			String [] tmp=ips.split(",");
			ip=tmp[0];
		}
		logger.info("URI={},Method={},IP={}",req.getRequestURI(),req.getMethod(),  ip);
		
		chain.doFilter(request, response);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
