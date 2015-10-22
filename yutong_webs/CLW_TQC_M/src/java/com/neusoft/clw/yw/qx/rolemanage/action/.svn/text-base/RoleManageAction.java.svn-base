package com.neusoft.clw.yw.qx.rolemanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.yw.qx.rolemanage.ds.ModuleResInfo;
import com.neusoft.clw.yw.qx.rolemanage.ds.RoleDataInfo;
import com.neusoft.clw.yw.qx.rolemanage.service.RoleManageService;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class RoleManageAction extends PaginationAction {

    private static final String DEFAULT_ROLE = "defrole";
    
    private static final String PARENTS_ROLE = "parents_role";
    
    private transient Service service;

    private transient RoleManageService roleManageService;

    private List < RoleDataInfo > pageList = new ArrayList < RoleDataInfo >();

    private String role_name;

    private String full_name;

    private String apply_id;

    private String role_id;

    private String ChooseModID_tree;

    private RoleDataInfo roleDataInfo = new RoleDataInfo();

    /** 页面消息 **/
    private String message;

    /** JSON 返回 角色信息MAP **/

    private Map roleMap = new HashMap();

    public String blankRoleManageAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("rolemanage.location"));

        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 角色管理页面初始化 查询页面
     * @return
     */
    public String init() {
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            /*
             * role_name = request.getParameter("ROLE_NAME"); full_name =
             * request.getParameter("FULL_NAME"); apply_id =
             * request.getParameter("APPLY_ID");
             */
            String rpNum = "";
            String pageIndex = "";

            pageIndex = request.getParameter("page");
            rpNum = request.getParameter("rp");

            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            Map < String, Object > map = new HashMap < String, Object >(3);
            map.put("role_name", SqlStringUtil.getNull(role_name));
            map.put("full_name", SqlStringUtil.getNull(full_name));
            map.put("apply_id", SqlStringUtil.getNull(apply_id));
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);

            int totalSize = service.getCount("RoleManage.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            pageList = (List < RoleDataInfo >) service.getObjectsByPage(
                    "RoleManage.selectRoleInfo", map, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (null != message) {
                super.addActionMessage(getText(message));
            }
            this.roleMap = getPagination(pageList, totalSize, pageIndex, rpNum);
        } catch (BusinessException e) {
            log.error("查询角色信息:" + e.getMessage());
            return ERROR;

        } catch (Exception e) {
            log.error("查询角色信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_QX_ROLE_QUERY_MID);
            addOperationLog("查询角色信息");
        }

        return SUCCESS;
    }

    public Map getPagination(List pageList, int totalCount, String pageIndex,
            String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();

        for (int i = 0; i < pageList.size(); i++) {

            RoleDataInfo role = (RoleDataInfo) pageList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", role.getRole_id());

            cellMap.put("cell", new Object[] {
                    (i + 1) + (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), role.getRole_name(),
                    role.getFull_name(), role.getApply_id() });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    public String gotoadd() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        String tree_script = "";
        try {
            Map < String, Object > map = new HashMap < String, Object >(4);
            map.put("in_module_id", "111");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("RoleManage.showall_module_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < ModuleResInfo > res = (ArrayList < ModuleResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getModuleAll(res);
            }

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    public String doadd() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        HttpServletRequest request = ServletActionContext.getRequest();

        try {

            roleManageService.insertRolesStr(request.getParameter("ROLE_NAME"),
                    request.getParameter("REMARK"), currentUser.getUserID(),
                    request.getParameter("ROLES_STR"));

        } catch (BusinessException e) {
            setMessage("rolemanage.create.error");
            log.error("新建角色信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("rolemanage.create.error");
            log.error("新建角色信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT, ModuleId.CLW_M_QX_ROLE_ADD_MID);
            addOperationLog("新建角色信息");
        }
        setMessage("rolemanage.create.success");
        return SUCCESS;
    }

    public String gotoinfo() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        HttpServletRequest request = ServletActionContext.getRequest();

        String tree_script = "";
        try {

            Map < String, Object > map2 = new HashMap < String, Object >(1);
            map2.put("role_id", request.getParameter("role_id"));

            roleDataInfo = (RoleDataInfo) service.getObject(
                    "RoleManage.getRoleInfo", map2);
            List < ModuleResInfo > modlist = service.getObjects(
                    "RoleManage.selectRoles", map2);
            String temp = "";
            for (int i = 0; i < modlist.size(); i++) {
                temp = temp + modlist.get(i).getModule_id() + "|";
            }
            if ("".equals(temp)) {
                ChooseModID_tree = "";
            } else {
                ChooseModID_tree = "111|"
                        + temp.substring(0, temp.length() - 1);
            }
            /*
             * 权限树显示
             */
            Map < String, Object > map = new HashMap < String, Object >(4);
            map.put("in_module_id", "111");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("RoleManage.showall_module_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < ModuleResInfo > res = (ArrayList < ModuleResInfo >) map
                        .get("out_ref");
                if ("0".equals(roleDataInfo.getApply_id())) {
                    tree_script = TreeHtmlShow.getModuleAll(res);
                } else {
                    tree_script = TreeHtmlShow.getModuleNoUsed(res);
                }
            }
        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    public String doedit() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        HttpServletRequest request = ServletActionContext.getRequest();
        role_id = request.getParameter("ROLE_ID");
        try {

            roleManageService.updateRolesStr(request.getParameter("ROLE_ID"),
                    request.getParameter("ROLE_NAME"), request
                            .getParameter("REMARK"), currentUser.getUserID(),
                    request.getParameter("ROLES_STR"));

        } catch (BusinessException e) {
            setMessage("rolemanage.update.error");
            log.error("修改角色信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("rolemanage.update.error");
            log.error("修改角色信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_QX_ROLE_MODIFY_MID);
            addOperationLog("修改角色信息");
        }
        setMessage("rolemanage.update.success");
        return SUCCESS;
    }

    public String dodel() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        try {
            if(DEFAULT_ROLE.equals(role_id) || PARENTS_ROLE.equals(role_id)) {
                setMessage("rolemanage.cannot.delete");
                log.error("删除角色信息:该角色为默认角色!");
                return ERROR;
            }
            int num = service.getCount("RoleManage.getUserRoleCont", role_id);
            if (num > 0) {
                setMessage("rolemanage.delete.error2");
                log.error("删除角色信息:该角色正在被使用!");
                return ERROR;
            }
            roleManageService.deleteRolesStr(role_id, currentUser.getUserID());

        } catch (BusinessException e) {
            setMessage("rolemanage.delete.error");
            log.error("删除角色信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("rolemanage.delete.error");
            log.error("删除角色信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_QX_ROLE_DELETE_MID);
            addOperationLog("删除角色信息");
        }
        setMessage("rolemanage.delete.success");
        return SUCCESS;
    }

    /*
     * set and get
     */

    public Map getRoleMap() {
        return roleMap;
    }

    public void setRoleMap(Map roleMap) {
        this.roleMap = roleMap;
    }

    public void setRoleDataInfo(RoleDataInfo roleDataInfo) {
        this.roleDataInfo = roleDataInfo;
    }

    public RoleDataInfo getRoleDataInfo() {
        return roleDataInfo;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setChooseModID_tree(String chooseModID_tree) {
        ChooseModID_tree = chooseModID_tree;
    }

    public String getChooseModID_tree() {
        return ChooseModID_tree;
    }

    public void setRoleManageService(RoleManageService roleManageService) {
        this.roleManageService = roleManageService;
    }

    public RoleManageService getRoleManageService() {
        return roleManageService;
    }

    public List < RoleDataInfo > getPageList() {
        return pageList;
    }

    public void setPageList(List < RoleDataInfo > pageList) {
        this.pageList = pageList;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
