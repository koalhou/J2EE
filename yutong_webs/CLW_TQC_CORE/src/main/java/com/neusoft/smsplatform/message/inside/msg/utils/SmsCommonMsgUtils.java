package com.neusoft.smsplatform.message.inside.msg.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class SmsCommonMsgUtils {

	private SmsCommonMsgUtils() {
	}

	// private static final NumberFormat CENTERID_FORMAT = new
	// DecimalFormat("0");

	// private static final NumberFormat RANDOMSERIAL_FORMAT = new
	// DecimalFormat("0000000000");

//	private static final String[] HEXDIGITS = { "0", "1", "2", "3", "4", "5",
//			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String formatMsgLen(int msgLen) {
		return format(String.valueOf(msgLen), 4);
	}

	public static String formatOprLen(int oprLen) {
		return format(String.valueOf(oprLen), 2);
	}

	public static String formatSeqLen(int seqLen) {
		return format(String.valueOf(seqLen),8);
	}

	public static String getCurrentTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(new Date());
	}
	
//	public static String getMd5(String content) {
//		try {
//			MessageDigest md = MessageDigest.getInstance("MD5");
//			byte[] results = md.digest(content.getBytes());
//			return byteArrayToHexString(results);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

//	private static String byteArrayToHexString(byte[] b) {
//		StringBuffer resultSb = new StringBuffer();
//		for (int i = 0; i < b.length; i++) {
//			resultSb.append(byteToHexString(b[i]));
//		}
//		return resultSb.toString();
//	}

//	private static String byteToHexString(byte b) {
//		int n = b;
//		if (n < 0) {
//			n = 256 + n;
//		}
//		int d1 = n / 16;
//		int d2 = n % 16;
//		return HEXDIGITS[d1] + HEXDIGITS[d2];
//	}

	public static String format(String str, int len) {
		try {
			while (str.getBytes("GBK").length  < len) {
				str = "0" + str;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String leftformat(String str, int len) {
		try {
			while (str.getBytes("GBK").length  < len) {
				str = str + "0";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String rightspaceformat(String str, int len) {
		try {
			while (str.getBytes("GBK").length < len) {
				str = str+" ";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String leftspaceformat(String str, int len) {
		try {
			while (str.getBytes("GBK").length < len) {
				str = " "+str;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static void main(String[] args) {
			System.out.println(rightspaceformat("啊1A啊", 10));		
	}
	private static int smsseq;
	public static final int SMSMAXSEQ = 99999999;
	public static synchronized String getSeq() {
        if (smsseq == SMSMAXSEQ) {
        	smsseq = 0;
        } else {
        	smsseq++;
        }
        return String.valueOf(smsseq);
    }
}
