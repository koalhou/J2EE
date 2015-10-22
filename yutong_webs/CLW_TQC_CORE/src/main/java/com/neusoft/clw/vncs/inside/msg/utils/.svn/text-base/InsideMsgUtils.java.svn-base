package com.neusoft.clw.vncs.inside.msg.utils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.vncs.inside.msg.InsideMsgStatusCode;
import com.neusoft.clw.vncs.inside.msg.req.BindReq;
import com.neusoft.clw.vncs.inside.msg.req.UpLoadDataReq;

//import com.neusoft.log.Logformater;
//import com.neusoft.tag.app.inside.msg.InsideMsgStatusCode;
//import com.neusoft.tag.app.inside.msg.req.BindReq;
//import com.neusoft.tag.app.inside.msg.req.UpLoadDataReq;
//import com.neusoft.tag.ota.constant.OtaModule;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-11-28 上午09:55:40
 */
public final class InsideMsgUtils {

    private static final Logger LOG = LoggerFactory.getLogger(InsideMsgUtils.class);

    private InsideMsgUtils() {
    }

    private static int seq,tseq;

    public static final int MAXSEQ = 9999;

    private static final NumberFormat MSGLEN_FORMAT = new DecimalFormat("00000000");

    private static final NumberFormat COMMAND_FORMAT = new DecimalFormat("0000");

    private static final NumberFormat STATUSCODE_FORMAT = new DecimalFormat("0000");

    private static final NumberFormat SEQ_FORMAT = new DecimalFormat("0000");

    private static final String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F" };

    public static String formatMsgLen(int msgLen) {
        return MSGLEN_FORMAT.format(msgLen);
    }

    public static String formatCommand(String command) {
        return COMMAND_FORMAT.format(Integer.parseInt(command.trim()));
    }

    public static String formatStatusCode(String statusCode) {
        return STATUSCODE_FORMAT.format(Integer.parseInt(statusCode.trim()));
    }

    public static String formatSeq(String msgSeq) {
        return SEQ_FORMAT.format(Integer.parseInt(msgSeq.trim()));
    }

    public static String formatTime(String time) {
        return format(time, BindReq.TIMESIZE);
    }

    public static String formatSystemId(String systemId) {
        return format(systemId, BindReq.SYSTEMIDSIZE);
    }

    public static String formatMd5Code(String md5Code) {
        return format(md5Code, BindReq.MD5CODESIZE);
    }

    public static String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    public static synchronized String getSeq() {
        if (seq == MAXSEQ) {
            seq = 0;
        } else {
            seq++;
        }
        return String.valueOf(seq);
    }
    public static synchronized String getTerminalSeq() {
        if (tseq == MAXSEQ) {
            tseq = 0;
        } else {
            tseq++;
        }
        return String.valueOf(tseq);
    }
    public static String formatPassword(String password) {
        return format(password, BindReq.PASSWORDSIZE);
    }

    public static String getMd5(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] results = md.digest(content.getBytes());
            return byteArrayToHexString(results);
        } catch (Exception e) {
            LOG.error(LogFormatter.formatMsg("CLW_C ", "construct md5 code field."));
            return null;
        }
    }

    /**
     * 轮换字节数组为十六进制字符串
     * @param b 字节数组
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    /**
     * 将一个字节转化成十六进制形式的字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0) {
            n = 256 + n;
        }
        int d1 = n / 16;
        int d2 = n % 16;
        return HEXDIGITS[d1] + HEXDIGITS[d2];
    }

    public static boolean checkMsgCommand(String command) {
        // return InsideProcessorMap.getInstance().containsKey(command.trim());
        return true;
    }

    public static boolean checkStatusCode(String statusCode) {
        return InsideMsgStatusCode.getInstance().contains(statusCode.trim());
    }

    public static boolean checkSeq(int parseInt) {
        return (seq < 0 || seq > InsideMsgUtils.MAXSEQ) ? false : true;
    }

    public static String formatTerminalId(String terminalId) {
        return format(terminalId, UpLoadDataReq.TERMINALIDSIZE);
    }

    public static String format(String str, int len) {
        while (str.length() < len) {
            str = str + " ";
        }
        return str;
    }

    public static String formatQXJia(String str, int len) {
        while (str.length() < len) {
            str = "0" + str;
        }
        return str;
    }
    public static String formatPacketLen(int packetLen) {
        return MSGLEN_FORMAT.format(packetLen);
    }
    
    public static String formatSignal(String str,int len){
    	StringBuffer sb = new StringBuffer(str);
    	while(str.length()<len){
    		sb.append("0");
    	}
    	return sb.toString();
    }
    
    public static String formatRouteid(String routeid) {
        return formatQXJia(routeid, 4 * 2);
    }
    
    public static String formatStuid(String stuid) {
        return formatQXJia(stuid, 4 * 2);
    }
    
    public static String formatCmdid(String cmdid) {
        return format(cmdid, 1 * 2);
    }
    
    public static String formatIpLength(String iplength) {
        return formatQXJia(iplength, 1 * 2);
    }
    
    public static String formatPort(String port) {
        return formatQXJia(port, 2 * 2);
    }
    
    public static String formatUserLength(String userlength) {
        return formatQXJia(userlength, 1 * 2);
    }
    
    public static String formatPassLength(String passlength) {
        return formatQXJia(passlength, 1 * 2);
    }
    
    public static String formatFileLength(String filelength) {
        return formatQXJia(filelength, 1 * 2);
    }
    
    public static String formatCrc(String crc) {
        return format(crc, 4 * 2);
    }
    
}
