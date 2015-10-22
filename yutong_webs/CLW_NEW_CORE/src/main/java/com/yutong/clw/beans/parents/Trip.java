/**
 * @author haoxy
 * @createdate 2013年9月3日 下午2:05:43
 * @description 
 */
package com.yutong.clw.beans.parents;

/**
 * @author haoxy
 *
 */
public class Trip
{
	private int tripId;
	private int type;
	private String vin;
	
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
}
