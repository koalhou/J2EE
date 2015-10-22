/**
 * @author haoxy
 * @createdate 2013年9月4日 上午11:25:19
 * @description 
 */
package com.neusoft.parents.algorithm.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.parents.algorithm.dao.*;
import com.neusoft.parents.algorithm.domain.*;
import com.neusoft.parents.algorithm.dao.impl.AlgorithmDAO;

/**
 * @author haoxy
 *
 */
public class SimpleStationStudentGenerator implements IStationStudentGenerator
{
	private int scopeMeter = 300;
	private int upOrDownHour = 13;
	private int studentCalculateDayScope = 10;
	private int studentCalculateMinAppearCount = 2;
	private Logger log = LoggerFactory.getLogger(SimpleStationStudentGenerator.class);
	private AlgorithmDAO algorithmDAO;

	public void setAlgorithmDAO(AlgorithmDAO algorithmDAO)
	{
		this.algorithmDAO = algorithmDAO;
	}
	
	public AlgorithmDAO getAlgorithmDAO()
	{
		return this.algorithmDAO;
	}
	
	public List<Station> generate(String vin)
	{
		List<Station> stationAll = new ArrayList<Station>();
		List<Trip> trips = algorithmDAO.getTripByVehicleVin(vin);
		
		if(trips!= null && trips.size() > 0)
		{
			for(Trip t : trips)
			{
				List<Station> stations = algorithmDAO.getStationByTripId(t.getTripId());
				
				if(stations != null && stations.size() > 0)
				{
					getStudents(vin, stations, t.getType());
					
					for(Station s : stations)
					{
						s.setTripId(t.getTripId());
						stationAll.add(s);
					}
				}
			}
		}
		
		return stationAll;
	}
	
	private void getStudents(String vin, List<Station> stations, int upOrDown)
	{
		List<Date> dates = algorithmDAO.getCheckRecordSampleDateByUpAndDown(vin, studentCalculateDayScope, upOrDown, upOrDownHour);
		
		Map<Station, StationStudentCount> stationStudentCountMap = new HashMap<Station, StationStudentCount>();
		
		for(Station s : stations)
		{
			StationStudentCount ssc = new StationStudentCount();
			ssc.setStation(s);
			stationStudentCountMap.put(s, ssc);
		}
		
		for(Date date : dates)
		{
			List<CheckCardRecord> checkCardRecords = algorithmDAO.getCheckCardRecordDetailByUpOrDown(vin, date, upOrDown, upOrDownHour);
			
			for(Station s : stations)
			{
				for(CheckCardRecord ccr : checkCardRecords)
				{
					double dis = AlgorithmUtil.getDistance(s.getLongitude(), s.getLatitude(), ccr.getLongitude(), ccr.getLatitude());
					
					if(dis <= scopeMeter)
					{
						String key = String.valueOf(ccr.getStudentId());
						boolean hasKey = stationStudentCountMap.get(s).getStudentCountMap().containsKey(key);
						
						if(hasKey)
						{
							Integer count = stationStudentCountMap.get(s).getStudentCountMap().get(key);
							int newCount = count.intValue() + 1;
							
							stationStudentCountMap.get(s).getStudentCountMap().remove(key);
							stationStudentCountMap.get(s).getStudentCountMap().put(key, new Integer(newCount));
						}
						else
						{
							Integer count = new Integer(0);
							stationStudentCountMap.get(s).getStudentCountMap().put(key, count);
						}
					}
				}
			}
		}
		
		//过滤次数少的学生.
		
		for(StationStudentCount ssc : stationStudentCountMap.values())
		{
			List<String> toBeDeletedKeys = new ArrayList<String>();

			for(String key : ssc.getStudentCountMap().keySet())
			{
				Integer count = ssc.getStudentCountMap().get(key);
				if(count.intValue() < studentCalculateMinAppearCount)
				{
					toBeDeletedKeys.add(key);
				}
			}
			
			if(toBeDeletedKeys.size() > 0)
			{
				for(String key : toBeDeletedKeys)
				{
					ssc.getStudentCountMap().remove(key);
				}
			}
		}
		//添加学生
		for(Station s : stations)
		{
			StationStudentCount ssc = stationStudentCountMap.get(s);
			
			if(ssc.getStudentCountMap().keySet() != null && ssc.getStudentCountMap().keySet().size() > 0)
			{
				for(String idStr : ssc.getStudentCountMap().keySet())
				{
					s.getStudentIdList().add(Integer.valueOf(idStr));
				}
			}
			
			Collections.sort(s.getStudentIdList());
		}
	}
}
