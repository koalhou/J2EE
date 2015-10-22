/*******************************************************************************
 * @(#)StringUtil.java Oct 23, 2007
 *
 * Copyright 2007 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util;

import java.io.File;
import java.io.IOException;
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
    /**
     * 判断字符是否为空
     * @param str
     * @return
     */
    public static boolean isNull(String str){
    	if(str==null||str.equals("")){
    		return false;
    	}
    	return  true;
    }
    /**
     * 路径转换 /转\\
     * @param path 原始路径
     * @return
     */
    public static String toRealPath(String path){
    	String newPath=path.replace(path.subSequence(0, 1), File.separator);
    	return newPath;
    }

	public static String[]  toStringList(String string) {
		if(string!=null&&string.length()>0) {
			String[] str = string.split(",");
			return str;
		} else 
			return null;
	}
    
}
