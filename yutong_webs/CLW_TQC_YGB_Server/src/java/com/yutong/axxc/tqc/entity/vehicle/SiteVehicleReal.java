package com.yutong.axxc.tqc.entity.vehicle;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SiteVehicleReal implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3746322639866727704L;

	@JsonProperty("line_range")
	private String line_range;
	
	@JsonProperty("status_range")
	private String status_range;
	
	@JsonProperty("station_id")
	private String site_id;
	
	@JsonProperty("belong_area_id")
	private String belong_area;
	
	@JsonProperty("name")
	private String site_name;
	
	@JsonProperty("alias")
	private String alias;
	
	@JsonProperty("gps_lon")
	private String gps_lon;
	
	@JsonProperty("gps_lat")
	private String gps_lat;
	
	@JsonProperty("status")
	private String status;
	
	@JsonProperty("plan_arrive_time")
	private String plan_arrive_time;
	
	@JsonProperty("favorates")
	private String favorates;
	
	@JsonProperty("vehicles")
	private List<VehicleReal> vehicles;

	public String getLine_range() {
		return line_range;
	}

	public void setLine_range(String line_range) {
		this.line_range = line_range;
	}

	public String getStatus_range() {
		return status_range;
	}

	public void setStatus_range(String status_range) {
		this.status_range = status_range;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getBelong_area() {
		return belong_area;
	}

	public void setBelong_area(String belong_area) {
		this.belong_area = belong_area;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getGps_lon() {
		return gps_lon;
	}

	public void setGps_lon(String gps_lon) {
		this.gps_lon = gps_lon;
	}

	public String getGps_lat() {
		return gps_lat;
	}

	public void setGps_lat(String gps_lat) {
		this.gps_lat = gps_lat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlan_arrive_time() {
		return plan_arrive_time;
	}

	public void setPlan_arrive_time(String plan_arrive_time) {
		this.plan_arrive_time = plan_arrive_time;
	}

	public String getFavorates() {
		return favorates;
	}

	public void setFavorates(String favorates) {
		this.favorates = favorates;
	}

	public List<VehicleReal> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<VehicleReal> vehicles) {
		this.vehicles = vehicles;
	}
	
}
