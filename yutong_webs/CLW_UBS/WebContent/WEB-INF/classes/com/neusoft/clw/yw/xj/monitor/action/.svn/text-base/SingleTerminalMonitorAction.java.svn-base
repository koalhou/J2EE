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
import com.neusoft.clw.yw.xj.monitor.ds.SingleTerminalInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 单终端监控action
 * @author JinPeng
 */
public class SingleTerminalMonitorAction extends PaginationAction {
    /** 共同service **/
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 终端参数信息 **/
    private SingleTerminalInfo singleTerminalInfo = new SingleTerminalInfo();

    /** 终端历史状态信息 **/
    private List < SingleTerminalInfo > terminalList = new ArrayList < SingleTerminalInfo >();

    /** GPS状态 **/
    public static final Map < String, String > GPS_MAP = new HashMap < String, String >();

    /** 电源状态 **/
    public static final Map < String, String > POWER_MAP = new HashMap < String, String >();

    static {
        GPS_MAP.put("0", "未初始化");
        GPS_MAP.put("1", "没有天线");
        GPS_MAP.put("2", "无效信号");
        GPS_MAP.put("3", "保留");
        GPS_MAP.put("4", "有效信号");

        POWER_MAP.put("0", "电瓶");
        POWER_MAP.put("1", "电池");
    }

    /**
     * 单终端监控初始化
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.location"));

        return SUCCESS;
    }

    /**
     * 格式化终端状态信息
     * @param list
     * @return
     */
    private List < SingleTerminalInfo > formatTerminalInfos(
            List < SingleTerminalInfo > list, String value) {
        // VIN号
        String vehicleVin = "";
        // 视频ID
        String videoId = "";
        // 终端号
        String terminalCode = "";
        // 车牌号
        String vehicleLn = "";
        // 企业名
        String enterpriseName = "";

        for (SingleTerminalInfo tmp : list) {
            // 获取基础信息
            if (null != tmp.getVehicleVin()) {
                vehicleVin = tmp.getVehicleVin();
                videoId = tmp.getVideoId();
                terminalCode = tmp.getTerminalCode();
                vehicleLn = tmp.getVehicleLn();
                enterpriseName = tmp.getEnterpriseName();
                break;
            }
        }

        if (vehicleVin == null || vehicleVin.length() == 0) {
            try {
                SingleTerminalInfo ret = (SingleTerminalInfo) service
                        .getObject("SingleTerminalMonitor.getVehicleInfo",
                                value);
                vehicleVin = ret.getVehicleVin();
                videoId = ret.getVideoId();
                terminalCode = ret.getTerminalCode();
                vehicleLn = ret.getVehicleLn();
                enterpriseName = ret.getEnterpriseName();
            } catch (BusinessException e) {
                ;
            }
        }

        for (SingleTerminalInfo tmp : list) {
            if (null == tmp.getVehicleVin()) {
                tmp.setVehicleVin(vehicleVin);
                tmp.setVideoId(videoId);
                tmp.setTerminalCode(terminalCode);
                tmp.setVehicleLn(vehicleLn);
                tmp.setEnterpriseName(enterpriseName);
            } else {
                tmp.setGpsStatusName(GPS_MAP.get(tmp.getGpsStatus()));
                tmp.setPowerStatus(POWER_MAP.get(tmp.getPowerStatus()));
            }
        }

        return list;
    }

    /**
     * 查询终端历史状态
     * @return
     */
    public String query() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.location"));

        try {
            if (singleTerminalInfo.getVehicleVin() == null
                    || singleTerminalInfo.getVehicleVin().length() == 0
                    || singleTerminalInfo.getStartDate() == null
                    || singleTerminalInfo.getStartDate().length() == 0
                    || singleTerminalInfo.getMinuteValue() == null
                    || singleTerminalInfo.getMinuteValue().length() == 0) {
                return ERROR;
            }

            int totalCount = 0;
            totalCount = service.getCount("SingleTerminalMonitor.getCount",
                    singleTerminalInfo);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            terminalList = (List < SingleTerminalInfo >) service
                    .getObjectsByPage(
                            "SingleTerminalMonitor.getSingleTerminalStatus",
                            singleTerminalInfo, pageObj.getStartOfPage(),
                            pageSize);

            if (terminalList != null && terminalList.size() == 0) {
                // 无终端信息
                addActionError(getText("common.no.data"));
                return ERROR;
            } else {
                terminalList = formatTerminalInfos(terminalList,
                        singleTerminalInfo.getVehicleVin());
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
            addOperationLog("查询终端状态");
        }

        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public SingleTerminalInfo getSingleTerminalInfo() {
        return singleTerminalInfo;
    }

    public void setSingleTerminalInfo(SingleTerminalInfo singleTerminalInfo) {
        this.singleTerminalInfo = singleTerminalInfo;
    }

    public List < SingleTerminalInfo > getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List < SingleTerminalInfo > terminalList) {
        this.terminalList = terminalList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
