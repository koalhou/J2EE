package com.yutong.clw.beans.report;

public class VehicleSpeed {
	 
	float    SUMSPEED         =0    ;//   车速=0运行次数          
	public float getSUMSPEED() {
		return SUMSPEED;
	}
	public void setSUMSPEED(float sUMSPEED) {
		SUMSPEED = sUMSPEED;
	}
	String    SPEED_ID              ;//主键                           
	String  VEHICLE_VIN           ;//  车辆VIN                        
	String    TERMINAL_DAY          ;//终端数据统计日期                
	String  CREATE_TIME           ;//  入库创建时间                    
	float    SPEEDING_0         =0    ;//   车速=0运行次数                  
	float  SPEEDING_0_TIME    =0     ;//   车速=0运行时间                  
	float    SPEEDING_0_10      =0    ;//   0<车速<10运行次数               
	float  SPEEDING_0_10_TIME =0    ;//   0<车速<10运行时间               
	float    SPEEDING_10_20      =0   ;//   10<=车速<20运行次数             
	float  SPEEDING_10_20_TIME =0   ;//   10<=车速<20运行时间             
	float    SPEEDING_20_30      =0   ;//   20<=车速<30运行次数             
	public String getSPEED_ID() {
		return SPEED_ID;
	}
	public void setSPEED_ID(String sPEEDID) {
		SPEED_ID = sPEEDID;
	}
	public String getVEHICLE_VIN() {
		return VEHICLE_VIN;
	}
	public void setVEHICLE_VIN(String vEHICLEVIN) {
		VEHICLE_VIN = vEHICLEVIN;
	}
	public String getTERMINAL_DAY() {
		return TERMINAL_DAY;
	}
	public void setTERMINAL_DAY(String tERMINALDAY) {
		TERMINAL_DAY = tERMINALDAY;
	}
	public String getCREATE_TIME() {
		return CREATE_TIME;
	}
	public void setCREATE_TIME(String cREATETIME) {
		CREATE_TIME = cREATETIME;
	}
	public float getSPEEDING_0() {
		return SPEEDING_0;
	}
	public void setSPEEDING_0(float sPEEDING_0) {
		SPEEDING_0 = sPEEDING_0;
	}
	public float getSPEEDING_0_TIME() {
		return SPEEDING_0_TIME;
	}
	public void setSPEEDING_0_TIME(float sPEEDING_0TIME) {
		SPEEDING_0_TIME = sPEEDING_0TIME;
	}
	public float getSPEEDING_0_10() {
		return SPEEDING_0_10;
	}
	public void setSPEEDING_0_10(float sPEEDING_0_10) {
		SPEEDING_0_10 = sPEEDING_0_10;
	}
	public float getSPEEDING_0_10_TIME() {
		return SPEEDING_0_10_TIME;
	}
	public void setSPEEDING_0_10_TIME(float sPEEDING_0_10TIME) {
		SPEEDING_0_10_TIME = sPEEDING_0_10TIME;
	}
	public float getSPEEDING_10_20() {
		return SPEEDING_10_20;
	}
	public void setSPEEDING_10_20(float sPEEDING_10_20) {
		SPEEDING_10_20 = sPEEDING_10_20;
	}
	public float getSPEEDING_10_20_TIME() {
		return SPEEDING_10_20_TIME;
	}
	public void setSPEEDING_10_20_TIME(float sPEEDING_10_20TIME) {
		SPEEDING_10_20_TIME = sPEEDING_10_20TIME;
	}
	public float getSPEEDING_20_30() {
		return SPEEDING_20_30;
	}
	public void setSPEEDING_20_30(float sPEEDING_20_30) {
		SPEEDING_20_30 = sPEEDING_20_30;
	}
	public float getSPEEDING_20_30_TIME() {
		return SPEEDING_20_30_TIME;
	}
	public void setSPEEDING_20_30_TIME(float sPEEDING_20_30TIME) {
		SPEEDING_20_30_TIME = sPEEDING_20_30TIME;
	}
	public float getSPEEDING_30_40() {
		return SPEEDING_30_40;
	}
	public void setSPEEDING_30_40(float sPEEDING_30_40) {
		SPEEDING_30_40 = sPEEDING_30_40;
	}
	public float getSPEEDING_30_40_TIME() {
		return SPEEDING_30_40_TIME;
	}
	public void setSPEEDING_30_40_TIME(float sPEEDING_30_40TIME) {
		SPEEDING_30_40_TIME = sPEEDING_30_40TIME;
	}
	public float getSPEEDING_40_50() {
		return SPEEDING_40_50;
	}
	public void setSPEEDING_40_50(float sPEEDING_40_50) {
		SPEEDING_40_50 = sPEEDING_40_50;
	}
	public float getSPEEDING_40_50_TIME() {
		return SPEEDING_40_50_TIME;
	}
	public void setSPEEDING_40_50_TIME(float sPEEDING_40_50TIME) {
		SPEEDING_40_50_TIME = sPEEDING_40_50TIME;
	}
	public float getSPEEDING_50_60() {
		return SPEEDING_50_60;
	}
	public void setSPEEDING_50_60(float sPEEDING_50_60) {
		SPEEDING_50_60 = sPEEDING_50_60;
	}
	public float getSPEEDING_50_60_TIME() {
		return SPEEDING_50_60_TIME;
	}
	public void setSPEEDING_50_60_TIME(float sPEEDING_50_60TIME) {
		SPEEDING_50_60_TIME = sPEEDING_50_60TIME;
	}
	public float getSPEEDING_60_70() {
		return SPEEDING_60_70;
	}
	public void setSPEEDING_60_70(float sPEEDING_60_70) {
		SPEEDING_60_70 = sPEEDING_60_70;
	}
	public float getSPEEDING_60_70_TIME() {
		return SPEEDING_60_70_TIME;
	}
	public void setSPEEDING_60_70_TIME(float sPEEDING_60_70TIME) {
		SPEEDING_60_70_TIME = sPEEDING_60_70TIME;
	}
	public float getSPEEDING_70_80() {
		return SPEEDING_70_80;
	}
	public void setSPEEDING_70_80(float sPEEDING_70_80) {
		SPEEDING_70_80 = sPEEDING_70_80;
	}
	public float getSPEEDING_70_80_TIME() {
		return SPEEDING_70_80_TIME;
	}
	public void setSPEEDING_70_80_TIME(float sPEEDING_70_80TIME) {
		SPEEDING_70_80_TIME = sPEEDING_70_80TIME;
	}
	public float getSPEEDING_80_90() {
		return SPEEDING_80_90;
	}
	public void setSPEEDING_80_90(float sPEEDING_80_90) {
		SPEEDING_80_90 = sPEEDING_80_90;
	}
	public float getSPEEDING_80_90_TIME() {
		return SPEEDING_80_90_TIME;
	}
	public void setSPEEDING_80_90_TIME(float sPEEDING_80_90TIME) {
		SPEEDING_80_90_TIME = sPEEDING_80_90TIME;
	}
	public float getSPEEDING_90_100() {
		return SPEEDING_90_100;
	}
	public void setSPEEDING_90_100(float sPEEDING_90_100) {
		SPEEDING_90_100 = sPEEDING_90_100;
	}
	public float getSPEEDING_90_100_TIME() {
		return SPEEDING_90_100_TIME;
	}
	public void setSPEEDING_90_100_TIME(float sPEEDING_90_100TIME) {
		SPEEDING_90_100_TIME = sPEEDING_90_100TIME;
	}
	public float getSPEEDING_100_110() {
		return SPEEDING_100_110;
	}
	public void setSPEEDING_100_110(float sPEEDING_100_110) {
		SPEEDING_100_110 = sPEEDING_100_110;
	}
	public float getSPEEDING_100_110_TIME() {
		return SPEEDING_100_110_TIME;
	}
	public void setSPEEDING_100_110_TIME(float sPEEDING_100_110TIME) {
		SPEEDING_100_110_TIME = sPEEDING_100_110TIME;
	}
	public float getSPEEDING_110_120() {
		return SPEEDING_110_120;
	}
	public void setSPEEDING_110_120(float sPEEDING_110_120) {
		SPEEDING_110_120 = sPEEDING_110_120;
	}
	public float getSPEEDING_110_120_TIME() {
		return SPEEDING_110_120_TIME;
	}
	public void setSPEEDING_110_120_TIME(float sPEEDING_110_120TIME) {
		SPEEDING_110_120_TIME = sPEEDING_110_120TIME;
	}
	public float getSPEEDING_120_130() {
		return SPEEDING_120_130;
	}
	public void setSPEEDING_120_130(float sPEEDING_120_130) {
		SPEEDING_120_130 = sPEEDING_120_130;
	}
	public float getSPEEDING_120_130_TIME() {
		return SPEEDING_120_130_TIME;
	}
	public void setSPEEDING_120_130_TIME(float sPEEDING_120_130TIME) {
		SPEEDING_120_130_TIME = sPEEDING_120_130TIME;
	}
	public float getSPEEDING_130_140() {
		return SPEEDING_130_140;
	}
	public void setSPEEDING_130_140(float sPEEDING_130_140) {
		SPEEDING_130_140 = sPEEDING_130_140;
	}
	public float getSPEEDING_130_140_TIME() {
		return SPEEDING_130_140_TIME;
	}
	public void setSPEEDING_130_140_TIME(float sPEEDING_130_140TIME) {
		SPEEDING_130_140_TIME = sPEEDING_130_140TIME;
	}
	public float getSPEEDING_140_150() {
		return SPEEDING_140_150;
	}
	public void setSPEEDING_140_150(float sPEEDING_140_150) {
		SPEEDING_140_150 = sPEEDING_140_150;
	}
	public float getSPEEDING_140_150_TIME() {
		return SPEEDING_140_150_TIME;
	}
	public void setSPEEDING_140_150_TIME(float sPEEDING_140_150TIME) {
		SPEEDING_140_150_TIME = sPEEDING_140_150TIME;
	}
	public float getSPEEDING_150_160() {
		return SPEEDING_150_160;
	}
	public void setSPEEDING_150_160(float sPEEDING_150_160) {
		SPEEDING_150_160 = sPEEDING_150_160;
	}
	public float getSPEEDING_150_160_TIME() {
		return SPEEDING_150_160_TIME;
	}
	public void setSPEEDING_150_160_TIME(float sPEEDING_150_160TIME) {
		SPEEDING_150_160_TIME = sPEEDING_150_160TIME;
	}
	public float getSPEEDING_160_170() {
		return SPEEDING_160_170;
	}
	public void setSPEEDING_160_170(float sPEEDING_160_170) {
		SPEEDING_160_170 = sPEEDING_160_170;
	}
	public float getSPEEDING_160_170_TIME() {
		return SPEEDING_160_170_TIME;
	}
	public void setSPEEDING_160_170_TIME(float sPEEDING_160_170TIME) {
		SPEEDING_160_170_TIME = sPEEDING_160_170TIME;
	}
	public float getSPEEDING_170_180() {
		return SPEEDING_170_180;
	}
	public void setSPEEDING_170_180(float sPEEDING_170_180) {
		SPEEDING_170_180 = sPEEDING_170_180;
	}
	public float getSPEEDING_170_180_TIME() {
		return SPEEDING_170_180_TIME;
	}
	public void setSPEEDING_170_180_TIME(float sPEEDING_170_180TIME) {
		SPEEDING_170_180_TIME = sPEEDING_170_180TIME;
	}
	public float getSPEEDING_180_190() {
		return SPEEDING_180_190;
	}
	public void setSPEEDING_180_190(float sPEEDING_180_190) {
		SPEEDING_180_190 = sPEEDING_180_190;
	}
	public float getSPEEDING_180_190_TIME() {
		return SPEEDING_180_190_TIME;
	}
	public void setSPEEDING_180_190_TIME(float sPEEDING_180_190TIME) {
		SPEEDING_180_190_TIME = sPEEDING_180_190TIME;
	}
	public float getSPEEDING_190_200() {
		return SPEEDING_190_200;
	}
	public void setSPEEDING_190_200(float sPEEDING_190_200) {
		SPEEDING_190_200 = sPEEDING_190_200;
	}
	public float getSPEEDING_190_200_TIME() {
		return SPEEDING_190_200_TIME;
	}
	public void setSPEEDING_190_200_TIME(float sPEEDING_190_200TIME) {
		SPEEDING_190_200_TIME = sPEEDING_190_200TIME;
	}
	public float getSPEEDING_MAX() {
		return SPEEDING_MAX;
	}
	public void setSPEEDING_MAX(float sPEEDINGMAX) {
		SPEEDING_MAX = sPEEDINGMAX;
	}
	public float getSPEEDING_MAX_TIME() {
		return SPEEDING_MAX_TIME;
	}
	public void setSPEEDING_MAX_TIME(float sPEEDINGMAXTIME) {
		SPEEDING_MAX_TIME = sPEEDINGMAXTIME;
	}
	float  SPEEDING_20_30_TIME =0   ;//   20<=车速<30运行时间             
	float    SPEEDING_30_40      =0   ;//   30<=车速<40运行次数             
	float  SPEEDING_30_40_TIME =0   ;//   30<=车速<40运行时间             
	float    SPEEDING_40_50      =0   ;//   40<=车速<50运行次数             
	float  SPEEDING_40_50_TIME =0   ;//   40<=车速<50运行时间             
	float    SPEEDING_50_60      =0   ;//   50<=车速<60运行次数             
	float  SPEEDING_50_60_TIME =0   ;//   50<=车速<60运行时间             
	float    SPEEDING_60_70      =0   ;//   60<=车速<70运行次数             
	float  SPEEDING_60_70_TIME =0   ;//   60<=车速<70运行时间             
	float    SPEEDING_70_80       =0  ;//   70<=车速<80运行次数             
	float  SPEEDING_70_80_TIME  =0  ;//   70<=车速<80运行时间             
	float    SPEEDING_80_90       =0  ;//   80<=车速<90运行次数             
	float  SPEEDING_80_90_TIME  =0  ;//   80<=车速<90运行时间             
	float    SPEEDING_90_100      =0  ;//   90<=车速<100运行次数            
	float  SPEEDING_90_100_TIME =0  ;//   90<=车速<100运行时间            
	float    SPEEDING_100_110     =0  ;//   100<=车速<110运行次数           
	float  SPEEDING_100_110_TIME=0  ;//   100<=车速<110运行时间           
	float    SPEEDING_110_120     =0  ;//   110<=车速<120运行次数           
	float  SPEEDING_110_120_TIME=0  ;//   110<=车速<120运行时间           
	float    SPEEDING_120_130     =0  ;//   120<=车速<130运行次数           
	float  SPEEDING_120_130_TIME=0  ;//   120<=车速<130运行时间           
	float    SPEEDING_130_140     =0  ;//   130<=车速<140运行次数           
	float  SPEEDING_130_140_TIME=0  ;//   130<=车速<140运行时间           
	float    SPEEDING_140_150     =0  ;//   140<=车速<150运行次数           
	float  SPEEDING_140_150_TIME=0  ;//   140<=车速<150运行时间           
	float    SPEEDING_150_160     =0  ;//   150<=车速<160运行次数           
	float  SPEEDING_150_160_TIME=0  ;//   150<=车速<160运行时间           
	float    SPEEDING_160_170     =0  ;//   160<=车速<170运行次数           
	float  SPEEDING_160_170_TIME=0  ;//   160<=车速<170运行时间           
	float    SPEEDING_170_180     =0  ;//   170<=车速<180运行次数           
	float  SPEEDING_170_180_TIME=0  ;//   170<=车速<180运行时间           
	float    SPEEDING_180_190     =0  ;//   180<=车速<190运行次数           
	float  SPEEDING_180_190_TIME=0  ;//   180<=车速<190运行时间           
	float    SPEEDING_190_200      =0 ;//   190<=车速<200运行次数           
	float  SPEEDING_190_200_TIME =0 ;//   190<=车速<200运行时间           
	float    SPEEDING_MAX          =0 ;//   车速>=200运行次数               
	float  SPEEDING_MAX_TIME     =0 ;//   车速>=200运行时间   
	float MAX_SPEEDING = 0;
	float MIN_SPEEDING = 0;
	public float getMAX_SPEEDING() {
		return MAX_SPEEDING;
	}
	public void setMAX_SPEEDING(float mAXSPEEDING) {
		MAX_SPEEDING = mAXSPEEDING;
	}
	public float getMIN_SPEEDING() {
		return MIN_SPEEDING;
	}
	public void setMIN_SPEEDING(float mINSPEEDING) {
		MIN_SPEEDING = mINSPEEDING;
	}
}
