package com.neusoft.SchoolBus.vncs.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.SchoolBus.vncs.domain.Driver;
import com.neusoft.SchoolBus.vncs.domain.Sichen;
import com.neusoft.SchoolBus.vncs.domain.Site;
import com.neusoft.SchoolBus.vncs.domain.StudentTrip;
import com.neusoft.SchoolBus.vncs.domain.Trip;
import com.neusoft.SchoolBus.vncs.thread.QuartzThread;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class QuartzTripDAO extends AbstractDaoManager {
    
	private static Logger log = LoggerFactory.getLogger(QuartzThread.class);
	//获得该车辆VIN的所有有效的行程信息 ,如果想控制行程文件的下发条件，例如：只下发上班文件、厂内通勤、下班文件
	@SuppressWarnings("unchecked")
	public List<Trip> getTripBaseInfo(String vin,String date) throws Exception {
	 /*String sql = "SELECT tte.TRIP_ID,tte.SEND_ORDER,tte.send_condition,tte.send_time,cxrt.route_id,cxrt.route_name" 
			    + " FROM TQC_TRIP_EXECUTE tte,CLW_XC_ROUTE_T cxrt" 
			    + " WHERE tte.VEHICLE_VIN = '"+ vin + "' " 
			    + " AND tte.EXE_DATE=TO_DATE('"+date+"','YYYY-MM-DD') "
			    + " AND tte.route_id=cxrt.route_id " 
			    + " AND tte.VALID_FLAG='0' ";	*/
	 String sql = " select * from ( "         
			   +" select tte.trip_id,tte.vehicle_vin,tte.send_order,tte.send_condition,tte.send_time,route.route_name "
			   +"   from TQC_TRIP_EXECUTE tte ,clw_xc_route_t route " 
			   +"  where tte.vehicle_vin = '"+ vin + "' "
			   +"    and tte.exe_date = TO_DATE('"+date+"','YYYY-MM-DD') "
			   +"    and route.route_id = tte.route_id "
			   +"    and route.route_class = 0 " 
			   +"    and tte.valid_flag='0' "  
			   +" order by tte.send_condition desc,tte.send_time,tte.trip_id) "
			   +" UNION ALL "
			   +" select * from ( "
			   +" select tte.trip_id,tte.vehicle_vin,tte.send_order,tte.send_condition,tte.send_time,route.route_name " 
			   +"   from TQC_TRIP_EXECUTE tte ,clw_xc_route_t route " 
			   +"  where tte.vehicle_vin = '"+ vin + "' "
			   +"    and tte.exe_date = TO_DATE('"+date+"','YYYY-MM-DD') "
			   +"    and route.route_id = tte.route_id "
			   +"    and route.route_class = 2 " 
			   +"    and tte.valid_flag='0' "  
			   +" order by tte.send_condition desc,tte.send_time,tte.trip_id) "
			   +" UNION ALL "
			   +" select * from ( "
			   +" select tte.trip_id,tte.vehicle_vin,tte.send_order,tte.send_condition,tte.send_time,route.route_name "
			   +"   from TQC_TRIP_EXECUTE tte ,clw_xc_route_t route " 
			   +"  where tte.vehicle_vin = '"+ vin + "' "
			   +"    and tte.exe_date = TO_DATE('"+date+"','YYYY-MM-DD') "
			   +"    and route.route_id = tte.route_id "
			   +"    and route.route_class = 1 "
 			   +"    and tte.valid_flag='0' "  
			   +" order by tte.send_condition desc,tte.send_time,tte.trip_id)";
		log.info("------【定时任务】获得该车辆VIN的所有有效的行程信息------->>:"+sql);
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Trip.class));
	}
	//2013-7-19侯俊虎增加，上班行程
	@SuppressWarnings("unchecked")
	public List<Trip> getTripBaseInfoMourning(String vin,String date) throws Exception {
		String sql = "SELECT t.TRIP_ID,t.SEND_ORDER from TQC_TRIP_EXECUTE t,CLW_XC_ROUTE_T r WHERE t.VEHICLE_VIN = '"
				+ vin + "' AND t.EXE_DATE=TO_DATE('"+date+"','YYYY-MM-DD') AND t.ROUTE_ID = r.ROUTE_ID AND r.ROUTE_CLASS='0' AND t.VALID_FLAG='0' ";		
		log.info("------【定时任务】获得 上班行程 该车辆VIN的所有有效的行程信息------->>:"+sql);		
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Trip.class));
	}
	//2013-7-19侯俊虎增加，下班行程
	@SuppressWarnings("unchecked")
	public List<Trip> getTripBaseInfoAfternoon(String vin,String date) throws Exception {
		String sql = "SELECT t.TRIP_ID,t.SEND_ORDER from TQC_TRIP_EXECUTE t,CLW_XC_ROUTE_T r WHERE t.VEHICLE_VIN = '"
				+ vin + "' AND t.EXE_DATE=TO_DATE('"+date+"','YYYY-MM-DD') AND t.ROUTE_ID = r.ROUTE_ID AND r.ROUTE_CLASS='1' AND t.VALID_FLAG='0' ";
		log.info("------【定时任务】获得 下班行程 该车辆VIN的所有有效的行程信息------->>:"+sql);	
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Trip.class));
	}

	//根据线路行程获得该车辆的线路信息
	@SuppressWarnings("unchecked")
	public List<Trip> getRouteBaseInfo(String tripid) throws Exception {
	  String sql = "SELECT CXTT.VEHICLE_VIN, TO_CHAR(CXTT.OPERATE_TIME, 'YYYYMMDDHH24MISS') OPERATE_TIME,"
				+ "  CXTT.ROUTE_ID,CXRT.ROUTE_NAME,CXTT.TRIP_ID,CXTT.TYPE,CXTT.SEND_TIME as START_TIME,"
				+ "  '' END_TIME, (SELECT COUNT(1) AS SITECOUNT  FROM CLW_XC_ROUTESITE_T T WHERE T.ROUTE_ID= CXTT.ROUTE_ID) AS SITECOUNT ,"
				+ "  (SELECT COUNT(1) AS DRIVERCOUNT FROM CLW_XC_VEHDRIVER_T T WHERE t.VEHICLE_VIN=CXTT.VEHICLE_VIN) AS DRIVERCOUNT,"				
				+ "  0 AS SICHENCOUNT,0 STUDENTCOUNT,CXTT.SEND_CONDITION,CXTT.SEND_TIME,CXTT.SEND_ORDER,CXTT.VALID_FLAG,CXTT.VALID_DAYS"
				+ " FROM TQC_TRIP_EXECUTE CXTT,CLW_XC_ROUTE_T CXRT "
				+ "WHERE CXTT.VALID_FLAG = '0' AND CXTT.ROUTE_ID = CXRT.ROUTE_ID "
				+ " AND  CXTT.TRIP_ID ='"+ tripid + "'  and cxtt.exe_date = trunc(sysdate,'dd')" ;
	    log.info("------根据线路行程获得该车辆的线路信息------->>:"+sql);	
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Trip.class));
	}
     //根据线路行程获得站点信息
	@SuppressWarnings("unchecked")
	public List<Site> getSiteInfo(String tripid) throws Exception {
	String sql = "SELECT CXSS.SITE_ID,CXSS.SITE_NAME,CXSS.SICHEN_ADDR,CXSS.SITE_LONGITUDE,CXSS.SITE_LATITUDE,'' PLAN_IN_TIME,'' PLAN_OUT_TIME,nvl(CXSS.set_in,'100') set_in,nvl(CXSS.set_out,'80') set_out,nvl(CXSS.set_perstation,'1') set_perstation,CXSS.CUSTOM_VOICE_CONTENT "				
				+ " FROM TQC_TRIP_EXECUTE CXTT,(SELECT CXRS.ROUTE_ID,CXST.SITE_ID,CXST.SITE_NAME,CXST.SICHEN_ADDR,CXST.SITE_LONGITUDE,CXST.SITE_LATITUDE,CXRS.RS_ORDER,CXRS.set_in,CXRS.set_out,CXRS.set_perstation,CXRS.CUSTOM_VOICE_CONTENT"
				+ " FROM CLW_XC_SITE_T CXST, CLW_XC_ROUTESITE_T CXRS  WHERE CXST.SITE_ID = CXRS.SITE_ID) CXSS "
				+ " WHERE CXTT.ROUTE_ID = CXSS.ROUTE_ID AND CXTT.TRIP_ID ='"+ tripid + "' and cxtt.exe_date = trunc(sysdate,'dd')" 
				+ " ORDER BY CXSS.RS_ORDER";
	    log.info("------根据线路行程获得站点信息------->>:"+sql);	
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Site.class));
	}
	
	//根据线路行程获得驾驶员信息
	@SuppressWarnings("unchecked")
	public List<Driver> getDriverInfo(String tripid) throws Exception {
		String sql = "SELECT CYDT.DRIVER_ID,CYDT.DRIVER_CARD_ID,CYDT.DRIVER_NAME,CYDT.DRIVER_TEL "			
				+ " FROM CLW_YW_DRIVER_T CYDT,TQC_TRIP_EXECUTE TTE "
				+ "WHERE TTE.DRIVER_ID=CYDT.DRIVER_ID "
				+ "AND TTE.TRIP_ID = '" + tripid + "' "
				+ "AND TTE.EXE_DATE = trunc(sysdate,'dd') ";
		log.info("------根据线路行程获得驾驶员信息------->>:"+sql);	
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(Driver.class));
	}
	
    //更新执行表TQC_TRIP_EXECUTE,TQC_TRIP_EXECUTE_TEMP
	public void updateCrc(String tripId,String crc) {
		//更新TQC_TRIP_EXECUTE表
		String sqlExe="UPDATE TQC_TRIP_EXECUTE tte SET tte.CRC='"+crc+"' WHERE tte.TRIP_ID='"+tripId+"'";
		sqlExe+=" and tte.exe_date = trunc(sysdate,'DD')";
		jdbcTemplate.update(sqlExe);
		
		//更新TQC_TRIP_EXECUTE_TEMP表
		String sqlExeTemp="UPDATE TQC_TRIP_EXECUTE_TEMP ttet SET ttet.CRC='"+crc+"' WHERE ttet.TRIP_ID='"+tripId+"'";
		sqlExeTemp+=" and ttet.exe_date = trunc(sysdate,'DD')";
		jdbcTemplate.update(sqlExeTemp);		
	}
	//查询该VIN号在当天是否存在
	public int gettrip(String vin,String date) throws Exception{
		String sql = "select count(1) from TQC_TRIP_EXECUTE t where t.vehicle_vin = '"+ vin +"' AND t.EXE_DATE=TO_DATE('"+date+"','YYYY-MM-DD') AND t.valid_flag='0' ";
		return jdbcTemplate.queryForInt(sql);
	}
	//-------------------------------------------------------------------------------------
     
	//根据行程获得学生信息
	@SuppressWarnings("unchecked")
	public List<StudentTrip> getStudentInfo(String tripid) throws Exception {
		String sql = "SELECT INFODATA.STU_NAME STU_NAME, "
				+ "INFODATA.STU_ID, " + "INFODATA.STU_CARD_ID STU_CARD_ID, "
				+ "INFODATA.STU_SCHOOL STU_SCHOOL, "
				+ "INFODATA.STU_CLASS STU_CLASS, "
				+ "INFODATA.TEACHER_TEL TEACHER_TEL, "
				+ "INFODATA.RELATIVE_TEL RELATIVE_TEL, "
				+ "INFODATA.PHOTONAME PHOTONAME, "
				+ "INFODATA.GETONBUS GETONBUS, "
				+ "INFODATA.GETDOWNBUS GETDOWNBUS, "
				+ "PHOTODATA.STU_PHOTO STU_PHOTO "
				+ "FROM (SELECT MAX(CXST.STU_NAME) STU_NAME, "
				+ "CXST.STU_ID, " + "MAX(CXST.STU_CARD_ID) STU_CARD_ID, "
				+ "MAX(CXST.STU_SCHOOL) STU_SCHOOL, "
				+ "MAX(CXST.STU_CLASS) STU_CLASS, "
				+ "MAX(CXST.TEACHER_TEL) TEACHER_TEL, "
				+ "MAX(CXST.RELATIVE_TEL) RELATIVE_TEL, " + "MAX(CASE "
				+ "WHEN CXST.PHOTO_NAME IS NULL THEN " + "NULL " + "ELSE "
				+ "CXST.STU_CARD_ID || '_' || CXST.PHOTO_NAME "
				+ "END) PHOTONAME, " + "WM_CONCAT(CASE "
				+ "WHEN VSS_STATE = '0' THEN " + "CXVT.SITE_ID "
				+ "END) GETONBUS, " + "WM_CONCAT(CASE "
				+ "WHEN VSS_STATE = '1' THEN " + " CXVT.SITE_ID "
				+ "END) GETDOWNBUS "
				+ "FROM CLW_XC_VSS_T CXVT, CLW_XC_STUDENT_T CXST "
				+ "WHERE CXVT.STUDENT_ID = CXST.STU_ID "
				+ "AND CXVT.TRIP_ID = '" + tripid + "' "
				+ " GROUP BY CXST.STU_ID) INFODATA, "
				+ "CLW_XC_STUDENT_T PHOTODATA "
				+ "WHERE INFODATA.STU_ID = PHOTODATA.STU_ID ";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper
				.newInstance(StudentTrip.class));
	}

	
   //根据行程获得司乘信息
	@SuppressWarnings("unchecked")
	public List<Sichen> getSichenInfo(String tripid) throws Exception {
		String sql = "SELECT " + "CXST.SICHEN_ID ,"
				+ "CXST.SICHEN_CARD_ID SICHEN_CARD_ID ,"
				+ "CXST.SICHEN_NAME SICHEN_NAME " + "FROM "
				+ "CLW_XC_SICHEN_T CXST , " + "CLW_XC_VEHSICHEN_T CXVT "
				+ "WHERE " + "CXST.SICHEN_ID = CXVT.SICHEN_ID "
				+ "AND CXVT.TRIP_ID = '" + tripid + "'";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper
				.newInstance(Sichen.class));
	}
	
	
    //获得车辆信息
	@SuppressWarnings("unchecked")
	public List<String> getVehicleList() throws Exception{
		String sql = "select t.vehicle_vin from clw_cl_base_info_t t,CLW_JC_ENTERPRISE_T et " +
				     "where  et.enterprise_id =t.enterprise_id and et.valid_flag='0' and t.valid_flag='0'";
		return jdbcTemplate.queryForList(sql, String.class);
	}
	
	public String getenidList(String vin) throws Exception {
		
			String sql = "select distinct vt.enterprise_model from clw_cl_base_info_t t,clw_jc_enterprise_t vt "
					+ "where t.enterprise_id=vt.enterprise_id and t.valid_flag='0' and vt.valid_flag='0' "
					+ "and t.vehicle_vin = ? ";
			return (String) jdbcTemplate.queryForObject(sql, new String[] { vin },String.class);

	}
	
	
	@SuppressWarnings("unchecked")
	public List<Driver> getDriverModelInfo(String vin) throws Exception {
		String sql = "SELECT DISTINCT CYDT.DRIVER_ID,CYDT.DRIVER_CARD_ID DRIVER_CARD_ID," +
				"CYDT.DRIVER_NAME DRIVER_NAME FROM CLW_YW_DRIVER_T CYDT " +
				"WHERE CYDT.ENTERPRISE_ID = (SELECT BT.ENTERPRISE_ID  FROM CLW_CL_BASE_INFO_T BT " +
				"WHERE BT.VEHICLE_VIN ='"+ vin+"' AND BT.VALID_FLAG='0') and CYDT.VALID_FLAG='0'";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper
				.newInstance(Driver.class));
	}

	@SuppressWarnings("unchecked")
	public List<Sichen> getSichenMModelInfo(String vin) throws Exception {
		String sql = "    SELECT CXST.SICHEN_ID,CXST.SICHEN_CARD_ID SICHEN_CARD_ID, " +
				"CXST.SICHEN_NAME SICHEN_NAME " +
				"FROM CLW_XC_SICHEN_T CXST " +
				"WHERE CXST.ENTERPRISE_ID = " +
				"(SELECT BT.ENTERPRISE_ID  FROM CLW_CL_BASE_INFO_T BT " +
				"WHERE BT.VEHICLE_VIN ='"+ vin+"' AND BT.VALID_FLAG='0') and CXST.VALID_FLAG='0'";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper
				.newInstance(Sichen.class));
	}

	public String getEnterPriseId(String vin) {
		String sql = "select t.enterprise_id from clw_cl_base_info_t t where t.vehicle_vin=? and t.valid_flag='0'";
		return (String) jdbcTemplate.queryForObject(sql, new String[] { vin },String.class);
	}


	public String getoperateTime(String vin) {
		String sql=" SELECT TO_CHAR(MAX(CXTT.OPERATE_TIME),'YYYYMMDDHH24MISS') " +
				"OPERATE_TIME FROM CLW_XC_VSS_T CXVT,CLW_XC_TRIP_T CXTT " +
				"WHERE CXVT.VEHICLE_VIN = ? AND CXVT.TRIP_ID=CXTT.TRIP_ID GROUP BY CXVT.VEHICLE_VIN";
		return (String) jdbcTemplate.queryForObject(sql, new String[] { vin },String.class);
	}
}
