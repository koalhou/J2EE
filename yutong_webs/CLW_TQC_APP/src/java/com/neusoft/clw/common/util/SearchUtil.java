/*******************************************************************************
 * @(#)SearchUtil.java 2009-12-10
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.util;

/**
 * @author <a href="mailto:x_wang@neusoft.com">x_wang </a>
 * @version $Revision 1.1 $ 2009-12-10 03:43:44
 */
public class SearchUtil {
    /**
     * 将特殊字符转化为普通字符，并去掉前后空格，以便进行模糊查询
     * @param String 需要进行转义的特殊字符
     * @return String 普通字符
     */
    public static String special2Usual(String str) {
        str = str.replace("\\", "\\\\");
        str = str.replace("％", "\\%");
        str = str.replace("　", " ");
        str = str.replace("＿", "\\_");
        str = str.replace("%", "\\%");
        str = str.replace("_", "\\_");
        str = str.replace("'", "''");
        str = str.trim();
        return str;
    }

    public static String timeFormat(String str) {
        str = str.replace("-", "");
        str = str.replace(" ", "");
        str = str.replace(":", "");
        str = str.trim();
        return str;
    }

    /**
     * 过滤通配符、空格
     * @param s
     * @return
     */
    public static String formatSpecialChar(String s) {
        if (null == s || s.length() == 0) {
            return s;
        }
        
        s = s.replace("\\", "\\\\");
        s = s.replace("%", "\\%");
        s = s.replace("_", "\\_");
        s = s.replace("％", "\\%");
        s = s.replace("＿", "\\_");
        
        s = s.trim();
        
        return s;
    }
}
