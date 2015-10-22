package com.neusoft.clw.yw.cl.vehicleRegister.ds;

import java.util.ArrayList;
import java.util.List;

/**
 * 车辆注册信息bean
 * @author JinPeng
 */
public class VehicleRegisterInfo {
    /** 车辆注册信息ID * */
    private String vehicleRegisterId = "";

    /** 车辆ID * */
    private String vehicleId = "";

    /** 车牌号 * */
    private String vehicleLn = "";

    /** 车辆VIN号 * */
    private String vehicleVin = "";

    /** 终端ID * */
    private String terminalId = "";

    /** 终端ID ,修改时保存老的ID* */
    private String terminalOldId = "";

    /** 终端号 * */
    private String terminalCode = "";

    /** SIM卡ID * */
    private String simId = "";

    /** SIM卡号 * */
    private String simCardNumber = "";

    /** 旧SIM卡号 **/
    private String oldSimCardNumber = "";
    
    private String cellPhone = "";
    
    /** 企业代码 **/
    private String enterpriseCode = "";

    /** 企业ID * */
    private String entipriseId = "";

    /** 旧企业ID **/
    private String oldEnterpriseId = "";
    
    /** 企业名称 * */
    private String entipriseName = "";

    /** 车主ID * */
    private String userId = "";

    /** 车主名称 * */
    private String userName = "";

    /** 行业应用ID * */
    // private String bizId = "";
    /** 行业应用名称 * */
    // private String bizName = "";
    /** 缴费标记 * */
    private String feeFlag = "0";

    /** 缴费状态 * */
    private String feeStatus = "";

    /** 创建人 * */
    private String creater = "";

    /** 创建时间 * */
    private String createTime = "";

    /** 修改人 * */
    private String modifier = "";

    /** 修改时间 * */
    private String modifyTime = "";

    /** 注册时间 **/
    private String registrationTime = "";

    /** 注册人 **/
    private String registrant = "";

    /** 安装方式 0：前装 1：后装 **/
    private String fixType = "0";

    /** '0：未出厂 1：已出厂'; * */
    private String deliveryFlag = "0";

    private String latitude = "";

    private String longitude = "";

    private String videoId = "";
    
    /** 行业应用相关信息 * */
    private List < BizTypeInfo > bizTypeList = new ArrayList < BizTypeInfo >();

    public String getVehicleRegisterId() {
        return vehicleRegisterId;
    }

    public void setVehicleRegisterId(String vehicleRegisterId) {
        this.vehicleRegisterId = vehicleRegisterId;
    }

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

    public String getVehicleVin() {
        if(vehicleVin != null) {
            vehicleVin = vehicleVin.trim(); 
        }
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getTerminalId() {
        if(terminalId != null) {
            terminalId = terminalId.trim(); 
        }
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTerminalCode() {
        if(terminalCode != null) {
            terminalCode = terminalCode.trim(); 
        }
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public String getSimCardNumber() {
        if(simCardNumber != null) {
            simCardNumber = simCardNumber.trim(); 
        }
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // public String getBizId() {
    // return bizId;
    // }
    // public void setBizId(String bizId) {
    // this.bizId = bizId;
    // }
    // public String getBizName() {
    // return bizName;
    // }
    // public void setBizName(String bizName) {
    // this.bizName = bizName;
    // }
    public String getFeeFlag() {
        return feeFlag;
    }

    public void setFeeFlag(String feeFlag) {
        this.feeFlag = feeFlag;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public List < BizTypeInfo > getBizTypeList() {
        return bizTypeList;
    }

    public void setBizTypeList(List < BizTypeInfo > bizTypeList) {
        this.bizTypeList = bizTypeList;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getRegistrant() {
        return registrant;
    }

    public void setRegistrant(String registrant) {
        this.registrant = registrant;
    }

    public String getFixType() {
        return fixType;
    }

    public void setFixType(String fixType) {
        this.fixType = fixType;
    }

    public String getDeliveryFlag() {
        return deliveryFlag;
    }

    public void setDeliveryFlag(String deliveryFlag) {
        this.deliveryFlag = deliveryFlag;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTerminalOldId() {
        return terminalOldId;
    }

    public void setTerminalOldId(String terminalOldId) {
        this.terminalOldId = terminalOldId;
    }

    public String getOldEnterpriseId() {
        return oldEnterpriseId;
    }

    public void setOldEnterpriseId(String oldEnterpriseId) {
        this.oldEnterpriseId = oldEnterpriseId;
    }

    public String getOldSimCardNumber() {
        return oldSimCardNumber;
    }

    public void setOldSimCardNumber(String oldSimCardNumber) {
        this.oldSimCardNumber = oldSimCardNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
