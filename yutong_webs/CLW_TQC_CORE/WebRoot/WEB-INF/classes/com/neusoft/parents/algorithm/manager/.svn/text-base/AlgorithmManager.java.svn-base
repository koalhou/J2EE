/**
 * @author haoxy
 * @createdate 2013年8月28日 下午1:01:16
 * @description 
 */
package com.neusoft.parents.algorithm.manager;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.parents.algorithm.dao.impl.AlgorithmDAO;
import com.neusoft.parents.algorithm.domain.*;
import com.neusoft.parents.service.ParentsBuildSQL;

/**
 * @author liuja
 * 
 */
public class AlgorithmManager
{
	private Logger log = LoggerFactory.getLogger(AlgorithmManager.class);
	private IStationGenerator stationGenerator;
	private IMileageCalculator mileageCalculator;
	private ITimeCalculator timeCalculator;
	private ITripOrbitGenerator tripOrbitGenerator;
	private IStationStudentGenerator stationStudentGenerator;

	private AlgorithmDAO algorithmDAO;

	public AlgorithmDAO getAlgorithmDAO()
	{
		return algorithmDAO;
	}

	public void setAlgorithmDAO(AlgorithmDAO algorithmDAO)
	{
		this.algorithmDAO = algorithmDAO;
	}

	public void setStationGenerator(IStationGenerator stationGenerator)
	{
		this.stationGenerator = stationGenerator;
	}

	public void setMileageCalculator(IMileageCalculator mileageCalculator)
	{
		this.mileageCalculator = mileageCalculator;
	}

	public void setTimeCalculator(ITimeCalculator timeCalculator)
	{
		this.timeCalculator = timeCalculator;
	}

	public void setTripOrbitGenerator(ITripOrbitGenerator tripOrbitGenerator)
	{
		this.tripOrbitGenerator = tripOrbitGenerator;
	}

	public void setStationStudentGenerator(IStationStudentGenerator stationStudentGenerator)
	{
		this.stationStudentGenerator = stationStudentGenerator;
	}

	public int getMileage(String vin,String route_id,String site_id, Date terminalTime, double longitude1, double latitude1, double direction, double longitude2, double latitude2)
	{
		return mileageCalculator.getMileage(vin, route_id,site_id, longitude1, latitude1, direction, longitude2, latitude2);
	}
	
	public int getMileage(String route_id,String site_id,  double longitude, double latitude, int taget_value,int threshold_value)
	{
		return mileageCalculator.getMileage(route_id,site_id, longitude, latitude, taget_value, threshold_value);
	}

	public int getTimespanInMinute(String vin, Date terminalTime, double longitude1, double latitude1, double direction, double longitude2, double latitude2)
	{
		return timeCalculator.getTimespanInMinute(vin, terminalTime, longitude1, latitude1, direction, longitude2, latitude2);
	}
	
	public int getTimespanInSec(String route_id,String site_id,  double longitude, double latitude, int taget_value,int threshold_value)
	{
		return timeCalculator.getTimespanInSec(route_id, site_id, longitude, latitude,  taget_value, threshold_value);
	}

	public void generateTripOrbit()
	{
		generateTripOrbitByModel(2);
		generateTripOrbitByModel(3);
	}

	public void generateTripOrbitByModel(int model)
	{
		try
		{
			List<Vehicle> vehicleList = algorithmDAO.getVehiclesByModel(model);

			for (Vehicle v : vehicleList)
			{
				generateTripOrbitByVin(v.getVin());
			}
		} catch (Exception e)
		{
			log.error("AlgorithmManager_generateTripOrbitByModel error", e.getStackTrace(), e.getMessage());
		}
	}

	public void generateTripOrbitByVin(String vin)
	{
		try
		{
			List<VehicleTripOrbit> vehicleTripOrbits = tripOrbitGenerator.generate(vin);

			if (vehicleTripOrbits != null && vehicleTripOrbits.size() > 0)
			{
				for (VehicleTripOrbit vto : vehicleTripOrbits)
				{
					int count = algorithmDAO.getTripOrbitCountByTripId(vto.getTripId());

					if (count == 0)
					{
						for (VehicleTrack vt : vto.getVehicleTracks())
						{
							algorithmDAO.insertTripOrbit(vt);
						}
					}
				}
			}
		} catch (Exception e)
		{
			log.error("AlgorithmManager_generateTripOrbitByVin error", e.getStackTrace(), e.getMessage());
		}
	}

