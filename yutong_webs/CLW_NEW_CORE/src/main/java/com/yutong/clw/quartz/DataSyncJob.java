package com.yutong.clw.quartz;

import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.yutong.clw.quartz.managers.NewsManager;
import com.yutong.clw.quartz.managers.QuartzTripManager;
import com.yutong.clw.quartz.managers.SiteNoteManager;
import com.yutong.clw.quartz.managers.SmsOrderManager;
import com.yutong.clw.quartz.managers.SyncManager;
import com.yutong.clw.quartz.managers.TqcOilWearManager;
import com.yutong.clw.quartz.managers.TqcStatisticManager;
import com.yutong.clw.quartz.managers.VehicleRealCacheManager;
import com.yutong.clw.quartz.managers.VinTripCacheManager;
import com.yutong.clw.quartz.managers.cachemanager.AppConfigCacheManager;
import com.yutong.clw.quartz.managers.cachemanager.FullQuantityCacheManager;
import com.yutong.clw.quartz.managers.cachemanager.HarmDefCacheManager;
import com.yutong.clw.quartz.managers.cachemanager.RidingPlanCacheManager;
import com.yutong.clw.quartz.managers.cachemanager.TerminalCacheManager;
import com.yutong.clw.quartz.managers.cachemanager.VehicleCacheManager;
import com.yutong.clw.quartz.managers.command.ReportManager;
import com.yutong.clw.quartz.managers.command.SendCommandManager;
import com.yutong.clw.quartz.managers.command.SendxcmsmCommandManager;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.StringUtil;

public class DataSyncJob {

	private Logger log = LoggerFactory.getLogger(DataSyncJob.class);

	private static final String NAME = "<DataSyncJob>";

	private AppConfigCacheManager appConfigCacheManager;

	private TerminalCacheManager terminalCacheManager;

	private SendCommandManager sendCommandManager;

	private ReportManager reportManager;

	private HarmDefCacheManager harmDefCacheManager;

	private VehicleCacheManager vehicleCacheManager;

	private SendxcmsmCommandManager sendxcmsmCommandManager;

	private QuartzTripManager quartzTripCommandManager;
	
	private TqcStatisticManager tqcStatisticManager;

//	private SyncManager syncCommandManager;

	private SmsOrderManager smsOrderManager;

	private FullQuantityCacheManager fullQuantityCacheManager;
	
	private TqcOilWearManager tqcOilWearManager;
	
	private RidingPlanCacheManager ridingPlanCacheManager; //2013-11-26添加员工乘车规划缓存管理
	
	private VehicleRealCacheManager vehicleRealCacheManager;
	
	private VinTripCacheManager vinTripCacheManager;
	
	private SiteNoteManager siteNoteManager; //增加消息推送
	
	private NewsManager newsManager; //增加消息推送

	public void setFullQuantityCacheManager(
			FullQuantityCacheManager fullQuantityCacheManager) {
		this.fullQuantityCacheManager = fullQuantityCacheManager;
	}

	public SmsOrderManager getSmsOrderManager() {
		return smsOrderManager;
	}

	public void setSmsOrderManager(SmsOrderManager smsOrderManager) {
		this.smsOrderManager = smsOrderManager;
	}

	public void setSyncCommandManager(SyncManager syncCommandManager) {
		this.syncCommandManager = syncCommandManager;
	}

	public QuartzTripManager getQuartzTripCommandManager() {
		return quartzTripCommandManager;
	}

	public void setQuartzTripCommandManager(
			QuartzTripManager quartzTripCommandManager) {
		this.quartzTripCommandManager = quartzTripCommandManager;
	}

	public void setSendxcmsmCommandManager(
			SendxcmsmCommandManager sendxcmsmCommandManager) {
		this.sendxcmsmCommandManager = sendxcmsmCommandManager;
	}

	public SendCommandManager getSendCommandManager() {
		return sendCommandManager;
	}

	public void setSendCommandManager(SendCommandManager sendCommandManager) {
		this.sendCommandManager = sendCommandManager;
	}

	public void setVehicleCacheManager(VehicleCacheManager vehicleCacheManager) {
		this.vehicleCacheManager = vehicleCacheManager;
	}

	public SiteNoteManager getSiteNoteManager() {
		return siteNoteManager;
	}

	public void setSiteNoteManager(SiteNoteManager siteNoteManager) {
		this.siteNoteManager = siteNoteManager;
	}

	public NewsManager getNewsManager() {
		return newsManager;
	}

	public void setNewsManager(NewsManager newsManager) {
		this.newsManager = newsManager;
	}

	public VehicleRealCacheManager getVehicleRealCacheManager() {
		return vehicleRealCacheManager;
	}

	public void setVehicleRealCacheManager(
			VehicleRealCacheManager vehicleRealCacheManager) {
		this.vehicleRealCacheManager = vehicleRealCacheManager;
	}

