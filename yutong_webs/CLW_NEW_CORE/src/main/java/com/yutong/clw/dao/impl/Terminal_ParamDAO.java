package com.yutong.clw.dao.impl;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.dao.ITerminal_ParamDAO;

public class Terminal_ParamDAO extends AbstractDaoManager implements
		ITerminal_ParamDAO {

	public int getParamCount(String terminal_id) {
		String SELECT_TERMINAL_PARAM_SQL = "select count(*) from CLW_JC_TERMINAL_PARA_T where TERMINAL_ID = ?";
		return jdbcTemplate.queryForInt(SELECT_TERMINAL_PARAM_SQL, new Object[]{terminal_id});
	}
	

	
	public int selectPhotoId(String id){
		String sql = "select count(*) from CLW_YW_PHOTO_T where photo_id = ? and media_id is not null";
		return jdbcTemplate.queryForInt(sql, new String[]{id});
	}
}
