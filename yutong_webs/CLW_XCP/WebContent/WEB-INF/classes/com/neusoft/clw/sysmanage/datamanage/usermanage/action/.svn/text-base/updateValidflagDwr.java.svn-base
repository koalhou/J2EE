package com.neusoft.clw.sysmanage.datamanage.usermanage.action;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;

public class updateValidflagDwr {

    private transient Service service;

    public int updatValidflag(String userid, String flag) {

        Map map = new HashMap();
        map.put("userID", userid);
        map.put("valideFlg", flag);

        try {
            int i = service.update("User.updateUserVALIDFLAG", map);
            return i;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }

    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
