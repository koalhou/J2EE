package com.neusoft.clw.sysmanage.datamanage.routemanage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;

public class StationDao extends ExtendSqlMapDao {
    /**
     * 批量更新
     */
    public int updateBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {

        SqlMapClient sqlmapclient = this.getSqlMapClient();
        int o = 0;
        List < Site > list = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < Site >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (Site siteInfo : list) {
                o = getSqlMapClientTemplate().update(statment, siteInfo);
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

    public int delete(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        int ret = 0;
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        List < Site > list = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < Site >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (Site siteInfo : list) {
            	ret = getSqlMapClientTemplate().delete(statment, siteInfo);
            }
            // 批处理执行
            sqlmapclient.executeBatch();
            return ret;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }

    	}
    }