	/**
	 * 加载应用配置任务
	 */
	public void appConfigCacheInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[appConfigCacheInit]");
		appConfigCacheManager.init();
	}

	/**
	 * 定时发送终端命令
	 */
	public void sendCommandInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[sendCommandInit]");
		sendCommandManager.init();
	}

	/**
	 * 定时报表统计
	 */
	public void reportInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[reportManagerInit]");
		reportManager.init();
	}

	/**
	 * 加载终端增减量信息
	 */
	public void terminalCacheInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[terminalCacheInit]");
		terminalCacheManager.init();
	}

	/**
	 * 不良驾驶信息加载
	 */
	public void harmDefCacheInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[harmDefCacheInit]");
		harmDefCacheManager.init();
	}

	/**
	 * 需要使用的车辆数据加载
	 */
	public void vehicleCacheInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[vehicleCacheInit]");
		vehicleCacheManager.init();
	}

	public void sendXcSMSCommandInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[sendXcSMSCommandInit]");
		sendxcmsmCommandManager.init();
	}

	public void fullQuantityInit() throws InterruptedException {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[fullQuantityInit]");
		fullQuantityCacheManager.init();
	}

	public void syncManagerInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[syncManagerInit]");
		syncCommandManager.init();
	}

	/**
	 * 行程文件时间更新
	 */
	public void quartzTripManagerInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[quartzTripManagerInit]");
		quartzTripCommandManager.init();  //通勤车一期暂时注释   modify by ningdonghai
	}

	/**
	 * 短信订购时间更新
	 */
	public void smsOrderInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[smsOrderInit]");
		//smsOrderManager.init();  --通勤车一期暂时注释   modify by ningdonghai
	}
	
	/**
	 * 通勤车定时统计更新  
	 */
	public void tqcStatisticManagerInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[tqcStatisticManager]");
		tqcStatisticManager.init();  //--通勤车一期暂时注释   modify by ningdonghai
	}
	
	/**
	 * 通勤车统计油耗【天统计】 
	 */
	public void tqcOilWearManagerInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[tqcOilWearManagerInit]");
		tqcOilWearManager.init();  
	}
	
	/**
	 * 加载泰安员工行程规划信息
	 */
	public void ridingPlanCacheInit() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[terminalCacheInit]");
		ridingPlanCacheManager.init();
	}
	
	/**
	 * 车辆实时位置缓存 
	 */
    public void vehicleRealInit()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[vehicleRealInit]");
        vehicleRealCacheManager.init();
    }
    
	/**
	 * 车辆行程缓存 
	 */
    public void vinTripInit()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[vinTripInit]");
        vinTripCacheManager.init();
    }
    
    /**
	 * 每日凌晨清除上一天车辆行程缓存 
	 */
    public void delVinTripInit()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[delVinTripInit]");
        vinTripCacheManager.delInit();
    }
	  
	/**
	 * 消息推送 
	 */
	public void siteNoteInit()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[siteNoteInit]");
        siteNoteManager.init();
    }
	/**
	 * 新闻推送 
	 */
	public void newsInit()
    {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[newsInit]");
        newsManager.init();
    }

	/**
	 * 定时器调度时间更新         侯俊虎修改，原因是该方法容易使quartz.xml文件的配置失效   2013-07-28
	 */
	public void updateTriggersInterval() {
//		try {
//			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
//			MDC.put("modulename", "[chgTrig]");
//
//			log.info(NAME + "开始执行定时器调度时间更新操作。");
//
//			log.debug(NAME + "开始查看全量应用配置定时时间是否更改。");
//			String cronExpOfDB = Config.props.getProperty("appConfigTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("appConfigCacheTrigger", cronExpOfDB);
//			}
//
//			log.debug(NAME + "开始查看终端定时时间是否更改。");
//			cronExpOfDB = Config.props.getProperty("terminalTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("terminalCacheTrigger", cronExpOfDB);
//			}
//
//			log.debug(NAME + "开始查看增量车辆信息定时时间是否更改。");
//			cronExpOfDB = Config.props.getProperty("vehicleCacheTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("vehicleCacheTrigger", cronExpOfDB);
//			}
//
//			log.debug(NAME + "开始查看下发命令定时时间是否更改。");
//			cronExpOfDB = Config.props.getProperty("clwsendCmdTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("sendCommandTrigger", cronExpOfDB);
//			}
//
//			log.debug(NAME + "开始查报表统计任务用到的间隔时间是否更改。");
//			cronExpOfDB = Config.props.getProperty("reportTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("reportManagerTrigger", cronExpOfDB);
//			}
//
//			log.debug(NAME + "开始查文件生成任务用到的间隔时间是否更改。");
//
//			log.debug(NAME + "开始查短信平台同步任务用到的间隔时间是否更改。");
//
//			log.debug(NAME + "开始查行程文件生成任务用到的间隔时间是否更改。");
//			cronExpOfDB = Config.props.getProperty("quartzTripTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("tripSendTrigger", cronExpOfDB);
//			}
//
//			log.debug(NAME + "开始查短信平台同步任务用到的间隔时间是否更改。");
//
//			cronExpOfDB = Config.props.getProperty("syncTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("syncManagerTrigger", cronExpOfDB);
//			}
//			log.info(NAME + "定时器调度时间更新操作执行结束！");
//
//			cronExpOfDB = Config.props.getProperty("smsOrderTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("smsOrderTrigger", cronExpOfDB);
//			}
//
//			cronExpOfDB = Config.props.getProperty("fullQuantityTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("fullQuantityTrigger", cronExpOfDB);
//			}
//
//			cronExpOfDB = Config.props.getProperty("sendxcmsmCommandTime");
//			if (!StringUtil.isEmpty(cronExpOfDB)) {
//				updateNotificationInterval("sendxcmsmCommandTrigger",
//						cronExpOfDB);
//			}
//
//			log.debug(NAME + "开始查短信平订购缓存信息间隔时间是否更改。");
//		} catch (SchedulerException e) {
//			log.error(NAME + "重新配置定时任务执行时间时发生异常,本次配置失败：" + e);
//		} catch (ParseException e) {
//			log.error(NAME + "重新配置定时任务执行时间时发生trigger的时间规则设置异常,本次配置失败：" + e);
//		} catch (Exception e) {
//			log.error(NAME + "重新配置定时任务执行时间时发生系统异常,本次配置失败：" + e);
//		}
	}

	/**
	 * 自定义定时器调度时间
	 * 
	 * @param triggerName
	 *            触发器名称
	 * @apram cronExpression 定时设置表达式
	 * @throws ParseException
	 * @throws ParseException
	 */
	private void updateNotificationInterval(String triggerName,
			String cronExpression) throws SchedulerException, ParseException {
		Scheduler scheduler = (Scheduler) SpringBootStrap.getInstance()
				.getBean("startQuartz");
		// 得到trigger
		CronTriggerBean trigger = (CronTriggerBean) scheduler.getTrigger(
				triggerName, Scheduler.DEFAULT_GROUP);
		String oldCronExp = trigger.getCronExpression();
		if (oldCronExp.equals(cronExpression)) {
			log.debug(NAME + "定时任务|" + triggerName + "|保持【" + oldCronExp
					+ "】不变，不作任务调整！");
			return;
		} else {
			log.info(NAME + "定时任务|" + triggerName + "|设置由【" + oldCronExp
					+ "】改为【" + cronExpression + "】，开始做任务重置！");
			// 设置trigger的时间规则
			trigger.setCronExpression(cronExpression);
			// 重置job
			scheduler.rescheduleJob(triggerName, Scheduler.DEFAULT_GROUP,
					trigger);
		}
	}

	public void setAppConfigCacheManager(
			AppConfigCacheManager appConfigCacheManager) {
		this.appConfigCacheManager = appConfigCacheManager;
	}

	public void setTerminalCacheManager(
			TerminalCacheManager terminalCacheManager) {
		this.terminalCacheManager = terminalCacheManager;
	}

	public void setHarmDefCacheManager(HarmDefCacheManager harmDefCacheManager) {
		this.harmDefCacheManager = harmDefCacheManager;
	}

	public ReportManager getReportManager() {
		return reportManager;
	}

	public void setReportManager(ReportManager reportManager) {
		this.reportManager = reportManager;
	}

	public TqcStatisticManager getTqcStatisticManager() {
		return tqcStatisticManager;
	}

	public void setTqcStatisticManager(TqcStatisticManager tqcStatisticManager) {
		this.tqcStatisticManager = tqcStatisticManager;
	}

	public TqcOilWearManager getTqcOilWearManager() {
		return tqcOilWearManager;
	}

	public void setTqcOilWearManager(TqcOilWearManager tqcOilWearManager) {
		this.tqcOilWearManager = tqcOilWearManager;
	}

	public RidingPlanCacheManager getRidingPlanCacheManager() {
		return ridingPlanCacheManager;
	}

	public void setRidingPlanCacheManager(
			RidingPlanCacheManager ridingPlanCacheManager) {
		this.ridingPlanCacheManager = ridingPlanCacheManager;
	}
	public VinTripCacheManager getVinTripCacheManager() {
		return vinTripCacheManager;
	}

	public void setVinTripCacheManager(VinTripCacheManager vinTripCacheManager) {
		this.vinTripCacheManager = vinTripCacheManager;
	}

}
