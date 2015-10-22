/**
 * @author haoxy
 * @createdate 2013年8月28日 下午5:35:38
 * @description 
 */
package com.neusoft.parents.algorithm.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author haoxy
 *
 */
public class StationCountPair {
	private Station station;
	private int count;
	
	private double total_station_longitude = 0.0;
	private double total_station_latitude = 0.0;
	private long total_station_terminal_time = 0;
	private int total_station_stu_num = 0;
	private List<Date> timeList = new ArrayList<Date>();
	private List<Long> studentCountList = new ArrayList<Long>();
	
	public List<Long> getStudentCountList()
	{
		return studentCountList;
	}

	public void setStudentCountList(List<Long> studentCountList)
	{
		this.studentCountList = studentCountList;
	}

	public double getAvgLongitude()
	{
		return total_station_longitude / count;
	}
	
	public List<Date> getTimeList()
	{
		return timeList;
	}

	public void setTimeList(List<Date> timeList)
	{
		this.timeList = timeList;
	}

	public double getAvgLatitude()
	{
		return total_station_latitude / count;
	}
	
	public Date getAvgTime()
	{
		return new Date(total_station_terminal_time / count);
	}
	
	public int getAvgStudentCount()
	{
		return total_station_stu_num / count;
	}
	
	public double get_total_station_longitude()
	{
		return this.total_station_longitude;
	}
	
	public void set_total_station_longitude(double total_station_longitude)
	{
		this.total_station_longitude = total_station_longitude;
	}
	
	public double get_total_station_latitude()
	{
		return this.total_station_latitude;
	}
	
	public void set_total_station_latitude(double total_station_latitude)
	{
		this.total_station_latitude = total_station_latitude;
	}
	
	public long get_total_station_terminal_time()
	{
		return this.total_station_terminal_time;
	}
	
	public void set_total_station_terminal_time(long total_station_terminal_time)
	{
		this.total_station_terminal_time = total_station_terminal_time;
	}
	
	public int get_total_station_stu_num()
	{
		return this.total_station_stu_num;
	}
	
	public void set_total_station_stu_num(int total_station_stu_num)
	{
		this.total_station_stu_num = total_station_stu_num;
	}
	
	public Station getStation()
	{
		return this.station;
	}
	
	public void setStation(Station station)
	{
		this.station = station;		
	}
	
	public int getCount()
	{
		return this.count;
	}
	
	public void setCount(int count)
	{
		this.count = count;		
	}
	

}
