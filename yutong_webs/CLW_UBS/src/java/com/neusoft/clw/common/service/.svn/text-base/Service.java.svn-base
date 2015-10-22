/*******************************************************************************
 * @(#)Service.java Oct 16, 2007
 *
 * Copyright 2011 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;

/**
 * @author <a href="hegq@neusoft.com">Puras.He</a>
 * @version Revision: 1.1 Date: Oct 15, 2007 4:22:05 PM
 */
@SuppressWarnings("unchecked")
public interface Service {
    Object getObject(Class clazz, Object obj) throws BusinessException;;

    Object getObject(String statment, Object obj) throws BusinessException;;

    List getObjects(Class clazz, Object obj) throws BusinessException;;

    List getObjects(String statement, Object obj) throws BusinessException;;

    List getObjectsByPage(Class clazz, Object obj, int skipResults,
            int maxResults) throws BusinessException;

    List getObjectsByPage(String statment, Object obj, int skipResults,
            int maxResults) throws BusinessException;

    void insert(Class clazz, Object obj) throws BusinessException;

    void insert(String statment, Object obj) throws BusinessException;

    int update(Class clazz, Object obj) throws BusinessException;

    int update(String statment, Object obj) throws BusinessException;

    int delete(Class clazz, Object obj) throws BusinessException;

    int delete(String statment, Object obj) throws BusinessException;

    int getCount(Class clazz, Object obj) throws BusinessException;

    int getCount(String statment, Object obj) throws BusinessException;

    int getCountByPrimaryKey(Class clazz, Object obj) throws BusinessException;

    void insertAccessoryFile(String statment, Object obj, String fileName)
            throws BusinessException;

}
