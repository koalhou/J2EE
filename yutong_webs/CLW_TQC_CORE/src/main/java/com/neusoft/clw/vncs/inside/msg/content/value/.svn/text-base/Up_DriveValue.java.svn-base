package com.neusoft.clw.vncs.inside.msg.content.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.AccountUtil;
import com.neusoft.clw.vncs.util.Converser;

public class Up_DriveValue {
	private static final Logger log = LoggerFactory
			.getLogger(Up_DriveValue.class);

	public static void getUpDriveContent(int location, byte[] buf,
			Up_InfoContent urp) {

		int bytelen = 0;
		String bytevalue = null;
		String cmd = null;
		int len = ContentParamName.getWhole_len(buf);
		while (location < len) {
			cmd = new String(buf,location,ContentParamName.REGULARLEN);
			location += ContentParamName.REGULARLEN;
			bytelen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN), 16);
			location += ContentParamName.REGULARLEN;
			bytevalue = new String(buf,location,bytelen);
			location += bytelen;
			setUpDriveContent(cmd, bytevalue, urp);
		}
	}

	/**
	 * @param cmd
	 * @param bytevalue
	 * @param urp
	 * @throws ParseException
	 */
	/**
	 * @param cmd
	 * @param bytevalue
	 * @param urp
	 * @throws ParseException
	 */
	private static void setUpDriveContent(String cmd, String bytevalue,
			Up_InfoContent urp) {
		if (cmd.equals(ContentParamName.packet_content01)) {
			urp.setTempEventsId(Integer.valueOf(bytevalue, 16));
		} else if (cmd.equals(ContentParamName.packet_content02)) {
			urp.setStartalarm(Up_RealTimeValue.parseInformation(bytevalue));
		} else if (cmd.equals(ContentParamName.packet_content03)) {
			urp.setStartonoff(Up_RealTimeValue.parseInformation(bytevalue));
		} else if (cmd.equals(ContentParamName.packet_content04)) {
			urp.setStartlatitude(ContentParamName.checkLength(AccountUtil.accountLongLat(bytevalue),ContentParamName.LATITUDE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content05)) {
			urp.setStartlongitude(ContentParamName.checkLength(AccountUtil.accountLongLat(bytevalue),ContentParamName.LONGITUDE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content06)) {
			if(!bytevalue.equals(Constant.F8)){
				//缩小1000倍为km
				urp.setStartelevation(ContentParamName.checkLength(Converser.hexToString(bytevalue, 1000),ContentParamName.ELEVATION_BASE));
			}else{
				urp.setStartelevation(Constant.F4);
			}
		} else if (cmd.equals(ContentParamName.packet_content07)) {
			if(!bytevalue.equals(Constant.F5)){
				//缩小1000倍为km/h
				urp.setStartspeed(ContentParamName.checkLength(Converser.hexToString(bytevalue, 1000),ContentParamName.VEHICLE_SPEED_BASE));
			}else{
				urp.setStartspeed(Constant.F4);
			}
		} else if (cmd.equals(ContentParamName.packet_content08)) {
			urp.setStartdirection(AccountUtil.setHexString(bytevalue,Constant.F4,100));
		} else if (cmd.equals(ContentParamName.packet_content09)) {
			urp.setStarttime(bytevalue);
		} else if (cmd.equals(ContentParamName.packet_content0A)) {
			urp.setEndalarm(Up_RealTimeValue.parseInformation(bytevalue));
		} else if (cmd.equals(ContentParamName.packet_content0B)) {
			urp.setEndonoff(Up_RealTimeValue.parseInformation(bytevalue));
		} else if (cmd.equals(ContentParamName.packet_content0C)) {
			urp.setEndlatitude(ContentParamName.checkLength(AccountUtil.accountLongLat(bytevalue),ContentParamName.LATITUDE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content0D)) {
			urp.setEndlongitude(ContentParamName.checkLength(AccountUtil.accountLongLat(bytevalue),ContentParamName.LATITUDE_BASE));
		} else if (cmd.equals(ContentParamName.packet_content0E)) {
			if(!bytevalue.equals(Constant.F8)){
				//缩小1000倍为km
				urp.setEndelevation(ContentParamName.checkLength(Converser.hexToString(bytevalue, 1000),ContentParamName.ELEVATION_BASE));
			}else{
				urp.setEndelevation(Constant.F4);
			}
		} else if (cmd.equals(ContentParamName.packet_content0F)) {
			if(!bytevalue.equals(Constant.F5)){
				//缩小1000倍为km/h
				urp.setEndspeed(ContentParamName.checkLength(Converser.hexToString(bytevalue, 1000),ContentParamName.VEHICLE_SPEED_BASE));
			}else{
				urp.setEndspeed(Constant.F4);
			}
			
		} else if (cmd.equals(ContentParamName.packet_content11)) {
			urp.setEnddirection(AccountUtil.setHexString(bytevalue,Constant.F4,100));
		} else if (cmd.equals(ContentParamName.packet_content12)) {
			urp.setEndtime(bytevalue);
		} else if (cmd.equals(ContentParamName.packet_content13)) {
			urp.setDriver_id(bytevalue);
		} else if (cmd.equals(ContentParamName.packet_content14)) {
			urp.setRoute_id(bytevalue);
		} else {
			log.info("<Up_DriveValue>报文元素错误，不包含元素" + cmd);
//			urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//			throw new ParseException("报文元素错误，不包含元素" + cmd);
		}
	}
}
