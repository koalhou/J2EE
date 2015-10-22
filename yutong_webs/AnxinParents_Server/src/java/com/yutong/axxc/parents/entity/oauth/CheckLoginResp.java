/**
 * @(#)CheckLoginResp.java 2013-3-24
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.oauth;

import javax.xml.bind.annotation.XmlElement;


/**
 
 * @version $Revision 1.0 $ 2013-3-24 下午3:48:31
 */
public class CheckLoginResp {

	/**
	 * 用户权限列表信息.
	 */
	@XmlElement(name = "scope")
	private String[] scope;

	/**
	 * 用户信息.
	 */
	@XmlElement(name = "usr_info")
	private UsrInfo usrInfo;

	/**
	 * @return Returns the scope.
	 */
	public String[] getScope() {
		return scope;
	}

	/**
	 * @param scope
	 *            The scope to set.
	 */
	public void setScope(String[] scope) {
		this.scope = scope;
	}

	/**
	 * @return Returns the usrInfo.
	 */
	public UsrInfo getUsrInfo() {
		return usrInfo;
	}

	/**
	 * @param usrInfo
	 *            The usrInfo to set.
	 */
	public void setUsrInfo(UsrInfo usrInfo) {
		this.usrInfo = usrInfo;
	}

}
