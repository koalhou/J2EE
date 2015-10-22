/**
 * @(#)ResultSendPhoto.java 2013-3-25
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.msg;

import javax.xml.bind.annotation.XmlElement;

/**
 
 * @version $Revision 1.0 $ 2013-3-25 下午7:16:08
 */
public class ResultSendPhoto {

	/**
	 * 车辆vin信息.
	 */
	@XmlElement(name = "vin")
	private String vin;

	/**
	 * 拍摄通道及结果信息.
	 * 格式：拍摄通道|命令下发状态，信息间隔使用“,”.
	 * 其中拍摄通道1：整车；2：路况；3：车门；4：司机
	 * 下发状态1：成功；0：失败
	 */
	@XmlElement(name = "flg")
	private String flg;

	/**
	 * @return Returns the vin.
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin The vin to set.
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * @return Returns the flg.
	 */
	public String getFlg() {
		return flg;
	}

	/**
	 * @param flg The flg to set.
	 */
	public void setFlg(String flg) {
		this.flg = flg;
	}

}
