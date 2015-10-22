package com.neusoft.SchoolBus.vncs.manage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.SchoolBus.vncs.dao.impl.QuartzTripDAO;
import com.neusoft.SchoolBus.vncs.thread.QuartzCountDown;
import com.neusoft.SchoolBus.vncs.thread.QuartzTripThread;
import com.neusoft.SchoolBus.vncs.thread.ThreadPool;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.util.StringUtil;

public class QuartzTripManager {
	private   Logger log = LoggerFactory.getLogger(QuartzTripManager.class);        
	private static final String NAME = "<QuartzTripManager>";
	private static ThreadPool pool = null;
	private static final QuartzTripManager qManager = new QuartzTripManager();
	public static boolean runflag = true;
	private static String date = null;
	//private static SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat pathDf = new SimpleDateFormat("yyyy-MM-dd");

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
	
	private QuartzTripManager() {
	}

	public static QuartzTripManager getInstance() {
		return qManager;
	}
	
	public void init() {		
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[quartzInit]");	
		boolean b = false;
		try {
			/*
			 * 不再判断是否是主核心服务器，因为  “采集路线更新查询” 协议需要查询本地服务器目录是否有文件，
			 * 再决定是否要下发行程文件更新指令,如果只有主核心生成文件，而辅核心没有生成，就会导致重启更新不能成功！
			 */			
			/*int coreActive = Integer.parseInt(Config.props.getProperty("core.active.time"));
			b = ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID, coreActive);
			log.info(NAME+"activeCoreDAOlist:10"+b);
			if (!b) {
				log.info(NAME+"本机不是行程文件生成服务器。");
				return;
			}*/
			
			//判断是不是核心01
			log.info(NAME+"core_id:"+Constant.CORE_ID);
			if(!(Constant.CORE_ID).equals("core01")){
				log.info(NAME+"本机不是行程文件生成服务器。");
				return;
			}
			initialThreadPool();
			log.info(NAME+" 行程文件生成开始！runflag:" + runflag);
			
			if (runflag) {
				runflag = false;// 设置独占运行模式
				QuartzCountDown.instance().setCount(0);
				date = pathDf.format(new Date());
				QuartzTripDAO qtdao = (QuartzTripDAO) SpringBootStrap.getInstance().getBean("qtdao");
				
				List<String> vehicleList = qtdao.getVehicleList();
					if (vehicleList!= null&& vehicleList.size() > 0) {
						for(String vin:vehicleList){
							QuartzTripThread newthread = new QuartzTripThread(date,vin);
							//	log.info(NAME+" 运行线程数"+MyCountDown.instance().getCount())+"总数："+Config.props.getProperty("maxThreadReport"));
							while (QuartzCountDown.instance().getCount() >= Integer.parseInt(Config.props.getProperty("maxQuartzThread"))) {
								try {
									Thread.sleep(3000);
									log.info(NAME+"行程文件生成线程已满，等待中。运行线程"+QuartzCountDown.instance().getCount());
								} catch (Exception e) {
									log.info(NAME+"行程文件生成线程异常："+e.toString());
								}
							}
							newthread.start();
//						    Thread.sleep(1000);
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
		log.info(NAME+" 本次行程文件生成结束！ runflag:" + runflag);
	}
}
