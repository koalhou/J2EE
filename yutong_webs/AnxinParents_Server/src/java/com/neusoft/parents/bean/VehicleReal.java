package com.neusoft.parents.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleReal implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -1391642180801832675L;

    private String vehicle_vin; //vin号
    @JsonProperty("gps_lat")
    public String latitude;//经度
    @JsonProperty("gps_lon")
    public String longitude;//纬度

    public String direction;//方向

    private String speed;//速度

    private Date terminal_time;//终端上传时间
    
    private String vehicle_ln;//车牌号
    @JsonProperty("status")
    private String stat_info;

    public String getStat_info()
    {
        return stat_info;
    }

    public void setStat_info(String stat_info)
    {
        this.stat_info = stat_info;
    }

    public String getVehicle_ln()
    {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln)
    {
        this.vehicle_ln = vehicle_ln;
    }

    public Date getTerminal_time()
    {
        return terminal_time;
    }

    public void setTerminal_time(Date terminal_time)
    {
        this.terminal_time = terminal_time;
    }

    public String getSpeed()
    {
        return speed;
    }

    public void setSpeed(String speed)
    {
        this.speed = speed;
    }

    public String getVehicle_vin()
    {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin)
    {
        this.vehicle_vin = vehicle_vin;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

}
