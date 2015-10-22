package com.neusoft.SchoolBus.vncs.manage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.SchoolBus.vncs.dao.impl.QuartzDAO;
import com.neusoft.SchoolBus.vncs.thread.QuartzCountDown;
import com.neusoft.SchoolBus.vncs.thread.QuartzThread;
import com.neusoft.SchoolBus.vncs.thread.ThreadPool;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.util.StringUtil;

public class QuartzManager {
	private   Logger log = LoggerFactory.getLogger(QuartzManager.class);        
	private static final String NAME = "<QuartzManager>";
	private static ThreadPool pool = null;
	private static final QuartzManager qManager = new QuartzManager();
	public static boolean runflag = true;
	private static String date = null;
	private static SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");

	public static void initialThreadPool() {
		pool = ThreadPool.instance();
		pool.setDebug(true);
	}
	
	@SuppressWarnings("unused")
	private static void recycleThreadPool() {
		if (null != null) {
			pool.shutdown();
		}
	}
	
	private QuartzManager() {
	}

	public static QuartzManager getInstance() {
		return qManager;
	}
	
	public void init() {
		// String systime = sendCommandDAO.getSysTime();
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[quartzInit]");
		// GregorianCalendar gc =new GregorianCalendar();
		// SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
		boolean b = false;
		try {
			int coreActive = Integer.parseInt(Config.props
					.getProperty("core.active.time"));
			b = ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID, coreActive);
			log.info(NAME+"activeCoreDAOlist:10"+b);
			if (!b) {
				log.info(NAME+"本机不是路线文件生成服务器。");
				return;
			}
			initialThreadPool();
			log.info(NAME+" 路线文件生成开始！runflag:" + runflag);
			
			if (runflag) {
				runflag = false;// 设置独占运行模式
				QuartzCountDown.instance().setCount(0);
				date = pathDf.format(new Date());
				QuartzDAO qdao = (QuartzDAO) SpringBootStrap.getInstance().getBean("qdao");
				List<String> vehicleList = qdao.getVehicleList();
				if (vehicleList!= null&& vehicleList.size() > 0) {
					for(String vin:vehicleList){
						QuartzThread newthread = new QuartzThread(date,vin);
						//	log.info(NAME+" 运行线程数"+MyCountDown.instance().getCount())+"总数："+Config.props.getProperty("maxThreadReport"));
						while (QuartzCountDown.instance().getCount() >= Integer.parseInt(Config.props.getProperty("maxQuartzThread"))) {
							try {
								Thread.sleep(3000);
								log.info(NAME+"路线文件生成线程已满，等待中。运行线程"+QuartzCountDown.instance().getCount());
							} catch (Exception e) {
								log.info(NAME+"路线文件生成线程异常："+e.toString());
							}
						}
						newthread.start();
//						Thread.sleep(1000);
					}
				}
				runflag = true;
			}
		}catch(Exception e) {
			QuartzCountDown.instance().setCount(0);
			runflag = true;
			log.error(NAME+
					"QuartzManager错误：" + e.toString());
			e.printStackTrace();
		}
		log.info(NAME+" 本次路线文件生成结束！ runflag:" + runflag);
	}
}
