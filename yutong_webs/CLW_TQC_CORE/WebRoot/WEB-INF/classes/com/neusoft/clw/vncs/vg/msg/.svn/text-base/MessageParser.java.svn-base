/*******************************************************************************
 * @(#)MessageParser.java 2009-2-17
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.vg.msg;

import java.io.UnsupportedEncodingException;

import static com.neusoft.tag.core.log.LogFormatter.formatMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author <a href="mailto:zhangmq@neusoft.com">zhangmeiqiu </a>
 * @version $Revision 1.1 $ 2009-2-17 上午01:55:46
 */
public class MessageParser {

    private static final Logger LOG = LoggerFactory.getLogger(MessageParser.class);

    private String name = "MessageParser";


    //为防止其他编码带来的冗余0x00字节，内部采用UTF-8编码
    private static final String ENCODING = "UTF-8";
    private static final int I4 = 4;
    private static final int I8 = 8;
    private static final int I10 = 10;
    private static final int I16 = 16;
    private static final int I24 = 24;


    /**
     * 根据前一个字符串的长度及当前指针的问题，计算偏移后指针的位置
     * @param start：当前指针位置
     * @param previous：前一个字符串
     * @return：偏移指针后的位置
     */
    public int nextStringPosition(int start, String previous) {
        int i = 0;
        try {
            i = previous.getBytes(ENCODING).length;
        } catch (UnsupportedEncodingException e) {
            LOG.error(formatMsg(getName(),
                    "UnsupportedEncodingException when nextStringPosition, previous[" + previous + "]start[" + start + "]."
                    ), e);
        }
        // 加1为了跳过字符串结尾标示0x00
        if (i > 0) {
            return start + i + 1;
        } else {
            return start + 1;
        }
    }

    /**
     * 根据前一个字节数组的长度及当前指针的位置,计算偏移后指针的位置
     * @param start
     * @param previous
     * @return
     */
    public int nextByteArrayPosition(int start, byte[] previous) {
        int i;
        i = previous.length;
        if (i > 0) {
            return start + i + 1;
        } else {
            return start + 1;
        }
    }

    /**
     * 计算偏移一个整数的位置
     * @param start：当前指针位置
     * @return：偏移后指针的位置
     */
    public int nextIntegerPosition(int start) {
        return start + I4;
    }

    /**
     * 将字符串转换成以0x00结尾的字符数组
     * @param input：字符串
     * @return：字符数组
     */
    public byte[] stringToNullTerminateByteArray(String input) {
        int len;
        byte[] output = null;
        byte[] temp;
        if (input == null) {
            output = new byte[1];
            output[0] = 0x00;
        } else {
            try {
                temp = input.getBytes(ENCODING);
                len = temp.length;
                output = new byte[len + 1];
                System.arraycopy(temp, 0, output, 0, len);
                output[len] = 0x00;
            } catch (UnsupportedEncodingException e) {
                output = new byte[1];
                output[0] = 0x00;
            }
        }
        return output;
    }

    /**
     * 将字符数据中从某一位置开始的数组转换为字符串，字符串以0x00结尾
     * @param src：数组
     * @param begin：字符开始位置
     * @return：解析后的字符串
     */
    public String byteArrayToNullTerminateString(byte[] src, int begin) {
        int i;
        byte[] temp;
        i = begin;
        while (src[begin] != 0x00) {
            begin++;
        }
        if (i != begin) {
            temp = new byte[begin - i];
            System.arraycopy(src, i, temp, 0, begin - i);
            try {
                return new String(temp, ENCODING);
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        } else {
            return "";
        }
    }

    /**
     * 将数组中某一位置开始的数组解析为整数
     * @param src：原数组
     * @param begin：开始位置
     * @return：解析后的整数
     */
    public int byteArrayToInteger(byte[] src, int begin) {
        byte[] temp = new byte[I4];
        int newInt = 0x00000000;
        System.arraycopy(src, begin, temp, 0, I4);
        newInt |= ((((int) temp[0]) << I24) & 0xff000000);
        newInt |= ((((int) temp[1]) << I16) & 0x00ff0000);
        newInt |= ((((int) temp[2]) << I8) & 0x0000ff00);
        newInt |= (((int) temp[3]) & 0x000000ff);
        return newInt;
    }

    /**
     * 将字符串转换成指定长度的字节数组，如果字符串长度小于要求长度则后补0x00，
     * 如果字符串 长度大于要求长度则对其进行截取。
     * @param input：输入字符串
     * @param byteArrayLen：输出字节数组的长度
     * @return：输出字节数组
     */
    public byte[] string2ByteArray(String input, int byteArrayLen) {
        int i;
        int stringLen;
        byte[] output;
        byte[] temp;
        if (input == null) {
            output = new byte[byteArrayLen];
            for (i = 0; i < byteArrayLen; i++) {
                output[i] = 0x00;
            }
        } else {
            temp = input.getBytes();
            stringLen = temp.length;
            output = new byte[byteArrayLen];
            if (byteArrayLen >= stringLen) {
                System.arraycopy(temp, 0, output, 0, stringLen);
            } else {
                System.arraycopy(temp, 0, output, 0, byteArrayLen);
            }
        }
        return output;
    }

    /**
     * 将输入数组转换成指定长度的数组，长度不足右侧补0x00，超长截取
     * @param input：输入数组
     * @param byteArrayLen：转换长度
     * @return：转换后数组
     */
    public byte[] byteArray2ByteArray(byte[] input, int byteArrayLen) {
        byte[] output = new byte[byteArrayLen];
        if (input != null) {
            int inputLen = input.length;
            if (byteArrayLen >= inputLen) {
                System.arraycopy(input, 0, output, 0, inputLen);
            } else {
                System.arraycopy(input, 0, output, 0, byteArrayLen);
            }
        }
        return output;
    }

    /**
     * 将输入数组转换成数组,直到遇到0x00为止
     * @param input byte[]
     * @Param begin int 开始转换位置
     * @return
     */
    public byte[] byteArray2ByteArray2(byte[] src, int begin) {
        int i;
        byte[] temp = null;
        i = begin;
        while (src[begin] != 0x00) {
            begin++;
        }
        if (i != begin) {
            temp = new byte[begin - i];
            System.arraycopy(src, i, temp, 0, begin - i);
        }
        return temp;
    }

    /**
     * 将字节输入右补的0x00去掉
     * @param input：输入字节数组
     * @return：去掉右补的0x00的字节数组
     */
    public byte[] rTrimByteArray(byte[] input) {
        int i = 0;
        byte[] output = null;
        int inputLen = input.length;
        while (i < inputLen) {
            if (input[i] == 0x00) {
                break;
            }
            i++;
        }
        output = new byte[i];
        System.arraycopy(input, 0, output, 0, i);
        return output;
    }

    /**
     * 将Long输出为一个用字符串表示的无符号Long
     * @param input：输入Long型数
     * @return：字符串表示的无符号Long
     */
    public String long2UnsignedLongString(long in) {
        java.math.BigInteger b = new java.math.BigInteger(Long.toHexString(in), I16);
        return b.toString(I10);
    }

    public String getName() {
        return name;
    }


}
