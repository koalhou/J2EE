package com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds;

public class XCTerminalParamsInfo {
    /** 用户ID **/
    private String userId = "";
    
    /** 查询终端ID **/
    private String queryTerminalId = "";
    
    /** 终端号 **/
    private String terminalId = "";
    
    /** 车辆VIN号 **/
    private String vehicleVin = "";
    
    /** SIM卡号 **/
    private String simCardNumber = "";
    
    /** 通信参数 begin **/
    /** 终端心跳发送时间间隔，单位为秒（s） **/
    private String keepAliveTime = "";
    
    /** TCP消息应答超时时间，单位为秒（s） **/
    private String tcpOverTime = "";
    
    /** TCP消息重传次数 **/
    private String tcpRetransTime = "";
    
    /** UDP消息应答超时时间，单位为秒（s） **/
    private String udpOverTime = "";
    
    /** UDP消息重传次数 **/
    private String udpRetransTime = "";
    
    /** 主服务器APN，无线通讯拨号访问点。若网络制式为CDMA，则该处为PPP拨号号码 **/
    private String mainApn = "";
    
    /** 主服务器拨号用户名 **/
    private String mainUser = "";
    
    /** 主服务器拨号密码 **/
    private String mainPass = "";
    
    /** 主服务器地址，IP或域名 **/
    private String mainIp = "";
    
    /** 备份服务器APN，无线通讯拨号访问点 **/
    private String standbyApn = "";
    
    /** 备份服务器拨号用户名 **/
    private String standbyUser = "";
    
    /** 备份服务器拨号密码 **/
    private String standbyPass = "";
    
    /** 备份服务器地址 **/
    private String standbyIp = "";
    
    /** 服务器TCP端口 **/
    private String tcpPort = "";
    
    /** 服务器UDP端口 **/
    private String udpPort = "";
    /** 通信参数 end **/
    
    /** 位置汇报参数begin **/
    /** 位置汇报策略 0：定时汇报 1：定距汇报 2：定时和定距汇报 **/
    private String positionUpType = "";
    
    /** 位置汇报方案 0：根据ACC状态 1：根据登录状态和ACC状态 **/
    private String positionUpSchema = "";
    
    /** 驾驶员未登陆汇报时间间隔 **/
    private String driverOverDateTime = "";
    
    /** 休眠时汇报时间间隔，单位为秒（s）> 0 **/
    private String sleepDateTime = "";
    
    /** 紧急报警时汇报时间间隔，单位为秒（s） >= 0 **/
    private String sosTime = "";
    
    /** 缺省汇报时间间隔，单位为秒（s） > 0 **/
    private String defaultDateTime = "";
    
    /** 缺省汇报距离间隔，单位为米（m） > 0 **/
    private String defaultSpaceTime = "";
    
    /** 驾驶员未登录汇报距离间隔，单位为米（m） > 0 **/
    private String driverOverSpaceTime = "";
    
    /** 休眠时汇报距离间隔，单位为米（m）> 0 **/
    private String sleepSpaceTime = "";
    
    /** 紧急报警时汇报距离间隔，单位为米（m）> 0 **/
    private String sosSpaceTime = "";
    
    /** 拐点补传角度，< 180 **/
    private String makeUpAngle = "";
    /** 位置汇报参数end **/
    
    /** 告警参数 begin **/
    /** 报警屏蔽字 **/
    private boolean alarmShieldFlag = false;
    private Bit32ValueInfo alarmShield = new Bit32ValueInfo();
    /** 报警屏蔽字【查询参数用】 **/
    private String alarmShieldStr = "";
    
    /** 报警拍摄开关 **/
    private boolean alarmShootFlag = false;
    private Bit32ValueInfo alarmShootSwitch = new Bit32ValueInfo();
    /** 报警拍摄开关【查询参数用】 **/
    private String alarmShootSwitchStr = "";
    
    /** 报警存储标志 **/
    private boolean alarmShootSave = false;
    private Bit32ValueInfo alarmShootSaveFlag = new Bit32ValueInfo();
    /** 报警存储标志【查询参数用】 **/
    private String alarmShootSaveFlagStr = "";
    
    /** 最高速度 **/
    private String topSpeed = "";
    
