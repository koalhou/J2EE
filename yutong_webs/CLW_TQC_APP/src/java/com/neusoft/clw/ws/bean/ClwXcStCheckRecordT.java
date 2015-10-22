package com.neusoft.clw.ws.bean;


import java.io.Serializable;
import java.util.*;


public class ClwXcStCheckRecordT implements Serializable{
	/**
     * 主键-无意义 . 
     */
    private String id;
    /**
     * 员工号 . 
     */
    private String stuCode;
    /**
     * 站点编码 . 
     */
    private String siteId;
    /**
     * 线路编码 . 
     */
    private String routeId;
    /**
     * 车辆VIN . 
     */
    private String vehicleVin;
    /**
     * 批次号 . 
     */
    private String piciId;
    /**
     * 纬度 . 
     */
    private String latitude;
    /**
     * 经度 . 
     */
    private String longitude;
    /**
     * 上下行状态，0-上学/上行；1-放学/下行 . 
     */
    private String siteFlag;
    /**
     * 乘车状态，0-上车；1-下车；2-未乘车 . 
     */
    private String vssFlag;
    /**
     * 告警类型ID，包括正常、未在规定站点上车、未在规定站点下车、未刷卡（上车）、未刷卡（下车）、“QJ” . 
     */
    private String alarmTypeId;
    /**
     * 已下车学生人数 . 
     */
    private Integer stDowmNum;
    /**
     * 已上车学生人数 . 
     */
    private Integer stUpNum;
    /**
     * 当前车内员工人数 . 
     */
    private Integer stNum;
    /**
     * 终端时间 . 
     */
    private Date terminalTime;
    /**
     * 创建时间 . 
     */
    private Date createTime;
    /**
     * 告警处理标志位，0-未处理；1-已处理 . 
     */
    private String operateFlag;
    /**
     * 告警处理时间 . 
     */
    private Date operateTime;
    /**
     * 告警处理意见 . 
     */
    private String operateDesc;
    /**
     * 确认用户编码 . 
     */
    private String userId;
    /**
     * 计划上车人数 . 
     */
    private Integer planUpNum;
    /**
     * 计划下车人数 . 
     */
    private Integer planDownNum;
    /**
     * 司乘编号 . 
     */
    private String sichenId;
    /**
     * 司机编号 . 
     */
    private String driverId;
    /**
     * 短信提醒标志位，0-未下发；1-下发成功；2-下发失败 . 
     */
    private String mesgFlag;
    /**
     * 短信息发失败原因 . 
     */
    private String smsFailInfo;
    /**
     *  . 
     */
    private String operateType;
    /**
     *  . 
     */
    private String operateRemark;
    /**
     * 行程编码 . 
     */
    private String tripId;
    /**
     * 进出站ID . 
     */
    private String inoutId;
    /**
     * 刷卡位置信息 . 
     */
    private String zonename;
    /**
     * 所刷卡卡ID . 
     */
    private String stuCardId;
    /**
     * 员工姓名 . 
     */
    private String stuName;
    /**
     * 员工所属企业 . 
     */
    private String organizationId;
    /**
     * 初始化单元对象 .
     */ 
    public  ClwXcStCheckRecordT(){
    }
    /**
     * 设置主键-无意义 .
     * @param id 主键-无意义
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 获得主键-无意义 .
     * @return 主键-无意义
     */
    public String getId() {
        return id;
    }
    /**
     * 设置员工号 .
     * @param stuCode 员工号
     */
    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }
    /**
     * 获得员工号 .
     * @return 员工号
     */
    public String getStuCode() {
        return stuCode;
    }
    /**
     * 设置站点编码 .
     * @param siteId 站点编码
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    /**
     * 获得站点编码 .
     * @return 站点编码
     */
    public String getSiteId() {
        return siteId;
    }
    /**
     * 设置线路编码 .
     * @param routeId 线路编码
     */
    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }
    /**
     * 获得线路编码 .
     * @return 线路编码
     */
    public String getRouteId() {
        return routeId;
    }
    /**
     * 设置车辆VIN .
     * @param vehicleVin 车辆VIN
     */
    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }
    /**
     * 获得车辆VIN .
     * @return 车辆VIN
     */
    public String getVehicleVin() {
        return vehicleVin;
    }
    /**
     * 设置批次号 .
     * @param piciId 批次号
     */
    public void setPiciId(String piciId) {
        this.piciId = piciId;
    }
    /**
     * 获得批次号 .
     * @return 批次号
     */
    public String getPiciId() {
        return piciId;
    }
    /**
     * 设置纬度 .
     * @param latitude 纬度
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    /**
     * 获得纬度 .
     * @return 纬度
     */
    public String getLatitude() {
        return latitude;
    }
    /**
     * 设置经度 .
     * @param longitude 经度
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    /**
     * 获得经度 .
     * @return 经度
     */
    public String getLongitude() {
        return longitude;
    }
    /**
     * 设置上下行状态，0-上学/上行；1-放学/下行 .
     * @param siteFlag 上下行状态，0-上学/上行；1-放学/下行
     */
    public void setSiteFlag(String siteFlag) {
        this.siteFlag = siteFlag;
    }
    /**
     * 获得上下行状态，0-上学/上行；1-放学/下行 .
     * @return 上下行状态，0-上学/上行；1-放学/下行
     */
    public String getSiteFlag() {
        return siteFlag;
    }
    /**
     * 设置乘车状态，0-上车；1-下车；2-未乘车 .
     * @param vssFlag 乘车状态，0-上车；1-下车；2-未乘车
     */
    public void setVssFlag(String vssFlag) {
        this.vssFlag = vssFlag;
    }
    /**
     * 获得乘车状态，0-上车；1-下车；2-未乘车 .
     * @return 乘车状态，0-上车；1-下车；2-未乘车
     */
    public String getVssFlag() {
        return vssFlag;
    }
    /**
     * 设置告警类型ID，包括正常、未在规定站点上车、未在规定站点下车、未刷卡（上车）、未刷卡（下车）、“QJ” .
     * @param alarmTypeId 告警类型ID，包括正常、未在规定站点上车、未在规定站点下车、未刷卡（上车）、未刷卡（下车）、“QJ”
     */
    public void setAlarmTypeId(String alarmTypeId) {
        this.alarmTypeId = alarmTypeId;
    }
    /**
     * 获得告警类型ID，包括正常、未在规定站点上车、未在规定站点下车、未刷卡（上车）、未刷卡（下车）、“QJ” .
     * @return 告警类型ID，包括正常、未在规定站点上车、未在规定站点下车、未刷卡（上车）、未刷卡（下车）、“QJ”
     */
    public String getAlarmTypeId() {
        return alarmTypeId;
    }
    /**
     * 设置已下车学生人数 .
     * @param stDowmNum 已下车学生人数
     */
    public void setStDowmNum(Integer stDowmNum) {
        this.stDowmNum = stDowmNum;
    }
    /**
     * 获得已下车学生人数 .
     * @return 已下车学生人数
     */
    public Integer getStDowmNum() {
        return stDowmNum;
    }
    /**
     * 设置已上车学生人数 .
     * @param stUpNum 已上车学生人数
     */
    public void setStUpNum(Integer stUpNum) {
        this.stUpNum = stUpNum;
    }
    /**
     * 获得已上车学生人数 .
     * @return 已上车学生人数
     */
    public Integer getStUpNum() {
        return stUpNum;
    }
    /**
     * 设置当前车内员工人数 .
     * @param stNum 当前车内员工人数
     */
    public void setStNum(Integer stNum) {
        this.stNum = stNum;
    }
    /**
     * 获得当前车内员工人数 .
     * @return 当前车内员工人数
     */
    public Integer getStNum() {
        return stNum;
    }
    /**
     * 设置终端时间 .
     * @param terminalTime 终端时间
     */
    public void setTerminalTime(Date terminalTime) {
        this.terminalTime = terminalTime;
    }
    /**
     * 获得终端时间 .
     * @return 终端时间
     */
    public Date getTerminalTime() {
        return terminalTime;
    }
    /**
     * 设置创建时间 .
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获得创建时间 .
     * @return 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }
    /**
     * 设置告警处理标志位，0-未处理；1-已处理 .
     * @param operateFlag 告警处理标志位，0-未处理；1-已处理
     */
    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }
    /**
     * 获得告警处理标志位，0-未处理；1-已处理 .
     * @return 告警处理标志位，0-未处理；1-已处理
     */
    public String getOperateFlag() {
        return operateFlag;
    }
    /**
     * 设置告警处理时间 .
     * @param operateTime 告警处理时间
     */
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    /**
     * 获得告警处理时间 .
     * @return 告警处理时间
     */
    public Date getOperateTime() {
        return operateTime;
    }
    /**
     * 设置告警处理意见 .
     * @param operateDesc 告警处理意见
     */
    public void setOperateDesc(String operateDesc) {
        this.operateDesc = operateDesc;
    }
    /**
     * 获得告警处理意见 .
     * @return 告警处理意见
     */
    public String getOperateDesc() {
        return operateDesc;
    }
    /**
     * 设置确认用户编码 .
     * @param userId 确认用户编码
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获得确认用户编码 .
     * @return 确认用户编码
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置计划上车人数 .
     * @param planUpNum 计划上车人数
     */
    public void setPlanUpNum(Integer planUpNum) {
        this.planUpNum = planUpNum;
    }
    /**
     * 获得计划上车人数 .
     * @return 计划上车人数
     */
    public Integer getPlanUpNum() {
        return planUpNum;
    }
    /**
     * 设置计划下车人数 .
     * @param planDownNum 计划下车人数
     */
    public void setPlanDownNum(Integer planDownNum) {
        this.planDownNum = planDownNum;
    }
    /**
     * 获得计划下车人数 .
     * @return 计划下车人数
     */
    public Integer getPlanDownNum() {
        return planDownNum;
    }
    /**
     * 设置司乘编号 .
     * @param sichenId 司乘编号
     */
    public void setSichenId(String sichenId) {
        this.sichenId = sichenId;
    }
    /**
     * 获得司乘编号 .
     * @return 司乘编号
     */
    public String getSichenId() {
        return sichenId;
    }
    /**
     * 设置司机编号 .
     * @param driverId 司机编号
     */
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    /**
     * 获得司机编号 .
     * @return 司机编号
     */
    public String getDriverId() {
        return driverId;
    }
    /**
     * 设置短信提醒标志位，0-未下发；1-下发成功；2-下发失败 .
     * @param mesgFlag 短信提醒标志位，0-未下发；1-下发成功；2-下发失败
     */
    public void setMesgFlag(String mesgFlag) {
        this.mesgFlag = mesgFlag;
    }
    /**
     * 获得短信提醒标志位，0-未下发；1-下发成功；2-下发失败 .
     * @return 短信提醒标志位，0-未下发；1-下发成功；2-下发失败
     */
    public String getMesgFlag() {
        return mesgFlag;
    }
    /**
     * 设置短信息发失败原因 .
     * @param smsFailInfo 短信息发失败原因
     */
    public void setSmsFailInfo(String smsFailInfo) {
        this.smsFailInfo = smsFailInfo;
    }
    /**
     * 获得短信息发失败原因 .
     * @return 短信息发失败原因
     */
    public String getSmsFailInfo() {
        return smsFailInfo;
    }
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    public String getOperateType() {
        return operateType;
    }
    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }
    public String getOperateRemark() {
        return operateRemark;
    }
    /**
     * 设置行程编码 .
     * @param tripId 行程编码
     */
    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
    /**
     * 获得行程编码 .
     * @return 行程编码
     */
    public String getTripId() {
        return tripId;
    }
    /**
     * 设置进出站ID .
     * @param inoutId 进出站ID
     */
    public void setInoutId(String inoutId) {
        this.inoutId = inoutId;
    }
    /**
     * 获得进出站ID .
     * @return 进出站ID
     */
    public String getInoutId() {
        return inoutId;
    }
    /**
     * 设置刷卡位置信息 .
     * @param zonename 刷卡位置信息
     */
    public void setZonename(String zonename) {
        this.zonename = zonename;
    }
    /**
     * 获得刷卡位置信息 .
     * @return 刷卡位置信息
     */
    public String getZonename() {
        return zonename;
    }
    /**
     * 设置所刷卡卡ID .
     * @param stuCardId 所刷卡卡ID
     */
    public void setStuCardId(String stuCardId) {
        this.stuCardId = stuCardId;
    }
    /**
     * 获得所刷卡卡ID .
     * @return 所刷卡卡ID
     */
    public String getStuCardId() {
        return stuCardId;
    }
    /**
     * 设置员工姓名 .
     * @param stuName 员工姓名
     */
    public void setStuName(String stuName) {
        this.stuName = stuName;
    }
    /**
     * 获得员工姓名 .
     * @return 员工姓名
     */
    public String getStuName() {
        return stuName;
    }
    /**
     * 设置员工所属企业 .
     * @param organizationId 员工所属企业
     */
    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }
    /**
     * 获得员工所属企业 .
     * @return 员工所属企业
     */
    public String getOrganizationId() {
        return organizationId;
    }
}
