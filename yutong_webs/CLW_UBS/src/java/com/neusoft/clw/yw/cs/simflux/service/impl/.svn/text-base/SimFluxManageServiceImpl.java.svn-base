package com.neusoft.clw.yw.cs.simflux.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.cs.simflux.ds.SimFluxInfo;
import com.neusoft.clw.yw.cs.simflux.service.SimFluxManageService;

/**
 * SIM卡流量管理service实现类
 * @author JinPeng
 */
public class SimFluxManageServiceImpl extends ServiceImpl implements
        SimFluxManageService {
    // 导入SIM卡流量信息
    public void importSimFluxInfos(List < SimFluxInfo > list)
            throws BusinessException {
        insert("", list);
    }

}
