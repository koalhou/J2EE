package com.neusoft.clw.yw.xj.operatingreport.ds;

/**
 * 统计信息bean
 * @author <a href="mailto:jin.p@neusoft.com">Jinp </a>
 * @version $Revision 1.1 $ 2011-9-6 上午08:56:37
 */
public class TotalReportInfo {
    /** 企业总数 **/
    private String enterpriseCnt = "";
    
    /** 注册车辆数 **/
    private String registeredVehicleCnt = "";
    
    /** 当日活动车辆 **/
    private String currentOnlineCnt = "";
    
    /** 当日上线率 **/
    private String onlineRate = "";
    
    /** 历史活动车辆 **/
    private String offlineCnt = "";
    
    /** 正常上报台数 **/
    private String normalCnt = "";
    
    /** 不正常上报台数 **/
    private String unnormalCnt = "";

    public String getEnterpriseCnt() {
        return enterpriseCnt;
    }

    public void setEnterpriseCnt(String enterpriseCnt) {
        this.enterpriseCnt = enterpriseCnt;
    }

    public String getRegisteredVehicleCnt() {
        return registeredVehicleCnt;
    }

    public void setRegisteredVehicleCnt(String registeredVehicleCnt) {
        this.registeredVehicleCnt = registeredVehicleCnt;
    }

    public String getCurrentOnlineCnt() {
        return currentOnlineCnt;
    }

    public void setCurrentOnlineCnt(String currentOnlineCnt) {
        this.currentOnlineCnt = currentOnlineCnt;
    }

    public String getOnlineRate() {
        return onlineRate;
    }

    public void setOnlineRate(String onlineRate) {
        this.onlineRate = onlineRate;
    }

    public String getOfflineCnt() {
        return offlineCnt;
    }

    public void setOfflineCnt(String offlineCnt) {
        this.offlineCnt = offlineCnt;
    }

    public String getNormalCnt() {
        return normalCnt;
    }

    public void setNormalCnt(String normalCnt) {
        this.normalCnt = normalCnt;
    }

    public String getUnnormalCnt() {
        return unnormalCnt;
    }

    public void setUnnormalCnt(String unnormalCnt) {
        this.unnormalCnt = unnormalCnt;
    }
}
