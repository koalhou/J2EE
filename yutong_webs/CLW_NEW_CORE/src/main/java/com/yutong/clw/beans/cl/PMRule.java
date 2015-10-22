package com.yutong.clw.beans.cl;

public class PMRule {
	
	private String user_id;
	private String on_off;
	private String flag;
	private String valid_flag;
	private String pm_rule_id;
	
	public String getPm_rule_id() {
		return pm_rule_id;
	}
	public void setPm_rule_id(String pmRuleId) {
		pm_rule_id = pmRuleId;
	}
	public String getValid_flag() {
		return valid_flag;
	}
	public void setValid_flag(String validFlag) {
		valid_flag = validFlag;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String userId) {
		user_id = userId;
	}
	public String getOn_off() {
		return on_off;
	}
	public void setOn_off(String onOff) {
		on_off = onOff;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
