package com.neusoft.mobile.bean;

import com.neusoft.clw.constant.Constant;

public class MessageBean {
	private String user_id;
	private String client_id;
	private long msg_id;
	private long msg_index;
	private String grant_type;
	private String version_id;
	private long msg_type;
	//1:新消息 2：推送过的消息
	private String type;
	private String p_s;
	
	public String getP_s() {
		return p_s;
	}
	public void setP_s(String pS) {
		p_s = pS;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getMsg_type() {
		return msg_type;
	}
	public void setMsg_type(long msgType) {
		msg_type = msgType;
	}
	public String getGrant_type() {
		return grant_type;
	}
	public void setGrant_type(String grantType) {
		grant_type = grantType;
	}
	public String getVersion_id() {
		return version_id;
	}
	public void setVersion_id(String versionId) {
		version_id = versionId;
	}
	
	public Long getMsg_index() {
		return msg_index;
	}
	public void setMsg_index(Long msgIndex) {
		msg_index = msgIndex;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String clientId) {
		client_id = clientId;
	}
	public Long getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(Long msgId) {
		msg_id = msgId;
	}
	
	public String setMessage(){
		StringBuffer sb = new StringBuffer();
		sb.append("\"version_id\":\""+Constant.MOBILE_CLIENT_VERSION+"\",");
		sb.append("\"grant_type\":\"2\",");
		sb.append("\"user_id\":\""+this.getUser_id()+"\",");
		sb.append("\"client_id\":\""+this.getClient_id()+"\",");
		sb.append("\"msg_id\":\""+this.getMsg_id()+"\"");
		sb.append("\"p_s\":\""+this.getP_s()+"\"");
		return sb.toString();
	}
}
