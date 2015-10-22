package com.neusoft.clw.info.util.tool;

import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

    /**
     * 判断字符串是否为空，包括空格的处理
     * @param str 待验证字符串 
     * @return 结果
     */
    public static boolean isEmpty(String str) {
        if (StringUtils.isEmpty(str)) {
            return true;
        }
        if ("".equals(str.trim())) {
            return true;
        }
        return false;
    }

    /**
     * 取得随机数
     * @param len 随机数长度
     * @return 生成的随机数
     */
    public static String getRandomString(final int len) {
        Random random = new Random();
        /** 数字与字母字典 */
        final char[] LETTER_AND_DIGIT = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ")
                .toCharArray();

        if (len < 1) return "";
        StringBuffer sb = new StringBuffer(len);
        for (int i = 0; i < len; i++) {
            sb.append(LETTER_AND_DIGIT[random.nextInt(LETTER_AND_DIGIT.length)]);
        }
        return sb.toString();
    }

    /**
     * 获取设置日志模块sessionid的随机数串
     * @return
     */
    public static String getLogRadomStr() {
//        return DateUtil.now2string("yyyyMMddHHmmssms") + getRandomString(2);
        return getRandomString(6);
    }

    public static void main(String[] args) {
        
    }

}
