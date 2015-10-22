package com.neusoft.SchoolBus.vncs.value;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.SchoolBus.vncs.domain.EnterPriseBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsVTBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.manage.SendxcmsmCommandManager;
import com.neusoft.SchoolBus.vncs.manage.SmsOrderManager;
import com.neusoft.SchoolBus.vncs.service.XCBuildSQL;
import com.neusoft.SchoolBus.vncs.service.XCVWBuildSQL;
import com.neusoft.SchoolBus.vncs.thread.SendRouteFile;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.exception.ParseException;
import com.neusoft.clw.info.bean.SendLineReq;
import com.neusoft.clw.info.bean.SendReq;
import com.neusoft.clw.info.bean.SmsData;
import com.neusoft.clw.info.dao.SendCommandDAO;
import com.neusoft.clw.info.exception.DBErrorException;
import com.neusoft.clw.info.exception.SendInfoException;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.buffer.insert.InsertBuffer;
import com.neusoft.clw.vncs.cachemanager.TerminalCacheManager;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.clw.vncs.inside.msg.content.ContentParamName;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.inside.msg.utils.InsideMsgUtils;
import com.neusoft.clw.vncs.service.CachePattern;
import com.neusoft.clw.vncs.service.SendCmdService;
import com.neusoft.clw.vncs.util.Converser;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.parents.algorithm.manager.AlgorithmUtil;
import com.neusoft.parents.bean.CacheItem;
import com.neusoft.parents.bean.Site;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.cachemanager.RuleCacheManager;
import com.neusoft.parents.cachemanager.StuSiteNoteCacheManager;
import com.neusoft.parents.cachemanager.VehicleRealCacheManager;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.pushBuffer.BasicObject;
import com.neusoft.parents.pushBuffer.PushBuffer;
import com.neusoft.parents.service.ParentsBuildSQL;
import com.neusoft.parents.sitenotemanager.SiteNoteManager;
import com.neusoft.parents.utils.CacheUtils;
import com.neusoft.smsplatform.message.buffer.send.SendBuffer;
import com.neusoft.smsplatform.message.inside.msg.req.SendMessageReq;
import com.neusoft.tqcpt.service.TqcStatisticSQL;
import com.neusoft.tqcpt.util.CDate;
import com.neusoft.parents.dao.impl.ParentsDAO;

public class Up_XCValue {
	private static final Logger log = LoggerFactory.getLogger(Up_XCValue.class);
	private static final Long round = 180000L;
	private static final Long timeDiffence  = 120000L;
	private static SimpleDateFormat sdfH = new SimpleDateFormat("yyMMddHHmmss");
	private static SimpleDateFormat sdfH1 = new SimpleDateFormat("HH:mm:ss");
	private static SimpleDateFormat sdfH2 = new SimpleDateFormat("yyMMddHH:mm");
	private static SimpleDateFormat sdfH3 = new SimpleDateFormat("HHmmss");
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final String NAME = "<Up_XCValue>";
	public static Map<String, VehicleReal> infoMap;
	


	/***
	 * 解析私有协议消息体部分， 例如：00000084001100009375LZYTFTB22C1026162
	 * 0000003600041900010016800204000001CE01022B3CBC07276CB5130121074929
	 * 解析此部分010016800204000001CE01022B3CBC07276CB5130121074929 私有协议中协议号为80
	 * 最新协议版本为02
	 */
	public static void getUp_XCContent(int location, byte[] buf,
			Up_InfoContent uhc) throws Exception {

		location += ContentParamName.REGULARLEN;
		int bytelen01 = Integer.parseInt(new String(buf, location, ContentParamName.REGULARLEN * 2), 16);
		location += ContentParamName.REGULARLEN * 2;

		int len = ContentParamName.getWhole_len(buf);
		int neironglen = len - location;
		int loc = 0;
		while (location < len) {
			byte[] bytevalue01 = new byte[bytelen01];
			System.arraycopy(buf, location, bytevalue01, 0, bytelen01);
			location += bytelen01;

			byte[] bytetype = new byte[1];
			System.arraycopy(bytevalue01, loc, bytetype, 0, bytetype.length);
			loc += bytetype.length;

			String type = Converser.bytesToHexString(bytetype);
			bytetype = null;
			if (type.equals("80")) {

				byte[] versionbyte = new byte[1];
				System.arraycopy(bytevalue01, loc, versionbyte, 0, 1);
				loc += 1;
				String version = Converser.bytesToHexString(versionbyte);
				versionbyte = null;
				uhc.setModelversion(version);
				// 判断协议版本(01、02)
				if ("01".equals(version)) {
					byte[] cmdbyte = new byte[1];
					System.arraycopy(bytevalue01, loc, cmdbyte, 0, 1);
					loc += 1;
					String cmd = Converser.bytesToHexString(cmdbyte);

					byte[] bytevalue = new byte[bytevalue01.length - 3];
					System.arraycopy(bytevalue01, loc, bytevalue, 0,
							bytevalue.length);
					loc += bytevalue.length;

					setUpXCContent(cmd, bytevalue, uhc, neironglen - 3);
				} else {
					byte[] cmdbyte = new byte[1];
					System.arraycopy(bytevalue01, loc, cmdbyte, 0, 1);
					loc += 1;
					String cmd = Converser.bytesToHexString(cmdbyte);

					byte[] bytevalue = new byte[bytevalue01.length - 3];
					System.arraycopy(bytevalue01, loc, bytevalue, 0, bytevalue.length);
					loc += bytevalue.length;
					setUpXCContentVersionTwo(cmd, bytevalue, uhc,neironglen - 3);
				}
			} else if (type.equals("F3")) {
				setUpExceptionType(bytevalue01, uhc, loc, bytelen01);
			} else {
				return;
			}

		}

	}

	private static void setUpExceptionType(byte[] bytevalue01,
			Up_InfoContent uhc, int loc, int bytelen01) throws Exception {
		log.info(NAME + "终端异常事件汇报处理开始");
		byte[] versionbyte = new byte[1];
		System.arraycopy(bytevalue01, loc, versionbyte, 0, 1);
		loc += 1;

		byte[] cmdbyte = new byte[1];
		System.arraycopy(bytevalue01, loc, cmdbyte, 0, 1);
		loc += 1;
		String cmd = Converser.bytesToHexString(cmdbyte);

		if (cmd.equals("01")) {
			byte[] ex_idbyte = new byte[2];
			System.arraycopy(bytevalue01, loc, ex_idbyte, 0, 2);
			loc += 2;

			// 保留
			loc += 2;

			// 事件发生时间
			byte[] timebyte = new byte[6];
			System.arraycopy(bytevalue01, loc, timebyte, 0, 6);
			loc += 6;

			// 事件状态
			byte[] ex_statebyte = new byte[1];
			System.arraycopy(bytevalue01, loc, ex_statebyte, 0, 1);
			loc += 1;

			// 保留
			loc += 1;

			byte[] desclenbyte = new byte[2];
			System.arraycopy(bytevalue01, loc, desclenbyte, 0, 2);
			loc += 2;
			int desclen = Integer.parseInt(
					Converser.bytesToHexString(desclenbyte), 16);

			// 事件描述
			byte[] descbyte = new byte[desclen];
			System.arraycopy(bytevalue01, loc, descbyte, 0, desclen);
			loc += desclen;

			if (loc != bytelen01) {
				throw new Exception("报文长度错误！");
			}

			uhc.setEx_id(Converser.bytesToHexString(ex_idbyte));
			uhc.setEx_time(Converser.bytesToHexString(timebyte));
			uhc.setEx_state(Converser.bytesToHexString(ex_statebyte));
			uhc.setEx_desc(new String(descbyte));

			DBBuffer.getInstance().add(
					XCBuildSQL.getInstance().insertExceptionType(uhc));
		}
		log.info(NAME + "终端异常事件汇报处理结束");
	}

	/**
	 * 此方法为解析协议版本为01的上行透传消息
	 * 
	 * @param cmd
	 *            私有协议中对应的消息id
	 * @param bytevalue
	 *            私有协议的字节流
	 * @param uhc
	 *            存储解析后数据的bean
	 * @param len
	 *            字节流bytevalue的长度
	 * @throws ParseException
	 * @throws java.text.ParseException
	 * @throws UnsupportedEncodingException
	 * @throws SendInfoException
	 * @throws DBErrorException
	 */
	public static void setUpXCContent(String cmd, byte[] bytevalue,
			Up_InfoContent uhc, int len) throws ParseException,
			java.text.ParseException, UnsupportedEncodingException,
			SendInfoException, DBErrorException {

		// 终端版本号
		if (cmd.equals(ContentParamName.packet_content01)) {
			log.info(NAME + "终端版本号记录处理开始");
			int le = setXcTerData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析终端版本号记录时发生异常");
				throw new ParseException("解析终端版本号记录时发生异常");
			}
			log.info(NAME + "终端版本号处理结束");
		}

