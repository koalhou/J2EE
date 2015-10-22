/**
 * @(#)EnvInfo.java 2013-3-26
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.env;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yutong.axxc.tqc.tools.BeanUtil.CheckBean;
import com.yutong.axxc.tqc.tools.BeanUtil.Require;

/**
 * @author <a href="mailto:yi_liu@neusoft.com">yi_liu </a>
 * @version $Revision 1.0 $ 2013-3-26 上午11:07:48
 */
@CheckBean
public class EnvInfo {

	@Require
	@JsonIgnore
	private String userId = "";
	
	/**
	 * 终端上报时间。格式:yyyymmddhh24miss
	 */
	@Require(contentType = Require.AttributeType.TIME)
	private String time = "";
	
	/**
	 * 客户端唯一标识
	 */
	@Require
	private String imei = "";	
	
	/**
	 * 终端手机号码
	 */
	private String msisdn = "";
	
	/**
	 * 终端机型。
	 */
	@Require
	@XmlElement(name = "mode_numb")
	private String modeNumb = "";
	
	/**
	 * 终端品牌
	 */
	@Require
	private String brand = "";
	
	/**
	 * 终端屏幕分辨率，分辨率格式为“长*宽”，例：1024*768
	 */
	@Require
	@XmlElement(name = "reso_rati")
	private String resoRati = "";
	
	/**
	 * 运营商类别。 0-wifi 1-中国移动2G 2-中国移动3G 3-中国电信2G 4-中国电信3G 5-中国联通2G 6-中国联通3G
	 */
	@Require
	@XmlElement(name = "t_mobile")
	private String typeMobile = "";

	/**
	 * 操作系统版本，格式为操作系统名称+版本号 例：android 4.1.1
	 */
	@Require
	@XmlElement(name = "os_vers")
	private String osVers = "";

	/**
	 * 安芯移动应用软件版本
	 */
	@Require
	@XmlElement(name = "soft_vers")
	private String softVers = "";

	/**
	 * 用户所在地市，需上传含有市或县的名称，例如：沈阳市或新民县
	 */
	
	private String city = "";
	/**
	 * 用户所在省会，需上传含有省的名称，例如：辽宁省
	 */
	
	private String province = "";

	/**
	 * @return Returns the time.
	 */
	public String getTime() {
		return time;
	}

	/**
	 * @param time
	 *            The time to set.
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * @return Returns the msisdn.
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn
	 *            The msisdn to set.
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return Returns the modeNumb.
	 */
	public String getModeNumb() {
		return modeNumb;
	}

	/**
	 * @param modeNumb
	 *            The modeNumb to set.
	 */
	public void setModeNumb(String modeNumb) {
		this.modeNumb = modeNumb;
	}

	/**
	 * @return Returns the brand.
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param brand
	 *            The brand to set.
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return Returns the resoRati.
	 */
	public String getResoRati() {
		return resoRati;
	}

	/**
	 * @param resoRati
	 *            The resoRati to set.
	 */
	public void setResoRati(String resoRati) {
		this.resoRati = resoRati;
	}

	/**
	 * @return Returns the osVers.
	 */
	public String getOsVers() {
		return osVers;
	}

	/**
	 * @param osVers
	 *            The osVers to set.
	 */
	public void setOsVers(String osVers) {
		this.osVers = osVers;
	}

	/**
	 * @return Returns the imei.
	 */
	public String getImei() {
		return imei;
	}

	/**
	 * @param imei
	 *            The imei to set.
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}

	/**
	 * @return Returns the city.
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            The city to set.
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return Returns the province.
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province
	 *            The province to set.
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * @return Returns the softVers.
	 */
	public String getSoftVers() {
		return softVers;
	}

	/**
	 * @param softVers
	 *            The softVers to set.
	 */
	public void setSoftVers(String softVers) {
		this.softVers = softVers;
	}

	/**
	 * @return Returns the typeMobile.
	 */
	public String getTypeMobile() {
		return typeMobile;
	}

	/**
	 * @param typeMobile
	 *            The typeMobile to set.
	 */
	public void setTypeMobile(String typeMobile) {
		this.typeMobile = typeMobile;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
