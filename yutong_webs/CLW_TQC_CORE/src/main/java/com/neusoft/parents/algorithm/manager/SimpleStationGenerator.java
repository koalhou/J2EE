/**
 * @author haoxy
 * @createdate 2013年8月29日 上午9:27:14
 * @description 
 */
package com.neusoft.parents.algorithm.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.parents.algorithm.dao.impl.AlgorithmDAO;
import com.neusoft.parents.algorithm.domain.Station;
import com.neusoft.parents.algorithm.domain.StationCountPair;
import com.neusoft.parents.algorithm.domain.CheckCardRecord;
import com.neusoft.parents.algorithm.domain.StationGenAlgorithmThreshold;
import com.neusoft.parents.algorithm.domain.VehicleRoute;
import com.neusoft.parents.algorithm.domain.VehicleStation;
import com.neusoft.parents.algorithm.domain.VehicleStationsPerDay;

/**
 * @author haoxy
 * 
 */
public class SimpleStationGenerator implements IStationGenerator
{
//	private int minSampleCount = 10;
//	private int recordCount = 100;
//	private int minMatchStationCountDivisor = 3;
//	private int scopeMeter = 300;
//	private int upOrDownHour = 13;
	private int minMatchStationCount = 0;
//	private int scopeminute = 15;

	private AlgorithmDAO algorithmDAO;
	private StationComparator stationComparator;
	private Logger log = LoggerFactory.getLogger(SimpleStationGenerator.class);
	
	public void setAlgorithmDAO(AlgorithmDAO algorithmDAO)
	{
		this.algorithmDAO = algorithmDAO;
	}
	
	public AlgorithmDAO getAlgorithmDAO()
	{
		return this.algorithmDAO;
	}
	
	public void setStationComparator(StationComparator stationComparator)
	{
		this.stationComparator = stationComparator;
	}
	
	public VehicleRoute generate(String vehicle_vin)
	{
		VehicleRoute vr = new VehicleRoute();
		VehicleStation vehicleStation = null;
		try
		{
			StationGenAlgorithmThreshold threshold = algorithmDAO.getStationGenAlgorithmThreshold(vehicle_vin);
			
			if(threshold != null)
			{
				List<VehicleStationsPerDay> samples = new ArrayList<VehicleStationsPerDay>();
	
				List<Date> dates = algorithmDAO.getCheckRecordSampleDate(vehicle_vin, threshold.getStationGenRecordCount());
				for (Date date : dates)
				{
					VehicleStationsPerDay vs = getVehicleStations(vehicle_vin, date, threshold);
	
					if (vs != null && vs.getSchool() != null && vs.getStationsDown() != null
							&& vs.getStationsDown().size() > 0 && vs.getStationsUp() != null
							&& vs.getStationsUp().size() > 0)
					{
						samples.add(vs);
					}
				}
	
				if (samples.size() > threshold.getStationGenMinSampleCount())
				{
					minMatchStationCount = samples.size() / threshold.getStationGenMinMatchStationCountDivisor();
	
					vehicleStation = doStatistics(samples, threshold);
	
					if (vehicleStation != null)
					{
						vehicleStation.setVin(vehicle_vin);
					}
				}
				else
				{
					if(dates.size() > 0)
					{
						//comments
						System.out.println("vin:" + vehicle_vin + " dates count:" + dates.size() + " sample count:" + samples.size());
					}
				}
			}
		} catch (Exception e)
		{
			log.error("SimpleStationGenerator_generate error", e.getStackTrace(), e.getMessage());
		}


		if(vehicleStation != null)
		{
			vr.getVehicleStations().add(vehicleStation);
		}
		return vr;
	}

