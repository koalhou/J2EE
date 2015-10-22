package com.neusoft.clw.yw.popup.vehicle.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.popup.vehicle.ds.VehicleBrowseInfo;

/**
 * 车辆选择popup子页面action
 * @author JinPeng
 */
public class VehicleBrowseAction extends BaseAction {
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 车辆VIN（查询条件） **/
    private String vehicleVin = "";

    /** 车牌号（查询条件） **/
    private String vehicleLn = "";

    /** 车辆列表 **/
    private List < VehicleBrowseInfo > vehicleList = new ArrayList < VehicleBrowseInfo >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("vehicleVin", vehicleVin);
            map.put("vehicleLn", vehicleLn);

            vehicleList = (List < VehicleBrowseInfo >) service.getObjects(
                    "VehicleBrowse.getVehicleInfos", map);

            if (vehicleList != null && vehicleList.size() == 0) {
                // 无车辆信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
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

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getVehicleLn() {
        return vehicleLn;
    }

    public void setVehicleLn(String vehicleLn) {
        this.vehicleLn = vehicleLn;
    }

    public List < VehicleBrowseInfo > getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List < VehicleBrowseInfo > vehicleList) {
        this.vehicleList = vehicleList;
    }
}
