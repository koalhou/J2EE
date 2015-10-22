package com.neusoft.clw.common.service.stationservice;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;

public interface StationService extends Service {
	String insertPointToStation(String pointID, Site site)
            throws BusinessException, DataAccessIntegrityViolationException, DataAccessException;
	String insertStation(String pointID, Site site)
    throws BusinessException, DataAccessIntegrityViolationException, DataAccessException;
}