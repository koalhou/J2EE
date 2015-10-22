package com.neusoft.clw.common.service.oilsetservice.impl;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.oilsetservice.OilSetService;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.sysmanage.sysset.oilset.domain.OilSet;

public class OilSetServiceImpl extends ServiceImpl implements OilSetService {
    public void insertList(OilSet oilset) throws BusinessException {
        String[] selectveh = oilset.getSelectveh();

        if (selectveh == null || selectveh.length <= 0) {
            return;
        }

        for (int i = 0; i < selectveh.length; i++) {
            oilset.setCheck_id(UUIDGenerator.getUUID());
            oilset.setVehicle_vin(selectveh[i]);

            try {
                dao.insert("OilSet.insertOilSetInfo", oilset);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

    }

}
