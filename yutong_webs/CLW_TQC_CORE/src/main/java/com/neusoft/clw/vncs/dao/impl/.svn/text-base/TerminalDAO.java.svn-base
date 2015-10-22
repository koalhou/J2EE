package com.neusoft.clw.vncs.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.dao.ITerminalDAO;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class TerminalDAO extends AbstractDaoManager implements ITerminalDAO {
	
	@SuppressWarnings("unchecked")
	public List<TerminalBean> getBaseTerminalInfo() {
		String SELECT_TERMINAL_SQL = "SELECT TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,VALID_FLAG,to_char(UPDATE_TIME,'yymmddhh24missSSS') UPDATE_TIME,SPEEDING,FIRE_UP_STATE" +
		" FROM CLW_JC_TERMINAL_T where to_char(UPDATE_TIME,'yymmddhh24miss')<=(select to_char(sysdate,'yymmddhh24miss') from dual)" +
		" and to_char(UPDATE_TIME,'yymmddhh24miss') >= ? and VEHICLE_VIN IS NOT NULL and SIM_CARD_NUMBER IS NOT NULL";
		return jdbcTemplate.query(SELECT_TERMINAL_SQL,new Object[] {Constant.upd_terminal_cache_time},ParameterizedBeanPropertyRowMapper.newInstance(TerminalBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<TerminalBean> getAllBaseTerminalInfo() {
		String SELECT_TERMINAL_ALL_SQL = "SELECT TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,VALID_FLAG,to_char(UPDATE_TIME,'yymmddhh24miss') UPDATE_TIME,SPEEDING,FIRE_UP_STATE" +
		" FROM CLW_JC_TERMINAL_T where VALID_FLAG = '0' and VEHICLE_VIN IS NOT NULL and SIM_CARD_NUMBER IS NOT NULL";
		return jdbcTemplate.query(SELECT_TERMINAL_ALL_SQL,ParameterizedBeanPropertyRowMapper.newInstance(TerminalBean.class));
	}
	
	public String getSysTime(){
		String SELECT_SYS_TIME = "SELECT TO_CHAR(SYSDATE,'yymmddhh24miss') FROM DUAL";
		return (String) jdbcTemplate.queryForObject(SELECT_SYS_TIME, String.class);
	}

	public TerminalBean getTerminalByid(String vin) {
		String SELECT_TERMINAL_BYID = "SELECT TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER FROM CLW_JC_TERMINAL_T WHERE VEHICLE_VIN = ? and VEHICLE_VIN IS NOT NULL";
		return (TerminalBean) jdbcTemplate.queryForObject(SELECT_TERMINAL_BYID, new Object[]{vin},ParameterizedBeanPropertyRowMapper.newInstance(TerminalBean.class));
	}
}
