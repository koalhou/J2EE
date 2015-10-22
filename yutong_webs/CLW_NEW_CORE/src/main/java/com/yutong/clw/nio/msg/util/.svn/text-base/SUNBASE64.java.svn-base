package com.yutong.clw.nio.msg.util;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SUNBASE64 {
	private static Logger log = LoggerFactory.getLogger(SUNBASE64.class);

    /**
     * 将字符串编码为Base64, 可以使用 decodeString进行返转?
     * @param str
     * @return
     */
    public static String encodeString(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(str.getBytes()).trim();
    }
    
    /**
     * 将字符串编码为Base64, 可以使用 decodeString进行返转?
     * @param str
     * @return
     */
    public static String encodeString(byte[] str) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encodeBuffer(str).trim();
    }

    /**
     * 将编码为Base64的字符串转换为普通的字符串
     * @param str
     * @return
     * @throws IOException
     */
    public static String decodeString(String str) {
        BASE64Decoder dec = new BASE64Decoder();
        String temp = null;
        try {
            temp = new String(dec.decodeBuffer(str));
        } catch (IOException e) {
            log.error("SUNBASE64 decode error!");
        }
        return temp;
    }
}
