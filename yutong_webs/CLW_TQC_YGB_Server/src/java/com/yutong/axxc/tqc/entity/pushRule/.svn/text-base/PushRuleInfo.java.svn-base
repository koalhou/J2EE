/**
 * @(#)PushRule.java 2013-4-8
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.entity.pushRule;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author wyg
 */
public class PushRuleInfo {

	/**
	 * 消息推送规则ID(对应用户个人)
	 */
	@XmlElement(name = "id")
	private String id ;
	/**
	 * 用户ID
	 */
	@XmlElement(name = "usr_id")
	private String usrId ;
	/**
	 * 用户ID
	 */
	@XmlElement(name = "cld_id")
	private String childId ;
	/**
	 * 消息推送规则ID(对应用户个人)
	 */
	@XmlElement(name = "rule_id")
	private String ruleId ;

	@XmlElement(name = "rule_parent _id")
	private String rulePId ;
	/**
	 * 消息推送规则描述
	 */
	@XmlElement(name = "rule_desc")
	private String ruleDesc ;
	
	@XmlElement(name = "rule_title")
	private String ruleTitle ;

	/**
	 * 是否推送：1– 开；0– 关
	 */
	@XmlElement(name = "on_off")
	private String onOff ;

	/**
	 * 是否提示：1– 提示；0– 不提示
	 */
	private String flag ;


	/**
	 * @return Returns the ruleId.
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId
	 *            The ruleId to set.
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return Returns the usrId.
	 */
	public String getUsrId() {
		return usrId;
	}

	/**
	 * @param usrId
	 *            The usrId to set.
	 */
	public void setUsrId(String usrId) {
		this.usrId = usrId;
	}

	/**
	 * @return Returns the ruleDesc.
	 */
	public String getRuleDesc() {
		return ruleDesc;
	}

	/**
	 * @param ruleDesc
	 *            The ruleDesc to set.
	 */
	public void setRuleDesc(String ruleDesc) {
		this.ruleDesc = ruleDesc;
	}

	/**
	 * @return Returns the onOff.
	 */
	public String getOnOff() {
		return onOff;
	}

	/**
	 * @param onOff
	 *            The onOff to set.
	 */
	public void setOnOff(String onOff) {
		this.onOff = onOff;
	}

	/**
	 * @return Returns the flag.
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * @param flag
	 *            The flag to set.
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public String getRulePId() {
		return rulePId;
	}

	public void setRulePId(String rulePId) {
		this.rulePId = rulePId;
	}

	public String getRuleTitle() {
		return ruleTitle;
	}

	public void setRuleTitle(String ruleTitle) {
		this.ruleTitle = ruleTitle;
	}

	@Override
	public String toString() {
		return "PushRuleInfo [id=" + id + ", usrId=" + usrId + ", childId="
				+ childId + ", ruleId=" + ruleId + ", rulePId=" + rulePId
				+ ", ruleDesc=" + ruleDesc + ", onOff=" + onOff + ", flag="
				+ flag + "]";
	}

	
}
