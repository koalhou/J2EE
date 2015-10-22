package com.neusoft.clw.yw.popup.owner.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.popup.owner.ds.OwnerInfo;

/**
 * 车主popup子页面action
 * @author JinPeng
 */
public class OwnerAction extends BaseAction {
    private transient Service service;

    /** 车主名称（查询条件） **/
    private String userName = "";

    /** 提示信息 **/
    private String message = null;

    /** 车主信息列表 **/
    private List < OwnerInfo > ownerList = new ArrayList < OwnerInfo >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("userName", userName);

            ownerList = (List < OwnerInfo >) service.getObjects(
                    "OwnerBrowse.getOwnerInfos", map);

            if (ownerList != null && ownerList.size() == 0) {
                // 无车主信息
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List < OwnerInfo > getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List < OwnerInfo > ownerList) {
        this.ownerList = ownerList;
    }

}
