/*******************************************************************************
 * @FileName: ConditionParameter.java 2013-7-10 下午5:57:49
 * @Author: zhangzhia
 * @Copyright: 2013 YUTONG Group CLW. All rights reserved.
 * @Remarks: YUTONG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.ub.system.ds;

import java.util.Date;

import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.yw.ub.login.helper.Helper;

/**
 * @author zhangzhia 2013-7-10 下午5:57:49
 *
 */
public class ConditionParameter {

	/**
	 * 系统类型（0：分辨率，1：操作系统，2：浏览器，3：FLASH版本）
	 */
	private String systemType = "";  
	/**
	 * 是否节假日(1：是，0否)
	 */
	private String is_Holiday = "";
    /**
     * 统计开始时间
     */
	private String startDate = "";
	/**
	 * 统计结束时间
	 */
	private String endDate = "";
	/**
	 * 排序字段
	 */
	private String order = "visitCount";	//访问次数：visitCount，访问企业次数：visitEpCount，访问用户次数：visitUserCount
	/**
	 * 排序方式
	 */
	private String direction = "desc";	//升序：asc，降序：desc
	
	public ConditionParameter() {
		//Date dt = new Date();
		//this.startDate = Helper.getSetDate(dt, -30);
		//this.startDate = DateUtil.formatDateToString(dt, "yyyy-MM-dd");
	}
	
	/**
	 * @return the systemType
	 */
	public String getSystemType() {
		return systemType;
	}
	/**
	 * @param systemType the systemType to set
	 */
	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}
	/**
	 * @return the is_Holiday
	 */
	public String getIs_Holiday() {
		return is_Holiday;
	}
	/**
	 * @param is_Holiday the is_Holiday to set
	 */
	public void setIs_Holiday(String is_Holiday) {
		this.is_Holiday = is_Holiday;
	}
	/**
	 * @return the startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the endDdate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDdate the endDdate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the order
	 */
	public String getOrder() {
		return order;
	}
	/**
	 * @param order the order to set
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}

	
}
