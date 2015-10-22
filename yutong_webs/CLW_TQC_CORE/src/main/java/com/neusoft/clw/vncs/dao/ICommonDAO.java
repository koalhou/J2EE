package com.neusoft.clw.vncs.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public interface ICommonDAO {
	/**
     * 批量执行sql语句
     * @param sqls
     * @return
     */
    public int[] batchUpdate(String[] sqls);
    
    public int[] batchUpdate(String sql,BatchPreparedStatementSetter ps);
}
