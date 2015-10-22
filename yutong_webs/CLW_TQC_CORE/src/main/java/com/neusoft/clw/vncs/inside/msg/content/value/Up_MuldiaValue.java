package com.neusoft.clw.vncs.inside.msg.content.value;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;

public class Up_MuldiaValue {
	
	private static final Logger log = LoggerFactory.getLogger(Up_MuldiaValue.class);
	
	/**
	 * 1003、1004通用
	 * @param location
	 * @param buf
	 * @param urp
	 * @throws UnsupportedEncodingException
	 * @throws ParseException
	 */
	public static void getUp_MuldiaValue(int location, byte[] buf,
			Up_InfoContent urp) {
		int bytelen = 0;
		String bytevalue = null;
		String cmd = null;
		int len = ContentParamName.getWhole_len(buf);
		while (location < len) {
			cmd = new String(buf,location,ContentParamName.REGULARLEN);
			location+=ContentParamName.REGULARLEN;
			bytelen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
			location += ContentParamName.REGULARLEN;
			bytevalue = new String(buf,location,bytelen);
			location += bytelen;
			setUpContent(cmd, bytevalue, urp);
		}
	}

	private static void setUpContent(String cmd, String bytevalue,
			Up_InfoContent urp) {
		if (cmd.equals(ContentParamName.packet_content01)) {
			urp.setMsg_id(bytevalue);
		} else if (cmd.equals(ContentParamName.packet_content02)) {
			urp.setMuldiatype(bytevalue);
		} else if(cmd.equals(ContentParamName.packet_content03)){
			urp.setMuldiaCode(bytevalue);
		} else if(cmd.equals(ContentParamName.packet_content04)){
			urp.setMuldiaevent(bytevalue);
		} else if(cmd.equals(ContentParamName.packet_content05)){
			urp.setMuldiachannel(bytevalue);
		} else {
			log.info("<Up_MuldiaValue>报文元素错误，不包含元素"+cmd);
//			urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//			throw new ParseException("报文元素错误，不包含元素"+cmd);
		}
	}
}
