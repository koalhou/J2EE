package com.neusoft.clw.sysmanage.sysset.smssignset.action;

import java.util.TreeMap;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.sysset.smssignset.domain.SmsSignInfo;
import com.opensymphony.xwork2.ActionContext;

public class SmsSignSetAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 短信设置标识 **/
    private String smsSignFlag = "";
    
    /** 短信签名bean **/
    private SmsSignInfo smsSignInfo = new SmsSignInfo();
    
    /** 短信签名类型 **/
    private TreeMap<String, String> smsSignMap = new TreeMap<String, String>();
    
    /**
     * 页面初始化
     * @return
     */
    public String init() {
        try {
            smsSignInfo = (SmsSignInfo) service.getObject("SmsSignSet.getSmsSignSet", getCurrentUser().getEntiID());
        } catch (BusinessException e) {
            log.error("query sign set error.");
        }
        smsSignMap.put("0", "宇通客车");
        smsSignMap.put("1", smsSignInfo.getShortName());
        smsSignFlag = smsSignInfo.getSmsFlag();
        
        return SUCCESS;
    }

    public String smsSignSet() {
        try {
            SmsSignInfo updateSignInfo = new SmsSignInfo();
            updateSignInfo.setSmsFlag(smsSignFlag);
            updateSignInfo.setEnterpriseId(getCurrentUser().getEntiID());
            
            service.update("SmsSignSet.updateSmsSignset", updateSignInfo);
            smsSignInfo = (SmsSignInfo) service.getObject("SmsSignSet.getSmsSignSet", getCurrentUser().getEntiID());
        } catch (BusinessException e) {
            log.error("query sign set error.");
        } finally {
            // 设置操作描述
            this.addOperationLog("设置短信签名");
            // 设置操作类型
            this.setOperationType(Constants.UPDATE);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_SMS_SIGNSET_SET_ID);
        }
        
        smsSignMap.put("0", "宇通客车");
        smsSignMap.put("1", smsSignInfo.getShortName());
        smsSignFlag = smsSignInfo.getSmsFlag();
        
        addActionMessage("短信签名设置成功！");
        return SUCCESS;
    }


    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getSmsSignFlag() {
        return smsSignFlag;
    }

    public void setSmsSignFlag(String smsSignFlag) {
        this.smsSignFlag = smsSignFlag;
    }

    public SmsSignInfo getSmsSignInfo() {
        return smsSignInfo;
    }

    public void setSmsSignInfo(SmsSignInfo smsSignInfo) {
        this.smsSignInfo = smsSignInfo;
    }

    public TreeMap < String, String > getSmsSignMap() {
        return smsSignMap;
    }

    public void setSmsSignMap(TreeMap < String, String > smsSignMap) {
        this.smsSignMap = smsSignMap;
    }
}
