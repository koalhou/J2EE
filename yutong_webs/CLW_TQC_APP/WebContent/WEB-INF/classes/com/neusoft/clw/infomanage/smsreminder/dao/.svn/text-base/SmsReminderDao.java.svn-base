/*******************************************************************************
 * @(#)SmsReminderDao.java 2012-7-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.smsreminder.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.infomanage.smsreminder.domain.MessageInfo;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-7-4 下午04:06:08
 */
public class SmsReminderDao extends ExtendSqlMapDao {
    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        List<MessageInfo> list = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be list instance");
            } else {
                list =  (List < MessageInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            
            for(MessageInfo messageInfo : list) {
                getSqlMapClient().update("SmsReminder.updateSmsReminderByStuidAndCell", messageInfo);
            }
            
            // 批处理执行
            sqlmapclient.executeBatch();
            return 0;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
