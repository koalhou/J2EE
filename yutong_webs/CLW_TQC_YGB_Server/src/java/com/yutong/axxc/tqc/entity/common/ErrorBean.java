/**
 * @(#)ErrorCoder.java 2013-4-1
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.common;

import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang.StringUtils;

import com.yutong.axxc.tqc.tools.JacksonUtils;

/**
 * 错误描述实体
 * 
 * @author <a href="mailto:yi_liu@neusoft.com">yi_liu </a>
 * @version $Revision 1.0 $ 2013-4-1 上午8:57:40
 */
public final class ErrorBean {

	/**
	 * 错误码
	 */
	@XmlElement(name = "error_code")
	private String errorCode = "";
	/**
	 * 错误描述
	 */
	@XmlElement(name = "error_des")
	private String errorDes = "";

	/**
	 * 基本构造器
	 * 
	 * @param errorCode
	 *            错误码
	 * @param errorDes
	 *            错误描述
	 */
	public ErrorBean(String errorCode, String errorDes) {
		errorCode = StringUtils.strip(errorCode);
		errorDes = StringUtils.strip(errorDes);
		// 确保在jackson转换为json字符串时有内容。
		if (StringUtils.isEmpty(errorCode) || StringUtils.isEmpty(errorDes)) {
			this.errorCode = "99999";
			this.errorDes = "Uknow Exception";
		} else {
			this.errorCode = errorCode;
			this.errorDes = errorDes;
		}
	}

	/**
	 * @return Returns the errorCode.
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            The errorCode to set.
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return Returns the errorDes.
	 */
	public String getErrorDes() {
		return errorDes;
	}

	/**
	 * @param errorDes
	 *            The errorDes to set.
	 */
	public void setErrorDes(String errorDes) {
		this.errorDes = errorDes;
	}

	/**
	 * 将ErrorBean转换为json字符串
	 * 
	 * @return json字符串
	 */
	public String toJson() {
		return JacksonUtils.toJsonRuntimeException(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "[ errorCode=" + errorCode + ", errorDes=" + errorDes + " ]";
	}
}