	private VehicleStationsPerDay getVehicleStations(String vin, Date date, StationGenAlgorithmThreshold threshold)
	{
		VehicleStationsPerDay vspd = null;
		try
		{
			List<CheckCardRecord> CheckCardDetailListUp = null;
			List<CheckCardRecord> CheckCardDetailListDown = null;
			try
			{
				CheckCardDetailListUp = algorithmDAO.getCheckCardRecordUp(vin, date, threshold.getUpOrDownHour());
				CheckCardDetailListDown = algorithmDAO.getCheckCardRecordDown(vin, date, threshold.getUpOrDownHour());
			}
			catch(Exception e)
			{
				log.error("SimpleStationGenerator_getVehicleStations error", e.getStackTrace(), e.getMessage());
			}
			
			List<Station> stationListUp = null;
			List<Station> stationListDown = null;

			if (CheckCardDetailListUp != null && CheckCardDetailListUp.size() > 0
					&& CheckCardDetailListDown != null && CheckCardDetailListDown.size() > 0)
			{

				stationListUp = MergeStation(CheckCardDetailListUp, 0, threshold);
				stationListDown = MergeStation(CheckCardDetailListDown, 1, threshold);

				// 获取学校 下行刷卡最多的
				Station school = generateSchool(stationListDown);

				setStationType(school, stationListDown, threshold);
				setStationType(school, stationListUp, threshold);

				vspd = new VehicleStationsPerDay();
				vspd.setVin(vin);
				vspd.setDate(date);

				vspd.setStationsDown(stationListDown);
				vspd.setStationsUp(stationListUp);
				vspd.setSchool(school);

			}
		} catch (Exception e)
		{
			log.error("SimpleStationGenerator_getVehicleStations error", e.getStackTrace(), e.getMessage());
		}

		return vspd;
	}
	
	private Station generateSchool(List<Station> stations)
	{
		Station school = stations.get(0);
		
		for (int i = 1; i < stations.size(); i++)
		{
			Station compareStation = stations.get(i);
			if (compareStation.getStudentCount() > school.getStudentCount())
			{
				school = compareStation;
			}
		}
		
		return school;
	}

	private List<Station> MergeStation(List<CheckCardRecord> CheckCardRecordList, int direction, StationGenAlgorithmThreshold threshold)
	{
		List<Station> stationList = new ArrayList<Station>();
		// 合并上行站点（具体差scopeMeter的合并，认为是同一站点）
		CheckCardRecord temp = CheckCardRecordList.get(0);
		Station s = getStation(temp, direction);
		stationList.add(s);

		for (int i = 1; i < CheckCardRecordList.size(); i++)
		{
			try
			{
				double dis = AlgorithmUtil.getDistance(temp.getLongitude(), temp.getLatitude(),
						CheckCardRecordList.get(i).getLongitude(), CheckCardRecordList.get(i)
								.getLatitude());
				CheckCardRecord row = CheckCardRecordList.get(i);
				temp = row;
				if (dis > threshold.getUpOrDownHour())
				{
					Station s_new = getStation(row, direction);
					stationList.add(s_new);
				} else
				{
					int studentCount = stationList.get(stationList.size() - 1).getStudentCount()
							+ row.getStudentCount();
					stationList.get(stationList.size() - 1).setStudentCount(studentCount);
				}
			} catch (Exception e)
			{
				log.error("SimpleStationGenerator_MergeStation error", e.getStackTrace(), e.getMessage());
			}
		}

		return stationList;
	}

	

	private Station getStation(CheckCardRecord record, int direction)
	{
		Station s = new Station();
		s.setDirection(direction);
		s.setLatitude(record.getLatitude());
		s.setLongitude(record.getLongitude());
		s.setStudentCount(record.getStudentCount());
		s.setTerminalTime(AlgorithmUtil.roundDate(record.getTerminalTime()));

		return s;
	}

	private void setStationType(Station school, List<Station> stations, StationGenAlgorithmThreshold threshold)
	{
		for (Station s : stations)
		{
			double dis = AlgorithmUtil.getDistance(school.getLongitude(), school.getLatitude(), s.getLongitude(),
					s.getLatitude());
			if (dis > threshold.getUpOrDownHour())
			{
				s.setType(0);
			} else
			{
				s.setType(1);
			}
		}
	}

