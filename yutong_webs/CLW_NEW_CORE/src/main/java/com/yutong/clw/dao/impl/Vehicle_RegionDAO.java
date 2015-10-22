package com.yutong.clw.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.beans.cl.Vehicle_RegionBean;
import com.yutong.clw.dao.IVehicle_RegionDAO;

public class Vehicle_RegionDAO extends AbstractDaoManager implements
		IVehicle_RegionDAO {

	@SuppressWarnings("unchecked")
	public List<Vehicle_RegionBean> getAllVRList(String time) {
		String SELECT_VEHICLE_REGION_SQL = 
			"select CLW_CL_BASE_INFO_T.VEHICLE_VIN,CLW_YW_GROUP_T.VEHICLE_RELATE,CLW_YW_GROUP_T.Valid_Flag AS Group_Valid_Flag,CLW_YW_REGION_T.* " +
			"from CLW_YW_REGION_T,CLW_YW_RG_T,CLW_YW_GV_T,CLW_YW_GROUP_T,CLW_CL_BASE_INFO_T " +
			"where CLW_YW_REGION_T.REGION_ID = CLW_YW_RG_T.REGION_ID " +
			"and CLW_YW_RG_T.GROUP_ID = CLW_YW_GROUP_T.GROUP_ID " +
			"and CLW_YW_GROUP_T.Group_Id = CLW_YW_GV_T.Group_Id " +
			"and CLW_CL_BASE_INFO_T.VEHICLE_ID = CLW_YW_GV_T.VEHICLE_ID " +
			"and to_char(CLW_YW_REGION_T.MODIFY_TIME,'yymmddhh24miss') >= ? " +
			"and to_char(CLW_YW_REGION_T.MODIFY_TIME,'yymmddhh24miss') <= (select to_char(sysdate,'yymmddhh24miss') from dual)";
		return jdbcTemplate.query(SELECT_VEHICLE_REGION_SQL, new String[]{time}, ParameterizedBeanPropertyRowMapper.newInstance(Vehicle_RegionBean.class));
	}

	@SuppressWarnings("unchecked")
	public List<Vehicle_RegionBean> getVRList() {
		String SELECT_ALL_VEHICLE_REGION_SQL = 
			"select CLW_CL_BASE_INFO_T.VEHICLE_VIN,CLW_YW_REGION_T.* " +
			"from CLW_YW_REGION_T,CLW_YW_RG_T,CLW_YW_GV_T,CLW_YW_GROUP_T,CLW_CL_BASE_INFO_T " +
			"where CLW_YW_REGION_T.REGION_ID = CLW_YW_RG_T.REGION_ID " +
			"and CLW_YW_RG_T.GROUP_ID = CLW_YW_GROUP_T.GROUP_ID " +
			"and CLW_YW_GROUP_T.Group_Id = CLW_YW_GV_T.Group_Id " +
			"and CLW_CL_BASE_INFO_T.VEHICLE_ID = CLW_YW_GV_T.VEHICLE_ID " +
			"and CLW_YW_REGION_T.VALID_FLAG = '0' " +
			"and CLW_YW_GROUP_T.Valid_Flag = '0' " +
			"and CLW_YW_GROUP_T.VEHICLE_RELATE='1'" ;
		return jdbcTemplate.query(SELECT_ALL_VEHICLE_REGION_SQL, ParameterizedBeanPropertyRowMapper.newInstance(Vehicle_RegionBean.class));
	}

}
