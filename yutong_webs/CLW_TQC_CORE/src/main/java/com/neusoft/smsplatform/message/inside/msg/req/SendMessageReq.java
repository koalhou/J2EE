package com.neusoft.smsplatform.message.inside.msg.req;

import java.io.UnsupportedEncodingException;

import com.neusoft.smsplatform.message.inside.msg.MesssageInsideMsg;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;

public class SendMessageReq extends MesssageInsideMsg {

	public static final String COMMAND = "08";

	public static final int PHONE1SIZE = 11;

	public static final int PHONE2SIZE = 11;

	public static final int PHONE3SIZE = 11;

	public static final int HEADERSIZE = 14;
	
	public static final int STUIDSIZE = 10;

	private String phone1;

	private String phone2;

	private String phone3;

	private String message;
	//刷卡流水id
	private String recordid;
	//事件类型
	private String event_type;
	//学生id
	private String stu_id;
	
	private String vehicle_vin;
	
	
	
	
	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicleVin) {
		vehicle_vin = vehicleVin;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stuId) {
		this.stu_id = stuId;
	}
	
	public String formatStuId(String stuId){
		return (stuId == null || stuId.equals("")) ? SmsCommonMsgUtils
				.format("", STUIDSIZE) : SmsCommonMsgUtils
				.format(stuId, STUIDSIZE);
	}
	
	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String eventType) {
		event_type = eventType;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = (phone1 == null || phone1.equals("")) ? SmsCommonMsgUtils
				.format("", PHONE1SIZE) : SmsCommonMsgUtils
				.format(phone1, PHONE1SIZE);
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = (phone2 == null || phone2.equals("")) ? SmsCommonMsgUtils
				.format("", PHONE2SIZE) : SmsCommonMsgUtils
				.format(phone2, PHONE2SIZE);
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = (phone3 == null || phone3.equals("")) ? SmsCommonMsgUtils
				.format("", PHONE3SIZE) : SmsCommonMsgUtils
				.format(phone3, PHONE3SIZE);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public byte[] getBytes() {
		try {
			return formatToString().getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String formatToString() throws UnsupportedEncodingException{
		int messagelen = this.getMessage().getBytes("GBK").length;
		int len = HEADERSIZE+PHONE1SIZE+PHONE2SIZE+PHONE3SIZE+STUIDSIZE+messagelen;
		this.setMsgLength(String.valueOf(len));
		StringBuffer sb = new StringBuffer();
		sb.append(this.getMsgLength());
		sb.append(this.getCommand());
		sb.append(this.getSeqLength());
		sb.append(this.getPhone1());
		sb.append(this.getPhone2());
		sb.append(this.getPhone3());
		sb.append(formatStuId(this.getStu_id()));
		sb.append(this.getMessage());
//		System.out.println("SendMessage:"+sb.toString()+",messagelen:"+messagelen + ",totallen:"+len);
		return sb.toString();
	}
	
	public static void main(String[] args) {
		SendMessageReq req = new SendMessageReq();
		req.setMessage("安芯温馨提示：您的孩子吴豆涛于14:04打卡下车。发送成功！");
		try {
			System.out.println(req.formatToString());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
