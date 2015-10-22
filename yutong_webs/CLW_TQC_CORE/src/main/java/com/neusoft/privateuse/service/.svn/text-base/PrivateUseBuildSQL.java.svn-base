package com.neusoft.privateuse.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.cachemanager.TerminalCacheManager;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.clw.vncs.service.BuildSQL;
import com.neusoft.privateuse.bean.RunRecord;
import com.neusoft.privateuse.bean.VehicleInfo;

public class PrivateUseBuildSQL {
	
	private static Logger log = LoggerFactory.getLogger(PrivateUseBuildSQL.class);
	
	public static final PrivateUseBuildSQL privateUseBuildSQL = new PrivateUseBuildSQL();
	
	public static PrivateUseBuildSQL getInstance() {
		return privateUseBuildSQL;
	}
	
	
	public String buildInsertSiteNoteLog(Date start,Date end,VehicleInfo vh){
		StringBuffer sql = new StringBuffer();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss"); 
		TerminalBean tb = TerminalCacheManager.getInstance().getValue(
				vh.getVehicle_vin());

        sql.append("insert into CLW_YW_ALERM_RECORD_T(ALARM_ID,TERMINAL_ID,VEHICLE_VIN,driver_id,SIM_CARD_NUMBER,effect_time,"
				+ "ALARM_TYPE_ID,SPEEDING,SPEED_UNIT,deal_flag,    ALARM_TIME,ALARM_FLAG,   ALARM_END_TIME," +
						"LONGITUDE,LATITUDE,DIRECTION)");
		//sql.append(" values(");
		sql.append(" values( ");
		sql.append("'" + IdCreater.getUUid() + "'");
		sql.append(",'" + tb.getTerminal_id() + "'");
		sql.append(",'" + vh.getVehicle_vin()+ "'");
		sql.append(",'" + vh.getDriver_id()+ "'");
		sql.append(",'" + tb.getSim_card_number() + "'");
		sql.append(",'" + vh.getEffect_time() + "'");
		sql.append(",'199'");   //199设置成公车私用
		
		
		
		sql.append(",''");   //速度为空
		
		sql.append(",'km/h'");
		sql.append(",'0'");
		sql.append(",to_date(" + sdf.format(start) + ",'yymmddhh24miss')");
		
		sql.append(",'1'");
		
		sql.append(",to_date(" + sdf.format(end) + ",'yymmddhh24miss')");
		
		
		sql.append(",''"); //经度设置为空
		
		
		sql.append(",''"); //纬度设置为空
		
		
		sql.append(",'')"); //方向设置为空

        log.info("<PrivateUseBuildSQL>插入公车私用告警:" + sql.toString());
        
        return sql.toString();
	}
			
}
