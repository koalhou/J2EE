package com.neusoft.clw.sysmanage.datamanage.rolemanage.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;

public class RoleManageDao extends ExtendSqlMapDao {

    public void insertRoles(String statment, String[] obj, String roleID,
            String enterid) throws DataAccessException {

        SqlMapClient sqlmapclient = this.getSqlMapClient();
        try {
            // 开启批处理
            sqlmapclient.startBatch();
            Map < String, Object > map = new HashMap < String, Object >(2);
            map.put("roleID", roleID);
            map.put("enterprise_id", enterid);
            // System.out.println("obj.length="+obj.length);
            for (int i = 0; i < obj.length; i++) {
                map.put("moduleID", obj[i]);
                // System.out.println(i+"moduleID="+map.get("moduleID").toString());
                getSqlMapClientTemplate().insert(statment, map);
            }
            // 批处理执行
            sqlmapclient.executeBatch();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
