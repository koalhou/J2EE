/**
 *    主要功能  : 专属应用防偷漏油   .
 */
package com.neusoft.clw.yw.ftly.ds;


import java.io.Serializable;
import java.util.*;


/** .
 * ************************************************************************
 * <p/>
 * Created Date:2012-5-10  10:10:13
 * Created By:
 * 主要功能  : 专属应用防偷漏油
 * Time    Modifier   Reason
 * <p/>
 * *************************************************************************
 */


public class ZsptFtlyInfo {
    /**
     * 序列号 . 
     */
    private Long ftlyId;
    /**
     * 油箱油位异常标志  000:油位正常; 001:偷油告警; 010:加油告警; 011:保留 . 
     */
    private String oilboxState;
    /**
     * 油箱油位 . 
     */
    private String oilboxLevel;
    /**
     * 当OILBOX_STATE为001时，为本次偷油油量;当为010时，为本次加油油量;当为其他值时，默认为FFFF; . 
     */
    private String addOill;
    /**
     * 油箱油量 . 
     */
    private String oilboxMass;
    /**
     * 纬度 . 
     */
    private String latitude;
    /**
     * 经度 . 
     */
    private String longitude;
    /**
     * 海拔 . 
     */
    private String elevation;
    /**
     * 方向 . 
     */
    private String direction;
    /**
     * GPS速度 . 
     */
    private String gpsSpeeding;
    /**
     * 车速 . 
     */
    private String speeding;
    /**
     * 上报时间 . 
     */
    private Date reportTime;
    /**
     * 所属车辆识别码 . 
     */
    private String vinCode;
    
    private String shortName;
    private String organization;
    private String vehicleTeam;
    private String vehicleLn;
    private String reportTimeString;
    

    private String allAddOill;
    private String allStealOill;
    private String mileage; //当天里程
    private String usedOil; //当天油耗
    private String totalOilPrice;//当天总油价

    private String orgName;
    private String zonename;
    
    private String driverName;
    private String driverTel;
    private String ftylyIdNum;
    
    private String operateDesc;//解除告警备注内容
    private String isValid;//是否解除告警 0: 未解除、1:解除
    
    private String alarmCount;//告警数量
    private String ftlyFlag; //设备是否安装
    private String oilPercent;//油量百分比
    private String oilCapacity;//油量容量
    
    /** 车辆自编码(企业自编码) */
    private String vehicle_code;
    
    /* 处理人 */
    private String operator_name;
    
    private String alarmTypeName;
    /* 处理时间 **/
    private String operateTimeString;
    
    private String compaDate;
    
    private String rno;
    
    private String diffDate;
    
