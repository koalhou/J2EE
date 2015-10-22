package com.neusoft.clw.yw.cs.simflux.ds;

/**
 * SIM卡流量信息bean
 * @author JinPeng
 */
public class SimFluxInfo {
    /** 序号 **/
    private String rowNumber = "";

    /** SIM卡ID **/
    private String simId = "";

    /** SIM卡号 **/
    private String simCardNumber = "";

    /** 手机号 **/
    private String cellPhone = "";

    /** 运营商 **/
    private String businessId = "";

    /** 运营商名称 **/
    private String businessName = "";

    /** 流量 **/
    private String fluxValue = "";

    /** 通话时间 **/
    private String callTime = "";

    /** 截止时间 **/
    private String closeTime = "";

    /** 创建人 **/
    private String creater = "";

    /** 创建时间 **/
    private String createTime = "";

    /** 修改人 **/
    private String modifier = "";

    /** 修改时间 **/
    private String modifyTime = "";

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public String getSimCardNumber() {
        return simCardNumber.trim();
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getFluxValue() {
        return fluxValue;
    }

    public void setFluxValue(String fluxValue) {
        this.fluxValue = fluxValue;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
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
}
