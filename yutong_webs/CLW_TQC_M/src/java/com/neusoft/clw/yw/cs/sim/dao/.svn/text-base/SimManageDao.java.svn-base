package com.neusoft.clw.yw.cs.sim.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.cs.sim.ds.SimInfo;

/**
 * SIM卡管理Dao
 * @author JinPeng
 */
public class SimManageDao extends ExtendSqlMapDao {
    /**
     * 导入SIM卡数据
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < SimInfo > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < SimInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (SimInfo simInfo : list) {
                o = getSqlMapClientTemplate().update("SimManage.mergeSimInfos",
                        simInfo);
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
     * 更新SIM卡信息
     */
    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SimInfo simInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof SimInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be SimInfo instance");
            } else {
                // 获取终端bean
                simInfo = (SimInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            
            // 修改终端信息
            int ret = getSqlMapClientTemplate().update(
                    "SimManage.updateSimInfo", simInfo);
            
            // 同步修改终端注册信息
            getSqlMapClientTemplate().update("SimManage.updateRegistedSimInfo",
                    simInfo);
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
