package com.yutong.clw.utils;

import com.yutong.clw.beans.xc.XcvsseBean;
import com.yutong.clw.config.Constant;
import com.yutong.clw.nio.msg.value.Up_InfoContent;

public class XCUtil {
	public static String xcVssKey(XcvsseBean xsb){
		StringBuffer buffer = new StringBuffer();
		buffer.append(xsb.getStudent_id());
		buffer.append(Constant.shaft);
		buffer.append(xsb.getRoute_id());
		buffer.append(Constant.shaft);
		buffer.append(xsb.getTrip_id());
		buffer.append(Constant.shaft);
		buffer.append(xsb.getVss_state());
		buffer.append(Constant.shaft);
		buffer.append(xsb.getSite_updown());
		return buffer.toString();
	}
	
	public static String getXcVssKey(Up_InfoContent urt) {
		StringBuffer buffer = new StringBuffer();
		buffer.append(urt.getStu_id());
		buffer.append(Constant.shaft);
		buffer.append(urt.getRoute_id());
		buffer.append(Constant.shaft);
		buffer.append(urt.getTrip_id());
		buffer.append(Constant.shaft);
		buffer.append(urt.getVss_flag());
		buffer.append(Constant.shaft);
		buffer.append(urt.getSite_flag());
		return buffer.toString();
	}
}
