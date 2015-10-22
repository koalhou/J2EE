/*******************************************************************************
 * @(#)HandheldDeviceInfo.java 2012-3-13
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.cl.handheldDevice.ds;

/**
 * 手持设备信息bean
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-13 上午09:27:56
 */
public class HandheldDeviceInfo {
    /** 企业ID **/
    private String entipriseId = "";
    /** 企业名称 **/
    private String entipriseName = "";
    /** 企业编号 **/
    private String enterpriseCode = "";
    /** 手机IMEI号 **/
    private String cellPhoneImei = "";
    /** 终端设备编号 **/
    private String handheldDeviceNo = "";
    /** 手机号 **/
    private String cellPhone = "";
    /** 操作人 **/
    private String operator = "";
    /** 车辆表ID **/
    private String vehicleId = "";
    /** 终端表ID **/
    private String terminalId = "";
    /** SIM卡信息表ID **/
    private String simId = "";
    /** 最后修改人 **/
    private String modifier = "";
    /** 最后修改时间 **/
    private String modifyTime = "";
    /** 注册人 **/
    private String registrant = "";
    /** 注册时间 **/
    private String registrationTime = "";
    private String sortname;
    private String sortorder;
    
    public String getEntipriseId() {
        return entipriseId;
    }
    public void setEntipriseId(String entipriseId) {
        this.entipriseId = entipriseId;
    }
    public String getEntipriseName() {
        return entipriseName;
    }
    public void setEntipriseName(String entipriseName) {
        this.entipriseName = entipriseName;
    }
    public String getEnterpriseCode() {
        return enterpriseCode;
    }
    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }
    public String getCellPhoneImei() {
        return cellPhoneImei;
    }
    public void setCellPhoneImei(String cellPhoneImei) {
        this.cellPhoneImei = cellPhoneImei;
    }
    public String getHandheldDeviceNo() {
        return handheldDeviceNo;
    }
    public void setHandheldDeviceNo(String handheldDeviceNo) {
        this.handheldDeviceNo = handheldDeviceNo;
    }
    public String getCellPhone() {
        return cellPhone;
    }
    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public String getTerminalId() {
        return terminalId;
    }
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
    public String getSimId() {
        return simId;
    }
    public void setSimId(String simId) {
        this.simId = simId;
    }
    public String getModifier() {
        return modifier;
    }
    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public String getModifyTime() {
        return modifyTime;
    }
    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getRegistrant() {
        return registrant;
    }
    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }
    public String getRegistrationTime() {
        return registrationTime;
    }
    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }
    public String getSortname() {
        return sortname;
    }
    public void setSortname(String sortname) {
        this.sortname = sortname;
    }
    public String getSortorder() {
        return sortorder;
    }
    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }
}
