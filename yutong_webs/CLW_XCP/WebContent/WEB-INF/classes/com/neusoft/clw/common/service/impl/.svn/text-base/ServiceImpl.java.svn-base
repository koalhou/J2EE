/**
 * @author <a href="hegq@neusoft.com">Puras.He</a>
 * @version Revision: 1.1 Date: Oct 15, 2007
 */
package com.neusoft.clw.common.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.neusoft.clw.common.dao.Dao;
import com.neusoft.clw.common.dao.impl.AffairDao;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.newenergy.newenergymsgsms.domain.EnergySms;
import com.neusoft.clw.sysmanage.datamanage.photographmanage.domain.PhotoGraphSet;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.sysset.oilset.domain.OilSet;

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

    protected AffairDao affairDao;

    public AffairDao getAffairDao() {
        return affairDao;
    }

    public void setAffairDao(AffairDao affairDao) {
        this.affairDao = affairDao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
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

    public Object batchUserAndRoleupdateList(String statment, Object obj)
            throws BusinessException {
        // TODO Auto-generated method stub
        try {
            return dao.batchUserAndRoleupdateList(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            // TODO Auto-generated catch block
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }

    }

    public Object batchUserAndRoleDeleteList(String statment, Object obj)
            throws BusinessException {
        // TODO Auto-generated method stub
        try {
            return dao.batchUserAndRoleupdateList(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            // TODO Auto-generated catch block
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }

    }

    public int updateBatch(String statment, Object obj)
            throws BusinessException {
        try {
            return dao.updateBatch(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public void insertGroupRegionBatch(String statment, Object obj)
            throws BusinessException {
        try {
            dao.insertGroupRegionBatch(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public void insertGroupVehBatch(String statment, Object obj)
            throws BusinessException {
        try {
            dao.insertGroupVehBatch(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public void insertRegCommandBatch(String statment, Object obj)
            throws BusinessException {
        try {
            dao.insertRegCommandBatch(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

    public void batchAddOilSet(List < OilSet > obj) throws SQLException {
        try {
            affairDao.addBatchOilSet(obj);
        } catch (Exception e) {
            LOG.error(e);
            throw new SQLException(INFO_DB_ERROR);
        }
    }

    public void deleteBatchOilSet(OilSet obj) throws SQLException {
        try {
            affairDao.deleteBatchOilSet(obj);
        } catch (Exception e) {
            LOG.error(e);
            throw new SQLException(INFO_DB_ERROR);
        }
    }

    public void updateBatchOilSet(OilSet obj) throws SQLException {
        try {
            affairDao.updateBatchOilSet(obj);
        } catch (Exception e) {
            LOG.error(e);
            throw new SQLException(INFO_DB_ERROR);
        }
    }

    public void batchCanelCars(List < VehcileInfo > obj) throws SQLException {
        try {
            affairDao.batchCanleVel(obj);
        } catch (Exception e) {
            LOG.error(e);
            throw new SQLException(INFO_DB_ERROR);
        }
    }

    public void insertAccessoryFiles(String statment, Object obj)
            throws BusinessException {
        try {
            dao.insertAccessoryBatch(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
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

    public void updateStuBatch(String statment, Object obj)
            throws BusinessException {
        try {
            dao.updateStuBatch(statment, obj);
        } catch (DataAccessIntegrityViolationException e) {
            LOG.error(e);
            throw new BusinessException(INFO_INTEGRITY_VIOLATION_ERROR);

        } catch (DataAccessException e) {
            LOG.error(e);
            throw new BusinessException(INFO_DB_ERROR);
        }
    }

	public void addBatchPhotoSet(List <PhotoGraphSet> obj) throws SQLException {
		try {
            affairDao.addBatchPhotoSet(obj);
        } catch (Exception e) {
            LOG.error(e);
            throw new SQLException(INFO_DB_ERROR);
        }
	}

	public void addBatchEnergySmsSet(List<EnergySms> smsList) throws SQLException {
		try {
			affairDao.addBatchEnergySmsSet(smsList);
		} catch (Exception e) {
			LOG.error(e);
			throw new SQLException(INFO_DB_ERROR);
		}
	}

}
