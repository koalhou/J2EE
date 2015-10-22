/**
 * @author haoxy
 * @createdate 2013年9月5日 上午11:33:38
 * @description 
 */
package com.yutong.axxc.tqc.service.algorithm;

import java.util.Date;

/**
 * @author haoxy
 *
 */
public interface ITimeCalculator
{
	int getTimespanInMinute(String vin, Date terminalTime, double longitude1, double latitude1, double longitude2, double latitude2);
}
