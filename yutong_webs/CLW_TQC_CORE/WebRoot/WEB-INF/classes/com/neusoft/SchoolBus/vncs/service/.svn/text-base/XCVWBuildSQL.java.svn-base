package com.neusoft.SchoolBus.vncs.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.SchoolBus.vncs.dao.IXcSmsDAO;
import com.neusoft.SchoolBus.vncs.domain.RouteSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsVTBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.domain.XcvsseBean;
import com.neusoft.SchoolBus.vncs.manage.SendxcmsmCommandManager;
import com.neusoft.SchoolBus.vncs.service.util.XCUtil;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.cachemanager.RidingPlanCacheManager;
import com.neusoft.clw.vncs.dao.ITerminalDAO;
import com.neusoft.clw.vncs.dao.impl.MsgCfgDAO;
import com.neusoft.clw.vncs.dao.impl.RidingPlanDAO;
import com.neusoft.clw.vncs.domain.RidingPlanBean;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.tqcpt.service.SearchGisAreaByCode;

public class XCVWBuildSQL {

	private static Logger log = LoggerFactory.getLogger(XCVWBuildSQL.class);

	@SuppressWarnings("unused")
	private IXcSmsDAO XcSmsDAO = null;
	@SuppressWarnings("unused")
	private ITerminalDAO terminalDAO = null;
	@SuppressWarnings("unused")
	private RidingPlanDAO ridingPlanDAO = null;

	public static final String num3 = "3";

	public static final XCVWBuildSQL buildSql = new XCVWBuildSQL();

	private XCVWBuildSQL() {
		XcSmsDAO = (IXcSmsDAO) SpringBootStrap.getInstance().getBean(
				"sendXcSmsDAO");

		terminalDAO = (ITerminalDAO) SpringBootStrap.getInstance().getBean(
				"terminalDAO");
		ridingPlanDAO = (RidingPlanDAO) SpringBootStrap.getInstance().getBean(
				"ridingPlanDAO");
	}

	public static XCVWBuildSQL getInstance() {
		return buildSql;
	}

