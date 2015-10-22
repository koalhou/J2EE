package com.yutong.clw.beans.cl;

import java.io.Serializable;

public class RidingPlanBean  implements Serializable{
	private static final long serialVersionUID = -6576966210874268495L;
	private String vehicle_vin;
	private String student_id;
	private String stu_code;
	private String stu_card_id;
	private String trip_id;
	private String modify_time;
	
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
	public String getStudent_id() {
		return student_id;
	}
	public void setStudent_id(String student_id) {
		this.student_id = student_id;
	}
	public String getStu_code() {
		return stu_code;
	}
	public void setStu_code(String stu_code) {
		this.stu_code = stu_code;
	}
	public String getStu_card_id() {
		return stu_card_id;
	}
	public void setStu_card_id(String stu_card_id) {
		this.stu_card_id = stu_card_id;
	}
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	
	
}
