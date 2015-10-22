package com.neusoft.clw.vncs.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.exception.MemcachedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.cachemanager.Vehicle_RegionCacheManager;
import com.neusoft.clw.vncs.domain.Vehicle_RegionBean;
import com.neusoft.clw.vncs.inside.msg.content.CommandCode;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.Converser;

public class CachePattern {

	private static Logger log = LoggerFactory.getLogger(CachePattern.class);

	private static final String NAME = "<CachePattern>";

	private static final String num0 = "0";
	private static final String num1 = "1";
	private static final String num2 = "2";

	public static final CachePattern processor = new CachePattern();

	public static CachePattern getInstance() {
		return processor;
	}

	/**
	 * 根据sos报警类型，设置报警入库sql
	 * 
	 * @param urt
	 * @param id 
	 * @param uuid
	 * @throws ParseException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	@SuppressWarnings("unchecked")
	public void setAlarmSql(Up_InfoContent urt, String id) throws ParseException {

		// 判断是否需要启用memcache的标志，1-需要 0-不需要
//		if (Constant.isstartMemcache.equals("1")
//				&& Constant.getMemcachedClient().connectState()) {
//			// 判断memcache是否处于连接状态，true-已连接 false-未连接
//			// 连接时就从memcache中读取缓存，否则需要读取本机缓存
//			if (urt.getAlarm_state() != null
//					&& !urt.getAlarm_state().equals("")) {
//				// setSosAlarmMemCache(urt, IdCreater.getUUid());
//				// 超速memcache
//				setOverSpeedAlarmMemCache(urt, IdCreater.getUUid());
//				// 疲劳驾驶memcache
//				setFatigueAlarmMemCache(urt, IdCreater.getUUid());
//				// gps超速memcache
//				setGpsAlarmMemCache(urt, IdCreater.getUUid());
//			} else {
//				log.info(NAME + "未接收到报警信号，处理结束");
//			}
//			// if(urt.getQuad_id_type()!=null&&!urt.getQuad_id_type().equals("")){
//			// setRegion_OverspeedAlarmMemCache(urt, IdCreater.getUUid());
//			// setRegion_InAlarmMemCache(urt, IdCreater.getUUid());
//			// setRegion_OutAlarmMemCache(urt, IdCreater.getUUid());
//			// setRegion_OpenCloseDoorAlarmMemCache(urt, IdCreater.getUUid());
//			// }
//			// else{
//			// log.info(NAME+ "未接收到区域报警标识，处理结束");
//			// }
//		} else {
//			if (urt.getAlarm_state() != null
//					&& !urt.getAlarm_state().equals("")) {
//				// setSosAlarmCache(urt, IdCreater.getUUid());
//				// 超速
//				setOverSpeedAlarmCache(urt, IdCreater.getUUid());
//				// 疲劳驾驶
//				setFatigueAlarmCache(urt, IdCreater.getUUid());
//				// gps超速
//				setGpsAlarmCache(urt, IdCreater.getUUid());
//
//			} else {
//				log.info(NAME + "未接收到报警信号，处理结束");
//			}
//			// if(urt.getQuad_id_type()!=null&&!urt.getQuad_id_type().equals("")){
//			// setRegion_OverspeedAlarmCache(urt, IdCreater.getUUid());
//			// setRegion_InAlarmCache(urt, IdCreater.getUUid());
//			// setRegion_OutAlarmCache(urt, IdCreater.getUUid());
//			// setRegion_OpenCloseDoorAlarmCache(urt, IdCreater.getUUid());
//			// }
//			// else{
//			// log.info(NAME+ "未接收到区域报警标识，处理结束");
//			// }
//		}
//		System.out.println(urt.getAlarm_state());
//		System.out.println(urt.getXcstate());
		Map<String, String> map = new HashMap<String, String>();
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			Object obj = Constant.getMemcachedClient().getObject(Constant.ALARM+urt.getTerminalId());
			if(obj!=null&&!obj.equals("")){
				map = (Map)obj;
			}else{
				map = Constant.cacheMap.get(Constant.ALARM+urt.getTerminalId());
			}
		}else{
			map = Constant.cacheMap.get(Constant.ALARM+urt.getTerminalId());
		}
		String alarm_state_old = (map!=null&&map.size()>0)?map.get("alarm_state"):null;
		String xcstate_old = (map!=null&&map.size()>0)?map.get("xcstate"):null;
		String alarm_seq = (map!=null&&map.size()>0)?map.get("alarm_seq"):null;
		if (urt.getAlarm_state() != null && !urt.getAlarm_state().equals("")) {
			setNew_RealTimeAlarmState(urt,id,alarm_state_old,alarm_seq);
		}
		if (urt.getXcstate() != null && !urt.getXcstate().equals("")) {
			setXC_RealTimeAlarmState(urt,id,xcstate_old);
		}
		setCachePattern(urt);
	}

	/**
	 * 设置区域内开关门告警入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_OpenCloseDoorAlarmCache(Up_InfoContent urt,
			String uuid) throws ParseException {
		Map map = null;
		if (urt.getRegion_openclose_alert() != null
				&& !urt.getRegion_openclose_alert().equals("")) {
			log
					.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>区域内开关门告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId()
					+ urt.getRegion_openclosedoor_alarm_type_id());
			if (urt.getRegion_openclose_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>区域内开关门告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_OpenCloseDoorAlarmSql(
											urt, uuid));
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>update 告警表中区域内开关门告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OpenCloseDoorState(urt));
					// log.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>update 终端"+urt.getTerminalId()+"区域内开关门告警状态：开启");
					setCachePattern(urt, uuid, urt
							.getRegion_openclosedoor_alarm_type_id(), urt
							.getRegion_openclose_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_openclosedoor_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>区域内开关门告警已开启，*本地缓存*车辆"
									+ urt.getTerminalId() + " 区域内开关门告警信息成功");
				}
			} else if (urt.getRegion_openclose_alert().equals(Constant.OFF)) {
				if (map != null && map.size() > 0) {
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>区域内开关门告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateRegion_OpenCloseDoorAlarmSql(
											urt, map));
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>update 告警表中区域内开关门告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OpenCloseDoorState(urt));
					// log.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>update 终端"+urt.getTerminalId()+"区域内开关门告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_openclosedoor_alarm_type_id());
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>区域内开关门告警已关闭，清除车辆"
									+ urt.getTerminalId() + " 区域内开关门告警*本地缓存*成功");
				}
			}
			log
					.info("<CachePattern-setRegion_OpenCloseDoorAlarmCache>区域内开关门告警处理结束");
		}
	}

	/**
	 * 设置出区域告警入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_OutAlarmCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		Map map = null;
		if (urt.getRegion_out_alert() != null
				&& !urt.getRegion_out_alert().equals("")) {
			log.info("<CachePattern-setRegion_OutAlarmCache>出区域告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId()
					+ urt.getRegion_out_alarm_type_id());
			if (urt.getRegion_out_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setRegion_OutAlarmCache>出区域告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_OutAlarmSql(urt, uuid));
					log
							.info("<CachePattern-setRegion_OutAlarmCache>update 告警表中出区域告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OutState(urt));
					// log.info("<CachePattern-setRegion_OutAlarmCache>update 终端"+urt.getTerminalId()+"出区域告警状态：开启");
					setCachePattern(urt, uuid, urt
							.getRegion_out_alarm_type_id(), urt
							.getRegion_out_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_out_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_OutAlarmCache>出区域告警已开启，*本地缓存*车辆"
									+ urt.getTerminalId() + " 出区域告警信息成功");
				}
			} else if (urt.getRegion_out_alert().equals(Constant.OFF)) {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setRegion_OutAlarmCache>出区域告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateRegion_OutAlarmSql(urt, map));
					log
							.info("<CachePattern-setRegion_OutAlarmCache>update 告警表中出区域告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OutState(urt));
					// log.info("<CachePattern-setRegion_OutAlarmCache>update 终端"+urt.getTerminalId()+"出区域告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_out_alarm_type_id());
					log
							.info("<CachePattern-setRegion_OutAlarmCache>出区域告警已关闭，清除车辆"
									+ urt.getTerminalId() + " 出区域告警*本地缓存*成功");
				}
			}
			log.info("<CachePattern-setRegion_OutAlarmCache>出区域告警处理结束");
		}
	}

	/**
	 * 设置入区域告警入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_InAlarmCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		Map map = null;
		if (urt.getRegion_in_alert() != null
				&& !urt.getRegion_in_alert().equals("")) {
			log.info("<CachePattern-setRegion_InAlarmCache>入区域告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId()
					+ urt.getRegion_in_alarm_type_id());
			if (urt.getRegion_in_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setRegion_InAlarmCache>入区域告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_InAlarmSql(urt, uuid));
					log
							.info("<CachePattern-setRegion_InAlarmCache>update 告警表中入区域告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_InState(urt));
					// log.info("<CachePattern-setRegion_InAlarmCache>update 终端"+urt.getTerminalId()+"入区域告警状态：开启");
					setCachePattern(urt, uuid,
							urt.getRegion_in_alarm_type_id(), urt
									.getRegion_in_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_in_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_InAlarmCache>入区域告警已开启，*本地缓存*车辆"
									+ urt.getTerminalId() + " 入区域告警信息成功");
				}
			} else {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setRegion_InAlarmCache>入区域告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateRegion_InAlarmSql(urt, map));
					log
							.info("<CachePattern-setRegion_InAlarmCache>update 告警表中入区域告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_InState(urt));
					// log.info("<CachePattern-setRegion_InAlarmCache>update 终端"+urt.getTerminalId()+"入区域告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_in_alarm_type_id());
					log
							.info("<CachePattern-setRegion_InAlarmCache>入区域告警已关闭，清除车辆"
									+ urt.getTerminalId() + " 入区域告警*本地缓存*成功");
				}
			}
			log.info("<CachePattern-setRegion_InAlarmCache>入区域告警处理结束");
		}
	}

	/**
	 * 设置区域内开关门告警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_OpenCloseDoorAlarmMemCache(Up_InfoContent urt,
			String uuid) throws ParseException {
		Map map = null;
		if (urt.getRegion_openclose_alert() != null
				&& !urt.getRegion_openclose_alert().equals("")) {
			log
					.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>区域内开关门告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId()
							+ urt.getRegion_openclosedoor_alarm_type_id()) != null
					&& !Constant
							.getMemcachedClient()
							.getObject(
									urt.getTerminalId()
											+ urt
													.getRegion_openclosedoor_alarm_type_id())
							.equals("")) {
				map = (Map) Constant.getMemcachedClient().getObject(
						urt.getTerminalId()
								+ urt.getRegion_openclosedoor_alarm_type_id());
			}
			if (urt.getRegion_openclose_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>区域内开关门告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_OpenCloseDoorAlarmSql(
											urt, uuid));
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>update 告警表中区域内开关门告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OpenCloseDoorState(urt));
					// log.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>update 终端"+urt.getTerminalId()+"区域内开关门告警状态：开启");
					setCachePattern(urt, uuid, urt
							.getRegion_openclosedoor_alarm_type_id(), urt
							.getRegion_openclose_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_openclosedoor_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>区域内开关门告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + " 区域内开关门告警信息成功");
				}
			} else {
				if (map != null && map.size() > 0) {
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>区域内开关门告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateRegion_OpenCloseDoorAlarmSql(
											urt, map));
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>update 告警表中出区域内开关门告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OpenCloseDoorState(urt));
					// log.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>update 终端"+urt.getTerminalId()+"区域内开关门告警状态：关闭");
					Constant
							.getMemcachedClient()
							.delete(
									urt.getTerminalId()
											+ urt
													.getRegion_openclosedoor_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_openclosedoor_alarm_type_id());
					log
							.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>区域内开关门告警已关闭，清除车辆"
									+ urt.getTerminalId()
									+ " 区域内开关门告警*memcache缓存*成功");
				}
			}
			log
					.info("<CachePattern-setRegion_OpenCloseDoorAlarmMemCache>区域内开关门告警处理结束");
		}
	}

	/**
	 * 设置出区域告警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_OutAlarmMemCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		Map map = null;
		if (urt.getRegion_out_alert() != null
				&& !urt.getRegion_out_alert().equals("")) {
			log.info("<CachePattern-setRegion_OutAlarmMemCache>出区域告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId() + urt.getRegion_out_alarm_type_id()) != null
					&& !Constant.getMemcachedClient().getObject(
							urt.getTerminalId()
									+ urt.getRegion_out_alarm_type_id())
							.equals("")) {
				map = (Map) Constant.getMemcachedClient()
						.getObject(
								urt.getTerminalId()
										+ urt.getRegion_out_alarm_type_id());
			}
			if (urt.getRegion_out_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log
							.info("<CachePattern-setRegion_OutAlarmMemCache>出区域告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_OutAlarmSql(urt, uuid));
					log
							.info("<CachePattern-setRegion_OutAlarmMemCache>update 告警表中出区域告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OutState(urt));
					// log.info("<CachePattern-setRegion_OutAlarmMemCache>update 终端"+urt.getTerminalId()+"出区域告警状态：开启");
					setCachePattern(urt, uuid, urt
							.getRegion_out_alarm_type_id(), urt
							.getRegion_out_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_out_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_OutAlarmMemCache>出区域告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + " 出区域告警信息成功");
				}
			} else {
				if (map != null && map.size() > 0) {
					log
							.info("<CachePattern-setRegion_OutAlarmMemCache>出区域告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateRegion_OutAlarmSql(urt, map));
					log
							.info("<CachePattern-setRegion_OutAlarmMemCache>update 告警表中出区域告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OutState(urt));
					// log.info("<CachePattern-setRegion_OutAlarmMemCache>update 终端"+urt.getTerminalId()+"出区域告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId()
									+ urt.getRegion_out_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_out_alarm_type_id());
					log
							.info("<CachePattern-setRegion_OutAlarmMemCache>出区域告警已关闭，清除车辆"
									+ urt.getTerminalId()
									+ " 出区域告警*memcache缓存*成功");
				}
			}
			log.info("<CachePattern-setRegion_OutAlarmMemCache>出区域告警处理结束");
		}
	}

	/**
	 * 设置入区域告警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_InAlarmMemCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		Map map = null;
		if (urt.getRegion_in_alert() != null
				&& !urt.getRegion_in_alert().equals("")) {
			log.info("<CachePattern-setRegion_InAlarmMemCache>入区域告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId() + urt.getRegion_in_alarm_type_id()) != null
					&& !Constant.getMemcachedClient().getObject(
							urt.getTerminalId()
									+ urt.getRegion_in_alarm_type_id()).equals(
							"")) {
				map = (Map) Constant.getMemcachedClient().getObject(
						urt.getTerminalId() + urt.getRegion_in_alarm_type_id());
			}
			if (urt.getRegion_in_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setRegion_InAlarmMemCache>入区域告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_InAlarmSql(urt, uuid));
					log
							.info("<CachePattern-setRegion_InAlarmMemCache>update 告警表中入区域告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_InState(urt));
					// log.info("<CachePattern-setRegion_InAlarmMemCache>update 终端"+urt.getTerminalId()+"入区域告警状态：开启");
					setCachePattern(urt, uuid,
							urt.getRegion_in_alarm_type_id(), urt
									.getRegion_in_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_in_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_InAlarmMemCache>入区域告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + " 入区域告警信息成功");
				}
			} else {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setRegion_InAlarmMemCache>入区域告警关闭");
					log
							.info("<CachePattern-setRegion_InAlarmMemCache>update 告警表中入区域告警状态：关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateRegion_InAlarmSql(urt, map));
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_InState(urt));
					// log.info("<CachePattern-setRegion_InAlarmMemCache>update 终端"+urt.getTerminalId()+"入区域告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId()
									+ urt.getRegion_in_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_in_alarm_type_id());
					log
							.info("<CachePattern-setRegion_InAlarmMemCache>入区域告警已关闭，清除车辆"
									+ urt.getTerminalId()
									+ " 入区域告警*memcache缓存*成功");
				}
			}
			log.info("<CachePattern-setRegion_InAlarmMemCache>入区域告警处理结束");
		}
	}

	/**
	 * 设置gps超速告警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void setGpsAlarmMemCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		log.debug(NAME + "gps_alarm_type_id:" + urt.getGps_alarm_type_id()
				+ ",gps_alert:" + urt.getGps_alert());
		Map map = null;
		if (urt.getGps_alert() != null && !urt.getGps_alert().equals("")
				&& !urt.getGps_alert().equals(num1)) {
			log.info("<CachePattern-setGpsAlarmMemCache>GPS超速告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId() + urt.getGps_alarm_type_id()) != null
					&& !Constant.getMemcachedClient().getObject(
							urt.getTerminalId() + urt.getGps_alarm_type_id())
							.equals("")) {
				map = (Map) Constant.getMemcachedClient().getObject(
						urt.getTerminalId() + urt.getGps_alarm_type_id());
			}
			if (urt.getGps_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setGpsAlarmMemCache>GPS超速告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildInsertGpsAlarmSql(urt,
									uuid));
					log
							.info("<CachePattern-setGpsAlarmMemCache>update 告警表中GPS超速告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalGpsState(urt));
					// log.info("<CachePattern-setGpsAlarmMemCache>update 终端"+urt.getTerminalId()+"GPS超速告警状态：开启");
					setCachePattern(urt, uuid, urt.getGps_alarm_type_id(), urt
							.getGps_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getGps_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setGpsAlarmMemCache>GPS超速告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + " GPS超速告警信息成功");
				}
			} else if (urt.getGps_alert().equals(num0)) {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setGpsAlarmMemCache>GPS超速告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildUpdateGpsAlarmSql(urt,
									map));
					log
							.info("<CachePattern-setGpsAlarmMemCache>update 告警表中GPS超速告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalGpsState(urt));
					// log.info("<CachePattern-setGpsAlarmMemCache>update 终端"+urt.getTerminalId()+"GPS超速告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId() + urt.getGps_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getGps_alarm_type_id());
					log
							.info("<CachePattern-setGpsAlarmMemCache>GPS超速告警已关闭，清除车辆"
									+ urt.getTerminalId()
									+ " GPS超速告警*memcache缓存*成功");
				}
			} else {
				log.info("<CachePattern-setGpsAlarmMemCache>GPS超速预警，不处理");
			}
			log.info("<CachePattern-setGpsAlarmMemCache>GPS超速告警处理结束");
		}
	}

	//
	// /**
	// * 设置急加/减速告警入库语句及memcache缓存
	// *
	// * @param urt
	// * @param uuid
	// * @throws ParseException
	// * @throws TimeoutException
	// * @throws InterruptedException
	// * @throws MemcachedException
	// */
	// @SuppressWarnings("unchecked")
	// private void setRapidAlarmMemCache(Up_InfoContent urt, String uuid)
	// throws ParseException {
	// log.debug(NAME+
	// "rapid_alarm_type_id:"+urt.getRapid_alarm_type_id()+",rapid_alert:"+urt.getRapid());
	// Map map = null;
	// if (urt.getRapid() != null && !urt.getRapid().equals("")) {
	// log.info("<CachePattern-setRapidAlarmMemCache>急加/减速处理开始");
	// if(Constant.getMemcachedClient().getObject(urt.getTerminalId() +
	// urt.getRapid_alarm_type_id())!=null
	// &&!Constant.getMemcachedClient().getObject(urt.getTerminalId() +
	// urt.getRapid_alarm_type_id()).equals("")){
	// map = (Map) Constant.getMemcachedClient().getObject(urt.getTerminalId() +
	// urt.getRapid_alarm_type_id());
	// }
	// if(urt.getRapid().equals(num2)){
	// if (map == null || map.size() == 0) {
	// log.info("<CachePattern-setRapidAlarmMemCache>急加/减速告警开启");
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertRAPIDAlarmSql(urt,
	// uuid));
	// log.info("<CachePattern-setRapidAlarmMemCache>update 告警表中急加/减速状态：开启");
	// //
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRapidState(urt));
	// setCachePattern(urt, uuid,urt.getRapid_alarm_type_id());
	// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRapid_alarm_type_id(),
	// urt);
	// log.info("<CachePattern-setRapidAlarmMemCache>急加/减速告警已开启，*memcache缓存*车辆"+urt.getTerminalId()+" 急加速告警信息成功");
	// }
	// } else {
	// if (map != null && map.size() > 0) {
	// log.info("<CachePattern-setRapidAlarmMemCache>急加/减速告警关闭");
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateRAPIDAlarmSql(urt,
	// map));
	// log.info("<CachePattern-setRapidAlarmMemCache>update 告警表中急加/减速告警状态：关闭");
	// //
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRapidState(urt));
	// Constant.getMemcachedClient().delete(urt.getTerminalId() +
	// urt.getRapid_alarm_type_id());
	// Constant.cacheMap.remove(urt.getTerminalId()+urt.getRapid_alarm_type_id());
	// log.info("<CachePattern-setRapidAlarmMemCache>急加/减速告警已关闭，清除车辆"+urt.getTerminalId()+" 急加/减速告警*memcache缓存*成功");
	// }
	// }
	// log.info("<CachePattern-setRapidAlarmMemCache>急加/减速处理结束");
	// }
	// }
	//
	// /**
	// * 设置急加/减速告警入库语句及本机缓存
	// *
	// * @param urt
	// * @param uuid
	// * @throws ParseException
	// */
	// @SuppressWarnings("unchecked")
	// private void setRapidAlarmCache(Up_InfoContent urt, String uuid) throws
	// ParseException {
	// log.debug(NAME+
	// "rapid_alarm_type_id:"+urt.getRapid_alarm_type_id()+",rapid_alert:"+urt.getRapid());
	// Map map = null;
	// if (urt.getRapid() != null && !urt.getRapid().equals("")) {
	// log.info("<CachePattern-setRapidAlarmCache>区域告警处理开始");
	// map = (Map) Constant.cacheMap.get(urt.getTerminalId() +
	// urt.getRapid_alarm_type_id());
	// if(urt.getRapid().equals(num2)){
	// if (map == null || map.size() == 0) {
	// log.info("<CachePattern-setRapidAlarmCache>区域超速告警开启");
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertRAPIDAlarmSql(urt,
	// uuid));
	// log.info("<CachePattern-setRapidAlarmCache>update 告警表中区域超速告警状态：开启");
	// //
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRapidState(urt));
	// setCachePattern(urt, uuid,urt.getRapid_alarm_type_id());
	// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRapid_alarm_type_id(),
	// urt);
	// log.info("<CachePattern-setRapidAlarmCache>区域超速告警已开启，*本机缓存*车辆"+urt.getTerminalId()+" 区域超速告警信息成功");
	// }
	// }else{
	// if (map != null && map.size() > 0) {
	// log.info("<CachePattern-setRapidAlarmCache>区域超速告警关闭");
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateRAPIDAlarmSql(urt,
	// map));
	// log.info("<CachePattern-setRapidAlarmCache>update 告警表中区域超速告警状态：关闭");
	// //
	// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRapidState(urt));
	// Constant.cacheMap.remove(urt.getTerminalId() +
	// urt.getRapid_alarm_type_id());
	// log.info("<CachePattern-setRapidAlarmCache>区域超速告警已关闭，清除车辆"+urt.getTerminalId()+" 区域超速告警*本机缓存*成功");
	// }
	// }
	// log.info("<CachePattern-setRapidAlarmCache>区域告警处理结束");
	// }
	// }
	//
	/**
	 * 设置gps超速报警入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void setGpsAlarmCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		log.debug(NAME + "gps_alarm_type_id:" + urt.getGps_alarm_type_id()
				+ ",gps_alert:" + urt.getGps_alert());
		Map map = null;
		if (urt.getGps_alert() != null && !urt.getGps_alert().equals("")
				&& !urt.getGps_alert().equals(num1)) {
			log.info("<CachePattern-setGpsAlarmCache>GPS超速告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId()
					+ urt.getGps_alarm_type_id());
			if (urt.getGps_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setGpsAlarmCache>GPS超速告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildInsertGpsAlarmSql(urt,
									uuid));
					log
							.info("<CachePattern-setGpsAlarmCache>update 告警表中GPS超速告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalGpsState(urt));
					// log.info("<CachePattern-setGpsAlarmCache>update 终端"+urt.getTerminalId()+"GPS超速告警状态：开启");
					setCachePattern(urt, uuid, urt.getGps_alarm_type_id(), urt
							.getGps_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getGps_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setGpsAlarmCache>GPS超速告警已开启，*本地缓存*车辆"
									+ urt.getTerminalId() + " GPS超速告警信息成功");
				}
			} else if (urt.getGps_alert().equals(num0)) {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setGpsAlarmCache>GPS超速告警关闭");
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateGpsAlarmSql(urt, map));
					log.info("<CachePattern-setGpsAlarmCache>update 告警表中GPS超速告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalGpsState(urt));
					// log.info("<CachePattern-setGpsAlarmCache>update 终端"+urt.getTerminalId()+"GPS超速告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId() + urt.getGps_alarm_type_id());
					log.info("<CachePattern-setGpsAlarmCache>GPS超速告警已关闭，清除车辆"	+ urt.getTerminalId() + " GPS超速告警*本地缓存*成功");
				}
			} else {
				log.info("<CachePattern-setGpsAlarmCache>GPS超速预警，不处理");
			}
			log.info("<CachePattern-setGpsAlarmCache>GPS超速告警处理结束");
		}
	}

	/**
	 * 设置sos报警入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public void setSosAlarmCache(Up_InfoContent urt, String uuid) throws ParseException {
		log.debug(NAME + "sos_alarm_type_id:" + urt.getSos_alarm_type_id() + ",sos_alert:" + urt.getSos());
		Map map = null;
		if (urt.getSos() != null && !urt.getSos().equals("")) {
			log.info("<CachePattern-setSosAlarmCache>sos告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId() + urt.getSos_alarm_type_id());
			if (urt.getSos().equals(num1)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setSosAlarmCache>sos告警开启");
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertSosAlarmSql(urt,uuid));
					log.info("<CachePattern-setSosAlarmCache>update 告警表中  sos 告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
					// log.info("<CachePattern-setSosAlarmCache>update 终端"+urt.getTerminalId()+" sos 告警状态：开启");
					setCachePattern(urt, uuid, urt.getSos_alarm_type_id(), urt.getSos());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getSos_alarm_type_id(),
					// urt);
					log.info("<CachePattern-setSosAlarmCache>sos告警已开启，*本地缓存*车辆" + urt.getTerminalId() + " sos告警信息成功");
				}
			} else {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setSosAlarmCache>sos告警关闭");
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSosAlarmSql(urt,map));
					log.info("<CachePattern-setSosAlarmCache>update 告警表中  sos 告警状态：关闭");
					if (!map.get(urt.getTerminalId() + "2B00").equals("") && map.get(urt.getTerminalId() + "2B00") != null) {
						urt.setDeal_state("3");
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateSendCmdSql(urt,(String) map.get(urt.getTerminalId() + "2B00")));
						Constant.cacheMap.remove(urt.getTerminalId() + CommandCode.down2B00);
						log.info(NAME + "收到报警取消回应，已处理，告警取消成功!");
					}
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
					// log.info("<CachePattern-setSosAlarmCache>update 终端"+urt.getTerminalId()+" sos 告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId() + urt.getSos_alarm_type_id());
					log.info("<CachePattern-setSosAlarmCache>sos告警已关闭，清除车辆" + urt.getTerminalId() + " sos告警*本地缓存*成功");
				}
			}
			log.info("<CachePattern-setSosAlarmCache>sos告警处理结束");
		}
	}

	/**
	 * 设置sos报警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 * @throws TimeoutException
	 * @throws MemcachedException
	 * @throws InterruptedException
	 */
	@SuppressWarnings("unchecked")
	public void setSosAlarmMemCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		log.debug(NAME + "sos_alarm_type_id:" + urt.getSos_alarm_type_id()
				+ ",sos_alert:" + urt.getSos());
		Map map = null;
		if (urt.getSos() != null && !urt.getSos().equals("")) {
			// log.info("<CachePattern-setSosAlarmMemCache>sos告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId() + urt.getSos_alarm_type_id()) != null
					&& !Constant.getMemcachedClient().getObject(
							urt.getTerminalId() + urt.getSos_alarm_type_id())
							.equals("")) {
				map = (Map) Constant.getMemcachedClient().getObject(
						urt.getTerminalId() + urt.getSos_alarm_type_id());
			}
			if (urt.getSos().equals(num1)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setSosAlarmMemCache>sos告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildInsertSosAlarmSql(urt,uuid));
					log
							.info("<CachePattern-setSosAlarmMemCache>update 告警表中  sos 告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
					// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：开启");
					setCachePattern(urt, uuid, urt.getSos_alarm_type_id(), urt
							.getSos());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getSos_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setSosAlarmMemCache>sos告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + " sos告警信息成功");
				} else {
					String seq = (String) map.get("alarm_seq");
					if (seq != null && !seq.equals("")) {
						log.info("<CachePattern-setSosAlarmMemCache>sos告警开启");
						DBBuffer.getInstance().add(
								BuildSQL.getInstance().buildInsertSosAlarmSql(urt, uuid));
						log
								.info("<CachePattern-setSosAlarmMemCache>update 告警表中  sos 告警状态：开启");
						// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
						// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：开启");
						setCachePattern(urt, uuid, urt.getSos_alarm_type_id(),
								urt.getSos());
						// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getSos_alarm_type_id(),
						// urt);
						log
								.info("<CachePattern-setSosAlarmMemCache>sos告警已开启，*memcache缓存*车辆"
										+ urt.getTerminalId() + " sos告警信息成功");
					}
				}
			} else {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setSosAlarmMemCache>sos告警关闭");
					String msg_id = null;
					if (Constant.getMemcachedClient().getObject(
							urt.getTerminalId() + CommandCode.down2B00) != null
							&& !Constant.getMemcachedClient().getObject(
									urt.getTerminalId() + CommandCode.down2B00)
									.equals("")) {
						msg_id = (String) Constant.getMemcachedClient()
								.getObject(
										urt.getTerminalId()
												+ CommandCode.down2B00);
					}
					if (!msg_id.equals("") && msg_id != null) {
						urt.setDeal_state("3");
						DBBuffer.getInstance().add(
								BuildSQL.getInstance().buildUpdateSendCmdSql(
										urt, msg_id));
						Constant.getMemcachedClient().delete(
								urt.getTerminalId() + CommandCode.down2B00);
						Constant.ytbsendcmdMap.remove(urt.getTerminalId()
								+ CommandCode.down2B00);
						log.info(NAME + "收到报警取消回应，已处理，告警取消成功!");
					}
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildUpdateSosAlarmSql(urt,
									map));
					log
							.info("<CachePattern-setSosAlarmMemCache>update 告警表中  sos 告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
					// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId() + urt.getSos_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getSos_alarm_type_id());
					log.info("<CachePattern-setSosAlarmMemCache>sos告警已关闭，清除车辆"
							+ urt.getTerminalId() + " sos告警*memcache缓存*成功");
				}
			}
			// log.info("<CachePattern-setSosAlarmMemCache>sos告警处理结束");
		}
	}

	/**
	 * 设置超速报警入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public void setOverSpeedAlarmCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		log.debug(NAME + "overspeed_alarm_type_id:"
				+ urt.getOverspeed_alarm_type_id() + ",overspeed_alert:"
				+ urt.getOverspeed_alert());
		Map map = null;
		// 超速
		if (urt.getOverspeed_alert() != null
				&& !urt.getOverspeed_alert().equals("")
				&& !urt.getOverspeed_alert().equals(num1)) {
			log.info("<CachePattern-setOverSpeedAlarmCache>超速告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId()
					+ urt.getOverspeed_alarm_type_id());
			if (urt.getOverspeed_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setOverSpeedAlarmCache>超速告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertOverSpeedAlarmSql(urt, uuid));
					log
							.info("<CachePattern-setOverSpeedAlarmCache>update 告警表中超速告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalOverspeedState(urt));
					setCachePattern(urt, uuid,
							urt.getOverspeed_alarm_type_id(), urt
									.getOverspeed_alert());
					// log.info("<CachePattern-setOverSpeedAlarmCache>update 终端"+urt.getTerminalId()+" 超速告警状态：开启");
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getOverspeed_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setOverSpeedAlarmCache>超速告警已开启，*本地缓存*车辆"
									+ urt.getTerminalId() + "超速告警信息完毕");
				}
			} else if (urt.getOverspeed_alert().equals(num0)) {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setOverSpeedAlarmCache>超速告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateOverSpeedAlarmSql(urt, map));
					log
							.info("<CachePattern-setOverSpeedAlarmCache>update 告警表中超速告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalOverspeedState(urt));
					// log.info("<CachePattern-setOverSpeedAlarmCache>update 终端"+urt.getTerminalId()+" 超速告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getOverspeed_alarm_type_id());
					log
							.info("<CachePattern-setOverSpeedAlarmCache>超速告警已关闭，清理车辆"
									+ urt.getTerminalId() + "超速告警*本地缓存*成功");
				}
			} else {
				log.info("<CachePattern-setOverSpeedAlarmCache>超速预警,不处理");
			}
			log.info("<CachePattern-setOverSpeedAlarmCache>超速告警处理结束");
		}
	}

	/**
	 * 设置超速报警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	@SuppressWarnings("unchecked")
	public void setOverSpeedAlarmMemCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		log.debug(NAME + "overspeed_alarm_type_id:"
				+ urt.getOverspeed_alarm_type_id() + ",overspeed_alert:"
				+ urt.getOverspeed_alert());
		Map map = null;
		if (urt.getOverspeed_alert() != null
				&& !urt.getOverspeed_alert().equals("")
				&& !urt.getOverspeed_alert().equals(num1)) {
			log.info("<CachePattern-setOverSpeedAlarmMemCache>超速告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId() + urt.getOverspeed_alarm_type_id()) != null
					&& !Constant.getMemcachedClient().getObject(
							urt.getTerminalId()
									+ urt.getOverspeed_alarm_type_id()).equals(
							"")) {
				map = (Map) Constant.getMemcachedClient().getObject(
						urt.getTerminalId() + urt.getOverspeed_alarm_type_id());
			}
			if (urt.getOverspeed_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setOverSpeedAlarmMemCache>超速告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertOverSpeedAlarmSql(urt, uuid));
					log
							.info("<CachePattern-setOverSpeedAlarmMemCache>update 告警表中超速告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalOverspeedState(urt));
					// log.info("<CachePattern-setOverSpeedAlarmMemCache>update 终端"+urt.getTerminalId()+" 超速告警状态：开启");
					setCachePattern(urt, uuid,
							urt.getOverspeed_alarm_type_id(), urt
									.getOverspeed_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getOverspeed_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setOverSpeedAlarmMemCache>超速告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + "超速告警信息完毕");
				}
			} else if (urt.getOverspeed_alert().equals(num0)) {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setOverSpeedAlarmMemCache>超速告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateOverSpeedAlarmSql(urt, map));
					log
							.info("<CachePattern-setOverSpeedAlarmMemCache>update 告警表中超速告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalOverspeedState(urt));
					// log.info("<CachePattern-setOverSpeedAlarmMemCache>update 终端"+urt.getTerminalId()+" 超速告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId()
									+ urt.getOverspeed_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getOverspeed_alarm_type_id());
					log
							.info("<CachePattern-setOverSpeedAlarmMemCache>超速告警已关闭，清理车辆"
									+ urt.getTerminalId()
									+ "超速告警*memcache缓存*成功");
				}
			} else {
				log.info("<CachePattern-setOverSpeedAlarmMemCache>超速预警,不处理");
				// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalOverspeedState(urt));
			}
			log.info("<CachePattern-setOverSpeedAlarmMemCache>超速告警处理结束");
		}
	}

	/**
	 * 设置疲劳驾驶入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public void setFatigueAlarmCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		log.debug(NAME + "fatigue_alarm_type_id:"
				+ urt.getFatigue_alarm_type_id() + ",fatigue_alert:"
				+ urt.getFatigue_alert());
		Map map = null;
		if (urt.getFatigue_alert() != null
				&& !urt.getFatigue_alert().equals("")
				&& !urt.getFatigue_alert().equals(num1)) {
			log.info("<CachePattern-setFatigueAlarmCache>疲劳驾驶告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId()
					+ urt.getFatigue_alarm_type_id());
			if (urt.getFatigue_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setFatigueAlarmCache>疲劳驾驶告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildInsertFatigueAlarmSql(
									urt, uuid));
					log
							.info("<CachePattern-setFatigueAlarmCache>update 告警表中疲劳驾驶告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalFatigueState(urt));
					// log.info("<CachePattern-setFatigueAlarmCache>update 终端"+urt.getTerminalId()+"疲劳驾驶告警状态：开启");
					setCachePattern(urt, uuid, urt.getFatigue_alarm_type_id(),
							urt.getFatigue_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getFatigue_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setFatigueAlarmCache>疲劳驾驶告警已开启，*本机缓存*车辆"
									+ urt.getTerminalId() + "疲劳驾驶告警信息完毕");
				}
			} else if (urt.getFatigue_alert().equals(num0)) {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setFatigueAlarmCache>疲劳驾驶告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildUpdateFatigueAlarmSql(
									urt, map));
					log
							.info("<CachePattern-setFatigueAlarmCache>update 告警表中疲劳驾驶告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalFatigueState(urt));
					// log.info("<CachePattern-setFatigueAlarmCache>update 终端"+urt.getTerminalId()+" 疲劳驾驶告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getFatigue_alarm_type_id());
					log
							.info("<CachePattern-setFatigueAlarmCache>疲劳驾驶告警已关闭，清理车辆"
									+ urt.getTerminalId() + "疲劳驾驶告警*本机缓存*成功");
				}
			} else {
				log.info("<CachePattern-setFatigueAlarmCache>疲劳驾驶预警，不处理");
			}
			log.info("<CachePattern-setFatigueAlarmCache>疲劳驾驶告警处理结束");
		}
	}

	/**
	 * 设置疲劳驾驶报警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	@SuppressWarnings("unchecked")
	public void setFatigueAlarmMemCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		log.debug(NAME + "fatigue_alarm_type_id:"
				+ urt.getFatigue_alarm_type_id() + ",fatigue_alert:"
				+ urt.getFatigue_alert());
		Map map = null;
		if (urt.getFatigue_alert() != null
				&& !urt.getFatigue_alert().equals("")
				&& !urt.getFatigue_alert().equals(num1)) {
			log.info("<CachePattern-setFatigueAlarmMemCache>疲劳驾驶告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId() + urt.getFatigue_alarm_type_id()) != null
					&& !Constant.getMemcachedClient().getObject(
							urt.getTerminalId()
									+ urt.getFatigue_alarm_type_id())
							.equals("")) {
				map = (Map) Constant.getMemcachedClient().getObject(
						urt.getTerminalId() + urt.getFatigue_alarm_type_id());
			}
			if (urt.getFatigue_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern-setFatigueAlarmMemCache>疲劳驾驶告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildInsertFatigueAlarmSql(
									urt, uuid));
					log
							.info("<CachePattern-setFatigueAlarmMemCache>update 告警表中疲劳驾驶告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalFatigueState(urt));
					// log.info("<CachePattern-setFatigueAlarmMemCache>update 终端"+urt.getTerminalId()+"疲劳驾驶告警状态：开启");
					setCachePattern(urt, uuid, urt.getFatigue_alarm_type_id(),
							urt.getFatigue_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getFatigue_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setFatigueAlarmMemCache>疲劳驾驶告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + "疲劳驾驶告警信息完毕");
				}
			} else if (urt.getFatigue_alert().equals(num0)) {
				if (map != null && map.size() > 0) {
					log.info("<CachePattern-setFatigueAlarmMemCache>疲劳驾驶告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildUpdateFatigueAlarmSql(
									urt, map));
					log
							.info("<CachePattern-setFatigueAlarmMemCache>update 告警表中疲劳驾驶告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalFatigueState(urt));
					// log.info("<CachePattern-setFatigueAlarmMemCache>update 终端"+urt.getTerminalId()+" 疲劳驾驶告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId()
									+ urt.getFatigue_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getFatigue_alarm_type_id());
					log
							.info("<CachePattern-setFatigueAlarmMemCache>疲劳驾驶告警已关闭，清理车辆"
									+ urt.getTerminalId()
									+ "疲劳驾驶告警*memcache缓存*成功");
				}
			} else {
				log.info("<CachePattern-setFatigueAlarmMemCache>疲劳驾驶预警，不处理");
			}
			log.info("<CachePattern-setFatigueAlarmMemCache>疲劳驾驶告警处理结束");
		}
	}

	//
	/**
	 * 设置区域超速告警入库语句及memcache缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 * @throws MemcachedException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_OverspeedAlarmMemCache(Up_InfoContent urt,
			String uuid) throws ParseException {
		Map map = null;
		if (urt.getRegion_overspeed_alert() != null
				&& !urt.getRegion_overspeed_alert().equals("")) {
			log
					.info("<CachePattern-setRegion_OverspeedAlarmMemCache>区域超速告警处理开始");
			if (Constant.getMemcachedClient().getObject(
					urt.getTerminalId()
							+ urt.getRegion_overspeed_alarm_type_id()) != null
					&& !Constant.getMemcachedClient().getObject(
							urt.getTerminalId()
									+ urt.getRegion_overspeed_alarm_type_id())
							.equals("")) {
				map = (Map) Constant.getMemcachedClient().getObject(
						urt.getTerminalId()
								+ urt.getRegion_overspeed_alarm_type_id());
			}
			if (urt.getRegion_overspeed_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log
							.info("<CachePattern-setRegion_OverspeedAlarmMemCache>区域超速告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_OverspeedAlarmSql(urt,
											uuid));
					log
							.info("<CachePattern-setRegion_OverspeedAlarmMemCache>update 告警表中区域超速告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OverspeedState(urt));
					// log.info("<CachePattern-setRegion_OverspeedAlarmMemCache>update 终端"+urt.getTerminalId()+"区域超速告警状态：开启");
					setCachePattern(urt, uuid, urt
							.getRegion_overspeed_alarm_type_id(), urt
							.getRegion_overspeed_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_overspeed_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_OverspeedAlarmMemCache>区域超速告警已开启，*memcache缓存*车辆"
									+ urt.getTerminalId() + "区域超速告警信息完毕");
				}
			} else {
				if (map != null && map.size() > 0) {
					log
							.info("<CachePattern-setRegion_OverspeedAlarmMemCache>区域超速告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateOverSpeedAlarmSql(urt, map));
					log
							.info("<CachePattern-setRegion_OverspeedAlarmMemCache>update 告警表中区域超速告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OverspeedState(urt));
					// log.info("<CachePattern-setRegion_OverspeedAlarmMemCache>update 终端"+urt.getTerminalId()+" 区域超速告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId()
									+ urt.getRegion_overspeed_alarm_type_id());
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_overspeed_alarm_type_id());
					log
							.info("<CachePattern-setRegion_OverspeedAlarmMemCache>区域超速告警已关闭，清理车辆"
									+ urt.getTerminalId()
									+ "区域超速告警*memcache缓存*成功");
				}
			}
			log
					.info("<CachePattern-setRegion_OverspeedAlarmMemCache>区域超速告警处理结束");
		}
	}

	//
	/**
	 * 设置区域超速告警入库语句及本机缓存
	 * 
	 * @param urt
	 * @param uuid
	 * @throws ParseException
	 */
	@SuppressWarnings( { "unchecked", "unused" })
	private void setRegion_OverspeedAlarmCache(Up_InfoContent urt, String uuid)
			throws ParseException {
		Map map = null;
		if (urt.getRegion_overspeed_alert() != null
				&& !urt.getRegion_overspeed_alert().equals("")) {
			log.info("<CachePattern-setRegion_OverspeedAlarmCache>区域超速告警处理开始");
			map = (Map) Constant.cacheMap.get(urt.getTerminalId()
					+ urt.getRegion_overspeed_alarm_type_id());
			if (urt.getRegion_overspeed_alert().equals(num2)) {
				if (map == null || map.size() == 0) {
					log
							.info("<CachePattern-setRegion_OverspeedAlarmCache>区域超速告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildInsertRegion_OverspeedAlarmSql(urt,
											uuid));
					log
							.info("<CachePattern-setRegion_OverspeedAlarmCache>update 告警表中区域超速告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OverspeedState(urt));
					// log.info("<CachePattern-setRegion_OverspeedAlarmCache>update 终端"+urt.getTerminalId()+"区域超速告警状态：开启");
					setCachePattern(urt, uuid, urt
							.getRegion_overspeed_alarm_type_id(), urt
							.getRegion_overspeed_alert());
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),urt.getRegion_overspeed_alarm_type_id(),
					// urt);
					log
							.info("<CachePattern-setRegion_OverspeedAlarmCache>区域超速告警已开启，*本机缓存*车辆"
									+ urt.getTerminalId() + "区域超速告警信息完毕");
				}
			} else {
				if (map != null && map.size() > 0) {
					log
							.info("<CachePattern-setRegion_OverspeedAlarmCache>区域超速告警关闭");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateOverSpeedAlarmSql(urt, map));
					log
							.info("<CachePattern-setRegion_OverspeedAlarmCache>update 告警表中区域超速告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalRegion_OverspeedState(urt));
					// log.info("<CachePattern-setRegion_OverspeedAlarmCache>update 终端"+urt.getTerminalId()+" 区域超速告警状态：关闭");
					Constant.cacheMap.remove(urt.getTerminalId()
							+ urt.getRegion_overspeed_alarm_type_id());
					log
							.info("<CachePattern-setRegion_OverspeedAlarmCache>区域超速告警已关闭，清理车辆"
									+ urt.getTerminalId() + "区域超速告警*本机缓存*成功");
				}
			}
			log.info("<CachePattern-setRegion_OverspeedAlarmCache>区域超速告警处理关闭");
		}
	}

	/**
	 * 根据开关方式设置告警信息存储memcache或者本机内存
	 * 
	 * @param urt
	 */
	public void setCachePattern(Up_InfoContent urt, String uuid,
			String alarm_type, String alarm_state) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("vin", urt.getTerminalId());
		map.put("alarm_id", uuid);
		map.put("alarm_time", urt.getTerminal_time());
		map.put("alarm_state", alarm_state);
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			Constant.getMemcachedClient().insert(urt.getTerminalId() + alarm_type, map);
		}
		Constant.cacheMap.put(urt.getTerminalId() + alarm_type, map);
