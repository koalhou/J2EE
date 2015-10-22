package com.neusoft.clw.yw.ftly.service.impl;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.ftly.dao.StealOilSmsDao;
import com.neusoft.clw.yw.ftly.ds.StealOilSmsInfo;
import com.neusoft.clw.yw.ftly.service.StealOilSmsService;

public class StealOilSmsServiceImpl implements StealOilSmsService{
	protected static final Logger LOG = Logger.getLogger(ServiceImpl.class);
	private static final String INFO_DB_ERROR = "info.db.error"; // 数据库访问错误
	private StealOilSmsDao stealOilSmsDao;
	
	public StealOilSmsDao getStealOilSmsDao() {
		return stealOilSmsDao;
	}
	public void setStealOilSmsDao(StealOilSmsDao stealOilSmsDao) {
		this.stealOilSmsDao = stealOilSmsDao;
	}
	public void insert(StealOilSmsInfo stealOilSmsInfo) throws BusinessException,DataAccessIntegrityViolationException, DataAccessException {
		stealOilSmsDao.insert(stealOilSmsInfo);
	}
	
	public void update(StealOilSmsInfo stealOilSmsInfo) throws BusinessException,DataAccessIntegrityViolationException, DataAccessException {
		 String stuId = stealOilSmsInfo.getStu_id();
		 this.batchDelete(stealOilSmsInfo);
		 stealOilSmsInfo.setStu_id(stuId);
		 this.insert(stealOilSmsInfo);
	}
	
	public void batchDelete(StealOilSmsInfo stealOilSmsInfo) throws BusinessException,DataAccessIntegrityViolationException, DataAccessException {
		try {
			String [] stuArr = stealOilSmsInfo.getStu_id().split(",");
			String stuStr = "";
			for(String stu_id : stuArr){
				stuStr = stuStr+"'"+stu_id+"',";
			}
			stealOilSmsInfo.setStu_id(stuStr.substring(0, stuStr.length()-1));
			stealOilSmsDao.batchDelete(stealOilSmsInfo);
		} catch (DataAccessIntegrityViolationException e) {
			throw new BusinessException(INFO_DB_ERROR);
		} catch (DataAccessException e) {
			LOG.error(e);
			throw new BusinessException(INFO_DB_ERROR);
		}
	}
}
