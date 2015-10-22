/**
 * @author haoxy
 * @createdate 2013年8月29日 上午9:27:14
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

import com.neusoft.parents.algorithm.dao.impl.AlgorithmDAO;
import com.neusoft.parents.algorithm.domain.*;

/**
 * @author haoxy
 * 
 */
public class SimpleStationGenerator2 implements IStationGenerator
{
	// private int minSampleCount = 30;
	// private int recordCount = 100;
	// private int minMatchStationCountDivisor = 2;
	// private int scopeMeter = 300;
	// private int upOrDownHour = 13;
	private int minMatchStationCount = 0;
	// private int scopeminute = 15;

	private AlgorithmDAO algorithmDAO;
	private StationComparator stationComparator;
	private Logger log = LoggerFactory.getLogger(SimpleStationGenerator2.class);
	
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

	/**
	 * 根据车辆的vin号生成站点。
	 */
	
	public VehicleRoute generate(String vehicle_vin)
	{
		VehicleRoute vr = new VehicleRoute();
		VehicleStation vehicleStation = null;
		try
		{
			//获取这辆车所需要的算法阈值
			StationGenAlgorithmThreshold threshold = algorithmDAO.getStationGenAlgorithmThreshold(vehicle_vin);

			if (threshold != null)
			{
				List<VehicleStationsPerDayUpOrDown> downStationSamples = null;
				List<VehicleStationsPerDayUpOrDown> upStationSamples = null;

				List<Station> downStations = null;
				List<Station> upStations = null;

				Station school = null;

				//根据阈值从学生刷卡流水表CLW_XC_ST_CHECK_RECORD_T获取下行的一定天数的记录样本日期。
				List<Date> datesDown = algorithmDAO.getCheckRecordSampleDateByUpAndDown(vehicle_vin, threshold.getStationGenRecordCount(), 1, threshold.getUpOrDownHour());

				if (datesDown != null && datesDown.size() > 0)
				{
					downStationSamples = getStationSamples(vehicle_vin, datesDown, 1, threshold);

					if (downStationSamples != null && downStationSamples.size() >= threshold.getStationGenMinSampleCount())
					{
						minMatchStationCount = downStationSamples.size() / threshold.getStationGenMinMatchStationCountDivisor() + 1;
						//生成学校
						school = getSchoolByStatistics(downStationSamples, threshold);

						downStations = doStatistics(downStationSamples, threshold);
					} else
					{
						System.out.println("vin:" + vehicle_vin + " downStationSamples:" + downStationSamples.size());
					}
				}
				//根据阈值从学生刷卡流水表CLW_XC_ST_CHECK_RECORD_T获取上行的一定天数的记录样本日期。
				List<Date> datesUp = algorithmDAO.getCheckRecordSampleDateByUpAndDown(vehicle_vin, threshold.getStationGenRecordCount(), 0, threshold.getUpOrDownHour());

				if (datesUp != null && datesUp.size() > 0)
				{
					upStationSamples = getStationSamples(vehicle_vin, datesUp, 0, threshold);

					if (upStationSamples != null && upStationSamples.size() >= threshold.getStationGenMinSampleCount())
					{
						minMatchStationCount = upStationSamples.size() / threshold.getStationGenMinMatchStationCountDivisor() + 1;

						upStations = doStatistics(upStationSamples, threshold);
					} else
					{
						System.out.println("vin:" + vehicle_vin + " upStationSamples:" + upStationSamples.size());
					}
				}

				if (school != null)
				{
					if (downStations != null && downStations.size() > 0)
					{
						CheckStationType(downStations, school, threshold);
					}

					if (upStations != null && upStations.size() > 0)
					{
						CheckStationType(upStations, school, threshold);
					}
				}

				if ((datesDown == null || datesDown.size() == 0) && (datesUp == null || datesUp.size() == 0))
				{
					System.out.println("vin:" + vehicle_vin + " no records");
				}

				if (school != null || (upStations != null && upStations.size() > 0) || (downStations != null && downStations.size() > 0))
				{
					vehicleStation = new VehicleStation();

					if (school != null)
					{
						vehicleStation.setSchool(school);
					}

					if (downStations != null && downStations.size() > 0)
					{
						vehicleStation.setStationsDown(downStations);
					}

					if (upStations != null && upStations.size() > 0)
					{
						vehicleStation.setStationsUp(upStations);
					}

					vehicleStation.setVin(vehicle_vin);
				}
			}

		} catch (Exception e)
		{
			log.error("SimpleStationGenerator2_generate error", e.getStackTrace(), e.getMessage());
		}

		if (vehicleStation != null)
		{
			vr.getVehicleStations().add(vehicleStation);
		}
		return vr;
	}

