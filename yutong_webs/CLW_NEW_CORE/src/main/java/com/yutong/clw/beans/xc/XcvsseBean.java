package com.yutong.clw.beans.xc;

public class XcvsseBean {
	
	private String student_id;//学生ID
	private String site_id;//站点ID
	private String route_id;//站点ID
	private String vehicle_vin;//车辆VIN
	private String vss_state;//上下车状态
	private String site_updown;//上下行状态
	private String trip_id;//行程编号
	
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String tripId) {
		trip_id = tripId;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String studentId) {
		student_id = studentId;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String siteId) {
		site_id = siteId;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicleVin) {
		vehicle_vin = vehicleVin;
	}
	public String getVss_state() {
		return vss_state;
	}
	public void setVss_state(String vssState) {
		vss_state = vssState;
	}
	public String getSite_updown() {
		return site_updown;
	}
	public void setSite_updown(String siteUpdown) {
		site_updown = siteUpdown;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String routeId) {
		route_id = routeId;
	}

}
