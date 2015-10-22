package com.yutong.clw.dao.impl;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.dao.ICerFailDAO;

public class CerFailDAO extends AbstractDaoManager implements ICerFailDAO {
	/**
	 * 查询终端鉴权失败表中，某车辆的终端是否鉴权通过，结果大于0说明已经鉴权并且未通过，否则等待鉴权
	 */
	public int getCerFailByID(String vin,String id,String sim) {
		String SELECT_CERTIFICATION_FAILE_SQL = "SELECT COUNT(*) FROM CLW_YW_CERTIFICATION_FAILE_T WHERE VEHICLE_VIN=? AND TERMINAL_ID = ? AND SIM_CARD_NUMBER = ?";
		return jdbcTemplate.queryForInt(SELECT_CERTIFICATION_FAILE_SQL, new String[]{vin,id,sim});
	}

}
