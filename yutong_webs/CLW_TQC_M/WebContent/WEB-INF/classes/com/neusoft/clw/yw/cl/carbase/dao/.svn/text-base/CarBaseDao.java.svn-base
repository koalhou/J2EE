package com.neusoft.clw.yw.cl.carbase.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.cl.carbase.ds.CarBaseInfo;

public class CarBaseDao extends ExtendSqlMapDao {
    /**
     * 导入车辆数据
     */
    public Object getObject(String statment, Object obj)
            throws DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < CarBaseInfo > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < CarBaseInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            for (CarBaseInfo carbaseInfo : list) {
                int i = 0;
                if (carbaseInfo.getVehicle_ln() == null
                        || "".equals(carbaseInfo.getVehicle_ln())) {
                    i = ((Integer) getSqlMapClientTemplate().queryForObject(
                            "CarBase.getVinCount", carbaseInfo.getVehicle_ln()))
                            .intValue();

                    if (i > 0) {
                        throw new DataAccessException("车辆VIN或车辆牌号已经在库中存在！");
                    }
                } else {
                    i = ((Integer) getSqlMapClientTemplate().queryForObject(
                            "CarBase.countLnVin", carbaseInfo)).intValue();
                    // System.out.println(carbaseInfo.getVehicle_ln());
                    // System.out.println(carbaseInfo.getVehicle_vin());
                    // System.out.println(i);
                    if (i > 0) {
                        throw new DataAccessException("车辆VIN或车辆牌号已经在库中存在！");
                    }
                }
                getSqlMapClientTemplate().insert("CarBase.insertCarBase",
                        carbaseInfo);
            }
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
     * 更新车辆信息
     */
    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        CarBaseInfo carBaseInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof CarBaseInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be CarBaseInfo instance");
            } else {
                // 获取终端bean
                carBaseInfo = (CarBaseInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            
            // 修改车辆信息
            int ret = getSqlMapClientTemplate().update(
                    "CarBase.updateCarBaseInfo", carBaseInfo);
            
            // 同步修改车辆注册信息
            getSqlMapClientTemplate().update("CarBase.updateRegistedCarBaseInfo",
                    carBaseInfo);
            
            // 同步修改行业应用信息
            getSqlMapClientTemplate().update("CarBase.updateRegistedCarBizInfo",
                    carBaseInfo);
            
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
