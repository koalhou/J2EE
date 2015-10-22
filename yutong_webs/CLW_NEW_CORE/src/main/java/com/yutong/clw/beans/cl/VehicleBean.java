package com.yutong.clw.beans.cl;

public class VehicleBean {
	public String vehicle_vin;
	public String vehicle_ln;
	public String tyre_r;
	public String rear_axle_rate;
	public String valid_flag;
	public String modify_time;
	public String standard_rotate;	
	public String enterprise_id;
		
	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = enterpriseId;
	}

	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicleLn) {
		vehicle_ln = vehicleLn;
	}

	public String getStandard_rotate() {
		return standard_rotate;
	}

	public void setStandard_rotate(String standard_rotate) {
		this.standard_rotate = standard_rotate;
	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicleVin) {
		vehicle_vin = vehicleVin;
	}

	public String getTyre_r() {
		return tyre_r;
	}

	public void setTyre_r(String tyre_r) {
		this.tyre_r = tyre_r;
	}

	public String getRear_axle_rate() {
		return rear_axle_rate;
	}

	public void setRear_axle_rate(String rearAxleRate) {
		rear_axle_rate = rearAxleRate;
	}

	public String getValid_flag() {
		return valid_flag;
	}

	public void setValid_flag(String validFlag) {
		valid_flag = validFlag;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modifyTime) {
		modify_time = modifyTime;
	}
}
