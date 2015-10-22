package com.neusoft.clw.yw.xs.zonemanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;

public class ZoneManageDWR extends BaseAction {
    private transient Service service;

    // 省市信息列表
    private List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();

    public Map < String, String > showZoneXsInfo(String zone_parent_id) {
        Map < String, String > map = new HashMap < String, String >();
        Map < String, Object > mapPar = new HashMap < String, Object >(1);
        mapPar.put("zone_parent_id", SqlStringUtil.getNull(zone_parent_id));
        try {
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                map.put(zoneXsInfo.getZone_id(), zoneXsInfo.getZone_name());
            }
        } catch (BusinessException e) {
            log.error("省市信息查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return map;
    }

    // 地名唯一验证
    public boolean checkZone_name(String zone_name) {
        try {
            int num = service
                    .getCount("ZoneManage.getZoneNameCount", zone_name);
            if (num == 0) {
                return true;

            } else {
                return false;
            }

        } catch (BusinessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    // 地区编码唯一验证
    public boolean checkZone_code(String zone_name) {
        try {
            int num = service
                    .getCount("ZoneManage.getZoneCodeCount", zone_name);
            if (num == 0) {
                return true;

            } else {
                return false;
            }

        } catch (BusinessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setList(List < ZoneXsInfo > list) {
        this.list = list;
    }

    public List < ZoneXsInfo > getList() {
        return list;
    }

}
