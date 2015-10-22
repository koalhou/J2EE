/**
 * @(#)SendPhotoResp.java 2013-3-25
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.msg;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-3-25 下午7:15:45
 */
public class SendPhotoResp {

	/**
	 * 拍照命令下发结果消息.
	 */
	@XmlElement(name = "result")
	private List<ResultSendPhoto> resultList;

	/**
	 * @return Returns the resultList.
	 */
	public List<ResultSendPhoto> getResultList() {
		return resultList;
	}

	/**
	 * @param resultList The resultList to set.
	 */
	public void setResultList(List<ResultSendPhoto> resultList) {
		this.resultList = resultList;
	}
}
