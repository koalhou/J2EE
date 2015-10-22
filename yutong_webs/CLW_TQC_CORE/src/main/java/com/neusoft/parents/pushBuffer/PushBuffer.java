package com.neusoft.parents.pushBuffer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.vncs.util.StringUtil;

public class PushBuffer implements Runnable {

	private static Logger log = LoggerFactory.getLogger(PushBuffer.class);

	private static final PushBuffer eventBuffer = new PushBuffer();

	private static final String NAME = "<EventBuffer>";

	private LinkedList<BasicObject> eventQueue;

	private ThreadPool pool = ThreadPool.instance(); // 需要修改为事件处理线程池

	private boolean shutdownFlag = true;

	private PushBuffer() {
		eventQueue = new LinkedList<BasicObject>();
		shutdownFlag = false;
	}

	public static PushBuffer getInstance() {
		return eventBuffer;
	}

	/**
	 * 向队列中加入一条执行指令
	 * 
	 * @param sql
	 */
	public synchronized void add(BasicObject sql) {
		eventQueue.offer(sql);
	}

	/**
	 * 向队列中加入一批执行指令
	 * 
	 * @param sqlList
	 */
	public synchronized void add(List<BasicObject> sqlList) {
		eventQueue.addAll(sqlList);
	}

	class ExeSqlRunner implements Runnable {
		private List<BasicObject> events;

		public ExeSqlRunner(List<BasicObject> events) {
			this.events = events;
		}

		public void run() {
			try {
				//log.info(NAME + "开始将缓冲队列中的" + events.size() + "个event 加入队列");
				BatchEvent(events);
				 Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDownSuccess")));

				//log.info(NAME + "已成功将缓冲队列中的" + events.size() + "个event 执行！");
			} catch (DataAccessException e) {
				log.error(NAME + "缓冲队列批量处理时出现数据库异常：" + e);
			} catch (Exception e) {
				log.error(NAME + "缓冲队列批量处理时出现系统异常：" + e);
			}
		}
	}

	@SuppressWarnings("static-access")
	public void run() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[EventBuf]");
		
		while (!shutdownFlag) {
			List<BasicObject> events = getEventsFromQueue();
			if (null == events || 0 == events.size()) {
				log.debug(NAME + "事件处理缓冲队列中暂时没有数据！");
				try {
					Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDownQueueIsNull")));  
				} catch (InterruptedException e) {
					log.error(NAME + "事件处理缓冲队列处理在休眠时出现异常，" + e);
				}
				continue;
			}
			ExeSqlRunner runner = new ExeSqlRunner(events);
			if (!pool.start(runner, pool.HIGH_PRIORITY)) { // 需要增加一个事件线程池
				log.info(NAME
						+ "用于执行DB数据入库的线程池已满！将该批待执行sql重新放入缓存中，并休眠");
				add(events);
				try {
					Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDownThreadPoolFull")));
				} catch (InterruptedException e) {
					log.error(NAME + "数据库缓冲队列处理在休眠时出现异常，" + e);
				}
			} else {
				// log.info(NAME+"用于执行Event数据入库的线程正常启动！");
			}
			//log.warn(NAME + "当前入库队列大小为:" + eventQueue.size());
		}
		// log.warn(NAME+"事件处理缓冲队列处理终止！");
	}

	private List<BasicObject> getEventsFromQueue() {

		int curQueueSize = eventQueue.size();
		if (curQueueSize > 0) {
			int count = 0;
			if (curQueueSize <= 300) { // 不够或刚够一页数据
				count = curQueueSize;
			} else { // 多于一页数据
				count = 300;
			}
			List<BasicObject> list = new ArrayList<BasicObject>();
			synchronized (this) {
				for (int i = 0; i < count; i++) {
					list.add(eventQueue.poll());
				}
			}

			//log.info(NAME + "从数据库缓冲队列中取出" + count + "个event语句。");
			return list;

		} else {
			log.debug(NAME + "数据库缓冲队列目前为空！");
			return null;
		}
	}

	private void BatchEvent(List<BasicObject> list) throws Exception {
		//log.info(NAME+"开始执行反射类方法.............");
		for (BasicObject events : list) {
			String objectName = events.getObjectName();
			String functionName = events.getFunctionName();
			List paramsList = events.getParamsList();
			Class<?> clazz = Class.forName(objectName);
			Object[] args=new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				args[i] = paramsList.get(i);
			}
			//触发要执行的方法
			invokeMethod(clazz.newInstance(), functionName, args);

		}
	}

	public Object invokeMethod(Object methodObject, String methodName, Object[] args)
			throws Exception {
		Class ownerClass = methodObject.getClass();
		Class[] argsClass = new Class[args.length];
		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
			//由于map类型的时候，系统把map类型的参数当成org.apache.commons.collections.map.ListOrderedMap类型，
			//所以下面暂时做一下判断转换成java.util.Map类型，否则会报错误。解决办法可能是commons-collections-3.1.jar版本问题
			if(argsClass[i].toString().equals("class org.apache.commons.collections.map.ListOrderedMap"))
			{
				argsClass[i]=Map.class;
			}
		}
		Method method = ownerClass.getMethod(methodName, argsClass);
		return method.invoke(methodObject, args);
		
	}

	public void shutdown() {
		log.info("<eventQueue>开始执行线程池关闭操作");
		shutdownFlag = true;

		pool.shutdown();
		log.info("<eventQueue>eventQueue", "线程池关闭结束！");
	}

}
