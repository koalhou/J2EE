package com.neusoft.mobile.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.info.bean.MobileInfo;
import com.neusoft.mobile.dao.IMobileInfoDAO;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class MobileInfoDAO extends AbstractDaoManager implements IMobileInfoDAO {

	@SuppressWarnings("unchecked")
	public List<MobileInfo> getMobileInfoList() {
		String sql = "select user_id,client_id,user_msg_etag,enterprise_id from CLW_M_USER_INFO_T where client_id is not null or client_id <> ''";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(MobileInfo.class));
	}

}
