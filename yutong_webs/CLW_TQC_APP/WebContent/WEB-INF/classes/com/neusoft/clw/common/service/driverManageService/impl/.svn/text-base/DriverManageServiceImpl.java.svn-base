package com.neusoft.clw.common.service.driverManageService.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.driverManageService.DriverManageService;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;

public class DriverManageServiceImpl extends ServiceImpl implements
        DriverManageService {
    public void importDriverInfos(List < DriverInfo > list)
            throws BusinessException {
        insert(DriverInfo.class, list);
    }
}