//		map = null;
	}
	
	
	public void setCachePattern(Up_InfoContent urt) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("vin", urt.getTerminalId());
		map.put("alarm_state", urt.getAlarm_state());
		map.put("xcstate", urt.getXcstate());
		map.put("alarm_seq", urt.getAlarm_seq());
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			Constant.getMemcachedClient().insert(Constant.ALARM+urt.getTerminalId(), map);
		}
		Constant.cacheMap.put(Constant.ALARM+urt.getTerminalId(), map);
//		map = null;
	}

	public void setCachePattern(Up_InfoContent urt, String uuid,
			String alarm_type, String alarm_state,String seq) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("vin", urt.getTerminalId());
		map.put("alarm_id", uuid);
		map.put("alarm_time", urt.getTerminal_time());
		map.put("alarm_state", alarm_state);
		if (Constant.isstartMemcache.equals("1")
				&& Constant.getMemcachedClient().connectState()) {
			Constant.getMemcachedClient().insert(
					urt.getTerminalId() + alarm_type, map);
			Constant.cacheMap.put(urt.getTerminalId() + alarm_type, map);
		} 
		Constant.cacheMap.put(urt.getTerminalId() + alarm_type, map);
		map = null;
	}

	/**
	 * 根据上行信息，设置区域内超速告警、出入区告警、区域非法开关门
	 * 
	 * @param urt
	 */
	public void setRegionAlarmParam(Up_InfoContent urt) {
		log.info("区域告警开始");
		if (urt.getQuad_id_type() != null && !urt.getQuad_id_type().equals("")) {
			int index = urt.getQuad_id_type().length() - 1;
			int len = urt.getQuad_id_type().length();
			String region_id = Converser.hexToString(urt.getQuad_id_type()
					.substring(0, index));
			String inorout = urt.getQuad_id_type().substring(index, len);
			// System.out.println(region_id+","+inorout);
			// 从缓存中获取车辆区域关系数据
			Vehicle_RegionBean vrbean = Vehicle_RegionCacheManager
					.getInstance().getValue(urt.getTerminalId() + region_id);
			// 如果没有查到，不产生任何报警；如果有对应数据，继续进行处理
			log.debug("setRegionAlarmParam_1:" + vrbean);
			if (vrbean != null) {
				// 如果上报信息中有开关量数据
				urt.setRegion_id(region_id);
				log.info("setRegionAlarmParam_2:" + urt.getOn_off());
				if (inorout.equals("1")) {
					log.debug("setRegionAlarmParam_11:");
					// in
					if (vrbean.getMove_in_alarm().equals("1")) {
						urt.setRegion_in_alert(num2);
						urt.setRegion_in_alarm_type_id("37");
					} else {
						log.debug("setRegionAlarmParam_12:");
						urt.setRegion_in_alert(num0);
						urt.setRegion_in_alarm_type_id("37");
					}
					urt.setRegion_out_alert(num0);
					urt.setRegion_out_alarm_type_id("38");
				} else if (inorout.equals("2")) {
					// out
					log.debug("setRegionAlarmParam_13:");
					if (vrbean.getMove_out_alarm().equals("1")) {
						urt.setRegion_out_alert(num2);
						urt.setRegion_out_alarm_type_id("38");
					} else {
						log.debug("setRegionAlarmParam_14:");
						urt.setRegion_out_alert(num0);
						urt.setRegion_out_alarm_type_id("38");
					}
					urt.setRegion_in_alert(num0);
					urt.setRegion_in_alarm_type_id("37");
				} else if (inorout.equals("3")) {
					if (urt.getOn_off() != null && !urt.getOn_off().equals("")) {
						log.debug("setRegionAlarmParam_3:"
								+ vrbean.getDoor_open_enable());
						// 区域是否允许开关门
						if (vrbean.getDoor_open_enable().equals("1")) {
							log.debug("setRegionAlarmParam_4:"
									+ urt.getFrontdoor_on_off() + ","
									+ urt.getMiddledoor_on_off());
							// 上报的 前门或中门信号为on时，产生区域开关门报警
							if (urt.getFrontdoor_on_off().equals(Constant.ON)
									|| urt.getMiddledoor_on_off().equals(
											Constant.ON)) {
								urt.setRegion_openclose_alert(num2);
								urt.setRegion_openclosedoor_alarm_type_id("39");
							} else {
								log.debug("setRegionAlarmParam_5:");
								// 否则不产生开关门告警
								urt.setRegion_openclose_alert(num0);
								urt.setRegion_openclosedoor_alarm_type_id("39");
							}
						} else {
							log.debug("setRegionAlarmParam_6:");
							// 否则不产生开关门告警
							urt.setRegion_openclose_alert(num0);
							urt.setRegion_openclosedoor_alarm_type_id("39");
						}
					} else {
						log.debug("setRegionAlarmParam_7:");
						// 上报信息中没有开关量数据，则不产生开关门告警
						urt.setRegion_openclose_alert(num0);
						urt.setRegion_openclosedoor_alarm_type_id("39");
					}
					// 如果该区域设置限速
					if (vrbean.getRate_limit_flag().equals("1")) {
						log.debug("setRegionAlarmParam_8:");
						// 实时信息车速大于区域表中规定的限速值
						if (Integer.parseInt(urt.getSpeeding()) > Integer
								.parseInt(vrbean.getRate_limit_value())) {
							// 产生区域超速告警
							urt.setRegion_overspeed_alert(num2);
							urt.setRegion_overspeed_alarm_type_id("36");
						} else {
							log.debug("setRegionAlarmParam_9:");
							urt.setRegion_overspeed_alert(num0);
							urt.setRegion_overspeed_alarm_type_id("36");
						}
					} else {
						log.info("setRegionAlarmParam_10:");
						urt.setRegion_overspeed_alert(num0);
						urt.setRegion_overspeed_alarm_type_id("36");
					}
				}
			} else {
				log.debug("setRegionAlarmParam_14:");
				urt.setRegion_overspeed_alert(num0);
				urt.setRegion_overspeed_alarm_type_id("36");
				urt.setRegion_out_alert(num0);
				urt.setRegion_out_alarm_type_id("38");
				urt.setRegion_in_alert(num0);
				urt.setRegion_in_alarm_type_id("37");
				urt.setRegion_openclose_alert(num0);
				urt.setRegion_openclosedoor_alarm_type_id("39");
			}
		}
	}

	/**
	 * 设置上行实时报警信号
	 * 
	 * @param urt
	 * @param value
	 */
	public void setUp_RealTimeAlarmState(Up_InfoContent urt, String value) {
		// if(value.substring(31, 32).equals(num1)){
		// urt.setSos(num2);
		// urt.setSos_alarm_type_id("40");
		// }
		// if(value.substring(30,31).equals(num1)){
		// urt.setSos(Constant.OFF);
		// urt.setSos_alarm_type_id("40");
		// }
		if (value.substring(29, 30).equals(num1)) {
			urt.setOverspeed_alert(num2);
			urt.setOverspeed_alarm_type_id("32");
		}

		if (value.substring(28, 29).equals(num1)) {
			urt.setOverspeed_alert(Constant.ON);
			urt.setOverspeed_alarm_type_id("32");
		}

		if (value.substring(27, 28).equals(num1)) {
			urt.setOverspeed_alert(Constant.OFF);
			urt.setOverspeed_alarm_type_id("32");
		}

		if (value.substring(26, 27).equals(num1)) {
			urt.setFatigue_alert(num2);
			urt.setFatigue_alarm_type_id("33");
		}
		if (value.substring(25, 26).equals(num1)) {
			urt.setFatigue_alert(Constant.ON);
			urt.setFatigue_alarm_type_id("33");
		}
		if (value.substring(24, 25).equals(num1)) {
			urt.setFatigue_alert(Constant.OFF);
			urt.setFatigue_alarm_type_id("33");
		}

		if (value.substring(23, 24).equals(num1)) {
			urt.setGps_alert(num2);
			urt.setGps_alarm_type_id("34");
		}
		if (value.substring(22, 23).equals(num1)) {
			urt.setGps_alert(Constant.ON);
			urt.setGps_alarm_type_id("34");
		}
		if (value.substring(21, 22).equals(num1)) {
			urt.setGps_alert(Constant.OFF);
			urt.setGps_alarm_type_id("34");
		}
		if (value.substring(63, 64).equals(num1)) {
			urt.setSos(num1);
			urt.setSos_alarm_type_id("40");
		} else {
			urt.setSos(num0);
			urt.setSos_alarm_type_id("40");
		}
		// if(value.substring(20, 21).equals(num1)){
		// urt.setShow_speed_alert(Constant.ON);
		// }else{
		// urt.setShow_speed_alert(Constant.OFF);
		// }
		// if(value.substring(5, 6).equals(num1)){
		// urt.setRapid(num2);
		// urt.setRapid_alarm_type_id("35");
		// }
		// if(value.substring(4, 5).equals(num1)){
		// urt.setRapid(Constant.OFF);
		// urt.setRapid_alarm_type_id("35");
		// }

	}
