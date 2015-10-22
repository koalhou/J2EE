package com.neusoft.clw.trippatch.domain;
/**
 * 
 *临时派车补录查询vo
 */
public class TripSearchVO {
	
	//班车号
	private String vehicle_code;
	private String vehicle_ln;//车牌号
	private String start_time;//开始时间
	private String end_time;//结束时间
	private String cur_time;//当前的时间
	private String type;//用车类型
	private String route_name;//线路名称
	private String patch_id;//补录id
	private String vins;

	
	private String sortName;
	private String sortOrder;
	
	
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public String getVins() {
		return vins;
	}
	public void setVins(String vins) {
		this.vins = vins;
	}
	public String getVehicle_code() {
		return vehicle_code;
	}
	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}
	public String getVehicle_ln() {
		return vehicle_ln;
	}
	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
	public String getPatch_id() {
		return patch_id;
	}
	public void setPatch_id(String patch_id) {
		this.patch_id = patch_id;
	}
	public String getCur_time() {
		return cur_time;
	}
	public void setCur_time(String cur_time) {
		this.cur_time = cur_time;
	}

}
