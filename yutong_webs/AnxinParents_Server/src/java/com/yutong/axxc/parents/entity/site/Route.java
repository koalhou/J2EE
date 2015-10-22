package com.yutong.axxc.parents.entity.site;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Route implements Serializable
{

	private static final long serialVersionUID = 5035305603535609419L;


	/**
     * 线路ID.
     */
    @JsonProperty( "trip_id")
    private String tripId;
    
    
    @JsonProperty( "line_id")
    private String routeId;

    /**
     * 线路名称.
     */
    @JsonProperty( "line_name")
    private String routeName;

    /**
     * 0-up 1-down
     */
    @JsonProperty( "line_type")
    private String routeType;

    @JsonProperty( "point_infos")
    private List<GPS> points;

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteType() {
		return routeType;
	}

	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}

	public List<GPS> getPoints() {
		return points;
	}

	public void setPoints(List<GPS> points) {
		this.points = points;
	}
    
   
}
