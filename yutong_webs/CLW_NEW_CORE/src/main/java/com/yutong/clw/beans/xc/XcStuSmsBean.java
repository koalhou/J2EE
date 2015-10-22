package com.yutong.clw.beans.xc;

public class XcStuSmsBean {
	private String stu_id;
	private String event_type;
	private String cell_number;
	private String relative_type;
	private String relative_name;
	private String end_time;
	private String parents_flag;
	public String getStu_id() {
		return stu_id;
	}
	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
	public String getCell_number() {
		return cell_number;
	}
	public void setCell_number(String cell_number) {
		this.cell_number = cell_number;
	}
	public String getRelative_type() {
		return relative_type;
	}
	public void setRelative_type(String relativeType) {
		relative_type = relativeType;
	}
	public String getRelative_name() {
		return relative_name;
	}
	public void setRelative_name(String relativeName) {
		relative_name = relativeName;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String endTime) {
		end_time = endTime;
	}
	public String getParents_flag() {
		return parents_flag;
	}
	public void setParents_flag(String parentsFlag) {
		parents_flag = parentsFlag;
	}
	
}
