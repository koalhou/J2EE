/**
 * @(#)TerminalViBean.java 2013-4-11
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.msg;

/**
 * 终端设备信息表
 
 * @version $Revision 1.0 $ 2013-4-11 上午9:02:34
 */
public class TerminalViBean {
	/**
	 * 终端发送时间.
	 */
	private String terminalTime;
	/**
	 * 车辆状态位码.
	 */
	private String stateInfo;
	/**
	 * SIM卡号.
	 */
	private String simCardNum;
	/**
	 * SIM卡号.
	 */
	private String accFlg;
	/**
	 * SIM卡号.
	 */
	private int seconds;
	/**
	 * @return Returns the terminalTime.
	 */
	public String getTerminalTime() {
		return terminalTime;
	}
	/**
	 * @param terminalTime The terminalTime to set.
	 */
	public void setTerminalTime(String terminalTime) {
		this.terminalTime = terminalTime;
	}
	/**
	 * @return Returns the stateInfo.
	 */
	public String getStateInfo() {
		return stateInfo;
	}
	/**
	 * @param stateInfo The stateInfo to set.
	 */
	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	/**
	 * @return Returns the simCardNum.
	 */
	public String getSimCardNum() {
		return simCardNum;
	}
	/**
	 * @param simCardNum The simCardNum to set.
	 */
	public void setSimCardNum(String simCardNum) {
		this.simCardNum = simCardNum;
	}
	/**
	 * @return Returns the accFlg.
	 */
	public String getAccFlg() {
		return accFlg;
	}
	/**
	 * @param accFlg The accFlg to set.
	 */
	public void setAccFlg(String accFlg) {
		this.accFlg = accFlg;
	}
	/**
	 * @return Returns the seconds.
	 */
	public int getSeconds() {
		return seconds;
	}
	/**
	 * @param seconds The seconds to set.
	 */
	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}
}
