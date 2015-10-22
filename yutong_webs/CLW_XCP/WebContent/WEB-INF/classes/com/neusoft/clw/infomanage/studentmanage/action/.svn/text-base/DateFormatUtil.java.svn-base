/*******************************************************************************
 * @(#)DateFormatUtil.java 2012-3-12
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.studentmanage.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日期相关函数库
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-12 下午02:42:47
 */
public class DateFormatUtil {
    /**
     * 根据len来左补"0"
     * @param value
     * @param len
     * @return
     */
    public static String zeroSupply(String value,int len){
        if(value == null){
            return value;
        }
        String supply = "";
        for(int i = value.length() ; i < len ; i++){
            supply = supply + "0";
        }
        
        return supply + value;
        
        
    }
    
    /**
     * 获取当前日期【YYYYMMDD】
     * @return String
     */
    public static String getYYYYMMDD() {
        Calendar cal = Calendar.getInstance();
        String dateStr = ""+cal.get(Calendar.YEAR);
        
        int month = cal.get(Calendar.MONTH)+1;
        if(month < 10){
            dateStr = dateStr + "0" + month;
        }
        else{
            dateStr = dateStr + "" + month;
        }
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if(day < 10){
            dateStr = dateStr + "0" + day;
        }
        else{
            dateStr = dateStr + "" + day;
        }
        
        return dateStr; 
    }
    
    /**
     * 获取当前时间【HH24MISSSSS】
     * @return String 
     */
    public static String getHHMISSSSS() {
        Calendar cal = Calendar.getInstance();
        String dateStr = "";
        
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        dateStr = zeroSupply(""+hour,2);
        
        int minute = cal.get(Calendar.MINUTE);
        dateStr = dateStr + zeroSupply(""+minute,2);

        int sec = cal.get(Calendar.SECOND);
        dateStr = dateStr + zeroSupply(""+sec,2);
        
        int msec = cal.get(Calendar.MILLISECOND);
        dateStr = dateStr + zeroSupply(""+msec,3);

        return dateStr; 
    }
    
    /**
     * 获取当前时间【HH24MISS】
     * @return String 
     */
    public static String getHHMISS() {

        String dateStr = getHHMISSSSS();
        
        return dateStr.substring(0,6);  
    }
    
    /**
     * 获取当前时间【YYYYMMHHMISS】
     * @return String 
     */
    public static String getYYYYMMDDHHMISS() {
        return getYYYYMMDD()+getHHMISSSSS();
    }
    
    /**
     * 根据当前日期时间来获取过去\未来的日期
     * @param y 年（未来：正数、当前：0、过去：负数）
     * @param m 月（未来：正数、当前：0、过去：负数）
     * @param d 日（未来：正数、当前：0、过去：负数）
     * @return
     */
    public static String getYYYYMMDD(int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        
        cal.add(Calendar.YEAR, y);
        cal.add(Calendar.MONTH, m);
        cal.add(Calendar.DATE, d);
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        return format.format(cal.getTime());
    }
}
