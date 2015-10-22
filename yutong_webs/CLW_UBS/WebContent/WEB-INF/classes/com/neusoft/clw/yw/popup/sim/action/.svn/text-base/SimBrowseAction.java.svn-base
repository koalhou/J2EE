package com.neusoft.clw.yw.popup.sim.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.popup.sim.ds.SimBrowseInfo;

/**
 * SIM卡选择popup子页面action
 * @author JinPeng
 */
public class SimBrowseAction extends BaseAction {
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** SIM卡号（查询条件） **/
    private String simCardNumber = "";

    /** SIM卡号列表 **/
    private List < SimBrowseInfo > simList = new ArrayList < SimBrowseInfo >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("simCardNumber", simCardNumber);

            simList = (List < SimBrowseInfo >) service.getObjects(
                    "SimBrowse.getSimInfos", map);

            if (simList != null && simList.size() == 0) {
                // 无SIM卡信息
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public List < SimBrowseInfo > getSimList() {
        return simList;
    }

    public void setSimList(List < SimBrowseInfo > simList) {
        this.simList = simList;
    }

}