	private VehicleStation doStatistics(List<VehicleStationsPerDay> samples, StationGenAlgorithmThreshold threshold)
	{

		VehicleStation vs = null;

		Station resultSchool = null;
		List<Station> resultStationUp = new ArrayList<Station>();
		List<Station> resultStationDown = new ArrayList<Station>();

		List<StationCountPair> resultStationCountPairUp = new ArrayList<StationCountPair>();
		List<StationCountPair> resultStationCountPairDown = new ArrayList<StationCountPair>();

		double total_station_longitude = 0.0;
		double total_station_latitude = 0.0;
		long total_station_terminal_time = 0;
		int total_station_stu_num = 0;
		
		
		List<Integer> wrong_station_id_list = new ArrayList<Integer>();

		// 计算学校
		for (int m = 0; m < samples.size(); m++)
		{
			total_station_longitude = samples.get(m).getSchool().getLongitude();
			total_station_latitude = samples.get(m).getSchool().getLatitude();
			total_station_terminal_time = samples.get(m).getSchool().getTerminalTime().getTime();
			total_station_stu_num = samples.get(m).getSchool().getStudentCount();

			wrong_station_id_list.clear();
			int match_station_count = 1;

			for (int n = 0; n < samples.size(); n++)
			{
				if (m != n)
				{
					double dis = AlgorithmUtil.getDistance(samples.get(m).getSchool().getLongitude(), samples.get(m)
						.getSchool().getLatitude(), samples.get(n).getSchool().getLongitude(),
						samples.get(n).getSchool().getLatitude());
					if (dis <= threshold.getUpOrDownHour())
					{
						match_station_count = match_station_count + 1;
	
						total_station_longitude = total_station_longitude + samples.get(n).getSchool()
								.getLongitude();
						total_station_latitude = total_station_latitude + samples.get(n).getSchool()
								.getLatitude();
						total_station_terminal_time = total_station_terminal_time + samples
								.get(n).getSchool().getTerminalTime().getTime();
						total_station_stu_num = total_station_stu_num + samples.get(n).getSchool()
								.getStudentCount();
					} else
					{
						wrong_station_id_list.add(n);
					}
				}
			}

			if (match_station_count >= minMatchStationCount)
			{
				resultSchool = new Station();
				resultSchool.setLatitude(total_station_latitude / match_station_count);
				resultSchool.setLongitude(total_station_longitude / match_station_count);
				resultSchool.setTerminalTime(AlgorithmUtil.roundDate(new Date(total_station_terminal_time/match_station_count)));
				resultSchool.setStudentCount(total_station_stu_num / match_station_count);
				resultSchool.setType(1);
				break;
			}
		}
		
		total_station_longitude = 0.0;
		total_station_latitude = 0.0;
		total_station_terminal_time = 0;
		total_station_stu_num = 0;

		// 清理sample
		if (resultSchool != null)
		{
			for (int i = wrong_station_id_list.size() - 1; i >= 0; i--)
			{
				samples.remove(i);
			}

			// 计算上行
			wrong_station_id_list.clear();

			for (int m = 0; m < samples.size(); m++)
			{
				for (int n = 0; n < samples.get(m).getStationsUp().size(); n++)
				{
					int matched_id = -1;

					for (int o = 0; o < resultStationCountPairUp.size(); o++)
					{
						double dis = AlgorithmUtil.getDistance(resultStationCountPairUp.get(o).getAvgLongitude(), resultStationCountPairUp.get(o).getAvgLatitude(), samples.get(m).getStationsUp().get(n)
								.getLongitude(), samples.get(m).getStationsUp().get(n).getLatitude());

						if (dis <= threshold.getUpOrDownHour())
						{
							int time_span = AlgorithmUtil.getTimespanMinutes(resultStationCountPairUp.get(o)
									.getAvgTime(), samples.get(m).getStationsUp()
									.get(n).getTerminalTime());

							if (time_span < threshold.getStationGenScopeMinute())
							{
								matched_id = o;
								break;
							}
						}
					}

					if (matched_id > -1)
					{
						resultStationCountPairUp.get(matched_id).set_total_station_longitude(resultStationCountPairUp.get(matched_id).get_total_station_longitude() + samples.get(m).getStationsUp().get(n).getLongitude());
						resultStationCountPairUp.get(matched_id).set_total_station_latitude(resultStationCountPairUp.get(matched_id).get_total_station_latitude() + samples.get(m).getStationsUp().get(n).getLatitude());
						resultStationCountPairUp.get(matched_id).set_total_station_terminal_time(resultStationCountPairUp.get(matched_id).get_total_station_terminal_time() + samples.get(m).getStationsUp().get(n).getTerminalTime().getTime());
						resultStationCountPairUp.get(matched_id).set_total_station_stu_num(resultStationCountPairUp.get(matched_id).get_total_station_stu_num() + samples.get(m).getStationsUp().get(n).getStudentCount());
//						resultStationCountPairUp.get(matched_id).getStation().setLongitude((resultStationCountPairUp.get(matched_id).getStation().getLongitude() + samples.get(m).getStationsUp().get(n).getLongitude()) / 2);
//						resultStationCountPairUp.get(matched_id).getStation().setLatitude((resultStationCountPairUp.get(matched_id).getStation().getLatitude() + samples.get(m).getStationsUp().get(n).getLatitude()) / 2);
//						resultStationCountPairUp.get(matched_id).getStation().setTerminalTime(roundDate(getAvgTime(resultStationCountPairUp.get(matched_id).getStation().getTerminalTime(), samples.get(m).getStationsUp().get(n).getTerminalTime())));
//						resultStationCountPairUp.get(matched_id).getStation().setStudentCount((resultStationCountPairUp.get(matched_id).getStation().getStudentCount() + samples.get(m).getStationsUp().get(n).getStudentCount()) / 2);
						resultStationCountPairUp.get(matched_id).setCount(resultStationCountPairUp.get(matched_id).getCount() + 1);
					} else
					{
						StationCountPair sc = new StationCountPair();
						sc.setStation(samples.get(m).getStationsUp().get(n));
						sc.set_total_station_latitude(samples.get(m).getStationsUp().get(n).getLatitude());
						sc.set_total_station_longitude(samples.get(m).getStationsUp().get(n).getLongitude());
						sc.set_total_station_stu_num(samples.get(m).getStationsUp().get(n).getStudentCount());
						sc.set_total_station_terminal_time(samples.get(m).getStationsUp().get(n).getTerminalTime().getTime());
						sc.setCount(1);
						resultStationCountPairUp.add(sc);
					}
				}
			}
			// 清理不满足次数的站点

			for (StationCountPair p : resultStationCountPairUp)
			{
				if (p.getCount() >= minMatchStationCount)
				{
					Station s = p.getStation();
					s.setLongitude(p.getAvgLongitude());
					s.setLatitude(p.getAvgLatitude());
					s.setStudentCount(p.getAvgStudentCount());
					s.setTerminalTime(p.getAvgTime());
					resultStationUp.add(p.getStation());
				}
			}

			Collections.sort(resultStationUp, stationComparator);
			CheckStationType(resultStationUp, resultSchool, threshold);

			// 计算下行
			wrong_station_id_list.clear();

			for (int m = 0; m < samples.size(); m++)
			{
				for (int n = 0; n < samples.get(m).getStationsDown().size(); n++)
				{
					int matched_id = -1;

					for (int o = 0; o < resultStationCountPairDown.size(); o++)
					{
						double dis = AlgorithmUtil.getDistance(resultStationCountPairDown.get(o).getAvgLongitude(), resultStationCountPairDown.get(o).getAvgLatitude(), samples.get(m).getStationsDown().get(n)
								.getLongitude(), samples.get(m).getStationsDown().get(n)
								.getLatitude());

						if (dis <= threshold.getUpOrDownHour())
						{
							int time_span = AlgorithmUtil.getTimespanMinutes(resultStationCountPairDown.get(o)
									.getStation().getTerminalTime(), samples.get(m)
									.getStationsDown().get(n).getTerminalTime());

							if (time_span < threshold.getStationGenScopeMinute())
							{
								matched_id = o;
								break;
							}
						}
					}

					if (matched_id > -1)
					{
						resultStationCountPairDown.get(matched_id).set_total_station_longitude(resultStationCountPairDown.get(matched_id).get_total_station_longitude() + samples.get(m).getStationsDown().get(n).getLongitude());
						resultStationCountPairDown.get(matched_id).set_total_station_latitude(resultStationCountPairDown.get(matched_id).get_total_station_latitude() + samples.get(m).getStationsDown().get(n).getLatitude());
						resultStationCountPairDown.get(matched_id).set_total_station_terminal_time(resultStationCountPairDown.get(matched_id).get_total_station_terminal_time() + samples.get(m).getStationsDown().get(n).getTerminalTime().getTime());
						resultStationCountPairDown.get(matched_id).set_total_station_stu_num(resultStationCountPairDown.get(matched_id).get_total_station_stu_num() + samples.get(m).getStationsDown().get(n).getStudentCount());
						
//						resultStationCountPairDown.get(matched_id).getStation().setLongitude((resultStationCountPairDown.get(matched_id).getStation().getLongitude() + samples.get(m).getStationsDown().get(n).getLongitude()) / 2);
//						resultStationCountPairDown.get(matched_id).getStation().setLatitude((resultStationCountPairDown.get(matched_id).getStation().getLatitude() + samples.get(m).getStationsDown().get(n).getLatitude()) / 2);
//						resultStationCountPairDown.get(matched_id).getStation().setTerminalTime(roundDate(getAvgTime(resultStationCountPairDown.get(matched_id).getStation().getTerminalTime(), samples.get(m).getStationsDown().get(n).getTerminalTime())));						
//						resultStationCountPairDown.get(matched_id).getStation().setStudentCount((resultStationCountPairDown.get(matched_id).getStation().getStudentCount() + samples.get(m).getStationsDown().get(n).getStudentCount()) / 2);
						resultStationCountPairDown.get(matched_id).setCount(resultStationCountPairDown.get(matched_id).getCount() + 1);
					} else
					{
						StationCountPair sc = new StationCountPair();
						sc.setStation(samples.get(m).getStationsDown().get(n));
						
						sc.set_total_station_latitude(samples.get(m).getStationsDown().get(n).getLatitude());
						sc.set_total_station_longitude(samples.get(m).getStationsDown().get(n).getLongitude());
						sc.set_total_station_stu_num(samples.get(m).getStationsDown().get(n).getStudentCount());
						sc.set_total_station_terminal_time(samples.get(m).getStationsDown().get(n).getTerminalTime().getTime());
						
						sc.setCount(1);
						resultStationCountPairDown.add(sc);
					}
				}
			}
			// 清理不满足次数的站点

			for (StationCountPair p : resultStationCountPairDown)
			{
				if (p.getCount() >= minMatchStationCount)
				{
					resultStationDown.add(p.getStation());
				}
			}
			// print('下行-----------------------------------')
			Collections.sort(resultStationDown, stationComparator);
			CheckStationType(resultStationDown, resultSchool, threshold);
		}

		if (resultSchool != null && resultStationUp.size() > 0 && resultStationDown.size() > 0)
		{
			vs = new VehicleStation();
			vs.setSchool(resultSchool);
			vs.setStationsDown(resultStationDown);
			vs.setStationsUp(resultStationUp);

			Date bestMatchDate = getBestMatchDate(vs, samples, threshold);
			vs.setBestMatchDate(bestMatchDate);
		}

		return vs;
	}

