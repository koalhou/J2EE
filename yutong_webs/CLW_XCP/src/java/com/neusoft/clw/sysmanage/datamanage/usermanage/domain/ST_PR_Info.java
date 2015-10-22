package com.neusoft.clw.sysmanage.datamanage.usermanage.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ST_PR_Info implements java.io.Serializable {
    private String pr_userid;
    private String stu_id;
    private String stu_name;

	public String getPr_userid() {
		return pr_userid;
	}
	public void setPr_userid(String pr_userid) {
		this.pr_userid = pr_userid;
	}
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}   
	
}
