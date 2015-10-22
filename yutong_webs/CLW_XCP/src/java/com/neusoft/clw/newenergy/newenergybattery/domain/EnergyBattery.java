package com.neusoft.clw.newenergy.newenergybattery.domain;

public class EnergyBattery {
	/* 主键ID */
	private String dataId;
	/* 车辆VIN */
	private String vehicleVin;
	/* 时间 */
	private String terminalTime;
	/* 经度 */
	private String longitude;
	/* 纬度 */
	private String latitude;
	/* 电池容量SOC */
	private String batSoc;
	/* 电池总电流 */
	private String batCurrent;
	/* 电池总电压 */
	private String batVolTage;
	/* 电池最低电压电芯号 */
	private String batLowvNum;
	/* 电池最高电压电芯号 */
	private String batHighvNum;
	/* 电池系统单体电压差 */
	private String sbatvDiff;
	/* 单体电池最低电压 */
	private String sbatvLow;
	/* 单体电池最高电压 */
	private String sbatvHigh;
	/* 放电电流限制 */
	private String dischargeLimit;
	/* 充电电流限制 */
	private String chargeLimit;
	/* 最低温度单体电池号 */
	private String sbatTempLowNum;
	/* 单体电池最低温度 */
	private String sbatTempLow;
	/* 最高温度单体电池号 */
	private String sbatTempHighNum;
	/* 单体电池最高温度 */
	private String sbatTempHigh;
	/* 电容最低电压电芯号 */
	private String capLowvNum;
	/* 电容最高电压电芯号 */
	private String capHighvNum;
	/* 电容系统单体电压差 */
	private String scapvDiff;
	/* 单体电容平均电压 */
	private String scapAvgV;
	/* 单体电容最低电压 */
	private String scapLowv;
	/* 单体电容最高电压 */
	private String scapHighv;
	/* 最低温度单体电容号 */
	private String scapTempLowNum;
	/* 单体电容最低温度 */
	private String scapTempLow;
	/* 最高温度单体电容号 */
	private String scapTempHighNum;
	/* 单体过压报警模块号 */
	private String sovervAlarmNum;
	/* 单体电容最高温度 */
	private String scapTempHigh;
	/* 档位  000:N（空档）；001：D（前进）； 010：R（倒档）； 011:L（低速）； 100-111无效 */
	private String gears;
	/* 离合器状态 01：分离； 10：结合； */
	private String clutchState;
	/* 当前速度 */
	private String speed;
	/* 制动踏板电压值 */
	private String brakepedalVoltage;
	/* 加速踏板电压值 */
	private String acceleratorpedalVoltage;
	/* 脚刹信号 */
	private String footbrakeSignal;
	/* 手刹信号 */
	private String handbrakeSignal;
	/* ON挡信号 */
	private String onState;
	/* 驾驶员指令扭矩百分比 */
	private String driverCommandTorque;
	/* 线路id */
	private String routeId;
	
	private String routeName;
	
	private String vehicleln;
	/* 实际扭矩 */
	private String eTorque;
	
	public String outaxleSpeed;//输出轴转速
	public String gearCount;//当前齿轮数
	
