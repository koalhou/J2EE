package com.neusoft.clw.safemanage.humanmanage.baddriving.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.safemanage.averagefuel.ranking.domain.rankingExport;
import com.neusoft.clw.safemanage.humanmanage.baddriving.domain.HumanBadDrivDay;
import com.neusoft.clw.safemanage.humanmanage.baddriving.domain.HumanBadDriving;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */
public class HumanBadDriveAction extends PaginationAction {
	private List<HumanBadDriving> list;
	
	private List<HumanBadDrivDay> dayList;
	// 过滤标识位-对全0数据进行过滤
	private String fileterFlag;
	// add by jinp start
	private List<HumanBadDrivDay> sumList;
	// add by jinp stop
	private HumanBadDriving baddetail;
	
	private VehcileInfo vehcileInfo;
	
	private HumanBadDrivDay baddrivday;
	
	private String vehicle_vin;
	
	private String vehicle_ln;
	
	private String vehicle_code;
	
	private String start_time;
	
	private String end_time;
	
	private String time_list;
	
	private String month;
	
	private String quarter;
	
	private String year;
	
	private String week;
	
	private String code_name;
	
	private String route_name;
	
	private String alarm_type_id;
	
	List<TerminalViBean> gps_list;
	
	private String organization_id;
	// 违规驾驶ID
	private String alarm_type_id_eq;
	
	private String chooseorgid;
	
	private String selectradio;
	
	private String timeline;
	
	private String pageParam;
	
	private String rpParm;
	
	private Map map = new HashMap();
	
	private Map mapDetail = new HashMap();
	/** 显示消息 **/
	private String message = null;

	/** 车辆列表 **/
	private List<VehcileInfo> vehcList;

	/** service共通类 */
	private transient Service service;

