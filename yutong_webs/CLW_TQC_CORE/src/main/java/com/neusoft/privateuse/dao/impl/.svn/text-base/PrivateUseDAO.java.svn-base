package com.neusoft.privateuse.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;


import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.cachemanager.TerminalCacheManager;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.parents.bean.Vehicle;
import com.neusoft.privateuse.bean.LawfulRunTimeInfo;
import com.neusoft.privateuse.bean.RunRecord;
import com.neusoft.privateuse.bean.VehicleInfo;
import com.neusoft.privateuse.dao.IPrivateUseDAO;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class PrivateUseDAO extends AbstractDaoManager implements IPrivateUseDAO {
	
	private static Logger log = LoggerFactory.getLogger(PrivateUseDAO.class);
	
	public List<VehicleInfo> getVehicleInfo(int weekDay) {
		//String sql = " select t.vehicle_vin,t.organization_id,nvl(t.driver_id, d.driver_id) driver_id from clw_cl_base_info_t t,clw_xc_vehdriver_t d where t.vehicle_vin = d.vehicle_vin(+) and t.valid_flag = 0 and t.organization_id is not null ";
		String sql = " select t.vehicle_vin,t.organization_id,nvl(ter.driver_id, d.driver_id) driver_id, " +
				" case substr(l.effect_often ,?,1) when '0' then '23:59' else l.start_time  end start_time, " +
				" case substr(l.effect_often ,?,1) when '0' then '23:59' else l.end_time  end end_time " +
				" from clw_cl_base_info_t t, clw_xc_vehdriver_t d,TQC_LAWFUL_RUN_TIME_T l,clw_jc_terminal_t ter   " +
				" where t.vehicle_vin = d.vehicle_vin(+) " +
				" and t.vehicle_vin = ter.vehicle_vin(+) " +
				//" and t.organization_id = l.organization_id " +
				" and t.organization_id in (SELECT ENTERPRISE_ID " +
				" FROM CLW_JC_ENTERPRISE_VI " +
				" WHERE LEFT_NUM >= (SELECT LEFT_NUM FROM CLW_JC_ENTERPRISE_VI " +
				" WHERE ENTERPRISE_ID = l.organization_id) " +
				" AND RIGHT_NUM <=(SELECT RIGHT_NUM " +
				" FROM CLW_JC_ENTERPRISE_VI " +
				" WHERE ENTERPRISE_ID = l.organization_id))  "+
				" and t.valid_flag = 0 " +
				" and l.status = 1 " +
				" order by t.vehicle_vin asc " ;
		return  jdbcTemplate.query(sql, new Object[] {weekDay,weekDay }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleInfo.class));
	}
	
	public List<VehicleInfo> getVehiclePrivateUseAlarm() {
		String sql = " select t.alarm_id,t.vehicle_vin,to_char(t.alarm_time,'yyyy-MM-dd hh24:mi:ss') alarm_time ,to_char(t.alarm_end_time,'yyyy-MM-dd hh24:mi:ss') alarm_end_time,t.mileage,t.oil " +
				" from CLW_YW_ALERM_RECORD_T t " +
				" where t.alarm_type_id = '199' " +
				" and t.alarm_time > trunc(sysdate,'dd') " +
				" order by t.alarm_time desc ";
		return jdbcTemplate.query(sql, new Object[] { }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleInfo.class));
	}
	
	public List<VehicleInfo> getVehicleInfoByVin(String vin,String start,String end) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		java.util.Date date=new java.util.Date();  
		String str=sdf.format(date);  

		
		String sql = " select t.mileage from clw_yw_terminal_record_t t where t.vehicle_vin= '" + vin +"' and t.terminal_time >= to_date( '" + start + "','yyyy-mm-dd hh24:mi:ss') and t.terminal_time <= to_date('" + end + "','yyyy-mm-dd hh24:mi:ss')  and t.mileage is not null  order by t.terminal_time desc";
		
		log.info("<PrivateUseDAO>获取公车私用告警里程:" + sql.toString());
		return  jdbcTemplate.query(sql, new Object[] {  }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleInfo.class));
	} 
	
public List<VehicleInfo> getVehicleOilByVin(String vin,String start,String end) {
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
		java.util.Date date=new java.util.Date();  
		String str=sdf.format(date);  

		
		String sql = " select t.oilbox_level oil from zspt_ftly_info t where t.vin_code = '" + vin +"' and t.report_time >= to_date( '" + start + "','yyyy-mm-dd hh24:mi:ss') and t.report_time <= to_date('" + end + "','yyyy-mm-dd hh24:mi:ss')  and t.oilbox_level is not null order by t.report_time asc ";
		log.info("<PrivateUseDAO>获取公车私用告警油耗:" + sql.toString());
		return  jdbcTemplate.query(sql, new Object[] {  }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleInfo.class));
	} 
	
	
	


	public List<LawfulRunTimeInfo> getVehicleInfoByOrganization_id(String organization_id) {
		String sql = " select t.start_time,t.end_time from TQC_LAWFUL_RUN_TIME_T t where t.organization_id = ? and rownum = 1 order by t.start_time asc ";
		return  jdbcTemplate.query(sql, new Object[] { organization_id}, ParameterizedBeanPropertyRowMapper.newInstance(LawfulRunTimeInfo.class));
	}
	
	
	public List<RunRecord> getRunRecordInfo(String vin) {
		String sql = " select t.vehicle_vin, t.on_date, t.off_date " +
				" from CLW_XC_RUN_RECORD_T t " +
				" where t.vehicle_vin = ? " +
				" and t.on_date > trunc(sysdate,'dd') " +
				" and t.private_use_flag = 0 " +
//				" and not exists " +
//				" (select 1 " +
//				" from clw_yw_alerm_record_t r " +
//				" where r.vehicle_vin = ? " +
//				" and r.alarm_type_id = '199' " +
//				" and (r.alarm_time = t.on_date or r.alarm_end_time = t.off_date)) " +
				" order by t.on_date asc ";
		
		return  jdbcTemplate.query(sql, new Object[] { vin }, ParameterizedBeanPropertyRowMapper.newInstance(RunRecord.class));
	}
	
	public int updatePrivateUseFlag() {
		// TODO Auto-generated method stub
		String sql = " update CLW_XC_RUN_RECORD_T t set t.private_use_flag = 1 where t.private_use_flag = 0 " ;
		log.info("<PrivateUseDAO>更新公车私用标识:" + sql.toString());
		return this.jdbcTemplate.update(sql,new Object[] { });
	}

	public int updatePrivateUseAlarm(VehicleInfo vi) {
		// TODO Auto-generated method stub
		String sql = " update CLW_YW_ALERM_RECORD_T t set t.mileage = ?,t.oil = ? where t.alarm_id= ? " ;
		log.info("<PrivateUseDAO>更新公车私用告警里程和油耗:" + sql.toString());
		return this.jdbcTemplate.update(sql,new Object[] {vi.getMileage(),vi.getOil(), vi.getAlarm_id() });
	}

}
