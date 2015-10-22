/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package com.neusoft.clw.yw.sitemanager.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.sitemanager.ds.SitSet;
import com.neusoft.clw.yw.sitemanager.ds.Site;
import com.neusoft.clw.yw.sitemanager.ds.SiteAddOilConfig;

public interface StationService extends Service {

	public abstract String insertPointToStation(String s, Site site)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException;

	public abstract String insertStation(String s, Site site)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException;
	
	public String  insertSet(String statment,List<SitSet> list) 
			throws BusinessException,DataAccessIntegrityViolationException, DataAccessException;
	
	
	public void saveRateConfig(SiteAddOilConfig siteConfig) 
			throws BusinessException,DataAccessIntegrityViolationException, DataAccessException;
	
	public void saveLowConfig(SiteAddOilConfig siteConfig) 
			throws BusinessException,DataAccessIntegrityViolationException, DataAccessException;
	
	public void saveLaterConfig(SiteAddOilConfig siteConfig)
			throws BusinessException, DataAccessIntegrityViolationException,DataAccessException; 
	
	public int updateConfig(String statment, SiteAddOilConfig siteConfig) 
			throws BusinessException,DataAccessIntegrityViolationException, DataAccessException;
	
	public List<SiteAddOilConfig> selectSiteConfig(String statment, SiteAddOilConfig siteConfig) 
			throws BusinessException,DataAccessIntegrityViolationException, DataAccessException;
	
	public List<SitSet> selectStationSet(String statment,String siteId) 
			throws BusinessException, DataAccessIntegrityViolationException, DataAccessException;
	
	public int deletes(String statment,Site site) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;
}


/*
	DECOMPILATION REPORT

	Decompiled from: D:\aaaa.jar
	Total time: 0 ms
	Jad reported messages/errors:
	Exit status: 0
	Caught exceptions:
*/