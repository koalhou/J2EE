package com.neusoft.clw.yw.ftly.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.SqlMapDao;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.ftly.ds.StealOilSmsInfo;

public class StealOilSmsDao extends SqlMapDao{
	
	public void insert(StealOilSmsInfo stealOilSmsInfo) throws BusinessException,DataAccessIntegrityViolationException, DataAccessException {
		 try {
			 	SqlMapClient sqlmapclient = this.getSqlMapClient();
	            sqlmapclient.startBatch();
	            String [] orgArr = stealOilSmsInfo.getOrganization_id().split(",");
	            for(String org : orgArr){
	    			stealOilSmsInfo.setOrganization_id(org);
	    			getSqlMapClientTemplate().delete("StealOilSms.insert_stealoilsms", stealOilSmsInfo);
	    		}
	            sqlmapclient.executeBatch();
	        } catch (SQLException e) {
	            throw new DataAccessException(e);
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
	 }
	
//	 public void update(StealOilSmsInfo stealOilSmsInfo) throws BusinessException,DataAccessIntegrityViolationException, DataAccessException {
//		 String stuId = stealOilSmsInfo.getStu_id();
//		 stealOilSmsInfo.setStu_id("'"+stuId+"'");
//		 this.batchDelete(stealOilSmsInfo);
//		 stealOilSmsInfo.setStu_id(stuId);
//		 String [] orgArr = stealOilSmsInfo.getOrganization_id().split(",");
//		 for(String org : orgArr){
//			stealOilSmsInfo.setOrganization_id(org);
//			this.insert(stealOilSmsInfo);
//		 }
//	 }
	 
	 public void batchDelete(StealOilSmsInfo stealOilSmsInfo)throws DataAccessIntegrityViolationException, DataAccessException {
	        SqlMapClient sqlmapclient = this.getSqlMapClient();
	        try {
	            sqlmapclient.startBatch();
	            getSqlMapClientTemplate().delete("StealOilSms.delete_stealoilsms", stealOilSmsInfo);
	            sqlmapclient.executeBatch();
	        } catch (SQLException e) {
	            throw new DataAccessException(e);
	        } catch (Exception e) {
	            throw new DataAccessException(e);
	        }
	    }
}
