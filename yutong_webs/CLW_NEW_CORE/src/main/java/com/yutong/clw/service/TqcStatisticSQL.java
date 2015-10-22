package com.yutong.clw.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.yutong.clw.beans.report.RealTimeRecord;
import com.yutong.clw.dao.analysis.AbstractDaoManager;
import com.yutong.clw.utils.CDate;
import com.yutong.clw.utils.StringUtil;
import com.yutong.clw.utils.UUIDGenerator;

public class TqcStatisticSQL extends AbstractDaoManager{	
	private static Logger logger = LoggerFactory.getLogger(TqcStatisticSQL.class);
	// 创建服务对象
	private static final TqcStatisticSQL tqcStatisticSQL = new TqcStatisticSQL();
	public static TqcStatisticSQL getInstance() {
		return tqcStatisticSQL;
	}
	/**
	 * 删除数据(TQC_SEND_PASSENGER)数据
	 * 
	 * @param   dayDate
	 * @return  int
	 */
	public int delSendPassengerStatByDay(String dayDate){		
		String deleteSql = "delete from TQC_SEND_PASSENGER tsp "
			      +"  where tsp.START_TIME >= to_date('"+dayDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss') "
			      +"    and tsp.START_TIME <= to_date('"+dayDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";
		
		logger.info("----查询车辆信息表(TQC_SEND_PASSENGER)数据Sql-->>:"+deleteSql);		
		return jdbcTemplate.update(deleteSql,new Object[]{});
	}	
	/**
	 * 查询车辆信息表(CLW_CL_BASE_INFO_T)数据
	 * 
	 * @param 
	 * @return  list
	 */
	public List<Map<String,String>> getVehicleListInfo(String sDate){		
		//String vehicleSql = "select t.VEHICLE_VIN,VEHICLE_CODE from CLW_CL_BASE_INFO_T t where t.VALID_FLAG='0' ";		
		String vehicleSql=" select distinct(cxit.vehicle_vin) as VEHICLE_VIN  "
		                 + "  from CLW_XC_INOUTSITE_T cxit,CLW_CL_BASE_INFO_T ccbi  "
		                 + " where cxit.vehicle_vin=ccbi.vehicle_vin "
		                 + "   and ccbi.VALID_FLAG='0' "
		                 + "   and cxit.terminal_time >=to_date('"+sDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss') "
		                 + "   and cxit.terminal_time <=to_date('"+sDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";		
		
		logger.info("----查询车辆信息表(CLW_XC_INOUTSITE_T,CLW_CL_BASE_INFO_T)数据Sql-->>:"+vehicleSql);		
		return jdbcTemplate.queryForList(vehicleSql,new Object[]{});
	}	
	
	/**
	 * 查询终端记录表(CLW_YW_TERMINAL_RECORD_T)数据
	 * 
	 * @param strVin,startDate,endDate
	 * @return  list
	 */
	public List<RealTimeRecord> getTerminalRecord(String strVin, String startDate,String endDate){
		
		String terminalSql = "select TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,CREATE_TIME,TO_CHAR(TERMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TERMINAL_TIME,NVL(MILEAGE,'FFFF') MILEAGE,NVL(OIL_TOTAL,'FFFF') OIL_TOTAL,NVL(OIL_INSTANT,'FFFF') OIL_INSTANT"
				+ " from CLW_YW_TERMINAL_RECORD_T partition(TERMINAL_RECORD_"+ StringUtil.Sdate(startDate)+ ")" 
				+ " where vehicle_vin=  '"+ strVin+"'"
				+ "   and terminal_time >=to_date('"+startDate+"','yyyy-mm-dd hh24:mi:ss') "
				+ "   and terminal_time <=to_date('"+endDate+"','yyyy-mm-dd hh24:mi:ss') "
				+ " order by terminal_time asc  ";
		
		logger.info("----查询终端记录表(CLW_YW_TERMINAL_RECORD_T)数据Sql-->>:"+terminalSql);		
		return jdbcTemplate.query(terminalSql,ParameterizedBeanPropertyRowMapper.newInstance(RealTimeRecord.class));
	}	
	
