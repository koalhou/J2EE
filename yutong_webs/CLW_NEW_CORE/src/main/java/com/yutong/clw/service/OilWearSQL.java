package com.yutong.clw.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.dao.analysis.AbstractDaoManager;
import com.yutong.clw.nio.msg.value.Up_InfoContent;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.CDate;
import com.yutong.clw.utils.StringUtil;

public class OilWearSQL extends AbstractDaoManager{
	private static Logger logger = LoggerFactory.getLogger(OilWearSQL.class);	
	// 创建服务对象
	private static final OilWearSQL oilWearSQL = new OilWearSQL();
	public static OilWearSQL getInstance() {
		return oilWearSQL;
	}
	
	/**
	 * 删除数据(ZSPT_OIL_ANALYS_INFO_BAK)数据
	 * 
	 * @param   dayDate
	 * @return  int
	 */
	public int delZsptOilAnalysInfoByDay(String dayDate){		
		String deleteSql = "delete from ZSPT_OIL_ANALYS_INFO zoai "
			      +"  where zoai.ANALYS_DATE >= to_date('"+dayDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss') "
			      +"    and zoai.ANALYS_DATE <= to_date('"+dayDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss') ";		
		
		logger.info("----查询车辆信息表(ZSPT_OIL_ANALYS_INFO_BAK)数据Sql-->>:"+deleteSql);		
		return jdbcTemplate.update(deleteSql,new Object[]{});
	}	
	
	/**
	 * 查询车辆信息表(CLW_CL_BASE_INFO_T)数据
	 * 
	 * @param 
	 * @return  list
	 */
	public List<Map<String,String>> getVehicleListInfo(){		
		String vehicleSql = "select t.VEHICLE_VIN,t.VEHICLE_CODE,t.VEHICLE_LN " +
				"from CLW_CL_BASE_INFO_T t " +
				"where t.VALID_FLAG='0' ";/* +
				"and t.VEHICLE_VIN ='LZYTBTD65B1014873' ";*/		
		logger.info("----查询车辆信息表(CLW_CL_BASE_INFO_T)数据Sql-->>:"+vehicleSql);		
		return jdbcTemplate.queryForList(vehicleSql,new Object[]{});
	}
	
	/**
	 * 查询油量记录(ZSPT_FTLY_INFO)表
	 * 
	 * @param   vinCode,sDate
	 * @return  list
	 */
	public List<Map<String,String>> getVehicleZsptFtlyInfo(String vinCode,String sDate){		
		String ftlySql = "SELECT t.OILBOX_STATE,t.OILBOX_LEVEL,t.ADD_OILL,t.OILBOX_MASS," 
				+"t.LATITUDE,t.LONGITUDE,TO_CHAR(t.REPORT_TIME,'YYYY-MM-DD HH24:MI:SS') AS REPORT_TIME,t.VIN_CODE " 
				+" FROM ZSPT_FTLY_INFO partition(FTLY_INFO_"+sDate+") t "
				/*+"where t.REPORT_TIME >= to_date('"+sDate+" 00:00:00','yyyy-mm-dd hh24:mi:ss') "
				+" and t.REPORT_TIME <= to_date('"+sDate+" 23:59:59','yyyy-mm-dd hh24:mi:ss') "*/	
				+" WHERE t.vin_code ='"+vinCode+"'"	
	         	+" ORDER BY t.REPORT_TIME ";
		logger.info("----查询车辆信息表(ZSPT_FTLY_INFO)数据Sql-->>:"+ftlySql);		
		return jdbcTemplate.queryForList(ftlySql,new Object[]{});
	}	
	
