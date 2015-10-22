/**
 * @(#)UserBehavior.java 2013-4-7
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.usr;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 客户端软件上报的用户行为信息.
 
 * @version $Revision 1.0 $ 2013-4-7 上午11:01:49
 */
public class UserBehavior {

	/**
	 * 用户ID.
	 */
	@JsonIgnore
	private String usrId;

	/**
	 * 用户所属企业ID.
	 */
	@JsonIgnore
	private String entrepriseId;

	/**
	 * 功能模块ID.
	 */
	@XmlElement(name = "module_id")
	private String moduleId;

	/**
	 * 子功能模块ID.
	 */
	@XmlElement(name = "module_son_id")
	private String moduleSonId;

	/**
	 * @return Returns the usrId.
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * @param usrId The usrId to set.
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	/**
	 * @return Returns the entrepriseId.
	 */
	public String getEntrepriseId() {
		return entrepriseId;
	}

	/**
	 * @param entrepriseId The entrepriseId to set.
	 */
	public void setEntrepriseId(String entrepriseId) {
		this.entrepriseId = entrepriseId;
	}

	/**
	 * @return Returns the moduleId.
	 */
	public String getModuleId() {
		return moduleId;
	}

	/**
	 * @param moduleId The moduleId to set.
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return Returns the moduleSonId.
	 */
	public String getModuleSonId() {
		return moduleSonId;
	}

	/**
	 * @param moduleSonId The moduleSonId to set.
	 */
	public void setModuleSonId(String moduleSonId) {
		this.moduleSonId = moduleSonId;
	}
}