	/**
	 * 查询专属应用防偷漏油表(ZSPT_FTLY_INFO)数据
	 * 
	 * @param strVin,startDate,endDate
	 * @return  list
	 */
	public List getFtlyInfoRecord(String strVin, String startDate,String endDate){
		
		String ftlyInfoSql = "select VIN_CODE,TO_CHAR(REPORT_TIME,'yyyy-mm-dd hh24:mi:ss') AS TERMINAL_TIME,OILBOX_STATE,NVL(ADD_OILL,'FFFF') AS ADD_OILL,NVL(OILBOX_MASS,'FFFF') AS OILBOX_MASS "
				//+ " from ZSPT_FTLY_INFO partition(FTLY_INFO_"+ StringUtil.Sdate(startDate)+ ")" 
			   + " from ZSPT_FTLY_INFO "
				+ " where VIN_CODE=  '"+ strVin+"'"
				+ "   and REPORT_TIME >=to_date('"+startDate+"','yyyy-mm-dd hh24:mi:ss') "
				+ "   and REPORT_TIME <=to_date('"+endDate+"','yyyy-mm-dd hh24:mi:ss') "
				+ " order by REPORT_TIME asc  ";
		
		logger.info("----查询专属应用防偷漏油表(ZSPT_FTLY_INFO)数据Sql-->>:"+ftlyInfoSql);		
		return jdbcTemplate.queryForList(ftlyInfoSql,new Object[]{});
	}	
	
