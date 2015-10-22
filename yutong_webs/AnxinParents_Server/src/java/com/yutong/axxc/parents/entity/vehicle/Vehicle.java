package com.yutong.axxc.parents.entity.vehicle;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vehicle implements Serializable
{

	private static final long serialVersionUID = -2477404127954687682L;

	/**
     * 车辆ID.
     */
    @JsonProperty("vehicle_id")
    private String vehicleId; 

    /**
     * vin.
     */
    @JsonProperty("vehicle_vin")
    private String vehicleVin;

    /**
     * 车辆编码.
     */
    @JsonProperty("vehicle_code")
    private String vehicleCode;

    /**
     * 车牌号.
     */
    @JsonProperty("vehicle_plate")
    private String vehiclePlate;

    /**
     * 司机.
     */
    @JsonProperty("vehicle_driver")
    private String vehicleDriver;

    /**
     * 车辆颜色.
     */
    @JsonProperty("vehicle_color")
    private String vehicleColor;

    /**
     * 经度.
     */
    @JsonProperty("gps_lon")
    private String gpsLon;

    /**
     * 纬度.
     */
    @JsonProperty("gps_lat")
    private String gpsLat;

    /**
     * 方向.
     */
    @JsonProperty("direction")
    private String direction;

    /**
     * 速度.
     */
    @JsonProperty("speed")
    private String speed;

    /**
     * 车辆状态.
     */
    @JsonProperty("status")
    private String status;

    /**
     * 更新时间
     */
    @JsonProperty("update_time")
    private String updateTime;

    
    
    
    public String getVehicleId()
    {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId)
    {
        this.vehicleId = vehicleId;
    }

    public String getVehicleVin()
    {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin)
    {
        this.vehicleVin = vehicleVin;
    }

    public String getVehicleCode()
    {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode)
    {
        this.vehicleCode = vehicleCode;
    }

    public String getVehiclePlate()
    {
        return vehiclePlate;
    }

    public void setVehiclePlate(String vehiclePlate)
    {
        this.vehiclePlate = vehiclePlate;
    }

    public String getVehicleDriver()
    {
        return vehicleDriver;
    }

    public void setVehicleDriver(String vehicleDriver)
    {
        this.vehicleDriver = vehicleDriver;
    }

    public String getVehicleColor()
    {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor)
    {
        this.vehicleColor = vehicleColor;
    }

    public String getGpsLon()
    {
        return gpsLon;
    }

    public void setGpsLon(String gpsLon)
    {
        this.gpsLon = gpsLon;
    }

    public String getGpsLat()
    {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat)
    {
        this.gpsLat = gpsLat;
    }

    public String getDirection()
    {
        return direction;
    }

    public void setDirection(String direction)
    {
        this.direction = direction;
    }

    public String getSpeed()
    {
        return speed;
    }

    public void setSpeed(String speed)
    {
        this.speed = speed;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

}
