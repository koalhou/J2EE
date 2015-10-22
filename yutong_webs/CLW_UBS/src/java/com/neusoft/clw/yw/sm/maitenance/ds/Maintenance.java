package com.neusoft.clw.yw.sm.maitenance.ds;

public class Maintenance {

    // 维保项目编码
    private String item_id;

    // 维保项目类型编码
    private String type_id;
    
    // 维保项目类型名称
    private String type_name;

    // 维保设置配置编号
    private String config_id;

    // 车架号
    private String vehicle_vin;

    // 车工号
    private String vehicle_number;

    // 新车报到条件
    private String conditionNewCar;

    // 新车报到提醒条件
    private String conditionRemindNewCar;

    // 走保条件
    private String conditionGo;

    // 走保提醒条件
    private String conditionRemindGo;

    // 强保条件
    private String conditionCompulsory;

    // 强保提醒条件
    private String conditionRemindCompulsory;

    // 高档车强保条件
    private String conditionCompulsoryLuxury;

    // 高档车强保提醒条件
    private String conditionRemindCompulsoryLuxury;

    // 高档车条件
    private String condition_luxury;

    // 创建时间
    private String create_time;

    // 修改时间
    private String modify_time;

    // 创建人
    private String creater_id;

    // 最后修改人
    private String modifier_id;

    // 维保设置启用状态 0:启用,2:禁用
    private String validate_flag;

    // 是否提醒,0:提醒,1:不提醒
    private String remind_flag;

    // 公司
    private String company;
    
    private String enterprise_id;

    // 分公司
    private String branch;

    // 车队
    private String vehicle_fleet;

    // 车牌号
    private String vehicle_ln;

    // 排序字段
    private String sortname;

    // 升序或者降序
    private String sortorder;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getCondition_luxury() {
        return condition_luxury;
    }

    public void setCondition_luxury(String condition_luxury) {
        this.condition_luxury = condition_luxury;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getCreater_id() {
        return creater_id;
    }

    public void setCreater_id(String creater_id) {
        this.creater_id = creater_id;
    }

    public String getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(String modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getValidate_flag() {
        return validate_flag;
    }

    public void setValidate_flag(String validate_flag) {
        this.validate_flag = validate_flag;
    }

    public String getRemind_flag() {
        return remind_flag;
    }

    public void setRemind_flag(String remind_flag) {
        this.remind_flag = remind_flag;
    }

    public String getConditionNewCar() {
        return conditionNewCar;
    }

    public void setConditionNewCar(String conditionNewCar) {
        this.conditionNewCar = conditionNewCar;
    }

    public String getConditionRemindNewCar() {
        return conditionRemindNewCar;
    }

    public void setConditionRemindNewCar(String conditionRemindNewCar) {
        this.conditionRemindNewCar = conditionRemindNewCar;
    }

    public String getConditionGo() {
        return conditionGo;
    }

    public void setConditionGo(String conditionGo) {
        this.conditionGo = conditionGo;
    }

    public String getConditionRemindGo() {
        return conditionRemindGo;
    }

    public void setConditionRemindGo(String conditionRemindGo) {
        this.conditionRemindGo = conditionRemindGo;
    }

    public String getConditionCompulsory() {
        return conditionCompulsory;
    }

    public void setConditionCompulsory(String conditionCompulsory) {
        this.conditionCompulsory = conditionCompulsory;
    }

    public String getConditionRemindCompulsory() {
        return conditionRemindCompulsory;
    }

    public void setConditionRemindCompulsory(String conditionRemindCompulsory) {
        this.conditionRemindCompulsory = conditionRemindCompulsory;
    }

    public String getConditionCompulsoryLuxury() {
        return conditionCompulsoryLuxury;
    }

    public void setConditionCompulsoryLuxury(String conditionCompulsoryLuxury) {
        this.conditionCompulsoryLuxury = conditionCompulsoryLuxury;
    }

    public String getConditionRemindCompulsoryLuxury() {
        return conditionRemindCompulsoryLuxury;
    }

    public void setConditionRemindCompulsoryLuxury(
            String conditionRemindCompulsoryLuxury) {
        this.conditionRemindCompulsoryLuxury = conditionRemindCompulsoryLuxury;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getVehicle_fleet() {
        return vehicle_fleet;
    }

    public void setVehicle_fleet(String vehicle_fleet) {
        this.vehicle_fleet = vehicle_fleet;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getConfig_id() {
        return config_id;
    }

    public void setConfig_id(String config_id) {
        this.config_id = config_id;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }
    
}
