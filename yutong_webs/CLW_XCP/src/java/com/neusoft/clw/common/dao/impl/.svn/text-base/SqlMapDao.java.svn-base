/**
 * @author <a href="hegq@neusoft.com">Puras.He</a>
 * @version Revision: 1.1 Date: Oct 15, 2007
 */
package com.neusoft.clw.common.dao.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.ClassUtils;
import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.Dao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.servlet.domain.AccessoryInfo;
import com.neusoft.clw.common.util.Constants;

/**
 * @author <a href="hegq@neusoft.com">Puras.He</a>
 * @version Revision: 1.1 Date: Oct 15, 2007 4:06:02 PM
 * @modified by wufei on 2008-01-14
 */
@SuppressWarnings("unchecked")
public class SqlMapDao extends SqlMapClientDaoSupport implements Dao {
	protected static final Logger LOG = Logger.getLogger(SqlMapDao.class);
	
    public Object getObject(String statment, Object obj)
            throws DataAccessException {
        try {
            return getSqlMapClientTemplate().queryForObject(statment, obj);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List getObjects(String statement, Object obj)
            throws DataAccessException {
        try {
            return getSqlMapClientTemplate().queryForList(statement, obj);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public Object getObject(Class clazz, Object obj) throws DataAccessException {
        try {
            return getSqlMapClientTemplate().queryForObject(
                    getFindQuery(ClassUtils.getShortClassName(clazz)), obj);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List getObjects(Class clazz, Object obj) throws DataAccessException {
        try {
            return getSqlMapClientTemplate().queryForList(
                    getSelectQuery(ClassUtils.getShortClassName(clazz)), obj);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List getObjectsByPage(Class clazz, Object obj, int skipResults,
            int maxResults) throws DataAccessException {
        try {
            return getSqlMapClientTemplate().queryForList(
                    getSelectQuery(ClassUtils.getShortClassName(clazz)), obj,
                    skipResults, maxResults);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List getObjectsByPage(String statment, Object obj, int skipResults,
            int maxResults) throws DataAccessException {
        try {
            return getSqlMapClientTemplate().queryForList(statment, obj,
                    skipResults, maxResults);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public Object insert(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        try {
            return getSqlMapClientTemplate().insert(
                    getInsertQuery(ClassUtils.getShortClassName(clazz)), obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * @param statment
     * @param obj
     * @throws DataAccessIntegrityViolationException
     * @throws DataAccessException
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        try {
            return getSqlMapClientTemplate().insert(statment, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * @param statment
     * @param obj
     * @return
     * @throws DataAccessIntegrityViolationException
     * @throws DataAccessException
     */
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

    public int update(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        int ret = 0;
        try {
            ret = getSqlMapClientTemplate().update(
                    getUpdateQuery(ClassUtils.getShortClassName(clazz)), obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return ret;
    }

    /**
     * @param statment
     * @param obj
     * @return
     * @throws DataAccessIntegrityViolationException
     * @throws DataAccessException
     */
    public int delete(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        int ret = 0;
        try {
            ret = getSqlMapClientTemplate().delete(statment, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return ret;

    }

    public int delete(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {

        int ret = 0;
        try {
            ret = getSqlMapClientTemplate().delete(
                    getDeleteQuery(ClassUtils.getShortClassName(clazz)), obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return ret;

    }

    public int getCount(Class clazz, Object obj) throws DataAccessException {
        try {
            return ((Integer) getSqlMapClientTemplate().queryForObject(
                    getCountQuery(ClassUtils.getShortClassName(clazz)), obj))
                    .intValue();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public int getCount(String statment, Object obj) throws DataAccessException {
        try {
            return (Integer) getSqlMapClientTemplate().queryForObject(statment,
                    obj);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public List getObjects(Object statement, Object obj)
            throws DataAccessException {
        try {
            return getSqlMapClientTemplate().queryForList((String) statement,
                    obj);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public int getCountByPrimaryKey(Class clazz, Object obj)
            throws DataAccessException {
        try {
            return ((Integer) getSqlMapClientTemplate().queryForObject(
                    getCountQueryByPrimaryKey(ClassUtils
                            .getShortClassName(clazz)), obj)).intValue();
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public int updateBatch(String statment, Object obj)
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

    /** 区域组加区域 **/
    public Object insertGroupRegionBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        try {
            return getSqlMapClientTemplate().insert(statment, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /** 区域组加车 **/
    public Object insertGroupVehBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        try {
            return getSqlMapClientTemplate().insert(statment, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /** 发送命令 **/
    public Object insertRegCommandBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        try {
            return getSqlMapClientTemplate().insert(statment, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    protected String getFindQuery(String className) {
        return className + ".get" + className;
    }

    protected String getSelectQuery(String className) {
        return className + ".get" + className + "s";
    }

    protected String getInsertQuery(String className) {
        return className + ".insert";
    }

    protected String getUpdateQuery(String className) {
        return className + ".update";
    }

    protected String getDeleteQuery(String className) {
        return className + ".delete";
    }

    protected String getCountQuery(String className) {
        return className + ".getCount";
    }

    private String getCountQueryByPrimaryKey(String className) {
        return className + ".getCountByPrimaryKey";
    }

    public Object batchUserAndRoleupdateList(String statment, Object obj)
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

    public Object batchUserAndRoleDeleteList(String statment, Object obj)
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
                o = getSqlMapClientTemplate().delete(statment, list.get(i));
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

    public Object insertAccessoryBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        AccessoryInfo accessoryInfo = new AccessoryInfo();
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof AccessoryInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be EnterpriseNotice instance");
            } else {
                accessoryInfo = (AccessoryInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            String strPath = Constants.UPLOAD_PATH_BASE
                    + accessoryInfo.getNoticeId();

            File dir = new File(strPath);
            File[] files = dir.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        String strFileName = files[i].getAbsolutePath()
                                .toLowerCase();

                        BufferedInputStream in = null;
                        ByteArrayOutputStream out = null;

                        byte[] temp = new byte[1024];
                        int size = 0;
                        try {
                            in = new BufferedInputStream(new FileInputStream(
                                    strFileName));
                            out = new ByteArrayOutputStream(1024);

                            while ((size = in.read(temp)) != -1) {
                                out.write(temp, 0, size);
                            }
                        } catch (IOException e) {
                            ;
                        } finally {
                            if (in != null) {
                                try {
                                    in.close();
                                } catch (IOException e) {
                                    ;
                                }
                            }
                        }

                        byte[] content = out.toByteArray();

                        // 创建附件信息bean
                        accessoryInfo.setAccessoryId(UUID.randomUUID()
                                .toString());
                        accessoryInfo.setAccessoryName(files[i].getName());
                        accessoryInfo.setAccessoryContent(content);

                        getSqlMapClientTemplate().insert(
                                "NoticeManage.saveNoticeAccessories",
                                accessoryInfo);
                    }
                }
            }

            // 批处理执行
            sqlmapclient.executeBatch();
            return o;

        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public Object insertAccessory(String statment, Object obj, String fileName)
            throws DataAccessIntegrityViolationException, DataAccessException {
        Object o = null;
        AccessoryInfo accessoryInfo = new AccessoryInfo();
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof AccessoryInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be AccessoryInfo instance");
            } else {
                accessoryInfo = (AccessoryInfo) obj;
            }
            String strPath = Constants.UPLOAD_PATH_BASE
                    + accessoryInfo.getNoticeId() + File.separator + fileName;

            File file = new File(strPath);

            if (file.isFile()) {
                String strFileName = file.getAbsolutePath().toLowerCase();

                BufferedInputStream in = null;
                ByteArrayOutputStream out = null;

                byte[] temp = new byte[1024];
                int size = 0;
                try {
                    in = new BufferedInputStream(new FileInputStream(
                            strFileName));
                    out = new ByteArrayOutputStream(1024);

                    while ((size = in.read(temp)) != -1) {
                        out.write(temp, 0, size);
                    }
                } catch (IOException e) {
                    ;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            ;
                        }
                    }
                }

                byte[] content = out.toByteArray();

                // 创建附件信息bean
                accessoryInfo.setAccessoryId(UUID.randomUUID().toString());
                accessoryInfo.setAccessoryName(file.getName());
                accessoryInfo.setAccessoryContent(content);

                getSqlMapClientTemplate().insert(
                        "NoticeManage.saveNoticeAccessories", accessoryInfo);

            }

            return o;

        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public int updateStuBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        try {
            return getSqlMapClientTemplate().update(statment, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
