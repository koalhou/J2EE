package com.neusoft.clw.vncs.inside.msg.content.value;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.Converser;

public class XcUp_ParamValue {
	private static final Logger log = LoggerFactory
			.getLogger(XcUp_ParamValue.class);

	@SuppressWarnings("unused")
	private static final String NAME = "XcUp_ParamValue";
	
	public static void getUpParamContent(int location, byte[] buf,
			Up_InfoContent urp) throws UnsupportedEncodingException{	
		int bytelen;
		String bytevalue = null;
		String cmd = null;
		int len = ContentParamName.getWhole_len(buf);
		while (location < len) {
			cmd = new String(buf,location,ContentParamName.REGULARLENNEW);
			location += ContentParamName.REGULARLENNEW;
			
			bytelen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
			location += ContentParamName.REGULARLEN;
			if(cmd.equals("00000083")){
				bytevalue = new String(buf,location,bytelen,"GBK");
			}else{
				bytevalue = new String(buf,location,bytelen);
			}
			location += bytelen;
//			if(location>900){
//				System.out.println("---");
//			}
			setUpParamContent(cmd, bytevalue, urp);
		}
	}
	/**
	 * 获得上行参数响应串，并存入Up_ParamContent
	 * @param cmd
	 * @param bytevalue
	 * @param urp
	 * @param type
	 * @throws ParseException
	 */
	/**
	 * @param cmd
	 * @param bytevalue
	 * @param urp
	 * @throws ParseException
	 */
	private static void setUpParamContent(String cmd, String bytevalue,
			Up_InfoContent urp) {
		if(cmd.equals(ContentParamName.packet_contentnewFA)){
			urp.setMsg_id(bytevalue.toLowerCase());
		}else if(cmd.equals(ContentParamName.packet_contentnewFB)){
			urp.setParamnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnewFC)){
			urp.setPacketnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnewFD)){
			urp.setCurrentnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew01)){
			urp.setHeartinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew02)){
			urp.setTcpovertime(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew03)){
			urp.setTcpretransnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew04)){
			urp.setUdpovertime(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew05)){
			urp.setUdpretransnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew06)){
			urp.setSmsovertime(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew07)){
			urp.setSmsretransnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnewFF)){
			urp.setTqc_alarm_psngrs_standard_cnt(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew10)){
			urp.setServerapn(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew11)){
			urp.setServerwirelessuser(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew12)){
			urp.setServerwirelesspass(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew13)){
			urp.setServerip(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew14)){
			urp.setBackapn(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew15)){
			urp.setBackuser(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew16)){
			urp.setBackpass(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew17)){
			urp.setBackip(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew18)){
			urp.setTcpport(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew19)){
			urp.setUdpport(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew20)){
			urp.setPositionstra(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew21)){
			urp.setPositionscheme(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew22)){
			urp.setNotlogintimeinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew27)){
			urp.setSleeptimeinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew28)){
			urp.setSostimeinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew29)){
			urp.setDefaulttimeinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew2C)){
			urp.setDefaultdistanceinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew2D)){
			urp.setNotlogindistanceinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew2E)){
			urp.setSleepdistanceinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew2F)){
			urp.setSosdistanceinterval(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew30)){
			urp.setInflectionpoint(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew31)){
			urp.setElec_fence_r(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew40)){
			urp.setPlatphonenum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew41)){
			urp.setResetnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew42)){
			urp.setFactorynum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew43)){
			urp.setSmsnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew44)){
			urp.setRecsmsnum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew45)){
			urp.setTerminalstrategy(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew46)){
			urp.setLongesttalk(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew47)){
			urp.setCurrentmonthtalk(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew48)){
			urp.setListennum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew49)){
			urp.setPlatmessagenum(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew50)){
			urp.setAlarmword(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew51)){
			urp.setAlarmtext(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew52)){
			urp.setAlarmshootingswitch(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew53)){
			urp.setAlarmshootingsign(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew54)){
			urp.setKeysign(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew55)){
			urp.setMaxspeed(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew56)){
			urp.setOverspeedtime(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew57)){
			urp.setContinuousdriving(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew58)){
			urp.setCurrenttotaldriving(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew59)){
			urp.setMinresttime(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew5A)){
			urp.setMaxstoptime(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew5B)){
			urp.setOverspeed_warning_diff(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew5C)){
			urp.setFatigue_warning_diff(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew5D)){
			urp.setCharacteristics_ratio(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew5E)){
			urp.setWheel_each_turn_pulse_number(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew5F)){
			urp.setTank_capacity(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew60)){
			urp.setAdditional_information_setup(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew61)){
			urp.setDoor_photos_control(Converser.hexTo2BCD(bytevalue));
		}else if(cmd.equals(ContentParamName.packet_contentnew62)){
			urp.setTerminal_sense_config(Converser.hexTo2BCD(bytevalue));
		}else if(cmd.equals(ContentParamName.packet_contentnew63)){
			urp.setBlind_area_mode(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew64)){
			urp.setTiming_photos_control(Converser.hexTo2BCD(bytevalue));
		}else if(cmd.equals(ContentParamName.packet_contentnew65)){
			urp.setSpace_photos_control(Converser.hexTo2BCD(bytevalue));
		}else if(cmd.equals(ContentParamName.packet_contentnew66)){
			urp.setSpeed_source(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew70)){
			urp.setIvquality(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew71)){
			urp.setBrightness(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew72)){
			urp.setContrast(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew73)){
			urp.setSaturation(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew74)){
			urp.setChroma(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew75)){
			urp.setResolution(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew80)){
			urp.setMilestones(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew81)){
			urp.setProvinceid(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew82)){
			urp.setCityid(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew83)){
			urp.setRegno(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew84)){
			urp.setRegcolor(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnew85)){
			urp.setLicense_type(bytevalue);
		}else if(cmd.equals(ContentParamName.packet_contentnewF0)){
			urp.setSpeech_output_channel_control(Converser.hexTo2BCD(bytevalue));
		}
	}
}
