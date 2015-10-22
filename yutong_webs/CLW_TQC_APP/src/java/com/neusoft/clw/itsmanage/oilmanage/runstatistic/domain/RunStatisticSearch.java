package com.neusoft.clw.itsmanage.oilmanage.runstatistic.domain;

public class RunStatisticSearch {
	
	private String beginTime;//查询开始时间
	private String endTime;//查询结束时间
	private String dateFlag;//查询时间标志
	private String vins;
	private String vehicle_vin;
	private String yesterday;
	private String user_organization_id;
	
	public String getYesterday() {
		return yesterday;
	}
	public void setYesterday(String yesterday) {
		this.yesterday = yesterday;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
	public String getVins() {
		return vins;
	}
	public void setVins(String vins) {
		this.vins = vins;
	}
	public String getDateFlag() {
		return dateFlag;
	}
	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUser_organization_id() {
		return user_organization_id;
	}
	public void setUser_organization_id(String user_organization_id) {
		this.user_organization_id = user_organization_id;
	}
	
}
