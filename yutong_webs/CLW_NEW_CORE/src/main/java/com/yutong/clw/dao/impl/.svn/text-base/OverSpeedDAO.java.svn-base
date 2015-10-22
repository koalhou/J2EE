package com.yutong.clw.dao.impl;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.dao.IOverSpeedDAO;

public class OverSpeedDAO extends AbstractDaoManager implements IOverSpeedDAO {
	
	public String getOverSpeed_NewTime(String vin) {
		String SELECT_OVERSPEED_SQL = "SELECT to_char(max(ALARM_END_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_MALARMD_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_OVERSPEED_SQL, new String[]{vin},String.class);
	}

}