	/**
	 * 查询单个车辆每天每个线路、行程的【起点站、起点时间、终点站、终点时间】时间集合，
	 * 
	 * 按出站点时间计算
	 * 
	 * @param strVin,date
	 * @return  list
	 */
	public List<Map> getSiteTimeRecord(String strVin, String startDate){
		
		// 因 终端上报时间 进/出站时间一致的  严重问题  所以将 terminal_time 全部修改为 create_time
		
		String  siteTimeSql=
			   //--查询每个行程起点站
			   "WITH aa as "
		        + " (select t1.vehicle_vin,t1.route_id,t1.trip_id,t1.site_id,t2.rs_order,"
		        + "  max(t1.terminal_time) as terminal_time,t1.terminal_tripid "
		        + " from CLW_XC_INOUTSITE_T t1,CLW_XC_ROUTESITE_T t2 "
		        + " where t1.vehicle_vin='"+ strVin+"'"
		        + "   and t1.route_id=t2.route_id "
		        + "   and t1.site_id=t2.site_id "
		        + "   and t1.inout_flag=1 " //进出站状态: 0-到站, 1-出站		        
		        + "   and t1.terminal_time >=to_date('"+startDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss') "
				+ "   and t1.terminal_time <=to_date('"+startDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss') "
				+ "   and t2.rs_order=(select min(cxr.rs_order) from CLW_XC_ROUTESITE_T cxr where cxr.route_id=t1.route_id) "
				+ " group by t1.vehicle_vin, t1.route_id,t1.trip_id, t1.site_id, t2.rs_order, t1.terminal_tripid ),"
				
		       //--查询每个行程终点站
				+ " bb as "
				+ " (select t1.vehicle_vin,t1.route_id,t1.trip_id,t1.site_id,t2.rs_order,"
				+ "  max(t1.terminal_time) as terminal_time,t1.terminal_tripid "
				+ "   from CLW_XC_INOUTSITE_T t1,CLW_XC_ROUTESITE_T t2 "
				+ "  where t1.vehicle_vin='"+ strVin+"'"
				+ "    and t1.route_id=t2.route_id "
		        + "    and t1.site_id=t2.site_id "
		        + "    and t1.inout_flag=0 " //进出站状态: 0-到站, 1-出站		        
		        + "    and t1.terminal_time >=to_date('"+startDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss') "
				+ "    and t1.terminal_time <=to_date('"+startDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss') "
				+ "    and t2.rs_order=(select max(cxr.rs_order) from CLW_XC_ROUTESITE_T cxr where cxr.route_id=t1.route_id) "
				+ " group by t1.vehicle_vin, t1.route_id,t1.trip_id, t1.site_id, t2.rs_order, t1.terminal_tripid ),"
				
			  //--查询每条行程“前一站”的承载人数，及其它车辆基本信息
				+ "  cc as "
				+ " (select t1.vehicle_vin,t1.route_id,t1.trip_id,t1.terminal_tripid,max(t1.st_num) as load_number "
				+ "    from CLW_XC_INOUTSITE_T t1, CLW_XC_ROUTESITE_T t2"
				+ "   where t1.vehicle_vin='"+ strVin+"'"
				+ "    and  t1.route_id=t2.route_id "
		        + "    and  t1.site_id=t2.site_id "
				+ "    and  t1.inout_flag=1 " //进出站状态: 0-到站, 1-出站	
				+ "    and  t1.terminal_time >=to_date('"+startDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss') "
			    + "    and  t1.terminal_time <=to_date('"+startDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss') "
			    + "    and  t2.rs_order=(select max(cxr.rs_order)-1 from CLW_XC_ROUTESITE_T cxr where cxr.route_id=t1.route_id) "			    
			    + " group by t1.vehicle_vin, t1.route_id,t1.trip_id, t1.terminal_tripid )"
			    
			    + "  select aa.vehicle_vin,dd.vehicle_ln,dd.enterprise_id,dd.organization_id,aa.route_id,ee.route_class,aa.trip_id,aa.terminal_tripid,ff.driver_name,"
			    + "         NVL(aa.site_id,0) as start_site,to_char(aa.terminal_time,'yyyy-mm-dd hh24:mi:ss') as start_time, aa.rs_order as start_order,"
			    + "         NVL(bb.site_id,0) as end_site,to_char(bb.terminal_time,'yyyy-mm-dd hh24:mi:ss') as end_time,bb.rs_order as end_order,NVL(cc.load_number, 0) as load_number "
			    + "   from  aa,bb,cc,CLW_CL_BASE_INFO_T dd,CLW_XC_ROUTE_T ee,clw_yw_driver_t ff, clw_xc_vehdriver_t gg"
			   
			  //+ "  where  aa.vehicle_vin=bb.vehicle_vin(+) "    //暂时将右关联去掉，即如果线路的终点站没有进站，都视为不完成的线路，不做统计
			    + "  where  aa.vehicle_vin=bb.vehicle_vin "
			    + "    and  aa.vehicle_vin = dd.vehicle_vin  "
			    + "	   and  ff.driver_id = gg.driver_id "
			    + "    and	aa.vehicle_vin=gg.vehicle_vin(+) "
			    + "    and  aa.route_id=bb.route_id(+)  "
			    + "    and  aa.trip_id =bb.trip_id(+)   "
			    + "    and  aa.terminal_tripid=bb.terminal_tripid(+) "
			    + "    and  aa.vehicle_vin=cc.vehicle_vin(+) "
			    + "    and  aa.route_id=cc.route_id(+) "
			    + "    and  aa.trip_id=cc.trip_id(+) "
			    + "    and  aa.terminal_tripid=cc.terminal_tripid(+) "
		        + "    and  aa.route_id=ee.route_id "
		        + "order by  aa.terminal_tripid,aa.terminal_time "; 
		
		logger.info("----查询单个车辆每天每个线路、行程的时间集合Sql-->>:"+siteTimeSql);		
		return jdbcTemplate.queryForList(siteTimeSql,new Object[]{});
	}
	
	
	
