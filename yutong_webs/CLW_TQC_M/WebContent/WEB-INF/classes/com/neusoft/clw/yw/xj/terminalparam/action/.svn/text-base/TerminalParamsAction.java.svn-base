package com.neusoft.clw.yw.xj.terminalparam.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.monitor.ds.TerminalRealTimeInfo;
import com.neusoft.clw.yw.xj.terminalparam.ds.XCTerminalParamsInfo;
import com.opensymphony.xwork2.ActionContext;

public class TerminalParamsAction extends PaginationAction{
    private transient Service service;
    
    /** 终端版本属性信息(显示) **/
    private XCTerminalParamsInfo xcTerminalAttributeInfo = new XCTerminalParamsInfo();
    
    /** 终端参数信息(显示) **/
    private XCTerminalParamsInfo xcTerminalParamsInfo = new XCTerminalParamsInfo();
    
    /** 终端参数信息(设置) **/
    private XCTerminalParamsInfo xcTerminalParamsSet = new XCTerminalParamsInfo();
    
    /** 当前Tab页ID **/
    private String currentPage = "";
    
    /** 终端ID **/
    private String terminalId = "";
    
    /** 提示信息 **/
    private String message = null;
    
    /** 车辆VIN号(查询条件) **/
    private String vehicleVin = "";

    /** 终端号(查询条件) **/
    private String terminalCode = "";

    /** SIM卡号(查询条件) **/
    private String simCardNumber = "";
    
    /** 终端实时状态信息 **/
    private List < TerminalRealTimeInfo > terminalList = new ArrayList < TerminalRealTimeInfo >();
    
    /** JSON 返回 注册终端信息MAP **/
    private Map map = new HashMap();
    
    /**
     * 空action
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.param"));
        
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 组装JSON数据
     * @param vehicleRegisterList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getPagination(List list, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        for (int i = 0; i < list.size(); i++) {
            TerminalRealTimeInfo terminalRealTimeInfo = (TerminalRealTimeInfo) list.get(i);

            if(null == terminalRealTimeInfo.getVehicleLn() || terminalRealTimeInfo.getVehicleLn().length() == 0) {
                terminalRealTimeInfo.setVehicleLn("暂无车牌");
            }
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", terminalRealTimeInfo.getTerminalId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), 
                    terminalRealTimeInfo.getVehicleLn(),
                    terminalRealTimeInfo.getVehicleVin(),
                    terminalRealTimeInfo.getTerminalCode(),
                    terminalRealTimeInfo.getSimCardNumber(),
                    terminalRealTimeInfo.getCellPhone(),
                    terminalRealTimeInfo.getEnterpriseCode(),
                    terminalRealTimeInfo.getEnterpriseName()
                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 页面初始化/查询
     * @return
     */
    public String browseTerminals() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        String rpNum = "";
        String pageIndex = "";

