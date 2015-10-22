package com.neusoft.clw.vncs.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.domain.CoreCfgBean;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class ActiveCoreDAO extends AbstractDaoManager {

	private Logger log = LoggerFactory.getLogger(ActiveCoreDAO.class);

	public void updateCoreCfg(String coreId) {
		String sql = "update CLW_YW_CORE_CFG_T set active_time = sysdate where core_id = ?";
		jdbcTemplate.update(sql, new String[] { coreId });
	}

	/* 用户不断更新核心配置表中当前核心的活跃时间 */
	public void operateParamTable() {
		/* 获取当前核心id */
		String core_id = Constant.CORE_ID;
		log.info("keepalive 's core_id :" + core_id);
		/* 查询core_id下的时间更新 */
		// List ltname = corecfgDAO.selectCoreUpdateTime(core_id);
		// log.info("keepalive 's list :" + ltname);
		// if (ltname != null && ltname.size() > 0) {//
		updateCoreCfg(core_id);
		// } else {
		// // 没有该core_id时插入一条
		// corecfgDAO.insertCoreCfg(core_id,core_id);
		// }
	}

	@SuppressWarnings("unchecked")
	public List selectRunState(String core_id) {
		String sql = "select CORE_ID,ACTIVE_TIME,ACTIVE_STATE,MAIN_FLAG from CLW_YW_CORE_CFG_T tab where CORE_ID = ? and MAIN_FLAG ='1'";
		return jdbcTemplate.query(sql,new String[]{core_id}, ParameterizedBeanPropertyRowMapper.newInstance(CoreCfgBean.class));
	}
}
