package com.neusoft.clw.yw.xs.versionmanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.versionmanage.ds.SysVersionInfo;
import com.opensymphony.xwork2.ActionContext;

public class VersionManageAction extends PaginationAction {
    private transient Service service;

    private List < SysVersionInfo > pageList = new ArrayList < SysVersionInfo >();

    private SysVersionInfo sysVersionInfo = new SysVersionInfo();

    private String message;

    private String apply_id;

    private String version_id;

    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("versionmanage.location"));

        try {

            Map < String, Object > map = new HashMap < String, Object >(1);
            map.put("apply_id", SqlStringUtil.getNull(apply_id));

            int totalSize = service.getCount("SysVersion.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < SysVersionInfo >) service.getObjectsByPage(
                    "SysVersion.selectSysVersion", map, pageObj
                            .getStartOfPage(), pageSize));
            if (null != message) {
                super.addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            log.error("查询版本信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询版本信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XS_VERSION_QUERY_MID);
            addOperationLog("查询版本信息");
        }

        return SUCCESS;

    }

    public String gotoadd() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        return SUCCESS;
    }

    public String doadd() {

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        sysVersionInfo.setVersion_id(UUIDGenerator.getUUID());
        sysVersionInfo.setCreater(currentUser.getUserID());
        try {

            service.insert("SysVersion.insertSysVersion", sysVersionInfo);

        } catch (BusinessException e) {
            setMessage("versionmanage.create.error");
            log.error("新建版本信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("versionmanage.create.error");
            log.error("新建版本信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_XS_VERSION_ADD_MID);
            addOperationLog("新建版本信息");
        }
        setMessage("versionmanage.create.success");
        return SUCCESS;
    }

    public String gotoinfo() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        try {

            sysVersionInfo = (SysVersionInfo) service.getObject(
                    "SysVersion.getSysVersionInfo", version_id);

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESS;
    }

    public String doedit() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        sysVersionInfo.setModifier(currentUser.getUserID());
        version_id = sysVersionInfo.getVersion_id();
        try {
            service.update("SysVersion.updateSysVersionInfo", sysVersionInfo);

        } catch (BusinessException e) {
            setMessage("versionmanage.update.error");
            log.error("修改版本信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("versionmanage.update.error");
            log.error("修改版本信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_VERSION_MODIFY_MID);
            addOperationLog("修改版本信息");
        }
        setMessage("versionmanage.update.success");
        return SUCCESS;

    }

    public String dodel() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        sysVersionInfo.setModifier(currentUser.getUserID());
        Map < String, Object > map = new HashMap < String, Object >(2);
        map.put("version_id", version_id);
        map.put("vaset_user_id", currentUser.getUserID());
        try {
            service.update("SysVersion.updateDelSysVersionInfo", map);

        } catch (BusinessException e) {
            setMessage("versionmanage.delete.error");
            log.error("删除版本信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("versionmanage.delete.error");
            log.error("删除版本信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_XS_VERSION_DELETE_MID);
            addOperationLog("删除版本信息");
        }
        setMessage("versionmanage.delete.success");
        return SUCCESS;

    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public List < SysVersionInfo > getPageList() {
        return pageList;
    }

    public void setPageList(List < SysVersionInfo > pageList) {
        this.pageList = pageList;
    }

    public SysVersionInfo getSysVersionInfo() {
        return sysVersionInfo;
    }

    public void setSysVersionInfo(SysVersionInfo sysVersionInfo) {
        this.sysVersionInfo = sysVersionInfo;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setVersion_id(String version_id) {
        this.version_id = version_id;
    }

    public String getVersion_id() {
        return version_id;
    }

}
