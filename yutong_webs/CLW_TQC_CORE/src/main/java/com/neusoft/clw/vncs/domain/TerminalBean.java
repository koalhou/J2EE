package com.neusoft.clw.vncs.domain;

public class TerminalBean {
	//终端硬件编码
	private String terminal_id;
	//车辆VIN号
	private String vehicle_vin;
	//SIM卡号
	private String sim_card_number;
	//终端是否审核标记
	private String valid_flag;
	//更新时间
	private String update_time;
	
	private String speeding;
	
	private String fire_up_state;
	
	public String getSpeeding() {
		return speeding;
	}
	public void setSpeeding(String speeding) {
		this.speeding = speeding;
	}
	public String getFire_up_state() {
		return fire_up_state;
	}
	public void setFire_up_state(String fireUpState) {
		fire_up_state = fireUpState;
	}
	public String getValid_flag() {
		return valid_flag;
	}
	public void setValid_flag(String validFlag) {
		valid_flag = validFlag;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String updateTime) {
		update_time = updateTime;
	}
	public String getTerminal_id() {
		return terminal_id;
	}
	public void setTerminal_id(String terminalId) {
		terminal_id = terminalId;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicleVin) {
		vehicle_vin = vehicleVin;
	}
	public String getSim_card_number() {
		return sim_card_number;
	}
	public void setSim_card_number(String simCardNumber) {
		sim_card_number = simCardNumber;
	}
}
