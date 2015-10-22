/**
 * @author haoxy
 * @createdate 2013年9月5日 上午9:14:33
 * @description 
 */
package com.yutong.clw.beans.parents;

/**
 * @author haoxy
 *
 */
public class StationGenAlgorithmThreshold
{
	private int stationGenMinSampleCount;
	private int stationGenRecordCount;
	private int stationGenMinMatchStationCountDivisor;
	private int stationGenScopeMeter;
	private int upOrDownHour;
	private int stationGenScopeMinute; 
	private int tripOrbitGetLastCheckRecordSampleDateTryTimes;
	private int tripOrbitCheckCardTimeScopeInSecond;
	private int mileageGetLastCheckRecordSampleDateTryTimes;
	private int mileageCacheTimeoutInMinute;
	private int mileageMinDistanceInMeter;
	private int mileageTerminalTimeScopeInMinute;
	private int mileageTerminalTimeScopeCheck;
	private int mileageDirectionScope;

	public int getTripOrbitCheckCardTimeScopeInSecond()
	{
		return tripOrbitCheckCardTimeScopeInSecond;
	}
	public void setTripOrbitCheckCardTimeScopeInSecond(int tripOrbitCheckCardTimeScopeInSecond)
	{
		this.tripOrbitCheckCardTimeScopeInSecond = tripOrbitCheckCardTimeScopeInSecond;
	}
	public int getMileageDirectionScope()
	{
		return mileageDirectionScope;
	}
	public void setMileageDirectionScope(int mileageDirectionScope)
	{
		this.mileageDirectionScope = mileageDirectionScope;
	}
	public int getMileageCacheTimeoutInMinute()
	{
		return mileageCacheTimeoutInMinute;
	}
	public void setMileageCacheTimeoutInMinute(int mileageCacheTimeoutInMinute)
	{
		this.mileageCacheTimeoutInMinute = mileageCacheTimeoutInMinute;
	}
	public int getMileageMinDistanceInMeter()
	{
		return mileageMinDistanceInMeter;
	}
	public void setMileageMinDistanceInMeter(int mileageMinDistanceInMeter)
	{
		this.mileageMinDistanceInMeter = mileageMinDistanceInMeter;
	}
	public int getMileageTerminalTimeScopeInMinute()
	{
		return mileageTerminalTimeScopeInMinute;
	}
	public void setMileageTerminalTimeScopeInMinute(int mileageTerminalTimeScopeInMinute)
	{
		this.mileageTerminalTimeScopeInMinute = mileageTerminalTimeScopeInMinute;
	}
	public int getMileageTerminalTimeScopeCheck()
	{
		return mileageTerminalTimeScopeCheck;
	}
	public void setMileageTerminalTimeScopeCheck(int mileageTerminalTimeScopeCheck)
	{
		this.mileageTerminalTimeScopeCheck = mileageTerminalTimeScopeCheck;
	}
	
	public int getMileageGetLastCheckRecordSampleDateTryTimes()
	{
		return mileageGetLastCheckRecordSampleDateTryTimes;
	}
	public void setMileageGetLastCheckRecordSampleDateTryTimes(int mileageGetLastCheckRecordSampleDateTryTimes)
	{
		this.mileageGetLastCheckRecordSampleDateTryTimes = mileageGetLastCheckRecordSampleDateTryTimes;
	}

	public int getUpOrDownHour()
	{
		return upOrDownHour;
	}
	public void setUpOrDownHour(int upOrDownHour)
	{
		this.upOrDownHour = upOrDownHour;
	}

	public int getTripOrbitGetLastCheckRecordSampleDateTryTimes()
	{
		return tripOrbitGetLastCheckRecordSampleDateTryTimes;
	}
	public void setTripOrbitGetLastCheckRecordSampleDateTryTimes(
			int tripOrbitGetLastCheckRecordSampleDateTryTimes)
	{
		this.tripOrbitGetLastCheckRecordSampleDateTryTimes = tripOrbitGetLastCheckRecordSampleDateTryTimes;
	}
	public int getStationGenMinSampleCount()
	{
		return stationGenMinSampleCount;
	}
	public void setStationGenMinSampleCount(int stationGenMinSampleCount)
	{
		this.stationGenMinSampleCount = stationGenMinSampleCount;
	}
	public int getStationGenRecordCount()
	{
		return stationGenRecordCount;
	}
	public void setStationGenRecordCount(int stationGenRecordCount)
	{
		this.stationGenRecordCount = stationGenRecordCount;
	}
	public int getStationGenMinMatchStationCountDivisor()
	{
		return stationGenMinMatchStationCountDivisor;
	}
	public void setStationGenMinMatchStationCountDivisor(int stationGenMinMatchStationCountDivisor)
	{
		this.stationGenMinMatchStationCountDivisor = stationGenMinMatchStationCountDivisor;
	}
	public int getStationGenScopeMeter()
	{
		return stationGenScopeMeter;
	}
	public void setStationGenScopeMeter(int stationGenScopeMeter)
	{
		this.stationGenScopeMeter = stationGenScopeMeter;
	}
	public int getStationGenScopeMinute()
	{
		return stationGenScopeMinute;
	}
	public void setStationGenScopeMinute(int stationGenScopeMinute)
	{
		this.stationGenScopeMinute = stationGenScopeMinute;
	}
	
}
