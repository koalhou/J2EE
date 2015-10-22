package com.neusoft.clw.yw.cs.sim.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.cs.sim.ds.SimInfo;

/**
 * SIM卡管理service接口
 * @author JinPeng
 */
public interface SimManageService extends Service {
    // 导入SIM卡数据
    void importSimInfos(List < SimInfo > list) throws BusinessException;
    
    // 修改SIM卡信息
    void updateSimInfo(SimInfo simInfo) throws BusinessException;
}
