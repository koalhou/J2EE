package com.neusoft.clw.vncs.inside.msg.content.value;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.insert.InsertBuffer;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.service.BuildSQL;
import com.neusoft.clw.vncs.service.CachePattern;
import com.neusoft.clw.vncs.util.AccountUtil;
import com.neusoft.clw.vncs.util.Converser;

public class Up_BlindAreaValue {
	
	private static final Logger log = LoggerFactory.getLogger(Up_BlindAreaValue.class);
	
	public static void getUp_BlindAreaValue(int location, byte[] buf,Up_InfoContent urt) throws UnsupportedEncodingException{
		
//		byte[] bytename = new byte[ContentParamName.REGULARLEN];
//		byte[] bytelen = null;
//		byte[] bytevalue = null;
		
		@SuppressWarnings("unused")
		String bytecmd = new String(buf,location,ContentParamName.REGULARLEN);
		location+=ContentParamName.REGULARLEN;
		int bytelen01 = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
		location+=ContentParamName.REGULARLEN;
		int bytevalue01 = Integer.parseInt(new String(buf,location,bytelen01),16);
		location+=bytelen01;
		for(int i=0;i<bytevalue01;i++){
			//02 cmd
			location+=ContentParamName.REGULARLEN;
			//02 length
			int bytelen02 = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN*2),16);
			location+=ContentParamName.REGULARLEN*2;
			int tlvLen = 0;
			String cmd = null;
//			int msgLength = Integer.parseInt(urt.getMsgLength());
			int loc = 0;
			while (loc < bytelen02) {
				cmd = new String(buf,location+loc,ContentParamName.REGULARLEN);
				loc += ContentParamName.REGULARLEN;
				if(cmd.equals(ContentParamName.packet_content2F)){
					tlvLen = Integer.parseInt(new String(buf,location+loc,ContentParamName.REGULARLEN*2),16);
					loc +=ContentParamName.REGULARLEN*2;
				}else{
					tlvLen = Integer.parseInt(new String(buf,location+loc,ContentParamName.REGULARLEN),16);
					loc += ContentParamName.REGULARLEN;
				}
				String tlvValue = new String(buf,location+loc,tlvLen).trim();
				loc+=tlvLen;
				setUpRealTimeContent(cmd, tlvValue,urt);
			}
			location+=bytelen02;
			InsertBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUp_RealTimeSql(urt,IdCreater.getUUid())); 
		}
	}

	private static Up_InfoContent setUpRealTimeContent(String cmd,
			String tlvValue,Up_InfoContent urt) throws UnsupportedEncodingException{
		if (cmd.equals(ContentParamName.packet_content00)) {
			urt.setMsg_cmd(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content01)) {
			if(!tlvValue.equals("FFFFFFFFFFFF")){
				urt.setUtc_time(tlvValue);
			}else{
				urt.setUtc_time("");
			}
		} else if (cmd.equals(ContentParamName.packet_content02)) {
			urt.setGps_valid(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content03)) {
			urt.setLatitude(ContentParamName.checkLength(AccountUtil.accountLongLat(tlvValue),ContentParamName.LATITUDE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content04)) {
			urt.setLongitude(ContentParamName.checkLength(AccountUtil.accountLongLat(tlvValue),ContentParamName.LONGITUDE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content05)) {
			urt.setGps_speeding(AccountUtil.setHexString(tlvValue, Constant.F5,1000));
		} else if (cmd.equals(ContentParamName.packet_content06)) {
			urt.setDirection(AccountUtil.setHexString(tlvValue,Constant.F4,100));
		} else if (cmd.equals(ContentParamName.packet_content07)) {
			urt.setSpeeding(AccountUtil.setHexString(tlvValue,Constant.F2));
		} else if (cmd.equals(ContentParamName.packet_content08)) {
			String on_off = parseInformation(tlvValue);
			String frontdoor = on_off.substring(19, 20);
			String middledoor = on_off.substring(20, 21);
			if(frontdoor.equals("1")){
				urt.setFrontdoor_on_off(Constant.ON);
			}else{
				urt.setFrontdoor_on_off(Constant.OFF);
			}
			if(middledoor.equals("1")){
				urt.setMiddledoor_on_off(Constant.ON);
			}else{
				urt.setMiddledoor_on_off(Constant.OFF);
			}
			urt.setOn_off(on_off);
		} else if (cmd.equals(ContentParamName.packet_content09)) {
			urt.setAlarm_state(parseInformation(tlvValue));
			CachePattern.getInstance().setUp_RealTimeAlarmState(urt,parseInformation(tlvValue));
		} else if (cmd.equals(ContentParamName.packet_content0E)) {
			if(!tlvValue.equals("FFFFFFFF")){
				urt.setDriver_id(tlvValue);
			}
		} else if (cmd.equals(ContentParamName.packet_content0F)) {
			if(new String(Converser.hexStringToBytes(tlvValue)).trim()!=null
			&&!new String(Converser.hexStringToBytes(tlvValue)).trim().equals("")){
				urt.setDriver_license(tlvValue);
			}
		} else if (cmd.equals(ContentParamName.packet_content10)) {
			urt.setTerminal_time(DateUtil.changeTime12ToFormat(DateUtil.utcToLocalDate(Long.parseLong(tlvValue,16)*1000)));
		} else if (cmd.equals(ContentParamName.packet_content12)) {
			urt.setEngine_rotate_speed(ContentParamName.checkLength(AccountUtil.setHexString(tlvValue,Constant.F8),ContentParamName.ENGINE_ROTATE_SPEED_BASE));
		} else if (cmd.equals(ContentParamName.packet_content13)) {
			urt.setFire_up_state(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content14)) {
			urt.setPower_state(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content15)) {
			urt.setBattery_voltage(ContentParamName.checkLength(AccountUtil.setHexString(tlvValue,Constant.F8,100),ContentParamName.BATTERY_VOLTAGE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content17)) {
			urt.setGps_state(ContentParamName.checkLength(tlvValue,ContentParamName.EXT_VOLTAGE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content18)) {
			urt.setExt_voltage(ContentParamName.checkLength(AccountUtil.setHexString(tlvValue,Constant.F8,100),ContentParamName.EXT_VOLTAGE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content1C)) {
			urt.setImg_process(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content1E)) {
			String mileage = AccountUtil.setHexToString(tlvValue,Constant.F8);
			if(!mileage.equals(Constant.F4)){
				//缩小1000倍为km
				urt.setMileage(ContentParamName.checkLength(String.valueOf((double)Converser.hexTolong(mileage)/1000),ContentParamName.MILEAGE_BASE));
			}else{
				urt.setMileage(mileage);
			}
			
		} else if (cmd.equals(ContentParamName.packet_content20)) {
			//缩小10000倍
			if(!tlvValue.equals(Constant.F12)){
				DecimalFormat df = new DecimalFormat("##########.##################"); 
//				long l = Converser.hexTolong(new String(bytevalue));
				urt.setOil_total(ContentParamName.checkLength(df.format(Converser.hexTo2String(tlvValue, 10000)),ContentParamName.OIL_TOTAL_BASE));
			}else{
				urt.setOil_total(Constant.F4);
			}
			
		} else if (cmd.equals(ContentParamName.packet_content21)) {
			urt.setQuad_id_type(tlvValue);
//			CachePattern.getInstance().setRegionAlarmParam(urt);
		} else if (cmd.equals(ContentParamName.packet_content22)) {
			urt.setRoute_info(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content27)) {
			urt.setE_water_temp(AccountUtil.accountWaterTemperature(tlvValue));
		} else if (cmd.equals(ContentParamName.packet_content28)) {
			urt.setOil_pressure(AccountUtil.setHexString(tlvValue,Constant.F3));
		} else if (cmd.equals(ContentParamName.packet_content29)) {
			//缩小100倍为真值
			urt.setOil_instant(AccountUtil.setHexString(tlvValue,Constant.F4,100));
		} else if (cmd.equals(ContentParamName.packet_content2A)) {
			//存储百分比
			urt.setE_torque(AccountUtil.accoutTorquePercent(tlvValue));
		} else if (cmd.equals(ContentParamName.packet_content2B)) {
			urt.setMeg_resp_id(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content2D)) {
			urt.setMeg_id(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content2E)) {
			urt.setMeg_type(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content2F)) {
			urt.setMeg_info(new String(Converser.hexStringToBytes(tlvValue),"GBK"));
		} else if (cmd.equals(ContentParamName.packet_content30)) {
			urt.setMeg_seq(tlvValue);
		} else if(cmd.equals(ContentParamName.packet_content31)){
			if(!tlvValue.equals(Constant.F3)){
				//缩小10倍为真值
				urt.setEcc_app(Converser.hexToString(tlvValue, 10));
			}else {
				urt.setEcc_app(Constant.F4);
			}
			
		} else if(cmd.equals(ContentParamName.packet_content32)){
			if(!tlvValue.equals(Constant.F5)){
				//缩小1000倍为km/h
				urt.setVin_speed(ContentParamName.checkLength(Converser.hexToString(tlvValue, 1000),ContentParamName.VEHICLE_SPEED_BASE));
			}else{
				urt.setVin_speed(Constant.F4);
			}
		} else if(cmd.equals(ContentParamName.packet_content33)){
			if(!tlvValue.equals(Constant.F8)){
				//缩小1000倍为km
				urt.setElevation(ContentParamName.checkLength(Converser.hexToString(tlvValue, 1000),ContentParamName.ELEVATION_BASE));
			}else{
				urt.setElevation(Constant.F4);
			}
		} else if(cmd.equals(ContentParamName.packet_content34)){
			if(!tlvValue.equals(Constant.F8)){
				//缩小1000倍为km
				urt.setPulse_mileage(ContentParamName.checkLength(Converser.hexToString(tlvValue,1000),ContentParamName.PULSE_MILEAGE_BASE));
			}else{
				urt.setPulse_mileage(Constant.F4);
			}
		} else if(cmd.equals("35")){
			urt.setStatus_bit(parseInformation(tlvValue));
		} else if(cmd.equals("36")){
			urt.setOil_mass(ContentParamName.checkLength(Converser.hexToString(tlvValue,10),32));
		} else if(cmd.equals("37")){
			urt.setRoute_lack(tlvValue);
		} else if(cmd.equals("38")){
			urt.setOverspeed_info(tlvValue);
		} else if(cmd.equals("39")){
			urt.setXcstate(parseInformation(tlvValue));
		} else if(cmd.equals("40")){
			urt.setEnginetime(tlvValue);
		} else if(cmd.equals("41")){
			urt.setEngineoiltemperature(ContentParamName.checkLength(AccountUtil.accountTemp(tlvValue, Constant.F3),ContentParamName.OIL_TEMPERATURE_BASE));
		} else if(cmd.equals("42")){
			urt.setEnginecoolanttemperature(ContentParamName.checkLength(AccountUtil.accountTemp(tlvValue, Constant.F3),ContentParamName.COLDER_TEMPERATURE_BASE));
		} else if(cmd.equals("43")){
			urt.setAirinlettemperature(ContentParamName.checkLength(AccountUtil.accountTemp(tlvValue, Constant.F3),ContentParamName.AIR_INFLOW_TPR_BASE));
		} else if(cmd.equals("44")){
			urt.setBarometricpressure(ContentParamName.checkLength(AccountUtil.setHexString(tlvValue, Constant.F3),ContentParamName.AIR_PRESSURE_BASE));
		} else if(cmd.equals("45")){
			urt.setXcononff(parseInformation(tlvValue));
		} else if(cmd.equals("46")){
			urt.setAlarm_seq(tlvValue);
		} else if(cmd.equals("47")){
			if(!tlvValue.equals("FFFFFFFF")){
				urt.setRoute_id(tlvValue);
			}
		} else if(cmd.equals("48")){
			if(!tlvValue.equals("FFFFFFFF")){
				urt.setSite_id(tlvValue);
			}
		} else if(cmd.equals("49")){
			urt.setDrivingper(tlvValue);
		} else if(cmd.equals("50")){
			if(!tlvValue.equals("FFFFFFFF")){
				urt.setCur_tea(tlvValue);
			}
		} else if(cmd.equals("51")){
			if(!tlvValue.equals("FFFFFFFF")){
				urt.setStu_num(tlvValue);
			}
		} else if(cmd.equals("52")){
			urt.setTrip_id(tlvValue);
		} else if(cmd.equals("53")){
			urt.setSpeed_source_setting(tlvValue);
		} else if(cmd.equals("54")){
			urt.setCharacter_oeffocient_status(tlvValue);
		} else if(cmd.equals("55")){
			urt.setDevice_default_list(parseInformation(tlvValue));
		} else{
			log.info("<Up_BlindAreaValue>报文元素错误，不包含元素"+cmd);
//			urt.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//			throw new ParseException("报文元素错误，不包含元素"+cmd);
		}
		return urt;
	}
	
	public static String parseInformation(String tlvValue){
//		String str = new String(bytevalue);
		StringBuffer sb = new StringBuffer();
		byte[] bytevalue = tlvValue.getBytes();
		for(int i=0;i<bytevalue.length;i++){
			switch((char)bytevalue[i]) {
				case '0': sb.append("0000");continue;
				case '1': sb.append("0001");continue;
				case '2': sb.append("0010");continue;
				case '3': sb.append("0011");continue;
				case '4': sb.append("0100");continue;
				case '5': sb.append("0101");continue;
				case '6': sb.append("0110");continue;
				case '7': sb.append("0111");continue;
				case '8': sb.append("1000");continue;
				case '9': sb.append("1001");continue;
				case 'a': sb.append("1010");continue;
				case 'b': sb.append("1011");continue;
				case 'c': sb.append("1100");continue;
				case 'd': sb.append("1101");continue;
				case 'e': sb.append("1110");continue;
				case 'f': sb.append("1111");continue;
				case 'A': sb.append("1010");continue;
				case 'B': sb.append("1011");continue;
				case 'C': sb.append("1100");continue;
				case 'D': sb.append("1101");continue;
				case 'E': sb.append("1110");continue;
				case 'F': sb.append("1111");continue;
				default:
					break;
			}
//			sb.append(InsideMsgUtils.formatSignal(Integer.toBinaryString(Integer.parseInt(str.substring(i,i+1)))));
		}
//		System.out.println(sb.toString());
		return sb.toString();
	}
}
