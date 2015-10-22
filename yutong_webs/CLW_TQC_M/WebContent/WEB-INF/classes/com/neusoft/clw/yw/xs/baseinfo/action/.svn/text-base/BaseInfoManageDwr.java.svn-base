package com.neusoft.clw.yw.xs.baseinfo.action;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

/**
 * 基础信息管理DWR
 * @author JinPeng
 */
public class BaseInfoManageDwr extends BaseAction {
    private transient Service service;

    /**
     * 检查编码ID唯一性
     * @param codeId 编码ID
     * @return
     */
    public boolean checkCodeIdUnique(String codeId, String codeType) {
        boolean ret = false;

        Map < String, String > map = new HashMap < String, String >();
        map.put("codeId", codeId.trim());
        map.put("codeType", codeType);

        try {
            int cnt = service.getCount("BaseInfoManage.getCodeCount", map);
            if (cnt == 0) {
                ret = false;
            } else {
                ret = true;
            }
        } catch (BusinessException e) {
            log.error("基础信息查询DWR异常发生，异常原因：" + e.getMessage());
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
