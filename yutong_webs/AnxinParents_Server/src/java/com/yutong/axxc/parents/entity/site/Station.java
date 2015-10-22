package com.yutong.axxc.parents.entity.site;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Station implements Serializable
{

	private static final long serialVersionUID = -1800751794515141698L;

	/**
     * 站点ID.
     */
    @JsonProperty( "station_id")
    private String siteId;
    
    @JsonProperty( "cld_id")
    private String childId;

    /**
     * 站点名称.
     */
    @JsonProperty( "station_name")
    private String siteName;

    /**
     * 经度.
     */
    @JsonProperty( "gps_lon")
    private String siteLongitude;

    public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	/**
     * 纬度.
     */
    @JsonProperty( "gps_lat")
    private String siteLatitude;

    /**
     * 站点类型.
     */
    @JsonProperty( "station_type")
    private String siteType;
    
    @JsonProperty( " remind_id")
    private String remindId;

    /**
     * 计划到达时间.
     */
    @JsonProperty( "plan_arrive_time")
    private String planArriveTime;

    /**
     * 备注名.
     */
    @JsonProperty( "remind_alias")
    private String remindAlias;

    /**
     * 提醒类型.
     */
    @JsonProperty( "remind_type")
    private String remindType;

    /**
     * 提醒值.
     */
    @JsonProperty( "remind_value")
    private String remindValue;

    private String noteTime;

    private String noteMileage;

    public String getNoteTime()
    {
        return noteTime;
    }

    public void setNoteTime(String noteTime)
    {
        this.noteTime = noteTime;
    }

    public String getNoteMileage()
    {
        return noteMileage;
    }

    public void setNoteMileage(String noteMileage)
    {
        this.noteMileage = noteMileage;
    }

    private String remindModifyTime;

    public String getRemindModifyTime()
    {
        return remindModifyTime;
    }

    public void setRemindModifyTime(String remindModifyTime)
    {
        this.remindModifyTime = remindModifyTime;
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

    public String getSiteId()
    {
        return siteId;
    }

    public void setSiteId(String siteId)
    {
        this.siteId = siteId;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getSiteLongitude()
    {
        return siteLongitude;
    }

    public void setSiteLongitude(String siteLongitude)
    {
        this.siteLongitude = siteLongitude;
    }

    public String getSiteLatitude()
    {
        return siteLatitude;
    }

    public void setSiteLatitude(String siteLatitude)
    {
        this.siteLatitude = siteLatitude;
    }

    public String getSiteType()
    {
        return siteType;
    }

    public void setSiteType(String siteType)
    {
        this.siteType = siteType;
    }

    public String getPlanArriveTime()
    {
        return planArriveTime;
    }

    public void setPlanArriveTime(String planArriveTime)
    {
        this.planArriveTime = planArriveTime;
    }

    public String getRemindAlias()
    {
        return remindAlias;
    }

    public void setRemindAlias(String remindAlias)
    {
        this.remindAlias = remindAlias;
    }

	public String getRemindId() {
		return remindId;
	}

	public void setRemindId(String remindId) {
		this.remindId = remindId;
	}

  

}