	public String getDataId() {
		return dataId;
	}
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}
	public String getVehicleVin() {
		return vehicleVin;
	}
	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}
	public String getTerminalTime() {
		return terminalTime;
	}
	public void setTerminalTime(String terminalTime) {
		this.terminalTime = terminalTime;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getBatSoc() {
		return batSoc;
	}
	public void setBatSoc(String batSoc) {
		this.batSoc = batSoc;
	}
	public String getBatCurrent() {
		return batCurrent;
	}
	public void setBatCurrent(String batCurrent) {
		this.batCurrent = batCurrent;
	}
	public String getBatVolTage() {
		return batVolTage;
	}
	public void setBatVolTage(String batVolTage) {
		this.batVolTage = batVolTage;
	}
	public String getBatLowvNum() {
		return batLowvNum;
	}
	public void setBatLowvNum(String batLowvNum) {
		this.batLowvNum = batLowvNum;
	}
	public String getBatHighvNum() {
		return batHighvNum;
	}
	public void setBatHighvNum(String batHighvNum) {
		this.batHighvNum = batHighvNum;
	}
	public String getSbatvDiff() {
		return sbatvDiff;
	}
	public void setSbatvDiff(String sbatvDiff) {
		this.sbatvDiff = sbatvDiff;
	}
	public String getSbatvLow() {
		return sbatvLow;
	}
	public void setSbatvLow(String sbatvLow) {
		this.sbatvLow = sbatvLow;
	}
	public String getSbatvHigh() {
		return sbatvHigh;
	}
	public void setSbatvHigh(String sbatvHigh) {
		this.sbatvHigh = sbatvHigh;
	}
	
	public String getDischargeLimit() {
		return dischargeLimit;
	}
	public void setDischargeLimit(String dischargeLimit) {
		this.dischargeLimit = dischargeLimit;
	}
	public String getChargeLimit() {
		return chargeLimit;
	}
	public void setChargeLimit(String chargeLimit) {
		this.chargeLimit = chargeLimit;
	}
	public String getSbatTempLowNum() {
		return sbatTempLowNum;
	}
	public void setSbatTempLowNum(String sbatTempLowNum) {
		this.sbatTempLowNum = sbatTempLowNum;
	}
	public String getSbatTempLow() {
		return sbatTempLow;
	}
	public void setSbatTempLow(String sbatTempLow) {
		this.sbatTempLow = sbatTempLow;
	}
	public String getSbatTempHighNum() {
		return sbatTempHighNum;
	}
	public void setSbatTempHighNum(String sbatTempHighNum) {
		this.sbatTempHighNum = sbatTempHighNum;
	}
	public String getSbatTempHigh() {
		return sbatTempHigh;
	}
	public void setSbatTempHigh(String sbatTempHigh) {
		this.sbatTempHigh = sbatTempHigh;
	}
	public String getCapLowvNum() {
		return capLowvNum;
	}
	public void setCapLowvNum(String capLowvNum) {
		this.capLowvNum = capLowvNum;
	}
	public String getCapHighvNum() {
		return capHighvNum;
	}
	public void setCapHighvNum(String capHighvNum) {
		this.capHighvNum = capHighvNum;
	}
	public String getScapvDiff() {
		return scapvDiff;
	}
	public void setScapvDiff(String scapvDiff) {
		this.scapvDiff = scapvDiff;
	}
	public String getScapAvgV() {
		return scapAvgV;
	}
	public void setScapAvgV(String scapAvgV) {
		this.scapAvgV = scapAvgV;
	}
	public String getScapLowv() {
		return scapLowv;
	}
	public void setScapLowv(String scapLowv) {
		this.scapLowv = scapLowv;
	}
	public String getScapHighv() {
		return scapHighv;
	}
	public void setScapHighv(String scapHighv) {
		this.scapHighv = scapHighv;
	}
	public String getScapTempLowNum() {
		return scapTempLowNum;
	}
	public void setScapTempLowNum(String scapTempLowNum) {
		this.scapTempLowNum = scapTempLowNum;
	}
	public String getScapTempLow() {
		return scapTempLow;
	}
	public void setScapTempLow(String scapTempLow) {
		this.scapTempLow = scapTempLow;
	}
	public String getScapTempHighNum() {
		return scapTempHighNum;
	}
	public void setScapTempHighNum(String scapTempHighNum) {
		this.scapTempHighNum = scapTempHighNum;
	}
	public String getSovervAlarmNum() {
		return sovervAlarmNum;
	}
	public void setSovervAlarmNum(String sovervAlarmNum) {
		this.sovervAlarmNum = sovervAlarmNum;
	}
	public String getScapTempHigh() {
		return scapTempHigh;
	}
	public void setScapTempHigh(String scapTempHigh) {
		this.scapTempHigh = scapTempHigh;
	}
	public String getGears() {
		return gears;
	}
	public void setGears(String gears) {
		this.gears = gears;
	}
	public String getClutchState() {
		return clutchState;
	}
	public void setClutchState(String clutchState) {
		this.clutchState = clutchState;
	}
	public String getSpeed() {
		return speed;
	}
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	public String getBrakepedalVoltage() {
		return brakepedalVoltage;
	}
	public void setBrakepedalVoltage(String brakepedalVoltage) {
		this.brakepedalVoltage = brakepedalVoltage;
	}
	public String getAcceleratorpedalVoltage() {
		return acceleratorpedalVoltage;
	}
	public void setAcceleratorpedalVoltage(String acceleratorpedalVoltage) {
		this.acceleratorpedalVoltage = acceleratorpedalVoltage;
	}
	public String getFootbrakeSignal() {
		return footbrakeSignal;
	}
	public void setFootbrakeSignal(String footbrakeSignal) {
		this.footbrakeSignal = footbrakeSignal;
	}
	public String getHandbrakeSignal() {
		return handbrakeSignal;
	}
	public void setHandbrakeSignal(String handbrakeSignal) {
		this.handbrakeSignal = handbrakeSignal;
	}
	public String getOnState() {
		return onState;
	}
	public void setOnState(String onState) {
		this.onState = onState;
	}
	public String getDriverCommandTorque() {
		return driverCommandTorque;
	}
	public void setDriverCommandTorque(String driverCommandTorque) {
		this.driverCommandTorque = driverCommandTorque;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getVehicleln() {
		return vehicleln;
	}
	public void setVehicleln(String vehicleln) {
		this.vehicleln = vehicleln;
	}
	public String getETorque() {
		return eTorque;
	}
	public void setETorque(String eTorque) {
		this.eTorque = eTorque;
	}
	public String geteTorque() {
		return eTorque;
	}
	public void seteTorque(String eTorque) {
		this.eTorque = eTorque;
	}
	public String getOutaxleSpeed() {
		return outaxleSpeed;
	}
	public void setOutaxleSpeed(String outaxleSpeed) {
		this.outaxleSpeed = outaxleSpeed;
	}
	public String getGearCount() {
		return gearCount;
	}
	public void setGearCount(String gearCount) {
		this.gearCount = gearCount;
	}
	
	
	
}
