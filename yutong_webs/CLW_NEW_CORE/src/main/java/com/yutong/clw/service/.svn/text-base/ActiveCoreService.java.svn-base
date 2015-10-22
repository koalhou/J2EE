package com.yutong.clw.service;

import com.yutong.clw.dao.impl.TransactionDaoServers;
import com.yutong.clw.sysboot.SpringBootStrap;

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
