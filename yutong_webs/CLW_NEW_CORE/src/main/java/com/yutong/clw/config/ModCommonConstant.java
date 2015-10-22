/*******************************************************************************
 * @(#)ModCommonConstant.java 2014-03-07
 *
 * Copyright 2013 YUTONG CLW. All rights reserved.
 *******************************************************************************/
package com.yutong.clw.config;

import java.io.File;

/**
 * @author <a href="houjh@yutong.com">houjh </a>
 * @version $Revision 1.0 $ 2014-03-07 上午08:37:04
 */
public class ModCommonConstant {

	// 系统的配置文件名称
	private static final String CONFIG_NAME = "config";

	// 系统home路径
	private static final String SERVER_HOME = "com.neusoft.mid.home";

	// log4j配置文件名称
	private static final String LOGBACK_FILE = "logback.xml";

	// 系统日志名称
	public static final String LOGGER_NAME = "main.logger";

	// 系统交易流水号名称.
	public static final String MOD_SEQ_NAME = "lsmpseqname";

	// 系统home路径
	public static final String HOME_DIR;

	// 配置文件路径
	public static final String CONFIG_DIR;

	// log4j配置文件路径
	public static final String LOG4J_CONFIG_FILE;

	// 系统简称
	public static final String MODULE_NAME = "clwapi";

	// 文件系统分隔符
	public static final String FILE_SEPARATOR;

	// 监控相关配置的文件名
	public static final String TOKEN_CONF_FILE = "token.properties";
	// 监控相关配置的文件路径
	public static final String TOKEN_CONF_FILE_PATH;

	// 系统配置相关配置的文件名
	public static final String SYS_CONF_FILE = "systemConfig.properties";
	// 系统配置相关配置的文件路径
	public static final String SYS_CONF_FILE_PATH;

	// 模块交易流水号长度,13.
	public static final int RANDOM_NUMBER_LENGTH = 13;

	public static final String MODULE_DIR;

	static {
		FILE_SEPARATOR = File.separator;
		final String home = System.getProperty(SERVER_HOME);
		HOME_DIR = null == home ? System.getProperty("user.dir") : home;
		MODULE_DIR = HOME_DIR + FILE_SEPARATOR + MODULE_NAME;
		CONFIG_DIR = MODULE_DIR + FILE_SEPARATOR + CONFIG_NAME + FILE_SEPARATOR;
		LOG4J_CONFIG_FILE = CONFIG_DIR + LOGBACK_FILE;
		TOKEN_CONF_FILE_PATH = CONFIG_DIR + TOKEN_CONF_FILE;
		SYS_CONF_FILE_PATH = CONFIG_DIR + SYS_CONF_FILE;
	}
}
