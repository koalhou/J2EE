package com.neusoft.clw.yw.cs.simflux.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.cs.simflux.ds.SimFluxInfo;

/**
 * SIM卡流量管理Dao
 * @author JinPeng
 */
public class SimFluxManageDao extends ExtendSqlMapDao {
    /**
     * 导入SIM卡流量数据
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < SimFluxInfo > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < SimFluxInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (SimFluxInfo simFluxInfo : list) {
                o = getSqlMapClientTemplate().update(
                        "SimFluxManage.mergeSimFluxInfos", simFluxInfo);
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
}
