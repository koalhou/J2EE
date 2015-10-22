package com.neusoft.clw.infomanage.handmobilemanage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.infomanage.handmobilemanage.domain.HandMobileInfo;

public class HandMobileManageDao extends ExtendSqlMapDao{
    /**
     * 车辆分配时批量更新ORGID
     */
    public int updateBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {

        SqlMapClient sqlmapclient = this.getSqlMapClient();
        int o = 0;
        List < HandMobileInfo > list = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < HandMobileInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (HandMobileInfo handmobileinfo : list) {
                o = getSqlMapClientTemplate().update(statment, handmobileinfo);
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
