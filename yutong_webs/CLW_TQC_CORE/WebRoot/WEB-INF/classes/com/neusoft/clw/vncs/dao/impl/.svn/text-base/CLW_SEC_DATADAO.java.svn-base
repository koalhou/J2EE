package com.neusoft.clw.vncs.dao.impl;

import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.dao.ICLW_SEC_DATADAO;

public class CLW_SEC_DATADAO extends AbstractDaoManager implements ICLW_SEC_DATADAO{
	
	public String getClw_Sec_New_Time(String vin) {
		String SELECT_CLW_SEC_SQL ="SELECT to_char(SEC_DATA_TIME,'yymmddhh24miss') as new_time FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_SEC_SQL, new String[]{vin}, String.class);
	}
	
	public String getClw_Minute1_NewTime(String vin) {
		String SELECT_CLW_1MIN_SQL ="SELECT to_char(MIN1_DATA_TIME,'yymmddhh24miss') as new_time FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_1MIN_SQL, new String[]{vin}, String.class);
	}
	
	public String getClw_Minute5_NewTime(String vin) {
		String SELECT_CLW_5MIN_SQL ="SELECT to_char(MIN5_DATA_TIME,'yymmddhh24miss') as new_time FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_5MIN_SQL, new String[]{vin}, String.class);
	}
	
	public String getRapID_NewTime(String vin) {
		String SELECT_CLW_RAPID_SQL ="SELECT to_char(RAPID_DATA_TIME,'yymmddhh24miss') as new_time FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_RAPID_SQL, new String[]{vin}, String.class);
	}
	
	public String getOnOffNewTime(String vin) {
		String SELECT_CLW_ONOFF_SQL ="SELECT to_char(ONOFF_DATA_TIME,'yymmddhh24miss') as new_time FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_ONOFF_SQL, new String[]{vin}, String.class);
	}
	
	public String getLoginNewTime(String vin) {
		String SELECT_CLW_LOGIN_SQL ="SELECT to_char(LOGIN_DATA_TIME,'yymmddhh24miss') as new_time FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_LOGIN_SQL, new String[]{vin}, String.class);
	}
	
	public String getOverspeedNewTime(String vin) {
		String SELECT_CLW_OVERSPEED_SQL ="SELECT to_char(OVERSPEED_DATA_TIME,'yymmddhh24miss') as new_time FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return (String) jdbcTemplate.queryForObject(SELECT_CLW_OVERSPEED_SQL, new String[]{vin}, String.class);
	}
	
	public int getNum(String vin){
		String SELECT_HISTORY_TIME_SQL = "SELECT count(*) as num FROM CLW_YW_HISTROY_TIME_T WHERE VEHICLE_VIN = ?";
		return jdbcTemplate.queryForInt(SELECT_HISTORY_TIME_SQL, new String[]{vin});
	}
	
	public int insertHis_Time(String vin){
		String INSERT_HISTORY_TIME_SQL = "insert into CLW_YW_HISTROY_TIME_T (VEHICLE_VIN, SEC_DATA_TIME, MIN1_DATA_TIME, MIN5_DATA_TIME, LOGIN_DATA_TIME, OVERSPEED_DATA_TIME, ONOFF_DATA_TIME, RAPID_DATA_TIME) " +
		"values (?, sysdate, sysdate, sysdate, sysdate, sysdate, sysdate, sysdate)";
		return jdbcTemplate.update(INSERT_HISTORY_TIME_SQL, new String[]{vin});
	}
	
	public int updateHis_Time(String date,String vin){
		String UPDATE_HISTORY_TIME_SQL = "update CLW_YW_HISTROY_TIME_T set SEC_DATA_TIME = TO_TIMESTAMP(?,'yymmddhh24missff3') where VEHICLE_VIN = ?";
		return jdbcTemplate.update(UPDATE_HISTORY_TIME_SQL, new String[]{date,vin});
	}
	
	public int update1MinHis_Time(String date,String vin){
		String UPDATE_1MIN_HISTORY_TIME_SQL = "update CLW_YW_HISTROY_TIME_T set MIN1_DATA_TIME = TO_TIMESTAMP(?,'yymmddhh24missff3') where VEHICLE_VIN = ?";
		return jdbcTemplate.update(UPDATE_1MIN_HISTORY_TIME_SQL, new String[]{date,vin});
	}
	
	public int update5MinHis_Time(String date,String vin){
		String UPDATE_5MIN_HISTORY_TIME_SQL = "update CLW_YW_HISTROY_TIME_T set MIN5_DATA_TIME = TO_TIMESTAMP(?,'yymmddhh24missff3') where VEHICLE_VIN = ?";
		return jdbcTemplate.update(UPDATE_5MIN_HISTORY_TIME_SQL, new String[]{date,vin});
	}
	
	public int updateRapidHis_Time(String date,String vin){
		String UPDATE_Rapid_HISTORY_TIME_SQL = "update CLW_YW_HISTROY_TIME_T set RAPID_DATA_TIME = TO_TIMESTAMP(?,'yymmddhh24missff3') where VEHICLE_VIN = ?";
		return jdbcTemplate.update(UPDATE_Rapid_HISTORY_TIME_SQL, new String[]{date,vin});
	}
	
	public int updateOnOffHis_Time(String date,String vin){
		String UPDATE_ONFF_HISTORY_TIME_SQL = "update CLW_YW_HISTROY_TIME_T set ONOFF_DATA_TIME = TO_DATE(?,'yymmddhh24miss') where VEHICLE_VIN = ?";
		return jdbcTemplate.update(UPDATE_ONFF_HISTORY_TIME_SQL, new String[]{date,vin});
	}
	
	public int updateLoginHis_Time(String date,String vin){
		String UPDATE_LOGIN_HISTORY_TIME_SQL = "update CLW_YW_HISTROY_TIME_T set LOGIN_DATA_TIME = TO_DATE(?,'yymmddhh24miss') where VEHICLE_VIN = ?";
		return jdbcTemplate.update(UPDATE_LOGIN_HISTORY_TIME_SQL, new String[]{date,vin});
	}
	
	public int updateOverspeedHis_Time(String date,String vin){
		String UPDATE_OVERSPEED_HISTORY_TIME_SQL = "update CLW_YW_HISTROY_TIME_T set OVERSPEED_DATA_TIME = TO_DATE(?,'yymmddhh24miss') where VEHICLE_VIN = ?";
		return jdbcTemplate.update(UPDATE_OVERSPEED_HISTORY_TIME_SQL, new String[]{date,vin});
	}
}
