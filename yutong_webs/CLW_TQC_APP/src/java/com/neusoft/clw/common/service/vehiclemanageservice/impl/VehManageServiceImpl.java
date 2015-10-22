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
    		update("VehicleManage.updateDriverName", vechinfo);
    		update("VehicleManage.updateOilbox", vechinfo);
    		
    	}
		if(updateStr.equals("2")){
			update("VehicleManage.updateInfoDelPhotobyVehicleid", vechinfo);
			update("VehicleManage.updateDriverName", vechinfo);
			update("VehicleManage.updateOilbox", vechinfo);
		}
		if(updateStr.equals("3")){
			update("VehicleManage.updateInfobyVehicleid", vechinfo);
			int count=0;
			count=getCount("VehicleManage.getCountVehicleAndDriver",vechinfo);			
			if(count>0){
				update("VehicleManage.updateDriverName", vechinfo);//如果有的话，修改
			}
			else{
				insert("VehicleManage.insertVehicleAndDriverInfo", vechinfo);//如果关系表中没有改车辆的数据，直接插入添加
			}
			
			update("VehicleManage.updateOilbox", vechinfo);
			
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
			int count=0;
			count=getCount("VehicleManage.getCountVehicleAndDriver",vechinfo);			
			if(count>0){
				update("VehicleManage.updateDriverName", vechinfo);//如果有的话，修改
			}
			else{
				insert("VehicleManage.insertVehicleAndDriverInfo", vechinfo);//如果关系表中没有改车辆的数据，直接插入添加
			}
			
			update("VehicleManage.updateOilbox", vechinfo);
		}
        update("VehicleManage.updateorgidFlagtwo", oldorgid);
        update("VehicleManage.updateorgidFlag", vechinfo.getOrganization_id());
    }

    public void cancleMulti(VehcileInfo vechinfo, String oldorgid)
            throws BusinessException {
        update("VehicleManage.canclebyVehicleid", vechinfo);
        update("VehicleManage.updateorgidFlagtwo", oldorgid);
    }

}
