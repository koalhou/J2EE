package com.neusoft.clw.common.service.writegzpackservice.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.ridingplanservice.RidingPlanService;
import com.neusoft.clw.common.service.writegzpackservice.Writegzpackservice;
import com.neusoft.clw.infomanage.ridingplan.dao.RidingPlanDao;
import com.neusoft.clw.infomationExport.dao.WriteGZPackDao;

public class WritegzpackserviceImpl extends ServiceImpl implements
		Writegzpackservice {
	// 该数据已经存在
	private static final String INFO_INTEGRITY_VIOLATION_ERROR = "info.integrity.violation.error";

	private static final String INFO_DB_ERROR = "info.db.error"; // 数据库访问错误

	private transient WriteGZPackDao writeGZPackDao;

	protected static final Logger LOG = Logger.getLogger(ServiceImpl.class);

	public WriteGZPackDao getWriteGZPackDao() {
		return writeGZPackDao;
	}

	public void setWriteGZPackDao(WriteGZPackDao writeGZPackDao) {
		this.writeGZPackDao = writeGZPackDao;
	}

	public Map getPackPath(String VEHICLE_VIN, String cTrip_ID, String path,String vehicle_vin_old)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		String filePath = "";
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			resultmap = writeGZPackDao.getVinToTrip(VEHICLE_VIN, cTrip_ID, path,vehicle_vin_old);
			//filePath=(String) resultmap.get("filename");
			
		} catch (DataAccessIntegrityViolationException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		} catch (DataAccessException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		}
		return resultmap;
	}
	
	public Map getPackPathM2(String VEHICLE_VIN, String path,String vehicle_vin_old)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		String filePath = "";
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			resultmap = writeGZPackDao.getVinToTripM2(VEHICLE_VIN, path,vehicle_vin_old);
			//filePath=(String) resultmap.get("filename");
			
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