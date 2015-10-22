
package com.yutong.axxc.tqc.tools;



public class BusinessUtil {
	
	/**
	 * 终端上报方向转换.
	 * @param dirc
	 *            方向数字.
	 * @return 方向文字.
	 */	
	public static String decodeDirction(String dirc){
		String result = "无";
		if(!CheckRequestParam.isEmpty(dirc)){
			Double temp = Double.valueOf(dirc);
			if(temp>=10 && temp<80){
				result = "东北";
			}else if(temp>=80 && temp<100){
				result = "东";
			}else if(temp>=100 && temp<170){
				result = "东南";
			}else if(temp>=170 && temp<190){
				result = "南";
			}else if(temp>=190 && temp<260){
				result = "西南";
			}else if(temp>=260 && temp<280){
				result = "西";
			}else if(temp>=280 && temp<350){
				result = "西北";
			}else{
				result = "北";
			}
		}
		return result;
	}
	
	/**
	 * 解码终端上报state_info中的点火、开关门、定位状态位.
	 * @param state 上报32位状态位
	 * @return 数组[0]开关门；[1]-定位状态；[2]-点火状态.
	 */		
	public static String[] decodeState(String state){
		String[] result = new String[3];
		if(CheckRequestParam.isEmpty(state) || state.length() < 32){
			result[0] = "";
			result[1] = "1";
			result[2] = "1";
		}
		char[] StateArray = state.toCharArray();
		result[0] = String.valueOf(StateArray[18]);
		result[1] = String.valueOf(StateArray[30]);
		result[2] = String.valueOf(StateArray[31]);
		return result;
	}

	/**
	 * 车辆实时告警串alarmBaseInfo解码.
	 * @param alarmBaseInfo
	 *            实时告警串.
	 * @return 实际告警ID数组.
	 */	
	public static String[] decodeBaseAlarm(String alarmBaseInfo){		
		if(CheckRequestParam.isEmpty(alarmBaseInfo) || alarmBaseInfo.length() < 64){
			return null;
		}else if ("0000000000000000000000000000000000000000000000000000000000000000".equals(alarmBaseInfo)){
			return null;
		}
		
		String alarm = "";
		char[] alarmArray = alarmBaseInfo.toCharArray();		
		if(alarmArray[52] == '1'){
			//log.info("摄像头故障");
			alarm += "64,";
		}
		
		if(alarmArray[53] == '1'){
			//log.info("TTS模块故障");
			alarm += "65,";
		}
		
		if(alarmArray[54] == '1'){
			//log.info("终端LCD或显示器故障");
			alarm += "66,";
		}
		if(alarmArray[55] == '1'){
			//log.info("终端主电源掉电");
			alarm += "67,";
		}
		if(alarmArray[56] == '1'){
			//log.info("终端主电源欠压");
			alarm += "68,";
		}
		if(alarmArray[57] == '1'){
			//log.info("GNSS天线短路");
			alarm += "69,";
		}
		if(alarmArray[58] == '1'){
			//log.info("GNSS天线未接或被剪断");
			alarm += "70,";
		}
		if(alarmArray[59] == '1'){
			//log.info("GNSS模块发生故障");
			alarm += "71,";
		}
		if(alarmArray[62] == '1'){
			//log.info("车辆超速");
			alarm += "32,";
		}
		if(alarmArray[63] == '1'){
			//log.info("紧急告警SOS");
			alarm += "40,";
		}
		if(CheckRequestParam.isEmpty(alarm)){
			return null;
		}
		String[] alarmIDs = alarm.substring(0, alarm.length()-1).split(",");
		return alarmIDs;		
	}
	
