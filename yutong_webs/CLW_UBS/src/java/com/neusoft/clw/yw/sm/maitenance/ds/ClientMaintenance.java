package com.neusoft.clw.yw.sm.maitenance.ds;

/**
 * 客户端维保记录
 * @author <a href="mailto:huangmb@neusoft.com">huangmb</a>
 * @version $Revision 1.1 $ 2011-9-4 上午11:26:45
 */
public class ClientMaintenance {
    // 维保计划号
    private String maint_num;

    // 车牌号
    private String vehicle_ln;

    // 车辆VIN
    private String vehicle_vin;

    // 维保日期
    private String maint_date;

    // 公司名称
    private String company;

    // 分公司
    private String branch;

    // 车型
    private String vehicle_type;

    // 车队
    private String vehicle_fleet;

    // 维保记录
    private String maint_record;

    public String getMaint_num() {
        return maint_num;
    }

    public void setMaint_num(String maint_num) {
        this.maint_num = maint_num;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getMaint_date() {
        return maint_date;
    }

    public void setMaint_date(String maint_date) {
        this.maint_date = maint_date;
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

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_fleet() {
        return vehicle_fleet;
    }

    public void setVehicle_fleet(String vehicle_fleet) {
        this.vehicle_fleet = vehicle_fleet;
    }

    public String getMaint_record() {
        return maint_record;
    }

    public void setMaint_record(String maint_record) {
        this.maint_record = maint_record;
    }

}
