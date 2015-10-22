package com.neusoft.clw.common.service.routemanageservice.impl;

import java.util.ArrayList;
import java.util.List;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.routemanageservice.RouteManageService;
import com.neusoft.clw.infomanage.sitemanage.dao.StationDao;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;
import com.neusoft.clw.sysmanage.datamanage.routemanage.domain.RouteInfo;

public class RouteManageServiceImpl extends ServiceImpl implements
        RouteManageService {
	private transient StationDao stationDao;

	public StationDao getStationDao() {
		return stationDao;
	}

	public void setStationDao(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	public void addRouteMap(RouteInfo routeinfo, Site siteInfo)
    	throws BusinessException {
		String insertID;
		try {
			insertID = stationDao.insert("RouteManage.insertrouteInfo", routeinfo);
	    	String [] sites = siteInfo.getSite_up_string().split(",");
	    	if(!siteInfo.getSite_up_string().equals("")){
	    		 List < Site > siteInfoList = new ArrayList < Site >();
	             for (int i = 0; i < sites.length; i++) {
	            	Site stationInfo = new Site();
	            	stationInfo.setRoute_id(insertID);
	            	stationInfo.setSite_id(sites[i]);
	            	stationInfo.setRs_order(Integer.toString(i+1));
	        		siteInfoList.add(stationInfo);
	             }
	             updateBatch("RouteManage.setUpStationInfos", siteInfoList);
	    	}     
	    	/*String [] siteDowns = siteInfo.getSite_down_string().split(",");
	    	if(!siteInfo.getSite_up_string().equals("")){
	    		 List < Site > siteDownInfo = new ArrayList < Site >();
	             for (int i = 0; i < siteDowns.length; i++) {
	            	Site stationDownInfo = new Site();
	            	stationDownInfo.setRoute_id(insertID);
	            	stationDownInfo.setSite_id(siteDowns[i]);
	            	stationDownInfo.setRs_order(Integer.toString(i+1));
	            	siteDownInfo.add(stationDownInfo);
	             }
	             updateBatch("RouteManage.setDownStationInfos", siteDownInfo);
	    	}  */
		} catch (Exception e) {
			// add by jinp begin 20120815
			throw new BusinessException(e);
			// add by jinp end 20120815
		}
	}
	
	public void editRouteMap(RouteInfo routeinfo, Site siteInfo)
		throws BusinessException {
		try {
			update("RouteManage.updatebyRouteid", routeinfo);
	    	String [] sites = siteInfo.getSite_up_string().split(",");
	    	if(!siteInfo.getSite_up_string().equals("")){
	    		siteInfo.setRoute_id(routeinfo.getRoute_id());
	    		String site_ids="";
	    		for(int j=0;j<sites.length;j++){
	    			if(sites.length>1&&sites.length!=j+1){
	    				site_ids+="'"+sites[j]+"',";
	    			}else{
	    				site_ids+="'"+sites[j]+"'";
	    			}
	    		}
	    		siteInfo.setSite_id_del(site_ids);
	    		stationDao.deletes("RouteManage.deleteupbyStationId", siteInfo);
				List < Site > siteInfoList = new ArrayList < Site >();
				for (int i = 0; i < sites.length; i++) {
					Site stationInfo = new Site();
					stationInfo.setRoute_id(routeinfo.getRoute_id());
					stationInfo.setSite_id(sites[i]);
					stationInfo.setRs_order(Integer.toString(i+1));
					siteInfoList.add(stationInfo);
				}
				updateBatch("RouteManage.setUpStationInfos", siteInfoList);
	    	}     
		} catch (Exception e) {
			// add by jinp begin 20120815
			throw new BusinessException(e);
			// add by jinp end 20120815
		}
		
	}
	
	public void deleteRouteStation(RouteInfo routeinfo)
    throws BusinessException {
		
		try {
			update("RouteManage.deletebyRouteid", routeinfo);
			stationDao.deletes("RouteManage.deleteRouteStation", routeinfo);
		} catch (DataAccessIntegrityViolationException e) {
			e.printStackTrace();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
	}

}
