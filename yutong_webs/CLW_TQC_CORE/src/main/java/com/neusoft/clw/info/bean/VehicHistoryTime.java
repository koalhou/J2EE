package com.neusoft.clw.info.bean;

import java.util.Date;

public class VehicHistoryTime {
    private String VEHICLE_VIN; //车辆vin
    private Date SEC_DATA_TIME; // 秒数据最新时间
    private Date MIN1_DATA_TIME; // 1分钟数据最新时间
    private Date MIN5_DATA_TIME; //5分钟数据最新时间
    private Date LOGIN_DATA_TIME; //登陆记录最新时间
    private Date OVERSPEED_DATA_TIME; //超速记录最新时间
    private Date ONOFF_DATA_TIME; //开关量记录最新时间
    private Date RAPID_DATE_TIME; // 急加速记录最新时间
    private Date FATIGUE_TIME; //统计疲劳驾驶最新时间
    
    public String getVEHICLE_VIN() {
        return VEHICLE_VIN;
    }
    public void setVEHICLE_VIN(String vehicle_vin) {
        VEHICLE_VIN = vehicle_vin;
    }
    public Date getSEC_DATA_TIME() {
        return SEC_DATA_TIME;
    }
    public void setSEC_DATA_TIME(Date sec_data_time) {
        SEC_DATA_TIME = sec_data_time;
    }
    public Date getMIN1_DATA_TIME() {
        return MIN1_DATA_TIME;
    }
    public void setMIN1_DATA_TIME(Date min1_data_time) {
        MIN1_DATA_TIME = min1_data_time;
    }
    public Date getMIN5_DATA_TIME() {
        return MIN5_DATA_TIME;
    }
    public void setMIN5_DATA_TIME(Date min5_data_time) {
        MIN5_DATA_TIME = min5_data_time;
    }
    public Date getLOGIN_DATA_TIME() {
        return LOGIN_DATA_TIME;
    }
    public void setLOGIN_DATA_TIME(Date login_data_time) {
        LOGIN_DATA_TIME = login_data_time;
    }
    public Date getOVERSPEED_DATA_TIME() {
        return OVERSPEED_DATA_TIME;
    }
    public void setOVERSPEED_DATA_TIME(Date overspeed_data_time) {
        OVERSPEED_DATA_TIME = overspeed_data_time;
    }
    public Date getONOFF_DATA_TIME() {
        return ONOFF_DATA_TIME;
    }
    public void setONOFF_DATA_TIME(Date onoff_data_time) {
        ONOFF_DATA_TIME = onoff_data_time;
    }
    public Date getRAPID_DATE_TIME() {
        return RAPID_DATE_TIME;
    }
    public void setRAPID_DATE_TIME(Date rapid_date_time) {
        RAPID_DATE_TIME = rapid_date_time;
    }
    public Date getFATIGUE_TIME() {
        return FATIGUE_TIME;
    }
    public void setFATIGUE_TIME(Date fatigue_time) {
        FATIGUE_TIME = fatigue_time;
    }
}
