/**
 *    主要功能  :    .
 */
package com.neusoft.clw.yunxing.car.runhistory.domain;


/** .
 * ************************************************************************
 * <p/>
 * Created Date:2013-12-23  8:19:22
 * Created By:
 * 主要功能  : 
 * Time    Modifier   Reason
 * <p/>
 * *************************************************************************
 */


public class ClwXcTripHistory {
    /**
     * 模板id . 
     */
    private Integer id;
    /**
     * 模板名称 . 
     */
    private String name;
    /**
     * 线路id . 
     */
    private String routeId;
    private String routename;
    /**
     * 描述 . 
     */
    private String moduleDesc;
    /**
     * 创建时间 . 
     */
    private String createTime;
    /**
     * 初始化单元对象 .
     */ 
    public  ClwXcTripHistory(){
    }
    /**
     * 设置模板id .
     * @param id 模板id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * 获得模板id .
     * @return 模板id
     */
    public Integer getId() {
        return id;
    }
    /**
     * 设置模板名称 .
     * @param name 模板名称
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 获得模板名称 .
     * @return 模板名称
     */
    public String getName() {
        return name;
    }
    /**
     * 设置线路id .
     * @param routeId 线路id
     */
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
    /**
     * 获得线路id .
     * @return 线路id
     */
    public String getRouteId() {
        return routeId;
    }
    /**
     * 设置描述 .
     * @param moduleDesc 描述
     */
    public void setModuleDesc(String moduleDesc) {
        this.moduleDesc = moduleDesc;
    }
    /**
     * 获得描述 .
     * @return 描述
     */
    public String getModuleDesc() {
        return moduleDesc;
    }
    /**
     * 设置创建时间 .
     * @param createTime 创建时间
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    /**
     * 获得创建时间 .
     * @return 创建时间
     */
    public String getCreateTime() {
        return createTime;
    }
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
    
}
