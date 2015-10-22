/**
 * @(#)SoftwareVersion.java 2013-4-3
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.oauth;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-4-3 下午1:57:28
 */
public class SoftwareVersion implements Serializable {

	private static final long serialVersionUID = 7150228458205659589L;

	/**
	 * 版本序号
	 */
	@XmlElement(name = "version_code")
	private String version_code;
	/**
	 * 访问令牌信息.
	 */
	@XmlElement(name = "access_token")
	private String token;

	/**
	 * 目标软件版本号.
	 */
	@XmlElement(name = "version_name")
	private String targetVersion;

	/**
	 * 软件升级描述.
	 */
	@XmlElement(name = "change_desc")
	private String changeDesc;

	/**
	 * 软件地址信息.
	 */
	@XmlElement(name = "uri")
	private String uri;

	/**
	 * 是否强制升级
	 */
	@XmlElement(name = "force")
	private String force;
	
	
	
	public String getVersion_code() {
		return version_code;
	}

	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}

	public String getForce() {
		return force;
	}

	public void setForce(String force) {
		this.force = force;
	}


	/**
	 * @return Returns the token.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token The token to set.
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return Returns the targetVersion.
	 */
	public String getTargetVersion() {
		return targetVersion;
	}

	/**
	 * @param targetVersion The targetVersion to set.
	 */
	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	/**
	 * @return Returns the changeDesc.
	 */
	public String getChangeDesc() {
		return changeDesc;
	}

	/**
	 * @param changeDesc The changeDesc to set.
	 */
	public void setChangeDesc(String changeDesc) {
		this.changeDesc = changeDesc;
	}

	/**
	 * @return Returns the uri.
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri The uri to set.
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
}
