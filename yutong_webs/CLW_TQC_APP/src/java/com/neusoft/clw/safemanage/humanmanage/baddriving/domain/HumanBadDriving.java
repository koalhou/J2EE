package com.neusoft.clw.safemanage.humanmanage.baddriving.domain;

public class HumanBadDriving {

    private String malarmd_id;

    private String alarm_type_id;

    private String alarm_type_name;

    private String short_name;

    private String alarm_date;

    private String route_name;

    private String vehicle_vin;

    private String vehicle_id;

    private String vehicle_ln;

    private String vehicle_code;

    private String alarm_start_time;

    private String alarm_end_time;

    private String alarm_time;

    private String alarm_start_speed;

    private String alarm_start_rpm;
    
    private String eventValue;
    
    private String eventUnit;

    // 告警开始时纬度
    private String alarm_start_latitude;

    // 告警开始时经度
    private String alarm_start_longitude;

    private String organization_id;

    private String enterprise_id;

    private int mypgnum;

    private String drivingoldorgid;

    private String sortname;

    private String sortorder;

    private String latitude;

    private String longitude;

    // add by jinp start
    private String time_list;

    private String selectradio;

    private String start_time;

    private String end_time;
    
    private String driver_name;
    private String driver_id;
    
    public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}

	public String getDriver_name() {
		return driver_name;
	}

	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}

	public String getEventValue() {
		return eventValue;
	}

	public void setEventValue(String eventValue) {
		this.eventValue = eventValue;
	}

	public String getEventUnit() {
		return eventUnit;
	}

	public void setEventUnit(String eventUnit) {
		this.eventUnit = eventUnit;
	}

	public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getSelectradio() {
        return selectradio;
    }

    public void setSelectradio(String selectradio) {
        this.selectradio = selectradio;
    }

    public String getTime_list() {
        return time_list;
    }

    public void setTime_list(String time_list) {
        this.time_list = time_list;
    }

    // add by jinp stop

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

    public String getDrivingoldorgid() {
        return drivingoldorgid;
    }

    public void setDrivingoldorgid(String drivingoldorgid) {
        this.drivingoldorgid = drivingoldorgid;
    }

    public int getMypgnum() {
        return mypgnum;
    }

    public void setMypgnum(int mypgnum) {
        this.mypgnum = mypgnum;
    }

    private int myoldpagesize;

    public int getMyoldpagesize() {
        return myoldpagesize;
    }

    public void setMyoldpagesize(int myoldpagesize) {
        this.myoldpagesize = myoldpagesize;
    }

    public String getAlarm_date() {
        return alarm_date;
    }

    public void setAlarm_date(String alarm_date) {
        this.alarm_date = alarm_date;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
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

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(String vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getMalarmd_id() {
        return malarmd_id;
    }

    public void setMalarmd_id(String malarmd_id) {
        this.malarmd_id = malarmd_id;
    }

    public String getAlarm_type_id() {
        return alarm_type_id;
    }

    public void setAlarm_type_id(String alarm_type_id) {
        this.alarm_type_id = alarm_type_id;
    }

    public String getAlarm_type_name() {
        return alarm_type_name;
    }

    public void setAlarm_type_name(String alarm_type_name) {
        this.alarm_type_name = alarm_type_name;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getAlarm_start_time() {
        return alarm_start_time;
    }

    public void setAlarm_start_time(String alarm_start_time) {
        this.alarm_start_time = alarm_start_time;
    }

    public String getAlarm_end_time() {
        return alarm_end_time;
    }

    public void setAlarm_end_time(String alarm_end_time) {
        this.alarm_end_time = alarm_end_time;
    }

    public String getAlarm_time() {
        return alarm_time;
    }

    public void setAlarm_time(String alarm_time) {
        this.alarm_time = alarm_time;
    }

    public String getAlarm_start_speed() {
        return alarm_start_speed;
    }

    public void setAlarm_start_speed(String alarm_start_speed) {
        this.alarm_start_speed = alarm_start_speed;
    }

    public String getAlarm_start_rpm() {
        return alarm_start_rpm;
    }

    public void setAlarm_start_rpm(String alarm_start_rpm) {
        this.alarm_start_rpm = alarm_start_rpm;
    }

    public String getAlarm_start_latitude() {
        return alarm_start_latitude;
    }

    public void setAlarm_start_latitude(String alarm_start_latitude) {
        this.alarm_start_latitude = alarm_start_latitude;
    }

    public String getAlarm_start_longitude() {
        return alarm_start_longitude;
    }

    public void setAlarm_start_longitude(String alarm_start_longitude) {
        this.alarm_start_longitude = alarm_start_longitude;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(String enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

}
