/**
 * @(#)PicCommandInfo.java 2013-4-11
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.msg;

import javax.xml.bind.annotation.XmlElement;

/**
 * 拍照命令请求信息.
 
 * @version $Revision 1.0 $ 2013-4-11 下午6:22:09
 */
public class PicCommandInfo {

	/**
	 * 车辆VIN信息，VIN信息间使用”|”分割.
	 */
	@XmlElement(name = "vins")
	private String vins;

	/**
	 * 拍摄通道.
	 * 1：整车;
	 * 2：路况;
	 * 3：车门;
	 * 4：司机.
	 * 拍摄通道间使用“|”分隔。.
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
