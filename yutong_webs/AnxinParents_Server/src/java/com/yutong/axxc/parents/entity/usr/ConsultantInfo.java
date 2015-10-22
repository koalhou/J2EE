/**
 * @(#)ConsultantInfo.java 2013-5-30
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.usr;

import javax.xml.bind.annotation.XmlElement;

/**
 * 安芯顾问信息.
 
 * @version $Revision 1.0 $ 2013-5-30 上午8:52:32
 */
public class ConsultantInfo {

	/**
	 * 姓名.
	 */
	@XmlElement(name = "name")
	private String name;

	/**
	 * 联系电话.
	 */
	@XmlElement(name = "tel")
	private String tel;

	/**
	 * 1– 客户经理;
	 * 2– 技术支持.
	 */
	@XmlElement(name = "manager_type")
	private String managerType;

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the tel.
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel The tel to set.
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return Returns the managerType.
	 */
	public String getManagerType() {
		return managerType;
	}

	/**
	 * @param managerType The managerType to set.
	 */
	public void setManagerType(String managerType) {
		this.managerType = managerType;
	}

}