	/**
	 * 功能:向表TQC_SEND_PASSENGER插入油耗信息。 
	 * 
	 * @param  tqcStatistic
	 * @return int 
	 */
	public int addTqcStatisticData(TqcStatistic tqcStatistic) {	
		StringBuffer sql = new StringBuffer();		
		sql.append("insert into TQC_SEND_PASSENGER("
						+ "SP_ID,VIN_CODE,VEHICLE_NO,ROUTE_ID,ROUTE_CLASS,TRIP_ID,LOAD_NUMBER,START_TIME,END_TIME,"
						+ "IDLE_OIL,TOTAL_OIL,EMPTY_MILEAGE,LOAD_MILEAGE,TOTAL_MILEAGE,ENTERPRISE_ID,ORGANIZATION_ID,CREATE_TIME,TERMINAL_TRIPID,DRIVER_NAME) values(");
		
		sql.append("'"+UUIDGenerator.getUUID32()+"'");// 序列号
		sql.append(",'" + StringUtil.nullToStr(tqcStatistic.getVinCode())+ "'");// 车辆VIN
		sql.append(",'" + StringUtil.nullToStr(tqcStatistic.getVehicleNo())+ "'");//车牌号
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getRouteId()) + "'");//线路ID
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getRouteClass()) + "'");//线路属性
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getTripId())+ "'"); //行程ID
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getLoadNumber())+ "'"); //载重人数	
		sql.append(",TO_DATE('" +StringUtil.nullToStr(tqcStatistic.getStartTime())+ "','YYYY-MM-DD HH24:MI:SS')");//起站时间
		sql.append(",TO_DATE('" +StringUtil.nullToStr(tqcStatistic.getEndTime())+ "','YYYY-MM-DD HH24:MI:SS')"); //到站时间
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getIdleOil())+ "'");     //怠速油耗(L)
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getTotalOil())+ "'");  //总油耗(L)
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getEmptyMileage())+ "'");   //空驶里程(KM)
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getLoadMileage())+ "'");    //载重里程(KM)
		sql.append(",'" + StringUtil.nullToZero(tqcStatistic.getTotalMileage())+ "'");     //总里程(KM)
		sql.append(",'" + StringUtil.nullToStr(tqcStatistic.getEnterpriseId())+ "'");    //企业ID
		sql.append(",'" + StringUtil.nullToStr(tqcStatistic.getOrganizationId())+ "'");   //组织ID 
		sql.append(",SYSDATE");   //统计日期
		sql.append(",'" + StringUtil.nullToStr(tqcStatistic.getTerminalTripId())+ "'");   //终端序列号
		sql.append(",'" + StringUtil.nullToStr(tqcStatistic.getDriverName())+ "'");//车辆驾驶员
		sql.append(")");
		logger.info("[TQC_SEND_PASSENGER]表 插入 sql:" + sql.toString());
		
		return jdbcTemplate.update(sql.toString());
	}
	/**
	 * （通勤车迟到告警判断使用）获取当前线路终点站ID
	 * 
	 * @param route_id
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectMaxSiteByRoute(int route_id,int flag){
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select r.route_id as route_id,r.route_enterprise_id as enterprise_id,rs.site_id as site_id,ad.later_config as late_time ");
		sqlBuffer.append("  from clw_xc_routesite_t rs,clw_xc_route_t r, zspt_later_param ad,");
		sqlBuffer.append("     (select max(rs_order) as maxorder,route_id as route_id ");
		sqlBuffer.append("      from CLW_XC_ROUTESITE_T t ");
		sqlBuffer.append("      where t.route_id = '" + route_id + "'");
		sqlBuffer.append("      group by route_id) laster ");
		sqlBuffer.append("where rs.route_id = r.route_id and ");
		sqlBuffer.append(" rs.rs_order = laster.maxorder and ");
		sqlBuffer.append("      rs.route_id = laster.route_id and ");
		//sqlBuffer.append("      r.route_organization_id = ad.organization_id(+) and ");     //add  by Liuja   添加判断一厂还是二厂等条件  flag=1 一厂，flag=2二厂

		sqlBuffer.append("		 r.route_organization_id in    ");
		sqlBuffer.append("			(select enterprise_id         ");
		sqlBuffer.append("			   from CLW_JC_ENTERPRISE_T   ");
		sqlBuffer.append("			  where left_num >=           ");
		sqlBuffer.append("				    (select left_num      ");
		sqlBuffer.append("	                   from CLW_JC_ENTERPRISE_T   ");
		sqlBuffer.append("					  where enterprise_id = ad.organization_id   ");
		sqlBuffer.append(" 					    and ad.organization_type='"+flag+"')  ");
		sqlBuffer.append(" 			  and right_num <=  ");
		sqlBuffer.append(" 					(select right_num  ");
		sqlBuffer.append("                     from CLW_JC_ENTERPRISE_T  ");
		sqlBuffer.append("                    where enterprise_id = ad.organization_id   ");
		sqlBuffer.append("                      and  ad.organization_type='"+flag+"'))  ");
		
		sqlBuffer.append("      and rs.route_id = laster.route_id ");
		
		return jdbcTemplate.queryForList(sqlBuffer.toString());
	
	}
	/**
	 * （通勤车迟到告警判断使用）获取当前线路终点站ID
	 * 
	 * @param route_id
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectMaxSiteByRoute(int route_id){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select r.route_id as route_id,r.route_enterprise_id as enterprise_id,rs.site_id as site_id,ad.later_config as late_time ");
		sqlBuffer.append("from clw_xc_routesite_t rs,clw_xc_route_t r, zspt_later_param ad,");
		sqlBuffer.append("     (select max(rs_order) as maxorder,route_id as route_id ");
		sqlBuffer.append("      from CLW_XC_ROUTESITE_T t ");
		sqlBuffer.append("      where t.route_id = '" + route_id + "'");
		sqlBuffer.append("      group by route_id) laster ");
		sqlBuffer.append("where rs.route_id = r.route_id and ");
		sqlBuffer.append("      rs.rs_order = laster.maxorder and ");
		sqlBuffer.append("      r.route_organization_id = ad.organization_id(+) and ");
		sqlBuffer.append("      rs.route_id = laster.route_id ");		
		return jdbcTemplate.queryForList(sqlBuffer.toString());		
	}
	/**
	 * （通勤车延迟发车判断使用）获取当前线路的起始站点ID
	 * @param route_id
	 * @return site_info
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectMinSiteByRoute(int route_id, int trip_id){
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select r.route_id as route_id,rs.site_id as site_id ,ex.send_time ");
		sqlBuffer.append("from clw_xc_routesite_t rs,clw_xc_route_t r, tqc_trip_execute ex, ");
		sqlBuffer.append("     (select min(rs_order) as minorder,route_id as route_id ");
		sqlBuffer.append("      from CLW_XC_ROUTESITE_T t ");
		sqlBuffer.append("      where t.route_id = '" + route_id + "'");
		sqlBuffer.append("      group by route_id) laster ");
		sqlBuffer.append("where rs.route_id = r.route_id  ");
		sqlBuffer.append("  and rs.rs_order = laster.minorder  ");
		sqlBuffer.append("  and rs.route_id = laster.route_id  ");
		sqlBuffer.append("  and rs.route_id = ex.route_id(+)  ");
		sqlBuffer.append("	and	ex.send_condition = '2'  ");
		sqlBuffer.append("  and ex.trip_id = '" + trip_id + "' ");
		sqlBuffer.append("  and ex.exe_date = TO_DATE('" + CDate.getCurrentDate() + "','YYYY-MM-DD') "); 
		
		logger.info("获取当前线路的起始站点ID sql::"+sqlBuffer.toString());
		return jdbcTemplate.queryForList(sqlBuffer.toString());
	}
	
	/**
	 * （晚班设置使用）判断是是否是站点的起点站
	 * @param route_id
	 * @return site_info
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectMinSiteByRouteNoTrip(int route_id){
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select r.route_id as route_id,rs.site_id as site_id  ");
		sqlBuffer.append("from clw_xc_routesite_t rs,clw_xc_route_t r, ");
		sqlBuffer.append("     (select min(rs_order) as minorder,route_id as route_id ");
		sqlBuffer.append("      from CLW_XC_ROUTESITE_T t ");
		sqlBuffer.append("      where t.route_id = '" + route_id + "'");
		sqlBuffer.append("      group by route_id) laster ");
		sqlBuffer.append("where rs.route_id = r.route_id  ");
		sqlBuffer.append("  and rs.rs_order = laster.minorder  ");
		sqlBuffer.append("  and rs.route_id = laster.route_id  ");

		
		logger.info("获取当前线路的起始站点ID sql::"+sqlBuffer.toString());
		return jdbcTemplate.queryForList(sqlBuffer.toString());
	}
	/**
	 * （下发线路更改成功发短信给司机）获取当前线路的司机信息
	 * @param site_id
	 * @return driver_info
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectDriverByTrip(String trip_id){
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select tte.trip_id,tte.route_id,cxr.route_name,tte.send_condition,tte.send_order,tte.vehicle_vin, cci.vehicle_code,cci.vehicle_ln,cyd.driver_name,cyd.driver_tel ");
		sqlBuffer.append("from TQC_TRIP_EXECUTE tte,clw_xc_vehdriver_t cxv, clw_yw_driver_t cyd,clw_cl_base_info_t cci,CLW_XC_ROUTE_T cxr ");
		sqlBuffer.append("where tte.vehicle_vin = cxv.vehicle_vin ");
		sqlBuffer.append(" and  tte.add_flag = 1 ");
		sqlBuffer.append(" and  tte.vehicle_vin =cci.vehicle_vin ");
		sqlBuffer.append(" and  tte.driver_id = cyd.driver_id ");
		sqlBuffer.append(" and  tte.route_id = cxr.route_id ");		
		sqlBuffer.append(" and  tte.trip_id = '" + trip_id + "' ");
		sqlBuffer.append(" and  tte.exe_date = TO_DATE('" + CDate.getCurrentDate() + "','YYYY-MM-DD') and rownum=1 "); 
		
		logger.info("通勤车系统获取当前线路的司机信息 sql:" + sqlBuffer.toString());
		return jdbcTemplate.queryForList(sqlBuffer.toString());
	}
	
	
	/**
	 * 根据VIN号查询所属司机信息
	 * @param vinCode
	 * @return driver_info
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectDriverByVin_Code(String vinCode){		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select cci.vehicle_ln,cci.vehicle_code,cci.vehicle_vin,cyd.driver_name,cyd.driver_tel,cci.ENTERPRISE_ID,cci.ORGANIZATION_ID ");
		sqlBuffer.append("  from clw_cl_base_info_t cci,clw_xc_vehdriver_t cxv, clw_yw_driver_t cyd ");
		sqlBuffer.append(" where cci.vehicle_vin= '"+vinCode+"'");		
		sqlBuffer.append("   and cci.vehicle_vin=cxv.vehicle_vin ");
	  //sqlBuffer.append("   and cxv.driver_id=cyd.driver_id and rownum=1 ");	
		sqlBuffer.append("   and cxv.driver_id=cyd.driver_id ");//2013-10-28  一辆车对多个司机
		logger.info(" 根据VIN号查询所属司机信息 sql:" + sqlBuffer.toString());
		return jdbcTemplate.queryForList(sqlBuffer.toString());
	}
	
	
	/**
	 * 根据VIN号查询车辆对应组织下设置的防偷漏油发送短信管理员信息
	 * @param vinCode
	 * @return driver_info
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> selectStealOilSmsByVin_Code(String enterprise_id,String organization_id){		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT DISTINCT T.TELEPHONE,T.STU_NAME USER_NAME FROM CLW_YW_STEALOILSMS_T T ");
		sqlBuffer.append(" WHERE ENTERPRISE_ID ='"+enterprise_id+"'  ");
		sqlBuffer.append(" AND ORGANIZATION_ID IN ");		
		sqlBuffer.append(" (SELECT ENTERPRISE_ID  FROM CLW_JC_ENTERPRISE_VI ");
		sqlBuffer.append(" WHERE LEFT_NUM <= (SELECT LEFT_NUM FROM CLW_JC_ENTERPRISE_VI WHERE ENTERPRISE_ID ='"+organization_id+"')");
		sqlBuffer.append(" AND RIGHT_NUM >= (SELECT RIGHT_NUM FROM CLW_JC_ENTERPRISE_VI WHERE ENTERPRISE_ID ='"+organization_id+"'))");
		logger.info(" 根据VIN号查询车辆对应组织下设置的防偷漏油发送短信管理员信息sql:" + sqlBuffer.toString());
		return jdbcTemplate.queryForList(sqlBuffer.toString());
	}
	
	//
	public static void main(String[] args){
	  
		TqcStatisticSQL tqcStatisticSQL=TqcStatisticSQL.getInstance();
		tqcStatisticSQL.selectMinSiteByRoute(123,111);
	}
}
