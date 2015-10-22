package com.neusoft.clw.common.service.stewardManageService.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.stewardManageService.StewardManageService;
import com.neusoft.clw.infomanage.stewardmanage.domain.StewardInfo;

public class StewardManageServiceImpl extends ServiceImpl implements
        StewardManageService {
    public void importStewardInfos(List < StewardInfo > list)
            throws BusinessException {
        insert(StewardInfo.class, list);
    }
}
