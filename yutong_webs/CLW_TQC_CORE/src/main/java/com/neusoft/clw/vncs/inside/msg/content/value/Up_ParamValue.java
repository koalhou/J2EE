package com.neusoft.clw.vncs.inside.msg.content.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.util.AccountUtil;
import com.neusoft.clw.vncs.util.Converser;

public class Up_ParamValue {
	private static final Logger log = LoggerFactory
			.getLogger(Up_ParamValue.class);

	private static final String NAME = "Up_ParamValue";
	
	public static void getUpParamContent(int location, byte[] buf,
			Up_InfoContent urp) {
		// 截取packet_content中0x01的参数名(msg_id)
		// 截取packet_content中0x01的长度
		location += ContentParamName.REGULARLEN;
		int bytelen01 = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
		location += ContentParamName.REGULARLEN;
		// 截取packet_content中0x01的值
		String bytevalue01 = new String(buf,location,bytelen01);
		location += bytelen01;
		
		// 将0x01的值(即msg_id)存入Up_ParamInfo
		urp.setMsg_id(bytevalue01);
		// 截取packet_content中0x02的长度及值(网络连接组参数表(01)、实时数据通讯组参数表(02)、报警组参数表(03))
		location += ContentParamName.REGULARLEN;
		int bytelen02 = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
		location += ContentParamName.REGULARLEN;
		String type = new String(buf,location,bytelen02);
		location += bytelen02;
		// 根据不同的上报参数类型，进入响应的处理流程
		urp.setType(type);		
		int bytelen;
		String bytevalue = null;
		String cmd = null;
		int len = ContentParamName.getWhole_len(buf);
		while (location < len) {
			cmd = new String(buf,location,ContentParamName.REGULARLEN);
			location += ContentParamName.REGULARLEN;
			
			bytelen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
			location += ContentParamName.REGULARLEN;
			bytevalue = new String(buf,location,bytelen);
			location += bytelen;
			setUpParamContent(cmd, bytevalue, urp,type);
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
	private static void setUpParamContent(String cmd, String bytevalue,
			Up_InfoContent urp,String type) {
		if (type.equals("1")) {//网络连接组参数表(01)
			if(cmd.equals(ContentParamName.packet_content03)){
				urp.setMsg_center(new String(Converser.hexStringToBytes(bytevalue)).trim());
			}else if(cmd.equals(ContentParamName.packet_content04)){
				urp.setApn(new String(Converser.hexStringToBytes(bytevalue)).trim());
			}else if(cmd.equals(ContentParamName.packet_content05)){
				urp.setServer_ip(AccountUtil.getServer_Ip(bytevalue));
			}else if(cmd.equals(ContentParamName.packet_content06)){
				urp.setServer_port(Converser.hexToString(bytevalue).trim());
			}else if(cmd.equals(ContentParamName.packet_content07)){
				urp.setReceive_time(Converser.hexToString(bytevalue).trim());
			}else{
				log.info(NAME+"报文元素错误，不包含元素"+cmd);
//				urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//				throw new ParseException("报文元素错误，不包含元素"+cmd);
			}
		} else if (type.equals("2")) {//实时数据通讯组参数表(02)
			if(cmd.equals(ContentParamName.packet_content03)){
				urp.setTime_answers(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content04)){
				urp.setSpacing_answers(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content05)){
				urp.setKeepalive_time(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content06)){
				urp.setKeepalive_overtime(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content07)){
				urp.setStalled_time_answers(bytevalue);
			}else{
				log.info(NAME+"报文元素错误，不包含元素"+cmd);
//				urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//				throw new ParseException("报文元素错误，不包含元素"+cmd);
			}
		} else if (type.equals("3")) {//报警组参数表(03)
			if(cmd.equals(ContentParamName.packet_content03)){
				urp.setOverspeed(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content04)){
				urp.setOverspeed_diff(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content05)){
				urp.setOverspeed_keep(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content06)){
				urp.setDriving_fatigue(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content07)){
				urp.setDriving_fatigue_diff(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content08)){
				urp.setDriving_fatigue_rest(bytevalue);
			}else{
				log.info(NAME+"报文元素错误，不包含元素"+cmd);
//				urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//				throw new ParseException("报文元素错误，不包含元素"+cmd);
			}
		} else if (type.equals("4")) {//一般车辆信息组参数表(04)
			if(cmd.equals(ContentParamName.packet_content03)){
				urp.setIndex_property(Converser.hexToString(bytevalue));
			}else if(cmd.equals(ContentParamName.packet_content04)){
				urp.setPulse_per_second(Converser.hexToString(bytevalue));
			}else if(cmd.equals(ContentParamName.packet_content05)){
				urp.setEngine_gear(Converser.hexToString(bytevalue));
			}else if(cmd.equals(ContentParamName.packet_content06)){
				urp.setVehicle_ln(new String(Converser.hexStringToBytes(bytevalue)).trim());
			}else if(cmd.equals(ContentParamName.packet_content07)){
				urp.setVehicle_no(new String(Converser.hexStringToBytes(bytevalue)).trim());
			}else if(cmd.equals(ContentParamName.packet_content08)){
				urp.setVehicle_sort(new String(Converser.hexStringToBytes(bytevalue)).trim());
			}else if(cmd.equals(ContentParamName.packet_content09)){
				urp.setVehicle_vin(new String(Converser.hexStringToBytes(bytevalue)).trim());
			}else if(cmd.equals(ContentParamName.packet_content10)){
				urp.setSleep_time(bytevalue);
			}else if(cmd.equals(ContentParamName.packet_content11)){
				if(new String(Converser.hexStringToBytes(bytevalue)).trim()!=null
					&&!new String(Converser.hexStringToBytes(bytevalue)).trim().equals("")){
					urp.setOut1tiem(DateUtil.changeTime12ToFormat(DateUtil.utcToLocalDate(Long.parseLong(bytevalue,16)*1000)));
				}
			}else{
				log.info(NAME+"报文元素错误，不包含元素"+cmd);
//				urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//				throw new ParseException("报文元素错误，不包含元素"+cmd);
			}
		} else {
			log.info(NAME+"报文元素错误，不包含元素"+cmd);
//			urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//			throw new ParseException("报文元素错误，不包含元素"+cmd);
		}
	}
}
