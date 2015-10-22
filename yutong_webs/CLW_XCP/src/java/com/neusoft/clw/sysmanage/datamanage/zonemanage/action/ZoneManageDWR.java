package com.neusoft.clw.sysmanage.datamanage.zonemanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.sysmanage.datamanage.zonemanage.domain.ZoneXsInfo;

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

    // add by jinp begin 
    // 省市县信息列表
    public List < ZoneXsInfo > getGeographyInfoByPid(String parentId) {
        List < ZoneXsInfo > ret = new ArrayList < ZoneXsInfo >();
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("parentId", parentId);
            ret = service.getObjects("ZoneManage.getGeographyList", map);
        } catch (BusinessException e) {
            log.error("省市信息查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return ret;
    }
    // add by jinp end
    
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
