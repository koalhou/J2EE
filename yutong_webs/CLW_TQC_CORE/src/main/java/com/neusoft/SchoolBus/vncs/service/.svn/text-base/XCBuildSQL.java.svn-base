package com.neusoft.SchoolBus.vncs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.SchoolBus.vncs.domain.RouteSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.domain.XcvsseBean;
import com.neusoft.SchoolBus.vncs.manage.SendxcmsmCommandManager;
import com.neusoft.SchoolBus.vncs.service.util.XCUtil;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.emay.sdk.SDKClient;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.dao.ITerminalDAO;
import com.neusoft.clw.vncs.dao.impl.MsgCfgDAO;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;

public class XCBuildSQL {

	private static Logger log = LoggerFactory.getLogger(XCBuildSQL.class);

	private ITerminalDAO terminalDAO = null;

	public static final String num3 = "3";

	public static final XCBuildSQL buildSql = new XCBuildSQL();

	private XCBuildSQL() {

		terminalDAO = (ITerminalDAO) SpringBootStrap.getInstance().getBean(
				"terminalDAO");
	}

	public static XCBuildSQL getInstance() {
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
		log.debug("<XCBuildSQL>解析上行终端版本信息,更新终端信息表:" + sql.toString());
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
		sql.append("insert into CLW_XC_ST_CHECK_RECORD_T("
						+ "ID,STU_ID,SITE_ID,ROUTE_ID,VEHICLE_VIN,PICI_ID,LATITUDE,"
						+ "LONGITUDE,SITE_FLAG,VSS_FLAG,ALARM_TYPE_ID,ST_DOWM_NUM,ST_UP_NUM,"
						+ "ST_NUM,TERMINAL_TIME,CREATE_TIME,OPERATE_FLAG,"
						+ "PLAN_UP_NUM,PLAN_DOWN_NUM,SICHEN_ID,DRIVER_ID,MESG_FLAG,SMS_FAIL_INFO) values(");
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
		if(urt.getSicheng_id()!=null&&!urt.getSicheng_id().equals("")){
			sql.append(",'" + urt.getSicheng_id() + "'");// 司乘编号
		}else{
			sql.append(",''");
		}
		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")){
			sql.append(",'" + urt.getDriver_id() + "'");// 司机编号
		}else{
			sql.append(",''");
		}
		if (urt.getSms_state() != null && !urt.getSms_state().equals("")) {		
			sql.append(",'"+ urt.getSms_state() +"'");//短信下发状态
			}else{
			sql.append(",'0'");	
			}
		sql.append(",'" + urt.getSms_fail_info() + "'");// 短信失败原因
		sql.append(")");
		log.debug("<XCBuildSQL>学生刷卡记录数据insert sql:" + sql.toString());

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
		if("0".equals(urt.getVss_flag())){
			sql.append("CS.UP_SITE_ID  = '" + urt.getSite_id() + "',");
			sql.append("CS.UP_SITE_TIME  = to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		}
		if("1".equals(urt.getVss_flag())){
			sql.append("CS.DOWN_SITE_ID  = '" + urt.getSite_id() + "',");
			sql.append("CS.DOWN_SITE_TIME  = to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");	
		}
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT");
		sql.append("(CS.STU_ID,CS.SITE_ID,CS.ROUTE_ID,CS.VEHICLE_VIN,"
				+ "CS.LATITUDE,CS.LONGITUDE,CS.SITE_FLAG,"
				+ "CS.VSS_FLAG,CS.ALARM_ID,CS.ST_DOWM_NUM,"
				+ "CS.ST_UP_NUM,CS.ST_NUM,CS.TERMINAL_TIME,CS.CREATE_TIME,"
				+ "CS.PLAN_UP_NUM,CS.PLAN_DOWN_NUM,CS.SICHEN_ID,CS.DRIVER_ID," 
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
		if("0".equals(urt.getVss_flag())){
			sql.append(",'" + urt.getSite_id() + "',");
			sql.append("to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss'),");
			sql.append("'',");
			sql.append("''");
		}
		if("1".equals(urt.getVss_flag())){
			sql.append(",'',");
			sql.append("'',");
			sql.append("'" + urt.getSite_id() + "',");
			sql.append("to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		}
		sql.append(")");
		log.debug("<XCBuildSQL>更新或添加学生刷卡实时表:" + sql.toString());
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
		sql.append("'" + urt.getSicheng_id()+ "',");//司乘编号
		sql.append("'" + urt.getDriver_id() + "',");//司机编号		
		sql.append("sysdate)");
		log.debug("<XCBuildSQL>更新或添加学生刷卡实时表:" + sql.toString());
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
		sql.append("'" + urt.getSicheng_id()+ "',");//司乘编号
		sql.append("'" + urt.getDriver_id() + "',");//司机编号		
		sql.append("sysdate)");
		log.debug("<XCBuildSQL>更新或添加学生刷卡实时表:" + sql.toString());
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
		sql.append("insert into CLW_XC_ST_CHECK_RECORD_T("
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
		sql.append(",'2'");// 告警状态
		sql.append(",'79'");// 告警类型ID
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		sql.append(",sysdate");
		sql.append(",'0'");
		if (urt.getSms_state() != null && !urt.getSms_state().equals("")) {		
			sql.append(",'"+ urt.getSms_state() +"'");//短信下发状态
			}else{
			sql.append(",'0'");	
			}
		sql.append(",'" + urt.getSms_fail_info() + "'");// 短信失败原因
		sql.append(",'" + urt.getDriver_id() + "'");// 司机编号
		sql.append(",'" + urt.getSicheng_id() + "'");// 司乘编号	
		sql.append(")");
		log.debug("<XCBuildSQL>学生上车未刷卡记录数据insert sql:" + sql.toString());
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

		sql.append("insert into CLW_XC_ST_CHECK_RECORD_T("
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
			sql.append(",'"+ urt.getSms_state() +"'");//短信下发状态
			}else{
			sql.append(",'0'");	
			}
		sql.append(",'" + urt.getSms_fail_info() + "'");// 短信失败原因
		sql.append(",'" + urt.getDriver_id() + "'");// 纬度
		sql.append(",'" + urt.getSicheng_id() + "'");// 经度		
		sql.append(")");
		log.debug("<XCBuildSQL>学生下车未刷卡记录数据insert sql:" + sql.toString());
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

		sql
				.append("insert into CLW_XC_SISCCARD_T("
						+ "ID,OTHER_ID,ROUTE_ID,VEHICLE_VIN,LATITUDE,"
						+ "LONGITUDE,SITE_FLAG,"
						+ "TERMINAL_TIME,CREATE_TIME) values(");
		if (urt.getSicheng_id() != null && !urt.getSicheng_id().equals("")) {
			sql.append("'" + urt.getSicheng_id() + "'");// 主键
		}
		if (urt.getOther_id() != null && !urt.getOther_id().equals("")) {
			sql.append(",'" + urt.getOther_id() + "'");// 司乘ID
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")) {
			sql.append(",'" + urt.getRoute_id() + "'");// 线路ID
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
		log.debug("<XCBuildSQL>司机司乘刷卡记录数据insert sql:" + sql.toString());
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
		sql.append(" and FLAG = '0'");
		log.debug("<XCBuildSQL>解析学生销假信息,更新请销假信息表:" + sql.toString());
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
		log.debug("<XCBuildSQL>解析超载告警信息,更新终端信息表:" + sql.toString());
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
		sql.append("insert into CLW_XC_INOUTSITE_T("
				+ "INOUT_ID,SITE_ID,ROUTE_ID,"
				+ "VEHICLE_VIN,INOUT_FLAG,LATITUDE,LONGITUDE,"
				+ "TERMINAL_TIME,CREATE_TIME,SICHEN_ID,DRIVER_ID,"
				+ "SITE_UPDOWN,REALITY_IN_TIME,PLAN_IN_TIME,"
				+ "REALITY_OUT_TIME,PLAN_OUT_TIME,REALITY_UP_NUM,"
				+ "PLAN_UP_NUM,REALITY_DOWN_NUM,PLAN_DOWN_NUM,ST_NUM) values(");
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
		sql.append(", to_date(" + urt.getIn_time() + ",'yymmddhh24miss')");// 上报时间
		sql.append(", sysdate");// 更新时间
		if(urt.getSicheng_id()!=null&&!urt.getSicheng_id().equals("")){
			sql.append(", '" + urt.getSicheng_id() + "'");// 当班司机编号
		}else{
			sql.append(",''");
		}
		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")){
			sql.append(", '" + urt.getDriver_id() + "'");// 当班司乘编号
		}else{
			sql.append(",''");
		}
		sql.append(",'"+urt.getSite_flag()+"'");
		sql.append(", to_date(" + urt.getIn_time() + ",'yymmddhh24miss')");// 实际进站时间
		sql.append(", to_date(" + urt.getPlan_in_time() + ",'yymmddhh24miss')");// 计划进站时间
		if(urt.getInout_state().equals("1")){
			sql.append(", to_date(" + urt.getOut_time() + ",'yymmddhh24miss')");// 实际出站时间
			sql.append(", to_date(" + urt.getPlan_out_time() + ",'yymmddhh24miss')");// 计划出站时间
			sql.append(", '" + urt.getSt_up_num() + "'");// 实际上车人数
			sql.append(", '" + urt.getSt_institute_up_num() + "'");// 计划上车人数
			sql.append(", '" + urt.getSt_down_num() + "'");// 实际下车人数
			sql.append(", '" + urt.getSt_institute_down_num() + "'");// 计划下车人数
			sql.append(", '" + urt.getSt_num() + "'");// 车内人数
		}else{
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<XCBuildSQL>车辆进出站记录数据insert sql:" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public Map getCacheQuotas(String enterprise_id) {
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			Object obj  = Constant.getMemcachedClient().getObject(enterprise_id) ;
			if (obj!= null && !obj.equals("")) {
				return (Map) obj;
			} else {
				return null;
			}
		} else {
			log.debug("kkkkkkkkkkkk:"
					+ MsgCfgDAO.msgquotasMap.get(enterprise_id));
			return MsgCfgDAO.msgquotasMap.get(enterprise_id);
		}
	}

	@SuppressWarnings("unchecked")
	public void isHasQuotas(XcStuSmsBean vb, Up_InfoContent urt, XcStudentBean sb) throws ParseException {
		try{
			log.debug("@!@!+1:");
			String type = isSendType(urt);
			String enterprise_id ;
			if( sb.getEnterprise_id()!=null&&! sb.getEnterprise_id().equals("")){
				enterprise_id= sb.getEnterprise_id();
				Map map = getCacheQuotas(enterprise_id);
				String systime = terminalDAO.getSysTime();
				if (map != null && map.size() > 0) {
					log.debug("@!@!+2:");
					String send_num;
					Integer msg_num;
					int current_month = 0;
					if (map.get("msg_num") != null && !map.get("msg_num").equals("")) {
						log.debug("@!@!+3msg_num:" + map.get("msg_num"));
						msg_num = Integer.parseInt((String) map.get("msg_num"));
						if (map.get("current_month") != null
								&& !map.get("current_month").equals("")) {
							log
									.debug("@!@!+4current_month:"
											+ map.get("current_month"));
							current_month = (Integer) map.get("current_month");
							if (current_month == DateUtil.getCurrentMonth(DateUtil
									.changeStringTo12Date(systime))) {
								log.debug("@!@!+5:");
								if (map.get("send_num") != null
										&& !map.get("send_num").equals("")) {
									send_num = (String) map.get("send_num");
								} else {
									send_num = "0";
								}
								log.debug("@!@!+6send_num:" + send_num);
								if (Integer.parseInt(send_num) >= msg_num) {
									log.info("<XCBuildSQL>当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_fail_info("当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_state("2");
								} else {
									log.debug("@!@!7:");
									if (isSuccessSendMsg(vb,urt, type,sb)) {
										map.put("send_num", String.valueOf(Integer
												.parseInt(send_num) + 1));
										if (Constant.isstartMemcache.equals("1")
												&& Constant.getMemcachedClient()
														.connectState()) {
											Constant.getMemcachedClient().insert(
													enterprise_id, map);
										}
										Constant.msgMap.put(enterprise_id, map);
	//									urt.setSms_state("1");
	//									urt.setSms_fail_info("短信下发成功！");
									}
								}
							} else {
								log.info("<XCBuildSQL>缓存当前月份：("
										+ current_month
										+ ")与系统当前月份("
										+ DateUtil.getCurrentMonth(DateUtil
												.changeStringTo12Date(systime))
										+ ")不匹配");
								map.put("current_month", DateUtil
										.getCurrentMonth(DateUtil
												.changeStringTo12Date(systime)));
								map.put("send_num", "0");
								log.debug(LogFormatter.formatMsg("XCBuildSQL",
										"发送额已过期，重置为0"));
								log.debug("eeeeeeeeqqqqq:" + map);
								if (Integer.parseInt((String) map.get("send_num")) >= msg_num) {
									log.info("<XCBuildSQL>当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_fail_info("当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_state("2");
								} else {
									if (isSuccessSendMsg(vb,urt, type,sb)) {
										map.put("send_num", String
												.valueOf(Integer.parseInt((String) map
														.get("send_num")) + 1));
										if (Constant.isstartMemcache.equals("1")
												&& Constant.getMemcachedClient()
														.connectState()) {
											Constant.getMemcachedClient().insert(
													enterprise_id, map);
											log.debug("memcache"
													+ Constant.getMemcachedClient()
															.getObject(enterprise_id));
										}
										Constant.msgMap.put(enterprise_id, map);
	//									urt.setSms_state("1");
	//									urt.setSms_fail_info("短信下发成功！");
									}
								}
							}
						} else {
							map.put("current_month", DateUtil.getCurrentMonth(DateUtil
									.changeStringTo12Date(systime)));
							log.debug("@!@!+12:");
							if (map.get("send_num") != null
									&& !map.get("send_num").equals("")) {
								send_num = (String) map.get("send_num");
							} else {
								send_num = "0";
							}
							log.debug("@!@!+13send_num:" + send_num);
							log.debug("@!@!14:");
							if (isSuccessSendMsg(vb,urt, type,sb)) {
								map.put("send_num", String.valueOf(Integer
										.parseInt(send_num) + 1));
								if (Constant.isstartMemcache.equals("1")
										&& Constant.getMemcachedClient().connectState()) {
									Constant.getMemcachedClient().insert(enterprise_id,
											map);
//									log.debug("memcache"
//											+ Constant.getMemcachedClient().getObject(
//													enterprise_id));
								}
								Constant.msgMap.put(enterprise_id, map);
	//							urt.setSms_state("1");
	//							urt.setSms_fail_info("短信下发成功！");
							}
						}
					} else {
						log.info("<XCBuildSQL>未缓存企业" + enterprise_id + "短信配额！");
						urt.setSms_fail_info("未缓存企业" + enterprise_id + "短信配额，短信未下发！");
						urt.setSms_state("2");
					}
				} else {
					log.info("<XCBuildSQL>未找到企业" + enterprise_id + "的短信配额缓存！");
					urt.setSms_fail_info("未找到企业" + enterprise_id + "的短信配额缓存，短信未下发！");
					urt.setSms_state("2");
				}
			}else{
				log.info("<XCBuildSQL>未找到该学生" +sb.getStu_id()+ "的短信配额缓存！");
				urt.setSms_fail_info("未找到该学生" +sb.getStu_id()+ "的短信配额缓存，短信未下发！");
				urt.setSms_state("2");
			}
		}catch(Exception e){
			log.info("<XCBuildSQL> 该学生未找到相应的企业，无法继续进行操作！");
			urt.setSms_fail_info("该学生未找到相应的企业，无法继续进行操作，短信未下发！");
			urt.setSms_state("2");
			log.error("<XCBuildSQL>发生错误："+e.getMessage());
			e.printStackTrace();
		}
	}

	public boolean isSuccessSendMsg(XcStuSmsBean vb, Up_InfoContent urt, String type,XcStudentBean sb)
			throws ParseException {
	    // 查询短信配置缓存信息
		log.info("学生短信配置信息key为： " + urt.getStu_id() + type);

		// 查询学生信息缓存信息
//		XcStudentBean sb = SendxcmsmCommandManager.getInstance()
//				.getStudentValue(Constant.STUDENT + urt.getStu_id());
		// 查询站点缓存信息
		XcSiteBean csb = SendxcmsmCommandManager.getInstance().getSiteValue(
				Constant.SITE + urt.getSite_id());

		// 查询站点缓存信息
		RouteSiteBean rsb = SendxcmsmCommandManager.getInstance()
				.getRouteSiteValue(urt.getRoute_id() + urt.getSite_id());

		// 查询学生订购站点信息
		XcSiteBean vsssb = null;
		XcvsseBean xsb = null;

//			if("02".equals(urt.getModelversion())){
//			          xsb= SendxcmsmCommandManager.getInstance().getVssValueVt(
//					urt.getStu_id()+ urt.getRoute_id() + urt.getVss_flag() + urt.getSite_flag());
//			}else{
//				      xsb = SendxcmsmCommandManager.getInstance().getVssValue(
//						urt.getStu_id()+ urt.getVss_flag() + urt.getSite_flag());				
//			}
		if(urt.getModelversion().equals("02")){
			xsb= SendxcmsmCommandManager.getInstance().getVssValue(XCUtil.getXcVssKey(urt));
				// 根据线路ID查询学生订购的站点
			if(xsb!=null){
				vsssb = SendxcmsmCommandManager.getInstance().getSiteValue(Constant.SITE + xsb.getSite_id());
			}
		}
		
		String msgtag = Config.props.getProperty("msgtag");
		// 判断该告警是否需要下发短信
		if (vb != null) {
			String sms_msg = isSendMsg(urt, vb, sb, csb, rsb, vsssb);
			if (vb.getCell_number() != null && !vb.getCell_number().equals("")
					&& vb.getEvent_type() != null
					&& !vb.getEvent_type().equals("")) {
				String time = new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date());
				if (vb.getEnd_time().compareTo(time) >= 0) {
					urt.setTel(vb.getCell_number());// 电话
					urt.setRelative_type(vb.getRelative_type());// 称谓
					urt.setRelative_name(vb.getRelative_name());// 姓名
					urt.setEnd_time(vb.getEnd_time());// 截止日期
					urt.setParents_flag(vb.getParents_flag());// 是否家长
					urt.setOrganization_id(sb.getOrganization_id());// 组织编码
					urt.setEnterprise_id(sb.getEnterprise_id());// 企业编码
					int i = 0; 
					int xcsend_num = 3;
					int num = 0;
					while (i < xcsend_num) {
						num = SDKClient.getClient().sendSMS(
								new String[] { vb.getCell_number() },
								msgtag + sms_msg , 5);
						log.info("短信息内容为：" + sms_msg);
//								",消息序列号："+urt.getSeq());
						log.info("手机号码为：" + vb.getCell_number());
						urt.setMsg(sms_msg);
						if (num == 0) {
							log.info("<XCBuildSQL>短信网关返回状态码：" + num
									+ ",学生刷卡短信下发成功");
							log.info("短信成功发送时间为："
									+ new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss")
											.format(new Date()));
							urt.setSms_state("1");
							urt.setSms_fail_info("短信下发成功！");
							urt.setState("0");
							DBBuffer.getInstance().add(
									XCBuildSQL.getInstance()
											.buildInsertSmsRecordSQL(urt));
							return true;
						} else {
							i++;
							log.info("<BuildSQL>该消息第" + i
									+ "次短信下发失败,短信网关返回状态码：" + num);
							continue;
						}
					}
					smsFailMessage(num, urt);
					urt.setSms_num(String.valueOf(num));
					urt.setSms_state("2");
					urt.setState("1");
					DBBuffer.getInstance().add(
							XCBuildSQL.getInstance()
									.buildInsertSmsRecordSQL(urt));					
					return false;
					
				} else {
					log.info("<BuildSQL>短信订购已到期，不下发短信！");
					urt.setSms_fail_info("短信订购已到期，不下发短信！");
					urt.setSms_state("2");
					return false;
				}
			} else {
				log.info("<BuildSQL>该消息未设置该事件下发的信息，不下发短信！");
				urt.setSms_fail_info("该消息未设置该事件下发的信息，不下发短信！");
				urt.setSms_state("2");
				return false;
			}
		} else {
			log.info("<BuildSQL>该学生" + urt.getStu_id() + "短信业务未开通，不下发短信！");
			urt.setSms_fail_info("该学生" + urt.getStu_id() + "短信业务未开通，不下发短信！");
			urt.setSms_state("4");
			return false;
		}
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
	private String isSendMsg(Up_InfoContent urt, XcStuSmsBean vb,
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
		if (rsb != null) {// 上传的站点
			// 正常打卡情况
			if ("".equals(urt.getAlarm_id()) || urt.getAlarm_id() == null) {
				// 上学上车打卡
				if (urt.getSmstype().equals("00")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "" + "于" + time + " 在"
							+ sitename + "站点乘坐校车前往学校。";
				}
				// 放学上车打卡
				if (urt.getSmstype().equals("02")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + " 在 "
							+ sitename + "站点乘坐校车返家。";
				}
				// 上学下车打卡
				if (urt.getSmstype().equals("01")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "顺利抵达"
							+ sitename + "站点。";
				}
				// 放学下车打卡
				if (urt.getSmstype().equals("03")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "顺利抵达 "
							+ sitename + "站点。";
				}
			} else {
				// 异常乘车情况
				// 未在规定站点上车
				if (urt.getAlarm_id().equals("73")) {
					// 上学时未在规定站点上车
					if (urt.getSmstype().equals("00")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在 "
								+ sitename + "" + "站点乘坐校车前往学校，没有在规定站点"
								+ vsssite_name + "上车，请您确认原因！";
					}
					// 放学时未在规定站点上车
					if (urt.getSmstype().equals("02")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在"
								+ sitename + "" + "站点乘坐校车返家，没有在规定站点"
								+ vsssite_name + "上车，请您确认原因！";
					}
				}
				// 未在规定站点下车
				if (urt.getAlarm_id().equals("74")) {
					// 上学时未在规定站点下车
					if (urt.getSmstype().equals("01")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在 "
								+ sitename + "站点离开校车前往学校，没有在规定站点"
								+ vsssite_name + "下车，请您确认原因！";
					}
					// 放学时未在规定站点下车
					if (urt.getSmstype().equals("03")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time + "在 "
								+ sitename + "站点离开校车返家，没有在规定站点" + vsssite_name
								+ "下车，请您确认原因！";
					}
				}
				// 上学时未在规定站点上车刷卡
				if (urt.getAlarm_id().equals("79")
						&& rsb.getSite_updown().equals("0")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的"
							+ sitename + "站点乘车前往学校，请您确认原因！";
				}
				// 放学时未在规定站点上车刷卡
				if (urt.getAlarm_id().equals("79")
						&& rsb.getSite_updown().equals("1")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的"
							+ sitename + "乘坐校车返家，请您确认原因！ ";
				}

				// 上学时未在规定站点下车刷卡
				if (urt.getAlarm_id().equals("80")
						&& rsb.getSite_updown().equals("0")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的"
							+ sitename + "站点刷卡离开校车前往学校，请您确认原因！";
				}
				// 放学时未在规定站点下车刷卡
				if (urt.getAlarm_id().equals("80")
						&& rsb.getSite_updown().equals("1")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的"
							+ sitename + "站点离开校车返家，请您确认原因！";
				}
			}
		} else {// 没报

			if ("".equals(urt.getAlarm_id()) || urt.getAlarm_id() == null) {
				if (urt.getSmstype().equals("00")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
							+ "乘坐校车前往学校。";
				}
				if (urt.getSmstype().equals("02")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "乘坐校车返家。";
				}
				if (urt.getSmstype().equals("01")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "顺利抵达学校。";
				}
				if (urt.getSmstype().equals("03")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "顺利到家。";
				}				

			} else {
				if (urt.getAlarm_id().equals("73")) {
					if (urt.getSmstype().equals("00")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "乘坐校车前往学校，没有在规定站点" +vsssite_name
								+ "上车，请您确认原因！";
					}

					if (urt.getSmstype().equals("02")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "乘坐校车返家，没有在规定站点"+ vsssite_name
								+ "上车，请您确认原因！";
					}

				}
				if (urt.getAlarm_id().equals("79")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的"
							+ sitename + "站点乘车前往学校，请您确认原因！";
				}
				if (urt.getAlarm_id().equals("74")) {

					if (urt.getSmstype().equals("01")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "离开校车前往学校，没有在规定站点" + vsssite_name
								+ "下车，请您确认原因！";
					}

					if (urt.getSmstype().equals("03")) {
						msg = "安芯告警信息：您的孩子" + Student_name + "于" + time
								+ "离开校车返家，没有在规定站点" + vsssite_name
								+ "下车，请您确认原因！";
					}
				}
				if (urt.getAlarm_id().equals("80")) {

					msg = "安芯告警信息：您的孩子" + Student_name + "没有在规定的"
							+ sitename + "站点刷卡离开校车，请您确认原因！";
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
		sql.append("insert into CLW_YW_SMS_RECORD_T(ID,VEHICLE_VIN,TEL,MSG,"
						+ "STATE,SEND_TAKE,SRC_ID,CREATE_TIME,"
						+ "ENTERPRISE_ID,ORGANIZATION_ID,STU_ID,"
						+ "EVENT_TYPE,FLAG,RELATIVE_TYPE,RELATIVE_NAME,"
						+ "PARENTS_FLAG,PICI_ID,SMS_NUM,SMS_FAIL_INFO) values(");
		
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
		//sql.append(",'" + isSendType(urt) + "'");
		sql.append(",''");
		
		sql.append(",'1'");
		sql.append(",'" + urt.getRelative_type() + "'");
		sql.append(",'" + urt.getRelative_name() + "'");
		if (urt.getParents_flag() != null && !"".equals(urt.getParents_flag())) {
			sql.append(",'" + urt.getParents_flag() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getPid()+ "'");
		sql.append(",'" + urt.getSms_num() + "'");
		sql.append(",'" + urt.getSms_fail_info() + "'");		
		sql.append(")");
		log.debug("<BuildSQL>通勤车发送短信流水记录数据insert sql:" + sql.toString());
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
		log.debug("<BuildSQL>驾驶员上车刷卡时更新终端信息表驾驶员编号:" + sql.toString());
		return sql.toString();
	}

	public String buildUpdateDownTerminalDriverSql(Up_InfoContent uhc) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		// CREATE_TIME 目前存储为String类型
		sql.append(" DRIVER_ID = ''");
		sql.append(" where vehicle_vin = '" + uhc.getTerminalId() + "'");
		log.debug("<BuildSQL>驾驶员下车刷卡时更新终端信息表驾驶员编号:" + sql.toString());
		return sql.toString();
	}

//	public String buildUpdteInOutTerminalSql(Up_InfoContent uhc) {
//		StringBuffer sql = new StringBuffer();
//		sql.append("update CLW_JC_TERMINAL_T set ");
//		sql.append(" SITE_ID = '" + uhc.getSite_id() + "'");
//		sql.append(",ROUTE_ID = '" + uhc.getRoute_id() + "'");
//		sql.append(",INOUT_FLAG = '" + uhc.getInout_state() + "'");
//		sql.append(" where vehicle_vin = '" + uhc.getTerminalId() + "'");
//		log.debug("<BuildSQL>进出站时更新终端表:" + sql.toString());
//		return sql.toString();
//	}

	public String buildUpdateShuaKaTerminalSql(Up_InfoContent uhc) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append(" STU_NUM = '" + uhc.getSt_num() + "'");
		sql.append(" where vehicle_vin = '" + uhc.getTerminalId() + "'");
		log.debug("<BuildSQL>进出站时更新终端表:" + sql.toString());
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
		sql.append("insert into CLW_XC_SITECOLLECTION_T(COLLECTION_ID,VEHICLE_VIN,SITE_LONGITUDE,SITE_LATITUDE,CREATE_TIME,VALID_FLAG) ");
		sql.append("values('"+IdCreater.getUUid()+"'");
		sql.append(",'"+uhc.getTerminalId()+"'");	
		sql.append(",'"+uhc.getLongitude()+"'");
		sql.append(",'"+uhc.getLatitude()+"'");
		sql.append(",to_date('"+uhc.getTerminal_time()+"','yymmddhh24miss')");
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
		sql.append("update CLW_XC_ST_CHECK_RECORD_T partition(ST_CHECK_RECORD_"+Sdate(uhc.getTerminal_time())+") set ");
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
        return "20"+sdate.substring(0, 2) + sdate.substring(2, 6);
    }
	
	/**
	 * 亿美短信发送失败提示
	 * 
	 * @param num
	 * urt
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
	 * 模式2亿美短信接口
	 * @param sb 
	 * 
	 * @param num
	 * urt
	 * @return
	 */	
	@SuppressWarnings("unchecked")
	public void isHasQuotasModelTwo(XcStuSmsBean vb, Up_InfoContent urt, XcStudentBean sb) throws ParseException {
		try{
			log.debug("@!@!+1:");
			String type = isSendType(urt);
			String enterprise_id;
			if(sb.getEnterprise_id()!=null&&!sb.getEnterprise_id().equals("")){
				enterprise_id = sb.getEnterprise_id();
				Map map = getCacheQuotas(enterprise_id);
				String systime = terminalDAO.getSysTime();
				if (map != null && map.size() > 0) {
					log.debug("@!@!+2:");
					String send_num;
					Integer msg_num;
					int current_month = 0;
					if (map.get("msg_num") != null && !map.get("msg_num").equals("")) {
						log.debug("@!@!+3msg_num:" + map.get("msg_num"));
						msg_num = Integer.parseInt((String) map.get("msg_num"));
						if (map.get("current_month") != null && !map.get("current_month").equals("")) {
							log.debug("@!@!+4current_month:" + map.get("current_month"));
							current_month = (Integer) map.get("current_month");
							if (current_month == DateUtil.getCurrentMonth(DateUtil.changeStringTo12Date(systime))) {
								log.debug("@!@!+5:");
								if (map.get("send_num") != null	&& !map.get("send_num").equals("")) {
									send_num = (String) map.get("send_num");
								} else {
									send_num = "0";
								}
								log.debug("@!@!+6send_num:" + send_num);
								if (Integer.parseInt(send_num) >= msg_num) {
									log.info("<XCBuildSQL>当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_fail_info("当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_state("2");
								} else {
									log.debug("@!@!7:");
									if (isSuccessSendMsgModelTwo(vb,urt, type,sb)) {
										map.put("send_num", String.valueOf(Integer.parseInt(send_num) + 1));
										if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState()) {
											Constant.getMemcachedClient().insert(enterprise_id, map);
										}
										Constant.msgMap.put(enterprise_id, map);
	//									urt.setSms_state("1");
	//									urt.setSms_fail_info("短信下发成功！");
									}
								}
							} else {
								log.info("<XCBuildSQL>缓存当前月份：(" + current_month + ")与系统当前月份(" + DateUtil.getCurrentMonth(DateUtil.changeStringTo12Date(systime)) + ")不匹配");
								map.put("current_month", DateUtil.getCurrentMonth(DateUtil.changeStringTo12Date(systime)));
								map.put("send_num", "0");
								log.debug(LogFormatter.formatMsg("XCBuildSQL","发送额已过期，重置为0"));
								log.debug("eeeeeeeeqqqqq:" + map);
								if (Integer.parseInt((String) map.get("send_num")) >= msg_num) {
									log.info("<XCBuildSQL>当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_fail_info("当月短信发送数量超出短信配额,短信下发失败！");
									urt.setSms_state("2");
								} else {
									if (isSuccessSendMsgModelTwo(vb,urt, type,sb)) {
										map.put("send_num", String.valueOf(Integer.parseInt((String) map.get("send_num")) + 1));
										if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState()) {
											Constant.getMemcachedClient().insert(enterprise_id, map);
											log.debug("memcache" + Constant.getMemcachedClient().getObject(enterprise_id));
										}
										Constant.msgMap.put(enterprise_id, map);
	//									urt.setSms_state("1");
	//									urt.setSms_fail_info("短信下发成功！");
									}
								}
							}
						} else {
							map.put("current_month", DateUtil.getCurrentMonth(DateUtil.changeStringTo12Date(systime)));
							log.debug("@!@!+12:");
							if (map.get("send_num") != null	&& !map.get("send_num").equals("")) {
								send_num = (String) map.get("send_num");
							} else {
								send_num = "0";
							}
							log.debug("@!@!+13send_num:" + send_num);
							log.debug("@!@!14:");
							if (isSuccessSendMsgModelTwo(vb,urt, type,sb)) {
								map.put("send_num", String.valueOf(Integer.parseInt(send_num) + 1));
								if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState()) {
									Constant.getMemcachedClient().insert(enterprise_id,	map);
//									log.debug("memcache" + Constant.getMemcachedClient().getObject(enterprise_id));
								}
								Constant.msgMap.put(enterprise_id, map);
	//							urt.setSms_state("1");
	//							urt.setSms_fail_info("短信下发成功！");
							}
						}
					} else {
						log.info("<XCBuildSQL>未缓存企业" + enterprise_id + "短信配额！");
						urt.setSms_fail_info("未缓存企业" + enterprise_id + "短信配额，短信未下发！");
						urt.setSms_state("2");
					}
				} else {
					log.info("<XCBuildSQL>未找到企业" + enterprise_id + "的短信配额缓存！");
					urt.setSms_fail_info("未找到企业" + enterprise_id + "的短信配额缓存，短信未下发！");
					urt.setSms_state("2");
				}
			}else{
				log.info("<XCBuildSQL>未找到该学生" +sb.getStu_id()+ "的短信配额缓存！");
				urt.setSms_fail_info("未找到该学生" +sb.getStu_id()+ "的短信配额缓存，短信未下发！");
				urt.setSms_state("2");
			}
		}catch(Exception e){
			log.info("<XCBuildSQL> 该学生未找到相应的企业，无法继续进行操作！");
			urt.setSms_fail_info("该学生未找到相应的企业，无法继续进行操作，短信未下发！");
			urt.setSms_state("2");
			log.error("<XCBuildSQL>发生错误："+e.getMessage());
			e.printStackTrace();
		}
	}
	public boolean isSuccessSendMsgModelTwo(XcStuSmsBean vb, Up_InfoContent urt, String type,XcStudentBean sb)
	throws ParseException {
		// 查询短信配置缓存信息
		log.info("学生短信配置信息key为： " + urt.getStu_id() + type);
	    String msgtag = Config.props.getProperty("msgtag");
		// 判断该告警是否需要下发短信
		if (vb != null) {
			String sms_msg = isSendMsgModelTow(urt, sb);
			if (vb.getCell_number() != null && !vb.getCell_number().equals("")
					&& vb.getEvent_type() != null
					&& !vb.getEvent_type().equals("")) {
				String time = new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date());
				if (vb.getEnd_time().compareTo(time) >= 0) {
					urt.setTel(vb.getCell_number());// 电话
					urt.setRelative_type(vb.getRelative_type());// 称谓
					urt.setRelative_name(vb.getRelative_name());// 姓名
					urt.setEnd_time(vb.getEnd_time());// 截止日期
					urt.setParents_flag(vb.getParents_flag());// 是否家长
					urt.setOrganization_id(sb.getOrganization_id());// 组织编码
					urt.setEnterprise_id(sb.getEnterprise_id());// 企业编码
					int i = 0; 
					int xcsend_num = 3;
					int num = 0;
					while (i < xcsend_num) {
						num = SDKClient.getClient().sendSMS(new String[] { vb.getCell_number() }, msgtag + sms_msg, 5);
						log.info("短信息内容为：" + sms_msg);
						log.info("手机号码为：" + vb.getCell_number());
						urt.setMsg(sms_msg);
						if (num == 0) {
							log.info("<XCBuildSQL>短信网关返回状态码：" + num
									+ ",学生刷卡短信下发成功");
							log.info("短信成功发送时间为："
									+ new SimpleDateFormat(
											"yyyy-MM-dd HH:mm:ss")
											.format(new Date()));
							urt.setSms_state("1");
							urt.setSms_fail_info("短信下发成功！");
							urt.setState("0");
							DBBuffer.getInstance().add(
									XCBuildSQL.getInstance()
											.buildInsertSmsRecordSQL(urt));
							return true;
						} else {
							i++;
							log.info("<BuildSQL>该消息第" + i
									+ "次短信下发失败,短信网关返回状态码：" + num);
							continue;
						}
					}
					smsFailMessage(num, urt);
					urt.setSms_num(String.valueOf(num));
					urt.setSms_state("2");
					urt.setState("1");
					DBBuffer.getInstance().add(
							XCBuildSQL.getInstance()
									.buildInsertSmsRecordSQL(urt));					
					return false;
					
				} else {
					log.info("<BuildSQL>短信订购已到期，不下发短信！");
					urt.setSms_fail_info("短信订购已到期，不下发短信！");
					urt.setSms_state("2");
					return false;
				}
			} else {
				log.info("<BuildSQL>该消息未设置该事件下发的信息，不下发短信！");
				urt.setSms_fail_info("该消息未设置该事件下发的信息，不下发短信！");
				urt.setSms_state("2");
				return false;
			}
		} else {
			log.info("<BuildSQL>该学生" + urt.getStu_id() + "短信业务未开通，不下发短信！");
			urt.setSms_fail_info("该学生" + urt.getStu_id() + "短信业务未开通，不下发短信！");
			urt.setSms_state("4");
			return false;
		}
	}
	
	
	
	
	/**
	 * 模式2亿美短信拼装内容
	 * 
	 * @param num
	 * urt
	 * @return
	 */		
	private String isSendMsgModelTow(Up_InfoContent urt, XcStudentBean sb) throws ParseException {
		String msg = null;
		String Student_name = null;
		if (sb.getStu_name() != null && !"".equals(sb.getStu_name())) {
			Student_name = sb.getStu_name();// 学生姓名
		}
		String time = DateUtil.changeTime12To04Format(urt.getTerminal_time());// 时间
        if ("".equals(urt.getAlarm_id()) || urt.getAlarm_id() == null) {
				if (urt.getVss_flag().equals("0")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time
							+ "打卡上车。";
				}
				if (urt.getVss_flag().equals("1")) {
					msg = "安芯温馨提示：您的孩子" + Student_name + "于" + time + "打卡下车。";
				}
				
			} 
        return msg;
	}
	
	
	public String insertExceptionType(Up_InfoContent urt){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into CLW_YW_TERMINAL_EXCEPTION_T(id,exception_id,exception_time,exception_state,excepton_desc,create_time,creater,vehicle_vin) values(");
		sb.append("'"+IdCreater.getUUid()+"'");
		sb.append(",'"+urt.getEx_id()+"'");
		sb.append(",to_date('"+urt.getEx_time()+"','yymmddhh24miss')");
		sb.append(",'"+urt.getEx_state()+"'");
		if(urt.getEx_desc()!=null&&!urt.getEx_desc().equals("")){
			sb.append(",'"+(urt.getEx_desc().contains("'")?urt.getEx_desc().replace("'", "''"):urt.getEx_desc())+"'");
		}else{
			sb.append(",''");
		}
		sb.append(",sysdate");
		sb.append(",'"+Constant.CORE_ID+"'");
		sb.append(",'"+urt.getTerminalId()+"'");
		sb.append(")");
		return sb.toString();
	}
	
	/**
	 * 通勤车系统发送短信到司机
	 * @param urt,sms_msg
	 * @return
	 */		
	public boolean sendMsg2DriverByTqc(Up_InfoContent urt, String sms_msg){
		//短信后缀标签   [宇通客车]
		String msgtag = Config.props.getProperty("msgtag");
		int i = 0; 
		int xcsend_num = 2;
		int num = 0;
		//每条行程发送两遍
		while (i < xcsend_num) {
			num = SDKClient.getClient().sendSMS(new String[] { urt.getTel() }, msgtag + sms_msg, 5);
			log.info("短信息内容为：" + sms_msg);
			log.info("手机号码为：" + urt.getTel());
			urt.setMsg(sms_msg);
			if (num == 0) {
				log.info("<XCBuildSQL>短信网关返回状态码：" + num + ",通勤车线路行程文件短信下发成功！");
				log.info("短信成功发送时间为："+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				urt.setSms_state("1");
				urt.setSms_fail_info("通勤车线路行程文件短信下发成功！");
				urt.setState("0");				
				DBBuffer.getInstance().add(XCBuildSQL.getInstance().buildInsertSmsRecordSQL(urt));			
				return true;
			} else {
				i++;
				log.info("<XCBuildSQL>该消息第" + i + "次短信下发失败,短信网关返回状态码：" + num);
				continue;
			}
		}
		//亿美短信发送失败提示
		smsFailMessage(num, urt);
		urt.setSms_num(String.valueOf(num));//短信返回状态码
		urt.setSms_state("2");
		urt.setState("1");//状态:0:成功；1：失败	
		DBBuffer.getInstance().add(XCBuildSQL.getInstance().buildInsertSmsRecordSQL(urt));		
		return false;
	}	
}
