/**
 * @author haoxy
 * @createdate 2013年8月28日 上午9:59:58
 * @description 
 */
package com.yutong.clw.beans.parents;

/**
 * @author haoxy
 *
 */
public class Vehicle {
	private String vin;
	private String ln;
	private String enterpriseid;
	private String organizationid;
	
	public String getVin()
	{
		return this.vin;
	}

	public void setVin(String vin)
	{
		this.vin = vin;
	}
	
	public String getLn()
	{
		return this.ln;
	}

	public void setLn(String ln)
	{
		this.ln = ln;
	}
	
	public String getEnterpriseId()
	{
		return this.enterpriseid;
	}

	public void setEnterpriseId(String enterpriseid)
	{
		this.enterpriseid = enterpriseid;
	}
	
	public String getOrganizationid()
	{
		return this.organizationid;
	}

	public void setOrganizationId(String organizationid)
	{
		this.organizationid = organizationid;
	}
}
