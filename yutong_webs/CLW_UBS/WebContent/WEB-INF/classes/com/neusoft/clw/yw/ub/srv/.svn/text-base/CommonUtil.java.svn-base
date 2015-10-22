package com.neusoft.clw.yw.ub.srv;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.neusoft.clw.common.util.DateUtil;

public class CommonUtil
{
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 
      * 函数介绍：获取指定时间跨周的周一
      * 参数：
      * 返回值：跨周返回周一，否则为null
     */
    public static String getMonday4Day(String dateStr){
        Date day=DateUtil.parseStringToDate(dateStr, YYYY_MM_DD);
        
        Calendar cal= Calendar.getInstance();
        cal.setTime(day);
        int week=cal.get(Calendar.DAY_OF_WEEK);
        if(week==1){
            return null;
        }
         cal.add(Calendar.DAY_OF_YEAR, -(week-2));
       return   DateUtil.formatDateToString(cal.getTime(), YYYY_MM_DD);
    }
    /**
     * 
      * 函数介绍：获取指定时间跨周的周一
      * 参数：
      * 返回值：跨周返回周一，否则为null
     */
    public static String getMOnday4Week(String dateStr){
        Date day=DateUtil.parseStringToDate(dateStr, YYYY_MM_DD);
        
        Calendar cal= Calendar.getInstance();
        cal.setTime(day);
        int week=cal.get(Calendar.DAY_OF_WEEK);
        if(week==2){
            return null;
        }
         cal.add(Calendar.DAY_OF_YEAR, (8-week));
       return   DateUtil.formatDateToString(cal.getTime(), YYYY_MM_DD);
    }
    /**
     * 
      * 函数介绍：获取指定时间跨月的第一天
      * 参数：
      * 返回值：跨月返回第一天，否则为null
     */
    public static String getFirstDay(String dateStr){
        Date day=DateUtil.parseStringToDate(dateStr, YYYY_MM_DD);
        Calendar cal= Calendar.getInstance();
        cal.setTime(day);
        int i=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int tmp=cal.get(Calendar.DAY_OF_MONTH);
        if(tmp==i){
            return null;
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
       return   DateUtil.formatDateToString(cal.getTime(), YYYY_MM_DD);
    }
    /**
     * 
      * 函数介绍：获取指定时间跨月的第一天
      * 参数：
      * 返回值：跨月返回第一天，否则为null
     */
    public static String getLastDay(String dateStr){
        Date day=DateUtil.parseStringToDate(dateStr, YYYY_MM_DD);
        Calendar cal= Calendar.getInstance();
        cal.setTime(day);
        int tmp=cal.get(Calendar.DAY_OF_MONTH);
        if(tmp==1){
            return null;
        }
        int i=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, i);
       return   DateUtil.formatDateToString(cal.getTime(), YYYY_MM_DD);
    }
    
    /**
     * 
      * 函数介绍：计算占比
      * 参数：a 单个数值  b 总数
      * 返回值：精度为3的百分数
     */
    public static float getPercent(int a,int b){
        if(a==0||b==0){
            return 0;
        }
        BigDecimal bd1=new BigDecimal(a);
        BigDecimal bd2=new BigDecimal(b);
        return bd1.divide(bd2,3, BigDecimal.ROUND_HALF_UP).movePointRight(2).floatValue();
    }
}
