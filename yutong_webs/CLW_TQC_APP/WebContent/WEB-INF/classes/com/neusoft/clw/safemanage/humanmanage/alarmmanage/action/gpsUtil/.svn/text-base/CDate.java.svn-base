package com.neusoft.clw.safemanage.humanmanage.alarmmanage.action.gpsUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class CDate {
	
	private static Logger log = Logger.getLogger(CDate.class);
	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	 */
	public static Date getNowDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		ParsePosition pos = new ParsePosition(8);
		Date currentTime_2 = formatter.parse(dateString, pos);
		return currentTime_2;
	}

	/**
	 * 将时间格式由srcformat 转化成 destformat
	 * 
	 * @return返回返回转化后的字符串
	 */
	public static String changeDateFormat(String srcformat, String destformat,
			String srcdatastr) {
		// yyyymmdd/hhmmss
		DateFormat format1 = new SimpleDateFormat(srcformat);
		DateFormat format2 = new SimpleDateFormat(destformat);
		Date date = null;
		String str = null;
		try {
			date = format1.parse(srcdatastr);
			str = format2.format(date);
		} catch (ParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			return str;
		}
	}

	public static Date changeDateFormat(String srcformat, String srcdatastr) {
		// yyyymmdd/hhmmss
		DateFormat format1 = new SimpleDateFormat(srcformat);
		Date date = null;
		try {
			date = format1.parse(srcdatastr);
		} catch (ParseException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} finally {
			return date;
		}
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取时间 小时:分;秒 HH:mm:ss
	 * 
	 * @return
	 */
	public static String getTimeShort() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date currentTime = new Date();
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateDate
	 * @return
	 */
	public static String dateToStrLong(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式时间转换为字符串 yyyy-MM-dd
	 * 
	 * @param dateDate
	 * @param k
	 * @return
	 */
	public static String dateToStr(java.util.Date dateDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(dateDate);
		return dateString;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return
	 */
	public static Date getNow() {
		Date currentTime = new Date();
		return currentTime;
	}

	/**
	 * 提取一个月中的最后一天
	 * 
	 * @param day
	 * @return
	 */
	public static Date getLastDate(long day) {
		Date date = new Date();
		long date_3_hm = date.getTime() - 3600000 * 34 * day;
		Date date_3_hm_date = new Date(date_3_hm);
		return date_3_hm_date;
	}

	/**
	 * 得到现在时间
	 * 
	 * @return 字符串 yyyyMMdd HHmmss
	 */
	public static String getStringToday() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 得到现在小时
	 */
	public static String getHour() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String hour;
		hour = dateString.substring(11, 13);
		return hour;
	}

	/**
	 * 得到现在分钟
	 * 
	 * @return
	 */
	public static String getTime() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		String min;
		min = dateString.substring(14, 16);
		return min;
	}

	/**
	 * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
	 * 
	 * @param sformat
	 *            yyyyMMddhhmmss
	 * @return
	 */
	public static String getUserDate(String sformat) {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(sformat);
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	public static String getNextDate(int i) {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String r = "";
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, i);
			r = sdf.format(cal.getTime());
			// System.out.println("下一天的时间是：" + sdf.format(cal.getTime()));
		} catch (Exception e) {
			log.error(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return r;
		}
	}

	/**
	 * 时间(yyyymmdd/hhmmss) 转换成长整型utc格式
	 * 
	 * @return
	 */
	public static long stringConvertUtc(String time) {
		if (time == null) {
			return 0;
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.parseInt(time.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.parseInt(time.substring(4, 6)) - 1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(time.substring(6, 8)));
		cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.substring(9, 11)));
		cal.set(Calendar.MINUTE, Integer.parseInt(time.substring(11, 13)));
		cal.set(Calendar.SECOND, Integer.parseInt(time.substring(13, 15)));
		cal.set(Calendar.MILLISECOND, 0);
		long utc = cal.getTimeInMillis();
		return utc;
	}

	/**
	 * 得到前一天的年月日Long类型
	 * 
	 * @return
	 */
	public static long getYesDayYearMonthDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.setTimeInMillis(cal.getTimeInMillis() - 24 * 60 * 60 * 1000);
		return cal.getTimeInMillis();
	}

	/**
	 * 得到当天的年月日Long类型
	 * 
	 * @return
	 */
	public static long getCurrentDayYearMonthDay() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取上一年份
	 * 
	 * @return
	 */
	public static int getPreviousYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getPreviousMonthUtc());
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取上一周
	 * 
	 * @return
	 */
	public static int getPreviousWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(getPreviousMonthUtc());
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取上一月
	 * 
	 * @return
	 */
	public static int getPreviousMonth() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		return (lastDate.get(Calendar.MONTH) + 1);
	}

	/**
	 * 获取上一月的UTC格式
	 * 
	 * @return
	 */
	public static long getPreviousMonthUtc() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		return lastDate.getTimeInMillis();
	}

	/**
	 * 根据参数获取一周中周一的UTC格式时间
	 * 
	 * @return
	 */
	public static long getWeekUtc(int num) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.WEEK_OF_YEAR, num);
		cal.add(Calendar.DAY_OF_YEAR, 1); // 获取是下一周周一00:00:00的时间
		return cal.getTimeInMillis();
	}

	/**
	 * 获取下一个月的UTC格式
	 * 
	 * @return
	 */
	public static long getNextMonthUtc() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, 1);
		return cal.getTimeInMillis();
	}

	/**
	 * 获取下一年的UTC格式
	 * 
	 * @return
	 */
	public static long getNextYearUtc() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.YEAR, 1);
		return cal.getTimeInMillis();
	}

	/**
	 * 根据参数获取年月的UTC格式
	 * 
	 * @return
	 */
	public static long getYearMonthUtc(int num) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 0);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.MONTH, num);
		return cal.getTimeInMillis();
	}

	public static Long getCurrentUtcMsDate() {
		Long dateLong = System.currentTimeMillis();
		return dateLong;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return 返回时间类型 yyMMddhhmmss
	 */
	public static long getStringToDate(String time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMddhhmmss");
		Date d = null;
		try {
			d = formatter.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		return c.getTimeInMillis();
	}

	/**
	 * 时间(yyyymmdd/hhmmss) 转换成长整型utc格式
	 * 
	 * @return
	 */
	public static String stringConvertDate(String time) {
		if (time == null || time.length() != 15) {
			return "";
		}
		String YEAR = time.substring(0, 4);
		String MONTH = time.substring(4, 6);
		String DAY_OF_MONTH = time.substring(6, 8);
		String HOUR_OF_DAY = time.substring(9, 11);
		String MINUTE = time.substring(11, 13);
		String SECOND = time.substring(13, 15);
		if ("".equals(MONTH) || "".equals(HOUR_OF_DAY)) {
			return "";
		}
		return YEAR + MONTH + DAY_OF_MONTH + HOUR_OF_DAY + MINUTE + SECOND;
	}

	public static void main(String argv[]) throws Exception {
		
        System.out.println(CDate.getNextDate(-1));
	}

}
