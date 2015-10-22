/**
 * @(#)PushRuleResp.java 2013-4-17
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.entity.pushRule;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author <a href="mailto:yi_liu@neusoft.com">yi_liu </a>
 * @version $Revision 1.0 $ 2013-4-17 上午10:53:07
 */
public class PushRuleResp {
	
	/**
	 * 服务端持有的个人消息推送规则最新标识
	 */
	@XmlElement(name = "ETag")
	private String eTag;
	
	/**
	 * 推送规则内容， 参见推送消息规则
	 */
	@XmlElement(name = "rule_content")
	private List<PushRuleInfo> ruleContent;

	/**
	 * @return Returns the eTag.
	 */
	public String geteTag() {
		return eTag;
	}

	/**
	 * @param eTag
	 *            The eTag to set.
	 */
	public void seteTag(String eTag) {
		this.eTag = eTag;
	}

	/**
	 * @return Returns the ruleContent.
	 */
	public List<PushRuleInfo> getRuleContent() {
		return ruleContent;
	}

	/**
	 * @param ruleContent
	 *            The ruleContent to set.
	 */
	public void setRuleContent(List<PushRuleInfo> ruleContent) {
		this.ruleContent = ruleContent;
	}

}
