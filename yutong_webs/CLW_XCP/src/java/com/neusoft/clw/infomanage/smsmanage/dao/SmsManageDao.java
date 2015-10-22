/*******************************************************************************
 * @(#)SmsManageDao.java 2012-3-14
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.infomanage.smsmanage.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.infomanage.smsmanage.domain.MessageInfo;
import com.neusoft.clw.infomanage.smsmanage.domain.SmsInfo;

/**
 * 短信提醒Dao
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-14 下午04:31:26
 */
public class SmsManageDao extends ExtendSqlMapDao{
    /**
     * 短信配置
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        Object o = null;
        SmsInfo smsInfo = null;

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof SmsInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be SmsInfo instance");
            } else {
                smsInfo = (SmsInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            // 根据ID清空原始配置
            getSqlMapClientTemplate().update("SmsManage.deleteMsgTypeByStuId", smsInfo.getStudent_id());
            // 分组ID值
            int groupId = 0;
            
            for(MessageInfo messageInfo : smsInfo.getParentsList()) {
                
                // 设置学生编号
                messageInfo.setStudent_id(smsInfo.getStudent_id());
                // 设置创建人
                messageInfo.setCreator(smsInfo.getCreator());
                // 设置联系人分组号
                messageInfo.setPersonGroupId(String.valueOf(groupId));
                groupId++;
                
                if(messageInfo.isUpNotChargeChoiceFlag()) {
                    // 未刷卡上车
                    messageInfo.setEventType("0");
                    getSqlMapClientTemplate().insert("SmsManage.insertMsgConfig", messageInfo);
                }
                if(messageInfo.isDownNotChargeChoiceFlag()) {
                    // 未刷卡下车
                    messageInfo.setEventType("1");
                    getSqlMapClientTemplate().insert("SmsManage.insertMsgConfig", messageInfo);
                }
                if(messageInfo.isUpNotSetSiteChoiceFlag()) {
                    // 未在规定站点上车
                    messageInfo.setEventType("2");
                    getSqlMapClientTemplate().insert("SmsManage.insertMsgConfig", messageInfo);
                }
                if(messageInfo.isDownNotSetSiteChoiceFlag()) {
                    // 未在规定站点下车
                    messageInfo.setEventType("3");
                    getSqlMapClientTemplate().insert("SmsManage.insertMsgConfig", messageInfo);
                }
                if(messageInfo.isUpNormalChoiceFlag()) {
                    // 正常上车
                    messageInfo.setEventType("4");
                    getSqlMapClientTemplate().insert("SmsManage.insertMsgConfig", messageInfo);
                }
                if(messageInfo.isDownNormalChoiceFlag()) {
                    // 正常下车
                    messageInfo.setEventType("5");
                    getSqlMapClientTemplate().insert("SmsManage.insertMsgConfig", messageInfo);
                }
                if(messageInfo.isAnXinWarnChoiceFlag()) {
                    // 安芯提醒
                    messageInfo.setEventType("6");
                    getSqlMapClientTemplate().insert("SmsManage.insertMsgConfig", messageInfo);
                }
            }
            // 批处理执行
            sqlmapclient.executeBatch();
            return o;
        } catch (SQLException e) {
            throw new DataAccessException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }
}
