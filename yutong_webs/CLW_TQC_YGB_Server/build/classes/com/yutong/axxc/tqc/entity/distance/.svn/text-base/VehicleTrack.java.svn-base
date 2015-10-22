/**
 * @author haoxy
 * @createdate 2013年8月30日 上午7:46:32
 * @description 
 */
package com.yutong.axxc.tqc.entity.distance;

import java.util.Date;

/**
 * @author haoxy
 *
 */
public class VehicleTrack implements Comparable<VehicleTrack>
{
	private int tripId = 0;
	private int type = 0;
	private String vin;
	private double longitude = 0;
	private double latitude = 0;
	private Date terminalTime = null;
	private Date trackDate = null;
	private Date createDate = null;
	private double mileage = 0;
	private int isTripOrbit = 1; //0:行程外 1:行程内
	
	public int getIsTripOrbit()
	{
		return this.isTripOrbit;
	}
	
	public void setIsTripOrbit(int isTripOrbit)
	{
		this.isTripOrbit = isTripOrbit;
	} 
	
	public int getTripId()
	{
		return this.tripId;
	}
	
	public void setTripId(int tripId)
	{
		this.tripId = tripId;
	}
	
	public int getType()
	{
		return this.type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	} 
	
	public String getVin()
	{
		return this.vin;
	}
	
	public void setVin(String vin)
	{
		this.vin = vin;
	}
	
	public double getLongitude()
	{
		return this.longitude;
	}
	
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	
	public double getLatitude()
	{
		return this.latitude;
	}
	
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	
	public Date getTerminalTime()
	{
		return this.terminalTime;
	}
	
	public void setTerminalTime(Date terminalTime)
	{
		this.terminalTime = terminalTime;
	}
	
	public Date getTrackDate()
	{
		return this.trackDate;
	}
	
	public void setTrackDate(Date trackDate)
	{
		this.trackDate = trackDate;
	}
	
	public Date getCreateDate()
	{
		return this.createDate;
	}
	
	public void setCreateDate(Date createDate)
	{
		this.createDate = createDate;
	}
	
	public void setMileage(double mileage)
	{
		this.mileage = mileage;
	}
	
	public double getMileage()
	{
		return this.mileage;
	}

	public int compareTo(VehicleTrack o)
	{
		return this.terminalTime.compareTo(o.getTerminalTime());
	}
}
