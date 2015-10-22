package com.neusoft.clw.yw.zd.oem.action;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

/**
 * 终端厂家管理DWR
 * @author JinPeng
 */
public class OemManageDWR extends BaseAction {
    private transient Service service;

    /**
     * 判断终端厂家编码唯一性
     * @param terminalCode
     * @return
     */
    public boolean checkOemCodeUnique(String oemCode) {
        boolean ret = false;

        try {
            int cnt = service.getCount("OemManage.getOemCount", oemCode.trim());
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("终端厂家编码查询DWR异常发生，异常原因：" + e.getMessage());
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
