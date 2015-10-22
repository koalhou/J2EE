package com.neusoft.clw.vncs.inside.msg.content.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;

public class Up_CerFailValue {
	
	private static final Logger log = LoggerFactory.getLogger(Up_CerFailValue.class);
	
	public static void getUpCerFailContent(int location, byte[] buf,
			Up_InfoContent urp) {
		int bytelen = 0;
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
			setUpCerFailContent(cmd, bytevalue, urp);
		}
	}

	private static void setUpCerFailContent(String cmd, String bytevalue,
			Up_InfoContent urp){
		if (cmd.equals(ContentParamName.packet_content01)) {
			urp.setVehicle_vin(ContentParamName.checkLength(bytevalue.trim(),ContentParamName.up_fail_vehiclevinlen));
		} else if (cmd.equals(ContentParamName.packet_content02)) {
			urp.setSim(ContentParamName.checkLength(bytevalue.trim(),ContentParamName.up_fail_simlen));
		} else if(cmd.equals(ContentParamName.packet_content03)){
			urp.setTerminal_id(ContentParamName.checkLength(bytevalue.trim(),ContentParamName.up_fail_terminalidlen));
		}else{
			log.info("<Up_CerFailValue>报文元素错误，不包含元素"+cmd);
//			urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//			throw new ParseException("报文元素错误，不包含元素"+cmd);
		}
	}
	
}
