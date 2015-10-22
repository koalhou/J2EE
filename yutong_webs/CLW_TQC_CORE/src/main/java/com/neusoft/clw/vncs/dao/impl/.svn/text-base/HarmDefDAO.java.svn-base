package com.neusoft.clw.vncs.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.vncs.dao.IHarmDefDAO;
import com.neusoft.clw.vncs.domain.HarmDefBean;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class HarmDefDAO extends AbstractDaoManager implements IHarmDefDAO{

	@SuppressWarnings("unchecked")
	public List<HarmDefBean> getHarmDefInfo() {
	//	List<HarmDefBean> hlist=jdbcTemplate.queryForList(SELECT_HARMDEF_SQL, HarmDefBean.class); 
		String SELECT_HARMDEF_SQL = "SELECT CLW_CL_BASE_INFO_T.VEHICLE_VIN VIN,CLW_CL_BASE_INFO_T.STANDARD_OIL STANDARD_OIL,CLW_CL_BASE_INFO_T.STANDARD_ROTATE STANDARD_ROTATE,CLW_YW_HARMDEF_T.* " +
		"FROM CLW_YW_HARMDEF_T,CLW_CL_BASE_INFO_T " +
		"WHERE CLW_YW_HARMDEF_T.DEF_ID = CLW_CL_BASE_INFO_T.CR_CONFIG_ID" +
		" and CLW_CL_BASE_INFO_T.valid_flag = '0' " ;
		List<HarmDefBean> hlist=jdbcTemplate.query(SELECT_HARMDEF_SQL, ParameterizedBeanPropertyRowMapper.newInstance(HarmDefBean.class));
		return  hlist;
	}

}
