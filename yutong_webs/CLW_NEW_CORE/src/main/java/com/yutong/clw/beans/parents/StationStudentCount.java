/**
 * @author haoxy
 * @createdate 2013年9月4日 上午10:29:50
 * @description 
 */
package com.yutong.clw.beans.parents;

import java.util.HashMap;
import java.util.Map;

/**
 * @author haoxy
 *
 */
public class StationStudentCount
{
	private Map<String, Integer> studentCountMap = new HashMap<String, Integer>();
	
	private Station station = null;

	public Map<String, Integer> getStudentCountMap()
	{
		return studentCountMap;
	}

	public void setStudentCountMap(Map<String, Integer> studentCountMap)
	{
		this.studentCountMap = studentCountMap;
	}

	public Station getStation()
	{
		return station;
	}

	public void setStation(Station station)
	{
		this.station = station;
	}
}