		// 学生刷卡记录
		if (cmd.equals(ContentParamName.packet_content02)) {
			log.info(NAME + "学生刷卡记录处理开始");
			int le = setShuaKaData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析学生刷卡记录时发生异常");
				throw new ParseException("解析学生刷卡记录时发生异常");
			}
			log.info(NAME + "学生刷卡记录处理结束");
		}

		// 学生未刷卡记录
		if (cmd.equals(ContentParamName.packet_content03)) {
			log.info(NAME + "学生未刷卡记录处理开始");
			int le = setNotShuaKaData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析学生未刷卡记录时发生异常");
				throw new ParseException("解析学生未刷卡记录时发生异常");
			}

			log.info(NAME + "学生未刷卡记录处理结束");
		}

		// 司机刷卡记录
		if (cmd.equals(ContentParamName.packet_content04)) {
			log.info(NAME + "司机刷卡记录处理开始");
			int le = DriverShuaKaData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析司机刷卡记录时发生异常");
				throw new ParseException("解析司机刷卡记录时发生异常");
			}
			log.info(NAME + "司机刷卡记录处理结束");
		}

		// 司乘刷卡记录
		if (cmd.equals(ContentParamName.packet_content05)) {
			log.info(NAME + "司乘刷卡记录处理开始");
			int le = SiChengShuaKaData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析司乘刷卡记录时发生异常");
				throw new ParseException("解析司乘刷卡记录时发生异常");
			}
			log.info(NAME + "司乘刷卡记录处理结束");
		}

		// 打卡自动销假记录
		if (cmd.equals(ContentParamName.packet_content09)) {
			log.info(NAME + "刷卡自动销假记录处理开始");
			int le = XiaoJiaData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析刷卡自动销假记录时发生异常");
				throw new ParseException("解析刷卡自动销假记录时发生异常");
			}
			log.info(NAME + "刷卡自动销假记录处理结束");
		}

		// 超载告警记录
		if (cmd.equalsIgnoreCase(ContentParamName.packet_content0a)) {
			log.info(NAME + "超载检测告警记录处理开始");
			int le = AlarmData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析超载检测告警记录时发生异常");
				throw new ParseException("解析超载检测告警记录时发生异常");
			}
			log.info(NAME + "超载检测告警记录处理结束");
		}

		// 校车进出站记录
		if (cmd.equalsIgnoreCase(ContentParamName.packet_content0b)) {
			log.info(NAME + "校车进出站记录处理开始");
			int le = setInOutData(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析校车进出站记录时发生异常");
				throw new ParseException("解析校车进出站记录时发生异常");
			}
			log.info(NAME + "校车进出站记录处理结束");
		}

		// 采集路线更新查询
		if (cmd.equalsIgnoreCase(ContentParamName.packet_content0c)) {
			boolean routeFileVersionOne = false;
			if (routeFileVersionOne) {
				log.info(NAME + "路线更新查询记录处理开始");
				try {
					searchRouteFiles(uhc, bytevalue);
				} catch (SendInfoException e) {
					throw new SendInfoException("发送m2m时发生异常：" + e.getMessage());
				} catch (DBErrorException e) {
					throw new DBErrorException("入库时发生异常：" + e.getMessage());
				}
				log.info(NAME + "路线更新查询记录处理结束");
			}
			log.info(NAME + "此协议已过期，不进行解析！");
		}

		// 采集站点经纬度记录
		if (cmd.equalsIgnoreCase(ContentParamName.packet_content0d)) {
			log.info(NAME + "采集站点经纬度记录处理开始");
			int le = getRoute(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析采集站点经纬度记录时发生异常");
				throw new ParseException("解析采集站点经纬度记录时发生异常");
			}
			log.info(NAME + "采集站点经纬度记录处理结束");
		}
	}	

	/**
	 * 解析车辆终端记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static int setXcTerData(Up_InfoContent uhc, byte[] bytevalue)
			throws UnsupportedEncodingException {
		int location = 0;
		// 记录仪版本号长度
		byte[] xc_ter_jilu_verlenbyte = new byte[1];
		System.arraycopy(bytevalue, location, xc_ter_jilu_verlenbyte, 0, 1);
		location += 1;
		// GPS记录仪版本号
		int jilulen = Integer.parseInt(
				Converser.bytesToHexString(xc_ter_jilu_verlenbyte), 16);
		xc_ter_jilu_verlenbyte = null;
		byte[] xc_ter_jilu_verbyte = new byte[jilulen];
		System.arraycopy(bytevalue, location, xc_ter_jilu_verbyte, 0, jilulen);
		location += jilulen;
		String xc_ter_jilu_ver = new String(xc_ter_jilu_verbyte, "GBK");
		xc_ter_jilu_verbyte = null;

		// 显示器版本号长度
		byte[] xc_ter_xianshi_verlen = new byte[1];
		System.arraycopy(bytevalue, location, xc_ter_xianshi_verlen, 0, 1);
		location += 1;
		// 显示器版本号
		int xianshilen = Integer.parseInt(
				Converser.bytesToHexString(xc_ter_xianshi_verlen), 16);
		xc_ter_xianshi_verlen = null;

		byte[] xc_ter_xianshi_verbyte = new byte[xianshilen];
		System.arraycopy(bytevalue, location, xc_ter_xianshi_verbyte, 0,
				xianshilen);
		location += xianshilen;
		String xc_ter_xianshi_ver = new String(xc_ter_xianshi_verbyte, "GBK");
		xc_ter_xianshi_verbyte = null;

		// DVR版本号长度
		byte[] xc_ter_dvr_verlen = new byte[1];
		System.arraycopy(bytevalue, location, xc_ter_dvr_verlen, 0, 1);
		location += 1;

		// DVR版本号
		int drvlen = Integer.parseInt(
				Converser.bytesToHexString(xc_ter_dvr_verlen), 16);
		xc_ter_dvr_verlen = null;

		byte[] xc_ter_dvr_verbyte = new byte[drvlen];
		System.arraycopy(bytevalue, location, xc_ter_dvr_verbyte, 0, drvlen);
		location += drvlen;
		String xc_ter_dvr_ver = new String(xc_ter_dvr_verbyte, "GBK");
		xc_ter_dvr_verbyte = null;

		// 射频读卡器版本号长度
		byte[] xc_ter_shepin_verlen = new byte[1];
		System.arraycopy(bytevalue, location, xc_ter_shepin_verlen, 0, 1);
		location += 1;
		// DVR版本号
		int shepinlen = Integer.parseInt(
				Converser.bytesToHexString(xc_ter_shepin_verlen), 16);
		xc_ter_shepin_verlen = null;

		byte[] xc_ter_shepin_verbyte = new byte[shepinlen];
		System.arraycopy(bytevalue, location, xc_ter_shepin_verbyte, 0,
				shepinlen);
		location += shepinlen;
		String xc_ter_shepin_ver = new String(xc_ter_shepin_verbyte, "GBK");
		xc_ter_shepin_verbyte = null;

		// 其他版本号长度
		byte[] xc_ter_qita_verlen = new byte[1];
		System.arraycopy(bytevalue, location, xc_ter_qita_verlen, 0, 1);
		location += 1;
		// 其他版本号
		int qitalen = Integer.parseInt(
				Converser.bytesToHexString(xc_ter_qita_verlen), 16);
		xc_ter_qita_verlen = null;

		byte[] xc_ter_qita_verbyte = new byte[qitalen];
		System.arraycopy(bytevalue, location, xc_ter_qita_verbyte, 0, qitalen);
		location += qitalen;
		String xc_ter_qita_ver = new String(xc_ter_qita_verbyte, "GBK");
		xc_ter_qita_verbyte = null;

		uhc.setTer_jilu_ver(xc_ter_jilu_ver);
		uhc.setTer_xianshi_ver(xc_ter_xianshi_ver);
		uhc.setTer_dvr_ver(xc_ter_dvr_ver);
		uhc.setTer_shepin_ver(xc_ter_shepin_ver);
		uhc.setTer_qita_ver(xc_ter_qita_ver);
		InsertBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildUpdateTerminalSql(uhc));
		log.info(NAME + "终端版本号流程处理成功");
		xc_ter_jilu_ver = null;
		xc_ter_xianshi_ver = null;
		xc_ter_dvr_ver = null;
		xc_ter_shepin_ver = null;
		xc_ter_qita_ver = null;
		return location;
	}

	/**
	 * 解析学生刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	
	public static void main(String[] args) {
//		System.out.println(Converser.hexTo2BCD("AB3C"));
//		System.out.println("1234".substring(0,1));
		String timeL = "140105144800";
		String timeM = "140105095555";
		SimpleDateFormat wewe = new SimpleDateFormat("yyMMddHHmmss");
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyMMddHHmmss");//设置日期格式
			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

			if(sdfH.parse(df.format(new Date())).getTime()-sdfH.parse(timeL).getTime()<=timeDiffence)
			{
				Long hile = sdfH.parse(df.format(new Date())).getTime()-sdfH.parse(timeL).getTime();
				System.out.println("11111"+hile);
			}
			else
			{
				Long hile = sdfH.parse(timeL).getTime()-sdfH.parse(df.format(new Date())).getTime();
				System.out.println("22222"+hile);
			}
		} catch (java.text.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static int setShuaKaData(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		// ID主键
		String shuaka_id = IdCreater.getUUid();
		String pid = IdCreater.getUUid();
		// 学生编号
		byte[] stu_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, stu_idbyte, 0, 4);
		location += 4;
		String stu_id = Converser.bytesToHexString(stu_idbyte);

		// 当班司机编号
		byte[] driver_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, driver_idbyte, 0, 4);
		location += 4;
		String driver_id = Converser.bytesToHexString(driver_idbyte);

		// 当班司乘编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);

		// 站点编号
		byte[] site_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, site_idbyte, 0, 4);
		location += 4;
		String site_id = Converser.bytesToHexString(site_idbyte);

		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);

		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);

		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);

		// 行为状态
		byte[] site_flagbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_flagbyte, 0, 1);
		location += 1;
		String site_flagState = Converser.bytesToHexString(site_flagbyte);

		if (site_flagState.equals("00")) {
			uhc.setSite_flag("0");
			uhc.setVss_flag("0");
			uhc.setSms_eventtype("4");
		} else if (site_flagState.equals("01")) {
			uhc.setSite_flag("0");
			uhc.setVss_flag("1");
			uhc.setSms_eventtype("5");
		} else if (site_flagState.equals("02")) {
			uhc.setSite_flag("1");
			uhc.setVss_flag("0");
			uhc.setSms_eventtype("4");
		} else if (site_flagState.equals("03")) {
			uhc.setSite_flag("1");
			uhc.setVss_flag("1");
			uhc.setSms_eventtype("5");
		}

		// 短信状态标识
		uhc.setSmstype(site_flagState);

		// 异常上下车状态
		byte[] alarm_typebyte = new byte[1];
		System.arraycopy(bytevalue, location, alarm_typebyte, 0, 1);
		location += 1;
		String alarm_type = Converser.bytesToHexString(alarm_typebyte);
		alarm_type = Converser.hexTo2BCD(alarm_type);

		if (!alarm_type.equals("") && alarm_type != null) {
			if (alarm_type.substring(7, 8).equals("1")) {
				uhc.setAlarm_id("73");
				uhc.setSms_eventtype("2");
			}
			if (alarm_type.substring(6, 7).equals("1")) {
				uhc.setAlarm_id("74");
				uhc.setSms_eventtype("3");
			}
		} else {
			uhc.setAlarm_id("");
		}

		// 下车学生数量
		byte[] st_down_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_down_numbyte, 0, 1);
		location += 1;
		String st_down_num = Converser.bytesToHexString(st_down_numbyte);

		// 应下车学生数量
		byte[] st_institute_down_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_institute_down_numbyte, 0, 1);
		location += 1;
		String st_institute_down_num = Converser
				.bytesToHexString(st_institute_down_numbyte);

		// 上车学生数量
		byte[] st_up_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_up_numbyte, 0, 1);
		location += 1;
		String st_up_num = Converser.bytesToHexString(st_up_numbyte);

		// 应上车学生数量
		byte[] setSt_institute_up_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, setSt_institute_up_numbyte, 0, 1);
		location += 1;
		String st_institute_up_num = Converser
				.bytesToHexString(setSt_institute_up_numbyte);

		// 当前学生数量
		byte[] st_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_numbyte, 0, 1);
		location += 1;
		String st_num = Converser.bytesToHexString(st_numbyte);

		// 刷卡时间
		byte[] shuaka_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shuaka_timebyte, 0, 6);
		location += 6;
		String shuaka_time = Converser.bytesToHexString(shuaka_timebyte);

		uhc.setPid(pid);
		uhc.setShuaka_id(shuaka_id);
		uhc.setStu_id(String.valueOf(Long.parseLong(stu_id, 16)));
		if (!site_id.equals("FFFFFFFF")) {
			uhc.setSite_id(String.valueOf(Long.parseLong(site_id, 16)));
		} else {
			uhc.setSite_id("-1");
		}
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		if (!driver_id.equals("FFFFFFFF")) {
			uhc.setDriver_id(String.valueOf(Long.parseLong(driver_id, 16)));
		} else {
			uhc.setDriver_id("");
		}
		if (!sicheng_crd_id.equals("FFFFFFFF")) {
			uhc.setSicheng_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		} else {
			uhc.setSicheng_id("");
		}
		uhc.setSt_down_num(String.valueOf(Integer.parseInt(st_down_num, 16)));
		uhc.setSt_institute_down_num(String.valueOf(Integer.parseInt(
				st_institute_down_num, 16)));
		uhc.setSt_up_num(String.valueOf(Integer.parseInt(st_up_num, 16)));
		uhc.setSt_institute_up_num(String.valueOf(Integer.parseInt(
				st_institute_up_num, 16)));
		uhc.setSt_num(String.valueOf(Integer.parseInt(st_num, 16)));
		uhc.setTerminal_time(shuaka_time);
		setCacheShuakaTerminal(uhc);

		log.info(NAME + "学生刷卡流程处理成功");
		// 操作学生刷卡实时表
		DBBuffer.getInstance()
				.add(XCBuildSQL.getInstance().buildShiShiSql(uhc));
		// 短信下发
		try {
			CallSmsGatewayInterface(uhc);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(NAME + ",短信发送错误");
		}
		InsertBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildInsertShuaKaSql(uhc));
		return location;
	}

	/**
	 * 解析学生未刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int setNotShuaKaData(Up_InfoContent uhc, byte[] bytevalue) {
		String pici = IdCreater.getUUid();
		int location = 0;
		// 刷卡流水表主键
		String downshuaka_id = IdCreater.getUUid();
		// uhc.setShuaka_id(shuaka_id);
		// 站点编号
		byte[] site_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, site_idbyte, 0, 4);
		location += 4;
		String site_id = Converser.bytesToHexString(site_idbyte);
		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);

		// 当班司机编号
		byte[] driver_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, driver_idbyte, 0, 4);
		location += 4;
		String driver_id = Converser.bytesToHexString(driver_idbyte);

		// 当班司乘编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);

		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);

		// 上报时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);

		uhc.setDownshuaka_id(downshuaka_id);
		uhc.setSite_id(String.valueOf(Long.parseLong(site_id, 16)));
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}

		if (!driver_id.equals("FFFFFFFF")) {
			uhc.setDriver_id(String.valueOf(Long.parseLong(driver_id, 16)));
		} else {
			uhc.setDriver_id("");
		}

		if (!sicheng_crd_id.equals("FFFFFFFF")) {
			uhc.setSicheng_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		} else {
			uhc.setSicheng_id("");
		}
		uhc.setTerminal_time(shangbao_time);

		// 上车未刷卡人数
		byte[] up_not_shuaka_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, up_not_shuaka_numbyte, 0, 1);
		location += 1;
		String st_up_num = Converser.bytesToHexString(up_not_shuaka_numbyte);
		int upnumlen = Integer.parseInt(st_up_num, 16);

		if (upnumlen != 0) {
			// 上车未刷卡学生编号
			String[] str = new String[upnumlen];
			for (int i = 0; i < upnumlen; i++) {
				String pid = IdCreater.getUUid();
				byte[] up_not_shuaka_codebyte = new byte[4];
				System.arraycopy(bytevalue, location, up_not_shuaka_codebyte,
						0, 4);
				location += 4;
				String up_not_shuaka_code = Converser
						.bytesToHexString(up_not_shuaka_codebyte);
				uhc.setStu_id(String.valueOf(Long.parseLong(up_not_shuaka_code,
						16)));
				uhc.setAlarm_id("79");
				uhc.setSms_eventtype("0");
				uhc.setPid(pid);
				str[i] = uhc.getStu_id();

				DBBuffer.getInstance().add(
						XCBuildSQL.getInstance().UpNobuildShiShiSql(uhc));

				// 短信下发
				try {
					CallSmsGatewayInterface(uhc);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(NAME + ",上车未刷卡发送短信错误");
				}
				InsertBuffer.getInstance().add(
						XCBuildSQL.getInstance().buildInsertNotShuaKaUpSql(uhc,
								pici));
			}
		}
		// 下车未刷卡人数
		byte[] st_down_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_down_numbyte, 0, 1);
		location += 1;
		String st_down_num = Converser.bytesToHexString(st_down_numbyte);
		int downnumlen = Integer.parseInt(st_down_num, 16);
		if (downnumlen != 0) {
			// 下车未刷卡学生编号
			String[] str = new String[downnumlen];
			for (int i = 0; i < downnumlen; i++) {
				String pid = IdCreater.getUUid();
				byte[] down_not_shuaka_codebyte = new byte[4];
				System.arraycopy(bytevalue, location, down_not_shuaka_codebyte,
						0, 4);
				location += 4;
				String up_down_shuaka_code = Converser
						.bytesToHexString(down_not_shuaka_codebyte);
				uhc.setStu_id(String.valueOf(Long.parseLong(
						up_down_shuaka_code, 16)));
				str[i] = uhc.getStu_id();
				uhc.setAlarm_id("80");
				uhc.setSms_eventtype("1");
				uhc.setPid(pid);

				DBBuffer.getInstance().add(
						XCBuildSQL.getInstance().DownNobuildShiShiSql(uhc));
				// 短信下发
				try {
					CallSmsGatewayInterface(uhc);
				} catch (Exception e) {
					e.printStackTrace();
					log.error(NAME + ",下车未刷卡发送短信错误");
				}
				InsertBuffer.getInstance().add(
						XCBuildSQL.getInstance().buildInsertNotShuaKaDownSql(
								uhc, pici));
			}
		}
		log.info(NAME + "学生未刷卡流程处理成功");
		return location;
	}

	/**
	 * 解析司机刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */

	private static int DriverShuaKaData(Up_InfoContent uhc, byte[] bytevalue)
			throws java.text.ParseException {
		int location = 0;
		// 司机编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);

		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);
		// 行为状态
		byte[] site_flagbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_flagbyte, 0, 1);
		location += 1;
		String site_flag = Converser.bytesToHexString(site_flagbyte);
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 上报时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);
		if ("00".equals(site_flag) || "02".equals(site_flag)) {
			uhc.setSite_flag("0");
		} else {
			uhc.setSite_flag("1");
		}
		String sicheng_id = IdCreater.getUUid();

		uhc.setSicheng_id(sicheng_id);
		uhc.setOther_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		uhc.setTerminal_time(shangbao_time);
		InsertBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildInsertSISCCRDSql(uhc));
		log.info(NAME + "司机刷卡流程处理成功");
		if ("0".equals(uhc.getSite_flag())) {
			log.info(NAME + "司机编号为" + uhc.getOther_id());
			DBBuffer.getInstance().add(
					XCBuildSQL.getInstance()
							.buildUpdateUpTerminalDriverSql(uhc));
		} else {
			DBBuffer.getInstance().add(
					XCBuildSQL.getInstance().buildUpdateDownTerminalDriverSql(
							uhc));
		}

		if ("00".equals(site_flag) || "02".equals(site_flag)) {
			setCachePattern(uhc);
		} else {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + Constant.DRIVER);
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + Constant.ShuaKaTerminal);
			}
			Constant.ytbsendcmdMap
					.remove(uhc.getTerminalId() + Constant.DRIVER);
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ Constant.ShuaKaTerminal);
		}

		return location;
	}

	/**
	 * 解析司乘刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */

	private static int SiChengShuaKaData(Up_InfoContent uhc, byte[] bytevalue)
			throws java.text.ParseException {
		int location = 0;
		// 司乘编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);

		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);
		// 行为状态
		byte[] site_flagbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_flagbyte, 0, 1);
		location += 1;
		String site_flag = Converser.bytesToHexString(site_flagbyte);
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 上报时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);
		if ("00".equals(site_flag) || "02".equals(site_flag)) {
			uhc.setSite_flag("0");
		} else {
			uhc.setSite_flag("1");
		}
		String sicheng_id = IdCreater.getUUid();
		uhc.setSicheng_id(sicheng_id);
		uhc.setOther_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		// uhc.setSite_flag(ContentParamName.checkLength(site_flag,ContentParamName.SITE_FLAGLEN));
		uhc.setTerminal_time(shangbao_time);

		InsertBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildInsertSISCCRDSql(uhc));
		log.info(NAME + "司乘刷卡记录流程处理成功");

		return location;
	}

	/**
	 * 解析刷卡自动销假记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int XiaoJiaData(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);

		// 销假学生编号
		byte[] back_stu_codebyte = new byte[4];
		System.arraycopy(bytevalue, location, back_stu_codebyte, 0, 4);
		location += 4;
		String back_stu_code = Converser.bytesToHexString(back_stu_codebyte);

		// 销假时间
		byte[] back_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, back_timebyte, 0, 6);
		location += 6;
		String back_time = Converser.bytesToHexString(back_timebyte);
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		uhc.setStu_id(String.valueOf(Long.parseLong(back_stu_code, 16)));
		uhc.setTerminal_time(back_time);
		DBBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildUpdateXiaoJiaSql(uhc));
		log.info(NAME + "刷卡自动销假记录流程处理成功");

		return location;
	}

	/**
	 * 解析车辆超载报警记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */
	private static int AlarmData(Up_InfoContent uhc, byte[] bytevalue)
			throws java.text.ParseException {
		int location = 0;
		String alarm_id = IdCreater.getUUid();
		// 告警状态 0x01为告警，0x00告警取消
		byte[] alarm_statebyte = new byte[1];
		System.arraycopy(bytevalue, location, alarm_statebyte, 0, 1);
		location += 1;
		String alarm_state = Converser.bytesToHexString(alarm_statebyte);
		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);
		// 当前人数
		byte[] now_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, now_numbyte, 0, 1);
		location += 1;
		String now_num = Converser.bytesToHexString(now_numbyte);
		// 核载人数
		byte[] hezai_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, hezai_numbyte, 0, 1);
		location += 1;
		String hezai_num = Converser.bytesToHexString(hezai_numbyte);
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 上报时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);
		uhc.setAlarm_id(alarm_id);
		if (alarm_state.equals("00")) {
			uhc.setAlrm_state("0");
		} else {
			uhc.setAlrm_state("1");
		}
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		uhc.setSt_num(String.valueOf(Integer.parseInt(now_num, 16)));
		uhc.setHezai_id(String.valueOf(Integer.parseInt(hezai_num, 16)));
		uhc.setTerminal_time(shangbao_time);
		// DBBuffer.getInstance().add(XCBuildSQL.getInstance().buildInsertChaoZaiSql(uhc));
		CachePattern.getInstance().setAlarmMemCache(uhc, alarm_id, "72",
				uhc.getAlrm_state(), "");
		setCacheShuakaTerminal(uhc);

		DBBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildUpdateTerminalNumSql(uhc));
		log.info(NAME + "超载告警流程处理成功");
		return location;
	}

	/**
	 * 解析车辆进出站记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */
	private static int setInOutData(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		String inout_id = IdCreater.getUUid();
		// 站点编号
		byte[] site_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, site_idbyte, 0, 4);
		location += 4;
		String site_id = Converser.bytesToHexString(site_idbyte);
		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);

		// 当班司机编号
		byte[] driver_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, driver_idbyte, 0, 4);
		location += 4;
		String driver_id = Converser.bytesToHexString(driver_idbyte);

		// 当班司乘编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);

		// 上放学状态，0x00为上学，0x01为放学
		byte[] site_updownbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_updownbyte, 0, 1);
		location += 1;
		String site_updown = Converser.bytesToHexString(site_updownbyte);

		// 进出站状态 0x00为进站，0x01为出站
		byte[] inout_statebyte = new byte[1];
		System.arraycopy(bytevalue, location, inout_statebyte, 0, 1);
		location += 1;
		String inout_state = Converser.bytesToHexString(inout_statebyte);
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 实际进站时间
		byte[] in_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, in_timebyte, 0, 6);
		location += 6;
		String in_time = Converser.bytesToHexString(in_timebyte);
		// 应进站时间
		byte[] plan_in_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, plan_in_timebyte, 0, 6);
		location += 6;
		String plan_in_time = Converser.bytesToHexString(plan_in_timebyte);
		if (inout_state.equals("01")) {
			// 实际出站时间
			byte[] out_timebyte = new byte[6];
			System.arraycopy(bytevalue, location, out_timebyte, 0, 6);
			location += 6;
			String out_time = Converser.bytesToHexString(out_timebyte);
			// 应出站时间
			byte[] plan_out_timebyte = new byte[6];
			System.arraycopy(bytevalue, location, plan_out_timebyte, 0, 6);
			location += 6;
			String plan_out_time = Converser
					.bytesToHexString(plan_out_timebyte);

			// 上车学生数量
			byte[] st_up_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_up_numbyte, 0, 1);
			location += 1;
			String st_up_num = Converser.bytesToHexString(st_up_numbyte);

			// 应上车学生数量
			byte[] setSt_institute_up_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, setSt_institute_up_numbyte,
					0, 1);
			location += 1;
			String st_institute_up_num = Converser
					.bytesToHexString(setSt_institute_up_numbyte);

			// 下车学生数量
			byte[] st_down_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_down_numbyte, 0, 1);
			location += 1;
			String st_down_num = Converser.bytesToHexString(st_down_numbyte);

			// 应下车学生数量
			byte[] st_institute_down_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_institute_down_numbyte, 0,
					1);
			location += 1;
			String st_institute_down_num = Converser
					.bytesToHexString(st_institute_down_numbyte);

			// 当前学生数量
			byte[] st_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_numbyte, 0, 1);
			location += 1;
			String st_num = Converser.bytesToHexString(st_numbyte);

			uhc.setOut_time(out_time);
			uhc.setPlan_out_time(plan_out_time);
			uhc.setSt_down_num(String.valueOf(Integer.parseInt(st_down_num, 16)));
			uhc.setSt_institute_down_num(String.valueOf(Integer.parseInt(
					st_institute_down_num, 16)));
			uhc.setSt_up_num(String.valueOf(Integer.parseInt(st_up_num, 16)));
			uhc.setSt_institute_up_num(String.valueOf(Integer.parseInt(
					st_institute_up_num, 16)));
			uhc.setSt_num(String.valueOf(Integer.parseInt(st_num, 16)));

		}

		uhc.setInout_id(inout_id);
		if (inout_state.equals("00")) {
			uhc.setInout_state("0");
		} else if (inout_state.equals("01")) {
			uhc.setInout_state("1");
		}
		if (site_updown.equals("00")) {
			uhc.setSite_flag("0");

		} else if (site_updown.equals("01")) {
			uhc.setSite_flag("1");

		}
		uhc.setSite_id(String.valueOf(Long.parseLong(site_id, 16)));
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!driver_id.equals("FFFFFFFF")) {
			uhc.setDriver_id(String.valueOf(Long.parseLong(driver_id, 16)));
		} else {
			uhc.setDriver_id("");
		}
		if (!sicheng_crd_id.equals("FFFFFFFF")) {
			uhc.setSicheng_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		} else {
			uhc.setSicheng_id("");
		}
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		uhc.setIn_time(in_time);
		uhc.setPlan_in_time(plan_in_time);

		log.info(NAME + "校车进出站流程处理成功");
		InsertBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildInsertInOutSql(uhc));
		log.info(NAME + "校车进出站更新终端表流程处理成功");
		return location;
	}

	@SuppressWarnings("unchecked")
	private static void searchRouteFiles(Up_InfoContent uhc, byte[] bytevalue)
			throws UnsupportedEncodingException, SendInfoException,
			DBErrorException {
		String id = IdCreater.getUUid();
		// 接收通知，入库
		TerminalCacheManager tc = TerminalCacheManager.getInstance();
		TerminalBean tb = tc.getValue(uhc.getTerminalId());
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap
				.getInstance().getBean("sendCommandDAO");
		sendCommandDAO.saveQuartzLineCommand(uhc, tb.getSim_card_number(), id);
		String srcini = null;
		String filepath = null;
		if (Config.props.getProperty("iniPath").endsWith("/")) {
			filepath = Config.props.getProperty("iniPath")
					+ uhc.getTerminalId();
		} else {
			filepath = Config.props.getProperty("iniPath") + "/"
					+ uhc.getTerminalId();
		}
		File file = new File(filepath);
		if (file.isFile()) {
			System.out.print("文件 :" + file.getName() + "\t");
		}// 是目录的情况
		else {
			File[] files = file.listFiles();
			for (File fileTemp : files) {
				if (fileTemp.isFile()) {
					if (fileTemp.getName().toLowerCase().endsWith(".ini")) {
						srcini = fileTemp.getName();
						break;
					}
				}
			}
		}
		if (srcini != null) {
			SendLineReq req = new SendLineReq();
			String ip = Config.props.getProperty("xcftpip");
			String port = Config.props.getProperty("xcftpport");
			String xcuser = Config.props.getProperty("xcftpuser");
			String xcpass = Config.props.getProperty("xcftppass");
			req.setCommand("0010");
			req.setStatusCode(SendReq.STATUS);
			req.setSeq(InsideMsgUtils.getSeq());
			req.setMsg_id(id);
			req.setTerminal_id(uhc.getTerminalId());
			req.setIplength(Integer.toHexString(ip.length()));
			req.setIp(ip);
			req.setPort(Integer.toHexString(Integer.parseInt(port)));
			req.setUserlength(Integer.toHexString(xcuser.length()));
			req.setUsername(xcuser);
			req.setPasslength(Integer.toHexString(xcpass.length()));
			req.setUserpass(xcpass);
			req.setFilelength(Integer.toHexString(("/" + uhc.getTerminalId()
					+ "/" + srcini).length()));
			req.setFilename("/" + uhc.getTerminalId() + "/" + srcini);
			req.setCrc(String.valueOf(SendRouteFile.doChecksum(filepath + "/"
					+ srcini)));
			req.setBytetype("80");
			req.setPacket_content(req.getTcToString());
			// 获取文件后，将命令入库
			sendCommandDAO.updateQuartzLine(id, req);
			String url = SendCmdService.cycleSendCommand(req.getBytes());
			if (url != null && !url.equals("")) {
				log.info(NAME + "向" + url + "下发命令成功！");
				Constant.respMap.put(req.getSeq(), id);
				log.debug("Constant.respMap" + Constant.respMap);
			} else {
				log.info(NAME + "平台url未配置,下发命令失败");
				throw new SendInfoException("下发命令失败");
			}
		} else {
			log.info(NAME + " 未找到该车" + uhc.getTerminalId() + "的线路文件");
		}
	}

	private static int getRoute(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		// 站点纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 站点经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);

		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		uhc.setTerminal_time(shangbao_time);
		InsertBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildInsertInGetRouteSql(uhc));
		return location;
	}

	// 将司机刷卡信息放入memcache
	@SuppressWarnings("unchecked")
	public static void setCachePattern(Up_InfoContent uhc) {
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			Constant.getMemcachedClient().insert(
					uhc.getTerminalId() + Constant.DRIVER,
					String.valueOf(Long.parseLong(uhc.getOther_id(), 16)));
			Constant.ytbsendcmdMap.put(uhc.getTerminalId() + Constant.DRIVER,
					String.valueOf(Long.parseLong(uhc.getOther_id(), 16)));
		} else {
			Constant.ytbsendcmdMap.put(uhc.getTerminalId() + Constant.DRIVER,
					String.valueOf(Long.parseLong(uhc.getOther_id(), 16)));
		}
	}

	// 将超载信息放入memcache
	@SuppressWarnings("unchecked")
	public static void setCacheShuakaTerminal(Up_InfoContent uhc) {
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			Constant.getMemcachedClient().insert(
					uhc.getTerminalId() + Constant.ShuaKaTerminal,
					uhc.getSt_num());
			if (uhc.getAlrm_state() != null && !uhc.getAlrm_state().equals("")) {
				Constant.getMemcachedClient().insert(
						uhc.getTerminalId() + Constant.OVERLOAD,
						uhc.getAlrm_state());
			}

		}
		Constant.ytbsendcmdMap.put(uhc.getTerminalId()
				+ Constant.ShuaKaTerminal, uhc.getSt_num());
		if (uhc.getAlrm_state() != null && !uhc.getAlrm_state().equals("")) {
			Constant.ytbsendcmdMap.put(uhc.getTerminalId() + Constant.OVERLOAD,
					uhc.getAlrm_state());
		}
	}

	private static String isSendType(Up_InfoContent urt) {
		String msg = null;
		if (urt.getAlarm_id() == null || "".equals(urt.getAlarm_id())) {
			// 正常上车
			if (urt.getVss_flag().equals("0")) {
				msg = "4";
			}
			// 正常下车
			if (urt.getVss_flag().equals("1")) {
				msg = "5";
			}
		} else {
			// 未刷卡上车
			if (urt.getAlarm_id().equals("79")) {
				msg = "0";
			}
			// 未刷卡下车
			if (urt.getAlarm_id().equals("80")) {
				msg = "1";
			}
			// 未在规定站点上车
			if (urt.getAlarm_id().equals("73")) {
				msg = "2";
			}
			// 未在规定站点下车
			if (urt.getAlarm_id().equals("74")) {
				msg = "3";
			}

		}
		return msg;
	}

	/**
	 * 亿美短信，判断学生是否订购短信业务
	 * 
	 * @param uhc
	 * @param sb
	 * @param
	 * @return
	 * @throws ParseException
	 */

	private static void CallSmsInterface(Up_InfoContent uhc, XcStudentBean sb)
			throws java.text.ParseException {
		String smsg_state = "";// 发送状态
		String cell_failinfo = "";
		String type = isSendType(uhc);// 打卡类型

		List<XcStuSmsBean> vblist = SendxcmsmCommandManager.getInstance()
				.getValue(uhc.getStu_id() + type);
		log.debug(NAME + "上车未刷卡学生学号为：" + uhc.getStu_id());
		if (uhc.getStu_id() != null && !"".equals(uhc.getStu_id())) {// 判断该学生是否配置
			if (vblist != null && vblist.size() > 0) {
				for (XcStuSmsBean vb : vblist) {
					XCBuildSQL.getInstance().isHasQuotas(vb, uhc, sb);
					smsg_state = smsg_state + uhc.getSms_state();
					cell_failinfo += vb.getCell_number() + ":"
							+ uhc.getSms_fail_info() + ";";
				}

				if (smsg_state.contains("1") && smsg_state.contains("2")) {// 判断短信发送是否部分成功
					log.debug(NAME + "群发信息部分成功提示：" + cell_failinfo);
					uhc.setSms_state("3");// 设置发送状态为部分成功
					uhc.setSms_fail_info(subStringMessage(cell_failinfo));
				}
			} else {
				log.info("<BuildSQL>该学生" + uhc.getStu_id() + "短信业务未开通，不下发短信！");
				uhc.setSms_state("4");
				uhc.setSms_fail_info("该学生" + uhc.getStu_id() + "短信业务未开通，不下发短信！");
				log.info("<BuildSQL>更新短信发送状态为:" + uhc.getSms_state());
			}
		}
	}

	/**
	 * 判断短信网关(1、亿美；2、翔东)
	 * 
	 * @param uhc
	 * @param
	 * @return
	 * @throws ParseException
	 */

	private static void CallSmsGatewayInterface(Up_InfoContent uhc)
			throws java.text.ParseException {
		XcStudentBean sb = SendxcmsmCommandManager.getInstance()
				.getStudentValue(Constant.STUDENT + uhc.getStu_id());
		if (sb != null) {
			EnterPriseBean epb = SendxcmsmCommandManager.getInstance()
					.getXcEntepriseValue(
							sb.getEnterprise_id() + Constant.ENTERPRISE);
			if (epb != null) {// 如果企业信息不为空
				if ("1".equals(epb.getEnterprise_smgateway())
						|| "".equals(epb.getEnterprise_smgateway())
						|| epb.getEnterprise_smgateway() == null) {
					try {
						CallSmsInterface(uhc, sb);
					} catch (Exception e) {
						e.printStackTrace();
						log.error(NAME + ",短信发送错误");
					}
				}
				if ("2".equals(epb.getEnterprise_smgateway())) {
					List<XcStuSmsVTBean> smslistbean = SmsOrderManager
							.getInstance().getXcSmsVTValue(
									uhc.getStu_id() + uhc.getSms_eventtype());
					if (smslistbean != null) {
						String message = XCVWBuildSQL.getInstance()
								.isSuccessSendMsg(smslistbean, uhc,
										uhc.getSms_eventtype(), sb);
						// 将所需信息存入SendMessageReq
						SendMessageReq sendMessageReq = new SendMessageReq();
						sendMessageReq.setStu_id(uhc.getStu_id());
						sendMessageReq.setEvent_type(uhc.getSms_eventtype());
						sendMessageReq.setMessage(message);
						sendMessageReq.setVehicle_vin(uhc.getTerminalId());
						sendMessageReq.setRecordid(uhc.getShuaka_id());
						// 调用短信发送接口
						SendBuffer.getInstance().add(sendMessageReq);
					} else {
						log.info("<BuildSQL>该学生" + uhc.getStu_id()
								+ "翔东短信业务未开通，不下发短信！");
					}
				}
			} else {
				log.info("<BuildSQL>未找到相应企业，不下发短信！");
			}
		} else {
			log.info("<BuildSQL>该学生" + uhc.getStu_id() + "不存在！");
		}
	}

	/**
	 * 截取短信部分发送成功消息
	 * 
	 * @param uhc
	 * @param
	 * @return
	 * @throws ParseException
	 */
	public static String subStringMessage(String cell_failinfo) {
		String anotherString = "";
		String character = "";
		try {
			anotherString = new String(cell_failinfo.getBytes("GBK"),
					"ISO8859_1");
			System.out.println(anotherString.length() + ":"
					+ cell_failinfo.length() + anotherString);
			if (anotherString.length() > 128) {
				character = new String(anotherString.substring(0, 127)
						.getBytes("ISO8859_1"), "gbk");
			} else {
				character = cell_failinfo;
			}

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return character;

	}

	/**
	 * //私有协议V2.0部分的解析处理     【【 目前正在使用 】】
	 */
	private static void setUpXCContentVersionTwo(String cmd, byte[] bytevalue,
			Up_InfoContent uhc, int len) throws ParseException,
			java.text.ParseException, UnsupportedEncodingException,
			SendInfoException, DBErrorException {	
		// 学生刷卡记录
		if (cmd.equals(ContentParamName.packet_content02)) {
			log.info(NAME + "V2.0学生刷卡记录处理开始");
			
			//modify by ningdonghai   2013-06-03			
			/*int le = setShuaKaDataVersionTwo(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "V2.0解析学生刷卡记录时发生异常");
				throw new ParseException("V2.0解析学生刷卡记录时发生异常");
			}*/
			
			log.info(NAME + "V2.0学生刷卡记录处理结束");
		}

		// 司机刷卡记录
		else if (cmd.equals(ContentParamName.packet_content04)) {
			log.info(NAME + "V2.0司机刷卡记录处理开始");
			int le = DriverShuaKaDataVersionTwo(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "V2.0解析司机刷卡记录时发生异常");
				throw new ParseException("V2.0解析司机刷卡记录时发生异常");
			}
			log.info(NAME + "V2.0司机刷卡记录处理结束");
		}

		// 司乘刷卡记录
		else if (cmd.equals(ContentParamName.packet_content05)) {
			log.info(NAME + "V2.0司乘刷卡记录处理开始");
			int le = SiChengShuaKaDataVersionTwo(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "V2.0解析司乘刷卡记录时发生异常");
				throw new ParseException("V2.0解析司乘刷卡记录时发生异常");
			}
			log.info(NAME + "V2.0司乘刷卡记录处理结束");
		}

		// 打卡自动销假记录
		else if (cmd.equals(ContentParamName.packet_content09)) {
			log.info(NAME + "V2.0刷卡自动销假记录处理开始");
			int le = XiaoJiaDataVersionTwo(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "V2.0解析刷卡自动销假记录时发生异常");
				throw new ParseException("V2.0解析刷卡自动销假记录时发生异常");
			}
			log.info(NAME + "V2.0刷卡自动销假记录处理结束");
		}

		// 超载告警记录
		else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0a)) {
			log.info(NAME + "V2.0超载检测告警记录处理开始");
			int le = AlarmDataVersionTwo(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "V2.0解析超载检测告警记录时发生异常");
				throw new ParseException("V2.0解析超载检测告警记录时发生异常");
			}
			log.info(NAME + "V2.0超载检测告警记录处理结束");
		}

		// 校车进出站记录
		else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0b)) {
			log.info(NAME + "V2.0通勤车进出站记录处理开始");
			int le = setInOutDataVersionTwo(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "V2.0解析通勤车进出站记录时发生异常");
				throw new ParseException("V2.0解析通勤车进出站记录时发生异常");
			}
			log.info(NAME + "V2.0通勤车进出站记录处理结束");
		}

		// 采集路线更新查询
		else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0c)) {
			log.info(NAME + "V2.0行程更新查询记录处理开始");
			try {
				searchRouteFilesVersionTwo(uhc, bytevalue);
			} catch (SendInfoException e) {
				throw new SendInfoException("发送m2m时发生异常：" + e.getMessage());
			} catch (DBErrorException e) {
				throw new DBErrorException("入库时发生异常：" + e.getMessage());
			}
			log.info(NAME + "V2.0行程更新查询记录处理结束");
		}

		// 采集站点经纬度记录
		else if (cmd.equalsIgnoreCase(ContentParamName.packet_content0d)) {
			log.info(NAME + "采集站点经纬度记录处理开始");
			int le = getRoute(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析采集站点经纬度记录时发生异常");
				throw new ParseException("解析采集站点经纬度记录时发生异常");
			}
			log.info(NAME + "采集站点经纬度记录处理结束");
		}
		// 行程更新结果汇报记录
		else if (cmd.equalsIgnoreCase(ContentParamName.packet_content10)) {
			log.info(NAME + "V2.0行程更新结果汇报记录处理开始");
			int le = TripUpdateReport(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析V2.0行程更新结果汇报记录时发生异常");
				throw new ParseException("解析V2.0行程更新结果汇报记录时发生异常");
			}
			log.info(NAME + "V2.0行程更新结果汇报记录处理结束");
		}

		// 解析无线路文件刷卡记录报文
		else if (cmd.equalsIgnoreCase(ContentParamName.packet_content12)) {
			log.info(NAME + "解析V2.0无线路文件刷卡记录处理开始");
			
			int le = setShuaKaDataNoRoute(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析V2.0无线路文件刷卡记录时发生异常");
				throw new ParseException("解析V2.0无线路文件刷卡记录时发生异常");
			}
			
			log.info(NAME + "解析V2.0无线路文件刷卡记录处理结束");

		}
		// 行程文件内容错误信息上传消息
		else if (cmd.equalsIgnoreCase(ContentParamName.packet_content13)) {
			log.info(NAME + "V2.0行程文件内容错误信息上传消息记录处理开始");
			int le = TripFileFailReport(uhc, bytevalue);
			if (le != len) {
				log.info(NAME + "解析V2.0行程文件内容错误信息上传消息记录时发生异常");
				throw new ParseException("解析V2.0行程文件内容错误信息上传消息记录时发生异常");
			}
			log.info(NAME + "V2.0行程文件内容错误信息上传消息记录处理结束");
		}
		//通勤车告警上报
		else if(cmd.equalsIgnoreCase(ContentParamName.packet_content14)){
			log.info(NAME + "V2.0通勤车版本内部协议解析：通勤车业务告警上报处理开始");
			int le = tqcAlarmRecord(uhc, bytevalue);
			if(le != len && le != 0) {
				log.info(NAME + "解析 v2.0通勤车版本内部协议：通勤车业务告警上报处理时发生异常");
				throw new ParseException("解析V2.0通勤车版本内部协议：通勤车业务告警上报处理时发生异常");
			}
			log.info(NAME + "解析V2.0通勤车版本内部协议：通勤车业务告警上报处理结束");
		}
	}
	
	/**
	 * 
	 * 解析V2.0通勤车版本内部协议：通勤车业务告警上报
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int tqcAlarmRecord(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		// ID主键
		uhc.setTqc_alarm_id(IdCreater.getUUid());
		byte[] alarm_type = new byte[4];
		System.arraycopy(bytevalue, location, alarm_type, 0, 4);
		location += 4;
		String alarmDetail = Converser.hexTo2BCD(Converser.bytesToHexString(alarm_type));
		int length = alarmDetail.length();
		String notFullDepart = alarmDetail.substring(length - 1,length);
		String notTimeDepart = alarmDetail.substring(length - 2,length - 1);
		String notSttnOpenDoor = alarmDetail.substring(length - 3,length - 2);
		String late = alarmDetail.substring(length - 4,length - 3);
		if("1".equals(notFullDepart)){
			uhc.setTqc_alarm_type("1");
		}else if("1".equals(notSttnOpenDoor)){
			uhc.setTqc_alarm_type("3");
		}else if("1".equals(notTimeDepart)){
			return 0;
//			uhc.setTqc_alarm_type("2");
		}else if("1".equals(late)){
			return 0;
//			uhc.setTqc_alarm_type("4");
		}
		
		//线路数据档案号
		byte route_id[] = new byte[4];
		System.arraycopy(bytevalue, location, route_id, 0, 4);
		location += 4;
		uhc.setTqc_alarm_route_id(Converser.bytesToHexString(route_id));
		
		//当前乘车人数
		byte psngrs_now[] = new byte[1]; 
		System.arraycopy(bytevalue, location, psngrs_now, 0, 1);
		location += 1;
		uhc.setTqc_alarm_psngrs_real_cnt(Converser.bytesToHexString(psngrs_now));
		
		//核载人数
		byte psngrs_sdandard[] = new byte[1];
		System.arraycopy(bytevalue, location, psngrs_sdandard, 0, 1);
		location += 1;
		uhc.setTqc_alarm_psngrs_standard_cnt(Converser.bytesToHexString(psngrs_sdandard));
		
		//站点经度
		byte latitude[] = new byte[4];
		System.arraycopy(bytevalue, location, latitude, 0, 4);
		location += 4;
		String la = Converser.bytesToHexString(latitude);
		if (!la.equals("FFFFFFFF")) {
			uhc.setTqc_alarm_site_latitude(ContentParamName.checkLength(
					Converser.hexToString(la, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setTqc_alarm_site_latitude("FFFF");
		}		
		//站点纬度
		byte longitude[] = new byte[4];
		System.arraycopy(bytevalue, location, longitude, 0, 4);
		location += 4;
		String lo = Converser.bytesToHexString(longitude);
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setTqc_alarm_site_longitude(ContentParamName.checkLength(
					Converser.hexToString(lo, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setTqc_alarm_site_longitude("FFFF");
		}
		//告警时间
		byte alarm_timebyte[] = new byte[6];
		System.arraycopy(bytevalue, location, alarm_timebyte, 0, 6);
		location += 6;
		uhc.setTqc_alarm_time(Converser.bytesToHexString(alarm_timebyte));
		
		//行程ID
		byte travel_id[] = new byte[4];
		System.arraycopy(bytevalue, location, travel_id, 0, 4);
		location += 4;
		uhc.setTqc_alarm_travel_id(Converser.bytesToHexString(travel_id));
		
		//通勤车新增   ningdonghai  20130723
		byte terminal_tripid[]=new byte[4];
		System.arraycopy(bytevalue, location, terminal_tripid, 0, 4);
		location += 4;
		String ter_tripId=Converser.bytesToHexString(terminal_tripid);
		uhc.setTerminal_tripId(ter_tripId);
		log.info(NAME + "通勤车新增ter_tripId--->>:"+ter_tripId);
		
		
		DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTqcAlarmSql(uhc));
		
		return location; 
	}
	/**
	 * 解析v2.0学生刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws
	 */

	private static int setShuaKaDataVersionTwo(Up_InfoContent uhc,
			byte[] bytevalue) {

		int location = 0;
		// ID主键
		String shuaka_id = IdCreater.getUUid();
		String pid = IdCreater.getUUid();
		// 学生编号
		byte[] stu_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, stu_idbyte, 0, 4);
		location += 4;
		String stu_id = Converser.bytesToHexString(stu_idbyte);
		// 当班司机编号
		byte[] driver_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, driver_idbyte, 0, 4);
		location += 4;
		String driver_id = Converser.bytesToHexString(driver_idbyte);
		// 当班司乘编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);
		// 站点编号
		byte[] site_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, site_idbyte, 0, 4);
		location += 4;
		String site_id = Converser.bytesToHexString(site_idbyte);
		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 行为状态
		byte[] site_flagbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_flagbyte, 0, 1);
		location += 1;
		String site_flagState = Converser.bytesToHexString(site_flagbyte);
		//上学上车
		if (site_flagState.equals("00")) {
			uhc.setSite_flag("0");
			uhc.setVss_flag("0");
			uhc.setSms_eventtype("4");
		}
		//上学下车
		else if (site_flagState.equals("01")) {
			uhc.setSite_flag("0");
			uhc.setVss_flag("1");
			uhc.setSms_eventtype("5");
		}
		//放学上车
		else if (site_flagState.equals("02")) {
			uhc.setSite_flag("1");
			uhc.setVss_flag("0");
			uhc.setSms_eventtype("4");
		}
		//放学下车
		else if (site_flagState.equals("03")) {
			uhc.setSite_flag("1");
			uhc.setVss_flag("1");
			uhc.setSms_eventtype("5");
		}

		// 短信状态标识
		uhc.setSmstype(site_flagState);

		// 异常上下车状态
		byte[] alarm_typebyte = new byte[1];
		System.arraycopy(bytevalue, location, alarm_typebyte, 0, 1);
		location += 1;
		String alarm_type = Converser.bytesToHexString(alarm_typebyte);
		alarm_type = Converser.hexTo2BCD(alarm_type);
		if (!alarm_type.equals("") && alarm_type != null) {
			if (alarm_type.substring(7, 8).equals("1")) {
				uhc.setAlarm_id("73");
				uhc.setSms_eventtype("2");
			}
			if (alarm_type.substring(6, 7).equals("1")) {
				uhc.setAlarm_id("74");
				uhc.setSms_eventtype("3");
			}
		} else {
			uhc.setAlarm_id("");
		}

		// 下车学生数量
		byte[] st_down_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_down_numbyte, 0, 1);
		location += 1;
		String st_down_num = Converser.bytesToHexString(st_down_numbyte);
		// 应下车学生数量
		byte[] st_institute_down_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_institute_down_numbyte, 0, 1);
		location += 1;
		String st_institute_down_num = Converser
				.bytesToHexString(st_institute_down_numbyte);
		// 上车学生数量
		byte[] st_up_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_up_numbyte, 0, 1);
		location += 1;
		String st_up_num = Converser.bytesToHexString(st_up_numbyte);
		// 应上车学生数量
		byte[] setSt_institute_up_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, setSt_institute_up_numbyte, 0, 1);
		location += 1;
		String st_institute_up_num = Converser
				.bytesToHexString(setSt_institute_up_numbyte);
		// 当前学生数量
		byte[] st_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_numbyte, 0, 1);
		location += 1;
		String st_num = Converser.bytesToHexString(st_numbyte);
		// 刷卡时间
		byte[] shuaka_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shuaka_timebyte, 0, 6);
		location += 6;
		String shuaka_time = Converser.bytesToHexString(shuaka_timebyte);
		// 行程编号
		byte[] trip_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, trip_idbyte, 0, 4);
		location += 4;
		String trip_id = Converser.bytesToHexString(trip_idbyte);
		uhc.setPid(pid);
		uhc.setShuaka_id(shuaka_id);
		uhc.setStu_id(String.valueOf(Long.parseLong(stu_id, 16)));
		if (!site_id.equals("FFFFFFFF")) {
			uhc.setSite_id(String.valueOf(Long.parseLong(site_id, 16)));
		} else {
			uhc.setSite_id("-1");
		}
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		if (!driver_id.equals("FFFFFFFF")) {
			uhc.setDriver_id(String.valueOf(Long.parseLong(driver_id, 16)));
		} else {
			uhc.setDriver_id("");
		}
		if (!sicheng_crd_id.equals("FFFFFFFF")) {
			uhc.setSicheng_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		} else {
			uhc.setSicheng_id("");
		}
		uhc.setSt_down_num(String.valueOf(Integer.parseInt(st_down_num, 16)));
		uhc.setSt_institute_down_num(String.valueOf(Integer.parseInt(
				st_institute_down_num, 16)));
		uhc.setSt_up_num(String.valueOf(Integer.parseInt(st_up_num, 16)));
		uhc.setSt_institute_up_num(String.valueOf(Integer.parseInt(
				st_institute_up_num, 16)));
		uhc.setSt_num(String.valueOf(Integer.parseInt(st_num, 16)));
		uhc.setTerminal_time(shuaka_time);
		uhc.setTrip_id(String.valueOf(Integer.parseInt(trip_id, 16)));
		setCacheShuakaTerminal(uhc);
		log.info(NAME + "V2.0学生刷卡流程处理成功");
		// 操作学生刷卡实时表
		DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildShiShiSql(uhc));

		try {
			CallSmsGatewayInterface(uhc);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
			log.error(NAME + ",短信接口调用错误");
		}
		
		InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildInsertShuaKaSql(uhc));
		
		return location;
	}

	/**
	 * 解析v2.0无线路文件刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws
	 */
	private static int setShuaKaDataNoRoute(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		// ID主键
		String shuaka_id = IdCreater.getUUid();
		// 乘客卡号
		byte[] stu_card_idbyte = new byte[7];
		System.arraycopy(bytevalue, location, stu_card_idbyte, 0, 7);
		location += 7;
		String stu_card_id = Converser.bytesToHexString(stu_card_idbyte);

//		String stu_id = SendxcmsmCommandManager.getInstance().getStudentIdValue(Constant.STUDENT + stu_card_id);

		// 行为状态
		byte[] site_flagbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_flagbyte, 0, 1);
		location += 1;
		String site_flagState = Converser.bytesToHexString(site_flagbyte);

		if (site_flagState.equals("00")) {
			uhc.setVss_flag("0");
			uhc.setSms_eventtype("4");

		} else if (site_flagState.equals("01")) {

			uhc.setVss_flag("1");
			uhc.setSms_eventtype("5");
		}

		// 当前车内人数
		byte[] st_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, st_numbyte, 0, 1);
		location += 1;
		String st_num = Converser.bytesToHexString(st_numbyte);

		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);

		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);

		// 刷卡时间
		byte[] shuaka_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shuaka_timebyte, 0, 6);
		location += 6;
		String shuaka_time = Converser.bytesToHexString(shuaka_timebyte);
