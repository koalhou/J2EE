package com.neusoft.privateuse.manager;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.buffer.DBBuffer;
import com.neusoft.clw.vncs.dao.impl.TransactionDao;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.privateuse.bean.LawfulRunTimeInfo;
import com.neusoft.privateuse.bean.RunRecord;
import com.neusoft.privateuse.bean.VehicleInfo;
import com.neusoft.privateuse.dao.IPrivateUseDAO;
import com.neusoft.privateuse.service.PrivateUseBuildSQL;

public class PrivateUseManager {

	private Logger log = LoggerFactory.getLogger(PrivateUseManager.class);

	private static final String NAME = "<PrivateUseManager>";

	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static DecimalFormat def = new DecimalFormat("###.###");

	private TransactionDao transactionDao;
	private IPrivateUseDAO privateUseDAO;

	public static boolean runflag = true;

	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	// 定时任务，半个小时执行一次，判断是否公车私用
	public void init() {
		String reportServer = null;
		transactionDao = (TransactionDao) SpringBootStrap.getInstance()
				.getBean("transactionDao");
		boolean b1 = false;
		try {
			int coreActive = Integer.parseInt(Config.props
					.getProperty("core.active.time"));
			reportServer = transactionDao.getReportServer();
			if (reportServer.equals(Constant.CORE_ID)) {// 是指定的报表计算服务器
				b1 = true;
			} else {
				// 是否为主核心，为了保证主核心业务的正常，核心数量大于1时主核心不参与报表计算。
				ActiveCoreService acs = ActiveCoreService.getInstance();
				b1 = acs.getMainCore(Constant.CORE_ID, coreActive);
				// 获取活跃核心数量
				int corenum = transactionDao.queryReportServer(coreActive);
				if (corenum == 1) {
					b1 = true;
				} else {
					int rcore = transactionDao.queryLiveReportS(coreActive,
							reportServer);
					if (rcore == 0 && !b1) {// 非主核心设置自己为报表服务器
						transactionDao.setReportServer(Constant.CORE_ID);
						b1 = true;
					}
					b1 = false;
				}
			}

			log.info(NAME + "判断是本服务器是否为【员工版】消息推送服务器" + b1);
			if (!b1) {
				log.info(NAME + "本机不是【员工版】消息推送服务器。");
				return;
			}

			log.info(NAME + "本次公车私用判断开始！runflag:" + runflag);
			if (runflag) {
				runflag = false;

				Date now = new Date();
				int weekDay = getDay(now) - 1;// 获取当天是星期几，星期一是第一天
				if (weekDay < 1)
					weekDay = 7;
				// 1、先获取车辆 所有的车辆
				List<VehicleInfo> vh_list = privateUseDAO
						.getVehicleInfo(weekDay);

				for (VehicleInfo vh : vh_list) {
					// 2、先从CLW_XC_RUN_RECORD_T表中查出车辆的行车记录
					List<RunRecord> list = privateUseDAO.getRunRecordInfo(vh
							.getVehicle_vin());
					for (RunRecord re : list) {
						// 3、从TQC_LAWFUL_RUN_TIME_T表中查询出该车辆公出的时间信息
						// 有序的 时间小的在前面
						if (vh.getOrganization_id() != null) {
							List<LawfulRunTimeInfo> timeList = new LinkedList<LawfulRunTimeInfo>();
							// List<LawfulRunTimeInfo> timeLines =
							// privateUseDAO.getVehicleInfoByOrganization_id(vh.getOrganization_id());

							String[] a = vh.getStart_time().split("/");
							String[] b = vh.getEnd_time().split("/");

							String timeConcat = "";

							for (int i = 0; i < a.length; i++) {
								LawfulRunTimeInfo minner = new LawfulRunTimeInfo();
								minner.setStart_time(a[i]);
								minner.setEnd_time(b[i]);

								if ("23:59".equals(a[i])) {
									timeConcat = "00:00-00:00";
								} else if (i == a.length - 1) {
									timeConcat += a[i] + "-" + b[i];
								} else {
									timeConcat += a[i] + "-" + b[i] + "/";
								}

								// minner.setEffect_time(timeConcat);

								timeList.add(minner);

							}
							vh.setEffect_time(timeConcat);
							VehicleInfo vm = new VehicleInfo();
							BeanUtils.copyProperties(vh, vm);

							if (timeList != null && timeList.size() > 0) { // 设置了公车时间的才进行判断，否则全部认为合法
																			// ,已经和乐乐确认
								bbb(timeList, re, vm);
							}
						}

					}

				}
				// 将所有的点火关火记录设置成已经巡检标识
				int l = privateUseDAO.updatePrivateUseFlag();

				// 4、两者时间比较
				// 代码暂时没有写

				// 5、根据时间和车辆VIN从表clw_yw_terminal_record_t中，查询出相关的mileage 和oil
				// 6、插入告警表中

				// 更新告警表 主要包括油耗和里程
				List<VehicleInfo> alarm_list = privateUseDAO
						.getVehiclePrivateUseAlarm();
				for (VehicleInfo vi : alarm_list) {
					double mileage_title = 0.0;
					double a = 0.0;
					double b = 0.0;
					List<VehicleInfo> list2 = privateUseDAO
							.getVehicleInfoByVin(vi.getVehicle_vin(),
									vi.getAlarm_time(), vi.getAlarm_end_time());
					if (list2 != null && list2.size() > 0) {
						for (VehicleInfo in : list2) {
							if (in.getMileage() != null) {
								a = Double.parseDouble(in.getMileage());
								if (b <= 0.000001) {
									b = Double.parseDouble(in.getMileage());
								}
								mileage_title = mileage_title + b - a;
								b = a;
							}
						}

						System.out.println(vi.getVehicle_vin()
								+ "-----------mileage_title:"
								+ def.format(mileage_title) + "--------------"
								+ list2.size());

					}

					// double oil_title = 0.0;
					// double x= 0.0;
					// double y= 0.0;
					//
					// List<VehicleInfo> list_oil =
					// privateUseDAO.getVehicleOilByVin(vi.getVehicle_vin(),vi.getAlarm_time(),vi.getAlarm_end_time());
					// if(list_oil != null && list_oil.size()>0){
					// for(VehicleInfo vo:list_oil){
					// x = Double.parseDouble(vo.getOil());
					// if(y<=0.000001){
					// y = Double.parseDouble(vo.getOil());
					// }
					// if(y>x){
					// oil_title = oil_title+y-x;
					// y = x;
					// }
					//
					// }
					//
					// System.out.println(vi.getVehicle_vin()+"-----------oil_title:"
					// + def.format(oil_title) + "--------------" +
					// list_oil.size() );
					// }
					//
					vi.setMileage(def.format(mileage_title));
					// vi.setOil(def.format(oil_title));
					vi.setOil(def.format(0.0));

					int i = privateUseDAO.updatePrivateUseAlarm(vi);

				}
				runflag = true;
			}
			log.info(NAME + "本次本次公车私用判断结束！ runflag:" + runflag);
		} catch (Exception e) {
			runflag = true;
			log.error(NAME + "公车私用判断异常：", e);
		}
	}

