package com.yutong.clw.dao.impl;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.dao.ICLW_MINUTE5_DATADAO;

public class CLW_MINUTE5_DATADAO extends AbstractDaoManager implements ICLW_MINUTE5_DATADAO {
	
	public String getClw_Minute5_NewTime(String vin) {
		String SELECT_CLW_MINUTE5_SQL ="SELECT to_char(max(TEMINAL_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_MINUTE5_DATA_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_MINUTE5_SQL, new String[]{vin}, String.class);
	}

}
