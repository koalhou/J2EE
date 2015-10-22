/**
 * @author haoxy
 * @createdate 2013年9月14日 下午4:33:14
 * @description 
 */
package com.yutong.axxc.parents.common;

/**
 * @author haoxy
 *
 */
public class DistanceConstant
{
	public static final String STUDENTSTATE_UNKOWN = "-1";
	public static final String STUDENTSTATE_BEFORESCHOOL = "0";
	public static final String STUDENTSTATE_ONBUSUP = "1";
	public static final String STUDENTSTATE_INSCHOOL = "2";
	public static final String STUDENTSTATE_ONBUSDOWN = "3";
	public static final String STUDENTSTATE_AFTERSCHOOL = "4";
	public static final String STUDENTSTATE_ONBUSUPWAIT = "5";
	public static final String STUDENTSTATE_AFTERSCHOOLWAIT = "6";

	public static final String REMINDTYPE_TIME = "0";
	public static final String REMINDTYPE_MILEAGE = "1";
	public static final String REMINDTYPE_SITE = "2";
	// 上行下行
	public static final int UPORDOWN_UP = 0; 
	public static final int UPORDOWN_DOWN = 1;
	
	public static final String VEHICLEREAL = "app_vehiclereal";
	public static final String STUSTATEINFO = "app_stustateinfo";
	
	public static final String STATIONREMIND_PREFIX = "app_station_remind_";
	
	public static final String STUDENTSTATION_PREFIX = "app_student_station_";
}
