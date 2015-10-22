package com.yutong.clw.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.beans.cl.RidingPlanBean;

public class RidingPlanDAO extends AbstractDaoManager{
	
	@SuppressWarnings("unchecked")
	public List<RidingPlanBean> getBaseRidingPlanInfo() {
		String SELECT_SQL =  " SELECT vt.VEHICLE_VIN,vt.STUDENT_ID,vt.TRIP_ID,TO_CHAR(vt.MODIFY_TIME,'yymmddhh24missSSS') MODIFY_TIME,st.STU_CODE,st.STU_CARD_ID " +
							 " FROM CLW_XC_VSS_T vt,CLW_XC_STUDENT_T st WHERE vt.STUDENT_ID = st.STU_ID" +
							 " AND vt.VEHICLE_VIN IS NOT NULL AND vt.STUDENT_ID IS NOT NULL";
		return jdbcTemplate.query(SELECT_SQL,ParameterizedBeanPropertyRowMapper.newInstance(RidingPlanBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<RidingPlanBean> getAllRidingPlanInfo() {
		String SELECT_ALL_SQL = " SELECT vt.VEHICLE_VIN,vt.STUDENT_ID,vt.TRIP_ID,TO_CHAR(vt.MODIFY_TIME,'yymmddhh24missSSS') MODIFY_TIME,st.STU_CODE,st.STU_CARD_ID " +
								" FROM CLW_XC_VSS_T vt,CLW_XC_STUDENT_T st WHERE vt.STUDENT_ID = st.STU_ID " +
								" AND vt.VEHICLE_VIN IS NOT NULL AND vt.STUDENT_ID IS NOT NULL" +
								" AND st.STU_CODE IS NOT NULL";
		return jdbcTemplate.query(SELECT_ALL_SQL,ParameterizedBeanPropertyRowMapper.newInstance(RidingPlanBean.class));
	}
	
	public String getSysTime(){
		String SELECT_SYS_TIME = "SELECT TO_CHAR(SYSDATE,'yymmddhh24miss') FROM DUAL";
		return (String) jdbcTemplate.queryForObject(SELECT_SYS_TIME, String.class);
	}

	public RidingPlanBean getRidingPlanByStuCode(String stuCode) {
		String SELECT_BYID = " SELECT vt.VEHICLE_VIN,vt.STUDENT_ID,vt.TRIP_ID,TO_CHAR(vt.MODIFY_TIME,'yymmddhh24missSSS') MODIFY_TIME,st.STU_CODE,st.STU_CARD_ID" +
							 " FROM CLW_XC_VSS_T vt,CLW_XC_STUDENT_T st WHERE vt.STUDENT_ID = st.STU_ID"+
							 " AND st.STU_CODE = ? AND vt.VEHICLE_VIN IS NOT NULL AND vt.STUDENT_ID IS NOT NULL" +
							 " AND st.STU_CODE IS NOT NULL";
		return (RidingPlanBean) jdbcTemplate.queryForObject(SELECT_BYID, new Object[]{stuCode},ParameterizedBeanPropertyRowMapper.newInstance(RidingPlanBean.class));
	}
}
