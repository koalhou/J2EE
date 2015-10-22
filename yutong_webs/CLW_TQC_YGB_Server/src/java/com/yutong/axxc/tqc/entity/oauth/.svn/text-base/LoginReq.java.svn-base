/**
 * @(#)LoginReq.java 2013-3-31
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.oauth;

import javax.xml.bind.annotation.XmlElement;


/**
 
 * @version $Revision 1.0 $ 2013-3-31 下午2:52:23
 */
public class LoginReq {

	/**
	 * 登录用户名.
	 */
	@XmlElement(name = "usr_account")
	private String userName;

	/**
	 * MD5加密的登录用户密码.
	 */
	@XmlElement(name = "usr_pwd")
	private String password;

	/**
	 * 客户端软件版本信息.
	 */
	@XmlElement(name = "version")
	private String version;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version The version to set.
	 */
	public void setVersion(String version) {
		this.version = version;
	}
}
