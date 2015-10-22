package com.neusoft.clw.common.service.vehiclemanageservice.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.vehiclemanageservice.VehicleManageService;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;

public class VehManageServiceImpl extends ServiceImpl implements
        VehicleManageService {
    public void updateList(List < VehcileInfo > list, String orgid)
            throws BusinessException {
        updateBatch("VehicleManage.updateVehOrgid", list);
        update("VehicleManage.updateorgidFlag", orgid);

    }

    public void updateaddMulti(VehcileInfo vechinfo,String updateStr) throws BusinessException {
    	if(updateStr.equals("1")){
    		update("VehicleManage.updatebyVehicleid", vechinfo);
    	}
		if(updateStr.equals("2")){
			update("VehicleManage.updateInfoDelPhotobyVehicleid", vechinfo);
		}
		if(updateStr.equals("3")){
			update("VehicleManage.updateInfobyVehicleid", vechinfo);
		}
        update("VehicleManage.updateorgidFlag", vechinfo.getOrganization_id());

    }

    public void updatesubMulti(VehcileInfo vechinfo, String oldorgid,String updateStr)
            throws BusinessException {
    	if(updateStr.equals("1")){
    		update("VehicleManage.updatebyVehicleid", vechinfo);
    	}
		if(updateStr.equals("2")){
			update("VehicleManage.updateInfoDelPhotobyVehicleid", vechinfo);
		}
		if(updateStr.equals("3")){
			update("VehicleManage.updateInfobyVehicleid", vechinfo);
		}
        update("VehicleManage.updateorgidFlagtwo", oldorgid);
        update("VehicleManage.updateorgidFlag", vechinfo.getOrganization_id());
    }

    public void cancleMulti(VehcileInfo vechinfo, String oldorgid)
            throws BusinessException {
        update("VehicleManage.canclebyVehicleid", vechinfo);
        update("VehicleManage.updateorgidFlagtwo", oldorgid);
    }
    
    public void updateDriverInfo(VehcileInfo vechinfo,String OldLicense)
            throws BusinessException{
		update("VehicleManage.deleteDriver", OldLicense);
		update("VehicleManage.insertDriver", vechinfo);
    }
    
    public void updateTripInfo(VehcileInfo vechinfo)
            throws BusinessException{
    	int tripcount = (Integer)getObject("VehicleManage.getTripByVin", vechinfo);
    	String driverId= (String)getObject("VehicleManage.getDrivers", vechinfo);
		vechinfo.setDriver_id(driverId);
    	if(tripcount==0){
    		insert("VehicleManage.insertTrip", vechinfo);
    	}else
    		update("VehicleManage.updateTrip", vechinfo);
    }
    
    public  void deleteDriverInfo(VehcileInfo vechinfo,String OldLicense)
            throws BusinessException{
    	update("VehicleManage.deleteDriver", OldLicense);
    	update("VehicleManage.deleteTrip", vechinfo);
    }

}
