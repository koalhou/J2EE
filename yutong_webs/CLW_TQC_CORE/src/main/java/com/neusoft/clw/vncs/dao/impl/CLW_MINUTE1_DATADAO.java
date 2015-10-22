package com.neusoft.clw.vncs.dao.impl;

import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.dao.ICLW_MINUTE1_DATADAO;

public class CLW_MINUTE1_DATADAO extends AbstractDaoManager implements ICLW_MINUTE1_DATADAO{

	public String getClw_Minute1_NewTime(String vin) {
		String SELECT_CLW_MINUTE1_SQL ="SELECT to_char(max(TEMINAL_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_MINUTE1_DATA_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_MINUTE1_SQL, new String[]{vin}, String.class);
	}
	
}
