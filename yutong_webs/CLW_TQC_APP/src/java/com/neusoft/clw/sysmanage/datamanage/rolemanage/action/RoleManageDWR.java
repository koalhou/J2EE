package com.neusoft.clw.sysmanage.datamanage.rolemanage.action;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

public class RoleManageDWR extends BaseAction {
    private transient Service service;
    
    public boolean checkRoleNameUnique(String RoleID, String RoleName, String entiID){
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("RoleID", RoleID);
        map.put("RoleName", RoleName);
        map.put("entiID", entiID);
        try {
            int i = service.getCount("Role.getRoleNameSameCount", map);
            if (i > 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
