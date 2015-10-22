package com.neusoft.smsplatform.message.buffer.send;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.SchoolBus.vncs.domain.XcStuSmsVTBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.manage.SendxcmsmCommandManager;
import com.neusoft.SchoolBus.vncs.manage.SmsOrderManager;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.util.IdCreater;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.smsplatform.configuration.MessageConfig;
import com.neusoft.smsplatform.message.inside.msg.req.SendMessageReq;
import com.neusoft.smsplatform.message.inside.msg.utils.SmsCommonMsgUtils;
import com.neusoft.smsplatform.message.util.MessageBuildSQL;
import com.neusoft.smsplatform.message.util.SendMethod;

/**
 * 安芯平台向翔东平台发送消息的队列
 * @author chenqiong
 *
 */
public class SendBuffer implements Runnable {

	private static Logger log = LoggerFactory.getLogger(SendBuffer.class);

	private static final SendBuffer dbBuffer = new SendBuffer();

	private static final String NAME = "<SendBuffer>";

	private Queue<SendMessageReq> sqlQueue;

	private SendThreadPool pool = SendThreadPool.instance();

	private boolean shutdownFlag = true;

//	private static UpdateDAO updatedao = (UpdateDAO) SpringBootStrap
//			.getInstance().getBean("updatedao");

	private SendBuffer() {
		sqlQueue = new LinkedList<SendMessageReq>();
		shutdownFlag = false;
	}

	public static SendBuffer getInstance() {
		return dbBuffer;
	}

	/**
	 * 向队列中加入一条sql
	 * 
	 * @param sql
	 */
	public synchronized void add(SendMessageReq sql) {
		sqlQueue.offer(sql);
	}

	/**
	 * 向队列中加入一批sql
	 * 
	 * @param sqlList
	 */
	public synchronized void add(List<SendMessageReq> sqlList) {
		for (SendMessageReq cc : sqlList) {
			sqlQueue.add(cc);
		}
	}

	class ExeSqlRunner implements Runnable {
		private List<SendMessageReq> sqls;

		public ExeSqlRunner(List<SendMessageReq> sqls) {
			this.sqls = sqls;
		}

