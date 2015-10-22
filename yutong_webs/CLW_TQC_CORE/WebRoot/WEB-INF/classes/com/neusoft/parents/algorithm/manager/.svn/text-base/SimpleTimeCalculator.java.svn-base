/**
 * @author haoxy
 * @createdate 2013年9月5日 上午11:34:48
 * @description 
 */
package com.neusoft.parents.algorithm.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.parents.algorithm.dao.impl.AlgorithmDAO;
import com.neusoft.parents.algorithm.domain.StationGenAlgorithmThreshold;
import com.neusoft.parents.algorithm.domain.VehicleTrack;
import com.neusoft.parents.algorithm.domain.VehicleTrackMapItem;
import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.parents.bean.VehicleReal;

/**
 * @author haoxy
 * 
 */
public class SimpleTimeCalculator implements ITimeCalculator
{
	private AlgorithmDAO algorithmDAO;
	private Logger log = LoggerFactory.getLogger(SimpleTimeCalculator.class);
	private Map<String, VehicleTrackMapItem> vehicleTracksCache = new HashMap<String, VehicleTrackMapItem>();

	public void setAlgorithmDAO(AlgorithmDAO algorithmDAO)
	{
		this.algorithmDAO = algorithmDAO;
	}

	public AlgorithmDAO getAlgorithmDAO()
	{
		return this.algorithmDAO;
	}

