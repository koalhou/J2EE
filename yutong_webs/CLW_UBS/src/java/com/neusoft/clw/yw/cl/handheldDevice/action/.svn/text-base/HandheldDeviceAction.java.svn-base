/*******************************************************************************
 * @(#)HandheldDeviceAction.java 2012-3-13
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.cl.handheldDevice.action;

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
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.cl.handheldDevice.ds.HandheldDeviceInfo;
import com.neusoft.clw.yw.cl.handheldDevice.service.HandheldDeviceService;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 手持设备Action
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-13 上午09:13:07
 */
public class HandheldDeviceAction extends PaginationAction{
    private transient Service service;
    
    private transient HandheldDeviceService handheldDeviceService;
    
    /** 手持设备信息bean **/
    private HandheldDeviceInfo handheldDeviceInfo = new HandheldDeviceInfo();
    
    /** 提示信息 * */
    private String message = null;

    /** JSON 返回 注册信息MAP **/
    private Map handheldMap = new HashMap();
    
    /** 终端ID * */
    private String terminalId = "";
    
    /** 车辆ID * */
    private String vehicleId = "";
    
    /** SIM ID **/
    private String simId = "";
    
    /**
     * 空action
     * @return
     */
    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("handheld.device.location"));
        
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
    public Map getPagination(List handheldDeviceList, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        for (int i = 0; i < handheldDeviceList.size(); i++) {
            HandheldDeviceInfo handheldDeviceInfo = (HandheldDeviceInfo) handheldDeviceList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", handheldDeviceInfo.getTerminalId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), 
                    handheldDeviceInfo.getCellPhoneImei(),
                    handheldDeviceInfo.getHandheldDeviceNo(),
                    handheldDeviceInfo.getCellPhone(),
                    handheldDeviceInfo.getEnterpriseCode(),
                    handheldDeviceInfo.getEntipriseName(),
                    handheldDeviceInfo.getRegistrant(),
                    handheldDeviceInfo.getRegistrationTime(),
                    handheldDeviceInfo.getModifier(),
                    handheldDeviceInfo.getModifyTime()
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
    public String init() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        String rpNum = "";
        String pageIndex = "";
        
        try {
            if (null == handheldDeviceInfo) {
                handheldDeviceInfo = new HandheldDeviceInfo();
            } else {
            	handheldDeviceInfo.setHandheldDeviceNo(SearchUtil.formatSpecialChar(handheldDeviceInfo.getHandheldDeviceNo()));
            	handheldDeviceInfo.setCellPhoneImei(SearchUtil.formatSpecialChar(handheldDeviceInfo.getCellPhoneImei()));
            	handheldDeviceInfo.setCellPhone(SearchUtil.formatSpecialChar(handheldDeviceInfo.getCellPhone()));
            	handheldDeviceInfo.setEnterpriseCode(SearchUtil.formatSpecialChar(handheldDeviceInfo.getEnterpriseCode()));
            }
            
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            handheldDeviceInfo.setSortname(sortName);
            handheldDeviceInfo.setSortorder(sortOrder);
            
            int totalCount = 0;
            totalCount = service.getCount("HandheldDevice.getCount", handheldDeviceInfo);

            pageIndex = request.getParameter("page");
            rpNum = request.getParameter("rp");
            List<HandheldDeviceInfo> list = service.getObjectsByPage(
                    "HandheldDevice.getHandheldDeviceInfos", handheldDeviceInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (list != null && list.size() == 0) {
                // 无手持设备注册信息
                addActionMessage(getText("common.no.data"));
            }

            this.handheldMap = getPagination(list, totalCount, pageIndex,rpNum);
            
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query handheld device error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query handheld device error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_CL_HANDHELDDEVICE_QUERY_MID);
            addOperationLog("查询已注册手持设备");
        }

        return SUCCESS;
    }
    
    /**
     * 填加页面初始化
     * @return
     */
    public String addHandheldDeviceBefore() {
        // 显示提示信息
        if (null != message) {
            addActionError(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 创建手持设备注册信息
     * @return
     */
    public String addHandheldDevice() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        try {
            handheldDeviceInfo.setVehicleId(UUIDGenerator.getUUID());
            handheldDeviceInfo.setTerminalId(UUIDGenerator.getUUID());
            handheldDeviceInfo.setSimId(UUIDGenerator.getUUID());
            // 设置操作人
            handheldDeviceInfo.setOperator(currentUser.getUserID());
            // 创建手持设备注册信息
            handheldDeviceService.insertHandheldDevice(handheldDeviceInfo);
            setMessage("handheld.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("create handheld device info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("create handheld device info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_CL_HANDHELDDEVICE_ADD_MID);
            addOperationLog("新建手持设备注册信息");
        }

        return SUCCESS;
    }
    
    /**
     * 查询手持设备注册信息
     * @return
     */
    public String queryHandheldDeviceById() {
        try {
            // 获取车辆注册信息
            handheldDeviceInfo = (HandheldDeviceInfo) service.getObject(
                    "HandheldDevice.getHandheldDeviceInfoById", terminalId);

            // 显示提示信息
            if (null != message) {
                addActionError(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Query register detail error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            setMessage("info.db.error");
            log.error("Query register detail error:" + e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 更新手持设备注册信息
     * @return
     */
    public String updateHandheldDevice() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
        .getSession().get(Constants.USER_SESSION_KEY);
        
        setTerminalId(handheldDeviceInfo.getTerminalId());
        
        try {
            handheldDeviceInfo.setOperator(currentUser.getUserID());
            
            // 更新手持设备注册信息
            handheldDeviceService.updateHandheldDevice(handheldDeviceInfo);
            setMessage("handheld.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update handheld info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update handheld info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_CL_HANDHELDDEVICE_MODIFY_MID);
            addOperationLog("修改手持设备注册信息");
        }

        return SUCCESS;
    }
    
    /**
     * 删除手持设备注册信息
     * @return
     */
    public String delHandheldDevice() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
        .getSession().get(Constants.USER_SESSION_KEY);
        
        handheldDeviceInfo.setVehicleId(vehicleId);
        handheldDeviceInfo.setTerminalId(terminalId);
        handheldDeviceInfo.setSimId(simId);
        handheldDeviceInfo.setOperator(currentUser.getUserID());
        
        try {
            // 删除手持设备注册信息
            handheldDeviceService.deleteHandheldDevice(handheldDeviceInfo);
            setMessage("vehicleregister.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete register info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete register info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_CL_HANDHELDDEVICE_DELETE_MID);
            addOperationLog("删除手持设备注册信息");
        }
        
        return SUCCESS;
    }
    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public HandheldDeviceService getHandheldDeviceService() {
        return handheldDeviceService;
    }

    public void setHandheldDeviceService(HandheldDeviceService handheldDeviceService) {
        this.handheldDeviceService = handheldDeviceService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HandheldDeviceInfo getHandheldDeviceInfo() {
        return handheldDeviceInfo;
    }

    public void setHandheldDeviceInfo(HandheldDeviceInfo handheldDeviceInfo) {
        this.handheldDeviceInfo = handheldDeviceInfo;
    }

    public Map getHandheldMap() {
        return handheldMap;
    }

    public void setHandheldMap(Map handheldMap) {
        this.handheldMap = handheldMap;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }
}
