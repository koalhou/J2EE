package com.neusoft.clw.yw.zd.protocal.ds;

/**
 * 终端协议信息bean
 * @author JinPeng
 */
public class ProtocalInfo {
    /** 序号 **/
    private String rowNumber = "";

    /** 终端协议ID **/
    private String protocalId = "";

    /** 终端协议名称 **/
    private String protocalName = "";

    /** 终端厂家ID **/
    private String oemId = "";

    /** 终端厂家名称 **/
    private String oemName = "";

    /** 终端型号ID **/
    private String typeId = "";

    /** 终端型号名称 **/
    private String typeName = "";

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

    public String getProtocalId() {
        return protocalId;
    }

    public void setProtocalId(String protocalId) {
        this.protocalId = protocalId;
    }

    public String getProtocalName() {
        return protocalName;
    }

    public void setProtocalName(String protocalName) {
        this.protocalName = protocalName;
    }

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public String getOemName() {
        return oemName;
    }

    public void setOemName(String oemName) {
        this.oemName = oemName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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
