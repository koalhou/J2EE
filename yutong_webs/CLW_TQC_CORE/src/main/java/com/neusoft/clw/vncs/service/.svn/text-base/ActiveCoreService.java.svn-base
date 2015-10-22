package com.neusoft.clw.vncs.service;

import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.impl.TransactionDaoServers;

public class ActiveCoreService {
	
	private static final ActiveCoreService activeCoreService = new ActiveCoreService();

	public static ActiveCoreService getInstance(){
		return activeCoreService;
	}
	
	public boolean getMainCore(String core_id,int sendvalid){
		TransactionDaoServers transactionDaoServersProxy = (TransactionDaoServers) SpringBootStrap.getInstance().getBean("transactionDaoServersProxy");
		return transactionDaoServersProxy.selectRunState(core_id, sendvalid);
	}
	
}