		public void run() {
			try {
				log.info(NAME + "开始将发送" + sqls.size() + "个短信！");
				// 批量发送
				batchSend(sqls);
				log.info(NAME + "已成功将发送" + sqls.size() + "个短信！");
			} catch(UnsupportedEncodingException e){
				log.error(NAME + "按照指定编码转移时发生异常：" + e);
				e.printStackTrace();
			} catch (DataAccessException e) {
				log.error(NAME + "短信发送队列批量入库时出现数据库异常：" + e);
				e.printStackTrace();
			} catch (Exception e) {
				log.error(NAME + "短信发送队列批量入库时出现系统异常：" + e);
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("static-access")
	public void run() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[SyncBuf]");
		while (!shutdownFlag) {
			List<SendMessageReq> sqls = getSqlsFromQueue();
			if (null == sqls || 0 == sqls.size()) {
				log.debug(NAME + "短信发送队列中暂时没有数据！");
				try {
					Thread.sleep(Long.parseLong(MessageConfig.props
							.getProperty("sleepTimeWhenSendQueueIsNull")));
				} catch (InterruptedException e) {
					log.error(NAME + "短信发送队列处理在休眠时出现异常，" + e);
				}
				continue;
			}
			ExeSqlRunner runner = new ExeSqlRunner(sqls);
			if (!pool.start(runner, pool.HIGH_PRIORITY)) {
				log.info(NAME
						+ "用于发送短信的线程池已满！将该批待发送短信重新放入缓存中，并休眠"
						+ MessageConfig.props
								.getProperty("sleepTimeWhenSendThreadPoolFull")
						+ "毫秒!");
				add(sqls);
				try {
					Thread.sleep(Long.parseLong(MessageConfig.props
							.getProperty("sleepTimeWhenSendThreadPoolFull")));
				} catch (InterruptedException e) {
					log.error(NAME + "短信发送队列处理在休眠时出现异常，" + e);
				}
			} else {
				// log.info(NAME+"用于执行DB数据入库的线程正常启动！");
			}
			log.warn(NAME + "当前短信发送队列大小为:" + sqlQueue.size());
		}
		// log.warn(NAME+"数据库缓冲队列处理终止！");
	}

	@SuppressWarnings("unchecked")
	public void batchSend(List<SendMessageReq> sqls) throws UnsupportedEncodingException {
		for (SendMessageReq req : sqls) {
//			System.out.println(SmsOrderManager.xcStuSmsVTMap);
			// 取翔东短信配置缓存
			List<XcStuSmsVTBean> list = SmsOrderManager.getInstance()
					.getXcSmsVTValue(Integer.parseInt(req.getStu_id()) + req.getEvent_type());
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					XcStuSmsVTBean bean = list.get(i);
					if (i == 0) {
						req.setPhone1(bean.getCell_number());
						req.setPhone2(null);
						req.setPhone3(null);
					} else if (i == 1) {
						req.setPhone2(bean.getCell_number());
						req.setPhone3(null);
					} else if (i == 2) {
						req.setPhone3(bean.getCell_number());
					} else {
						break;
					}
				}
				req.setCommand(SendMessageReq.COMMAND);
				req.setSeqLength(SmsCommonMsgUtils.getSeq());
				String address = SendMethod.SendCommand(req.getBytes());
				if (address != null && !address.equals("")) {
					log.info(NAME + "该短信"+req.formatToString()+"发送成功！");
					Map map = new HashMap();
					Up_InfoContent urt = new Up_InfoContent();
					XcStudentBean sb = SendxcmsmCommandManager
							.getInstance()
							.getStudentValue(Constant.STUDENT + req.getStu_id());
					urt.setVehicle_vin(req.getVehicle_vin());
					urt.setEnterprise_id(sb.getEnterprise_id());
					urt.setOrganization_id(sb.getOrganization_id());
					urt.setMsg(req.getMessage());
					urt.setStu_id(req.getStu_id());
					urt.setPid(IdCreater.getUUid());
					for (int i = 0; i < list.size(); i++) {
						XcStuSmsVTBean bean = list.get(i);
						String id = IdCreater.getUUid();
						urt.setTel(bean.getCell_number());
						urt.setRelative_type(bean.getRelative_type());
						if(bean.getRelative_type().equals("0")||bean.getRelative_type().equals("1")||bean.getRelative_type().equals("2")
								||bean.getRelative_type().equals("3")||bean.getRelative_type().equals("4")||bean.getRelative_type().equals("5")){
							urt.setParents_flag("1");
						}else{
							urt.setParents_flag("0");
						}
						DBBuffer.getInstance().add(MessageBuildSQL.getInstance()
								.buildInsertSmsRecordSQL(urt,
										req.getEvent_type(), id));
						if (i == 0) {
							map.put("msgid1", id);
							map.put("phone1", bean.getCell_number());
						} else if (i == 1) {
							map.put("msgid2", id);
							map.put("phone2", bean.getCell_number());
						} else if (i == 2) {
							map.put("msgid3", id);
							map.put("phone3", bean.getCell_number());
						} else {
							break;
						}
					}
					map.put("recordid", req.getRecordid());
					map.put("message", req.getMessage());
					map.put("count", 1);

					if (Constant.isstartMemcache.equals("1")
							&& Constant.getMemcachedClient().connectState()) {
						Constant.getMemcachedClient().insert(
								req.getSeqLength(), map);
					}
					Constant.ytbsendcmdMap.put(req.getSeqLength(), map);
				} else {
					log.info(NAME + "该短信发送失败！");
					if (sqlQueue.size() >= Integer.parseInt(MessageConfig.props
							.getProperty("smsqueueSize"))) {
						log.error(NAME + "队列存储容量已满,发送失败的信息将丢弃："
								+ req.getMessage());
					} else {
						add(req);
						log.info(NAME + "发送失败的信息重新放入队列中，等待发送");
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	private List<SendMessageReq> getSqlsFromQueue() {

		int curQueueSize = sqlQueue.size();
		if (curQueueSize > 0) {
			int count = 0;
			if (curQueueSize <= Integer.parseInt(MessageConfig.props
					.getProperty("countOfExeSendPerTime"))) { // 不够或刚够一页数据
				count = curQueueSize;
			} else { // 多于一页数据
				count = Integer.parseInt(MessageConfig.props
						.getProperty("countOfExeSendPerTime"));
			}
			List<SendMessageReq> sqls = new ArrayList<SendMessageReq>();

			synchronized (this) {
				for (int i = 0; i < count; i++) {
					sqls.add(sqlQueue.poll());
				}
			}

			log.info(NAME + "从短信发送队列中取出" + count + "个同步信息。");
			return sqls;

		} else {
			log.debug(NAME + "短信发送缓冲队列目前为空！");
			return null;
		}
	}

	public void shutdown() {
		log.info("<sqlQueue>开始执行线程池关闭操作");
		shutdownFlag = true;

		pool.shutdown();
		log.info("<sqlQueue>sqlQueue", "线程池关闭结束！");
	}
}
