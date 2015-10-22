/**
 * @author haoxy
 * @createdate 2013年9月14日 下午3:44:11
 * @description 
 */
package com.yutong.axxc.tqc.entity.distance;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nick 安芯校车家长版移动应用接口规范 2.4.5
 *
 */
public class DistanceMsg  implements Serializable
{
	@JsonProperty("station_id")
    private String stationId;
	
	@JsonProperty("station_name")
    private String stationName;
	
	@JsonProperty("vehiche_plate")
    private String vehichePlate;
	
	@JsonProperty("remind_alias")
    private String remindAlias;
	
	@JsonProperty("remind_type")
    private String remindType;
	
	@JsonProperty("remind_value")
    private String remindValue;

	public String getStationId()
	{
		return stationId;
	}

	public void setStationId(String stationId)
	{
		this.stationId = stationId;
	}

	public String getStationName()
	{
		return stationName;
	}

	public void setStationName(String stationName)
	{
		this.stationName = stationName;
	}

	public String getVehichePlate()
	{
		return vehichePlate;
	}

	public void setVehichePlate(String vehichePlate)
	{
		this.vehichePlate = vehichePlate;
	}

	public String getRemindAlias()
	{
		return remindAlias;
	}

	public void setRemindAlias(String remindAlias)
	{
		this.remindAlias = remindAlias;
	}

	public String getRemindType()
	{
		return remindType;
	}

	public void setRemindType(String remindType)
	{
		this.remindType = remindType;
	}

	public String getRemindValue()
	{
		return remindValue;
	}

	public void setRemindValue(String remindValue)
	{
		this.remindValue = remindValue;
	}
}
