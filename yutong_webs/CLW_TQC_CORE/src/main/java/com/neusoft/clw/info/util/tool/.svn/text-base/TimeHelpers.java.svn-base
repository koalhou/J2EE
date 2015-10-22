/**
 * 
 */
package com.neusoft.clw.info.util.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

public class TimeHelpers {
    @SuppressWarnings("unused")
	private static final String NAME = "TimeHelpers";
    @SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(TimeHelpers.class);
    
    /** 日期-大于标记 */
    public static final int EXCEED_FLAG_OF_DATE = 1; 
    /** 日期-小于标记 */ 
    public static final int DRAGGLE_FLAG_OF_DATE = -1; 
    /** 日期-等于标记 */
    public static final int EQUAL_FLAG_OF_DATE = 0;
    
    private TimeHelpers() {
    }

    /**
     * 计算两个时间相差的秒数 (strEnd - strStart)
     * @param strStart String
     * @param strEnd String
     * @return long --相差的秒数
     */
    public static long timeDiff(String strStart, String strEnd) {
        long nSeconds = 0;
        Date dateStart = null;
        Date dateEnd = null;

        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            dateStart = inputFormat.parse(strStart); //将字符型转换成日期型
            dateEnd = inputFormat.parse(strEnd);

            nSeconds = (dateEnd.getTime() - dateStart.getTime())/1000;   // 相差的秒数
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ONLY FOR DEBUG
//        System.out.println(String.format("timeDiff = %s - %s", strEnd, strStart));
//        System.out.println(String.format("         = %d - %d", dateEnd.getTime(), dateStart.getTime()));
//        System.out.println("       =" + nSeconds);

        return Math.abs(nSeconds);
    }

    //Calendar类型转换为字符串包含年月日时分秒'yyyy-mm-dd hh:mi:ss'
    public static String calenderToString(Calendar calendar) {
        String strCalendar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
        return strCalendar;
    }

    public static Date stringToDate(String str) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
            return date;
        } catch (Exception e) {
            return null;
        }        
    }
    
    // Date类型转换为字符串包含年月日时分秒'yyyy-mm-dd hh:mi:ss'
    public static String dateToString(Date date) {
        String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date.getTime());
        return strDate;
    }
    
    // Date类型时间转换为年月日字符串'yyyy-mm-dd'
    public static String dateToStringYYYYMMDD(Date date) {
        String strDate = new SimpleDateFormat("yyyy-MM-dd").format(date.getTime());
        return strDate;
    }
    
    // 从包含了年月日时分秒的时间字符串中取得'yyyy-mm-dd'
    public static String parseYYYYMMDDFromString(String str) {
        String strYMD = null;
        try {
            strYMD = dateToStringYYYYMMDD(new SimpleDateFormat("yyyy-MM-dd").parse(str));
        } catch (Exception e) {
            //  : handle exception
        	e.getMessage();
        }
        return strYMD;
    }
    
    // 本地时间转换为UTC时间
    // by  zhang.li.zhi
    public static long transLocalTimeToUTC(Date date) {
        
       // TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        @SuppressWarnings("unused")
		Date time=new Date(); 
        //Long timecode=time.UTC(time.getYear(), time.getMonth(), time.getDate(), time.getHours(), time.getMinutes(), time.getSeconds()); 
        
        
        return calendar.getTimeInMillis() ;
    }
 
   
   // utc时间转换为本地时间
   // by  zhang.li.zhi
   public static Date utcToLocalDate(long utcTime) {
  
	   Calendar calendar = Calendar.getInstance();
       calendar.setTimeInMillis(utcTime);
       return calendar.getTime();
    }

    
    /**
     * 判断字符串能否转换为有效的'yyyy-MM-dd HH:mm:ss'格式
     */
    public static boolean isValidDateString(String strDate) {
        try {
            //System.out.println(strDate);
            //System.out.println(new SimpleDateFormat("yyyy-mm-dd").parse(strDate));
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    // 拆分时间段, 油耗重传的时候用到
    // 例如: splitTimes("2010-10-11 12:50:22", "2010-10-11 17:50:22"); 结果为: [2010-10-11 12:50:22, 2010-10-11 17:50:22]
     //     splitTimes("2010-10-11 12:50:22", "2010-10-13 17:50:22"); 结果为:  [2010-10-11 12:50:22, 2010-10-11 23:59:59, 2010-10-12 00:00:00, 2010-10-12 23:59:59, 2010-10-13 00:00:00, 2010-10-13 17:50:22]
    
    public static List<String> splitTimes(String strStartTime, String strEndTime) {
        List<String>  listOfTimes =  new ArrayList<String>();
        
        try {
          // TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
            SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
            Date dateStart = timeFormat.parse(strStartTime);
            Date dateEnd   = timeFormat.parse(strEndTime);
            
            if (dateStart.after(dateEnd)) { // dateStart晚于dateEnd,不拆分,直接返回空的list
                //System.out.println(String.format("%s在%s之后!", strStartTime, strEndTime));
                
                // 不拆分, 直接添加,
                listOfTimes.add(strStartTime);
                listOfTimes.add(strEndTime);
                return listOfTimes;
            }
            
            String strStartYYYYMMDD = TimeHelpers.parseYYYYMMDDFromString(strStartTime);
            String strEndYYYYMMDD   = TimeHelpers.parseYYYYMMDDFromString(strEndTime);
            
            if (strStartYYYYMMDD.equals(strEndYYYYMMDD)) {
                // 同一天, 直接添加时间
                listOfTimes.add(strStartTime);
                listOfTimes.add(strEndTime);
            }else {
                // 两个'yyyy-mm-dd hh24:mi:ss' 不在同一天
                listOfTimes.add(strStartTime);
                listOfTimes.add(strStartYYYYMMDD + " 23:59:59");
                Date dateNewStart = null;
               
                dateNewStart = timeFormat.parse(strStartYYYYMMDD + " 00:00:00"); //将字符型转换成日期型
                dateNewStart.setTime(dateNewStart.getTime()+24*3600*1000); // 加一天
                
                String strNewStartTime = dateToString(dateNewStart);
                
                // 递归处理
                listOfTimes.addAll(splitTimes(strNewStartTime, strEndTime));         
            }        
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfTimes;
    }
    
    /**
     * 比较两个日期的大小
     * @param dt1
     * @param dt2
     * @return 比较结果
     */
    public static int compareDate(Date dt1, Date dt2) {
        if (dt1.getTime() > dt2.getTime()) {
            return EXCEED_FLAG_OF_DATE;   //dt1大于dt2
        } else if (dt1.getTime() < dt2.getTime()) {
            return DRAGGLE_FLAG_OF_DATE;
        } else {
            return EQUAL_FLAG_OF_DATE;
        }
    }
    
    /**
     * 将字符串转为日期
     * @param aMask 日期格式字串
     * @param strDate 日期字符串
     * @return 日期
     */
    public static final Date string2date(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        return date;
    }
    
    public static void main(String[] args) {
        List<String> list = splitTimes("2010-07-11 12:50:22", "2010-6-12 17:50:22");
        for (String string : list) {
            System.out.println(string);
        }
        System.out.println(list.toString());
        
        int z = 0;
        System.out.println("===:"+(z++));
        int j = 0;
        System.out.println("===:"+(++j));
    }
}
