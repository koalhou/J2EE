/**
 * @(#)AppUserLoginResp.java 2013-3-17
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.oauth;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-3-17 下午12:50:06
 */
public class LoginResp {

	/**
	 * 服务访问令牌信息.
	 */
	@XmlElement(name = "access_token")
	private String accessToken;

	/**
	 * 令牌有效期.单位:秒.
	 */
	@XmlElement(name = "expires_in")
	private long expiresIn;

	/**
	 * 用于刷新Access Token的令牌信息.
	 */
	@XmlElement(name = "refresh_token")
	private String refreshToken;

	/**
	 * 用户权限列表信息.
	 */
	@XmlElement(name = "scope")
	private List<String> scope = new ArrayList<String>();

	/**
	 * 用户信息
	 */
	@XmlElement(name = "usr_info")
	private UsrInfo usrInfo;

	/**
	 * @return Returns the accessToken.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * @param accessToken
	 *            The accessToken to set.
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return Returns the expiresIn.
	 */
	public long getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn
	 *            The expiresIn to set.
	 */
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return Returns the refreshToken.
	 */
	public String getRefreshToken() {
		return refreshToken;
	}

	/**
	 * @param refreshToken
	 *            The refreshToken to set.
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	/**
	 * @return Returns the scope.
	 */
	public List<String> getScope() {
		return scope;
	}

	/**
	 * @param scope The scope to set.
	 */
	public void setScope(List<String> scope) {
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
