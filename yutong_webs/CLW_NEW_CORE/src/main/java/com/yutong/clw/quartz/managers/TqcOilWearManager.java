package com.yutong.clw.quartz.managers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.yutong.clw.config.Config;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.impl.TransactionDao;
import com.yutong.clw.dao.oil.TqcOilWearDao;
import com.yutong.clw.service.ActiveCoreService;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.CDate;
import com.yutong.clw.utils.StringUtil;

public class TqcOilWearManager {
	private   Logger log = LoggerFactory.getLogger(TqcOilWearManager.class); 
	private static final String NAME = "<TqcOilWearManager>";
	public static boolean runflag = true;
	private TransactionDao transactionDao;

	public void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[quartzInit]");
		String reportServer = null;
		transactionDao = (TransactionDao) SpringBootStrap.getInstance().getBean("transactionDao");
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
			
			log.info(NAME+"判断是本服务器是否为报表计算服务器"+b);
			if (!b) {
				log.info(NAME+"本机不是报表生成服务器。");
				return;
			}
			if (runflag) {
				runflag = false;				
				//计算上一天的数据
			    String dateCurrent=CDate.getNextDate(-1);
			    GregorianCalendar gc = new GregorianCalendar();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				Date d = null;
				String dateSplit="",dateNoSplit=""; 
			    for (int m = 0; m <= 5; m++) {
			    	d = sf.parse(dateCurrent);
					gc.setTime(d);
					gc.add(5, -m);
					dateSplit = sf.format(gc.getTime());					
		    	    dateNoSplit=CDate.getNextDateNoSplitByDate(dateSplit);	
				    log.info(NAME+"--------------【通勤车计算 dateSplit= "+dateSplit+" 日】油耗统计执行开始！dateNoSplit= "+dateNoSplit+" runflag:" + runflag);				
				    //String dateSplit="2013-07-03";
				    //String dateNoSplit="20130703";
				    // 设置独占运行模式	
				    TqcOilWearDao tqcOilWearDao =(TqcOilWearDao)SpringBootStrap.getInstance().getBean("tqcOilWearDao");				
				    tqcOilWearDao.selectAllVehicleOilWear(dateSplit,dateNoSplit);	
				    log.info(NAME+"--------------【通勤车计算 dateSplit= "+dateSplit+" 日】油耗统计执行结束！dateNoSplit= "+dateNoSplit+" runflag:" + runflag);
			    }
			    runflag = true;			   
			}
		}catch(Exception e) {		
			runflag = true;
			log.error(NAME+"TqcOilWearManager错误：" + e.toString());
			e.printStackTrace();
		}
		    
	}
}
