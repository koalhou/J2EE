package com.neusoft.clw.vncs.inside.msg.content.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.AccountUtil;

public class Up_RunRecordValue {
	private static final Logger log = LoggerFactory
			.getLogger(Up_RunRecordValue.class);

	public static void getUpOnRecordContent(int location, byte[] buf,
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
			setUpOnRecordContent(cmd, bytevalue, urp);
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
	/**
	 * @param cmd
	 * @param bytevalue
	 * @param urp
	 * @throws ParseException
	 */
	private static void setUpOnRecordContent(String cmd, String bytevalue,
			Up_InfoContent urp){
		if (cmd.equals(ContentParamName.packet_content01)) {
			urp.setOn_date(bytevalue);
		} else if (cmd.equals(ContentParamName.packet_content02)) {
			urp.setOff_date(bytevalue);
		} else if (cmd.equals(ContentParamName.packet_content03)) {
			urp.setOil(AccountUtil.setHexDoubleString(bytevalue, Constant.F12, 10000));
		} else if (cmd.equals(ContentParamName.packet_content04)) {
			urp.setTotalmileage(AccountUtil.setHexDoubleString(bytevalue, Constant.F12, 10000));
		} else if (cmd.equals(ContentParamName.packet_content05)) {
			urp.setSpd_oil(AccountUtil.setHexDoubleString(bytevalue, Constant.F12, 10000));
		} else {
			log.info("<Up_RunRecordValue>报文元素错误，不包含元素" + cmd);
		}
	}
}
