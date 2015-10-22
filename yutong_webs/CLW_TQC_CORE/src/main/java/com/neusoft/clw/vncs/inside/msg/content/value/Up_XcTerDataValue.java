package com.neusoft.clw.vncs.inside.msg.content.value;

import java.io.UnsupportedEncodingException;

import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;

public class Up_XcTerDataValue {
		
	public static void getUp_XcTerDataValue(int location, byte[] buf,Up_InfoContent urt) throws UnsupportedEncodingException {
		
		int tlvLen = 0;
		String cmd = null;
		int msgLength = Integer.parseInt(urt.getMsgLength());
		while (location < msgLength) {
			cmd = new String(buf,location,ContentParamName.REGULARLEN);
			location += ContentParamName.REGULARLEN;
			tlvLen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
			location += ContentParamName.REGULARLEN;
			String tlvValue = null;
			if(cmd.equals("0A")){
				tlvValue = new String(buf,location,tlvLen,"GBK").trim();
			}else{
				tlvValue = new String(buf,location,tlvLen).trim();
			}
			location+=tlvLen;
			setUp_XcTerDataValue(cmd, tlvValue,urt);
		}
	}

	public static Up_InfoContent setUp_XcTerDataValue(String cmd,
			String tlvValue,Up_InfoContent urt){
		if (cmd.equals(ContentParamName.packet_content01)) {
			urt.setXc_hardware_ver(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content02)) {
			urt.setXc_firmware_ver(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content03)) {
			urt.setXc_sim_iccid(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content04)) {
			urt.setXc_screen_hardware_ver(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content05)) {
			urt.setXc_screen_firmware_ver(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content06)) {
			urt.setXc_video_hardware_ver(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content07)) {
			urt.setXc_video_firmware_ver(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content08)) {
			urt.setXc_rfcard_hardware_ver(tlvValue);
		} else if (cmd.equals(ContentParamName.packet_content09)) {
			urt.setXc_rfcard_firmware_ver(tlvValue);
		} else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0a)){
			urt.setHardware_vehicle_ln(tlvValue);
		} else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0b)){
			urt.setHardware_veh_pai_color(tlvValue);
		} else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0c)){
			urt.setHardware_vehicle_vin(tlvValue);
		} else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0d)){
			urt.setHardware_terminal_id(tlvValue);
		}
		return urt;
	}
	
	public static String parseInformation(String tlvValue){
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
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
	}
}
