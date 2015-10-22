package com.neusoft.clw.yw.xj.drivingrecords.ds;

public class VehcileInfo {
    // 车辆VIN
    private String vehicle_vin;

    // 车辆id
    private String vehicle_id;

    // 车辆内部编码
    private String vehicle_code;

    // 车牌号
    private String vehicle_ln;

    // 所属企业
    private String enterprise_id;

    // 企业名称
    private String enterprise_name;

    // 所属车主
    private String user_id;

    // 车主姓名
    private String user_name;

    // add by jinp start
    private String sortname;

    private String sortorder;

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

    // add by jinp stop

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    // 所属线路
    private String route_id;

    // 线路名称
    private String route_name;

    // 所属组织结构
    private String organization_id;

    // 所属不良驾驶定义
    private String cr_config_id;

    // 所属驾驶员
    private String driver_id;

    // 驾驶员名称
    private String driver_name;

    // 发动机号
    private String engine_number;

    // 发动机型号
    private String engine_type;

    // 品牌
    private String brand;

    // 车辆类型
    private String vehicle_type_id;

    // 车身颜色
    private String vehicle_color;

    // 载客限员
    private String limite_number;

    // 标准油耗
    private String standard_oil;

    // 自重
    private String dead_load;

    // 售价
    private String sale_price;

    // 排量
    private String out_number;

    // 轮胎滚动半径
    private double tyre_r;

    // 后桥速比
    private double rear_axle_rate;

    // 创建人
    private String creater;

    // 创建时间
    private String create_time;

    // 最后修改人
    private String modifier;

    // 最后修改时间
    private String modify_time;

    // 所属企业全名称
    private String short_allname;

    // 描述
    private String vehicle_desc;

    // 分配车辆数组
    private String[] selectveh;

    // 车辆分配线路标志位
    private boolean choiceflag;

    // 区域组ID
    private String group_id;

    // sim卡号
    private String sim_card_number;

    // 区域组删除的车辆id
    private String oldids;

    // 区域组新增的车辆id
    private String newids;

    // 与区域组有关的取消的id
    private String cancleids;

    // 与区域有关的设置的id
    private String carsetids;

    // 车工号
    private String vehicle_number;

    // 车型描述
    private String vehtypename;

    // 发动机型号名称
    private String entypename;

    // 车辆品牌
    private String vehbrand;

    // 发动机品牌名称
    private String enbrandname;

    public String getVehtypename() {
        return vehtypename;
    }

    public void setVehtypename(String vehtypename) {
        this.vehtypename = vehtypename;
    }

    public String getEntypename() {
        return entypename;
    }

    public void setEntypename(String entypename) {
        this.entypename = entypename;
    }

    public String getVehbrand() {
        return vehbrand;
    }

    public void setVehbrand(String vehbrand) {
        this.vehbrand = vehbrand;
    }

    public String getEnbrandname() {
        return enbrandname;
    }

    public void setEnbrandname(String enbrandname) {
        this.enbrandname = enbrandname;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getCancleids() {
        return cancleids;
    }

    public void setCancleids(String cancleids) {
        this.cancleids = cancleids;
    }

    public String getCarsetids() {
        return carsetids;
    }

    public void setCarsetids(String carsetids) {
        this.carsetids = carsetids;
    }

    public String getOldids() {
        return oldids;
    }

    public void setOldids(String oldids) {
        this.oldids = oldids;
    }

    public String getNewids() {
        return newids;
    }

    public void setNewids(String newids) {
        this.newids = newids;
    }

    public String getSim_card_number() {
        return sim_card_number;
    }

    public void setSim_card_number(String sim_card_number) {
        this.sim_card_number = sim_card_number;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public boolean isChoiceflag() {
        return choiceflag;
    }

    public void setChoiceflag(boolean choiceflag) {
        this.choiceflag = choiceflag;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String[] getSelectveh() {
        return selectveh;
    }

    public void setSelectveh(String[] selectveh) {
        this.selectveh = selectveh;
    }

    public String getVehicle_desc() {
        return vehicle_desc;
    }

    public void setVehicle_desc(String vehicle_desc) {
        this.vehicle_desc = vehicle_desc;
    }

    public String getEnterprise_name() {
        return enterprise_name;
    }

    public void setEnterprise_name(String enterprise_name) {
        this.enterprise_name = enterprise_name;
    }

    public String getShort_allname() {
        return short_allname;
    }

    public void setShort_allname(String short_allname) {
        this.short_allname = short_allname;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getVehicle_code() {
        return vehicle_code;
    }

    public void setVehicle_code(String vehicle_code) {
        this.vehicle_code = vehicle_code;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getCr_config_id() {
        return cr_config_id;
    }

    public void setCr_config_id(String cr_config_id) {
        this.cr_config_id = cr_config_id;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getEngine_number() {
        return engine_number;
    }

    public void setEngine_number(String engine_number) {
        this.engine_number = engine_number;
    }

    public String getEngine_type() {
        return engine_type;
    }

    public void setEngine_type(String engine_type) {
        this.engine_type = engine_type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getVehicle_type_id() {
        return vehicle_type_id;
    }

    public void setVehicle_type_id(String vehicle_type_id) {
        this.vehicle_type_id = vehicle_type_id;
    }

    public String getVehicle_color() {
        return vehicle_color;
    }

    public void setVehicle_color(String vehicle_color) {
        this.vehicle_color = vehicle_color;
    }

    public String getLimite_number() {
        return limite_number;
    }

    public void setLimite_number(String limite_number) {
        this.limite_number = limite_number;
    }

    public String getStandard_oil() {
        return standard_oil;
    }

    public void setStandard_oil(String standard_oil) {
        this.standard_oil = standard_oil;
    }

    public String getDead_load() {
        return dead_load;
    }

    public void setDead_load(String dead_load) {
        this.dead_load = dead_load;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getOut_number() {
        return out_number;
    }

    public void setOut_number(String out_number) {
        this.out_number = out_number;
    }

    public double getTyre_r() {
        return tyre_r;
    }

    public void setTyre_r(double tyre_r) {
        this.tyre_r = tyre_r;
    }

    public double getRear_axle_rate() {
        return rear_axle_rate;
    }

    public void setRear_axle_rate(double rear_axle_rate) {
        this.rear_axle_rate = rear_axle_rate;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getValid_flag() {
        return valid_flag;
    }

    public void setValid_flag(String valid_flag) {
        this.valid_flag = valid_flag;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    // 车辆是否有效标记
    private String valid_flag;

    // 企业简称
    private String short_name;

}
