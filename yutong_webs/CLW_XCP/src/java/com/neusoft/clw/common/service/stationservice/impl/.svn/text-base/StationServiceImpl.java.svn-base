package com.neusoft.clw.common.service.stationservice.impl;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.stationservice.StationService;
import com.neusoft.clw.infomanage.sitemanage.dao.StationDao;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;

public class StationServiceImpl extends ServiceImpl implements StationService {
	private transient StationDao stationDao;

	public StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public String insertPointToStation(String pointID, Site site)
			throws BusinessException, DataAccessIntegrityViolationException, DataAccessException {
		String insertID="";
		insertID = stationDao.insert("StationManage.insertStationInfo", site);
		TerminalViBean tv = new TerminalViBean();
		tv.setID(pointID);
		update("StationManage.deletebyCollection_ID", tv);
		return insertID;
	}
	public String insertStation(String pointID, Site site)
		throws BusinessException, DataAccessIntegrityViolationException, DataAccessException {
		String insertID="";
		insertID = stationDao.insert("StationManage.insertStationInfo", site);
		return insertID;
	}
}