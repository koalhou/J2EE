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

	/**
	 * 宇通乘车规划下发
	 */
	public Map getPackPath(String VEHICLE_VIN, String cTrip_ID,String path,String vehicle_vin_old,String exe_date)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		String filePath = "";
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			resultmap = writeGZPackDao.getVinToTrip(VEHICLE_VIN, cTrip_ID,path,vehicle_vin_old,exe_date);
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
	
	/**
	 * 泰安乘车规划下发
	 */
	public Map getTPackPath(String VEHICLE_VIN, String cTrip_ID,String path,String vehicle_vin_old,String exe_date)
			throws BusinessException, DataAccessIntegrityViolationException,
			DataAccessException {
		Map < String, Object > resultmap = new HashMap < String, Object >();
		try {
			resultmap = writeGZPackDao.getTVinToTrip(VEHICLE_VIN, cTrip_ID,path,vehicle_vin_old,exe_date);
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