	/**
	 * 获取站点的样本，每天的站点列表集合。
	 * @param vin
	 * @param dates
	 * @param upOrDown
	 * @param threshold
	 * @return
	 */
	private List<VehicleStationsPerDayUpOrDown> getStationSamples(String vin, List<Date> dates, int upOrDown, StationGenAlgorithmThreshold threshold)
	{
		List<VehicleStationsPerDayUpOrDown> samples = new ArrayList<VehicleStationsPerDayUpOrDown>();

		for (Date date : dates)
		{
			VehicleStationsPerDayUpOrDown vspd = getVehicleStations(vin, date, upOrDown, threshold);

			if (vspd != null)
			{
				samples.add(vspd);
			}
		}

		return samples;
	}
	/**
	 * 根据日期，获取当日的站点列表
	 * @param vin
	 * @param date
	 * @param upOrDown
	 * @param threshold
	 * @return
	 */
	private VehicleStationsPerDayUpOrDown getVehicleStations(String vin, Date date, int upOrDown, StationGenAlgorithmThreshold threshold)
	{
		VehicleStationsPerDayUpOrDown vspd = new VehicleStationsPerDayUpOrDown();

		vspd.setVin(vin);
		vspd.setDate(date);

		try
		{
			List<CheckCardRecord> CheckCardDetailList = null;
			try
			{
				CheckCardDetailList = algorithmDAO.getCheckCardRecordByUpOrDown(vin, date, upOrDown, threshold.getUpOrDownHour());
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			List<Station> stationList = null;
			Station school = null;

			if (CheckCardDetailList != null && CheckCardDetailList.size() > 0)
			{
				stationList = MergeStation(CheckCardDetailList, upOrDown, threshold);
				vspd.setStations(stationList);

				if (upOrDown == 1 && stationList != null && stationList.size() > 0)
				{
					school = generateSchool(stationList);

					if (school != null)
					{
						setStationType(school, stationList, threshold);
						vspd.setSchool(school);
					}
				}
			}
		} catch (Exception e)
		{
			log.error("SimpleStationGenerator2_getVehicleStations error", e.getStackTrace(), e.getMessage());
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
		// 合并站点（具体差scopeMeter的合并，认为是同一站点）
		CheckCardRecord temp = CheckCardRecordList.get(0);
		Station s = getStation(temp, direction);
		stationList.add(s);

		for (int i = 1; i < CheckCardRecordList.size(); i++)
		{
			try
			{
				double dis = AlgorithmUtil.getDistance(temp.getLongitude(), temp.getLatitude(), CheckCardRecordList.get(i).getLongitude(), CheckCardRecordList.get(i).getLatitude());
				CheckCardRecord row = CheckCardRecordList.get(i);
				temp = row;
				if (dis > threshold.getStationGenScopeMeter())
				{
					Station s_new = getStation(row, direction);
					stationList.add(s_new);
				} else
				{
					int studentCount = stationList.get(stationList.size() - 1).getStudentCount() + row.getStudentCount();
					stationList.get(stationList.size() - 1).setStudentCount(studentCount);
				}
			} catch (Exception e)
			{
				log.error("SimpleStationGenerator2_MergeStation error", e.getStackTrace(), e.getMessage());
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
			double dis = AlgorithmUtil.getDistance(school.getLongitude(), school.getLatitude(), s.getLongitude(), s.getLatitude());
			if (dis > threshold.getStationGenScopeMeter())
			{
				s.setType(0);
			} else
			{
				s.setType(1);
			}
		}
	}

	private Station getSchoolByStatistics(List<VehicleStationsPerDayUpOrDown> samples, StationGenAlgorithmThreshold threshold)
	{
		Station resultSchool = null;

		double total_station_longitude = 0.0;
		double total_station_latitude = 0.0;
		int total_station_stu_num = 0;

		try
		{
			int match_station_count = 0;

			for (int m = 0; m < samples.size(); m++)
			{
				
				try				
				{
					total_station_longitude = samples.get(m).getSchool().getLongitude();
					total_station_latitude = samples.get(m).getSchool().getLatitude();
					// total_station_terminal_time =
					// samples.get(m).getSchool().getTerminalTime().getTime();
	
					List<Date> dates = new ArrayList<Date>();
					dates.add(samples.get(m).getSchool().getTerminalTime());
					
					List<Long> studentCounts = new ArrayList<Long>();
					studentCounts.add(Long.valueOf(samples.get(m).getSchool().getStudentCount()));
					
					total_station_stu_num = samples.get(m).getSchool().getStudentCount();
	
					match_station_count = 1;
	
					try
					{
						for (int n = 0; n < samples.size(); n++)
						{
							if (m != n)
							{
								try
								{
									double dis = AlgorithmUtil.getDistance(samples.get(m).getSchool().getLongitude(), samples.get(m).getSchool().getLatitude(), samples.get(n).getSchool().getLongitude(), samples.get(n).getSchool().getLatitude());
									if (dis <= threshold.getStationGenScopeMeter())
									{
										match_station_count = match_station_count + 1;
			
										total_station_longitude = total_station_longitude + samples.get(n).getSchool().getLongitude();
										total_station_latitude = total_station_latitude + samples.get(n).getSchool().getLatitude();
										// total_station_terminal_time = total_station_terminal_time + samples.get(n).getSchool().getTerminalTime().getTime();
										
										dates.add(AlgorithmUtil.roundDate(samples.get(n).getSchool().getTerminalTime()));
										studentCounts.add(Long.valueOf(samples.get(n).getSchool().getStudentCount()));
			//							total_station_stu_num = total_station_stu_num + samples.get(n).getSchool().getStudentCount();
									}
								}
								catch(Exception e)
								{
									log.error("SimpleStationGenerator2_getSchoolByStatistics error", e.getStackTrace(), e.getMessage());
								}
							}
						}
					}
					catch(Exception e)
					{
						log.error("SimpleStationGenerator2_getSchoolByStatistics error", e.getStackTrace(), e.getMessage());
					}
					if (match_station_count >= minMatchStationCount)
					{
						resultSchool = new Station();
						resultSchool.setLatitude(total_station_latitude / match_station_count);
						resultSchool.setLongitude(total_station_longitude / match_station_count);
						// resultSchool.setTerminalTime(AlgorithmUtil.roundDate(new
						// Date(total_station_terminal_time/match_station_count)));
						resultSchool.setTerminalTime(AlgorithmUtil.getAverageDateMedianFilter(dates));
	//					resultSchool.setStudentCount(total_station_stu_num / match_station_count);
						resultSchool.setStudentCount((int)AlgorithmUtil.getAverageLongMedianFilter(studentCounts));
						resultSchool.setType(1);
						break;
					}
				}
				catch(Exception e)
				{
					log.error("SimpleStationGenerator2_getSchoolByStatistics error", e.getStackTrace(), e.getMessage());
				}
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return resultSchool;
	}

	private List<Station> doStatistics(List<VehicleStationsPerDayUpOrDown> samples, StationGenAlgorithmThreshold threshold)
	{
		try
		{
			List<Station> resultStations = new ArrayList<Station>();

			List<StationCountPair> resultStationCountPair = new ArrayList<StationCountPair>();

			for (int m = 0; m < samples.size(); m++)
			{
				for (int n = 0; n < samples.get(m).getStations().size(); n++)
				{
					int matched_id = -1;

					for (int o = 0; o < resultStationCountPair.size(); o++)
					{
						double dis = AlgorithmUtil.getDistance(resultStationCountPair.get(o).getAvgLongitude(), resultStationCountPair.get(o).getAvgLatitude(), samples.get(m).getStations().get(n).getLongitude(), samples.get(m).getStations().get(n).getLatitude());

						if (dis <= threshold.getStationGenScopeMeter())
						{
							int time_span = AlgorithmUtil.getTimespanMinutes(AlgorithmUtil.getAverageDateMedianFilter(resultStationCountPair.get(o).getTimeList()), samples.get(m).getStations().get(n).getTerminalTime());

							if (time_span < threshold.getStationGenScopeMinute())
							{
								matched_id = o;
								break;
							}
						}
					}

					if (matched_id > -1)
					{
						resultStationCountPair.get(matched_id).set_total_station_longitude(resultStationCountPair.get(matched_id).get_total_station_longitude() + samples.get(m).getStations().get(n).getLongitude());
						resultStationCountPair.get(matched_id).set_total_station_latitude(resultStationCountPair.get(matched_id).get_total_station_latitude() + samples.get(m).getStations().get(n).getLatitude());
						// resultStationCountPair.get(matched_id).set_total_station_terminal_time(resultStationCountPair.get(matched_id).get_total_station_terminal_time()
						// +
						// samples.get(m).getStations().get(n).getTerminalTime().getTime());

						resultStationCountPair.get(matched_id).getTimeList().add(AlgorithmUtil.roundDate(samples.get(m).getStations().get(n).getTerminalTime()));

						//resultStationCountPair.get(matched_id).set_total_station_stu_num(resultStationCountPair.get(matched_id).get_total_station_stu_num() + samples.get(m).getStations().get(n).getStudentCount());
						
						resultStationCountPair.get(matched_id).getStudentCountList().add(Long.valueOf(samples.get(m).getStations().get(n).getStudentCount()));
						
						resultStationCountPair.get(matched_id).setCount(resultStationCountPair.get(matched_id).getCount() + 1);
					} else
					{
						StationCountPair sc = new StationCountPair();
						sc.setStation(samples.get(m).getStations().get(n));
						sc.set_total_station_latitude(samples.get(m).getStations().get(n).getLatitude());
						sc.set_total_station_longitude(samples.get(m).getStations().get(n).getLongitude());
//						sc.set_total_station_stu_num(samples.get(m).getStations().get(n).getStudentCount());
//						sc.set_total_station_terminal_time(samples.get(m).getStations().get(n).getTerminalTime().getTime());

						sc.getTimeList().add(AlgorithmUtil.roundDate(samples.get(m).getStations().get(n).getTerminalTime()));
						sc.getStudentCountList().add(Long.valueOf(samples.get(m).getStations().get(n).getStudentCount()));
						
						sc.setCount(1);
						resultStationCountPair.add(sc);
					}
				}
			}

			for (StationCountPair p : resultStationCountPair)
			{
				if (p.getCount() >= minMatchStationCount)
				{
					Station s = p.getStation();
					s.setLongitude(p.getAvgLongitude());
					s.setLatitude(p.getAvgLatitude());
//					s.setStudentCount(p.getAvgStudentCount());
//					s.setTerminalTime(p.getAvgTime());

					s.setTerminalTime(AlgorithmUtil.getAverageDateMedianFilter(p.getTimeList()));
					s.setStudentCount((int)AlgorithmUtil.getAverageLongMedianFilter(p.getStudentCountList()));
					resultStations.add(p.getStation());
				} else
				{
					System.out.print("消除" + p.getAvgTime() + " " + p.getCount());
				}
			}

			if (resultStations != null && resultStations.size() > 0)
			{
				Collections.sort(resultStations, stationComparator);
			}

			return resultStations;
		} catch (Exception e)
		{
			log.error("SimpleStationGenerator2_doStatistics error", e.getStackTrace(), e.getMessage());
			return null;
		}
	}

	private void CheckStationType(List<Station> stations, Station school, StationGenAlgorithmThreshold threshold)
	{
		for (Station s : stations)
		{
			double dis = AlgorithmUtil.getDistance(school.getLongitude(), school.getLatitude(), s.getLongitude(), s.getLatitude());
			if (dis < threshold.getStationGenScopeMeter())
			{
				s.setType(1);
			} else
			{
				s.setType(0);
			}
		}
	}
}
