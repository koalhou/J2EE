/**
 * @(#)MsgMoldInfo.java 2013-4-15
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.msg;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao </a>
 * @version $Revision 1.0 $ 2013-4-15 上午10:05:45
 */
public class MsgMoldInfo {
	/**
	 * 消息调度模板ID.
	 */
	@XmlElement(name = "ttmsg_id")
	private String id;
	/**
	 * 消息调度模板内容.
	 */
	@XmlElement(name = "ttmsg_remark")
	private String remark;
	/**
	 * 用户ID.
	 */
	@JsonIgnore
	private String userId;
	/**
	 * 消息调度模板创建时间.
	 */
	@JsonIgnore
	private String creatT;
	/**
	 * 消息调度模板修改时间.
	 */
	@JsonIgnore
	private String editT;
	/**
	 * 消息调度模板删除标志位，0-有效；1-删除.
	 */
	@JsonIgnore
	private String delFlag;
	/**
	 * 调度信息模板删除标志
	 */
	@JsonIgnore
	private String del;

	/**
	 * @return Returns the del.
	 */
	public String getDel() {
		return del;
	}

	/**
	 * @param del The del to set.
	 */
	public void setDel(String del) {
		this.del = del;
	}

	/**
	 * @return Returns the id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            The id to set.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return Returns the remark.
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            The remark to set.
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            The userId to set.
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return Returns the creatT.
	 */
	public String getCreatT() {
		return creatT;
	}

	/**
	 * @param creatT
	 *            The creatT to set.
	 */
	public void setCreatT(String creatT) {
		this.creatT = creatT;
	}

	/**
	 * @return Returns the editT.
	 */
	public String getEditT() {
		return editT;
	}

	/**
	 * @param editT
	 *            The editT to set.
	 */
	public void setEditT(String editT) {
		this.editT = editT;
	}

	/**
	 * @return Returns the delFlag.
	 */
	public String getDelFlag() {
		return delFlag;
	}

	/**
	 * @param delFlag
	 *            The delFlag to set.
	 */
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
