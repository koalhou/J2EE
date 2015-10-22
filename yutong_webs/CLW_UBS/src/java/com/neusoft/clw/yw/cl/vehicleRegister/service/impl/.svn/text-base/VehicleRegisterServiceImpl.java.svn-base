package com.neusoft.clw.yw.cl.vehicleRegister.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.VehicleRegisterInfo;
import com.neusoft.clw.yw.cl.vehicleRegister.service.VehicleRegisterService;

/**
 * 车辆注册service实现类
 * @author JinPeng
 */
public class VehicleRegisterServiceImpl extends ServiceImpl implements
        VehicleRegisterService {
    /** 创建车辆注册信息 **/
    public void insertVehicleRegister(VehicleRegisterInfo vehicleRegisterInfo)
            throws BusinessException {
        insert("", vehicleRegisterInfo);
    }

    /** 修改车辆注册信息 **/
    public void updateVehicleRegister(VehicleRegisterInfo vehicleRegisterInfo)
            throws BusinessException {
        update("", vehicleRegisterInfo);
    }

    /** 删除车辆注册信息 **/
    public void deleteVehicleRegister(VehicleRegisterInfo vehicleRegisterInfo)
            throws BusinessException {
        delete("", vehicleRegisterInfo);
    }

    /** 导入车辆注册信息 **/
    public Object importVehicleRegisterInfos(List < VehicleRegisterInfo > list)
            throws BusinessException {
        return getObject(VehicleRegisterServiceImpl.class, list);
    }
}
