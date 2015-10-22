package com.neusoft.clw.yw.cs.sim.ds;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SIM卡信息bean
 * @author JinPeng
 */
public class SimInfo {
    /** 序号 **/
    private String rowNumber = "";

    /** SIM卡ID **/
    private String simId = "";

    /** SIM卡号 **/
    private String simCardNumber = "";
    
    /** 旧SIM卡号 **/
    private String oldSimCardNumber = "";
    
    /** 电子ICCID **/
    private String iccidElectron = "";

    /** 印刷ICCID **/
    private String iccidPrint = "";

    /** 手机号 **/
    private String cellPhone = "";

    /** 运营商 **/
    private String businessId = "";

    /** 运营商名称 **/
    private String businessName = "";

    /** 开卡时间 **/
    private String startUseTime = "";

    /** 创建人 **/
    private String creater = "";

    /** 创建时间 **/
    private String createTime = "";

    /** 修改人 **/
    private String modifier = "";

    /** 修改时间 **/
    private String modifyTime = "";

    /** APN类型 **/
    private String apnType = "";

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

    public String getIccidElectron() {
        return iccidElectron;
    }

    public void setIccidElectron(String iccidElectron) {
        this.iccidElectron = iccidElectron;
    }

    public String getIccidPrint() {
        return iccidPrint;
    }

    public void setIccidPrint(String iccidPrint) {
        this.iccidPrint = iccidPrint;
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

    public String getStartUseTime() {
        if (startUseTime == "" || startUseTime == null) {
            // 获取当前时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            startUseTime = sdf.format(new Date());
        }
        return startUseTime;
    }

    public void setStartUseTime(String startUseTime) {
        this.startUseTime = startUseTime;
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

    public String getApnType() {
        return apnType;
    }

    public void setApnType(String apnType) {
        this.apnType = apnType;
    }

    public String getOldSimCardNumber() {
        return oldSimCardNumber;
    }

    public void setOldSimCardNumber(String oldSimCardNumber) {
        this.oldSimCardNumber = oldSimCardNumber;
    }
    
}
