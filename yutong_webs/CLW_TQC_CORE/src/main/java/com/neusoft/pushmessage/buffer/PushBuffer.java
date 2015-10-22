package com.neusoft.pushmessage.buffer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.mobile.bean.AlarmBean;
import com.neusoft.mobile.bean.MessageBean;
import com.neusoft.mobile.bean.PhotoBean;
import com.neusoft.mobile.dao.impl.PushDAO;
import com.neusoft.pushmessage.PushMessage;
import com.neusoft.pushmessage.sql.BuildPushSql;
import com.neusoft.pushmessage.util.JacksonUtils;

public class PushBuffer implements Runnable {

	private static Logger log = LoggerFactory.getLogger(PushBuffer.class);

	private static final PushBuffer dbBuffer = new PushBuffer();

	private static final String NAME = "<PushBuffer>";

	private Queue<PushBean> pushQueue;

	private PushThreadPool pool = PushThreadPool.instance();

	private boolean shutdownFlag = true;

	private PushDAO pushDAO = null;

	private PushBuffer() {
		System.out.println("00000000000000000000000000000");
		pushQueue = new LinkedList<PushBean>();
		System.out.println("1111111111111111111111111111");
		
		pushDAO = (PushDAO) SpringBootStrap.getInstance().getBean("pushDAO");
		System.out.println("222222222222222222222222222222222222");
		shutdownFlag = false;
	}

	public static PushBuffer getInstance() {
		return dbBuffer;
	}

	/**
	 * 向队列中加入一条push消息
	 * 
	 * @param sql
	 */
	public synchronized void add(PushBean sql) {
		pushQueue.offer(sql);
	}

	/**
	 * 向队列中加入一批push消息
	 * 
	 * @param sqlList
	 */
	public synchronized void add(List<PushBean> sqlList) {
		pushQueue.addAll(sqlList);
	}

	class ExeSqlRunner implements Runnable {
		private List<PushBean> msg;

		public ExeSqlRunner(List<PushBean> msg) {
			this.msg = msg;
		}

		public void run() {
			try {
				log.info(NAME + "开始将缓冲队列中的" + msg.size() + "个push信息");
				pushMessage(msg);
				log.info(NAME + "已成功将缓冲队列中的" + msg.size() + "个push消息！");
			} catch (Exception e) {
				log.error(NAME + "缓冲队列批量处理时出现系统异常：" + e);
			}
		}
	}

