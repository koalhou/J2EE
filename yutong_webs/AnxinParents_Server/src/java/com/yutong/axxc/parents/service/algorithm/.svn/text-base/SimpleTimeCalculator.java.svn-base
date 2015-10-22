/**
 * @author haoxy
 * @createdate 2013年9月5日 上午11:34:48
 * @description 
 */
package com.yutong.axxc.parents.service.algorithm;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neusoft.parents.bean.Vehicle;
import com.yutong.axxc.parents.entity.distance.*;
import com.yutong.axxc.parents.mapper.MybatisDAO;

/**
 * @author haoxy
 * 
 */
@Component
public class SimpleTimeCalculator implements ITimeCalculator
{
	private Logger log = LoggerFactory.getLogger(SimpleTimeCalculator.class);
	private Map<String, VehicleTrackMapItem> vehicleTracksCache = new HashMap<String, VehicleTrackMapItem>();

	@Autowired
	protected MybatisDAO dao;

	public int getTimespanInMinute(String vin, Date terminalTime, double fromLongitude, double fromLatitude, double direction, double toLongitude, double toLatitude)
	{
		int timespanInMinute = -1;

		try
		{
			StationGenAlgorithmThreshold threshold = getStationGenAlgorithmThreshold(vin);

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

	@SuppressWarnings(
	{ "unchecked", "rawtypes" })
	private List<VehicleTrack> getVehicleTrackInDB(String vin, int type, StationGenAlgorithmThreshold threshold)
	{
		Map map1 = new HashMap();
		map1.put("vin", vin);
		map1.put("type", type);

		List<VehicleTrack> vehicleTracks = dao.getList("Algorithm.getVehicleTrack", map1);

		if (vehicleTracks == null || vehicleTracks.size() == 0)
		{
			int times = 1;
			Date date = new Date();

			while (times <= threshold.getMileageGetLastCheckRecordSampleDateTryTimes())
			{
				Map map2 = new HashMap();
				map2.put("vin", vin);
				map2.put("date", date);

				Date d = dao.get("Algorithm.getLastCheckRecordSampleDate", map2);

				if (d != null)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
					String partition = sdf.format(d);

					String sql = "";

					if (type == 0)
					{
						sql = "select vehicle_vin vin, longitude,latitude, mileage,terminal_time terminalTime from CLWXC.CLW_YW_TERMINAL_RECORD_T " + "" + "	partition(terminal_record_" + partition + ") where Mileage is not null and vehicle_vin = '" + vin + "'" + " and to_number(to_char(terminal_time,'hh24')) < " + threshold.getUpOrDownHour() + "	and longitude is not null and latitude is not null and " + "	longitude != 'FFFF' and latitude != 'FFFF' order by terminalTime";
					}
					else
					{
						sql = "select vehicle_vin vin, longitude,latitude, mileage,terminal_time terminalTime from CLWXC.CLW_YW_TERMINAL_RECORD_T " + "" + "	partition(terminal_record_" + partition + ") where Mileage is not null and vehicle_vin = '" + vin + "'" + " and to_number(to_char(terminal_time,'hh24')) >= " + threshold.getUpOrDownHour() + "	and longitude is not null and latitude is not null and " + "	longitude != 'FFFF' and latitude != 'FFFF' order by terminalTime";
					}

					// SQLAdapter adapter = new SQLAdapter();
					// adapter.setSql(sql);

					Map map3 = new HashMap();
					map3.put("sql", sql);
					vehicleTracks = dao.getList("Algorithm.executeSql", map3);
					// Map map3 = new HashMap();
					// map3.put("vin", vin);
					// map3.put("partition", partition);
					// map3.put("type", type);
					// map3.put("upOrDownHour", threshold.getUpOrDownHour());
					//
					// vehicleTracks =
					// dao.getList("Algorithm.getTerminalRecordDetail", map3);
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

	public StationGenAlgorithmThreshold getStationGenAlgorithmThreshold(String vin)
	{
		StationGenAlgorithmThreshold threshold = null;

		try
		{
			List<StationGenAlgorithmParam> params = null;

			params = dao.getList("Algorithm.getStationGenAlgorithmParamByVin", vin);

			if (params == null || params.size() == 0)
			{
				Vehicle v = dao.get("Algorithm.getVehicle", vin);

				String organizationId = v.getOrganizationid();

				params = dao.getList("Algorithm.getStationGenAlgorithmParamByOrganization", organizationId);

				if (params == null || params.size() == 0)
				{
					String enterpriseId = v.getEnterpriseId();

					params = dao.getList("Algorithm.getStationGenAlgorithmParamByEnterprise", enterpriseId);

					if (params == null || params.size() == 0)
					{
						params = dao.getList("Algorithm.getStationGenAlgorithmParamByDefault");
					}
				}
			}

			if (params != null && params.size() > 0)
			{
				threshold = new StationGenAlgorithmThreshold();

				for (StationGenAlgorithmParam p : params)
				{
					String paramName = p.getParamName().toLowerCase();

					if (paramName.equals("stationgenminsamplecount"))
					{
						threshold.setStationGenMinSampleCount(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("stationgenrecordcount"))
					{
						threshold.setStationGenRecordCount(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("stationgenminmatchstationcountdivisor"))
					{
						threshold.setStationGenMinMatchStationCountDivisor(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("stationgenscopemeter"))
					{
						threshold.setStationGenScopeMeter(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("upordownhour"))
					{
						threshold.setUpOrDownHour(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("stationgenscopeminute"))
					{
						threshold.setStationGenScopeMinute(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("triporbitgetlastcheckrecordsampledatetrytimes"))
					{
						threshold.setTripOrbitGetLastCheckRecordSampleDateTryTimes(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("mileagegetlastcheckrecordsampledatetrytimes"))
					{
						threshold.setMileageGetLastCheckRecordSampleDateTryTimes(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("mileagecachetimeoutinminute"))
					{
						threshold.setMileageCacheTimeoutInMinute(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("mileagemindistanceinmeter"))
					{
						threshold.setMileageMinDistanceInMeter(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("mileageterminaltimescopeinminute"))
					{
						threshold.setMileageTerminalTimeScopeInMinute(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("mileageterminaltimescopecheck"))
					{
						threshold.setMileageTerminalTimeScopeCheck(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("mileagedirectionscope"))
					{
						threshold.setMileageDirectionScope(Integer.parseInt(p.getParamValue()));
					}
					else if (paramName.equals("triporbitcheckcardtimescopeinsecond"))
					{
						threshold.setTripOrbitCheckCardTimeScopeInSecond(Integer.parseInt(p.getParamValue()));
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return threshold;
	}
}