	public int getTimespanInMinute(String vin, Date terminalTime, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude)
	{
		int timespanInMinute = -1;

		try
		{
			StationGenAlgorithmThreshold threshold = algorithmDAO.getStationGenAlgorithmThreshold(vin);

			if (threshold != null)
			{
				Calendar cal = Calendar.getInstance();
				cal.setTime(terminalTime);
				int hours = cal.get(Calendar.HOUR_OF_DAY);
				int type = hours >= threshold.getUpOrDownHour() ? 1 : 0;

				List<VehicleTrack> vehicleTracks = getVehicleTrack(vin, type, threshold);

				if (vehicleTracks != null && vehicleTracks.size() > 0)
				{
					VehicleTrack fromVehicleTrack = getFromNearestVehicleTrack(fromLongitude, fromLatitude, direction, vehicleTracks, terminalTime, threshold);

					if (fromVehicleTrack != null)
					{
						VehicleTrack toVehicleTrack = getToNearestStationVehicleTrack(toLongitude, toLatitude, vehicleTracks, fromVehicleTrack.getTerminalTime(), threshold);

						if (toVehicleTrack != null)
						{
							timespanInMinute = Math.abs(AlgorithmUtil.getTimespanMinutes(toVehicleTrack.getTerminalTime(), fromVehicleTrack.getTerminalTime()));
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			log.error("SimpleTimeCalculator_getTimespanInMinute error", e.getStackTrace(), e.getMessage());
		}

		return timespanInMinute;
	}
	
	
	
	
	public int getTimespanInSec(String route_id,String site_id,  double longitude, double latitude, int taget_value,int threshold_value){
		int timespanInMinute = -1;
	
		
		List<NoticeBean> vtList=algorithmDAO.getLineByTagetTime(route_id,site_id,taget_value);
		
		//判断车辆是不是在查询到的轨迹内
		for(NoticeBean be:vtList){
			double disTemp = AlgorithmUtil.getDistance(longitude, latitude, Double.parseDouble(be.getLongitude()), Double.parseDouble(be.getLatitude()));
			if(disTemp <= threshold_value){
				timespanInMinute = 1;
				break;
			}
		}
		
		
		return  timespanInMinute;
	}
	/**
	 * 求时间差值
	 * 车辆对应标准线路的标准时间-要提醒站点对应标准线路的标准时间
	 * 通过经纬度匹配来确定是否对应
	 */
	public int getTimespanInMinute(VehicleReal vr,String vin,String route_id ,Date terminalTime, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude)
	{
		int timespanInMinute = -1;

		try
		{
			//StationGenAlgorithmThreshold threshold = algorithmDAO.getStationGenAlgorithmThreshold(vin);

			//if (threshold != null)
			//{
				//Calendar cal = Calendar.getInstance();
				//cal.setTime(terminalTime);
				//int hours = cal.get(Calendar.HOUR_OF_DAY);
				//int type = hours >= threshold.getUpOrDownHour() ? 1 : 0;

				//List<VehicleTrack> vehicleTracks = getVehicleTrack(vin, type, threshold);

				//if (vehicleTracks != null && vehicleTracks.size() > 0)
				//{
					//VehicleTrack fromVehicleTrack = getFromNearestVehicleTrack(fromLongitude, fromLatitude, direction, vehicleTracks, terminalTime, threshold);
					//VehicleTrack fromVehicleTrack = getFromNearestVehicleTrack1(route_id,vin,fromLongitude, fromLatitude, direction, terminalTime);
					
			
			VehicleTrack fromVehicleTrack = getFromNearestVehicleTrack1(route_id,vin,fromLongitude, fromLatitude, direction, terminalTime);

					if (fromVehicleTrack != null)
					{
						//VehicleTrack toVehicleTrack = getToNearestStationVehicleTrack(toLongitude, toLatitude, vehicleTracks, fromVehicleTrack.getTerminalTime(), threshold);
						VehicleTrack toVehicleTrack = getFromNearestVehicleTrack1(route_id,vin,toLongitude, toLatitude, direction, fromVehicleTrack.getTerminalTime());
						if (toVehicleTrack != null)
						{
							//timespanInMinute = Math.abs(AlgorithmUtil.getTimespanMinutes(toVehicleTrack.getTerminalTime(), fromVehicleTrack.getTerminalTime()));
							timespanInMinute = Math.abs(toVehicleTrack.getStandard_time()-fromVehicleTrack.getStandard_time());
						}
					}
			double disTemp = AlgorithmUtil.getDistance(fromLongitude, fromLatitude,toLongitude, toLatitude);
			timespanInMinute = (int) ((Math.abs(disTemp))/Double.parseDouble(vr.getSpeed())*3.6);
				//}
			//}
		}
		catch (Exception e)
		{
			log.error("SimpleTimeCalculator_getTimespanInMinute error", e.getStackTrace(), e.getMessage());
		}

		return timespanInMinute;
	}

	private List<VehicleTrack> getVehicleTrack(String vin, int type, StationGenAlgorithmThreshold threshold)
	{
		List<VehicleTrack> vehicleTracks = null;

		String key = vin;

		key += "_" + type;

		if (vehicleTracksCache.containsKey(key))
		{
			VehicleTrackMapItem itemInCache = vehicleTracksCache.get(key);

			int timespanInMinutes = AlgorithmUtil.getTimespanMinutes(new Date(), itemInCache.getCreateDate());

			if (timespanInMinutes > threshold.getMileageCacheTimeoutInMinute())
			{
				vehicleTracksCache.remove(key);
			}
			else
			{
				vehicleTracks = vehicleTracksCache.get(key).getVehicleTracks();
			}
		}

		if (vehicleTracks == null || vehicleTracks.size() == 0)
		{
			vehicleTracks = getVehicleTrackInDB(vin, type, threshold);

			if (vehicleTracks != null && vehicleTracks.size() > 0)
			{
				VehicleTrackMapItem item = new VehicleTrackMapItem();
				item.setCreateDate(new Date());
				item.setVehicleTracks(vehicleTracks);
				item.setVin(vin);

				vehicleTracksCache.put(key, item);
			}
		}

		return vehicleTracks;
	}

	private List<VehicleTrack> getVehicleTrackInDB(String vin, int type, StationGenAlgorithmThreshold threshold)
	{
		List<VehicleTrack> vehicleTracks = algorithmDAO.getVehicleTrack(vin, type);

		if (vehicleTracks == null || vehicleTracks.size() == 0 )
		{
			int times = 1;
			Date date = new Date();

			while (times <= threshold.getMileageGetLastCheckRecordSampleDateTryTimes())
			{
				Date d = algorithmDAO.getLastCheckRecordSampleDate(vin, date);

				if (d != null)
				{
					vehicleTracks = algorithmDAO.getTerminalRecordDetail(vin, d, type, threshold.getUpOrDownHour());
				}
				else
				{
					break;
				}

				date = d;

				if (vehicleTracks != null)
				{
					break;
				}
				
				times++;
			}
		}

		return vehicleTracks;
	}

	private Date getDateReplaced(Date date1, Date date2)
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String datePart = sdf1.format(date1);

		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		String timePart = sdf2.format(date2);

		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try
		{
			return sdf3.parse(datePart + " " + timePart);
		}
		catch (ParseException e)
		{
			log.error("SimpleTimeCalculator_getDateReplaced error", e.getStackTrace(), e.getMessage());
			return null;
		}
	}

	/*
	 * 找最后离开给定GPS点的GPS
	 */
	private VehicleTrack getExactMatchVehicleTrack(double longitude, double latitude, double direction, List<VehicleTrack> vtList, Date terminalTime, int minuteScope, StationGenAlgorithmThreshold threshold)
	{
		VehicleTrack vt = null;

		List<VehicleTrack> vtListForSearch = new ArrayList<VehicleTrack>();

		Date minDate = DateUtil.getDateByDiscreMinutes(terminalTime, -minuteScope);
		Date maxDate = DateUtil.getDateByDiscreMinutes(terminalTime, minuteScope);

		for (VehicleTrack item : vtList)
		{

			if (item.getTerminalTime().getTime() > minDate.getTime() && item.getTerminalTime().getTime() < maxDate.getTime())
			{
				vtListForSearch.add(item);
			}
		}

		if (vtListForSearch.size() > 0)
		{
			boolean hasMatched = false;

			for (int i = 0; i < vtListForSearch.size(); i++)
			{
				double dis = AlgorithmUtil.getDistance(longitude, latitude, vtListForSearch.get(i).getLongitude(), vtListForSearch.get(i).getLatitude());

				if (dis < threshold.getMileageMinDistanceInMeter())
				{
					hasMatched = true;
				}
				else
				{
					// 找到后，又离开了
					if (hasMatched)
					{
						if (CheckDirectionInScope(direction, vtList.get(i).getDirection(), threshold))
						{
							vt = vtListForSearch.get(i);
						}
					}
				}
			}
		}

		return vt;
	}

	private VehicleTrack getFromNearestVehicleTrack(double longitude, double latitude, double direction, List<VehicleTrack> vtList, Date terminalTime, StationGenAlgorithmThreshold threshold)
	{
		VehicleTrack vt = null;

		Date newTerminalTime = getDateReplaced(vtList.get(0).getTerminalTime(), terminalTime);

		if (newTerminalTime != null)
		{
			int times = 1;
			while (times < threshold.getMileageTerminalTimeScopeCheck() && vt == null)
			{
				vt = getExactMatchVehicleTrack(longitude, latitude, direction, vtList, newTerminalTime, times * threshold.getMileageTerminalTimeScopeInMinute(), threshold);
				times++;
			}

			if (vt == null)
			{
				if (vtList != null && vtList.size() > 0)
				{
					boolean hasMatched = false;

					for (int i = 0; i < vtList.size(); i++)
					{
						double dis = AlgorithmUtil.getDistance(longitude, latitude, vtList.get(i).getLongitude(), vtList.get(i).getLatitude());

						if (dis < threshold.getMileageMinDistanceInMeter())
						{
							vt = vtList.get(i);
							hasMatched = true;

						}
						else
						{
							if (hasMatched)
							{
								if (CheckDirectionInScope(direction, vtList.get(i).getDirection(), threshold))
								{
									break;
								}
							}
						}
					}
				}
			}
		}

		return vt;
	}
	
	private VehicleTrack getFromNearestVehicleTrack1(String route_id,String vin,double longitude, double latitude, double direction, Date terminalTime)
	{
		VehicleTrack vt = null;
		List<VehicleTrack> vtList=algorithmDAO.getStationVinRoute(route_id);

		//Date newTerminalTime = getDateReplaced(vtList.get(0).getTerminalTime(), terminalTime);

		//if (newTerminalTime != null)
		//{
				
				VehicleTrack temp = vtList.get(0);
				double disTemp = AlgorithmUtil.getDistance(longitude, latitude, temp.getLongitude(), temp.getLatitude());
				if (disTemp < 50)
				{
					vt = temp;
				}
				else
				{
					for (int i = 1; i < vtList.size(); i++)
					{
						double dis = AlgorithmUtil.getDistance(longitude, latitude, vtList.get(i).getLongitude(), vtList.get(i).getLatitude());

						if (dis < disTemp)
						{
							temp = vtList.get(i);
							disTemp = dis;
							if (dis < 150)
							{								
									vt = temp;
							}
							if (dis < 50)
							{
								
									vt = temp;
									break;
							}
						}
				}

			

				
		}

		return vt;
	}

	private boolean CheckDirectionInScope(double direction1, String direction2, StationGenAlgorithmThreshold threshold)
	{
		boolean inScope = false;

		try
		{
			if (direction2 != null)
			{
				double directionTemp = Double.parseDouble(direction2);

				if (Math.abs(directionTemp - direction1) <= threshold.getMileageDirectionScope())
				{
					inScope = true;
				}
			}
		}
		catch (Exception e)
		{
		}

		return inScope;
	}
	
	private VehicleTrack getToNearestStationVehicleTrack(double longitude, double latitude, List<VehicleTrack> vtList, Date fromTime, StationGenAlgorithmThreshold threshold)
	{
		VehicleTrack vt = null;

		List<VehicleTrack> vtToList = new ArrayList<VehicleTrack>();

		for (VehicleTrack item : vtList)
		{
			if (item.getTerminalTime().getTime() > fromTime.getTime() && item.getIsStation() == 1)
			{
				vtToList.add(item);
			}
		}

		if (vtToList != null && vtToList.size() > 0)
		{
			VehicleTrack temp = vtToList.get(0);
			double disTemp = AlgorithmUtil.getDistance(longitude, latitude, temp.getLongitude(), temp.getLatitude());

			if (disTemp >= threshold.getMileageMinDistanceInMeter())
			{
				for (int i = 1; i < vtToList.size(); i++)
				{
					double dis = AlgorithmUtil.getDistance(longitude, latitude, vtToList.get(i).getLongitude(), vtToList.get(i).getLatitude());

					if (dis < disTemp)
					{
						temp = vtToList.get(i);
						disTemp = dis;

						if (dis < threshold.getMileageMinDistanceInMeter())
						{
							break;
						}
					}
				}
			}

			vt = temp;
		}
		else
		{
			//vt = getToNearestVehicleTrack(longitude, latitude, vtList, fromTime, threshold);
		}

		return vt;
	}
	
	private VehicleTrack getToNearestVehicleTrack(double longitude, double latitude, List<VehicleTrack> vtList, Date fromTime, StationGenAlgorithmThreshold threshold)
	{
		VehicleTrack vt = null;

		List<VehicleTrack> vtToList = new ArrayList<VehicleTrack>();

		for (VehicleTrack item : vtList)
		{
			if (item.getTerminalTime().getTime() > fromTime.getTime())
			{
				vtToList.add(item);
			}
		}

		if (vtToList != null && vtToList.size() > 0)
		{
			VehicleTrack temp = vtToList.get(0);
			double disTemp = AlgorithmUtil.getDistance(longitude, latitude, temp.getLongitude(), temp.getLatitude());

			if (disTemp >= threshold.getMileageMinDistanceInMeter())
			{
				for (int i = 1; i < vtToList.size(); i++)
				{
					double dis = AlgorithmUtil.getDistance(longitude, latitude, vtToList.get(i).getLongitude(), vtToList.get(i).getLatitude());

					if (dis < disTemp)
					{
						temp = vtToList.get(i);
						disTemp = dis;

						if (dis < threshold.getMileageMinDistanceInMeter())
						{
							break;
						}
					}
				}
			}

			vt = temp;
		}

		return vt;
	}

}
