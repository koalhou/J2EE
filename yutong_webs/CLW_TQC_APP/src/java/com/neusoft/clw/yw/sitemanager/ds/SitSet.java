package com.neusoft.clw.yw.sitemanager.ds;

/**
 * 加油站点相关车辆表
 * @author wxliq
 *
 */
public class SitSet {

	private String sit_id;
	
	private String vehicle_vin;
	
	private String vehicle_ln;
	
	private String enterprise_id;
	
	private String organization_id;

	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public String getSit_id() {
		return sit_id;
	}

	public void setSit_id(String sit_id) {
		this.sit_id = sit_id;
	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}

	public String getEnterprise_id() {
		return enterprise_id;
	}

	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}
	
	
	
}