    /** 超速持续时间 **/
    private String overspeedTime = "";
    
    /** 监听电话号码 **/
    private String listenPhone = "";
    
    /** 超速报警预警差值 **/
    private String overspeedAlarmDifference = "";
    /** 告警参数 end **/
    
    /** 车辆属性参数 begin **/
    /** 特征系数 **/
    private String characteristicOefficient = "";
    
    /** 车轮每转脉冲数 **/
    private String wheelPulseCount = "";
    
    /** 油箱容量 **/
    private String fuelCapacity = "";
    
    /** 车辆里程表读数 **/
    private String odometer = "";
    
    /** 车牌号 **/
    private String vehicleLn = "";
    
    /** 车牌颜色 **/
    private String vehicleLnColor = "";
    
    /** 语音输出通道控制 **/
    private String voiceOutputControlType0 = "";
    
    private String voiceOutputControlType1 = "";
    
    private String voiceOutputControlType2 = "";
    
    private String voiceOutputControlType3 = "";
    
    private String voiceOutputControlType4 = "";
    
    private String voiceOutputControlType5 = "";
    
    /** 语音输出通道控制【查询参数用】 **/
    private String voiceOutputControlType = "";
    
    /** 速度来源设置 **/
    private String speedSourceSetting = "";
    
    /** 终端外设安装配置 **/
    private boolean terminalOuterDeviceFlag = false;
    private Bit32ValueInfo terminalOuterDevice = new Bit32ValueInfo();
    /** 终端外设安装配置【查询参数用】 **/
    private String terminalOuterDeviceStr = "";
    /** 车辆属性参数 end **/
    
    /** 拍照控制参数 begin **/
    /** 门开关拍照控制 **/
    private boolean carDoorFlag = false;
    private Bit32ValueInfo carDoorControl = new Bit32ValueInfo();
    /** 门开关拍照控制【查询参数用】 **/
    private String carDoorControlStr = ""; 
    
    /** 定时拍照控制 **/
    private boolean regularCameraFlag = false;
    private Bit32ValueInfo regularCameraControl = new Bit32ValueInfo();
    /** 定时拍照控制【查询参数用】 **/
    private String regularCameraControlStr = "";
    
    /** 定时时间 **/
    private String regularTime = "";
    
    /** 定距拍照控制 **/
    private boolean fixDistanceFlag = false;
    private Bit32ValueInfo fixDistanceCameraControl = new Bit32ValueInfo();
    /** 定距拍照控制【查询参数用】 **/
    private String fixDistanceCameraControlStr = "";
    
    /** 定距距离 **/
    private String fixDistance = "";
    
    /** 拍照控制参数 end **/
    
    
    /** 其他参数begin **/
    /** 学生刷卡自动切换行程控制 **/
    private String autoSwitchTrip = "";
    /** 其他参数end **/
    
	public String getKeepAliveTime() {
        return keepAliveTime;
    }

    public String getQueryTerminalId() {
        return queryTerminalId;
    }

