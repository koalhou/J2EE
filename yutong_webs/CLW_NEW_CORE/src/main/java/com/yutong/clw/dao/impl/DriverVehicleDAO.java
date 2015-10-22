package com.yutong.clw.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.beans.cl.DriverVehicleBean;

public class DriverVehicleDAO extends AbstractDaoManager {

	@SuppressWarnings("unchecked")
	public List<DriverVehicleBean> getDriverVehicle() {
		String sql = "select vehicle_vin,driver_id from CLW_XC_VEHDRIVER_T";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(DriverVehicleBean.class));
	}
	
}
