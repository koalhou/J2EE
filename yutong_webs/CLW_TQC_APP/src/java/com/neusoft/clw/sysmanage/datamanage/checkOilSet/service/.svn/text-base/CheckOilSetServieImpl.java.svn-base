package com.neusoft.clw.sysmanage.datamanage.checkOilSet.service;

import java.util.List;


import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.InsertCheckOilList;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.TestList;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;

public class CheckOilSetServieImpl extends ServiceImpl implements CheckOilSetService{

	public void importCheckOilSetInfos(InsertCheckOilList s)
			throws BusinessException {
		try{
			dao.insert("CheckOilSet.insertCheckOilSetInfo", s);
		}catch (Exception e) {
			// TODO: handle exception
		}
                
		
	}

	public void importCheckOilSetInfosTest(TestList s)throws BusinessException {
		
		
		
		
		
		try{
			dao.insert("CheckOilSet.insertCheckOilSetInfoTest", s);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	

}
