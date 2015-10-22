package com.neusoft.clw.main;

import org.apache.log4j.xml.DOMConfigurator;

import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.buffer.insert.InsertBuffer;
import com.neusoft.clw.vncs.errorBuffer.ErrorBuffer;
import com.neusoft.clw.vncs.job.DataSyncJob;
import com.neusoft.parents.eventBuffer.EventBuffer;
import com.neusoft.parents.pushmessage.manager.PushMsgBuffer;
import com.neusoft.parents.pushBuffer.PushBuffer;

public class Startup {

//	private final static String NAME = "Startup";
//
//	private static Logger log = LoggerFactory.getLogger(Startup.class);
	// private SendInfoList sendInfo = SendInfoList.getInstance();
	// private UtilInfoList utilInfoList = UtilInfoList.getInstance();
	private static String LOCATION = "classpath:main.xml";
	private static String MEMCACHE_LOCATION = "classpath:xmemcache.xml";
	private static String SMS_LOCATION = "classpath:smsplatform.xml";
//	private ClassPathXmlApplicationContext cx;

	// private static String SENDCMD = "SENDCMD";
	// private static String UPDATESQL = "UPDATESQL";
	protected Startup() {

	}

	private void loadLog4jConfig() {
		String log4jpath = System.getProperty("user.dir")
				+ System.getProperty("file.separator") + "config"
				+ System.getProperty("file.separator") + "log4j.xml";
		System.out.println("log4j文件路径：" + log4jpath);

		DOMConfigurator.configure(log4jpath);
	}

	void init() {
		loadLog4jConfig();
	}

	void start() {
//		Queue.getInstance().queueInit();
		// sendInfo.regedit(SENDCMD);
		// utilInfoList.regedit(UPDATESQL);		
		SpringBootStrap.getInstance().setConfig(LOCATION);
		SpringBootStrap.getInstance().init();
	
		/* 启动数据库缓冲队列多线程入库处理 */
		DBBuffer dbBuffer = DBBuffer.getInstance();
		Thread worker = new Thread(dbBuffer);
		worker.start();
		
		InsertBuffer downBuffer = InsertBuffer.getInstance();
		Thread worker1 = new Thread(downBuffer);
		worker1.start();
		
		ErrorBuffer errorBuffer = ErrorBuffer.getInstance();
		Thread worker5 = new Thread(errorBuffer);
		worker5.start();
	
		MemcacheThread memcache = new MemcacheThread(MEMCACHE_LOCATION);
		memcache.start();
		
		
		PushBuffer buffer = PushBuffer.getInstance();
		Thread worker7 = new Thread(buffer);
		worker7.start();
		
		
		
		//liuja添加start
		
        EventBuffer eventBuffer = EventBuffer.getInstance();
        Thread worker3 = new Thread(eventBuffer);
        worker3.start();
        
        PushMsgBuffer buffer2 = PushMsgBuffer.getInstance();
        Thread worker8 = new Thread(buffer2);
        worker8.start();
      //liuja添加end
		// 执行定时业务处理
		doJob();
		
		EmayThread em = EmayThread.getInstance();
		Thread worker6 = new Thread(em);
		worker6.start();
		
		XdThread xd = new XdThread(SMS_LOCATION);
		xd.start();
		
//		SpringBootStrap.getInstance().setInit(true);
	}

	private void doJob() {
		DataSyncJob dataSyncJob = (DataSyncJob) SpringBootStrap
				.getInstance().getBean("dataSyncJob");
		/* 加载应用配置信息 */
		dataSyncJob.appConfigCacheInit();

		/* 加载终端信息 */
//		dataSyncJob.terminalCacheInit();

		/* 加载不良驾驶信息 */
		dataSyncJob.harmDefCacheInit();
		/* 加载车辆区域信息 */
//		dataSyncJob.vehicle_regionCacheInit();
		/* 加载需要使用的车辆相关信息 */
//		dataSyncJob.vehicleCacheInit();
		
		/*dataSyncJob.driverVehicleCommandInit();*/
		
		dataSyncJob.smsOrderInit();
		//加载员工乘车规划定时任务
		//dataSyncJob.ridingPlanCacheInit();
		
		try {
			dataSyncJob.fullQuantityInit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		//add by liuja  start
		//车辆实时信息
        dataSyncJob.vehicleRealInit();
        dataSyncJob.vinTripInit();
        //推送规则缓存
        //dataSyncJob.ruleCacheInit();
        //add by liuja  end
	}

	public static void main(String[] args) {
//		byte[] value = new byte[2];
//		value[0] = 55;
//		value[1] = 22;
//		System.out.println(Converser.bytesToHexString(value));
		 try {
		 Startup startup = new Startup();
		 startup.init();
		 startup.start();
		            
		 System.out.println("VNCS SYSTEM start successful.");
		
		 } catch (Throwable t) {
//			 log.error(LogFormatter.formatMsg(NAME, "start failed."), t);
			 t.printStackTrace();
		 }
//		String str = "99999999";
//		byte[] b = str.getBytes();
//		System.out.println(b);
//		System.out.println(new String(b));
	}

}
