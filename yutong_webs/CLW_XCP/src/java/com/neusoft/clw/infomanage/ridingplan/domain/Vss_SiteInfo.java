package com.neusoft.clw.infomanage.ridingplan.domain;

/**
 * 乘车规划计划时间专用bean
 * @author yangliang
 *
 */
public class Vss_SiteInfo {
	private String vehicle_vin;
	private String route_id;
	private String site_id;
	private String plan_out_time;
	private String plan_in_time;
	private String trip_id;
	
	public String getTrip_id() {
		return trip_id;
	}
	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}
	public String getPlan_out_time() {
		return plan_out_time;
	}
	public void setPlan_out_time(String plan_out_time) {
		this.plan_out_time = plan_out_time;
	}
	public String getPlan_in_time() {
		return plan_in_time;
	}
	public void setPlan_in_time(String plan_in_time) {
		this.plan_in_time = plan_in_time;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getSite_id() {
		return site_id;
	}
	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}
}