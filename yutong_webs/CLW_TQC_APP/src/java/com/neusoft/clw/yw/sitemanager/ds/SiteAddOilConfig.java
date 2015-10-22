package com.neusoft.clw.yw.sitemanager.ds;

/***
 * 加油时段提醒与低油量阀值设置
 * @author wxliq
 *
 */
public class SiteAddOilConfig {
	
	private Integer siteConfigId;
	/** 企业ＩＤ */
	private String enterprise_id;
	/** 加油频率 0:周　1:月 */
	private String addOilRate = "0";
	/** 开始时段 */
	private String startTimeQuantum = "0";
	/** 结束时段 */
	private String endTimeQuantum = "0";
	/** 低油量阀值 */
	private String lowOilValue = "0";
	/**
	 * 是否启用低油量阀值：1-启用  0-停用
	 */
	private String oilValueCheck = "0";
	/**
	 * 是否启用加油频率监控：1-启用  0-停用
	 */
	private String oilRateCheck = "0";
	/**
	 * 油价
	 */
	private float oilPrice;
	
	/*
	 * 组织ID
	 * **/
	private String organization_id;
	
	private String laterConfig1;
	
	private String laterConfig2;
	
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}
	public String getAddOilRate() {
		return addOilRate;
	}
	public void setAddOilRate(String addOilRate) {
		this.addOilRate = addOilRate;
	}
	public String getStartTimeQuantum() {
		return startTimeQuantum;
	}
	public void setStartTimeQuantum(String startTimeQuantum) {
		this.startTimeQuantum = startTimeQuantum;
	}
	public String getEndTimeQuantum() {
		return endTimeQuantum;
	}
	public void setEndTimeQuantum(String endTimeQuantum) {
		this.endTimeQuantum = endTimeQuantum;
	}
	public String getLowOilValue() {
		return lowOilValue;
	}
	public void setLowOilValue(String lowOilValue) {
		this.lowOilValue = lowOilValue;
	}
	public Integer getSiteConfigId() {
		return siteConfigId;
	}
	public void setSiteConfigId(Integer siteConfigId) {
		this.siteConfigId = siteConfigId;
	}
	public String getOilValueCheck() {
		return oilValueCheck;
	}
	public void setOilValueCheck(String oilValueCheck) {
		this.oilValueCheck = oilValueCheck;
	}
	public String getOilRateCheck() {
		return oilRateCheck;
	}
	public void setOilRateCheck(String oilRateCheck) {
		this.oilRateCheck = oilRateCheck;
	}
	public float getOilPrice() {
		return oilPrice;
	}
	public void setOilPrice(float oilPrice) {
		this.oilPrice = oilPrice;
	}
	public String getLaterConfig1() {
		return laterConfig1;
	}
	public void setLaterConfig1(String laterConfig1) {
		this.laterConfig1 = laterConfig1;
	}
	public String getLaterConfig2() {
		return laterConfig2;
	}
	public void setLaterConfig2(String laterConfig2) {
		this.laterConfig2 = laterConfig2;
	}
	
}
