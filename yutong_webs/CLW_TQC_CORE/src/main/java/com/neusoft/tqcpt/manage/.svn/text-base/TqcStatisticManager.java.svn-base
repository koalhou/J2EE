package com.neusoft.tqcpt.manage;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.tqcpt.dao.TqcStatisticDao;

public class TqcStatisticManager {
	private   Logger log = LoggerFactory.getLogger(TqcStatisticManager.class);        
	private static final String NAME = "<TqcStatisticManager>";
	public static boolean runflag = true;
	private TransactionDao transactionDao;
	
	public void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[quartzInit]");
		
		String reportTime = null, reportServer = null, reportBuildTime = null;
		transactionDao = (TransactionDao) SpringBootStrap.getInstance()
				.getBean("transactionDao");
		boolean b = false;
		try {		
			int coreActive = Integer.parseInt(Config.props.getProperty("core.active.time"));
			//b = ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID, coreActive);
			reportServer = transactionDao.getReportServer();
			if (reportServer.equals(Constant.CORE_ID)) {// 是指定的报表计算服务器
				b = true;
			} else {
				// 是否为主核心，为了保证主核心业务的正常，核心数量大于1时主核心不参与报表计算。
				ActiveCoreService acs = ActiveCoreService.getInstance();
				b = acs.getMainCore(Constant.CORE_ID, coreActive);
				// 获取活跃核心数量
				int corenum = transactionDao.queryReportServer(coreActive);
				if (corenum == 1) {
					b = true;
				} else {
					int rcore = transactionDao.queryLiveReportS(coreActive,reportServer);
					if (rcore == 0 && !b) {// 非主核心设置自己为报表服务器
						transactionDao.setReportServer(Constant.CORE_ID);
						b = true;
					}
					b = false;
				}
			}
			
			if (!b) {
				log.info(NAME+"本机不是报表生成服务器。");
				return;
			}			
			log.info(NAME+"--------------发车与客流统计报表执行开始！runflag:" + runflag);	
			
			if (runflag) {
				runflag = false;// 设置独占运行模式			
				TqcStatisticDao tqcStatisticDao =(TqcStatisticDao)SpringBootStrap.getInstance().getBean("tqcStatisticDao");
				//执行发车与客流统计报表统计
				tqcStatisticDao.selectAllVehicleStatistic();
				runflag = true;
			}
		}catch(Exception e) {		
			runflag = true;
			log.error(NAME+"TqcStatisticManager错误：" + e.toString());
			e.printStackTrace();
		}
		log.info(NAME+"--------------发车与客流统计报表执行结束！ runflag:" + runflag);
	}
}
