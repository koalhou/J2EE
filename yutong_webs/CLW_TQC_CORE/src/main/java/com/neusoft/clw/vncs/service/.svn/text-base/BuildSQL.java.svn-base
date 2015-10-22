package com.neusoft.clw.vncs.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.emay.sdk.SDKClient;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.cachemanager.TerminalCacheManager;
import com.neusoft.clw.vncs.cachemanager.VehicleCacheManager;
import com.neusoft.clw.vncs.dao.ITerminalDAO;
import com.neusoft.clw.vncs.dao.impl.MsgCfgDAO;
import com.neusoft.clw.vncs.domain.MsgCfgBean;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.clw.vncs.domain.UserBean;
import com.neusoft.clw.vncs.domain.VehicleBean;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.util.AccountUtil;
import com.neusoft.clw.vncs.util.Converser;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.tqcpt.service.SearchGisAreaByCode;

public class BuildSQL {

	private static Logger log = LoggerFactory.getLogger(BuildSQL.class);

	private ITerminalDAO terminalDAO = null;

	public static final String num3 = "3";

	private MsgCfgDAO msgCfgDAO;

	public static final BuildSQL buildSql = new BuildSQL();

	private BuildSQL() {
		terminalDAO = (ITerminalDAO) SpringBootStrap.getInstance().getBean(
				"terminalDAO");
		msgCfgDAO = (MsgCfgDAO) SpringBootStrap.getInstance().getBean(
				"msgCfgDAO");
	}

	public static BuildSQL getInstance() {
		return buildSql;
	}

	/**
	 * 拼装实时报文更新语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		// CREATE_TIME 目前存储为String类型
		sql.append("UPDATE_TIME=sysdate");
		if (urt.getTerminal_time() != null
				&& !urt.getTerminal_time().equals("")) {
			sql.append(",TERMINAL_TIME=" + "to_date(" + urt.getTerminal_time()
					+ ",'yymmddhh24miss')");
		}
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")) {
			sql.append(",GPS_VALID=" + "'" + urt.getGps_valid().trim() + "'");
			if (urt.getGps_valid().equals("1")) {
				if (urt.getUtc_time() != null && !urt.getUtc_time().equals("")) {
					sql.append(",UTC_TIME=" + "to_date(" + urt.getUtc_time()
							+ ",'yymmddhh24miss')");
				}
				if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
					sql.append(",LATITUDE=" + "'" + urt.getLatitude().trim()
							+ "'");
				}
				if (urt.getLongitude() != null
						&& !urt.getLongitude().equals("")) {
					sql.append(",LONGITUDE=" + "'" + urt.getLongitude().trim()
							+ "'");
				}
				if (urt.getDirection() != null
						&& !urt.getDirection().equals("")) {
					sql.append(",DIRECTION=" + "'" + urt.getDirection().trim()
							+ "'");
				}
				if (urt.getGps_speeding() != null
						&& !urt.getGps_speeding().equals("")) {
					if(!urt.getGps_speeding().equals("FFFF")){
						sql.append(",GPS_SPEEDING=" + "'"
								+ (!urt.getGps_speeding().trim().equals("0.0")?urt.getGps_speeding().trim():"0") + "'");
						if(Double.parseDouble(urt.getGps_speeding())>Double.parseDouble(Config.props.getProperty("vehicle.monitoring.threshold"))){
							sql.append(",VEHICLE_MONITORING_GPS_SPEED='"+(!urt.getGps_speeding().trim().equals("0.0")?urt.getGps_speeding().trim():"0")+"'");
							sql.append(",VEHICLE_MONITORING_SPEEDING='"+urt.getSpeeding().trim()+"'");
							sql.append(",VEHICLE_MONITORING_TIME=" + "to_date(" + urt.getTerminal_time()
									+ ",'yymmddhh24miss')");
						}
					}else{
						sql.append(",GPS_SPEEDING='0'");
					}
				}
			}else{
				sql.append(",GPS_SPEEDING='0'");
			}
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",SPEEDING=" + "'" + urt.getSpeeding().trim() + "'");
		} else if(urt.getSpeeding() == null) {
			sql.append(",SPEEDING=" + "'0'");
		}
		if (urt.getOn_off() != null && !urt.getOn_off().equals("")) {
			sql.append(",ON_OFF=" + "'" + urt.getOn_off().trim() + "'");
		}
		if (urt.getSos() != null && !urt.getSos().equals("")) {
			sql.append(",SOS=" + "'" + urt.getSos().trim() + "'");
		}
		if (urt.getOverspeed_alert() != null
				&& !urt.getOverspeed_alert().equals("")) {
			sql.append(",OVERSPEED_ALERT=" + "'"
					+ urt.getOverspeed_alert().trim() + "'");
		}
		if (urt.getFatigue_alert() != null
				&& !urt.getFatigue_alert().equals("")) {
			sql.append(",FATIGUE_ALERT=" + "'" + urt.getFatigue_alert().trim()
					+ "'");
		}
		if (urt.getGps_alert() != null && !urt.getGps_alert().equals("")) {
			sql.append(",GPS_ALERT ='" + urt.getGps_alert().trim() + "'");
		} 
		if (urt.getShow_speed_alert() != null && !urt.getShow_speed_alert().equals("")) {
			sql.append(",SHOW_SPEED_ALERT=" + "'"
					+ urt.getShow_speed_alert().trim() + "'");
		}
		if (urt.getRapid() != null && !urt.getRapid().equals("")) {
			sql.append(",RAPID_ALERT='" + urt.getRapid().trim() + "'");
		}
		if (urt.getRegion_overspeed_alert() != null
				&& !urt.getRegion_overspeed_alert().equals("")) {
			sql.append(",REGION_OVERSPEED_ALERT ='"
					+ urt.getRegion_overspeed_alert().trim() + "'");
		}
		if (urt.getRegion_in_alert() != null
				&& !urt.getRegion_in_alert().equals("")) {
			sql.append(",REGION_IN_ALERT='" + urt.getRegion_in_alert().trim()
					+ "'");
		}
		if (urt.getRegion_out_alert() != null
				&& !urt.getRegion_out_alert().equals("")) {
			sql.append(",REGION_OUT_ALERT='" + urt.getRegion_out_alert().trim()
					+ "'");
		}
		if (urt.getRegion_openclose_alert() != null
				&& !urt.getRegion_openclose_alert().equals("")) {
			sql.append(",REGION_OPENCLOSEDOOR_ALERT='"
					+ urt.getRegion_openclose_alert().trim() + "'");
		}
		if (urt.getVeh_ext_info() != null && !urt.getVeh_ext_info().equals("")) {
			sql.append(",veh_ext_info='" + urt.getVeh_ext_info().trim() + "'");
		} else if (urt.getXcononff() != null && !urt.getXcononff().equals("")) {
			sql.append(",VEH_EXT_INFO='" + urt.getXcononff() + "'");
		}
		if (urt.getDriver_id() != null && !urt.getDriver_id().equals("")) {
			if(!urt.getDriver_id().equals("FFFFFFFF")){
				sql.append(",DRIVER_ID=" + "'" + Long.parseLong(urt.getDriver_id().trim(),16) + "'");
			}else{
				sql.append(",DRIVER_ID=" + "'" + Constant.F4 + "'");
			}
		}else{
			sql.append(",DRIVER_ID=" + "''");
		}
//		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
//			String driver_id = (String) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",DRIVER_ID="+driver_id+"'");
//			}
//		}else{
//			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",DRIVER_ID='"+driver_id+"'");
//			}
//		}
		if (urt.getDriver_license() != null
				&& !urt.getDriver_license().equals("")) {
			sql.append(",DRIVER_LICENSE=" + "'"
					+ urt.getDriver_license().trim() + "'");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",ENGINE_ROTATE_SPEED=" + "'"
					+ urt.getEngine_rotate_speed().trim() + "'");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",MILEAGE=" + "'" + urt.getMileage().trim() + "'");
		}
		if (urt.getOil_instant() != null && !urt.getOil_instant().equals("")) {
			sql.append(",OIL_INSTANT=" + "'" + urt.getOil_instant().trim()
					+ "'");
		}
		if (urt.getOil_pressure() != null && !urt.getOil_pressure().equals("")) {
			sql.append(",OIL_PRESSURE=" + "'" + urt.getOil_pressure().trim()
					+ "'");
		}
		if (urt.getFire_up_state() != null
				&& !urt.getFire_up_state().equals("")) {
			sql.append(",FIRE_UP_STATE=" + "'" + urt.getFire_up_state().trim()
					+ "'");
		}
		if (urt.getPower_state() != null && !urt.getPower_state().equals("")) {
			sql.append(",POWER_STATE=" + "'" + urt.getPower_state().trim()
					+ "'");
		}
		if (urt.getBattery_voltage() != null
				&& !urt.getBattery_voltage().equals("")) {
			sql.append(",BATTERY_VOLTAGE=" + "'"
					+ urt.getBattery_voltage().trim() + "'");
		}
		if (urt.getGps_state() != null && !urt.getGps_state().equals("")) {
			sql.append(",GPS_STATE=" + "'" + urt.getGps_state().trim() + "'");
		}
		if (urt.getExt_voltage() != null && !urt.getExt_voltage().equals("")) {
			sql.append(",EXT_VOLTAGE=" + "'" + urt.getExt_voltage().trim()
					+ "'");
		}
		if (urt.getImg_process() != null && !urt.getImg_process().equals("")) {
			sql.append(",IMG_PROCESS=" + "'" + urt.getImg_process().trim()
					+ "'");
		}
		if (urt.getOil_total() != null && !urt.getOil_total().equals("")) {
			sql.append(",OIL_TOTAL=" + "'" + urt.getOil_total().trim() + "'");
		}
		if (urt.getE_water_temp() != null && !urt.getE_water_temp().equals("")) {
			sql.append(",E_WATER_TEMP=" + "'" + urt.getE_water_temp().trim()
					+ "'");
		}
		if (urt.getE_torque() != null && !urt.getE_torque().equals("")) {
			sql.append(",E_TORQUE=" + "'" + urt.getE_torque().trim() + "'");
		}
		if (urt.getQuad_id_type() != null && !urt.getQuad_id_type().equals("")) {
			sql.append(",QUAD_ID_TYPE=" + "'" + urt.getQuad_id_type().trim()
					+ "'");
		}
		if (urt.getRoute_info() != null && !urt.getRoute_info().equals("")) {
			sql.append(",ROUTE_INFO=" + "'" + urt.getRoute_info().trim() + "'");
		}
		if (urt.getMeg_resp_id() != null && !urt.getMeg_resp_id().equals("")) {
			sql.append(",MEG_RESP_ID=" + "'" + urt.getMeg_resp_id().trim()
					+ "'");
		}
		if (urt.getMeg_id() != null && !urt.getMeg_id().equals("")) {
			sql.append(",MEG_ID=" + "'" + urt.getMeg_id().trim() + "'");
		}
		if (urt.getMeg_type() != null && !urt.getMeg_type().equals("")) {
			sql.append(",MEG_TYPE=" + "'" + urt.getMeg_type().trim() + "'");
		}
		if (urt.getMeg_info() != null && !urt.getMeg_info().equals("")) {
			sql.append(",MEG_INFO=" + "'" + urt.getMeg_info().trim() + "'");
		}
		if (urt.getMeg_seq() != null && !urt.getMeg_seq().equals("")) {
			sql.append(",MEG_SEQ=" + "'" + urt.getMeg_seq().trim() + "'");
		}
		if (urt.getElevation() != null && !urt.getElevation().equals("")) {
			sql.append(",ELEVATION=" + "'" + urt.getElevation() + "'");
		}
		if (urt.getEcc_app() != null && !urt.getEcc_app().equals("")) {
			sql.append(",EEC_APP=" + "'" + urt.getEcc_app() + "'");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")
				&& !urt.getSpeeding().equals(Constant.F4)) {
			String ratio = AccountUtil.accountRatio(urt.getTerminalId(), urt
					.getSpeeding(), urt.getEngine_rotate_speed());
			String gears = AccountUtil.accountGears(urt.getTerminalId(), urt
					.getSpeeding(), urt.getEngine_rotate_speed());
			if (ratio != null && !ratio.equals("")) {
				sql.append(",RATIO=" + "'" + ratio + "'");
			}
			if (gears != null && !gears.equals("")) {
				if (gears.equals("12")) {
					gears = "R";
				}
				sql.append(",GEARS=" + "'" + gears + "'");

			}
		} else {
			if (urt.getVin_speed() != null && !urt.getVin_speed().equals("")) {
				if (!urt.getVin_speed().equals(Constant.F4)) {
					String ratio = AccountUtil.accountRatio(
							urt.getTerminalId(), urt.getVin_speed(), urt
									.getEngine_rotate_speed());
					String gears = AccountUtil.accountGears(
							urt.getTerminalId(), urt.getVin_speed(), urt
									.getEngine_rotate_speed());
					if (ratio != null && !ratio.equals("")) {
						sql.append(",RATIO=" + "'" + ratio + "'");
					}
					if (gears != null && !gears.equals("")) {
						if (gears.equals("12")) {
							gears = "R";
						}
						sql.append(",GEARS=" + "'" + gears + "'");
					}
				}
			}
		}
		if (urt.getVin_speed() != null && !urt.getVin_speed().equals("")) {
			sql.append(",VEHICLE_SPEED='" + urt.getVin_speed().trim() + "'");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")
				&& !urt.getMileage().equals(Constant.F4)) {
			sql.append(",PULSE_MILEAGE=" + "'" + urt.getMileage() + "'");
		} else {
			if (urt.getPulse_mileage() != null
					&& !urt.getPulse_mileage().equals("")) {
				sql.append(",PULSE_MILEAGE=" + "'" + urt.getPulse_mileage()
						+ "'");
			}
		}
		if (urt.getAlarm_state() != null && !urt.getAlarm_state().equals("")) {
			sql.append(",ALARM_BASE_INFO='" + urt.getAlarm_state() + "'");
		}
		if (urt.getXcstate() != null && !urt.getXcstate().equals("")) {
			sql.append(",ALARM_EXT_INFO='" + urt.getXcstate() + "'");
		}
		if (urt.getStatus_bit() != null && !urt.getStatus_bit().equals("")) {
			sql.append(",STAT_INFO='" + urt.getStatus_bit() + "'");
		}
		if (urt.getEnginetime() != null && !urt.getEnginetime().equals("")) {
			sql.append(",E_RUN_TIME='" + urt.getEnginetime() + "'");
		}
		if (urt.getEngineoiltemperature() != null
				&& !urt.getEngineoiltemperature().equals("")) {
			sql.append(",OIL_TEMPERATURE='" + urt.getEngineoiltemperature()
					+ "'");
		}
		if (urt.getEnginecoolanttemperature() != null
				&& !urt.getEnginecoolanttemperature().equals("")) {
			sql.append(",COLDER_TEMPERATURE='"
					+ urt.getEnginecoolanttemperature() + "'");
		}
		if (urt.getAirinlettemperature() != null
				&& !urt.getAirinlettemperature().equals("")) {
			sql.append(",AIR_PRESSURE='" + urt.getAirinlettemperature() + "'");
		}
		if (urt.getBarometricpressure() != null
				&& !urt.getBarometricpressure().equals("")) {
			sql.append(",AIR_INFLOW_TPR='" + urt.getBarometricpressure() + "'");
		}
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			String overload = (String)Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.OVERLOAD);
			if(overload!=null&&!overload.equals("")){
				sql.append(",OVERLOAD_FLAG='"+overload+"'");
//				String stu_num = (String)Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.ShuaKaTerminal);
//				if(stu_num!=null&&!stu_num.equals("")){
//					sql.append(",STU_NUM =to_number("+stu_num+")");
//				}
			}
		}else{
			String overload = (String)Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.OVERLOAD);
			if(overload!=null&&!overload.equals("")){
				sql.append(",OVERLOAD_FLAG='"+overload+"'");
//				String stu_num = (String)Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.ShuaKaTerminal);
//				if(stu_num!=null&&!stu_num.equals("")){
//					sql.append(",STU_NUM=to_number("+stu_num+")");
//				}
			}
		}
		if(urt.getStu_num()!=null&&!urt.getStu_num().equals("")){
			sql.append(",STU_NUM="+Integer.parseInt(urt.getStu_num(),16));
		}else{
			sql.append(",STU_NUM=''");
		}
		if(urt.getSite_id()!=null&&!urt.getSite_id().equals("")&&!urt.getSite_id().equals("FFFFFFFF")){
			sql.append(",SITE_ID="+Integer.parseInt(urt.getSite_id(),16));
		}else{
			sql.append(",SITE_ID=''");
		}
		if(urt.getRoute_id()!=null&&!urt.getRoute_id().equals("")&&!urt.getRoute_id().equals("FFFFFFFF")){
			sql.append(",ROUTE_ID="+Integer.parseInt(urt.getRoute_id(),16));
		}else{
			sql.append(",ROUTE_ID=''");
		}
		if(urt.getDrivingper()!=null&&!urt.getDrivingper().equals("")){
			sql.append(",INSTANCE_PERCENT='"+Integer.parseInt(urt.getDrivingper(),16)+"'");
		}
		if(urt.getCur_tea()!=null&&!urt.getCur_tea().equals("")){
			if(!urt.getCur_tea().equals("FFFFFFFF")){
				sql.append(",CURRENT_TEACHER='"+Integer.parseInt(urt.getCur_tea(),16)+"'");
			}else{
				sql.append(",CURRENT_TEACHER='"+Constant.F4+"'");
			}
		}else{
			sql.append(",CURRENT_TEACHER=''");
		}
		if(urt.getSpeed_source_setting()!=null&&!urt.getSpeed_source_setting().equals("")){
			sql.append(",SPEED_SOURCE_SETTING='"+Integer.parseInt(urt.getSpeed_source_setting(),16)+"'");
		}
		if(urt.getTrip_id()!=null&&!urt.getTrip_id().equals("")&&!urt.getTrip_id().equals("FFFFFFFF")){
			sql.append(",TRIP_ID='"+Integer.parseInt(urt.getTrip_id(),16)+"'");
		}else{
			sql.append(",TRIP_ID=''");
		}
		if(urt.getCharacter_oeffocient_status()!=null&&!urt.getCharacter_oeffocient_status().equals("")){
			sql.append(",CHARACTER_OEFFICIENT_STATUS='"+urt.getCharacter_oeffocient_status()+"'");
		}
		if(urt.getDevice_default_list()!=null&&!urt.getDevice_default_list().equals("")){
			sql.append(",DEVICE_FAULT_LIST='"+urt.getDevice_default_list()+"'");
		}
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.info("<BuildSQL>解析上行实时报文,更新终端信息表:" + sql.toString());
		return sql.toString();
	}

	// public String buildUpdateTerminalRecordSql(Up_InfoContent urt) {
	// StringBuffer sql = new StringBuffer();
	// sql.append("update CLW_YW_TERMINAL_RECORD_T set ");
	// // CREATE_TIME 目前存储为String类型
	// sql.append("UPDATE_TIME=" + "to_date("
	// + DateUtil.changeTime12ToFormat()
	// + ",'yymmddhh24miss')");
	// if (urt.getSos() != null && !urt.getSos().equals("")) {
	// sql.append(",SOS=" + "'" + StringUtils.trimToEmpty(urt.getSos())
	// + "'");
	// }
	// if (urt.getOverspeed_alert() != null
	// && !urt.getOverspeed_alert().equals("")) {
	// sql.append(",OVERSPEED_ALERT=" + "'"
	// + StringUtils.trimToEmpty(urt.getOverspeed_alert()) + "'");
	// }
	// if (urt.getFatigue_alert() != null
	// && !urt.getFatigue_alert().equals("")) {
	// sql.append(",FATIGUE_ALERT=" + "'"
	// + StringUtils.trimToEmpty(urt.getFatigue_alert()) + "'");
	// }
	// if (urt.getGps_alert() != null && !urt.getGps_alert().equals("")) {
	// sql.append(",SHOW_SPEED_ALERT=" + "'"
	// + StringUtils.trimToEmpty(urt.getShow_speed_alert()) + "'");
	// }
	// if(urt.getRapid_alert()!=null&&!urt.getRapid_alert().equals("")){
	// sql.append(",RAPID_ALERT='"+StringUtils.trimToEmpty(urt.getRapid_alert())+"'");
	// }
	// if(urt.getRegion_overspeed_alert()!=null&&!urt.getRegion_overspeed_alert().equals("")){
	// sql.append(",REGION_OVERSPEED_ALERT ='"+StringUtils.trimToEmpty(urt.getRegion_overspeed_alert())+"'");
	// }
	// if(urt.getRegion_in_alert()!=null&&!urt.getRegion_in_alert().equals("")){
	// sql.append(",REGION_IN_ALERT='"+StringUtils.trimToEmpty(urt.getRegion_in_alert())+"'");
	// }
	// if(urt.getRegion_out_alert()!=null&&!urt.getRegion_out_alert().equals("")){
	// sql.append(",REGION_OUT_ALERT='"+StringUtils.trimToEmpty(urt.getRegion_out_alert())+"'");
	// }
	// if(urt.getRegion_openclose_alert()!=null&&!urt.getRegion_openclose_alert().equals("")){
	// sql.append(",REGION_OPENCLOSEDOOR_ALERT='"+StringUtils.trimToEmpty(urt.getRegion_openclose_alert())+"'");
	// }
	// sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
	// log.debug(LogFormatter.formatMsg("BuildSQL","解析上行实时报文,更新终端信息流水表实时告警状态sql:"
	// + sql.toString()));
	// return sql.toString();
	// }

	/**
	 * 组装实时报文insert语句
	 * 
	 * @param urt
	 * @param id 
	 * @return
	 */
	public String buildInsertUp_RealTimeSql(Up_InfoContent urt, String id) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(urt.getTerminalId());
		
