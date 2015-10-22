/**
 *    主要功能  : 预运行线路轨迹表   .
 */
package com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain;


import java.util.*;


/** .
 * ************************************************************************
 * <p/>
 * Created Date:2013-10-11  15:23:50
 * Created By:
 * 主要功能  : 预运行线路轨迹表
 * Time    Modifier   Reason
 * <p/>
 * *************************************************************************
 */


public class ClwTqcEmpPreline {
    /**
     * 主键 . 
     */
    private Integer noteId;
    /**
     * 提醒站点ID . 
     */
    private Integer routeId;
    private String routeName;
    /**
     * 提醒站点描述 . 
     */
    private String isSite;
    /**
     * 经度 . 
     */
    private String longitude;
    /**
     * 纬度 . 
     */
    private String latitude;
    /**
     * 上下行：0：上行；1：下行 . 
     */
    private String elevation;
    /**
     * 创建时间 . 
     */
    private Date createTime;
    /**
     * 修改时间 . 
     */
    private Date modifyTime;
    /**
     *  . 
     */
    private String mileage;
    /**
     *  . 
     */
    private String isCorner;
    /**
     *  . 
     */
    private String isOverpass;
    /**
     *  . 
     */
    private String isRound;
    /**
     *  . 
     */
    private String direction;
    /**
     *  . 
     */
    private Integer isOrder;
    /**
     * 初始化单元对象 .
     */ 
    private Boolean gpsIsExc;
    
    public Boolean getGpsIsExc() {
		return gpsIsExc;
	}
	public void setGpsIsExc(Boolean gpsIsExc) {
		this.gpsIsExc = gpsIsExc;
	}
	public  ClwTqcEmpPreline(){
    }
    /**
     * 设置主键 .
     * @param noteId 主键
     */
    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }
    /**
     * 获得主键 .
     * @return 主键
     */
    public Integer getNoteId() {
        return noteId;
    }
    /**
     * 设置提醒站点ID .
     * @param routeId 提醒站点ID
     */
    public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
    /**
     * 获得提醒站点ID .
     * @return 提醒站点ID
     */
    public Integer getRouteId() {
        return routeId;
    }
    /**
     * 设置提醒站点描述 .
     * @param isSite 提醒站点描述
     */
    public void setIsSite(String isSite) {
        this.isSite = isSite;
    }
    /**
     * 获得提醒站点描述 .
     * @return 提醒站点描述
     */
    public String getIsSite() {
        return isSite;
    }
    /**
     * 设置经度 .
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    /**
     * 获得经度 .
     * @return 经度
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * 设置纬度 .
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    /**
     * 获得纬度 .
     * @return 纬度
     */
    public String getLatitude() {
        return latitude;
    }
    /**
     * 设置上下行：0：上行；1：下行 .
     * @param elevation 上下行：0：上行；1：下行
     */
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
    /**
     * 获得上下行：0：上行；1：下行 .
     * @return 上下行：0：上行；1：下行
     */
    public String getElevation() {
        return elevation;
    }
    /**
     * 设置创建时间 .
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获得创建时间 .
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置修改时间 .
     * @param modifyTime 修改时间
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    /**
     * 获得修改时间 .
     * @return 修改时间
     */
    public Date getModifyTime() {
        return modifyTime;
    }
    public void setMileage(String mileage) {
        this.mileage = mileage;
    }
    public String getMileage() {
        return mileage;
    }
    public void setIsCorner(String isCorner) {
        this.isCorner = isCorner;
    }
    public String getIsCorner() {
        return isCorner;
    }
    public void setIsOverpass(String isOverpass) {
        this.isOverpass = isOverpass;
    }
    public String getIsOverpass() {
        return isOverpass;
    }
    public void setIsRound(String isRound) {
        this.isRound = isRound;
    }
    public String getIsRound() {
        return isRound;
    }
    public void setDirection(String direction) {
        this.direction = direction;
    }
    public String getDirection() {
        return direction;
    }
    public void setIsOrder(Integer isOrder) {
        this.isOrder = isOrder;
    }
    public Integer getIsOrder() {
        return isOrder;
    }
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
    
}
