/*******************************************************************************
 * @(#)SpeedMonitoringAction.java 2012-7-5
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.zdnew.speedmonitoring.action;

import java.io.IOException;
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
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.terminalparam.action.SendCommandUtils;
import com.neusoft.clw.yw.zdnew.speedmonitoring.ds.SpeedMonitoringInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-7-5 下午01:09:20
 */
public class SpeedMonitoringAction extends PaginationAction{
    private transient Service service;

    /** 页面提示信息 **/
    private String message = null;
    
    /** 车辆信息 **/
    private Map map = new HashMap();
    
    /** 选择企业ID **/
    private String enterpriseId = "";
    
    /** 车牌号 **/
    private String vehicleLn = "";
    
    /** 下发车辆ID **/
    private String carIdList = "";
    
    public static final Map < String, String > STATUS_MAP = new HashMap < String, String >();
    
    static {
        STATUS_MAP.put("00", "未校正");
        STATUS_MAP.put("01", "校正中");
        STATUS_MAP.put("02", "已校正");
    }
    
    /**
     * 空action
     * @return
     */
    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("speedmonitoring.location"));

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
    public Map getPagination(List list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        for (int i = 0; i < list.size(); i++) {
            SpeedMonitoringInfo speedMonitoringInfo = (SpeedMonitoringInfo) list.get(i);

            if(null == speedMonitoringInfo.getVehicleLn() || speedMonitoringInfo.getVehicleLn().length() == 0) {
                speedMonitoringInfo.setVehicleLn("暂无车牌");
            }
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", speedMonitoringInfo.getVehicleId());

            cellMap.put("cell", new Object[] {
                    speedMonitoringInfo.getVehicleId(), 
                    speedMonitoringInfo.getVehicleLn(),
                    speedMonitoringInfo.getGpsSpeed(),
                    speedMonitoringInfo.getVssSpeed(),
                    speedMonitoringInfo.getOffsetScale(),
                    speedMonitoringInfo.getTerminalTime(),
                    STATUS_MAP.get(speedMonitoringInfo.getDealStatus())
                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 获取车辆信息
     * @return
     */
    public String getSpeedMonitoringListByEnterpriseId() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        String rpNum = "";
        String pageIndex = "";
        String sortName = "";
        String sortOrder = "";

        pageIndex = request.getParameter("page");
        rpNum = request.getParameter("rp");
            
        sortName = request.getParameter("sortname");
        sortOrder = request.getParameter("sortorder");
        
        try {
            Map < String, String > map = new HashMap < String, String >();
            if(null == enterpriseId || enterpriseId.length() == 0) {
                map.put("enterpriseId", "111");
            } else {
                map.put("enterpriseId", enterpriseId);
            }
            map.put("vehicleLn", vehicleLn);
            map.put("sortname", sortName);
            map.put("sortorder", sortOrder);
            
            int totalCount = 0;
            totalCount = service.getCount("SpeedMonitoring.getVehicleCount", map);

            List<SpeedMonitoringInfo> vehicleList = service.getObjectsByPage(
                    "SpeedMonitoring.getVehicleListById", map, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(vehicleList, totalCount, pageIndex,rpNum);
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query speedmonitoring error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query speedmonitoring error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_SPEEDMONITORING_QUERY_MID);
            addOperationLog("车速监控查询");
        }
        
        return SUCCESS;
    }
    
    /**
     * 开启矫正
     */
    public void startAdjust() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        // 处理结果
        String ret = "";
        int failCnt = 0;
        
        try {
            SpeedMonitoringInfo speedMonitoringInfo = new SpeedMonitoringInfo();
            // 获取车辆属性信息
            List<SpeedMonitoringInfo> vehicleInfos = service.getObjects("SpeedMonitoring.getVehicleInfos", carIdList); 
            // 设置操作人
            speedMonitoringInfo.setUserId(currentUser.getUserID());
            for(SpeedMonitoringInfo tmp : vehicleInfos) {
                if(null != tmp.getVehicleVin() && tmp.getVehicleVin().length() > 0) {
                    // 设置VIN号
                    speedMonitoringInfo.setVehicleVin(tmp.getVehicleVin());
                    // 设置SIM卡号
                    speedMonitoringInfo.setSimCardNo(tmp.getSimCardNo());
                }
                
                SendCommandUtils sendCommandUtils = new SendCommandUtils();
                // 开启VSS矫正
                ret = sendCommandUtils.configAdjust(speedMonitoringInfo,"00");
                if(!"0".equals(ret)) {
                    failCnt ++ ;
                }
            }
        } catch (BusinessException e) {
            log.error("start adjust error:" + e.getMessage());
        } catch (Exception e) {
            log.error("start adjust error:" + e.getMessage());
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_SPEEDMONITORING_START_ADJUST_MID);
            addOperationLog("开启VSS特征系数矫正");
            try {
                response.getWriter().write(String.valueOf(failCnt));
            } catch (IOException ioException) {
            }
        }
    }
    
    /**
     * 关闭矫正
     */
    public void closeAdjust() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        // 处理结果
        String ret = "";
        int failCnt = 0;
        
        try {
            SpeedMonitoringInfo speedMonitoringInfo = new SpeedMonitoringInfo();
            // 获取车辆属性信息
            List<SpeedMonitoringInfo> vehicleInfos = service.getObjects("SpeedMonitoring.getVehicleInfos", carIdList); 
            // 设置操作人
            speedMonitoringInfo.setUserId(currentUser.getUserID());
            for(SpeedMonitoringInfo tmp : vehicleInfos) {
                if(null != tmp.getVehicleVin() && tmp.getVehicleVin().length() > 0) {
                    // 设置VIN号
                    speedMonitoringInfo.setVehicleVin(tmp.getVehicleVin());
                    // 设置SIM卡号
                    speedMonitoringInfo.setSimCardNo(tmp.getSimCardNo());
                }
                
                SendCommandUtils sendCommandUtils = new SendCommandUtils();
                // 关闭VSS矫正
                ret = sendCommandUtils.configAdjust(speedMonitoringInfo,"01");
                if(!"0".equals(ret)) {
                    failCnt ++ ;
                }
            }
        } catch (BusinessException e) {
            log.error("close adjust error:" + e.getMessage());
        } catch (Exception e) {
            log.error("close adjust error:" + e.getMessage());
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_SPEEDMONITORING_CLOSE_ADJUST_MID);
            addOperationLog("关闭VSS特征系数矫正");
            try {
                response.getWriter().write(String.valueOf(failCnt));
            } catch (IOException ioException) {
            }
        }
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getVehicleLn() {
        return vehicleLn;
    }

    public void setVehicleLn(String vehicleLn) {
        this.vehicleLn = vehicleLn;
    }

    public String getCarIdList() {
        return carIdList;
    }

    public void setCarIdList(String carIdList) {
        this.carIdList = carIdList;
    }
}
