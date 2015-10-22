package com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain;

/**
 * 报警
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 2, 2011 3:25:14 PM
 */
public class Alarm {
    private String alarm_id;

    private String vehicle_ln;

    private String vehicle_vin;

    private String alarm_type_name;

    private String alarm_type_comments;

    private String deal_flag;

    private String alarm_time;

    private String enterprise_id;

    private String latitude;

    private String longitude;

    private String speeding;

    private String organization_id;

    private String userid;

    // add by huangmb (engine_rotate_speed发动机转速\mileage总里程\direction方向)
    private String engine_rotate_speed;

    private String mileage;

    private String direction;
    
    private String color;
    
    public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getEngine_rotate_speed() {
        return engine_rotate_speed;
    }

    public void setEngine_rotate_speed(String engine_rotate_speed) {
        this.engine_rotate_speed = engine_rotate_speed;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

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

    public String getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(String alarm_id) {
        this.alarm_id = alarm_id;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
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

    public String getSpeeding() {
        return speeding;
    }

    public void setSpeeding(String speeding) {
        this.speeding = speeding;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public String getAlarm_type_name() {
        return alarm_type_name;
    }

    public void setAlarm_type_name(String alarm_type_name) {
        this.alarm_type_name = alarm_type_name;
    }

    public String getAlarm_type_comments() {
        return alarm_type_comments;
    }

    public void setAlarm_type_comments(String alarm_type_comments) {
        this.alarm_type_comments = alarm_type_comments;
    }

    public String getDeal_flag() {
        return deal_flag;
    }

    public void setDeal_flag(String deal_flag) {
        this.deal_flag = deal_flag;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

}
