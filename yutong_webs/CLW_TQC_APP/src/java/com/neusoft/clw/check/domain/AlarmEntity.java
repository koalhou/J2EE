package com.neusoft.clw.check.domain;
/**
 * 公车私用信息实体类
 * @author changweitao
 *
 */
public class AlarmEntity {
	
	private String alarm_id; //主键ID
	private String vehicle_vin;//车工号
	private String alarm_type_id;//告警类型，公车私用：
	private String confirm_time;//处理日期
	private String deal_flag;//处理与否 默认0-未处理，处理状态 :0:未处理，1：已处理
	private String alarm_time;//开始时间
	private String user_id;//处理用户id
	private String alarm_end_time;//结束时间
	private String mileage;//行驶里程
	private String opeerate_desc;//处理意见
	private String driver_id;//驾驶员
	private String operate_type;//类别,0:公车私用，1：正常用车
	private String vehicle_ln;//车牌号
	private String vehicle_code;//班车号
	private String driver_name;//驾驶员姓名
	private String driver_tel;//驾驶员联系方式
	private String use_time;//用车时长(分钟)
	private String user_name;//处理人
	private String ids;//多个告警Id，以逗号分隔
	private String effect_time;//授权运行时间
	
	public String getEffect_time() {
		return effect_time;
	}
	public void setEffect_time(String effect_time) {
		this.effect_time = effect_time;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getUse_time() {
		return use_time;
	}
	public void setUse_time(String use_time) {
		this.use_time = use_time;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getAlarm_id() {
		return alarm_id;
	}
	public void setAlarm_id(String alarm_id) {
		this.alarm_id = alarm_id;
	}
	public String getVehicle_vin() {
		return vehicle_vin;
	}
	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}
	public String getAlarm_type_id() {
		return alarm_type_id;
	}
	public void setAlarm_type_id(String alarm_type_id) {
		this.alarm_type_id = alarm_type_id;
	}
	public String getConfirm_time() {
		return confirm_time;
	}
	public void setConfirm_time(String confirm_time) {
		this.confirm_time = confirm_time;
	}
	public String getDeal_flag() {
		return deal_flag;
	}
	public void setDeal_flag(String deal_flag) {
		this.deal_flag = deal_flag;
	}
	public String getAlarm_time() {
		return alarm_time;
	}
	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAlarm_end_time() {
		return alarm_end_time;
	}
	public void setAlarm_end_time(String alarm_end_time) {
		this.alarm_end_time = alarm_end_time;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getOpeerate_desc() {
		return opeerate_desc;
	}
	public void setOpeerate_desc(String opeerate_desc) {
		this.opeerate_desc = opeerate_desc;
	}
	public String getDriver_id() {
		return driver_id;
	}
	public void setDriver_id(String driver_id) {
		this.driver_id = driver_id;
	}
	public String getOperate_type() {
		return operate_type;
	}
	public void setOperate_type(String operate_type) {
		this.operate_type = operate_type;
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
	public String getDriver_name() {
		return driver_name;
	}
	public void setDriver_name(String driver_name) {
		this.driver_name = driver_name;
	}
	public String getDriver_tel() {
		return driver_tel;
	}
	public void setDriver_tel(String driver_tel) {
		this.driver_tel = driver_tel;
	}
	
	
}
