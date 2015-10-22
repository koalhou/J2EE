package com.neusoft.clw.yw.zdnew.terminalversions.ds;


/**
 * 终端属性信息bean
 * @author JinPeng
 */
public class TerminalVersionsInfo {
    /** 序号 **/
    private String rowNumber = "";
    
    /** 平台注册车牌号 **/
    private String vehicle_ln = "";

    /** 终端上报车牌号 **/
    private String hardware_vehicle_ln = "";
    
    /** 平台注册VIN **/
    private String vehicle_vin = "";

    /** 终端上报VIN **/
    private String hardware_vehicle_vin = "";

    /** 平台注册终端号 **/
    private String terminal_id = "";

    /** 终端上报终端号 **/
    private String hardware_terminal_id = "";
    
    /** 平台注册车牌颜色 **/
    private String veh_pai_color = "";

    /** 终端上报车牌颜色 **/
    private String hardware_veh_pai_color = "";

    /** 手机号 **/
    private String cellphone = "";

    /** ICCID **/
    private String sim_sccid = "";

    /** 主机硬件版本 **/
    private String host_hard_ver = "";

    /** 主机固件版本 **/
    private String host_firm_ver = "";

    /** 显示屏硬件版本 **/
    private String xianshi_hard_ver = "";

    /** 显示屏固件版本 **/
    private String xianshi_firm_ver = "";

    /** DVR硬件版本 **/
    private String dvr_hard_ver = "";

    /** DVR固件版本 **/
    private String dvr_firm_ver = "";

    /** 射频读卡器硬件版本 **/
    private String shepin_hard_ver = "";

    /** 射频读卡器固件版本 **/
    private String shepin_firm_ver = "";

    /** 上报时间 **/
    private String terminal_time = "";
    
    /** 企业ID **/
    private String enterprise_id = ""; 
    
    /** 排序字段 **/
    private String sortname = "";
    
    /** 排序规则 **/
    private String sortorder = "";     

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

    public String getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(String rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getHardware_vehicle_ln() {
        return hardware_vehicle_ln;
    }

    public void setHardware_vehicle_ln(String hardware_vehicle_ln) {
        this.hardware_vehicle_ln = hardware_vehicle_ln;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getHardware_vehicle_vin() {
        return hardware_vehicle_vin;
    }

    public void setHardware_vehicle_vin(String hardware_vehicle_vin) {
        this.hardware_vehicle_vin = hardware_vehicle_vin;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getHardware_terminal_id() {
        return hardware_terminal_id;
    }

    public void setHardware_terminal_id(String hardware_terminal_id) {
        this.hardware_terminal_id = hardware_terminal_id;
    }

    public String getVeh_pai_color() {
        return veh_pai_color;
    }

    public void setVeh_pai_color(String veh_pai_color) {
        this.veh_pai_color = veh_pai_color;
    }

    public String getHardware_veh_pai_color() {
        return hardware_veh_pai_color;
    }

    public void setHardware_veh_pai_color(String hardware_veh_pai_color) {
        this.hardware_veh_pai_color = hardware_veh_pai_color;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getSim_sccid() {
        return sim_sccid;
    }

    public void setSim_sccid(String sim_sccid) {
        this.sim_sccid = sim_sccid;
    }

    public String getHost_hard_ver() {
        return host_hard_ver;
    }

    public void setHost_hard_ver(String host_hard_ver) {
        this.host_hard_ver = host_hard_ver;
    }

    public String getHost_firm_ver() {
        return host_firm_ver;
    }

    public void setHost_firm_ver(String host_firm_ver) {
        this.host_firm_ver = host_firm_ver;
    }

    public String getXianshi_hard_ver() {
        return xianshi_hard_ver;
    }

    public void setXianshi_hard_ver(String xianshi_hard_ver) {
        this.xianshi_hard_ver = xianshi_hard_ver;
    }

    public String getXianshi_firm_ver() {
        return xianshi_firm_ver;
    }

    public void setXianshi_firm_ver(String xianshi_firm_ver) {
        this.xianshi_firm_ver = xianshi_firm_ver;
    }

    public String getDvr_hard_ver() {
        return dvr_hard_ver;
    }

    public void setDvr_hard_ver(String dvr_hard_ver) {
        this.dvr_hard_ver = dvr_hard_ver;
    }

    public String getDvr_firm_ver() {
        return dvr_firm_ver;
    }

    public void setDvr_firm_ver(String dvr_firm_ver) {
        this.dvr_firm_ver = dvr_firm_ver;
    }

    public String getShepin_hard_ver() {
        return shepin_hard_ver;
    }

    public void setShepin_hard_ver(String shepin_hard_ver) {
        this.shepin_hard_ver = shepin_hard_ver;
    }

    public String getShepin_firm_ver() {
        return shepin_firm_ver;
    }

    public void setShepin_firm_ver(String shepin_firm_ver) {
        this.shepin_firm_ver = shepin_firm_ver;
    }

    public String getTerminal_time() {
        return terminal_time;
    }

    public void setTerminal_time(String terminal_time) {
        this.terminal_time = terminal_time;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    } 

}
