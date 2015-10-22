package com.neusoft.clw.common.service.handmobileservice;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.infomanage.handmobilemanage.domain.HandMobileInfo;


public interface HandMobileService extends Service {
    void updateList(List < HandMobileInfo > list, String orgid)
    throws BusinessException;
    void batchCancle(List < HandMobileInfo > list)throws BusinessException;
}
