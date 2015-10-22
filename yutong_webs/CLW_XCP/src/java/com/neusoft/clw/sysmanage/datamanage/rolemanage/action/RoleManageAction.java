package com.neusoft.clw.sysmanage.datamanage.rolemanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.interceptor.AuthenticationInterceptor;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.rolemanageservice.RoleManageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.itsmanage.oilmanage.baddriving.domain.BadDrivDay;
import com.neusoft.clw.sysmanage.datamanage.rolemanage.domain.ModuleResInfo;
import com.neusoft.clw.sysmanage.datamanage.rolemanage.domain.Role;
import com.neusoft.clw.sysmanage.datamanage.rolemanage.domain.RoleRight;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 角色管理Action
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 2, 2011 3:25:14 PM
 */
public class RoleManageAction extends PaginationAction {

    private transient Service service;

    private Role role = new Role();

    private List < Role > leftList = null;

    private String roleRightString = null;

    private transient RoleManageService roleManageService;

    private String role_name;

    private String enterprise_id;

    private String full_name;

    private String apply_id;

    private String ChooseModID_tree;

    private List < Role > pageList = new ArrayList < Role >();

    private List < RoleRight > editList = new ArrayList < RoleRight >();

    private String role_id;

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    // add by jinp start
    private Map map = new HashMap();

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    // add by jinp stop

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getChooseModID_tree() {
        return ChooseModID_tree;
    }

