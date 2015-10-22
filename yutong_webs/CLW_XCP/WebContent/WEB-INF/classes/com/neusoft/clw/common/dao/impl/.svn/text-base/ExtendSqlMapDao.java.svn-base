/*******************************************************************************
 * @(#)ExtendSqlMapDao.java 2008-4-11
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.dao.impl;

import org.apache.commons.lang.ClassUtils;
import org.springframework.dao.DataIntegrityViolationException;

import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;

/**
 * @author <a href="mailto:hanbj@neusoft.com">baojun.han </a>
 * @version $Revision 1.1 $ 2011-02-28 11:38:05
 */
@SuppressWarnings("unchecked")
public class ExtendSqlMapDao extends SqlMapDao {

    public Object getObject(Class clazz, Object obj) throws DataAccessException {
        try {
            String statement = getFindQuery(ClassUtils.getShortClassName(clazz));
            if (obj instanceof String) {
                statement = statement + "ById";
            }
            return getSqlMapClientTemplate().queryForObject(statement, obj);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    public int delete(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {

        int ret = 0;
        try {
            String statement = getDeleteQuery(ClassUtils
                    .getShortClassName(clazz));
            if (obj instanceof String) {
                statement = statement + "ById";
            }
            ret = getSqlMapClientTemplate().delete(statement, obj);
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
        return ret;

    }
}
