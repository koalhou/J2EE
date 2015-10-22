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
public class CoreMsgRespFunction {
	private String name;
	private String seq = "3001";
	private CoreMsgRespResult result;

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
	 * @return Returns the result.
	 */
	public CoreMsgRespResult getResult() {
		return result;
	}

	/**
	 * @param result
	 *            The result to set.
	 */
	@XmlElement(name = "result")
	public void setResult(CoreMsgRespResult result) {
		this.result = result;
	}
}
