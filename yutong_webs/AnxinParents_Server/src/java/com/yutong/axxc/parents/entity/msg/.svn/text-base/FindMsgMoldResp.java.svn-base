/**
 * @(#)FindMsgMoldResp.java 2013-4-17
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author <a href="mailto:yi_liu@neusoft.com">yi_liu </a>
 * @version $Revision 1.0 $ 2013-4-17 下午6:42:06
 */
public class FindMsgMoldResp {
	/**
	 * 最新标记信息
	 */
	@XmlElement(name = "ETag")
	private String etag;
	/**
	 * 所有的模板信息
	 */
	@XmlElement(name = "ttmsg_all")
	private List<MsgMoldInfo> list;

	/**
	 * @return Returns the etag.
	 */
	public String getEtag() {
		return etag;
	}

	/**
	 * @param etag
	 *            The etag to set.
	 */
	public void setEtag(String etag) {
		this.etag = etag;
	}

	/**
	 * @return Returns the list.
	 */
	public List<MsgMoldInfo> getList() {
		return list;
	}

	/**
	 * @param list
	 *            The list to set.
	 */
	public void setList(List<MsgMoldInfo> list) {
		this.list = list;
	}
}
