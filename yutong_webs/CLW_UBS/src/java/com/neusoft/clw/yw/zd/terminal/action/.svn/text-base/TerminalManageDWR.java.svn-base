package com.neusoft.clw.yw.zd.terminal.action;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

/**
 * 终端管理DWR
 * @author JinPeng
 */
public class TerminalManageDWR extends BaseAction {
    private transient Service service;

    /**
     * 判断终端号唯一性
     * @param terminalCode
     * @return
     */
    public boolean checkTmCodeUnique(String terminalCode) {
        boolean ret = false;

        try {
            int cnt = service.getCount("TerminalManage.getTmCount",
                    terminalCode.trim());
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("终端号查询DWR异常发生，异常原因：" + e.getMessage());
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
