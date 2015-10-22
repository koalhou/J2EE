package com.yutong.clw.beans.parents;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * @author 郝昕悦 20130827
 * 站点
 */
public class Station  implements Comparable<Station> {
	private double longitude = 0.0;
	private double latitude = 0.0;
	private int studentCount = 0;	
	private Date terminalTime = null;
	private String terminalTimeStr = null;
	private int type = 0; //0:normal,1:school
	private int direction = 0; //0:up,1:down
	private List<Integer> studentIdList = new ArrayList<Integer>();
	private int siteId = 0;
	private int tripId = 0;
	
	public int getTripId()
	{
		return tripId;
	}

	public void setTripId(int tripId)
	{
		this.tripId = tripId;
	}

	public List<Integer> getStudentIdList()
	{
		return studentIdList;
	}

	public int getSiteId()
	{
		return siteId;
	}

	public void setSiteId(int siteId)
	{
		this.siteId = siteId;
	}

	public void setStudentIdList(List<Integer> studentIdList)
	{
		this.studentIdList = studentIdList;
	}
	
	public String getTerminalTimeStr()
	{
		return terminalTimeStr;
	}
	
	public void setTerminalTimeStr(String terminalTimeStr)
	{
		this.terminalTimeStr = terminalTimeStr;
	}
	
	public double getLongitude()
	{
		return longitude;
	}
	
	public void setLongitude(double longitude)
	{
		this.longitude = longitude;
	}
	
	public double getLatitude()
	{
		return latitude;
	}
	
	public void setLatitude(double latitude)
	{
		this.latitude = latitude;
	}
	
	public int getStudentCount()
	{
		return studentCount;
	}
	
	public void setStudentCount(int studentCount)
	{
		this.studentCount = studentCount;
	}
	
	public Date getTerminalTime()
	{
		return terminalTime;
	}
	
	public void setTerminalTime(Date terminalTime)
	{
		this.terminalTime = terminalTime;
	}
	
	public int getType()
	{
		return type;
	}
	
	public void setType(int type)
	{
		this.type = type;
	}
	
	public int getDirection()
	{
		return direction;
	}
	
	public void setDirection(int direction)
	{
		this.direction = direction;
	}

	public int compareTo(Station arg0)
	{
		return this.terminalTime.compareTo(arg0.getTerminalTime());
	}
}
