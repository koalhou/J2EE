/**
 * @author haoxy
 * @createdate 2013年8月27日 下午5:25:05
 */
package com.neusoft.parents.algorithm.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.parents.algorithm.dao.*;
import com.neusoft.parents.algorithm.domain.*;
import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.tag.dao.support.*;

/**
 * @author haoxy
 * 
 */
public class AlgorithmDAO extends AbstractDaoManager implements IAlgorithmDAO
{

	@SuppressWarnings("unchecked")
	public List<CheckCardRecord> getCheckCardRecordUp(String vehicle_vin, Date date, int hour)
	{
		String sql = "select longitude,latitude, count(1) studentCount, min(terminal_time) terminalTime from CLW_XC_ST_CHECK_RECORD_T where terminal_time >= to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd')" + " and terminal_time < to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd') " + " and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) < ? " + " and longitude != 'FFFF' and latitude != 'FFFF' " + " and longitude is not null and Latitude is not null group by longitude,Latitude order by terminalTime";
		return this.jdbcTemplate.query(sql, new Object[]
		{ date, DateUtil.getDateByDiscreHours(date, 24), vehicle_vin, hour }, ParameterizedBeanPropertyRowMapper.newInstance(CheckCardRecord.class));
	}

	@SuppressWarnings("unchecked")
	public List<CheckCardRecord> getCheckCardRecordDown(String vehicle_vin, Date date, int hour)
	{
		String sql = "select longitude,latitude, count(1) studentCount, min(terminal_time) terminalTime from CLW_XC_ST_CHECK_RECORD_T where terminal_time >= to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd')" + " and terminal_time < to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd') " + " and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) >= ? " + " and longitude != 'FFFF' and latitude != 'FFFF' " + " and longitude is not null and Latitude is not null group by longitude,Latitude order by terminalTime";
		return this.jdbcTemplate.query(sql, new Object[]
		{ date, DateUtil.getDateByDiscreHours(date, 24), vehicle_vin, hour }, ParameterizedBeanPropertyRowMapper.newInstance(CheckCardRecord.class));
	}

	@SuppressWarnings("unchecked")
	public List<CheckCardRecord> getCheckCardRecordByUpOrDown(String vehicle_vin, Date date, int upOrDown, int upOrDownHour)
	{
		String sql = "";
		if (upOrDown == 0)
		{
			sql = "select longitude,latitude, count(1) studentCount, min(terminal_time) terminalTime from CLW_XC_ST_CHECK_RECORD_T where terminal_time >= to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd')" + " and terminal_time < to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd') " + " and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) < ? " + " and longitude != 'FFFF' and latitude != 'FFFF' " + " and longitude is not null and Latitude is not null group by longitude,Latitude order by terminalTime";
		}
		else
		{
			sql = "select longitude,latitude, count(1) studentCount, min(terminal_time) terminalTime from CLW_XC_ST_CHECK_RECORD_T where terminal_time >= to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd')" + " and terminal_time < to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd') " + " and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) >= ? " + " and longitude != 'FFFF' and latitude != 'FFFF' " + " and longitude is not null and Latitude is not null group by longitude,Latitude order by terminalTime";
		}

		return this.jdbcTemplate.query(sql, new Object[]
		{ date, DateUtil.getDateByDiscreHours(date, 24), vehicle_vin, upOrDownHour }, ParameterizedBeanPropertyRowMapper.newInstance(CheckCardRecord.class));
	}

