package com.yutong.clw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.beans.cl.CoreCfgBean;

public class TransactionDao extends AbstractDaoManager{
	/**
	 * 查询系统运行表信息
	 * 
	 * @param loginname
	 * @param serverid
	 */
	
	@SuppressWarnings("unchecked")
	public List selectAllRunState() throws SQLException, DataAccessException {
		String sql = "select CORE_ID,to_char(ACTIVE_TIME,'yymmddhh24miss') as ACTIVE_TIME,ACTIVE_STATE,MAIN_FLAG from CLW_YW_CORE_CFG_T for update wait 20";
		List list = jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CoreCfgBean.class));
//		System.out.println("ssssssss:" + list);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List selectSendState()
			throws SQLException, DataAccessException {
		String sql = "select CORE_ID,to_char(ACTIVE_TIME,'yymmddhh24miss') as ACTIVE_TIME,ACTIVE_STATE,MAIN_FLAG from CLW_YW_CORE_CFG_T tab where MAIN_FLAG = '1' for update wait 3";
		return  jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(CoreCfgBean.class));
	}

	public void updateRunState(String main_flag,String core_id) {
		String sql = "update CLW_YW_CORE_CFG_T set MAIN_FlAG=? where CORE_ID=?";
		jdbcTemplate.update(sql, new String[]{main_flag,core_id});
	}
	//报表服务器用

	public String getReportServer() {
		String sql = "select param_value from clw_jc_param_cfg_t t where t.param_name='reportServer' for update wait 20";
		String core=  (String) jdbcTemplate.queryForObject(sql, String.class);
		return core;
	}
	
	public String getQuartzServer() {
		String sql = "select param_value from clw_jc_param_cfg_t t where t.param_name='quartzServer' for update wait 20";
		String core=  (String) jdbcTemplate.queryForObject(sql, String.class);
		return core;
	}

	public String getQServer() {
		String sql = "select param_value from clw_jc_param_cfg_t t where t.param_name='reportServer' for update wait 20";
		String core=  (String) jdbcTemplate.queryForObject(sql, String.class);
		return core;
	}
	
	public int queryReportServer(int coreActive) {
		String sql = "select count(*) as count from CLW_YW_CORE_CFG_T t where (sysdate-t.active_time)*24*60<"+coreActive;
		int core=    jdbcTemplate.queryForInt(sql);
		return core;
	}
	public int queryLiveReportS(int coreActive, String reportServer) {
		String sql = "select count(*) as count from CLW_YW_CORE_CFG_T t where (sysdate-t.active_time)*24*60<"+coreActive+" and core_id=?";
		int core=    jdbcTemplate.queryForInt(sql,  new String[]{reportServer});
		return core;
	}

	public void setReportServer(String cOREID) {
		String sql = "update clw_jc_param_cfg_t set param_value=? where param_name='reportServer'";
		jdbcTemplate.update(sql, new String[]{cOREID});
		
	}
	
	public void setQuartzServer(String cOREID) {
		String sql = "update clw_jc_param_cfg_t set param_value=? where param_name='QuartzServer'";
		jdbcTemplate.update(sql, new String[]{cOREID});
		
	}

	
	
}
