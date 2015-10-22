/*******************************************************************************
 * @(#)TimeUtil.java 2010-2-1
 *
 * Copyright 2010 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.axxc.parents.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 
 * @version $Revision 1.0 $ 2010-2-1 下午07:02:16
 */
public final class TimeUtil {

	private TimeUtil() {
	}

	/**
	 * 获取当前时间（精确到秒）yyyy-MM-dd HH:mm:ss.
	 * 
	 * @return 当前时间
	 */
	public static String getThisSecondTime() {

		final Calendar calendar = Calendar.getInstance();
		final Date currentTime = calendar.getTime();
		final SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return formatter.format(currentTime);

	}

	/**
	 * 获取上月时间（精确到月）yyyyMM.
	 * 
	 * @return 上月时间
	 */
	public static String getLastMonth() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, -1);
		final Date date = calendar.getTime();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMM");
		return formatter.format((date));
	}

	/**
	 * 获取上月时间（精确到月）yyyyMM.
	 * 
	 * @return 上月时间
	 */
	public static Date getLastMonthD() {
		final Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	/**
	 * 获取当前时间（精确到日）yyyyMMdd.
	 * 
	 * @return 获取当前时间
	 */
	public static String getSysdate() {
		final Calendar calendar = Calendar.getInstance();
		final Date date = calendar.getTime();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format((date));
	}

	/**
	 * 获取当前时间的前一日（精确到日）yyyyMMdd.
	 * 
	 * @return 获取当前时间
	 */
	public static String getSysdateYesterday() {
		final Calendar calendar = Calendar.getInstance();
		final Date date = calendar.getTime();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return formatter.format((cal.getTime()));
	}

	/**
	 * 获取当前时间（精确到秒）yyyyMMddHHmmss.
	 * 
	 * @return 当前时间
	 */
	public static String getSysTime() {

		final Calendar calendar = Calendar.getInstance();
		final Date currentTime = calendar.getTime();
		final SimpleDateFormat formatter = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return formatter.format(currentTime);

	}

	/**
	 * 根据pattern来格式化日期
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public static String formatDateToString(Date date, String pattern) {
		if (null == date) {
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 根据pattern将字符串转换成日期
	 * 
	 * @param pattern
	 *            时间格式:yyyyMMdd
	 * @param src
	 *            日期型字符串.
	 * @return 转换后的具体时间.
	 * @throws ParseException
	 *             转换异常.
	 */
	public static Date parseStringToDate(String src, String pattern)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		// 设置lenient为false.
		// 否则SimpleDateFormat会比较宽松地验证日期
		// 比如2007/02/29会被接受，并转换成2007/03/01
		sdf.setLenient(false);
		return sdf.parse(src);
	}

	/**
	 * 判断字符串是否是yyyyMM格式.
	 * 
	 * @param month
	 *            月份字符串.
	 * @return true-符合；false-不符合.
	 */
	public static boolean checkyyyyMM(String month) {
		Pattern p = Pattern
				.compile("^[1-9]{1}[0-9]{3}((0[1-9]{1})|(1[0-2]{1}))$");
		return p.matcher(month).matches();
	}

	/**
	 * 获取当前时间的零时时刻
	 * 
	 * @return
	 */
	public static Date getTodayZero() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获取当前时间24小时前的时刻
	 * 
	 * @return
	 */
	public static Date get24Ago(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * 获取当前时间15天前的时刻
	 * 
	 * @param dBTime
	 * @return
	 */
	public static Date get15Ago(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, -15);
		return cal.getTime();
	}
}
