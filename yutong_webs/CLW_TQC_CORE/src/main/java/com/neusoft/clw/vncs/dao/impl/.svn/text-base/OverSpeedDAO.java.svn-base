package com.neusoft.clw.vncs.dao.impl;

import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.dao.IOverSpeedDAO;

public class OverSpeedDAO extends AbstractDaoManager implements IOverSpeedDAO {
	
	public String getOverSpeed_NewTime(String vin) {
		String SELECT_OVERSPEED_SQL = "SELECT to_char(max(ALARM_END_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_MALARMD_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_OVERSPEED_SQL, new String[]{vin},String.class);
	}

}