	@SuppressWarnings("unchecked")
	public List<CheckCardRecord> getCheckCardRecordDetailByUpOrDown(String vehicle_vin, Date date, int upOrDown, int upOrDownHour)
	{
		String sql = "";
		if (upOrDown == 0)
		{
			sql = "select longitude,latitude, stu_id studentId, terminal_time terminalTime from CLW_XC_ST_CHECK_RECORD_T where terminal_time >= to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd')" + " and terminal_time < to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd') " + " and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) < ? " + " and longitude != 'FFFF' and latitude != 'FFFF' " + " and longitude is not null and Latitude is not null order by terminalTime";
		}
		else
		{
			sql = "select longitude,latitude, stu_id studentId, terminal_time terminalTime from CLW_XC_ST_CHECK_RECORD_T where terminal_time >= to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd')" + " and terminal_time < to_date(to_char(?,'yyyy-MM-dd'),'yyyy-MM-dd') " + " and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) >= ? " + " and longitude != 'FFFF' and latitude != 'FFFF' " + " and longitude is not null and Latitude is not null order by terminalTime";
		}

		return this.jdbcTemplate.query(sql, new Object[]
		{ date, DateUtil.getDateByDiscreHours(date, 24), vehicle_vin, upOrDownHour }, ParameterizedBeanPropertyRowMapper.newInstance(CheckCardRecord.class));
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> getVehiclesByModel(int model)
	{
		String sql = "select vehicle_vin Vin,vehicle_ln Ln, c.enterprise_id enterpriseid,C.ORGANIZATION_ID organizationid from clw_cl_base_info_t c inner join clw_jc_enterprise_t e on c.enterprise_id = e.enterprise_id" + " where c.valid_flag = 0 and e.valid_flag = 0 and e.enterprise_model = ? and exists (select 1 from CLW_XC_ST_CHECK_RECORD_T where longitude is not null and Latitude is not null and vehicle_vin = c.vehicle_vin)";

		return this.jdbcTemplate.query(sql, new Object[]
		{ model }, ParameterizedBeanPropertyRowMapper.newInstance(Vehicle.class));

	}

	public Vehicle getVehicle(String vin)
	{
		String sql = "select vehicle_vin Vin,vehicle_ln Ln, c.enterprise_id enterpriseid,C.ORGANIZATION_ID organizationid from clw_cl_base_info_t c inner join clw_jc_enterprise_t e on c.enterprise_id = e.enterprise_id" + " where c.valid_flag = 0 and e.valid_flag = 0 and c.vehicle_vin = ? ";

		return (Vehicle) this.jdbcTemplate.queryForObject(sql, new Object[]
		{ vin }, ParameterizedBeanPropertyRowMapper.newInstance(Vehicle.class));

	}

	@SuppressWarnings("unchecked")
	public List<Date> getCheckRecordSampleDate(String vin, int recordCount)
	{
		String sql = "select terminal_date from (select distinct(trunc(terminal_time)) terminal_date from CLW_XC_ST_CHECK_RECORD_T where vehicle_vin = ? order by terminal_date desc) where rownum <= ?";
		return (List<Date>) this.jdbcTemplate.queryForList(sql, new Object[]
		{ vin, recordCount }, Date.class);
	}

	@SuppressWarnings("unchecked")
	public List<Date> getCheckRecordSampleDateByUpAndDown(String vin, int recordCount, int upOrDown, int upOrDownHour)
	{
		String sql = "";

		if (upOrDown == 0)
		{
			sql = "select terminal_date from (select distinct(trunc(terminal_time)) terminal_date from CLW_XC_ST_CHECK_RECORD_T where vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) < ? and longitude != 'FFFF' and latitude != 'FFFF' and longitude is not null and Latitude is not null order by terminal_date desc) where rownum <= ?";
		}
		else
		{
			sql = "select terminal_date from (select distinct(trunc(terminal_time)) terminal_date from CLW_XC_ST_CHECK_RECORD_T where vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) >= ? and longitude != 'FFFF' and latitude != 'FFFF' and longitude is not null and Latitude is not null order by terminal_date desc) where rownum <= ?";
		}

		return (List<Date>) this.jdbcTemplate.queryForList(sql, new Object[]
		{ vin, upOrDownHour, recordCount }, Date.class);
	}

	@SuppressWarnings("unchecked")
	public List<VehicleTrack> getVehicleTrack(String vin, int type)
	{
		String sql = "select trip_id tripId, type, vehicle_vin vin, longitude,latitude, Mileage,orbit_time terminalTime, create_time createdate,direction, is_station isStation from CLW_JZ_TripOrbit_T where vehicle_vin = ? and type = ?";
		return this.jdbcTemplate.query(sql, new Object[]
		{ vin, type }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleTrack.class));
	}

