package com.neusoft.parents.overpreline.dao.impl;
/**
 * @author liuja
 * @createdate 2014年4月29日  上午8:38:30
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.parents.overpreline.dao.IOverPreLineDAO;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class OverPreLineDAO extends AbstractDaoManager implements IOverPreLineDAO{
	
	private static Logger log = LoggerFactory.getLogger(OverPreLineDAO.class);

	public List<NoticeBean> getPreLine(String route_id) {
		
		String sql = "select t.route_id,t.longitude,t.latitude from CLW_TQC_EMP_PRELINE_T t where t.route_id = ?";
        return jdbcTemplate.query(sql, new Object[] { route_id }, ParameterizedBeanPropertyRowMapper.newInstance(NoticeBean.class));
	}

}
