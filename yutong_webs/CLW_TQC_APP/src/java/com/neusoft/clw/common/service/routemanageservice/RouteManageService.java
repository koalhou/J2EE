package com.neusoft.clw.common.service.routemanageservice;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;
import com.neusoft.clw.sysmanage.datamanage.routemanage.domain.RouteInfo;

public interface RouteManageService extends Service {
    void addRouteMap(RouteInfo routeInfo, Site siteInfo)
		throws BusinessException;
    
    void editRouteMap(RouteInfo routeInfo, Site siteInfo)
		throws BusinessException;
    void deleteRouteStation(RouteInfo routeInfo)
		throws BusinessException;
}
