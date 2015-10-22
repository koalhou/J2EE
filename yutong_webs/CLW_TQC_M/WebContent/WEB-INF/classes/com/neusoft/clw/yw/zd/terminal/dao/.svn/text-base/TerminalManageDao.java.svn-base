package com.neusoft.clw.yw.zd.terminal.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.yw.zd.terminal.ds.ChannelInfo;
import com.neusoft.clw.yw.zd.terminal.ds.TerminalInfo;

/**
 * 终端管理Dao
 * @author JinPeng
 */
public class TerminalManageDao extends ExtendSqlMapDao {

    /**
     * insert终端信息
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        TerminalInfo terminalInfo = null;
        Object o = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof TerminalInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be TerminalInfo instance");
            } else {
                // 获取终端bean
                terminalInfo = (TerminalInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            
            // 鉴权码问题修改 begin
            // 查询是否存在无效终端
            String id = (String)getSqlMapClientTemplate().queryForObject("TerminalManage.getDeleteDataId", terminalInfo.getTerminalCode());
            if(id != null) {
            	// 存在无效终端时则恢复原始记录
            	terminalInfo.setTerminalId(id);
            	getSqlMapClientTemplate().update("TerminalManage.updateDeletedTerminalInfo", terminalInfo);
            } else {
            	// insert终端信息
                getSqlMapClientTemplate().insert("TerminalManage.mergeTerminalInfo",
                        terminalInfo);
            }
            // 鉴权码问题修改 end

            // insert终端与通道关联表
            for (ChannelInfo channelInfo : terminalInfo.getChannelList()) {
                if (channelInfo.getChannelName() == ""
                        && channelInfo.getChannelNumber() == "") {
                    continue;
                } else {
                    o = getSqlMapClientTemplate().insert(
                            "TerminalManage.insertTmChannels", channelInfo);
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

    /**
     * 更新终端信息
     */
    public int update(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        TerminalInfo terminalInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof TerminalInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be TerminalInfo instance");
            } else {
                // 获取终端bean
                terminalInfo = (TerminalInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();
            // 修改终端信息
            int ret = getSqlMapClientTemplate().update(
                    "TerminalManage.updateTerminalInfo", terminalInfo);
            // 删除全部原始终端号与通道号关联关系
            getSqlMapClientTemplate().delete("TerminalManage.deleteChannels",
                    terminalInfo.getTerminalOldCode());
            // insert终端与通道关联表
            for (ChannelInfo channelInfo : terminalInfo.getChannelList()) {
                if (channelInfo.getChannelName() == ""
                        && channelInfo.getChannelNumber() == "") {
                    continue;
                } else {
                    getSqlMapClientTemplate().insert(
                            "TerminalManage.insertTmChannels", channelInfo);
                }
            }
            // 更新行业应用信息
            getSqlMapClientTemplate().update("TerminalManage.updateTerminalBizInfo", terminalInfo);
            
            // 批处理执行
            sqlmapclient.executeBatch();
            return ret;
        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 删除终端信息
     */
    public int delete(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        TerminalInfo terminalInfo = null;
        SqlMapClient sqlmapclient = this.getSqlMapClient();

        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof TerminalInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be TerminalInfo instance");
            } else {
                // 获取终端bean
                terminalInfo = (TerminalInfo) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            // 更新终端信息
            getSqlMapClientTemplate().update(
                    "TerminalManage.updateTerminalValid", terminalInfo);
            // 删除全部原始终端号与通道号关联关系
            int ret = getSqlMapClientTemplate().delete(
                    "TerminalManage.deleteChannels",
                    terminalInfo.getTerminalOldCode());

            // 批处理执行
            sqlmapclient.executeBatch();
            return ret;

        } catch (DataIntegrityViolationException e) {
            throw new DataAccessIntegrityViolationException(e);
        } catch (Exception e) {
            throw new DataAccessException(e);
        }
    }

    /**
     * 导入终端信息
     */
    public Object insert(Class clazz, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        List < TerminalInfo > list = null;
        Object o = null;
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof List)) {
                throw new UnsupportedOperationException(
                        "the obj should be List instance");
            } else {
                // 获取终端bean
                list = (List < TerminalInfo >) obj;
            }
            // 开启批处理
            sqlmapclient.startBatch();

            for (TerminalInfo terminalInfo : list) {
                // 导入终端信息
                getSqlMapClientTemplate().insert(
                        "TerminalManage.mergeTerminalInfo", terminalInfo);

                // 删除全部终端号与通道号关联关系
                getSqlMapClientTemplate().delete(
                        "TerminalManage.deleteChannels",
                        terminalInfo.getTerminalCode());
                // insert终端与通道关联表
                for (ChannelInfo channelInfo : terminalInfo.getChannelList()) {
                    if (channelInfo.getChannelName() == ""
                            && channelInfo.getChannelNumber() == "") {
                        continue;
                    } else {
                        o = getSqlMapClientTemplate().insert(
                                "TerminalManage.insertTmChannels", channelInfo);
                    }
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
