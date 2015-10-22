package com.neusoft.clw.info.bean;

import java.io.Serializable;

public class MobileInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2382149730696585520L;
	private String user_id;
	private String client_id;
	private long user_msg_etag;
	private String enterprise_id;
	
	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = enterpriseId;
	}
	public long getUser_msg_etag() {
		return user_msg_etag;
	}
	public void setUser_msg_etag(long userMsgEtag) {
		user_msg_etag = userMsgEtag;
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
	
}
