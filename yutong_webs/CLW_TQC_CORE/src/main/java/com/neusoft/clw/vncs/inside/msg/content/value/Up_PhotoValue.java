package com.neusoft.clw.vncs.inside.msg.content.value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.AccountUtil;

public class Up_PhotoValue {
	private static final Logger log = LoggerFactory.getLogger(Up_PhotoValue.class);

	public static void getUpPhotoContent(int location, byte[] buf,
			Up_InfoContent urp) { 
		int bytelen = 0;
		String bytevalue = null;
		String cmd = null;
		int len = ContentParamName.getWhole_len(buf);
		while (location < len) {
			
			//00000229 0004 1001 
		    //类型  长度   值
			//01  0C 1311220745 
			//02  02 011 
			//03  09 024091BA8
			//04  09 07508093E
			//05  05 08660
			//06  04 68B0
			//07  20 6ce6485e939a4b9982a90e6473a91688
			//08  0C 131122074502
			//09  50 /opt/m2mfile/ftp/xcphoto/131122/LZYTATE62D1052890/131122074502_1080.JPEG        
			//0A  01 0
			//0B  02 01
			//0C  01 0 
			//0D  01 0
			
			//获得命令行
			cmd = new String(buf,location,ContentParamName.REGULARLEN); 
			
			//跳过类型 标识
			location += ContentParamName.REGULARLEN;
			//获得该值的长度
			bytelen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN), 16);
			
			//跳过长度 标识位
			location += ContentParamName.REGULARLEN;
			//获得具体值
			bytevalue = new String(buf,location,bytelen);
			
		    //跳过本次总长度
			location += bytelen;
			//将值赋入对象中
			setUpPhotoContent(cmd, bytevalue, urp);				
		}
	}

	private static void setUpPhotoContent(String cmd, String bytevalue,
			Up_InfoContent urp){
		if (cmd.equals(ContentParamName.packet_content01)) {
			urp.setUtc_time(bytevalue); //utc时间
		} else if (cmd.equals(ContentParamName.packet_content02)) {
			urp.setGps_valid(bytevalue); //gps是否有效
		} else if (cmd.equals(ContentParamName.packet_content03)) {
			urp.setLatitude(AccountUtil.accountLongLat(bytevalue)); //经度
		} else if (cmd.equals(ContentParamName.packet_content04)) {
			urp.setLongitude(AccountUtil.accountLongLat(bytevalue));//纬度
		} else if (cmd.equals(ContentParamName.packet_content05)) {
			urp.setGps_speeding(AccountUtil.setHexString(bytevalue.trim(), Constant.F5,1000)); //车速
		} else if (cmd.equals(ContentParamName.packet_content06)) {
			urp.setDirection(AccountUtil.setHexString(bytevalue.trim(),Constant.F4,100)); //方向
		} else if (cmd.equals(ContentParamName.packet_content07)) {
			urp.setMsg_id(bytevalue); //消息ID
		} else if (cmd.equals(ContentParamName.packet_content08)) {
			urp.setPhoto_time(bytevalue);  //拍照时间
		} else if (cmd.equals(ContentParamName.packet_content09)) {
			urp.setPhoto_file(bytevalue);  //拍照文件名
		} else if (cmd.equals(ContentParamName.packet_content0A)) {
			urp.setPhoto_event(bytevalue.trim()); //拍照事件
		} else if (cmd.equals(ContentParamName.packet_content0B)) {
			urp.setChannel_number(bytevalue.trim()); //拍照通道
		} else if (cmd.equals(ContentParamName.packet_content0C)) {
			urp.setMedia_type(bytevalue.trim());
		} else if (cmd.equals(ContentParamName.packet_content0D)) {
			urp.setMedia_code(bytevalue.trim());
		} else {
			log.info("<Up_PhotoValue>报文元素错误，不包含元素" + cmd);
//			urp.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
//			throw new ParseException("报文元素错误，不包含元素" + cmd);
		}
	}
}