        pageIndex = request.getParameter("page");
        rpNum = request.getParameter("rp");
        
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("vehicleVin", vehicleVin);
            map.put("terminalCode", terminalCode);
            map.put("simCardNumber", simCardNumber);
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);
            
            int totalCount = 0;
            totalCount = service.getCount("TerminalParams.getCount", map);
            terminalList = (List < TerminalRealTimeInfo >) service
                    .getObjectsByPage(
                            "TerminalParams.getTerminalRealTimeInfos", map,
                            (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (terminalList != null && terminalList.size() == 0) {
                // 无终端信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            this.map = getPagination(terminalList, totalCount, pageIndex, rpNum);
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
            addOperationLog("查询当前注册终端");
        }

        return SUCCESS;
    }
    
    /**
     * 整理信息
     * @param str
     * @return
     */
    private String changeStringValue(String str) {
        if(str == null || "".equals(str)) {
            str = XCTerminalParamsInfo.DONNOT_GET;
        }
        return str;
    }
    
    /**
     * 整理信息bean
     * @param bean
     */
    private void initShowData(XCTerminalParamsInfo bean) {
        // 终端版本信息
        bean.setTerminalRecordVersion(changeStringValue(bean.getTerminalRecordVersion()));
        bean.setTerminalScreenVersion(changeStringValue(bean.getTerminalScreenVersion()));
        bean.setTerminalRFVersion(changeStringValue(bean.getTerminalRFVersion()));
        bean.setTerminalDVRVersion(changeStringValue(bean.getTerminalDVRVersion()));
        bean.setTerminalOtherVersion(changeStringValue(bean.getTerminalOtherVersion()));
        
        // 位置汇报参数
        bean.setKeepAliveTime(changeStringValue(bean.getKeepAliveTime()));
        bean.setTcpOverTime(changeStringValue(bean.getTcpOverTime()));
        bean.setTcpRetransTime(changeStringValue(bean.getTcpRetransTime()));
        bean.setUdpOverTime(changeStringValue(bean.getUdpOverTime()));
        bean.setUdpRetransTime(changeStringValue(bean.getUdpRetransTime()));
        bean.setPositionUpType(changeStringValue(bean.getPositionUpType()));
        bean.setPositionUpSchema(changeStringValue(bean.getPositionUpSchema()));
        bean.setSleepDateTime(changeStringValue(bean.getSleepDateTime()));
        bean.setSosTime(changeStringValue(bean.getSosTime()));
        bean.setDefaultDateTime(changeStringValue(bean.getDefaultDateTime()));
        bean.setDefaultSpaceTime(changeStringValue(bean.getDefaultSpaceTime()));
        bean.setDriverOverSpaceTime(changeStringValue(bean.getDriverOverSpaceTime()));
        bean.setSleepSpaceTime(changeStringValue(bean.getSleepSpaceTime()));
        bean.setSosSpaceTime(changeStringValue(bean.getSosSpaceTime()));
        bean.setMakeUpAngle(changeStringValue(bean.getMakeUpAngle()));
        
        // 中心平台参数
        bean.setMainApn(changeStringValue(bean.getMainApn()));
        bean.setMainUser(changeStringValue(bean.getMainUser()));
        bean.setMainPass(changeStringValue(bean.getMainPass()));
        bean.setStandbyApn(changeStringValue(bean.getStandbyApn()));
        bean.setStandbyUser(changeStringValue(bean.getStandbyUser()));
        bean.setStandbyPass(changeStringValue(bean.getStandbyPass()));
        bean.setMainIp(changeStringValue(bean.getMainIp()));
        bean.setTcpPort(changeStringValue(bean.getTcpPort()));
        bean.setUdpPort(changeStringValue(bean.getUdpPort()));
        bean.setMonitorPhone(changeStringValue(bean.getMonitorPhone()));
        bean.setResetPhone(changeStringValue(bean.getResetPhone()));
        bean.setResetFactory(changeStringValue(bean.getResetFactory()));
        bean.setMonitorSmsPhone(changeStringValue(bean.getMonitorSmsPhone()));
        bean.setTerminalSmsPhone(changeStringValue(bean.getTerminalSmsPhone()));
        bean.setTerminalPhoneTactic(changeStringValue(bean.getTerminalPhoneTactic()));
        bean.setCallTimePer(changeStringValue(bean.getCallTimePer()));
        bean.setCallTimeMonth(changeStringValue(bean.getCallTimeMonth()));
        
        //车辆属性参数
        bean.setOdometer(changeStringValue(bean.getOdometer()));
        
        // 拍照参数
        bean.setMediaQuality(changeStringValue(bean.getMediaQuality()));
        bean.setLuminance(changeStringValue(bean.getLuminance()));
        bean.setContrast(changeStringValue(bean.getContrast()));
        bean.setSaturation(changeStringValue(bean.getSaturation()));
        bean.setChroma(changeStringValue(bean.getChroma()));
        
        // 告警参数
        bean.setAlarmShield(changeStringValue(bean.getAlarmShield()));
        bean.setAlarmSmsSwitch(changeStringValue(bean.getAlarmSmsSwitch()));
        bean.setAlarmShootSwitch(changeStringValue(bean.getAlarmShootSwitch()));
        bean.setAlarmShootSaveFlag(changeStringValue(bean.getAlarmShootSaveFlag()));
        bean.setKeyFlag(changeStringValue(bean.getKeyFlag()));
    }
    
    /**
     * 查询终端参数信息
     * @return
     */
    public String queryTerminalParamsById() {
        if (terminalId == "" || terminalId == null) {
            return ERROR;
        } else {
            try {
                // 获取终端版本属性信息
                xcTerminalAttributeInfo = (XCTerminalParamsInfo) service.getObject(
                        "TerminalParams.getTerminalInfoById", terminalId);
                initShowData(xcTerminalAttributeInfo);
                // 设置终端信息表主键值
                xcTerminalAttributeInfo.setQueryTerminalId(terminalId);
                
                // 获取终端参数信息
                xcTerminalParamsInfo = (XCTerminalParamsInfo) service.getObject(
                        "TerminalParams.getTerminalParamsById", terminalId);
                if(null == xcTerminalParamsInfo) {
                    xcTerminalParamsInfo = new XCTerminalParamsInfo();
                }
                initShowData(xcTerminalParamsInfo);
                
                // 显示信息
                if (null != message) {
                    if(message.contains("success")) {
                        addActionMessage(getText(message));
                    } else {
                        addActionError(getText(message));
                    }
                }

            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Query terminal parameters error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Query terminal parameters error:" + e.getMessage());
                return ERROR;
            }
        }
        return SUCCESS;
    }
    
    /**
     * 获取当前终端参数信息
     * @return
     */
    public String getCurrentTerminalParams() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        try {
            // 下发终端参数查询命令
            XCTerminalParamsInfo queryParamsBean = new XCTerminalParamsInfo();
            queryParamsBean.setUserId(currentUser.getUserID());
            queryParamsBean.setVehicleVin(vehicleVin);
            queryParamsBean.setSimCardNumber(simCardNumber);
            SendCommandUtils sendCommandUtils = new SendCommandUtils();
            // 查询终端参数
            String ret = sendCommandUtils.queryTerminalParams(queryParamsBean);
            if("0".equals(ret)) {
                setMessage("params.query.success");
            } else {
                setMessage("params.query.error");
                return ERROR;
            }
        } catch (Exception e) {
            setMessage("error_param");
            log.error("Query terminal params error:" + e.getMessage());
            setMessage("params.query.error");
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_TERMINAL_PARAMQUERY_MID);
            addOperationLog("查询最新终端参数信息");
        }

        
        return SUCCESS;
    }
    
    /**
     * 设置当前终端参数信息
     * @return
     */
    public String setCurrentTerminalParams() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        setCurrentPage("setTerminalParams");
        
        try {
            // 设置VIN号
            xcTerminalParamsSet.setVehicleVin(xcTerminalAttributeInfo.getVehicleVin());
            // 设置SIM卡号
            xcTerminalParamsSet.setSimCardNumber(xcTerminalAttributeInfo.getSimCardNumber());
            // 设置操作人
            xcTerminalParamsSet.setUserId(currentUser.getUserID());
            
            SendCommandUtils sendCommandUtils = new SendCommandUtils();
            // 查询终端参数
            String ret = sendCommandUtils.setTerminalParams(xcTerminalParamsSet);
            if("0".equals(ret)) {
                setMessage("params.set.success");
            } else {
                setMessage("params.set.error");
                return ERROR;
            }
        } catch (Exception e) {
            setMessage("error_param");
            log.error("Query terminal params error:" + e.getMessage());
            setMessage("params.set.error");
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_TERMINAL_SETPARAM_MID);
            addOperationLog("设置终端参数信息");
        }

        
        return SUCCESS;
    }
    
    public Service getService() {
        return service;
    }
    public void setService(Service service) {
        this.service = service;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public XCTerminalParamsInfo getXcTerminalParamsInfo() {
        return xcTerminalParamsInfo;
    }
    public void setXcTerminalParamsInfo(XCTerminalParamsInfo xcTerminalParamsInfo) {
        this.xcTerminalParamsInfo = xcTerminalParamsInfo;
    }

    public XCTerminalParamsInfo getXcTerminalAttributeInfo() {
        return xcTerminalAttributeInfo;
    }

    public void setXcTerminalAttributeInfo(
            XCTerminalParamsInfo xcTerminalAttributeInfo) {
        this.xcTerminalAttributeInfo = xcTerminalAttributeInfo;
    }

    public XCTerminalParamsInfo getXcTerminalParamsSet() {
        return xcTerminalParamsSet;
    }

    public void setXcTerminalParamsSet(XCTerminalParamsInfo xcTerminalParamsSet) {
        this.xcTerminalParamsSet = xcTerminalParamsSet;
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

    public List < TerminalRealTimeInfo > getTerminalList() {
        return terminalList;
    }

    public void setTerminalList(List < TerminalRealTimeInfo > terminalList) {
        this.terminalList = terminalList;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
