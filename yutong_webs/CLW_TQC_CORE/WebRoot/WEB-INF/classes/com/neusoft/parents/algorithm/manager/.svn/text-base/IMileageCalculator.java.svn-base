/**
 * @author liuja
 * @createdate 2013年8月30日 上午8:14:22
 * @description 
 */
package com.neusoft.parents.algorithm.manager;

import java.util.Date;

import com.neusoft.parents.algorithm.domain.VehicleStation;

/**
 * @author liuja
 *
 */
public interface IMileageCalculator
{
	int getMileage(String vin, Date terminalTime, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude); //校车方法
	int getMileage(String vin, String route_id,String site_id, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude);//通勤车被pass方法
	int getMileage(String route_id,String site_id,  double longitude, double latitude, int taget_value,int threshold_value);//通勤车新方法
}
