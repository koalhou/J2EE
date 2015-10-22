package com.neusoft.clw.infomationExport;

public class Trip{
    private String trip_id;
    private String route_id;
    private String type;
    private String crc;
    private String start_time;
    private String end_time;
    private String sitecount;
    private String drivercount;
    private String sichencount;
    private String studentcount;
    private String vehicle_vin;
    private String operateor;
    private String operate_time;
    private String route_name;
    
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCrc() {
		return crc;
	}
	public void setCrc(String crc) {
		this.crc = crc;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getSitecount() {
		return sitecount;
	}
	public void setSitecount(String sitecount) {
		this.sitecount = sitecount;
	}
	public String getDrivercount() {
		return drivercount;
	}
	public void setDrivercount(String drivercount) {
		this.drivercount = drivercount;
	}
	public String getSichencount() {
		return sichencount;
	}
	public void setSichencount(String sichencount) {
		this.sichencount = sichencount;
	}
	public String getStudentcount() {
		return studentcount;
	}
	public void setStudentcount(String studentcount) {
		this.studentcount = studentcount;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
	public String getOperateor() {
		return operateor;
	}
	public void setOperateor(String operateor) {
		this.operateor = operateor;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
}