    public void setChooseModID_tree(String chooseModID_tree) {
        ChooseModID_tree = chooseModID_tree;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public RoleManageService getRoleManageService() {
        return roleManageService;
    }

    public void setRoleManageService(RoleManageService roleManageService) {
        this.roleManageService = roleManageService;
    }

    public String getRoleRightString() {
        return roleRightString;
    }

    public void setRoleRightString(String roleRightString) {
        this.roleRightString = roleRightString;
    }

    public List < Role > getLeftList() {
        return leftList;
    }

    public void setLeftList(List < Role > leftList) {
        this.leftList = leftList;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public List < RoleRight > getEditList() {
        return editList;
    }

    public void setEditList(List < RoleRight > editList) {
        this.editList = editList;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List < Role > getPageList() {
        return pageList;
    }

    public void setPageList(List < Role > pageList) {
        this.pageList = pageList;
    }

    // add by jinp start
    /**
     * 页面初始化
     * @return
     */
    public String init() {

        if (errorMessage != null) {
            addActionError(getText(errorMessage));
        }
        return SUCCESS;
    }

    // add by jinp stop

    /**
     * 添加角色信息
     * @return 返回添加是否成功
     */
    public String addRole() {

        HttpServletRequest request = ServletActionContext.getRequest();
        final String addTitle = getText("roleinfo.add.title");
        log.info(addTitle);
        UserInfo user = getCurrentUser();
        try {
            roleManageService.insertRolesStr(request.getParameter("ROLE_NAME"),
                    request.getParameter("REMARK"), user.getUserID(), request
                            .getParameter("ROLES_STR"), user.getEntiID());

           

           
            
        } catch (BusinessException e) {
            // addActionError(getText(e.getMessage()));
            log.error(addTitle, e);
            return ERROR;
        }finally{
        	// 设置操作描述
            this.addOperationLog(formatLog(addTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.INSERT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_ROLEMANAGE_ADD_ID);
        }
        return SUCCESS;
    }

    public String addRoleBefore() {
        final String addBefTitle = getText("roleinfo.add.title");
        log.info(addBefTitle);
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            // map.put("in_module_id", user.getUserModuleId());
            map.put("in_module_id", "111_3");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("Role.show_module_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < ModuleResInfo > res = (ArrayList < ModuleResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getModuleAll(res);
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    // add by jinp start
    public Map getPagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            Role s = (Role) list.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getRole_id());

            cellMap
                    .put("cell",
                            new Object[] {s.getRole_name(), s.getRemark() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    // add by jinp stop

    /**
     * 查看所有角色信息
     * @return
     */
    public String findAllRole() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String browseTitle = getText("roleinfo.browse.title");

        try {
            role_name = request.getParameter("ROLE_NAME");
            apply_id = Constants.CLW_P_CODE;
            UserInfo user = getCurrentUser();
            enterprise_id = user.getEntiID();

            Map < String, Object > map = new HashMap < String, Object >(4);
            map.put("role_name", SqlStringUtil.getNull(request
                    .getParameter("ROLE_NAME")));
            map.put("apply_id", SqlStringUtil.getNull(request
                    .getParameter("APPLY_ID")));
            map.put("enterprise_id", enterprise_id);

            int totalSize = service.getCount("Role.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }

            // add by jinp start
            // 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);
            // add by jinp stop

            // modify by jinp start
            // Page pageObj = new Page(page, totalSize, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);

            pageList = service.getObjectsByPage("Role.getInfos", map, (Integer
                    .parseInt(pageIndex) - 1)
                    * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            setMap(getPagination(pageList, totalSize, pageIndex));
            // modify by jinp end

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_ROLEMANAGE_QUREY_ID);
        } catch (BusinessException e) {
            // addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 跳转编辑角色
     * @return
     * @throws BusinessException
     */
    public String updateRoleBefore() throws BusinessException {
        final String addEditTitle = getText("roleinfo.edit.title");
        log.info(addEditTitle);
        HttpServletRequest request = ServletActionContext.getRequest();
        String tree_script = "";
        try {
            Map < String, Object > map2 = new HashMap < String, Object >(1);
            map2.put("role_id", request.getParameter("role_id"));

            role = (Role) service.getObject("Role.getRoleUpdate", map2);
            List < ModuleResInfo > modlist = service.getObjects(
                    "Role.selectRoles", map2);
            String temp = "";
            for (int i = 0; i < modlist.size(); i++) {
                temp = temp + modlist.get(i).getModule_id() + "|";
            }
            if ("".equals(temp)) {
                ChooseModID_tree = "";
            } else {
                ChooseModID_tree = temp.substring(0, temp.length() - 1);
            }

            /*
             * 权限树显示
             */
            Map < String, Object > map = new HashMap < String, Object >(4);
            // map.put("in_module_id", user.getUserModuleId());
            map.put("in_module_id", "111_3");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("Role.show_module_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < ModuleResInfo > res = (ArrayList < ModuleResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getModuleAll(res);
            }
            // editList = service.getObjects("Role.getRole1",role);

        } catch (BusinessException e) {
            // addActionError(getText(e.getMessage()));
            log.error(addEditTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    /**
     * 修改角色信息
     * @return
     */
    public String updateRole() {
        UserInfo user = getCurrentUser();
        HttpServletRequest request = ServletActionContext.getRequest();
        final String editTitle = getText("roleinfo.edit.title");
        log.info(editTitle);
        try {
            roleManageService.updateRolesStr(request.getParameter("ROLE_ID"),
                    request.getParameter("ROLE_NAME"), role.getRemark(), user
                            .getUserID(), request.getParameter("ROLES_STR"),
                    user.getEntiID());

            

            

        } catch (BusinessException e) {
            // addActionError(getText(e.getMessage()));
            log.error(editTitle, e);
            return ERROR;
        }
        finally{
        	// 设置操作描述
            this.addOperationLog(formatLog(editTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.UPDATE);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_ROLEMANAGE_EDIT_ID);
        }
        return SUCCESS;
    }

    /**
     * 删除角色
     * @return
     */
    public String removeRole() {
        UserInfo user = getCurrentUser();
        final String deleteTitle = getText("roleinfo.delete.title");
        log.info(deleteTitle);
        try {
            // 对角色用户关联效验
            Role userRole = new Role();
            userRole.setRole_id(role_id);
            int userRoleResNum = service.getCount("Role.getUserRoleRes",
                    userRole);
            if (userRoleResNum != 0) {
                this.setErrorMessage("role.user.delete");

                return ERROR;
            }

            roleManageService.deleteRolesStr(role_id, user.getUserID());
            /*
             * 回显部分
             */
            role_name = "";
            full_name = "";
            apply_id = "";
            enterprise_id = user.getEntiID();
            Map < String, Object > map = new HashMap < String, Object >(4);
            map.put("enterprise_id", enterprise_id);

            int totalSize = service.getCount("Role.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            pageList = service.getObjectsByPage("Role.getInfos", map, pageObj
                    .getStartOfPage(), pageSize);

            // 设置操作描述
            this.addOperationLog(formatLog(deleteTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.DELETE);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_ROLEMANAGE_DELETE_ID);

        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(deleteTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                AuthenticationInterceptor.USER_SESSION_KEY);
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, Role role) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != role) {
            if (null != role.getRole_id()) {
                OperateLogFormator.format(sb, "role_id", role.getRole_id());
            }
        }
        return sb.toString();
    }
}
