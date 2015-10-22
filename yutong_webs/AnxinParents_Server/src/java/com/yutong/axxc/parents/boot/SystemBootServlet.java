/**
 * @(#)SystemBootServlet.java 2013-03-16
 *
 * Copyright 2011 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.boot;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.yutong.axxc.parents.common.HttpConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.tools.PropertiesTools;

/**

 */
public class SystemBootServlet extends HttpServlet {
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);

	/**
	 * XCPAPI初始化.
	 */
	@Override
	public void init(final ServletConfig config) throws ServletException {
		// 添加处理类型信息至日志
		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-INIT]");

		// 加载系统中所需要的相关资源
		try {
			loadPropertie(config.getServletContext().getRealPath("/")+File.separator+"WEB-INF"+File.separator+"classes"+File.separator);
			logger.info("-------------------------------");
			logger.info("---安芯移动服务API模块启动成功---");
			logger.info("-------------------------------");
		} catch (final Exception e) {
			logger.error("加载资源信息时出错" + e, e);
			logger.error("-------------------------------");
			logger.error("---安芯移动服务API模块启动失败---");
			logger.error("-------------------------------");

		}

		// 从日志中移除处理类型信息
		MDC.remove("processType");
		MDC.remove("module");
	}

	/**
	 * 获取配置信息
	 * 
	 * @throws IOException
	 * 
	 */
	private void loadPropertie(String path) throws IOException {
		HttpConstant.WEATHER_IMG_PATH = PropertiesTools.readValue(
				ModCommonConstant.SYS_CONF_FILE, "weather.web.path");
	}

	/**
	 * CLWAPI系统销毁.
	 */
	@Override
	public void destroy() {

		// 添加处理类型信息至日志
		MDC.put("module", "[MAIN]");
		MDC.put("processType", "[SYSTEM-DESTROY]");

		logger.info("-------------------------------");
		logger.info("-----安芯移动服务API模块退出-----");
		logger.info("-------------------------------");

		// 从日志中移除处理类型信息
		MDC.remove("processType");
		MDC.remove("module");
	}

}
