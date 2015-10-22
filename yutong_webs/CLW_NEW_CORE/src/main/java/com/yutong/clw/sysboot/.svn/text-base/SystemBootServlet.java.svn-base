/**
 * @(#)SystemBootServlet.java 2013-03-16
 *
 * Copyright 2011 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.clw.sysboot;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.yutong.clw.config.ModCommonConstant;


/**
 * @author <a href="mailto:majch@neusoft.com">majch </a>
 * @version $Revision 1.0 $ 2013-03-16 上午10:21:38
 */
public class SystemBootServlet extends HttpServlet {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory.getLogger(ModCommonConstant.LOGGER_NAME);

	/**
	 * Spring起始加载
	 */
	private static String LOCATION = "classpath:applicationContext-core.xml";
	
	@Override
	public void init(final ServletConfig config) throws ServletException {

		// 添加处理类型信息至日志
		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-INIT]");

		// 加载系统中所需要的相关资源
		try {
			logger.info("-------------------------------");
			logger.info("------通勤车核心层启动成功------");
			logger.info("-------------------------------");
			SpringBootStrap.getInstance().setConfig(LOCATION);
			SpringBootStrap.getInstance().init();
		} catch (final Exception e) {
			logger.error("加载资源信息时出错" + e, e);
			logger.error("-------------------------------");
			logger.error("------通勤车核心层启动失败------");
			logger.error("-------------------------------");

		}

		// 从日志中移除处理类型信息
		MDC.remove("processType");
		MDC.remove("module");
	}

	/**
	 * 系统销毁.
	 */
	@Override
	public void destroy() {

		// 添加处理类型信息至日志
		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-DESTROY]");

		logger.info("-------------------------------");
		logger.info("--------通勤车核心层退出--------");
		logger.info("-------------------------------");

		// 从日志中移除处理类型信息
		MDC.remove("processType");
		MDC.remove("module");
	}

}
