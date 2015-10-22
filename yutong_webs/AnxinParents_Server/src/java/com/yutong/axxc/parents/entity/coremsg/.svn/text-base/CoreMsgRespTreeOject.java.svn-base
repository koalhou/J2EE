/**
 * @(#)CoreMsgReqTreeOject.java 2013-4-9
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.coremsg;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-4-9 下午3:03:42
 */
public class CoreMsgRespTreeOject {
	private String version = "0.0.1";
	private List<TreeObjectValue> treeList;
	private String respC = "false";

	/**
	 * @return Returns the treeList.
	 */
	public List<TreeObjectValue> getTreeList() {
		return treeList;
	}

	/**
	 * @param treeList
	 *            The treeList to set.
	 */
	@XmlElement(name = "value")
	public void setTreeList(List<TreeObjectValue> treeList) {
		this.treeList = treeList;
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
