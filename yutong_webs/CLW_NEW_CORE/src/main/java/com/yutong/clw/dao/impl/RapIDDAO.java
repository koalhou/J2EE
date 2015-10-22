package com.yutong.clw.dao.impl;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.dao.IRapIDDAO;

public class RapIDDAO extends AbstractDaoManager implements IRapIDDAO {

	public String getRapID_NewTime(String vin) {
		String SELECT_RAPID_NEWTIME_SQL ="SELECT to_char(max(TEMINAL_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_RAPID_RECORD_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_RAPID_NEWTIME_SQL, new String[]{vin},String.class);
	}

}