	private Date getBestMatchDate(VehicleStation vs, List<VehicleStationsPerDay> samples, StationGenAlgorithmThreshold threshold)
	{
		Date bestMatchDate = samples.get(samples.size() - 1).getDate();
		for (int i = samples.size() - 1; i >= 0; i--)
		{
			boolean match = false;

			if (samples.get(i).getStationsUp().size() == vs.getStationsUp().size()
					&& samples.get(i).getStationsDown().size() == vs.getStationsDown().size())
			{
				double schoolDis = AlgorithmUtil.getDistance(samples.get(i).getSchool().getLongitude(), samples
						.get(i).getSchool().getLatitude(), vs.getSchool().getLongitude(), vs
						.getSchool().getLatitude());

				if (schoolDis <= threshold.getUpOrDownHour())
				{

					boolean stationCountMatch = true;

					for (int m = 0; m < vs.getStationsUp().size(); m++)
					{
						double stationDis = AlgorithmUtil.getDistance(samples.get(i).getStationsUp().get(m)
								.getLongitude(), samples.get(i).getStationsUp().get(m)
								.getLatitude(), vs.getStationsUp().get(m).getLongitude(), vs
								.getStationsUp().get(m).getLatitude());

						if (stationDis > threshold.getUpOrDownHour())
						{
							stationCountMatch = false;
							break;
						}
					}

					if (stationCountMatch)
					{
						for (int n = 0; n < vs.getStationsDown().size(); n++)
						{
							double stationDis = AlgorithmUtil.getDistance(samples.get(i).getStationsDown().get(n)
									.getLongitude(), samples.get(i).getStationsDown().get(n)
									.getLatitude(), vs.getStationsDown().get(n).getLongitude(), vs
									.getStationsDown().get(n).getLatitude());

							if (stationDis > threshold.getUpOrDownHour())
							{
								stationCountMatch = false;
								break;
							}
						}

						if (stationCountMatch)
						{
							match = true;
						}
					}
				}
			}

			if (match)
			{
				bestMatchDate = samples.get(i).getDate();
				break;
			}
		}

		return bestMatchDate;
	}

	private void CheckStationType(List<Station> stations, Station school, StationGenAlgorithmThreshold threshold)
	{
		for (Station s : stations)
		{
			double dis = AlgorithmUtil.getDistance(school.getLongitude(), school.getLatitude(), s.getLongitude(),
					s.getLatitude());
			if (dis < threshold.getUpOrDownHour())
			{
				s.setType(1);
			} else
			{
				s.setType(0);
			}
		}
	}
}
