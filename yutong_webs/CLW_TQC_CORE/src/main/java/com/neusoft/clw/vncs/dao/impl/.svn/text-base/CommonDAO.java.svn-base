package com.neusoft.clw.vncs.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.vncs.dao.ICommonDAO;

public class CommonDAO extends AbstractDaoManager implements ICommonDAO{

	/**
     * 批量执行sql语句
     * @param sqls
     * @return
     */
    public int[] batchUpdate(String[] sqls) throws DataAccessException{
        return jdbcTemplate.batchUpdate(sqls);
    }

	public int[] batchUpdate(String sql,BatchPreparedStatementSetter pss) throws DataAccessException{
		return jdbcTemplate.batchUpdate(sql, pss);
	}
	
    
    
}
