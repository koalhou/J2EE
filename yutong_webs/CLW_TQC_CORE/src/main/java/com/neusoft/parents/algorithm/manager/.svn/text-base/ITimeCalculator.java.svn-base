/**
 * @author haoxy
 * @createdate 2013年9月5日 上午11:33:38
 * @description 
 */
package com.neusoft.parents.algorithm.manager;

import java.util.Date;

import com.neusoft.parents.bean.VehicleReal;

/**
 * @author haoxy
 *
 */
public interface ITimeCalculator
{
	int getTimespanInMinute(String vin, Date terminalTime, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude);
	int getTimespanInMinute(VehicleReal vr,String vin,String route_id, Date terminalTime, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude);
	int getTimespanInSec(String route_id,String site_id,  double longitude, double latitude, int taget_value,int threshold_value);//通勤车新方法 按时间
}
