package com.neusoft.smsplatform.message.inside.msg.resp;

import java.io.UnsupportedEncodingException;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;

public class ReceiveStuInfoResp extends MesssageInsideMsg {

    public static final String COMMAND = "03";
    public static final int total = 25;
    private static final int STUIDSIZE = 10;
    private String result;
    private String stu_id;

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stuId) {
		stu_id = (stuId==null||stuId.equals(""))?SmsCommonMsgUtils.format("", STUIDSIZE):SmsCommonMsgUtils.format(stuId, STUIDSIZE);
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
    
	public byte[] getBytes(){
		StringBuffer sb = new StringBuffer();
		sb.append(this.getMsgLength());
		sb.append(this.getCommand());
		sb.append(this.getSeqLength());
		sb.append(this.getResult());
		sb.append(this.getStu_id());
		System.out.println("ReceiveStuInfoResp:"+sb.toString());
		try {
			return sb.toString().getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
