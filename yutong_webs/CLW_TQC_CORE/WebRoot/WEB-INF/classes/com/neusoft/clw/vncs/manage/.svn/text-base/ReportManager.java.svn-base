package com.neusoft.clw.vncs.manage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.RealTimeRecord;
import com.neusoft.clw.info.dao.ReportDAO;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.cachemanager.TerminalCacheManager;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.domain.TerminalBean;
import com.neusoft.clw.vncs.reportAnalysis.AnalysisReportThread;
import com.neusoft.clw.vncs.reportAnalysis.MyCountDown;
import com.neusoft.clw.vncs.reportAnalysis.ThreadPool;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.util.StringUtil;

public class ReportManager {

	private Logger log = LoggerFactory.getLogger(ReportManager.class);
	private static final String NAME = "<ReportManager>";
	private static ThreadPool pool = null;
	private static final ReportManager reportManager = new ReportManager();
	public static boolean runflag = true;
	private ReportDAO reportDAO;
	private TransactionDao transactionDao;

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

	private ReportManager() {
	}

	public static ReportManager getInstance() {
		return reportManager;
	}

	@SuppressWarnings({ "unchecked" })
	public void init() {
		// String systime = sendCommandDAO.getSysTime();
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[reportInit]");
		@SuppressWarnings("unused")
		String reportTime = null, reportServer = null, reportBuildTime = null;
		transactionDao = (TransactionDao) SpringBootStrap.getInstance()
				.getBean("transactionDao");
		boolean b = false;
		try {
			int coreActive = Integer.parseInt(Config.props.getProperty("core.active.time"));
			reportServer = Config.props.getProperty("reportServer");
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
					int rcore = transactionDao.queryLiveReportS(coreActive,
							reportServer);
					if (rcore == 0 && !b) {// 非主核心设置自己为报表服务器
						transactionDao.setReportServer(Constant.CORE_ID);
						b = true;
					}
					b = false;
				}
			}
			if (!b) {
				log.info(NAME + "本机不是报表计算服务器。");
				return;
			}
			initialThreadPool();
			log.info(NAME + " 报表统计开始！runflag:" + runflag);

