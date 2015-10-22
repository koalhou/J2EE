package com.neusoft.clw.yw.xs.noticemanage.dao;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.yw.xs.noticemanage.domain.AjtNotice;

public class AjtNoticeDao extends ExtendSqlMapDao {
    /**
     * 创建公告信息
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        AjtNotice ajtNotice = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof AjtNotice)) {
                throw new UnsupportedOperationException(
                        "the obj should be EnterpriseNotice instance");
            } else {
                ajtNotice = (AjtNotice) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            getSqlMapClientTemplate().insert(statment, ajtNotice);

            String delIds = ajtNotice.getDelAccessoryIds();

            StringBuffer sb = new StringBuffer();

            if (delIds != null && delIds.length() > 0) {
                String[] delAccressories = delIds.split(",");
                for (int i = 0; i < delAccressories.length; i++) {
                    if (sb.length() == 0 && delAccressories[i].length() > 0) {
                        sb.append("'" + delAccressories[i] + "'");
                    } else if (delAccressories[i] != null
                            && delAccressories[i].length() > 0) {
                        sb.append(",'" + delAccressories[i] + "'");
                    }
                }
            }

            if (sb.length() == 0) {
                sb.append("''");
            }

            ajtNotice.setDelAccessoryIds(sb.toString());

            getSqlMapClientTemplate().delete("NoticeManage.deleteAccessories",
                    ajtNotice);

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
     * 编辑公告信息
     */
    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        int ret = 0;
        AjtNotice ajtNotice = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof AjtNotice)) {
                throw new UnsupportedOperationException(
                        "the obj should be EnterpriseNotice instance");
            } else {
                ajtNotice = (AjtNotice) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            ret = getSqlMapClientTemplate().update(statment, ajtNotice);

            String delIds = ajtNotice.getDelAccessoryIds();

            StringBuffer sb = new StringBuffer();

            if (delIds != null && delIds.length() > 0) {
                String[] delAccressories = delIds.split(",");
                for (int i = 0; i < delAccressories.length; i++) {
                    if (sb.length() == 0 && delAccressories[i].length() > 0) {
                        sb.append("'" + delAccressories[i] + "'");
                    } else if (delAccressories[i] != null
                            && delAccressories[i].length() > 0) {
                        sb.append(",'" + delAccressories[i] + "'");
                    }
                }
            }
            if (sb.length() == 0) {
                sb.append("''");
            }
            ajtNotice.setDelAccessoryIds(sb.toString());

            getSqlMapClientTemplate().delete("NoticeManage.deleteAccessories",
                    ajtNotice);

            // 批处理执行
            sqlmapclient.executeBatch();
            return ret;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public static void main(String[] args) {
        String delIds = "";

        StringBuffer sb = new StringBuffer();

        if (delIds != null && delIds.length() > 0) {
            String[] delAccressories = delIds.split(",");
            for (int i = 0; i < delAccressories.length; i++) {
                if (sb.length() == 0 && delAccressories[i].length() > 0) {
                    sb.append("'" + delAccressories[i] + "'");
                } else if (delAccressories[i] != null
                        && delAccressories[i].length() > 0) {
                    sb.append(",'" + delAccressories[i] + "'");
                }
            }
        }

        System.out.println(sb.toString());
    }

}
