package com.neusoft.clw.yw.cl.carbase.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.cl.carbase.ds.CarBaseInfo;
import com.neusoft.clw.yw.cl.carbase.service.CarBaseService;

public class CarBaseServiceImpl extends ServiceImpl implements CarBaseService {
    // 导入车辆数据
    public Object importCarBaseInfos(List < CarBaseInfo > list)
            throws BusinessException {
        return getObject("", list);
    }

    // 修改车辆信息
    public void updateCarBaseInfo(CarBaseInfo carBaseInfo) throws BusinessException {
        update("", carBaseInfo);
    }
}