//		if (stu_id != null && !stu_id.equals("")) {
			uhc.setShuaka_id(shuaka_id);
//			uhc.setStu_id(stu_id);
			//此处直接把乘客ID换成了卡ID。
			uhc.setStu_id(stu_card_id);
			uhc.setSt_num(String.valueOf(Integer.parseInt(st_num, 16)));
			if (!latitude.equals("FFFFFFFF")) {
				uhc.setLatitude(ContentParamName.checkLength(Converser.hexToString(latitude, 1000000), ContentParamName.LATITUDE_BASE));
			} else {
				uhc.setLatitude("FFFF");
			}
			if (!longitude.equals("FFFFFFFF")) {
				uhc.setLongitude(ContentParamName.checkLength(Converser.hexToString(longitude, 1000000), ContentParamName.LONGITUDELEN));
			} else {
				uhc.setLongitude("FFFF");
			}
			uhc.setTerminal_time(shuaka_time);
			location += 16;
			// 操作学生刷卡实时表
//			DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildShiShiSqlNoRoute(uhc));
//			try {
//				CallSmsGatewayVTInterface(uhc);
//			} catch (java.text.ParseException e) {
//				e.printStackTrace();
//				log.error(NAME + ",短信接口调用错误");
//			}
			InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildInsertShuaKaNORouteSql(uhc));
			log.info(NAME + "V2.0无线路学生刷卡流程处理完成");