	/**
	 * 通勤车告警数据插入
	 * @param vinCode,dayOilSum,sDate
	 * @return String
	 */
	public int buildInsertOilAnalysInfoSql(String vinCode,String dayOilSum,String sDate){
		
		StringBuffer sql = new StringBuffer();

		sql.append(" INSERT INTO ZSPT_OIL_ANALYS_INFO");
		sql.append(" (oil_analys_id, vehicle_vin, vehicle_ln, vehicle_code,");
		sql.append(" analys_date,driver_id,driver_name, mileage,");
		sql.append(" count_mileage,start_oil,end_oil,added_oil,stolen_oil,used_oil,");
		sql.append(" bgl_oil,TOTAL_OIL_PRICE,create_time)");
		
		sql.append(" SELECT cybt.baddriving_id,");
		sql.append("        cybt.vehicle_vin,");
		sql.append("        ccbi.vehicle_ln,");
		sql.append("        ccbi.vehicle_code,");
		sql.append("        cybt.alarm_day,");
		sql.append("        cxvt.driver_id,");		
		sql.append("        (select cydt.driver_name ");
		sql.append("        from clw_yw_driver_t cydt ");
		sql.append("        where cydt.driver_id=cxvt.driver_id) as driver_name,");
		sql.append("        NVL(cybt.mileage,0) as mileage,");
		sql.append("        NVL(cybt.count_mileage,0) as count_mileage,");
		sql.append("        NVL(oil_info.start_oil,0) as start_oil,");
		sql.append("        NVL(oil_info.end_oil,0) as end_oil,");
		sql.append("        NVL(oil_info.oil_added,0) as oil_added,");
		sql.append("        NVL(oil_info.oil_stolen,0) as oil_stolen,");
		//sql.append("        NVL(oil_info.oil_used,0) as oil_used,");
		sql.append("        '"+dayOilSum+"' as oil_used,");
		//sql.append("        ROUND(NVL(oil_info.oil_used,0) * 100/DECODE(NVL(cybt.mileage, 0), 0, 1, NVL(cybt.mileage, 0)),2) as BGL_OIL,");
		sql.append("        ROUND(NVL('"+dayOilSum+"',0) * 100/DECODE(NVL(cybt.mileage, 0), 0, 1, NVL(cybt.mileage, 0)),2) as BGL_OIL,");
		//sql.append("        oil_used * ROUND(nvl(ccbi.oil_price,0),2) as TOTAL_OIL_PRICE,SYSDATE");
		sql.append("        "+dayOilSum+" * ROUND(nvl(ccbi.oil_price,0),2) as TOTAL_OIL_PRICE,SYSDATE");
		
		sql.append("   FROM clw_yw_baddriving_t cybt, ");
		sql.append("     ( SELECT baseinfo.vehicle_vin,baseinfo.vehicle_ln, ");
		sql.append("              baseinfo.vehicle_code,config.oil_price ");
		sql.append("         FROM clw_cl_base_info_t baseinfo, ");
		sql.append("              zspt_sit_addoil_config config, ");
		sql.append("              CLW_JC_TERMINAL_T cjtt ");
		sql.append("        WHERE baseinfo.enterprise_id = config.enterprise_id ");
		sql.append("          AND baseinfo.vehicle_vin = cjtt.vehicle_vin ");
		sql.append("          AND baseinfo.vehicle_vin = '"+vinCode+"' ");
		//只查询已接入流量管的    ---暂时注释，要考虑到天然气车辆
		//sql.append("          AND cjtt.ftly_flag = '0' ");
		sql.append("      ) ccbi,");
		sql.append("      clw_xc_vehdriver_t cxvt,");
		sql.append("      (SELECT (miner.oilbox_mass - maxer.oilbox_mass + nvl(adder.oil_added,0) - nvl(adder.oil_stolen,0) ) as oil_used,");
		sql.append("               miner.oilbox_mass as start_oil,");
		sql.append("               maxer.oilbox_mass as end_oil,");
		sql.append("               nvl(adder.oil_added,0) as oil_added,");
		sql.append("               nvl(adder.oil_stolen,0) as oil_stolen,");
		sql.append("               maxer.vin_code as vin_code ");
		sql.append("        FROM ");
		//查询一天中最后一刻钟油箱的油量
		sql.append("           (select distinct(t.report_time),t.oilbox_mass,t.vin_code");
		sql.append("              from ZSPT_FTLY_INFO t,");
		sql.append("                 (select max(tt1.report_time) as maxtime,tt1.vin_code");
		sql.append("                 from ZSPT_FTLY_INFO partition(FTLY_INFO_"+sDate+") tt1 ");
		//sql.append("                 where tt1.report_time > =  TO_DATE('"+sDate+"','YYYY-MM-DD')");
		//sql.append("                   and tt1.report_time <  TO_DATE('"+sDate+"','YYYY-MM-DD')+1");
		sql.append("                 where tt1.vin_code ='"+vinCode+"'");
		sql.append("              group by tt1.vin_code) maxtimer ");
		sql.append("                 where t.report_time = maxtimer.maxtime ");
		sql.append("                   and t.vin_code = maxtimer.vin_code ");
		sql.append("            ) maxer, ");
		//查询一天中第一刻种一刻钟油箱的油量
		sql.append("           (select distinct(t.report_time),t.oilbox_mass ,t.vin_code");
		sql.append("              from ZSPT_FTLY_INFO t,");
		sql.append("                 (select min(tt1.report_time) as maxtime,tt1.vin_code");
		sql.append("                    from ZSPT_FTLY_INFO partition(FTLY_INFO_"+sDate+") tt1 ");
		//sql.append("                   where tt1.report_time > =  TO_DATE('"+sDate+"','YYYY-MM-DD')");
		//sql.append("                     and tt1.report_time <  TO_DATE('"+sDate+"','YYYY-MM-DD')+1");
		sql.append("                   where tt1.vin_code ='"+vinCode+"'");
		sql.append("                group by tt1.vin_code) maxtimer ");
		sql.append("                   where t.report_time = maxtimer.maxtime ");
		sql.append("                     and t.vin_code = maxtimer.vin_code ");
		sql.append("            ) miner,");
		//计算加油和偷油
		sql.append("           ( select nvl(t1.oil_all,0) as oil_added,");
		sql.append("                    nvl(t2.oil_all,0) as oil_stolen,");
		sql.append("                    nvl(t1.vin_code,t2.vin_code) as vin_code");
		sql.append("               from (select sum(t.add_oill) as oil_all, t.vin_code");
		sql.append("                       from zspt_ftly_info partition(FTLY_INFO_"+sDate+") t ");
		//sql.append("                      where t.report_time > =  TO_DATE('"+sDate+"','YYYY-MM-DD')");
		//sql.append("                        and t.report_time <  TO_DATE('"+sDate+"','YYYY-MM-DD')+1");
		sql.append("                      where t.vin_code ='"+vinCode+"'");
		sql.append("                        and t.oilbox_state = '010' ");
		sql.append("                      group by t.vin_code ");
		sql.append("                     ) t1 full join ");
		sql.append("                    (select sum(t.add_oill) as oil_all,t.vin_code ");
		sql.append("                       from zspt_ftly_info partition(FTLY_INFO_"+sDate+") t ");
		//sql.append("                      where t.report_time > =  TO_DATE('"+sDate+"','YYYY-MM-DD')");
		//sql.append("                        and t.report_time <  TO_DATE('"+sDate+"','YYYY-MM-DD')+1");
		sql.append("                      where t.vin_code ='"+vinCode+"'");
		sql.append("                        and t.oilbox_state = '001'");
		sql.append("                     group by t.vin_code");
		sql.append("                     ) t2 ");
		sql.append("                     on  t1.vin_code= t2.vin_code");
		sql.append("           ) adder ");
		sql.append("     WHERE maxer.vin_code = miner.vin_code");
		sql.append("       AND maxer.vin_code = adder.vin_code(+) ) oil_info");
		
		sql.append(" where cybt.vehicle_vin = oil_info.vin_code(+)");
		sql.append("   and cybt.vehicle_vin = cxvt.vehicle_vin(+)");
		sql.append("   and cybt.vehicle_vin = ccbi.vehicle_vin");		
		sql.append("   and cybt.alarm_day = TO_DATE('"+sDate+"','YYYY-MM-DD')");
		sql.append("   and rownum = 1 ");    //add by liuja 因为数据库中驾驶员不良驾驶表中会存在两行数据（正常情况应该不会出现），加入该条件以增加容错
		
		logger.info("<OilWearSQL>插入表专属应用精准油耗分析:" + sql.toString());		
		return jdbcTemplate.update(sql.toString());
	}
	
