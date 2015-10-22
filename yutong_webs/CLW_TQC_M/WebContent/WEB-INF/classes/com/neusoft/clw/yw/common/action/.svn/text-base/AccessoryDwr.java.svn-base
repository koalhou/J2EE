package com.neusoft.clw.yw.common.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.common.ds.AccessoryInfo;

public class AccessoryDwr extends BaseAction {
    private transient Service service;
    
    /**
     * 获取已上传附件信息
     * @param noticeId
     * @return
     */
    public Map getAccessoryInfos(String noticeId, String accessoryIds) {
        List data = new ArrayList();
        List ret = null;
        Map<String, String> idsMap = new HashMap<String, String>();
        
        if(accessoryIds != null && accessoryIds.length() > 0) {
            String[] accessoryArray = accessoryIds.split(",");
            for(int i = 0 ; i < accessoryArray.length; i++) {
                if(accessoryArray[i].length() > 0) {
                    idsMap.put(accessoryArray[i], "gotIt");
                }
            }
        }
        
        // 获得总记录数
        try {
            if(null != noticeId && noticeId.length() > 0) {
                ret = service.getObjects("CommonBaseInfo.getAccessoriesByID", noticeId);
            } 
            
            for(int i = 0; i < ret.size(); i++) {
                AccessoryInfo tmp = (AccessoryInfo) ret.get(i);
                if("gotIt".equals(idsMap.get(tmp.getAccessoryId()))) {
                    continue;
                } else {
                    data.add(tmp);
                }
            }
        } catch (Exception e) {
            ;
        } finally {
            ;
        }

        Map resultMap = new HashMap < String, Object >();
        resultMap.put("data", data);
        return resultMap;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
