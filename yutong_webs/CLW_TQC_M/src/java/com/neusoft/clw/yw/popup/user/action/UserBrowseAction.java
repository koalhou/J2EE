package com.neusoft.clw.yw.popup.user.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.yw.common.ds.CommonMapBean;
import com.neusoft.clw.yw.popup.user.ds.UserBrowseInfo;

/**
 * 用户popup子页面action
 * @author JinPeng
 */
public class UserBrowseAction extends BaseAction {
    private transient Service service;

    /** 用户名称（查询条件） **/
    private String userName = "";

    /** 各子系统ID信息 **/
    private String sysId = "";

    /** 提示信息 **/
    private String message = null;

    /** 子系统用户编码 **/
    private Map < String, String > subSysMap = new HashMap < String, String >();

    /** 用户信息列表 **/
    private List < UserBrowseInfo > userList = new ArrayList < UserBrowseInfo >();

    /**
     * 获取各子系统用户编码
     */
    private void getSubSysInfos() {
        if (subSysMap != null && subSysMap.size() == 0) {
            try {
                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos", "USERTYPE");
                for (CommonMapBean commonMapBean : list) {
                    subSysMap.put(commonMapBean.getCodeId(), commonMapBean
                            .getCodeName());
                }
            } catch (BusinessException e) {
                super.addActionError(getText("info.db.error"));
                log.error(e.getMessage());
                return;
            } catch (Exception e) {
                super.addActionError(getText("info.db.error"));
                log.error(e.getMessage());
                return;
            }
        }
    }

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        getSubSysInfos();

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("userName", userName);

            if (null == sysId || sysId.length() == 0) {
                sysId = Constants.CLW_M_CODE;
            }
            map.put("sysId", sysId);

            userList = (List < UserBrowseInfo >) service.getObjects(
                    "UserBrowse.getUserInfos", map);

            if (userList != null && userList.size() == 0) {
                // 无用户信息
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

    public Map < String, String > getSubSysMap() {
        return subSysMap;
    }

    public void setSubSysMap(Map < String, String > subSysMap) {
        this.subSysMap = subSysMap;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public List < UserBrowseInfo > getUserList() {
        return userList;
    }

    public void setUserList(List < UserBrowseInfo > userList) {
        this.userList = userList;
    }
}
