package com.neusoft.clw.yw.cl.carbase.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.cl.carbase.ds.CarBaseInfo;

public interface CarBaseService extends Service {
    // 导入车辆数据
    Object importCarBaseInfos(List < CarBaseInfo > list)
            throws BusinessException;
    
    // 修改车辆信息
    void updateCarBaseInfo(CarBaseInfo carBaseInfo) throws BusinessException;
}
