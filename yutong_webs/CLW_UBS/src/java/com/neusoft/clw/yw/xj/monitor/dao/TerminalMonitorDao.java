package com.neusoft.clw.yw.xj.monitor.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.xj.monitor.ds.SendCommandInfo;

/**
 * 终端监控DAO
 * @author JinPeng
 */
public class TerminalMonitorDao extends ExtendSqlMapDao {
    /**
     * 下发查询终端参数命令
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List < SendCommandInfo > list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < SendCommandInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (SendCommandInfo sendCommandInfo : list) {
                o = getSqlMapClientTemplate().insert(
                        "TerminalMonitor.insertSendCommandInfo",
                        sendCommandInfo);
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
