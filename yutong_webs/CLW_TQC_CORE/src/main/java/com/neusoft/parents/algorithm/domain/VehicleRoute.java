/**
 * @author haoxy
 * @createdate 2013年9月2日 下午5:39:20
 * @description 
 */
package com.neusoft.parents.algorithm.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author haoxy
 *
 */
public class VehicleRoute
{
	private Vehicle vehicle = null;
	
	private List<VehicleStation> vehicleStations = new ArrayList<VehicleStation>();
	
	public Vehicle getVehicle()
	{
		return this.vehicle;
	}
	
	public void setVehicle(Vehicle vehicle)
	{
		this.vehicle = vehicle;
	}
	
	public List<VehicleStation> getVehicleStations()
	{
		return this.vehicleStations;
	}
	
	public void setvVhicleStations(List<VehicleStation> vehicleStations)
	{
		this.vehicleStations = vehicleStations;
	}
}
