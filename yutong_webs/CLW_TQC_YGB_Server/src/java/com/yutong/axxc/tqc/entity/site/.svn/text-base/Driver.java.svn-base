package com.yutong.axxc.tqc.entity.site;

import java.io.Serializable;

import oracle.sql.BLOB;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Driver implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6714602379432520235L;
	
	@JsonIgnore
	private String driver_id;
	
	@JsonProperty("driver_tel")
	private String driver_tel;
	
	@JsonProperty("driver_name")
	private String driver_name;
	
	@JsonProperty("vehicle_vin")
	private String vehicle_vin;
	
	@JsonProperty("line_id")
	private String line_id;

	@JsonProperty("url")
	private String url;

	@JsonProperty("emp_code")
	private String emp_code;
	
	@JsonIgnore
    // 相片
    private byte[] photoContent = null;
	
	

	public byte[] getPhotoContent() {
		return photoContent;
	}

	public void setPhotoContent(byte[] photoContent) {
		this.photoContent = photoContent;
	}

	public String getEmp_code() {
		return emp_code;
	}

	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public String getDriver_tel() {
		return driver_tel;
	}

	public void setDriver_tel(String driver_tel) {
		this.driver_tel = driver_tel;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

}
