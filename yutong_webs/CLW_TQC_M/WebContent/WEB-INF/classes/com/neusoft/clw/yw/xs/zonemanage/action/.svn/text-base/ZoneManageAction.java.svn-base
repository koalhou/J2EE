package com.neusoft.clw.yw.xs.zonemanage.action;

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
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;
import com.opensymphony.xwork2.ActionContext;

public class ZoneManageAction extends PaginationAction {
    private transient Service service;

    private String zone_id;

    private String zone_level;

    private String zone_parent_id;

    private String zone_name;

    private String zone_parent_name;

    private List < ZoneXsInfo > pageList = new ArrayList < ZoneXsInfo >();

    private ZoneXsInfo zoneXsInfo = new ZoneXsInfo();

    private String message;

    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("zonemanage.location"));
        // System.out.println("zone_level=" + zone_level);
        // System.out.println("zone_parent_id=" + zone_parent_id);
        // System.out.println("zone_name=" + zone_name);
        // System.out.println("zone_parent_name=" + zone_parent_name);

        if (zone_level == null) {
            zone_level = "1";
        }
        try {
            if (zone_parent_id != null && !"".equals(zone_parent_id)) {
                zone_parent_name = (String) service.getObject(
                        "ZoneManage.getZoneName", zone_parent_id);
                // System.out.println("zone_parent_name=" + zone_parent_name);
            }
            Map < String, Object > map = new HashMap < String, Object >(3);
            map.put("zone_name", SqlStringUtil.getNull(zone_name));
            map.put("zone_level", SqlStringUtil.getNull(zone_level));
            map.put("zone_parent_id", SqlStringUtil.getNull(zone_parent_id));

            int totalSize = service.getCount("ZoneManage.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < ZoneXsInfo >) service.getObjectsByPage(
                    "ZoneManage.selectZoneXsInfo", map, pageObj
                            .getStartOfPage(), pageSize));
            if (null != message) {
                super.addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            log.error("查询省市信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询省市信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XS_CODE_ZONE_QUERY_MID);
            addOperationLog("查询省市信息");
        }
        return SUCCESS;
    }

    public String gotoadd() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        if (zone_level == null) {
            zone_level = "1";
        }
        try {

            if (zone_parent_id != null && !"".equals(zone_parent_id)) {
                zone_parent_name = (String) service.getObject(
                        "ZoneManage.getZoneName", zone_parent_id);
                // System.out.println("zone_parent_name=" + zone_parent_name);
            }

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESS;

    }

    public String doadd() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // System.out.println("zone_level=" + zone_level);
        // System.out.println("zone_parent_id=" + zone_parent_id);
        // System.out.println("zone_name=" + zone_name);
        // System.out.println("zone_parent_name=" + zone_parent_name);
        // System.out.println("zoneXsInfo.zone_name="+zoneXsInfo.getZone_name());

        zoneXsInfo.setZone_id(UUIDGenerator.getUUID());
        zoneXsInfo.setZone_parent_id(SqlStringUtil.getNoNull(zone_parent_id));
        zoneXsInfo.setZone_level(SqlStringUtil.getNoNull(zone_level));
        zoneXsInfo.setCreater(currentUser.getUserID());

        try {
            service.insert("ZoneManage.insertZoneXsInfo", zoneXsInfo);

        } catch (BusinessException e) {
            setMessage("zonemanage.create.error");
            log.error("新建省市信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("zonemanage.create.error");
            log.error("新建省市信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_XS_CODE_ZONE_ADD_MID);
            addOperationLog("新建省市信息");
        }
        setMessage("zonemanage.create.success");
        return SUCCESS;
    }

    public String gotoinfo() {
        if (null != message) {
            super.addActionError(getText(message));
        }
        try {
            zoneXsInfo = (ZoneXsInfo) service.getObject(
                    "ZoneManage.getZoneXsInfo", zone_id);

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
        zone_parent_id = zoneXsInfo.getZone_parent_id();
        zone_level = zoneXsInfo.getZone_level();
        zone_id = zoneXsInfo.getZone_id();
        try {

            zoneXsInfo.setModifier(currentUser.getUserID());
            service.update("ZoneManage.updateZoneXsInfo", zoneXsInfo);

        } catch (BusinessException e) {
            setMessage("zonemanage.update.error");
            log.error("修改省市信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("zonemanage.update.error");
            log.error("修改省市信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_CODE_ZONE_MODIFY_MID);
            addOperationLog("修改省市信息");
        }
        setMessage("zonemanage.update.success");
        return SUCCESS;
    }

    public String dodel() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        zone_parent_id = zoneXsInfo.getZone_parent_id();
        zone_level = zoneXsInfo.getZone_level();
        zone_id = zoneXsInfo.getZone_id();
        try {

            zoneXsInfo.setVaset_user_id(currentUser.getUserID());

            int sonnum = service.getCount("ZoneManage.getSonCount", zoneXsInfo
                    .getZone_id());
            // System.out.println("sonnum" + sonnum);
            if (sonnum > 0) {
                setMessage("zonemanage.delete.error2");
                return ERROR;
            } else {
                service.update("ZoneManage.updateDelZoneXsInfo", zoneXsInfo);
            }

        } catch (BusinessException e) {
            setMessage("zonemanage.delete.error");
            log.error("删除省市信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("删除省市信息:" + e.getMessage());
            setMessage("zonemanage.delete.error");
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_XS_CODE_ZONE_DELETE_MID);
            addOperationLog("删除省市信息");
        }
        if (null == message || "".equals(message)) {
            setMessage("zonemanage.delete.success");
        }
        return SUCCESS;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public void setZone_level(String zone_level) {
        this.zone_level = zone_level;
    }

    public String getZone_level() {
        return zone_level;
    }

    public void setZone_parent_id(String zone_parent_id) {
        this.zone_parent_id = zone_parent_id;
    }

    public String getZone_parent_id() {
        return zone_parent_id;
    }

    public void setPageList(List < ZoneXsInfo > pageList) {
        this.pageList = pageList;
    }

    public List < ZoneXsInfo > getPageList() {
        return pageList;
    }

    public void setZoneXsInfo(ZoneXsInfo zoneXsInfo) {
        this.zoneXsInfo = zoneXsInfo;
    }

    public ZoneXsInfo getZoneXsInfo() {
        return zoneXsInfo;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_parent_name(String zone_parent_name) {
        this.zone_parent_name = zone_parent_name;
    }

    public String getZone_parent_name() {
        return zone_parent_name;
    }

    public void setZone_id(String zone_id) {
        this.zone_id = zone_id;
    }

    public String getZone_id() {
        return zone_id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
