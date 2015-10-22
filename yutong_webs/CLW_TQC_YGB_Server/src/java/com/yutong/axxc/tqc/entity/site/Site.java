/**
 * @author houjh
 * @createdate 2013年10月24日 上午9:37:03
 * @description 
 */
package com.yutong.axxc.tqc.entity.site;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author houjh
 * 
 */
public class Site implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty
	private String id;
	@JsonProperty
	private String area_type;
	@JsonProperty
	private String status_range;
	@JsonProperty
	private String belong_area_id;
	@JsonProperty
	private String name;
	@JsonProperty
	private String alias;
	@JsonProperty("gps_lon")
	private String site_longitude;
	@JsonProperty("gps_lat")
	private String site_latitude;
	@JsonProperty
	private String type;
	@JsonProperty
	private String status;
	@JsonProperty
	private String plan_arrive_time;
	@JsonProperty
	private String favorites;
	
	
	
	public String getArea_type() {
		return area_type;
	}

	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}

	public String getStatus_range() {
		return status_range;
	}

	public void setStatus_range(String status_range) {
		this.status_range = status_range;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBelong_area_id() {
		return belong_area_id;
	}

	public void setBelong_area_id(String belong_area_id) {
		this.belong_area_id = belong_area_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSite_longitude() {
		return site_longitude;
	}

	public void setSite_longitude(String site_longitude) {
		this.site_longitude = site_longitude;
	}

	public String getSite_latitude() {
		return site_latitude;
	}

	public void setSite_latitude(String site_latitude) {
		this.site_latitude = site_latitude;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPlan_arrive_time() {
		return plan_arrive_time;
	}

	public void setPlan_arrive_time(String plan_arrive_time) {
		this.plan_arrive_time = plan_arrive_time;
	}

	public String getFavorites() {
		return favorites;
	}

	public void setFavorites(String favorites) {
		this.favorites = favorites;
	}

}
