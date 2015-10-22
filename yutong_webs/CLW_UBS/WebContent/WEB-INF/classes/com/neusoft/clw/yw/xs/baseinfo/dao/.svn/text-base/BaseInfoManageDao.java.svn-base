package com.neusoft.clw.yw.xs.baseinfo.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.xs.baseinfo.ds.BaseInfo;

/**
 * 基础信息管理Dao
 * @author JinPeng
 */
public class BaseInfoManageDao extends ExtendSqlMapDao {
    /**
     * 导入基础信息
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < BaseInfo > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < BaseInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (BaseInfo baseInfo : list) {
                o = getSqlMapClientTemplate().update(
                        "BaseInfoManage.mergeBaseInfos", baseInfo);
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
