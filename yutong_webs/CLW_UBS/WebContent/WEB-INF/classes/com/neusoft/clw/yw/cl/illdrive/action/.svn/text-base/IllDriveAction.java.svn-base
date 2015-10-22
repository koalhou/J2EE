package com.neusoft.clw.yw.cl.illdrive.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.cl.illdrive.ds.HarmdefDataInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class IllDriveAction extends PaginationAction {
    private transient Service service;

    private List < HarmdefDataInfo > pageList = new ArrayList < HarmdefDataInfo >();

    private HarmdefDataInfo harmdefDataInfo = new HarmdefDataInfo();

    private String def_name;

    private String full_name;

    private String apply_id;

    private String def_id;

    private String message;

    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("illdrive.location"));

        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            def_name = request.getParameter("DEF_NAME");
            full_name = request.getParameter("FULL_NAME");
            apply_id = request.getParameter("APPLY_ID");

            Map < String, Object > map = new HashMap < String, Object >(3);
            map.put("def_name", SqlStringUtil.getNull(request
                    .getParameter("DEF_NAME")));
            map.put("full_name", SqlStringUtil.getNull(request
                    .getParameter("FULL_NAME")));
            map.put("apply_id", SqlStringUtil.getNull(request
                    .getParameter("APPLY_ID")));

            int totalSize = service.getCount("IllDrive.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < HarmdefDataInfo >) service.getObjectsByPage(
                    "IllDrive.selectHarmdefDataInfo", map, pageObj
                            .getStartOfPage(), pageSize));
            if (null != message) {
                super.addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            log.error("查询不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_CL_BADDRIVE_QUERY_MID);
            addOperationLog("查询不良驾驶定义信息");
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
        // System.out.println(harmdefDataInfo.getDef_name());
        String id = UUIDGenerator.getUUID();
        harmdefDataInfo.setDef_id(id);
        harmdefDataInfo.setCreater(currentUser.getUserID());
        // System.out.println(id);
        try {

            service.insert("IllDrive.insertHarmdef", harmdefDataInfo);

        } catch (BusinessException e) {
            setMessage("illdrive.create.error");
            log.error("新建不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("illdrive.create.error");
            log.error("新建不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_CL_BADDRIVE_ADD_MID);
            addOperationLog("新建不良驾驶定义信息");
        }
        setMessage("illdrive.create.success");
        return SUCCESS;
    }

    public String gotoinfo() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        try {
            Map < String, Object > map = new HashMap < String, Object >(1);
            map.put("def_id", def_id);
            harmdefDataInfo = (HarmdefDataInfo) service.getObject(
                    "IllDrive.getHarmdefInfoByDefId", map);
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
        def_id = harmdefDataInfo.getDef_id();
        try {

            harmdefDataInfo.setModifier(currentUser.getUserID());
            service.update("IllDrive.updateHarmdefInfo", harmdefDataInfo);

        } catch (BusinessException e) {
            setMessage("illdrive.update.error");
            log.error("修改不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("illdrive.update.error");
            log.error("修改不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_CL_BADDRIVE_MODIFY_MID);
            addOperationLog("修改不良驾驶定义信息");
        }
        setMessage("illdrive.update.success");
        return SUCCESS;
    }

    public String dodel() {
        // System.out.println(harmdefDataInfo.getDef_id());
        // System.out.println(def_id);
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        Map < String, Object > map2 = new HashMap < String, Object >(2);
        map2.put("def_id", def_id);
        map2.put("vaset_user_id", currentUser.getUserID());
        try {
            int num = service.getCount("IllDrive.getNumCont", def_id);
            if (num > 0) {
                setMessage("illdrive.delete.error2");
                log.error("删除不良驾驶定义信息:该定义正在被车辆使用!");
                return ERROR;
            }

            service.update("IllDrive.updateDelHarmdefInfo", map2);

        } catch (BusinessException e) {
            setMessage("illdrive.delete.error");
            log.error("删除不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("illdrive.delete.error");
            log.error("删除不良驾驶定义信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_CL_BADDRIVE_DELETE_MID);
            addOperationLog("删除不良驾驶定义信息");
        }
        setMessage("illdrive.delete.success");
        return SUCCESS;

    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setPageList(List < HarmdefDataInfo > pageList) {
        this.pageList = pageList;
    }

    public List < HarmdefDataInfo > getPageList() {
        return pageList;
    }

    public String getDef_name() {
        return def_name;
    }

    public void setDef_name(String def_name) {
        this.def_name = def_name;
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

    public void setHarmdefDataInfo(HarmdefDataInfo harmdefDataInfo) {
        this.harmdefDataInfo = harmdefDataInfo;
    }

    public HarmdefDataInfo getHarmdefDataInfo() {
        return harmdefDataInfo;
    }

    public void setDef_id(String def_id) {
        this.def_id = def_id;
    }

    public String getDef_id() {
        return def_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
