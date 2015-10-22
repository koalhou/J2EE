/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.neusoft.clw.yw.sitemanager.service.impl;

//import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.sitemanager.dao.StationDao;
import com.neusoft.clw.yw.sitemanager.ds.SitSet;
import com.neusoft.clw.yw.sitemanager.ds.Site;
import com.neusoft.clw.yw.sitemanager.ds.SiteAddOilConfig;
import com.neusoft.clw.yw.sitemanager.ds.TerminalViBean;
import com.neusoft.clw.yw.sitemanager.service.StationService;

public class StationServiceImpl extends ServiceImpl implements StationService {

	public StationServiceImpl() {
	}

	public StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public String insertPointToStation(String pointID, Site site)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		String insertID = "";
		insertID = stationDao.insert("SitManage.insertStationInfo", site);
		 TerminalViBean tv = new TerminalViBean();
		 tv.setID(pointID);
		 update("SitManage.deletebyCollection_ID", tv);
		return insertID;
	}

	public String insertStation(String pointID, Site site)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		String insertID = "";
		insertID = stationDao.insert("SitManage.insertStationInfo", site);
		return insertID;
	}

	public String insertSet(String statment, List<SitSet> list)
			throws DataAccessIntegrityViolationException, DataAccessException {
		String returnStr = stationDao.insertSet(statment, list);
		return returnStr;
	}

	private transient StationDao stationDao;

	/**
	 * 保存油量监控周期，先查询是否存在，再新增或保存
	 */
	public void saveRateConfig(SiteAddOilConfig siteConfig)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		if (getCount("SitManage.countSiteConfig", siteConfig) == 0) {
			insert("SitManage.insertConfig", siteConfig);
		}else{
			dao.update("SitManage.updateRateConfig", siteConfig);
		}
	}
	
	/**
	 * 保存油量监控周期，先查询是否存在，再新增或保存
	 */
	public void saveLowConfig(SiteAddOilConfig siteConfig)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		if (getCount("SitManage.countSiteConfig", siteConfig) == 0) {
			insert("SitManage.insertConfig", siteConfig);
		}else{
			dao.update("SitManage.updateLowerConfig", siteConfig);
		}
	}
		
	/**
	 * 保存迟到告警时间，先查询是否存在，再新增或保存
	 */
	public void saveLaterConfig(SiteAddOilConfig siteConfig)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		if (getCount("SitManage.countSiteConfig", siteConfig) == 0) {
			insert("SitManage.insertConfig", siteConfig);
		}else{
			dao.update("SitManage.updatelaterConfig", siteConfig);
		}
	}

	public int updateConfig(String statment, SiteAddOilConfig siteConfig)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		int returnStr = stationDao.updateConfig(statment, siteConfig);
		return returnStr;
	}

	
	public List<SiteAddOilConfig> selectSiteConfig(String statment,
			SiteAddOilConfig siteConfig) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException {
		return stationDao.selectSiteConfig(statment, siteConfig);
	}

	public List<SitSet> selectStationSet(String statment, String siteId)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		List<SitSet> list = stationDao.selectStationSet(statment, siteId);
		return list;
	}

	public int deletes(String statment, Site site) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException {
		return stationDao.delete(statment, site);
	}
}
