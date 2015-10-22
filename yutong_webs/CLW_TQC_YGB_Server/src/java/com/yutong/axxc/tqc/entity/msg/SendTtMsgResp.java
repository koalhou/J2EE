/**
 * @(#)SendTtMsgResp.java 2013-3-25
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;


/**
 
 * @version $Revision 1.0 $ 2013-3-25 下午7:05:08
 */
public class SendTtMsgResp {

	/**
	 * 调度信息下发结果消息.
	 */
	@XmlElement(name = "result")
	private List<ResultSendTtMsg> resultList;

	/**
	 * @return Returns the resultList.
	 */
	public List<ResultSendTtMsg> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List<ResultSendTtMsg> resultList) {
		this.resultList = resultList;
	}
}
