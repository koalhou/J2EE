package com.neusoft.clw.yw.xs.poimanage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.xs.poimanage.ds.PoiInfo;

/**
 * 服务点管理dao
 * @author JinPeng
 */
public class PoiManageDao extends ExtendSqlMapDao {
    /**
     * 导入服务点信息
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < PoiInfo > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < PoiInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (PoiInfo poiInfo : list) {
                o = getSqlMapClientTemplate().update("PoiManage.mergePoiInfos",
                        poiInfo);
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
