package com.neusoft.clw.yw.cl.handheldDevice.action;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

/**
 * 手持设备管理DWR
 * @author JinPeng
 */
public class HandheldDeviceDWR extends BaseAction {
    private transient Service service;

    /**
     * 判断手机IMEI号唯一性
     * @param simNumber
     * @return
     */
    public boolean checkCellphoneImeiUnique(String imeiNumber) {
        boolean ret = false;
        try {
            int cnt = service.getCount("HandheldDevice.getVehicleCount", imeiNumber
                    .trim());
            if (cnt == 0) {
                ret = false;
            } else {
                ret = true;
            }
        } catch (BusinessException e) {
            log.error("手机IMEI号查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return ret;
    }

    /**
     * 判断手机号唯一性
     * @param cellNumber
     * @return
     */
    public boolean checkCellNumberUnique(String cellNumber) {
        boolean ret = false;
        try {
            int cnt = service.getCount("HandheldDevice.getSimCount", cellNumber
                    .trim());
            if (cnt == 0) {
                ret = false;
            } else {
                ret = true;
            }
        } catch (BusinessException e) {
            log.error("手机号查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return ret;
    }
    
    /**
     * 判断设备编号唯一性
     * @param cellNumber
     * @return
     */
    public boolean checkDeviceNumberUnique(String deviceNumber) {
        boolean ret = false;
        try {
            int cnt = service.getCount("HandheldDevice.getTerminalCount", deviceNumber
                    .trim());
            if (cnt == 0) {
                ret = false;
            } else {
                ret = true;
            }
        } catch (BusinessException e) {
            log.error("设备编号查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return ret;
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
