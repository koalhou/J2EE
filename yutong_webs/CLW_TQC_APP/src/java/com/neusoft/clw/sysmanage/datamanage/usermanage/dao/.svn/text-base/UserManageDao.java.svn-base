package com.neusoft.clw.sysmanage.datamanage.usermanage.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;

public class UserManageDao extends ExtendSqlMapDao {

    public Object batchUserAndRoleupdateList2(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        // TODO Auto-generated method stub

        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List list = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                list = (List) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            for (int i = 0; i < list.size(); i++) {
                // list.get(i);
                o = getSqlMapClientTemplate().insert(statment, list.get(i));
            }
            // for(VehcileInfo vechileinfo:list) {
            // o = getSqlMapClientTemplate().update(statment,vechileinfo);
            // }
            // 批处理执行
            sqlmapclient.executeBatch();
            return o;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        // return 0;
    }

}
