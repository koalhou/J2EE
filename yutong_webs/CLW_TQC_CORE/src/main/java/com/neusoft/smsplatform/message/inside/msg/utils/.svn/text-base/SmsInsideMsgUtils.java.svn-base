package com.neusoft.smsplatform.message.inside.msg.utils;

import com.neusoft.smsplatform.message.inside.msg.MessageInsideMsgStatusCode;
import com.neusoft.smsplatform.message.inside.processor.MessageInsideProcessorMap;

//import com.neusoft.log.Logformater;
//import com.neusoft.tag.app.inside.msg.InsideMsgStatusCode;
//import com.neusoft.tag.app.inside.msg.req.BindReq;
//import com.neusoft.tag.app.inside.msg.req.UpLoadDataReq;
//import com.neusoft.tag.ota.constant.OtaModule;


public final class SmsInsideMsgUtils {

//    private static final Logger LOG = LoggerFactory.getLogger(SmsInsideMsgUtils.class);

    private SmsInsideMsgUtils() {
    }

//    private static int smsseq,ttseq;

    public static final int SMSMAXSEQ = 9999;
    
    public static final int TTMAXSEQ = 9999;

//    private static final NumberFormat MSGLEN_FORMAT = new DecimalFormat("0000");
//
//    private static final NumberFormat COMMAND_FORMAT = new DecimalFormat("0000");
//
//    private static final NumberFormat STATUSCODE_FORMAT = new DecimalFormat("0000");
//
//    private static final NumberFormat SEQ_FORMAT = new DecimalFormat("0000");
//
//    private static final String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
//            "A", "B", "C", "D", "E", "F" };
//
//    public static String formatMsgLen(int msgLen) {
//        return MSGLEN_FORMAT.format(msgLen);
//    }
//
//    public static String formatCommand(String command) {
//        return COMMAND_FORMAT.format(Integer.parseInt(command.trim()));
//    }
//
//    public static String formatStatusCode(String statusCode) {
//        return STATUSCODE_FORMAT.format(Integer.parseInt(statusCode.trim()));
//    }
//
//    public static String formatSeq(String msgSeq) {
//        return SEQ_FORMAT.format(Integer.parseInt(msgSeq.trim()));
//    }
//
//    public static String getCurrentTime() {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        return dateFormat.format(new Date());
//    }

//    public static synchronized String getSeq() {
//        if (smsseq == SMSMAXSEQ) {
//        	smsseq = 0;
//        } else {
//        	smsseq++;
//        }
//        return String.valueOf(smsseq);
//    }
//    public static synchronized String getTerminalSeq() {
//        if (ttseq == TTMAXSEQ) {
//            ttseq = 0;
//        } else {
//            ttseq++;
//        }
//        return String.valueOf(ttseq);
//    }
//    public static String formatPassword(String password) {
//        return format(password, MessageBindReq.PASSWORDSIZE);
//    }

//    public static String getMd5(String content) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] results = md.digest(content.getBytes());
//            return byteArrayToHexString(results);
//        } catch (Exception e) {
//            LOG.error(LogFormatter.formatMsg("CLW_C ", "construct md5 code field."));
//            return null;
//        }
//    }
//
//    /**
//     * 轮换字节数组为十六进制字符串
//     * @param b 字节数组
//     * @return 十六进制字符串
//     */
//    private static String byteArrayToHexString(byte[] b) {
//        StringBuffer resultSb = new StringBuffer();
//        for (int i = 0; i < b.length; i++) {
//            resultSb.append(byteToHexString(b[i]));
//        }
//        return resultSb.toString();
//    }
//
//    /**
//     * 将一个字节转化成十六进制形式的字符串
//     */
//    private static String byteToHexString(byte b) {
//        int n = b;
//        if (n < 0) {
//            n = 256 + n;
//        }
//        int d1 = n / 16;
//        int d2 = n % 16;
//        return HEXDIGITS[d1] + HEXDIGITS[d2];
//    }

    public static boolean checkMsgCommand(String command) {
         return MessageInsideProcessorMap.getInstance().containsKey(command.trim());
//        return true;
    }

    public static boolean checkStatusCode(String statusCode) {
        return MessageInsideMsgStatusCode.getInstance().contains(statusCode.trim());
    }

//    public static boolean checkSeq(int parseInt) {
//        return (smsseq < 0 || smsseq > SmsInsideMsgUtils.SMSMAXSEQ) ? false : true;
//    }

    public static String format(String str, int len) {
        while (str.length() < len) {
            str = str + " ";
        }
        return str;
    }
    
//    public static String formatPacketLen(int packetLen) {
//        return MSGLEN_FORMAT.format(packetLen);
//    }
    
    public static String formatSignal(String str,int len){
    	StringBuffer sb = new StringBuffer(str);
    	while(str.length()<len){
    		sb.append("0");
    	}
    	return sb.toString();
    }
}