	/**
	 * 车辆实时告警alarmExtInfo串解码.
	 * @param alarmExtInfo
	 *            实时告警串.
	 * @return 实际告警ID数组.
	 */	
	public static String[] decodeExtAlarm(String alarmExtInfo){		
		if(CheckRequestParam.isEmpty(alarmExtInfo) || alarmExtInfo.length() < 32){
			return null;
		}else if ("00000000000000000000000000000000".equals(alarmExtInfo)){
			return null;
		}
		
		String alarm = "";
		char[] alarmArray = alarmExtInfo.toCharArray();		
		if(alarmArray[10] == '1'){
			//log.info("ABS故障告警");
			alarm += "78,";
		}
		
		if(alarmArray[11] == '1'){
			//log.info("蓄电池电压报警");
			alarm += "77,";
		}
		
		if(alarmArray[12] == '1'){
			//log.info("冷却液温度过高报警");
			alarm += "76,";
		}
		if(alarmArray[24] == '1'){
			//log.info("仓温报警信号");
			alarm += "26,";
		}
		if(alarmArray[25] == '1'){
			//log.info("缓速器高温报警信号");
			alarm += "25,";
		}
		if(alarmArray[27] == '1'){
			//log.info("制动蹄片磨损报警");
			alarm += "13,";
		}
		if(alarmArray[29] == '1'){
			//log.info("油压报警");
			alarm += "10,";
		}
		if(alarmArray[30] == '1'){
			//log.info("制动气压报警");
			alarm += "09,";
		}
		if(CheckRequestParam.isEmpty(alarm)){
			return null;
		}
		String[] alarmIDs = alarm.substring(0, alarm.length()-1).split(",");
		return alarmIDs;		
	}
	
	/**
	 * 车辆实时告警串解码.
	 * @param s
	 *         base和Ext的alarm合并字符串.
	 * @return 告警ID数组.
	 */	
	public static String[] decodeAlarmStr(String s){		
		if(CheckRequestParam.isEmpty(s) || s.length() < 64+32+1){
			return null;
		}else if ("0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000".equals(s)){
			return null;
		}
		
		String alarm = "";
		char[] alarmArray = s.toCharArray();		
		/*if(alarmArray[52] == '1'){
			//log.info("摄像头故障");
			alarm += "64,";
		}
		
		if(alarmArray[53] == '1'){
			//log.info("TTS模块故障");
			alarm += "65,";
		}
		
		if(alarmArray[54] == '1'){
			//log.info("终端LCD或显示器故障");
			alarm += "66,";
		}
		if(alarmArray[55] == '1'){
			//log.info("终端主电源掉电");
			alarm += "67,";
		}
		if(alarmArray[56] == '1'){
			//log.info("终端主电源欠压");
			alarm += "68,";
		}
		if(alarmArray[57] == '1'){
			//log.info("GNSS天线短路");
			alarm += "69,";
		}
		if(alarmArray[58] == '1'){
			//log.info("GNSS天线未接或被剪断");
			alarm += "70,";
		}
		if(alarmArray[59] == '1'){
			//log.info("GNSS模块发生故障");
			alarm += "71,";
		}*/
		if(alarmArray[62] == '1'){
			//log.info("车辆超速");
			alarm += "32,";
		}
		/*if(alarmArray[63] == '1'){
			//log.info("紧急告警SOS");
			alarm += "40,";
		}
		if(alarmArray[74] == '1'){
			//log.info("ABS故障告警");
			alarm += "78,";
		}
		
		if(alarmArray[75] == '1'){
			//log.info("蓄电池电压报警");
			alarm += "77,";
		}
		
		if(alarmArray[76] == '1'){
			//log.info("冷却液温度过高报警");
			alarm += "76,";
		}
		if(alarmArray[88] == '1'){
			//log.info("仓温报警信号");
			alarm += "26,";
		}
		if(alarmArray[89] == '1'){
			//log.info("缓速器高温报警信号");
			alarm += "25,";
		}
		if(alarmArray[91] == '1'){
			//log.info("制动蹄片磨损报警");
			alarm += "13,";
		}
		if(alarmArray[93] == '1'){
			//log.info("油压报警");
			alarm += "10,";
		}
		if(alarmArray[94] == '1'){
			//log.info("制动气压报警");
			alarm += "09,";
		}*/
		if(alarmArray[96] == '1'){
			//log.info("超载");
			alarm += "72,";
		}
		if(CheckRequestParam.isEmpty(alarm)){
			return null;
		}
		String[] alarmIDs = alarm.substring(0, alarm.length()-1).split(",");
		return alarmIDs;		
	}
}
