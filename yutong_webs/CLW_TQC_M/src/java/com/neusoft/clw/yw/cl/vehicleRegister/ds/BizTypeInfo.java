package com.neusoft.clw.yw.cl.vehicleRegister.ds;

/**
 * 行业应用信息bean
 * @author JinPeng
 */
public class BizTypeInfo {
    /** 主键 **/
    private String tbizId = "";

    /** 终端号 **/
    private String terminalCode = "";

    /** 车辆VIN号 **/
    private String vehicleVin = "";
    
    /** 行业应用ID **/
    private String bizId = "";

    /** 行业应用名 **/
    private String bizName = "";

    /** 创建人 **/
    private String creater = "";

    /** 创建时间 **/
    private String createTime = "";

    /** 修改人 **/
    private String modifier = "";

    /** 修改时间 **/
    private String modifyTime = "";

    /** 复选框选择状态 **/
    private boolean choiceFlag = false;

    public String getTbizId() {
        return tbizId;
    }

    public void setTbizId(String tbizId) {
        this.tbizId = tbizId;
    }

    public String getTerminalCode() {
        return terminalCode;
    }

    public void setTerminalCode(String terminalCode) {
        this.terminalCode = terminalCode;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
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

    public boolean isChoiceFlag() {
        return choiceFlag;
    }

    public void setChoiceFlag(boolean choiceFlag) {
        this.choiceFlag = choiceFlag;
    }
}
