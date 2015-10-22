package com.neusoft.clw.vncs.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.back.Back;
import com.neusoft.clw.vncs.back.BackMap;
import com.neusoft.clw.vncs.cacheBuffer.CacheBean;
import com.neusoft.clw.vncs.cacheBuffer.CacheBuffer;
import com.neusoft.clw.vncs.dao.ICLW_SEC_DATADAO;
import com.neusoft.clw.vncs.dao.impl.TerminalDAO;
import com.neusoft.clw.vncs.downbuffer.DownBuffer;
import com.neusoft.clw.vncs.inside.msg.content.CommandCode;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.inside.msg.req.SendHistoryCmdReq;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;
import com.neusoft.clw.vncs.monitor.SendHistoryCmdBean;

/**
 * 
 * @author <a href="mailto:chenqiong@neusoft.com">chenqiong</a>
 * 
 */
public class SendCmdService {

	private static Logger log = LoggerFactory.getLogger(SendCmdService.class);

	private static final String NAME = "<SendCmdService>";
	
	private static CacheBean cb = new CacheBean();
	
	public static int num = 0;

	@SuppressWarnings("unchecked")
	public static void setCacheMap(String type, Up_InfoContent uhc, Map map) {
		if (type.equals(CommandCode.down2006_25)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_25);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_25, map);

		} else if (type.equals(CommandCode.down2006_22)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_22);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_22, map);
		} else if (type.equals(CommandCode.down2006_23)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_23);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_23, map);
		} else if (type.equals(CommandCode.down2006_24)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_24);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_24, map);
		} else if (type.equals(CommandCode.down2006_12)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_12);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_12, map);
		} else if (type.equals(CommandCode.down2006_13)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_13);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_13, map);
		} else if (type.equals(CommandCode.down2006_14)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_14);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_14, map);
		} else if (type.equals(CommandCode.down2006_15)) {
			cb.setKey(uhc.getTerminalId() + CommandCode.down2006_15);
			cb.setValue(map);
			CacheBuffer.getInstance().add(cb);
			Constant.ytbsendcmdMap.put(uhc.getTerminalId()
					+ CommandCode.down2006_15, map);
		}
	}

	@SuppressWarnings("unchecked")
	public static Map getCacheMap(String type, Up_InfoContent uhc) {
		if (type.equals(CommandCode.down2006_25)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_25) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_25)
								.equals("")) {
					log
							.debug("----------------uhc.getTerminalId()+CommandCode.down2006_25"
									+ Constant.getMemcachedClient().getObject(
											uhc.getTerminalId()
													+ CommandCode.down2006_25));
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_25);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc.getTerminalId()
						+ CommandCode.down2006_25);
			}
		} else if (type.equals(CommandCode.down2006_22)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_22) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_22)
								.equals("")) {
					log
					.debug("----------------uhc.getTerminalId()+CommandCode.down2006_22"
							+ Constant.getMemcachedClient().getObject(
									uhc.getTerminalId()
											+ CommandCode.down2006_22));
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_22);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc.getTerminalId()
						+ CommandCode.down2006_22);
			}
		} else if (type.equals(CommandCode.down2006_23)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_23) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_23)
								.equals("")) {
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_23);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc.getTerminalId()
						+ CommandCode.down2006_23);
			}
		} else if (type.equals(CommandCode.down2006_24)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_24) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_24)
								.equals("")) {
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_24);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc.getTerminalId()
						+ CommandCode.down2006_24);
			}
		} else if (type.equals(CommandCode.down2006_12)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_12) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_12)
								.equals("")) {
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_12);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc
						.getTerminalId()
						+ CommandCode.down2006_12);
			}
		} else if (type.equals(CommandCode.down2006_13)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_13) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_13)
								.equals("")) {
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_13);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc
						.getTerminalId()
						+ CommandCode.down2006_13);
			}
		} else if (type.equals(CommandCode.down2006_14)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_14) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_14)
								.equals("")) {
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_14);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc.getTerminalId()
						+ CommandCode.down2006_14);
			}
		} else if (type.equals(CommandCode.down2006_15)) {
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				if (Constant.getMemcachedClient().getObject(
						uhc.getTerminalId() + CommandCode.down2006_15) != null
						&& !Constant.getMemcachedClient().getObject(
								uhc.getTerminalId() + CommandCode.down2006_15)
								.equals("")) {
					return (Map) Constant.getMemcachedClient().getObject(
							uhc.getTerminalId() + CommandCode.down2006_15);
				} else {
					return null;
				}
			} else {
				return (Map) Constant.ytbsendcmdMap.get(uhc.getTerminalId()
						+ CommandCode.down2006_15);
			}
		} else {
			return null;
		}
	}

	public static void removeClwCache(String type, Up_InfoContent uhc) {
		if (type.equals(CommandCode.down2006_25)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_25);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_25);
		} else if (type.equals(CommandCode.down2006_22)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_22);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_22);
		} else if (type.equals(CommandCode.down2006_23)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_23);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_23);
		} else if (type.equals(CommandCode.down2006_24)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_24);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_24);
		} else if (type.equals(CommandCode.down2006_12)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_12);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_12);
		} else if (type.equals(CommandCode.down2006_13)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_13);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_13);
		} else if (type.equals(CommandCode.down2006_14)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_14);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_14);
		} else if (type.equals(CommandCode.down2006_15)) {
			if (Constant.isstartMemcache.equals("1")) {
				Constant.getMemcachedClient().delete(
						uhc.getTerminalId() + CommandCode.down2006_15);
			}
			Constant.ytbsendcmdMap.remove(uhc.getTerminalId()
					+ CommandCode.down2006_15);
		}
	}

	// public static SendHistoryCmdBean setCmdOrder(String new_time,String
	// cmd,String timebetween){
	// SendHistoryCmdBean cmdbean = new SendHistoryCmdBean();
	// //设置packet_content内容
	// cmdbean.setMsg_id(IdCreater.getUUid());
	// cmdbean.setQuery_field(cmd);
	// cmdbean.setCmd(CommandCode.down2006);
	// //查询到历史数据表中有最近操作的时间，如果没有，设置开始结束时间为当前时间前几分钟开始到现在这段时间
	// if (new_time != null && !new_time.equals("")) {
	// cmdbean.setStart_time(new_time);
	// cmdbean.setEnd_time(DateUtil.changeTime12ToFormat());
	// } else {
	// cmdbean.setEnd_time(DateUtil.changeTime12ToFormat());
	// cmdbean.setStart_time(DateUtil.getDateByDiscreMinutesWithNow(-Integer.parseInt(timebetween)));
	// }
	// return cmdbean;
	// }

	public static void CmdSendService(SendHistoryCmdReq req, Back back) {
		try {
			back.getCommunicateService().send(req.getBytes());
		} catch (Exception e) {
			log.error(NAME+"下发命令失败"+ e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public static void addSendCmdMap(SendHistoryCmdBean cmdbean, String vin,
			Map ytbmap) {
		// 设置缓存
		// Map<String, String> ytbmap = new HashMap<String, String>();
		ytbmap.put("msg_id", cmdbean.getMsg_id());
		// ytbmap.put("query_field", cmdbean.getQuery_field());
		TerminalDAO terminalDAO = (TerminalDAO) SpringBootStrap.getInstance()
				.getBean("terminalDAO");
		ytbmap.put("currenttime", terminalDAO.getSysTime());
		ytbmap.put("start_time", cmdbean.getStart_time());
		// ytbmap.put("end_time", cmdbean.getEnd_time());
		// ytbmap.put("cmd", cmdbean.getCmd());
		// ytbmap.put("vin", vin);
		log.debug("!!!!!!!~~~~~~~~~~~~~~ytbmap" + ytbmap);
		if (Constant.isstartMemcache.equals("1")) {
			if (Constant.getMemcachedClient().connectState()) {
				Constant.getMemcachedClient().insert(
						vin + cmdbean.getQuery_field(), 0, ytbmap);
				// System.out.println(Constant.getMemcachedClient().getObject(vin+cmdbean.getQuery_field()));
			}
			Constant.ytbsendcmdMap.put(vin + cmdbean.getQuery_field(), ytbmap);
			log.debug(""
					+ Constant.ytbsendcmdMap
							.get(vin + cmdbean.getQuery_field()));
		} else {
			Constant.ytbsendcmdMap.put(vin + cmdbean.getQuery_field(), ytbmap);
			log.debug(""
					+ Constant.ytbsendcmdMap
							.get(vin + cmdbean.getQuery_field()));
		}
	}

	public static void removeSendCmdMap(String vin, String cmd) {
		if (Constant.isstartMemcache.equals("1")) {
			if (Constant.getMemcachedClient().connectState()) {
				Constant.getMemcachedClient().delete(vin + cmd);
			}
			Constant.ytbsendcmdMap.remove(vin + cmd);
		} else {
			Constant.ytbsendcmdMap.remove(vin + cmd);
		}
	}

	@SuppressWarnings("unchecked")
	public static Map getSendCmdMap(String vin, String cmd) {
		Map map = null;
		if (Constant.isstartMemcache.equals("1")) {
			if (Constant.getMemcachedClient().connectState()) {
				map = (Map) Constant.getMemcachedClient().getObject(vin + cmd);
			} else {
				map = (Map) Constant.ytbsendcmdMap.get(vin + cmd);
			}
		} else {
			map = (Map) Constant.ytbsendcmdMap.get(vin + cmd);
		}
		return map;
	}

	/**
	 * // * 上行历史信息，如果时间未达到结束时间，则继续进行下发宇通杯秒数据命令， 否则，清除缓存，等待下一次定时任务触发进行任务下发
	 * 
	 * @param urt
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public static void isContinueSendYTBCmd(Up_InfoContent urt)
			throws ParseException {
		Map map = getSendCmdMap(urt.getTerminalId(), CommandCode.down2006_25);
		// System.out.println(map.size());
		if (map != null && map.size() > 0) {
			String end_time = (String) map.get("end_time");
//			String start_time = (String) map.get("start_time");
			log.debug("+++++++++++++++++++++++++++++++++" + end_time
					+ "+++++++++++++++++++++++++++++++++++++++");
			log.debug("%%%%%%%%%%%%%%%%%%%%%%" + urt.getTime()
					+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			// log.error(")()()("+DateUtil.string2date("yyMMddHHmmss",urt.getTime().substring(0,12)));
			// if(DateUtil.string2date("yyMMddHHmmss",urt.getTime().substring(0,12)).after(DateUtil.string2date("yyMMddHHmmss",start_time))){
			// if(DateUtil.string2date("yyMMddHHmmss",urt.getTime().substring(0,12)).before(DateUtil.string2date("yyMMddHHmmss",end_time))){
			if (urt.getTime().compareTo(end_time) >= 0) {
				log.info(NAME+
				"下发时间断内的历史数据已全部接收，清除缓存中该时间段信息");
				removeSendCmdMap(urt.getTerminalId(), CommandCode.down2006_25);
				log.debug("################"
				+ Constant.ytbsendcmdMap.get(urt.getTerminalId()
						+ CommandCode.down2006_25));
				log.info(NAME+ "缓存清除成功");
			} else {
				log.info(NAME+
				"未完全接收下发时间段数据，继续下发剩余时间段查询命令");
				SendHistoryCmdBean cmdbean = new SendHistoryCmdBean();
				SendHistoryCmdReq req = new SendHistoryCmdReq();
				req.setTerminalId(urt.getTerminalId());
				// 设置packet_content内容
				cmdbean.setMsg_id(IdCreater.getUUid());
				cmdbean.setQuery_field(CommandCode.down2006_25);
				cmdbean.setCmd(CommandCode.down2006);
				// 查询到ytb秒数据表中有最近操作的时间，如果没有，
				// 设置开始结束时间为当前时间前几分钟开始到现在这段时间
				cmdbean.setStart_time(DateUtil.getDate15ByDiscreSecondWithNow12(urt.getTime(), 1));
				cmdbean.setEnd_time(end_time);
				log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~3"
						+ cmdbean.getStart_time());
				log.debug("@~@@@@@@@@@@@@@@@@@@@@@@@@@@@@~~~~~4"
						+ cmdbean.getEnd_time());
		//		req.setCmdbean(cmdbean);
				// 继续下发
				log.info(NAME+ "车辆vin:"
						+ urt.getTerminalId() + "下发命令串:"
						+ new String(req.getBytes()));
		//		CmdSendService(req, BackMap.getInstance().get(
		//				Config.props.get("backAddress")));
				CmdSendService(req, BackMap.getInstance().get(Config.props.get("backAddress")));
				log.info(NAME+
						"历史数据查询命令下发成功");
				// 设置缓存
				addSendCmdMap(cmdbean, urt.getTerminalId(), map);
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+cmdbean.getQuery_field()));
				log.info(NAME+ "缓存" + urt.getTerminalId()+ cmdbean.getQuery_field() + "成功");
			}
		} else {
			log.info(NAME+ urt
					.getTerminalId()
					+ CommandCode.down2006_25 + "不存在，不进行历史数据查询下发");
		}
	}

	@SuppressWarnings("unchecked")
	public static void isSendClw1SecCmd(Up_InfoContent urt,String currenttime) throws Exception {
		Map map = getCacheMap(urt.getType(), urt);
		if (map != null && map.size() > 0) {
			String end_time = (String) map.get("end_time");
//			String start_time = (String) map.get("start_time");
			log.debug("+++++++++++++++++++++++++++++++++" + end_time
					+ "+++++++++++++++++++++++++++++++++++++++");
			log.debug("%%%%%%%%%%%%%%%%%%%%%%" + urt.getTime()
					+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			// log.error(")()()("+DateUtil.string2date("yyMMddHHmmss",urt.getTime().substring(0,12)));
			if (urt.getTime().compareTo(end_time) >= 0) {
				log.info(NAME+
				"下发时间断内的车联网秒数据已全部接收，清除缓存中该时间段信息");
				removeClwCache(urt.getType(), urt);
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+CommandCode.down2006_25));
				log.info(NAME+ "缓存清除成功");
			} else {
				log.info(NAME+
				"未完全接收下发时间段数据，继续下发剩余时间段查询命令");
				SendHistoryCmdBean cmdbean = new SendHistoryCmdBean();
				SendHistoryCmdReq req = new SendHistoryCmdReq();
				req.setTerminalId(urt.getTerminalId());
				// 设置packet_content内容
				cmdbean.setMsg_id(IdCreater.getUUid());
				cmdbean.setQuery_field(urt.getType());
				cmdbean.setCmd(CommandCode.down2006);
				// 查询到车联网历史数据表中有最近操作的时间，如果没有，
				// 设置开始结束时间为当前时间前几分钟开始到现在这段时间
				cmdbean.setStart_time(DateUtil.getDate15ByDiscreSecondWithNow12(urt.getTime(), 5));
				cmdbean.setEnd_time(end_time);
				cmdbean.setVin(urt.getTerminalId());
				cmdbean.setSystime(currenttime);
				log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~3"
						+ cmdbean.getStart_time());
				log.debug("@~@@@@@@@@@@@@@@@@@@@@@@@@@@@@~~~~~4"
						+ cmdbean.getEnd_time());
				req.setCmdbean(cmdbean);
				cmdbean.setBytes(req.getBytes());
				// 继续下发
				DownBuffer.getInstance().add(cmdbean);
				// log.info(NAME+
				// "车联网秒数据下发命令串:"+new String(req.getBytes())));
				// String url = cycleSendCommand(req.getBytes());
				// if(url!=null&&!url.equals("")){
				// map.put("start_time", start_time);
				// log.info(NAME+
				// "车联网秒数据查询命令下发成功"));
				// //设置缓存
				// setCacheMap(urt.getType(),urt,map);
				// //
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+cmdbean.getQuery_field()));
				// log.info(NAME+
				// "缓存车联网秒数据查询命令"+urt.getTerminalId()+cmdbean.getQuery_field()+"成功"));
				// }else{
				// log.info(NAME+
				// "车联网秒数据查询命令下发失败"));
				// }
			}
		} else {
			log.info(NAME+ urt
					.getTerminalId()
					+ urt.getType() + "不存在，不进行车联网秒数据查询下发");
		}
	}

	/**
	 * 上报时间为15位的继续下发命令 (急加速记录)
	 * 
	 * @param urt
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void isSendClwRapidCmd(Up_InfoContent urt,String currenttime) throws Exception {
		Map map = getCacheMap(urt.getType(), urt);
		if (map != null && map.size() > 0) {
			String end_time = (String) map.get("end_time");
//			String start_time = (String) map.get("start_time");
			log.debug("+++++++++++++++++++++++++++++++++" + end_time
					+ "+++++++++++++++++++++++++++++++++++++++");
			log.debug("%%%%%%%%%%%%%%%%%%%%%%"
					+ urt.getClw_rapid_onedata_time()
					+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			// log.error(")()()("+DateUtil.string2date("yyMMddHHmmss",urt.getTime().substring(0,12)));
			if (urt.getClw_rapid_onedata_time().compareTo(end_time) >= 0) {
				log.info(NAME+
				"下发时间断内的历史数据已全部接收，清除缓存中该时间段信息");
				removeClwCache(urt.getType(), urt);
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+CommandCode.down2006_25));
				log.info(NAME+ "缓存清除成功");
			} else {
				log.info(NAME+
				"未完全接收下发时间段数据，继续下发剩余时间段查询命令");
				SendHistoryCmdBean cmdbean = new SendHistoryCmdBean();
				SendHistoryCmdReq req = new SendHistoryCmdReq();
				req.setTerminalId(urt.getTerminalId());
				// 设置packet_content内容
				cmdbean.setMsg_id(IdCreater.getUUid());
				cmdbean.setQuery_field(urt.getType());
				cmdbean.setCmd(CommandCode.down2006);
				// 查询到车联网历史数据表中有最近操作的时间，如果没有，
				// 设置开始结束时间为当前时间前几分钟开始到现在这段时间
				cmdbean.setStart_time(DateUtil.getDate15ByDiscreSecondWithNow12(urt.getClw_rapid_onedata_time(), 1));
				cmdbean.setEnd_time(end_time);
				cmdbean.setVin(urt.getTerminalId());
				cmdbean.setSystime(currenttime);
				log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~3"
						+ cmdbean.getStart_time());
				log.debug("@~@@@@@@@@@@@@@@@@@@@@@@@@@@@@~~~~~4"
						+ cmdbean.getEnd_time());
				req.setCmdbean(cmdbean);
				cmdbean.setBytes(req.getBytes());
				// 继续下发
				DownBuffer.getInstance().add(cmdbean);
				// log.info(NAME+
				// "车联网开关量数据下发命令串:"+new String(req.getBytes())));
				// String url = cycleSendCommand(req.getBytes());
				// if(url!=null&&!url.equals("")){
				// map.put("start_time", start_time);
				// TerminalDAO terminalDAO = (TerminalDAO)
				// SpringBootStrap.getInstance().getBean("terminalDAO");
				// map.put("currenttime", terminalDAO.getSysTime());
				// log.info(NAME+
				// "车联网开关量数据查询命令下发成功"));
				// //设置缓存
				// setCacheMap(urt.getType(),urt,map);
				// //
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+cmdbean.getQuery_field()));
				// log.info(NAME+
				// "缓存"+urt.getTerminalId()+cmdbean.getQuery_field()+"成功"));
				// }else{
				// log.info(NAME+
				// "车联网开关量数据查询命令下发失败"));
				// }
			}
		} else {
			log.info(NAME+ urt
					.getTerminalId()
					+ urt.getType() + "不存在，不进行车联网开关量数据查询下发");
		}
	}

	/**
	 * 车联网1分钟数据、车联网5分钟数据
	 * 
	 * @param urt
	 * @param minute
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void isSendClwMCmd(Up_InfoContent urt, int minute,String currenttime)
			throws Exception {
		Map map = getCacheMap(urt.getType(), urt);
		if (map != null && map.size() > 0) {
			String end_time = (String) map.get("end_time");
//			String start_time = (String) map.get("start_time");
			log.debug("+++++++++++++++++++++++++++++++++" + end_time
					+ "+++++++++++++++++++++++++++++++++++++++");
			log.debug("%%%%%%%%%%%%%%%%%%%%%%" + urt.getTime()
					+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			// log.error(")()()("+DateUtil.string2date("yyMMddHHmmss",urt.getTime().substring(0,12)));
			if (urt.getTime().compareTo(end_time) >= 0) {
				log.info(NAME+ "下发时间断内的车联网"
						+ minute + "分钟数据已全部接收，清除缓存中该时间段信息");
				removeClwCache(urt.getType(), urt);
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+CommandCode.down2006_25));
				log.info(NAME+ "缓存清除成功");
			} else {
				log.info(NAME+
				"未完全接收下发时间段数据，继续下发剩余时间段查询命令");
				SendHistoryCmdBean cmdbean = new SendHistoryCmdBean();
				SendHistoryCmdReq req = new SendHistoryCmdReq();
				req.setTerminalId(urt.getTerminalId());
				// 设置packet_content内容
				cmdbean.setMsg_id(IdCreater.getUUid());
				cmdbean.setQuery_field(urt.getType());
				cmdbean.setCmd(CommandCode.down2006);
				// 查询到车联网历史数据表中有最近操作的时间，如果没有，
				// 设置开始结束时间为当前时间前几分钟开始到现在这段时间
				cmdbean.setStart_time(DateUtil.getDate15ByDiscreMinuteWithNow12(urt.getTime(), minute));
				cmdbean.setEnd_time(end_time);
				cmdbean.setVin(urt.getTerminalId());
				cmdbean.setSystime(currenttime);
				log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~3"
						+ cmdbean.getStart_time());
				log.debug("@~@@@@@@@@@@@@@@@@@@@@@@@@@@@@~~~~~4"
						+ cmdbean.getEnd_time());
				req.setCmdbean(cmdbean);
				cmdbean.setBytes(req.getBytes());
				// 继续下发
				DownBuffer.getInstance().add(cmdbean);
				// log.info(NAME+
				// "车联网"+minute+"分钟数据下发命令串"+new String(req.getBytes())));
				// String url = cycleSendCommand(req.getBytes());
				// if(url!=null&&!url.equals("")){
				// map.put("start_time", start_time);
				// TerminalDAO terminalDAO = (TerminalDAO)
				// SpringBootStrap.getInstance().getBean("terminalDAO");
				// map.put("currenttime", terminalDAO.getSysTime());
				// log.info(NAME+
				// "车联网"+minute+"分钟数据查询命令下发成功"));
				// //设置缓存
				// setCacheMap(urt.getType(),urt,map);
				// //
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+cmdbean.getQuery_field()));
				// log.info(NAME+
				// "缓存"+urt.getTerminalId()+cmdbean.getQuery_field()+"成功"));
				// }else{
				// log.info(NAME+
				// "车联网"+minute+"分钟数据查询命令下发失败"));
				// }
			}
		} else {
			log.info(NAME+ urt
					.getTerminalId()
					+ urt.getType() + "不存在，不进行车联网" + minute + "分钟数据查询下发");
		}
	}

	/**
	 * 登陆记录、车辆超速记录、开关量记录命令继续下发
	 * 
	 * @param urt
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void isSendClw12Cmd(Up_InfoContent urt,String currenttime) throws Exception {
		Map map = getCacheMap(urt.getType(), urt);
		if (map != null && map.size() > 0) {
			String end_time = (String) map.get("end_time");
//			String start_time = (String) map.get("start_time");
			log.debug("+++++++++++++++++++++++++++++++++" + end_time
					+ "+++++++++++++++++++++++++++++++++++++++");
			log.debug("%%%%%%%%%%%%%%%%%%%%%%" + urt.getTime()
					+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			// log.error(")()()("+DateUtil.string2date("yyMMddHHmmss",urt.getTime().substring(0,12)));
			if (urt.getTime().substring(0, 12).compareTo(end_time) >= 0) {
				log.info(NAME+
				"下发时间断内的历史数据已全部接收，清除缓存中该时间段信息");
				removeClwCache(urt.getType(), urt);
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+CommandCode.down2006_25));
				log.info(NAME+ "缓存清除成功");			
			} else {
				log.info(NAME+
				"未完全接收下发时间段数据，继续下发剩余时间段查询命令");
				SendHistoryCmdBean cmdbean = new SendHistoryCmdBean();
				SendHistoryCmdReq req = new SendHistoryCmdReq();
				req.setTerminalId(urt.getTerminalId());
				// 设置packet_content内容
				cmdbean.setMsg_id(IdCreater.getUUid());
				cmdbean.setQuery_field(urt.getType());
				cmdbean.setCmd(CommandCode.down2006);
				cmdbean.setVin(urt.getTerminalId());
				// 查询到车联网历史数据表中有最近操作的时间，如果没有，
				// 设置开始结束时间为当前时间前几分钟开始到现在这段时间
				cmdbean.setStart_time(DateUtil.getDate12ByDiscreSecondWithNow(urt.getTime(), 1));
				cmdbean.setEnd_time(end_time);
				cmdbean.setSystime(currenttime);
				log.debug("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~3"
						+ cmdbean.getStart_time());
				log.debug("@~@@@@@@@@@@@@@@@@@@@@@@@@@@@@~~~~~4"
						+ cmdbean.getEnd_time());
				req.setCmdbean(cmdbean);
				cmdbean.setBytes(req.getBytes());
				// 继续下发
				DownBuffer.getInstance().add(cmdbean);
				// log.info(NAME+
				// "历史数据下发命令串:"+new String(req.getBytes())));
				// String url = cycleSendCommand(req.getBytes());
				// if(url!=null&&!url.equals("")){
				// map.put("start_time", start_time);
				// TerminalDAO terminalDAO = (TerminalDAO)
				// SpringBootStrap.getInstance().getBean("terminalDAO");
				// map.put("currenttime", terminalDAO.getSysTime());
				// log.info(NAME+
				// "历史数据查询命令下发成功"));
				// log.debug("!)!)!)!)!)!)!map"+map);
				// //设置缓存
				// setCacheMap(urt.getType(),urt,map);
				// //
				// log.error("################"+Constant.ytbsendcmdMap.get(urt.getTerminalId()+cmdbean.getQuery_field()));
				// log.info(NAME+
				// "缓存"+urt.getTerminalId()+cmdbean.getQuery_field()+"成功"));
				// }else{
				// log.info(NAME+
				// "历史数据查询命令下发失败"));
				// }
			}
		} else {
			log.info(NAME+ urt
					.getTerminalId()
					+ urt.getType() + "不存在，不进行历史数据查询下发");
		}
	}	

	public static String cycleSendCommand(byte[] buf){
		String baddress = null;
//		if (Constant.array != null && Constant.array.size() > 0) {
		
		BackMap map = BackMap.getInstance();
		int i = 0;
//		int len = map.size();
		while (i < 20) {
			i++;
//			System.out.println(map.getBacklist());
			Back back = map.getList();
			if(back==null){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					log.error(NAME+"cycleSendCommand(buf) error:"+e.getMessage());
				}
				continue;
			}
			baddress = back.getAddress();
			if (back.getCommunicateService().isAvailable()) {
				try {
					back.getCommunicateService().send(buf);
				} catch (Exception e) {
					continue;
				}
				break;
			} 
			if (i >= 20) {
				log.info("<cycleSendCommand>无有效连接，发送失败");
				return null;
			}
		}
//		} else {
//			log
//					.info(LogFormatter.formatMsg("cycleSendCommand",
//							"当前无有效连接，发送失败"));
//			return "";
//		}
		return baddress;
	}

	public static void cycleSendCommand(SendHistoryCmdBean cmdbean)
			throws Exception {
		BackMap map = BackMap.getInstance();
		int i = 0;
//		int len = map.size();
		while (i < 20) {
			i++;
//			System.out.println(map.getBacklist());
			Back back = map.getList();
			if(back==null){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					log.error(NAME+"cycleSendCommand(cmdbean) error:"+e.getMessage());
				}
				continue;
			}
			if (back.getCommunicateService().isAvailable()) {
				try {
					back.getCommunicateService().send(cmdbean.getBytes());
					SendState(cmdbean, back.getAddress());
					break;
				} catch (Exception e) {
					continue;
				}
			} 
			if (i >= 20) {
				log.info("<cycleSendCommand>无有效连接，发送失败");
			}
		}
//		return baddress;
	}

	@SuppressWarnings("unchecked")
	private static void SendState(SendHistoryCmdBean cmdbean, String baddress) {
		if (cmdbean.getQuery_field().equals(CommandCode.down2006_22)) {
			log.info("<cycleSendCommand>向" + baddress
					+ "下发车联网秒数据命令成功！");
		} else if (cmdbean.getQuery_field().equals(CommandCode.down2006_23)) {
			log.info("<cycleSendCommand>向" + baddress
					+ "下发车联网1分钟命令成功！");
		} else if (cmdbean.getQuery_field().equals(CommandCode.down2006_24)) {
			log.info("<cycleSendCommand>向" + baddress
					+ "下发车联网5分钟命令成功！");
		} else if (cmdbean.getQuery_field().equals(CommandCode.down2006_15)) {
			log.info("<cycleSendCommand>向" + baddress
					+ "下发车联网急加速命令成功！");
		} else if (cmdbean.getQuery_field().equals(CommandCode.down2006_14)) {
			log.info("<cycleSendCommand>向" + baddress
					+ "下发车联网开关量命令成功！");
		} else {
			System.out.println("==================================");
		}
		Map<String, String> ytbmap = new HashMap<String, String>();
		ytbmap.put("msg_id", cmdbean.getMsg_id());
		log.debug("msg_id="+cmdbean.getMsg_id());
		ytbmap.put("query_field", cmdbean.getQuery_field());
		ytbmap.put("start_time", cmdbean.getStart_time());
		ytbmap.put("end_time", cmdbean.getEnd_time());
		ytbmap.put("cmd", cmdbean.getCmd());
		ytbmap.put("vin", cmdbean.getVin());
		ytbmap.put("currenttime", cmdbean.getSystime());
		log.debug("ytbmap="+ytbmap);
		if (Constant.isstartMemcache.equals("1")) {
			if (Constant.getMemcachedClient().connectState()) {
				Constant.getMemcachedClient().insert(
						cmdbean.getVin() + cmdbean.getQuery_field(), ytbmap);
				log.debug(""
						+ Constant.getMemcachedClient().getObject(
								cmdbean.getVin() + cmdbean.getQuery_field()));
			}
			Constant.ytbsendcmdMap.put(cmdbean.getVin()
					+ cmdbean.getQuery_field(), ytbmap);
			// log.error(""+Constant.sendcmdMap.get(tb.getVehicle_vin()+cmdbean.getQuery_field()));
		} else {
			Constant.ytbsendcmdMap.put(cmdbean.getVin()
					+ cmdbean.getQuery_field(), ytbmap);
		}
	}

	public static void existHis(ICLW_SEC_DATADAO clwSecDataDAO,
			String vehicleVin) {
		int num = clwSecDataDAO.getNum(vehicleVin);
		if(num==0){
			clwSecDataDAO.insertHis_Time(vehicleVin);
		}
	}
}
