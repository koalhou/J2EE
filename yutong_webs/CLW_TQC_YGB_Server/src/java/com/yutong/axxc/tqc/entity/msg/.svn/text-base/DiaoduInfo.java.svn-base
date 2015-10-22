/**
 * @(#)DiaoduInfo.java 2013-4-10
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.msg;

import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-4-10 下午4:01:21
 */
public class DiaoduInfo {

	/**
	 * 车辆VIN信息，VIN信息间使用”|”分割.
	 */
	@XmlElement(name = "vins")
	private String vins;

	/**
	 * 消息内容.
	 */
	@XmlElement(name = "msg")
	private String msg;

	/**
	 * 下发方式，下发方式间使用”|”分割
	 * 00 – 紧急
	 * 01 – 语音播报
	 * 02 – 车载屏
	 * 03 – 终端显示器.
	 */
	@XmlElement(name = "type")
	private String type;

	/**
	 * @return Returns the vins.
	 */
	public String getVins() {
		return vins;
	}

	/**
	 * @param vins The vins to set.
	 */
	public void setVins(String vins) {
		this.vins = vins;
	}

	/**
	 * @return Returns the msg.
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg The msg to set.
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * @return Returns the type.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type The type to set.
	 */
	public void setType(String type) {
		this.type = type;
	}

}