//		} else {
//			log.info(NAME + "此次无线路刷卡操作上报的乘客卡号不存在");
//		}
		return location;

	}

	/**
	 * 解析V2.0进出站记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int setInOutDataVersionTwo(Up_InfoContent uhc,
			byte[] bytevalue) {
		String pici = IdCreater.getUUid();
		int upnumlen = 0;
		int downnumlen = 0;
		int qjianumlen = 0;
		int location = 0;
		String[] downstr = null;
		String[] upstr = null;
		String[] qjiastr = null;
		// 刷卡流水表主键
		String downshuaka_id = IdCreater.getUUid();
		String inout_id = IdCreater.getUUid();
		// uhc.setShuaka_id(shuaka_id);
		// 站点编号
		byte[] site_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, site_idbyte, 0, 4);
		location += 4;
		String site_id = Converser.bytesToHexString(site_idbyte);
		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);

		// 当班司机编号
		byte[] driver_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, driver_idbyte, 0, 4);
		location += 4;
		String driver_id = Converser.bytesToHexString(driver_idbyte);

		// 当班司乘编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);

		// 上放学状态，0x00为上学，0x01为放学
		byte[] site_updownbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_updownbyte, 0, 1);
		location += 1;
		String site_updown = Converser.bytesToHexString(site_updownbyte);

		// 进出站状态 0x00为进站，0x01为出站
		byte[] inout_statebyte = new byte[1];
		System.arraycopy(bytevalue, location, inout_statebyte, 0, 1);
		location += 1;
		String inout_state = Converser.bytesToHexString(inout_statebyte);

		if (inout_state.equals("00")) {
			uhc.setInout_state("0");
		} else if (inout_state.equals("01")) {
			uhc.setInout_state("1");
		}
		if (site_updown.equals("00")) {
			uhc.setSite_flag("0");

		} else if (site_updown.equals("01")) {
			uhc.setSite_flag("1");

		}
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 实际进站时间
		byte[] in_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, in_timebyte, 0, 6);
		location += 6;
		String in_time = Converser.bytesToHexString(in_timebyte);
		// 应进站时间
		byte[] plan_in_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, plan_in_timebyte, 0, 6);
		location += 6;
		String plan_in_time = Converser.bytesToHexString(plan_in_timebyte);

		uhc.setInout_id(inout_id);
		uhc.setIn_time(in_time);
		uhc.setPlan_in_time(plan_in_time);
		uhc.setDownshuaka_id(downshuaka_id);
		uhc.setSite_id(String.valueOf(Long.parseLong(site_id, 16)));
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}

		if (!driver_id.equals("FFFFFFFF")) {
			uhc.setDriver_id(String.valueOf(Long.parseLong(driver_id, 16)));
		} else {
			uhc.setDriver_id("");
		}

		if (!sicheng_crd_id.equals("FFFFFFFF")) {
			uhc.setSicheng_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		} else {
			uhc.setSicheng_id("");
		}
		//00： 表示进站    01：表示出站
		if (inout_state.equals("01")) {
			// 实际出站时间
			byte[] out_timebyte = new byte[6];
			System.arraycopy(bytevalue, location, out_timebyte, 0, 6);
			location += 6;
			String out_time = Converser.bytesToHexString(out_timebyte);
			// 应出站时间
			byte[] plan_out_timebyte = new byte[6];
			System.arraycopy(bytevalue, location, plan_out_timebyte, 0, 6);
			location += 6;
			String plan_out_time = Converser
					.bytesToHexString(plan_out_timebyte);

			// 上车学生数量
			byte[] st_up_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_up_numbyte, 0, 1);
			location += 1;
			String st_up_num = Converser.bytesToHexString(st_up_numbyte);

			// 应上车学生数量
			byte[] setSt_institute_up_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, setSt_institute_up_numbyte,
					0, 1);
			location += 1;
			String st_institute_up_num = Converser
					.bytesToHexString(setSt_institute_up_numbyte);

			// 下车学生数量
			byte[] st_down_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_down_numbyte, 0, 1);
			location += 1;
			String st_down_num = Converser.bytesToHexString(st_down_numbyte);

			// 应下车学生数量
			byte[] st_institute_down_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_institute_down_numbyte, 0,
					1);
			location += 1;
			String st_institute_down_num = Converser
					.bytesToHexString(st_institute_down_numbyte);

			// 当前学生数量
			byte[] st_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_numbyte, 0, 1);
			location += 1;
			String st_num = Converser.bytesToHexString(st_numbyte);

			uhc.setOut_time(out_time);
			uhc.setPlan_out_time(plan_out_time);
			uhc.setSt_down_num(String.valueOf(Integer.parseInt(st_down_num, 16)));
			uhc.setSt_institute_down_num(String.valueOf(Integer.parseInt(
					st_institute_down_num, 16)));
			uhc.setSt_up_num(String.valueOf(Integer.parseInt(st_up_num, 16)));
			uhc.setSt_institute_up_num(String.valueOf(Integer.parseInt(
					st_institute_up_num, 16)));
			uhc.setSt_num(String.valueOf(Integer.parseInt(st_num, 16)));

		}
		//00： 表示进站    01：表示出站
		if (inout_state.equals("01")) {
			// 上车未刷卡人数
			byte[] up_not_shuaka_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, up_not_shuaka_numbyte, 0, 1);
			location += 1;
			String st_up_num = Converser
					.bytesToHexString(up_not_shuaka_numbyte);
			upnumlen = Integer.parseInt(st_up_num, 16);

			upstr = new String[upnumlen];
			if (inout_state.equals("01")) {
				uhc.setTerminal_time(uhc.getOut_time());
			} else {
				uhc.setTerminal_time(uhc.getIn_time());
			}
			if (upnumlen != 0) {
				// 上车未刷卡学生编号

				for (int i = 0; i < upnumlen; i++) {

					byte[] up_not_shuaka_codebyte = new byte[4];
					System.arraycopy(bytevalue, location,
							up_not_shuaka_codebyte, 0, 4);
					location += 4;
					String up_not_shuaka_code = Converser
							.bytesToHexString(up_not_shuaka_codebyte);
					uhc.setStu_id(String.valueOf(Long.parseLong(
							up_not_shuaka_code, 16)));
					uhc.setAlarm_id("79");
					upstr[i] = uhc.getStu_id();
				}
			}
			// 下车未刷卡人数
			byte[] st_down_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, st_down_numbyte, 0, 1);
			location += 1;
			String st_down_num = Converser.bytesToHexString(st_down_numbyte);
			downnumlen = Integer.parseInt(st_down_num, 16);
			downstr = new String[downnumlen];
			if (downnumlen != 0) {
				// 下车未刷卡学生编号
				for (int i = 0; i < downnumlen; i++) {
					byte[] down_not_shuaka_codebyte = new byte[4];
					System.arraycopy(bytevalue, location,
							down_not_shuaka_codebyte, 0, 4);
					location += 4;
					String up_down_shuaka_code = Converser
							.bytesToHexString(down_not_shuaka_codebyte);
					uhc.setStu_id(String.valueOf(Long.parseLong(
							up_down_shuaka_code, 16)));
					downstr[i] = uhc.getStu_id();
					uhc.setAlarm_id("80");
				}
			}
			// 请假学生人数
			byte[] qjia_numbyte = new byte[1];
			System.arraycopy(bytevalue, location, qjia_numbyte, 0, 1);
			location += 1;
			String qjia_num = Converser.bytesToHexString(qjia_numbyte);
			qjianumlen = Integer.parseInt(qjia_num, 16);
			qjiastr = new String[qjianumlen];
			if (qjianumlen != 0) {

				for (int i = 0; i < qjianumlen; i++) {
					byte[] qjia_codebyte = new byte[4];
					System.arraycopy(bytevalue, location, qjia_codebyte, 0, 4);
					location += 4;
					String qjia_code = Converser
							.bytesToHexString(qjia_codebyte);
					uhc.setStu_id(String.valueOf(Long.parseLong(qjia_code, 16)));
					qjiastr[i] = uhc.getStu_id();

				}
			}
			uhc.setUp_num(String.valueOf(upnumlen));
			uhc.setDown_num(String.valueOf(downnumlen));
			uhc.setQjia_num(String.valueOf(qjianumlen));
		}

		// 行程编号
		byte[] trip_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, trip_idbyte, 0, 4);
		location += 4;
		String trip_id = Converser.bytesToHexString(trip_idbyte);

		// 行程开始时间
		byte[] trip_beg_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, trip_beg_timebyte, 0, 6);
		location += 6;
		String trip_beg_time = Converser.bytesToHexString(trip_beg_timebyte);

		// 行程结束时间
		byte[] trip_end_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, trip_end_timebyte, 0, 6);
		location += 6;
		String trip_end_time = Converser.bytesToHexString(trip_end_timebyte);
	
		
		//行程序列号  终端自动生成  ---add by ningdh start
		byte[] terminal_tripIdByte = new byte[4];
		System.arraycopy(bytevalue, location, terminal_tripIdByte, 0, 4);
		location += 4;
		String terminal_tripId = String.valueOf(Integer.parseInt(Converser.bytesToHexString(terminal_tripIdByte), 16));
		uhc.setTerminal_tripId(terminal_tripId);
		log.info("-------terminal_tripId(ningdh)------>>:"+terminal_tripId);
		//--------add by ningdh end

		uhc.setTrip_id(String.valueOf(Long.parseLong(trip_id, 16)));
		uhc.setTrip_beg_time(trip_beg_time);
		uhc.setTrip_end_time(trip_end_time);
		//00： 表示进站    01：表示出站
		if (inout_state.equals("01")) {
			for (int i = 0; i < upnumlen; i++) {
				String pid = IdCreater.getUUid();
				uhc.setStu_id(upstr[i]);
				uhc.setAlarm_type_id("79");
				uhc.setSms_eventtype("0");
				uhc.setPid(pid);
				DBBuffer.getInstance().add(
						XCVWBuildSQL.getInstance().UpNobuildShiShiSql(uhc));
				try {
					CallSmsGatewayInterface(uhc);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
					log.error(NAME + ",短信接口调用错误");
				}
				InsertBuffer.getInstance().add(
						XCVWBuildSQL.getInstance().buildInsertNotShuaKaUpSql(
								uhc, pici));
			}
			for (int i = 0; i < downnumlen; i++) {
				String pid = IdCreater.getUUid();
				uhc.setStu_id(downstr[i]);
				uhc.setAlarm_type_id("80");
				uhc.setSms_eventtype("1");
				uhc.setPid(pid);
				DBBuffer.getInstance().add(
						XCVWBuildSQL.getInstance().UpNobuildShiShiSql(uhc));
				try {
					// 判断短信接口
					CallSmsGatewayInterface(uhc);
				} catch (java.text.ParseException e) {
					e.printStackTrace();
					log.error(NAME + ",短信接口调用错误");
				}
				InsertBuffer.getInstance().add(
						XCVWBuildSQL.getInstance().buildInsertNotShuaKaUpSql(
								uhc, pici));
			}
			for (int i = 0; i < qjianumlen; i++) {
				String pid = IdCreater.getUUid();
				uhc.setStu_id(qjiastr[i]);
				uhc.setAlarm_type_id("QJ");
				uhc.setPid(pid);
				InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildInsertNotShuaKaUpSql(uhc, pici));
			}
		}
		InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildInsertInOutSql(uhc));
		log.info(NAME + "V2.0通勤车进出站流程处理成功");
		

		//更新车辆缓存的行程trip_id和线路route_id   add by liuja start 2013-10-26
		//更新行程的代码放在了车辆缓存定时任务之中
		/*log.info("更新车辆缓存的行程ID和线路ID开始");
		String key=Constant.VEHICLEREAL+uhc.getTerminalId();
		VehicleReal vehicleReal = CacheUtils.getVehicleRealCache(uhc.getTerminalId());
		
		vehicleReal.setRoute_id(uhc.getRoute_id());
		vehicleReal.setTrip_id(uhc.getTrip_id());
        //log.info("key::"+key+"   "+uhc.getTerminalId()+" getGps_speeding  "+uhc.getGps_speeding()+" getSpeed  " +uhc.getSpeed()+"   ");
		//更新该车辆的缓存
		VehicleRealCacheManager.getInstance().SyncVehicleRealInfoValue(Constant.OFF, key, vehicleReal); 
		
		log.info("更新车辆缓存的行程ID和线路ID结束");
		*/
		//add by liuja end
		
		//00： 表示进站    01：表示出站
		if (inout_state.equals("00")) {
			//add by liuja start 按照站点推送
			log.info("厂内通勤按照站点推送开始");
			try {				
				if(sdfH.parse(sdfH.format(new Date())).getTime()-sdfH.parse(in_time).getTime()<=timeDiffence)
				{
					ParentsDAO parentsDAO = (ParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
					List<Site> listSite = parentsDAO.getNextSite(uhc.getRoute_id(),uhc.getSite_id());
					if(listSite != null && listSite.size()>0){
						for(Site site : listSite){
							List<StuSiteNote> listStuSiteNote = parentsDAO.getEmpSiteNote(uhc.getTrip_id(),String.valueOf(site.getSite_Id()));
							if(listStuSiteNote != null && listStuSiteNote.size()>0){
								for (StuSiteNote stuSiteNote : listStuSiteNote)
						        {
									stuSiteNote.setVehicle_vin(uhc.getTerminalId());
									//此处异步调用厂内   站点推送
									//siteNoteManager.pushMsg1(stuSiteNote);
									BasicObject boSendCommand=new BasicObject();
									boSendCommand.setObjectName("com.neusoft.parents.service.PushMsgCommand");										
									boSendCommand.setFunctionName("pushMsg1");
									List<Object> paramsSendCommand=new ArrayList<Object>();
									paramsSendCommand.add(stuSiteNote);
									//paramsSendCommand.add(parentsDAO);
									boSendCommand.setParamsList(paramsSendCommand);
									//将BasicObject加入事件处理缓存
									PushBuffer.getInstance().add(boSendCommand);
									
									
									//将发送的内容存放到数据库中
									DBBuffer.getInstance().add(ParentsBuildSQL.getInstance().buildInsertSiteNoteLog(new Date(), stuSiteNote));
									//log.info("dddddddddddddddddddddddddddddddddddddddddddddddddd");
						        }
							}
						}
					}
					
					log.info("厂内通勤按照站点推送结束");
				}
				else{
					log.info("厂内通勤上报超时不触发厂内推送，该信息可能是因掉线补传的进站信息");
				}
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		//add by liuja end 按照站点推送   if (site_updown.equals("00"))
		try {
			
			if("01".equals(inout_state)){
				//只要有出站，将行程表的出站字段修改成1
				log.info("出站修改out_site_flag。。。" + uhc.getTrip_id());
				DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTripOut(uhc.getTrip_id()));
			}else if("00".equals(inout_state)){
				log.info("进非第一站修改out_site_flag。。。" + uhc.getTrip_id());
				DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTripIut(uhc.getTrip_id(),uhc.getRoute_id(),uhc.getSite_id()));
			}
			
			TqcStatisticSQL tqcStatisticSQL = (TqcStatisticSQL) SpringBootStrap.getInstance().getBean("tqcStatisticSQL");
			//迟到告警
			if("00".equals(inout_state) && "00".equals(site_updown)){
				List<Map<String, String>> rs = tqcStatisticSQL.selectMaxSiteByRoute(Integer.parseInt(route_id,16),2);    //先判断该线路是第二厂区
				if(rs.size()<=0){
					rs = tqcStatisticSQL.selectMaxSiteByRoute(Integer.parseInt(route_id,16),1);    //在判断该新路是第一厂区
				}
				if(rs.size()<=0){
					rs = tqcStatisticSQL.selectMaxSiteByRoute(Integer.parseInt(route_id,16));    //在判断该新路在其他厂区
				}
				if(rs.size()<=0){
					log.info("未查到线路，请检查，入站迟到判断退出！！！");
					return location;
				}
				Map<String, String> last_site = rs.get(0);
				String lateFlagTime = "08:00:00";
				if( String.valueOf(Integer.parseInt(site_id,16)).equals(String.valueOf(last_site.get("site_id")))){
					//add by liuja 更新车辆行程表数据    最后一站的时候，将status字段设置成2，表示已经完成
					//放在判断迟到之前，避免后面出现异常跳过这一段设置     
					//注释掉，因为在最后一站刷卡也会上报进站信息，将该条行程置为已经完成 ，显示异常
//					DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTrip(2,uhc.getTrip_id(),0));
//					DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTrip(2,uhc.getTrip_id(),1));
//					DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTrip(2,uhc.getTrip_id(),2));
					
					if(null != last_site.get("late_time") && !"".equals(last_site.get("late_time"))){
						lateFlagTime = last_site.get("late_time");
					}
					if(sdfH3.parse(in_time.substring(6)).after(sdfH1.parse(lateFlagTime))){
						uhc.setTqc_alarm_id(String.valueOf(UUID.randomUUID()));
						InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTqcLateAlarmSql(uhc,lateFlagTime));
					}
				}else{
					log.debug("入站迟到判断：不是最后一站" + "route_id:" + Integer.parseInt(route_id,16) + "，site_id : " + Integer.parseInt(site_id,16));
					//上班的话，进第一站即改变状态，如果是下班，出第一站才将状态改变   00：进站 01：出站
					//add by liuja 更新车辆行程表数据    不是最后一站的时候，将status字段设置成1，表示正在进行    
					//DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTrip(1,uhc.getTrip_id(),0));
					//DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTrip(1,uhc.getTrip_id(),2));
				}
			}
			log.info("通勤车迟到处理完成。。。并开始处理延时发车。。。");
			//晚班出站设置
			if("01".equals(inout_state)){
				List<Map<String, String>> rs  = tqcStatisticSQL.selectMaxSiteByRoute(Integer.parseInt(route_id,16)); 
				if(rs.size()<=0){
					log.info("未查到线路，请检查，晚班出站设置status字段退出！！！");
				}
				else
				{
					Map<String, String> last_site = rs.get(0);	
					if( !String.valueOf(Integer.parseInt(site_id,16)).equals(String.valueOf(last_site.get("site_id")))){
						//如果出的是第一站
						//add by liuja 更新车辆行程表数据    不是最后一站的时候，将status字段设置成1，表示正在进行    
						//DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().updateTrip(1,uhc.getTrip_id(),1));
					}else{
						log.info("晚班出非最后一站。。。" + "route_id:" + Integer.parseInt(route_id,16) + "，site_id : " + Integer.parseInt(site_id,16));
					}
				}
			}
			
			//延时发车
			if("01".equals(inout_state) && "00".equals(site_updown)){
				List<Map<String, String>> rs = tqcStatisticSQL.selectMinSiteByRoute(Integer.parseInt(route_id,16), Integer.parseInt(trip_id,16));
				if(rs.size()<=0){
					log.info("未查到规定发车时间，请检查，延时发车判断退出！！！");
					return location;
				}
				Map<String, String> first_site = rs.get(0);	
				if( String.valueOf(Integer.parseInt(site_id,16)).equals(String.valueOf(first_site.get("site_id")))){
					in_time=uhc.getOut_time();
					log.info("----发车时间----"+in_time);
					if(sdfH.parse(in_time).getTime() >= sdfH2.parse(in_time.substring(0,6)+first_site.get("send_time")).getTime()+round){
						uhc.setTqc_alarm_id(String.valueOf(UUID.randomUUID()));
						InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTqcDelayAlarmSql(uhc,"5"));
					}else if(sdfH.parse(in_time).getTime() <= sdfH2.parse(in_time.substring(0,6)+first_site.get("send_time")).getTime()-round){
						uhc.setTqc_alarm_id(String.valueOf(UUID.randomUUID()));
						InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTqcDelayAlarmSql(uhc,"2"));
					}
				}else{
					log.info("延迟发车判断：不是首发站。。。" + "route_id:" + Integer.parseInt(route_id,16) + "，site_id : " + Integer.parseInt(site_id,16));
				}
			}
			//此处不能再添加内容
		} catch (Exception e) {
			log.error("通勤车迟到或者延时发车处理失败，请检查。。。");
			e.printStackTrace();
		}
		return location;
	}
	
	/**
	 * 解析V2.0司机刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */

	private static int DriverShuaKaDataVersionTwo(Up_InfoContent uhc,
			byte[] bytevalue) throws java.text.ParseException {
		int location = 0;
		// 司机编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);
		
		// 线路编号
		//byte[] route_idbyte = new byte[4];
		//System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		//location += 4;
		//String route_id = Converser.bytesToHexString(route_idbyte);
		
		// 行为状态
		byte[] site_flagbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_flagbyte, 0, 1);
		location += 1;
		String site_flag = Converser.bytesToHexString(site_flagbyte);

		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 上报时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);
		if ("00".equals(site_flag) || "02".equals(site_flag)) {
			uhc.setSite_flag("0");
		} else {
			uhc.setSite_flag("1");
		}
		String sicheng_id = IdCreater.getUUid();

		uhc.setSicheng_id(sicheng_id);
		uhc.setOther_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		uhc.setTerminal_time(shangbao_time);
		InsertBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildInsertSISCCRDSql(uhc));
		log.info(NAME + "V2.0司机刷卡流程处理成功");
		if ("0".equals(uhc.getSite_flag())) {
			log.info(NAME + "V2.0司机编号为" + uhc.getOther_id());
			DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildUpdateUpTerminalDriverSql(uhc));
		} else {
			DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildUpdateDownTerminalDriverSql(uhc));
		}

		if ("00".equals(site_flag) || "02".equals(site_flag)) {
			setCachePattern(uhc);
		} else {
			if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState()) {
				Constant.getMemcachedClient().delete(uhc.getTerminalId() + Constant.DRIVER);
				Constant.getMemcachedClient().delete(uhc.getTerminalId() + Constant.ShuaKaTerminal);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId() + Constant.DRIVER);
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId() + Constant.ShuaKaTerminal);
		}

		return location;
	}

	/**
	 * 解析V2.0司乘刷卡记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */

	private static int SiChengShuaKaDataVersionTwo(Up_InfoContent uhc,
			byte[] bytevalue) throws java.text.ParseException {
		int location = 0;
		// 司乘编号
		byte[] sicheng_crd_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, sicheng_crd_idbyte, 0, 4);
		location += 4;
		String sicheng_crd_id = Converser.bytesToHexString(sicheng_crd_idbyte);

		// 行为状态
		byte[] site_flagbyte = new byte[1];
		System.arraycopy(bytevalue, location, site_flagbyte, 0, 1);
		location += 1;
		String site_flag = Converser.bytesToHexString(site_flagbyte);
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 上报时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);
		if ("00".equals(site_flag) || "02".equals(site_flag)) {
			uhc.setSite_flag("0");
		} else {
			uhc.setSite_flag("1");
		}
		String sicheng_id = IdCreater.getUUid();
		uhc.setSicheng_id(sicheng_id);
		uhc.setOther_id(String.valueOf(Long.parseLong(sicheng_crd_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		// uhc.setSite_flag(ContentParamName.checkLength(site_flag,ContentParamName.SITE_FLAGLEN));
		uhc.setTerminal_time(shangbao_time);

		InsertBuffer.getInstance().add(
				XCVWBuildSQL.getInstance().buildInsertSISCCRDSql(uhc));
		log.info(NAME + "V2.0司乘刷卡记录流程处理成功");

		return location;
	}

	/**
	 * 解析V2.0刷卡自动销假记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 */
	private static int XiaoJiaDataVersionTwo(Up_InfoContent uhc,
			byte[] bytevalue) {
		int location = 0;

		// 销假学生编号
		byte[] back_stu_codebyte = new byte[4];
		System.arraycopy(bytevalue, location, back_stu_codebyte, 0, 4);
		location += 4;
		String back_stu_code = Converser.bytesToHexString(back_stu_codebyte);

		// 销假时间
		byte[] back_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, back_timebyte, 0, 6);
		location += 6;
		String back_time = Converser.bytesToHexString(back_timebyte);
		uhc.setStu_id(String.valueOf(Long.parseLong(back_stu_code, 16)));
		uhc.setTerminal_time(back_time);
		DBBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildUpdateXiaoJiaSql(uhc));
		log.info(NAME + "V2.0刷卡自动销假记录流程处理成功");

		return location;
	}

	/**
	 * 解析V2.0车辆超载报警记录报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */
	private static int AlarmDataVersionTwo(Up_InfoContent uhc, byte[] bytevalue)
			throws java.text.ParseException {
		int location = 0;
		String alarm_id = IdCreater.getUUid();
		// 告警状态 0x01为告警，0x00告警取消
		byte[] alarm_statebyte = new byte[1];
		System.arraycopy(bytevalue, location, alarm_statebyte, 0, 1);
		location += 1;
		String alarm_state = Converser.bytesToHexString(alarm_statebyte);
		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);
		// 当前人数
		byte[] now_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, now_numbyte, 0, 1);
		location += 1;
		String now_num = Converser.bytesToHexString(now_numbyte);
		// 核载人数
		byte[] hezai_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, hezai_numbyte, 0, 1);
		location += 1;
		String hezai_num = Converser.bytesToHexString(hezai_numbyte);
		// 车辆纬度
		byte[] latitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, latitudebyte, 0, 4);
		location += 4;
		String latitude = Converser.bytesToHexString(latitudebyte);
		// 车辆经度
		byte[] longitudebyte = new byte[4];
		System.arraycopy(bytevalue, location, longitudebyte, 0, 4);
		location += 4;
		String longitude = Converser.bytesToHexString(longitudebyte);
		// 上报时间
		byte[] shangbao_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, shangbao_timebyte, 0, 6);
		location += 6;
		String shangbao_time = Converser.bytesToHexString(shangbao_timebyte);

		// 行程编号
		byte[] trip_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, trip_idbyte, 0, 4);
		location += 4;
		String trip_id = Converser.bytesToHexString(trip_idbyte);

		uhc.setAlarm_id(alarm_id);
		if (alarm_state.equals("00")) {
			uhc.setAlrm_state("0");
		} else {
			uhc.setAlrm_state("1");
		}
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		if (!latitude.equals("FFFFFFFF")) {
			uhc.setLatitude(ContentParamName.checkLength(
					Converser.hexToString(latitude, 1000000),
					ContentParamName.LATITUDE_BASE));
		} else {
			uhc.setLatitude("FFFF");
		}
		if (!longitude.equals("FFFFFFFF")) {
			uhc.setLongitude(ContentParamName.checkLength(
					Converser.hexToString(longitude, 1000000),
					ContentParamName.LONGITUDELEN));
		} else {
			uhc.setLongitude("FFFF");
		}
		uhc.setSt_num(String.valueOf(Integer.parseInt(now_num, 16)));
		uhc.setHezai_id(String.valueOf(Integer.parseInt(hezai_num, 16)));
		uhc.setTerminal_time(shangbao_time);
		uhc.setTrip_id(String.valueOf(Long.parseLong(trip_id, 16)));
		// DBBuffer.getInstance().add(XCBuildSQL.getInstance().buildInsertChaoZaiSql(uhc));
		CachePattern.getInstance().setAlarmMemCache(uhc, alarm_id, "72",
				uhc.getAlrm_state(), "");
		setCacheShuakaTerminal(uhc);

		DBBuffer.getInstance().add(
				XCBuildSQL.getInstance().buildUpdateTerminalNumSql(uhc));
		log.info(NAME + "V2.0超载告警流程处理成功");
		return location;
	}

	/**
	 * 解析V2.0行程更新结果汇报消息报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */
	private static int TripUpdateReport(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;
		// 更新完成时间
		byte[] updateComplete_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, updateComplete_timebyte, 0, 6);
		location += 6;
		String updateComplete_time = Converser
				.bytesToHexString(updateComplete_timebyte);

		// 行程数
		byte[] trip_numbyte = new byte[1];
		System.arraycopy(bytevalue, location, trip_numbyte, 0, 1);
		location += 1;
		String trip_num = Converser.bytesToHexString(trip_numbyte);

		int tripnum = Integer.parseInt(trip_num, 16);
		for (int i = 0; i < tripnum; i++) {
			// 行程编号
			byte[] trip_codebyte = new byte[4];
			System.arraycopy(bytevalue, location, trip_codebyte, 0, 4);
			location += 4;
			String trip_code = Converser.bytesToHexString(trip_codebyte);

			// CRC校验码
			byte[] CRCbyte = new byte[4];
			System.arraycopy(bytevalue, location, CRCbyte, 0, 4);
			location += 4;
			String CRC = Converser.bytesToHexString(CRCbyte);

			// 行程状态
			byte[] trip_statebyte = new byte[1];
			System.arraycopy(bytevalue, location, trip_statebyte, 0, 1);
			location += 1;
			String trip_state = Converser.bytesToHexString(trip_statebyte);

			uhc.setUpdateComplete_time(updateComplete_time);
			uhc.setTrip_num(String.valueOf(Integer.parseInt(trip_num, 16)));
			uhc.setTrip_code(String.valueOf(Long.parseLong(trip_code, 16)));
			uhc.setCRC(String.valueOf(Long.parseLong(CRC, 16)));
			uhc.setTrip_state(trip_state);
            
			if ("00".equals(trip_state)) {
				DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTripReportSuccess(uhc));
				DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTripReportSuccessTemp(uhc));
				try {
					//先判断是否早上6点之前的更新，不再调用短信下发网关接口
					long sixTime=CDate.getUtcMsTime("06:00");
					long currTime=CDate.getUtcMsTime(CDate.getStringDate_Hm());
					log.info(NAME + "sixTime-->:"+sixTime+" currTime-->:"+currTime);
					//如果当前时间大于等于06:00
					if(currTime>=sixTime){
						callSms(uhc);	
					}					
				} catch (Exception e) {
					log.error("调用发送短信接口失败。。。");
					e.printStackTrace();
				}
				log.info(NAME + "V2.0行程更新结果汇报消息处理成功");
			}
			if ("01".equals(trip_state)) {
				DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTripReportFail(uhc));
				DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTripReportFailTemp(uhc));
				log.info(NAME + "V2.0行程更新结果汇报消息处理成功");
			}
		}
		return location;
	}

	/**
	 * 解析V2.0行程文件内容错误信息上传消息报文
	 * 
	 * @param uhc
	 * @return
	 */
	private static void callSms(Up_InfoContent uhc) throws Exception{		                                                                      
    	
		TqcStatisticSQL tqcStatisticSQL = (TqcStatisticSQL) SpringBootStrap.getInstance().getBean("tqcStatisticSQL");
		List<Map<String, String>> listData = tqcStatisticSQL.selectDriverByTrip(uhc.getTrip_code());	
		//String smsTemplate = Config.props.getProperty("msgtemplate");	
		
		Map<String,String> mapData=listData.get(0);
		String driverTel=StringUtil.objToStr(mapData.get("DRIVER_TEL"));	
		String driverName=StringUtil.objToStr(mapData.get("DRIVER_NAME"));
		String vehicleCode=StringUtil.objToStr(mapData.get("VEHICLE_CODE"));
		String tqcMegTemplate=driverName+"司机：您的车牌号为 "+mapData.get("VEHICLE_LN")+"（"+vehicleCode+"）的车辆,今天有以下变动！\r\n";
	    
		
	    String routeName=StringUtil.objToStr(mapData.get("ROUTE_NAME"));
	    String sendConditionCode=StringUtil.objToStr(mapData.get("SEND_CONDITION"));
	    String sendOrder=StringUtil.objToStr(mapData.get("SEND_ORDER"));
	    
	    String entId=StringUtil.objToStr(mapData.get("ENTERPRISE_ID"));
	    String orgId=StringUtil.objToStr(mapData.get("ORGANIZATION_ID"));
	    
	    tqcMegTemplate+="线路名称："+routeName+" \r\n";
	    String sendConditionName="";
	    if(sendConditionCode.equals("0")){
	    	sendConditionName="坐满发车";
	    }else if(sendConditionCode.equals("1")){
	    	sendConditionName="循环发车";
	    }else{
	    	sendConditionName="按时发车";
			SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap.getInstance().getBean("sendCommandDAO");
			List<SmsData> data = sendCommandDAO.getSmsData(uhc.getTrip_code());
			SmsData sd = data.get(0);
			if(null != sd){
				tqcMegTemplate += "发车时间：" + sd.getSendTime() + "\r\n";
				String lineKind = "";
				if(sd.getUpDown().equals("0")){
					lineKind = "上班线路";
				}else if(sd.getUpDown().equals("1")){
					lineKind = "下班线路";
				}else{
					lineKind = "场内通勤线路";
				}
				tqcMegTemplate += "线路类型：" + lineKind + "\r\n";
			}
	    }
	    tqcMegTemplate+="发车条件："+sendConditionName+" \r\n";
	    tqcMegTemplate+="发车顺序："+sendOrder+" \r\n";	    
	    tqcMegTemplate+="祝一路平安！\r\n";
	
	    //拼接对象		
		uhc.setTel(driverTel);// 电话	
		uhc.setRelative_name(driverName);  //司机姓名
		uhc.setParents_flag("0");//0：其他联系人 1：家长
		uhc.setOrganization_id(orgId);// 组织编码
		uhc.setEnterprise_id(entId);// 企业编码
		XCBuildSQL.getInstance().sendMsg2DriverByTqc(uhc, tqcMegTemplate);		
	}


	/**
	 * 解析V2.0行程文件内容错误信息上传消息报文
	 * 
	 * @param uhc
	 * @param bytevalue
	 * @return
	 * @throws java.text.ParseException
	 */
	private static int TripFileFailReport(Up_InfoContent uhc, byte[] bytevalue) {
		int location = 0;

		// 行程编号
		byte[] trip_codebyte = new byte[4];
		System.arraycopy(bytevalue, location, trip_codebyte, 0, 4);
		location += 4;
		String trip_code = Converser.bytesToHexString(trip_codebyte);

		// 行程CRC
		byte[] CRCbyte = new byte[4];
		System.arraycopy(bytevalue, location, CRCbyte, 0, 4);
		location += 4;
		String CRC = Converser.bytesToHexString(CRCbyte);

		// 线路编号
		byte[] route_idbyte = new byte[4];
		System.arraycopy(bytevalue, location, route_idbyte, 0, 4);
		location += 4;
		String route_id = Converser.bytesToHexString(route_idbyte);

		// 上报时间
		byte[] updateComplete_timebyte = new byte[6];
		System.arraycopy(bytevalue, location, updateComplete_timebyte, 0, 6);
		location += 6;
		String updateComplete_time = Converser
				.bytesToHexString(updateComplete_timebyte);

		// 错误信息长度
		byte[] failMessagelenbyte = new byte[1];
		System.arraycopy(bytevalue, location, failMessagelenbyte, 0, 1);
		location += 1;
		String failMessagelen = Converser.bytesToHexString(failMessagelenbyte);
		int fmlen = Integer.parseInt(failMessagelen, 16);

		// 错误信息描述内容
		byte[] failMessagebyte = new byte[fmlen];
		System.arraycopy(bytevalue, location, failMessagebyte, 0, fmlen);
		location += fmlen;
		String failMessage = Converser.bytesToHexString(failMessagebyte);

		uhc.setUpdateComplete_time(updateComplete_time);
		uhc.setRoute_id(String.valueOf(Long.parseLong(route_id, 16)));
		uhc.setTrip_code(String.valueOf(Long.parseLong(trip_code, 16)));
		uhc.setCRC(String.valueOf(Long.parseLong(CRC, 16)));
		uhc.setFailMessage(failMessage);

		DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTripFileFail(uhc));
		DBBuffer.getInstance().add(XCVWBuildSQL.getInstance().buildTripFileFailTemp(uhc));
		log.info(NAME + "V2.0行程文件内容错误信息上传消息处理成功");
		return location;
	}

	@SuppressWarnings("unchecked")
	private static void searchRouteFilesVersionTwo(Up_InfoContent uhc,
			byte[] bytevalue) throws UnsupportedEncodingException,
			SendInfoException, DBErrorException {	
		String id = IdCreater.getUUid();
		// 接收通知，入库
		TerminalCacheManager tc = TerminalCacheManager.getInstance();
		TerminalBean tb = tc.getValue(uhc.getTerminalId());
		SendCommandDAO sendCommandDAO = (SendCommandDAO) SpringBootStrap
				.getInstance().getBean("sendCommandDAO");
		sendCommandDAO.saveQuartzLineCommand(uhc, tb.getSim_card_number(), id);	
		String srcini = null;
		String filepath = null;
		String iniFileSendPath = null;
		if (Config.props.getProperty("iniFileSendPath").equals("/")) {
			iniFileSendPath = "";
		} else {
			iniFileSendPath = Config.props.getProperty("iniFileSendPath");
		}
		if (Config.props.getProperty("iniPath").endsWith("/")) {
			filepath = Config.props.getProperty("iniPath")
					+ uhc.getTerminalId();
		} else {
			filepath = Config.props.getProperty("iniPath") + "/"
					+ uhc.getTerminalId();
		}		
		log.info(NAME +"---filepath-->>:"+filepath);
		File file = new File(filepath);
		if (file.isFile()) {
			log.info(NAME +"文件 :" + file.getName() + "\t");
		}// 是目录的情况
		else {
			File[] files = file.listFiles();
			for (File fileTemp : files) {
				if (fileTemp.isFile()) {
					if (fileTemp.getName().toLowerCase().endsWith(".gz")) {
						srcini = fileTemp.getName();
						break;
					}
				}
			}
		}
		log.info(NAME +"---srcini--->>:"+srcini);		
		if (srcini != null) {			
			SendLineReq req = new SendLineReq();		
			//-------根据核心ID，调用不同的服务器地址
			String coreId=Constant.CORE_ID;  
			log.info(NAME +"---coreId--->>:"+coreId);	
	        String ip=Config.props.getProperty("xcftpip");    
	        /*if(coreId.equals("core01")){
	        	//125.46.82.41
	        	ip=Config.props.getProperty("xcftpip");
	        }
	        if(coreId.equals("core02")){
	        	//61.163.211.129
	        	ip=Config.props.getProperty("xcftpip2");
	        }*/
			//-------修改结束
	        log.info(NAME +"---ip--->>:"+ip);	
			String port = Config.props.getProperty("xcftpport");
			String xcuser = Config.props.getProperty("xcftpuser");
			String xcpass = Config.props.getProperty("xcftppass");			
			req.setCommand("0010");
			req.setStatusCode(SendReq.STATUS);
			req.setSeq(InsideMsgUtils.getSeq());
			req.setMsg_id(id);
			req.setTerminal_id(uhc.getTerminalId());
			req.setIplength(Integer.toHexString(ip.length()));
			req.setIp(ip);
			req.setPort(Integer.toHexString(Integer.parseInt(port)));
			req.setUserlength(Integer.toHexString(xcuser.length()));
			req.setUsername(xcuser);
			req.setPasslength(Integer.toHexString(xcpass.length()));
			req.setUserpass(xcpass);
			req.setFilelength(Integer.toHexString((iniFileSendPath + "/"
					+ uhc.getTerminalId() + "/" + srcini).length()));
			req.setFilename(iniFileSendPath + "/" + uhc.getTerminalId() + "/"
					+ srcini);
			log.info(NAME + "下发行程文件为：" + req.getFilename());
			req.setCrc(Long.toHexString((SendRouteFile.doChecksum(filepath
					+ "/" + uhc.getTerminalId() + "/" + "triplist.ini"))));
			req.setBytetype("80");
			req.setPacket_content(req.getTcToString());
			// 获取文件后，将命令入库
			sendCommandDAO.updateQuartzLine(id, req);
			String url = SendCmdService.cycleSendCommand(req.getBytes());
			if (url != null && !url.equals("")) {
				log.info(NAME + "向" + url + "下发命令成功！");
				Constant.respMap.put(req.getSeq(), id);
				log.debug("Constant.respMap" + Constant.respMap);
			} else {
				log.info(NAME + "平台url未配置,下发命令失败");
				throw new SendInfoException("下发命令失败");
			}
		} else {
			log.info(NAME + " 未找到该车" + uhc.getTerminalId() + "的行程文件");
		}
	}

	private static void CallSmsGatewayVTInterface(Up_InfoContent uhc)
			throws java.text.ParseException {
		// 查询学生缓存信息
		XcStudentBean sb = SendxcmsmCommandManager.getInstance()
				.getStudentValue(Constant.STUDENT + uhc.getStu_id());
		if (sb != null) {// 如果学生信息不为空
			// 更具学号查询缓存中的企业信息
			EnterPriseBean epb = SendxcmsmCommandManager.getInstance()
					.getXcEntepriseValue(
							sb.getEnterprise_id() + Constant.ENTERPRISE);
			if (epb != null) {// 如果企业信息不为空
				if ("1".equals(epb.getEnterprise_smgateway())) {
					// 亿美短信下发接口
					try {
						CallSmsInterfaceModelTwo(uhc, sb);
					} catch (Exception e) {
						e.printStackTrace();
						log.error(NAME + ",短信发送错误");
					}
				}
				// 翔东短信下发接口
				if ("2".equals(epb.getEnterprise_smgateway())) {
					// 根据学号和短信发送类型查询短信配置缓存
					List<XcStuSmsVTBean> smslistbean = SmsOrderManager
							.getInstance().getXcSmsVTValue(
									uhc.getStu_id() + uhc.getSms_eventtype());
					if (smslistbean != null) {// 如果缓存存在该学生信息
						// 短信消息内容
						String message = XCVWBuildSQL.getInstance()
								.isSendMsgModelTowXD(uhc, sb);
						// 将所需信息存入SendMessageReq
						SendMessageReq sendMessageReq = new SendMessageReq();
						sendMessageReq.setStu_id(uhc.getStu_id());
						sendMessageReq.setEvent_type(uhc.getSms_eventtype());
						sendMessageReq.setMessage(message);
						sendMessageReq.setVehicle_vin(uhc.getTerminalId());
						sendMessageReq.setRecordid(uhc.getShuaka_id());
						// 调用短信发送接口
						SendBuffer.getInstance().add(sendMessageReq);
					} else {
						log.info("<BuildSQL>该学生" + uhc.getStu_id()
								+ "翔东短信业务未开通，不下发短信！");
					}
				}
			} else {
				log.info("<BuildSQL>未找到相应企业，不下发短信！");
			}
		} else {
			log.info("<BuildSQL>该学生" + uhc.getStu_id() + "不存在！");
		}
	}

	/**
	 * 无线路刷卡下发短信
	 * 
	 * @param uhc
	 * @param sb
	 * @param
	 * @return
	 * @throws ParseException
	 */

	private static void CallSmsInterfaceModelTwo(Up_InfoContent uhc,
			XcStudentBean sb) throws java.text.ParseException {
		String smsg_state = "";// 发送状态
		String cell_failinfo = "";
		String type = isSendType(uhc);// 打卡类型

		List<XcStuSmsBean> vblist = SendxcmsmCommandManager.getInstance().getValue(uhc.getStu_id() + type);
		log.debug(NAME + "上车未刷卡学生学号为：" + uhc.getStu_id());
		if (uhc.getStu_id() != null && !"".equals(uhc.getStu_id())) {// 判断该学生是否配置
			if (vblist != null && vblist.size() > 0) {
				for (XcStuSmsBean vb : vblist) {
					XCBuildSQL.getInstance().isHasQuotasModelTwo(vb, uhc, sb);
					smsg_state = smsg_state + uhc.getSms_state();
					cell_failinfo += vb.getCell_number() + ":" + uhc.getSms_fail_info() + ";";
				}

				if (smsg_state.contains("1") && smsg_state.contains("2")) {// 判断短信发送是否部分成功
					log.debug(NAME + "群发信息部分成功提示：" + cell_failinfo);
					uhc.setSms_state("3");// 设置发送状态为部分成功
					uhc.setSms_fail_info(subStringMessage(cell_failinfo));
				}
			} else {
				log.info("<BuildSQL>该学生" + uhc.getStu_id() + "短信业务未开通，不下发短信！");
				uhc.setSms_state("4");
				uhc.setSms_fail_info("该学生" + uhc.getStu_id() + "短信业务未开通，不下发短信！");
				log.info("<BuildSQL>更新短信发送状态为:" + uhc.getSms_state());
			}
		}
	}

}