/**
 * 
 * @param urt 数据存储的bean
 * @param id 流水表的主键id
 * @param alarmStateOld 上一次上报的告警状态位
 * @param alarmSeq 上一次上报的序列号
 * @throws ParseException
 */
	public void setNew_RealTimeAlarmState(Up_InfoContent urt, String id, String alarmStateOld, String alarmSeq)
			throws ParseException {
		String value = urt.getAlarm_state();
		// 碰撞侧翻告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "52", value.substring(34, 35),alarmStateOld,34,id);
		// 车辆被盗
		setAlarmMemCache(urt, IdCreater.getUUid(), "55", value.substring(37, 38),alarmStateOld,37,id);
		// 车辆油量故障
		setAlarmMemCache(urt, IdCreater.getUUid(), "56", value.substring(38, 39),alarmStateOld,38,id);
		// 车辆VSS故障
		setAlarmMemCache(urt, IdCreater.getUUid(), "57", value.substring(39, 40),alarmStateOld,39,id);
		// 路线偏离报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "58", value.substring(40, 41),alarmStateOld,40,id);

		// 超时停车
		setAlarmMemCache(urt, IdCreater.getUUid(), "62", value.substring(44, 45),alarmStateOld,44,id);
		// 当天累计驾驶超时
		setAlarmMemCache(urt, IdCreater.getUUid(), "63", value.substring(45, 46),alarmStateOld,45,id);
		// 摄像头故障
		setAlarmMemCache(urt, IdCreater.getUUid(), "64", value.substring(52, 53),alarmStateOld,52,id);
		// TTS模块故障
		setAlarmMemCache(urt, IdCreater.getUUid(), "65", value.substring(53, 54),alarmStateOld,53,id);
		// 终端LCD或显示器故障
		setAlarmMemCache(urt, IdCreater.getUUid(), "66", value.substring(54, 55),alarmStateOld,54,id);
		// 终端主电源掉电
		setAlarmMemCache(urt, IdCreater.getUUid(), "67", value.substring(55, 56),alarmStateOld,55,id);
		// 终端主电源欠压
		setAlarmMemCache(urt, IdCreater.getUUid(), "68", value.substring(56, 57),alarmStateOld,56,id);
		// GNSS天线短路
		setAlarmMemCache(urt, IdCreater.getUUid(), "69", value.substring(57, 58),alarmStateOld,57,id);
		// GNSS天线未接或被剪断
		setAlarmMemCache(urt, IdCreater.getUUid(), "70", value.substring(58, 59),alarmStateOld,58,id);
		// GNSS模块发生故障
		setAlarmMemCache(urt, IdCreater.getUUid(), "71", value.substring(59, 60),alarmStateOld,59,id);
		//超速
		setAlarmMemCache(urt, IdCreater.getUUid(), "32", value.substring(62, 63),alarmStateOld,62,id);
		//疲劳驾驶
		setAlarmMemCache(urt, IdCreater.getUUid(), "33", value.substring(61, 62),alarmStateOld,61,id);

//		String seq = urt.getAlarm_seq();
//		if (seq != null && !seq.equals("")) {
			// 车辆非法移位
			setAlarmSeqMemCache(urt, IdCreater.getUUid(), "53", value.substring(35, 36),alarmStateOld,35,id,alarmSeq);
			// 车辆非法点火
			setAlarmSeqMemCache(urt, IdCreater.getUUid(), "54", value.substring(36, 37),alarmStateOld,36,id,alarmSeq);
			// 路线行驶时间不足/过长
			setAlarmSeqMemCache(urt, IdCreater.getUUid(), "59", value.substring(41, 42),alarmStateOld,41,id,alarmSeq);
			// 进出路线
			setAlarmSeqMemCache(urt, IdCreater.getUUid(), "60", value.substring(42, 43),alarmStateOld,42,id,alarmSeq);
			// 进出区域
			setAlarmSeqMemCache(urt, IdCreater.getUUid(), "61", value.substring(43, 44),alarmStateOld,43,id,alarmSeq);
			// 预警
			setAlarmSeqMemCache(urt, IdCreater.getUUid(), "84", value.substring(60, 61),alarmStateOld,60,id,alarmSeq);
			// 紧急报警（触动报警开关后触发）
			setAlarmSeqMemCache(urt, IdCreater.getUUid(), "40", value.substring(63, 64),alarmStateOld,63,id,alarmSeq);
//		}
	}
	/**
	 * 
	 * @param urt
	 * @param id 流水表记录
	 * @param xcstateOld 上次上报的扩展告警状态位
	 * @throws ParseException
	 */
	public void setXC_RealTimeAlarmState(Up_InfoContent urt, String id, String xcstateOld)
			throws ParseException {
		String value = urt.getXcstate();
		// 严重故障
		setAlarmMemCache(urt, IdCreater.getUUid(), "08", value.substring(31, 32),xcstateOld,31,id);
		// 制动气压报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "09", value.substring(30, 31),xcstateOld,30,id);
		// 油压报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "10", value.substring(29, 30),xcstateOld,29,id);
		// 水位低报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "12", value.substring(28, 29),xcstateOld,28,id);
		// 制动蹄片磨损报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "13", value.substring(27, 28),xcstateOld,27,id);
		// 空滤堵塞报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "14", value.substring(26, 27),xcstateOld,26,id);
		// 缓速器高温报警信号
		setAlarmMemCache(urt, IdCreater.getUUid(), "25", value.substring(25, 26),xcstateOld,25,id);
		// 仓温报警信号
		setAlarmMemCache(urt, IdCreater.getUUid(), "26", value.substring(24, 25),xcstateOld,24,id);
		// 机滤堵塞信号
		setAlarmMemCache(urt, IdCreater.getUUid(), "27", value.substring(23, 24),xcstateOld,23,id);
		// 燃油堵塞信号
		setAlarmMemCache(urt, IdCreater.getUUid(), "28", value.substring(22, 23),xcstateOld,22,id);
		// 机油温度报警信号
		setAlarmMemCache(urt, IdCreater.getUUid(), "29", value.substring(21, 22),xcstateOld,21,id);
		// 燃油警告
		setAlarmMemCache(urt, IdCreater.getUUid(), "16", value.substring(20, 21),xcstateOld,20,id);
		// 空档滑行告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "46", value.substring(19, 20),xcstateOld,19,id);
		// 超长怠速告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "50", value.substring(18, 19),xcstateOld,18,id);
		// 怠速空调告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "51", value.substring(17, 18),xcstateOld,17,id);
		// 发动机超转告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "49", value.substring(16, 17),xcstateOld,16,id);
		// 急加速报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "47", value.substring(15, 16),xcstateOld,15,id);
		// 急减速报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "48", value.substring(14, 15),xcstateOld,14,id);
		// 门开告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "75", value.substring(13, 14),xcstateOld,13,id);
		// 冷却液温度过高报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "76", value.substring(12, 13),xcstateOld,12,id);
		// 蓄电池电压报警
		setAlarmMemCache(urt, IdCreater.getUUid(), "77", value.substring(11, 12),xcstateOld,11,id);
		// ABS故障告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "78", value.substring(10, 11),xcstateOld,10,id);
		// CAN总线通讯故障告警
		setAlarmMemCache(urt, IdCreater.getUUid(), "96", value.substring(9, 10),xcstateOld,9,id);
	}
	
	@SuppressWarnings("unchecked")
	public void setAlarmMemCache(Up_InfoContent urt, String uuid, String type,
			String state, String id) throws ParseException {
		log.debug(NAME + "alarm_type_id:" + type + ",alarm_alert:" + state);
		urt.setAlarm_type_id(type);
		Map map = null;
		if (state != null && !state.equals("")) {
			// log.info("<CachePattern> "+type+"告警处理开始");
			if (Constant.isstartMemcache.equals("1")
					&& Constant.getMemcachedClient().connectState()) {
				Object obj = Constant.getMemcachedClient().getObject(urt.getTerminalId() + type); 
				if (obj!= null && !obj.equals("")) {
					map = (Map) obj;
					// log.info(NAME+"读取memcache缓存!");
				} else {
					map = Constant.cacheMap.get(urt.getTerminalId() + type);
				}
			} else {
				// log.info(NAME+"读取本地cache缓存!");
				map = Constant.cacheMap.get(urt.getTerminalId() + type);
			}
			if (state.equals(num1)) {
				if (map == null || map.size() == 0) {
					log.info("<CachePattern> " + type + "告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildInsertAlarmSql(urt,
									uuid, type, state,id));
					log
							.info("<CachePattern> update 告警表中  " + type
									+ " 告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
					// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：开启");
					setCachePattern(urt, uuid, type, state);
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),type,
					// urt);
					log.info("<CachePattern> " + type + "告警已开启，*缓存*车辆"
							+ urt.getTerminalId() + " " + type + "告警信息成功");
				}
			} else {
				log.info("<CachePattern> " + type + "告警关闭");
				if (map != null && map.size() > 0) {
					DBBuffer.getInstance().add(
							BuildSQL.getInstance()
									.buildUpdateAlarmSql(urt,type, map));
					log
							.info("<CachePattern> update 告警表中  " + type
									+ " 告警状态：关闭");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
					// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：关闭");
					Constant.getMemcachedClient().delete(
							urt.getTerminalId() + type);
					Constant.cacheMap.remove(urt.getTerminalId() + type);
					log.info("<CachePattern> " + type + "告警已关闭，清除车辆"
							+ urt.getTerminalId() + " " + type + "告警*缓存*成功");
				}else{
					log.info(NAME+ type + "告警关闭失败，缓存中未发现告警开启标识");
				}
			}
			// log.info("<CachePattern> "+type+"告警处理结束");
//			map = null;
		}
	}
	

	@SuppressWarnings("unchecked")
	public void setAlarmMemCache(Up_InfoContent urt, String uuid, String type,
			String state, String state_old, int i, String id) throws ParseException {
		log.debug(NAME + "alarm_type_id:" + type + ",alarm_alert:" + state);
		urt.setAlarm_type_id(type);
		if (state != null && !state.equals("")) {
			if (state.equals(num1)) {
				if (state_old == null || state_old.equals("")||(state_old != null && !state_old.equals("")&&state_old.substring(i, i+1).equals("0"))) {
					log.info("<CachePattern> " + type + "告警开启");
					DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertAlarmSql(urt,uuid, type, state,id));
					log.info("<CachePattern> update 告警表中  " + type+ " 告警状态：开启");
					setCachePattern(urt, uuid, type, state);
					log.info("<CachePattern> " + type + "告警已开启，*缓存*车辆"+ urt.getTerminalId() + " " + type + "告警信息成功");
				} 
			} else {
				if (state_old != null && !state_old.equals("")&&state_old.substring(i, i+1).equals("1")) {
					Map map = null;
					if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
						Object obj = Constant.getMemcachedClient().getObject(urt.getTerminalId()+type);
						if(obj!=null&&!obj.equals("")){
							map = (Map)obj;
						}else{
							map = Constant.cacheMap.get(urt.getTerminalId()+type);
						}
					}
					log.info("<CachePattern> " + type + "告警关闭");
					if(map!=null&&map.size()>0){
						DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateAlarmSql(urt,type, map));
						log.info("<CachePattern> update 告警表中  " + type+ " 告警状态：关闭");
						// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
						// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：关闭");
						Constant.getMemcachedClient().delete(urt.getTerminalId() + type);
						Constant.cacheMap.remove(urt.getTerminalId() + type);
						log.info("<CachePattern> " + type + "告警已关闭，清除车辆"+ urt.getTerminalId() + " " + type + "告警*缓存*成功");
					}else{
						log.info(NAME+ type + "告警关闭失败，缓存中未发现告警开启标识");
					}
				}
			}
