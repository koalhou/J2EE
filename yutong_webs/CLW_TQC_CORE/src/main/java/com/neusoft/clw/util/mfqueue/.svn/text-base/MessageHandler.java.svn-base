package com.neusoft.clw.util.mfqueue;

import java.text.*;
import java.io.*;
import java.util.*;

public class MessageHandler {
    public MessageHandler() {
    }

    /**
     * 从输入流中读取数据包到数组中,包的前四位为包长度
     * @param is:输入流
     * @return:数据包数组
     * @throws IOException
     */
    public byte[] readPacketInto(InputStream is) throws IOException {
        byte[] packetLen = new byte[4];
        byte[] message;
        int totalLen;

        packetLen[0] = (byte) is.read();
        packetLen[1] = (byte) is.read();
        packetLen[2] = (byte) is.read();
        packetLen[3] = (byte) is.read();

        totalLen = (getBytesAsInt(packetLen[0]) << 24) | (getBytesAsInt(packetLen[1]) << 16)
                | (getBytesAsInt(packetLen[2]) << 8) | (getBytesAsInt(packetLen[3]));
        // if (packetLen[3] == -1)
        // throw new EOFException("Socket输入流异常结束。");
        // 注释上述信息，由于与pdsmp之间通信存在问题，包长度末尾大于255时出现异常
        // modify by wangwei at 2004-3-16
        message = new byte[totalLen];
        message[0] = packetLen[0];
        message[1] = packetLen[1];
        message[2] = packetLen[2];
        message[3] = packetLen[3];
        for (int i = 4; i < totalLen; i++)
            message[i] = (byte) is.read();
        return message;
    }

    public int getBytesAsInt(byte i_byte) {
        return i_byte & 0xff;
    }

    /**
     * 从字节数组中获取所要的整数
     * @param msg
     * @param len
     * @param messageIndex
     * @return
     */
    public int getIntegerValue(byte[] msg, int len, int messageIndex) throws Exception {
        if (len > 4) {
            throw new Exception("非法整数转换字节数[" + len + "]");
        }
        byte[] ba = new byte[4];
        int newInt = 0x00000000;
        int l = len - 1;
        int ia = 3;

        for (int i = l; i >= 0; i--) {
            ba[ia] = msg[messageIndex + i];
            ia--;
        }
        for (int i = ia; i >= 0; i--) {
            ba[i] = 0x00;
        }

        newInt |= ((((int) ba[0]) << 24) & 0xff000000);
        newInt |= ((((int) ba[1]) << 16) & 0x00ff0000);
        newInt |= ((((int) ba[2]) << 8) & 0x0000ff00);
        newInt |= (((int) ba[3]) & 0x000000ff);

        return newInt;
    }

    /**
     * 从字节数组中读取c-otect-string类型的字符串
     * @param msg
     * @param maxlength
     * @param messageIndex
     * @return
     * @throws SAPParserException
     */
    public String geCOctecttStringValue(byte[] msg, int maxlength, int messageIndex)
            throws Exception {
        boolean nullReached = false;
        int target_len = 0;
        int msg_len = msg.length;
        int startPoint = messageIndex;
        while ((!nullReached) && (messageIndex <= msg_len) && (target_len < maxlength)) {
            if (msg[messageIndex] == (byte) 0x00) {
                nullReached = true;
            } else {
                target_len++;
            }
            messageIndex++;
        }

        if (target_len < maxlength) {
            byte[] result = new byte[target_len];
            System.arraycopy(msg, startPoint, result, 0, target_len);
            return new String(result);
        } else {
            throw new Exception("字符串转换长度[" + target_len + 1 + "]超过最大长度[" + maxlength + "]");
        }
    }

    /**
     * 从字节数据中读取一定长度的字符串（otect-string）
     * @param msg
     * @param fieldActualLength
     * @param messageIndex
     * @return
     */
    public String getOctectStringValue(byte[] msg, int fieldActualLength, int messageIndex) {
        int target_len = 0;
        int msg_len = msg.length;
        int startPoint = messageIndex;
        while ((messageIndex <= msg_len) && (target_len < fieldActualLength)) {
            target_len++;
            messageIndex++;
        }
        byte[] result = new byte[target_len];
        System.arraycopy(msg, startPoint, result, 0, target_len);
        return new String(result);
    }

    /**
     * 将整数转换为指定长度的字节数组
     * @param i
     * @param numBytes
     * @return
     */
    public byte[] makeByteArrayFromInt(int i, int numBytes) {
        byte[] result = new byte[numBytes];
        int shiftBits = (numBytes - 1) * 8;

        for (int j = 0; j < numBytes; j++) {
            result[j] = (byte) (i >>> shiftBits);
            shiftBits -= 8;
        }
        return result;
    }

    /**
     * 将字符串转换成以二进制零为结束符字节数组
     * @param input
     * @return
     */
    public byte[] stringToNullTerminatedByteArray(String input) {
        int len;
        byte[] output;
        if (input == null) {
            output = new byte[1];
            output[0] = 0x00;
        } else {
            len = input.length();
            output = new byte[len + 1];
            for (int i = 0; i < len; i++) {
                output[i] = (byte) input.charAt(i);
            }
            output[len] = (byte) 0x00;
        }
        return output;
    }

    /**
     * 将字符串转换成字节数组
     * @param input
     * @return
     * @throws NullPointerException
     */
    public byte[] stringToByteArray(String input) throws NullPointerException {
        int i, len;
        byte[] output;
        if (input == null) {
            throw new NullPointerException("输入字符串为空。");
        } else {
            len = input.length();
            output = new byte[len + 1];
            for (i = 0; i < len; i++) {
                output[i] = (byte) input.charAt(i);
            }
            output[i] = 0;
        }
        return output;
    }

    /**
     * 将字符串转换为以0x00结尾的字符数组
     * @param input：字符串
     * @return：字符数组
     * @throws NullPointerException
     * @throws UnsupportedEncodingException
     */
    public byte[] stringToNullTerminateGB2312ByteArray(String input) throws NullPointerException,
            UnsupportedEncodingException {
        int i, len;
        byte[] output;
        byte[] temp;
        if (input == null) {
            throw new NullPointerException("输入字符串为空。");
        } else {
            temp = input.getBytes("GB2312");
            len = temp.length;
            output = new byte[len + 1];
            for (i = 0; i < len; i++) {
                output[i] = temp[i];
            }
            output[i] = 0;
        }
        return output;
    }

    /**
     * 形成SMPP规定的日期类型串
     * @param date
     * @return
     */
    public String dateToSMPPString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMddHHmmss");
        return (formatter.format(date) + "032+");
    }

    public String RTrimNull(String msg) {
        String temp = "";
        int len = msg.length();
        int i = 0;
        for (i = len - 1; i > 0; i--) {
            if (msg.charAt(i) != 0x00) {
                break;
            }
        }
        temp = msg.substring(0, i + 1);
        return temp;
    }
}
