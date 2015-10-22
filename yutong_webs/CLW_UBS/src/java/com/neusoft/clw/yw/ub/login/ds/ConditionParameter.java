/*******************************************************************************
 * @FileName: ConditionParameter.java 2013-7-16下午1:57:49
 * @Author: zhangzhia
 * @Copyright: 2013 YUTONG Group CLW. All rights reserved.
 * @Remarks: YUTONG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.ub.login.ds;

import java.util.Date;

import com.neusoft.clw.common.util.DateUtil;

/**
 * @author zhangzhia 2013-7-10 下午5:57:49
 *
 */
public class ConditionParameter {

	/**
	 * 平台类型（所有平台，Web平台，App平台）
	 */
	private String statics_platform = "";  
	/**
	 * 客户类型（A, B, C）
	 */
	private String customer_type = "";  
	/**
	 * 统计月
	 */
	private String statis_month = "";
	
	public ConditionParameter()
	{
		this.statics_platform = "web";
		//this.statis_month = DateUtil.getMonth();
		Date dt=new Date();
		Date lastdt=DateUtil.addMonth(dt, -1);
		this.statis_month=DateUtil.getDateTime("yyyy-MM",lastdt);
	}
	
	/**
	 * @return the statics_platform
	 */
	public String getStatics_platform() {
		return statics_platform;
	}
	/**
	 * @param statics_platform the statics_platform to set
	 */
	public void setStatics_platform(String statics_platform) {
		this.statics_platform = statics_platform;
	}
	/**
	 * @return the customer_type
	 */
	public String getCustomer_type() {
		return customer_type;
	}
	/**
	 * @param customer_type the customer_type to set
	 */
	public void setCustomer_type(String customer_type) {
		this.customer_type = customer_type;
	}
	/**
	 * @return the statis_month
	 */
	public String getStatis_month() {
		return statis_month;
	}
	/**
	 * @param statis_month the statis_month to set
	 */
	public void setStatis_month(String statis_month) {
		this.statis_month = statis_month;
	}
 
	
}
