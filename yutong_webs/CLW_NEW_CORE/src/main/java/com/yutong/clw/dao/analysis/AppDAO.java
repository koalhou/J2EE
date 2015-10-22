package com.yutong.clw.dao.analysis;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.yutong.clw.beans.app.AppBean;

public class AppDAO extends AbstractDaoManager{

	public AppBean getAppCfg(String appid) {
		String SELECT_APP_CFG_SQL = "SELECT APP_ID,SEND_PATH,CORE_PASS FROM CLW_YW_APP_CFG_T where APP_ID = ?";
		return (AppBean) jdbcTemplate.queryForObject(SELECT_APP_CFG_SQL,new String[]{appid},ParameterizedBeanPropertyRowMapper.newInstance(AppBean.class));
	}

}
