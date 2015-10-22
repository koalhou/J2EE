package com.neusoft.clw.yw.zdnew.terminalremoteupdate.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.neusoft.clw.common.dao.impl.ExtendSqlMapDao;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.BizTypeInfo;
import com.neusoft.clw.yw.cl.vehicleRegister.ds.VehicleRegisterInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.terminalRomoteUpdate.ds.TerminalParamsInfo;
import com.neusoft.clw.yw.xj.terminalparam.action.SendCommandUtils;
import com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds.UpdateUserInfo;
import com.opensymphony.xwork2.ActionContext;

public class NewTerminalUpdateDao extends ExtendSqlMapDao{
	 /**
     * 终端远程升级
     */
    public Object insert(String statment, Object obj)
            throws DataAccessIntegrityViolationException, DataAccessException {
        SqlMapClient sqlmapclient = this.getSqlMapClient();
        UpdateUserInfo updateuserinfo = null;
        Object o = null;
        
        List<TerminalParamsInfo> infos = new ArrayList<TerminalParamsInfo>();
        try {
            if (null == obj) {
                throw new DataAccessException("the obj value is null");
            }
            if (!(obj instanceof UpdateUserInfo)) {
                throw new UnsupportedOperationException(
                        "the obj should be VehicleRegisterInfo instance");
            } else {
                // 获取车辆注册信息bean
            	updateuserinfo = (UpdateUserInfo) obj;
            }
            UserInfo user = (UserInfo) ActionContext.getContext()
            .getSession().get(Constants.USER_SESSION_KEY);
            // 开启批处理
            sqlmapclient.startBatch();
            // 更新终端信息
            getSqlMapClientTemplate().insert(
                    "NewTerminalUpdate.mergeUserUpdateInfo", updateuserinfo);
            
            SendCommandUtils util = new SendCommandUtils();
        	for (int i = 0; i < updateuserinfo.getPIdArray().length; i++) {
        		String messageId = UUIDGenerator.getUUID32();
        		updateuserinfo.setMsg_id(messageId);
        		updateuserinfo.setPid(updateuserinfo.getPIdArray()[i]);
        		getSqlMapClientTemplate().update("NewTerminalUpdate.updateTerminalInfo", updateuserinfo);
        		
        		TerminalParamsInfo info  = new TerminalParamsInfo();
	    		info.setUserId(user.getUserID());
	    		info.setTerminalId(updateuserinfo.getTeminalArray()[i]);
	    		info.setVehicleVin(updateuserinfo.getVinArray()[i]);
	    		info.setHardver(updateuserinfo.getHost_hard_ver()); 
	    		info.setFirmver(updateuserinfo.getHost_firm_ver());
	    		info.setTimePer(updateuserinfo.getConnection_time()); 
	    		info.setUrl(updateuserinfo.getUrl_address());
	    		info.setMainapn(updateuserinfo.getDial_peer_name()); 
	    		info.setMainuser(updateuserinfo.getDial_peer_account()); 
	    		info.setMainpass(updateuserinfo.getDial_password()); 
	    		info.setIp(updateuserinfo.getIp_address()); 
	    		info.setTcpport(updateuserinfo.getTcp_port()); 
	    		info.setUdpport(updateuserinfo.getUdp_port());
	    		info.setSimCardNumber(updateuserinfo.getSimArray()[i]);
	    		infos.add(info);
	    		
        		util.updateTerminalNewVersion(infos.get(i),messageId);    
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