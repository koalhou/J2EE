package com.neusoft.clw.common.service.driverManageService;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;

public interface DriverManageService extends Service {
    // 导入驾驶员信息
    void importDriverInfos(List < DriverInfo > list) throws BusinessException;
}
