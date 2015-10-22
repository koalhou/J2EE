package com.neusoft.clw.yw.qx.usermanage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.qx.usermanage.ds.UserDetail;

/**
 * 用户授权管理Dao
 * @author YuGang
 */
public class UserManageDao extends ExtendSqlMapDao {
    /**
     * 新增用户授权
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < UserDetail > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < UserDetail >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (UserDetail userDetail : list) {
                o = getSqlMapClientTemplate().insert(
                        "UserManage.insertUserRole", userDetail);
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
     * 删除用户授权
     */
    public int delete(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        int o = 0;
        List < UserDetail > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < UserDetail >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (UserDetail userDetail : list) {
                o = getSqlMapClientTemplate().delete(
                        "UserManage.deleteUserRole", userDetail);
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
