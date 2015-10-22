package com.yutong.clw.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.nio.msg.value.Up_InfoContent;
/**
 * 拼接防偷漏油sql语句
 * @author ningdonghai
 */
public class FtlyBuildSQL{
	private static Logger log = LoggerFactory.getLogger(FtlyBuildSQL.class);    
	private static final String NAME = "<FtlyBuildSQL>";
	//拼接sql语句
	public static final FtlyBuildSQL ftlyBuildSql = new FtlyBuildSQL();
	
	public FtlyBuildSQL(){
	}	
	public static FtlyBuildSQL getInstance() {
		return ftlyBuildSql;		
	}
	
	/**
	 * 拼接插入 ZSPT_FTLY_INFO表语句
	 * 
	 * @param urt
	 * @return  String
	 */
	public String buildInsertZsptFtlyInfoSql(Up_InfoContent urt) {		
		StringBuffer sql = new StringBuffer();
		/*if("01".equals(urt.getZsptFtlyInfo().getOilboxState()) || "10".equals(urt.getZsptFtlyInfo().getOilboxState())){ //如果发生加油偷油报警 则更新ZSPT_FTLY_INFO前7分钟记录
			sql = this.buildInsertZsptFtlyInfoSqlForAddOrSteal(urt);
		}else{*/
			sql.append("insert into ZSPT_FTLY_INFO("
					+ "FTLY_ID,OILBOX_STATE,OILBOX_LEVEL,ADD_OILL,OILBOX_MASS,LATITUDE,LONGITUDE,"
					+ "ELEVATION,DIRECTION,GPS_SPEEDING,SPEEDING,REPORT_TIME,VIN_CODE,PT_FLAG,ZONENAME,OIL_DEVICE_VERSION,CREATE_TIME) values(");
	
			//sql.append("SEQ_FTLY_INFO.nextval");// 序列号
			sql.append("'" +nullToStr(urt.getZsptFtlyInfo().getFtlyId())+ "'");// 序列号
			
			sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getOilboxState()) + "'");//油箱油位异常标志 00:油位正常;01:偷油告警;10:加油告警;11:告警
			sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getOilboxLevel()) + "'");//油箱油位
			sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getAddOill()) + "'");//加油量
			sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getOilboxMass()) + "'");//油箱油量
			
			sql.append(",'" + nullToStr(urt.getLatitude()) + "'");// 纬度
			sql.append(",'" + nullToStr(urt.getLongitude()) + "'");// 经度		
			sql.append(",'" + nullToStr(urt.getElevation()) + "'");// 海拔
			sql.append(",'" + nullToStr(urt.getDirection()) + "'");// 方向		
			sql.append(",'" + nullToStr(urt.getGps_speeding()) + "'");// GPS速度
			sql.append(",'" + nullToStr(urt.getSpeed()) + "'");// 车速	
			sql.append(",TO_DATE('20" + urt.getTerminal_time() + "','YYYYMMDD HH24MISS')");// 终端上报时间   
			sql.append(",'" + nullToStr(urt.getTerminalId()) + "'");// 所属车辆识别码
			sql.append(",'" + nullToStr(urt.getPtFlag()) + "'");// 所属平台	
			sql.append(",'" + nullToStr(urt.getZonename()) + "'");// 区域位置	
			sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getOilDeviceVersion()) + "'");// 油量监控设备版本号
			sql.append(",sysdate)");
			log.info("<FtlyBuildSQL>[ZSPT_FTLY_INFO]表 插入 sql:" + sql.toString());
		//}
		return sql.toString();
	}
	
	/**
	 * 拼接插入 ZSPT_FTLY_ALARM表语句
	 * 
	 * @param urt
	 * @return  String
	 */
	public String buildInsertZsptFtlyAlarmSql(Up_InfoContent urt) {		
		StringBuffer sql = new StringBuffer();
		
		sql.append("insert into ZSPT_FTLY_ALARM("
						+ "FTLY_ID,OILBOX_STATE,OILBOX_LEVEL,ADD_OILL,OILBOX_MASS,LATITUDE,LONGITUDE,"
						+ "ELEVATION,DIRECTION,GPS_SPEEDING,SPEEDING,REPORT_TIME,VIN_CODE,PT_FLAG,ZONENAME) values(");
		
		//sql.append("SEQ_FTLY_INFO.nextval");// 序列号
		sql.append("'" +nullToStr(urt.getZsptFtlyInfo().getFtlyId())+ "'");// 序列号
		
		sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getOilboxState()) + "'");//油箱油位异常标志 00:油位正常;01:偷油告警;10:加油告警;11:告警
		sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getOilboxLevel()) + "'");//油箱油位
		sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getAddOill()) + "'");//加油量
		sql.append(",'" + nullToStr(urt.getZsptFtlyInfo().getOilboxMass()) + "'");//油箱油量
		
		sql.append(",'" + nullToStr(urt.getLatitude()) + "'");// 纬度
		sql.append(",'" + nullToStr(urt.getLongitude()) + "'");// 经度		
		sql.append(",'" + nullToStr(urt.getElevation()) + "'");// 海拔
		sql.append(",'" + nullToStr(urt.getDirection()) + "'");// 方向		
		sql.append(",'" + nullToStr(urt.getGps_speeding()) + "'");// GPS速度
		sql.append(",'" + nullToStr(urt.getSpeed()) + "'");// 车速	
		sql.append(",TO_DATE('20" + urt.getTerminal_time() + "','YYYYMMDD HH24MISS')");// 终端上报时间   
		sql.append(",'" + nullToStr(urt.getTerminalId()) + "'");// 所属车辆识别码
		sql.append(",'" + nullToStr(urt.getPtFlag()) + "'");// 所属平台	
		sql.append(",'" + nullToStr(urt.getZonename()) + "'");// 区域位置	
		sql.append(")");
		
		log.info("<FtlyBuildSQL>[ZSPT_FTLY_ALARM]表 插入 sql:" + sql.toString());
		return sql.toString();
	}
	
	/**
	 * 将空值转换为空字符串
	 * 
	 * @return String
	 */
	public static String nullToStr(String str) {
		return str == null || str.equals("null") ? "" : str.trim();
	}
	
	
	/**
	 * 拼接插入 ZSPT_FTLY_INFO表语句
	 * 
	 * @param urt
	 * @return  String
	 */
	public StringBuffer buildInsertZsptFtlyInfoSqlForAddOrSteal(Up_InfoContent urt) {		
		String report_time = "20"+urt.getTerminal_time();
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE ZSPT_FTLY_INFO SET ");
		sql.append(" OILBOX_STATE = '"+nullToStr(urt.getZsptFtlyInfo().getOilboxState())+"',");
		sql.append(" OILBOX_LEVEL='"+nullToStr(urt.getZsptFtlyInfo().getOilboxLevel())+"',");
		sql.append(" ADD_OILL='"+urt.getZsptFtlyInfo().getAddOill()+"',");
		sql.append(" OILBOX_MASS='"+urt.getZsptFtlyInfo().getOilboxMass()+"'");
		sql.append(" WHERE REPORT_TIME = (");
		sql.append(" SELECT MIN(T.REPORT_TIME)");
		sql.append(" FROM ZSPT_FTLY_INFO PARTITION(FTLY_INFO_"+report_time.substring(0,8)+") t");
		sql.append(" WHERE t.REPORT_TIME >= TO_DATE('20" + urt.getTerminal_time() + "','YYYYMMDD HH24MISS')-(1*390)/(24*60*60)");
		sql.append(" AND t.REPORT_TIME <= TO_DATE('20" + urt.getTerminal_time() + "','YYYYMMDD HH24MISS')");
		sql.append(" AND t.VIN_CODE = '"+urt.getTerminalId()+"'");
		sql.append(" )");
		log.info("<FtlyBuildSQL>发生'10','01'报警时[ZSPT_FTLY_INFO]表 更新 sql:" + sql.toString());
		return sql;
	}
}
