package com.yutong.clw.quartz.report;

/**
 * 
 */

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.beans.cl.HarmDefBean;
import com.yutong.clw.beans.report.DayReport;
import com.yutong.clw.beans.report.RealTimeRecord;
import com.yutong.clw.config.Config;
import com.yutong.clw.dao.ReportDAO;
import com.yutong.clw.quartz.managers.cachemanager.HarmDefCacheManager;
import com.yutong.clw.sysboot.SpringBootStrap;

/**
 * @author tmc
 */
public class AnalysisReportThread extends Thread implements Runnable {
	@SuppressWarnings("unused")
	private static final String NAME = "AnalysisAlgorithmThread";

	private Logger log = LoggerFactory.getLogger(AnalysisReportThread.class);

	private String strVin;

	private String strDate;

	private List<RealTimeRecord> realTimeRecord;

	private ReportDAO reportDAO;

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	// String sumEngine = "0"; // 发动机运行时间
	@SuppressWarnings("unused")
	private AnalysisReportThread() {
	}

	public AnalysisReportThread(String strVin, String strDate,
			List<RealTimeRecord> realTimeRecord) {
		this.strVin = strVin;
		this.strDate = strDate;
		this.realTimeRecord = realTimeRecord;
		this.reportDAO = (ReportDAO) SpringBootStrap.getInstance().getBean(
				"reportDAO");
	}

	@SuppressWarnings("static-access")
	public void run() {
		log.info("线程开始======strVin：" + strVin + "strDate:" + strDate);
		MyCountDown myCount = MyCountDown.instance();
		myCount.countAdd();
		try {
			// 获取实时数据
			this.realTimeRecord = reportDAO.getRecord(strVin, strDate);
			analyseREAl();// 分析实时数据
			reportDAO.updateCsdsDataXc(strVin, strDate, "32");
			// 超转
			reportDAO.updateCsdsDataXc(strVin, strDate, "49");
			// 空档滑行
			reportDAO.updateCsdsDataXc(strVin, strDate, "46");
			// 超长怠速
			reportDAO.updateCsdsDataXc(strVin, strDate, "50");
			// 空调怠速
			reportDAO.updateCsdsDataXc(strVin, strDate, "51");
			// 非经济区运行
			reportDAO.updateCsdsDataXc(strVin, strDate, "81");
			// 疲劳驾驶
			reportDAO.updateCsdsDataXc(strVin, strDate, "33");
			// 生成非法点火，和告警明细
			reportDAO.makczdetail(strVin, strDate);
			// 校车异常乘车统计2012-03-14
			//reportDAO.xcYcStatistics(strVin, strDate);
			// 校车上座率统计2012-03-14
			//reportDAO.xcSzlStatistics(strVin, strDate);
			// 校车非法点火
			reportDAO.updateCsdsDataXc(strVin, strDate, "54");
			// 校车超载
			reportDAO.updateCsdsDataXc(strVin, strDate, "72");
			// 更新结束时间
			reportDAO.updateEndData(strVin, strDate);

			myCount.countDown();// 线程结束
			log.info("线程结束======strVin：" + strVin + ",strDate:" + strDate);
			this.interrupted();
		} catch (Exception e) {
			myCount.countDown();
			e.printStackTrace();
			log.info("报表分析线程异常=strVin：" + strVin + ",strDate:" + strDate
					+ e.toString());
			this.interrupted();
			return;
		}
	}

