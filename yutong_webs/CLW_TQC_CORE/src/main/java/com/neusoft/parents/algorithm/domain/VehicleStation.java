package com.neusoft.parents.algorithm.domain;

import java.util.Date;
import java.util.List;

public class VehicleStation {
	private String vin;
	private List<Station> stationsUp = null;
	private List<Station> stationsDown = null;
	private Station school = null;
	private Date bestMatchDate = null; 
	private String enterpriseid = null;
	private String organizationid = null;
	
	public String getVin()
	{
		return this.vin;
	}
	
	public void setVin(String vin)
	{
		this.vin = vin;
	}
	
	public List<Station> getStationsUp()
	{
		return this.stationsUp;
	}
	
	public List<Station> setStationsUp(List<Station> stations)
	{
		return this.stationsUp = stations;
	}
	
	public List<Station> getStationsDown()
	{
		return this.stationsDown;
	}
	
	public List<Station> setStationsDown(List<Station> stations)
	{
		return this.stationsDown = stations;
	}
	
	public Station getSchool()
	{
		return this.school;
	}
	
	public Station setSchool(Station station)
	{
		return this.school = station;
	}
	
	public Date getBestMatchDate()
	{
		return this.bestMatchDate;
	}
	
	public void setBestMatchDate(Date bestMatchDate)
	{
		this.bestMatchDate = bestMatchDate;
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