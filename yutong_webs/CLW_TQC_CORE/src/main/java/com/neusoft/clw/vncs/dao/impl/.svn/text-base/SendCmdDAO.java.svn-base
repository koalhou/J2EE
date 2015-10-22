package com.neusoft.clw.vncs.dao.impl;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.dao.ISendCmdDAO;
import com.neusoft.clw.vncs.domain.SendCmdBean;

public class SendCmdDAO extends AbstractDaoManager implements ISendCmdDAO{
	
	public SendCmdBean getOperateByMSGID(String msg_id) {
		String SELECT_OPERATE_BYID = "SELECT OPERATE_USER_ID,to_char(OPERATE_TIME,'yymmddhh24miss') AS OPERATE_TIME,MSG_ID,DEAL_STATE FROM CLW_YW_SEND_COMMAND_T WHERE MSG_ID=?";
		return (SendCmdBean) jdbcTemplate.queryForObject(SELECT_OPERATE_BYID, new Object[]{msg_id},ParameterizedBeanPropertyRowMapper.newInstance(SendCmdBean.class));
	}

}
