package com.neusoft.smsplatform.message.inside.msg.resp;

import java.io.UnsupportedEncodingException;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;

public class ChangePhoneResp extends MesssageInsideMsg {

    public static final String COMMAND = "07";
    public static final int total = 15;
    private String result;

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
		System.out.println("ChangePhoneResp:"+sb.toString());
		try {
			return sb.toString().getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}
