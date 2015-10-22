package com.yutong.axxc.tqc.tools;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.axxc.tqc.boot.SystemBootServlet;
import com.yutong.axxc.tqc.common.ModCommonConstant;


public final class PropertiesTools {

	private PropertiesTools() {
	}

	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);

	/**
	 * 获取指定路径下properties配置文件中的配置信息
	 * 
	 * @param filePath
	 *            properties配置文件路径信息
	 * @param key
	 *            properties配置文件中配置信息key
	 * @return 配置信息
	 * @throws IOException
	 */
	public static String readValue(final String filePath, final String key)
			throws IOException {
		ClassLoader loader=SystemBootServlet.class.getClassLoader();
		InputStream in = null;
		try {

			// 获取文件文件流
			in = new BufferedInputStream(loader.getResourceAsStream(filePath));

			final Properties props = new Properties();

			// 加载配置文件
			props.load(in);

			// 获取配置文件中配置信息属性值
			return props.getProperty(key);

		} catch (final IOException ex) {
			logger.error("加载properties配置文件时出错", ex);
			throw new IOException();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (final IOException e) {
					logger.error("关闭properties流时出错", e);
				}
			}
		}
	}

	/**
	 * 获取指定路径下properties配置文件中的配置信息
	 * 
	 * @param filePath
	 *            properties配置文件路径信息
	 * @return 配置信息
	 * @throws IOException
	 */
	public static Map<String, String> readValues(final String filePath,
			final String longKeys) throws IOException {

		InputStream in = null;
		final Map<String, String> propertiesMap = new HashMap<String, String>();
		try {

			// 获取文件文件流
			in = new BufferedInputStream(new FileInputStream(filePath));

			final Properties props = new Properties();

			// 加载配置文件
			props.load(in);

			final String[] keyArray = longKeys.split(";");

			for (final String temp : keyArray) {
				propertiesMap.put(temp, props.getProperty(temp));
			}

		} catch (final IOException ex) {
			logger.error("加载properties配置文件时出错", ex);
			throw new IOException();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (final IOException e) {
					logger.error("关闭properties流时出错", e);
				}
			}
		}

		return propertiesMap;
	}

}