	/**
	 * 拼装终端版本更新语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		// CREATE_TIME 目前存储为String类型
		if (urt.getTer_jilu_ver() != null && !urt.getTer_jilu_ver().equals("")) {
			sql.append(" TER_JILU_VER='" + urt.getTer_jilu_ver().trim() + "'");
		}

		if (urt.getTer_xianshi_ver() != null
				&& !urt.getTer_xianshi_ver().equals("")) {
			sql.append(",TER_XIANSHI_VER=" + "'"
					+ urt.getTer_xianshi_ver().trim() + "'");
		}

		if (urt.getTer_dvr_ver() != null && !urt.getTer_dvr_ver().equals("")) {
			sql.append(",TER_DVR_VER=" + "'" + urt.getTer_dvr_ver().trim()
					+ "'");
		}

		if (urt.getTer_shepin_ver() != null
				&& !urt.getTer_shepin_ver().equals("")) {
			sql.append(",TER_SHEPIN_VER=" + "'"
					+ urt.getTer_shepin_ver().trim() + "'");
		}

		if (urt.getTer_qita_ver() != null && !urt.getTer_qita_ver().equals("")) {
			sql.append(",TER_QITA_VER=" + "'" + urt.getTer_qita_ver().trim()
					+ "'");
		}
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.info("<XCBuildSQL>解析上行终端版本信息,更新终端信息表:" + sql.toString());
		return sql.toString();
	}
	
	/**
	 * 通勤车告警数据插入
	 * @param urt
	 * @return
	 */
	public String buildTqcAlarmSql(Up_InfoContent urt){
		StringBuffer sql = new StringBuffer();
//		sql.append("Merge into TQC_ALARM_RECORDS TAR USING DUAL ON ( TAR.alarm_id = '" + urt.getTqc_alarm_id() + "' )");
//		sql.append(" WHEN MATCHED THEN UPDATE ");
//		if(urt.getTqc_alarm_type() != null && !urt.getTqc_alarm_type().equals("")){
//			sql.append(" SET ALARM_TYPE = '" + urt.getTqc_alarm_type() + "',");
//		}
//		if(urt.getTqc_alarm_route_id() != null && !urt.getTqc_alarm_route_id().equals("")){
//			sql.append(" ROUTE_ID = '" + urt.getTqc_alarm_route_id() + "',");
//		}
//		if(urt.getTerminalId() != null && !urt.getTerminalId().equals("")){
//			sql.append(" VEHICLE_VIN = '" + urt.getTerminalId() + "',");
//		}
//		if(urt.getTqc_alarm_psngrs_real_cnt() != null && !urt.getTqc_alarm_psngrs_real_cnt().equals("")){
//			sql.append(" REAL_CNT = '" + urt.getTqc_alarm_psngrs_real_cnt() + "',");
//		}
//		if(urt.getTqc_alarm_psngrs_standard_cnt() != null && !urt.getTqc_alarm_psngrs_standard_cnt().equals("")){
//			sql.append(" STANDARD_CNT = '" + urt.getTqc_alarm_psngrs_standard_cnt() + "',");
//		}
//		if(urt.getTqc_alarm_site_latitude() != null && !urt.getTqc_alarm_site_latitude().equals("")){
//			sql.append(" LATITUDE = '" + urt.getTqc_alarm_site_latitude() + "',");
//		}
//		if(urt.getTqc_alarm_site_longitude() != null && !urt.getTqc_alarm_site_longitude().equals("")){
//			sql.append(" LONGITUDE = '" + urt.getTqc_alarm_site_longitude() + "',");
//		}
//		if(urt.getTqc_alarm_travel_id() != null && !urt.getTqc_alarm_travel_id().equals("")){
//			sql.append(" trip_id = '" + urt.getTqc_alarm_travel_id() + "',");
//		}
//		sql.append(" ALARM_DATE = to_date('" + urt.getTqc_alarm_time() + "','yymmddhh24miss')");
//		
//		sql.append(" WHEN NOT MATCHED THEN INSERT ");
		sql.append(" INSERT into TQC_ALARM_RECORDS tar ");
		sql.append(" (TAR.alarm_id, TAR.ALARM_TYPE, TAR.VEHICLE_VIN, TAR.VEHICLE_LN");
		sql.append(" , TAR.REAL_CNT, TAR.STANDARD_CNT, TAR.LATITUDE, TAR.LONGITUDE");
		sql.append(" , tar.route_id, TAR.trip_id, TAR.ALARM_DATE, tar.operate_flag,tar.zonename,tar.driver_id)");
		sql.append(" select ");
		
		if(urt.getTqc_alarm_id() != null && !urt.getTqc_alarm_id().equals("")){
			sql.append("'" + urt.getTqc_alarm_id() + "', ");
		}else{
			sql.append( "'' , ");
		}
		if(urt.getTqc_alarm_type() != null && !urt.getTqc_alarm_type().equals("")){
			sql.append("'" + urt.getTqc_alarm_type() + "',");
		}else{
			sql.append( "'' , ");
		}
		if(urt.getTerminalId() != null && !urt.getTerminalId().equals("")){
			sql.append("'" + urt.getTerminalId() + "',");
		}else{
			sql.append("'' , ");
		}
		sql.append("c.vehicle_ln ,");
		if(urt.getTqc_alarm_psngrs_real_cnt() != null && !urt.getTqc_alarm_psngrs_real_cnt().equals("")){
			sql.append("'" + Integer.parseInt(urt.getTqc_alarm_psngrs_real_cnt(),16) + "',");
		}else{
			sql.append("'' , ");
		}
		if(urt.getTqc_alarm_psngrs_standard_cnt() != null && !urt.getTqc_alarm_psngrs_standard_cnt().equals("")){
			sql.append("'" + Integer.parseInt(urt.getTqc_alarm_psngrs_standard_cnt(),16) + "',");
		}else{
			sql.append("'', ");
		}
		if(urt.getTqc_alarm_site_latitude() != null && !urt.getTqc_alarm_site_latitude().equals("")){
			sql.append("'" + urt.getTqc_alarm_site_latitude() + "',");
		}else{
			sql.append("'', ");
		}
		if(urt.getTqc_alarm_site_longitude() != null && !urt.getTqc_alarm_site_longitude().equals("")){
			sql.append("'" + urt.getTqc_alarm_site_longitude() + "',");
		}else{
			sql.append("'', ");
		}
		if(urt.getTqc_alarm_route_id() != null && !urt.getTqc_alarm_route_id().equals("")){
			sql.append(Integer.parseInt(urt.getTqc_alarm_route_id(),16) + ",");
		}else{
			sql.append("'', ");
		}
		if(urt.getTqc_alarm_travel_id() != null && !urt.getTqc_alarm_travel_id().equals("")){
			sql.append(Integer.parseInt(urt.getTqc_alarm_travel_id(),16) + ",");
		}else{
			sql.append("'', ");
		}
		if(urt.getTqc_alarm_time() != null && !urt.getTqc_alarm_time().equals("")){
			sql.append("to_date('" + urt.getTqc_alarm_time() + "','yymmddhh24miss')" + ",");
		}else{
			sql.append("'', ");
		}
		sql.append("'0',");
		sql.append("'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getTqc_alarm_site_longitude(),urt.getTqc_alarm_site_latitude()) + "', ");		
		sql.append("  nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append("from clw_cl_base_info_t c, ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  CLW_JC_TERMINAL_T tre ");
		sql.append(" where tre.vehicle_vin = '" + urt.getTerminalId() + "' ) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append("  from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getTerminalId() + "' ");
		sql.append(" and v.driver_id = d.driver_id)veh_d ");
		sql.append("where c.vehicle_vin = '" + urt.getTerminalId() + "'");
		sql.append(" and c.valid_flag = '0'  ");
		log.info("<XCBuildSQL>更新或添加通勤车专用告警表:" + sql.toString());
		return sql.toString();
	}	
	/**
	 * 通勤车告警数据插入（迟到判断）
	 * @param urt
	 * @return
	 */
	public String buildTqcLateAlarmSql(Up_InfoContent urt,String laterConfig){
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT into TQC_ALARM_RECORDS tar ");
		sql.append(" (TAR.alarm_id, TAR.ALARM_TYPE, TAR.VEHICLE_VIN, TAR.VEHICLE_LN");
		sql.append(" , TAR.REAL_CNT, TAR.STANDARD_CNT, TAR.LATITUDE, TAR.LONGITUDE, tar.route_id, ");
		sql.append(" TAR.trip_id, TAR.TERMINAL_TRIPID,TAR.ALARM_DATE, tar.operate_flag, tar.zonename,tar.later_config,tar.driver_id) ");
		sql.append(" select ");
		
		if(urt.getTqc_alarm_id() != null && !urt.getTqc_alarm_id().equals("")){
			sql.append("'" + urt.getTqc_alarm_id() + "', ");
		}else{
			sql.append( "'' , ");
		}
		sql.append("'4',");
		if(urt.getTerminalId() != null && !urt.getTerminalId().equals("")){
			sql.append("'" + urt.getTerminalId() + "',");
		}else{
			sql.append("'' , ");
		}
		sql.append("c.vehicle_ln ,");
		sql.append("'0' , ");
		sql.append("'0', ");
		if(urt.getLatitude() != null && !urt.getLatitude().equals("")){
			sql.append("'" + urt.getLatitude() + "',");
		}else{
			sql.append("'', ");
		}
		if(urt.getLongitude() != null && !urt.getLongitude().equals("")){
			sql.append("'" + urt.getLongitude() + "',");
		}else{
			sql.append("'', ");
		}
		if(urt.getRoute_id() != null && !urt.getRoute_id().equals("")){
			sql.append(Integer.parseInt(urt.getRoute_id()) + ",");
		}else{
			sql.append("'', ");
		}
		if(urt.getTrip_id() != null && !urt.getTrip_id().equals("")){
			sql.append(Integer.parseInt(urt.getTrip_id()) + ",");
		}else{
			sql.append("'', ");
		}
		if(urt.getTerminal_tripId() != null && !urt.getTerminal_tripId().equals("")){
			sql.append(Integer.parseInt(urt.getTerminal_tripId()) + ",");
		}else{
			sql.append("'', ");
		}		
		if(urt.getIn_time() != null && !urt.getIn_time().equals("")){
			sql.append("to_date('" + urt.getIn_time() + "','yymmddhh24miss')" + ",");
		}else{
			sql.append("'', ");
		}
		sql.append("'0',");
		sql.append("'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "', ");
		sql.append("'" + laterConfig + "',");
		sql.append("  nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append("from clw_cl_base_info_t c, ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  CLW_JC_TERMINAL_T tre ");
		sql.append(" where tre.vehicle_vin = '" + urt.getTerminalId() + "'  ) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append("  from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getTerminalId() + "' ");
		sql.append(" and v.driver_id = d.driver_id)veh_d ");
		sql.append("  where c.vehicle_vin = '" + urt.getTerminalId() + "'");
		sql.append(" and c.valid_flag = '0'  ");
		log.info("<XCBuildSQL>更新或添加通勤车迟到告警表:" + sql.toString());
		return sql.toString();
	}	
	/**
	 * 通勤车告警数据插入（非时发车判断）
	 * @param urt
	 * @return
	 */
	public String buildTqcDelayAlarmSql(Up_InfoContent urt, String alarmType){
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT into TQC_ALARM_RECORDS tar ");
		sql.append(" (TAR.alarm_id, TAR.ALARM_TYPE, TAR.VEHICLE_VIN, TAR.VEHICLE_LN");
		sql.append(" , TAR.REAL_CNT, TAR.STANDARD_CNT, TAR.LATITUDE, TAR.LONGITUDE, tar.route_id, ");
		sql.append(" TAR.trip_id, TAR.TERMINAL_TRIPID,TAR.ALARM_DATE, tar.operate_flag,tar.zonename, tar.driver_id) "); 
		sql.append(" select ");
		
		if(urt.getTqc_alarm_id() != null && !urt.getTqc_alarm_id().equals("")){
			sql.append("'" + urt.getTqc_alarm_id() + "', ");
		}else{
			sql.append( "'' , ");
		}
		sql.append("'"+alarmType + "',");
		if(urt.getTerminalId() != null && !urt.getTerminalId().equals("")){
			sql.append("'" + urt.getTerminalId() + "',");
		}else{
			sql.append("'' , ");
		}
		sql.append("c.vehicle_ln ,");
		sql.append("'0' , ");
		sql.append("'0', ");
		if(urt.getLatitude() != null && !urt.getLatitude().equals("")){
			sql.append("'" + urt.getLatitude() + "',");
		}else{
			sql.append("'', ");
		}
		if(urt.getLongitude() != null && !urt.getLongitude().equals("")){
			sql.append("'" + urt.getLongitude() + "',");
		}else{
			sql.append("'', ");
		}
		if(urt.getRoute_id() != null && !urt.getRoute_id().equals("")){
			sql.append(Integer.parseInt(urt.getRoute_id()) + ",");
		}else{
			sql.append("'', ");
		}
		if(urt.getTrip_id() != null && !urt.getTrip_id().equals("")){
			sql.append(Integer.parseInt(urt.getTrip_id()) + ",");
		}else{
			sql.append("'', ");
		}
		if(urt.getTerminal_tripId() != null && !urt.getTerminal_tripId().equals("")){
			sql.append(Integer.parseInt(urt.getTerminal_tripId()) + ",");
		}else{
			sql.append("'', ");
		}	
		if(urt.getOut_time() != null && !urt.getOut_time().equals("")){
			sql.append("to_date('" + urt.getOut_time() + "','yymmddhh24miss')" + ",");
		}else{
			sql.append("'', ");
		}
		sql.append("'0',");
		sql.append("'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "', ");
		sql.append("  nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append("from clw_cl_base_info_t c, ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  CLW_JC_TERMINAL_T tre ");
		sql.append(" where tre.vehicle_vin = '" + urt.getTerminalId() + "' ) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append("  from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getTerminalId() + "' ");
		sql.append(" and v.driver_id = d.driver_id) veh_d ");
		sql.append("  where c.vehicle_vin = '" + urt.getTerminalId() + "'");
		sql.append(" and c.valid_flag = '0'  ");
		log.debug("<XCBuildSQL>更新或添加通勤车专用告警表:" + sql.toString());
		return sql.toString();
	}
	/**
	 * 拼装学生刷卡添加语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertShuaKaSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into CLW_XC_ST_CHECK_RECORD_T("
						+ "ID,STU_ID,SITE_ID,ROUTE_ID,VEHICLE_VIN,PICI_ID,LATITUDE,"
						+ "LONGITUDE,SITE_FLAG,VSS_FLAG,ALARM_TYPE_ID,ST_DOWM_NUM,ST_UP_NUM,"
						+ "ST_NUM,TERMINAL_TIME,CREATE_TIME,OPERATE_FLAG,"
						+ "PLAN_UP_NUM,PLAN_DOWN_NUM,SICHEN_ID,DRIVER_ID,MESG_FLAG,SMS_FAIL_INFO,TRIP_ID) values(");
		sql.append("'" + urt.getPid() + "'");// 刷卡流水表主键
		if (urt.getStu_id() != null && !urt.getStu_id().equals("")) {
			sql.append(",'" + urt.getStu_id() + "'");// 学生编号
		}
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append(",'" + urt.getSite_id() + "'");// 站点编号
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append(",'" + urt.getRoute_id() + "'");// 线路编号
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append(",'" + urt.getTerminalId() + "'");// 车辆VIN
		}
		sql.append(",'" + urt.getShuaka_id() + "'");
		sql.append(",'" + urt.getLatitude() + "'");// 纬度
		sql.append(",'" + urt.getLongitude() + "'");// 经度
		sql.append(",'" + urt.getSite_flag() + "'");// 上下行状态
		sql.append(",'" + urt.getVss_flag() + "'");// 乘车状态
		if (urt.getAlarm_id() != null && !urt.getAlarm_id().equals("")) {
			sql.append(",'" + urt.getAlarm_id() + "'");// 告警类型ID
		} else {
			sql.append(",''");
		}

		sql.append(",'" + urt.getSt_down_num() + "'");// 下车人数
		sql.append(",'" + urt.getSt_up_num() + "'");// 上车人数
		sql.append(",'" + urt.getSt_num() + "'");// 车内学生数
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");// 终端时间
		sql.append(",sysdate");// 创建时间
		sql.append(",'0'");// 告警处理标识位
		sql.append(",'" + urt.getSt_institute_up_num() + "'");// 计划上车人数
		sql.append(",'" + urt.getSt_institute_down_num() + "'");// 计划下车人数
		if (urt.getSicheng_id() != null && !urt.getSicheng_id().equals("")) {
			sql.append(",'" + urt.getSicheng_id() + "'");// 司乘编号
		} else {
			sql.append(",''");
		}
		if (urt.getDriver_id() != null && !urt.getDriver_id().equals("")) {
			sql.append(",'" + urt.getDriver_id() + "'");// 司机编号
		} else {
			sql.append(",''");
		}
		if (urt.getSms_state() != null && !urt.getSms_state().equals("")) {
			sql.append(",'" + urt.getSms_state() + "'");// 短信下发状态
		} else {
			sql.append(",'0'");
		}
		sql.append(",'" + urt.getSms_fail_info() + "'");// 短信失败原因
		sql.append(",'" + urt.getTrip_id() + "'");// 行程编号
		sql.append(")");
		log.info("<XCBuildSQL>学生刷卡记录数据insert sql:" + sql.toString());

		return sql.toString();
	}

	/**
	 * 拼装学生无线路刷卡添加语句
	 * 
	 * @param urt
	 * @return
	 */
//	public String buildInsertShuaKaNORouteSql(Up_InfoContent urt) {
//		StringBuffer sql = new StringBuffer();
//		sql.append("insert into CLW_XC_ST_CHECK_RECORD_T("
//				+ "ID,STU_CARD_ID,VEHICLE_VIN,LATITUDE,LONGITUDE,"
//				+ "VSS_FLAG,ST_NUM,TERMINAL_TIME,CREATE_TIME,"
//				+ "OPERATE_FLAG, ZONENAME, STU_CODE,stu_name,organization_id,PLAN_VIN,PLAN_STRIDE_FLAG,route_id,site_id) ");
//		sql.append(" select ");
//		sql.append("'" + urt.getShuaka_id() + "'");// 刷卡流水表主键
//		if (urt.getStu_id() != null && !urt.getStu_id().equals("")) {
//			sql.append(",'" + urt.getStu_id() + "'");// 学生编号
//		}
//		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
//			sql.append(",'" + urt.getTerminalId() + "'");// 车辆VIN
//		}
//		sql.append(",'" + urt.getLatitude() + "'");// 纬度
//		sql.append(",'" + urt.getLongitude() + "'");// 经度
//		sql.append(",'" + urt.getVss_flag() + "'");// 乘车状态
//		sql.append(",'" + urt.getSt_num() + "'");// 车内学生数
//		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");// 终端时间
//		sql.append(",sysdate");// 创建时间
//		sql.append(",'0'");// 告警处理标识位
//		sql.append(",'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "'");
//		sql.append(",t.stu_code");
//		sql.append(",t.stu_name");
//		sql.append(",t.organization_id");
//		//2013-11-26添加规划车辆、乘车状态字段值
//		String planVin ="";
//		String planState ="";
//		List<RidingPlanBean> rpList = RidingPlanCacheManager.getInstance().getValue("taRidingPlan");
//		if(rpList == null || rpList.size() == 0){
//			rpList = ridingPlanDAO.getBaseRidingPlanInfo();
//			RidingPlanCacheManager.getInstance().addRidingPlanIntoCache("taRidingPlan", rpList);
//		}
//		for(RidingPlanBean rp : rpList){
//			if(rp.getStu_card_id().equals(urt.getStu_id())){
//				planVin = rp.getVehicle_vin();
//				if(rp.getVehicle_vin().equals(urt.getTerminalId()))//如果刷卡车辆与规划车辆相同 则乘车状态为正常==1
//					planState = "1";
//				else
//					planState ="0";
//			    break;	
//			}
//		}
//		sql.append(",'"+planVin+"'");
//		sql.append(",'"+planState+"'");
//	
//		sql.append(",t.route_id");
//		sql.append(",t.site_id");
////		
//		sql.append(" from (select t.stu_code, t.stu_name, t.organization_id,m.route_id,m.site_id from CLW_XC_STUDENT_T t,CLW_JC_TERMINAL_T m where m.vehicle_vin = '" + urt.getTerminalId() + "'");
//		sql.append(" and t.stu_card_id = '" + urt.getStu_id() + "'");
//		sql.append(" and t.valid_flag = '0' union all select '' stu_code, '' stu_name, '' organization_id,m.route_id,m.site_id from CLW_JC_TERMINAL_T m  where m.vehicle_vin = '" + urt.getTerminalId() + "'");
//		sql.append(" and not exists (select 1 from CLW_XC_STUDENT_T t where t.stu_card_id = '" + urt.getStu_id() + "'");
//		sql.append(" and t.valid_flag = '0')) t ");
//
//				//"CLW_XC_STUDENT_T t where t.stu_card_id = '" + urt.getStu_id() + "'");
//		//sql.append(" and t.valid_flag = '0'");
//		log.info("<XCBuildSQL>V2.0无线路文件刷卡记录数据insert sql:" + sql.toString());
//
//		return sql.toString();
//	}

	/**
	 * 拼装学生无线路刷卡添加语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertShuaKaNORouteSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into CLW_XC_ST_CHECK_RECORD_T("
				+ "ID,STU_CARD_ID,VEHICLE_VIN,LATITUDE,LONGITUDE,"
				+ "VSS_FLAG,ST_NUM,TERMINAL_TIME,CREATE_TIME,"
				+ "OPERATE_FLAG, ZONENAME, STU_CODE,stu_name,organization_id,PLAN_VIN,PLAN_STRIDE_FLAG,route_id,site_id,driver_id ) ");
		sql.append(" select ");
		sql.append("'" + urt.getShuaka_id() + "'");// 刷卡流水表主键
		if (urt.getStu_id() != null && !urt.getStu_id().equals("")) {
			sql.append(",'" + urt.getStu_id() + "'");// 学生编号
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append(",'" + urt.getTerminalId() + "'");// 车辆VIN
		}
		sql.append(",'" + urt.getLatitude() + "'");// 纬度
		sql.append(",'" + urt.getLongitude() + "'");// 经度
		sql.append(",'" + urt.getVss_flag() + "'");// 乘车状态
		sql.append(",'" + urt.getSt_num() + "'");// 车内学生数
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");// 终端时间
		sql.append(",sysdate");// 创建时间
		sql.append(",'0'");// 告警处理标识位
		sql.append(",'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "'");
		//sql.append(",t.stu_code"); //学号设置为空，平台展示的时候关联表
		sql.append(",''");
		//sql.append(",t.stu_name");
		sql.append(",''");
		//sql.append(",t.organization_id"); //组织设置为空
		sql.append(",''");
		//2013-11-26添加规划车辆、乘车状态字段值
		String planVin ="";
		String planState ="";
		//这些是校车的东西，通勤车-泰安版员工刷卡记录需要 start
		List<RidingPlanBean> rpList = RidingPlanCacheManager.getInstance().getValue("taRidingPlan");
//		if(rpList == null || rpList.size() == 0){
//			rpList = ridingPlanDAO.getBaseRidingPlanInfo();
//			RidingPlanCacheManager.getInstance().addRidingPlanIntoCache("taRidingPlan", rpList);
//		}
		if(rpList != null && rpList.size() > 0){
			for(RidingPlanBean rp : rpList){
				if(rp.getStu_card_id().equals(urt.getStu_id())){
					planVin = rp.getVehicle_vin();
					if(rp.getVehicle_vin().equals(urt.getTerminalId()))//如果刷卡车辆与规划车辆相同 则乘车状态为正常==1
						planState = "1";
					else
						planState ="0";
				    break;	
				}
			}
		}
		//这些是校车的东西，通勤车-泰安版员工刷卡记录需要 end
		sql.append(",'"+planVin+"'");
		sql.append(",'"+planState+"'");
		
		sql.append(",t.route_id");
		sql.append(",t.site_id");
		
		sql.append(",nvl(t.driver_id,vd.driver_id)");
		
		sql.append(" from CLW_JC_TERMINAL_T t,clw_xc_vehdriver_t vd where t.vehicle_vin = '" + urt.getTerminalId() + "'");
		sql.append(" and vd.vehicle_vin = '" + urt.getTerminalId() + "'");
		sql.append(" and t.valid_flag = '0' ");
		log.info("<XCBuildSQL>V2.0无线路文件刷卡记录数据insert sql:" + sql.toString());

		return sql.toString();
	}
	
//	 private static final String SQL_INSERT = "INSERT INTO CLW_YW_BADDRIVING_T (BADDRIVING_ID,ROUTE_ID,VEHICLE_VIN,VEHICLE_ID,ENTERPRISE_ID,ORGANIZATION_ID,ALARM_DAY,BEGIN_TIME)     "
//         + "select sys_guid(),ROUTE_ID,VEHICLE_VIN,VEHICLE_ID,ENTERPRISE_ID,ORGANIZATION_ID ,to_date(?,'yyyy-mm-dd'),sysdate from CLW_CL_BASE_INFO_T   "
//         + "  where vehicle_vin=? and valid_flag = '0' and DEVICE_TYPE = '0'";
//    public int insertvin(String vin, String date) throws DataAccessException {
//        // System.out.println(selPagedSql);
//        int num = jdbcTemplate.update(SQL_INSERT, new Object[] { date, vin });
//        return num;
//    }

	/**
	 * 拼装学生刷卡时操作刷卡实时表语句
	 * 
	 * @param urt
	 * @return
	 */

	public String buildShiShiSqlNoRoute(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();

		sql.append("MERGE INTO CLW_XC_ST_CHECK_T CS USING DUAL"
				+ " ON (CS.STU_ID = " + urt.getStu_id() + ")");
		sql.append(" WHEN MATCHED THEN");
		sql.append(" UPDATE ");
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append("SET CS.VEHICLE_VIN  = '" + urt.getTerminalId() + "',");
		}
		sql.append("CS.LATITUDE   = '" + urt.getLatitude() + "',");
		sql.append("CS.LONGITUDE  = '" + urt.getLongitude() + "',");
		if (urt.getVss_flag() != null && !urt.getVss_flag().equals("")) {
			sql.append("CS.VSS_FLAG = '" + urt.getVss_flag() + "',");
		}
		sql.append("CS.ST_NUM  = '" + urt.getSt_num() + "',");
		sql.append("CS.TERMINAL_TIME    = to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss'),");
		sql.append("CS.CREATE_TIME=sysdate");
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT ");
		sql
				.append("(CS.STU_ID,CS.VEHICLE_VIN,CS.LATITUDE,CS.LONGITUDE,"
						+ "CS.VSS_FLAG,CS.ST_NUM,CS.TERMINAL_TIME,CS.CREATE_TIME"
						+ ")");
		sql.append("VALUES(");

		sql.append("'" + urt.getStu_id() + "',");
		sql.append("'" + urt.getTerminalId() + "',");
		sql.append("'" + urt.getLatitude() + "',");
		sql.append("'" + urt.getLongitude() + "',");
		if (urt.getVss_flag() != null && !urt.getVss_flag().equals("")) {
			sql.append("'" + urt.getVss_flag() + "',");
		} else {
			sql.append("'',");
		}
		sql.append("'" + urt.getSt_num() + "',");
		sql.append("to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss'),");
		sql.append("sysdate");
		sql.append(")");
		log.info("<XCBuildSQL>更新或添加学生刷卡实时表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装学生刷卡时操作刷卡实时表语句
	 * 
	 * @param urt
	 * @return
	 */

	public String buildShiShiSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();

		sql.append("MERGE INTO CLW_XC_ST_CHECK_T CS USING DUAL"
				+ " ON (CS.STU_ID = " + urt.getStu_id() + ")");
		sql.append(" WHEN MATCHED THEN");
		sql.append(" UPDATE");
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append(" SET CS.SITE_ID = '" + urt.getSite_id() + "',");
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append("CS.ROUTE_ID = '" + urt.getRoute_id() + "',");
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append("CS.VEHICLE_VIN  = '" + urt.getTerminalId() + "',");
		}
		sql.append("CS.LATITUDE   = '" + urt.getLatitude() + "',");
		sql.append("CS.LONGITUDE  = '" + urt.getLongitude() + "',");
		sql.append("CS.SITE_FLAG = '" + urt.getSite_flag() + "',");
		if (urt.getVss_flag() != null && !urt.getVss_flag().equals("")) {
			sql.append("CS.VSS_FLAG = '" + urt.getVss_flag() + "',");
		}
		if (urt.getAlarm_id() != null && !urt.getAlarm_id().equals("")) {
			sql.append("CS.ALARM_ID = '" + urt.getAlarm_id() + "',");
		}
		sql.append("CS.ST_DOWM_NUM  = '" + urt.getSt_down_num() + "',");
		sql.append("CS.ST_UP_NUM   = '" + urt.getSt_up_num() + "',");
		sql.append("CS.ST_NUM  = '" + urt.getSt_num() + "',");
		sql.append("CS.TERMINAL_TIME    = to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss'),");
		sql.append("CS.CREATE_TIME=sysdate,");
		sql.append("CS.PLAN_UP_NUM  = '" + urt.getSt_institute_up_num() + "',");
		sql.append("CS.PLAN_DOWN_NUM  = '" + urt.getSt_institute_down_num()
				+ "',");
		sql.append("CS.SICHEN_ID  = '" + urt.getSicheng_id() + "',");
		sql.append("CS.DRIVER_ID  = '" + urt.getDriver_id() + "',");
		sql.append("CS.TRIP_ID  = '" + urt.getTrip_id() + "',");
		if ("0".equals(urt.getVss_flag())) {
			sql.append("CS.UP_SITE_ID  = '" + urt.getSite_id() + "',");
			sql.append("CS.UP_SITE_TIME  = to_date(" + urt.getTerminal_time()
					+ ",'yymmddhh24miss')");
		}
		if ("1".equals(urt.getVss_flag())) {
			sql.append("CS.DOWN_SITE_ID  = '" + urt.getSite_id() + "',");
			sql.append("CS.DOWN_SITE_TIME  = to_date(" + urt.getTerminal_time()
					+ ",'yymmddhh24miss')");
		}
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT");
		sql
				.append("(CS.STU_ID,CS.SITE_ID,CS.ROUTE_ID,CS.VEHICLE_VIN,"
						+ "CS.LATITUDE,CS.LONGITUDE,CS.SITE_FLAG,"
						+ "CS.VSS_FLAG,CS.ALARM_ID,CS.ST_DOWM_NUM,"
						+ "CS.ST_UP_NUM,CS.ST_NUM,CS.TERMINAL_TIME,CS.CREATE_TIME,"
						+ "CS.PLAN_UP_NUM,CS.PLAN_DOWN_NUM,CS.SICHEN_ID,CS.DRIVER_ID,CS.TRIP_ID,"
						+ "CS.UP_SITE_ID,CS.UP_SITE_TIME,CS.DOWN_SITE_ID,CS.DOWN_SITE_TIME)");
		sql.append("VALUES(");

		sql.append("'" + urt.getStu_id() + "',");
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append("'" + urt.getSite_id() + "',");
		} else {
			sql.append("'',");
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append("'" + urt.getRoute_id() + "',");
		} else {
			sql.append("'',");
		}
		sql.append("'" + urt.getTerminalId() + "',");
		sql.append("'" + urt.getLatitude() + "',");
		sql.append("'" + urt.getLongitude() + "',");
		sql.append("'" + urt.getSite_flag() + "',");
		if (urt.getVss_flag() != null && !urt.getVss_flag().equals("")) {
			sql.append("'" + urt.getVss_flag() + "',");
		} else {
			sql.append("'',");
		}
		if (urt.getAlarm_id() != null && !urt.getAlarm_id().equals("")) {
			sql.append("'" + urt.getAlarm_id() + "',");
		} else {
			sql.append("'',");
		}
		sql.append("'" + urt.getSt_down_num() + "',");
		sql.append("'" + urt.getSt_up_num() + "',");
		sql.append("'" + urt.getSt_num() + "',");
		sql.append("to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss'),");
		sql.append("sysdate");
		sql.append(",'" + urt.getSt_institute_up_num() + "'");// 计划上车人数
		sql.append(",'" + urt.getSt_institute_down_num() + "'");// 计划下车人数
		sql.append(",'" + urt.getSicheng_id() + "'");// 司乘编号
		sql.append(",'" + urt.getDriver_id() + "'");// 司机编号
		sql.append(",'" + urt.getTrip_id() + "'");// 行程编号
		if ("0".equals(urt.getVss_flag())) {
			sql.append(",'" + urt.getSite_id() + "',");
			sql.append("to_date(" + urt.getTerminal_time()
					+ ",'yymmddhh24miss'),");
			sql.append("'',");
			sql.append("''");
		}
		if ("1".equals(urt.getVss_flag())) {
			sql.append(",'',");
			sql.append("'',");
			sql.append("'" + urt.getSite_id() + "',");
			sql.append("to_date(" + urt.getTerminal_time()
					+ ",'yymmddhh24miss')");
		}
		sql.append(")");
		log.info("<XCBuildSQL>更新或添加学生刷卡实时表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装学生上车未刷卡时操作刷卡实时表语句
	 * 
	 * @param urt
	 * @return
	 */

	public String UpNobuildShiShiSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();

		sql.append("MERGE INTO CLW_XC_ST_CHECK_T CS USING DUAL "
				+ "ON (CS.STU_ID = " + urt.getStu_id() + ")");
		sql.append("WHEN MATCHED THEN");
		sql.append(" UPDATE");
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append(" SET CS.SITE_ID = '" + urt.getSite_id() + "',");
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append("CS.ROUTE_ID = '" + urt.getRoute_id() + "',");
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append("CS.VEHICLE_VIN  = '" + urt.getTerminalId() + "',");
		}
		sql.append("CS.LATITUDE   = '" + urt.getLatitude() + "',");
		sql.append("CS.LONGITUDE  = '" + urt.getLongitude() + "',");
		sql.append("CS.SICHEN_ID   = '" + urt.getSicheng_id() + "',");
		sql.append("CS.DRIVER_ID  = '" + urt.getDriver_id() + "',");
		if (urt.getSite_flag() != null && !urt.getSite_flag().equals("")) {
			sql.append("CS.SITE_FLAG = '" + urt.getSite_flag() + "',");
		}
		sql.append("CS.VSS_FLAG = '2',");
		sql.append("CS.ALARM_ID = '79',");
		sql.append("CS.TERMINAL_TIME    = to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT");
		sql.append("(CS.STU_ID,CS.SITE_ID,CS.ROUTE_ID,CS.VEHICLE_VIN,"
				+ "CS.LATITUDE,CS.LONGITUDE," + "CS.VSS_FLAG,CS.ALARM_ID,"
				+ "CS.TERMINAL_TIME,CS.SICHEN_ID,CS.DRIVER_ID,CS.CREATE_TIME)");
		sql.append("VALUES(");

		sql.append("'" + urt.getStu_id() + "',");
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append("'" + urt.getSite_id() + "',");
		} else {
			sql.append(" '',");
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append("'" + urt.getRoute_id() + "',");// 线路编号
		} else {
			sql.append(" '',");
		}
		sql.append("'" + urt.getTerminalId() + "',");
		sql.append("'" + urt.getLatitude() + "',");
		sql.append("'" + urt.getLongitude() + "',");
		sql.append("'2',");

		sql.append("'79',");
		sql.append("to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss'),");
		sql.append("'" + urt.getSicheng_id() + "',");// 司乘编号
		sql.append("'" + urt.getDriver_id() + "',");// 司机编号
		sql.append("sysdate)");
		log.info("<XCBuildSQL>更新或添加学生刷卡实时表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装学生下车未刷卡时操作刷卡实时表语句
	 * 
	 * @param urt
	 * @return
	 */

	public String DownNobuildShiShiSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();

		sql.append("MERGE INTO CLW_XC_ST_CHECK_T CS USING DUAL "
				+ "ON (CS.STU_ID = " + urt.getStu_id() + ")");
		sql.append(" WHEN MATCHED THEN");
		sql.append(" UPDATE");
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append(" SET CS.SITE_ID = '" + urt.getSite_id() + "',");
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append("CS.ROUTE_ID = '" + urt.getRoute_id() + "',");
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append("CS.VEHICLE_VIN  = '" + urt.getTerminalId() + "',");
		}
		sql.append("CS.LATITUDE   = '" + urt.getLatitude() + "',");
		sql.append("CS.LONGITUDE  = '" + urt.getLongitude() + "',");
		sql.append("CS.SICHEN_ID   = '" + urt.getSicheng_id() + "',");
		sql.append("CS.DRIVER_ID  = '" + urt.getDriver_id() + "',");
		if (urt.getSite_flag() != null && !urt.getSite_flag().equals("")) {
			sql.append("CS.SITE_FLAG = '" + urt.getSite_flag() + "',");
		}
		sql.append("CS.VSS_FLAG = '2',");
		sql.append("CS.ALARM_ID = '80',");
		sql.append("CS.TERMINAL_TIME    = to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT");
		sql.append("(CS.STU_ID,CS.SITE_ID,CS.ROUTE_ID,CS.VEHICLE_VIN,"
				+ "CS.LATITUDE,CS.LONGITUDE," + "CS.VSS_FLAG,CS.ALARM_ID,"
				+ "CS.TERMINAL_TIME,CS.SICHEN_ID,CS.DRIVER_ID,CS.CREATE_TIME)");
		sql.append("VALUES(");

		sql.append("'" + urt.getStu_id() + "',");
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append("'" + urt.getSite_id() + "',");
		} else {
			sql.append(" '',");
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append("'" + urt.getRoute_id() + "',");// 线路编号
		} else {
			sql.append(" '',");
		}
		sql.append("'" + urt.getTerminalId() + "',");
		sql.append("'" + urt.getLatitude() + "',");
		sql.append("'" + urt.getLongitude() + "',");
		sql.append("'2',");

		sql.append("'80',");
		sql.append("to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss'),");
		sql.append("'" + urt.getSicheng_id() + "',");// 司乘编号
		sql.append("'" + urt.getDriver_id() + "',");// 司机编号
		sql.append("sysdate)");
		log.info("<XCBuildSQL>更新或添加学生刷卡实时表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装学生未刷卡上车未刷卡添加语句
	 * 
	 * @param urt
	 * @param pici
	 * @return
	 */
	public String buildInsertNotShuaKaUpSql(Up_InfoContent urt, String pici) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into CLW_XC_ST_CHECK_RECORD_T("
						+ "ID,STU_ID,SITE_ID,ROUTE_ID,VEHICLE_VIN,PICI_ID,LATITUDE,"
						+ "LONGITUDE,VSS_FLAG,ALARM_TYPE_ID,"
						+ "TERMINAL_TIME,CREATE_TIME,OPERATE_FLAG,DRIVER_ID,SICHEN_ID,TRIP_ID,INOUT_ID) values(");
		sql.append("'" + urt.getPid() + "'");// 刷卡流水表主键
		if (urt.getStu_id() != null && !urt.getStu_id().equals("")) {
			sql.append(",'" + urt.getStu_id() + "'");// 学生编号
		}
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append(",'" + urt.getSite_id() + "'");// 站点编号
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append(",'" + urt.getRoute_id() + "'");// 线路编号
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append(",'" + urt.getTerminalId() + "'");// 车辆VIN
		}
		sql.append(",'" + pici + "'");// 批次号
		sql.append(",'" + urt.getLatitude() + "'");// 纬度
		sql.append(",'" + urt.getLongitude() + "'");// 经度
		sql.append(",'2'");// 告警状态
		sql.append(",'" + urt.getAlarm_type_id() + "'");// 告警类型ID
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		sql.append(",sysdate");
		sql.append(",'0'");
		sql.append(",'" + urt.getDriver_id() + "'");// 司机编号
		sql.append(",'" + urt.getSicheng_id() + "'");// 司乘编号
		sql.append(",'" + urt.getTrip_id() + "'");// 司机编号
		sql.append(",'" + urt.getInout_id() + "'");// 司乘编号
		sql.append(")");
		log.info("<XCBuildSQL>学生上车未刷卡记录数据insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装学生下车未刷卡添加语句
	 * 
	 * @param urt
	 * @param pici
	 * @return
	 */
	public String buildInsertNotShuaKaDownSql(Up_InfoContent urt, String pici) {
		StringBuffer sql = new StringBuffer();

		sql
				.append("insert into CLW_XC_ST_CHECK_RECORD_T("
						+ "ID,STU_ID,SITE_ID,ROUTE_ID,VEHICLE_VIN,PICI_ID,LATITUDE,"
						+ "LONGITUDE,VSS_FLAG,ALARM_TYPE_ID,"
						+ "TERMINAL_TIME,CREATE_TIME,OPERATE_FLAG,MESG_FLAG,SMS_FAIL_INFO,DRIVER_ID,SICHEN_ID) values(");

		sql.append("'" + urt.getPid() + "'");// 刷卡流水表主键

		if (urt.getStu_id() != null && !urt.getStu_id().equals("")) {
			sql.append(",'" + urt.getStu_id() + "'");// 学生编号
		}
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append(",'" + urt.getSite_id() + "'");// 站点编号
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append(",'" + urt.getRoute_id() + "'");// 线路编号
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append(",'" + urt.getTerminalId() + "'");// 车辆VIN
		}
		sql.append(",'" + pici + "'");// 批次号
		sql.append(",'" + urt.getLatitude() + "'");// 纬度
		sql.append(",'" + urt.getLongitude() + "'");// 经度
		sql.append(",'2'");
		sql.append(",'80'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		sql.append(",sysdate");
		sql.append(",'0'");
		if (urt.getSms_state() != null && !urt.getSms_state().equals("")) {
			sql.append(",'" + urt.getSms_state() + "'");// 短信下发状态
		} else {
			sql.append(",'0'");
		}
		sql.append(",'" + urt.getSms_fail_info() + "'");// 短信失败原因
		sql.append(",'" + urt.getDriver_id() + "'");// 纬度
		sql.append(",'" + urt.getSicheng_id() + "'");// 经度
		sql.append(")");
		log.info("<XCBuildSQL>学生下车未刷卡记录数据insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装司乘刷卡添加语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertSISCCRDSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();

		sql.append("insert into CLW_XC_SISCCARD_T("
				+ "ID,OTHER_ID,VEHICLE_VIN,LATITUDE," + "LONGITUDE,SITE_FLAG,"
				+ "TERMINAL_TIME,CREATE_TIME) values(");
		if (urt.getSicheng_id() != null && !urt.getSicheng_id().equals("")) {
			sql.append("'" + urt.getSicheng_id() + "'");// 主键
		}
		if (urt.getOther_id() != null && !urt.getOther_id().equals("")) {
			sql.append(",'" + urt.getOther_id() + "'");// 司乘ID
		}

		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append(",'" + urt.getTerminalId() + "'");// 车辆VIN
		}
		sql.append(",'" + urt.getLatitude() + "'");// 纬度
		sql.append(",'" + urt.getLongitude() + "'");// 经度
		sql.append(",'" + urt.getSite_flag() + "'");// 状态：0-上学；1-放学
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");// 上报时间
		sql.append(",sysdate");// 接收时间
		sql.append(")");
		log.info("<XCBuildSQL>司机司乘刷卡记录数据insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装学生销假更新语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateXiaoJiaSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_XC_QXJIA_T set ");
		sql.append(" FLAG = '1'");
		sql.append(",MODIFY_TIME=" + "to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",MODIFIER= 'CLW_XC'");
		sql.append(" where STU_ID = '" + urt.getStu_id() + "'");
		log.info("<XCBuildSQL>解析学生销假信息,更新请销假信息表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 告警消息同时修改终端表
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalNumSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		// CREATE_TIME 目前存储为String类型
		sql.append(" OVERLOAD_FLAG = '" + urt.getAlrm_state() + "'");
		sql.append(",STU_NUM= '" + urt.getSt_num() + "'");
		sql.append("where vehicle_vin = '" + urt.getTerminalId() + "'");
		log.info("<XCBuildSQL>解析超载告警信息,更新终端信息表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装车辆进出站表sql语句
	 * 
	 * @param urt
	 * @return sql
	 */
	public String buildInsertInOutSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into CLW_XC_INOUTSITE_T("
						+ "INOUT_ID,SITE_ID,ROUTE_ID,"
						+ "VEHICLE_VIN,INOUT_FLAG,LATITUDE,LONGITUDE,"
						+ "TERMINAL_TIME,CREATE_TIME,SICHEN_ID,DRIVER_ID,"
						+ "SITE_UPDOWN,REALITY_IN_TIME,PLAN_IN_TIME,"
						+ "REALITY_OUT_TIME,PLAN_OUT_TIME,REALITY_UP_NUM,"
						+ "PLAN_UP_NUM,REALITY_DOWN_NUM,PLAN_DOWN_NUM,ST_NUM,"
						+ "TRIP_ID,TRIP_BEG_TIME,TRIP_END_TIME,NO_UP_NUM,NO_DOWN_NUM,QJIA_NUM,TERMINAL_TRIPID) values(");
		if (urt.getInout_id() != null && !urt.getInout_id().equals("")) {
			sql.append(" '" + urt.getInout_id() + "'");// 进出站ID
		}
		if (urt.getSite_id() != null && !urt.getSite_id().equals("")) {
			sql.append(", '" + urt.getSite_id() + "'");// 站点编号
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append(", '" + urt.getRoute_id() + "'");// 线路编号
		}
		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append(", '" + urt.getTerminalId() + "'");// 车辆VIN
		}
		sql.append(", '" + urt.getInout_state() + "'");// 进出站状态 0x00进站,0x01出站
		sql.append(", '" + urt.getLatitude() + "'");// 纬度
		sql.append(", '" + urt.getLongitude() + "'");// 经度		
		if(urt.getInout_state().equals("1")){
			sql.append(", to_date(20" + urt.getOut_time() + ",'yymmddhh24miss')");// 出站上报时间
		}else{
			sql.append(", to_date(20" + urt.getIn_time() + ",'yymmddhh24miss')");// 进站上报时间
		}
		sql.append(", sysdate");// 更新时间
		if (urt.getSicheng_id() != null && !urt.getSicheng_id().equals("")) {
			sql.append(", '" + urt.getSicheng_id() + "'");// 当班司机编号
		} else {
			sql.append(",''");
		}
		if (urt.getDriver_id() != null && !urt.getDriver_id().equals("")) {
			sql.append(", '" + urt.getDriver_id() + "'");// 当班司乘编号
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getSite_flag() + "'");
		sql.append(", to_date(20" + urt.getIn_time() + ",'yymmddhh24miss')");// 实际进站时间
		sql.append(", to_date(20" + urt.getPlan_in_time() + ",'yymmddhh24miss')");// 计划进站时间
		if (urt.getInout_state().equals("1")) {
			sql.append(", to_date(20" + urt.getOut_time() + ",'yymmddhh24miss')");// 实际出站时间
			sql.append(", to_date(20" + urt.getPlan_out_time()+ ",'yymmddhh24miss')");// 计划出站时间
			sql.append(", '" + urt.getSt_up_num() + "'");// 实际上车人数
			sql.append(", '" + urt.getSt_institute_up_num() + "'");// 计划上车人数
			sql.append(", '" + urt.getSt_down_num() + "'");// 实际下车人数
			sql.append(", '" + urt.getSt_institute_down_num() + "'");// 计划下车人数
			sql.append(", '" + urt.getSt_num() + "'");// 车内人数
		} else {
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
		}
		sql.append(", '" + urt.getTrip_id() + "'");// 行程编号
		sql
				.append(", to_date(20" + urt.getTrip_beg_time()+ ",'yymmddhh24miss')");// 行程开始时间
		sql
				.append(", to_date(20" + urt.getTrip_end_time()+ ",'yymmddhh24miss')");// 行程结束时间
		if (urt.getUp_num() != null && !urt.getUp_num().equals("")) {
			sql.append(", '" + urt.getUp_num() + "'");// 上车人数
		} else {
			sql.append(", '0'");// 上车人数
		}
		if (urt.getDown_num() != null && !urt.getDown_num().equals("")) {
			sql.append(", '" + urt.getDown_num() + "'");// 下车人数
		} else {
			sql.append(", '0'");// 下车人数
		}
		if (urt.getQjia_num() != null && !urt.getQjia_num().equals("")) {
			sql.append(", '" + urt.getQjia_num() + "'");// 请假人数
		} else {
			sql.append(", '0'");// 请假人数
		}
		//---------增加终端行程自编号时间    ningdh 20130606
		if(urt.getTerminal_tripId()!=null && !urt.getTerminal_tripId().equals("")){
			sql.append(", '" + urt.getTerminal_tripId() + "'"); 
		}
		
		sql.append(")");
		
		log.info("<XCBuildSQL>-------实际上车人数----->>:"+urt.getUp_num());
		log.info("<XCBuildSQL>-------车内人数-------->>:"+urt.getSt_num());
		
		log.info("<XCBuildSQL>车辆进出站记录数据insert sql:" + sql.toString());
		return sql.toString();
	}	
	/**
	 * 拼装车辆迟到告警sql语句
	 * 
	 * @param urt
	 * @return sql
	 */
	public String buildInsertLateSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into tqc_alarm_records("
						+ "alarm_ID,alarm_type,vehicle_vin,"
						+ "VEHICLE_ln,alarm_date,route_id,real_cnt,"
						+ "standard_cnt,longitude,latitude,trip_ID,"
						+ "TRIP_ID,operate_flag) select ");
		sql.append(" '" + UUID.randomUUID() + "'");// 告警ID
		sql.append(", '4'");// 迟到告警

		if (urt.getTerminalId() != null && !urt.getTerminalId().equals("")) {
			sql.append(", '" + urt.getTerminalId() + "'");// 车辆VIN
		}
		sql.append(", c.vehicle_ln ");
		sql.append(", to_date(" + urt.getPlan_in_time() + ",'yymmddhh24miss')");// 告警时间
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append(", '" + urt.getRoute_id() + "'");// 线路编号
		}
		sql.append(", '" + urt.getSt_num() + "'");// 车内人数
		sql.append(", '' ");//核载人数？？？
		sql.append(", '" + urt.getLatitude() + "'");// 纬度
		sql.append(", '" + urt.getLongitude() + "'");// 经度
		sql.append(", '" + urt.getTrip_id() + "'");// 行程编号
		sql.append(", '0' ");// 处理状态
		sql.append(" from clw_cl_base_info c where c.vehicle_vin = " + urt.getTerminalId());
		log.debug("<XCBuildSQL>车辆迟到告警数据插库 insert sql:" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public Map getCacheQuotas(String enterprise_id) {
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			if (Constant.getMemcachedClient().getObject(enterprise_id) != null
					&& !Constant.getMemcachedClient().getObject(enterprise_id)
							.equals("")) {
				return (Map) Constant.getMemcachedClient().getObject(
						enterprise_id);
			} else {
				return null;
			}
		} else {
			log.info("kkkkkkkkkkkk:"
					+ MsgCfgDAO.msgquotasMap.get(enterprise_id));
			return MsgCfgDAO.msgquotasMap.get(enterprise_id);
		}
	}

	public String isSuccessSendMsg(List<XcStuSmsVTBean> smslistbean,
			Up_InfoContent urt, String type,XcStudentBean sb) throws ParseException {
		String message = "";

		// 查询学生信息缓存信息
//		XcStudentBean sb = SendxcmsmCommandManager.getInstance()
//				.getStudentValue(Constant.STUDENT + urt.getStu_id());
		// 查询站点缓存信息
		//上报的站点信息
		XcSiteBean csb = SendxcmsmCommandManager.getInstance().getSiteValue(
				Constant.SITE + urt.getSite_id());

		// 查询站点缓存信息
		RouteSiteBean rsb = SendxcmsmCommandManager.getInstance()
				.getRouteSiteValue(urt.getRoute_id() + urt.getSite_id());

		// 查询学生订购站点信息
		XcSiteBean vsssb = null;
		XcvsseBean xsb = null;

		// if("02".equals(urt.getModelversion())){
		// xsb= SendxcmsmCommandManager.getInstance().getVssValueVt(
		// urt.getStu_id()+ urt.getRoute_id() + urt.getVss_flag() +
		// urt.getSite_flag());
		// }else{
		// xsb = SendxcmsmCommandManager.getInstance().getVssValue(
		// urt.getStu_id()+ urt.getVss_flag() + urt.getSite_flag());
		// }
		//实际订购的线路站点信息
		if(urt.getModelversion().equals("02")){
			xsb = SendxcmsmCommandManager.getInstance().getVssValue(XCUtil.getXcVssKey(urt));
			// 根据线路ID查询学生订购的站点
			if (xsb != null) {
				//实际订购的站点信息
				vsssb = SendxcmsmCommandManager.getInstance().getSiteValue(
						Constant.SITE + xsb.getSite_id());
			}
		}

		// 判断该告警是否需要下发短信
		if (smslistbean != null) {
			message = isSendMsg(urt, smslistbean, sb, csb, rsb, vsssb);
		}
		return message;
	}

	/**
	 * 判断短信下发类型
	 * 
	 * @param csb
	 * @param sb
	 * @param rsb
	 * @param vsssb
	 * 
	 * @param V
	 * @param vin
	 * @param alarmtype
	 * @return
	 * @throws ParseException
	 */
	private String isSendMsg(Up_InfoContent urt, List<XcStuSmsVTBean> vb,
			XcStudentBean sb, XcSiteBean csb, RouteSiteBean rsb,
			XcSiteBean vsssb) throws ParseException {
		String msg = null;
		String vsssite_name = null;
		String sitename = null;
		String Student_name = null;
		if (sb.getStu_name() != null && !"".equals(sb.getStu_name())) {
			Student_name = sb.getStu_name();// 学生姓名
		}
		String time = DateUtil.changeTime12To04Format(urt.getTerminal_time());// 时间
		if (csb != null && !"".equals(csb.getSite_name())
				&& csb.getSite_name() != null) {
			sitename = csb.getSite_name();// 终端上报站点名称
		} else {
			sitename = " ";
		}
		if (vsssb != null && !"".equals(vsssb.getSite_name())
				&& vsssb.getSite_name() != null) {
			vsssite_name = vsssb.getSite_name();// 订购关系中站点名
		} else {
			vsssite_name = " ";
		}
		if (rsb != null) {
			if (!"".equals(urt.getAlarm_id()) && urt.getAlarm_id() != null) {
				if (urt.getAlarm_id().equals("73")) {
					if ("0".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在 "
								+ sitename + "" + "站点乘坐校车前往学校，没有在规定站点"
								+ vsssite_name + "上车，请您确认原因！";
					}
					if ("1".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在"
								+ sitename + "" + "站点乘坐校车返家，没有在规定站点"
								+ vsssite_name + "上车，请您确认原因！";
					}
				}

				if (urt.getAlarm_id().equals("74")) {
					if ("0".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在 "
								+ sitename + "站点离开校车前往学校，没有在规定站点"
								+ vsssite_name + "下车，请您确认原因！";
					}
					if ("1".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在 "
								+ sitename + "站点离开校车返家，没有在规定站点" + vsssite_name
								+ "下车，请您确认原因！";
					}
				}
				// 上学时未在规定站点上车刷卡
				if (urt.getAlarm_id().equals("79")
						&& rsb.getSite_updown().equals("0")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的" + sitename
							+ "站点乘车前往学校，请您确认原因！";
				}
				// 放学时未在规定站点上车刷卡
				if (urt.getAlarm_id().equals("79")
						&& rsb.getSite_updown().equals("1")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的" + sitename
							+ "乘坐校车返家，请您确认原因！ ";
				}

				// 上学时未在规定站点下车刷卡
				if (urt.getAlarm_id().equals("80")
						&& rsb.getSite_updown().equals("0")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的" + sitename
							+ "站点刷卡离开校车前往学校，请您确认原因！";
				}
				// 放学时未在规定站点下车刷卡
				if (urt.getAlarm_id().equals("80")
						&& rsb.getSite_updown().equals("1")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的" + sitename
							+ "站点离开校车返家，请您确认原因！";
				}
			} else {
				if (!"".equals(urt.getSmstype()) && urt.getSmstype() != null) {
					if (urt.getSmstype().equals("00")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "" + "于" + time
								+ " 在" + sitename + "站点乘坐校车前往学校。";
					}
					// 放学上车打卡
					if (urt.getSmstype().equals("02")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + " 在 "
								+ sitename + "站点乘坐校车返家。";
					}
					// 上学下车打卡
					if (urt.getSmstype().equals("01")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "顺利抵达" + sitename + "站点。";
					}
					// 放学下车打卡
					if (urt.getSmstype().equals("03")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "顺利抵达 " + sitename + "站点。";
					}
				} else {
					if (urt.getSms_eventtype().equals("4")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "在"
								+ sitename + "打卡上车。";
					}
					if (urt.getSms_eventtype().equals("5")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "在"
								+ sitename + "打卡下车。";
					}
				}
			}

		} else {// 没报

			if (!"".equals(urt.getAlarm_id()) && urt.getAlarm_id() != null) {
				if (urt.getAlarm_id().equals("73")) {
					if ("0".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "乘坐校车前往学校，没有在规定站点" + vsssite_name
								+ "上车，请您确认原因！";
					}
					if ("1".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "乘坐校车返家，没有在规定站点" + vsssite_name
								+ "上车，请您确认原因！";
					}
				}

				if (urt.getAlarm_id().equals("74")) {
					if ("0".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "离开校车前往学校，没有在规定站点" + vsssite_name
								+ "下车，请您确认原因！";
					}
					if ("1".equals(urt.getSite_flag())) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "离开校车返家，没有在规定站点" + vsssite_name
								+ "下车，请您确认原因！";
					}
				}
				// 放学时未在规定站点上车刷卡
				if (urt.getAlarm_id().equals("79")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的" + sitename
							+ "乘坐校车返家，请您确认原因！ ";
				}
				// 放学时未在规定站点下车刷卡
				if (urt.getAlarm_id().equals("80")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的" + sitename
							+ "站点离开校车返家，请您确认原因！";
				}
			} else {
				if (!"".equals(urt.getSmstype()) && urt.getSmstype() != null) {
					if (urt.getSmstype().equals("00")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "乘坐校车前往学校。";
					}
					if (urt.getSmstype().equals("02")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "乘坐校车返家。";
					}
					if (urt.getSmstype().equals("01")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "顺利抵达学校。";
					}
					if (urt.getSmstype().equals("03")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "顺利到家。";
					}
				} else {
					if (urt.getSms_eventtype().equals("4")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "打卡上车。";
					}
					if (urt.getSms_eventtype().equals("5")) {
						msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
								+ "打卡下车。";
					}
				}
			}

		}
		return msg;
	}

	/**
	 * 短信流水表发送短信insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertSmsRecordSQL(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// UserBean ub = msgCfgDAO.getUserInfo(urt.getTerminalId());
		sql
				.append("insert into CLW_YW_SMS_RECORD_T(ID,VEHICLE_VIN,TEL,MSG,"
						+ "STATE,SEND_TAKE,SRC_ID,CREATE_TIME,"
						+ "ENTERPRISE_ID,ORGANIZATION_ID,STU_ID,"
						+ "EVENT_TYPE,FLAG,RELATIVE_TYPE,RELATIVE_NAME,PARENTS_FLAG,PICI_ID,SMS_NUM,SMS_FAIL_INFO) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + urt.getTerminalId() + "'");// 车辆VIN
		sql.append(",'" + urt.getTel() + "'");// 发送号码
		sql.append(",'" + urt.getMsg() + "'");// 信息内容
		sql.append(",'" + urt.getState() + "'");// 是否成功，0成功，1失败
		sql.append(",'0'");// 发送类型，0发送，1接收

		if (urt.getSrc_id() != null && !urt.getSrc_id().equals("")) {
			sql.append(",'" + urt.getSrc_id() + "'");// SP代码
		} else {
			sql.append(",''");
		}

		sql.append(",sysdate");
		sql.append(",'" + urt.getEnterprise_id() + "'");
		sql.append(",'" + urt.getOrganization_id() + "'");
		sql.append("," + urt.getStu_id());
		sql.append(",'" + isSendType(urt) + "'");
		sql.append(",'1'");
		sql.append(",'" + urt.getRelative_type() + "'");
		sql.append(",'" + urt.getRelative_name() + "'");
		if (urt.getParents_flag() != null && !"".equals(urt.getParents_flag())) {
			sql.append(",'" + urt.getParents_flag() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getPid() + "'");
		sql.append(",'" + urt.getSms_num() + "'");
		sql.append(",'" + urt.getSms_fail_info() + "'");
		sql.append(")");
		log.info("<BuildSQL>短信流水记录数据insert sql:" + sql.toString());
		return sql.toString();
	}

	public String buildUpdateUpTerminalDriverSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		log.info("线路编号是" + urt.getOther_id());
		sql.append("update CLW_JC_TERMINAL_T set ");
		// CREATE_TIME 目前存储为String类型
		sql.append(" DRIVER_ID = '" + urt.getOther_id() + "'");
		sql.append(" where vehicle_vin = '" + urt.getTerminalId() + "'");
		log.info("更新终端司机编号为：" + sql);
		log.info("<BuildSQL>驾驶员上车刷卡时更新终端信息表驾驶员编号:" + sql.toString());
		return sql.toString();
	}

	public String buildUpdateDownTerminalDriverSql(Up_InfoContent uhc) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		// CREATE_TIME 目前存储为String类型
		sql.append(" DRIVER_ID = ''");
		sql.append(" where vehicle_vin = '" + uhc.getTerminalId() + "'");
		log.info("<BuildSQL>驾驶员下车刷卡时更新终端信息表驾驶员编号:" + sql.toString());
		return sql.toString();
	}

	// public String buildUpdteInOutTerminalSql(Up_InfoContent uhc) {
	// StringBuffer sql = new StringBuffer();
	// sql.append("update CLW_JC_TERMINAL_T set ");
	// sql.append(" SITE_ID = '" + uhc.getSite_id() + "'");
	// sql.append(",ROUTE_ID = '" + uhc.getRoute_id() + "'");
	// sql.append(",INOUT_FLAG = '" + uhc.getInout_state() + "'");
	// sql.append(" where vehicle_vin = '" + uhc.getTerminalId() + "'");
	// log.info("<BuildSQL>进出站时更新终端表:" + sql.toString());
	// return sql.toString();
	// }

	public String buildUpdateShuaKaTerminalSql(Up_InfoContent uhc) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append(" STU_NUM = '" + uhc.getSt_num() + "'");
		sql.append(" where vehicle_vin = '" + uhc.getTerminalId() + "'");
		log.info("<BuildSQL>进出站时更新终端表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 判断短信下发类型
	 * 
	 * @param csb
	 * @param sb
	 * 
	 * @param V
	 * @param vin
	 * @param alarmtype
	 * @return
	 */
	private String isSendType(Up_InfoContent urt) {
		String msg = null;
		if (urt.getAlarm_id() == null || "".equals(urt.getAlarm_id())) {
			// 正常上车
			if (urt.getVss_flag().equals("0")) {
				msg = "4";
			}
			// 正常下车
			if (urt.getVss_flag().equals("1")) {
				msg = "5";
			}
		} else {
			// 未刷卡上车
			if (urt.getAlarm_id().equals("79")) {
				msg = "0";
			}
			// 未刷卡下车
			if (urt.getAlarm_id().equals("80")) {
				msg = "1";
			}
			// 未在规定站点上车
			if (urt.getAlarm_id().equals("73")) {
				msg = "2";
			}
			// 未在规定站点下车
			if (urt.getAlarm_id().equals("74")) {
				msg = "3";
			}

		}
		return msg;
	}

	public String buildInsertInGetRouteSql(Up_InfoContent uhc) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into CLW_XC_SITECOLLECTION_T(COLLECTION_ID,VEHICLE_VIN,SITE_LONGITUDE,SITE_LATITUDE,CREATE_TIME,VALID_FLAG) ");
		sql.append("values('" + IdCreater.getUUid() + "'");
		sql.append(",'" + uhc.getTerminalId() + "'");
		sql.append(",'" + uhc.getLongitude() + "'");
		sql.append(",'" + uhc.getLatitude() + "'");
		sql.append(",to_date('" + uhc.getTerminal_time()
				+ "','yymmddhh24miss')");
		sql.append(",'0'");
		sql.append(")");
		return sql.toString();
	}

	/**
	 * 发送短信后更新刷卡流水表
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public String buildUpdateShuaKaSMSSQL(Up_InfoContent uhc) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_XC_ST_CHECK_RECORD_T partition(ST_CHECK_RECORD_"
				+ Sdate(uhc.getTerminal_time()) + ") set ");
		sql.append(" MESG_FLAG = '" + uhc.getSms_state() + "'");
		if (uhc.getSms_fail_info() != null
				&& !"".equals(uhc.getSms_fail_info())) {
			sql.append(",SMS_FAIL_INFO = '" + uhc.getSms_fail_info() + "'");
		}
		sql.append(" where ID = '" + uhc.getPid() + "'");
		log.info("<BuildSQL>短信下发时更新学生刷卡流水表:" + sql.toString());
		return sql.toString();
	}

	public String Sdate(String sdate) throws DataAccessException {
		return "20" + sdate.substring(0, 2) + sdate.substring(2, 6);
	}

	/**
	 * 亿美短信发送失败提示
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public void smsFailMessage(int num, Up_InfoContent uhc) {
		if (num == 17) {
			uhc.setSms_fail_info("发送信息失败！");
		}
		if (num == 101) {
			uhc.setSms_fail_info("客户端网络故障！");
		}
		if (num == 303) {
			uhc.setSms_fail_info("短信网关连接超时！");
		}
		if (num == 305) {
			uhc.setSms_fail_info("服务器端返回错误，错误的返回值（返回值不是数字字符串）！");
		}
		if (num == 307) {
			uhc.setSms_fail_info("目标电话号码不符合规则，电话号码必须是以0、1开头！");
		}
		if (num == 997) {
			uhc.setSms_fail_info("平台返回找不到超时的短信，该信息是否成功无法确定！");
		}
		if (num == 998) {
			uhc.setSms_fail_info("由于客户端网络问题导致信息发送超时，该信息是否成功下发无法确定！");
		}
	}

	/**
	 * 行程文件更新成功
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public String buildTripReportSuccess(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update TQC_TRIP_EXECUTE  set ");
		// CREATE_TIME 目前存储为String类型
		//sql.append(" UPDATE_TIME= to_date('" + urt.getUpdateComplete_time()+ "','yymmddhh24miss')");
		sql.append(" UPDATE_TIME= SYSDATE ");
		sql.append(" where TRIP_ID =" + "'" + urt.getTrip_code() + "'");
		sql.append(" and exe_date = trunc(sysdate,'DD')");
		//sql.append(" and CRC =" + "'" + urt.getCRC() + "'");

		log.info("<XCBuildSQL>解析行程更新结果汇报,更新行程信息表:" + sql.toString());
		return sql.toString();

	}

	/**
	 * 行程文件更新成功
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public String buildTripReportSuccessTemp(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update TQC_TRIP_EXECUTE_TEMP set ");
		// CREATE_TIME 目前存储为String类型
		//sql.append(" UPDATE_TIME= to_date('" + urt.getUpdateComplete_time()+ "','yymmddhh24miss')");
		sql.append(" UPDATE_TIME= SYSDATE ");
		sql.append(" where TRIP_ID =" + "'" + urt.getTrip_code() + "'");
		sql.append(" and exe_date = trunc(sysdate,'DD')");
		//sql.append(" and CRC =" + "'" + urt.getCRC() + "'");

		log.info("<XCBuildSQL>解析行程更新结果汇报,更新行程信息表:" + sql.toString());
		return sql.toString();

	}

	/**
	 * 行程文件更新失败
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public String buildTripReportFail(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update TQC_TRIP_EXECUTE set ");
		// CREATE_TIME 目前存储为String类型
		//sql.append(" FAIL_TIME= to_date('" + urt.getUpdateComplete_time()+ "','yymmddhh24miss')");
		sql.append(" FAIL_TIME= SYSDATE ");
		sql.append(" ,TER_CRC='" + urt.getCRC() + "'");
		sql.append(" where TRIP_ID =" + "'" + urt.getTrip_code() + "'");
		sql.append(" and exe_date = trunc(sysdate,'DD')");

		log.info("<XCBuildSQL>解析行程更新结果汇报,更新行程信息表:" + sql.toString());
		return sql.toString();
	}
	/**
	 * 行程文件更新失败（临时表）
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public String buildTripReportFailTemp(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update TQC_TRIP_EXECUTE_TEMP set ");
		// CREATE_TIME 目前存储为String类型
		//sql.append(" FAIL_TIME= to_date('" + urt.getUpdateComplete_time()+ "','yymmddhh24miss')");
		sql.append(" FAIL_TIME= SYSDATE ");
		sql.append(" ,TER_CRC='" + urt.getCRC() + "'");
		sql.append(" where TRIP_ID =" + "'" + urt.getTrip_code() + "'");
		sql.append(" and exe_date = trunc(sysdate,'DD')");

		log.info("<XCBuildSQL>解析行程更新结果汇报（临时表）,更新行程信息表:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 行程文件内容错误信息上传更新方法
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public String buildTripFileFail(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update TQC_TRIP_EXECUTE set ");
		// CREATE_TIME 目前存储为String类型

		sql.append(" FAIL_TIME= to_date('" + urt.getUpdateComplete_time()
				+ "','yymmddhh24miss')");
		sql.append(" ,TER_CRC='" + urt.getCRC() + "'");
		sql.append(" ,ANSWER_INFO='" + urt.getFailMessage() + "'");
		sql.append(" where TRIP_ID =" + "'" + urt.getTrip_code() + "'");
		sql.append(" and exe_date = trunc(sysdate,'DD')");

		log.info("<XCBuildSQL>解析行程文件内容错误信息,更新行程信息表:" + sql.toString());
		return sql.toString();
	}
	/**
	 * 行程文件内容错误信息上传更新方法（临时表）
	 * 
	 * @param num
	 *            urt
	 * @return
	 */
	public String buildTripFileFailTemp(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update TQC_TRIP_EXECUTE_TEMP set ");
		// CREATE_TIME 目前存储为String类型

		sql.append(" FAIL_TIME= to_date('" + urt.getUpdateComplete_time()
				+ "','yymmddhh24miss')");
		sql.append(" ,TER_CRC='" + urt.getCRC() + "'");
		sql.append(" ,ANSWER_INFO='" + urt.getFailMessage() + "'");
		sql.append(" where TRIP_ID =" + "'" + urt.getTrip_code() + "'");
		sql.append(" and exe_date = trunc(sysdate,'DD')");

		log.info("<XCBuildSQL>解析行程文件内容错误信息,更新行程信息表:" + sql.toString());
		return sql.toString();
	}	
		
	/**
	 * 更新行程发车状态  0：未发车  1：正在执行  2表示已结束
	 * @param flag
	 * @param trip_id
	 * @return
	 */
	public String updateTrip(int flag,String trip_id,int updown) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update TQC_TRIP_EXECUTE t set ");
		sql.append(" t.FAIL_TIME= SYSDATE, ");
		sql.append(" t.status = " + "'" + flag + "'" );
		sql.append(" where t.TRIP_ID =" + "'" + trip_id + "'");
		sql.append(" and t.exe_date = trunc(sysdate,'DD') ");
		sql.append(" and exists (select 1 from TQC_TRIP_EXECUTE t1, clw_xc_route_t t2 where t1.route_id = t2.route_id "); 
		sql.append(" and t1.TRIP_ID =" + "'" + trip_id + "'");
		sql.append(" and t2.route_class = " + "'" + updown + "')" );

		log.info("<XCBuildSQL>解析行程更新结果汇报,更新行程信息表:" + sql.toString());
		return sql.toString();
	}
	
	/**
	 * 更新行程发车出站状态为1
	 * @param flag
	 * @param trip_id
	 * @return
	 */
	public String updateTripOut( String trip_id ) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update TQC_TRIP_EXECUTE t set ");
		sql.append(" t.FAIL_TIME= SYSDATE, ");
		sql.append(" t.OUT_SITE_FLAG  = '1' " );
		sql.append(" where t.TRIP_ID =" + "'" + trip_id + "'");
		sql.append(" and t.exe_date = trunc(sysdate,'DD') ");
		sql.append(" and t.OUT_SITE_FLAG is null ");

		log.info("<XCBuildSQL>解析行程更新结果汇报,更新行程信息表:" + sql.toString());
		return sql.toString();
	}
	/**
	 * 更新行程发车出站状态为1
	 * @param flag
	 * @param trip_id
	 * @return
	 */
	public String updateTripIut( String trip_id,String route_id,String site_id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" update TQC_TRIP_EXECUTE t set ");
		sql.append(" t.FAIL_TIME= SYSDATE, ");
		sql.append(" t.OUT_SITE_FLAG  = '1' " );
		sql.append(" where t.TRIP_ID =" + "'" + trip_id + "'");
		sql.append(" and t.exe_date = trunc(sysdate,'DD') ");
		sql.append(" and t.OUT_SITE_FLAG is null ");
		sql.append(" and exists (select 1 from (select rs.route_id, rs.site_id, rs.rs_order ");
		sql.append(" from clw_xc_routesite_t rs where rs.route_id = " + "'" + route_id + "'" );
		sql.append(" and rs.site_id = " + "'" + site_id + "'");       
		sql.append(" ) rs,(select min(rs.rs_order) min_order from clw_xc_routesite_t rs where rs.route_id = " + "'" + route_id + "'"); 
		sql.append(" ) min where rs.rs_order <> min.min_order) ");

		log.info("<XCBuildSQL>解析行程更新结果汇报,更新行程信息表:" + sql.toString());
		return sql.toString();
	}
	
	public String isSendMsgModelTowXD(Up_InfoContent urt, XcStudentBean sb) throws ParseException {
		String msg = null;
		String Student_name = null;
		if (sb.getStu_name() != null && !"".equals(sb.getStu_name())) {
			Student_name = sb.getStu_name();// 学生姓名
		}
		String time = DateUtil.changeTime12To04Format(urt.getTerminal_time());// 时间
		if (urt.getVss_flag().equals("0")) {
				msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
							+ "打卡上车。";
		}
		if (urt.getVss_flag().equals("1")) {
			msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "打卡下车。";
		}
        return msg;
	}
}
