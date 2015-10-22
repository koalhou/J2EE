package com.yutong.axxc.parents.entity.site;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Remind implements Serializable
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
     * 经度.
     */
    @JsonProperty( "gps_lon")
    private String siteLongitude;


	/**
     * 纬度.
     */
    @JsonProperty( "gps_lat")
    private String siteLatitude;

    
    @JsonProperty( "remind_id")
    private String remindId;
    
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
    private String valid;
    
    
    public String getChildId() {
  		return childId;
  	}

  	public void setChildId(String childId) {
  		this.childId = childId;
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
	
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@Override
	public String toString() {
		return "Remind [siteId=" + siteId + ", childId=" + childId
				+ ", siteLongitude=" + siteLongitude + ", siteLatitude="
				+ siteLatitude + ", remindId=" + remindId + ", remindAlias="
				+ remindAlias + ", remindType=" + remindType + ", remindValue="
				+ remindValue + ", remindModifyTime=" + remindModifyTime + "]";
	}

  

}
