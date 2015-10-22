package com.neusoft.clw.vncs.domain;

public class MsgCfgBean {
	
	public String vehicle_vin;
	public String alarm_id;
	public String state;
	public String add_info;
	public String sendtime;
	
	public String getSendtime() {
		return sendtime;
	}
	public void setSendtime(String sendtime) {
		this.sendtime = sendtime;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicleVin) {
		vehicle_vin = vehicleVin;
	}
	public String getAlarm_id() {
		return alarm_id;
	}
	public void setAlarm_id(String alarmId) {
		alarm_id = alarmId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAdd_info() {
		return add_info;
	}
	public void setAdd_info(String addInfo) {
		add_info = addInfo;
	}
	
}