	public void generateStationStudentsByVin(String vin, boolean canUpdateToXCTables)
	{
		try
		{
			List<Station> stations = stationStudentGenerator.generate(vin);

			if (stations != null && stations.size() > 0)
			{
				PrintStationStudents(vin, stations);
				UpdateStationStudentsToTempTables(vin, stations);

				if (canUpdateToXCTables)
				{
					UpdateStationStudentsToXCTables(vin, stations);
				}
			}
		} catch (Exception e)
		{
			log.error("AlgorithmManager_generateStationStudentsByVin error", e.getStackTrace(), e.getMessage());
		}
	}

	private void UpdateStationStudentsToTempTables(String vin, List<Station> stations)
	{
		if (stations != null && stations.size() > 0)
		{
			for (Station s : stations)
			{
				log.info("Station Id:" + s.getSiteId() + " Type:" + s.getDirection());

				try
				{
					algorithmDAO.deleteStationStudentFromBackup(s.getSiteId());

					for (Integer id : s.getStudentIdList())
					{
						algorithmDAO.insertStationStudentToBackup(s.getSiteId(), id.intValue());
					}
				} catch (Exception e)
				{
					log.error("AlgorithmManager_insertStationStudents error", e.getStackTrace(), e.getMessage());
				}
			}
		}
	}

	private void PrintStationStudents(String vin, List<Station> stations)
	{
		if (stations != null && stations.size() > 0)
		{
			for (Station s : stations)
			{
				log.info("Station Id:" + s.getSiteId() + " Type:" + s.getDirection());

				for (Integer id : s.getStudentIdList())
				{
					System.out.print(id + ", ");
				}

				log.info("------------------------------------------------------------------------");
			}
		}
	}

	private void UpdateStationStudentsToXCTables(String vin, List<Station> stations)
	{
		if (stations != null && stations.size() > 0)
		{
			for (Station s : stations)
			{
				log.info("Station Id:" + s.getSiteId() + " Type:" + s.getDirection());

				try
				{
					int count = algorithmDAO.getStationStudentTripSiteCount(s.getTripId(), s.getSiteId());
					int routeId = algorithmDAO.getRouteIdByTripId(s.getTripId());

					if (count == 0)
					{
						for (Integer id : s.getStudentIdList())
						{
							algorithmDAO.insertStationStudent(vin, routeId, s.getTripId(), s.getSiteId(), id.intValue());
						}
					}
				} catch (Exception e)
				{
					log.error("AlgorithmManager_insertStationStudents error", e.getStackTrace(), e.getMessage());
				}
			}
		}
	}

	public Date getAverageDateMedianFilter(List<Date> dates)
	{
		Date avgDate = AlgorithmUtil.getAverageDateMedianFilter(dates);
		return avgDate;
	}

	public List<Long> getSampleByMedianFilter(List<Long> samples)
	{
		List<Long> newSamples = AlgorithmUtil.getSampleByMedianFilter(samples);
		return newSamples;
	}


	private void delTempTables(String vin)
	{
		algorithmDAO.deleteJzStuSite(vin);
		algorithmDAO.deleteJzRouteCalc(vin);
		algorithmDAO.deleteJzSiteCalc(vin);
		algorithmDAO.deleteJzSiteVehicle(vin);
		algorithmDAO.deleteJzTrip(vin);
	}

	private void UpdateToXCRouteSite(Station s, String vin, int xcSiteId, int xcRouteId, int order)
	{
		algorithmDAO.ExecuteSql(ParentsBuildSQL.getInstance().buildInsertRouteSiteSql(s, xcSiteId, xcRouteId, order));
	}

	private void UpdateToXCSite(Station s, VehicleStation vs, int xcSiteId)
	{
		algorithmDAO.ExecuteSql(ParentsBuildSQL.getInstance().buildInsertSiteSql(s, vs, xcSiteId));
	}

	private void UpdateToXCRoute(Date dateup1, Date dateup2, Date datedown1, Date datedown2, VehicleStation vs, String vin, int xcRouteId)
	{
		algorithmDAO.ExecuteSql(ParentsBuildSQL.getInstance().buildInsertRouteSql(dateup1, dateup2, datedown1, datedown2, vs, xcRouteId));
	}

