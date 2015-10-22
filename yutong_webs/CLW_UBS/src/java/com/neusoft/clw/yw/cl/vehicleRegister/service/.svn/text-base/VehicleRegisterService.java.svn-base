package com.neusoft.clw.yw.cl.vehicleRegister.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.VehicleRegisterInfo;

/**
 * 车辆注册service接口
 * @author JinPeng
 */
public interface VehicleRegisterService extends Service {
    /** 创建车辆注册信息 **/
    void insertVehicleRegister(VehicleRegisterInfo vehicleRegisterInfo)
            throws BusinessException;

    /** 修改车辆注册信息 **/
    void updateVehicleRegister(VehicleRegisterInfo vehicleRegisterInfo)
            throws BusinessException;

    /** 删除车辆注册信息 **/
    void deleteVehicleRegister(VehicleRegisterInfo vehicleRegisterInfo)
            throws BusinessException;

    /** 导入车辆注册信息 **/
    Object importVehicleRegisterInfos(List < VehicleRegisterInfo > list)
            throws BusinessException;
}
