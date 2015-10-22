package com.yutong.clw.beans.report;

public class ClStatisticsBean {
	String VEHICLE_VIN;         //	     车辆VIN号;   
	String ROUTE_ID ;//线路
	String ALARM_TYPE_ID;         //	    告警类型
	String COUNT;   //数量
	String SITE_UPDOWN; //上行下行
	String PLAN_UP_NUM;	// 				 上行应上车人数总和
	String PLAN_DOWN_NUM	;	//			下行应上车人数总和
	String REALITY_UP_NUM	;	//			上行实际上车人数总和
	String REALITY_DOWN_NUM;	//			下行实际上车人数总和
	
	public String getSITE_UPDOWN() {
		return SITE_UPDOWN;
	}

	public void setSITE_UPDOWN(String sITEUPDOWN) {
		SITE_UPDOWN = sITEUPDOWN;
	}
	public String getPLAN_UP_NUM() {
		return PLAN_UP_NUM;
	}

	public void setPLAN_UP_NUM(String pLANUPNUM) {
		PLAN_UP_NUM = pLANUPNUM;
	}

	public String getPLAN_DOWN_NUM() {
		return PLAN_DOWN_NUM;
	}

	public void setPLAN_DOWN_NUM(String pLANDOWNNUM) {
		PLAN_DOWN_NUM = pLANDOWNNUM;
	}

	public String getREALITY_UP_NUM() {
		return REALITY_UP_NUM;
	}

	public void setREALITY_UP_NUM(String rEALITYUPNUM) {
		REALITY_UP_NUM = rEALITYUPNUM;
	}

	public String getREALITY_DOWN_NUM() {
		return REALITY_DOWN_NUM;
	}

	public void setREALITY_DOWN_NUM(String rEALITYDOWNNUM) {
		REALITY_DOWN_NUM = rEALITYDOWNNUM;
	}

	public String getVEHICLE_VIN() {
		return VEHICLE_VIN;
	}

	public String getROUTE_ID() {
		return ROUTE_ID;
	}

	public void setROUTE_ID(String rOUTEID) {
		ROUTE_ID = rOUTEID;
	}

	public String getALARM_TYPE_ID() {
		return ALARM_TYPE_ID;
	}

	public void setALARM_TYPE_ID(String aLARMTYPEID) {
		ALARM_TYPE_ID = aLARMTYPEID;
	}

	public String getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(String cOUNT) {
		COUNT = cOUNT;
	}

	public void setVEHICLE_VIN(String vEHICLEVIN) {
		VEHICLE_VIN = vEHICLEVIN;
	}

	
	 
}
