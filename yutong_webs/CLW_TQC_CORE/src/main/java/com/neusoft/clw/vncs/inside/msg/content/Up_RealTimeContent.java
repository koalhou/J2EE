package com.neusoft.clw.vncs.inside.msg.content;

import com.neusoft.clw.vncs.inside.msg.req.UpLoadDataReq;


public class Up_RealTimeContent extends UpLoadDataReq {

	
	/**
	 * quantity name
	 */
	public String msg_cmd;
	public String utc_time;
	public String terminal_time;
	public String gps_valid;
	public String latitude;
	public String longitude;
	public String elevation;
	public String direction;
	public String gps_speeding;
	public String speeding;
	public String on_off;
	public String sos;
	public String overspeed_alert;
	public String fatigue_alert;
	public String gps_alert;
	public String show_speed_alert;
	public String driver_id;
	public String driver_license;
	public String engine_rotate_speed;
	public String mileage;
	public String oil_instant;
	public String oil_pressure;
	public String torque_percent;
	public String fire_up_state;
	public String power_state;
	public String battery_voltage;
	public String gps_state;
	public String ext_voltage;
	public String img_process;	
	public String oil_total;
	public String e_water_temp;	
	public String e_torque;
	public String quad_id_type;
	public String route_info;
	public String meg_resp_id; 
	public String meg_id;
	public String meg_type;
	public String meg_info;
	public String meg_seq;
	public String ratio;
	public String gears;
	
	//sos报警类型ID
	public String sos_alarm_type_id;
	//超速报警类型ID
	public String overspeed_alarm_type_id;
	//疲劳驾驶报警类型ID
	public String fatigue_alarm_type_id;
	//gps超速报警类型ID
	public String gps_alarm_type_id;
	//急加/减速报警类型ID
	public String rapid_alarm_type_id;
	
	//急加/减速开关量
	public String rapid;
	
	public String getRapid() {
		return rapid;
	}

	public void setRapid(String rapid) {
		this.rapid = rapid;
	}