	public String blankAction() {
		MDC.put("modulename", "[illegaldriving]");
		try {
			if (StringUtils.isEmpty(end_time)) {
				end_time= DateUtil.getCurrentDay();
		    }
			if (StringUtils.isEmpty(start_time)) {
				start_time=DateUtil.getMonthFirstDay1();//这一月的第一天   
	        }
			//start_time = DateUtil.getPreDay();
			//end_time = DateUtil.getCurrentDay();
			log.info("[start_time:" + start_time + ",end_time:" + end_time
					+ "]:进入违规驾驶页面");
		} catch (Exception e) {
			addActionError(getText(e.getMessage()));
			log.error("违规驾驶页面异常", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 违规驾驶页面浏览
	 * 
	 * @return
	 */
	public String badDrivList() {
		final String browseTitle = getText("humanbaddirve.browseCar.title");
		MDC.put("modulename", "[illegaldriving]");
		UserInfo user = getCurrentUser();
		int totalCountDay = 0;
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

		try {
			if (null == baddrivday) {
				baddrivday = new HumanBadDrivDay();
				baddrivday.setOrganization_id(user.getOrganizationID());
			} else if (baddrivday.getOrganization_id() == null || "".equals(baddrivday.getOrganization_id())) {
				baddrivday.setOrganization_id(user.getOrganizationID());
			}

			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");

			baddrivday.setSortname(sortName);
			baddrivday.setSortorder(sortOrder);

			totalCountDay = service.getCount("HumanBad.getIllegalDriveCount_day", baddrivday);
			dayList = service.getObjectsByPage("HumanBad.getIllegalDriveList_day",
							baddrivday, (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum),Integer.parseInt(rpNum));
			//sumList = service.getObjects("HumanBad.getIllegalDriveSum", baddrivday);
			this.map = getPagination(dayList, sumList, totalCountDay, pageIndex);
			if (dayList.size() == 0) {
				addActionMessage(getText("nodata.list"));
			}

			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.YTP_HUMANBADDRIVING_QUERY_ID);
			log.info(browseTitle + "结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error(browseTitle + "异常", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 违规驾驶详细列表
	 */
	public String loadEditAjaxData() {
		final String editBefTitle = getText("humanbaddetail.browse.title");
		MDC.put("modulename", "[illegaldriving]");
		int totalCountDay = 0;
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			UserInfo user = getCurrentUser();
			Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
			calendar.add(Calendar.DATE, -1); // 得到前一天
			String yestedayDate = new SimpleDateFormat("yyyy-MM-dd")
					.format(calendar.getTime());
			yestedayDate += " 23:59:59";
			if (null == baddetail) {
				baddetail = new HumanBadDriving();
				baddetail.setOrganization_id(user.getOrganizationID());
				baddetail.setTime_list("week");
			} else {
				if (organization_id == null || "".equals(organization_id)) {
					baddetail.setOrganization_id(user.getOrganizationID());
				} else {
					baddetail.setOrganization_id(organization_id);
				}
			}

			if (!"".equals(baddetail.getTime_list())&&baddetail.getTime_list()!=null) {
				/*if ("month".equals(baddetail.getTime_list())) {
					baddetail.setAlarm_start_time(DateUtil.getMonthFirstDay());

					int comNum = (int) getCompareDate(
							DateUtil.getMonthFirstDay(), yestedayDate);
					if (comNum >= 0) {
						baddetail.setAlarm_end_time(yestedayDate);
					} else {
						baddetail
								.setAlarm_end_time(DateUtil.getMonthFirstDay());
					}
				} else if ("quarter".equals(baddetail.getTime_list())) {
					int i = DateUtil.getSeason();
					baddetail
							.setAlarm_start_time(DateUtil.getSeasonFirstDay(i));
					int comNum = (int) getCompareDate(
							DateUtil.getSeasonFirstDay(i), yestedayDate);
					if (comNum >= 0) {
						baddetail.setAlarm_end_time(yestedayDate);
					} else {
						baddetail.setAlarm_end_time(DateUtil
								.getSeasonFirstDay(i));
					}
				} else if ("year".equals(baddetail.getTime_list())) {
					baddetail.setAlarm_start_time(DateUtil
							.getCurrentYearFirst());

					int comNum = (int) getCompareDate(
							DateUtil.getCurrentYearFirst(), yestedayDate);

					if (comNum >= 0) {
						baddetail.setAlarm_end_time(yestedayDate);
					} else {
						baddetail.setAlarm_end_time(DateUtil
								.getCurrentYearFirst());
					}
				} else if ("week".equals(baddetail.getTime_list())) {
					baddetail.setAlarm_start_time(DateUtil
							.getCurrentWeekFirst());
					int comNum = (int) getCompareDate(
							DateUtil.getCurrentWeekFirst(), yestedayDate);
					if (comNum >= 0) {
						baddetail.setAlarm_end_time(yestedayDate);
					} else {
						baddetail.setAlarm_end_time(DateUtil
								.getCurrentWeekFirst());
					}
				}*/
			} else {
				if (!"".equals(baddetail.getAlarm_start_time()) && !"".equals(baddetail.getAlarm_end_time())) {
					baddetail.setAlarm_start_time(baddetail.getAlarm_start_time() + " 00:00:00");
					int comNum = (int) getCompareDate(baddetail.getAlarm_end_time(), yestedayDate);
					//if (comNum >= 0) {
						baddetail.setAlarm_end_time(baddetail.getAlarm_end_time() + " 23:59:59");
					/*} else {
						baddetail.setAlarm_end_time(yestedayDate);
					}*/
				}
			}
			baddetail.setVehicle_vin(vehicle_vin);
			baddetail.setAlarm_type_id(alarm_type_id_eq);
			if (vehicle_ln != null && !"".equals(vehicle_ln)) {
				baddetail.setVehicle_ln(vehicle_ln);
			}
			log.info("[Organization_id:" + baddetail.getOrganization_id()
					+ ",Time_list:" + baddetail.getTime_list() + ",start_time:"
					+ baddetail.getAlarm_start_time() + ",end_time:"
					+ baddetail.getAlarm_end_time() + ",vehicle_vin:"
					+ vehicle_vin + "]:" + editBefTitle + "开始");

			baddetail.setRoute_name(SearchUtil.formatSpecialChar(route_name));
			//String driverid = request.getParameter("driverid");
			//baddetail.setDriver_id(driverid);
			totalCountDay = service.getCount("HumanBad.getdetailCount",
					baddetail);
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");

			baddetail.setSortname(sortName);
			baddetail.setSortorder(sortOrder);

			list = service.getObjectsByPage( "HumanBad.getdetailList", baddetail,
							(Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

			this.mapDetail = getDetailPagination(list, totalCountDay, pageIndex);

			// 设置操作描述
			this.addOperationLog(formatLog(editBefTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.YTP_HUMANBADDRIVE_INFO);
			log.info(editBefTitle + "结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error(editBefTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (list.size() == 0) {
			addActionMessage(getText("nodata.list"));
		}
		return SUCCESS;
	}

	/**
	 * 时间比较方法
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private long getCompareDate(String startDate, String endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date1 = formatter.parse(startDate);
			Date date2 = formatter.parse(endDate);
			long l = date2.getTime() - date1.getTime();
			long d = l / (24 * 60 * 60 * 1000);
			return d;
		} catch (Exception e) {
			log.error("time Compare error:", e);
			return 0;
		}
	}

	/**
	 * 格式化日志信息
	 * 
	 * @param desc
	 * @param Object
	 * @return
	 */
	protected String formatLog(String desc, HumanBadDrivDay badday) {
		StringBuffer sb = new StringBuffer();
		if (null != desc) {
			sb.append(desc);
		}
		if (null != badday) {
			if (null != badday.getBaddriving_id()) {
				OperateLogFormator.format(sb, "baddriving_id",
						badday.getBaddriving_id());
			}
		}
		return sb.toString();
	}

	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession()
				.get(Constants.USER_SESSION_KEY);
	}
	
	private static SimpleDateFormat sdfMinute = new SimpleDateFormat("HH:mm:ss");

	public Map getPagination(List dayList, List sumList, int totalCountDay,
			String pageIndex) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		int numcount = 0;
		int timecount = 0;
		for (int i = 0; i < dayList.size(); i++) {
			HumanBadDrivDay s = (HumanBadDrivDay) dayList.get(i);
			Map<String, Object> cellMap = new LinkedHashMap();
			/**
			 * 新增的列 车辆vin vehicle_vin 线路 route_name 驾驶员 driver_name
			 */
			cellMap.put("id", s.getVehicle_vin());
			numcount += Integer.valueOf(objToZero(s.getSpeeding_num()));
			if(Integer.valueOf(objToZero(s.getSpeeding_time()))>0){
				timecount +=Integer.valueOf(objToZero(s.getSpeeding_time()));
			}
			else{
				timecount +=0;
			}
			//timecount += Integer.valueOf(objToZero(s.getSpeeding_time()))>0?Integer.valueOf(objToZero(s.getSpeeding_time())):0.00;
//			if(Integer.valueOf(objToZero(s.getSpeeding_time()))>0)
//			{
//				s.setSpeeding_time(s.getSpeeding_time());
//			}
//			else
//			{
//				s.setSpeeding_time("0");
//			}
			s.setSpeeding_time(Integer.valueOf(objToZero(s.getSpeeding_time()))>0?s.getSpeeding_time():"0");
			cellMap.put(
					"cell",
					new Object[] { 
							s.getVehicle_code(),    //班车号
							s.getVehicle_ln(), 		//车牌号
							s.getVehicle_vin(),		//车辆VIN
							s.getSpeeding_num(), 	//超速次数
							s.getSpeeding_time(),	//超速时间
							s.getDriver_name(),		//驾驶员
							s.getDriver_id(),		//驾驶员
							s.getShort_allname() 	//所属企业或者组织
							});
			mapList.add(cellMap);
		}
		Map sumMap = new LinkedHashMap();
		//HumanBadDrivDay s = (HumanBadDrivDay) sumList.get(0);
		sumMap.put("id", "sumid");
		sumMap.put(
				"cell",
				new Object[] { null, null, null, 
						numcount,
						timecount,
						null,
						null });
		mapList.add(sumMap);
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}
	
	
	public Map getDetailPagination(List list, int totalCountDay,
			String pageIndex) throws Exception{
		
		SimpleDateFormat d= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < list.size(); i++) {
			HumanBadDriving s = (HumanBadDriving) list.get(i);
			Map<String, Object> cellMap = new LinkedHashMap();
			if (s.getAlarm_start_rpm() == null) {
				s.setAlarm_start_rpm("--");
			}
			if (null == s.getDriver_name() || "".equals(s.getDriver_name())) {
				s.setDriver_name("未登录");
			}
			cellMap.put("id", UUID.randomUUID().toString());
			cellMap.put(
					"cell",
					new Object[] { 
							s.getVehicle_ln(), 
							s.getVehicle_vin(),
							s.getVehicle_code(),
							s.getRoute_name(), 
							s.getAlarm_type_name(), 
							s.getEventValue(),
							s.getEventUnit(), 
							s.getAlarm_time(),
							s.getAlarm_start_speed(), 
							s.getAlarm_start_rpm(),
							s.getAlarm_start_latitude(),
							s.getAlarm_start_longitude(), 
							s.getAlarm_type_id(), 
							//s.getAlarm_start_time(),
							s.getAlarm_start_time()==null?"未上报":s.getAlarm_start_time(),
							//s.getAlarm_end_time(),
							s.getAlarm_end_time()==null?"未上报":s.getAlarm_end_time(),
							
							//s.getAlarm_time(),//告警持续时间，改为同一个表：超速告警表，如果使用驾驶明细表则使用这一行
							s.getAlarm_end_time()==null?0:String.valueOf(
							(d.parse(s.getAlarm_end_time()).getTime()-d.parse(s.getAlarm_start_time()).getTime())/1000
							),
							
							
							//((sdfMinute.parse(s.getAlarm_end_time().substring(11,16))).getTime() - (sdfMinute.parse("08:00")).getTime())/(60*1000),
							//((sdfMinute.parse(s.getAlarm_end_time().substring(11,16))).getTime() - (sdfMinute.parse("08:00")).getTime())/(60*1000),
							//((sdfMinute.parse(s.getAlarm_end_time().substring(11,19))).getTime() - (sdfMinute.parse(s.getAlarm_start_time().substring(11,19))).getTime())/(60*1000),

							/*				
							s.getAlarm_end_time()==null?0:String.valueOf(
									(
									   (
											sdfMinute.parse(s.getAlarm_end_time().substring(11,19))
										).getTime() 
										- 
										(
											sdfMinute.parse(s.getAlarm_start_time().substring(11,19))
										).getTime()
									 )/1000
									),
									*/
//							String.valueOf(
//									
//									   
//											s.getAlarm_end_time()
//										
//										- 
//										s.getAlarm_start_time()
//										
//									 
//									),
							
							s.getDriver_name()
							});
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}

	/**
	 * 按车辆导出
	 * 
	 * @return
	 */
	public String exportCar() throws UnsupportedEncodingException{
		int numcount = 0;
		int timecount = 0;
		String modelTitle = getText("humanbaddrive.export.title");
		MDC.put("modulename", "[illegaldriving]");

		if (null == baddrivday) {
			baddrivday = new HumanBadDrivDay();
		}

		/*String timeStr = baddrivday.getTime_list();
		if ("week".equals(timeStr)) {
			timeStr = "本周";
		} else if ("month".equals(timeStr)) {
			timeStr = "本月";
		} else if ("quarter".equals(timeStr)) {
			timeStr = "本季度";
		} else if ("year".equals(timeStr)) {
			timeStr = "本年";
		} else {
			timeStr = baddrivday.getStart_time() + "——"
					+ baddrivday.getEnd_time();
		}*/
		String timeStr = baddrivday.getStart_time() + "——"+ baddrivday.getEnd_time();

		UserInfo user = getCurrentUser();

		if (null == baddrivday) {
			baddrivday = new HumanBadDrivDay();
			baddrivday.setOrganization_id(user.getOrganizationID());
			//baddrivday.setTime_list("week");
		} else if (baddrivday.getOrganization_id() == null || "".equals(baddrivday.getOrganization_id())) {
			baddrivday.setOrganization_id(user.getOrganizationID());
		}
		//baddrivday.setSortname("speeding_num");    从前台获取排序规则
		//baddrivday.setSortorder("desc");
		log.info("[Organization_id:" + baddrivday.getOrganization_id() + ",Time_list:" + baddrivday.getTime_list() + "]:" + modelTitle+ "开始");

		try {
			//totalCountDay = service.getCount("HumanBad.getIllegalDriveCount_day", baddrivday);
			dayList = service.getObjects("HumanBad.getIllegalDriveList_day", baddrivday);
			/*dayList = service.getObjects("HumanBad.getIllegalDriveList", baddrivday);
			sumList = service.getObjects("HumanBad.getIllegalDriveSum", baddrivday);
			if (dayList.size() > 0) {
				for (int i = 0; i < sumList.size(); i++) {
					HumanBadDrivDay baddrivdayTemp = sumList.get(i);
					baddrivdayTemp.setVehicle_ln(getText("common.sum"));
					dayList.add(baddrivdayTemp);
				}
			}*/
		} catch (BusinessException e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		}
		List < HumanBadDrivDay > exportlist = new ArrayList < HumanBadDrivDay >();
        DecimalFormat decimalformat = new DecimalFormat("0.00");
        for (int i = 0; i < dayList.size(); i++) {
        	HumanBadDrivDay oilexport = new HumanBadDrivDay();
        	HumanBadDrivDay oilused = dayList.get(i);
        	oilexport.setVehicle_code(oilused.getVehicle_code());
            oilexport.setVehicle_ln(oilused.getVehicle_ln());
            
            oilexport.setSpeeding_num(oilused.getSpeeding_num());
            oilexport.setSpeeding_time(Integer.valueOf(objToZero(oilused.getSpeeding_time()))>0?oilused.getSpeeding_time():"0");
            
            oilexport.setDriver_name(oilused.getDriver_name());
            oilexport.setShort_allname(oilused.getShort_allname());

            exportlist.add(oilexport);
            
            numcount += Integer.valueOf(objToZero(oilused.getSpeeding_num()));
			if(Integer.valueOf(objToZero(oilused.getSpeeding_time()))>0){
				timecount +=Integer.valueOf(objToZero(oilused.getSpeeding_time()));
			}else{
				timecount +=0;
			}
        }
        
        HumanBadDrivDay oilexport1 = new HumanBadDrivDay();
        oilexport1.setVehicle_code("总计");
        oilexport1.setSpeeding_num(String.valueOf(numcount));
        oilexport1.setSpeeding_time(String.valueOf(timecount));
        exportlist.add(oilexport1);

		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "HumanBadDrive.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("超速统计(" + timeStr + ")");

			if (dayList == null || dayList.size() < 1) {
				dayList.add(new HumanBadDrivDay());
			}
			excelExporter.putAutoExtendSheets("exporthumanBadDriveCar", 0,exportlist);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} finally {
			// 关闭流
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					;
				}
			}
		}

		SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String datetimestr = pathDf.format(calendar.getTime());

		// 设置下载文件属性		
		String fileName=URLEncoder.encode("超速统计","UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-disposition", "attachment;filename="+fileName+"-" + datetimestr + ".xls");
		response.setContentType("application/msexcel; charset=\"utf-8\"");

		FileInputStream fileInputStream = null;
		OutputStream out = null;
		try {
			// 下载刚生成的文件
			fileInputStream = new FileInputStream(filePath);
			out = response.getOutputStream();
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
		} catch (FileNotFoundException e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} catch (Exception e) {
			log.error(modelTitle + "异常", e);
			return ERROR;
		} finally {
			// 关闭流
			if (null != fileInputStream) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					;
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					;
				}
			}
			// 设置操作描述
			this.addOperationLog(formatLog(modelTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.CLW_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.YTP_HUMANBADDRIVING_EXPORT_ID);
		}
		log.info(modelTitle + "结束");
		// 导出文件成功
		return null;
	}

	public String getAlarmGPSList() {
		try {
			TerminalViBean tv = new TerminalViBean();
			tv.setSTART_TIME(start_time);
			tv.setEND_TIME(end_time);
			tv.setVEHICLE_VIN(vehicle_vin);
			Map<String, Object> map = new HashMap<String, Object>(4);
			map.put("VEHICLE_VIN_V", vehicle_vin);
			map.put("START_TIME_V", start_time);
			map.put("END_TIME_V", end_time);
			map.put("out_refcur", null);
			service.getObject("HumanBad.show_map_list", map);
			if (null != map.get("out_refcur")) {
				ArrayList<TerminalViBean> res = (ArrayList<TerminalViBean>) map
						.get("out_refcur");
				gpsUtil gpsUtil = new gpsUtil();
				gps_list = gpsUtil.getpost(res);
			}
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			return ERROR;
		}
		return SUCCESS;
	}

	public String getFileterFlag() {
		return fileterFlag;
	}

	public void setFileterFlag(String fileterFlag) {
		this.fileterFlag = fileterFlag;
	}

	public List<HumanBadDrivDay> getSumList() {
		return sumList;
	}

	public void setSumList(List<HumanBadDrivDay> sumList) {
		this.sumList = sumList;
	}

	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public String getAlarm_type_id() {
		return alarm_type_id;
	}

	public void setAlarm_type_id(String alarm_type_id) {
		this.alarm_type_id = alarm_type_id;
	}

	public List<TerminalViBean> getGps_list() {
		return gps_list;
	}

	public void setGps_list(List<TerminalViBean> gps_list) {
		this.gps_list = gps_list;
	}

	public String getRoute_name() {
		return route_name;
	}

	public void setRoute_name(String route_name) {
		this.route_name = route_name;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	public String getSelectradio() {
		return selectradio;
	}

	public void setSelectradio(String selectradio) {
		this.selectradio = selectradio;
	}

	/**
	 * @return Returns the timeline.
	 */
	public String getTimeline() {
		return timeline;
	}

	/**
	 * @param timeline
	 *            The timeline to set.
	 */
	public void setTimeline(String timeline) {
		this.timeline = timeline;
	}

	public String getPageParam() {
		return pageParam;
	}

	public void setPageParam(String pageParam) {
		this.pageParam = pageParam;
	}

	public String getRpParm() {
		return rpParm;
	}

	public void setRpParm(String rpParm) {
		this.rpParm = rpParm;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map getMapDetail() {
		return mapDetail;
	}

	public void setMapDetail(Map mapDetail) {
		this.mapDetail = mapDetail;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HumanBadDriving getBaddetail() {
		return baddetail;
	}

	public void setBaddetail(HumanBadDriving baddetail) {
		this.baddetail = baddetail;
	}

	public String getChooseorgid() {
		return chooseorgid;
	}

	public void setChooseorgid(String chooseorgid) {
		this.chooseorgid = chooseorgid;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getTime_list() {
		return time_list;
	}

	public void setTime_list(String time_list) {
		this.time_list = time_list;
	}

	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}

	public List<HumanBadDrivDay> getDayList() {
		return dayList;
	}

	public void setDayList(List<HumanBadDrivDay> dayList) {
		this.dayList = dayList;
	}

	public VehcileInfo getVehcileInfo() {
		return vehcileInfo;
	}

	public void setVehcileInfo(VehcileInfo vehcileInfo) {
		this.vehcileInfo = vehcileInfo;
	}

	public HumanBadDrivDay getBaddrivday() {
		return baddrivday;
	}

	public void setBaddrivday(HumanBadDrivDay baddrivday) {
		this.baddrivday = baddrivday;
	}

	public List<VehcileInfo> getVehcList() {
		return vehcList;
	}

	public String getAlarm_type_id_eq() {
		return alarm_type_id_eq;
	}

	public void setAlarm_type_id_eq(String alarm_type_id_eq) {
		this.alarm_type_id_eq = alarm_type_id_eq;
	}

	public void setVehcList(List<VehcileInfo> vehcList) {
		this.vehcList = vehcList;
	}

	public List<HumanBadDriving> getList() {

		return list;
	}

	public void setList(List<HumanBadDriving> list) {
		this.list = list;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getCode_name() {
		return code_name;
	}

	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public static int objToZero(Object str) {        
		return str == null || str.equals("null") ? 0 : Integer.parseInt(str.toString().trim());
	}

	public String getVehicle_code() {
		return vehicle_code;
	}

	public void setVehicle_code(String vehicle_code) {
		this.vehicle_code = vehicle_code;
	}
}