			if (runflag) {
				runflag = false;// 设置独占运行模式
				MyCountDown.instance().setCount(0);// 计数器清零
				int reportBuildTimeDelay = 1, delay = 0;
				reportBuildTimeDelay = Integer.valueOf(Config.props.getProperty("reportBuildTimeDelay")).intValue();
				// 时间+1天生成计算时间
				reportBuildTime = reportDAO.getReportDate();// 获取报表统计时间
				delay = reportDAO.getDelay();// 实际延迟天数

				while (delay >= reportBuildTimeDelay) {
					log.info(NAME + "统计报表：日期：" + reportBuildTime + "！");
					List<TerminalBean> TList = new ArrayList();
					if (Constant.terminalMap != null
							&& Constant.terminalMap.size() > 0) {
						Collection<TerminalBean> col = TerminalCacheManager
								.getInstance().getValues();
						if (col != null && col.size() > 0) {
							Iterator it = col.iterator();
							while (it.hasNext()) {
								TList.add((TerminalBean) it.next());
							}
						}
					}
					if (TList == null || TList.size() == 0) {
						log.info(NAME + "待分析list为空  停止分析！");
						runflag = true;//
						return;
					}

					// 计算昨天的数据
					if (delay == reportBuildTimeDelay) {
						GregorianCalendar gc = new GregorianCalendar();
						SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
						// 当前时间减一天
						gc.add(5, -1);
						String dd = sf.format(gc.getTime());
						log.info(NAME +" 开始补算 "+dd+" 之前的数据!");
						//开始补算当天之前的数据
						RunSum(dd, TList);						
					}

					// 补算未计算完的,暂时是四天,  东软代码暂时不执行
					// RepairSum(reportBuildTime);
					
					List<RealTimeRecord> list = null;
					TerminalBean tb;
					// 启动多线程进行分析。//
					for (int i = 0; i < TList.size(); i++) {
						try {
							tb = TList.get(i);
							// 初始化记录日报记录 CLW_YW_BADDRIVING_T
							reportDAO.deletevin(tb.getVehicle_vin(),reportBuildTime);
							//创建CLW_YW_BADDRIVING_T
							reportDAO.insertvin(tb.getVehicle_vin(),reportBuildTime);
							// 初始化详细信息表
							reportDAO.deletedetail(tb.getVehicle_vin(),reportBuildTime);
							list = null;
							AnalysisReportThread newAnalysis = new AnalysisReportThread(
									tb.getVehicle_vin(), reportBuildTime, list);
							// log.info(NAME+" 运行线程数"+MyCountDown.instance().getCount())+"总数："+Config.props.getProperty("maxThreadReport"));
							while (MyCountDown.instance().getCount() >= Integer
									.parseInt(Config.props
											.getProperty("maxThreadReport"))) {
								try {
									Thread.sleep(3000);
									log.info(NAME + "报表统计线程已满，等待中。运行线程"
											+ MyCountDown.instance().getCount());
								} catch (Exception e) {
									log.info(NAME + "报表统计异常：");
									e.printStackTrace();
								}
							}
							newAnalysis.start();

						} catch (Exception e) {
							log.error(NAME + "ReportManager 循环遍历计算错误："
									+ e.toString());
							continue;
						}
					}

					int i = 0;
					while (MyCountDown.instance().getCount() != 0) {// 所有线程结束
						i++;
						if (i > 200) {
							MyCountDown.instance().setCount(0);
						}
						Thread.sleep(3000);
						log.info(NAME + "等待，线程数为:"
								+ MyCountDown.instance().getCount());
						// log.info(NAME+"等待，线程数为:"+MyCountDown.instance().getCount()+"运行线程数为:"+pool.getRunNum(ThreadPool.NORMAL_PRIORITY)));
					}
					reportDAO.setReportDate();// 更新报表时间
					log.info(NAME + "！！！！报表统计结束runflag:" + runflag);
					delay = reportDAO.getDelay();// 实际延迟天数
					reportBuildTime = reportDAO.getReportDate();// 获取报表统计时间
				}// while 结束
				runflag = true;
			} else {
				log.info("！！！！！！！报表统计运行中！！！！！！！！！");
				// log.info("线程数为:" + MyCountDown.instance().getCount());
				return;
			}
		} catch (Exception e) {
			MyCountDown.instance().setCount(0);
			runflag = true;
			log.error(NAME + "ReportManager错误：" + e.toString());
		}
		log.info("ReportManager 本次报表统计结束！ runflag:" + runflag);
	}

	public void setReportDAO(ReportDAO reportDAO) {
		this.reportDAO = reportDAO;
	}

	public ReportDAO getReportDAO() {
		return reportDAO;
	}
	
	/**
	 * 功能：根据传入日期，计算当天日期  之前几天的数据
	 * 
	 * @param inDate 传入日期,TList 终端列表
	 * @return  
	 */	
	public void RunSum(String inDate, List<TerminalBean> TList) {
		@SuppressWarnings("unused")
	    GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		String newDate;
		try {
			for (int m = 1; m <= 5; m++) {				
				d = sf.parse(inDate);
				gc.setTime(d);
				gc.add(5, -m);
				newDate = sf.format(gc.getTime());
				
				List<RealTimeRecord> list = null;
				TerminalBean tb;
				// 启动多线程进行分析。
				for (int i = 0; i < TList.size(); i++) {
					tb = TList.get(i);
					log.info(NAME + " vinCode="+tb.getVehicle_vin()+", newDate="+newDate);
					// 初始化记录日报记录
					reportDAO.deletevin(tb.getVehicle_vin(), newDate);
					reportDAO.insertvin(tb.getVehicle_vin(), newDate);
					// 初始化详细信息表
					reportDAO.deletedetail(tb.getVehicle_vin(), newDate);
					list = null;
					AnalysisReportThread newAnalysis = new AnalysisReportThread(tb.getVehicle_vin(), newDate, list);
					// log.info(NAME+" 运行线程数"+MyCountDown.instance().getCount())+"总数："+Config.props.getProperty("maxThreadReport"));
					while (MyCountDown.instance().getCount() >= Integer
							.parseInt(Config.props.getProperty("maxThreadReport"))) {
						try {
							Thread.sleep(3000);
							log.info(NAME + "报表统计线程已满，等待中。运行线程"
									+ MyCountDown.instance().getCount());
						} catch (Exception e) {
							log.info(NAME + "报表统计异常：" + e.toString());
						}
					}
					newAnalysis.start();
				}
			}
			// MyCountDown.instance().setCount(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*//补算报表统计方法
	public void RepairSum(String sdate) {
		String ndate;
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		List<BaddrivingDate> list = null;
		try {
			for (int i = 1; i < 4; i++) {
				d = sf.parse(sdate);
				gc.setTime(d);
				gc.add(5, -i);
				ndate = sf.format(gc.getTime());
				list = reportDAO.getBaddrivingDate(ndate);
				if (null == list || 0 == list.size()) {
					// 如果没有实时记录将不计算
					continue;
				} else {
					for (int j = 0; j < list.size(); j++) {

						AnalysisReportThread newAnalysis = new AnalysisReportThread(
								list.get(j).getVEHICLE_VIN(), list.get(j)
										.getALARM_DAY(), null);
						// log.info(NAME+" 运行线程数"+MyCountDown.instance().getCount())+"总数："+Config.props.getProperty("maxThreadReport"));
						while (MyCountDown.instance().getCount() >= Integer
								.parseInt(Config.props
										.getProperty("maxThreadReport"))) {
							try {
								Thread.sleep(3000);
								log.info(NAME + "补算报表统计线程已满，等待中。运行线程"
										+ MyCountDown.instance().getCount());
							} catch (Exception e) {
								log.info(NAME + "补算报表统计异常：" + e.toString());
							}
						}
						log.info(NAME + "补算报表统计线程，vin"
								+ list.get(j).getVEHICLE_VIN() + ";日期："
								+ list.get(j).getALARM_DAY());
						newAnalysis.start();
					}
				}

			}
			// MyCountDown.instance().setCount(0);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}*/

	public static void main(String[] args) throws Exception {
		String ndate;
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		for (int i = 0; i <= 5; i++) {
			d = sf.parse("2013-12-15");
			gc.setTime(d);
			gc.add(5, -i);
			ndate = sf.format(gc.getTime());			
			System.out.println("------->:"+ndate);
		}
	
	}
}
