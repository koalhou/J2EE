
package com.yutong.axxc.parents.tools;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;

import com.yutong.axxc.parents.common.ModCommonConstant;

/**
 * 从web.xml中加载指定文件名的日志配置文件
 * 
 */
public class LogbackConfigListener implements ServletContextListener {
	private static final Logger logger = LoggerFactory
			.getLogger(LogbackConfigListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {

			// assume SLF4J is bound to logback in the current environment
			LoggerContext context = (LoggerContext) LoggerFactory
					.getILoggerFactory();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			// Call context.reset() to clear any previous configuration, e.g.
			// default
			// configuration. For multi-step configuration, omit calling
			// context.reset().
			context.reset();
			logger.info("logbck_conf_file_path:"
					+ ModCommonConstant.LOG4J_CONFIG_FILE);
			configurator.doConfigure(ModCommonConstant.LOG4J_CONFIG_FILE);

		} catch (Exception e) {
			logger.error("加载日志配置文件" + ModCommonConstant.LOG4J_CONFIG_FILE
					+ "出错", e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
