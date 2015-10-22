/*******************************************************************************
 * @(#)HandheldDeviceServiceImpl.java 2012-3-13
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.cl.handheldDevice.service.impl;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.cl.handheldDevice.ds.HandheldDeviceInfo;
import com.neusoft.clw.yw.cl.handheldDevice.service.HandheldDeviceService;

/**
 * 手持设备service实现类
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-13 上午09:32:36
 */
public class HandheldDeviceServiceImpl extends ServiceImpl implements
        HandheldDeviceService {
    /*
     * (non-Javadoc)
     * @seecom.neusoft.clw.yw.cl.handheldDevice.service.HandheldDeviceService#
     * insertHandheldDevice
     * (com.neusoft.clw.yw.cl.handheldDevice.ds.HandheldDeviceInfo)
     */
    public void insertHandheldDevice(HandheldDeviceInfo handheldDeviceInfo)
            throws BusinessException {
        insert("", handheldDeviceInfo);
    }

    /*
     * (non-Javadoc)
     * @seecom.neusoft.clw.yw.cl.handheldDevice.service.HandheldDeviceService#
     * updateHandheldDevice
     * (com.neusoft.clw.yw.cl.handheldDevice.ds.HandheldDeviceInfo)
     */
    public void updateHandheldDevice(HandheldDeviceInfo handheldDeviceInfo)
            throws BusinessException {
        update("", handheldDeviceInfo);
    }

    /*
     * (non-Javadoc)
     * @seecom.neusoft.clw.yw.cl.handheldDevice.service.HandheldDeviceService#
     * deleteHandheldDevice
     * (com.neusoft.clw.yw.cl.handheldDevice.ds.HandheldDeviceInfo)
     */
    public void deleteHandheldDevice(HandheldDeviceInfo handheldDeviceInfo)
            throws BusinessException {
        delete("", handheldDeviceInfo);
    }

}