	private void bbb(List<LawfulRunTimeInfo> timeList, RunRecord re,
			VehicleInfo vh) {
		for (LawfulRunTimeInfo time : timeList) {
			vh.setStart_time(time.getStart_time());
			vh.setEnd_time(time.getEnd_time());
			Date start = putTime(vh.getStart_time());
			Date end = putTime(vh.getEnd_time());
			// 对re进行逻辑处理
			// -----------
			// ------
			if (re.getOn_date() != null && re.getOn_date().before(start)
					&& re.getOff_date() != null
					&& re.getOff_date().before(start)) {
				// 公车私用，插入数据库 开始时间：re.getOn_date() 结束时间：re.getOff_date()
				DBBuffer.getInstance().add(
						PrivateUseBuildSQL.getInstance()
								.buildInsertSiteNoteLog(re.getOn_date(),
										re.getOff_date(), vh));
//				System.out.println("公车私用告警------------开始时间："
//						+ sdf.format(re.getOn_date()) + "------------结束时间："
//						+ sdf.format(re.getOff_date()));
				return;
			}
			// -----------
			// --------------
			else if (re.getOn_date() != null && re.getOn_date().before(start)
					&& re.getOff_date() != null
					&& re.getOff_date().after(start)
					&& re.getOff_date().before(end)) {
				// 公车私用，插入数据库 开始时间：re.getOn_date() 结束时间：start
				DBBuffer.getInstance().add(
						PrivateUseBuildSQL.getInstance()
								.buildInsertSiteNoteLog(re.getOn_date(), start,
										vh));
//				System.out.println("公车私用告警------------开始时间："
//						+ sdf.format(re.getOn_date()) + "------------结束时间："
//						+ sdf.format(start));
				return;
			}
			// -----------
			// ---------------------------------
			else if (re.getOn_date() != null && re.getOn_date().before(start)
					&& re.getOff_date() != null && re.getOff_date().after(end)) {
				// 公车私用，插入数据库 开始时间：re.getOn_date() 结束时间：start
				DBBuffer.getInstance().add(
						PrivateUseBuildSQL.getInstance()
								.buildInsertSiteNoteLog(re.getOn_date(), start,
										vh));
//				System.out.println("公车私用告警------------开始时间："
//						+ sdf.format(re.getOn_date()) + "------------结束时间："
//						+ sdf.format(start));
				re.setOn_date(end);
				bbb(timeList, re, vh);
				return;

			}
			// ------------
			// -----
			else if (re.getOn_date() != null && re.getOn_date().after(start)
					&& re.getOff_date() != null && re.getOff_date().before(end)) {
				// 正常使用
				return;
			}
			// -------------
			// -----------------
			else if (re.getOn_date() != null && re.getOn_date().after(start)
					&& re.getOn_date().before(end) && re.getOff_date() != null
					&& re.getOff_date().after(end)) {
				// 公车私用，插入数据库 开始时间：end 结束时间：re.getOff_date()
				// 这个地方不能放开
//				System.out.println("公车私用告警------------开始时间：" + sdf.format(end)
//						+ "------------结束时间：" + sdf.format(re.getOff_date()));

				re.setOn_date(end);
				bbb(timeList, re, vh);
				return;

			}
			// --------------
			// ---------
			else if (re.getOn_date() != null && re.getOn_date().after(end)
					&& re.getOff_date() != null && re.getOff_date().after(end)) {
				// 不做处理，让循环进行完即可
			}
		}

		DBBuffer.getInstance().add(
				PrivateUseBuildSQL.getInstance().buildInsertSiteNoteLog(
						re.getOn_date(), re.getOff_date(), vh));
//		System.out.println("公车私用告警------------开始时间："
//				+ sdf.format(re.getOn_date()) + "------------结束时间："
//				+ sdf.format(re.getOff_date()));

	}

	/**
	 * 把字符串转换成今天的时间 比如今天是2014-05-09 把6:30 变成2014-05-09 06:30:00
	 * 
	 * @param time
	 */
	private Date putTime(String time) {
		int y, m, d, h, mi, s;
		Calendar cal = Calendar.getInstance();
		y = cal.get(Calendar.YEAR);
		m = cal.get(Calendar.MONTH) + 1;
		d = cal.get(Calendar.DATE);
		h = cal.get(Calendar.HOUR_OF_DAY);
		mi = cal.get(Calendar.MINUTE);
		s = cal.get(Calendar.SECOND);
		time = y + "-" + m + "-" + d + " " + time + ":00";
		System.out.println(time);
		// SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		// private static SimpleDateFormat sdf = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public static void main(String[] abc) {
		PrivateUseManager a = new PrivateUseManager();
		Date date = a.putTime("6:00:00");

	}

	public void setPrivateUseDAO(IPrivateUseDAO privateUseDAO) {
		this.privateUseDAO = privateUseDAO;
	}

}
