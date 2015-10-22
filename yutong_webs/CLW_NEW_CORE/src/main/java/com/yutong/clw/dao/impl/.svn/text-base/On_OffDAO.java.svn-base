package com.yutong.clw.dao.impl;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.dao.IOn_OffDAO;

public class On_OffDAO extends AbstractDaoManager implements IOn_OffDAO{

	public String getOnOffNewTime(String vin) {
		String SELECT_ONOFF_SQL= "SELECT to_char(max(TERMINAL_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_ON_OF_RECORD_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_ONOFF_SQL, new String[]{vin}, String.class);
	}

}
