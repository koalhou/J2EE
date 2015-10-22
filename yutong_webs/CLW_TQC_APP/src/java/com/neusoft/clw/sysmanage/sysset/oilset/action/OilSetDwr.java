package com.neusoft.clw.sysmanage.sysset.oilset.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.util.Log;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;

public class OilSetDwr {

    /** service共通类 */
    private transient Service service;

    public List < VehcileInfo > OilValidateInit(String entid, String ockid) {

        Map map = new HashMap();
        map.put("organization_id", entid);
        map.put("CHECK_TIME_CODE", ockid);

        try {
            List < VehcileInfo > list = service.getObjects(
                    "OilSet.getVehicleLeft", map);

            return list;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            Log.info(e.getMessage());
            return null;
        }

    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

}
