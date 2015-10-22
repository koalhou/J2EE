package com.neusoft.clw.yw.cs.sim.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.cs.sim.ds.SimInfo;
import com.neusoft.clw.yw.cs.sim.service.SimManageService;

/**
 * SIM卡管理service实现类
 * @author JinPeng
 */
public class SimManageServiceImpl extends ServiceImpl implements
        SimManageService {
    // 导入SIM卡信息
    public void importSimInfos(List < SimInfo > list) throws BusinessException {
        insert("", list);
    }

    // 修改SIM卡信息
    public void updateSimInfo(SimInfo simInfo) throws BusinessException {
        update("", simInfo);
    }
    
    
}
