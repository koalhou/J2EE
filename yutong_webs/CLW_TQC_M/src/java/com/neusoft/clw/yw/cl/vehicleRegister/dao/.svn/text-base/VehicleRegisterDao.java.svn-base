package com.neusoft.clw.yw.cl.vehicleRegister.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.BizTypeInfo;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.VehicleRegisterInfo;

/**
 * 车辆注册dao
 * @author JinPeng
 */
public class VehicleRegisterDao extends ExtendSqlMapDao {

    /**
     * 创建车辆注册信息
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        VehicleRegisterInfo vehicleRegisterInfo = null;
        Object o = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof VehicleRegisterInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be VehicleRegisterInfo instance");
            } else {
                // 获取车辆注册信息bean
                vehicleRegisterInfo = (VehicleRegisterInfo) obj;
            }

            // 开启批处理
            sqlmapclient.startBatch();

            // 更新终端信息
            getSqlMapClientTemplate().update(
                    "VehicleRegister.updateTerminalInfo", vehicleRegisterInfo);
            // 更新车辆信息
            getSqlMapClientTemplate().update(
                    "VehicleRegister.updateVehicleInfo", vehicleRegisterInfo);
            /**
            // 更新企业信息
            getSqlMapClientTemplate().update(
                    "VehicleRegister.updateEnterpriseInfo",
                    vehicleRegisterInfo.getEntipriseId());
**/
            for (BizTypeInfo bizTypeInfo : vehicleRegisterInfo.getBizTypeList()) {
                if (bizTypeInfo.getBizId() == ""
                        && bizTypeInfo.getTerminalCode() == "") {
                    continue;
                } else {
                    if (bizTypeInfo.isChoiceFlag()) {
                        // 行业应用被选择时，insert行业应用信息
                        o = getSqlMapClientTemplate().insert(
                                "VehicleRegister.insertBizTypeInfos",
                                bizTypeInfo);
                    }
                }
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
     * 更新车辆注册信息
     */
    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        VehicleRegisterInfo vehicleRegisterInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof VehicleRegisterInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be VehicleRegisterInfo instance");
            } else {
                // 获取车辆注册信息bean
                vehicleRegisterInfo = (VehicleRegisterInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            // 更新车辆信息
            int ret = getSqlMapClientTemplate().update(
                    "VehicleRegister.updateVehicleAttribute",
                    vehicleRegisterInfo);

            if (!vehicleRegisterInfo.getTerminalOldId().equals(
                    vehicleRegisterInfo.getTerminalId())) {
                // 更新原有终端信息
                getSqlMapClientTemplate().update(
                        "VehicleRegister.updateTerminalRegister",
                        vehicleRegisterInfo.getTerminalOldId());
            }
            
            if (vehicleRegisterInfo.getTerminalOldId().equals(
                    vehicleRegisterInfo.getTerminalId())
                    && vehicleRegisterInfo.getOldSimCardNumber().equals(
                            vehicleRegisterInfo.getSimCardNumber())) {
                // 如三者关联未发生变化时，不更新注册人信息
                getSqlMapClientTemplate().update(
                        "VehicleRegister.updateTerminalInfo2", vehicleRegisterInfo);
            } else {
                // 如三者关联发生变化时，更新注册人信息
                getSqlMapClientTemplate().update(
                        "VehicleRegister.reUpdateTerminalInfo", vehicleRegisterInfo);
            }
            
            // 删除行业应用关联信息
            getSqlMapClientTemplate().delete(
                    "VehicleRegister.deleteBizTypeInfos",
                    vehicleRegisterInfo.getTerminalOldId());

            for (BizTypeInfo bizTypeInfo : vehicleRegisterInfo.getBizTypeList()) {
                if (bizTypeInfo.getBizId() == ""
                        && bizTypeInfo.getTerminalCode() == "") {
                    continue;
                } else {
                    if (bizTypeInfo.isChoiceFlag()) {
                        // 行业应用被选择时，insert行业应用信息
                        getSqlMapClientTemplate().insert(
                                "VehicleRegister.insertBizTypeInfos",
                                bizTypeInfo);
                    }
                }
            }

            // 批处理执行
            sqlmapclient.executeBatch();

            sqlmapclient.startBatch();
            // 判断车辆是否换企业
            if (!(vehicleRegisterInfo.getOldEnterpriseId()
                    .equals(vehicleRegisterInfo.getEntipriseId()))) {
                int carCount = (Integer) getSqlMapClientTemplate()
                        .queryForObject("VehicleRegister.getCarCount",
                                vehicleRegisterInfo.getOldEnterpriseId());
                if (0 == carCount) {
                    // 判断原企业是否关联车辆
                    getSqlMapClientTemplate().update(
                            "VehicleRegister.clearEnterpriseFlag",
                            vehicleRegisterInfo.getOldEnterpriseId());
                }
                // 更新新企业状态
                /**
                getSqlMapClientTemplate().update(
                        "VehicleRegister.updateEnterpriseInfo",
                        vehicleRegisterInfo.getEntipriseId());**/
            }
            sqlmapclient.executeBatch();

            return ret;
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除车辆注册信息
     */
    public int delete(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        VehicleRegisterInfo vehicleRegisterInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof VehicleRegisterInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be VehicleRegisterInfo instance");
            } else {
                // 获取车辆注册信息bean
                vehicleRegisterInfo = (VehicleRegisterInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            // 更新终端信息
            getSqlMapClientTemplate().update(
                    "VehicleRegister.updateTerminalRegister",
                    vehicleRegisterInfo.getTerminalId());

            // 更新车辆信息
            getSqlMapClientTemplate().update(
                    "VehicleRegister.updateVehicleRegister",
                    vehicleRegisterInfo.getVehicleId());

            // 删除行业应用关联信息
            int ret = getSqlMapClientTemplate().delete(
                    "VehicleRegister.deleteBizTypeInfos",
                    vehicleRegisterInfo.getTerminalId());
            // 批处理执行
            sqlmapclient.executeBatch();

            sqlmapclient.startBatch();
            int carCount = (Integer) getSqlMapClientTemplate().queryForObject(
                    "VehicleRegister.getCarCount",
                    vehicleRegisterInfo.getOldEnterpriseId());
            if (0 == carCount) {
                // 判断原企业是否关联车辆
                getSqlMapClientTemplate().update(
                        "VehicleRegister.clearEnterpriseFlag",
                        vehicleRegisterInfo.getOldEnterpriseId());
            }
            sqlmapclient.executeBatch();

            return ret;
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 核对是否包含VIN与SIM卡、终端信息
     * @param list
     * @param vehicleRegisterInfo
     * @return
     */
    private boolean checkVehicleAndSimInfos(List < VehicleRegisterInfo > list,
            VehicleRegisterInfo vehicleRegisterInfo) {
        boolean vehicleFlag = false;
        boolean simFlag = false;
        boolean terminalFlag = false;
        for (VehicleRegisterInfo tmp : list) {
            // 检查VIN号是否存在
            if (tmp.getVehicleVin() != null
                    && tmp.getVehicleVin().equals(
                            vehicleRegisterInfo.getVehicleVin())) {
                vehicleFlag = true;
                break;
            }
        }
        for (VehicleRegisterInfo tmp : list) {
            // 检查SIM卡号是否存在
            if (tmp.getSimCardNumber() != null
                    && tmp.getSimCardNumber().equals(
                            vehicleRegisterInfo.getSimCardNumber())) {
                simFlag = true;
                break;
            }
        }
        for (VehicleRegisterInfo tmp : list) {
            // 检查终端是否存在
            if (tmp.getTerminalCode() != null
                    && tmp.getTerminalCode().equals(
                            vehicleRegisterInfo.getTerminalCode())) {
                terminalFlag = true;
                break;
            }
        }
        return vehicleFlag && simFlag && terminalFlag;
    }

    /**
     * 导入车辆注册信息
     */
    public Object getObject(Class clazz, Object obj) throws DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        List < VehicleRegisterInfo > list = null;
        Object o = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                // 获取车辆注册信息list
                list = (List < VehicleRegisterInfo >) obj;
            }

            // 开启批处理
            sqlmapclient.startBatch();

            List < VehicleRegisterInfo > vehicleList = getSqlMapClientTemplate()
                    .queryForList("VehicleRegister.getVehicleSimInfos", null);

            String vehicleVins = "";

            String enterpriseId = "";

            for (VehicleRegisterInfo vehicleRegisterInfo : list) {

                if (enterpriseId.length() == 0
                        && vehicleRegisterInfo.getEntipriseId() != null
                        && vehicleRegisterInfo.getEntipriseId().length() > 0) {
                    enterpriseId = vehicleRegisterInfo.getEntipriseId();
                }

                if (checkVehicleAndSimInfos(vehicleList, vehicleRegisterInfo)) {
                    // 更新车辆信息
                    getSqlMapClientTemplate().update(
                            "VehicleRegister.importVehicleInfo",
                            vehicleRegisterInfo);
                    
                    // 更新终端信息
                    getSqlMapClientTemplate().update(
                            "VehicleRegister.importTerminalInfo",
                            vehicleRegisterInfo);

//                    if (0 == i) {
//                        if (vehicleVins.length() > 0) {
//                            vehicleVins = vehicleVins + ","
//                                    + vehicleRegisterInfo.getVehicleVin();
//                        } else {
//                            vehicleVins = vehicleRegisterInfo.getVehicleVin();
//                        }
//                    }
                    
                    // 删除行业应用关联信息
                    getSqlMapClientTemplate().delete(
                            "VehicleRegister.importBizTypeInfos",
                            vehicleRegisterInfo.getTerminalCode());

                    for (BizTypeInfo bizTypeInfo : vehicleRegisterInfo
                            .getBizTypeList()) {
                        if (bizTypeInfo.getBizId() == ""
                                && bizTypeInfo.getTerminalCode() == "") {
                            continue;
                        } else {
                            if (bizTypeInfo.isChoiceFlag()) {
                                // 行业应用被选择时，insert行业应用信息
                                o = getSqlMapClientTemplate().insert(
                                        "VehicleRegister.insertBizTypeInfos",
                                        bizTypeInfo);
                            }
                        }
                    }
                } else {
                    if (vehicleVins.length() > 0) {
                        vehicleVins = vehicleVins + ","
                                + vehicleRegisterInfo.getVehicleVin();
                    } else {
                        vehicleVins = vehicleRegisterInfo.getVehicleVin();
                    }
                }
            }

            /**
            if (vehicleVins.length() == 0) {
                // 更新新企业状态
                getSqlMapClientTemplate().update(
                        "VehicleRegister.updateEnterpriseInfo", enterpriseId);
            }**/

            // 批处理执行
            sqlmapclient.executeBatch();

            if (vehicleVins.length() > 0) {
                return vehicleVins;
            } else {
                return "import_done";
            }

        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

}
