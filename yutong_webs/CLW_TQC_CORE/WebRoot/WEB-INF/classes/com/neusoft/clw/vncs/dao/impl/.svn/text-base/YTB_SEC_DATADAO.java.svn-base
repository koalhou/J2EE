package com.neusoft.clw.vncs.dao.impl;

import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.dao.IYTB_SEC_DATADAO;

public class YTB_SEC_DATADAO extends AbstractDaoManager implements IYTB_SEC_DATADAO{
	//查询宇通杯秒数据表中最近一条更新时间
	
	public String getUpdateTime(String vin) {
		String SELECT_UPDATE_TIME_SQL = "SELECT to_char(max(TEMINAL_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_MATCH_SEC_DATA_T WHERE VEHICLE_VIN =?"; 
		return (String) jdbcTemplate.queryForObject(SELECT_UPDATE_TIME_SQL,new Object[]{vin}, String.class);
	}
}
