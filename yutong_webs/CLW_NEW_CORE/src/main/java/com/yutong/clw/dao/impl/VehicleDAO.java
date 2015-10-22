package com.yutong.clw.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.beans.cl.VehicleBean;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.IVehicleDAO;

public class VehicleDAO extends AbstractDaoManager implements IVehicleDAO{

	@SuppressWarnings("unchecked")
	public List<VehicleBean> getVehicleParam() {
		String SELECT_VEHICLE_PARAM = "SELECT VEHICLE_VIN,VEHICLE_LN,TYRE_R,REAR_AXLE_RATE,STANDARD_ROTATE,VALID_FLAG,to_char(MODIFY_TIME,'yymmddhh24miss') AS MODIFY_TIME,ENTERPRISE_ID FROM CLW_CL_BASE_INFO_T WHERE VALID_FLAG ='0'";
		return jdbcTemplate.query(SELECT_VEHICLE_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(VehicleBean.class));
	}
	@SuppressWarnings("unchecked")
	public List<VehicleBean> getAllVehicleParam() {
		String SELECT_VEHICLE_SQL = "SELECT VEHICLE_VIN,VEHICLE_LN,TYRE_R,REAR_AXLE_RATE,STANDARD_ROTATE,VALID_FLAG,to_char(MODIFY_TIME,'yymmddhh24miss')  AS MODIFY_TIME,ENTERPRISE_ID" +
		" FROM CLW_CL_BASE_INFO_T where MODIFY_TIME<=sysdate and MODIFY_TIME >= to_date(?,'yymmddhh24miss')";
		return jdbcTemplate.query(SELECT_VEHICLE_SQL,new Object[] {Constant.upd_vehicle_cache_time},ParameterizedBeanPropertyRowMapper.newInstance(VehicleBean.class));
	}
}
