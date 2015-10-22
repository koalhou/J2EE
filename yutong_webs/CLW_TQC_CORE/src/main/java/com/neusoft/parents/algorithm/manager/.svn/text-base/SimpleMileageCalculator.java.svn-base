/**
 * @author liuja
 * @createdate 2013年8月30日 上午8:15:36
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
import com.neusoft.parents.algorithm.domain.*;
import com.neusoft.parents.bean.NoticeBean;

/**
 * @author liuja
 * 
 */
public class SimpleMileageCalculator implements IMileageCalculator
{
	private AlgorithmDAO algorithmDAO;
	private Logger log = LoggerFactory.getLogger(SimpleMileageCalculator.class);
	private Map<String, VehicleTrackMapItem> vehicleTracksCache = new HashMap<String, VehicleTrackMapItem>();

	public void setAlgorithmDAO(AlgorithmDAO algorithmDAO)
	{
		this.algorithmDAO = algorithmDAO;
	}

	public AlgorithmDAO getAlgorithmDAO()
	{
		return this.algorithmDAO;
	}
	/**
	 * 获取实时里程计算
	 */
	public int getMileage(String vin, Date terminalTime, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude)
	{
		int mileage = -1;

		try
		{
			StationGenAlgorithmThreshold threshold = algorithmDAO.getStationGenAlgorithmThreshold(vin);//获取阈值，通过阈值，和上下行，找到标准行程轨迹

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
							// change from km to m
							mileage = (int) (Math.abs(toVehicleTrack.getMileage() - fromVehicleTrack.getMileage()) * 1000);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			log.error("SimpleMileageCalculator_getMileage error", e.getStackTrace(), e.getMessage());
		}

		return mileage;
	}
	
	public int getMileage(String vin,String route_id, String site_id, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude)
	{
		int mileage = -1;

		try
		{
			VehicleTrack fromVehicleTrack = getFromNearestVehicleTrack1(route_id,vin,fromLongitude, fromLatitude, direction);

			if (fromVehicleTrack != null)
			{
				VehicleTrack toVehicleTrack = getFromNearestVehicleTrack1(route_id,vin,toLongitude, toLatitude, direction);

				if (toVehicleTrack != null)
				{
					// change from km to m
					mileage = (int) (Math.abs(toVehicleTrack.getMileage() - fromVehicleTrack.getMileage()) * 1000);
				}
			}
			double disTemp = AlgorithmUtil.getDistance(fromLongitude, fromLatitude,toLongitude, toLatitude);
			mileage = (int) (Math.abs(disTemp));
				//}
			//}
		}
		catch (Exception e)
		{
			log.error("SimpleMileageCalculator_getMileage error", e.getStackTrace(), e.getMessage());
		}

		return mileage;
	}
	
	
	/**
	 * 判断车辆到站点距离新方法
	 */
	public int getMileage(String route_id,String site_id,  double longitude, double latitude, int taget_value,int threshold_value){
		
		int mileage = -1;
		float f_taget = (float)taget_value/1000;
		
		List<NoticeBean> vtList=algorithmDAO.getLineByTagetMile(route_id,site_id,f_taget);
		
		//判断车辆是不是在查询到的轨迹内
		for(NoticeBean be:vtList){
			double disTemp = AlgorithmUtil.getDistance(longitude, latitude, Double.parseDouble(be.getLongitude()), Double.parseDouble(be.getLatitude()));
			if(disTemp <= threshold_value){
				mileage = 1;
				break;
			}
		}

		return mileage;
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

		if (vehicleTracks == null)
		{
			int times = 1;
			Date date = new Date();
			
			while(times <= 	threshold.getMileageGetLastCheckRecordSampleDateTryTimes())
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
				
				if(vehicleTracks != null)
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
		} catch (ParseException e)
		{
			log.error("SimpleMileageCalculator_getDateReplaced error", e.getStackTrace(), e.getMessage());
			return null;
		}
	}
	
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
			/*
			 * 快速，但是不够精确 for (int i = 0; i < vtListForSearch.size(); i++) {
			 * double dis = AlgorithmUtil.getDistance(longitude, latitude,
			 * vtListForSearch.get(i) .getLongitude(),
			 * vtListForSearch.get(i).getLatitude());
			 * 
			 * if (dis < minDistanceInMeter) { vt = vtListForSearch.get(i);
			 * break; } }
			 */
			double disTemp = 999999999;

			VehicleTrack temp = null;
			for (int i = 0; i < vtListForSearch.size(); i++)
			{
				double dis = AlgorithmUtil.getDistance(longitude, latitude, vtListForSearch.get(i).getLongitude(), vtListForSearch.get(i).getLatitude());

				if (dis < disTemp)
				{
					disTemp = dis;
					temp = vtListForSearch.get(i);
				}
			}

			if (disTemp < threshold.getMileageMinDistanceInMeter())
			{
				if (CheckDirectionInScope(direction, temp.getDirection(), threshold))
				{
					vt = temp;
				}
			}
		}

		return vt;
	}

	/**
	 * 获取在标准轨迹中，离输入起始点点最近的那个点
	 * @param longitude
	 * @param latitude
	 * @param direction
	 * @param vtList
	 * @param terminalTime
	 * @param threshold
	 * @return
	 */
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
					VehicleTrack temp = vtList.get(0);

					double disTemp = AlgorithmUtil.getDistance(longitude, latitude, temp.getLongitude(), temp.getLatitude());

					if (disTemp < threshold.getMileageMinDistanceInMeter() && CheckDirectionInScope(direction, temp.getDirection(), threshold))
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

								if (dis < threshold.getMileageMinDistanceInMeter())
								{
									if (CheckDirectionInScope(direction, vtList.get(i).getDirection(), threshold))
									{
										vt = temp;
										break;
									}
								}
							}
						}
					}
				}
			}
		}

		return vt;
	}
	
	/**
	 * 获取在标准轨迹中，离输入起始点点最近的那个点
	 * @param longitude
	 * @param latitude
	 * @param direction
	 * @param vtList
	 * @param threshold
	 * @return
	 */
	private VehicleTrack getFromNearestVehicleTrack1(String route_id,String vin,double longitude, double latitude, double direction)
	{
		VehicleTrack vt = null;
		List<VehicleTrack> vtList=algorithmDAO.getStationVinRoute(route_id);
		//StationGenAlgorithmThreshold threshold;
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
								if (dis <50)
								{
									
										vt = temp;
										break;
								}
							}
						}
					}
			
		//}

		return vt;
	}
	
	
	private VehicleTrack getFromNearestVehicleTrack2(String route_id,String vin,double longitude, double latitude, double direction,Date terminalTime)
	{
		VehicleTrack vt = null;
		List<VehicleTrack> vtList=algorithmDAO.getStationVinRoute(route_id);
		//StationGenAlgorithmThreshold threshold;
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

								if (dis < 50)
								{
									
										vt = temp;
										break;
								}
							}
						}
					}
			
		//}

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
	
	/**
	 * 获取在标准轨迹中，离输入结束点最近的那个点
	 * @param longitude
	 * @param latitude
	 * @param vtList
	 * @param fromTime
	 * @param threshold
	 * @return
	 */
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
							vt = temp;
							break;
						}
					}
				}
			}
			else
			{
				vt = temp;
			}
		}
		else
		{
			// vt = getToNearestVehicleTrack(longitude, latitude, vtList,
			// fromTime, threshold);
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
