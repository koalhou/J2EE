package com.neusoft.clw.yw.qx.usermanage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.common.ds.CommonMapBean;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.qx.usermanage.ds.EnterpriseInfo;
import com.neusoft.clw.yw.qx.usermanage.ds.RoleInfo;
import com.neusoft.clw.yw.qx.usermanage.ds.UserDetail;
import com.neusoft.clw.yw.qx.usermanage.service.DoUserManageService;
import com.neusoft.clw.yw.qx.usermanage.service.UserManageService;
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 用户管理action
 * @author JinPeng
 */
public class UserManageAction extends PaginationAction {
    /** 共通处理service **/
    private transient Service service;

    /** 用户管理service **/
    private transient UserManageService userManageService;

    /** 用户授权管理service **/
    private transient DoUserManageService doUserManageService;

    /** 用户名称（查询条件） **/
    private String userName = "";

    /** 各子系统ID信息 **/
    private String sysId = "";

    /** 企业ID **/
    private String enterpriseId = "";

    /** 企业名称 **/
    private String entipriseName = "";

    /** 用户ID **/
    private String userId = "";

    /** 用户状态 **/
    private String validFlag = "";

    /** 用户ID 字符串 **/
    private String userIdStr = "";

    /** 角色ID **/
    private String roleId = "";

    /** 创建来源ID **/
    private String applyId = "";

    /** 页面消息 **/
    private String message = null;

    /** 用户类型原始值 **/
    private String oldUsetType = "";
    
    /** 用户信息bean **/
    private UserDetail userDetail = new UserDetail();

    /** 用户信息 **/
    private List < UserDetail > userList = new ArrayList < UserDetail >();

    /** 子系统用户编码 **/
    private Map < String, String > subSysMap = new HashMap < String, String >();

    /** 性别编码 **/
    private Map < String, String > sexSysMap = new HashMap < String, String >();

    /** 工作类型编码 **/
    private Map < String, String > jobTypeMap = new HashMap < String, String >();

    /** 企业列表 **/
    private List < EnterpriseInfo > entiList = new ArrayList < EnterpriseInfo >();

    /** 角色列表 **/
    private List < RoleInfo > roleList = new ArrayList < RoleInfo >();

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /** 用户密码重置值 **/
    private static final String RESET_PASSWORD = "12345";

    /** JSON 返回 用户信息MAP **/

    private Map userMap = new HashMap();

