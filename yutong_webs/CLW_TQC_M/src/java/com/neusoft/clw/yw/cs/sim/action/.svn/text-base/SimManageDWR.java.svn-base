package com.neusoft.clw.yw.cs.sim.action;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

/**
 * SIM卡管理DWR
 * @author JinPeng
 */
public class SimManageDWR extends BaseAction {
    private transient Service service;

    /**
     * 判断SIM卡号唯一性
     * @param simNumber
     * @return
     */
    public boolean checkSimNumberUnique(String simNumber) {
        boolean ret = false;
        try {
            int cnt = service.getCount("SimManage.getSimCount", simNumber
                    .trim());
            if (cnt == 0) {
                ret = false;
            } else {
                ret = true;
            }
        } catch (BusinessException e) {
            log.error("SIM卡号查询DWR异常发生，异常原因：" + e.getMessage());
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
            int cnt = service.getCount("SimManage.getCellCount", cellNumber
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
     * 判断SIM卡号唯一性（SIM卡流量管理）
     * @param simNumber
     * @return
     */
    public boolean checkSimFluxUnique(String simNumber) {
        boolean ret = false;
        try {
            int cnt = service.getCount("SimFluxManage.getSimFluxCount",
                    simNumber.trim());
            if (cnt == 0) {
                ret = false;
            } else {
                ret = true;
            }
        } catch (BusinessException e) {
            log.error("SIM卡号(SIM卡流量管理)查询DWR异常发生，异常原因：" + e.getMessage());
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
