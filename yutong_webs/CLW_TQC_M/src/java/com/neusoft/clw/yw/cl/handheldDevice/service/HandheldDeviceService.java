package com.neusoft.clw.yw.cl.handheldDevice.service;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.cl.handheldDevice.ds.HandheldDeviceInfo;

/**
 * 手持设备注册service接口
 * @author JinPeng
 */
public interface HandheldDeviceService extends Service {
    /** 创建手持设备注册信息 **/
    void insertHandheldDevice(HandheldDeviceInfo handheldDeviceInfo)
            throws BusinessException;

    /** 修改手持设备注册信息 **/
    void updateHandheldDevice(HandheldDeviceInfo handheldDeviceInfo)
            throws BusinessException;

    /** 删除手持设备注册信息 **/
    void deleteHandheldDevice(HandheldDeviceInfo handheldDeviceInfo)
            throws BusinessException;
}
