/*******************************************************************************
 * @(#)NewTerminalDeviceStatusAction.java 2012-7-5
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.zdnew.terminaldevice.action;

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
import com.neusoft.clw.yw.zdnew.terminaldevice.ds.TerminalDeviceStatus;
import com.neusoft.clw.yw.zdnew.terminalversions.ds.TerminalVersionsInfo;
import com.opensymphony.xwork2.ActionContext;
/**
 * 终端设备状态管理action
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao </a>
 * @version $Revision 1.1 $ 2012-7-5 上午9:41:56
 */
public class NewTerminalDeviceStatusAction extends PaginationAction {
    private transient Service service;

    /** 页面提示信息 **/
    private String message = null;
    
    private Map device_status_map = new HashMap();
    
    private TerminalDeviceStatus queryObj;
    
    /** 版本信息 **/
    private Map map = new HashMap();
    
    /**
     * 空action
     * @return
     */
    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("terminaldevicestatus.location"));
        if (null != message) {
            addActionMessage(getText(message));
        }
        device_status_map.put("0", "无异常");
        device_status_map.put("1", "异常");
        queryObj = new TerminalDeviceStatus();
        setOperationType(Constants.SELECT,
                ModuleId.CLW_M_ZDVERSIONS_MID);
        addOperationLog("查询终端设备状态");
        return SUCCESS;
    }
    
    /**
     * 组装JSON数据
     * @param versionList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getPagination(List list, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        for (int i = 0; i < list.size(); i++) {
            TerminalDeviceStatus versionInfo = (TerminalDeviceStatus) list.get(i);

            if(null == versionInfo.getVehicle_ln() || versionInfo.getVehicle_ln().length() == 0) {
                versionInfo.setVehicle_ln("暂无车牌");
            }
            
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", versionInfo.getVehicle_vin());

            cellMap.put("cell", new Object[] {
                    versionInfo.getVehicle_ln(),
                    versionInfo.getTerminal_time(),
                    versionInfo.getDvr_flag(),
                    versionInfo.getCard_flag(),
                    versionInfo.getDriver_helper_flag(),
                    versionInfo.getBattery_flag(),
                    versionInfo.getGps_aerial_flag(),
                    versionInfo.getGps_mode_flag(),
                    versionInfo.getVss_flag(),
                    versionInfo.getTts_flag(),
                    versionInfo.getCamera1(),
                    versionInfo.getCamera2(),
                    versionInfo.getCamera3(),
                    versionInfo.getCamera4()
                     });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }    
    
/*    public Map getPagination(List list, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        String dvr_flag = "3";
        String card_flag = "3";
        String driver_helper_flag = "3";
        String battery_flag = "3";
        String gps_aerial_flag = "3";
        String gps_mode_flag = "3";
        String vss_flag = "3";
        String tts_flag = "3";
        String camera1 = "3";
        String camera2 = "3";
        String camera3 = "3";
        String camera4 = "3";
        String device_state = "";
        
        for (int i = 0; i < list.size(); i++) {
            TerminalDeviceStatus versionInfo = (TerminalDeviceStatus) list.get(i);

            if(null == versionInfo.getVehicle_ln() || versionInfo.getVehicle_ln().length() == 0) {
                versionInfo.setVehicle_ln("暂无车牌");
            }
            if("0".equals(versionInfo.getOnline_flag())){
                dvr_flag = "3";
                card_flag = "3";
                driver_helper_flag = "3";
                battery_flag = "3";
                gps_aerial_flag = "3";
                gps_mode_flag = "3";
                vss_flag = "3";
                tts_flag = "3";
                camera1 = "3";
                camera2 = "3";
                camera3 = "3";
                camera4 = "3";
            }else{
                device_state = versionInfo.getDevice_state();
                dvr_flag = ""+device_state.charAt(11);
                card_flag = ""+device_state.charAt(10);
                driver_helper_flag = ""+device_state.charAt(9);
                battery_flag = ""+device_state.charAt(8);
                gps_aerial_flag = ""+device_state.charAt(7);
                gps_mode_flag = ""+device_state.charAt(6);
                vss_flag = ""+device_state.charAt(5);
                tts_flag = ""+device_state.charAt(4);
                camera1 = ""+device_state.charAt(3);
                camera2 = ""+device_state.charAt(2);
                camera3 = ""+device_state.charAt(1);
                camera4 = ""+device_state.charAt(0);
            }
            
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", versionInfo.getVehicle_vin());

            cellMap.put("cell", new Object[] {
                    versionInfo.getVehicle_ln(),
                    versionInfo.getTerminal_time(),
                    dvr_flag,
                    card_flag,
                    driver_helper_flag,
                    battery_flag,
                    gps_aerial_flag,
                    gps_mode_flag,
                    vss_flag,
                    tts_flag,
                    camera1,
                    camera2,
                    camera3,
                    camera4
                     });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }*/
    
    /**
     * 获取终端版本信息
     * @return
     */
    public String getDeviceStatusByEnterpriseId() {
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
            if(null == queryObj) {
                queryObj = new TerminalDeviceStatus();
                queryObj.setEnterprise_id("111");
            }
            if(null == queryObj.getEnterprise_id() || "".equals(queryObj.getEnterprise_id())){
                queryObj.setEnterprise_id("111");
            }
            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("NewTerminalDeviceStatus.getTerminalDeviceStatusCount", queryObj);

            List<TerminalDeviceStatus> list = service.getObjectsByPage(
                    "NewTerminalDeviceStatus.getTerminalDeviceStatus", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));


            // 显示提示信息
//            if (null != message) {
//                addActionMessage(getText(message));
//            }

            this.map = getPagination(list, totalCount, pageIndex,rpNum);
            
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered terminal's device status error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered terminal's device status error:" + e.getMessage());
            return ERROR;
        } finally {
            addOperationLog("查询已注册终端设备状态");
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


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getDevice_status_map() {
        return device_status_map;
    }

    public void setDevice_status_map(Map device_status_map) {
        this.device_status_map = device_status_map;
    }

    public TerminalDeviceStatus getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(TerminalDeviceStatus queryObj) {
        this.queryObj = queryObj;
    }

}