	@SuppressWarnings({ "static-access", "unchecked" })
	public void run() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[PushBuf]");
		while (!shutdownFlag) {
			List msg = getPushsFromQueue();
			if (null == msg || 0 == msg.size()) {
				log.debug(NAME + "缓冲队列中暂时没有数据！");
				try {
					Thread.sleep(Long.parseLong(Config.props
							.getProperty("sleepTimeWhenPushQueueIsNull")));
				} catch (InterruptedException e) {
					log.error(NAME + "缓冲队列处理在休眠时出现异常，" + e);
				}
				continue;
			}
			ExeSqlRunner runner = new ExeSqlRunner(msg);
			if (!pool.start(runner, pool.HIGH_PRIORITY)) {
				log.info(NAME
						+ "用于执行推送的线程池已满！将该批待执行push信息重新放入缓存中，并休眠"
						+ Config.props
								.getProperty("sleepTimeWhenPushThreadPoolFull")
						+ "毫秒!");
				add(msg);
				try {
					Thread.sleep(Long.parseLong(Config.props
							.getProperty("sleepTimeWhenPushThreadPoolFull")));
				} catch (InterruptedException e) {
					log.error(NAME + "缓冲队列处理在休眠时出现异常，" + e);
				}
			} else {
				// log.info(NAME+"用于执行DB数据入库的线程正常启动！");
			}
			log.warn(NAME + "当前推送队列大小为:" + pushQueue.size());
		}
	}

	private List<PushBean> getPushsFromQueue() {

		int curQueueSize = pushQueue.size();
		if (curQueueSize > 0) {
			int count = 0;
			if (curQueueSize <= Integer.parseInt(Config.props
					.getProperty("countOfExePushPerTime"))) { // 不够或刚够一页数据
				count = curQueueSize;
			} else { // 多于一页数据
				count = Integer.parseInt(Config.props
						.getProperty("countOfExePushPerTime"));
			}
			List<PushBean> list = new ArrayList<PushBean>();

			synchronized (this) {
				for (int i = 0; i < count; i++) {
					list.add(pushQueue.poll());
				}
			}

			log.info(NAME + "从缓冲队列中取出" + count + "个push消息。");
			return list;

		} else {
			log.debug(NAME + "缓冲队列目前为空！");
			return null;
		}
	}

	public void shutdown() {
		log.info("<pushQueue>开始执行线程池关闭操作");
		shutdownFlag = true;

		pool.shutdown();
		log.info("<pushQueue>pushQueue", "线程池关闭结束！");
	}

	/**
	 * 推送消息方法
	 * 
	 * @param msg
	 */
	/**
	 * @param msgs
	 */
	/**
	 * @param msgs
	 */
	private void pushMessage(List<PushBean> msgs) {
		try {
			for (PushBean msg : msgs) {
				if (msg.getType() != null && !msg.getType().equals("")) {
					Map<String, Object> map = null;
					if (msg.getType().equals("2")) {
						MessageBean message = msg.getMessageBean();
						// PushMessage.pushPassThroughMessage(Config.props.getProperty("push_ip"),
						// Integer.parseInt(Config.props.getProperty("push_port")),
						// message.getClient_id(), message.setMessage());
						pushDAO.merge(BuildPushSql.getInstance()
								.mergePushMsg(message, "0"));
						pushDAO.updatePushNum(message.getMsg_index(),
								message.getUser_id());
						String obj = JacksonUtils.toJson(message);
						map = PushMessage.pushPassThroughMessage(message
								.getClient_id(), obj);
						if (map.get("result").equals(Constant.PUSH_OK)) {
							log.info(NAME + "消息text:" + obj
									+ "已推送给个推平台,推送返回结果：" + map.toString());
							pushDAO.merge(BuildPushSql.getInstance().updatePushMsgState(message.getUser_id(), "1", message.getClient_id(), message.getMsg_id()));
						} else {
							log.info(NAME + "消息text:" + obj
									+ "推送返回结果：" + map.toString());
							pushDAO.merge(BuildPushSql.getInstance().updatePushMsgState(message.getUser_id(), "2", message.getClient_id(), message.getMsg_id()));
						}
					} else if (msg.getType().equals("alarm")) {
						AlarmBean alarm = msg.getAlarmBean();
						pushDAO.merge(BuildPushSql.getInstance().mergePushAlarm(alarm, "0"));
						String obj = JacksonUtils.toJson(alarm);
						map = PushMessage.pushPassThroughMessage(alarm
								.getClient_id(), obj);
						if (map.get("result").equals(Constant.PUSH_OK)) {
							log.info(NAME + "告警text:" + obj
									+ "已推送给个推平台,推送返回结果：" + map.toString());
							pushDAO.merge(BuildPushSql.getInstance().updatePushAlarmState(alarm.getUser_id(), "1", alarm.getClient_id(), alarm.getAlarm_id()));
						} else {
							log.info(NAME + "告警text:" + obj + "推送返回结果：" + map.toString());
							pushDAO.merge(BuildPushSql.getInstance().updatePushAlarmState(alarm.getUser_id(), "2", alarm.getClient_id(), alarm.getAlarm_id()));
						}
					} else if (msg.getType().equals("1")) {
						PhotoBean photo = msg.getPhotoBean();
						pushDAO.merge(BuildPushSql.getInstance().mergePushPhoto(photo, "0"));
						String obj = JacksonUtils.toJson(photo);
						map = PushMessage.pushPassThroughMessage(photo.getClient_id(), obj);
						if (map.get("result").equals(Constant.PUSH_OK)) {
							log.info(NAME + "照片text:" + obj
									+ "已推送给个推平台,推送返回结果：" + map.toString());
							pushDAO.merge(BuildPushSql.getInstance().updatePushPhotoState(photo.getUser_id(), "1", photo.getClient_id(), photo.getPhoto_id()));
						} else {
							log.info(NAME + "照片text:" + obj
									+ "推送返回结果：" + map.toString());
							pushDAO.merge(BuildPushSql.getInstance().updatePushPhotoState(photo.getUser_id(), "2", photo.getClient_id(), photo.getPhoto_id()));
						}
					} else {
						log.info(NAME + "未知推送类型!");
					}
				}
			}
		} catch (IOException e) {
			log.error(NAME+"推送时发生异常："+e.getMessage());
			e.printStackTrace();
		}
	}
}