//			log.info("<CachePattern> "+type+"告警处理结束");
		}
	}

	@SuppressWarnings("unchecked")
	public void setAlarmSeqMemCache(Up_InfoContent urt, String uuid,
			String type, String state, String state_old, int i, String id, String alarmSeq) throws ParseException {
		log.debug(NAME + "alarm_type_id:" + type + ",alarm_alert:" + state);
		urt.setAlarm_type_id(type);
//		Map map = null;
		if (state != null && !state.equals("")) {
			if (state.equals(num1)) {
				if (state_old == null || state_old.equals("") ||(state_old != null && !state_old.equals("")&&state_old.substring(i, i+1).equals("0"))) {
					log.info("<CachePattern> " + type + "告警开启");
					DBBuffer.getInstance().add(
							BuildSQL.getInstance().buildInsertAlarmSql(urt,
									uuid, type, state,id));
					log
							.info("<CachePattern> update 告警表中  " + type
									+ " 告警状态：开启");
					// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
					// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：开启");
					setCachePattern(urt, uuid, type, state);
					// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),type,
					// urt);
					log.info("<CachePattern> " + type + "告警已开启，*缓存*车辆"
							+ urt.getTerminalId() + " " + type + "告警信息成功");
				} else {
//					@SuppressWarnings("unused")
//					Map map = null;
//					if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
//						Object obj = Constant.getMemcachedClient().getObject(urt.getTerminalId()+type);
//						if(obj!=null&&!obj.equals("")){
//							map = (Map)obj;
//						}else{
//							map = Constant.cacheMap.get(urt.getTerminalId()+type);
//						}
//					}
					if(alarmSeq!=null&&!alarmSeq.equals("")){
						String seq = getAlarm_Seq(alarmSeq, type);
						String nseq = getAlarm_Seq(urt, type);
						if (seq != null && !seq.equals("") && !nseq.equalsIgnoreCase(seq)) {
							log.info("<CachePattern> " + type + "告警开启");
							DBBuffer.getInstance().add(BuildSQL.getInstance().buildInsertAlarmSql(urt, uuid, type, state, seq,id));
							log.info("<CachePattern> update 告警表中  " + type + " 告警状态：开启");
							// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
							// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：开启");
							setCachePattern(urt, uuid, type, state,nseq);
							// BuildSQL.getInstance().isHasQuotas(urt.getTerminalId(),type,
							// urt);
							log.info("<CachePattern> " + type + "告警已开启，*缓存*车辆" + urt.getTerminalId() + " " + type + "告警信息成功");
						}
						seq = null;
						nseq = null;
					}
//					map = null;
				}
			} else {
				if (state_old != null && !state_old.equals("")&&state_old.substring(i, i+1).equals("1")) {
					Map map = null;
					if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
						Object obj = Constant.getMemcachedClient().getObject(urt.getTerminalId()+type);
						if(obj!=null&&!obj.equals("")){
							map = (Map)obj;
						}else{
							map = Constant.cacheMap.get(urt.getTerminalId()+type);
						}
					}
					log.info("<CachePattern> " + type + "告警关闭");
					if(map!=null&&map.size()>0){
						DBBuffer.getInstance().add(
								BuildSQL.getInstance().buildUpdateSeqAlarmSql(urt,type,map));
						log
								.info("<CachePattern> update 告警表中  " + type
										+ " 告警状态：关闭");
						// DBBuffer.getInstance().add(BuildSQL.getInstance().buildUpdateTerminalSosState(urt));
						// log.info("<CachePattern-setSosAlarmMemCache>update 终端"+urt.getTerminalId()+" sos 告警状态：关闭");
						Constant.getMemcachedClient().delete(
								urt.getTerminalId() + type);
						Constant.cacheMap.remove(urt.getTerminalId() + type);
						log.info("<CachePattern> " + type + "告警已关闭，清除车辆"
								+ urt.getTerminalId() + " " + type + "告警*缓存*成功");
					}else{
						log.info(NAME+ type + "告警关闭失败，缓存中未发现告警开启标识");
					}
//					map = null;
				}
			}
			// log.info("<CachePattern> "+type+"告警处理结束");
//			map = null;
		}
	}

	private String getAlarm_Seq(Up_InfoContent urt,String type) {
		String seq = urt.getAlarm_seq();
		if(seq !=null&&!seq.equals("")){
			if(type.equals("53")){
				return seq.substring(1, 2);
			}else if(type.equals("54")){
				return seq.substring(2, 3);
			}else if(type.equals("59")){
				return seq.substring(3, 4);
			}else if(type.equals("60")){
				return seq.substring(4, 5);
			}else if(type.equals("61")){
				return seq.substring(5, 6);
			}else if(type.equals("84")){
				return seq.substring(6, 7);
			}else if(type.equals("40")){
				return seq.substring(7, 8);
			}else{
				return null;
			}
		}else{
			return null;
		}
		
		
	}
	
	private String getAlarm_Seq(String seq,String type) {
		if(seq !=null&&!seq.equals("")){
			if(type.equals("53")){
				return seq.substring(1, 2);
			}else if(type.equals("54")){
				return seq.substring(2, 3);
			}else if(type.equals("59")){
				return seq.substring(3, 4);
			}else if(type.equals("60")){
				return seq.substring(4, 5);
			}else if(type.equals("61")){
				return seq.substring(5, 6);
			}else if(type.equals("84")){
				return seq.substring(6, 7);
			}else if(type.equals("40")){
				return seq.substring(7, 8);
			}else{
				return null;
			}
		}else{
			return null;
		}
		
		
	}
}
