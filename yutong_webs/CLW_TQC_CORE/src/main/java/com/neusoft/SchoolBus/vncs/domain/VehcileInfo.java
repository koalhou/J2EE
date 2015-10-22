package com.neusoft.SchoolBus.vncs.domain;




public class VehcileInfo{
	private String organization_id;
	private String vin;
	private String enterprise_id;
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organizationId) {
		organization_id = organizationId;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterpriseId) {
		enterprise_id = enterpriseId;
	}
	
	
}