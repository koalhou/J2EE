package com.neusoft.SchoolBus.vncs.service.util;

import com.neusoft.SchoolBus.vncs.domain.XcvsseBean;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;

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
