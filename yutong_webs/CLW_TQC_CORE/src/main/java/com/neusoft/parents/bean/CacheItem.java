package com.neusoft.parents.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CacheItem   implements Serializable
{

 
    /**
     * 
     */
    private static final long serialVersionUID = -893218531350595726L;

    private Date date;

    List<StuSiteNote> stuSiteNoteList;
    
    List<CacheVinTripList> cacheVinTripList;
    
    private String vehicle_vin;
    
    private String trip_id;

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public List<StuSiteNote> getStuSiteNoteList()
    {
        return stuSiteNoteList;
    }

    public void setStuSiteNoteList(List<StuSiteNote> stuSiteNoteList)
    {
        this.stuSiteNoteList = stuSiteNoteList;
    }

	public List<CacheVinTripList> getCacheVinTripList() {
		return cacheVinTripList;
	}

	public void setCacheVinTripList(List<CacheVinTripList> cacheVinTripList) {
		this.cacheVinTripList = cacheVinTripList;
	}

     
}
