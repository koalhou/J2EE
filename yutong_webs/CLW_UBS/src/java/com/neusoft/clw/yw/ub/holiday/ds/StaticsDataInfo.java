/*******************************************************************************
 * @FileName: StaticsDataInfo.java 2013-7-5 下午3:51:04
 * @Author: zhangzhia
 * @Copyright: 2013 YUTONG Group CLW. All rights reserved.
 * @Remarks: YUTONG PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.ub.holiday.ds;

/**
 * @author zhangzhia 2013-7-5 下午3:51:04
 *
 */
/**
 * @author zhangzhia 2013-7-9 下午4:44:36
 *
 */
public class StaticsDataInfo {

	//分组名
	private String groupname = "";
	//名称
	private String day_name = "";
	//年度
	private String year_select = "";
	//统计区间
	private String date_section = "";
	//统计天数
	private String statics_count = "";
	//开始时间
	private String startDate = "";
	//结束时间
	private String endDate = "";
	
	//0:节假日 1:工作日
	private String dayflag = "";
	
	
	/**
	 * @return the groupname
	 */
	public String getGroupname() {
		return groupname;
	}
	/**
	 * @param groupname the groupname to set
	 */
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	/**
	 * @return the day_name
	 */
	public String getDay_name() {
		return day_name;
	}
	/**
	 * @param day_name the day_name to set
	 */
	public void setDay_name(String day_name) {
		this.day_name = day_name;
	}
	/**
	 * @return the year_select
	 */
	public String getYear_select() {
		return year_select;
	}
	/**
	 * @param year_select the year_select to set
	 */
	public void setYear_select(String year_select) {
		this.year_select = year_select;
	}
	/**
	 * @return the date_section
	 */
	public String getDate_section() {
		return date_section;
	}
	/**
	 * @param date_section the date_section to set
	 */
	public void setDate_section(String date_section) {
		this.date_section = date_section;
	}
	/**
	 * @return the statics_count
	 */
	public String getStatics_count() {
		return statics_count;
	}
	/**
	 * @param statics_count the statics_count to set
	 */
	public void setStatics_count(String statics_count) {
		this.statics_count = statics_count;
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
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	/**
	 * @return the dayflag
	 */
	public String getDayflag() {
		return dayflag;
	}
	/**
	 * @param dayflag the dayflag to set
	 */
	public void setDayflag(String dayflag) {
		this.dayflag = dayflag;
	}

}