	public Date getLastCheckRecordSampleDate(String vin, Date date)
	{
		try
		{
			String sql = "select terminal_date from (select distinct(trunc(terminal_time)) terminal_date from CLW_XC_ST_CHECK_RECORD_T where vehicle_vin = ? and terminal_time < trunc(?) and longitude != 'FFFF' and latitude != 'FFFF' and longitude is not null and Latitude is not null order by terminal_date desc) where rownum = 1";
			return (Date) this.jdbcTemplate.queryForObject(sql, new Object[]
			{ vin, date }, Date.class);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<VehicleTrack> getTerminalRecordDetail(String vin, Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String partition = sdf.format(date);

		String sql = "select vehicle_vin vin, longitude,latitude, Mileage,terminal_time terminalTime,direction from CLWXC.CLW_YW_TERMINAL_RECORD_T partition(terminal_record_" + partition + ")" + " where Mileage is not null and vehicle_vin = ? and longitude is not null and latitude is not null and longitude != 'FFFF' and latitude != 'FFFF' order by terminalTime";
		return this.jdbcTemplate.query(sql, new Object[]
		{ vin }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleTrack.class));
	}

	@SuppressWarnings("unchecked")
	public List<VehicleTrack> getTerminalRecordDetail(String vin, Date date, int type, int upOrDownHour)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String partition = sdf.format(date);
		String sql = "";
		if (type == 0)
		{
			sql = "select vehicle_vin vin, longitude,latitude, mileage,terminal_time terminalTime,direction from CLWXC.CLW_YW_TERMINAL_RECORD_T partition(terminal_record_" + partition + ")" + " where Mileage is not null and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) < ? and longitude is not null and latitude is not null and longitude != 'FFFF' and latitude != 'FFFF' order by terminalTime";
		}
		else
		{
			sql = "select vehicle_vin vin, longitude,latitude, mileage,terminal_time terminalTime,direction from CLWXC.CLW_YW_TERMINAL_RECORD_T partition(terminal_record_" + partition + ")" + " where Mileage is not null and vehicle_vin = ? and to_number(to_char(terminal_time,'hh24')) >= ? and longitude is not null and latitude is not null and longitude != 'FFFF' and latitude != 'FFFF' order by terminalTime";
		}

		return this.jdbcTemplate.query(sql, new Object[]
		{ vin, upOrDownHour }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleTrack.class));
	}

	@SuppressWarnings("unchecked")
	public List<VehicleTripStation> getVehicleTripStation(String vin)
	{
		String sql = "select  t.trip_id tripId,t.type,v.vehicle_vin vin, s.site_id siteId, S.SITE_LONGITUDE siteLongitude, s.site_latitude siteLatitude,S.SITE_NAME siteName from CLW_XC_VSS_SITE_T ts inner join CLW_XC_TRIP_T t on ts.TRIP_ID = t.TRIP_ID " + " inner join CLW_XC_SITE_T s on s.SITE_ID = ts.SITE_ID inner join clw_cl_base_info_t v on V.VEHICLE_VIN = t.vehicle_vin" + " where t.valid_flag = 0 and v.valid_flag = '0' and s.valid_flag = 0 and v.vehicle_vin = ?";

		return this.jdbcTemplate.query(sql, new Object[]
		{ vin }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleTripStation.class));
	}

	@SuppressWarnings("unchecked")
	public List<Trip> getTripByVehicleVin(String vin)
	{
		String sql = "select trip_id tripId, type, vehicle_vin vin from CLW_XC_TRIP_T where valid_flag = 0 and vehicle_vin = ?";

		return this.jdbcTemplate.query(sql, new Object[]
		{ vin }, ParameterizedBeanPropertyRowMapper.newInstance(Trip.class));
	}

	@SuppressWarnings("unchecked")
	public List<Trip> getTripNotInOrbitByVehicleVin(String vin)
	{
		String sql = "select trip_id tripId, type, vehicle_vin vin from CLW_XC_TRIP_T where valid_flag = 0 and vehicle_vin = ?";

		return this.jdbcTemplate.query(sql, new Object[]
		{ vin }, ParameterizedBeanPropertyRowMapper.newInstance(Trip.class));
	}

	@SuppressWarnings("unchecked")
	public List<VehicleTripStation> getVehicleTripStationByTripId(int tripId)
	{
		String sql = "select  t.trip_id tripId,t.type,v.vehicle_vin vin, s.site_id siteId, S.SITE_LONGITUDE siteLongitude, s.site_latitude siteLatitude,S.SITE_NAME siteName, ts.plan_in_time planInTime,ts.plan_out_time planOutTime from CLW_XC_VSS_SITE_T ts inner join CLW_XC_TRIP_T t on ts.TRIP_ID = t.TRIP_ID " + " inner join CLW_XC_SITE_T s on s.SITE_ID = ts.SITE_ID inner join clw_cl_base_info_t v on V.VEHICLE_VIN = t.vehicle_vin" + " where t.valid_flag = 0 and v.valid_flag = '0' and s.valid_flag = 0 and t.trip_id = ? order by planInTime";

		return this.jdbcTemplate.query(sql, new Object[]
		{ tripId }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleTripStation.class));
	}

	public Date getLastTerminalRecordDate(String vin, Date date)
	{
		String sql = "select terminal_date from (select distinct(trunc(terminal_time)) terminal_date from CLWXC.CLW_YW_TERMINAL_RECORD_T where vehicle_vin = ? and terminal_time < trunc(?) order by terminal_date desc) where rownum = 1";

		try
		{
			return (Date) this.jdbcTemplate.queryForObject(sql, new Object[]
			{ vin, date }, Date.class);
		}
		catch (Exception e)
		{
			return null;
		}
	}

	public void insertTripOrbit(VehicleTrack vt)
	{
		String sql = "insert into CLW_JZ_TRIPORBIT_T (TRIP_ID,TYPE,VEHICLE_VIN,LONGITUDE,LATITUDE,MILEAGE,ORBIT_TIME,CREATE_TIME,IS_TRIPORBIT,DIRECTION, IS_STATION) values (?,?,?,?,?,?,?,?,?,?,?)";
		this.jdbcTemplate.update(sql, new Object[]
		{ vt.getTripId(), vt.getType(), vt.getVin(), vt.getLongitude(), vt.getLatitude(), vt.getMileage(), vt.getTerminalTime(), new Date(), vt.getIsTripOrbit(), vt.getDirection(), vt.getIsStation() });
	}

	public void deleteTripOrbitByTripId(int tripId)
	{
		String sql = "delete CLW_JZ_TRIPORBIT_T where TRIP_ID = ?";

		this.jdbcTemplate.update(sql, new Object[]
		{ tripId });
	}

	@SuppressWarnings("unchecked")
	public List<VehicleTrack> getTripOrbitByTripId(int tripId)
	{
		String sql = "select TRIP_ID tripId,TYPE,VEHICLE_VIN vin,LONGITUDE,LATITUDE,MILEAGE,ORBIT_TIME terminalTime,IS_TRIPORBIT isTripOrbit,direction, is_station isStation from CLW_JZ_TRIPORBIT_T where TRIP_ID = ?";
		return this.jdbcTemplate.query(sql, new Object[]
		{ tripId }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleTrack.class));
	}

	public int getTripOrbitCountByTripId(int tripId)
	{
		String sql = "select count(1) from CLW_JZ_TRIPORBIT_T where TRIP_ID = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ tripId });
	}

	@SuppressWarnings("unchecked")
	public List<Station> getStationByTripId(int tripId)
	{
		try
		{
			String sql = "select s.site_id siteId, s.SITE_LONGITUDE LONGITUDE, S.SITE_LATITUDE LATITUDE, s.CONTROL_STATION direction from CLW_XC_VSS_SITE_T v inner join CLW_XC_SITE_T s on s.site_id = v.site_id where trip_id = ? and S.VALID_FLAG = 0";
			return this.jdbcTemplate.query(sql, new Object[]
			{ tripId }, ParameterizedBeanPropertyRowMapper.newInstance(Station.class));
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StationGenAlgorithmParam> getStationGenAlgorithmParamByEnterprise(String enterpriseId)
	{
		try
		{
			String sql = "select param_name paramName, param_desc paramDesc, param_value paramValue, enterprise_id enterpriseId, organization_id organizationId, Vehicle_Vin vin from CLW_JZ_SITE_CALC_PARAM_T where enterprise_id = ? ";
			return this.jdbcTemplate.query(sql, new Object[]
			{ enterpriseId }, ParameterizedBeanPropertyRowMapper.newInstance(StationGenAlgorithmParam.class));
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StationGenAlgorithmParam> getStationGenAlgorithmParamByVin(String vin)
	{
		try
		{
			String sql = "select param_name paramName, param_desc paramDesc, param_value paramValue, enterprise_id enterpriseId, organization_id organizationId, Vehicle_Vin vin from CLW_JZ_SITE_CALC_PARAM_T where Vehicle_Vin = ? ";
			return this.jdbcTemplate.query(sql, new Object[]
			{ vin }, ParameterizedBeanPropertyRowMapper.newInstance(StationGenAlgorithmParam.class));
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StationGenAlgorithmParam> getStationGenAlgorithmParamByOrganization(String organizationId)
	{
		try
		{
			String sql = "select param_name paramName, param_desc paramDesc, param_value paramValue, enterprise_id enterpriseId, organization_id organizationId, Vehicle_Vin vin from CLW_JZ_SITE_CALC_PARAM_T where organization_id = ? ";
			return this.jdbcTemplate.query(sql, new Object[]
			{ organizationId }, ParameterizedBeanPropertyRowMapper.newInstance(StationGenAlgorithmParam.class));
		}
		catch (Exception e)
		{
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<StationGenAlgorithmParam> getStationGenAlgorithmParamByDefault()
	{
		try
		{
			String sql = "select param_name paramName, param_desc paramDesc, param_value paramValue, enterprise_id enterpriseId, organization_id organizationId, Vehicle_Vin vin from CLW_JZ_SITE_CALC_PARAM_T where enterprise_id = '00000000-0000-0000-0000-000000000000' ";
			return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StationGenAlgorithmParam.class));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public StationGenAlgorithmThreshold getStationGenAlgorithmThreshold(String vin)
	{
		StationGenAlgorithmThreshold threshold = null;

		try
		{
			List<StationGenAlgorithmParam> params = null;

			params = getStationGenAlgorithmParamByVin(vin); //获取阈值

			if (params == null || params.size() == 0)
			{
				Vehicle v = getVehicle(vin);

				String organizationId = v.getOrganizationid();

				params = getStationGenAlgorithmParamByOrganization(organizationId);

				if (params == null || params.size() == 0)
				{
					String enterpriseId = v.getEnterpriseId();

					params = getStationGenAlgorithmParamByEnterprise(enterpriseId);

					if (params == null || params.size() == 0)
					{
						params = getStationGenAlgorithmParamByDefault();
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
	
	public void insertStationStudent(String vin, int routeId, int tripId, int stationId, int studentId)
	{
		String sql = "insert into CLW_XC_VSS_T (VEHICLE_VIN, TRIP_ID, ROUTE_ID,SITE_ID,STUDENT_ID, VSS_STATE, MODIFY_TIME) values (?,?,?,?,?,?,?)";
		this.jdbcTemplate.update(sql, new Object[]
		{ vin, tripId, routeId, stationId, studentId, 0, new Date() });
	}

	public int getStationStudentTripSiteCount(int tripId, int stationId)
	{
		String sql = "select count(1) from CLW_XC_VSS_T where TRIP_ID = ? and SITE_ID = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ tripId, stationId });
	}

	public void insertStationStudentToBackup(int stationId, int studentId)
	{
		String sql = "insert into CLW_JZ_STU_SITE_T (stu_id,site_id,create_time) values (?,?,?)";
		this.jdbcTemplate.update(sql, new Object[]
		{ studentId, stationId, new Date() });
	}

	public void deleteStationStudentFromBackup(int stationId)
	{
		String sql = "delete CLW_JZ_STU_SITE_T where site_id = ?";
		this.jdbcTemplate.update(sql, new Object[]
		{ stationId });
	}

	public int getRouteIdByTripId(int tripId)
	{
		String sql = "select ROUTE_ID from CLW_XC_TRIP_T where TRIP_ID = ? ";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ tripId });
	}
	
	public List<VehicleTrack> getStationVinRoute(String route_id)
	{
		String sql = "select t.longitude,t.latitude,t.standard_time,t.mileage from clw_tqc_ygb.CLW_TQC_EMP_PRELINE_T  t where t.route_id = ?";
		return this.jdbcTemplate.query(sql, new Object[]
		{ route_id }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleTrack.class));
	}
	
	//add by liuja 从新的轨迹表中获取距离站点在taget米的所有点
	public List<NoticeBean> getLineByTagetMile(String route_id,String site_id,float taget_value)
	{
		
		String sql = " select t.route_id,t.site_id,t.longitude,t.latitude from CLW_TQC_VEHICLE_NOTE_BASE_T t " +
				" where t.route_id = ? " +
				" and t.site_id = ? " +
				" and t.mileage <= ? order by t.point_id asc  " ;

		return this.jdbcTemplate.query(sql, new Object[]{ route_id,site_id,taget_value }, ParameterizedBeanPropertyRowMapper.newInstance(NoticeBean.class));
	}
	
	//add by liuja 从新的轨迹表中获取距离站点在taget秒的所有点
	public List<NoticeBean> getLineByTagetTime(String route_id,String site_id,int taget_value)
	{
		
		String sql = " select t.route_id,t.site_id,t.longitude,t.latitude from CLW_TQC_VEHICLE_NOTE_BASE_T t " +
				" where t.route_id = ? " +
				" and t.site_id = ? " +
				" and t.standard_time <= ? order by t.point_id asc  " ;

		return this.jdbcTemplate.query(sql, new Object[]{ route_id,site_id,taget_value }, ParameterizedBeanPropertyRowMapper.newInstance(NoticeBean.class));
	}
	

	// 陈卫峰添加start
	public int getJZSiteID()
	{
		String sql = "select SEQ_CLW_JZ_SITE_CALC_T_ID.NEXTVAL from dual ";
		return jdbcTemplate.queryForInt(sql);
	}

	public int getJZRouteID()
	{
		String sql = "select SEQ_CLW_JZ_TRIP_T_ID.NEXTVAL from dual ";
		return jdbcTemplate.queryForInt(sql);
	}

	public int getXCSiteID()
	{
		String sql = "select SEQ_XC_SITE_T.NEXTVAL from dual ";
		return jdbcTemplate.queryForInt(sql);
	}

	public int getXCRouteID()
	{
		String sql = "select SEQ_XC_ROUTE_T.NEXTVAL from dual ";
		return jdbcTemplate.queryForInt(sql);
	}

	public int getXCTripID()
	{
		String sql = "select SEQ_XC_TRIP_T.NEXTVAL from dual ";
		return jdbcTemplate.queryForInt(sql);
	}

	public int getJZRelationID()
	{
		String sql = "select SEQ_CLW_JZ_RELATION_T_ID.NEXTVAL from dual ";
		return jdbcTemplate.queryForInt(sql);
	}


	public int deleteJzStuSite(String vin)
	{
		String sql = "delete from CLW_JZ_STU_SITE_T where site_id in (select site_id from CLW_JZ_SITE_VEHICLE_T where VEHICLE_VIN = ?)";
		return this.jdbcTemplate.update(sql, new Object[]
		{ vin });

	}


	public int deleteJzRouteCalc(String vin)
	{
		String sql = "delete from CLW_JZ_ROUTE_CALC_T where route_id in (select route_id from CLW_JZ_TRIP_T where VEHICLE_VIN = ?)";
		return this.jdbcTemplate.update(sql, new Object[]
		{ vin });
	}


	public int deleteJzSiteCalc(String vin)
	{
		String sql = "delete from CLW_JZ_SITE_CALC_T where site_id in (select site_id from CLW_JZ_SITE_VEHICLE_T where VEHICLE_VIN = ?)";
		return this.jdbcTemplate.update(sql, new Object[]
		{ vin });
	}


	public int deleteJzSiteVehicle(String vin)
	{
		String sql = "delete from CLW_JZ_SITE_VEHICLE_T where vehicle_vin = ?";
		return this.jdbcTemplate.update(sql, new Object[]
		{ vin });
	}


	public int deleteJzTrip(String vin)
	{
		String sql = "delete from CLW_JZ_TRIP_T where vehicle_vin = ?";
		return this.jdbcTemplate.update(sql, new Object[]
		{ vin });
	}


	public int getSiteListCount(String vin)
	{
		String sql = "select count(1) count from CLW_XC_SITE_T s inner join CLW_XC_VSS_SITE_T ts on s.site_id = ts.site_id  where ts.VEHICLE_VIN = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ vin });
	}


	public int getRouteListCount(String vin)
	{
		String sql = "select count(1) count from CLW_XC_ROUTE_T r inner join CLW_XC_TRIP_T t on r.route_id = t.route_id where t.VEHICLE_VIN = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ vin });
	}


	public int getRouteSiteListCount(String vin)
	{
		String sql = "select count(1) count from CLW_XC_ROUTESITE_T rs inner join CLW_XC_TRIP_T t on rs.route_id = t.route_id where t.VEHICLE_VIN = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ vin });
	}


	public int getTripListCount(String vin)
	{
		String sql = "select count(1) count  from CLW_XC_TRIP_T where VEHICLE_VIN = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ vin });
	}


	public int getVssSiteListCount(String vin)
	{
		String sql = "select count(1) count from CLW_XC_VSS_SITE_T where VEHICLE_VIN = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ vin });
	}


	public int getVssListCount(String vin)
	{
		String sql = "select count(1) count from CLW_XC_VSS_T where VEHICLE_VIN = ?";
		return this.jdbcTemplate.queryForInt(sql, new Object[]
		{ vin });
	}

	// 陈卫峰添加end

	public void ExecuteSql(String sql)
	{
		this.jdbcTemplate.update(sql);
	}

	@SuppressWarnings("unchecked")

	public List<AlgorithmVehicle> getVehiclesForAlgorithm()
	{
		try
		{
			String sql = "select vehicle_vin vin, update_strategy strategy from CLW_JZ_ALGORITHM_VEHICLE_T where is_completed = 0";
			return this.jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(AlgorithmVehicle.class));
		}
		catch (Exception e)
		{
			return null;
		}
	}


	public void UpdateCalculationBegin(String vin, Date beginDate)
	{
		String sql = "update CLW_JZ_ALGORITHM_VEHICLE_T set previous_begin_time = ?, modify_time = ? where vehicle_vin = ?";
		this.jdbcTemplate.update(sql, new Object[]
		{ beginDate, beginDate, vin });
	}


	public void UpdateCalculationEnd(String vin, Date endDate, int isCompleted)
	{
		String sql = "update CLW_JZ_ALGORITHM_VEHICLE_T set previous_end_time = ?, is_completed = ?, modify_time = ? where vehicle_vin = ?";
		this.jdbcTemplate.update(sql, new Object[]
		{ endDate, isCompleted, endDate, vin });
	}
}
