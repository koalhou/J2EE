/*******************************************************************************
 * @(#)DateUtil.java Oct 23, 2007
 *
 * Copyright 2007 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:hegq@neusoft.com">puras.he </a>
 * @version $Revision 1.1 $ Oct 23, 2007 3:21:42 PM
 */
public class DateUtil {
    // private static Log log = LogFactory.getLog(DateUtil.class);

    private static Logger log = Logger.getLogger(DateUtil.class);

    public static String formatDateToString(Date date, String pattern) {
        if (null == date) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static Date parseStringToDate(String src, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(src);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 根据aMask来格式化日期
     * @param aMask
     * @param date
     * @return
     */
    public static final String getDateTime(String aMask, Date date) {
        SimpleDateFormat sdf = null;
        String returnValue = "";

        if (date == null) {
            log.error("The Date is null!");
        } else {
            sdf = new SimpleDateFormat(aMask);
            returnValue = sdf.format(date);
        }

        return returnValue;
    }

    /**
     * 将字符串转换为日期
     * @param aMask
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static final Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat sdf = null;
        Date date = null;
        sdf = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"
                    + aMask + "'");
        }

        try {
            date = sdf.parse(strDate);
        } catch (ParseException ex) {
            throw new ParseException(ex.getMessage(), ex.getErrorOffset());
        }

        return date;
    }

    public static final Date convertLongToDate(long time) {
        Date date = new Date(time);
        return date;
    }

    /**
     * 获取当前时间（精确到秒）
     * @return 当前时间
     */
    public static String getThisSecondTime() {

        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
        return formatter.format(currentTime);

    }
    
    /**
     * 获取当前时间（yyyy-MM-dd HH:mm:ss）
     * @return 当前时间
     */
    public static String getThisSecondTime2() {

        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);

    }

    /**
     * 获取当天时间
     * @return
     */
    public static String getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(currentTime);
    }

    /**
     * 设置开始时间为当天5点
     * @return
     */
    public static String getSpeedStartime() {
        StringBuffer str = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        str.append(formatter.format(currentTime));
        str.append(" 09:00");
        return str.toString();
    }

    /**
     * 设置开始时间为当天5点
     * @return
     */
    public static String getSpeedEndime() {
        StringBuffer str = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        Date currentTime = calendar.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        str.append(formatter.format(currentTime));
        str.append(" 17:00");
        return str.toString();
    }

    /**
     * 获取当前月第一天 小时分钟为00:00:00
     **/
    public static String getMonthFirstDay() {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        str.append(sdf.format(lastDate.getTime()));
        str.append(" 00:00:00");
        return str.toString();
    }

    /**
     * 获取当前月最后一天 小时分钟为23:59:59
     **/
    public static String getMonthLastDay() {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar lastDate = Calendar.getInstance();
        lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
        lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
        lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
        str.append(sdf.format(lastDate.getTime()));
        str.append(" 23:59:59");
        return str.toString();
    }

    /**
     * 获取当前年第一天 小时分钟为00:00:00
     **/
    public static String getCurrentYearFirst() {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        str.append(sdf.format(cd.getTime()));
        str.append(" 00:00:00");
        return str.toString();
    }

    /**
     * 获取当前年最后一天 小时分钟为23:59:59
     **/
    public static String getCurrentYearLast() {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cd = Calendar.getInstance();
        cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
        cd.add(Calendar.YEAR, 1);
        cd.add(Calendar.DATE, -1);
        str.append(sdf.format(cd.getTime()));
        str.append(" 23:59:59");
        return str.toString();
    }

    /**
     * 根据日期获取季度
     * @param cal
     * @return
     */
    public static int getSeason() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        return (month + 1 + 2) / 3;
    }

    /**
     * 根据获取季度的第一天 小时分钟为00:00:00
     * @param cal
     * @return
     */
    public static String getSeasonFirstDay(int i) {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        if (i == 1) {
            i = 0;
        } else if (i == 2) {
            i = 3;
        } else if (i == 3) {
            i = 6;
        } else if (i == 4) {
            i = 9;
        }
        cal.set(Calendar.MONTH, i);// 设为季度开始月
        cal.set(Calendar.DATE, 1);
        str.append(sdf.format(cal.getTime()));
        str.append(" 00:00:00");
        return str.toString();
    }

    /**
     * 根据获取季度的最后一天
     * @param cal
     * @return
     */
    public static String getSeasonLastDay(int i) {
        StringBuffer str = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        if (i == 1) {
            i = 2;
        } else if (i == 2) {
            i = 5;
        } else if (i == 3) {
            i = 8;
        } else if (i == 4) {
            i = 11;
        }
        cal.set(Calendar.MONTH, i);// 设为季度开始月
        cal.set(Calendar.DATE, 1);// 设季度开始月的第一天
        cal.add(Calendar.MONTH, 1);// 当前季度最后一月的下一月
        cal.add(Calendar.DATE, -1);// 当前季度最后一月的最后一天
        str.append(sdf.format(cal.getTime()));
        str.append(" 23:59:59");
        return str.toString();
    }

    /**
     * 获取当前月
     * @param cal
     * @return
     */
    public static String getMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        String str = sdf.format(cal.getTime());
        return str;
    }

    /**
     * 获取当前周第一天 小时分钟为00:00:00
     **/
    public static String getCurrentWeekFirst() {
        StringBuffer str = new StringBuffer();
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date());
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday

        str.append(df.format(c.getTime()));
        str.append(" 00:00:00");

        return str.toString();
    }

    /**
     * 获取当前周最后一天 小时分钟为23:59:59
     **/
    public static String getCurrentWeekLast() {
        StringBuffer str = new StringBuffer();
        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
                "yyyy-MM-dd");
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(new Date());
        // c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
        str.append(df.format(c.getTime()));
        str.append(" 23:59:59");

        return str.toString();
    }
    
    public static void main(String[] args) {
        String src = "10/23/2007 15:20";
        System.out.println(formatDateToString(parseStringToDate(src,
                "MM/dd/yyyy HH:mm"), "yyyyMMddHHmmss"));
    }
}
