package com.neusoft.clw.yw.sitemanager.dao;

import java.util.List;

import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.sitemanager.ds.SitSet;
import com.neusoft.clw.yw.sitemanager.ds.SiteAddOilConfig;

public class StationDao extends ExtendSqlMapDao {
	public String insert(String statment, Object obj)
			throws DataAccessIntegrityViolationException, DataAccessException {
		try {
			String insertID = (String) getSqlMapClientTemplate().insert(
					statment, obj);
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
	
	public String  insertSet(String statment,List<SitSet> list) 
			throws DataAccessIntegrityViolationException, DataAccessException {
		String returnStr = "success";
		try{
			for(SitSet sitset:list) {
				getSqlMapClientTemplate().insert(statment,sitset);
			}
		}catch(Exception e) {
			returnStr = "error";
			throw new DataAccessException(e);
		}
		return returnStr;
	}
	
	public int insertConfig(String statment, SiteAddOilConfig siteConfig)
		throws BusinessException, DataAccessIntegrityViolationException,
		DataAccessException {
		int returnStr = 0;
		try{
			Object obj = getSqlMapClientTemplate().insert(statment,siteConfig);
		} catch(Exception e) {
			returnStr = 5;
			throw new DataAccessException(e);
		}
		return returnStr;
	}
	
	
	public int updateConfig(String statment, SiteAddOilConfig siteConfig)
		throws BusinessException, DataAccessIntegrityViolationException,
		DataAccessException {
		int returnID = 0;
		try{
			returnID = getSqlMapClientTemplate().update(statment,siteConfig);
		} catch(Exception e) {
			returnID = 5;
			throw new DataAccessException(e);
		}
		return returnID;
	}

	
	@SuppressWarnings("unchecked")
	public List<SiteAddOilConfig> selectSiteConfig(String statment, SiteAddOilConfig siteConfig)
		throws BusinessException, DataAccessIntegrityViolationException,
		DataAccessException {
		return getSqlMapClientTemplate().queryForList(statment,siteConfig);
	}
	
	@SuppressWarnings("unchecked")
	public List<SitSet> selectStationSet(String statment,String siteId) throws BusinessException, DataAccessIntegrityViolationException,
		DataAccessException {
		return getSqlMapClientTemplate().queryForList(statment,siteId);
	}
	
}

/*
 * Location: E:\work_spaces\workspace_zspt\CLW_XCP.jar Qualified Name:
 * WEB-INF.classes.com.neusoft.clw.infomanage.sitemanage.dao.StationDao JD-Core
 * Version: 0.6.0
 */