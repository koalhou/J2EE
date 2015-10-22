package com.neusoft.clw.yw.popup.enterprise.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.popup.enterprise.ds.EnterprisePopupInfo;

/**
 * 企业popup列表页action
 * @author JinPeng
 */
public class EnterpriseAction extends BaseAction {
    private transient Service service;

    /** 企业名称（查询条件） **/
    private String enterpriseName = "";

    private String enterpriseCode = "";
    
    /** 提示信息 **/
    private String message = null;

    /** 企业显示标记：1为不显示宇通杯企业 **/
    private String flag = "";

    /** 企业信息列表 **/
    private List < EnterprisePopupInfo > enterpriseList = new ArrayList < EnterprisePopupInfo >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("enterpriseName", enterpriseName);
            map.put("enterpriseCode", enterpriseCode);
            map.put("flag", flag);

            if ("2".equals(flag)) {
                enterpriseList = (List < EnterprisePopupInfo >) service
                        .getObjects("EnterpriseBrowse.getAllEnterpriseInfos",
                                map);
            } else {
                enterpriseList = (List < EnterprisePopupInfo >) service
                        .getObjects("EnterpriseBrowse.getEnterpriseInfos", map);
            }

            if (enterpriseList != null && enterpriseList.size() == 0) {
                // 无企业信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List < EnterprisePopupInfo > getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(List < EnterprisePopupInfo > enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

}
