/**
 * @author <a href="hegq@neusoft.com">Puras.He</a>
 * @version Revision: 1.1 Date: Oct 15, 2007
 */
package com.neusoft.clw.common.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.dao.Dao;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.qx.rolemanage.dao.RoleManageDao;

/**
 * @author <a href="hegq@neusoft.com">Puras.He</a>
 * @version Revision: 1.1 Date: Oct 15, 2007 4:22:36 PM
 */
@SuppressWarnings("unchecked")
public class ServiceImpl implements Service {

    // 该数据已经存在
    private static final String INFO_INTEGRITY_VIOLATION_ERROR = "info.integrity.violation.error";

    private static final String INFO_DB_ERROR = "info.db.error"; // 数据库访问错误

    // protected static final Log LOG = LogFactory.getLog(ServiceImpl.class);

    protected static final Logger LOG = Logger.getLogger(ServiceImpl.class);

    protected Dao dao;

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    protected RoleManageDao roleManageDao;

    public void setRoleManageDao(RoleManageDao dao) {
        this.roleManageDao = dao;
    }

    public Object getObject(Class clazz, Object obj) throws BusinessException {
        try {
            return dao.getObject(clazz, obj);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public Object getObject(String statment, Object obj)
            throws BusinessException {
        try {
            return dao.getObject(statment, obj);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public List getObjects(Class clazz, Object obj) throws BusinessException {
        try {
            return dao.getObjects(clazz, obj);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public List getObjects(String statement, Object obj)
            throws BusinessException {
        try {
            return dao.getObjects(statement, obj);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public List getObjectsByPage(Class clazz, Object obj, int skipResults,
            int maxResults) throws BusinessException {
        try {
            return dao.getObjectsByPage(clazz, obj, skipResults, maxResults);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public void insert(Class clazz, Object obj) throws BusinessException {
        try {
            dao.insert(clazz, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public void insert(String statment, Object obj) throws BusinessException {
        try {
            dao.insert(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public int update(Class clazz, Object obj) throws BusinessException {
        try {
            return dao.update(clazz, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public int update(String statment, Object obj) throws BusinessException {
        try {
            return dao.update(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public int delete(Class clazz, Object obj) throws BusinessException {
        try {
            return dao.delete(clazz, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public int delete(String statment, Object obj) throws BusinessException {
        try {
            return dao.delete(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public int getCount(Class clazz, Object obj) throws BusinessException {
        try {
            return dao.getCount(clazz, obj);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public int getCount(String statment, Object obj) throws BusinessException {
        try {
            return dao.getCount(statment, obj);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public int getCountByPrimaryKey(Class clazz, Object obj)
            throws BusinessException {
        try {
            return dao.getCountByPrimaryKey(clazz, obj);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public List getObjectsByPage(String statment, Object obj, int skipResults,
            int maxResults) throws BusinessException {
        try {
            return dao.getObjectsByPage(statment, obj, skipResults, maxResults);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public void insertAccessoryFile(String statment, Object obj, String fileName)
            throws BusinessException {
        try {
            dao.insertAccessory(statment, obj, fileName);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }
}
