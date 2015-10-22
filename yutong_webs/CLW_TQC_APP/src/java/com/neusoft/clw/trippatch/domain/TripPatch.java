package com.neusoft.clw.trippatch.domain;

public class TripPatch {
	private String patch_id;//临时派车补录ID
	private String vehicle_ln;//车牌号
	private String vehicle_code;//班车号
	private String route_type;//线路类别，1:早班，2:晚班
	private String route_id;//线路Id
	private String type;//用车类别，1：通勤，2：公差
	private String start_time;//开始时间
	private String end_time;//结束时间
	private String update_by;//修改用户id
	private String update_time;//修改时间
	private String count;//乘车人数
	private String driver_id;//驾驶员id
	private String driver_name;//驾驶员名
	private String mileage;//里程
	private String extra_trip_name;//线路名称（公差）
	private String route_name;//线路名称（通勤）
	private String vehicle_vin;
	private String use_time;//时长
	private String operate_time;
	private String emp_name;//修改人
	private String yesterday;//昨天
	
	
	public String getYesterday() {
		return yesterday;
	}
	public void setYesterday(String yesterday) {
		this.yesterday = yesterday;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getOperate_time() {
		return operate_time;
	}
	public void setOperate_time(String operate_time) {
		this.operate_time = operate_time;
	}
	public String getUse_time() {
		return use_time;
	}
	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
	public String getPatch_id() {
		return patch_id;
	}
	public void setPatch_id(String patch_id) {
		this.patch_id = patch_id;
	}
	public String getVehicle_ln() {
		return vehicle_ln;
	}
	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}
	public String getVehicle_code() {
		return vehicle_code;
	}
	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}
	public String getRoute_type() {
		return route_type;
	}
	public void setRoute_type(String route_type) {
		this.route_type = route_type;
	}
	public String getRoute_id() {
		return route_id;
	}
	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getUpdate_by() {
		return update_by;
	}
	public void setUpdate_by(String update_by) {
		this.update_by = update_by;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getExtra_trip_name() {
		return extra_trip_name;
	}
	public void setExtra_trip_name(String extra_trip_name) {
		this.extra_trip_name = extra_trip_name;
	}
	public String getRoute_name() {
		return route_name;
	}
	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}
}
