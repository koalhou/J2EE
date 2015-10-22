/**
 * @(#)CoreMsgReq.java 2013-4-9
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.coremsg;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 
 * @version $Revision 1.0 $ 2013-4-9 上午11:09:02
 */
@XmlRootElement(name = "olx")
public class CoreMsgResp {
	private String dir = "down";
	private String version = "0.0.1";
	private String respC = "false";

	private CoreMsgRespFunction function;

	/**
	 * @return Returns the dir.
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir
	 *            The dir to set.
	 */
	@XmlAttribute(name = "dir")
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * @return Returns the version.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            The version to set.
	 */
	@XmlAttribute(name = "version")
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return Returns the function.
	 */
	public CoreMsgRespFunction getFunction() {
		return function;
	}

	/**
	 * @param function
	 *            The function to set.
	 */
	@XmlElement(name = "function")
	public void setFunction(CoreMsgRespFunction function) {
		this.function = function;
	}

	/**
	 * @return Returns the respC.
	 */
	public String getRespC() {
		return respC;
	}

	/**
	 * @param respC
	 *            The respC to set.
	 */
	@XmlAttribute(name = "c")
	public void setRespC(String respC) {
		this.respC = respC;
	}
}
