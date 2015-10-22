package com.neusoft.clw.yw.popup.role.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.popup.role.ds.RoleBrowseInfo;
import com.neusoft.clw.yw.popup.user.ds.UserBrowseInfo;
import com.neusoft.clw.yw.qx.rolemanage.ds.RoleDataInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 角色popup子页面action
 * @author JinPeng
 */
public class RoleBrowseAction extends BaseAction {
    private transient Service service;

    /** 用户名称（查询条件） **/
    private String roleName = "";

    /** 提示信息 **/
    private String message = null;

    /** 角色信息列表 **/
    private List < RoleBrowseInfo > roleList = new ArrayList < RoleBrowseInfo >();

    private String role_name;

    private String apply_id;

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("roleName", roleName);

            roleList = (List < RoleBrowseInfo >) service.getObjects(
                    "RoleBrowse.getRoleInfos", map);

            if (roleList != null && roleList.size() == 0) {
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

    /**
     * 角色管理页面初始化 查询页面
     * @return
     */

    public String popInitRole() {

        HttpServletRequest request = ServletActionContext.getRequest();
        try {

            Map < String, String > map = new HashMap < String, String >();
            map.put("roleName", roleName);
            //map.put("apply_id", apply_id);

            roleList = (List < RoleBrowseInfo >) service.getObjects(
                    "RoleBrowse.getRoleInfos", map);
            if (roleList != null && roleList.size() == 0) {
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

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List < RoleBrowseInfo > getRoleList() {
        return roleList;
    }

    public void setRoleList(List < RoleBrowseInfo > roleList) {
        this.roleList = roleList;
    }
}
