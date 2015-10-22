/**
 * @(#)MsgMoldReq.java 2013-4-15
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.msg;

import javax.xml.bind.annotation.XmlElement;

import com.yutong.axxc.tqc.tools.BeanUtil.CheckBean;
import com.yutong.axxc.tqc.tools.BeanUtil.Require;

/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao </a>
 * @version $Revision 1.0 $ 2013-4-15 上午11:17:48
 */
@CheckBean
public class MsgMoldReq {
	/**
	 * 消息调度模板内容.
	 */
	@Require
	@XmlElement(name = "ttmsg_remark")
	private String ttmsg_remark;

	/**
	 * @return Returns the ttmsg_remark.
	 */
	public String getTtmsg_remark() {
		return ttmsg_remark;
	}

	/**
	 * @param ttmsg_remark The ttmsg_remark to set.
	 */
	public void setTtmsg_remark(String ttmsg_remark) {
		this.ttmsg_remark = ttmsg_remark;
	}	
}
