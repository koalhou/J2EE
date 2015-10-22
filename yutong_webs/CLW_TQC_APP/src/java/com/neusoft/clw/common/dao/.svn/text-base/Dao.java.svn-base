/*******************************************************************************
 * @(#)Dao.java Oct 16, 2007
 *
 * Copyright 2007 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.dao;

import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;

/**
 * @author <a href="hegq@neusoft.com">Puras.He</a>
 * @version Revision: 1.1 Date: Oct 15, 2007 4:05:14 PM
 */
@SuppressWarnings("unchecked")
public interface Dao {
    Object getObject(Class clazz, Object obj) throws DataAccessException;

    List getObjects(Class clazz, Object obj) throws DataAccessException;

    List getObjectsByPage(Class clazz, Object obj, int skipResults,
            int maxResults) throws DataAccessException;

    Object insert(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    int update(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    int delete(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    int getCount(Class clazz, Object obj) throws DataAccessException;

    int getCount(String statment, Object obj) throws DataAccessException;

    List getObjects(Object statement, Object obj) throws DataAccessException;

    Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    int updateBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    Object insertGroupRegionBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    Object insertGroupVehBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    Object insertRegCommandBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    int delete(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    Object getObject(String statment, Object obj) throws DataAccessException;

    List getObjects(String statment, Object obj) throws DataAccessException;

    List getObjectsByPage(String statment, Object obj, int skipResults,
            int maxResults) throws DataAccessException;

    int getCountByPrimaryKey(Class clazz, Object obj)
            throws DataAccessException;

    Object batchUserAndRoleupdateList(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;
    
    Object batchUserAndRoleDeleteList(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    Object insertAccessoryBatch(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException;

    Object insertAccessory(String statment, Object obj, String fileName)
            throws DataAccessIntegrityViolationException, DataAccessException;
    
    int updateStuBatch(String statment, Object obj)
    throws DataAccessIntegrityViolationException, DataAccessException;

    int updateTqcBatch(String statment, Object obj)
    		throws DataAccessIntegrityViolationException, DataAccessException;
    
}
