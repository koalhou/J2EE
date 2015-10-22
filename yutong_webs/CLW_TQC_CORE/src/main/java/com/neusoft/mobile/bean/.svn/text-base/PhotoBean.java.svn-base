package com.neusoft.mobile.bean;

import java.io.Serializable;

import com.neusoft.clw.constant.Constant;

public class PhotoBean implements Serializable{

	private static final long serialVersionUID = -3951077241127493787L;
	private String user_id;
	private String client_id;
	private String photo_id;
	private String vehicle_vin;
	private String grant_type;
	private String version_id;
	private String p_s;
	
	
	public String getP_s() {
		return p_s;
	}
	public void setP_s(String pS) {
		p_s = pS;
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
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicleVin) {
		vehicle_vin = vehicleVin;
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
	public String getPhoto_id() {
		return photo_id;
	}
	public void setPhoto_id(String photoId) {
		photo_id = photoId;
	}
	
	public String setPhoto(){
		StringBuffer sb = new StringBuffer();
		sb.append("\"version_id\":\""+Constant.MOBILE_CLIENT_VERSION+"\",");
		sb.append("\"grant_type\":\"2\",");
		sb.append("\"user_id\":\""+this.getUser_id()+"\",");
		sb.append("\"client_id\":\""+this.getClient_id()+"\",");
		sb.append("\"photo_id\":\""+this.getPhoto_id()+"\"");
		sb.append("\"p_s\":\""+this.getP_s()+"\"");
		return sb.toString();
	}
}
