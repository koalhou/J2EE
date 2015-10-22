package com.neusoft.clw.vncs.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.vncs.dao.IAppConfigDAO;
import com.neusoft.clw.vncs.domain.AppConfigBean;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class AppConfigDAO extends AbstractDaoManager implements IAppConfigDAO{
	 
	@SuppressWarnings("unchecked")
	public List<AppConfigBean> getAllAppConfigList() {
		 String SELECT_APP_CFG_SQL = "SELECT APP_ID,APP_NAME,APP_IP,SEND_PATH,CORE_ID," +
			"CORE_PASS FROM CLW_YW_APP_CFG_T";
		return jdbcTemplate.query(SELECT_APP_CFG_SQL, ParameterizedBeanPropertyRowMapper.newInstance(AppConfigBean.class));
	}
}