    /**
     * 获取各子系统用户编码
     */
    private void getSubSysInfos(String flag) {
        if (subSysMap != null && subSysMap.size() == 0) {
            try {
                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos", "USERTYPE");
                for (CommonMapBean commonMapBean : list) {
                    if ("PART".equals(flag)) {
                        if (!("3".equals(commonMapBean.getCodeId()))) {
                            subSysMap.put(commonMapBean.getCodeId(),
                                    commonMapBean.getCodeName());
                        }
                    } else {
                        subSysMap.put(commonMapBean.getCodeId(), commonMapBean
                                .getCodeName());
                    }
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
     * 获取性别信息
     */
    private void getSexInfos() {
        if (sexSysMap != null && sexSysMap.size() == 0) {
            try {
                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos", "SEXTYPE");
                for (CommonMapBean commonMapBean : list) {
                    sexSysMap.put(commonMapBean.getCodeId(), commonMapBean
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
     * 获取工作类型编码
     */
    private void getJobTypeInfos() {
        if (jobTypeMap != null && jobTypeMap.size() == 0) {
            try {
                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos", "JOBTYPE");
                for (CommonMapBean commonMapBean : list) {
                    jobTypeMap.put(commonMapBean.getCodeId(), commonMapBean
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
     * 获取地理信息
     * @return
     */
    private boolean getGeoInfos() {
        // 地理信息列表
        List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
        Map < String, Object > mapPar = new HashMap < String, Object >(1);

        try {
            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市
            if (userDetail != null && userDetail.getCountryId() != ""
                    && userDetail.getCountryId() != null) {
                mapPar.put("zone_parent_id", userDetail.getCountryId());

                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (userDetail != null && userDetail.getProvinceId() != ""
                    && userDetail.getProvinceId() != null) {
                mapPar.put("zone_parent_id", userDetail.getProvinceId());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    public String blankUserManageAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("user.manage.location"));
        // 用户类型下拉框初始化
        getSubSysInfos(null);
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }

    /**
     * 用户管理页面初始化
     * @return
     */
    public String init() {

        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        // 页面初始化时，显示管理系统用户信息
        // sysId = Constants.CLW_U_M_CODE;
        String rpNum = "";
        String pageIndex = "";

        pageIndex = request.getParameter("page");
        rpNum = request.getParameter("rp");

        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        // 查询管理系统用户信息
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("userName", userName);
            map.put("sysId", sysId);
            map.put("enterpriseId", enterpriseId);
            map.put("roleId", roleId);
            map.put("validFlag", validFlag);
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);

            int totalCount = 0;

            totalCount = service.getCount("UserManage.getCount", map);

            userList = (List < UserDetail >) service.getObjectsByPage(
                    "UserManage.getUserDetails", map, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (userList != null && userList.size() == 0) {
                // 无用户信息
                addActionError(getText("common.no.data"));
                // return ERROR;
            } else {
                getSubSysInfos(null);
                for (UserDetail userDetail : userList) {
                    userDetail.setUserType(subSysMap.get(userDetail
                            .getUserType()));
                }
            }
            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }
            this.userMap = getPagination(userList, totalCount, pageIndex,rpNum);

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query user error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query user error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_QX_USER_QUERY_MID);
            addOperationLog("查询用户");
        }

        return SUCCESS;
    }

    public Map getPagination(List userList, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();

        for (int i = 0; i < userList.size(); i++) {

            UserDetail user = (UserDetail) userList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", user.getUserid());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), 
                    user.getLoginName(),
                    user.getUserType(), user.getEntipriseCode(),
                    user.getEntipriseName(), user.getRoleName(),
                    user.getUserName(), user.getUserCell(),
                    user.getUserEmail(), user.getValidFlag(),
                    user.getCreatTime(), user.getApplyId() });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    /**
     * 导出用户信息
     * @return
     */
    public String exportUserInfo() {

        Map < String, String > map = new HashMap < String, String >();

        map.put("userName", SqlStringUtil.getNull(userName));
        map.put("sysId", SqlStringUtil.getNull(sysId));
        map.put("enterpriseId", SqlStringUtil.getNull(enterpriseId));
        map.put("roleId", SqlStringUtil.getNull(roleId));
        map.put("validFlag", validFlag);

        try {
            userList = (List < UserDetail >) service.getObjects(
                    "UserManage.getUserDetails", map);
            getSubSysInfos(null);
            for (UserDetail userDetail : userList) {
                userDetail.setUserType(subSysMap.get(userDetail.getUserType()));
            }

        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID() + "userInfo.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("用户信息");

            excelExporter.putAutoExtendSheets("userInfo", 0, userList);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 设置下载文件属性
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-disposition",
                "attachment;filename=userInfo.xls");
        response.setContentType("application/msexcel; charset=\"utf-8\"");

        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return null;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
            setOperationType(Constants.EXPORT, ModuleId.CLW_M_QX_USER_EXPORT);
            addOperationLog("用户信息");
        }
        // 导出文件成功
        return null;
    }

    /**
     * 查询用户
     * @return
     */
    public String queryUser() {
        getSubSysInfos(null);

        try {
            Map < String, String > map = new HashMap < String, String >();
            // 用户录入的查询条件
            map.put("userName", userName);
            map.put("sysId", sysId);

            int totalCount = 0;

            totalCount = service.getCount("UserManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            userList = (List < UserDetail >) service.getObjectsByPage(
                    "UserManage.getUserDetails", map, pageObj.getStartOfPage(),
                    pageSize);

            if (userList != null && userList.size() == 0) {
                // 无用户信息
                super.addActionError(getText("common.no.data"));
                return ERROR;
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query user error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query user error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_QX_USER_QUERY_MID);
            addOperationLog("查询用户");
        }

        return SUCCESS;
    }

    /**
     * 初始化下拉框信息
     * @return
     */
    public boolean initSelectValue() {
        // 用户类型型
        getSubSysInfos("PART");
        // 性别
        getSexInfos();

        // 工作类型
        getJobTypeInfos();
        /**
         * if (entiList != null && entiList.size() == 0) { try { // 获取企业信息
         * entiList = (List<EnterpriseInfo>)
         * service.getObjects("UserManage.getEnterpriseInfos", ""); } catch
         * (BusinessException e) { log.error("Query enterprise error:" +
         * e.getMessage()); return false; } catch (Exception e) {
         * log.error("Query enterprise error:" + e.getMessage()); return false;
         * } } if (roleList != null && roleList.size() == 0) { try { // 获取角色信息
         * roleList = (List<RoleInfo>)
         * service.getObjects("UserManage.getRoleInfos", ""); } catch
         * (BusinessException e) { log.error("Query role error:" +
         * e.getMessage()); return false; } catch (Exception e) {
         * log.error("Query role error:" + e.getMessage()); return false; } }
         **/

        return true;
    }

    /**
     * 填加用户初始化
     * @return
     */
    public String addUser() {
        if (!initSelectValue()) {
            // 下拉框信息初始化失败时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        if (!getGeoInfos()) {
            // 地理信息初始化失败时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 执行填加用户
     * @return
     */
    public String add() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (userDetail != null) {
            // 设置创建用户
            userDetail.setCreator(currentUser.getUserID());
        }

        try {
            // 创建用户
            userManageService.insertUser(userDetail);
            setMessage("user.add.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Insert user error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Insert user error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT, ModuleId.CLW_M_QX_USER_ADD_MID);
            addOperationLog("新建用户");
        }
        return SUCCESS;
    }

    /**
     * 查询指定用户信息
     * @return
     */
    public String queryByUserId() {
        // 判断用户ID是否为空
        if (userId == "" || userId == null) {
            setMessage("user.not.choice");
            return ERROR;
        }
        // 判断是否为管理系统创建的用户
        if (applyId == "" || applyId == null) {
            // 非管理系统用户
        } else {
            // 管理系统用户
            try {
                userDetail = (UserDetail) service.getObject(
                        "UserManage.getUserInfoById", userId);
                
                setOldUsetType(userDetail.getUserType());
                
                if (!initSelectValue()) {
                    // 下拉框信息初始化失败时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }

                if (!getGeoInfos()) {
                    // 地理信息初始化失败时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }

            } catch (BusinessException e) {
                super.addActionError(getText("info.db.error"));
                log.error("Query user detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                super.addActionError(getText("info.db.error"));
                log.error("Query user detail error:" + e.getMessage());
                return ERROR;
            }
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 用户更新
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (userDetail != null) {
            // 设置创建用户
            userDetail.setCreator(currentUser.getUserID());
            // 设置修改用户
            userDetail.setModifier(currentUser.getUserID());
            // 设置用户ID值
            userDetail.setUserid(userId);
        }

        try {
            // 修改用户
            userManageService.updateUser(userDetail);
            setMessage("user.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update user error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update user error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_QX_USER_MODIFY_MID);
            addOperationLog("修改用户");
        }

        return SUCCESS;
    }

    /**
     * 用户删除
     * @return
     */
    public String delete() {
        // 判断用户ID是否为空
        if (userId == "" || userId == null) {
            setMessage("user.not.choice");
            return ERROR;
        }

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (userDetail != null) {
            // 设置修改用户
            userDetail.setModifier(currentUser.getUserID());
            // 设置用户ID值
            userDetail.setUserid(userId);
        }

        try {
            // 删除用户
            userManageService.delUser(userDetail);
            setMessage("user.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_QX_USER_DELETE_MID);
            addOperationLog("删除用户");
        }

        return SUCCESS;
    }

    /**
     * 用户启用
     * @return
     */
    public String userStartUse() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // 修改用户ID
        userDetail.setModifier(currentUser.getUserID());
        // 用户有效
        userDetail.setValidFlag("0");
        // 用户ID
        userDetail.setUserid(userId);

        try {
            // 用户启用
            service.update("UserManage.setUserStatus", userDetail);
            setMessage("user.startuse.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_QX_USER_STARTUSE_MID);
            addOperationLog("用户启用");
        }

        return SUCCESS;
    }

    /**
     * 用户禁用
     * @return
     */
    public String userForbid() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // 修改用户ID
        userDetail.setModifier(currentUser.getUserID());
        // 用户有效
        userDetail.setValidFlag("2");
        // 用户ID
        userDetail.setUserid(userId);

        try {
            // 用户启用
            service.update("UserManage.setUserStatus", userDetail);
            setMessage("user.forbid.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_QX_USER_FORBID_MID);
            addOperationLog("用户禁用");
        }

        return SUCCESS;
    }

    /**
     * 重置密码初始化
     * @return
     */
    public String resetPwdBefore() {
        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 重置密码
     * @return
     */
    public String reset() {
        // 判断用户ID是否为空
        if (userId == "" || userId == null) {
            setMessage("user.not.choice");
            return ERROR;
        }

        Map < String, String > map = new HashMap < String, String >();
        map.put("password", MD5digest
                .generatePassword(userDetail.getPassword()));
        map.put("userId", userId);

        try {
            // 重置密码
            service.update("UserManage.resetUserPwd", map);
            setMessage("user.reset.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            setUserId(userId);
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            setUserId(userId);
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE, ModuleId.CLW_M_QX_USER_RESET_MID);
            addOperationLog("重置用户密码");
        }

        return SUCCESS;
    }

    /**
     * 用户授权初始化
     * @return
     */
    public String authorizationBefore() {
        return SUCCESS;
    }

    /**
     * 用户授权
     * @return
     */
    public String doAuthorization() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // 设置创建用户ID
        try {
            if (null != userIdStr) {
                String[] userid = userIdStr.split(",");
                List < UserDetail > detailList = new ArrayList < UserDetail >();
                for (int i = 0; i < userid.length; i++) {
                    UserDetail userDetail = new UserDetail();
                    userDetail.setUserid(userid[i]);
                    userDetail.setRoleId(roleId);
                    userDetail.setCreator(currentUser.getUserID());
                    userDetail.setEntipriseId(currentUser.getEntiID());
                    detailList.add(userDetail);
                }
                // 用户授权
                doUserManageService.authorizationUser(detailList);
            }

        } catch (BusinessException e) {
            addActionMessage(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            addActionMessage(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE, ModuleId.CLW_M_QX_USER_AU_MID);
            addOperationLog("用户授权");
        }
        
        setMessage("user.authorization.success");
        return SUCCESS;
    }

    public DoUserManageService getDoUserManageService() {
        return doUserManageService;
    }

    public void setDoUserManageService(DoUserManageService doUserManageService) {
        this.doUserManageService = doUserManageService;
    }

    public String getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(String validFlag) {
        this.validFlag = validFlag;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Map getUserMap() {
        return userMap;
    }

    public void setUserMap(Map userMap) {
        this.userMap = userMap;
    }

    public Map < String, String > getSubSysMap() {
        return subSysMap;
    }

    public void setSubSysMap(Map < String, String > subSysMap) {
        this.subSysMap = subSysMap;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEntipriseName() {
        return entipriseName;
    }

    public void setEntipriseName(String entipriseName) {
        this.entipriseName = entipriseName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List < UserDetail > getUserList() {
        return userList;
    }

    public void setUserList(List < UserDetail > userList) {
        this.userList = userList;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Map < String, String > getSexSysMap() {
        return sexSysMap;
    }

    public void setSexSysMap(Map < String, String > sexSysMap) {
        this.sexSysMap = sexSysMap;
    }

    public List < EnterpriseInfo > getEntiList() {
        return entiList;
    }

    public void setEntiList(List < EnterpriseInfo > entiList) {
        this.entiList = entiList;
    }

    public List < RoleInfo > getRoleList() {
        return roleList;
    }

    public void setRoleList(List < RoleInfo > roleList) {
        this.roleList = roleList;
    }

    public Map < String, String > getCountryInfosMap() {
        return countryInfosMap;
    }

    public void setCountryInfosMap(Map < String, String > countryInfosMap) {
        this.countryInfosMap = countryInfosMap;
    }

    public Map < String, String > getProvinceInfosMap() {
        return provinceInfosMap;
    }

    public void setProvinceInfosMap(Map < String, String > provinceInfosMap) {
        this.provinceInfosMap = provinceInfosMap;
    }

    public Map < String, String > getCityInfosMap() {
        return cityInfosMap;
    }

    public void setCityInfosMap(Map < String, String > cityInfosMap) {
        this.cityInfosMap = cityInfosMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOldUsetType() {
        return oldUsetType;
    }

    public void setOldUsetType(String oldUsetType) {
        this.oldUsetType = oldUsetType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    /**
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }

    public UserManageService getUserManageService() {
        return userManageService;
    }

    public void setUserManageService(UserManageService userManageService) {
        this.userManageService = userManageService;
    }

    public Map < String, String > getJobTypeMap() {
        return jobTypeMap;
    }

    public void setJobTypeMap(Map < String, String > jobTypeMap) {
        this.jobTypeMap = jobTypeMap;
    }

    public String getUserIdStr() {
        return userIdStr;
    }

    public void setUserIdStr(String userIdStr) {
        this.userIdStr = userIdStr;
    }

}
