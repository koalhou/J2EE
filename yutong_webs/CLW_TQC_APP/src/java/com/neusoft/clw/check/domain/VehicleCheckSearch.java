package com.neusoft.clw.check.domain;

public class VehicleCheckSearch {
	
	//班车号
	private String vehicle_code;
	//开始日期
	private String beginTime;
	//结束日期
	private String endTime;
	//处理状态
	private String operate_state;
	//类型
	private String operate_type;
	//车辆vin
	private String vins;
	//组织id
	private String organization_id;
	//当前日期
	private String curr_date;
	
	private String sortName;
	private String sortOrder;
	
	
	public String getCurr_date() {
		return curr_date;
	}
	public void setCurr_date(String curr_date) {
		this.curr_date = curr_date;
	}
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getVins() {
		return vins;
	}
	public void setVins(String vins) {
		this.vins = vins;
	}
	public String getVehicle_code() {
		return vehicle_code;
	}
	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
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
	public String getOperate_state() {
		return operate_state;
	}
	public void setOperate_state(String operate_state) {
		this.operate_state = operate_state;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
	}
	
}