	// 分析实时数据
	private void analyseREAl() throws Exception {
		try {
			// 总里程、总油耗等
			float COUNT_MILEAGE_START = 0;// 总行车里程开始
			float COUNT_MILEAGE = 0;// 总行车里程
			float MILEAGE = 0;// 行车里程(当日差值)
			float COUNT_OIL_TOTAL_START = 0; // 总燃油消耗开始
			float COUNT_OIL_TOTAL = 0; // 总燃油消耗
			float OIL = 0; // 燃油消耗(当日差值)		
			String OIL_INSTANT = "0";
			DecimalFormat def = new DecimalFormat(".#####");		
			DayReport reportResult = new DayReport();// 存当日累计结果
			List<RealTimeRecord> rows = realTimeRecord;		
			RealTimeRecord cSPerv = null, CsNonce = null, perv = null, nonce = null;
			// plPerv = null, plNonce = null,
			float diff = 0;
			//reportTimeSpace  90 流水表数据间隔，判断是否连续
			long reportTimeSpace = Long.valueOf(Config.props.getProperty("reportTimeSpace"));
			boolean continuous;
		
		
			float IDLAIR_SPD = (float) 2; // 车速阀值(km/h)-怠速空调		
			float IDLAIR_RPM = 800; // 发动机转速(r/min)-怠速空调		
			float IDLAIR_TIME = 10 * 60; // 持续时间(min)-怠速空调
			long reltime = 10;// 单条记录的持续时间
			int po = 0; // 连续的时候赋值为0，断开开的时候加1，如果大于等于2则说明前一点为为单点
			HarmDefBean hdf = HarmDefCacheManager.harmdefMap.get(strVin);
			if (hdf != null) {
				// 空调怠速
				if (hdf.getIdlair_spd() != null && hdf.getIdlair_rpm() != null
						&& hdf.getIdlair_time() != null) {
					IDLAIR_SPD = Float.valueOf(hdf.getIdlair_spd())
							.floatValue();
					IDLAIR_RPM = Float.valueOf(hdf.getIdlair_rpm())
							.floatValue();
					IDLAIR_TIME = Float.valueOf(hdf.getIdlair_time())
							.floatValue();
				}

			}
			if (null == rows || 0 == rows.size()) {
				// 如果没有实时记录将不计算
				return;
			}
			// 分析实时数据的第一条和最后一条
			// analyseBeginEnd(rows);
			// try {
			if (rows != null & rows.size() > 0) {
				// 先赋值总里程、总油耗、及瞬时油耗
				if (rows.size() == 1) {// 只有一条记录时
					if (rows.get(0).getMILEAGE() != null && !rows.get(0).getMILEAGE().equals("FFFF")) {
						COUNT_MILEAGE = Float.parseFloat(rows.get(0).getMILEAGE());
					} else {
						COUNT_MILEAGE = 0;
					}
					if (rows.get(0).getOIL_TOTAL() != null && !rows.get(0).getOIL_TOTAL().equals("FFFF")) {
						COUNT_OIL_TOTAL = Float.parseFloat(rows.get(0).getOIL_TOTAL());
					} else {
						COUNT_OIL_TOTAL = 0;
					}
					if (rows.get(0).getOIL_INSTANT().equals("FFFF")) {
						OIL_INSTANT = "0";
					} else {
						OIL_INSTANT = rows.get(0).getOIL_INSTANT();
					}

				}
				//再赋值总行车里程开始
				if (rows.get(0).getMILEAGE() != null && !rows.get(0).getMILEAGE().equals("FFFF")) {
					COUNT_MILEAGE_START = Float.parseFloat(rows.get(0).getMILEAGE());
				}
				//总燃油消耗开始
				if (rows.get(0).getOIL_TOTAL() != null && !rows.get(0).getOIL_TOTAL().equals("FFFF")) {
					COUNT_OIL_TOTAL_START = Float.parseFloat(rows.get(0).getOIL_TOTAL());
				}

				perv = rows.get(0);
                //如果是多条记录,继续计算   ---------开始
				for (int i = 1; i < rows.size(); i++) {
					// 总里程，总油耗
					if (rows.get(0).getMILEAGE() != null&& !rows.get(i).getMILEAGE().equals("FFFF")) {
						if (COUNT_MILEAGE_START == 0) {
							COUNT_MILEAGE_START = Float.parseFloat(rows.get(i).getMILEAGE());
						} else {
							COUNT_MILEAGE = Float.parseFloat(rows.get(i).getMILEAGE());
						}
					}
					if (rows.get(0).getOIL_TOTAL() != null && !rows.get(i).getOIL_TOTAL().equals("FFFF")) {
						if (COUNT_OIL_TOTAL_START == 0) {
							COUNT_OIL_TOTAL_START = Float.parseFloat(rows.get(i).getOIL_TOTAL());
						} else {
							COUNT_OIL_TOTAL = Float.parseFloat(rows.get(i).getOIL_TOTAL());
						}
					}
					if (rows.get(i).getOIL_INSTANT() != null && !rows.get(i).getOIL_INSTANT().equals("FFFF")) {
						OIL_INSTANT = rows.get(i).getOIL_INSTANT();
					}
					nonce = rows.get(i);

					diff = (df.parse(nonce.getTERMINAL_TIME()).getTime() - df
							.parse(perv.getTERMINAL_TIME()).getTime()) / 1000;
					
					if (diff >= 0 && diff < reportTimeSpace) {
						continuous = true;// 两条记录连续
					} else {
						continuous = false;// 两条记录不连续
					}
					if (diff == 0) {
						diff = reltime;// 如果为零付给时间。
					}
					// 计算各种持续时间
					if (continuous) {// 记录连续的时候累计时间
						po = 0;

					} else {// 不连续
						po = po + 1;
						if (po >= 2) {// 前一点为孤立点
						}
					}
					perv = nonce;
				}
				//---------------------  结束
			
				// 总里程，总油耗 计算及入库
				MILEAGE = COUNT_MILEAGE - COUNT_MILEAGE_START;
				OIL = COUNT_OIL_TOTAL - COUNT_OIL_TOTAL_START;
				// 增加对负数的修正2011-08-18
				if (MILEAGE < 0) {
					MILEAGE = 0;
				}
				if (OIL < 0) {
					OIL = 0;
				}

				reportDAO.updateOilMieage(def.format(COUNT_MILEAGE),
						def.format(MILEAGE), def.format(COUNT_OIL_TOTAL),
						def.format(OIL), strVin, strDate);// 更新油耗
			}
		} catch (Exception e) {
			log.info("实时数据异常" + e.toString());
			e.printStackTrace();
			throw new Exception("分析实时数据时发生异常：" + e.getMessage());
		}
	}

	/**
     *  
     */

	public static void main(String[] args) {

	}
}
