package com.neusoft.clw.vncs.inside.msg.content.value;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.cachemanager.HarmDefCacheManager;
import com.neusoft.clw.vncs.cachemanager.VehicleCacheManager;
import com.neusoft.clw.vncs.dao.ICLW_SEC_DATADAO;
import com.neusoft.clw.vncs.dao.impl.TerminalDAO;
import com.neusoft.clw.vncs.domain.HarmDefBean;
import com.neusoft.clw.vncs.domain.VehicleBean;
import com.neusoft.clw.vncs.inside.msg.InsideMsgStatusCode;
import com.neusoft.clw.vncs.inside.msg.content.CommandCode;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.service.BuildSQL;
import com.neusoft.clw.vncs.service.SendCmdService;
import com.neusoft.clw.vncs.util.AccountUtil;
import com.neusoft.clw.vncs.util.Converser;

public class Up_HistoryValue {
	private static final Logger log = LoggerFactory.getLogger(Up_HistoryValue.class);
	
	private static final String NAME = "<Up_HistoryValue>";
	
	private static final TerminalDAO terminalDAO = (TerminalDAO) SpringBootStrap.getInstance().getBean("terminalDAO");
	
	private static ICLW_SEC_DATADAO secdao = (ICLW_SEC_DATADAO) SpringBootStrap.getInstance().getBean("clw_sec_dataDAO");
	