    public void setQueryTerminalId(String queryTerminalId) {
        this.queryTerminalId = queryTerminalId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getSimCardNumber() {
        return simCardNumber;
    }

    public void setSimCardNumber(String simCardNumber) {
        this.simCardNumber = simCardNumber;
    }

    public void setKeepAliveTime(String keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public String getTcpOverTime() {
        return tcpOverTime;
    }

    public void setTcpOverTime(String tcpOverTime) {
        this.tcpOverTime = tcpOverTime;
    }

    public String getTcpRetransTime() {
        return tcpRetransTime;
    }

    public void setTcpRetransTime(String tcpRetransTime) {
        this.tcpRetransTime = tcpRetransTime;
    }

    public String getUdpOverTime() {
        return udpOverTime;
    }

    public void setUdpOverTime(String udpOverTime) {
        this.udpOverTime = udpOverTime;
    }

    public String getUdpRetransTime() {
        return udpRetransTime;
    }

    public void setUdpRetransTime(String udpRetransTime) {
        this.udpRetransTime = udpRetransTime;
    }

    public String getPositionUpType() {
        return positionUpType;
    }

    public void setPositionUpType(String positionUpType) {
        this.positionUpType = positionUpType;
    }

    public String getPositionUpSchema() {
        return positionUpSchema;
    }

    public void setPositionUpSchema(String positionUpSchema) {
        this.positionUpSchema = positionUpSchema;
    }

    public String getSleepDateTime() {
        return sleepDateTime;
    }

    public void setSleepDateTime(String sleepDateTime) {
        this.sleepDateTime = sleepDateTime;
    }

    public String getSosTime() {
        return sosTime;
    }

    public void setSosTime(String sosTime) {
        this.sosTime = sosTime;
    }

    public String getDefaultDateTime() {
        return defaultDateTime;
    }

    public void setDefaultDateTime(String defaultDateTime) {
        this.defaultDateTime = defaultDateTime;
    }

    public String getDefaultSpaceTime() {
        return defaultSpaceTime;
    }

    public void setDefaultSpaceTime(String defaultSpaceTime) {
        this.defaultSpaceTime = defaultSpaceTime;
    }

    public String getDriverOverSpaceTime() {
        return driverOverSpaceTime;
    }

    public void setDriverOverSpaceTime(String driverOverSpaceTime) {
        this.driverOverSpaceTime = driverOverSpaceTime;
    }

    public String getSleepSpaceTime() {
        return sleepSpaceTime;
    }

    public void setSleepSpaceTime(String sleepSpaceTime) {
        this.sleepSpaceTime = sleepSpaceTime;
    }

    public String getSosSpaceTime() {
        return sosSpaceTime;
    }

    public void setSosSpaceTime(String sosSpaceTime) {
        this.sosSpaceTime = sosSpaceTime;
    }

    public String getMakeUpAngle() {
        return makeUpAngle;
    }

    public void setMakeUpAngle(String makeUpAngle) {
        this.makeUpAngle = makeUpAngle;
    }

    public String getMainApn() {
        return mainApn;
    }

    public void setMainApn(String mainApn) {
        this.mainApn = mainApn;
    }

    public String getMainUser() {
        return mainUser;
    }

    public void setMainUser(String mainUser) {
        this.mainUser = mainUser;
    }

    public String getMainPass() {
        return mainPass;
    }

    public void setMainPass(String mainPass) {
        this.mainPass = mainPass;
    }

    public String getStandbyApn() {
        return standbyApn;
    }

    public void setStandbyApn(String standbyApn) {
        this.standbyApn = standbyApn;
    }

    public String getStandbyUser() {
        return standbyUser;
    }

    public void setStandbyUser(String standbyUser) {
        this.standbyUser = standbyUser;
    }

    public String getStandbyPass() {
        return standbyPass;
    }

    public void setStandbyPass(String standbyPass) {
        this.standbyPass = standbyPass;
    }

    public String getMainIp() {
        return mainIp;
    }

    public void setMainIp(String mainIp) {
        this.mainIp = mainIp;
    }

    public String getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(String tcpPort) {
        this.tcpPort = tcpPort;
    }

    public String getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(String udpPort) {
        this.udpPort = udpPort;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStandbyIp() {
        return standbyIp;
    }

    public void setStandbyIp(String standbyIp) {
        this.standbyIp = standbyIp;
    }

    public String getDriverOverDateTime() {
        return driverOverDateTime;
    }

    public void setDriverOverDateTime(String driverOverDateTime) {
        this.driverOverDateTime = driverOverDateTime;
    }

    public Bit32ValueInfo getAlarmShield() {
        return alarmShield;
    }

    public void setAlarmShield(Bit32ValueInfo alarmShield) {
        this.alarmShield = alarmShield;
    }

    public Bit32ValueInfo getAlarmShootSwitch() {
        return alarmShootSwitch;
    }

    public void setAlarmShootSwitch(Bit32ValueInfo alarmShootSwitch) {
        this.alarmShootSwitch = alarmShootSwitch;
    }

    public Bit32ValueInfo getAlarmShootSaveFlag() {
        return alarmShootSaveFlag;
    }

    public void setAlarmShootSaveFlag(Bit32ValueInfo alarmShootSaveFlag) {
        this.alarmShootSaveFlag = alarmShootSaveFlag;
    }

    public String getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(String topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getOverspeedTime() {
        return overspeedTime;
    }

    public void setOverspeedTime(String overspeedTime) {
        this.overspeedTime = overspeedTime;
    }

    public String getListenPhone() {
        return listenPhone;
    }

    public void setListenPhone(String listenPhone) {
        this.listenPhone = listenPhone;
    }

    public String getOverspeedAlarmDifference() {
        return overspeedAlarmDifference;
    }

    public void setOverspeedAlarmDifference(String overspeedAlarmDifference) {
        this.overspeedAlarmDifference = overspeedAlarmDifference;
    }

    public String getCharacteristicOefficient() {
        return characteristicOefficient;
    }

    public void setCharacteristicOefficient(String characteristicOefficient) {
        this.characteristicOefficient = characteristicOefficient;
    }

    public String getWheelPulseCount() {
        return wheelPulseCount;
    }

    public void setWheelPulseCount(String wheelPulseCount) {
        this.wheelPulseCount = wheelPulseCount;
    }

    public String getFuelCapacity() {
        return fuelCapacity;
    }

    public void setFuelCapacity(String fuelCapacity) {
        this.fuelCapacity = fuelCapacity;
    }

    public String getOdometer() {
        return odometer;
    }

    public void setOdometer(String odometer) {
        this.odometer = odometer;
    }

    public String getVehicleLn() {
        return vehicleLn;
    }

    public void setVehicleLn(String vehicleLn) {
        this.vehicleLn = vehicleLn;
    }

    public String getVehicleLnColor() {
        return vehicleLnColor;
    }

    public void setVehicleLnColor(String vehicleLnColor) {
        this.vehicleLnColor = vehicleLnColor;
    }

    public String getVoiceOutputControlType0() {
        return voiceOutputControlType0;
    }

    public void setVoiceOutputControlType0(String voiceOutputControlType0) {
        this.voiceOutputControlType0 = voiceOutputControlType0;
    }

    public String getVoiceOutputControlType1() {
        return voiceOutputControlType1;
    }

    public void setVoiceOutputControlType1(String voiceOutputControlType1) {
        this.voiceOutputControlType1 = voiceOutputControlType1;
    }

    public String getVoiceOutputControlType2() {
        return voiceOutputControlType2;
    }

    public void setVoiceOutputControlType2(String voiceOutputControlType2) {
        this.voiceOutputControlType2 = voiceOutputControlType2;
    }

    public String getVoiceOutputControlType3() {
        return voiceOutputControlType3;
    }

    public void setVoiceOutputControlType3(String voiceOutputControlType3) {
        this.voiceOutputControlType3 = voiceOutputControlType3;
    }

    public String getVoiceOutputControlType4() {
        return voiceOutputControlType4;
    }

    public void setVoiceOutputControlType4(String voiceOutputControlType4) {
        this.voiceOutputControlType4 = voiceOutputControlType4;
    }

    public String getVoiceOutputControlType5() {
        return voiceOutputControlType5;
    }

    public void setVoiceOutputControlType5(String voiceOutputControlType5) {
        this.voiceOutputControlType5 = voiceOutputControlType5;
    }

    public String getSpeedSourceSetting() {
        return speedSourceSetting;
    }

    public void setSpeedSourceSetting(String speedSourceSetting) {
        this.speedSourceSetting = speedSourceSetting;
    }

    public Bit32ValueInfo getCarDoorControl() {
        return carDoorControl;
    }

    public void setCarDoorControl(Bit32ValueInfo carDoorControl) {
        this.carDoorControl = carDoorControl;
    }

    public Bit32ValueInfo getRegularCameraControl() {
        return regularCameraControl;
    }

    public void setRegularCameraControl(Bit32ValueInfo regularCameraControl) {
        this.regularCameraControl = regularCameraControl;
    }

    public String getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(String regularTime) {
        this.regularTime = regularTime;
    }

    public String getFixDistance() {
        return fixDistance;
    }

    public void setFixDistance(String fixDistance) {
        this.fixDistance = fixDistance;
    }

    public Bit32ValueInfo getFixDistanceCameraControl() {
        return fixDistanceCameraControl;
    }

    public void setFixDistanceCameraControl(Bit32ValueInfo fixDistanceCameraControl) {
        this.fixDistanceCameraControl = fixDistanceCameraControl;
    }

    public String getAlarmShieldStr() {
        return alarmShieldStr;
    }

    public void setAlarmShieldStr(String alarmShieldStr) {
        this.alarmShieldStr = alarmShieldStr;
    }

    public String getAlarmShootSwitchStr() {
        return alarmShootSwitchStr;
    }

    public void setAlarmShootSwitchStr(String alarmShootSwitchStr) {
        this.alarmShootSwitchStr = alarmShootSwitchStr;
    }

    public String getAlarmShootSaveFlagStr() {
        return alarmShootSaveFlagStr;
    }

    public void setAlarmShootSaveFlagStr(String alarmShootSaveFlagStr) {
        this.alarmShootSaveFlagStr = alarmShootSaveFlagStr;
    }

    public String getVoiceOutputControlType() {
        return voiceOutputControlType;
    }

    public void setVoiceOutputControlType(String voiceOutputControlType) {
        this.voiceOutputControlType = voiceOutputControlType;
    }

    public String getCarDoorControlStr() {
        return carDoorControlStr;
    }

    public void setCarDoorControlStr(String carDoorControlStr) {
        this.carDoorControlStr = carDoorControlStr;
    }

    public String getRegularCameraControlStr() {
        return regularCameraControlStr;
    }

    public void setRegularCameraControlStr(String regularCameraControlStr) {
        this.regularCameraControlStr = regularCameraControlStr;
    }

    public String getFixDistanceCameraControlStr() {
        return fixDistanceCameraControlStr;
    }

    public void setFixDistanceCameraControlStr(String fixDistanceCameraControlStr) {
        this.fixDistanceCameraControlStr = fixDistanceCameraControlStr;
    }

    public boolean isAlarmShieldFlag() {
        return alarmShieldFlag;
    }

    public void setAlarmShieldFlag(boolean alarmShieldFlag) {
        this.alarmShieldFlag = alarmShieldFlag;
    }

    public boolean isAlarmShootFlag() {
        return alarmShootFlag;
    }

    public void setAlarmShootFlag(boolean alarmShootFlag) {
        this.alarmShootFlag = alarmShootFlag;
    }

    public boolean isAlarmShootSave() {
        return alarmShootSave;
    }

    public void setAlarmShootSave(boolean alarmShootSave) {
        this.alarmShootSave = alarmShootSave;
    }

    public boolean isTerminalOuterDeviceFlag() {
        return terminalOuterDeviceFlag;
    }

    public void setTerminalOuterDeviceFlag(boolean terminalOuterDeviceFlag) {
        this.terminalOuterDeviceFlag = terminalOuterDeviceFlag;
    }

    public Bit32ValueInfo getTerminalOuterDevice() {
        return terminalOuterDevice;
    }

    public void setTerminalOuterDevice(Bit32ValueInfo terminalOuterDevice) {
        this.terminalOuterDevice = terminalOuterDevice;
    }

    public String getTerminalOuterDeviceStr() {
        return terminalOuterDeviceStr;
    }

    public void setTerminalOuterDeviceStr(String terminalOuterDeviceStr) {
        this.terminalOuterDeviceStr = terminalOuterDeviceStr;
    }

    public boolean isCarDoorFlag() {
        return carDoorFlag;
    }

    public void setCarDoorFlag(boolean carDoorFlag) {
        this.carDoorFlag = carDoorFlag;
    }

    public boolean isRegularCameraFlag() {
        return regularCameraFlag;
    }

    public void setRegularCameraFlag(boolean regularCameraFlag) {
        this.regularCameraFlag = regularCameraFlag;
    }

    public boolean isFixDistanceFlag() {
        return fixDistanceFlag;
    }

    public void setFixDistanceFlag(boolean fixDistanceFlag) {
        this.fixDistanceFlag = fixDistanceFlag;
    }

    public String getAutoSwitchTrip() {
        return autoSwitchTrip;
    }

    public void setAutoSwitchTrip(String autoSwitchTrip) {
        this.autoSwitchTrip = autoSwitchTrip;
    }
    
}
