package com.yutong.axxc.parents.entity.site;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GPS implements Serializable{

	private static final long serialVersionUID = -6049156761588468286L;

	/**
     * 经度.
     */
    @JsonProperty( "gps_lon")
    private String lon;

    /**
     * 纬度.
     */
    @JsonProperty( "gps_lat")
    private String lat;

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "GPS [lon=" + lon + ", lat=" + lat + "]";
	}
    
    
}
