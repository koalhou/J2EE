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
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.monitor.ds.SendCommandInfo;
import com.neusoft.clw.yw.xj.monitor.ds.TerminalParamsInfo;
import com.neusoft.clw.yw.xj.monitor.ds.TerminalRealTimeInfo;
import com.neusoft.clw.yw.xj.monitor.service.TerminalMonitorService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端实时状态监控action
 * @author JinPeng
 */
public class TerminalMonitorAction extends PaginationAction {
    /** 共同service **/
    private transient Service service;

    /** 终端监控service **/
    private transient TerminalMonitorService terminalMonitorService;

    /** 提示信息 **/
    private String message = null;

    /** 车辆VIN号(查询条件) **/
    private String vehicleVin = "";

    /** 终端号(查询条件) **/
    private String terminalCode = "";

    /** SIM卡号(查询条件) **/
    private String simCardNumber = "";

    /** 终端参数信息 **/
    private TerminalParamsInfo terminalParamsInfo = new TerminalParamsInfo();

    /** 终端实时状态信息 **/
    private List < TerminalRealTimeInfo > terminalList = new ArrayList < TerminalRealTimeInfo >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.param"));

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("vehicleVin", vehicleVin);
            map.put("terminalCode", terminalCode);
            map.put("simCardNumber", simCardNumber);

            int totalCount = 0;
            totalCount = service.getCount("TerminalMonitor.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            terminalList = (List < TerminalRealTimeInfo >) service
                    .getObjectsByPage(
                            "TerminalMonitor.getTerminalRealTimeInfos", map,
                            pageObj.getStartOfPage(), pageSize);

            if (terminalList != null && terminalList.size() == 0) {
                // 无终端信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            // 显示提示信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query terminal status error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query terminal status error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_TERMINAL_QUERY_MID);
            addOperationLog("查询终端当前状态");
        }

        return SUCCESS;
    }

    /**
     * 查询终端参数信息
     * @return
     */
    public String queryTerminalParamsById() {
        try {
            terminalParamsInfo = (TerminalParamsInfo) service.getObject(
                    "TerminalMonitor.getTerminalParams", terminalCode);
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query terminal params error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query terminal params error:" + e.getMessage());
            return ERROR;
        }

        // 显示操作结果信息
        if (null != message) {
            if ("success_param".equals(message)) {
                addActionMessage(getText("params.query.success"));
            } else if ("error_param".equals(message)) {
                addActionError(getText("params.query.error"));
            } else if ("error_number".equals(message)) {
                // 特征系数值非法
                addActionError(getText("index.value.invalid"));
            } else if ("success_index".equals(message)) {
                addActionMessage(getText("index.set.success"));
            } else if ("error_index".equals(message)) {
                addActionError(getText("index.set.error"));
            }
        }

        return SUCCESS;
    }

    /**
     * 显示最新终端参数信息
     * @return
     */
    public String showTerminalParamsByCode() {
        return SUCCESS;
    }

    /**
     * 查询最新终端参数信息
     * @return
     */
    public String getTerminalParamsByCode() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        // 设置基本参数值
        Map < String, String > map = new HashMap < String, String >();
        map.put("vehicleVin", vehicleVin);
        map.put("terminalCode", terminalCode);
        map.put("simCardNumber", simCardNumber);
        map.put("userId", currentUser.getUserID());

        try {
            // 下发终端参数查询命令
            terminalMonitorService.getTerminalParams(map);
        } catch (BusinessException e) {
            setMessage("error_param");
            log.error("Query terminal params error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("error_param");
            log.error("Query terminal params error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_TERMINAL_SETPARAM_MID);
            addOperationLog("查询最新终端参数信息");
        }

        setMessage("success_param");
        return SUCCESS;
    }

    /**
     * 设置特征系数
     * @return
     */
    public String setIndexPropertyByCode() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // 设置基本参数值
        Map < String, String > map = new HashMap < String, String >();
        map.put("vehicleVin", vehicleVin);
        map.put("terminalCode", terminalCode);
        map.put("simCardNumber", simCardNumber);
        map.put("userId", currentUser.getUserID());

        // 获取特征系数值
        int idxValue = Integer.valueOf(terminalParamsInfo.getIndexProperty());
        if (idxValue > 65535) {
            // 特征系数值非法
            setMessage("error_number");
            return ERROR;
        } else {
            map.put("indexProperty", String.valueOf(idxValue));
        }

        // 获取特征系数命令值
        SendCommandInfo sendCommandInfo = terminalMonitorService
                .getIndexPropertyCmd(map);

        try {
            // 设置特征系数
            service.insert("TerminalMonitor.insertSendCommandInfo",
                    sendCommandInfo);
        } catch (BusinessException e) {
            setMessage("error_index");
            log.error("Update terminal index property error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("error_index");
            log.error("Update terminal index property error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XJ_TERMINAL_SETPARAM_MID);
            addOperationLog("修改特征系数值");
        }

        setMessage("success_index");
        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public TerminalMonitorService getTerminalMonitorService() {
        return terminalMonitorService;
    }

    public void setTerminalMonitorService(
            TerminalMonitorService terminalMonitorService) {
        this.terminalMonitorService = terminalMonitorService;
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

    public TerminalParamsInfo getTerminalParamsInfo() {
        return terminalParamsInfo;
    }

    public void setTerminalParamsInfo(TerminalParamsInfo terminalParamsInfo) {
        this.terminalParamsInfo = terminalParamsInfo;
    }

    public List < TerminalRealTimeInfo > getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List < TerminalRealTimeInfo > terminalList) {
        this.terminalList = terminalList;
    }

}
