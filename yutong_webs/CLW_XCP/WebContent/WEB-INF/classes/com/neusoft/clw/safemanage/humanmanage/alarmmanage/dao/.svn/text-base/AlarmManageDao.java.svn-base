package com.neusoft.clw.safemanage.humanmanage.alarmmanage.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.StuAlarm;

public class AlarmManageDao extends ExtendSqlMapDao {
    /**
     * 批量处理告警
     */
    public int updateBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {

        SqlMapClient sqlmapclient = this.getSqlMapClient();
        int o = 0;
        List < AlarmManage > list = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < AlarmManage >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (AlarmManage alarm : list) {
                o = getSqlMapClientTemplate().update(statment, alarm);
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

    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        int ret = 0;
        try {
            ret = getSqlMapClientTemplate().update(statment, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return ret;
    }

    public int updateStuBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {

        SqlMapClient sqlmapclient = this.getSqlMapClient();
        int o = 0;
        List < StuAlarm > list = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List < StuAlarm >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            for (StuAlarm alarm : list) {
                o = getSqlMapClientTemplate().update(statment, alarm);
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
