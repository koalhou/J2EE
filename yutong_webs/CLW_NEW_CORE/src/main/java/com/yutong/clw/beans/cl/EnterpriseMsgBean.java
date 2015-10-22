package com.yutong.clw.beans.cl;

public class EnterpriseMsgBean {
	private String enterprise_id;
	private String msg_num;
	private String send_num;
	private String valid_flag;
	private String modify_time;
	
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modifyTime) {
		modify_time = modifyTime;
	}
	public String getValid_flag() {
		return valid_flag;
	}
	public void setValid_flag(String validFlag) {
		valid_flag = validFlag;
	}
	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = enterpriseId;
	}
	public String getMsg_num() {
		return msg_num;
	}
	public void setMsg_num(String msgNum) {
		msg_num = msgNum;
	}
	public String getSend_num() {
		return send_num;
	}
	public void setSend_num(String sendNum) {
		send_num = sendNum;
	}
	
}
