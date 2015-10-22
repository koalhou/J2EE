package com.neusoft.clw.yw.xj.monitor.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.xj.monitor.ds.TerminalAuthenticationInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端未鉴权信息action
 * @author JinPeng
 */
public class TerminalAuthenticationAction extends PaginationAction {
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 未鉴权信息ID **/
    private String terminalAnthenticationId = "";

    /** 车辆VIN号(查询条件) **/
    private String vehicleVin = "";

    /** 终端号(查询条件) **/
    private String terminalCode = "";

    /** SIM卡号(查询条件) **/
    private String simCardNumber = "";

    /** 未鉴权信息详细 **/
    private TerminalAuthenticationInfo terminalAuthenticationInfo = new TerminalAuthenticationInfo();

    /** 未鉴权信息列表 **/
    private List < TerminalAuthenticationInfo > authenticationList = new ArrayList < TerminalAuthenticationInfo >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.location"));

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("vehicleVin", vehicleVin);
            map.put("terminalCode", terminalCode);
            map.put("simCardNumber", simCardNumber);

            int totalCount = 0;
            totalCount = service.getCount("TerminalAuthentication.getCount",
                    map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            authenticationList = (List < TerminalAuthenticationInfo >) service
                    .getObjectsByPage(
                            "TerminalAuthentication.getTerminalAuthenticationInfos",
                            map, pageObj.getStartOfPage(), pageSize);

            if (authenticationList != null && authenticationList.size() == 0) {
                // 无终端未鉴权信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            // 显示提示信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query illegality terminal error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query illegality terminal error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_TERMINAL_QUERY_MID);
            addOperationLog("查询未鉴权终端");
        }

        return SUCCESS;
    }

    /**
     * 查询未鉴权终端详细
     * @return
     */
    public String queryAuthenticationById() {
        try {
            // 查询未鉴权终端信息
            terminalAuthenticationInfo = (TerminalAuthenticationInfo) service
                    .getObject(
                            "TerminalAuthentication.getTerminalAuthenticationById",
                            terminalAnthenticationId);

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Query illegality terminal detail error:"
                    + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Query illegality terminal detail error:"
                    + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 删除终端未鉴权信息
     * @return
     */
    public String delete() {
        try {
            // 删除终端未鉴权信息
            service.delete(
                    "TerminalAuthentication.deleteTerminalAuthenticationById",
                    terminalAnthenticationId);
            setMessage("authentication.delete.success");
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Delete illegality terminal error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Delete illegality terminal error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_XJ_TERMINAL_AUTHENTICATION_MID);
            addOperationLog("删除未鉴权终端信息");
        }
        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getTerminalAnthenticationId() {
        return terminalAnthenticationId;
    }

    public void setTerminalAnthenticationId(String terminalAnthenticationId) {
        this.terminalAnthenticationId = terminalAnthenticationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public TerminalAuthenticationInfo getTerminalAuthenticationInfo() {
        return terminalAuthenticationInfo;
    }

    public void setTerminalAuthenticationInfo(
            TerminalAuthenticationInfo terminalAuthenticationInfo) {
        this.terminalAuthenticationInfo = terminalAuthenticationInfo;
    }

    public List < TerminalAuthenticationInfo > getAuthenticationList() {
        return authenticationList;
    }

    public void setAuthenticationList(
            List < TerminalAuthenticationInfo > authenticationList) {
        this.authenticationList = authenticationList;
    }
}
