package com.yutong.clw.nio.msg.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/*******************************************************************************
 * @(#)Helper.java 2009-8-24
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

/**
 * @author <a href="mailto:zhangmq@neusoft.com">zhangmeiqiu </a>
 * @version $Revision 1.1 $ 2009-8-24 上午06:07:15
 */
public class Converser {

    public static final int DIRECTION_LEFT = 0;

    public static final int DIRECTION_RIGHT = 1;

    /**
     * 把单个字节转换成16进制字符串
     * @param bArray
     * @return
     */
    public static String byteToHexString(byte bArray) {
        StringBuffer sb = new StringBuffer(1);
        String sTemp;
        for (int i = 0; i < 1; i++) {
            sTemp = Integer.toHexString(0xFF & bArray);
            if (sTemp.length() < 2) sb.append(0);
            sb.append(sTemp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * @函数功能: BCD码转为10进制串(阿拉伯数据)
     * @输入参数: BCD码
     * @输出结果: 10进制串
     */
    public static String bcdToStr(byte[] src, int pos, int len) {
        byte[] bytes = new byte[len];
        System.arraycopy(src, pos, bytes, 0, len);
        StringBuffer temp = new StringBuffer(bytes.length * 2);

        for (int i = 0; i < bytes.length; i++) {
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));
            temp.append((byte) (bytes[i] & 0x0f));
        }
        return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp.toString().substring(1)
                : temp.toString();
    }

    /**
     * @函数功能: 10进制串转为BCD码
     * @输入参数: 10进制串
     * @输出结果: BCD码
     */
    public static byte[] strToBcd(String asc) {
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        byte abt[] = new byte[len];
        if (len >= 2) {
            len = len / 2;
        }

        byte bbt[] = new byte[len];
        abt = asc.getBytes();
        int j, k;

        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }

            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }

            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    private final static byte[] hex = "0123456789ABCDEF".getBytes();

    private static int parse(char c) {
        if (c >= 'a') return (c - 'a' + 10) & 0x0f;
        if (c >= 'A') return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    /**
     * 从字节数组到十六进制字符串转换。
     * @param b
     * @return
     */
    public static String bytesToHexString(byte[] b) {
        byte[] buff = new byte[2 * b.length];
        for (int i = 0; i < b.length; i++) {
            buff[2 * i] = hex[(b[i] >> 4) & 0x0f];
            buff[2 * i + 1] = hex[b[i] & 0x0f];
        }
        return new String(buff);
    }

    /**
     * 从十六进制字符串到字节数组转换。
     * @param hexstr
     * @return
     */
    public static byte[] hexStringToBytes(String hexstr) {
    	if (null == hexstr) {
            return null;
        }
        if (hexstr.length() % 2 != 0) {

              hexstr = "0" + hexstr;
        }
        
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;
        for (int i = 0; i < b.length; i++) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return b;
    }

    public static byte hexStringToByte(String hexstr) {
        if (hexstr.length() != 2) {
            return 0;
        }

        byte b = 0;
        char c0 = hexstr.charAt(0);
        char c1 = hexstr.charAt(1);
        b = (byte) ((parse(c0) << 4) | parse(c1));

        return b;
    }

    public static String trimChar(String src, char c, int direction) {

        String dest = "";
        if (src == null || src.length() == 0) {
            return dest;
        }

        int pos = 0;
        if (direction == DIRECTION_RIGHT) {
            pos = src.length() - 1;
            for (int i = src.length(); i > 0; i--) {
                if (src.charAt(i - 1) != c) {
                    pos = i;
                    break;
                }
            }

            dest = src.substring(0, pos);

        } else {
            for (int i = 0; i < src.length(); i++) {
                if (src.charAt(i) != c) {
                    pos = i;
                    break;
                }
            }

            dest = src.substring(pos, src.length());
        }

        return dest;
    }

    public static String converToString(String src, int length, char c) {
        for (int j = src.length(); j < length; j++) {
            src = c + src;
        }
        return src;
    }

    public static long getUTC() {        // 从2000年1月1日（UTC/GMT00:00:00Z）开始所经过的秒数，不考虑闰秒。
        return (new Date().getTime() / 1000 - 946656000);
    }

    public static void main(String[] args) {
    	
    	
//    	System.out.println((int)hexStringToByte("0B"));
    	System.out.println(hexStringToBytes("0B"));
    	
        // String src = "13898812305";
        // int length = 16;
        // char c = 'F';
        // for (int j = src.length(); j < length; j++) {
        // src = c + src;
        // }
        //
        // System.out.println(src);
        //
        // byte[] b = Converser.hexStringToBytes(src);
        //
        // ByteBuffer buffer = new ByteBuffer();
        // buffer.appendShort(ByteData.encodeUnsigned(12));
        // buffer.appendByte(Converser.hexStringToByte("01"));
        // buffer.appendByte((byte) 18);
        // buffer.appendByte(Converser.hexStringToByte("01"));
        // buffer.appendByte(Converser.hexStringToByte("01"));
        // buffer.appendByte(Converser.hexStringToByte("01"));
        // buffer.appendByte(Converser.hexStringToByte("01"));
        // buffer.appendInt(ByteData.encodeUnsigned(1));
        // String devid = Converser.converToString("13898812305", 16, 'F');
        // buffer.appendBytes(Converser.hexStringToBytes(devid));
        // buffer.appendInt(ByteData.encodeUnsigned(1));
        //
        // System.out.println(Converser.bytesToHexString(buffer.getBuffer()));
        //
        // int ii = 0;
        @SuppressWarnings("unused")
		String s = "0018900100000000000EFFFFF15998359891129CB49840000101000354696400045052535410027049002500017400012000036C61740004053EDF8800036C6F6E000419566FA20003616C74000201F3000173000200160001640002000300026D65000400000000000174000100000161000101000167000102";
        System.out.println(UUID.randomUUID().toString());
//        byte[] b = Converser.hexStringToBytes(s);
//        System.out.println(b);
//        Short ss = Converser.getCRC16(b);
//
//        System.out.println(ss);
    }

    public static short bytes2Short(byte[] b, int pos) {
        return (short) (((b[pos] << 8) | b[pos + 1] & 0xff));
    }
    
    public static int bytes2int(byte b[]) {
        int s = 0;
        s = ((((b[0] & 0xff) << 8 | (b[1] & 0xff)) << 8) | (b[2] & 0xff)) << 8 | (b[3] & 0xff);
        return s;
        }

    public static byte[] short2Bytes(short b) {
        byte[] shortBuf = new byte[2];
        shortBuf[1] = (byte) (b & 0xff);
        shortBuf[0] = (byte) ((b >>> 8) & 0xff);
        return shortBuf;
    }

    public static byte[] int2Bytes(int b) {
        byte[] intBuf = new byte[4];
        for (int i = 0; i < 4; i++) {
            intBuf[i] = (byte) (b >> 8 * (3 - i) & 0xFF);
        }
        return intBuf;
    }

    public static short getCRC16(byte[] src) {

        short crc = (short) 0xFFFF;
        int i, j;
        boolean c15, bit;

        for (i = 0; i < src.length; i++) {
            for (j = 0; j < 8; j++) {
                c15 = ((crc >> 15 & 1) == 1);
                bit = ((src[i] >> (7 - j) & 1) == 1);
                crc <<= 1;
                if (c15 ^ bit) crc ^= 0x1021;
            }
        }

        return crc;
    }

    public static byte[] md5(byte[] src) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return md.digest(src);
    }
    
    public static  void main1(String args[]){
    	System.out.println();
    	System.out.println();
    	
    } 
}
