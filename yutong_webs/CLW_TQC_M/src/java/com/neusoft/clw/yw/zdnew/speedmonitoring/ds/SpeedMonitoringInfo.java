/*******************************************************************************
 * @(#)SpeedMonitoringInfo.java 2012-7-5
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.zdnew.speedmonitoring.ds;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-7-5 下午01:17:53
 */
public class SpeedMonitoringInfo {
    /** 车辆ID **/
    private String vehicleId = "";
    /** 车牌号 **/
    private String vehicleLn = "";
    /** GPS速度 **/
    private String gpsSpeed = "";
    /** VSS速度 **/
    private String vssSpeed = "";
    /** 偏差比例 **/
    private String offsetScale = "";
    /** 汇报时间 **/
    private String terminalTime = "";
    /** 汇报时间 **/
    private String dealStatus = "";
    /** 车辆VIN **/
    private String vehicleVin = "";
    /** SIM卡号 **/
    private String simCardNo = "";
    /** 操作人 **/
    private String userId = "";
    
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getVehicleLn() {
        return vehicleLn;
    }
    public void setVehicleLn(String vehicleLn) {
        this.vehicleLn = vehicleLn;
    }
    public String getGpsSpeed() {
        return gpsSpeed;
    }
    public void setGpsSpeed(String gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }
    public String getVssSpeed() {
        return vssSpeed;
    }
    public void setVssSpeed(String vssSpeed) {
        this.vssSpeed = vssSpeed;
    }
    public String getOffsetScale() {
        return offsetScale;
    }
    public void setOffsetScale(String offsetScale) {
        this.offsetScale = offsetScale;
    }
    public String getTerminalTime() {
        return terminalTime;
    }
    public void setTerminalTime(String terminalTime) {
        this.terminalTime = terminalTime;
    }
    public String getDealStatus() {
        return dealStatus;
    }
    public void setDealStatus(String dealStatus) {
        this.dealStatus = dealStatus;
    }
    public String getVehicleVin() {
        return vehicleVin;
    }
    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }
    public String getSimCardNo() {
        return simCardNo;
    }
    public void setSimCardNo(String simCardNo) {
        this.simCardNo = simCardNo;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
