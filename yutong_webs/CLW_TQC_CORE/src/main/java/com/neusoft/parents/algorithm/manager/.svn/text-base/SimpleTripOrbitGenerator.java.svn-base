/**
 * @author haoxy
 * @createdate 2013年9月3日 上午11:12:23
 * @description 
 */
package com.neusoft.parents.algorithm.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.parents.algorithm.dao.*;
import com.neusoft.parents.algorithm.domain.*;

/**
 * @author haoxy
 * 
 */
public class SimpleTripOrbitGenerator implements ITripOrbitGenerator
{
	private IAlgorithmDAO algorithmDAO;
	private Logger log = LoggerFactory.getLogger(SimpleTripOrbitGenerator.class);

	// private int tryTimes = 10;
	// private int upOrDownHour = 13;
	// private int scopeMeter = 300;

	public void setAlgorithmDAO(IAlgorithmDAO algorithmDAO)
	{
		this.algorithmDAO = algorithmDAO;
	}

	public IAlgorithmDAO getAlgorithmDAO()
	{
		return this.algorithmDAO;
	}

	public List<VehicleTripOrbit> generate(String vin)
	{
		List<VehicleTripOrbit> vehicleTripOrbits = null;

		StationGenAlgorithmThreshold threshold = algorithmDAO.getStationGenAlgorithmThreshold(vin);

		if (threshold != null)
		{
			List<Trip> trips = algorithmDAO.getTripNotInOrbitByVehicleVin(vin);

			if (trips != null && trips.size() > 0)
			{
				vehicleTripOrbits = new ArrayList<VehicleTripOrbit>();

				for (Trip t : trips)
				{
					try
					{
						List<VehicleTrack> matchedVehicleTracks = null;

						List<VehicleTripStation> vehicleTripStations = algorithmDAO.getVehicleTripStationByTripId(t.getTripId());

						int matchCount = vehicleTripStations.size();

						Date date = new Date();

//						SimpleDateFormat dt1 = new SimpleDateFormat("MM/dd/yyyy");
//						date = dt1.parse("6/20/2013");

						int times = 1;
						while (times <= threshold.getTripOrbitGetLastCheckRecordSampleDateTryTimes())
						{
							Date d = algorithmDAO.getLastCheckRecordSampleDate(vin, date);

							if (d == null)
							{
								break;
							}

							List<CheckCardRecord> checkCardRecords = algorithmDAO.getCheckCardRecordDetailByUpOrDown(vin, d, t.getType(), threshold.getUpOrDownHour());

							date = d;

							if (checkCardRecords != null && checkCardRecords.size() > 0)
							{
								int matchCountTemp = 0;

								// 查看是否经过所有的站点。
								for (int i = 0; i < vehicleTripStations.size(); i++)
								{
									for (CheckCardRecord ccr : checkCardRecords)
									{
										double dis = AlgorithmUtil.getDistance(vehicleTripStations.get(i).getSiteLongitude(), vehicleTripStations.get(i).getSiteLatitude(), ccr.getLongitude(), ccr.getLatitude());
 
										if (dis <= threshold.getStationGenScopeMeter())
										{
											matchCountTemp++;
											break;
										}
									}
								}

								if (matchCountTemp == matchCount)
								{
									matchedVehicleTracks = algorithmDAO.getTerminalRecordDetail(vin, d, t.getType(), threshold.getUpOrDownHour());
									break;
								}
							}
						}

						//行程内：在手机端要显示的路线轨迹，上行不包含车辆到第一个站点前的轨迹点，下行不包含车辆到达最后一个站点以后的轨迹点
						//行程外：里程计算要用到的轨迹点。用一个字段来区分。
						if (matchedVehicleTracks != null)
						{
							SetIsStation(vin, date, t.getType(), threshold.getUpOrDownHour(), t.getTripId(), matchedVehicleTracks, threshold);
							// 标记哪些应该是行程内的点，哪些不是。
							// 去头
							for (VehicleTrack vt : matchedVehicleTracks)
							{
								double dis = AlgorithmUtil.getDistance(vehicleTripStations.get(0).getSiteLongitude(), vehicleTripStations.get(0).getSiteLatitude(), vt.getLongitude(), vt.getLatitude());

								if (dis > threshold.getStationGenScopeMeter())
								{
									vt.setIsTripOrbit(0);
								}
								else
								{
									break;
								}
							}
							// 去尾
							for (int m = matchedVehicleTracks.size() - 1; m >= 0; m--)
							{
								double dis = AlgorithmUtil.getDistance(vehicleTripStations.get(vehicleTripStations.size() - 1).getSiteLongitude(), vehicleTripStations.get(vehicleTripStations.size() - 1).getSiteLatitude(), matchedVehicleTracks.get(m).getLongitude(), matchedVehicleTracks.get(m).getLatitude());

								if (dis > threshold.getStationGenScopeMeter())
								{
									matchedVehicleTracks.get(m).setIsTripOrbit(0);
								}
								else
								{
									break;
								}
							}

							List<VehicleTrack> vehicleTracks = filter(matchedVehicleTracks);

							VehicleTripOrbit vto = new VehicleTripOrbit();
							vto.setTripId(t.getTripId());
							vto.setVin(vin);
							vto.setVehicleTracks(vehicleTracks);

							for (VehicleTrack vt : vehicleTracks)
							{
								vt.setTripId(t.getTripId());
								vt.setType(t.getType());
							}

							vehicleTripOrbits.add(vto);
						}
						else
						{
							System.out.println("Trip Id:" + t.getTripId() + " has no matched track.");
						}
					}
					catch (Exception e)
					{
						log.error("SimpleTripOrbitGenerator_generate error", e.getStackTrace(), e.getMessage());
					}
				}
			}
		}

		return vehicleTripOrbits;
	}

