/*******************************************************************************
 * @(#)HandheldDeviceDao.java 2012-3-13
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.cl.handheldDevice.dao;

import java.sql.SQLException;

import org.springframework.dao.DataIntegrityViolationException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.cl.handheldDevice.ds.HandheldDeviceInfo;

/**
 * 手持设备管理DAO
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-13 上午09:43:08
 */
public class HandheldDeviceDao extends ExtendSqlMapDao{
    /**
     * 创建手持设备注册信息
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        HandheldDeviceInfo handheldDeviceInfo = null;
        Object o = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof HandheldDeviceInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be HandheldDeviceInfo instance");
            } else {
                // 获取车辆注册信息bean
                handheldDeviceInfo = (HandheldDeviceInfo) obj;
            }

            // 开启批处理
            sqlmapclient.startBatch();

            getSqlMapClientTemplate().insert("HandheldDevice.insertVehicleTable", handheldDeviceInfo);
            getSqlMapClientTemplate().insert("HandheldDevice.insertTerminalTable", handheldDeviceInfo);
            getSqlMapClientTemplate().insert("HandheldDevice.insertSimTable", handheldDeviceInfo);
            
            // 批处理执行
            sqlmapclient.executeBatch();
            return o;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 更新手持设备注册信息
     */
    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        HandheldDeviceInfo handheldDeviceInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof HandheldDeviceInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be HandheldDeviceInfo instance");
            } else {
                // 获取车辆注册信息bean
                handheldDeviceInfo = (HandheldDeviceInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            // 更新车辆信息
            int ret = getSqlMapClientTemplate().update(
                    "HandheldDevice.updateVehicleInfo",
                    handheldDeviceInfo);
            
            ret = getSqlMapClientTemplate().update(
                    "HandheldDevice.updateTerminalInfo",
                    handheldDeviceInfo);

            // 批处理执行
            sqlmapclient.executeBatch();

            return ret;
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除手持设备注册信息
     */
    public int delete(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        HandheldDeviceInfo handheldDeviceInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof HandheldDeviceInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be HandheldDeviceInfo instance");
            } else {
                // 获取车辆注册信息bean
                handheldDeviceInfo = (HandheldDeviceInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();


            int ret = getSqlMapClientTemplate().update(
                    "HandheldDevice.delVehicleInfo",
                    handheldDeviceInfo);
            
            getSqlMapClientTemplate().update(
                    "HandheldDevice.delTerminalInfo",
                    handheldDeviceInfo);
            
            getSqlMapClientTemplate().update(
                    "HandheldDevice.delSimInfo",
                    handheldDeviceInfo);
            
            // 批处理执行
            sqlmapclient.executeBatch();

            return ret;
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
