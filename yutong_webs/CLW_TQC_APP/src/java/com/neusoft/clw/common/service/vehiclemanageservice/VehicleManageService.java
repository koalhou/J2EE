package com.neusoft.clw.common.service.vehiclemanageservice;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;

public interface VehicleManageService extends Service {
    void updateList(List < VehcileInfo > list, String orgid)
            throws BusinessException;

    void updateaddMulti(VehcileInfo vechinfo,String updateStr) throws BusinessException;

    void updatesubMulti(VehcileInfo vechinfo, String oldorgid,String updateStr)
            throws BusinessException;

    void cancleMulti(VehcileInfo vechinfo, String oldorgid)
            throws BusinessException;
}
