package com.yutong.axxc.tqc.entity.site;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route implements Serializable
{

	private static final long serialVersionUID = 5035305603535609419L;


	/**
     * 线路ID.
     */
    @JsonProperty( "line_id")
    private String line_id;

    /**
     * 线路名称.
     */
    @JsonProperty( "line_name")
    private String line_name;

    /**
     * 厂区
     */
    @JsonProperty("area_type")
    private String area_type;
    
    /**
     * 线路范围 
     */
    @JsonProperty("line_range")
    private String line_range;
    
    /**
     * 上下班范围
     */
    @JsonProperty("status_range")
    private String status_range;
    
    @JsonProperty( "points")
    private List<GPS> point_infos;

    @JsonProperty("stations")
    private List<Site> site_infos;

    
	public String getLine_id() {
		return line_id;
	}

	public void setLine_id(String line_id) {
		this.line_id = line_id;
	}

	public String getLine_name() {
		return line_name;
	}

	public void setLine_name(String line_name) {
		this.line_name = line_name;
	}

	public String getArea_type() {
		return area_type;
	}

	public void setArea_type(String area_type) {
		this.area_type = area_type;
	}

	public String getLine_range() {
		return line_range;
	}

	public void setLine_range(String line_range) {
		this.line_range = line_range;
	}

	public String getStatus_range() {
		return status_range;
	}

	public void setStatus_range(String status_range) {
		this.status_range = status_range;
	}

	public List<GPS> getPoint_infos() {
		return point_infos;
	}

	public void setPoint_infos(List<GPS> point_infos) {
		this.point_infos = point_infos;
	}

	public List<Site> getSite_infos() {
		return site_infos;
	}

	public void setSite_infos(List<Site> site_infos) {
		this.site_infos = site_infos;
	}
    
}
