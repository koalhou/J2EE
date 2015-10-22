package com.neusoft.clw.common.service.ridingplanservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.ridingplanservice.RidingPlanService;
import com.neusoft.clw.infomanage.ridingplan.dao.RidingPlanDao;
import com.neusoft.clw.infomanage.ridingplan.domain.RidingReady;
import com.neusoft.clw.infomanage.ridingplan.domain.VssInfo;
import com.neusoft.clw.infomationExport.dao.WriteGZPackDao;

public class RidingPlanServiceImpl extends ServiceImpl implements
		RidingPlanService {
	// 该数据已经存在
	private static final String INFO_INTEGRITY_VIOLATION_ERROR = "info.integrity.violation.error";

	private static final String INFO_DB_ERROR = "info.db.error"; // 数据库访问错误

	private transient RidingPlanDao ridingPlanDao;
	
	private transient WriteGZPackDao writeGZPackDao;
	
	protected static final Logger LOG = Logger.getLogger(ServiceImpl.class);

	public WriteGZPackDao getWriteGZPackDao() {
		return writeGZPackDao;
	}

	public void setWriteGZPackDao(WriteGZPackDao writeGZPackDao) {
		this.writeGZPackDao = writeGZPackDao;
	}

	public RidingPlanDao getRidingPlanDao() {
		return ridingPlanDao;
	}

	public void setRidingPlanDao(RidingPlanDao ridingPlanDao) {
		this.ridingPlanDao = ridingPlanDao;
	}

	public void batchAddRidingPlan(List<VssInfo> vssList) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException {
		try {
			ridingPlanDao.batchAddRidingPlan(vssList);
		} catch (DataAccessIntegrityViolationException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		} catch (DataAccessException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		}

	}
	
	public void batchAddRidingPlan2(Map map,List vssList, List vss_siteList) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException {
		try {
			ridingPlanDao.batchAddRidingPlan2(map, vssList, vss_siteList);
		} catch (DataAccessIntegrityViolationException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		} catch (DataAccessException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		}

	}

	public Map batchDeletedRidingPlan(Map map,String usedPath) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException {
		String targetFileName="";
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			ridingPlanDao.batchDeletedRidingPlan(map);
			resultmap = writeGZPackDao.getTVinToTrip((String)map.get("vehicle_vin"), (String)map.get("trip_id"), usedPath,(String)map.get("vehicle_vin"),"");
		} catch (DataAccessIntegrityViolationException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		} catch (DataAccessException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		}
		return resultmap;
	}

	public Map batchUpdateRidingPlan(RidingReady ridingReady,List vssList,String vehicle_vin_old,String usedPath) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException {
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			ridingPlanDao.batchUpdateRidingPlan(ridingReady,vssList, vehicle_vin_old);
			resultmap = writeGZPackDao.getTVinToTrip(ridingReady.getVehicle_vin(), ridingReady.getTrip_id(),usedPath,vehicle_vin_old,"");
			
		} catch (DataAccessIntegrityViolationException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		} catch (DataAccessException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		}
		return resultmap;
	}
	
}