		sql.append("insert into CLW_YW_TERMINAL_RECORD_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, "
						+ "CREATE_TIME, UTC_TIME, TERMINAL_TIME, GPS_VALID, LATITUDE, LONGITUDE, ELEVATION, DIRECTION, "
						+ "GPS_SPEEDING, SPEEDING, ON_OFF, SOS, OVERSPEED_ALERT, FATIGUE_ALERT, GPS_ALERT, "
						+ "SHOW_SPEED_ALERT, DRIVER_ID, DRIVER_LICENSE, ENGINE_ROTATE_SPEED, MILEAGE, OIL_INSTANT, "
						+ "OIL_PRESSURE, TORQUE_PERCENT, FIRE_UP_STATE, POWER_STATE, BATTERY_VOLTAGE, GPS_STATE, "
						+ "EXT_VOLTAGE, IMG_PROCESS, OIL_TOTAL, E_WATER_TEMP, E_TORQUE, QUAD_ID_TYPE, ROUTE_INFO, "
						+ "MEG_RESP_ID, MEG_ID, MEG_TYPE, MEG_INFO, MEG_SEQ, RATIO, GEARS, EEC_APP, VEHICLE_SPEED, "
						+ "PULSE_MILEAGE, ALARM_BASE_INFO,ALARM_EXT_INFO,STAT_INFO,VEH_EXT_INFO,E_RUN_TIME,"
						+ "OIL_TEMPERATURE,COLDER_TEMPERATURE,AIR_PRESSURE,AIR_INFLOW_TPR,ALERT_ID,OVERLOAD_FLAG,STU_NUM,SITE_ID,ROUTE_ID,INSTANCE_PERCENT"
						+ ",CURRENT_TEACHER,SPEED_SOURCE_SETTING,TRIP_ID,CHARACTER_OEFFICIENT_STATUS,DEVICE_FAULT_LIST) ");
		sql.append("values('"+id+"','");
		sql.append(tb.getTerminal_id() + "','");
		sql.append(urt.getTerminalId() + "','");
		sql.append(tb.getSim_card_number() + "',");
		sql.append("sysdate");
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")
				&& urt.getGps_valid().equals("1")) {
			if (urt.getUtc_time() != null && !urt.getUtc_time().equals("")) {
				sql.append(",to_date(" + urt.getUtc_time()
						+ ",'yymmddhh24miss')");
			} else {
				sql.append(",''");
			}
		} else {
			sql.append(",''");
		}
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")) {
			sql.append(",'" + urt.getGps_valid() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")
				&& urt.getGps_valid().equals("1")) {
			if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
				sql.append(",'" + urt.getLatitude() + "'");
			} else {
				sql.append(",''");
			}
			if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
				sql.append(",'" + urt.getLongitude() + "'");
			} else {
				sql.append(",''");
			}
		} else {
			sql.append(",'" + Constant.F4 + "'");
			sql.append(",'" + Constant.F4 + "'");
		}

		if (urt.getElevation() != null && !urt.getElevation().equals("")) {
			sql.append(",'" + urt.getElevation() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")
				&& urt.getGps_valid().equals("1")) {
			if (urt.getDirection() != null && !urt.getDirection().equals("")) {
				sql.append(",'" + urt.getDirection() + "'");
			} else {
				sql.append(",''");
			}
			if (urt.getGps_speeding() != null
					&& !urt.getGps_speeding().equals("")) {
				if(!urt.getGps_speeding().equals("FFFF")){
					sql.append(",'" + (!urt.getGps_speeding().trim().equals("0.0")?urt.getGps_speeding().trim():"0") + "'");
				}else{
					sql.append(",'0'");
				}
			} else {
				sql.append(",''");
			}
		} else {
			sql.append(",'FFFF'");
			sql.append(",'0'");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getOn_off() != null && !urt.getOn_off().equals("")) {
			sql.append(",'" + urt.getOn_off() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getSos() != null && !urt.getSos().equals("")) {
			sql.append(",'" + urt.getSos() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getOverspeed_alert() != null
				&& !urt.getOverspeed_alert().equals("")) {
			sql.append(",'" + urt.getOverspeed_alert() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getFatigue_alert() != null
				&& !urt.getFatigue_alert().equals("")) {
			sql.append(",'" + urt.getFatigue_alert() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getGps_alert() != null && !urt.getGps_alert().equals("")) {
			sql.append(",'" + urt.getGps_alert() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getShow_speed_alert() != null
				&& !urt.getShow_speed_alert().equals("")) {
			sql.append(",'" + urt.getShow_speed_alert() + "'");
		} else {
			sql.append(",''");
		}
		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")){
			if(!urt.getDriver_id().equals("FFFFFFFF")){
				sql.append(",'"+Long.parseLong(urt.getDriver_id(),16)+"'");
			}else{
				sql.append(",'"+Constant.F4+"'");
			}
		}else{
			sql.append(",''");
		}
//		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
//			String driver_id = (String) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}else{
//			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}
		if (urt.getDriver_license() != null
				&& !urt.getDriver_license().equals("")) {
			sql.append(",'" + urt.getDriver_license() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getOil_instant() != null && !urt.getOil_instant().equals("")) {
			sql.append(",'" + urt.getOil_instant() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getOil_pressure() != null && !urt.getOil_pressure().equals("")) {
			sql.append(",'" + urt.getOil_pressure() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getTorque_percent() != null
				&& !urt.getTorque_percent().equals("")) {
			sql.append(",'" + urt.getTorque_percent() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getFire_up_state() != null
				&& !urt.getFire_up_state().equals("")) {
			sql.append(",'" + urt.getFire_up_state() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getPower_state() != null && !urt.getPower_state().equals("")) {
			sql.append(",'" + urt.getPower_state() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBattery_voltage() != null
				&& !urt.getBattery_voltage().equals("")) {
			sql.append(",'" + urt.getBattery_voltage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getGps_state() != null && !urt.getGps_state().equals("")) {
			sql.append(",'" + urt.getGps_state() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getExt_voltage() != null && !urt.getExt_voltage().equals("")) {
			sql.append(",'" + urt.getExt_voltage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getImg_process() != null && !urt.getImg_process().equals("")) {
			sql.append(",'" + urt.getImg_process() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getOil_total() != null && !urt.getOil_total().equals("")) {
			sql.append(",'" + urt.getOil_total() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getE_water_temp() != null && !urt.getE_water_temp().equals("")) {
			sql.append(",'" + urt.getE_water_temp() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getE_torque() != null && !urt.getE_torque().equals("")) {
			sql.append(",'" + urt.getE_torque() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getQuad_id_type() != null && !urt.getQuad_id_type().equals("")) {
			sql.append(",'" + urt.getQuad_id_type() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getRoute_info() != null && !urt.getRoute_info().equals("")) {
			sql.append(",'" + urt.getRoute_info() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMeg_resp_id() != null && !urt.getMeg_resp_id().equals("")) {
			sql.append(",'" + urt.getMeg_resp_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMeg_id() != null && !urt.getMeg_id().equals("")) {
			sql.append(",'" + urt.getMeg_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMeg_type() != null && !urt.getMeg_type().equals("")) {
			sql.append(",'" + urt.getMeg_type() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMeg_info() != null && !urt.getMeg_info().equals("")) {
			sql.append(",'" + urt.getMeg_info() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMeg_seq() != null && !urt.getMeg_seq().equals("")) {
			sql.append(",'" + urt.getMeg_seq() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")
				&& !urt.getSpeeding().equals(Constant.F4)) {
			String ratio = AccountUtil.accountRatio(urt.getTerminalId(), urt
					.getSpeeding(), urt.getEngine_rotate_speed());
			String gears = AccountUtil.accountGears(urt.getTerminalId(), urt
					.getSpeeding(), urt.getEngine_rotate_speed());
			if (ratio != null && !ratio.equals("")) {
				sql.append(",'" + ratio + "'");
			} else {
				sql.append(",''");
			}
			if (gears != null && !gears.equals("")) {
				if (gears.equals("12")) {
					gears = "R";
				}
				sql.append(",'" + gears + "'");
			} else {
				sql.append(",''");
			}
		} else {
			if (urt.getVin_speed() != null && !urt.getVin_speed().equals("")
					&& !urt.getVin_speed().equals(Constant.F4)) {
				String ratio = AccountUtil.accountRatio(urt.getTerminalId(),
						urt.getVin_speed(), urt.getEngine_rotate_speed());
				String gears = AccountUtil.accountGears(urt.getTerminalId(),
						urt.getVin_speed(), urt.getEngine_rotate_speed());
				if (ratio != null && !ratio.equals("")) {
					sql.append(",'" + ratio + "'");
				} else {
					sql.append(",''");
				}
				if (gears != null && !gears.equals("")) {
					if (gears.equals("12")) {
						gears = "R";
					}
					sql.append(",'" + gears + "'");
				} else {
					sql.append(",''");
				}
			} else {
				sql.append(",''");
				sql.append(",''");
			}
		}
		if (urt.getEcc_app() != null && !urt.getEcc_app().equals("")) {
			sql.append(",'" + urt.getEcc_app() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getVin_speed() != null && !urt.getVin_speed().equals("")) {
			sql.append(",'" + urt.getVin_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getPulse_mileage() != null
				&& !urt.getPulse_mileage().equals("")) {
			sql.append(",'" + urt.getPulse_mileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarm_state() != null && !urt.getAlarm_state().equals("")) {
			sql.append(",'"
					+ urt.getAlarm_state() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getXcstate() != null && !urt.getXcstate().equals("")) {
			sql.append(",'" + urt.getXcstate() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStatus_bit() != null && !urt.getStatus_bit().equals("")) {
			sql.append(",'" + urt.getStatus_bit() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getXcononff() != null && !urt.getXcononff().equals("")) {
			sql.append(",'" + urt.getXcononff() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEnginetime() != null && !urt.getEnginetime().equals("")) {
			sql.append(",'" + urt.getEnginetime() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngineoiltemperature() != null
				&& !urt.getEngineoiltemperature().equals("")) {
			sql.append(",'" + urt.getEngineoiltemperature() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEnginecoolanttemperature() != null
				&& !urt.getEnginecoolanttemperature().equals("")) {
			sql.append(",'" + urt.getEnginecoolanttemperature() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAirinlettemperature() != null
				&& !urt.getAirinlettemperature().equals("")) {
			sql.append(",'" + urt.getAirinlettemperature() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBarometricpressure() != null
				&& !urt.getBarometricpressure().equals("")) {
			sql.append(",'" + urt.getBarometricpressure() + "'");
		} else {
			sql.append(",''");
		}
		if(urt.getAlarm_seq()!=null&&!urt.getAlarm_seq().equals("")){
			sql.append(",'"+urt.getAlarm_seq()+"'");
		}else{
			sql.append(",''");
		}
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			Object overload = (Object)Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.OVERLOAD);
			if(overload!=null&&!overload.equals("")){
				sql.append(",'"+(String)overload+"'");
			}else{
				sql.append(",''");
			}
//			String stu_num = (String)Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.ShuaKaTerminal);
//			if(stu_num!=null&&!stu_num.equals("")){
//				sql.append(",to_number("+stu_num+")");
//			}else{
//				sql.append(",0");
//			}
		}else{
			String overload = (String)Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.OVERLOAD);
			if(overload!=null&&!overload.equals("")){
				sql.append(",'"+overload+"'");
			}else{
				sql.append(",''");
			}
//			String stu_num = (String)Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.ShuaKaTerminal);
//			if(stu_num!=null&&!stu_num.equals("")){
//				sql.append(",to_number("+stu_num+")");
//			}else{
//				sql.append(",0");
//			}
		}
		if(urt.getStu_num()!=null&&!urt.getStu_num().equals("")){
			sql.append(","+Integer.parseInt(urt.getStu_num(),16));
		}else{
			sql.append(",''");
		}
		if(urt.getSite_id()!=null&&!urt.getSite_id().equals("")&&!urt.getSite_id().equals("FFFFFFFF")){
			sql.append(","+Integer.parseInt(urt.getSite_id(),16));
		}else{
			sql.append(",''");
		}
		if(urt.getRoute_id()!=null&&!urt.getRoute_id().equals("")&&!urt.getRoute_id().equals("FFFFFFFF")){
			sql.append(","+Integer.parseInt(urt.getRoute_id(),16));
		}else{
			sql.append(",''");
		}
		if(urt.getDrivingper()!=null&&!urt.getDrivingper().equals("")){
			sql.append(",'"+Integer.parseInt(urt.getDrivingper(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getCur_tea()!=null&&!urt.getCur_tea().equals("")){
			if(!urt.getCur_tea().equals("FFFFFFFF")){
				sql.append(",'"+Integer.parseInt(urt.getCur_tea(),16)+"'");
			}else{
				sql.append(",'"+Constant.F4+"'");
			}
		}else{
			sql.append(",''");
		}
		if(urt.getSpeed_source_setting()!=null&&!urt.getSpeed_source_setting().equals("")){
			sql.append(",'"+Integer.parseInt(urt.getSpeed_source_setting(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getTrip_id()!=null&&!urt.getTrip_id().equals("")&&!urt.getTrip_id().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getTrip_id(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getCharacter_oeffocient_status()!=null&&!urt.getCharacter_oeffocient_status().equals("")){
			sql.append(",'"+urt.getCharacter_oeffocient_status()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getDevice_default_list()!=null&&!urt.getDevice_default_list().equals("")){
			sql.append(",'"+urt.getDevice_default_list()+"'");
		}else{
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<BuildSQL>解析上行实时报文,组装入库语句:" + sql.toString());
		tb = null;
		return sql.toString();
	}

	/**
	 * 拼装上行区域响应更新语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateUpSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_SEND_COMMAND_T ");
		sql.append("set DEAL_TIME= sysdate");
		if (urt.getState().equals("1")) {
			sql.append(",DEAL_STATE='" + num3 + "'");
		}
		sql.append(" where MSG_ID ='" + urt.getMsg_id() + "'");
		log.debug("<BuildSQL>解析上行区域响应报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装上行参数响应更新语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateUpAlarmParamSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_SEND_COMMAND_T ");
		sql.append("set DEAL_TIME= sysdate");
		if (urt.getState().equals("1")) {
			sql.append(",DEAL_STATE='" + num3 + "'");
		}
		sql.append(" where MSG_ID ='" + urt.getMsg_id() + "'");
		log.debug("<BuildSQL>解析上行参数响应报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装上行车辆响应更新语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateUp_VehicleSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_SEND_COMMAND_T ");
		sql.append("set DEAL_TIME= sysdate");
		if (urt.getState().equals("1")) {
			sql.append(",DEAL_STATE='" + num3 + "'");
		}
		sql.append(" where MSG_ID ='" + urt.getMsg_id() + "'");
		log.debug("<BuildSQL>解析上行参数响应报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateUp_MessageSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_SEND_COMMAND_T ");
		sql.append("set DEAL_TIME= sysdate");
		if (urt.getState().equals("1")) {
			sql.append(",DEAL_STATE='" + num3 + "'");
		}
		sql.append(" where MSG_ID ='" + urt.getMsg_id() + "'");
		log.debug("<BuildSQL>解析上行短消息响应报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装上行鉴权不通过通知消息更新语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateUpCerFailSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_CERTIFICATION_FAILE_T ");
		sql.append("set MODIFY_TIME= sysdate");
		sql.append(" where VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		sql.append(" AND SIM_CARD_NUMBER= '" + urt.getSim() + "'");
		sql.append(" and TERMINAL_ID= '" + urt.getTerminal_id() + "'");
		log.debug("<BuildSQL>解析上行鉴权不通过通知消息报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装上行参数信息消息更新终端参数信息表语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalParamSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql.append("update CLW_JC_TERMINAL_PARA_T ");
		sql.append("set UPDATE_TIME= sysdate");
		if (urt.getType().equals("1")) {
			if (urt.getMsg_center() != null && !urt.getMsg_center().equals("")) {
				sql.append(",MSG_CENTER='" + urt.getMsg_center() + "'");
			}
			if (urt.getApn() != null && !urt.getApn().equals("")) {
				sql.append(",APN='" + urt.getApn() + "'");
			}
			if (urt.getServer_ip() != null && !urt.getServer_ip().equals("")) {
				sql.append(",SERVER_IP='" + urt.getServer_ip() + "'");
			}
			if (urt.getServer_port() != null
					&& !urt.getServer_port().equals("")) {
				sql.append(",SERVER_PORT='" + urt.getServer_port() + "'");
			}
			if (urt.getReceive_time() != null
					&& !urt.getReceive_time().equals("")) {
				sql.append(",RECEIVER_TIME='" + urt.getReceive_time() + "'");
			}
		} else if (urt.getType().equals("2")) {
			if (urt.getTime_answers() != null
					&& !urt.getTime_answers().equals("")) {
				sql.append(",TIME_ANSWERS="
						+ Long.valueOf(urt.getTime_answers(), 16) + "");
			}
			if (urt.getSpacing_answers() != null
					&& !urt.getSpacing_answers().equals("")) {
				sql.append(",SPACING_ANSWERS="
						+ Long.valueOf(urt.getSpacing_answers(), 16) + "");
			}
			if (urt.getKeepalive_time() != null
					&& !urt.getKeepalive_time().equals("")) {
				sql.append(",KEEPALIVE_TIME="
						+ Long.valueOf(urt.getKeepalive_time(), 16) + "");
			}
			if (urt.getKeepalive_overtime() != null
					&& !urt.getKeepalive_overtime().equals("")) {
				sql.append(",KEEPALIVE_OVERTIME="
						+ Long.valueOf(urt.getKeepalive_overtime(), 16) + "");
			}
			if (urt.getStalled_time_answers() != null
					&& !urt.getStalled_time_answers().equals("")) {
				sql.append(",STALLED_TIME_ANSWERS="
						+ Long.valueOf(urt.getStalled_time_answers(), 16) + "");
			}
		} else if (urt.getType().equals("3")) {
			if (urt.getOverspeed() != null && !urt.getOverspeed().equals("")) {
				sql.append(",OVERSPEED=" + Long.valueOf(urt.getOverspeed(), 16)
						+ "");
			}
			if (urt.getOverspeed_diff() != null
					&& !urt.getOverspeed_diff().equals("")) {
				sql.append(",OVERSPEED_DIFF="
						+ Long.valueOf(urt.getOverspeed_diff(), 16) + "");
			}
			if (urt.getOverspeed_keep() != null
					&& !urt.getOverspeed_keep().equals("")) {
				sql.append(",OVERSPEED_KEEP="
						+ Long.valueOf(urt.getOverspeed_keep(), 16) + "");
			}
			if (urt.getDriving_fatigue() != null
					&& !urt.getDriving_fatigue().equals("")) {
				sql.append(",DRIVING_FATIGUE="
						+ Long.valueOf(urt.getDriving_fatigue(), 16) + "");
			}
			if (urt.getDriving_fatigue_diff() != null
					&& !urt.getDriving_fatigue_diff().equals("")) {
				sql.append(",DRIVING_FATIGUE_DIFF="
						+ Long.valueOf(urt.getDriving_fatigue_diff(), 16) + "");
			}
			if (urt.getDriving_fatigue_rest() != null
					&& !urt.getDriving_fatigue_rest().equals("")) {
				sql.append(",DRIVING_FATIGUE_REST="
						+ Long.valueOf(urt.getDriving_fatigue_rest(), 16) + "");
			}
		} else if (urt.getType().equals("4")) {
			if (urt.getIndex_property() != null
					&& !urt.getIndex_property().equals("")) {
				sql.append(",INDEX_PROPERTY="
						+ Long.valueOf(urt.getIndex_property()) + "");
			}
			if (urt.getPulse_per_second() != null
					&& !urt.getPulse_per_second().equals("")) {
				sql.append(",PULSE_PER_SECOND="
						+ Long.valueOf(urt.getPulse_per_second()) + "");
			}
			if (urt.getEngine_gear() != null
					&& !urt.getEngine_gear().equals("")) {
				sql.append(",ENGINE_GEAR=" + urt.getEngine_gear() + "");
			}
			if (urt.getVehicle_ln() != null && !urt.getVehicle_ln().equals("")) {
				sql.append(",VEHICLE_LN='" + urt.getVehicle_ln() + "'");
			}
			if (urt.getVehicle_no() != null && !urt.getVehicle_no().equals("")) {
				sql.append(",VEHICLE_NO='" + urt.getVehicle_no() + "'");
			}
			if (urt.getVehicle_sort() != null
					&& !urt.getVehicle_sort().equals("")) {
				sql.append(",VEHICLE_SORT='" + urt.getVehicle_sort() + "'");
			}
			if (urt.getVehicle_vin() != null
					&& !urt.getVehicle_vin().equals("")) {
				sql.append(",VEHICLE_VIN='" + urt.getVehicle_vin() + "'");
			}
			if (urt.getSleep_time() != null && !urt.getSleep_time().equals("")) {
				sql.append(",SLEEP_TIME="
						+ Long.valueOf(urt.getSleep_time(), 16) + "");
			}
			if (urt.getOut1tiem() != null && !urt.getOut1tiem().equals("")) {
				sql.append(",OUT1TIME=to_date(" + urt.getOut1tiem()
						+ ",'yymmddhh24miss')");
			}
		}
		sql.append(" where TERMINAL_ID ='" + tb.getTerminal_id() + "' ");
		// sql.append("and VEHICLE_VIN='" + urt.getTerminalId() + "' ");
		log.debug("<BuildSQL>解析上行参数响应报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 组装上行报文insert语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertUpParamSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_PARA_RECORD_T (ID, TERMINAL_ID, UPDATE_TIME, MSG_CENTER, APN, SERVER_IP, "
						+ "SERVER_PORT, RECEIVER_TIME, TIME_ANSWERS, SPACING_ANSWERS, KEEPALIVE_TIME, KEEPALIVE_OVERTIME,"
						+ " STALLED_TIME_ANSWERS, OVERSPEED, OVERSPEED_DIFF, OVERSPEED_KEEP, DRIVING_FATIGUE, "
						+ "DRIVING_FATIGUE_DIFF, DRIVING_FATIGUE_REST, INDEX_PROPERTY, PULSE_PER_SECOND, ENGINE_GEAR, "
						+ "VEHICLE_LN, VEHICLE_NO, VEHICLE_SORT, VEHICLE_VIN, SLEEP_TIME,OUT1TIME)");
		
		//sql.append("values('" + urt.getMsg_id() + "','");
		
		if(urt.getMsg_id() != null && !"".equals(urt.getMsg_id().trim())){
			sql.append("values('" + urt.getMsg_id().trim() + "','");
		}else{
			sql.append("values(SYS_GUID(),'");
		}		
		
		sql.append(tb.getTerminal_id() + "',");
		sql.append("sysdate");
		if (urt.getMsg_center() != null && !urt.getMsg_center().equals("")) {
			sql.append(",'" + urt.getMsg_center() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getApn() != null && !urt.getApn().equals("")) {
			sql.append(",'" + urt.getApn() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServer_ip() != null && !urt.getServer_ip().equals("")) {
			sql.append(",'" + urt.getServer_ip() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServer_port() != null && !urt.getServer_port().equals("")) {
			sql.append(",'" + urt.getServer_port() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getReceive_time() != null && !urt.getReceive_time().equals("")) {
			sql.append(",'" + urt.getReceive_time() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getTime_answers() != null && !urt.getTime_answers().equals("")) {
			sql.append("," + Long.valueOf(urt.getTime_answers(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSpacing_answers() != null
				&& !urt.getSpacing_answers().equals("")) {
			sql.append("," + Long.valueOf(urt.getSpacing_answers(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getKeepalive_time() != null
				&& !urt.getKeepalive_time().equals("")) {
			sql.append("," + Long.valueOf(urt.getKeepalive_time(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getKeepalive_overtime() != null
				&& !urt.getKeepalive_overtime().equals("")) {
			sql.append("," + Long.valueOf(urt.getKeepalive_overtime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getStalled_time_answers() != null
				&& !urt.getStalled_time_answers().equals("")) {
			sql.append("," + Long.valueOf(urt.getStalled_time_answers(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getOverspeed() != null && !urt.getOverspeed().equals("")) {
			sql.append("," + Long.valueOf(urt.getOverspeed(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getOverspeed_diff() != null
				&& !urt.getOverspeed_diff().equals("")) {
			sql.append("," + Long.valueOf(urt.getOverspeed_diff(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getOverspeed_keep() != null
				&& !urt.getOverspeed_keep().equals("")) {
			sql.append("," + Long.valueOf(urt.getOverspeed_keep(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getDriving_fatigue() != null
				&& !urt.getDriving_fatigue().equals("")) {
			sql.append("," + Long.valueOf(urt.getDriving_fatigue(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getDriving_fatigue_diff() != null
				&& !urt.getDriving_fatigue_diff().equals("")) {
			sql.append("," + Long.valueOf(urt.getDriving_fatigue_diff(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getDriving_fatigue_rest() != null
				&& !urt.getDriving_fatigue_rest().equals("")) {
			sql.append("," + Long.valueOf(urt.getDriving_fatigue_rest(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getIndex_property() != null
				&& !urt.getIndex_property().equals("")) {
			sql.append("," + Long.valueOf(urt.getIndex_property()));
		} else {
			sql.append(",0");
		}
		if (urt.getPulse_per_second() != null
				&& !urt.getPulse_per_second().equals("")) {
			sql.append("," + Long.valueOf(urt.getPulse_per_second()));
		} else {
			sql.append(",0");
		}
		if (urt.getEngine_gear() != null && !urt.getEngine_gear().equals("")) {
			sql.append("," + Long.valueOf(urt.getEngine_gear()));
		} else {
			sql.append(",0");
		}
		if (urt.getVehicle_ln() != null && !urt.getVehicle_ln().equals("")) {
			sql.append(",'" + urt.getVehicle_ln() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getVehicle_no() != null && !urt.getVehicle_no().equals("")) {
			sql.append(",'" + urt.getVehicle_no() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getVehicle_sort() != null && !urt.getVehicle_sort().equals("")) {
			sql.append(",'" + urt.getVehicle_sort() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getVehicle_vin() != null && !urt.getVehicle_vin().equals("")) {
			sql.append(",'" + urt.getVehicle_vin() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getSleep_time() != null && !urt.getSleep_time().equals("")) {
			sql.append("," + Long.valueOf(urt.getSleep_time(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getOut1tiem() != null && !urt.getOut1tiem().equals("")) {
			sql.append(",to_date(" + urt.getOut1tiem() + ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<BuildSQL>解析上行参数报文，组装入库语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 部分事件通用应答更新
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateSendCmdSql(Up_InfoContent urt, String msg_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_SEND_COMMAND_T ");
		sql.append("set DEAL_STATE= '" + urt.getDeal_state() + "',");
		sql.append("DEAL_TIME=sysdate");
		sql.append(" where MSG_ID ='" + msg_id.toLowerCase() + "'");
		log.debug("<BuildSQL>解析报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}	
	/**
	 * SOS告警解除
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateSOSAlarmSql(Up_InfoContent urt, String msg_id) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T ");
		sql.append("set DEAL_FLAG= '1',");
		sql.append("ALARM_END_TIME=sysdate");
		sql.append(" where VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		sql.append(" and alarm_type_id = '40'");
		sql.append(" and deal_flag = '2'");
		log.debug("<BuildSQL>解析报文，组装更新入库语句：" + sql.toString());
		return sql.toString();
	}
	
	/**
	 * 拼装上行图片消息插入语句
	 * 
	 * @param urt
	 * @return
	 * @throws ParseException
	 */
	public String buildInsertUpPhotoSql(Up_InfoContent urt)
			throws ParseException {
		StringBuffer sql = new StringBuffer();
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		
		sql.append("insert into CLW_YW_PHOTO_T (PHOTO_ID, VEHICLE_VIN, SIM_CARD_NUMBER, PHOTO_TIME, "
						+ "CHANNEL_NUMBER, PHOTO_FILE, OPERATE_USER_ID, OPERATE_TIME, UTC_TIME, GPS_VALID, LATITUDE, "
						+ "LONGITUDE, DIRECTION, GPS_SPEEDING,PHOTO_EVENT)");
		
		//根据拍照事件，调用不同的序列号，只有手工拍照是终端上传，其他全部用系统UUID实现
		//0平台下发指令,1定时动作,2抢劫报警触发,3碰撞侧翻报警触发,4门开拍照,5门关拍照,6车门由开变关，时速从＜20公里超过20公里		
		
		log.debug("---获得图片上传标识--->>:"+urt.getPhoto_event());
		if (urt.getPhoto_event() != null && !urt.getPhoto_event().equals("")) {
			//如果是手工拍照，用终端上报的ID
			if(urt.getPhoto_event().equals("0")){
				sql.append("values('" + urt.getMsg_id().trim() + "','");
			}else{
				sql.append("values(SYS_GUID(),'");
			}
		}
		sql.append(urt.getTerminalId().trim() + "','");
		sql.append(tb.getSim_card_number() + "',");
		sql.append("to_date(" + urt.getPhoto_time().trim()
				+ ",'yymmddhh24miss')");
		if (urt.getChannel_number() != null
				&& !urt.getChannel_number().equals("")) {
			sql.append(",'" + Integer.parseInt(urt.getChannel_number().trim(),16) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getPhoto_file() != null && !urt.getPhoto_file().equals("")) {
			sql.append(",'" + urt.getPhoto_file().trim() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'clw_tqc'");
		sql.append(",sysdate");
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")
				&& urt.getGps_valid().equals("1")) {
			if (urt.getUtc_time() != null && !urt.getUtc_time().equals("")) {
				sql.append(",to_date(" + urt.getUtc_time().trim()
						+ ",'yymmddhh24miss')");
			} else {
				sql.append(",''");
			}
			sql.append(",'" + urt.getGps_valid() + "'");
			if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
				sql.append(",'" + urt.getLatitude().trim() + "'");
			} else {
				sql.append(",''");
			}
			if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
				sql.append(",'" + urt.getLongitude().trim() + "'");
			} else {
				sql.append(",''");
			}
			if (urt.getDirection() != null && urt.getDirection().equals("")) {
				sql.append(",'" + urt.getDirection().trim() + "'");
			} else {
				sql.append(",''");
			}
			if (urt.getGps_speeding() != null
					&& !urt.getGps_speeding().equals("")) {
				sql.append(",'" + urt.getGps_speeding().trim() + "'");
			} else {
				sql.append(",''");
			}
		} else {
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
			sql.append(",''");
		}
		if (urt.getPhoto_event() != null && !urt.getPhoto_event().equals("")) {
			sql.append(",'" + urt.getPhoto_event() + "')");
		} else {
			sql.append(",'')");
		}
		log.debug("<BuildSQL>解析上行图片报文，组装插入语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 拼装上行鉴权不通过通知消息插入语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertUpCerFailSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into CLW_YW_CERTIFICATION_FAILE_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER,CREATE_TIME)");
		sql.append(" values(SYS_GUID(),'");
		sql.append(urt.getTerminal_id() + "','");
		sql.append(urt.getVehicle_vin() + "','");
		sql.append(urt.getSim() + "',");
		sql.append("sysdate");
		sql.append(")");
		log.debug("<BuildSQL>解析上行鉴权不通过通知消息报文，组装插入语句：" + sql.toString());
		return sql.toString();
	}

	// 告警sql

	/**
	 * sos告警insert sql
	 */
	public String buildInsertSosAlarmSql(Up_InfoContent urt, String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,ZONENAME,DRIVER_ID)");
//		sql.append(" values(");
		sql.append(" select ");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getSos_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time() + ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getSos() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null && !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		sql.append("'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "', ");
		/*sql.append(" , nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append(" from ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  tqc_trip_execute tre, ");
		sql.append(" (select nvl(max(t.trip_id),1) as trip_id ");
		sql.append(" from tqc_trip_execute t ");
		sql.append(" where t.vehicle_vin = '" + urt.getVehicle_vin() + "') triper ");
		sql.append(" where tre.trip_id(+) = triper.trip_id) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append(" from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getVehicle_vin() + "' ");
		sql.append(" and v.driver_id = d.driver_id) veh_d ");*/
       
		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")&&!urt.getDriver_id().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getDriver_id(),16)+"'");
		}else{
			//sql.append(",''");
			sql.append(" ,(select veh_d.driver_id ");
			sql.append(" from ");
			sql.append(" (select d.driver_id ");
			sql.append(" from distinct_driver v,clw_yw_driver_t d ");
			sql.append(" where v.vehicle_vin = '" + urt.getVehicle_vin() + "' ");
			sql.append(" and v.driver_id = d.driver_id) veh_d )");
		}

		log.debug("<BuildSQL>sos告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * sos告警update sql
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateSosAlarmSql(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + map.get("alarm_state") + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_TYPE_ID ='" + urt.getSos_alarm_type_id() + "'");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		sql.append(" and ALARM_END_TIME is null");
		log.debug("<BuildSQL>sos告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 *超速告警insert sql
	 */
	public String buildInsertOverSpeedAlarmSql(Up_InfoContent urt, String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,zonename,DRIVER_ID) ");
		sql.append("select ");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getOverspeed_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getOverspeed_alert() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		sql.append("'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "', ");
		sql.append(" , nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append(" from ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  tqc_trip_execute tre, ");
		sql.append(" (select nvl(max(t.trip_id),1) as trip_id ");
		sql.append(" from tqc_trip_execute t ");
		sql.append(" where t.vehicle_vin = '" + urt.getVehicle_vin() + "') triper ");
		sql.append(" where tre.trip_id(+) = triper.trip_id) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append(" from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getVehicle_vin() + "' ");
		sql.append(" and v.driver_id = d.driver_id) veh_d ");
//		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
//			Object driver_id = (Object) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+(String)driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}else{
//			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}
//		sql.append(")");
		log.debug("<BuildSQL>超速告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 *超速告警update sql
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateOverSpeedAlarmSql(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + map.get("overspeed_alert") + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ='" + urt.getOverspeed_alarm_type_id()
				+ "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>超速告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 *疲劳驾驶告警insert sql
	 */
	public String buildInsertFatigueAlarmSql(Up_InfoContent urt, String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,DRIVER_ID) values(");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getFatigue_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getFatigue_alert() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
			Object driver_id = (Object) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+(String)driver_id+"'");
			}else{
				sql.append(",''");
			}
		}else{
			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+driver_id+"'");
			}else{
				sql.append(",''");
			}
		}
		sql.append(")");
		log.debug("<BuildSQL>疲劳驾驶告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 *疲劳驾驶告警update sql
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateFatigueAlarmSql(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + urt.getFatigue_alert() + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ='" + urt.getFatigue_alarm_type_id()
				+ "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>疲劳驾驶告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 *gps超速告警insert sql
	 */
	public String buildInsertGpsAlarmSql(Up_InfoContent urt, String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,zonename,DRIVER_ID)");
		sql.append("select ");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getGps_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getGps_alert() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		sql.append("'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "', ");
		sql.append(" , nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append(" from ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  tqc_trip_execute tre, ");
		sql.append(" (select nvl(max(t.trip_id),1) as trip_id ");
		sql.append(" from tqc_trip_execute t ");
		sql.append(" where t.vehicle_vin = '" + urt.getVehicle_vin() + "') triper ");
		sql.append(" where tre.trip_id(+) = triper.trip_id) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append(" from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getVehicle_vin() + "' ");
		sql.append(" and v.driver_id = d.driver_id) veh_d ");		
//		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
//			Object driver_id = (Object) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+(String)driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}else{
//			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}
//		sql.append(")");
		log.debug("<BuildSQL>gps超速告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 *gps超速告警update sql
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateGpsAlarmSql(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + urt.getGps_alert() + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ='" + urt.getGps_alarm_type_id() + "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>gps超速告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 急加/减速告警insert sql
	 * 
	 * @param urt
	 * @param uuid
	 * @return
	 */
	public String buildInsertRAPIDAlarmSql(Up_InfoContent urt, String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG"
						+ ",LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION) values(");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getRapid_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getRapid() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<BuildSQL>急加/减速告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 急加/减速告警update sql
	 * 
	 * @param urt
	 * @param map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateRAPIDAlarmSql(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + urt.getRapid() + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql
				.append(" and ALARM_TYPE_ID ='" + urt.getRapid_alarm_type_id()
						+ "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>急加/减速告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 区域超速告警insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertRegion_OverspeedAlarmSql(Up_InfoContent urt,
			String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,zonename,DRIVER_ID)");
		sql.append("select ");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getRegion_overspeed_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getRegion_overspeed_alert() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		sql.append("'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "', ");
		sql.append(" , nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append(" from ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  tqc_trip_execute tre, ");
		sql.append(" (select nvl(max(t.trip_id),1) as trip_id ");
		sql.append(" from tqc_trip_execute t ");
		sql.append(" where t.vehicle_vin = '" + urt.getVehicle_vin() + "') triper ");
		sql.append(" where tre.trip_id(+) = triper.trip_id) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append(" from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getVehicle_vin() + "' ");
		sql.append(" and v.driver_id = d.driver_id) veh_d ");		
//		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
//			Object driver_id = (Object) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+(String)driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}else{
//			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}
//		sql.append(")");
		log.debug("<BuildSQL>区域超速告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 区域超速告警update sql告警状态
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateRegion_OverspeedAlarmSql(Up_InfoContent urt,
			Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + urt.getRegion_overspeed_alert() + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ="
				+ urt.getRegion_overspeed_alarm_type_id() + "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>区域超速告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 区域非法开关门告警insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertRegion_OpenCloseDoorAlarmSql(Up_InfoContent urt,
			String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,DRIVER_ID) values(");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getRegion_openclosedoor_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getRegion_openclose_alert() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
			String driver_id = (String) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+driver_id+"'");
			}else{
				sql.append(",''");
			}
		}else{
			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+driver_id+"'");
			}else{
				sql.append(",''");
			}
		}
		sql.append(")");
		log.debug("<BuildSQL>区域非法开关门告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 区域非法开关门告警update sql告警状态
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateRegion_OpenCloseDoorAlarmSql(Up_InfoContent urt,
			Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + urt.getRegion_openclose_alert() + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ="
				+ urt.getRegion_openclosedoor_alarm_type_id() + "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>区域非法开关门告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 入区域告警insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertRegion_InAlarmSql(Up_InfoContent urt, String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,DRIVER_ID) values(");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getRegion_in_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getRegion_in_alert() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
			String driver_id = (String) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+driver_id+"'");
			}else{
				sql.append(",''");
			}
		}else{
			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+driver_id+"'");
			}else{
				sql.append(",''");
			}
		}
		sql.append(")");
		log.debug("<BuildSQL>区域非法开关门告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 入区域告警update sql告警状态
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateRegion_InAlarmSql(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + urt.getRegion_in_alert() + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ='" + urt.getRegion_in_alarm_type_id()
				+ "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>入区域告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 出区域告警insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertRegion_OutAlarmSql(Up_InfoContent urt, String uuid) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION,DRIVER_ID) values(");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + urt.getRegion_out_alarm_type_id() + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
			sql.append(",'" + urt.getSpeeding() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getRegion_out_alert() + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
			Object driver_id = (Object) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+(String)driver_id+"'");
			}else{
				sql.append(",''");
			}
		}else{
			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
			if(driver_id!=null&&!driver_id.equals("")){
				sql.append(",'"+driver_id+"'");
			}else{
				sql.append(",''");
			}
		}
		sql.append(")");
		log.debug("<BuildSQL>出区域告警insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 出区域告警update sql告警状态
	 * 
	 * @param urt
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String buildUpdateRegion_OutAlarmSql(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + urt.getRegion_out_alert() + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		sql.append(",DEAL_FLAG='1'");
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ='" + urt.getRegion_out_alarm_type_id()
				+ "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>出区域告警update sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 更新终端信息表实时sos报警状态
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalSosState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("SOS=" + "'" + StringUtils.trimToEmpty(urt.getSos()) + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.info("<BuildSQL>更新终端信息表实时sos报警状态语句：" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public String buildUpdateTerminalSosState(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("SOS=" + "'" + map.get("sos") + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时sos报警状态语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 更新终端信息表实时超速报警状态
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalOverspeedState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("OVERSPEED_ALERT=" + "'"
				+ StringUtils.trimToEmpty(urt.getOverspeed_alert()) + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时超速：" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public String buildUpdateTerminalOverspeedState(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("OVERSPEED_ALERT=" + "'" + map.get("overspeed") + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时超速" + "：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 更新终端信息表实时疲劳驾驶报警状态
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalFatigueState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("FATIGUE_ALERT=" + "'"
				+ StringUtils.trimToEmpty(urt.getFatigue_alert()) + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时疲劳驾驶" + "报警状态语句：" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public String buildUpdateTerminalFatigueState(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("FATIGUE_ALERT=" + "'" + map.get("fatigue_alert") + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时疲劳驾驶" + "报警状态语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 更新终端信息表实时gps超速报警状态
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalGpsState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("GPS_ALERT=" + "'"
				+ StringUtils.trimToEmpty(urt.getGps_alert()) + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时gps超速报警状态语句：" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public String buildUpdateTerminalGpsState(Up_InfoContent urt, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("GPS_ALERT=" + "'" + map.get("gps_alert") + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug(LogFormatter.formatMsg("BuildSQL", "更新终端信息表实时gps超速报警状态语句："
				+ sql.toString()));
		return sql.toString();
	}

	public String buildUpdateTerminalRapidState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("RAPID_ALERT=" + "'"
				+ StringUtils.trimToEmpty(urt.getRapid()) + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时急加/减速报警状态语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 更新终端信息表实时区域内超速报警状态
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalRegion_OverspeedState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("region_overspeed_alert=" + "'"
				+ StringUtils.trimToEmpty(urt.getRegion_overspeed_alert())
				+ "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时区域内超速报警状态语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 更新终端信息表实时区域内非法开关门告警
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalRegion_OpenCloseDoorState(
			Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("region_openclosedoor_alert=" + "'"
				+ StringUtils.trimToEmpty(urt.getRegion_openclose_alert())
				+ "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>更新终端信息表实时区域内超速报警状态语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 入区域报警状态
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalRegion_InState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("region_in_alert=" + "'"
				+ StringUtils.trimToEmpty(urt.getRegion_in_alert()) + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>入区域报警状态语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 出区域报警状态
	 * 
	 * @param urt
	 * @return
	 */
	public String buildUpdateTerminalRegion_OutState(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_JC_TERMINAL_T set ");
		sql.append("region_out_alert=" + "'"
				+ StringUtils.trimToEmpty(urt.getRegion_out_alert()) + "',");
		sql.append("UPDATE_TIME=sysdate");
		sql.append(" where VEHICLE_VIN =" + "'" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>出区域报警状态语句：" + sql.toString());
		return sql.toString();
	}

	/**
	 * 宇通杯1秒数据入库语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertYTB(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_MATCH_SEC_DATA_T "
						+ "(ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, WRITE_TIME, TEMINAL_TIME, "
						+ "SPEEDING, UNIT, ON_OFF_VALUE, LONGITUDE, LATITUDE, ELEVATION, ENGINE_ROTATE_SPEED, "
						+ "TORQUE, COLDER_TEMPERATURE, EEC_APP, VEHICLE_SPEED, PULSE_MILEAGE, "
						+ "RESERVED1, RESERVED2, WEEK, TIME_OF_WEEK, RATIO, GEARS, OVER_ENGINE_SPEED,"
						+ " GEAR_UNFIT, EGEAR_RUN, GEAR2_START, FLOWRATE, TOTALCOMSUMPTION) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + tb.getTerminal_id());
		sql.append("','" + urt.getTerminalId());
		sql.append("','" + tb.getSim_card_number());
		sql.append("',sysdate");
		sql
				.append(",TO_TIMESTAMP('" + urt.getTime()
						+ "','yymmddhh24missff3')");
		sql.append(",'" + urt.getSpeed());
		sql.append("','km/h'");

		if (urt.getOn_off_value() != null && !urt.getOn_off_value().equals("")) {
			sql.append(",'" + urt.getOn_off_value());
		} else {
			sql.append(",'" + Constant.F4);
		}
		sql.append("','" + urt.getYtb_longitude());
		sql.append("','" + urt.getYtb_latitude());
		sql.append("','" + urt.getYtb_altitude());
		sql.append("','" + urt.getEngine_speed());
		sql.append("','" + urt.getEngine_torque());
		sql.append("','" + urt.getEngine_coolant_temperature());
		sql.append("','" + urt.getThrottle_position());
		sql.append("','" + urt.getVehicle_speed());
		sql.append("','" + urt.getVehicle_distance());
		if (urt.getReserved1() != null && !urt.getReserved1().equals("")) {
			sql.append("','" + urt.getReserved1());
		} else {
			sql.append("','");
		}
		if (urt.getReserved2() != null && !urt.getReserved2().equals("")) {
			sql.append("','" + urt.getReserved2());
		} else {
			sql.append("','");
		}
		sql.append("','" + urt.getWeek());
		sql.append("','" + urt.getTime_of_week());
		sql.append("','" + urt.getRatio());
		if (urt.getGears().equals("12")) {
			urt.setGears("R");
		}
		sql.append("','" + urt.getGears());
		sql.append("','" + urt.getOver_engine_speed());
		sql.append("','" + urt.getGear_unfit());
		sql.append("','" + urt.getEgear_run());
		sql.append("','" + urt.getGear2_start());
		sql.append("','" + urt.getFlowrate());
		sql.append("','" + urt.getTotalconsumption());
		// sql.append("','"+urt.getYtb_rapid());
		sql.append("')");
		log.debug("<BuildSQL>宇通杯秒数据insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 读出终端编号，车辆vin，sim卡号
	 * 
	 * @param id
	 * @return
	 */
	public TerminalBean getTerminal(String id) {
		return terminalDAO.getTerminalByid(id);
	}

	/**
	 * 车联网秒数据insert语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertClwSec(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_SEC_DATA_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, "
						+ "WRITE_TIME, TEMINAL_TIME, SPEEDING, UNIT, ON_OFF_VALUE, LONGITUDE, LATITUDE, "
						+ "OIL_INSTANT, ENGINE_ROTATE_SPEED, TORQUE, EEC_APP, VEHICLE_SPEED,DIRECTION, RESERVED) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + tb.getTerminal_id());
		sql.append("','" + urt.getTerminalId());
		sql.append("','" + tb.getSim_card_number());
		sql.append("',sysdate");
		sql
				.append(",TO_TIMESTAMP('" + urt.getTime()
						+ "','yymmddhh24missff3')");
		sql.append(",'" + urt.getClw_speed());
		// if(urt.getUnit()!=null&&!urt.getUnit().equals("")){
		// sql.append("','"+urt.getUnit()+"'");
		// }else{
		// sql.append("',''");
		// }
		sql.append("','km/h'");
		if (urt.getOn_off_value() != null && !urt.getOn_off_value().equals("")) {
			sql.append(",'" + urt.getOn_off_value());
		} else {
			sql.append(",'" + Constant.F4);
		}
		sql.append("','" + urt.getClw_longitude());
		sql.append("','" + urt.getClw_latitude());
		sql.append("','" + urt.getClw_fuelrate());
		sql.append("','" + urt.getClw_engine_speed());
		sql.append("','" + urt.getClw_engine_torque());
		sql.append("','" + urt.getClw_throttle_position());
		sql.append("','" + urt.getClw_vehicle_speed());
		if (urt.getClw_heading() != null && !urt.getClw_heading().equals("")) {
			sql.append("','" + urt.getClw_heading());
		} else {
			sql.append("','");
		}
		if (urt.getReserved1() != null && !urt.getReserved1().equals("")) {
			sql.append("','" + urt.getReserved1());
		} else {
			sql.append("','");
		}
		sql.append("')");
		log.debug("<BuildSQL>车联网秒数据insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 车联网车辆超速记录insert sql
	 * 
	 * @param urt
	 * @return
	 */
	// public String buildInsertClwOverspeedSql(Up_InfoContent urt) {
	// String start_time =
	// "to_date("+urt.getOverspeed_start_time()+",'yymmddhh24miss')";
	// String end_time = "to_date("+urt.getTime()+",'yymmddhh24miss')";
	// StringBuffer sql = new StringBuffer();
	// sql.append("insert into CLW_YW_MALARMD_T(MALARMD_ID, ALARM_TYPE_ID, VEHICLE_VIN, ALARM_START_TIME, "
	// +
	// "ALARM_END_TIME, ALARM_TIME, ALARM_START_SPEED, ALARM_START_RPM, ALARM_START_LATITUDE, "
	// +
	// "ALARM_START_LONGITUDE, UNIT, ON_OFF_VALUE, RESERVRD1, RESERVERD2, DRIVER_ID, TEMINAL_TIME, "
	// +
	// "WRITE_TIME, MAXSPEED) values(");
	// sql.append("SYS_GUID()");
	// sql.append(",'32");
	// // sql.append("','超速报警");
	// sql.append("','"+urt.getTerminalId());
	// sql.append("',"+start_time+"");
	// sql.append(","+end_time+"");
	// // Date start_time = null;
	// // Date end_time = null;
	// // try {
	// // start_time =
	// DateUtil.changeStringTo12Date(urt.getOverspeed_start_time());
	// // end_time= DateUtil.changeStringTo12Date(urt.getTime());
	// // } catch (ParseException e) {
	// // e.printStackTrace();
	// // }
	// // long diff = end_time.getTime()-start_time.getTime();
	// sql.append(",("+(end_time+"-"+start_time)+")*24*60*60");
	// if(urt.getOverspeed_highspeed()!=null&&!urt.getOverspeed_highspeed().equals("")){
	// sql.append(",'"+urt.getOverspeed_highspeed()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getOverspeed_rpm()!=null&&!urt.getOverspeed_rpm().equals("")){
	// sql.append(",'"+urt.getOverspeed_rpm()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getOverspeed_latitude()!=null&&!urt.getOverspeed_latitude().equals("")){
	// sql.append(",'"+urt.getOverspeed_latitude()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getOverspeed_longitude()!=null&&!urt.getOverspeed_longitude().equals("")){
	// sql.append("','"+urt.getOverspeed_longitude()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getUnit()!=null&&!urt.getUnit().equals("")){
	// sql.append(",'"+urt.getUnit()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getOverspeed_onoff()!=null&&!urt.getOverspeed_onoff().equals("")){
	// sql.append(",'"+urt.getOn_off_value()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getOverspeed_type()!=null&&!urt.getOverspeed_type().equals("")){
	// sql.append(",'"+urt.getOverspeed_type()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getReserved2()!=null&&!urt.getReserved2().equals("")){
	// sql.append(",'"+urt.getReserved2()+"'");
	// }else{
	// sql.append(",''");
	// }
	// if(urt.getOverspeed_driver_id()!=null&&!urt.getOverspeed_driver_id().equals("")){
	// sql.append(",'"+urt.getOverspeed_driver_id()+"'");
	// }else{
	// sql.append(",''");
	// }
	// sql.append(",to_date("+urt.getOverspeed_start_time()+",'yymmddhh24miss')");
	// sql.append(",sysdate");
	// sql.append(",'"+urt.getOverspeed_maxspeed()+"'");
	// sql.append(")");
	// log.debug("<BuildSQL>车辆超速记录insert sql:"+sql.toString());
	// return sql.toString();
	// }

	public String buildInsertClwOverspeedSql(Up_InfoContent urt) {
		String start_time = "to_date(" + urt.getOverspeed_start_time()
				+ ",'yymmddhh24miss')";
		String end_time = "to_date(" + urt.getTime() + ",'yymmddhh24miss')";
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into CLW_YW_OVERSPEED_RECORD_T(ID, VEHICLE_VIN, WRITE_TIME, SPEEDING, "
						+ "DRIVER_ID, TEMINAL_TIME, START_TIME, STOP_TIME, TYPE, "
						+ "RESERVRD1, RESERVERD2) values(");
		sql.append("SYS_GUID()");
		// sql.append("','超速报警");
		sql.append(",'" + urt.getTerminalId());
		sql.append("',sysdate");
		sql.append(",'" + urt.getOverspeed_maxspeed() + "'");
		if (urt.getOverspeed_driver_id() != null
				&& !urt.getOverspeed_driver_id().equals("")) {
			sql.append(",'" + urt.getOverspeed_driver_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append("," + start_time + "");
		sql.append("," + start_time + "");
		sql.append("," + end_time + "");
		// Date start_time = null;
		// Date end_time = null;
		// try {
		// start_time =
		// DateUtil.changeStringTo12Date(urt.getOverspeed_start_time());
		// end_time= DateUtil.changeStringTo12Date(urt.getTime());
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// long diff = end_time.getTime()-start_time.getTime();
		// sql.append(",("+(end_time+"-"+start_time)+")*24*60*60");
		if (urt.getOverspeed_type() != null
				&& !urt.getOverspeed_type().equals("")) {
			sql.append(",'" + urt.getOverspeed_type() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getReserved1() != null && !urt.getReserved1().equals("")) {
			sql.append(",'" + urt.getReserved1() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getReserved2() != null && !urt.getReserved2().equals("")) {
			sql.append(",'" + urt.getReserved2() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<BuildSQL>车辆超速记录insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 车联网登陆记录insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertClwLoginSQL(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_LOGIN_RECORD_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, CARD_ID,"
						+ " CARD_LEVEL, DRIVER_ID, WRITE_TIME, LONIN_PASS, TERMINAL_TIME, DRIVER_LICENSE, RESERVERD) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + tb.getTerminal_id());
		sql.append("','" + urt.getTerminalId());
		sql.append("','" + tb.getSim_card_number());
		sql.append("','" + urt.getClw_login_cardid());
		sql.append("','" + urt.getClw_login_cardlevel());
		sql.append("','" + urt.getClw_login_driverid());
		sql.append("',sysdate");
		sql.append(",'" + urt.getClw_login_pass());
		sql.append("',to_date(" + urt.getTime() + ",'yymmddhh24miss')");
		sql.append(",'" + urt.getClw_login_driverlicense());
		if (urt.getReserved1() != null && !urt.getReserved1().equals("")) {
			sql.append("','" + urt.getReserved1());
		} else {
			sql.append("','");
		}
		sql.append("')");
		log.debug("<BuildSQL>车联网登陆记录insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 车联网开关量变化记录insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertClwOnOffSQL(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_ON_OF_RECORD_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, WRITE_TIME, "
						+ "TERMINAL_TIME, AFTER_VALUE, PRE_VALUE, SPEED, DOOR_STATE) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + tb.getTerminal_id());
		sql.append("','" + urt.getTerminalId());
		sql.append("','" + tb.getSim_card_number());
		sql.append("',sysdate");
		sql.append(",to_date(" + urt.getTime() + ",'yymmddhh24miss')");
		sql.append(",'" + urt.getClw_onoff_aftervalue());
		sql.append("','" + urt.getClw_onoff_prevalue());
		sql.append("','" + urt.getClw_onoff_speed());
		if (urt.getClw_onoff_doorstate() != null
				&& !urt.getClw_onoff_doorstate().equals("")) {
			sql.append("','" + urt.getClw_onoff_doorstate());
		} else {
			sql.append("','");
		}
		sql.append("')");
		log.debug("<BuildSQL>车联网开关量记录insert sql:" + sql.toString());
		return sql.toString();
	}

	public String buildInsertClwMunite1(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_MINUTE1_DATA_T(ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, "
						+ "WRITE_TIME, TEMINAL_TIME, OIL_TOTAL, OIL_VALUE, EP_EFDP, OIL_TEMPERATURE, "
						+ "COLDER_TEMPERATURE, RESERVED1, RESERVED2, RESERVED3) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + tb.getTerminal_id());
		sql.append("','" + urt.getTerminalId());
		sql.append("','" + tb.getSim_card_number());
		sql.append("',sysdate");
		sql
				.append(",TO_TIMESTAMP('" + urt.getTime()
						+ "','yymmddhh24missff3')");
		sql.append(",'" + urt.getClw_munite1_totalfuelused());
		sql.append("','" + urt.getClw_munite1_fuellevel());
		sql.append("','" + urt.getClw_munite1_engineoilpressure());
		sql.append("','" + urt.getClw_munite1_engineoiltemperature());
		sql.append("','" + urt.getClw_munite1_enginecoolanttemperature());
		if (urt.getReserved1() != null && !urt.getReserved1().equals("")) {
			sql.append("','" + urt.getReserved1());
		} else {
			sql.append("','");
		}
		if (urt.getReserved2() != null && !urt.getReserved2().equals("")) {
			sql.append("','" + urt.getReserved2());
		} else {
			sql.append("','");
		}
		if (urt.getReserved3() != null && !urt.getReserved3().equals("")) {
			sql.append("','" + urt.getReserved3());
		} else {
			sql.append("','");
		}
		sql.append("')");
		log.debug("<BuildSQL>车联网1分钟数据insert sql:" + sql.toString());
		return sql.toString();
	}

	public String buildInsertClwMunite5(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_MINUTE5_DATA_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, WRITE_TIME, "
						+ "TEMINAL_TIME, ENGHRREV_T_E_H, BATTERY_VOLTAGE, AIR_PRESSURE, AIR_INFLOW_TPR) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + tb.getTerminal_id());
		sql.append("','" + urt.getTerminalId());
		sql.append("','" + tb.getSim_card_number());
		sql.append("',sysdate");
		sql
				.append(",TO_TIMESTAMP('" + urt.getTime()
						+ "','yymmddhh24missff3')");
		sql.append(",'" + urt.getClw_munite5_totalenginehours());
		sql.append("','" + urt.getClw_munite5_electricalpotentia());
		sql.append("','" + urt.getClw_munite5_barometricpressure());
		sql.append("','" + urt.getClw_munite5_airinlettemperature());
		sql.append("')");
		log.debug("<BuildSQL>车联网5分钟数据insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 上行参数insert sql
	 * 
	 * @param udp
	 * @param tb
	 * @return
	 */
	public String buildInsertTerminalParamSql(Up_InfoContent udp,
			TerminalBean tb) {
		StringBuffer sql = new StringBuffer();
		sql
				.append("insert into CLW_JC_TERMINAL_PARA_T (TERMINAL_ID, UPDATE_TIME, MSG_CENTER, APN, SERVER_IP, "
						+ "SERVER_PORT, RECEIVER_TIME, TIME_ANSWERS, SPACING_ANSWERS, KEEPALIVE_TIME, KEEPALIVE_OVERTIME, "
						+ "STALLED_TIME_ANSWERS, OVERSPEED, OVERSPEED_DIFF, OVERSPEED_KEEP, DRIVING_FATIGUE, "
						+ "DRIVING_FATIGUE_DIFF, DRIVING_FATIGUE_REST, INDEX_PROPERTY, PULSE_PER_SECOND, ENGINE_GEAR, "
						+ "VEHICLE_LN, VEHICLE_NO, VEHICLE_SORT, VEHICLE_VIN, SLEEP_TIME,OUT1TIME) values('");
		sql.append(tb.getTerminal_id() + "'");
		sql.append(",sysdate");
		if (udp.getMsg_center() != null && !udp.getMsg_center().equals("")) {
			sql.append(",'" + udp.getMsg_center() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getApn() != null && !udp.getApn().equals("")) {
			sql.append(",'" + udp.getApn() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getServer_ip() != null && !udp.getServer_ip().equals("")) {
			sql.append(",'" + udp.getServer_ip() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getServer_port() != null && !udp.getServer_port().equals("")) {
			sql.append(",'" + udp.getServer_port() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getReceive_time() != null && !udp.getReceive_time().equals("")) {
			sql.append(",'" + udp.getReceive_time() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getTime_answers() != null && !udp.getTime_answers().equals("")) {
			sql.append("," + Long.valueOf(udp.getTime_answers(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getSpacing_answers() != null
				&& !udp.getSpacing_answers().equals("")) {
			sql.append("," + Long.valueOf(udp.getSpacing_answers(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getKeepalive_time() != null
				&& !udp.getKeepalive_time().equals("")) {
			sql.append("," + Long.valueOf(udp.getKeepalive_time(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getKeepalive_overtime() != null
				&& !udp.getKeepalive_overtime().equals("")) {
			sql
					.append("," + Long.valueOf(udp.getKeepalive_overtime(), 16)
							+ "");
		} else {
			sql.append(",''");
		}
		if (udp.getStalled_time_answers() != null
				&& !udp.getStalled_time_answers().equals("")) {
			sql.append("," + Long.valueOf(udp.getStalled_time_answers(), 16)
					+ "");
		} else {
			sql.append(",''");
		}
		if (udp.getOverspeed() != null && !udp.getOverspeed().equals("")) {
			sql.append("," + Long.valueOf(udp.getOverspeed(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getOverspeed_diff() != null
				&& !udp.getOverspeed_diff().equals("")) {
			sql.append("," + Long.valueOf(udp.getOverspeed_diff(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getOverspeed_keep() != null
				&& !udp.getOverspeed_keep().equals("")) {
			sql.append("," + Long.valueOf(udp.getOverspeed_keep(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getDriving_fatigue() != null
				&& !udp.getDriving_fatigue().equals("")) {
			sql.append("," + Long.valueOf(udp.getDriving_fatigue(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getDriving_fatigue_diff() != null
				&& !udp.getDriving_fatigue_diff().equals("")) {
			sql.append("," + Long.valueOf(udp.getDriving_fatigue_diff(), 16)
					+ "");
		} else {
			sql.append(",''");
		}
		if (udp.getDriving_fatigue_rest() != null
				&& !udp.getDriving_fatigue_rest().equals("")) {
			sql.append("," + Long.valueOf(udp.getDriving_fatigue_rest(), 16)
					+ "");
		} else {
			sql.append(",''");
		}
		if (udp.getIndex_property() != null
				&& !udp.getIndex_property().equals("")) {
			sql.append("," + Long.valueOf(udp.getIndex_property(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getPulse_per_second() != null
				&& !udp.getPulse_per_second().equals("")) {
			sql.append("," + Long.valueOf(udp.getPulse_per_second(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getEngine_gear() != null && !udp.getEngine_gear().equals("")) {
			sql.append("," + Long.valueOf(udp.getEngine_gear(), 16) + "");
		} else {
			sql.append(",''");
		}
		if (udp.getVehicle_ln() != null && !udp.getVehicle_ln().equals("")) {
			sql.append(",'" + udp.getVehicle_ln() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getVehicle_no() != null && !udp.getVehicle_no().equals("")) {
			sql.append(",'" + udp.getVehicle_no() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getVehicle_sort() != null && !udp.getVehicle_sort().equals("")) {
			sql.append(",'" + udp.getVehicle_sort() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getVehicle_vin() != null && !udp.getVehicle_vin().equals("")) {
			sql.append(",'" + udp.getVehicle_vin() + "'");
		} else {
			sql.append(",''");
		}
		if (udp.getSleep_time() != null && !udp.getSleep_time().equals("")) {
			sql.append("," + udp.getSleep_time() + "");
		} else {
			sql.append(",''");
		}
		if (udp.getOut1tiem() != null && !udp.getOut1tiem().equals("")) {
			sql.append(",to_date(" + udp.getOut1tiem() + ",'yymmddhh24miss'");
		} else {
			sql.append(",''");
		}
		// sql.append(",'admin'");
		// sql.append(",sysdate");
		// if(udp.getModifier()!=null&&!udp.getModifier().equals("")){
		// sql.append(",'"+udp.getModifier()+"'");
		// }else{
		// sql.append(",''");
		// }
		// if(udp.getModify_time()!=null&&!udp.getModify_time().equals("")){
		// sql.append(",to_date("+udp.getModify_time()+",'yymmddhh24miss')");
		// }else{
		// sql.append(",''");
		// }
		sql.append(")");
		log.debug("<BuildSQL>上行参数数据insert sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 急加速记录 insert into
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertClwRapidSQL(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_RAPID_RECORD_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, "
						+ "WRITE_TIME, TEMINAL_TIME, SPEEDING, UNIT, ON_OFF_VALUE, LONGITUDE, LATITUDE,DIRECTION) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",sysdate");
		sql.append(",TO_TIMESTAMP('" + urt.getClw_rapid_onedata_time()
				+ "','yymmddhh24missff3')");
		sql.append(",'" + urt.getClw_rapid_speed() + "'");
		if (urt.getUnit() != null && !urt.getUnit().equals("")) {
			sql.append(",'" + urt.getUnit() + "'");
		} else {
			sql.append(",'km/h'");
		}
		sql.append(",'" + urt.getClw_rapid_onoff() + "'");
		sql.append(",'" + urt.getClw_rapid_longitude() + "'");
		sql.append(",'" + urt.getClw_rapid_latitude() + "'");
		sql.append(",'" + urt.getClw_rapid_heading());
		sql.append("')");
		log.debug("<BuildSQL>急加速记录数据insert sql:" + sql.toString());
		return sql.toString();
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
				.append("insert into CLW_YW_SMS_RECORD_T(ID,VEHICLE_VIN,TEL,MSG,STATE,SEND_TAKE,SRC_ID,CREATE_TIME," +
						"ENTERPRISE_ID,ORGANIZATION_ID,FLAG) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + urt.getTel() + "'");
		sql.append(",'" + urt.getMsg() + "'");
		sql.append(",'" + urt.getSms_state() + "'");
		sql.append(",'0'");
		if (urt.getSrc_id() != null && !urt.getSrc_id().equals("")) {
			sql.append(",'" + urt.getSrc_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",sysdate");
		sql.append(",'" + urt.getEnterprise_id() + "'");
		sql.append(",'" + urt.getOrganization_id());
		sql.append(",'0')");
		log.debug("<BuildSQL>短信流水记录数据insert sql:" + sql.toString());
		return sql.toString();
	}

	public String buildInsertUPMESSAGESQL(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into CLW_YW_UPMESSAGE_T("
				+ "UPMESSAGE_ID,VEHICLE_VIN,DRIVER_ID,TERMINAL_TIME,MEG_INFO,"
				+ "MEG_TYPE,CREATE_TIME,MEG_RESP_ID,MEG_ID,MEG_SEQ) values(");
		sql.append("SYS_GUID()");
		sql.append(",'" + urt.getTerminalId() + "'");
		if (urt.getDriver_id() != null && !urt.getDriver_id().equals("")&&!urt.getDriver_id().equals("FFFFFFFF")) {
			sql.append(",'" + Integer.parseInt(urt.getDriver_id(),16) + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		sql.append(",'" + urt.getMeg_info() + "'");
		if (urt.getMeg_type() != null && !urt.getMeg_type().equals("")) {
			sql.append(",'" + urt.getMeg_type() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",sysdate");
		if (urt.getMeg_resp_id() != null && !urt.getMeg_resp_id().equals("")) {
			sql.append(",'" + urt.getMeg_resp_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMeg_id() != null && !urt.getMeg_id().equals("")) {
			sql.append(",'" + urt.getMeg_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMeg_seq() != null && !urt.getMeg_seq().equals("")) {
			sql.append(",'" + urt.getMeg_seq() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<BuildSQL>短信流水记录数据insert sql:" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public Map getCacheQuotas(String vin, String enterprise_id) {
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			Object obj = Constant.getMemcachedClient().getObject(enterprise_id);
			if (obj != null && !obj.equals("")) {
				log.debug("lllllllllllll:"
						+ Constant.getMemcachedClient()
								.getObject(enterprise_id));
				return (Map) obj;
			} else {
				return null;
			}
		} else {
			log.debug("kkkkkkkkkkkk:"
					+ MsgCfgDAO.msgquotasMap.get(enterprise_id));
			return (Map) MsgCfgDAO.msgquotasMap.get(enterprise_id);
		}
	}

	@SuppressWarnings("unchecked")
	public void isHasQuotas(String vin, String alarmtype, Up_InfoContent urt)
			throws ParseException {
		log.debug("@!@!+1:");
		String enterprise_id = msgCfgDAO.getEnterprise_id(vin);
		Map map = getCacheQuotas(vin, enterprise_id);
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
							log.info("<BuildSQL>当月短信发送数量超出短信配额,短信下发失败");
						} else {
							log.debug("@!@!7:");
							if (isSuccessSendMsg(vin, alarmtype, urt)) {
								map.put("send_num", String.valueOf(Integer
										.parseInt(send_num) + 1));
								if (Constant.isstartMemcache.equals("1")
										&& Constant.getMemcachedClient()
												.connectState()) {
									Constant.getMemcachedClient().insert(
											enterprise_id, map);
									// log.debug("memcache"+Constant.getMemcachedClient().getObject(enterprise_id));
								}
								Constant.msgMap.put(enterprise_id, map);
							}
							// return true;
						}
					} else {
						log.info("<BuildSQL>缓存当前月份：("
								+ current_month
								+ ")与系统当前月份("
								+ DateUtil.getCurrentMonth(DateUtil
										.changeStringTo12Date(systime))
								+ ")不匹配");
						map.put("current_month", DateUtil
								.getCurrentMonth(DateUtil
										.changeStringTo12Date(systime)));
						map.put("send_num", "0");
						log.debug(LogFormatter.formatMsg("BuildSQL",
								"发送额已过期，重置为0"));
						log.debug("eeeeeeeeqqqqq:" + map);
						if (Integer.parseInt((String) map.get("send_num")) >= msg_num) {
							// return false;
							log.info("<BuildSQL>当月短信发送数量超出短信配额,短信下发失败");
						} else {
							if (isSuccessSendMsg(vin, alarmtype, urt)) {
								map.put("send_num", String
										.valueOf(Integer.parseInt((String) map
												.get("send_num")) + 1));
								if (Constant.isstartMemcache.equals("1")
										&& Constant.getMemcachedClient()
												.connectState()) {
									Constant.getMemcachedClient().insert(
											enterprise_id, map);
//									log.debug("memcache"
//											+ Constant.getMemcachedClient()
//													.getObject(enterprise_id));
								}
								Constant.msgMap.put(enterprise_id, map);
							}
							// return true;
						}
					}
				} else {
					map.put("current_month", DateUtil.getCurrentMonth(DateUtil
							.changeStringTo12Date(systime)));
					// send_num="0";
					log.debug("@!@!+12:");
					if (map.get("send_num") != null
							&& !map.get("send_num").equals("")) {
						send_num = (String) map.get("send_num");
					} else {
						send_num = "0";
					}
					log.debug("@!@!+13send_num:" + send_num);
					// if(send_num.compareTo(msg_num)>=0){
					// log.error(LogFormatter.formatMsg("BuildSQL",
					// "当月短信发送数量超出短信配额,短信下发失败"));
					// }else{
					log.debug("@!@!14:");
					if (isSuccessSendMsg(vin, alarmtype, urt)) {
						map.put("send_num", String.valueOf(Integer
								.parseInt(send_num) + 1));
						if (Constant.isstartMemcache.equals("1")
								&& Constant.getMemcachedClient().connectState()) {
							Constant.getMemcachedClient().insert(enterprise_id,
									map);
//							log.debug("memcache"
//									+ Constant.getMemcachedClient().getObject(
//											enterprise_id));
						}
						Constant.msgMap.put(enterprise_id, map);
					}
					// return true;
					// }
					// return false;
				}
			} else {
				log.info("<BuildSQL>未缓存企业" + enterprise_id + "短信配额");
				// return false;
			}
		} else {
			log.info("<BuildSQL>未找到企业" + enterprise_id + "的短信配额缓存");
			// return false;
		}
	}

	/**
	 * 判断该告警是否需要下发短信
	 * 
	 * @param vin
	 * @param alarmtype
	 * @return
	 */
	public boolean isSendMsg(String vin, String alarmtype, MsgCfgBean cfb) {
		if (cfb != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSuccessSendMsg(String vin, String alarmtype,
			Up_InfoContent urt) {
		log.debug("@!@!+8isSuccessSendMsg:");
		VehicleBean vb = VehicleCacheManager.getInstance().getValue(
				urt.getTerminalId());
		List<MsgCfgBean> list = msgCfgDAO.getAlarmMsg(vin, alarmtype);
		if (list != null && list.size() > 0) {
			for (MsgCfgBean cfb : list) {
				log.debug("@!@!+11send_time:" + cfb.getSendtime());
				// 判断该告警是否需要下发短信
				if (isSendMsg(vin, alarmtype, cfb)) {
					log.debug("@!@!+9isSendMsg(vin, alarmtype,cfb):"
							+ isSendMsg(vin, alarmtype, cfb));
					UserBean ub = msgCfgDAO.getUserInfo(vin);
					if (ub != null) {
						log.debug("@!@!+10UserBean:" + ub.getEnterprise_id()
								+ "," + ub.getOrganization_id() + ","
								+ ub.getUser_mobile());
						if (ub.getEnterprise_id() != null
								&& !ub.getEnterprise_id().equals("")
								&& ub.getOrganization_id() != null
								&& !ub.getOrganization_id().equals("")
								&& ub.getUser_mobile() != null
								&& !ub.getUser_mobile().equals("")) {
							urt.setTel(ub.getUser_mobile());
							urt.setOrganization_id(ub.getOrganization_id());
							urt.setEnterprise_id(ub.getEnterprise_id());
							log.debug("@!@!+19UserBean:" + ub.getUser_mobile()
									+ "," + ub.getOrganization_id() + ","
									+ ub.getEnterprise_id());
							int i = 0;
							while (i < Integer.parseInt(cfb.getSendtime())) {
								log.debug("@!@!+15send_time:"
										+ cfb.getSendtime());
								int num = SDKClient
										.getClient()
										.sendSMS(
												new String[] { ub
														.getUser_mobile() },
												vb.getVehicle_ln()
														+ cfb.getAdd_info()
														+ Config.props
																.getProperty("company"),
												3);
								urt.setMsg(vb.getVehicle_ln()
										+ cfb.getAdd_info()
										+ Config.props.getProperty("company"));
								if (num == 0) {
									log.info("<BuildSQL>该告警" + alarmtype
											+ "短信下发成功");
									urt.setSms_state(String.valueOf(num));
									DBBuffer.getInstance().add(
											buildInsertSmsRecordSQL(urt));
									// return;
									return true;
								} else {
									i++;
									log.info("<BuildSQL>该告警" + alarmtype + "第"
											+ i + "次短信下发失败");
									urt.setSms_state(String.valueOf(1));
									continue;
								}
							}
							DBBuffer.getInstance().add(
									buildInsertSmsRecordSQL(urt));
							return false;
						} else {
							log.info("<BuildSQL>该告警" + alarmtype
									+ "未设置下发的信息，不下发短信");
							return false;
						}
					} else {
						// log.info(LogFormatter.formatMsg("BuildSQL",
						// "该告警"+alarmtype+"未设置下发的对象，下发短信失败"));
						log.info("<BuildSQL>该车辆" + urt.getTerminalId()
								+ "未配置，不下发短信");
						return false;
					}
				} else {
					log.info("<BuildSQL>该告警" + alarmtype + "未设置短信下发配置，不进行下发短信");
					return false;
				}
			}
		} else {
			log.info("<BuildSQL>该车辆" + urt.getTerminalId() + "的告警" + alarmtype
					+ "未设置短信下发配置，不进行下发短信");
			return false;
		}
		return false;
	}
	/**
	 * 超速实时告警insert sql
	 */
	public String buildInsertAlarmSql(Up_InfoContent urt, String uuid,
			String type, String state,String id) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION," +
								"SICHEN_ID,STU_NUM,LOAD_NUM,ALERT_ID,ROUTE_ID,TRIP_ID,RECORD_ID,zonename,driver_id)"
						);
		//sql.append("select ");
		sql.append("values( ");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + type + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")
				&& urt.getGps_valid().equals("1")) {
			if(urt.getSpeed_source_setting()!=null&&!urt.getSpeed_source_setting().equals("")){
				if(urt.getSpeed_source_setting().equals("00")){
					if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
						sql.append(",'" + urt.getSpeeding() + "'");
					} else {
						sql.append(",''");
					}
				}else{
					if (urt.getGps_speeding() != null && !urt.getGps_speeding().equals("")) {
						if(!urt.getGps_speeding().equals("FFFF")){
							sql.append(",'" + (!urt.getGps_speeding().trim().equals("0.0")?urt.getGps_speeding().trim():"0") + "'");
						}else{
							sql.append(",'0'");
						}
					} else {
						sql.append(",''");
					}
				}
			}else{
				if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
					sql.append(",'" + urt.getSpeeding() + "'");
				} else {
					sql.append(",''");
				}
			}
		}else{
			if(urt.getSpeed_source_setting()!=null&&!urt.getSpeed_source_setting().equals("")){
				if(urt.getSpeed_source_setting().equals("00")){
					if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
						sql.append(",'" + urt.getSpeeding() + "'");
					} else {
						sql.append(",''");
					}
				}else{
					if (urt.getGps_speeding() != null && !urt.getGps_speeding().equals("")) {
						sql.append(",'" + urt.getGps_speeding() + "'");
					} else {
						sql.append(",'0'");
					}
				}
			}else{
				if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
					sql.append(",'" + urt.getSpeeding() + "'");
				} else {
					sql.append(",''");
				}
			}	
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + state + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
		if(urt.getCur_tea()!=null&&!urt.getCur_tea().equals("")&&!urt.getCur_tea().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getCur_tea(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getStu_num()!=null&&!urt.getStu_num().equals("")&&!urt.getStu_num().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getStu_num(),16)+"'");
		}else{
			sql.append(",''");
		}		
//		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
//			String driver_id = (String) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}else{
//			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}
		if (urt.getHezai_id() != null && !urt.getHezai_id().equals("")) {
			sql.append(",'" + urt.getHezai_id() + "'");
		} else {
			sql.append(",''");
		}
		if(urt.getAlarm_seq()!=null&&!urt.getAlarm_seq().equals("")){
			sql.append(",'"+urt.getAlarm_seq()+"'");
		}else{
			sql.append(",''");
		}
		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")&&!urt.getRoute_id().equals("FFFFFFFF")) {
			sql.append("," + Integer.parseInt(urt.getRoute_id(),16));			
		}else{
			sql.append(",''");
		}
		if (urt.getTrip_id() != null && !urt.getTrip_id().equals("")&&!urt.getTrip_id().equals("FFFFFFFF")) {
			sql.append("," + Integer.parseInt(urt.getTrip_id(),16));			
		}else{
			sql.append(",''");
		}
		if(id!=null&&!id.equals("")){
			sql.append(",'"+id+"'");
		}else{
			sql.append(",''");
		}
		sql.append(",'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "'");
		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")&&!urt.getDriver_id().equals("FFFFFFFF")){
		      sql.append(",'"+Integer.parseInt(urt.getDriver_id(),16)+"')");
		    }else{
		      //sql.append(",''");
		      sql.append(" ,(select veh_d.driver_id ");
		      sql.append(" from ");
		      sql.append(" (select d.driver_id ");
		      sql.append(" from distinct_driver v,clw_yw_driver_t d ");
		      sql.append(" where v.vehicle_vin = '" + urt.getTerminalId() +  "' ");
		      sql.append(" and v.driver_id = d.driver_id) veh_d ))");
		    }
		/*sql.append(" , nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append(" from ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  tqc_trip_execute tre, ");
		sql.append(" (select nvl(max(t.trip_id),1) as trip_id ");
		sql.append(" from tqc_trip_execute t ");
		sql.append(" where t.vehicle_vin = '" + urt.getTerminalId() + "') triper ");
		sql.append(" where tre.trip_id(+) = triper.trip_id) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append(" from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getTerminalId() + "' ");
		sql.append(" and v.driver_id = d.driver_id) veh_d ");*/
		log.debug("<BuildSQL>实时告警insert sql:" + sql.toString());
		return sql.toString();
	}

	public String buildInsertAlarmSql(Up_InfoContent urt, String uuid,
			String type, String state,String seq,String id) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(urt.getTerminalId());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,REGION_ID,CONFIRM_TIME,SPEEDING,SPEED_UNIT,ALARM_TIME,USER_ID,ALARM_FLAG," +
								"LONGITUDE,LATITUDE,ENGINE_ROTATE_SPEED,MILEAGE,DIRECTION," + 
						"ROUTE_ID,SICHEN_ID,STU_NUM,LOAD_NUM,TRIP_ID,RECORD_ID,zonename,driver_id)");
		sql.append(" values( ");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + urt.getTerminalId() + "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + type + "'");
		if (urt.getRegion_id() != null && !urt.getRegion_id().equals("")) {
			sql.append(",'" + urt.getRegion_id() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getConfirm_time() != null && !urt.getConfirm_time().equals("")) {
			sql.append(",to_date(" + urt.getConfirm_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")
				&& urt.getGps_valid().equals("1")) {
			if(urt.getSpeed_source_setting()!=null&&!urt.getSpeed_source_setting().equals("")){
				if(urt.getSpeed_source_setting().equals("00")){
					if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
						sql.append(",'" + urt.getSpeeding() + "'");
					} else {
						sql.append(",''");
					}
				}else{
					if (urt.getGps_speeding() != null && !urt.getGps_speeding().equals("")) {
						if(!urt.getGps_speeding().equals("FFFF")){
							sql.append(",'" + (!urt.getGps_speeding().trim().equals("0.0")?urt.getGps_speeding().trim():"0") + "'");
						}else{
							sql.append(",'0'");
						}
					} else {
						sql.append(",''");
					}
				}
			}else{
				if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
					sql.append(",'" + urt.getSpeeding() + "'");
				} else {
					sql.append(",''");
				}
			}
		}else{
			if(urt.getSpeed_source_setting()!=null&&!urt.getSpeed_source_setting().equals("")){
				if(urt.getSpeed_source_setting().equals("00")){
					if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
						sql.append(",'" + urt.getSpeeding() + "'");
					} else {
						sql.append(",''");
					}
				}else{
					if (urt.getGps_speeding() != null && !urt.getGps_speeding().equals("")) {
						sql.append(",'" + urt.getGps_speeding() + "'");
					} else {
						sql.append(",'0'");
					}
				}
			}else{
				if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
					sql.append(",'" + urt.getSpeeding() + "'");
				} else {
					sql.append(",''");
				}
			}	
		}
		sql.append(",'km/h'");
		sql.append(",to_date(" + urt.getTerminal_time() + ",'yymmddhh24miss')");
		if (urt.getUser_id() != null && !urt.getUser_id().equals("")) {
			sql.append(",'" + urt.getUser_id() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + state + "'");
		if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
			sql.append(",'" + urt.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
			sql.append(",'" + urt.getLatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEngine_rotate_speed() != null
				&& !urt.getEngine_rotate_speed().equals("")) {
			sql.append(",'" + urt.getEngine_rotate_speed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMileage() != null && !urt.getMileage().equals("")) {
			sql.append(",'" + urt.getMileage() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getDirection() != null && !urt.getDirection().equals("")) {
			sql.append(",'" + urt.getDirection() + "'");
		} else {
			sql.append(",''");
		}
//		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")){
//			if(!urt.getDriver_id().equals("FFFFFFFF")){
//				sql.append(",'"+Integer.parseInt(urt.getDriver_id(),16)+"'");
//			}else{
//				sql.append(",'"+Constant.F4+"'");
//			}
//		}else{
//			sql.append(",''");
//		}

		if (urt.getRoute_id() != null && !urt.getRoute_id().equals("")&&!urt.getRoute_id().equals("FFFFFFFF")) {
			sql.append("," + Integer.parseInt(urt.getRoute_id(),16));			
		}else{
			sql.append(",''");
		}
		if(urt.getCur_tea()!=null&&!urt.getCur_tea().equals("")&&!urt.getCur_tea().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getCur_tea(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getStu_num()!=null&&!urt.getStu_num().equals("")&&!urt.getStu_num().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getStu_num(),16)+"'");
		}else{
			sql.append(",''");
		}
		if (urt.getHezai_id() != null && !urt.getHezai_id().equals("")) {
			sql.append(",'" + urt.getHezai_id() + "'");
		} else {
			sql.append(",''");
		}		

//		if(Constant.getMemcachedClient().equals("1")&&Constant.getMemcachedClient().connectState()){
//			String driver_id = (String) Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}else{
//			String driver_id = (String) Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.DRIVER);
//			if(driver_id!=null&&!driver_id.equals("")){
//				sql.append(",'"+driver_id+"'");
//			}else{
//				sql.append(",''");
//			}
//		}
		if (urt.getTrip_id() != null && !urt.getTrip_id().equals("")&&!urt.getTrip_id().equals("FFFFFFFF")) {
			sql.append("," + Integer.parseInt(urt.getTrip_id(),16));			
		}else{
			sql.append(",''");
		}
		if(id!=null&&!id.equals("")){
			sql.append(",'"+id+"'");
		}else{
			sql.append(",''");
		}
		sql.append(",'" + SearchGisAreaByCode.getZoneNameByPosition(urt.getLongitude(),urt.getLatitude()) + "'");
		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")&&!urt.getDriver_id().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getDriver_id(),16)+"')");
		}else{
			//sql.append(",''");
			sql.append(" ,(select veh_d.driver_id ");
			sql.append(" from ");
			sql.append(" (select d.driver_id ");
			sql.append(" from distinct_driver v,clw_yw_driver_t d ");
			sql.append(" where v.vehicle_vin = '" + urt.getTerminalId() +  "' ");
			sql.append(" and v.driver_id = d.driver_id) veh_d ))");
		}
		/*sql.append(" , nvl(trip_d.driver_id,veh_d.driver_id) ");
		sql.append(" from ");
		sql.append(" (select tre.driver_id as driver_id ");
		sql.append(" from  tqc_trip_execute tre, ");
		sql.append(" (select nvl(max(t.trip_id),1) as trip_id ");
		sql.append(" from tqc_trip_execute t ");
		sql.append(" where t.vehicle_vin = '" + urt.getTerminalId() + "') triper ");
		sql.append(" where tre.trip_id(+) = triper.trip_id) trip_d, ");
		sql.append(" (select d.driver_id ");
		sql.append(" from distinct_driver v,clw_yw_driver_t d ");
		sql.append(" where v.vehicle_vin = '" + urt.getTerminalId() + "' ");
		sql.append(" and v.driver_id = d.driver_id) veh_d ");*/
		
		
		log.debug("<BuildSQL>实时告警insert sql:" + sql.toString());
		return sql.toString();
	}
	
	@SuppressWarnings("unchecked")
	public String buildUpdateAlarmSql(Up_InfoContent urt, String type, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + map.get("alarm_state") + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
		if(urt.getAlarm_type_id().equals("40")){
			sql.append(",DEAL_FLAG='1'");
		}
//		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")&&!urt.getDriver_id().equals("FFFFFFFF")){
//			sql.append(",DRIVER_ID='"+Integer.parseInt(urt.getDriver_id(),16)+"'");
//		}
		if(urt.getLatitude()!=null&&!urt.getLatitude().equals("")){
			sql.append(",END_LATITUDE='"+urt.getLatitude()+"'");
		}
		if(urt.getLongitude()!=null&&!urt.getLongitude().equals("")){
			sql.append(",END_LONGITUDE='"+urt.getLongitude()+"'");
		}		
		sql.append(" where ALARM_ID = '" + map.get("alarm_id") + "'");
		sql.append(" and ALARM_TYPE_ID ='" + type + "'");
		sql.append(" and ALARM_TIME =to_date(" + map.get("alarm_time")
				+ ",'yymmddhh24miss')");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		log.debug("<BuildSQL>sos告警update sql:" + sql.toString());
		return sql.toString();
	}

	@SuppressWarnings("unchecked")
	public String buildUpdateSeqAlarmSql(Up_InfoContent urt,String type, Map map) {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_ALERM_RECORD_T set ");
		sql.append("ALARM_END_TIME=to_date(" + urt.getTerminal_time()
				+ ",'yymmddhh24miss')");
		sql.append(",ALARM_FLAG='" + map.get("alarm_state") + "'");
		// if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")) {
		// sql.append(",SPEEDING='" + urt.getSpeeding() + "'");
		// }
		// if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
		// sql.append(",LATITUDE='" + urt.getLatitude() + "'");
		// }
		// if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
		// sql.append(",LONGITUDE='" + urt.getLongitude() + "'");
		// }
//		if(urt.getAlarm_type_id().equals("40")){
			sql.append(",DEAL_FLAG='1'");
//		}
		sql.append(" where ALARM_TYPE_ID ='" + type + "'");
		sql.append(" and VEHICLE_VIN ='" + urt.getTerminalId() + "'");
		sql.append(" and ALARM_END_TIME is null");
		log.debug("<BuildSQL>sos告警update sql:" + sql.toString());
		return sql.toString();
	}
	
	public String buildInsertDriveEventSql(Up_InfoContent urt) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into CLW_XC_BADEVENT_T(EVENT_ID,EVENT_TYPE_ID,EVENT_START_ALARMINFO,"
						+ "EVENT_START_STATINFO,EVENT_START_LATITUDE,EVENT_START_LONGITUDE,EVENT_START_ELEVATION,"
						+ "EVENT_START_SPEED,EVENT_START_DIRECTION,EVENT_START_TIME,EVENT_END_ALARMINFO,"
						+ "EVENT_END_STATINFO,EVENT_END_LATITUDE,EVENT_END_LONGITUDE,EVENT_END_ELEVATION,"
						+ "EVENT_END_SPEED,EVENT_END_DIRECTION,EVENT_END_TIME,CREATE_TIME,VEHICLE_VIN,"
						+ "DRIVER_ID,EVENT_TIME,ROUTE_ID) values(");
		sql.append("'" + IdCreater.getUUid() + "'");
		sql.append(",'" + urt.getEventsId() + "'");
		if (urt.getStartalarm() != null && !urt.getStartalarm().equals("")) {
			sql.append(",'" + urt.getStartalarm() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStartonoff() != null && !urt.getStartonoff().equals("")) {
			sql.append(",'" + urt.getStartonoff() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStartlatitude() != null
				&& !urt.getStartlatitude().equals("")) {
			sql.append(",'" + urt.getStartlatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStartlongitude() != null
				&& !urt.getStartlongitude().equals("")) {
			sql.append(",'" + urt.getStartlongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStartelevation() != null
				&& !urt.getStartelevation().equals("")) {
			sql.append(",'" + urt.getStartelevation() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStartspeed() != null && !urt.getStartspeed().equals("")) {
			sql.append(",'" + urt.getStartspeed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStartdirection() != null
				&& !urt.getStartdirection().equals("")) {
			sql.append(",'" + urt.getStartdirection() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getStarttime() != null && !urt.getStarttime().equals("")) {
			sql.append(",to_date('" + urt.getStarttime()
					+ "','yymmddhh24miss')");
		} else {
			sql.append(",''");
		}

		if (urt.getEndalarm() != null && !urt.getEndalarm().equals("")) {
			sql.append(",'" + urt.getEndalarm() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEndonoff() != null && !urt.getEndonoff().equals("")) {
			sql.append(",'" + urt.getEndonoff() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEndlatitude() != null && !urt.getEndlatitude().equals("")) {
			sql.append(",'" + urt.getEndlatitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEndlongitude() != null && !urt.getEndlongitude().equals("")) {
			sql.append(",'" + urt.getEndlongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEndelevation() != null && !urt.getEndelevation().equals("")) {
			sql.append(",'" + urt.getEndelevation() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEndspeed() != null && !urt.getEndspeed().equals("")) {
			sql.append(",'" + urt.getEndspeed() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEnddirection() != null && !urt.getEnddirection().equals("")) {
			sql.append(",'" + urt.getEnddirection() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getEndtime() != null && !urt.getEndtime().equals("")) {
			sql.append(",to_date('" + urt.getEndtime() + "','yymmddhh24miss')");
		} else {
			sql.append(",''");
		}
		sql.append(",sysdate");
		sql.append(",'" + urt.getTerminalId() + "'");
		if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")&&!urt.getDriver_id().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getDriver_id(),16)+"'");
		}else{
			sql.append(",''");
		}
		if (urt.getStarttime() != null && !urt.getStarttime().equals("")&&urt.getEndtime() != null && !urt.getEndtime().equals("")) {
			sql.append(",(to_date('" + urt.getEndtime() + "','yymmddhh24miss')-to_date('" + urt.getStarttime() + "','yymmddhh24miss'))*24*60*60");
		}else{
			sql.append(",''");
		}
		if(urt.getRoute_id()!=null&&!urt.getRoute_id().equals("")&&!urt.getRoute_id().equals("FFFFFFFF")){
			sql.append(",'"+Integer.parseInt(urt.getRoute_id(),16)+"'");
		}else{
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<BuildSQL> drive event sql:" + sql.toString());
		return sql.toString();
	}

	/**
	 * 组装上行报文insert语句
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsert808UpParamSql(Up_InfoContent urt,TerminalBean tb) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
//		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
//				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_PARA_RECORD_T (ID, TERMINAL_ID,CREATER,CREATE_TIME, KEEPALIVE_TIME,TCP_OVERTIME,"
						+ "TCP_RETRANS_TIME,UDP_OVERTIME,UDP_RETRANS_TIME,SMS_OVERTIME,SMS_RETRANS_TIME,main_Apn,main_user,"
						+ "main_pass,main_ip,standby_apn,standby_user,standby_pass,standby_ip,tcp_port,udp_port,position_up_type,"
						+ "position_up_scheme,driver_over_datetime,sleep_datetime,sos_time,default_datetime,default_spacetime,"
						+ "driver_over_spacetime,SLEEP_SPACETIME,SOS_SPACETIME,makeup_angle,monitor_phone,reset_phone,reset_factory,"
						+ "monitor_smsphone,terminal_smsphone,terminal_phone_tactic,call_timeper,calltime_monch,listen_phone,"
						+ "monitor_privilege_phone,alarm_shield,alarmsms_switch,alarmshoot_switch,alarmshoot_saveflag,key_flag,"
						+ "top_speed,overspeed_time,drivertime_limit,drivertime_day_limit,min_timeout,max_timeout,media_quality,"
						+ "luminance,contrast,saturation,chroma,odometer,provinceid,cityid,number_plate,number_plate_color," +
								"ELECTRONIC_FENCE_RADIUS,OVERSPEED_ALARM_DIFFERENCE,FATIGUE_DIVING_DIFFERENCE,CHARACTERISTIC_OEFFICIENT," +
								"WHEELPULSES_PER_SECOND,FUELTANK_CAPACITY,ADDITIONAL_INFORMATION_SETUP,CARDOOR_CONTROL,TERMINAL_PERIPHERAL_CONTROL," +
								"BLINDAREA_STATE,REGULAR_CAMERA_CONTROL,FIXEDDISTANCE_CAMERA_CONTROL,SPEED_SOURCE_SETTING," +
								"RESOLUTION,LICENSE_PLATE_TYPE,VOICE_OUTPUT_CONTROL,SWITCH_TRIP,STANDARD_CNT) ");
		//sql.append("values('" + urt.getMsg_id() + "','");
		if(urt.getMsg_id() != null && !"".equals(urt.getMsg_id().trim())){
			sql.append("values('" + urt.getMsg_id().trim() + "','");
		}else{
			sql.append("values(SYS_GUID(),'");
		}			
		sql.append(tb.getTerminal_id() + "',");
		sql.append("'clw_xc',");
		sql.append("sysdate");
		if (urt.getHeartinterval() != null
				&& !urt.getHeartinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getHeartinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getTcpovertime() != null && !urt.getTcpovertime().equals("")) {
			sql.append("," + Long.valueOf(urt.getTcpovertime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getTcpretransnum() != null
				&& !urt.getTcpretransnum().equals("")) {
			sql.append("," + Long.valueOf(urt.getTcpretransnum(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getUdpovertime() != null && !urt.getUdpovertime().equals("")) {
			sql.append("," + Long.valueOf(urt.getUdpovertime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getUdpretransnum() != null
				&& !urt.getUdpretransnum().equals("")) {
			sql.append("," + Long.valueOf(urt.getUdpretransnum(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSmsovertime() != null && !urt.getSmsovertime().equals("")) {
			sql.append("," + Long.valueOf(urt.getSmsovertime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSmsretransnum() != null
				&& !urt.getSmsretransnum().equals("")) {
			sql.append("," + Long.valueOf(urt.getSmsretransnum(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getServerapn() != null && !urt.getServerapn().equals("")) {
			sql.append(",'" + urt.getServerapn() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServerwirelessuser() != null
				&& !urt.getServerwirelessuser().equals("")) {
			sql.append(",'" + urt.getServerwirelessuser() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServerwirelesspass() != null
				&& !urt.getServerwirelesspass().equals("")) {
			sql.append(",'" + urt.getServerwirelesspass() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServerip() != null && !urt.getServerip().equals("")) {
			sql.append(",'" + urt.getServerip() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackapn() != null && !urt.getBackapn().equals("")) {
			sql.append(",'" + urt.getBackapn() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackuser() != null && !urt.getBackuser().equals("")) {
			sql.append(",'" + urt.getBackuser() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackpass() != null && !urt.getBackpass().equals("")) {
			sql.append(",'" + urt.getBackpass() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackip() != null && !urt.getBackip().equals("")) {
			sql.append(",'" + urt.getBackip() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getTcpport() != null && !urt.getTcpport().equals("")) {
			sql.append("," + Long.valueOf(urt.getTcpport(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getUdpport() != null && !urt.getUdpport().equals("")) {
			sql.append("," + Long.valueOf(urt.getUdpport(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getPositionstra() != null && !urt.getPositionstra().equals("")) {
			sql.append(",'" + Long.valueOf(urt.getPositionstra(),16) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getPositionscheme() != null
				&& !urt.getPositionscheme().equals("")) {
			sql.append(",'" + Long.valueOf(urt.getPositionscheme(),16) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getNotlogintimeinterval() != null
				&& !urt.getNotlogintimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getNotlogintimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSleeptimeinterval() != null
				&& !urt.getSleeptimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSleeptimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSostimeinterval() != null
				&& !urt.getSostimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSostimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getDefaulttimeinterval() != null
				&& !urt.getDefaulttimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getDefaulttimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getDefaultdistanceinterval() != null
				&& !urt.getDefaultdistanceinterval().equals("")) {
			sql.append(","+ Long.valueOf(urt.getDefaultdistanceinterval(),16));
		} else {
			sql.append(",0");
		}
		if (urt.getNotlogindistanceinterval() != null
				&& !urt.getNotlogindistanceinterval().equals("")) {
			sql.append(","
					+ Long.valueOf(urt.getNotlogindistanceinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSleepdistanceinterval() != null
				&& !urt.getSleepdistanceinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSleepdistanceinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSosdistanceinterval() != null
				&& !urt.getSosdistanceinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSosdistanceinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getInflectionpoint() != null
				&& !urt.getInflectionpoint().equals("")) {
			sql.append("," + Long.valueOf(urt.getInflectionpoint(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getPlatphonenum() != null && !urt.getPlatphonenum().equals("")) {
			sql.append(",'" + urt.getPlatphonenum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getResetnum() != null && !urt.getResetnum().equals("")) {
			sql.append(",'" + urt.getResetnum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getFactorynum() != null && !urt.getFactorynum().equals("")) {
			sql.append(",'" + urt.getFactorynum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getSmsnum() != null && !urt.getSmsnum().equals("")) {
			sql.append(",'" + urt.getSmsnum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getRecsmsnum() != null && !urt.getRecsmsnum().equals("")) {
			sql.append(",'" + urt.getRecsmsnum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getTerminalstrategy() != null
				&& !urt.getTerminalstrategy().equals("")) {
			sql.append(",'" + Long.valueOf(urt.getTerminalstrategy(),16) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLongesttalk() != null && !urt.getLongesttalk().equals("")) {
			if(urt.getLongesttalk().equals("FFFFFFFF")){
				sql.append(",99999999");
			}else{
				sql.append("," + Long.valueOf(urt.getLongesttalk(), 16));
			}
		} else {
			sql.append(",0");
		}
		if (urt.getCurrentmonthtalk() != null && !urt.getCurrentmonthtalk().equals("")) {
			if(urt.getCurrentmonthtalk().equals("FFFFFFFF")){
				sql.append(",99999999");
			}else{
				sql.append("," + Long.valueOf(urt.getCurrentmonthtalk(), 16));
			}
		} else {
			sql.append(",0");
		}
		if (urt.getListennum() != null && !urt.getListennum().equals("")) {
			sql.append(",'" + urt.getListennum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getPlatmessagenum() != null
				&& !urt.getPlatmessagenum().equals("")) {
			sql.append(",'" + urt.getPlatmessagenum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmword() != null && !urt.getAlarmword().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getAlarmword()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmtext() != null && !urt.getAlarmtext().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getAlarmtext()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmshootingswitch() != null
				&& !urt.getAlarmshootingswitch().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getAlarmshootingswitch()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmshootingsign() != null
				&& !urt.getAlarmshootingsign().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getAlarmshootingsign()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getKeysign() != null && !urt.getKeysign().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getKeysign()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMaxspeed() != null && !urt.getMaxspeed().equals("")) {
			sql.append("," + Long.valueOf(urt.getMaxspeed(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getOverspeedtime() != null
				&& !urt.getOverspeedtime().equals("")) {
			sql.append("," + Long.valueOf(urt.getOverspeedtime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getContinuousdriving() != null
				&& !urt.getContinuousdriving().equals("")) {
			sql.append("," + Long.valueOf(urt.getContinuousdriving(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getCurrenttotaldriving() != null
				&& !urt.getCurrenttotaldriving().equals("")) {
			sql.append("," + Long.valueOf(urt.getCurrenttotaldriving(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getMinresttime() != null && !urt.getMinresttime().equals("")) {
			sql.append("," + Long.valueOf(urt.getMinresttime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getMaxstoptime() != null && !urt.getMaxstoptime().equals("")) {
			sql.append("," + Long.valueOf(urt.getMaxstoptime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getIvquality() != null && !urt.getIvquality().equals("")) {
			sql.append("," + Long.valueOf(urt.getIvquality(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getBrightness() != null && !urt.getBrightness().equals("")) {
			sql.append("," + Long.valueOf(urt.getBrightness(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getContrast() != null && !urt.getContrast().equals("")) {
			sql.append("," + Long.valueOf(urt.getContrast(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSaturation() != null && !urt.getSaturation().equals("")) {
			sql.append("," + Long.valueOf(urt.getSaturation(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getChroma() != null && !urt.getChroma().equals("")) {
			sql.append("," + Long.valueOf(urt.getChroma(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getMilestones() != null && !urt.getMilestones().equals("")) {
			sql.append("," + Long.valueOf(urt.getMilestones(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getProvinceid() != null && !urt.getProvinceid().equals("")) {
			sql.append(",'" + urt.getProvinceid() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getCityid() != null && !urt.getCityid().equals("")) {
			sql.append(",'" + urt.getCityid() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getRegno() != null && !urt.getRegno().equals("")) {
			sql.append(",'" + urt.getRegno() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getRegcolor() != null && !urt.getRegcolor().equals("")) {
			sql.append(",'" + Integer.parseInt(urt.getRegcolor(),16) + "'");
		} else {
			sql.append(",''");
		}
		if(urt.getElec_fence_r()!=null&&!urt.getElec_fence_r().equals("")){
			sql.append(","+Long.valueOf(urt.getElec_fence_r(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getOverspeed_warning_diff()!=null&&!urt.getOverspeed_warning_diff().equals("")){
			sql.append(","+Long.valueOf(urt.getOverspeed_warning_diff(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getFatigue_warning_diff()!=null&&!urt.getFatigue_warning_diff().equals("")){
			sql.append(","+Long.valueOf(urt.getFatigue_warning_diff(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getCharacteristics_ratio()!=null&&!urt.getCharacteristics_ratio().equals("")){
			sql.append(","+Long.valueOf(urt.getCharacteristics_ratio(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getWheel_each_turn_pulse_number()!=null&&!urt.getWheel_each_turn_pulse_number().equals("")){
			sql.append(","+Long.valueOf(urt.getWheel_each_turn_pulse_number(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getTank_capacity()!=null&&!urt.getTank_capacity().equals("")){
			sql.append(","+Long.valueOf(urt.getTank_capacity(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getAdditional_information_setup()!=null&&!urt.getAdditional_information_setup().equals("")){
			sql.append(",'"+urt.getAdditional_information_setup()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getDoor_photos_control()!=null&&!urt.getDoor_photos_control().equals("")){
			sql.append(",'"+urt.getDoor_photos_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getTerminal_sense_config()!=null&&!urt.getTerminal_sense_config().equals("")){
			sql.append(",'"+urt.getTerminal_sense_config()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getBlind_area_mode()!=null&&!urt.getBlind_area_mode().equals("")){
			sql.append(",'"+urt.getBlind_area_mode()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getTiming_photos_control()!=null&&!urt.getTiming_photos_control().equals("")){
			sql.append(",'"+urt.getTiming_photos_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSpace_photos_control()!=null&&!urt.getSpace_photos_control().equals("")){
			sql.append(",'"+urt.getSpace_photos_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSpeed_source()!=null&&!urt.getSpeed_source().equals("")){
			sql.append(",'"+Long.valueOf(urt.getSpeed_source(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getResolution()!=null&&!urt.getResolution().equals("")){
			sql.append(","+Long.valueOf(urt.getResolution(), 16));
		}else{
			sql.append(",''");
		}
		if(urt.getLicense_type()!=null&&!urt.getLicense_type().equals("")){
			sql.append(",'"+urt.getLicense_type()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSpeech_output_channel_control()!=null&&!urt.getSpeech_output_channel_control().equals("")){
			sql.append(",'"+urt.getSpeech_output_channel_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSwitch_trip()!=null&&!urt.getSwitch_trip().equals("")){
			sql.append(",'"+Integer.parseInt(urt.getSwitch_trip(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getTqc_alarm_psngrs_standard_cnt()!=null&&!urt.getTqc_alarm_psngrs_standard_cnt().equals("")){
			sql.append(",'"+Integer.parseInt(urt.getTqc_alarm_psngrs_standard_cnt(),16)+"'");
		}else{
			sql.append(",''");
		}		
		sql.append(")");
		log.debug("<BuildSQL>解析上行参数报文，组装入库语句：" + sql.toString());
		return sql.toString();
	}

	public String buildInsert808TerminalParamSql(Up_InfoContent urt,
			TerminalBean tb) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		// TerminalBean tb =
		// TerminalCacheManager.getInstance().getValue(urt.getTerminalId());
		sql
				.append("insert into CLW_JC_TERMINAL_PARA_T (TERMINAL_ID,CREATER,CREATE_TIME, KEEPALIVE_TIME,TCP_OVERTIME,"
						+ "TCP_RETRANS_TIME,UDP_OVERTIME,UDP_RETRANS_TIME,SMS_OVERTIME,SMS_RETRANS_TIME,main_Apn,main_user,"
						+ "main_pass,main_ip,standby_apn,standby_user,standby_pass,standby_ip,tcp_port,udp_port,position_up_type,"
						+ "position_up_scheme,driver_over_datetime,sleep_datetime,sos_time,default_datetime,default_spacetime,"
						+ "driver_over_spacetime,SLEEP_SPACETIME,SOS_SPACETIME,makeup_angle,monitor_phone,reset_phone,reset_factory,"
						+ "monitor_smsphone,terminal_smsphone,terminal_phone_tactic,call_timeper,calltime_monch,listen_phone,"
						+ "monitor_privilege_phone,alarm_shield,alarmsms_switch,alarmshoot_switch,alarmshoot_saveflag,key_flag,"
						+ "top_speed,overspeed_time,drivertime_limit,drivertime_day_limit,min_timeout,max_timeout,media_quality,"
						+ "luminance,contrast,saturation,chroma,odometer,provinceid,cityid,number_plate,number_plate_color," +
								"ELECTRONIC_FENCE_RADIUS,OVERSPEED_ALARM_DIFFERENCE,FATIGUE_DIVING_DIFFERENCE,CHARACTERISTIC_OEFFICIENT," +
								"WHEELPULSES_PER_SECOND,FUELTANK_CAPACITY,ADDITIONAL_INFORMATION_SETUP,CARDOOR_CONTROL,TERMINAL_PERIPHERAL_CONTROL," +
								"BLINDAREA_STATE,REGULAR_CAMERA_CONTROL,FIXEDDISTANCE_CAMERA_CONTROL,SPEED_SOURCE_SETTING," +
								"RESOLUTION,LICENSE_PLATE_TYPE,VOICE_OUTPUT_CONTROL,SWITCH_TRIP,STANDARD_CNT) ");
		sql.append("values('");
		sql.append(tb.getTerminal_id() + "',");
		sql.append("'clw_xc',");
		sql.append("sysdate");
		if (urt.getHeartinterval() != null
				&& !urt.getHeartinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getHeartinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getTcpovertime() != null && !urt.getTcpovertime().equals("")) {
			sql.append("," + Long.valueOf(urt.getTcpovertime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getTcpretransnum() != null
				&& !urt.getTcpretransnum().equals("")) {
			sql.append("," + Long.valueOf(urt.getTcpretransnum(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getUdpovertime() != null && !urt.getUdpovertime().equals("")) {
			sql.append("," + Long.valueOf(urt.getUdpovertime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getUdpretransnum() != null
				&& !urt.getUdpretransnum().equals("")) {
			sql.append("," + Long.valueOf(urt.getUdpretransnum(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSmsovertime() != null && !urt.getSmsovertime().equals("")) {
			sql.append("," + Long.valueOf(urt.getSmsovertime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSmsretransnum() != null
				&& !urt.getSmsretransnum().equals("")) {
			sql.append("," + Long.valueOf(urt.getSmsretransnum(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getServerapn() != null && !urt.getServerapn().equals("")) {
			sql.append(",'" + urt.getServerapn() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServerwirelessuser() != null
				&& !urt.getServerwirelessuser().equals("")) {
			sql.append(",'" + urt.getServerwirelessuser() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServerwirelesspass() != null
				&& !urt.getServerwirelesspass().equals("")) {
			sql.append(",'" + urt.getServerwirelesspass() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getServerip() != null && !urt.getServerip().equals("")) {
			sql.append(",'" + urt.getServerip() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackapn() != null && !urt.getBackapn().equals("")) {
			sql.append(",'" + urt.getBackapn() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackuser() != null && !urt.getBackuser().equals("")) {
			sql.append(",'" + urt.getBackuser() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackpass() != null && !urt.getBackpass().equals("")) {
			sql.append(",'" + urt.getBackpass() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getBackip() != null && !urt.getBackip().equals("")) {
			sql.append(",'" + urt.getBackip() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getTcpport() != null && !urt.getTcpport().equals("")) {
			sql.append("," + Long.valueOf(urt.getTcpport(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getUdpport() != null && !urt.getUdpport().equals("")) {
			sql.append("," + Long.valueOf(urt.getUdpport(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getPositionstra() != null && !urt.getPositionstra().equals("")) {
			sql.append(",'" + Long.valueOf(urt.getPositionstra(), 16) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getPositionscheme() != null
				&& !urt.getPositionscheme().equals("")) {
			sql.append(",'" + Long.valueOf(urt.getPositionscheme(), 16) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getNotlogintimeinterval() != null
				&& !urt.getNotlogintimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getNotlogintimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSleeptimeinterval() != null
				&& !urt.getSleeptimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSleeptimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSostimeinterval() != null
				&& !urt.getSostimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSostimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getDefaulttimeinterval() != null
				&& !urt.getDefaulttimeinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getDefaulttimeinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getDefaultdistanceinterval() != null
				&& !urt.getDefaultdistanceinterval().equals("")) {
			sql.append(","+ Long.valueOf(urt.getDefaultdistanceinterval(),16));
		} else {
			sql.append(",0");
		}
		if (urt.getNotlogindistanceinterval() != null
				&& !urt.getNotlogindistanceinterval().equals("")) {
			sql.append(","
					+ Long.valueOf(urt.getNotlogindistanceinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSleepdistanceinterval() != null
				&& !urt.getSleepdistanceinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSleepdistanceinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSosdistanceinterval() != null
				&& !urt.getSosdistanceinterval().equals("")) {
			sql.append("," + Long.valueOf(urt.getSosdistanceinterval(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getInflectionpoint() != null
				&& !urt.getInflectionpoint().equals("")) {
			sql.append("," + Long.valueOf(urt.getInflectionpoint(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getPlatphonenum() != null && !urt.getPlatphonenum().equals("")) {
			sql.append(",'" + urt.getPlatphonenum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getResetnum() != null && !urt.getResetnum().equals("")) {
			sql.append(",'" + urt.getResetnum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getFactorynum() != null && !urt.getFactorynum().equals("")) {
			sql.append(",'" + urt.getFactorynum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getSmsnum() != null && !urt.getSmsnum().equals("")) {
			sql.append(",'" + urt.getSmsnum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getRecsmsnum() != null && !urt.getRecsmsnum().equals("")) {
			sql.append(",'" + urt.getRecsmsnum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getTerminalstrategy() != null
				&& !urt.getTerminalstrategy().equals("")) {
			sql.append(",'" + Long.valueOf(urt.getTerminalstrategy(), 16) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getLongesttalk() != null && !urt.getLongesttalk().equals("")) {
			if(urt.getLongesttalk().equals("FFFFFFFF")){
				sql.append(",99999999");
			}else{
				sql.append("," + Long.valueOf(urt.getLongesttalk(), 16));
			}
		} else {
			sql.append(",0");
		}
		if (urt.getCurrentmonthtalk() != null
				&& !urt.getCurrentmonthtalk().equals("")) {
			if(urt.getLongesttalk().equals("FFFFFFFF")){
				sql.append(",99999999");
			}else{
				sql.append("," + Long.valueOf(urt.getCurrentmonthtalk(), 16));
			}
		} else {
			sql.append(",0");
		}
		if (urt.getListennum() != null && !urt.getListennum().equals("")) {
			sql.append(",'" + urt.getListennum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getPlatmessagenum() != null
				&& !urt.getPlatmessagenum().equals("")) {
			sql.append(",'" + urt.getPlatmessagenum() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmword() != null && !urt.getAlarmword().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getAlarmword()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmtext() != null && !urt.getAlarmtext().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getAlarmtext()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmshootingswitch() != null
				&& !urt.getAlarmshootingswitch().equals("")) {
			sql.append(",'" +Converser.hexTo2BCD(urt.getAlarmshootingswitch()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getAlarmshootingsign() != null
				&& !urt.getAlarmshootingsign().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getAlarmshootingsign()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getKeysign() != null && !urt.getKeysign().equals("")) {
			sql.append(",'" + Converser.hexTo2BCD(urt.getKeysign()) + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getMaxspeed() != null && !urt.getMaxspeed().equals("")) {
			sql.append("," + Long.valueOf(urt.getMaxspeed(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getOverspeedtime() != null
				&& !urt.getOverspeedtime().equals("")) {
			sql.append("," + Long.valueOf(urt.getOverspeedtime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getContinuousdriving() != null
				&& !urt.getContinuousdriving().equals("")) {
			sql.append("," + Long.valueOf(urt.getContinuousdriving(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getCurrenttotaldriving() != null
				&& !urt.getCurrenttotaldriving().equals("")) {
			sql.append("," + Long.valueOf(urt.getCurrenttotaldriving(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getMinresttime() != null && !urt.getMinresttime().equals("")) {
			sql.append("," + Long.valueOf(urt.getMinresttime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getMaxstoptime() != null && !urt.getMaxstoptime().equals("")) {
			sql.append("," + Long.valueOf(urt.getMaxstoptime(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getIvquality() != null && !urt.getIvquality().equals("")) {
			sql.append("," + Long.valueOf(urt.getIvquality(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getBrightness() != null && !urt.getBrightness().equals("")) {
			sql.append("," + Long.valueOf(urt.getBrightness(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getContrast() != null && !urt.getContrast().equals("")) {
			sql.append("," + Long.valueOf(urt.getContrast(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getSaturation() != null && !urt.getSaturation().equals("")) {
			sql.append("," + Long.valueOf(urt.getSaturation(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getChroma() != null && !urt.getChroma().equals("")) {
			sql.append("," + Long.valueOf(urt.getChroma(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getMilestones() != null && !urt.getMilestones().equals("")) {
			sql.append("," + Long.valueOf(urt.getMilestones(), 16));
		} else {
			sql.append(",0");
		}
		if (urt.getProvinceid() != null && !urt.getProvinceid().equals("")) {
			sql.append(",'" + urt.getProvinceid() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getCityid() != null && !urt.getCityid().equals("")) {
			sql.append(",'" + urt.getCityid() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getRegno() != null && !urt.getRegno().equals("")) {
			sql.append(",'" + urt.getRegno() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getRegcolor() != null && !urt.getRegcolor().equals("")) {
			sql.append(",'" + Integer.parseInt(urt.getRegcolor(),16) + "'");
		} else {
			sql.append(",''");
		}
		if(urt.getElec_fence_r()!=null&&!urt.getElec_fence_r().equals("")){
			sql.append(","+Long.valueOf(urt.getElec_fence_r(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getOverspeed_warning_diff()!=null&&!urt.getOverspeed_warning_diff().equals("")){
			sql.append(","+Long.valueOf(urt.getOverspeed_warning_diff(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getFatigue_warning_diff()!=null&&!urt.getFatigue_warning_diff().equals("")){
			sql.append(","+Long.valueOf(urt.getFatigue_warning_diff(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getCharacteristics_ratio()!=null&&!urt.getCharacteristics_ratio().equals("")){
			sql.append(","+Long.valueOf(urt.getCharacteristics_ratio(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getWheel_each_turn_pulse_number()!=null&&!urt.getWheel_each_turn_pulse_number().equals("")){
			sql.append(","+Long.valueOf(urt.getWheel_each_turn_pulse_number(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getTank_capacity()!=null&&!urt.getTank_capacity().equals("")){
			sql.append(","+Long.valueOf(urt.getTank_capacity(), 16));
		}else{
			sql.append(",0");
		}
		if(urt.getAdditional_information_setup()!=null&&!urt.getAdditional_information_setup().equals("")){
			sql.append(",'"+urt.getAdditional_information_setup()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getDoor_photos_control()!=null&&!urt.getDoor_photos_control().equals("")){
			sql.append(",'"+urt.getDoor_photos_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getTerminal_sense_config()!=null&&!urt.getTerminal_sense_config().equals("")){
			sql.append(",'"+urt.getTerminal_sense_config()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getBlind_area_mode()!=null&&!urt.getBlind_area_mode().equals("")){
			sql.append(",'"+urt.getBlind_area_mode()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getTiming_photos_control()!=null&&!urt.getTiming_photos_control().equals("")){
			sql.append(",'"+urt.getTiming_photos_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSpace_photos_control()!=null&&!urt.getSpace_photos_control().equals("")){
			sql.append(",'"+urt.getSpace_photos_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSpeed_source()!=null&&!urt.getSpeed_source().equals("")){
			sql.append(",'"+Long.valueOf(urt.getSpeed_source(), 16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getResolution()!=null&&!urt.getResolution().equals("")){
			sql.append(","+Long.valueOf(urt.getResolution(), 16));
		}else{
			sql.append(",''");
		}
		if(urt.getLicense_type()!=null&&!urt.getLicense_type().equals("")){
			sql.append(",'"+urt.getLicense_type()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSpeech_output_channel_control()!=null&&!urt.getSpeech_output_channel_control().equals("")){
			sql.append(",'"+urt.getSpeech_output_channel_control()+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getSwitch_trip()!=null&&!urt.getSwitch_trip().equals("")){
			sql.append(",'"+Integer.parseInt(urt.getSwitch_trip(),16)+"'");
		}else{
			sql.append(",''");
		}
		if(urt.getTqc_alarm_psngrs_standard_cnt()!=null&&!urt.getTqc_alarm_psngrs_standard_cnt().equals("")){
			sql.append(",'"+Integer.parseInt(urt.getTqc_alarm_psngrs_standard_cnt(),16)+"'");
		}else{
			sql.append(",''");
		}		
		sql.append(")");
		log.debug("<BuildSQL>解析上行参数报文，组装入库语句：" + sql.toString());
		return sql.toString();
	}

	public String buildUpdate808TerminalParamSql(Up_InfoContent urt,
			TerminalBean tb) {
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		// TerminalBean tb =
		// TerminalCacheManager.getInstance().getValue(urt.getTerminalId());
		sql
				.append("update CLW_JC_TERMINAL_PARA_T set modifier = 'clwc_xc',modify_time = sysdate");
		if (urt.getHeartinterval() != null
				&& !urt.getHeartinterval().equals("")) {
			sql.append(",KEEPALIVE_TIME = "
					+ Long.valueOf(urt.getHeartinterval(), 16));
		}
		if (urt.getTcpovertime() != null && !urt.getTcpovertime().equals("")) {
			sql.append(",TCP_OVERTIME = "
					+ Long.valueOf(urt.getTcpovertime(), 16));
		}
		if (urt.getTcpretransnum() != null
				&& !urt.getTcpretransnum().equals("")) {
			sql.append(",TCP_RETRANS_TIME = "
					+ Long.valueOf(urt.getTcpretransnum(), 16));
		}
		if (urt.getUdpovertime() != null && !urt.getUdpovertime().equals("")) {
			sql.append(",UDP_OVERTIME = "
					+ Long.valueOf(urt.getUdpovertime(), 16));
		}
		if (urt.getUdpretransnum() != null
				&& !urt.getUdpretransnum().equals("")) {
			sql.append(",UDP_RETRANS_TIME = "
					+ Long.valueOf(urt.getUdpretransnum(), 16));
		}
		if (urt.getSmsovertime() != null && !urt.getSmsovertime().equals("")) {
			sql.append(",SMS_OVERTIME = "
					+ Long.valueOf(urt.getSmsovertime(), 16));
		}
		if (urt.getSmsretransnum() != null
				&& !urt.getSmsretransnum().equals("")) {
			sql.append(",SMS_RETRANS_TIME = "
					+ Long.valueOf(urt.getSmsretransnum(), 16));
		}
		if (urt.getServerapn() != null && !urt.getServerapn().equals("")) {
			sql.append(",main_Apn = '" + urt.getServerapn() + "'");
		}
		if (urt.getServerwirelessuser() != null
				&& !urt.getServerwirelessuser().equals("")) {
			sql.append(",main_user = '" + urt.getServerwirelessuser() + "'");
		}
		if (urt.getServerwirelesspass() != null
				&& !urt.getServerwirelesspass().equals("")) {
			sql.append(",main_pass = '" + urt.getServerwirelesspass() + "'");
		}
		if (urt.getServerip() != null && !urt.getServerip().equals("")) {
			sql.append(",main_ip = '" + urt.getServerip() + "'");
		}
		if (urt.getBackapn() != null && !urt.getBackapn().equals("")) {
			sql.append(",standby_apn = '" + urt.getBackapn() + "'");
		}
		if (urt.getBackuser() != null && !urt.getBackuser().equals("")) {
			sql.append(",standby_user = '" + urt.getBackuser() + "'");
		}
		if (urt.getBackpass() != null && !urt.getBackpass().equals("")) {
			sql.append(",standby_pass = '" + urt.getBackpass() + "'");
		}
		if (urt.getBackip() != null && !urt.getBackip().equals("")) {
			sql.append(",standby_ip = '" + urt.getBackip() + "'");
		}
		if (urt.getTcpport() != null && !urt.getTcpport().equals("")) {
			sql.append(",tcp_port = " + Long.valueOf(urt.getTcpport(), 16));
		}
		if (urt.getUdpport() != null && !urt.getUdpport().equals("")) {
			sql.append(",udp_port = " + Long.valueOf(urt.getUdpport(), 16));
		}
		if (urt.getPositionstra() != null && !urt.getPositionstra().equals("")) {
			sql.append(",position_up_type = '" + Long.valueOf(urt.getPositionstra(),16) + "'");
		}
		if (urt.getPositionscheme() != null
				&& !urt.getPositionscheme().equals("")) {
			sql.append(",position_up_scheme = '" + Long.valueOf(urt.getPositionscheme(),16)+ "'");
		}
		if (urt.getNotlogintimeinterval() != null
				&& !urt.getNotlogintimeinterval().equals("")) {
			sql.append(",driver_over_datetime = "
					+ Long.valueOf(urt.getNotlogintimeinterval(), 16));
		}
		if (urt.getSleeptimeinterval() != null
				&& !urt.getSleeptimeinterval().equals("")) {
			sql.append(",sleep_datetime = "
					+ Long.valueOf(urt.getSleeptimeinterval(), 16));
		}
		if (urt.getSostimeinterval() != null
				&& !urt.getSostimeinterval().equals("")) {
			sql.append(",sos_time = "
					+ Long.valueOf(urt.getSostimeinterval(), 16));
		}
		if (urt.getDefaulttimeinterval() != null
				&& !urt.getDefaulttimeinterval().equals("")) {
			sql.append(",default_datetime = "
					+ Long.valueOf(urt.getDefaulttimeinterval(), 16));
		}
		if (urt.getDefaultdistanceinterval() != null
				&& !urt.getDefaultdistanceinterval().equals("")) {
			sql.append(",default_spacetime = "
					+ Long.valueOf(urt.getDefaultdistanceinterval(), 16));
		}
		if (urt.getNotlogindistanceinterval() != null
				&& !urt.getNotlogindistanceinterval().equals("")) {
			sql.append(",driver_over_spacetime = "
					+ Long.valueOf(urt.getNotlogindistanceinterval(), 16));
		}
		if (urt.getSleepdistanceinterval() != null
				&& !urt.getSleepdistanceinterval().equals("")) {
			sql.append(",SLEEP_SPACETIME = "
					+ Long.valueOf(urt.getSleepdistanceinterval(), 16));
		}
		if (urt.getSosdistanceinterval() != null
				&& !urt.getSosdistanceinterval().equals("")) {
			sql.append(",SOS_SPACETIME = "
					+ Long.valueOf(urt.getSosdistanceinterval(), 16));
		}
		if (urt.getInflectionpoint() != null
				&& !urt.getInflectionpoint().equals("")) {
			sql.append(",makeup_angle = "
					+ Long.valueOf(urt.getInflectionpoint(), 16));
		}
		if (urt.getPlatphonenum() != null && !urt.getPlatphonenum().equals("")) {
			sql.append(",monitor_phone = '" + urt.getPlatphonenum() + "'");
		}
		if (urt.getResetnum() != null && !urt.getResetnum().equals("")) {
			sql.append(",reset_phone = '" + urt.getResetnum() + "'");
		}
		if (urt.getFactorynum() != null && !urt.getFactorynum().equals("")) {
			sql.append(",reset_factory = '" + urt.getFactorynum() + "'");
		}
		if (urt.getSmsnum() != null && !urt.getSmsnum().equals("")) {
			sql.append(",monitor_smsphone = '" + urt.getSmsnum() + "'");
		}
		if (urt.getRecsmsnum() != null && !urt.getRecsmsnum().equals("")) {
			sql.append(",terminal_smsphone = '" + urt.getRecsmsnum() + "'");
		}
		if (urt.getTerminalstrategy() != null
				&& !urt.getTerminalstrategy().equals("")) {
			sql.append(",terminal_phone_tactic = '" + Long.valueOf(urt.getTerminalstrategy(), 16)
					+ "'");
		}
		if (urt.getLongesttalk() != null && !urt.getLongesttalk().equals("")) {
			if(urt.getLongesttalk().equals("FFFFFFFF")){
				sql.append(",call_timeper = 99999999");
			}else{
				sql.append(",call_timeper = "+ Long.valueOf(urt.getLongesttalk(), 16));
			}
		}
		if (urt.getCurrentmonthtalk() != null
				&& !urt.getCurrentmonthtalk().equals("")) {
			if(urt.getCurrentmonthtalk().equals("FFFFFFFF")){
				sql.append(",calltime_monch = 99999999");
			}else{
				sql.append(",calltime_monch = "
						+ Long.valueOf(urt.getCurrentmonthtalk(), 16));
			}
		}
		if (urt.getListennum() != null && !urt.getListennum().equals("")) {
			sql.append(",listen_phone = '" + urt.getListennum() + "'");
		}
		if (urt.getPlatmessagenum() != null
				&& !urt.getPlatmessagenum().equals("")) {
			sql.append(",monitor_privilege_phone = '" + urt.getPlatmessagenum()
					+ "'");
		}
		if (urt.getAlarmword() != null && !urt.getAlarmword().equals("")) {
			sql.append(",alarm_shield = '" + Converser.hexTo2BCD(urt.getAlarmword()) + "'");
		}
		if (urt.getAlarmtext() != null && !urt.getAlarmtext().equals("")) {
			sql.append(",alarmsms_switch = '" + Converser.hexTo2BCD(urt.getAlarmtext()) + "'");
		}
		if (urt.getAlarmshootingswitch() != null
				&& !urt.getAlarmshootingswitch().equals("")) {
			sql.append(",alarmshoot_switch = '" + Converser.hexTo2BCD(urt.getAlarmshootingswitch()) 
					+ "'");
		}
		if (urt.getAlarmshootingsign() != null
				&& !urt.getAlarmshootingsign().equals("")) {
			sql.append(",alarmshoot_saveflag = '" + Converser.hexTo2BCD(urt.getAlarmshootingsign()) 
					+ "'");
		}
		if (urt.getKeysign() != null && !urt.getKeysign().equals("")) {
			sql.append(",key_flag = '" + Converser.hexTo2BCD(urt.getKeysign())  + "'");
		}
		if (urt.getMaxspeed() != null && !urt.getMaxspeed().equals("")) {
			sql.append(",top_speed = " + Long.valueOf(urt.getMaxspeed(), 16));
		}
		if (urt.getOverspeedtime() != null
				&& !urt.getOverspeedtime().equals("")) {
			sql.append(",overspeed_time = "
					+ Long.valueOf(urt.getOverspeedtime(), 16));
		}
		if (urt.getContinuousdriving() != null
				&& !urt.getContinuousdriving().equals("")) {
			sql.append(",drivertime_limit = "
					+ Long.valueOf(urt.getContinuousdriving(), 16));
		}
		if (urt.getCurrenttotaldriving() != null
				&& !urt.getCurrenttotaldriving().equals("")) {
			sql.append(",drivertime_day_limit = "
					+ Long.valueOf(urt.getCurrenttotaldriving(), 16));
		}
		if (urt.getMinresttime() != null && !urt.getMinresttime().equals("")) {
			sql.append(",min_timeout = "
					+ Long.valueOf(urt.getMinresttime(), 16));
		}
		if (urt.getMaxstoptime() != null && !urt.getMaxstoptime().equals("")) {
			sql.append(",max_timeout = "
					+ Long.valueOf(urt.getMaxstoptime(), 16));
		}
		if (urt.getIvquality() != null && !urt.getIvquality().equals("")) {
			sql.append(",media_quality = "
					+ Long.valueOf(urt.getIvquality(), 16));
		}
		if (urt.getContrast() != null && !urt.getContrast().equals("")) {
			sql.append(",luminance = " + Long.valueOf(urt.getContrast(), 16));
		}
		if (urt.getSaturation() != null && !urt.getSaturation().equals("")) {
			sql
					.append(",saturation = "
							+ Long.valueOf(urt.getSaturation(), 16));
		}
		if (urt.getChroma() != null && !urt.getChroma().equals("")) {
			sql.append(",chroma = " + Long.valueOf(urt.getChroma(), 16));
		}
		if (urt.getMilestones() != null && !urt.getMilestones().equals("")) {
			sql.append(",odometer = " + Long.valueOf(urt.getMilestones(), 16));
		}
		if (urt.getProvinceid() != null && !urt.getProvinceid().equals("")) {
			sql.append(",provinceid = '" + urt.getProvinceid() + "'");
		}
		if (urt.getCityid() != null && !urt.getCityid().equals("")) {
			sql.append(",cityid = '" + urt.getCityid() + "'");
		}
		if (urt.getRegno() != null && !urt.getRegno().equals("")) {
			sql.append(",number_plate = '" + urt.getRegno() + "'");
		}
		if (urt.getRegcolor() != null && !urt.getRegcolor().equals("")) {
			sql.append(",number_plate_color = '" + Integer.parseInt(urt.getRegcolor(),16) + "'");
		}
		if(urt.getElec_fence_r()!=null&&!urt.getElec_fence_r().equals("")){
			sql.append(",ELECTRONIC_FENCE_RADIUS="+Long.valueOf(urt.getElec_fence_r(), 16));
		}
		if(urt.getOverspeed_warning_diff()!=null&&!urt.getOverspeed_warning_diff().equals("")){
			sql.append(",OVERSPEED_ALARM_DIFFERENCE="+Long.valueOf(urt.getOverspeed_warning_diff(), 16));
		}
		if(urt.getFatigue_warning_diff()!=null&&!urt.getFatigue_warning_diff().equals("")){
			sql.append(",FATIGUE_DIVING_DIFFERENCE="+Long.valueOf(urt.getFatigue_warning_diff(), 16));
		}
		if(urt.getCharacteristics_ratio()!=null&&!urt.getCharacteristics_ratio().equals("")){
			sql.append(",CHARACTERISTIC_OEFFICIENT="+Long.valueOf(urt.getCharacteristics_ratio(), 16));
		}
		if(urt.getWheel_each_turn_pulse_number()!=null&&!urt.getWheel_each_turn_pulse_number().equals("")){
			sql.append(",WHEELPULSES_PER_SECOND="+Long.valueOf(urt.getWheel_each_turn_pulse_number(), 16));
		}
		if(urt.getTank_capacity()!=null&&!urt.getTank_capacity().equals("")){
			sql.append(",FUELTANK_CAPACITY="+Long.valueOf(urt.getTank_capacity(), 16));
		}
		if(urt.getAdditional_information_setup()!=null&&!urt.getAdditional_information_setup().equals("")){
			sql.append(",ADDITIONAL_INFORMATION_SETUP='"+urt.getAdditional_information_setup()+"'");
		}
		if(urt.getDoor_photos_control()!=null&&!urt.getDoor_photos_control().equals("")){
			sql.append(",CARDOOR_CONTROL='"+urt.getDoor_photos_control()+"'");
		}
		if(urt.getTerminal_sense_config()!=null&&!urt.getTerminal_sense_config().equals("")){
			sql.append(",TERMINAL_PERIPHERAL_CONTROL='"+urt.getTerminal_sense_config()+"'");
		}
		if(urt.getBlind_area_mode()!=null&&!urt.getBlind_area_mode().equals("")){
			sql.append(",BLINDAREA_STATE='"+urt.getBlind_area_mode()+"'");
		}
		if(urt.getTiming_photos_control()!=null&&!urt.getTiming_photos_control().equals("")){
			sql.append(",REGULAR_CAMERA_CONTROL='"+urt.getTiming_photos_control()+"'");
		}
		if(urt.getSpace_photos_control()!=null&&!urt.getSpace_photos_control().equals("")){
			sql.append(",FIXEDDISTANCE_CAMERA_CONTROL='"+urt.getSpace_photos_control()+"'");
		}
		if(urt.getSpeed_source()!=null&&!urt.getSpeed_source().equals("")){
			sql.append(",SPEED_SOURCE_SETTING='"+Long.valueOf(urt.getSpeed_source(), 16)+"'");
		}
		if(urt.getResolution()!=null&&!urt.getResolution().equals("")){
			sql.append(",RESOLUTION="+Long.valueOf(urt.getResolution(), 16));
		}
		if(urt.getLicense_type()!=null&&!urt.getLicense_type().equals("")){
			sql.append(",LICENSE_PLATE_TYPE='"+urt.getLicense_type()+"'");
		}
		if(urt.getSpeech_output_channel_control()!=null&&!urt.getSpeech_output_channel_control().equals("")){
			sql.append(",VOICE_OUTPUT_CONTROL='"+urt.getSpeech_output_channel_control()+"'");
		}
		if(urt.getSwitch_trip()!=null&&!urt.getSwitch_trip().equals("")){
			sql.append(",SWITCH_TRIP='"+Integer.parseInt(urt.getSwitch_trip(),16)+"'");
		}
		if(urt.getTqc_alarm_psngrs_standard_cnt()!=null&&!urt.getTqc_alarm_psngrs_standard_cnt().equals("")){
			sql.append(",SWITCH_TRIP='"+Integer.parseInt(urt.getTqc_alarm_psngrs_standard_cnt(),16)+"'");
		}		
		sql.append(" where terminal_id = '"+tb.getTerminal_id()+"'");
		log.debug("<BuildSQL>解析上行参数报文，组装入库语句：" + sql.toString());
		return sql.toString();
	}

	public String buildInsertUpMuldiaSql(Up_InfoContent urt)
			throws ParseException {
		StringBuffer sql = new StringBuffer();
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				urt.getTerminalId());
		sql
				.append("insert into CLW_YW_PHOTO_T (PHOTO_ID,MEDIA_ID, VEHICLE_VIN, SIM_CARD_NUMBER,PHOTO_TIME,"
						+ "CHANNEL_NUMBER, OPERATE_USER_ID, OPERATE_TIME,PHOTO_EVENT)");
		sql.append("values('" + urt.getMsg_id().trim() + "','");
		sql.append(urt.getMsg_id().trim() + "','");
		sql.append(urt.getTerminalId().trim() + "','");
		sql.append(tb.getSim_card_number() + "',");
		sql.append("sysdate");
		if (urt.getChannel_number() != null
				&& !urt.getChannel_number().equals("")) {
			sql.append(",'" + urt.getChannel_number().trim() + "'");
		} else {
			sql.append(",''");
		}
		if (urt.getOperate_user_id() != null
				&& !urt.getOperate_user_id().equals("")) {
			sql.append(",'" + urt.getOperate_user_id() + "'");
		} else {
			sql.append(",'clw_xc'");
		}
		if (urt.getOperate_time() != null && !urt.getOperate_time().equals("")) {
			sql.append(",to_date(" + urt.getOperate_time()
					+ ",'yymmddhh24miss')");
		} else {
			sql.append(",sysdate");
		}
		if (urt.getMuldiaevent() != null && !urt.getMuldiaevent().equals("")) {
			sql.append(",'" + Integer.parseInt(urt.getMuldiaevent()) + "'");
		} else {
			sql.append(",''");
		}
		sql.append(")");
		log.debug("<BuildSQL>解析多媒体上传事件报文，组装插入语句：" + sql.toString());
		return sql.toString();
	}

	public String buildUpdateUpMuldiaSql(Up_InfoContent urt)
			throws ParseException {
		StringBuffer sql = new StringBuffer();
		sql.append("update CLW_YW_PHOTO_T set OPERATE_TIME = sysdate");
		if(urt.getPhoto_time()!=null&&!urt.getPhoto_time().equals("")){
			sql.append(",PHOTO_TIME = to_date(" + urt.getPhoto_time().trim()
					+ ",'yymmddhh24miss')");
		}
		if (urt.getChannel_number() != null
				&& !urt.getChannel_number().equals("")) {
			sql.append(",CHANNEL_NUMBER = '" + urt.getChannel_number().trim() + "'");
		}
		if (urt.getPhoto_file() != null && !urt.getPhoto_file().equals("")) {
			sql.append(",PHOTO_FILE = '" + urt.getPhoto_file().trim() + "'");
		} 
		if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")) {
			sql.append(",GPS_VALID = '" + urt.getGps_valid() + "'");
			if(urt.getGps_valid().equals("")){
				if (urt.getUtc_time() != null && !urt.getUtc_time().equals("")) {
					sql.append(",UTC_TIME = to_date(" + urt.getUtc_time().trim()
							+ ",'yymmddhh24miss')");
				} 
				if (urt.getLatitude() != null && !urt.getLatitude().equals("")) {
					sql.append(",LATITUDE = '" + urt.getLatitude().trim() + "'");
				} 
				if (urt.getLongitude() != null && !urt.getLongitude().equals("")) {
					sql.append(",LONGITUDE = '" + urt.getLongitude().trim() + "'");
				} 
				if (urt.getDirection() != null && urt.getDirection().equals("")) {
					sql.append(",DIRECTION = '" + urt.getDirection().trim() + "'");
				} 
				if (urt.getGps_speeding() != null
						&& !urt.getGps_speeding().equals("")) {
					sql.append(",GPS_SPEEDING = '" + urt.getGps_speeding().trim() + "')");
				} 
			}else{
				sql.append(",LATITUDE = 'FFFF'");
				sql.append(",LONGITUDE = 'FFFF'");
				sql.append(",DIRECTION = 'FFFF'");
				sql.append(",GPS_SPEEDING = 'FFFF'");
			}
			
		} 
		if (urt.getPhoto_event() != null && !urt.getPhoto_event().equals("")) {
			sql.append(",PHOTO_EVENT = '" + urt.getPhoto_event() + "'");
		}
		sql.append(" where msg_id = '"+urt.getMsg_id()+"'");
		log.debug("<BuildSQL>解析上行图片报文，组装插入语句：" + sql.toString());
		return sql.toString();
	}

	public String buildXcTerDataSql(Up_InfoContent udp) {
		StringBuffer sql = new StringBuffer();
		sql.append("update clw_jc_terminal_t set ");
		sql.append("VERSION_RECEIVING_TIME=sysdate");
		if(udp.getXc_hardware_ver()!=null&&!udp.getXc_hardware_ver().equals("")){
			sql.append(",HOST_HARD_VER='"+udp.getXc_hardware_ver()+"'");
		}else{
			sql.append(",HOST_HARD_VER=''");
		}
		if(udp.getXc_firmware_ver()!=null&&!udp.getXc_firmware_ver().equals("")){
			sql.append(",HOST_FIRM_VER='"+udp.getXc_firmware_ver()+"'");
		}
		if(udp.getXc_screen_hardware_ver()!=null&&!udp.getXc_screen_hardware_ver().equals("")){
			sql.append(",XIANSHI_HARD_VER='"+udp.getXc_screen_hardware_ver()+"'");
		}
		if(udp.getXc_screen_firmware_ver()!=null&&!udp.getXc_screen_firmware_ver().equals("")){
			sql.append(",XIANSHI_FIRM_VER='"+udp.getXc_screen_firmware_ver()+"'");
		}
		if(udp.getXc_video_hardware_ver()!=null&&!udp.getXc_video_hardware_ver().equals("")){
			sql.append(",DVR_HARD_VER='"+udp.getXc_video_hardware_ver()+"'");
		}
		if(udp.getXc_video_firmware_ver()!=null&&!udp.getXc_video_firmware_ver().equals("")){
			sql.append(",DVR_FIRM_VER='"+udp.getXc_video_firmware_ver()+"'");
		}
		if(udp.getXc_video_firmware_ver()!=null&&!udp.getXc_video_firmware_ver().equals("")){
			sql.append(",SHEPIN_HARD_VER='"+udp.getXc_rfcard_hardware_ver()+"'");
		}
		if(udp.getXc_rfcard_firmware_ver()!=null&&!udp.getXc_rfcard_firmware_ver().equals("")){
			sql.append(",SHEPIN_FIRM_VER='"+udp.getXc_rfcard_firmware_ver()+"'");
		}
		if(udp.getXc_sim_iccid()!=null&&!udp.getXc_sim_iccid().equals("")){
			sql.append(",SIM_SCCID='"+udp.getXc_sim_iccid()+"'");
		}
		if(udp.getHardware_terminal_id()!=null&&!udp.getHardware_terminal_id().equals("")){
			sql.append(",HARDWARE_TERMINAL_ID='"+udp.getHardware_terminal_id()+"'");
		}
		if(udp.getHardware_vehicle_vin()!=null&&!udp.getHardware_vehicle_vin().equals("")){
			sql.append(",HARDWARE_VEHICLE_VIN='"+udp.getHardware_vehicle_vin()+"'");
		}
		if(udp.getHardware_vehicle_ln()!=null&&!udp.getHardware_vehicle_ln().equals("")){
			sql.append(",HARDWARE_VEHICLE_LN='"+udp.getHardware_vehicle_ln()+"'");
		}
		if(udp.getHardware_veh_pai_color()!=null&&!udp.getHardware_veh_pai_color().equals("")){
			sql.append(",HARDWARE_VEH_PAI_COLOR='"+udp.getHardware_veh_pai_color()+"'");
		}
		sql.append(" where vehicle_vin='"+udp.getTerminalId()+"'");
		return sql.toString();
	}

	public String buildInsertRunRecordSql(Up_InfoContent udp) {
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO CLW_XC_RUN_RECORD_T(ID,VEHICLE_VIN,ON_DATE,OFF_DATE,OIL,MILEAGE,SPD_OIL,INSERT_TIME) VALUES(");
		sql.append("'"+IdCreater.getUUid()+"'");
		sql.append(",'"+udp.getTerminalId()+"'");
		sql.append(",to_date('"+udp.getOn_date()+"','yymmddhh24miss')");
		sql.append(",to_date('"+udp.getOff_date()+"','yymmddhh24miss')");
		if(udp.getOil()!=null&&!udp.getOil().equals("")){
			sql.append(",'"+udp.getOil()+"'");
		}else{
			sql.append(",''");
		}
		if(udp.getTotalmileage()!=null&&!udp.getTotalmileage().equals("")){
			sql.append(",'"+udp.getTotalmileage()+"'");
		}else{
			sql.append(",''");
		}
		if(udp.getSpd_oil()!=null&&!udp.getSpd_oil().equals("")){
			sql.append(",'"+udp.getSpd_oil()+"'");
		}else{
			sql.append(",''");
		}
		sql.append(",sysdate)");
		return sql.toString();
	}
	
	/**
	 * 偏航告警insert sql
	 */
	public String buildInsertOverPreLineAlarmSql(VehicleReal vr, String uuid) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss"); 
		StringBuffer sql = new StringBuffer();
		// TerminalBean tb = getTerminal(urt.getTerminalId());
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				vr.getVehicle_vin());
//		DriverVehicleBean dv = DriverVehicleCacheManager.getInstance().getValue(urt.getTerminalId()+Constant.DRIVER);
		sql
				.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,"
						+ "ALARM_TYPE_ID,SPEEDING,SPEED_UNIT,deal_flag,    ALARM_TIME,ALARM_FLAG,   ALARM_END_TIME," +
								"LONGITUDE,LATITUDE,DIRECTION)");
//		sql.append(" values(");
		sql.append(" values( ");
		sql.append("'" + uuid + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + vr.getVehicle_vin()+ "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'120'");   //120设置成偏航
		
		
		if (vr.getSpeed() != null && !vr.getSpeed().equals("")) {
			sql.append(",'" + vr.getSpeed() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'km/h'");
		sql.append(",'0'");
		sql.append(",to_date(" + sdf.format(vr.getStart_overtime()) + ",'yymmddhh24miss')");
		
		sql.append(",'1'");
		
		sql.append(",to_date(" + sdf.format(vr.getEnd_overtime()) + ",'yymmddhh24miss')");
		
		if (vr.getLongitude() != null && !vr.getLongitude().equals("")) {
			sql.append(",'" + vr.getLongitude() + "'");
		} else {
			sql.append(",''");
		}
		if (vr.getLatitude() != null && !vr.getLatitude().equals("")) {
			sql.append(",'" + vr.getLatitude() + "'");
		} else {
			sql.append(",''");
		}

		if (vr.getDirection() != null && !vr.getDirection().equals("")) {
			sql.append(",'" + vr.getDirection() + "')");
		} else {
			sql.append(",'')");
		}

		

		log.debug("<BuildSQL>偏航告警insert sql:" + sql.toString());
		return sql.toString();
	}
}
