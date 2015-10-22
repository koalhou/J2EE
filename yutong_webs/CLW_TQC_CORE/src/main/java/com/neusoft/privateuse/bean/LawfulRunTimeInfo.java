package com.neusoft.privateuse.bean;

/**
 * TQC_LAWFUL_RUN_TIME_T表所对应内容
 * @author liuja
 *
 */
public class LawfulRunTimeInfo {
	
	private String organization_id;
	
	private String start_time;  //公出开始时间
	
	private String end_time; //公出结束时间

	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	
	

}
