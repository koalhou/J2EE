package com.neusoft.clw.common.service.ridingplanservice;

import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.infomanage.ridingplan.domain.RidingReady;
import com.neusoft.clw.infomanage.ridingplan.domain.VssInfo;

public interface RidingPlanService extends Service {
	public void batchAddRidingPlan(List<VssInfo> vssList) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;

	public Map batchDeletedRidingPlan(Map map,String usedPath) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;

	public void batchAddRidingPlan2(Map map, List vssList, List vss_siteList) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;
	
	public Map batchUpdateRidingPlan(RidingReady ridingReady, List vssList, String vehicle_vin_old,String usedPath) throws BusinessException,
			DataAccessIntegrityViolationException, DataAccessException;

}