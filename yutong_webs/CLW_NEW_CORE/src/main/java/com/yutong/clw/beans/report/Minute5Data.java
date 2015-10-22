package com.yutong.clw.beans.report;

public class Minute5Data {
	
	String ID           ;      //    ID;            
	String TERMINAL_ID;        //	     终端硬件编码;   
	String VEHICLE_VIN;        //	     车辆VIN号;      
	String SIM_CARD_NUMBER;    //  SIM卡号;        
	String WRITE_TIME;         //	     记录时间;       
	String TEMINAL_TIME;       //     终端时间;       
	String ENGHRREV_T_E_H;     //   发动机运行时间; 
	String BATTERY_VOLTAGE;    //  电池电压;       
	String AIR_PRESSURE;       //   大气压力;       
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getTERMINAL_ID() {
		return TERMINAL_ID;
	}
	public void setTERMINAL_ID(String tERMINALID) {
		TERMINAL_ID = tERMINALID;
	}
	public String getVEHICLE_VIN() {
		return VEHICLE_VIN;
	}
	public void setVEHICLE_VIN(String vEHICLEVIN) {
		VEHICLE_VIN = vEHICLEVIN;
	}
	public String getSIM_CARD_NUMBER() {
		return SIM_CARD_NUMBER;
	}
	public void setSIM_CARD_NUMBER(String sIMCARDNUMBER) {
		SIM_CARD_NUMBER = sIMCARDNUMBER;
	}
	public String getWRITE_TIME() {
		return WRITE_TIME;
	}
	public void setWRITE_TIME(String wRITETIME) {
		WRITE_TIME = wRITETIME;
	}
	public String getTEMINAL_TIME() {
		return TEMINAL_TIME;
	}
	public void setTEMINAL_TIME(String tEMINALTIME) {
		TEMINAL_TIME = tEMINALTIME;
	}
	public String getENGHRREV_T_E_H() {
		return ENGHRREV_T_E_H;
	}
	public void setENGHRREV_T_E_H(String eNGHRREVTEH) {
		ENGHRREV_T_E_H = eNGHRREVTEH;
	}
	public String getBATTERY_VOLTAGE() {
		return BATTERY_VOLTAGE;
	}
	public void setBATTERY_VOLTAGE(String bATTERYVOLTAGE) {
		BATTERY_VOLTAGE = bATTERYVOLTAGE;
	}
	public String getAIR_PRESSURE() {
		return AIR_PRESSURE;
	}
	public void setAIR_PRESSURE(String aIRPRESSURE) {
		AIR_PRESSURE = aIRPRESSURE;
	}
	public String getAIR_INFLOW_TPR() {
		return AIR_INFLOW_TPR;
	}
	public void setAIR_INFLOW_TPR(String aIRINFLOWTPR) {
		AIR_INFLOW_TPR = aIRINFLOWTPR;
	}
	String AIR_INFLOW_TPR;     //   进气温度; 

}
