/**
 * @(#)MsgMoldInfo.java 2013-4-15
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.msg;

import javax.xml.bind.annotation.XmlElement;



/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao </a>
 * @version $Revision 1.0 $ 2013-4-15 上午10:05:45
 */
public class MsgMoldResp {
	/**
	 * 调度信息模板ID.
	 */
	@XmlElement(name = "ttmsg_id")
	private String id;
	/**
	 * 调度信息模板修改时间，yyyymmddhh24miss.
	 */
	@XmlElement(name = "ETag")
	private String editT;

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return Returns the editT.
	 */
	public String getEditT() {
		return editT;
	}
	/**
	 * @param editT The editT to set.
	 */
	public void setEditT(String editT) {
		this.editT = editT;
	}
	
}
