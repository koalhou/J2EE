/**
 * @(#)CoreMsgReqFunction.java 2013-4-9
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.coremsg;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-4-9 上午11:20:08
 */
public class CoreMsgReqFunction {
	private String name;
	private String timeout = "60";
	private String seq = "3001";
	private CoreMsgReqParam param;

	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            The name to set.
	 */
	@XmlAttribute(name = "name")
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return Returns the timeout.
	 */
	public String getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout
	 *            The timeout to set.
	 */
	@XmlAttribute(name = "timeout")
	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	/**
	 * @return Returns the seq.
	 */
	public String getSeq() {
		return seq;
	}

	/**
	 * @param seq
	 *            The seq to set.
	 */
	@XmlAttribute(name = "seq")
	public void setSeq(String seq) {
		this.seq = seq;
	}

	/**
	 * @return Returns the param.
	 */
	public CoreMsgReqParam getParam() {
		return param;
	}

	/**
	 * @param param
	 *            The param to set.
	 */
	@XmlElement(name = "param")
	public void setParam(CoreMsgReqParam param) {
		this.param = param;
	}
}
