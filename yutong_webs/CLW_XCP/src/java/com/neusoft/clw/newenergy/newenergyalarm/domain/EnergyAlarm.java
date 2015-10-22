package com.neusoft.clw.newenergy.newenergyalarm.domain;
/**
 * 
 * @author wxliq
 * @see
 * 新能源故障基础类
 */
public class EnergyAlarm {
	/* ID主键 */
	private String alarmId;
	/* 车辆VIN */
	private String vehicleVin;
	/* 时间 */
	private String terminalTime;
	/* 经度 */
	private String longitude;
	/* 纬度 */
	private String latitude;
	/* 当前速度 */
	private String speed;
	/* 故障地址 */
	private String alarmAddress;
	/* 故障等级 */
	private String alarmLevel;
	/* 故障码 */
	private String alarmCode;
	/* 处理结果 */
	private String record;
	
	/* 车牌号 */
	private String vehicleln;
	
	/* 线路名称 */
	private String routeName;
	/* 告警地址位置 */
	private String address;
	
	private String alarmMsg;
	
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public String getVehicleVin() {
		return vehicleVin;
	}
	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}
	public String getTerminalTime() {
		return terminalTime;
	}
	public void setTerminalTime(String terminalTime) {
		this.terminalTime = terminalTime;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getAlarmAddress() {
		return alarmAddress;
	}
	public void setAlarmAddress(String alarmAddress) {
		this.alarmAddress = alarmAddress;
	}
	public String getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(String alarmLevel) {
		this.alarmLevel = alarmLevel;
	}
	public String getAlarmCode() {
		return alarmCode;
	}
	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getVehicleln() {
		return vehicleln;
	}
	public void setVehicleln(String vehicleln) {
		this.vehicleln = vehicleln;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAlarmMsg() {
		return alarmMsg;
	}
	public void setAlarmMsg(String alarmMsg) {
		this.alarmMsg = alarmMsg;
	}
	
	
	
}
