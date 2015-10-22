package com.neusoft.clw.vncs.inside.msg.content.value;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.info.exception.DBErrorException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.AccountUtil;
import com.neusoft.clw.vncs.util.Converser;
import com.neusoft.tqcpt.service.SearchGisAreaByCode;

public class Up_EngLockValue {
	private static final Logger log = LoggerFactory.getLogger(Up_EngLockValue.class);
  
	/**
	 * 1F06
	 * 
	 * @param M2M响应
	 * @return  
	 */
	public static void setUpM2MLockData(int idxLock,byte[] buf,Up_InfoContent uhc) throws ParseException,UnsupportedEncodingException {
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap.getInstance().getBean("sendCommandDAO");
		uhc=Up_EngLockValue.getM2MInfo(uhc,buf,idxLock,"远程锁车");
		
	    try {
			DBBuffer.getInstance().add(sendCommandDAO.updateM2MLockCommand(uhc));
			System.out.println("远程锁车m2m应答"+new String(buf));
			log.info("远程锁车m2m应答"+new String(buf));
		} catch (DBErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
		/*if("01".equals(oilboxState)){
			//发送短信
			callSmsInterface(uhc);			
		}*/	
	}
	/**
	 * 1F17
	 * 
	 * @param 终端响应
	 * @return  
	 */
	public static void setUpEngLockData(int idxLock,byte[] buf,Up_InfoContent uhc) throws ParseException,UnsupportedEncodingException {
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap.getInstance().getBean("sendCommandDAO");
		
		uhc=Up_EngLockValue.getEngInfo(uhc,buf,idxLock,"远程锁车");
		
	    try {
	    	DBBuffer.getInstance().add(sendCommandDAO.updateEngLockCommand(uhc));
	    	System.out.println("远程锁车终端应答"+new String(buf));
			log.info("远程锁车终端应答"+new String(buf));
		} catch (DBErrorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	    
		/*if("01".equals(oilboxState)){
			//发送短信
			callSmsInterface(uhc);			
		}*/
	}
	public static Up_InfoContent getM2MInfo(Up_InfoContent uhc,byte[] buf,int idxLock,String moduleName) throws UnsupportedEncodingException{
		//01 20 BE8DFE78956C44bdADE57924F2DA208C 02 04 0001 03 0201
		byte[] msg_id_b = new byte[36];//消息id
		System.arraycopy(buf, idxLock, msg_id_b, 0, 36);
		idxLock += 36;
		String msg_id = new String(msg_id_b);
		uhc.setMsg_id(msg_id.substring(msg_id.length()-32,msg_id.length()));
		
		
		byte[] send_type_b = new byte[8];//锁车应答类型
		System.arraycopy(buf, idxLock, send_type_b, 0, 8);
		idxLock += 8;
		String send_type = new String(send_type_b);
		uhc.setSend_type(send_type.substring(send_type.length()-4,send_type.length()));

		byte[] m2m_result_b = new byte[6];//指令执行结果
		System.arraycopy(buf, idxLock, m2m_result_b, 0, 6);
		idxLock += 6;
		String m2m_result = new String(m2m_result_b);
		uhc.setM2m_result(m2m_result.substring(m2m_result.length()-2,m2m_result.length()));
		
		if(m2m_result.equals("00")&&send_type.equals("02")) {
			log.info("m2m远程锁车 查询锁车指令 下发成功！");
			byte[] engstate_view_b = new byte[52];//解锁码
			System.arraycopy(buf, idxLock, engstate_view_b, 0, 52);
			idxLock += 52;
			String engstate_view = new String(engstate_view_b);
			uhc.setEngstate_view(engstate_view);
		} else {
			uhc.setEngstate_view("");
		}
		
		return uhc;
	}
	
	public static Up_InfoContent getEngInfo(Up_InfoContent uhc,byte[] buf,int idxLock,String moduleName){
		byte[] eng_result_b = new byte[6];//终端结果
		System.arraycopy(buf, idxLock, eng_result_b, 0, 6);
		idxLock += 6;
		String eng_result = new String(eng_result_b);
		uhc.setEng_result(eng_result.substring(eng_result.length()-2,eng_result.length()));

		byte[] eng_res_info_l_b = new byte[8];//终端结果信息
		System.arraycopy(buf, idxLock, eng_res_info_l_b, 0, 8);
		idxLock += 8;
		String eng_res_info_l = new String(eng_res_info_l_b);
		int i = Converser.hexToInt(eng_res_info_l.substring(eng_res_info_l.length()-4,eng_res_info_l.length()));
		
		byte[] eng_res_info_b = new byte[i];
		System.arraycopy(buf, idxLock, eng_res_info_b, 0, i);
		idxLock += i;
		String eng_res_info = new String(eng_res_info_b);
//		uhc.setEng_res_info(eng_res_info);
		
		HashMap<String,String> hashMap = new HashMap<String,String>();
		
		for(int j=0;j<50;j++) {
			String type1 = eng_res_info.substring(0, 2);
			if(type1.equals("01")) {
				//长度12	时间
				String time = eng_res_info.substring(0, 16);
				hashMap.put("utctime",time.substring(4, time.length()));
				eng_res_info = eng_res_info.substring(16, eng_res_info.length());
			} else if(type1.equals("02")) {
				//长度1	gps是否有效	有效：1，无效：0
				String gpsValid = eng_res_info.substring(0, 5);
				hashMap.put("gpsValid",gpsValid.substring(4, gpsValid.length()));
				eng_res_info = eng_res_info.substring(5, eng_res_info.length());
			} else if(type1.equals("03")) {
				//长度9	latitude纬度
				String latitude = eng_res_info.substring(0, 13);
				hashMap.put("latitude",latitude.substring(4, latitude.length()));
				eng_res_info = eng_res_info.substring(13, eng_res_info.length());
			} else if(type1.equals("04")) {
				//长度9	longitude经度
				String longitude = eng_res_info.substring(0, 13);
				hashMap.put("longitude",longitude.substring(4, longitude.length()));
				eng_res_info = eng_res_info.substring(13, eng_res_info.length());
			} else if(type1.equals("05")) {
				//长度5	GPS速度 缩小1000倍
				String gpsSpd = eng_res_info.substring(0, 9);
				hashMap.put("gpsSpd",gpsSpd.substring(4, gpsSpd.length()));
				eng_res_info = eng_res_info.substring(9, eng_res_info.length());
			} else if(type1.equals("06")) {
				//长度4	GPS方向，缩小100倍
				String gpsDir = eng_res_info.substring(0, 8);
				hashMap.put("gpsDir",gpsDir.substring(4, gpsDir.length()));
				eng_res_info = eng_res_info.substring(8, eng_res_info.length());
			} else if(type1.equals("07")) {
				//长度2	车速(km/h)  16进制 VSS速度
				String vssSpd = eng_res_info.substring(0, 6);
				hashMap.put("vssSpd",vssSpd.substring(4, vssSpd.length()));
				eng_res_info = eng_res_info.substring(6, eng_res_info.length());
			} else if(type1.equals("09")) {
				//长度16	报警状态（低位在右，从右到左读取）16进制
				String alarmState = eng_res_info.substring(0, 20);
				hashMap.put("alarmState",alarmState.substring(4, alarmState.length()));
				eng_res_info = eng_res_info.substring(20, eng_res_info.length());
			} else if(type1.equals("10")) {
				//长度8	发送时间信息(Unix时间) 16进制
				String unixTime = eng_res_info.substring(0, 12);
				hashMap.put("unixTime",unixTime.substring(4, unixTime.length()));
				eng_res_info = eng_res_info.substring(12, eng_res_info.length());
			} else if(type1.equals("13")) {
				//长度1	点火状态(1=点火, 0=熄火)
				String fireON = eng_res_info.substring(0, 5);
				hashMap.put("fireON",fireON.substring(4, fireON.length()));
				eng_res_info = eng_res_info.substring(5, eng_res_info.length());
			} else if(type1.equals("14")) {
				//长度1	供电状态(0:外部电源 1:电池)
				String powerON = eng_res_info.substring(0, 5);
				hashMap.put("powerON",powerON.substring(4, powerON.length()));
				eng_res_info = eng_res_info.substring(5, eng_res_info.length());
			} else if(type1.equals("35")) {
				//长度8	状态位16进制 （从右到左）
				String state = eng_res_info.substring(0, 12);
				hashMap.put("state",state.substring(4, state.length()));
				eng_res_info = eng_res_info.substring(12, eng_res_info.length());
			}
			
			if(eng_res_info.length()<4)
				break;
		}
		
		if(hashMap.get("latitude")!=null&&hashMap.get("latitude")!=null) {
			String lon = ContentParamName.checkLength(AccountUtil.accountLongLat(hashMap.get("longitude")),ContentParamName.LONGITUDE_BASE);
			String lat = ContentParamName.checkLength(AccountUtil.accountLongLat(hashMap.get("latitude")),ContentParamName.LATITUDE_BASE);
			if(lon.equals("FFFF")||lat.equals("FFFF")) {
				hashMap.put("zonename","无法定位");
			} else {
				String zoneName = SearchGisAreaByCode.getZoneNameByPosition(lon, lat);
				hashMap.put("zonename",zoneName);
			}
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			String mapJson = mapper.writeValueAsString(hashMap);
			uhc.setEng_res_info(mapJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return uhc;
	}
	
	//测试
	public static void  main(String[] args){
		String eng_res_info = "3508C000000009100000000000000000020101301014010010C140808104900100853E43A9C";

		for(int i=0;i<50;i++) {
			String type1 = eng_res_info.substring(0, 2);
			if(type1.equals("01")) {
				//长度12	时间
				String time = eng_res_info.substring(0, 16);
				System.out.println("时间："+time);
				eng_res_info = eng_res_info.substring(16, eng_res_info.length());
			} else if(type1.equals("02")) {
				//长度1	gps是否有效	有效：1，无效：0
				String gpsValid = eng_res_info.substring(0, 5);
				System.out.println("时间："+gpsValid);
				eng_res_info = eng_res_info.substring(5, eng_res_info.length());
			} else if(type1.equals("03")) {
				//长度9	latitude纬度
				String latitude = eng_res_info.substring(0, 13);
				System.out.println("纬度："+latitude);
				eng_res_info = eng_res_info.substring(13, eng_res_info.length());
			} else if(type1.equals("04")) {
				//长度9	longitude经度
				String longitude = eng_res_info.substring(0, 13);
				System.out.println("经度："+longitude);
				eng_res_info = eng_res_info.substring(13, eng_res_info.length());
			} else if(type1.equals("05")) {
				//长度5	GPS速度 缩小1000倍
				String gpsSpd = eng_res_info.substring(0, 9);
				System.out.println("GPS速度："+gpsSpd);
				eng_res_info = eng_res_info.substring(9, eng_res_info.length());
			} else if(type1.equals("06")) {
				//长度4	GPS方向，缩小100倍
				String gpsSqr = eng_res_info.substring(0, 8);
				System.out.println("GPS方向："+gpsSqr);
				eng_res_info = eng_res_info.substring(8, eng_res_info.length());
			} else if(type1.equals("07")) {
				//长度2	车速(km/h)  16进制 VSS速度
				String vssSpd = eng_res_info.substring(0, 6);
				System.out.println("车速："+vssSpd);
				eng_res_info = eng_res_info.substring(6, eng_res_info.length());
			} else if(type1.equals("09")) {
				//长度16	报警状态（低位在右，从右到左读取）16进制
				String alarmState = eng_res_info.substring(0, 20);
				System.out.println("报警状态："+alarmState);
				eng_res_info = eng_res_info.substring(20, eng_res_info.length());
			} else if(type1.equals("10")) {
				//长度8	发送时间信息(Unix时间) 16进制
				String UnixTime = eng_res_info.substring(0, 12);
				System.out.println("发送时间信息："+UnixTime);
				eng_res_info = eng_res_info.substring(12, eng_res_info.length());
			} else if(type1.equals("13")) {
				//长度1	点火状态(1=点火, 0=熄火)
				String fireON = eng_res_info.substring(0, 5);
				System.out.println("点火状态："+fireON);
				eng_res_info = eng_res_info.substring(5, eng_res_info.length());
			} else if(type1.equals("14")) {
				//长度1	供电状态(0:外部电源 1:电池)
				String lkON = eng_res_info.substring(0, 5);
				System.out.println("供电状态(："+lkON);
				eng_res_info = eng_res_info.substring(5, eng_res_info.length());
			} else if(type1.equals("35")) {
				//长度8	状态位16进制 （从右到左）
				String state = eng_res_info.substring(0, 12);
				System.out.println("状态位："+state);
				eng_res_info = eng_res_info.substring(12, eng_res_info.length());
			}
			
			if(eng_res_info.length()<4)
				break;
		}
		
		HashMap<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("abc", "123");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String mapJson = mapper.writeValueAsString(hashMap);
			System.out.println(mapJson);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}