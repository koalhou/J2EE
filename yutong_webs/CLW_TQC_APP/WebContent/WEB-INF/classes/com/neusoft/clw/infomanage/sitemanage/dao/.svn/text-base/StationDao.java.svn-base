package com.neusoft.clw.infomanage.sitemanage.dao;

import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.dao.impl.SqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;

public class StationDao extends SqlMapDao{
   
    public String insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        try {
           String insertID = (String) getSqlMapClientTemplate().insert(statment, obj);
            return insertID;
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
    
    public int deletes(String statment, Object obj)
    throws DataAccessIntegrityViolationException, DataAccessException {
		try {
			int deleteID = getSqlMapClientTemplate().delete(statment, obj);
		    return deleteID;
		} catch (Exception e) {
		    throw new DataAccessException(e);
		}
	}
}