	public String getElevation() {
		return elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public String getTorque_percent() {
		return torque_percent;
	}

	public void setTorque_percent(String torquePercent) {
		torque_percent = torquePercent;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getGears() {
		return gears;
	}

	public void setGears(String gears) {
		this.gears = gears;
	}

	public String getMeg_info() {
		return meg_info;
	}

	public void setMeg_info(String megInfo) {
		meg_info = megInfo;
	}

	public String getMsg_cmd() {
		return msg_cmd;
	}

	public void setMsg_cmd(String msgCmd) {
		msg_cmd = msgCmd;
	}

	public String getUtc_time() {
		return utc_time;
	}

	public void setUtc_time(String utcTime) {
		utc_time = utcTime;
	}

	public String getGps_valid() {
		return gps_valid;
	}

	public void setGps_valid(String gpsValid) {
		gps_valid = gpsValid;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getGps_speeding() {
		return gps_speeding;
	}

	public void setGps_speeding(String gpsSpeeding) {
		gps_speeding = gpsSpeeding;
	}

	public String getSpeeding() {
		return speeding;
	}

	public void setSpeeding(String speeding) {
		this.speeding = speeding;
	}

	public String getOn_off() {
		return on_off;
	}

	public void setOn_off(String onOff) {
		on_off = onOff;
	}

	public String getSos() {
		return sos;
	}

	public void setSos(String sos) {
		this.sos = sos;
	}

	public String getOverspeed_alert() {
		return overspeed_alert;
	}

	public void setOverspeed_alert(String overspeedAlert) {
		overspeed_alert = overspeedAlert;
	}

	public String getFatigue_alert() {
		return fatigue_alert;
	}

	public void setFatigue_alert(String fatigueAlert) {
		fatigue_alert = fatigueAlert;
	}

	public String getGps_alert() {
		return gps_alert;
	}

	public void setGps_alert(String gpsAlert) {
		gps_alert = gpsAlert;
	}

	public String getShow_speed_alert() {
		return show_speed_alert;
	}

	public void setShow_speed_alert(String showSpeedAlert) {
		show_speed_alert = showSpeedAlert;
	}

	public String getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(String driverId) {
		driver_id = driverId;
	}

	public String getDriver_license() {
		return driver_license;
	}

	public void setDriver_license(String driverLicense) {
		driver_license = driverLicense;
	}

	public String getTerminal_time() {
		return terminal_time;
	}

	public void setTerminal_time(String terminalTime) {
		terminal_time = terminalTime;
	}

	public String getFire_up_state() {
		return fire_up_state;
	}

	public void setFire_up_state(String fireUpState) {
		fire_up_state = fireUpState;
	}

	public String getPower_state() {
		return power_state;
	}

	public void setPower_state(String powerState) {
		power_state = powerState;
	}

	public String getGps_state() {
		return gps_state;
	}

	public void setGps_state(String gpsState) {
		gps_state = gpsState;
	}

	public String getImg_process() {
		return img_process;
	}

	public void setImg_process(String imgProcess) {
		img_process = imgProcess;
	}

	public String getRoute_info() {
		return route_info;
	}

	public void setRoute_info(String routeInfo) {
		route_info = routeInfo;
	}

	public String getE_water_temp() {
		return e_water_temp;
	}

	public void setE_water_temp(String eWaterTemp) {
		e_water_temp = eWaterTemp;
	}

	public String getOil_pressure() {
		return oil_pressure;
	}

	public void setOil_pressure(String oilPressure) {
		oil_pressure = oilPressure;
	}

	public String getOil_instant() {
		return oil_instant;
	}

	public void setOil_instant(String oilInstant) {
		oil_instant = oilInstant;
	}

	public String getE_torque() {
		return e_torque;
	}

	public void setE_torque(String eTorque) {
		e_torque = eTorque;
	}

	public String getEngine_rotate_speed() {
		return engine_rotate_speed;
	}

	public void setEngine_rotate_speed(String engineRotateSpeed) {
		engine_rotate_speed = engineRotateSpeed;
	}

	public String getBattery_voltage() {
		return battery_voltage;
	}

	public void setBattery_voltage(String batteryVoltage) {
		battery_voltage = batteryVoltage;
	}

	public String getExt_voltage() {
		return ext_voltage;
	}

	public void setExt_voltage(String extVoltage) {
		ext_voltage = extVoltage;
	}

	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	public String getOil_total() {
		return oil_total;
	}

	public void setOil_total(String oilTotal) {
		oil_total = oilTotal;
	}

	public String getQuad_id_type() {
		return quad_id_type;
	}

	public void setQuad_id_type(String quadIdType) {
		quad_id_type = quadIdType;
	}

	public String getMeg_resp_id() {
		return meg_resp_id;
	}

	public void setMeg_resp_id(String megRespId) {
		meg_resp_id = megRespId;
	}

	public String getMeg_id() {
		return meg_id;
	}

	public void setMeg_id(String megId) {
		meg_id = megId;
	}

	public String getMeg_type() {
		return meg_type;
	}

	public void setMeg_type(String megType) {
		meg_type = megType;
	}

	public String getMeg_seq() {
		return meg_seq;
	}

	public void setMeg_seq(String megSeq) {
		meg_seq = megSeq;
	}

	public String getSos_alarm_type_id() {
		return sos_alarm_type_id;
	}

	public void setSos_alarm_type_id(String sos_alarm_type_id) {
		this.sos_alarm_type_id = sos_alarm_type_id;
	}

	public String getOverspeed_alarm_type_id() {
		return overspeed_alarm_type_id;
	}

	public void setOverspeed_alarm_type_id(String overspeed_alarm_type_id) {
		this.overspeed_alarm_type_id = overspeed_alarm_type_id;
	}

	public String getFatigue_alarm_type_id() {
		return fatigue_alarm_type_id;
	}

	public void setFatigue_alarm_type_id(String fatigue_alarm_type_id) {
		this.fatigue_alarm_type_id = fatigue_alarm_type_id;
	}

	public String getGps_alarm_type_id() {
		return gps_alarm_type_id;
	}

	public void setGps_alarm_type_id(String gps_alarm_type_id) {
		this.gps_alarm_type_id = gps_alarm_type_id;
	}

	public String getRapid_alarm_type_id() {
		return rapid_alarm_type_id;
	}

	public void setRapid_alarm_type_id(String rapid_alarm_type_id) {
		this.rapid_alarm_type_id = rapid_alarm_type_id;
	}
}
