package com.neusoft.clw.infomanage.ridingplan.domain;

 /**
  * 车辆司乘列表
  * @author yangliang
  *
  */
public class VehsichenInfo {

	private String steward_id;
	private String vehicle_vin;
	private String trip_id;
	
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getSteward_id() {
		return steward_id;
	}
	public void setSteward_id(String steward_id) {
		this.steward_id = steward_id;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
}