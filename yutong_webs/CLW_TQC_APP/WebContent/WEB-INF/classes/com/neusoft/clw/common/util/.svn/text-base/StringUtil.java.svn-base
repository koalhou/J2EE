/*******************************************************************************
 * @(#)StringUtil.java Oct 23, 2007
 *
 * Copyright 2007 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * @author <a href="mailto:hegq@neusoft.com">puras.he </a>
 * @version $Revision 1.1 $ Oct 23, 2007 3:24:18 PM
 */
public class StringUtil {

    // private static final Log log = LogFactory.getLog(StringUtil.class);
    private static final Logger log = Logger.getLogger(StringUtil.class);

    /**
     * 将字符串编码为Base64, 可以使用 decodeString进行返转
     * @param str
     * @return
     */
    public static String encodeString(String str) {
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encodeBuffer(str.getBytes()).trim();
    }

    /**
     * 将编码为Base64的字符串转换为普通的字符串
     * @param str
     * @return
     */
    public static String decodeString(String str) {
        sun.misc.BASE64Decoder dec = new sun.misc.BASE64Decoder();
        try {
            return new String(dec.decodeBuffer(str));
        } catch (IOException ex) {
            log.error(ex.getMessage());
            throw new RuntimeException(ex.getMessage(), ex.getCause());
        }
    }
    public static int stringdatebackweek(String time) {
    	SimpleDateFormat dd= new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = dd.parse(time);
			Calendar cal=Calendar.getInstance();
			cal.setTime(date);
			return cal.get(Calendar.DAY_OF_WEEK)-1;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return -1;
    }
    /**
	 * 将空值转换为空字符串
	 * 
	 * @return String
	 */
	public static String nullToStr(String str) {
		return str == null || str.equals("null") ? "" : str.trim();
	}
	
	/**
	 * 将空值转换为空0
	 * 
	 * @return String
	 */
	public static String nullToZero(String str) {
		return str == null || str.equals("null") ? "0" : str.trim();
	}

    
    /**
	 * 获取下行透传消息串(校车平台)
	 * @param msgId  消息ID
	 * @param bb  需要透传的消息内容
	 * @return
	 */
	public static  String downMsgStr(String msgId,byte[] bb) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("00042900");
		sb.append("01");
		sb.append(format(Integer.toHexString(msgId.getBytes("GBK").length), 2));
		sb.append(msgId);
		
		byte contentByte[]=bb;
		sb.append("02");
		sb.append(format(Integer
                .toHexString(contentByte.length), 4));
		
		sb.append(new String(contentByte,0,contentByte.length));
		
		return sb.toString();
		
	}
	 /**
	 * 获取下行透传消息串(专属应用)
	 * @param msgId  消息ID
	 * @param bb  需要透传的消息内容
	 * @return
	 */
	public static  byte[] downMsgStr2(String msgId,byte[] bb) throws Exception{
		StringBuffer sb = new StringBuffer();
		sb.append("00042900");
		sb.append("01");
		sb.append(format(Integer.toHexString(msgId.getBytes("GBK").length), 2));
		sb.append(msgId);
		
		byte contentByte[]=bb;
		sb.append("02");
		sb.append(format(Integer.toHexString(contentByte.length), 4));
		byte[] cc=sb.toString().getBytes();
		
		byte[] dd = new byte[cc.length+bb.length];
		System.arraycopy(cc, 0, dd, 0, cc.length);		
		System.arraycopy(bb, 0, dd, cc.length, bb.length);		
      
		return dd;
		
	}
	
	public static String format(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }
	
	public static byte[] shorts2bytes(short[] bb){
		if (bb.length==0) return null;
		byte b[] = new byte[bb.length*2];
		int loc = 0;
		for (int i=0;i<bb.length;i++){
			System.arraycopy(short2Bytes(bb[i]), 0, b, loc, 2);
			loc+=2;
		}
		return b;
		
	}
	
	public static byte[] short2Bytes(short b) {
        byte[] shortBuf = new byte[2];
        shortBuf[1] = (byte) (b & 0xff);
        shortBuf[0] = (byte) ((b >>> 8) & 0xff);
        return shortBuf;
    }
	
	
	public static String[]  toStringList(String string) {
		if(string!=null&&string.length()>0) {
			String[] str = string.split(",");
			return str;
		} else 
			return null;
	}
	
	public static String doubleToString2(double value) {
		DecimalFormat df = new DecimalFormat("######0.00");   
		return df.format(value);
	}	
	
	 /**
     * 字符串编码转换为GBK
     *
     * @param str 被处理的字符串
     * @return String 将数据库中读出的数据进行转换
     */
    public static String convert(String str) {
        String str1 = "";     
        if (str != null) {
            try {
                str1 = new String(str.getBytes("ISO8859_1"), "GBK");
            } catch (Exception e) {
                str1 = "";
            }
        }      
        return str1;
    }

    /**
     * 字符串编码反转换为ISO8859_1
     *
     * @param str 被处理的字符串
     * @return String 将jsp提交的数据进行转换放入数据库中
     */
    public static String reconvert(String str) {
        String str1 = null;       
        if (str != null) {
            try {
                str1 = new String(str.getBytes("GBK"), "ISO8859_1");
            } catch (Exception e) {
                return str1;
            }
        }
        return str1;
    }
    /*全角转半角**/
	public static String ToDBC(String input) {
		char c[] = input.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
           } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
             c[i] = (char) (c[i] - 65248);
           }
		}
		String returnString = new String(c);
		return returnString;
    }
}
