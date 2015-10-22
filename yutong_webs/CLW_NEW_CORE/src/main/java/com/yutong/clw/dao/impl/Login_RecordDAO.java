package com.yutong.clw.dao.impl;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.dao.ILogin_RecordDAO;

public class Login_RecordDAO extends AbstractDaoManager implements ILogin_RecordDAO{

	public String getNewWrite_Time(String vin) {
		String SELECT_WRITE_TIME_SQL = "SELECT to_char(max(TERMINAL_TIME),'yymmddhh24miss') as new_time FROM CLW_YW_LOGIN_RECORD_T WHERE VEHICLE_VIN=?"; 
		return (String) jdbcTemplate.queryForObject(SELECT_WRITE_TIME_SQL,new String[]{vin}, String.class);
	}

}