	/**
	 * 从表ZSPT_FTLY_INFO 到 表ZSPT_FTLY_ALARM 的同步
	 * 
	 * @param   sDate
	 * @return  String
	 */
	public int synFromInfoToAlarm(String currDate) {
		//2013-12-09修改 查询6分半前记录插入alarm表
		//查询出要插入alarm表的数据记录
		StringBuffer querySql = new StringBuffer();
		querySql.append(" SELECT ");
		querySql.append("    m.FTLY_ID,");
		querySql.append("    m.OILBOX_STATE,");
		querySql.append("    m.OILBOX_LEVEL,");
		querySql.append("    m.ADD_OILL,");
		querySql.append("    m.OILBOX_MASS,");
		querySql.append("    m.LATITUDE,");
		querySql.append("    m.LONGITUDE,");
		querySql.append("    m.ELEVATION,");
		querySql.append("    m.DIRECTION,");
		querySql.append("    m.GPS_SPEEDING,");
		querySql.append("    m.SPEEDING,");
		querySql.append("    TO_CHAR(m.REPORT_TIME,'YYYY-MM-DD HH24:MI:SS') REPORT_TIME,");
		querySql.append("    m.VIN_CODE,");
		querySql.append("    m.PT_FLAG,");
		querySql.append("    m.ZONENAME");
		//querySql.append(" FROM ZSPT_FTLY_INFO partition(FTLY_INFO_"+sDate+") m");
		querySql.append(" FROM ZSPT_FTLY_INFO m");
		querySql.append(" WHERE  m.OILBOX_STATE in ('001','010')");
		//querySql.append("   AND  m.REPORT_TIME >= TO_DATE('"+CDate.getNextDateByDate(currDate,-1)+"','YYYY-MM-DD')");
		querySql.append("   AND  m.REPORT_TIME >= TO_DATE('"+currDate+"','YYYY-MM-DD')"); //由于是定时任务，一分钟执行一次，是当天及时判断
		querySql.append("   AND  m.REPORT_TIME < TO_DATE('"+currDate+"','YYYY-MM-DD')+1");
		querySql.append("   AND  NOT EXISTS ");
		querySql.append(" (SELECT 1 FROM ZSPT_FTLY_ALARM n  ");
	    querySql.append(" WHERE  n.FTLY_ID = m.FTLY_ID ");
	    //querySql.append("   AND  n.REPORT_TIME >= TO_DATE('"+CDate.getNextDateByDate(currDate,-1)+"','YYYY-MM-DD')");
	    querySql.append("   AND  n.REPORT_TIME >= TO_DATE('"+currDate+"','YYYY-MM-DD')");//由于是定时任务，一分钟执行一次，是当天及时判断
		querySql.append("   AND  n.REPORT_TIME < TO_DATE('"+currDate+"','YYYY-MM-DD')+1 )");	    		
		
		logger.info("<OilWearSQL>从表ZSPT_FTLY_INFO 中查询要更新到ALARM表的数据:" + querySql.toString());
		List<Map<String,Object>> result = jdbcTemplate.queryForList(querySql.toString());
		int flag = 0 ;
		//循环插入alarm表
		for(Map<String,Object> mapResult : result){
			//油量状态
			String oilBoxState=StringUtil.objToStr(mapResult.get("OILBOX_STATE"));
			//判断该偷油的前10分钟是否存在车速   李帅修改
			StringBuffer querySpeed = new StringBuffer();
			querySpeed.append("select count(1) from clw_yw_terminal_record_t  t where ");
			querySpeed.append("t.terminal_time <= to_date('"+CDate.dateToStrLong(CDate.getPre9Time(CDate.strToDateLong(mapResult.get("REPORT_TIME").toString())))+"','yyyy-mm-dd hh24:mi:ss') ");
			querySpeed.append("and t.terminal_time > to_date('"+CDate.dateToStrLong(CDate.getPre11Time(CDate.strToDateLong(mapResult.get("REPORT_TIME").toString())))+"','yyyy-mm-dd hh24:mi:ss') ");
			querySpeed.append("and t.vehicle_vin = '"+mapResult.get("VIN_CODE")+"' ");
			querySpeed.append("and t.speeding > 0");
			
			logger.debug(querySpeed.toString());
			int speed = jdbcTemplate.queryForInt(querySpeed.toString());
			//加油/偷油
			float oilAddOil = Float.parseFloat(StringUtil.objToStr(mapResult.get("ADD_OILL")));
			//如果是加油，则全部同步，  如果是偷油，需要判断油量是否大于0L，否则就也不同步
			if(oilBoxState.equals("010") || (oilBoxState.equals("001") && (oilAddOil > 0) && speed==0 )){
				
				StringBuffer selectSql = new StringBuffer();			
				//查询对应GPS文字坐标
				selectSql.append(" SELECT t.LATITUDE,t.LONGITUDE,t.ELEVATION,t.DIRECTION,t.GPS_SPEEDING,");
				selectSql.append(" t.SPEEDING,TO_CHAR(t.REPORT_TIME,'YYYY-MM-DD HH24:MI:SS') as REPORT_TIME,t.VIN_CODE,t.PT_FLAG,t.ZONENAME");
				selectSql.append(" FROM ZSPT_FTLY_INFO t");
				selectSql.append(" WHERE t.REPORT_TIME = ");
				selectSql.append(" (SELECT MIN(REPORT_TIME) FROM ZSPT_FTLY_INFO ");
				selectSql.append(" WHERE REPORT_TIME >= TO_DATE('" + StringUtil.objToStr(mapResult.get("REPORT_TIME")) + "','YYYY-MM-DD HH24:MI:SS')-(1*390)/(24*60*60)");
				selectSql.append(" AND REPORT_TIME <= TO_DATE('" + StringUtil.objToStr(mapResult.get("REPORT_TIME")) + "','YYYY-MM-DD HH24:MI:SS')");
				selectSql.append(" AND VIN_CODE = '"+StringUtil.objToStr(mapResult.get("VIN_CODE"))+"'");
				selectSql.append(" )");
				selectSql.append(" AND t.VIN_CODE = '"+StringUtil.objToStr(mapResult.get("VIN_CODE"))+"' AND ROWNUM=1 ");			
				logger.info("<OilWearSQL>查询满足条件的车辆油耗信息数据:" + selectSql.toString());
				
				List<Map<String, String>> listCorrect =jdbcTemplate.queryForList(selectSql.toString());
				Map<String,String> mapCorrect=listCorrect.get(0);			
				SearchGisAreaByCode sgabc=	new SearchGisAreaByCode();
				String xmlString = sgabc.getAddressInfoFrPoint(mapCorrect.get("LONGITUDE"), mapCorrect.get("LATITUDE"), 0, 3000, 10);
				String zoneName="";
				if(!xmlString.equals("")){
					zoneName=sgabc.getZoneNameByXmlString(xmlString);
				}else{
					zoneName="定位无效";
				}			
				logger.info("<OilWearSQL>获得位置信息:" + zoneName);			
				StringBuffer insertSql = new StringBuffer();
				insertSql.append(" INSERT INTO ZSPT_FTLY_ALARM");
				insertSql.append(" (FTLY_ID,OILBOX_STATE,OILBOX_LEVEL,ADD_OILL,OILBOX_MASS,LATITUDE,LONGITUDE,");
				insertSql.append("  ELEVATION,DIRECTION,GPS_SPEEDING,SPEEDING,REPORT_TIME,VIN_CODE,PT_FLAG,ZONENAME) values(");
				insertSql.append(" '"+StringUtil.objToStr(mapResult.get("FTLY_ID"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapResult.get("OILBOX_STATE"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapResult.get("OILBOX_LEVEL"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapResult.get("ADD_OILL"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapResult.get("OILBOX_MASS"))+"',");
				
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("LATITUDE"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("LONGITUDE"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("ELEVATION"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("DIRECTION"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("GPS_SPEEDING"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("SPEEDING"))+"',");
				insertSql.append(" TO_DATE('"+StringUtil.objToStr(mapResult.get("REPORT_TIME").toString())+"','YYYY-MM-DD HH24:MI:SS'),");	
				
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("VIN_CODE"))+"',");
				insertSql.append(" '"+StringUtil.objToStr(mapCorrect.get("PT_FLAG"))+"',");
				insertSql.append(" '"+zoneName+"'");
				
				insertSql.append(" )");	
				logger.info("<OilWearSQL>插入到 表ZSPT_FTLY_ALARM 的同步:" + insertSql.toString());		
				flag = jdbcTemplate.update(insertSql.toString());			
				
				//增加发送短信功能，目前只对偷油发送短信
				if(oilBoxState.equals("001")){
					try {				
						Up_InfoContent ufc = new Up_InfoContent();
						ufc.setTerminalId(StringUtil.objToStr(mapCorrect.get("VIN_CODE")));
						ufc.setTerminal_time(StringUtil.objToStr(mapCorrect.get("REPORT_TIME")));
						ufc.setZonename(zoneName);
						ufc.getZsptFtlyInfo().setAddOill(StringUtil.objToStr(mapResult.get("ADD_OILL")));
						ufc.getZsptFtlyInfo().setOilboxMass(StringUtil.objToStr(mapResult.get("OILBOX_MASS")));
						//调用短信网关接口
						callSmsInterface(ufc);
						logger.info("<OilWearSQL> 偷油发送短信开始!" );
						logger.info("VIN_CODE------->> "+ StringUtil.objToStr(mapCorrect.get("VIN_CODE")));
						logger.info("上报时间--------->> "+ StringUtil.objToStr(mapCorrect.get("REPORT_TIME")));
						logger.info("偷油地点--------->> "+ zoneName);
						logger.info("偷油量---------->> "+ StringUtil.objToStr(mapResult.get("ADD_OILL")));
						logger.info("当前油量--------->> "+ StringUtil.objToStr(mapResult.get("OILBOX_MASS")));
					} catch (Exception e) {
						e.printStackTrace();
						logger.info("<OilWearSQL> 偷油短信发送失败！"+e.getMessage());
					}
				}			
			}
		}	
		
		//为了保证数据没有重复记录出现，当有加油/偷油 记录出现时 (针对油量一样的情况下)，先删除记录
		if(result.size()>0){
			try {
			    StringBuffer deleteSql = new StringBuffer();
				deleteSql.append("delete from ZSPT_FTLY_ALARM t ");
				//deleteSql.append("  where t.report_time >= TO_DATE('"+CDate.getNextDateByDate(currDate,-1)+"','YYYY-MM-DD')");
				deleteSql.append("  where t.report_time >= TO_DATE('"+currDate+"','YYYY-MM-DD')");
				deleteSql.append("    and t.report_time <  TO_DATE('"+currDate+"','YYYY-MM-DD')+1 ");
				
				deleteSql.append("    and t.add_oill in (select m.add_oill from ZSPT_FTLY_ALARM m ");
				deleteSql.append("                       where m.report_time >= TO_DATE('"+currDate+"','YYYY-MM-DD')");
				deleteSql.append("                         and m.report_time <  TO_DATE('"+currDate+"','YYYY-MM-DD')+1 ");	
				deleteSql.append("                         group by m.add_oill having count(m.add_oill) > 1) ");
				
				deleteSql.append("    and t.report_time not in (select min(report_time) from ZSPT_FTLY_ALARM n ");
				deleteSql.append("                       where n.report_time >= TO_DATE('"+currDate+"','YYYY-MM-DD')");
			    deleteSql.append("                         and n.report_time < TO_DATE('"+currDate+"','YYYY-MM-DD')+1 ");	
			    deleteSql.append("                         group by n.add_oill having count(n.add_oill) > 1) ");
			    
			    logger.info("<OilWearSQL> 删除ZSPT_FTLY_ALARM表重复记录SQL语句-->:"+deleteSql.toString());
			    //执行操作
			    jdbcTemplate.update(deleteSql.toString());
			    
			    //新增，对流水表里面的数据也进行删除操作，并且只对偷油误报数据进行删除，[待验证方案]
			    deleteSql = new StringBuffer();
			    deleteSql.append("delete from ZSPT_FTLY_INFO t1 ");
			    deleteSql.append(" where (t1.oilbox_state ='001' or t1.oilbox_state ='010') "); //001偷油标志位    010加油标识位
			    deleteSql.append("   and trunc(t1.report_time,'DD') =to_date('"+currDate+"','YYYY-MM-DD')");
			    deleteSql.append("   and not exists ");
			    deleteSql.append("   (select 1 from ZSPT_FTLY_ALARM t2 ");
			    deleteSql.append("     where (t1.oilbox_state ='001' or t1.oilbox_state ='010')  ");//001偷油标志位    010加油标识位
			    deleteSql.append("       and t2.ftly_id=t1.ftly_id ");
			    deleteSql.append("   and trunc(t2.report_time,'DD') =to_date('"+currDate+"','YYYY-MM-DD') )");
			    //执行操作
			    
			    logger.info("<OilWearSQL> 删除ZSPT_FTLY_INFO 表重复记录SQL语句-->:"+deleteSql.toString());
			    jdbcTemplate.update(deleteSql.toString());
			    
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("<OilWearSQL> 删除ZSPT_FTLY_ALARM表重复记录执行失败！"+e.getMessage());
			}
		}	
		
		return flag;
	}	
	
	/**
	 * 发送手机短信调用亿美网关，发送紧急偷油告警短信 
	 * @param uhc
	 * @return void
	 */
	public static void callSmsInterface(Up_InfoContent uhc){			
		// 获得tqcStatisticSQL对象		
		TqcStatisticSQL tqcStatisticSQL = (TqcStatisticSQL) SpringBootStrap.getInstance().getBean("tqcStatisticSQL");
		//查询车辆对应的多个司机姓名、组织、电话等信息、循环发送短信
		List<Map<String, String>> listData = tqcStatisticSQL.selectDriverByVin_Code(uhc.getTerminalId());
		Map<String, String> map = listData.get(0);
		String vehicleLn=StringUtil.objToStr(map.get("VEHICLE_LN"));
		
		//如果不是泰安的车辆则不发送短信
		if(vehicleLn.indexOf("鲁")==-1){
			return;
		}
		
		String vehicleCode=StringUtil.objToStr(map.get("VEHICLE_CODE"));
		String entId=StringUtil.objToStr(map.get("ENTERPRISE_ID"));
		String orgId=StringUtil.objToStr(map.get("ORGANIZATION_ID"));
		
		String terminal_time =uhc.getTerminal_time();
		/*String terminal_time ="20"+uhc.getTerminal_time();
		terminal_time = terminal_time.substring(0,4)+"-"+terminal_time.substring(4,6)+"-"+terminal_time.substring(6,8)+" "+terminal_time.substring(8,10)+":"+
				terminal_time.substring(10,12)+":"+terminal_time.substring(12,14);
		terminal_time = CDate.getPreDateBySecond(terminal_time, -390);//计算6分半之前的时间*/

		List<String> driverTelList= new ArrayList<String>();
		int zoneIndex = uhc.getZonename().indexOf("（");
		for(Map<String,String> mapData : listData){
			String driverTel=StringUtil.objToStr(mapData.get("DRIVER_TEL"));	
			String driverName=StringUtil.objToStr(mapData.get("DRIVER_NAME"));
			StringBuffer mesgTemplate = new StringBuffer();
			mesgTemplate.append("【油量骤减】"+vehicleLn+"（"+vehicleCode+"号）");
			mesgTemplate.append("油量骤减"+uhc.getZsptFtlyInfo().getAddOill()+" 升,\r\n");
			mesgTemplate.append("当前油箱剩余"+uhc.getZsptFtlyInfo().getOilboxMass()+"升油,\r\n");
			mesgTemplate.append("骤减时间："+terminal_time+",\r\n");
			if(zoneIndex != -1)//有位置信息
				mesgTemplate.append("骤减位置："+uhc.getZonename().substring(0,zoneIndex)+"。\r\n");
			else if(uhc.getZonename().equals("定位无效"))
				mesgTemplate.append("骤减位置：未知。\r\n");
			else
				mesgTemplate.append("骤减位置："+uhc.getZonename()+"。\r\n");
		    //拼接对象		
			uhc.setTel(driverTel);// 电话
			//uhc.setTel("15136126762");// 电话	
			uhc.setRelative_name(driverName);  //司机姓名
			uhc.setParents_flag("0");//0：其他联系人 
			uhc.setOrganization_id(orgId);// 组织编码
			uhc.setEnterprise_id(entId);// 企业编码
			XCBuildSQL.getInstance().sendMsg2DriverByTqc(uhc, mesgTemplate.toString());	
			driverTelList.add(mapData.get("DRIVER_TEL").toString());
		}
		//查询车辆对应组织及上级组织设置的防偷漏油发送短信管理员信息
		List<Map<String, String>> stealOilSmsData = tqcStatisticSQL.selectStealOilSmsByVin_Code(entId, orgId);
		for(Map<String,String> mapData : stealOilSmsData){
			if(!driverTelList.contains(mapData.get("TELEPHONE").toString())){//如果电话号码已经发送过则不重复发送
				String driverTel=StringUtil.objToStr(mapData.get("TELEPHONE"));	
				String driverName=StringUtil.objToStr(mapData.get("USER_NAME"));
				StringBuffer mesgTemplate = new StringBuffer();
				mesgTemplate.append("【油量骤减】"+vehicleLn+"（"+vehicleCode+"号）");
				mesgTemplate.append("油量骤减"+uhc.getZsptFtlyInfo().getAddOill()+" 升,\r\n");
				mesgTemplate.append("当前油箱剩余"+uhc.getZsptFtlyInfo().getOilboxMass()+"升油,\r\n");
				mesgTemplate.append("骤减时间："+terminal_time+",\r\n");
				if(zoneIndex != -1)//有位置信息
					mesgTemplate.append("骤减位置："+uhc.getZonename().substring(0,zoneIndex)+"。\r\n");
				else if(uhc.getZonename().equals("定位无效"))
					mesgTemplate.append("骤减位置：未知。\r\n");
				else
					mesgTemplate.append("骤减位置："+uhc.getZonename()+"。\r\n");
			    //拼接对象		
				uhc.setTel(driverTel);// 电话
				uhc.setRelative_name(driverName);  //司机姓名
				uhc.setParents_flag("0");//0：其他联系人 
				uhc.setOrganization_id(orgId);// 组织编码
				uhc.setEnterprise_id(entId);// 企业编码
				XCBuildSQL.getInstance().sendMsg2DriverByTqc(uhc, mesgTemplate.toString());	
			}
		}
	}
	
	/**
	 * 拼接插入 TQC_PROC_LOG表语句
	 * 
	 * @param urt
	 * @return  String
	 */
	public int  executeInsertTqcProcLog(String flag) {
		StringBuffer sql = new StringBuffer();	
		sql.append("insert into TQC_PROC_LOG(PROC_NAME,EXE_DESC,EXE_DATE,EXE_FLAG,OPERATE_TIME) values(");		
		sql.append("'TQC_OIL_ANALYSIS_PROC','通勤车每天定时统计油耗！【核心层】' ");//信息序列号	
		sql.append(",SYSDATE,'"+flag+"',SYSDATE");	
		sql.append(")");
		logger.info("<OilWearSQL>[TQC_PROC_LOG]表 插入 sql:" + sql.toString());
		return jdbcTemplate.update(sql.toString());
	}	
	
	/**
	 * 将空值转换为空字符串
	 * 
	 * @return String
	 */
	public static String nullToStr(String str) {
		return str == null || str.equals("null") ? "" : str.trim();
	}
	
	public static void main(String[] args){
		//OilWearSQL os =new OilWearSQL();		
		//os.buildInsertOilAnalysInfoSql("LZYTAGD65B1033613", "21.5", "2013-10-02");		
	}
	
}