    public String getOperateTimeString() {
		return operateTimeString;
	}
	public void setOperateTimeString(String operateTimeString) {
		this.operateTimeString = operateTimeString;
	}
	public String getAlarmTypeName() {
		return alarmTypeName;
	}
	public void setAlarmTypeName(String alarmTypeName) {
		this.alarmTypeName = alarmTypeName;
	}
	public String getOperator_name() {
		return operator_name;
	}
	public void setOperator_name(String operator_name) {
		this.operator_name = operator_name;
	}
	public String getFtlyFlag() {
		return ftlyFlag;
	}
	public void setFtlyFlag(String ftlyFlag) {
		this.ftlyFlag = ftlyFlag;
	}
	public String getOilPercent() {
		return oilPercent;
	}
	public void setOilPercent(String oilPercent) {
		this.oilPercent = oilPercent;
	}
	/**
     * 初始化单元对象 .
     */ 
    public  ZsptFtlyInfo(){
    }
    /**
     * 设置序列号 .
     * @param ftlyId 序列号
     */
    public void setFtlyId(Long ftlyId) {
        this.ftlyId = ftlyId;
    }
    /**
     * 获得序列号 .
     * @return 序列号
     */
    public Long getFtlyId() {
        return ftlyId;
    }
    /**
     * 设置油箱油位异常标志 000:油位正常;001:偷油告警;010:加油告警;011:告警 .
     * @param oilboxState 油箱油位异常标志0 00:油位正常;001:偷油告警;010:加油告警;011:告警
     */
    public void setOilboxState(String oilboxState) {
        this.oilboxState = oilboxState;
    }
    /**
     * 获得油箱油位异常标志 000:油位正常;001:偷油告警;010:加油告警;011:告警 .
     * @return 油箱油位异常标志 000:油位正常;001:偷油告警;010:加油告警;011:告警
     */
    public String getOilboxState() {
        return oilboxState;
    }
    /**
     * 设置油箱油位 .
     * @param oilboxLevel 油箱油位
     */
    public void setOilboxLevel(String oilboxLevel) {
        this.oilboxLevel = oilboxLevel;
    }
    /**
     * 获得油箱油位 .
     * @return 油箱油位
     */
    public String getOilboxLevel() {
        return oilboxLevel;
    }
    /**
     * 设置加油量 .
     * @param addOill 加油量
     */
    public void setAddOill(String addOill) {
        this.addOill = addOill;
    }
    /**
     * 获得加油量 .
     * @return 加油量
     */
    public String getAddOill() {
        return addOill;
    }
    /**
     * 设置油箱油量 .
     * @param oilboxMass 油箱油量
     */
    public void setOilboxMass(String oilboxMass) {
        this.oilboxMass = oilboxMass;
    }
    /**
     * 获得油箱油量 .
     * @return 油箱油量
     */
    public String getOilboxMass() {
        return oilboxMass;
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
     * 设置海拔 .
     * @param elevation 海拔
     */
    public void setElevation(String elevation) {
        this.elevation = elevation;
    }
    /**
     * 获得海拔 .
     * @return 海拔
     */
    public String getElevation() {
        return elevation;
    }
    /**
     * 设置方向 .
     * @param direction 方向
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }
    /**
     * 获得方向 .
     * @return 方向
     */
    public String getDirection() {
        return direction;
    }
    /**
     * 设置GPS速度 .
     * @param gpsSpeeding GPS速度
     */
    public void setGpsSpeeding(String gpsSpeeding) {
        this.gpsSpeeding = gpsSpeeding;
    }
    /**
     * 获得GPS速度 .
     * @return GPS速度
     */
    public String getGpsSpeeding() {
        return gpsSpeeding;
    }
    /**
     * 设置车速 .
     * @param speeding 车速
     */
    public void setSpeeding(String speeding) {
        this.speeding = speeding;
    }
    /**
     * 获得车速 .
     * @return 车速
     */
    public String getSpeeding() {
        return speeding;
    }
    /**
     * 设置上报时间 .
     * @param reportTime 上报时间
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
    /**
     * 获得上报时间 .
     * @return 上报时间
     */
    public Date getReportTime() {
        return reportTime;
    }
    /**
     * 设置所属车辆识别码 .
     * @param vinCode 所属车辆识别码
     */
    public void setVinCode(String vinCode) {
        this.vinCode = vinCode;
    }
    /**
     * 获得所属车辆识别码 .
     * @return 所属车辆识别码
     */
    public String getVinCode() {
        return vinCode;
    }
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getVehicleTeam() {
		return vehicleTeam;
	}
	public void setVehicleTeam(String vehicleTeam) {
		this.vehicleTeam = vehicleTeam;
	}
	public String getVehicleLn() {
		return vehicleLn;
	}
	public void setVehicleLn(String vehicleLn) {
		this.vehicleLn = vehicleLn;
	}
	public String getReportTimeString() {
		return reportTimeString;
	}
	public void setReportTimeString(String reportTimeString) {
		this.reportTimeString = reportTimeString;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getAllAddOill() {
		return allAddOill;
	}
	public void setAllAddOill(String allAddOill) {
		this.allAddOill = allAddOill;
	}
	public String getAllStealOill() {
		return allStealOill;
	}
	public void setAllStealOill(String allStealOill) {
		this.allStealOill = allStealOill;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getZonename() {
		return zonename;
	}
	public void setZonename(String zonename) {
		this.zonename = zonename;
	}
	public String getAlarmCount() {
		return alarmCount;
	}
	public void setAlarmCount(String alarmCount) {
		this.alarmCount = alarmCount;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public String getDriverTel() {
		return driverTel;
	}
	public void setDriverTel(String driverTel) {
		this.driverTel = driverTel;
	}
	public String getOperateDesc() {
		return operateDesc;
	}
	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getFtylyIdNum() {
		return ftylyIdNum;
	}
	public void setFtylyIdNum(String ftylyIdNum) {
		this.ftylyIdNum = ftylyIdNum;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getUsedOil() {
		return usedOil;
	}
	public void setUsedOil(String usedOil) {
		this.usedOil = usedOil;
	}
	public String getTotalOilPrice() {
		return totalOilPrice;
	}
	public void setTotalOilPrice(String totalOilPrice) {
		this.totalOilPrice = totalOilPrice;
	}
	public String getOilCapacity() {
		return oilCapacity;
	}
	public void setOilCapacity(String oilCapacity) {
		this.oilCapacity = oilCapacity;
	}
	public String getVehicle_code() {
		return vehicle_code;
	}
	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}
	public String getCompaDate() {
		return compaDate;
	}
	public void setCompaDate(String compaDate) {
		this.compaDate = compaDate;
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getDiffDate() {
		return diffDate;
	}
	public void setDiffDate(String diffDate) {
		this.diffDate = diffDate;
	}
	
    
}
