/**
 * @author haoxy
 * @createdate 2013年9月3日 下午4:36:36
 * @description 
 */
package com.neusoft.parents.algorithm.domain;

import java.util.List;

/**
 * @author haoxy
 *
 */
public class VehicleTripOrbit
{
	private String vin;	
	private int tripId;	
	private List<VehicleTrack> vehicleTracks;
	
	public String getVin()
	{
		return this.vin;
	}
	
	public void setVin(String vin)
	{
		this.vin = vin;
	}	

	public int getTripId()
	{
		return this.tripId;
	}
	
	public void setTripId(int tripId)
	{
		this.tripId = tripId;
	}

	public List<VehicleTrack> getVehicleTracks()
	{
		return this.vehicleTracks;
	}
	
	public void setVehicleTracks(List<VehicleTrack> vehicleTracks)
	{
		this.vehicleTracks = vehicleTracks;
	}
}
