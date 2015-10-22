package com.neusoft.clw.yw.xs.poimanage.action;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

/**
 * 服务点信息DWR
 * @author JinPeng
 */
public class PoiManageDwr extends BaseAction {
    private transient Service service;

    /**
     * 检查网点代码唯一性
     * @param codeId 网点代码
     * @return
     */
    public boolean checkPoiCodeUnique(String poiCode) {
        boolean ret = false;

        Map < String, String > map = new HashMap < String, String >();
        map.put("poiCode", poiCode.trim());

        try {
            int cnt = service.getCount("PoiManage.getPoiCount", map);
            if (cnt == 0) {
                ret = false;
            } else {
                ret = true;
            }
        } catch (BusinessException e) {
            log.error("网点代码查询DWR异常发生，异常原因：" + e.getMessage());
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