	/*
	 * 过滤相同GPS点的轨迹
	 */
	private List<VehicleTrack> filter(List<VehicleTrack> vehicleTracks)
	{
		List<VehicleTrack> newList = new ArrayList<VehicleTrack>();

		VehicleTrack previous = null;
		for (VehicleTrack vt : vehicleTracks)
		{
			if (previous == null || previous.getLatitude() != vt.getLatitude() || previous.getLongitude() != vt.getLongitude() || vt.getIsStation() == 1)
			{
				newList.add(vt);
			}

			previous = vt;
		}

		return newList;
	}

	private void SetIsStation(String vin, Date date, int upOrDown, int upOrDownHour, int tripId, List<VehicleTrack> vehicleTracks, StationGenAlgorithmThreshold threshold)
	{
		List<CheckCardRecord> ccrList = algorithmDAO.getCheckCardRecordDetailByUpOrDown(vin, date, upOrDown, upOrDownHour);
		List<Station> stationList = algorithmDAO.getStationByTripId(tripId);

		if (ccrList != null && ccrList.size() > 0)
		{
			for (VehicleTrack vt : vehicleTracks)
			{
				boolean isStationCandidate = false;
				boolean isStation = false;

				for (Station s : stationList)
				{
					double dis = AlgorithmUtil.getDistance(vt.getLongitude(), vt.getLatitude(), s.getLongitude(), s.getLatitude());

					// 同一个位置
					if (dis <= threshold.getStationGenScopeMeter())
					{
						isStationCandidate = true;
						break;
					}
				}

				if (isStationCandidate)
				{
					for (CheckCardRecord ccr : ccrList)
					{
						int timeSpanInSeconds = AlgorithmUtil.getTimespanSeconds(vt.getTerminalTime(), ccr.getTerminalTime());
						
						if (timeSpanInSeconds <= threshold.getTripOrbitCheckCardTimeScopeInSecond())
						{
							isStation = true;
							break;
						}
					}
				}
				
				if(isStation)
				{
					vt.setIsStation(1);
				}
			}
		}
	}
}
