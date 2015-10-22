/**
 * @author haoxy
 * @createdate 2013年9月16日 上午10:13:17
 * @description 
 */
package com.yutong.axxc.parents.service;

import static org.junit.Assert.*;

import org.junit.Test;

import com.yutong.axxc.parents.BaseSpringTest;
import com.yutong.axxc.tqc.entity.distance.DistanceMsg;
import com.yutong.axxc.tqc.entity.vehicle.VehicleReal;
import com.yutong.axxc.tqc.service.DistanceService;
import com.yutong.axxc.tqc.service.EtagService;

/**
 * @author haoxy
 *
 */
public class DistanceTest extends BaseSpringTest
{

	@Test
	public void test()
	{
		DistanceService distanceService=(DistanceService)ac.getBean("distanceService");
		
		DistanceMsg msg = distanceService.getDistance("2183");
		
		String state = distanceService.getStudentState("2184");
		
//		VehicleReal vr = distanceService.getVehicleRealtimeInfo("LZYTCTC20C1011716");
//		assertNotNull(vr);
	}

}