	private void UpdateToXCTrip(VehicleStation vs, String vin, int xcTripId, int xcRouteId, int upOrDown)
	{
		algorithmDAO.ExecuteSql(ParentsBuildSQL.getInstance().buildInsertTripSql(vs, xcTripId, xcRouteId, upOrDown));
	}

	private void UpdateToXCVssSite(String vin, int xcSiteId, int xcRouteId, int xcTripId, Date planOutTime, Date planInTime)
	{
		algorithmDAO.ExecuteSql(ParentsBuildSQL.getInstance().buildInsertVssSiteSql(vin, xcSiteId, xcRouteId, xcTripId, planOutTime, planInTime));
	}

	private void UpdateStationsToXCTables(VehicleRoute vr, String vin)
	{
		for (VehicleStation vs : vr.getVehicleStations())
		{
			if (vs.getStationsUp() != null && vs.getStationsUp().size() > 0 && vs.getStationsDown() != null && vs.getStationsDown().size() > 0)
			{
				int xcRouteId = algorithmDAO.getXCRouteID();
				int xcTripId = 0;

				if (vs.getStationsUp() != null && vs.getStationsUp().size() > 0)
				{
					int order = 1;
					xcTripId = algorithmDAO.getXCTripID();
					for (Station s : vs.getStationsUp())
					{
						int xcSiteId = algorithmDAO.getXCSiteID();

						UpdateToXCSite(s, vs, xcSiteId);
						UpdateToXCRouteSite(s, vin, xcSiteId, xcRouteId, order);

						this.UpdateToXCVssSite(vin, xcSiteId, xcRouteId, xcTripId, s.getTerminalTime(), s.getTerminalTime());

						order++;
					}

					this.UpdateToXCTrip(vs, vin, xcTripId, xcRouteId, 0);
				}
				if (vs.getStationsDown() != null && vs.getStationsDown().size() > 0)
				{
					int order = 1;

					xcTripId = algorithmDAO.getXCTripID();
					for (Station s : vs.getStationsDown())
					{
						int xcSiteId = algorithmDAO.getXCSiteID();

						UpdateToXCSite(s, vs, xcSiteId);
						UpdateToXCRouteSite(s, vin, xcSiteId, xcRouteId, order);

						this.UpdateToXCVssSite(vin, xcSiteId, xcRouteId, xcTripId, s.getTerminalTime(), s.getTerminalTime());

						order++;
					}

					this.UpdateToXCTrip(vs, vin, xcTripId, xcRouteId, 1);
				}

				Date dateUpBegin = null;
				Date dateUpEnd = null;
				Date dateDownBegin = null;
				Date dateDownEnd = null;

				if (vs.getStationsUp() != null && vs.getStationsUp().size() > 0)
				{
					dateUpBegin = vs.getStationsUp().get(0).getTerminalTime();
					dateUpEnd = vs.getStationsUp().get(vs.getStationsUp().size() - 1).getTerminalTime();
				}
				if (vs.getStationsDown() != null && vs.getStationsDown().size() > 0)
				{
					dateDownBegin = vs.getStationsDown().get(0).getTerminalTime();
					dateDownEnd = vs.getStationsDown().get(vs.getStationsDown().size() - 1).getTerminalTime();
				}

				if (dateUpBegin != null && dateUpEnd != null && dateDownBegin != null && dateDownEnd != null)
				{
					this.UpdateToXCRoute(dateUpBegin, dateUpEnd, dateDownBegin, dateDownEnd, vs, vin, xcRouteId);
				} else
				{
					log.info("Route Id:" + xcRouteId + " Date is null");
				}
			}
		}
	}

	private boolean checkCanUpdateXCTables(String vin)
	{
		boolean can = false;

		int count = algorithmDAO.getTripListCount(vin);

		if (count == 0)
		{
			count = algorithmDAO.getVssSiteListCount(vin);

			if (count == 0)
			{
				count = algorithmDAO.getRouteListCount(vin);

				if (count == 0)
				{
					count = algorithmDAO.getRouteSiteListCount(vin);

					if (count == 0)
					{
						count = algorithmDAO.getSiteListCount(vin);

						if (count == 0)
						{
							count = algorithmDAO.getVssListCount(vin);

							if (count == 0)
							{
								can = true;
							}
						}
					}
				}
			}
		}

		return can;
	}
}
