package com.yutong.axxc.parents.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoryMsg {
	@JsonProperty("msg_id")
	private String id;
	@JsonProperty("cld_id")
	private String childId;
	@JsonProperty("rule_id")
	private String ruleId;//消息对应规则ID
	@JsonProperty("msg_content")
	private String content;
	@JsonProperty("msg_time")
	private String time;
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
	public String getRuleId() {
		return ruleId;
	}
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