	@SuppressWarnings("unchecked")
	public static void getUp_HistoryContent(int location,byte[] buf ,Up_InfoContent uhc) throws Exception{
		// 截取packet_content中0x01的参数名(msg_id)
//		byte[] bytename01 = new byte[ContentParamName.REGULARLEN];
//		System.out.println(new String(bytename01));
		// 截取packet_content中0x01的长度
//		byte[] bytelen01 = new byte[ContentParamName.REGULARLEN];
//		System.arraycopy(buf, location, bytename01, 0,ContentParamName.REGULARLEN);
		location += ContentParamName.REGULARLEN;
		int bytelen01 = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
//		System.arraycopy(buf, location, bytelen01, 0,ContentParamName.REGULARLEN);
		location += ContentParamName.REGULARLEN;
		String bytevalue01 = new String(buf,location,bytelen01);
		// 截取packet_content中0x01的值
//		byte[] bytevalue01 = new byte[Integer.parseInt(new String(bytelen01),16)];
//		System.arraycopy(buf, location, bytevalue01, 0, Integer.parseInt(new String(bytelen01),16));
		location += bytelen01;
		String type = null;
		int len = ContentParamName.getWhole_len(buf);
		if(location<len){
			// 截取packet_content中0x02的长度及值(12=登录记录	13=车辆超速记录 14=开关量变化记录  15=急加速记录
			//22=REC_ID_TEST_DEMO_1S_DATA5 23=REC_ID_TEST_DEMO_1MIN_DATA6 24=REC_ID_TEST_DEMO_5MIN_DATA7	
			//25= rec_celldata_test_demo_1s_data8)
			location += ContentParamName.REGULARLEN;
			
			int bytelen02 = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
			location += ContentParamName.REGULARLEN;
			
			type = new String(buf,location,bytelen02); 
			location += bytelen02;
			// 根据不同的上报参数类型，进入响应的处理流程
			uhc.setType(type);
			String currenttime = terminalDAO.getSysTime();
			Map map = SendCmdService.getCacheMap(type, uhc);
			if(map!=null&&map.size()>0){
				String uuid = (String) map.get("msg_id");
				log.debug("uuid="+uuid);
				if(uuid.equals(bytevalue01)){
					log.info("uuid相等,进行解析");
					map.put("currenttime", currenttime);
					log.debug("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+map);
					SendCmdService.setCacheMap(type,uhc,map);
				}else{
					return;
				}
					// 将0x01的值(即msg_id)存入Up_InfoContent
				uhc.setMsg_id(bytevalue01);
				if(type.equals("12")){
					log.info(NAME+"终端登录记录处理");
				}else if(type.equals("13")){
					log.info(NAME+"车辆超速记录处理");
				}else if(type.equals("14")){
					log.info(NAME+"车辆开关量记录处理");
				}else if(type.equals("15")){
					log.info(NAME+"车辆急加减速记录处理");
				}else if(type.equals("22")){
					log.info(NAME+"车联网秒数据处理");
				}else if(type.equals("23")){
					log.info(NAME+"车联网1分钟数据处理");
				}else if(type.equals("24")){
					log.info(NAME+"车联网5分钟数据处理");
				}else{
					throw new ParseException("没有此种历史数据!");
				}
				while (location < len) {	
					String cmd = null;
					int bytelen = 0;
					byte[] bytevalue = null;
				
//					System.arraycopy(buf, location, bytename, 0,ContentParamName.REGULARLEN);		
					//比如0x00这个字段的值 也就是说00
					cmd = new String(buf,location,ContentParamName.REGULARLEN);
					location += ContentParamName.REGULARLEN;
//					bytelen = new byte[ContentParamName.REGULARLEN];
					if(cmd.equals(ContentParamName.packet_content03)){
						bytelen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN*2),16);
//						System.arraycopy(buf, location, bytelen, 0, ContentParamName.REGULARLEN*2);
						location +=ContentParamName.REGULARLEN*2;
					}else{
						bytelen = Integer.parseInt(new String(buf,location,ContentParamName.REGULARLEN),16);
//						System.arraycopy(buf, location, bytelen, 0, ContentParamName.REGULARLEN);
						location += ContentParamName.REGULARLEN;
					}
					bytevalue = new byte[bytelen];
					location = ContentParamName.setBytevalue(bytelen, buf,location, bytevalue, cmd);
					setUpHistoryContent(cmd, bytevalue,uhc,type,bytelen);
				}
			}
			//检索上行的数据是否接收完全，以msg_id和车辆vin进行读取缓存开始时间和结束时间，进行比较
			//如果未到结束时间点，则继续进行下发，并更新缓存中的时开始时间，直至到达结束时间点，清除该缓存
			if(type.equals(CommandCode.down2006_25)){
				SendCmdService.isContinueSendYTBCmd(uhc);
			}else if(type.equals(CommandCode.down2006_22)){
				secdao.updateHis_Time(uhc.getTime(),uhc.getTerminalId());
				SendCmdService.isSendClw1SecCmd(uhc,currenttime);
			}else if(type.equals(CommandCode.down2006_23)){
				secdao.update1MinHis_Time(uhc.getTime(), uhc.getTerminalId());
				SendCmdService.isSendClwMCmd(uhc, 1,currenttime);
			}else if(type.equals(CommandCode.down2006_24)){
				secdao.update5MinHis_Time(uhc.getTime(), uhc.getTerminalId());
				SendCmdService.isSendClwMCmd(uhc, 5,currenttime);
			}else if(type.equals(CommandCode.down2006_15)){
				secdao.updateRapidHis_Time(uhc.getClw_rapid_onedata_time(), uhc.getTerminalId());
				SendCmdService.isSendClwRapidCmd(uhc,currenttime);
			}else {
				if(type.equals("14")){
					secdao.updateOnOffHis_Time(uhc.getTime(), uhc.getTerminalId());
				}
				if(type.equals("13")){
					secdao.updateOverspeedHis_Time(uhc.getTime(), uhc.getTerminalId());
				}
				if(type.equals("12")){
					secdao.updateLoginHis_Time(uhc.getTime(), uhc.getTerminalId());
				}
				SendCmdService.isSendClw12Cmd(uhc,currenttime);
			}
		}else{
			log.info(NAME+"缓存中"+uhc.getTerminalId()+"没有数据,该数据无效");
		}
		
	}

	public static void setUpHistoryContent(String cmd, byte[] bytevalue,
			Up_InfoContent uhc, String type,int len) throws ParseException, java.text.ParseException {
		if(type.equals("25")){
			//表示上行历史数据类型为宇通杯秒级数据
			if(cmd.equals(ContentParamName.packet_content03)){
				//解析报文并返回报文长度
				int le = setYTBHistoryData(uhc,bytevalue);
				//如果报文长度与规定长度不等，表明上传的报文错误，不符合协议
				if(le !=len){
					log.info(NAME+"解析宇通杯秒数据时发生异常");
					throw new ParseException("解析宇通杯秒数据时发生异常");
				}
//				log.info(NAME+"宇通杯秒数据入库成功"));
			}else{
				log.info(NAME+"宇通杯秒数据报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("宇通杯秒数据报文元素错误，不包含元素"+cmd);
			}
		}else if(type.equals("12")){
			log.debug(NAME+"终端登录记录处理开始");
			//登陆记录
			if(cmd.equals(ContentParamName.packet_content03)){
				int le = setCLWLoginData(uhc,bytevalue);
				if(le!=len){
					log.info(NAME+"解析登陆记录时发生异常");
					throw new ParseException("解析登陆记录时发生异常");
				}
			}else{
				log.info(NAME+"登陆记录报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("登陆记录报文元素错误，不包含元素"+cmd);
			}
			log.debug(NAME+"终端登录记录处理结束");
		}else if(type.equals("13")){
			log.debug(NAME+"车辆超速记录处理开始");
			//车辆超速记录
			if(cmd.equals(ContentParamName.packet_content03)){
				int le = setCLWOverSpeedData(uhc,bytevalue);
				if(le!=len){
					log.info(NAME+"解析车辆超速记录时发生异常");
					throw new ParseException("解析车辆超速记录时发生异常");
				}
			}else{
				log.info(NAME+"车辆超速记录报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("车辆超速记录报文元素错误，不包含元素"+cmd);
			}
			log.debug(NAME+"车辆超速记录处理结束");
		}else if(type.equals("14")){
			log.debug(NAME+"车辆开关量记录处理开始");
			//开关量变化记录
			if(cmd.equals(ContentParamName.packet_content03)){
				int le = setCLWOnOffData(uhc,bytevalue);
				if(le!=len){
					log.info(NAME+"解析开关量记录时发生异常");
					throw new ParseException("解析开关量记录时发生异常");
				}
			}else{
				log.info(NAME+"车辆超速记录报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("车辆超速记录报文元素错误，不包含元素"+cmd);
			}
			log.debug(NAME+"车辆开关量记录处理结束");
		}else if(type.equals("15")){
			log.debug(NAME+"急加减速记录处理开始");
			//急加速记录
			if(cmd.equals(ContentParamName.packet_content03)){
				int le = setCLWRapidData(uhc,bytevalue);
				if(le!=len){
					log.info(NAME+"解析急加速记录时发生异常");
					throw new ParseException("解析急加速记录时发生异常");
				}else{
					//处理急加速数据，并入库
					RapidInsert(uhc);
				}
			}else{
				log.info(NAME+"车辆超速记录报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("车辆超速记录报文元素错误，不包含元素"+cmd);
			}
			log.debug(NAME+"急加减速记录处理结束");
		}else if(type.equals("22")){
			log.debug(NAME+"车联网秒数据处理开始");
			//车联网秒数据
			if(cmd.equals(ContentParamName.packet_content03)){
				int le = setCLWSecData(uhc,bytevalue);
				if(le!=len){
					log.info(NAME+"解析车联网秒数据记录时发生异常");
					throw new ParseException("解析车联网秒数据记录时发生异常");
				}
			}else{
				log.info(NAME+"车联网秒数据记录报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("车联网秒数据记录报文元素错误，不包含元素"+cmd);
			}
			log.debug(NAME+"车联网秒数据处理结束");
		}else if(type.equals("23")){
			log.debug(NAME+"车联网1分钟数据处理开始");
			//车联网1分钟数据
			if(cmd.equals(ContentParamName.packet_content03)){
				int le = setCLWMinute1Data(uhc,bytevalue);
				if(le!=len){
					log.info(NAME+"解析车联网1分钟数据记录时发生异常");
					throw new ParseException("解析车联网1分钟数据记录时发生异常");
				}
			}else{
				log.info(NAME+"车联网1分钟数据记录报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("车联网1分钟数据记录报文元素错误，不包含元素"+cmd);
			}
			log.debug(NAME+"车联网1分钟数据处理结束");
		}else if(type.equals("24")){
			log.debug(NAME+"车联网5分钟数据处理开始");
			//车联网5分钟数据
			if(cmd.equals(ContentParamName.packet_content03)){
				int le = setCLWMinute5Data(uhc,bytevalue);
				if(le!=len){
					log.info(NAME+"解析车联网5分钟数据记录时发生异常");
					throw new ParseException("解析车联网5分钟数据记录时发生异常");
				}
			}else{
				log.info(NAME+"车联网5分钟数据记录报文元素错误，不包含元素"+cmd);
				uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
				throw new ParseException("车联网5分钟数据记录报文元素错误，不包含元素"+cmd);
			}
			log.debug(NAME+"车联网5分钟处理结束");
		}else {
			log.info(NAME+"历史数据报文元素错误，不包含元素"+cmd);
			uhc.setStatusCode(InsideMsgStatusCode.STATUS_NOT_THIS_ELEMENT);
			throw new ParseException("历史数据报文元素错误，不包含元素"+cmd);
		}
	}
	
//	private static void RapidInsert(Up_InfoContent uhc) throws ParseException, java.text.ParseException {
//		String speedAndonoff = uhc.getClw_rapid_speed_dit();
//		List<String> rapidsql = new ArrayList<String>();
//		if(speedAndonoff.length()!=200){
//			throw new ParseException("急加速记录u16_t speed_dit长度错误");
//		}else{
//			int len = speedAndonoff.length()/10;
//			for(int i=len-1;i>=0;i--){
//				uhc.setTime(DateUtil.getDateByDiscreMilliSecondWithNow(uhc.getClw_rapid_time(), -200*(i)));
//				int index =i+2;
//				uhc.setClw_rapid_speed(speedAndonoff.substring(i,index));
//				uhc.setClw_rapid_onoff(Converser.hexTo2BCD(speedAndonoff).substring(index, i+10));
//				System.out.println(uhc.getClw_rapid_onedata_time()+","+uhc.getClw_rapid_onoff());
//				rapidsql.add(BuildSQL.getInstance().buildInsertClwRapidSQL(uhc));
//			}
//		}
//		DBBuffer.getInstance().add(rapidsql);	
//	}

	public static void RapidInsert(Up_InfoContent uhc) throws ParseException, java.text.ParseException {
		String speedAndonoff = uhc.getClw_rapid_speed_dit();
		List<String> rapidsql = new ArrayList<String>();
		if(speedAndonoff.length()!=400){
			throw new ParseException("急加速记录u16_t speed_dit长度错误");
		}else{
			int len = speedAndonoff.length()/ContentParamName.clw_rapid_totallen;
//			System.out.println(len);
			for(int i=0;i<len;i++){
				if(i>len/2){
//					System.out.println(len/2-i);
					uhc.setClw_rapid_onedata_time(DateUtil.getDateByDiscreMilliSecondWithNow(uhc.getTime(), -200*(len/2-i)));
				}else{
//					System.out.println(i-len/2);
					uhc.setClw_rapid_onedata_time(DateUtil.getDateByDiscreMilliSecondWithNow(uhc.getTime(), 200*(i-len/2)));
				}
//				System.out.println(uhc.getClw_rapid_onedata_time());
				String onedata = speedAndonoff.substring(ContentParamName.clw_rapid_totallen*i, ContentParamName.clw_rapid_totallen*(i+1));
//				System.out.println(onedata);
//				System.out.println(onedata.substring(0, index)+","+onedata.substring(index, 10));
				uhc.setClw_rapid_speed(String.valueOf(Long.valueOf(onedata.substring(0,ContentParamName.clw_rapid_onespeedlen),16)));
				uhc.setClw_rapid_onoff(Converser.hexTo2BCD(onedata.substring(ContentParamName.clw_rapid_onespeedlen, ContentParamName.clw_rapid_totallen)));
//				System.out.println(uhc.getClw_rapid_onedata_time()+","+uhc.getClw_rapid_speed()+","+uhc.getClw_rapid_onoff());
				rapidsql.add(BuildSQL.getInstance().buildInsertClwRapidSQL(uhc));
			}
		}
		DBBuffer.getInstance().add(rapidsql);	
	}
	
	/**
	 * 解析车联网急加速记录报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int setCLWRapidData(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		//Time：12 
		String time = new String(bytevalue,location,ContentParamName.clw_rapid_timelen);
		location+=ContentParamName.clw_rapid_timelen;
		
		//200长度的串包括20秒的数据
		String value = new String(bytevalue,location,ContentParamName.clw_rapid_onofflen);
		location+=ContentParamName.clw_rapid_onofflen;
		
		//latitude： 9，（第1位， 1=北纬 0=南纬）
		String latitude = new String(bytevalue,location,ContentParamName.clw_rapid_latitudelen);
		location+=ContentParamName.clw_rapid_latitudelen;
		
		//ongitude：9，（第1位， 1=东经 0=西经）
		String longitude = new String(bytevalue,location,ContentParamName.clw_rapid_longitudelen);
		location+=ContentParamName.clw_rapid_longitudelen;
		
		//speed（单位: km/h：）：7，缩小100000为真值
		String speed  = new String(bytevalue,location,ContentParamName.clw_rapid_speedlen);
		location+=ContentParamName.clw_rapid_speedlen;
		
		//t heading（单位度）：6，缩小100为真值
		String heading = new String(bytevalue,location,ContentParamName.clw_rapid_heading);
		location+=ContentParamName.clw_rapid_heading;
		
		uhc.setTime(time);
		uhc.setClw_rapid_speed_dit(value);
		uhc.setClw_rapid_latitude(ContentParamName.checkLength(AccountUtil.accountLongLat(latitude),ContentParamName.CLW_RAPID_LATITUDE_BASE));
		uhc.setClw_rapid_longitude(ContentParamName.checkLength(AccountUtil.accountLongLat(longitude), ContentParamName.CLW_RAPID_LONGITUDE_BASE));
		uhc.setClw_rapid_speed(ContentParamName.checkLength(AccountUtil.setHexString(speed, Constant.F7, 100000),ContentParamName.CLW_RAPID_SPEED_BASE));
		uhc.setClw_rapid_heading(ContentParamName.checkLength(AccountUtil.setHexString(heading,Constant.F6, 100),ContentParamName.CLW_RAPID_DIRECTION_BASE));
		
		log.debug("车联网急加速记录原始串:"+new String(bytevalue));
		log.debug("msg_id="+uhc.getMsg_id()+",vehicle_vin="+uhc.getTerminalId()+",time="+uhc.getTime()
				+",u16_t speed_dit="+uhc.getClw_rapid_speed_dit()
				+",latitude="+uhc.getClw_rapid_latitude()
				+",longitude="+uhc.getClw_rapid_longitude()
				+",speed="+uhc.getClw_rapid_speed()
				+",t heading="+uhc.getClw_rapid_heading());
		
		return location;
	}
	/**
	 * 解析车联网开关量记录报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int setCLWOnOffData(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		//时间byte
		String time = new String(bytevalue,location,ContentParamName.clw_onoff_timelen);
		location+=ContentParamName.clw_onoff_timelen;
		//变化后的开关量
		String aftervalue = new String(bytevalue,location,ContentParamName.clw_onoff_aftervaluelen);
		location+=ContentParamName.clw_onoff_aftervaluelen;
		//变化前的开关量
		String prevalue = new String(bytevalue,location,ContentParamName.clw_onoff_prevaluelen);
		location+=ContentParamName.clw_onoff_prevaluelen;
		//变化时的车速：用于判定是否有车速时开关门
		String speed = new String(bytevalue,location,ContentParamName.clw_onoff_speedlen);
		location+=ContentParamName.clw_onoff_speedlen;
		
		uhc.setTime(time);
		uhc.setClw_onoff_aftervalue(Up_RealTimeValue.parseInformation(aftervalue));
		uhc.setClw_onoff_prevalue(Up_RealTimeValue.parseInformation(prevalue));
		uhc.setClw_onoff_speed(ContentParamName.checkLength(AccountUtil.setHexString(speed, Constant.F8,1000000,3.6),ContentParamName.CLW_ON_OF_SPEED_BASE));
		
		log.debug("车联网开关量记录原始串:"+new String(bytevalue));
		log.debug("msg_id="+uhc.getMsg_id()+",vehicle_vin="+uhc.getTerminalId()+",time="+uhc.getTime()
				+",变化后的开关量="+uhc.getClw_onoff_aftervalue()
				+",变化前的开关量="+uhc.getClw_onoff_prevalue()
				+",变化时的车速="+uhc.getClw_onoff_speed());
		
		DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertClwOnOffSQL(uhc));
		log.info(NAME+"车联网开关量记录入库成功");
		
		return location;
	}
	/**
	 * 解析车联网登陆记录报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int setCLWLoginData(Up_InfoContent uhc, byte[] bytevalue) {
		int location  = 0;
		//登陆记录
		//时间byte
		String logintime = new String(bytevalue,location,ContentParamName.clw_login_timelen);
		location+=ContentParamName.clw_login_timelen;
		//驾驶员编号byte
		String logindriverid = new String(bytevalue,location,ContentParamName.clw_login_driveridlen);
		location+=ContentParamName.clw_login_driveridlen;
		//权限byte
		String logincardlevel = new String(bytevalue,location,ContentParamName.clw_login_cardlevellen);
		location+=ContentParamName.clw_login_cardlevellen;
		//一般管理员卡(管理员登陆密码)
		String loginpass = new String(bytevalue,location,ContentParamName.clw_login_passlen);
		location+=ContentParamName.clw_login_passlen;
		//卡id byte
		String logincardid = new String(bytevalue, location,ContentParamName.clw_login_cardidlen);
		location+=ContentParamName.clw_login_cardidlen;
		//驾驶员号码
		String logindriverlicense = new String(bytevalue,location,ContentParamName.clw_login_driverlicenselen);
		location+=ContentParamName.clw_login_driverlicenselen;
		//set登陆记录logincardidbyte
		uhc.setTime(logintime);
		if(!Converser.hexToString(logindriverid).trim().equals("0")){
			uhc.setClw_login_driverid(Converser.hexToString(logindriverid).trim());
		}else{
			uhc.setClw_login_driverid("");
		}
		if(!Integer.valueOf(logincardlevel,2).equals("0")){
			uhc.setClw_login_cardlevel(Integer.toHexString(Integer.valueOf(logincardlevel,2)).trim());
		}else{
			uhc.setClw_login_cardlevel("");
		}
		if(loginpass.equals("FFFFFFFFFFFFFFFF")){
			uhc.setClw_login_pass("FFFF");
		}else{
			if(!Converser.hexToString(loginpass).equals("0")){
				uhc.setClw_login_pass(new String(Converser.hexStringToBytes(loginpass)).trim());
			}else{
				uhc.setClw_login_pass("");
			}
		}
		if(logincardid.equals("FFFFFFFFFFFFFFFFFFFFFFFFFFFF")){
			uhc.setClw_login_cardid("FFFF");
		}else {
			if(!new String(Converser.hexStringToBytes(logincardid)).trim().equals("")){
				uhc.setClw_login_cardid(new String(Converser.hexStringToBytes(logincardid)).trim());
			}else{
				uhc.setClw_login_cardid("");
			}
		}
		if(!new String(Converser.hexStringToBytes(logindriverlicense)).trim().equals("")){
			uhc.setClw_login_driverlicense(new String(Converser.hexStringToBytes(logindriverlicense)).trim());
		}else{
			uhc.setClw_login_driverlicense("");
		}
		
		log.debug("车联网登陆记录原始串:"+new String(bytevalue));
		log.debug("msg_id="+uhc.getMsg_id()+",vehicle_vin="+uhc.getTerminalId()+",time="+uhc.getTime()
				+",驾驶员代码="+uhc.getClw_login_driverid()
				+",权限="+uhc.getClw_login_cardlevel()
				+",密码="+uhc.getClw_login_pass()
				+",卡ID="+uhc.getClw_login_cardid()
				+",驾驶证号码="+uhc.getClw_login_driverlicense());
		
		DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertClwLoginSQL(uhc));
		log.info(NAME+"车联网登陆记录入库成功");
		
		return location;
	}
	/**
	 * 解析车联网5分钟数据报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException 
	 */
	@SuppressWarnings("unchecked")
	private static int setCLWMinute5Data(Up_InfoContent uhc, byte[] bytevalue) throws java.text.ParseException {
		int location = 0;
		String data_valid = new String(bytevalue,location,ContentParamName.data_validlen);
		location+=ContentParamName.data_validlen;
		if(data_valid.equals("0")){
			//时间
			String time = new String(bytevalue,location,ContentParamName.clw_munite5_timelen);
			location+=ContentParamName.clw_munite5_timelen;
			//total_engine_hours（发动机运行时间 h）：10，缩小100倍为真值
			String total_engine_hours = new String(bytevalue,location,ContentParamName.clw_munite5_totalenginehourslen);
			location+=ContentParamName.clw_munite5_totalenginehourslen;
			//electrical_potentia（蓄电池电压 V）：5，缩小10倍为真值
			String electrical_potentia = new String(bytevalue,location,ContentParamName.clw_munite5_electricalpotentialen);
			location+=ContentParamName.clw_munite5_electricalpotentialen;
			//barometric_pressure（大气压力 kPa）：3，缩小10倍为真值
			String barometric_pressure = new String(bytevalue,location,ContentParamName.clw_munite5_barometricpressurelen);
			location+=ContentParamName.clw_munite5_barometricpressurelen;
			//air_inlet_temperature(进气温度  1degC)：3， 16进制（第一位为1为负，0为正）
			String air_inlet_temperature = new String(bytevalue,location,ContentParamName.clw_munite5_airinlettemperaturelen);
			location+=ContentParamName.clw_munite5_airinlettemperaturelen;
			
			uhc.setTime(time);
			uhc.setClw_munite5_totalenginehours(ContentParamName.checkLength(AccountUtil.setHexString(total_engine_hours, Constant.F10,100),ContentParamName.ENGHRREV_T_E_H_BASE));
			uhc.setClw_munite5_barometricpressure(ContentParamName.checkLength(AccountUtil.setHexString(barometric_pressure, Constant.F3,10),ContentParamName.AIR_PRESSURE_BASE));
			uhc.setClw_munite5_electricalpotentia(ContentParamName.checkLength(AccountUtil.setHexString(electrical_potentia, Constant.F5,10),ContentParamName.BATTERY_VOLTAGE_BASE));
			uhc.setClw_munite5_airinlettemperature(ContentParamName.checkLength(AccountUtil.accountTemp(air_inlet_temperature, Constant.F3),ContentParamName.AIR_INFLOW_TPR_BASE));
			log.debug("车联网5分钟数据原始串:"+new String(bytevalue));
			Map map = SendCmdService.getCacheMap(CommandCode.down2006_24, uhc);
			if(map!=null&&map.size()>0){
				String start_time = (String) map.get("start_time");
				if(start_time!=null&&!start_time.equals("")){
					if(uhc.getTime().compareTo(start_time)>0){
						log.debug("车联网5分钟数据原始串:"+new String(bytevalue));
						log.debug("msg_id="+uhc.getMsg_id()+",vehicle_vin="+uhc.getTerminalId()+",time="+uhc.getTime()
								+",total_engine_hours="+uhc.getClw_munite5_totalenginehours()
								+",electrical_potentia="+uhc.getClw_munite5_electricalpotentia()
								+",barometric_pressure="+uhc.getClw_munite5_barometricpressure()
								+",air_inlet_temperature="+uhc.getClw_munite5_airinlettemperature());
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertClwMunite5(uhc));
					}else{
						log.info("<Up_HistoryValue-setCLWMinute5Data>该时间数据"+uhc.getTime()+"已存在");
					}
				}else{
					log.info("<Up_HistoryValue-setCLWMinute5Data>"+start_time+"无效，不处理");
				}
			}else{
				log.info("<Up_HistoryValue-setCLWMinute5Data>"+map+"未找到，不处理");
			}
//			log.info(NAME+"车联网5分钟数据入库成功"));	
		}else{
			if(uhc.getTime()!=null&&!uhc.getTime().equals("")){
//				System.out.println("qqqqqqqqq"+DateUtil.getDateByDiscreSecondWithNow(uhc.getTime(), 1));
				uhc.setTime(DateUtil.getDate15ByDiscreMuniteWithNow15(uhc.getTime(), 5));
				log.info("<Up_HistoryValue-setCLWMinute5Data>"+uhc.getTime()+"车联网5分钟数据无效:"+data_valid+"，不对其操作");
			}
		}
		return location;
	}
	/**
	 * 解析车联网1分钟数据报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException 
	 */
	@SuppressWarnings("unchecked")
	private static int setCLWMinute1Data(Up_InfoContent uhc, byte[] bytevalue) throws java.text.ParseException {
		int location = 0;
		String data_valid = new String(bytevalue,location,ContentParamName.data_validlen);
		location+=ContentParamName.data_validlen;
		if(data_valid.equals("0")){
			//时间
			String time = new String(bytevalue,location,ContentParamName.clw_munite1_timelen);
			location+=ContentParamName.clw_munite1_timelen;
			//total_fuel_used（累计油耗L）：缩小10倍为真值
			String totaloil = new String(bytevalue,location,ContentParamName.clw_munite1_totalfuelusedlen);
			location+=ContentParamName.clw_munite1_totalfuelusedlen;
			//fuel_level(油箱油量),缩小10倍为真值
			String fuellevel = new String(bytevalue,location,ContentParamName.clw_munite1_fuellevellen);
			location+=ContentParamName.clw_munite1_fuellevellen;
			//engine_oil_pressure（发动机油压 kPa）
			String engineoilpressure = new String(bytevalue,location,ContentParamName.clw_munite1_engineoilpressurelen);
			location+=ContentParamName.clw_munite1_engineoilpressurelen;
			//engine_oil_temperature（发动机油温1degC）：3 ，（第一位为1为负，0为正）
			String engineoiltemperature = new String(bytevalue,location,ContentParamName.clw_munite1_engineoiltemperaturelen);
			location+=ContentParamName.clw_munite1_engineoiltemperaturelen;
			//engine_coolant_temperature（发动机冷却水水温=degC 3，（第一位为1为负，0为正）
			String enginecooltemperature = new String(bytevalue,location,ContentParamName.clw_munite1_enginecoolanttemperaturelen);
			location+=ContentParamName.clw_munite1_enginecoolanttemperaturelen;
			
			uhc.setTime(time);
			uhc.setClw_munite1_totalfuelused(ContentParamName.checkLength(AccountUtil.setHexString(totaloil, Constant.F9,10),ContentParamName.OIL_TOTAL_BASE));
			uhc.setClw_munite1_fuellevel(ContentParamName.checkLength(AccountUtil.setHexString(fuellevel, Constant.F5,10),ContentParamName.OIL_VALUE_BASE));
			uhc.setClw_munite1_engineoilpressure(ContentParamName.checkLength(AccountUtil.setHexString(engineoilpressure, Constant.F3),ContentParamName.EP_EFDP_BASE));
			uhc.setClw_munite1_engineoiltemperature(ContentParamName.checkLength(AccountUtil.accountTemp(engineoiltemperature, Constant.F3),ContentParamName.OIL_TEMPERATURE_BASE));
			if(enginecooltemperature.equals(Constant.F3)){
				uhc.setClw_munite1_enginecoolanttemperature(Constant.F4);
			}else{
				uhc.setClw_munite1_enginecoolanttemperature(ContentParamName.checkLength(Converser.hexToString(enginecooltemperature),ContentParamName.COLDER_TEMPERATURE_BASE));
			}
			log.debug("车联网1分钟数据原始串:"+new String(bytevalue));
			Map map = SendCmdService.getCacheMap(CommandCode.down2006_23, uhc);
			if(map!=null&&map.size()>0){
				String start_time = (String) map.get("start_time");
				if(start_time!=null&&!start_time.equals("")){
					if(uhc.getTime().compareTo(start_time)>0){
//						log.debug("车联网1分钟数据原始串:"+new String(bytevalue));
						log.debug("msg_id="+uhc.getMsg_id()+",vehicle_vin="+uhc.getTerminalId()+",time="+uhc.getTime()
								+",total_fuel_used="+uhc.getClw_munite1_totalfuelused()+",fuel_level="+uhc.getClw_munite1_fuellevel()
								+",engine_oil_pressure="+uhc.getClw_munite1_engineoilpressure()+",engine_oil_temperature="+uhc.getClw_munite1_engineoiltemperature()
								+",engine_coolant_temperature="+uhc.getClw_munite1_enginecoolanttemperature());
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertClwMunite1(uhc));
					}else{
						log.info("<Up_HistoryValue-setCLWMinute1Data>该时间数据"+uhc.getTime()+"已存在");
					}
				}else{
					log.info("<Up_HistoryValue-setCLWMinute1Data>"+start_time+"无效，不处理");
				}
			}else{
				log.info("<Up_HistoryValue-setCLWMinute1Data>"+map+"未找到，不处理");
			}
//			log.info(NAME+"车联网1分钟数据入库成功"));
		}else{
			if(uhc.getTime()!=null&&!uhc.getTime().equals("")){
//				System.out.println("qqqqqqqqq"+DateUtil.getDateByDiscreSecondWithNow(uhc.getTime(), 1));
				uhc.setTime(DateUtil.getDate15ByDiscreMuniteWithNow15(uhc.getTime(), 1));
				log.info("<Up_HistoryValue-setCLWMinute1Data>"+uhc.getTime()+"车联网1分钟数据无效:"+data_valid+"，不对其操作");
			}
		}
		
		return location;
	}
	/**
	 * 解析车联网秒数据报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException 
	 */
	@SuppressWarnings("unchecked")
	private static int setCLWSecData(Up_InfoContent uhc, byte[] bytevalue) throws java.text.ParseException {
		int location = 0;
		String data_valid = new String(bytevalue,location,ContentParamName.data_validlen);
		location+=ContentParamName.data_validlen;
		if(data_valid.equals("0")){
			//截取16进制时间串
			String time = new String(bytevalue,location,ContentParamName.clw_timelen);
			location+=ContentParamName.clw_timelen;

			String latitude = new String(bytevalue,location,ContentParamName.clw_latitudelen);
			location+=ContentParamName.clw_latitudelen;
			//截取longitude经度 16进制值
			String longitude = new String(bytevalue,location,ContentParamName.clw_longitudelen);
			location+=ContentParamName.clw_longitudelen;
			//截取speed车速: 16进制值
			String speed = new String(bytevalue,location,ContentParamName.clw_speedlen);
			location+=ContentParamName.clw_speedlen;
			//截取t heading（单位度）: 16进制值
			String heading = new String(bytevalue,location,ContentParamName.clw_headinglen);
			location+=ContentParamName.clw_headinglen;
//			截取flowrate 16进制值
			String fuel_rate = new String(bytevalue,location,ContentParamName.clw_fuelratelen);
			location+=ContentParamName.clw_fuelratelen;
//			截取engine_speed 16进制值
			String engine_speed = new String(bytevalue,location,ContentParamName.clw_enginespeedlen);
			location+=ContentParamName.clw_enginespeedlen;
//			截取engine_torque 16进制值
			String engine_torque = new String(bytevalue,location,ContentParamName.clw_enginetorguelen);
			location+=ContentParamName.clw_enginetorguelen;
//			截取throttle_position 16进制值
			String throttle_position = new String(bytevalue,location,ContentParamName.clw_throttlepositionlen);
			location+=ContentParamName.clw_throttlepositionlen;
//			截取vehicle_speed 16进制值
			String vehicle_speed = new String(bytevalue,location,ContentParamName.clw_vehiclespeedlen);
			location+=ContentParamName.clw_vehiclespeedlen;

			uhc.setTime(time);
			uhc.setClw_latitude(ContentParamName.checkLength(AccountUtil.accountLongLat(latitude),ContentParamName.LATITUDE_BASE));
			uhc.setClw_longitude(ContentParamName.checkLength(AccountUtil.accountLongLat(longitude),ContentParamName.LONGITUDE_BASE));
			uhc.setClw_speed(ContentParamName.checkLength(AccountUtil.setHexString(speed, Constant.F7,100000),ContentParamName.SPEEDING_BASE));
			uhc.setClw_heading(ContentParamName.checkLength(AccountUtil.setHexString(heading, Constant.F6,100),ContentParamName.CLW_SEC_DIRECTION_BASE));
			uhc.setClw_fuelrate(AccountUtil.setHexString(fuel_rate, Constant.F6,100));
			uhc.setClw_engine_speed(ContentParamName.checkLength(AccountUtil.setHexString(engine_speed,Constant.F7,1000),ContentParamName.ENGINE_ROTATE_SPEED_BASE));
			uhc.setClw_engine_torque(AccountUtil.setHexString(engine_torque,Constant.F3,100));
			uhc.setClw_throttle_position(AccountUtil.setHexString(throttle_position,Constant.F3,10));
			uhc.setClw_vehicle_speed(ContentParamName.checkLength(AccountUtil.setHexString(vehicle_speed, Constant.F2),ContentParamName.VEHICLE_SPEED_BASE));
			log.debug("车联网秒数据原始串:"+new String(bytevalue));
			Map map = SendCmdService.getCacheMap(CommandCode.down2006_22, uhc);
			if(map!=null&&map.size()>0){
				String start_time = (String) map.get("start_time");
				if(start_time!=null&&!start_time.equals("")){
					if(uhc.getTime().compareTo(start_time)>0){
//						log.debug("车联网秒数据原始串:"+new String(bytevalue));
						log.debug("msg_id="+uhc.getMsg_id()+",vehicle_vin="+uhc.getTerminalId()+",time="+uhc.getTime()
								+",vehicle_speed="+uhc.getClw_vehicle_speed()+",engine_speed="+uhc.getClw_engine_speed()
								+",engine_torque="+uhc.getClw_engine_torque()+",throttle_position="+uhc.getClw_throttle_position()
								+",fuel_rate="+uhc.getClw_fuelrate()+",t heading="+uhc.getClw_heading()
								+",latitude="+uhc.getClw_latitude()+",longitude="+uhc.getClw_longitude()+",speed="+uhc.getClw_speed());
						//拼装车联网秒数据,1秒数据的入库语句
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertClwSec(uhc));
					}else{
						log.info("<Up_HistoryValue-setCLWSecData>该时间数据"+uhc.getTime()+"已存在");
					}
				}else{
					log.info("<Up_HistoryValue-setCLWSecData>"+start_time+"无效，不处理");
				}
			}else{
				log.info("<Up_HistoryValue-setCLWSecData>"+map+"未找到，不处理");
			}
//			log.info(NAME+"车联网秒数据入库成功"));
		}else{
			if(uhc.getTime()!=null&&!uhc.getTime().equals("")){
//				System.out.println("qqqqqqqqq"+DateUtil.getDateByDiscreSecondWithNow(uhc.getTime(), 1));
				uhc.setTime(DateUtil.getDateByDiscreSecondWithNow(uhc.getTime(), 5));
				log.info("<Up_HistoryValue-setCLWSecData>"+uhc.getTime()+"数据无效:"+data_valid+"，不对其操作");
			}
		}
		return location;
	}
	/**
	 * 解析车联网车辆超速记录报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int setCLWOverSpeedData(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		//开始时间String
		String start_time = new String(bytevalue,location,ContentParamName.start_timelen);
		location+=ContentParamName.start_timelen;
		//结束时间String
		String end_time = new String(bytevalue,location,ContentParamName.end_timelen);
		location+=ContentParamName.end_timelen;
		//驾驶员编号String
		String driver_id = new String(bytevalue,location,ContentParamName.driver_idlen);
		location+=ContentParamName.driver_idlen;
		
		String type = new String(bytevalue,location,ContentParamName.typelen);
		location+=ContentParamName.typelen;
		
		String highspeed = new String(bytevalue,location,ContentParamName.highspeedlen);
		location+=ContentParamName.highspeedlen;
		
		uhc.setOverspeed_start_time(start_time);
		//结束时间
		uhc.setTime(end_time);
		if(!Converser.hexToString(driver_id).trim().equals("0")){
			uhc.setOverspeed_driver_id(Converser.hexToString(driver_id).trim());
		}
		uhc.setOverspeed_type(type);
		uhc.setOverspeed_maxspeed(ContentParamName.checkLength(AccountUtil.setHexString(highspeed,Constant.F8,1000000,3.6),ContentParamName.CLW_OVERSPEED_MAXSPEED_BASE));
		log.debug("车联网车辆超速记录原始串:"+new String(bytevalue));
		DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertClwOverspeedSql(uhc));
//		log.info(NAME+"车联网车辆超速记录入库成功"));
		return location;
	}
	/**
	 * 解析宇通杯秒数据报文
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException 
	 */
	@SuppressWarnings("unchecked")
	public static int setYTBHistoryData(Up_InfoContent uhc,byte[] bytevalue) throws java.text.ParseException{
		int location = 0;
		String data_validbyte = new String(bytevalue,location,ContentParamName.data_validlen);
//		System.arraycopy(bytevalue, location, data_validbyte, 0, ContentParamName.data_validlen);
		location+=ContentParamName.data_validlen;
		if(data_validbyte.equals("0")){
			//截取16进制时间串
			String time = new String(bytevalue,location,ContentParamName.timelen);
			location+=ContentParamName.timelen;

			//截取vehicle_distance 16进制值
			String vehicle_distance = new String(bytevalue,location,ContentParamName.vehicle_distancelen);
			location+=ContentParamName.vehicle_distancelen;

			//截取vehicle_speed 16进制值
			String vehicle_speed = new String(bytevalue,location,ContentParamName.vehicle_speedlen);
			location+=ContentParamName.vehicle_speedlen;

			//截取engine_speed 16进制值
			String engine_speed = new String(bytevalue,location,ContentParamName.engine_speedlen);
			location+=ContentParamName.engine_speedlen;

			//截取engine_torque 16进制值
			String engine_torque = new String(bytevalue,location,ContentParamName.engine_torquelen);
			location+=ContentParamName.engine_torquelen;

			//截取engine_coolant_temperature 16进制值
			String engine_coolant_temperature = new  String(bytevalue,location,ContentParamName.engine_coolant_temperaturelen);
			location+=ContentParamName.engine_coolant_temperaturelen;

			//截取throttle_position 16进制值
			String throttle_position = new String(bytevalue,location,ContentParamName.throttle_positionlen);
			location+=ContentParamName.throttle_positionlen;

			//截取flowrate 16进制值
			String flowrate = new String(bytevalue,location,ContentParamName.flowratelen);
			location+=ContentParamName.flowratelen;

			//截取totalconsumption 16进制值
			String totalconsumption = new String(bytevalue,location,ContentParamName.totalconsumptionlen);
			location+=ContentParamName.totalconsumptionlen;			

			//截取latitude纬度 16进制值
			String latitude = new String(bytevalue,location,ContentParamName.latitudelen);
			location+=ContentParamName.latitudelen;

			//截取longitude经度 16进制值
			String longitude = new String(bytevalue,location,ContentParamName.longitudelen);
			location+=ContentParamName.longitudelen;

			//截取altitude海拔: 16进制值
			String altitude = new String(bytevalue,location,ContentParamName.altitudelen);
			location+=ContentParamName.altitudelen;

			//截取speed车速: 16进制值
			String speed = new String(bytevalue,location,ContentParamName.speedlen);
			location+=ContentParamName.speedlen;

			//截取week星期
			uhc.setWeek(ContentParamName.checkLength(AccountUtil.setHexString(new String(bytevalue,location,ContentParamName.weeklen),Constant.F4),ContentParamName.WEEK_BASE));
			location+=ContentParamName.weeklen;

			//截取time_of_week星期
			uhc.setTime_of_week(ContentParamName.checkLength(AccountUtil.setHexString(new String(bytevalue,location,ContentParamName.time_of_weeklen), Constant.F8),ContentParamName.TIME_OF_WEEK_BASE));		
			location+=ContentParamName.time_of_weeklen;
			uhc.setTime(time);
			uhc.setVehicle_distance(ContentParamName.checkLength(AccountUtil.setHexString(vehicle_distance,Constant.F8,1000),ContentParamName.PULSE_MILEAGE_BASE));
			uhc.setVehicle_speed(ContentParamName.checkLength(AccountUtil.setHexString(vehicle_speed,Constant.F5,1000),ContentParamName.VEHICLE_SPEED_BASE));
			uhc.setEngine_speed(ContentParamName.checkLength(AccountUtil.setHexString(engine_speed,Constant.F7,1000),ContentParamName.ENGINE_ROTATE_SPEED_BASE));
			uhc.setEngine_torque(AccountUtil.setHexString(engine_torque,Constant.F3,100));
			uhc.setEngine_coolant_temperature(AccountUtil.setHexString(engine_coolant_temperature,Constant.F3));
			uhc.setThrottle_position(AccountUtil.setHexString(throttle_position,Constant.F3,10));
			uhc.setFlowrate(AccountUtil.setHexString(flowrate,Constant.F4,100));
			if(!totalconsumption.equals(Constant.F8)){
				DecimalFormat df = new DecimalFormat("##########.##################"); 
				uhc.setTotalconsumption(ContentParamName.checkLength(df.format(Converser.hexTo2String(totalconsumption, 10000)),ContentParamName.OIL_TOTAL_BASE));
			}else{
				uhc.setTotalconsumption(Constant.F4);
			}
			uhc.setYtb_latitude(ContentParamName.checkLength(AccountUtil.accountLongLat(latitude),ContentParamName.LATITUDE_BASE));
			uhc.setYtb_longitude(ContentParamName.checkLength(AccountUtil.accountLongLat(longitude),ContentParamName.LONGITUDE_BASE));
			uhc.setYtb_altitude(ContentParamName.checkLength(AccountUtil.setHexString(altitude, Constant.F8,1000),ContentParamName.ELEVATION_BASE));
			uhc.setSpeed(ContentParamName.checkLength(AccountUtil.setHexString(speed, Constant.F8,100000,3.6),ContentParamName.SPEEDING_BASE));
			String vengine_speed = uhc.getEngine_speed();
			String vspeed = uhc.getVehicle_speed();
			
			if(vspeed!=null&&!vspeed.equals("")&&vengine_speed!=null&&!vengine_speed.equals("")
					&&!speed.equals(Constant.F4)&&!vengine_speed.equals(Constant.F4)){
				String gears = AccountUtil.accountGears(uhc.getTerminalId(), vspeed, vengine_speed);
				//变速箱比
				uhc.setRatio(AccountUtil.accountRatio(uhc.getTerminalId(), vspeed, vengine_speed));
//				log.info("!!!!!!!!@@@@@@@@gears:"+gears);
				//档位
				uhc.setGears(gears);
				HarmDefBean hdf = HarmDefCacheManager.harmdefMap.get(uhc.getTerminalId());
				if(hdf!=null){
					if(!gears.equals("-")&&!vspeed.equals("0.0")){
						//行车档位不当
						uhc.setGear_unfit(AccountUtil.accountGear_unfitAlarm(vspeed, gears, hdf));
						//空挡滑行
//						log.info("!!!!!!!!!!###########2"+hdf);
						uhc.setEgear_run(AccountUtil.accountEgear_runAlarm(vspeed, gears,hdf));
						//2挡起步
						uhc.setGear2_start(AccountUtil.accountGear2_spdAlarm(vspeed, gears,hdf));
//						log.info("!!!!!!!!!!###########3"+hdf);	
					}else{
						uhc.setGear_unfit("0");
						uhc.setEgear_run("0");
						uhc.setGear2_start("0");
					}
				}else{
					uhc.setGear_unfit("0");
					uhc.setEgear_run("0");
					uhc.setGear2_start("0");
				}				
			}else{
				uhc.setRatio(Constant.F4);
				uhc.setGears(Constant.F4);
				uhc.setGear_unfit("0");
				uhc.setEgear_run("0");
				uhc.setGear2_start("0");
			}	
			//超转与否
			VehicleBean vb = VehicleCacheManager.getInstance().getValue(uhc.getTerminalId());
			if(vb!=null&&vb.getStandard_rotate()!=null&&!vb.getStandard_rotate().equals("")){
				if(!vengine_speed.equals(Constant.F4)){
					String standard_rotate = vb.getStandard_rotate();
					if(Double.valueOf(vengine_speed)>Double.parseDouble(standard_rotate)){
						uhc.setOver_engine_speed("1");
					}else{
						uhc.setOver_engine_speed("0");
					}
				}else{
					uhc.setOver_engine_speed("0");
				}
			}else{
				uhc.setOver_engine_speed("0");
			}
			
			log.debug("原始串:"+new String(bytevalue));
			Map map = SendCmdService.getCacheMap(CommandCode.down2006_25, uhc);
			log.debug(NAME+map+"");
			if(map!=null&&map.size()>0){
				String start_time = (String) map.get("start_time");
				log.debug(NAME+start_time+"");
				if(start_time!=null&&!start_time.equals("")){
					if(uhc.getTime().compareTo(start_time)>0){
						log.debug("msg_id="+uhc.getMsg_id()+",vehicle_vin="+uhc.getTerminalId()+",time="+uhc.getTime()+",vehicle_distance="+uhc.getVehicle_distance()+",vehicle_speed="+uhc.getVehicle_speed()+",engine_speed="+uhc.getEngine_speed()
								+",engine_torque="+uhc.getEngine_torque()+",engine_coolant_temperature="+uhc.getEngine_coolant_temperature()+",throttle_position="+uhc.getThrottle_position()
								+",flowRate="+uhc.getFlowrate()+",totalComsumption="+uhc.getTotalconsumption()+",latitude="+uhc.getYtb_latitude()
								+",longitude="+uhc.getYtb_longitude()+",altitude="+uhc.getYtb_altitude()+",speed="+uhc.getSpeed()+",week="+uhc.getWeek()+",time_of_week="+uhc.getTime_of_week());
						//拼装宇通杯秒数据,1秒数据的入库语句
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertYTB(uhc));
					}else{
						log.info(NAME+"该时间数据"+uhc.getTime()+"已存在");
					}
				}else{
					log.info(NAME+start_time+"无效，不处理");
				}
			}else{
				log.info(NAME+map+"未找到，不处理");
			}
		}else{
			if(uhc.getTime()!=null&&!uhc.getTime().equals("")){
				uhc.setTime(DateUtil.getDateByDiscreSecondWithNow(uhc.getTime(), 1));
				log.info(NAME+uhc.getTime()+"数据无效:"+new String(bytevalue)+"，不对其操作");
			}
		}
		return location;
	}
}
