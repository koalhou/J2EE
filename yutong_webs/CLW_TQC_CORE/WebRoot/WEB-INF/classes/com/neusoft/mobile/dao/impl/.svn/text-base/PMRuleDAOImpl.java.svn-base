package com.neusoft.mobile.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.vncs.domain.PMRule;
import com.neusoft.mobile.dao.IPMRuleDAO;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class PMRuleDAOImpl extends AbstractDaoManager implements IPMRuleDAO{

	@SuppressWarnings("unchecked")
	public List<PMRule> getAllPMRule() {
		String sql = "select user_id, on_off, flag,valid_flag,Pm_Rule_Id" +
		" from CLW_M_PERSONALSEND_RULE_T" +
		" where valid_flag = '0'";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PMRule.class));
	}

	@SuppressWarnings("unchecked")
	public List<PMRule> getPartPMRule(String time) {
		String sql = "select user_id, on_off, flag,valid_flag,Pm_Rule_Id " +
				" from CLW_M_PERSONALSEND_RULE_T " +
				" where CLW_M_PERSONALSEND_RULE_T.update_time <= sysdate " +
				" and CLW_M_PERSONALSEND_RULE_T.update_time > to_date('"+time+"', 'yymmddhh24miss')";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PMRule.class));
	}
	
	public String getSysTime(){
		String SELECT_SYS_TIME = "SELECT TO_CHAR(SYSDATE,'yymmddhh24miss') FROM DUAL";
		return (String) jdbcTemplate.queryForObject(SELECT_SYS_TIME, String.class);
	}
}
