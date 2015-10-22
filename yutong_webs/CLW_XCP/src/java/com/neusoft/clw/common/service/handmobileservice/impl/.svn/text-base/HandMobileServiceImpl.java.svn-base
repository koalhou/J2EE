package com.neusoft.clw.common.service.handmobileservice.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.handmobileservice.HandMobileService;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.infomanage.handmobilemanage.domain.HandMobileInfo;

public class HandMobileServiceImpl extends ServiceImpl implements
        HandMobileService {
    public void updateList(List < HandMobileInfo > list, String orgid)
            throws BusinessException {
        updateBatch("HandMobileManage.updateHandOrgid", list);
    }
    public void batchCancle(List < HandMobileInfo > list) throws BusinessException{
        updateBatch("HandMobileManage.canclebyVin", list);
    }
}
