package com.neusoft.clw.safemanage.humanmanage.alarmmanage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.alarmmanageservice.AlarmmanageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.safemanage.averagefuel.ranking.domain.rankingExport;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.action.gpsUtil.SearchGisAreaByCode;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.PhotoRoute;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.StuAlarm;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.TqcAlarm;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.clw.yw.ftly.ds.ZsptFtlyInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */
public class AlarmManageAction extends PaginationAction {

	private List<AlarmManage> dayList;

	private List<AlarmManage> messageList;

	private SendCommandClient sendCommandClient;

	private Map<String, String> alarmtypemap = new HashMap<String, String>();

	private Map<String, String> stualarmtypemap = new HashMap<String, String>();

	private Map<String, String> dealflagmap = new HashMap<String, String>();

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static SimpleDateFormat sdfMinute = new SimpleDateFormat("HH:mm");
	
	
	public Map<String, String> getDealflagmap() {
		return dealflagmap;
	}

	public void setDealflagmap(Map<String, String> dealflagmap) {
		this.dealflagmap = dealflagmap;
	}

	private AlarmManage alarmmanage;

	private String vehicle_ln;

	// private String s_vehicle_ln;

	private String time_list;

	private String chooseorgid;

	private String alarm_type_id;

	private String stu_alarm_type_id;

	private String deal_flag;

	private String daohangid;

	private String sourceid;

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getDaohangid() {
		return daohangid;
	}

	public void setDaohangid(String daohangid) {
		this.daohangid = daohangid;
	}

	public String getDeal_flag() {
		return deal_flag;
	}

	public void setDeal_flag(String deal_flag) {
		this.deal_flag = deal_flag;
	}

	// 监听处位置
	private String lon;

	private String lat;

	// 消息处使用
	private TerminalViBean terminalViBean;

	private String start_time;

	private String end_time;

	// 链接用VIN
	private String vin;

	// TAB也用的MAP
	private Map map = new HashMap();

	private String message;

	/** service共通类 */
	private transient Service service;

	private AlarmmanageService alarmservice;

	// 批量处理用
	private String alarmids;

	private String vins;

	private String alarmtypes;

	private String dealflags;
	
	private String alramtimes;
	
	private String tertimes;

	public String getTertimes() {
		return tertimes;
	}

	public void setTertimes(String tertimes) {
		this.tertimes = tertimes;
	}

	public String getAlramtimes() {
		return alramtimes;
	}

	public void setAlramtimes(String alramtimes) {
		this.alramtimes = alramtimes;
	}

	// TAB页用组织ID
	private String orgid;

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public String getDealflags() {
		return dealflags;
	}

	public void setDealflags(String dealflags) {
		this.dealflags = dealflags;
	}

	public String getAlarmids() {
		return alarmids;
	}

	public void setAlarmids(String alarmids) {
		this.alarmids = alarmids;
	}

	public String getVins() {
		return vins;
	}

	public void setVins(String vins) {
		this.vins = vins;
	}

	public String getAlarmtypes() {
		return alarmtypes;
	}

	public void setAlarmtypes(String alarmtypes) {
		this.alarmtypes = alarmtypes;
	}

	public SendCommandClient getSendCommandClient() {
		return sendCommandClient;
	}

	public void setSendCommandClient(SendCommandClient sendCommandClient) {
		this.sendCommandClient = sendCommandClient;
	}

	public Map<String, String> getStualarmtypemap() {
		return stualarmtypemap;
	}

	public void setStualarmtypemap(Map<String, String> stualarmtypemap) {
		this.stualarmtypemap = stualarmtypemap;
	}

	public Map<String, String> getAlarmtypemap() {
		return alarmtypemap;
	}

	public void setAlarmtypemap(Map<String, String> alarmtypemap) {
		this.alarmtypemap = alarmtypemap;
	}

	public TerminalViBean getTerminalViBean() {
		return terminalViBean;
	}

	public void setTerminalViBean(TerminalViBean terminalViBean) {
		this.terminalViBean = terminalViBean;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getStu_alarm_type_id() {
		return stu_alarm_type_id;
	}

	public void setStu_alarm_type_id(String stu_alarm_type_id) {
		this.stu_alarm_type_id = stu_alarm_type_id;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public AlarmmanageService getAlarmservice() {
		return alarmservice;
	}

	public void setAlarmservice(AlarmmanageService alarmservice) {
		this.alarmservice = alarmservice;
	}

	private StuAlarm stualarm;

	private TqcAlarm tqcalarm;

	private List<StuAlarm> stuAlarmList;
	private List<TqcAlarm> tqcAlarmList;

	private List<StuAlarm> allStuAlarmList;

	private List<TqcAlarm> allTqcAlarmList;

	public List<StuAlarm> getAllStuAlarmList() {
		return allStuAlarmList;
	}

	public void setAllStuAlarmList(List<StuAlarm> allStuAlarmList) {
		this.allStuAlarmList = allStuAlarmList;
	}

	public List<TqcAlarm> getAllTqcAlarmList() {
		return allTqcAlarmList;
	}

	public void setAllTqcAlarmList(List<TqcAlarm> allTqcAlarmList) {
		this.allTqcAlarmList = allTqcAlarmList;
	}

	public StuAlarm getStualarm() {
		return stualarm;
	}

	public void setStualarm(StuAlarm stualarm) {
		this.stualarm = stualarm;
	}

	public TqcAlarm getTqcalarm() {
		return tqcalarm;
	}

	public void setTqcalarm(TqcAlarm tqcalarm) {
		this.tqcalarm = tqcalarm;
	}

	public List<StuAlarm> getStuAlarmList() {
		return stuAlarmList;
	}

	public void setStuAlarmList(List<StuAlarm> stuAlarmList) {
		this.stuAlarmList = stuAlarmList;
	}

	public List<TqcAlarm> getTqcAlarmList() {
		return tqcAlarmList;
	}

	public void setTqcAlarmList(List<TqcAlarm> tqcAlarmList) {
		this.tqcAlarmList = tqcAlarmList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getChooseorgid() {
		return chooseorgid;
	}

	public void setChooseorgid(String chooseorgid) {
		this.chooseorgid = chooseorgid;
	}

	public List<AlarmManage> getDayList() {
		return dayList;
	}

	public void setDayList(List<AlarmManage> dayList) {
		this.dayList = dayList;
	}

	public AlarmManage getAlarmmanage() {
		return alarmmanage;
	}

	public void setAlarmmanage(AlarmManage alarmmanage) {
		this.alarmmanage = alarmmanage;
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

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<AlarmManage> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<AlarmManage> messageList) {
		this.messageList = messageList;
	}

	private List<PhotoRoute> photorouteList = new ArrayList();

	public List<PhotoRoute> getPhotorouteList() {
		return photorouteList;
	}

	public void setPhotorouteList(List<PhotoRoute> photorouteList) {
		this.photorouteList = photorouteList;
	}

	/**
	 * 获取SOS页TAB数据
	 * 
	 * @return
	 */
	public String getTabSosAlarms() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String sortName = request.getParameter("sortname"); // 排序字段名
		String sortOrder = request.getParameter("sortorder"); // 升序OR降序
		String alarmtypeid = request.getParameter("alarmtypeid");
		try {
			log.info("获取车辆SOSTAB数据开始:");
			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			alarmmanage.setOrganization_id(orgid);
			alarmmanage.setSortname(sortName); // domain类中设置排序字段名
			alarmmanage.setSortorder(sortOrder); // domain类中设置升序OR降序
			alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
			alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			if (null == alarmtypeid || "".equals(alarmtypeid)) {
				alarmtypeid = "'40','72'";
			}
			alarmmanage.setAlarm_type_id(alarmtypeid);

			messageList = service.getObjectsByPage(
					"AlarmManage.getTAbSosAlarmInfos", alarmmanage, 0, 3);
			map = getTabSosPagination(messageList);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			log.error("获取SOSTAB数据异常:", e);
			messageList = null;
		} catch (Exception e) {
			log.error("获取SOSTAB数据异常:", e);
			return ERROR;
		}

		// log.info("info: " + map.get("rows"));
		log.info("获取SOSTAB数据结束");
		return SUCCESS;
	}

	/**
	 * 获取SOSTAB页告警的MAP
	 * 
	 * @param dayList
	 * @return
	 */
	public Map getTabSosPagination(List dayList) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		//gpsUtil gsputil = new gpsUtil();
		//dayList = gsputil.getAlarmPosition(dayList);
		//dayList = gsputil.getAlarmAddress(dayList);
		for (int i = 0; i < dayList.size(); i++) {
			AlarmManage s = (AlarmManage) dayList.get(i);
			if (null == s.getDriver_name() || "".equals(s.getDriver_name())) {
				s.setDriver_name("未登录");
			}
			if (null == s.getSichen_name() || "".equals(s.getSichen_name())) {
				s.setSichen_name("未登录");
			}
			if (null == s.getStu_num() || "".equals(s.getStu_num())) {
				s.setStu_num("0");
			}

			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] { s.getLongitude(),
					s.getLatitude(), s.getVehicle_vin(), s.getDeal_flag(),
					s.getAlarm_type_id(), s.getDirection(), "",
					s.getVehicle_code(),
					s.getVehicle_ln(),
					s.getAlarm_type_name(),
					s.getAlarm_time(),
					s.getDriver_name(), 
					s.getSichen_name(),
					s.getStu_num(),
					"", 
					(s.getZonename() == null || "".equals(s.getZonename()) )? "定位无效":s.getZonename()});
			mapList.add(cellMap);
		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", 5);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}

	/**
	 * 获取TAB页表格下告警数据
	 * 
	 * @return
	 */
	public String getTabVehAlarms() {
		//MDC.put("modulename", "[tabvehalarm]");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String sortName = request.getParameter("sortname"); // 排序字段名
		String sortOrder = request.getParameter("sortorder"); // 升序OR降序
		String alarmtypeid = request.getParameter("alarmtypeid");
		try {
			log.info("获取车辆TAB数据开始:" + alarmtypeid);
			//UserInfo user = getCurrentUser();
			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			// alarm.setEnterprise_id(user.getEntiID());
			alarmmanage.setOrganization_id(orgid);
			alarmmanage.setSortname(sortName); // domain类中设置排序字段名
			alarmmanage.setSortorder(sortOrder); // domain类中设置升序OR降序
			alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
			alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			if (null == alarmtypeid || "".equals(alarmtypeid)) {
				alarmtypeid = "40";
			}
			alarmmanage.setAlarm_type_id(alarmtypeid);
			messageList = service.getObjectsByPage("AlarmManage.getTAbVehAlarmInfos", alarmmanage, 0, 3);
			map = getTabVehPagination(messageList);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			log.error("获取车辆TAB数据异常:", e);
			messageList = null;
		} catch (Exception e) {
			log.error("获取车辆TAB数据异常:", e);
			return ERROR;
		}
		log.info("获取车辆TAB数据结束");
		return SUCCESS;
	}

	/**
	 * 获取车辆TAB页告警的MAP
	 * 
	 * @param dayList
	 * @return
	 */
	public Map getTabVehPagination(List dayList) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		gpsUtil gsputil = new gpsUtil();
		//dayList = gsputil.getAlarmPosition(dayList);
		dayList = gsputil.getAlarmAddress(dayList);
		for (int i = 0; i < dayList.size(); i++) {
			AlarmManage s = (AlarmManage) dayList.get(i);
			// s.setDeal_flag(Constants.ALARM_SYS_MAP.get(s.getDeal_flag()));
			if (null == s.getSpeeding()	|| "".equals(s.getSpeeding())) {
				s.setSpeeding("");
			}else if("FFFF".equals(s.getSpeeding())){
				s.setSpeeding("无效");
			}else{
				s.setSpeeding(s.getSpeeding()+"km/h");
			}
			if (null == s.getEngine_rotate_speed() || "".equals(s.getEngine_rotate_speed())) {
				s.setEngine_rotate_speed("");
			}else if("FFFF".equals(s.getEngine_rotate_speed())){
				s.setEngine_rotate_speed("无效");
			}else{
				s.setEngine_rotate_speed(s.getEngine_rotate_speed()+"rpm");
			}
			if (null == s.getMileage()|| "".equals(s.getMileage())) {
				s.setMileage("");
			}else if("FFFF".equals(s.getMileage())){
				s.setMileage("无效");
			}else{
				s.setMileage(s.getMileage()+"km");
			}
			
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] { s.getLongitude(),
					s.getLatitude(), s.getVehicle_vin(), s.getDeal_flag(),
					s.getAlarm_type_id(), s.getDirection(), "",
					s.getVehicle_code(),s.getVehicle_ln(), s.getAlarm_type_name(),
					s.getAlarm_time(), "", s.getSpeeding(),
					s.getEngine_rotate_speed() , s.getMileage(),
					(s.getZonename() == null || "".equals(s.getZonename()) )? "定位无效":s.getZonename(), "" });
			mapList.add(cellMap);

		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", 5);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}

	/**
	 * 获取TAB页通勤车告警(非时发车)
	 * 
	 * @return
	 */
	public String getTabTqcNoFullAlarm() {
		//MDC.put("modulename", "[tabstualarm]");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String alarmtypeid = request.getParameter("alarmtypeid");
		String sortname = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");
		log.info("获取通勤车TAB数据开始:" + alarmtypeid);
		try {
			//UserInfo user = getCurrentUser();
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			tqcalarm.setOrganization_id(orgid);
//			tqcalarm.setAlarm_type(alarmtypeid);
			tqcalarm.setSortname(sortname);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setStart_time(DateUtil.getPreNDay(-2));
			tqcalarm.setOperate_flag("0");
			tqcalarm.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			tqcAlarmList = service.getObjectsByPage("AlarmManage.getNofullAlarmInfos", tqcalarm, 0, 3);
			map = getTqcTabNofullPagination(tqcAlarmList);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			log.error("获取通勤车（非时发车）TAB数据异常:", e);
			return ERROR;
			// stuAlarmList = null;
		} catch (Exception e) {
			log.error("获取通勤车（非时发车）TAB数据异常:", e);
			return ERROR;
		}

		log.info("获取通勤车（非时发车）TAB数据结束");
		return SUCCESS;
	}
	/**
	 * 获取TAB页通勤车告警(未满发车)
	 * 
	 * @return
	 */
	public String getTabTqcNoFullAlarm1() {
		//MDC.put("modulename", "[tabstualarm]");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String alarmtypeid = request.getParameter("alarmtypeid");
		String sortname = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");
		log.info("获取通勤车TAB数据开始:" + alarmtypeid);
		try {
			//UserInfo user = getCurrentUser();
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			tqcalarm.setOrganization_id(orgid);
			tqcalarm.setSortname(sortname);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setStart_time(DateUtil.getPreNDay(-2));
			tqcalarm.setOperate_flag("0");
			tqcalarm.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			tqcAlarmList = service.getObjectsByPage("AlarmManage.getNofullAlarmInfos1", tqcalarm, 0, 3);
			map = getTqcTabNofullPagination1(tqcAlarmList);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			log.error("获取通勤车（未满发车）TAB数据异常:", e);
			return ERROR;
			// stuAlarmList = null;
		} catch (Exception e) {
			log.error("获取通勤车（未满发车）TAB数据异常:", e);
			return ERROR;
		}

		log.info("获取通勤车（未满发车）TAB数据结束");
		return SUCCESS;
	}
	/**
	 * 获取TAB页通勤车告警(非站开门)
	 * 
	 * @return
	 */
	public String getTabTqcNotSiteAlarm() {
		//MDC.put("modulename", "[tabstualarm]");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String alarmtypeid = request.getParameter("alarmtypeid");
		String sortname = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");
		log.info("获取通勤车（站外开门）TAB数据开始:" + alarmtypeid);
		try {
			//UserInfo user = getCurrentUser();
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			tqcalarm.setOrganization_id(orgid);
			tqcalarm.setAlarm_type(alarmtypeid);
			tqcalarm.setSortname(sortname);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setOperate_flag("0");
			tqcalarm.setStart_time(DateUtil.getPreNDay(-2));
			tqcalarm.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			tqcAlarmList = service.getObjectsByPage("AlarmManage.getNotSiteAlarmInfos", tqcalarm, 0, 3);
			map = getTqcTabNotsitePagination(tqcAlarmList);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			log.error("获取通勤车（站外开门）TAB数据异常:", e);
			return ERROR;
			// stuAlarmList = null;
		} catch (Exception e) {
			log.error("获取通勤车（站外开门）TAB数据异常:", e);
			return ERROR;
		}

		log.info("获取通勤车（站外开门）TAB数据结束");
		return SUCCESS;
	}
	/**
	 * 获取TAB页通勤车告警(迟到)
	 * 
	 * @return
	 */
	public String getTabTqcLateAlarm() {
		//MDC.put("modulename", "[tabstualarm]");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String alarmtypeid = request.getParameter("alarmtypeid");
		String sortname = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");
		log.info("获取通勤车TAB数据开始:" + alarmtypeid);
		try {
			//UserInfo user = getCurrentUser();
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			tqcalarm.setOrganization_id(orgid);
			tqcalarm.setAlarm_type(alarmtypeid);
			tqcalarm.setSortname(sortname);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setStart_time(DateUtil.getPreNDay(-2));
			tqcalarm.setOperate_flag("0");
			tqcalarm.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			tqcAlarmList = service.getObjectsByPage("AlarmManage.getLateAlarmInfos", tqcalarm, 0, 3);
			map = getTqcTabLatePagination(tqcAlarmList);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			log.error("获取通勤车（迟到）TAB数据异常:", e);
			return ERROR;
			// stuAlarmList = null;
		} catch (Exception e) {
			log.error("获取通勤车（迟到）TAB数据异常:", e);
			return ERROR;
		}

		log.info("获取通勤车（迟到）TAB数据结束");
		return SUCCESS;
	}	
	/**
	 * 获取通勤车TAB页的MAP
	 * 
	 * @return
	 */
	public Map getTqcTabNofullPagination(List tqcalarmList) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < tqcalarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) tqcalarmList.get(i);
			// s.setOperate_flag(Constants.ALARM_SYS_MAP.get(s.getOperate_flag()));
			String lessTime = "";
			if("5".equals(s.getOperate_flag())){
				lessTime = s.getSend_time() == null || "".equals(s.getSend_time().trim()) ? "无"  : String.valueOf(Math.abs(((sdfMinute.parse(s.getSend_time())).getTime() - (sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime())/(60*1000)));
			} else {
				lessTime = s.getTerminal_time() == null || "".equals(s.getTerminal_time().trim()) ? "无" : String.valueOf(Math.abs(((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse(s.getSend_time())).getTime())/(60*1000)));
			}
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] { "", s.getVehicle_code(),s.getVehicle_ln(),
					s.getAlarm_type_name(),
					"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getRoute_name(),
					s.getAlarm_date(),
					s.getSite_name(),
					(s.getSend_time() != null && !"".equals(s.getSend_time()))?s.getTerminal_time().substring(0,10)+ " " + s.getSend_time() + ":00":"",
					s.getTerminal_time(),
					//s.getSend_time() == null || "".equals(s.getSend_time().trim()) ? "无"  : Math.abs(((sdfMinute.parse(s.getSend_time())).getTime() - (sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime())/(60*1000)),
					lessTime,
					Integer.parseInt("".equals(s.getStandard_cnt()) ? "0":s.getStandard_cnt()) - Integer.parseInt("".equals(s.getReal_cnt()) ? "0":s.getReal_cnt()),
					tmpDriver,
					s.getDriver_tel(),
					s.getAlarm_type(), 
					s.getAlarm_id(),
					s.getLatitude(),
					s.getLongitude(),
					s.getVehicle_vin(),
					s.getOperate_flag() });
			mapList.add(cellMap);
		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", 5);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}
	/**
	 * 获取通勤车TAB页的MAP
	 * 
	 * @return
	 */
	public Map getTqcTabNofullPagination1(List tqcalarmList) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < tqcalarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) tqcalarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			// s.setOperate_flag(Constants.ALARM_SYS_MAP.get(s.getOperate_flag()));
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] { "",
					s.getVehicle_code(),
					s.getVehicle_ln(),
					s.getAlarm_type_name(),
					"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getAlarm_date(),
					s.getRoute_name(),
					s.getSite_name(),
					"".equals(s.getStandard_cnt()) ? "0":s.getStandard_cnt(),
					Integer.parseInt("".equals(s.getReal_cnt()) ? "0":s.getReal_cnt()),
					Integer.parseInt("".equals(s.getStandard_cnt()) || null == s.getStandard_cnt() ? "0":s.getStandard_cnt()) - Integer.parseInt("".equals(s.getReal_cnt()) ? "0":s.getReal_cnt()),
					tmpDriver,
					s.getDriver_tel(),
					s.getAlarm_type(), 
					s.getAlarm_id(),
					s.getLatitude(),
					s.getLongitude(),
					s.getVehicle_vin(),
					s.getOperate_flag() });
			mapList.add(cellMap);
		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", 5);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}	
	/**
	 * 获取通勤车TAB页的MAP(非站开门)
	 * 
	 * @return
	 */
	public Map getTqcTabNotsitePagination(List tqcalarmList) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < tqcalarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) tqcalarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			// s.setOperate_flag(Constants.ALARM_SYS_MAP.get(s.getOperate_flag()));
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] { "", s.getVehicle_code(),s.getVehicle_ln(),
					s.getAlarm_type_name(),
					"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getRoute_name(),
					s.getAlarm_date(),
					SearchGisAreaByCode.getZoneNameByPosition(s.getLongitude(),s.getLatitude()),
					s.getUpSite(),
					s.getSite_name(),//最近站点，需求更改后，改为只在线路中有非站开们告警，所以该字段不再使用。
					s.getDownSite(),
					tmpDriver,
					s.getDriver_tel(),
					"3", 
					s.getAlarm_id(),
					s.getLatitude(),
					s.getLongitude(),
					s.getVehicle_vin(),
					s.getOperate_flag() });
			mapList.add(cellMap);
		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", 5);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}
	/**
	 * 获取通勤车TAB页的MAP(迟到)
	 * 
	 * @return
	 */
	public Map getTqcTabLatePagination(List tqcalarmList) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < tqcalarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) tqcalarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			// s.setOperate_flag(Constants.ALARM_SYS_MAP.get(s.getOperate_flag()));
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] { "", s.getVehicle_code(),s.getVehicle_ln(),
					"迟到告警",
					"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getAlarm_date(),
					s.getRoute_name(),
					s.getSite_name(),
					//sdfMinute.format(sdf.parse(s.getTerminal_time())),
					s.getTerminal_time(),
					((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse("08:00")).getTime())/(60*1000),
					tmpDriver,
					s.getDriver_tel(),
					"4", 
					s.getAlarm_id(),
					s.getLatitude(),
					s.getLongitude(),
					s.getVehicle_vin(),
					s.getOperate_flag() });
			mapList.add(cellMap);
		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", 5);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}

	/**
	 * 更多SOS告警列表显示 和2.0共用
	 * 
	 * @return
	 */
	public String alarmOpenSosList() {
		final String browseTitle = getText("alarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		//String logid=getlogid();
		int totalCount = 0;
		//MDC.put("loginid", getloginuuid());
		//MDC.put("modulename", "[sosalarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:"
				+ start_time + ";end_time:" + end_time + ";alarm_type_id:"
				+ alarm_type_id+"chooseorgid"+chooseorgid+"];更多紧急告警查询开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			if (null == chooseorgid || "".equals(chooseorgid) || "undefined".equals(chooseorgid)) {
				alarmmanage.setOrganization_id(user.getOrganizationID());
			} else {
				alarmmanage.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode"); 
			// 增加中文排序
			if (sortName.equals("vehicle_ln") || sortName.equals("driver_name") || sortName.equals("sichen_name")) {
				sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			alarmmanage.setSortname(sortName);
			alarmmanage.setSortorder(sortOrder);
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			alarmmanage.setStart_time(start_time);
			alarmmanage.setDeal_flag(deal_flag);
			if("1".equals(deal_flag)){
				alarmmanage.setSortotherconfig(" cyat.alarm_end_time desc, ");
				alarmmanage.setSortotherconfig2(" cyat.opeerate_desc , ");
			}
			alarmmanage.setVehicle_code(SearchUtil.formatSpecialChar(vehicleCode));
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}

			alarmmanage.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getBusAlarmCount", alarmmanage);
			dayList = service.getObjectsByPage("AlarmManage.getSosAlarmInfos", alarmmanage, (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

			this.map = getSosMorePagination(dayList, totalCount, pageIndex,	rpNum);

			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("更多紧急告警查询结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询更多紧急告警出错:", e);
			return ERROR;
		}catch (Exception e){
			addActionError(getText(e.getMessage()));
			log.error("查询更多紧急告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 更多SOS告警列表显示优化 暂时不用
	 * 
	 * @return
	 */
	public String alarmOpenSosListnew() {
		final String browseTitle = getText("alarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[sosalarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:"
				+ start_time + ";end_time:" + end_time + ";alarm_type_id:"
				+ alarm_type_id+"chooseorgid:"+chooseorgid+"];更多SOS告警");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			Map<String, Object> countmap = new HashMap<String, Object>(8);
			alarmmanage.setOrganization_id(user.getOrganizationID());
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			alarmmanage.setSortname(sortName);
			alarmmanage.setSortorder(sortOrder);
			alarmmanage.setVehicle_ln(vehicle_ln);
			alarmmanage.setStart_time(start_time);
			alarmmanage.setDeal_flag(deal_flag);
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}
			alarmmanage.setAlarm_type_id(alarm_type_id);

			countmap.put("in_org_id", alarmmanage.getOrganization_id());
			countmap.put("in_starttime", start_time);
			countmap.put("in_endtime", end_time);
			countmap.put("in_vehln", vehicle_ln);
			countmap.put("in_alarmtypeid", alarm_type_id);
			countmap.put("in_dealflag", deal_flag);
			countmap.put("out_message", null);
			countmap.put("out_count", null);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}
			log.info("查询总数开始");
			service.getObject("AlarmManage.get_alarmcount", countmap);
			if (SUCCESS.equals(countmap.get("out_message"))) {
				totalCount = (Integer) countmap.get("out_count");
			} else {
				log.error("获取TAB数量出错," + map.get("out_message"));
			}
			log.info("查询总数结束");
			log.info("查询列表开始");
			if (totalCount != 0) {
				dayList = service.getObjectsByPage(
						"AlarmManage.getSosAlarmInfos", alarmmanage, (Integer
								.parseInt(pageIndex) - 1)
								* Integer.parseInt(rpNum), Integer
								.parseInt(rpNum));
			} else {
				dayList = new ArrayList<AlarmManage>();
			}
			log.info("查询列表结束");

			this.map = getSosMorePagination(dayList, totalCount, pageIndex,
					rpNum);

			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询更多SOS告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 更多SOS告警转换MAP
	 * 
	 * @param dayList
	 * @param totalCountDay
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */
	public Map getSosMorePagination(List dayList, int totalCountDay,
			String pageIndex, String rpNum) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		//gpsUtil gsputil = new gpsUtil();
		//dayList = gsputil.getAlarmPosition(dayList);
		//dayList = gsputil.getAlarmAddress(dayList);
		for (int i = 0; i < dayList.size(); i++) {

			AlarmManage s = (AlarmManage) dayList.get(i);
			if (null == s.getDriver_name() || "".equals(s.getDriver_name())) {
				s.setDriver_name("未登录");
			}
			if (null == s.getSichen_name() || "".equals(s.getSichen_name())) {
				s.setSichen_name("未登录");
			}
			if (null == s.getStu_num() || "".equals(s.getStu_num())) {
				s.setStu_num("0");
			}

			Map cellMap = new LinkedHashMap();

			cellMap.put("id", s.getAlarm_id());
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			cellMap.put("cell", new Object[] { s.getLongitude(),
					s.getLatitude(), s.getVehicle_vin(), s.getAlarm_type_id(),
					s.getDeal_flag(), s.getDirection(), s.getAlarm_id(),
					s.getVehicle_code(),s.getVehicle_ln(), s.getAlarm_type_name(),s.getDeal_flag(),
					s.getAlarm_time(), 
					tmpDriver, 
					s.getSichen_name(),
					s.getStu_num(),(s.getZonename() == null || "".equals(s.getZonename()) )? "定位无效":s.getZonename(), "", s.getUser_name(),s.getOpeerate_desc() });
			mapList.add(cellMap);

		}

		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}

	

	/**
	 * 更多车辆告警列表显示 2.0共用
	 * 
	 * @return
	 */
	public String alarmOpenManageList() {
		final String browseTitle = getText("alarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[vehalarm]");
		log.info("[vehicle_ln:" + vehicle_ln
				+ ";start_time:" + start_time + ";end_time:" + end_time
				+ ";alarm_type_id:" + alarm_type_id+"chooseorgid"+chooseorgid+"]:更多车辆其他告警查询开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			if (null == chooseorgid || "".equals(chooseorgid) || "undefined".equals(chooseorgid)) {
				alarmmanage.setOrganization_id(user.getOrganizationID());
			} else {
				alarmmanage.setOrganization_id(chooseorgid);
			}

			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			alarmmanage.setSortname(sortName);
			alarmmanage.setSortorder(sortOrder);
			alarmmanage.setVehicle_code(SearchUtil.formatSpecialChar(vehicleCode));
			// 增加中文排序
			if (sortName.equals("vehicle_ln")) {
				sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			alarmmanage.setStart_time(start_time);
			if(null != deal_flag || deal_flag.length() != 0){
				alarmmanage.setDeal_flag(deal_flag);
				
				if("1".equals(deal_flag)&& sortName.equals("alarm_time")){
					alarmmanage.setSortname("cyat.alarm_end_time");   //默认排序方式：全部和未处理 按告警时间降序排列；已处理按处理时间降序排列
				}
			}
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}

			alarmmanage.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}
			log.info("更多车辆其他告警计算总数开始");
			totalCount = service.getCount("AlarmManage.getBusAlarmCount",alarmmanage);
			log.info("更多车辆其他告警计算总数结束");
			log.info("更多车辆其他告警列表查询开始");
			/*
			if(sortName.equals("alarm_time")){
				dayList = service.getObjectsByPage("AlarmManage.testordertime",
						alarmmanage, (Integer.parseInt(pageIndex) - 1)
								* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			}else{
				dayList = service.getObjectsByPage("AlarmManage.getBusAlarmInfos",
						alarmmanage, (Integer.parseInt(pageIndex) - 1)
								* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			}*/
			
			dayList = service.getObjectsByPage("AlarmManage.getBusAlarmInfos",
					alarmmanage, (Integer.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			
			log.info("更多车辆其他告警列表查询结束");
			this.map = getOpenMorePagination(dayList, totalCount, pageIndex, rpNum);

			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("更多车辆其他告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询更多其他车辆告警出错:", e);
			return ERROR;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			log.error("查询更多其他车辆告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 更多车辆告警列表显示优化 暂时不用
	 * 
	 * @return
	 */
	public String alarmOpenManageListnew() {
		//String logid = UUIDGenerator.getUUID32();
		final String browseTitle = getText("alarmmanage.browse.title");
		//MDC.put("modulename", "[vehalarm]");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		log.info("[vehicle_ln:" + vehicle_ln
				+ ";start_time:" + start_time + ";end_time:" + end_time
				+ ";alarm_type_id:" + alarm_type_id+"chooseorgid"+chooseorgid+"]更多车辆其他告警");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			Map<String, Object> countmap = new HashMap<String, Object>(8);
			alarmmanage.setOrganization_id(user.getOrganizationID());
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			alarmmanage.setSortname(sortName);
			alarmmanage.setSortorder(sortOrder);
			alarmmanage.setVehicle_ln(vehicle_ln);
			alarmmanage.setStart_time(start_time);
			alarmmanage.setDeal_flag(deal_flag);
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}

			alarmmanage.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}
			countmap.put("in_org_id", alarmmanage.getOrganization_id());
			countmap.put("in_starttime", start_time);
			countmap.put("in_endtime", end_time);
			countmap.put("in_vehln", vehicle_ln);
			countmap.put("in_alarmtypeid", alarm_type_id);
			countmap.put("in_dealflag", deal_flag);
			countmap.put("out_message", null);
			countmap.put("out_count", null);
			log.info("更多车辆其他告警计算总数开始");
			service.getObject("AlarmManage.get_alarmcount", countmap);
			if (SUCCESS.equals(countmap.get("out_message"))) {
				totalCount = (Integer) countmap.get("out_count");
			} else {
				log.error("获取数量出错," + map.get("out_message"));
			}
			log.info("更多车辆其他告警计算总数结束");
			log.info("更多车辆其他告警列表查询开始");
			if (totalCount != 0) {
				dayList = service.getObjectsByPage(
						"AlarmManage.getBusAlarmInfos", alarmmanage, (Integer
								.parseInt(pageIndex) - 1)
								* Integer.parseInt(rpNum), Integer
								.parseInt(rpNum));
			} else {
				dayList = new ArrayList<AlarmManage>();
			}
			log.info("更多车辆其他告警列表查询结束");
			this.map = getOpenMorePagination(dayList, totalCount, pageIndex,
					rpNum);

			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询更多其他车辆告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 更多车辆其他告警转换MAP
	 * 
	 * @param dayList
	 * @param totalCountDay
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */
	public Map getOpenMorePagination(List dayList, int totalCountDay,
			String pageIndex, String rpNum) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		gpsUtil gsputil = new gpsUtil();
		//dayList = gsputil.getAlarmPosition(dayList);
		dayList = gsputil.getAlarmAddress(dayList);
		for (int i = 0; i < dayList.size(); i++) {

			AlarmManage s = (AlarmManage) dayList.get(i);
			if (null == s.getSpeeding()
					|| "".equals(s.getSpeeding())) {
				s.setSpeeding("");
			}else if("FFFF".equals(s.getSpeeding())){
				s.setSpeeding("无效");
			}else{
				s.setSpeeding(s.getSpeeding()+"km/h");
			}
					
			
			if (null == s.getEngine_rotate_speed()
					|| "".equals(s.getEngine_rotate_speed())) {
				s.setEngine_rotate_speed("");
			}else if("FFFF".equals(s.getEngine_rotate_speed())){
				s.setEngine_rotate_speed("无效");
			}else{
				s.setEngine_rotate_speed(s.getEngine_rotate_speed()+"rpm");
			}
			
			if (null == s.getMileage()
					|| "".equals(s.getMileage())) {
				s.setMileage("");
			}else if("FFFF".equals(s.getMileage())){
				s.setMileage("无效");
			}else{
				s.setMileage(s.getMileage()+"km");
			}
			
			Map cellMap = new LinkedHashMap();

			cellMap.put("id", s.getAlarm_id());

			cellMap.put("cell", new Object[] { s.getLongitude(),
					s.getLatitude(), s.getVehicle_vin(), s.getAlarm_type_id(),
					s.getDeal_flag(), s.getDirection(), s.getAlarm_id(),
					s.getVehicle_code(),s.getVehicle_ln(), s.getAlarm_type_name(),s.getDeal_flag(),
					s.getAlarm_time(), s.getSpeeding(),
					s.getEngine_rotate_speed(), s.getMileage(),
					(s.getZonename() == null || "".equals(s.getZonename()) )? "定位无效":s.getZonename(), "",s.getUser_name(),s.getOpeerate_desc(),s.getDriver_name() });
			mapList.add(cellMap);

		}

		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}

	/**
	 * 更多学生告警转换MAP
	 * 
	 * @param dayList
	 * @param totalCountDay
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */
	public Map getStuOpenMorePagination(List dayList, int totalCountDay,
			String pageIndex, String rpNum) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < dayList.size(); i++) {

			StuAlarm s = (StuAlarm) dayList.get(i);

			Map cellMap = new LinkedHashMap();

			cellMap.put("id", s.getId());
			cellMap.put("cell", new Object[] { s.getLongitude(),
					s.getLatitude(), s.getVehicle_vin(), s.getAlarm_type_id(),
					s.getOperate_flag(), s.getId(), s.getVehicle_ln(),
					s.getRoute_name(), s.getSite_name(), s.getStu_name(),
					s.getStudent_code(), s.getStu_school(), s.getStu_class(),
					s.getAlarm_type_name(),s.getOperate_flag(),s.getTerminal_time(), "",s.getUser_name(),s.getOperate_desc() });
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}

	/**
	 * 二期车辆告警解除 下发告警关闭指令
	 * 
	 * @param userid
	 * @param vin
	 * @param desc
	 * @param alarmtypeid
	 * @param alarmid
	 * @return
	 */
	public String postOffCommand(String userid, String vin, String desc,
			String alarmtypeid, String alarmid,String alarmtime) {
		//MDC.put("modulename", "[dealvehalarm]");
		String returnvalue = "";
		log.info("[alarmid:"+alarmid+";vin:"+vin+"]处理车辆告警开始");
		if (null == alarmmanage) {
			alarmmanage = new AlarmManage();
		}
		alarmmanage.setUser_id(userid);
		alarmmanage.setVehicle_vin(vin);
		alarmmanage.setOpeerate_desc(desc);
		alarmmanage.setAlarm_id(alarmid);
		alarmmanage.setAlarm_type_id(alarmtypeid);
		alarmmanage.setAlarm_time(alarmtime);
		if (Constants.ALARM_OFF_MAP.containsKey(alarmmanage.getAlarm_type_id())) {
			try {
				alarmmanage.setDeal_flag("2");
				returnvalue = alarmservice.updateAlarm(alarmmanage,	sendCommandClient);
			} catch (Exception e) {
				log.error("处理车辆告警异常:", e);
			}
		} else {
			try {
				alarmmanage.setDeal_flag("1");
				service.update("AlarmManage.updateVehAlarmFlag", alarmmanage);
				returnvalue = "0";
			} catch (Exception e) {
				log.error("处理车辆告警异常:", e);
			}
		}
		// 设置操作描述
		this.addOperationLog("解除告警");
		// 设置操作类型
		this.setOperationType(Constants.UPDATE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ALARM_UPDATE_ID);
		log.info("处理车辆告警结束");
		return returnvalue;
	}

	/**
	 * 解除通勤车告警
	 * 
	 * @param userid
	 * @param vin
	 * @param desc
	 * @param alarmtypeid
	 * @param alarmid
	 * @return
	 */
	public String postTqcCommand(String userid, String vin, String desc,
			String alarmtypeid, String alarmid,String alarmtime) {
		//MDC.put("loginid", getloginuuid());
		//MDC.put("modulename", "[dealstualarm]");
		log.info("[alarmid:"+alarmid+";vin:"+vin+"]处理通勤车告警开始");
		String returnvalue = "";
		if (null == tqcalarm) {
			tqcalarm = new TqcAlarm();
		}
		tqcalarm.setVehicle_vin(vin);
		tqcalarm.setOperate_desc(desc);
		tqcalarm.setAlarm_id(alarmid);
		tqcalarm.setAlarm_date(alarmtime);
		tqcalarm.setOperate_name(userid);
		try {
			if("010".equals(alarmtypeid) || "001".equals(alarmtypeid) || "12".equals(alarmtypeid)||"010,12".equals(alarmtypeid)){
				service.update("AlarmManage.updateTqcOilAlarmFlag", tqcalarm);
			} else {
				service.update("AlarmManage.updateTqcAlarmFlag", tqcalarm);
			}
			returnvalue = "0";
		} catch (Exception e) {
			log.error("处理通勤车告警异常:", e);
			returnvalue = "7002";
		}
		// 设置操作描述
		this.addOperationLog("解除告警");
		// 设置操作类型
		this.setOperationType(Constants.UPDATE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ALARM_UPDATE_ID);
		log.info("处理通勤车告警结束");
		return returnvalue;
	}

	/**
	 * 车辆告警批量处理
	 */
	public void alarmMoreDeal() {
		//MDC.put("modulename", "[dealmorevehalarm]");
		log.info("[alarmids:"+alarmids+"]车辆告警批量处理开始");
		String alarmid[] = alarmids.split(",");
		String vin[] = vins.split(",");
		String alarmtype[] = alarmtypes.split(",");
		String dealflag[] = dealflags.split(",");
		String alarmtime[]=alramtimes.split(",");
		List<AlarmManage> moredeallist = new ArrayList<AlarmManage>();
		String usrid = getCurrentUser().getUserID();
		String batchid = UUIDGenerator.getUUID32();
		try {
			for (int i = 0; i < alarmid.length; i++) {
				AlarmManage alarm = new AlarmManage();
				alarm.setAlarm_id(alarmid[i]);
				alarm.setVehicle_vin(vin[i]);
				alarm.setUser_id(usrid);
				alarm.setOperate_type("2");
				alarm.setAlarm_time(alarmtime[i]);
				alarm.setAlarm_type_id(alarmtype[i]);
				if (Constants.ALARM_OFF_MAP.containsKey(alarmtype[i])) {
					String msgid = UUIDGenerator.getUUID32();
					String returnvalue = sendCommandClient
							.sendAlaramOffCommand(alarm.getVehicle_vin(), alarm
									.getUser_id(), Constants.ALARM_OFF_MAP
									.get(alarm.getAlarm_type_id()), msgid,
									alarm.getAlarm_id(), alarm
											.getOpeerate_desc(), batchid);
					LOG.info("msgid:" + msgid + ";returnvalue:" + returnvalue);
					if ("0".equals(returnvalue)) {
						// update("AlarmManage.updateVehAlarmFlag",
						// alarmmanage);
						alarm.setDeal_flag("2");
					} else {
						alarm.setDeal_flag(dealflag[i]);
					}
				} else {
					alarm.setDeal_flag("1");
				}
				moredeallist.add(alarm);
			}
			if(alarmtype[0]!=null&&alarmtype[0].equals("40"))
				alarmservice.updateList(moredeallist);
			else if(alarmtype[0]!=null&&alarmtype[0].equals("32"))
				alarmservice.updateotherAlarmList(moredeallist);
			// 设置操作描述
			this.addOperationLog("批量解除");
			// 设置操作类型
			this.setOperationType(Constants.UPDATE);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_PIUPDATE_ID);
			log.info("车辆告警批量处理结束");
		} catch (Exception e) {
			log.error("车辆批量处理告警出错:", e);
		}

	}

	/**
	 * 通勤车告警批量处理
	 */
	public void alarmtqcMoreDeal() {
		//MDC.put("modulename", "[dealmorestualarm]");
		log.info("[alarmids:"+alarmids+"]通勤车告警批量处理开始");
		String alarmid[] = alarmids.split(",");
		String dealflag[] = dealflags.split(",");
		String tertime[]=tertimes.split(",");
		List<TqcAlarm> moretqcdeallist = new ArrayList<TqcAlarm>();
		String usrid = getCurrentUser().getUserID();
		String batchid = UUIDGenerator.getUUID32();
		try {
			for (int i = 0; i < alarmid.length; i++) {
				TqcAlarm tqcalarm = new TqcAlarm();
				tqcalarm.setAlarm_id(alarmid[i]);
				tqcalarm.setOperate_name(usrid);
				tqcalarm.setOperate_flag("1");
				tqcalarm.setAlarm_date(tertime[i]);
				moretqcdeallist.add(tqcalarm);
			}
			alarmservice.updateTqcList(moretqcdeallist);
			// 设置操作描述
			this.addOperationLog("批量解除");
			// 设置操作类型
			this.setOperationType(Constants.UPDATE);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_PIUPDATE_ID);
			log.info("违规乘车批量处理结束");
		} catch (Exception e) {
			log.error("违规乘车批量处理出错:", e);
		}
	}

	/**
	 * SOS告警数据导出
	 * 
	 * @return
	 */
	public String exportSosalarm() throws Exception{
		//String logid=getlogid();
		String exportTitle = getText("alarmmanage.sos.export");
		//log.info("logid:"+logid+";"+exportTitle+"开始");
		//MDC.put("modulename", "[exportsos]");
		UserInfo user = getCurrentUser();
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:"
				+ start_time + ";end_time:" + end_time + ";alarm_type_id:"
				+ alarm_type_id+";chooseorgid:"+chooseorgid+"]导出紧急告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String timestr = start_time + "——" + end_time;
		String vehicleCode = request.getParameter("vehicleCode");
		
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				alarmmanage.setOrganization_id(user.getOrganizationID());
			} else {
				alarmmanage.setOrganization_id(chooseorgid);
			}
			// alarmmanage.setOrganization_id(user.getOrganizationID());
			// String rpNum = request.getParameter("rp");
			// String pageIndex = request.getParameter("page");
			// String sortName = request.getParameter("sortname");
			// String sortOrder = request.getParameter("sortorder");
			//alarmmanage.setSortname("ALARM_TIME");
			//alarmmanage.setSortorder("desc"); //从前台获取排序规则
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			alarmmanage.setStart_time(start_time);
			alarmmanage.setDeal_flag(deal_flag);
			alarmmanage.setVehicle_code(vehicleCode);
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}

			alarmmanage.setAlarm_type_id(alarm_type_id);
			/*
			 * if (StringUtils.isEmpty(pageIndex)) { pageIndex = "1"; } if
			 * (StringUtils.isEmpty(rpNum)) { rpNum = "10"; } totalCount =
			 * service.getCount("AlarmManage.getBusAlarmCount", alarmmanage);
			 */
			dayList = service.getObjects("AlarmManage.getSosAlarmInfos",
					alarmmanage);
			log.info("导出紧急告警查询列表结束");
		} catch (BusinessException e) {
			log.error("导出紧急告警查询列表时出错:", e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出紧急告警查询列表时出错:", e);
			return ERROR;
		}
		 List < AlarmManage > exportlist = new ArrayList < AlarmManage >();
	        DecimalFormat decimalformat = new DecimalFormat("0.00");
	        for (int i = 0; i < dayList.size(); i++) {
	        	AlarmManage oilexport = new AlarmManage();
	        	AlarmManage oilused = dayList.get(i);
	        	oilexport.setVehicle_code(oilused.getVehicle_code());
	            oilexport.setVehicle_ln(oilused.getVehicle_ln());
	            oilexport.setAlarm_type_name(oilused.getAlarm_type_name());
	            //oilexport.setDeal_flag("0".equals(oilused.getDeal_flag())?"未处理":"已处理");
	            if("0".equals(oilused.getDeal_flag())){
	            	oilexport.setDeal_flag("未处理");
	            }
	            else if("1".equals(oilused.getDeal_flag())){
	            	oilexport.setDeal_flag("已处理");
	            }
	            else{
	            	oilexport.setDeal_flag("处理中");
	            }
	            oilexport.setAlarm_time(oilused.getAlarm_time());
	            oilexport.setDriver_name(oilused.getDriver_name()==null?"未登录":oilused.getDriver_name());
	            oilexport.setStu_num(oilused.getStu_num());
	            oilexport.setZonename((oilused.getZonename()==null || "".equals(oilused.getZonename()))?"定位无效":oilused.getZonename());
	            exportlist.add(oilexport);
	        }
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "SosAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("紧急告警("+timestr+")");
			
			if (null == exportlist || exportlist.size() < 1) {
				exportlist.add(new AlarmManage());
			}
			//gpsUtil gsputil = new gpsUtil();//获取地理位置
			//dayList = gsputil.getAlarmAddress(dayList);
			excelExporter.putAutoExtendSheets("exportsosalarm", 0, exportlist);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出紧急告警写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出紧急告警写入Excel时出错:",e);
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

		// 设置下载文件属性
		String fileName = URLEncoder.encode("紧急告警","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename="+ fileName + "-" + name + ".xls");
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
			log.error("导出紧急告警下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出紧急告警下载时出错:",e);
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
			this.addOperationLog(formatLog(exportTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
			log.info("导出紧急告警结束");
		}
		// 导出文件成功
		return null;
	}

	/**
	 * 超速告警数据导出
	 * 
	 * @return
	 */
	public String exportOtheralarm() throws Exception{
		//String logid=getlogid();
		String exportTitle = getText("alarmmanage.other.export");
		if (null != alarm_type_id) {
			if (alarm_type_id.contains("32") || alarm_type_id.contains("33")
					|| alarm_type_id.contains("54")) {
				exportTitle = getText("alarmmanage.weigui.export");
			}
		}
		List<AlarmManage> tempList;
		//MDC.put("modulename", "[exportvehalarm]");
		//log.info("logid:"+logid+";"+exportTitle+"开始");
		UserInfo user = getCurrentUser();
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:"
				+ start_time + ";end_time:" + end_time + ";alarm_type_id:"
				+ alarm_type_id+";chooseorgid:"+chooseorgid+"]更多车辆超速告警导出开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String timestr = start_time + "——" + end_time;
		String vehicleCode = request.getParameter("vehicleCode");
		
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			//alarmmanage.setOrganization_id(user.getOrganizationID());
			if (null == chooseorgid || "".equals(chooseorgid)) {
				alarmmanage.setOrganization_id(user.getOrganizationID());
			} else {
				alarmmanage.setOrganization_id(chooseorgid);
			}
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			alarmmanage.setStart_time(start_time);
			alarmmanage.setDeal_flag(deal_flag);
			alarmmanage.setVehicle_code(vehicleCode);
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}
			alarmmanage.setAlarm_type_id(alarm_type_id);
			//alarmmanage.setSortname("ALARM_TIME");    从前台获取排序规则
			//alarmmanage.setSortorder("desc");
//			dayList = service.getObjects("AlarmManage.exportBusAlarmInfos",
//					alarmmanage);
			dayList = service.getObjects("AlarmManage.getBusAlarmInfos",
					alarmmanage);
			log.info("超速告警导出查询列表结束");
		} catch (BusinessException e) {
			if (null != alarm_type_id) {
				if (alarm_type_id.contains("32")
						|| alarm_type_id.contains("33")
						|| alarm_type_id.contains("54")) {
					log.error("超速告警导出查询列表出错:", e);
				} else {
					log.error("超速告警导出查询列表出错:", e);
				}
			}
			return ERROR;
		} catch (Exception e) {
			if (null != alarm_type_id) {
				if (alarm_type_id.contains("32")
						|| alarm_type_id.contains("33")
						|| alarm_type_id.contains("54")) {
					log.error("超速告警导出查询列表出错:", e);
				} else {
					log.error("超速故障告警导出查询列表出错:", e);
				}
			}
			return ERROR;
		}
		
		List < AlarmManage > exportlist = new ArrayList < AlarmManage >();
        DecimalFormat decimalformat = new DecimalFormat("0.00");
        for (int i = 0; i < dayList.size(); i++) {
        	AlarmManage oilexport = new AlarmManage();
        	AlarmManage oilused = dayList.get(i);
        	oilexport.setVehicle_code(oilused.getVehicle_code());
            oilexport.setVehicle_ln(oilused.getVehicle_ln());
            oilexport.setAlarm_type_name(oilused.getAlarm_type_name());
            oilexport.setDeal_flag("0".equals(oilused.getDeal_flag())?"未处理":"已处理");
            oilexport.setAlarm_time(oilused.getAlarm_time());
            oilexport.setSpeeding(oilused.getSpeeding());
            oilexport.setMileage(oilused.getMileage());
            //oilexport.setDriver_name(oilused.getDriver_name()==null?"未登录":oilused.getDriver_name());
            
            oilexport.setZonename((oilused.getZonename()==null || "".equals(oilused.getZonename()))?"定位无效":oilused.getZonename());
            exportlist.add(oilexport);
        }

		
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "Alarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			if (null != alarm_type_id) {
				if (alarm_type_id.contains("32")
						|| alarm_type_id.contains("33")
						|| alarm_type_id.contains("54")) {
					excelExporter.setTitle("超速告警("+timestr+")");
				} else {
					excelExporter.setTitle("车辆故障("+timestr+")");
				}
			}
			
			if (null == exportlist || exportlist.size() < 1) {
				exportlist.add(new AlarmManage());
			}
			
			excelExporter.putAutoExtendSheets("exportotheralarm", 0, exportlist);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("更多车辆其他告警导出生成EXCLE出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("更多车辆其他告警导出生成EXCLE出错:",e);
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

		// 设置下载文件属性
		String fileName = URLEncoder.encode("超速告警","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition", "attachment;filename="+ fileName +"-"
				+ name + ".xls");
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
			if (null != alarm_type_id) {
				if (alarm_type_id.contains("32")
						|| alarm_type_id.contains("33")
						|| alarm_type_id.contains("54")) {
					log.error("导出违规驾驶告警下载时出错:", e);
				} else {
					log.error("导出车辆故障告警下载时出错:", e);
				}
			}
			return ERROR;
		} catch (Exception e) {
			if (null != alarm_type_id) {
				if (alarm_type_id.contains("32")
						|| alarm_type_id.contains("33")
						|| alarm_type_id.contains("54")) {
					log.error("导出违规驾驶告警下载时出错:", e);
				} else {
					log.error("导出车辆故障告警下载时出错:", e);
				}
			}
			// log.error("导出紧急告警出错:" + e.getMessage());
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
			this.addOperationLog(formatLog(exportTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
			log.info("更多车辆其他告警导出结束");
		}
		// 导出文件成功
		return null;
	}

	public String exportStualarm() {
		//String logid=getlogid();
		String exportTitle = getText("alarmmanage.stualarm.export");
		//MDC.put("modulename", "[exportstualarm]");
		//log.info("logid:"+logid+";"+exportTitle+"开始");
		UserInfo user = getCurrentUser();
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:"
				+ start_time + ";end_time:" + end_time + ";alarm_type_id:"
				+ alarm_type_id+";chooseorgid:"+chooseorgid+"]通勤车迟到告警导出开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String timestr = start_time + "——" + end_time;
		try {

			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			//stualarm.setOrganization_id(user.getOrganizationID());
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}
			if ("".equals(alarm_type_id) || null == alarm_type_id) {
				tqcalarm.setAlarm_type("4");
			} else {
				tqcalarm.setAlarm_type(alarm_type_id);
			}

			allStuAlarmList = service.getObjects("AlarmManage.exportStuAlarmInfos", tqcalarm);
			log.info("更多学生违规告警导出列表查询结束");
		} catch (BusinessException e) {
			log.error("导出学生告警列表查询出错:", e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出学生告警列表查询出错:", e);
			return ERROR;
		}
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "stuAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("异常乘车("+timestr+")");
			
			if (null == allStuAlarmList || allStuAlarmList.size() < 1) {
				allStuAlarmList.add(new StuAlarm());
			}
			
			excelExporter.putAutoExtendSheets("exportstualarm", 0,
					allStuAlarmList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出学生告警生成EXCLE出错:", e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出学生告警生成EXCLE出错:", e);
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

		// 设置下载文件属性
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename=stuAlarm-" + name + ".xls");
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
			log.error("导出学生告警下载时出错:", e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出学生告警下载时出错:", e);
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
		}
		// 设置操作描述
		this.addOperationLog(formatLog(exportTitle, null));
		// 设置操作类型
		this.setOperationType(Constants.EXPORT);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
		log.info("更多学生告警导出结束");
		return null;
	}
	
	
	/**
	 * 非时发车告警数据导出
	 * 
	 * @return
	 */
	public String exportnontimealarm() throws Exception{
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			//String sortName = request.getParameter("sortname");
			//String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			//tqcalarm.setSortname(sortName);
			//tqcalarm.setSortorder(sortOrder);   //排序的规则从前台已经获取
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setVehicle_code(vehicleCode);
			
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getNofullAlarmCount", tqcalarm);
			allTqcAlarmList = service.getObjects("AlarmManage.getNofullAlarmInfos", tqcalarm);
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车非时发车告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车非时发车告警出错:", e);
			return ERROR;
		}
		
		List<TqcAlarm> nofullAlarmList= new ArrayList < TqcAlarm >();
		for (int i = 0; i < allTqcAlarmList.size(); i++) {
			TqcAlarm export=new TqcAlarm();
			TqcAlarm s = (TqcAlarm) allTqcAlarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			export.setVehicle_code(s.getVehicle_code());
			export.setVehicle_ln(s.getVehicle_ln());
			export.setAlarm_type_name(s.getAlarm_type_name());
			export.setOperate_flag("0".equals(s.getOperate_flag())? "未处理" : "已处理");
			export.setRoute_name(s.getRoute_name());
			export.setAlarm_date(s.getAlarm_date());
			export.setSite_name(s.getSite_name());
			export.setSend_time(s.getSend_time()== null||"".equals(s.getSend_time().trim())? "无" :s.getSend_time());
//			if(s.getSend_time() == null || "".equals(s.getSend_time())){
//				export.setTerminal_time("无");
//			}
//			else{
//				Object mmm=((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse(s.getSend_time())).getTime())/(60*1000);
//				String nn=mmm.toString();
//				s.setTerminal_time(nn);
//			}
			export.setTerminal_time(s.getTerminal_time());
			export.setTime_dis(s.getSend_time() == null || "".equals(s.getSend_time().trim())?"无":String.valueOf(Math.abs(((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse(s.getSend_time())).getTime())/(60*1000))));
//			export.setEmpty_num(String.valueOf(Integer.parseInt("".equals(s.getStandard_cnt()) ? "0":s.getStandard_cnt()) - Integer.parseInt("".equals(s.getReal_cnt()) ? "0":s.getReal_cnt())));
			export.setDriver_name(tmpDriver);
//			export.setDriver_tel(s.getDriver_tel());
			nofullAlarmList.add(export);
			
		}
		
		
		
		
		
		
        String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "SosAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			String timestr = start_time + "——" + end_time;
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("非时发车告警("+timestr+")");
			
			if (null == nofullAlarmList || nofullAlarmList.size()<1) {
				nofullAlarmList.add(new TqcAlarm());
			}

			excelExporter.putAutoExtendSheets("exportnontimealarm", 0, nofullAlarmList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出非时发车告警写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出非时发车告警写入Excel时出错:",e);
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

		// 设置下载文件属性
		String fileName = URLEncoder.encode("非时发车告警","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename="+ fileName + "-" + name + ".xls");
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
			log.error("导出非时告警下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出非时告警下载时出错:",e);
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
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
			log.info("导出非时发车告警结束");
		}
		// 导出文件成功
		return null;
	}

	/**
	 * 未满发车告警数据导出
	 * 
	 * @return
	 */
	public String exportnofullalarm() throws Exception{
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			//String sortName = request.getParameter("sortname");
			//String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			//tqcalarm.setSortname(sortName);
			//tqcalarm.setSortorder(sortOrder);//从前台获取排序规则
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setVehicle_code(vehicleCode);
			
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getNofullAlarmCount1", tqcalarm);
			allTqcAlarmList = service.getObjects("AlarmManage.getNofullAlarmInfos1", tqcalarm);
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车未满发车告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车未满发车告警出错:", e);
			return ERROR;
		}
		
		List<TqcAlarm> nofullAlarmList= new ArrayList < TqcAlarm >();
		for (int i = 0; i < allTqcAlarmList.size(); i++) {
			TqcAlarm export=new TqcAlarm();
			TqcAlarm s = (TqcAlarm) allTqcAlarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			export.setVehicle_code(s.getVehicle_code());
			export.setVehicle_ln(s.getVehicle_ln());
			export.setAlarm_type_name(s.getAlarm_type_name());
			export.setOperate_flag("0".equals(s.getOperate_flag())? "未处理" : "已处理");
			export.setAlarm_date(s.getAlarm_date());
			export.setRoute_name(s.getRoute_name());
			export.setSite_name(s.getSite_name());
			export.setStandard_cnt(s.getStandard_cnt());
			export.setReal_cnt(s.getReal_cnt());
			export.setEmpty_num(String.valueOf(Integer.parseInt("".equals(s.getStandard_cnt()) || null == s.getStandard_cnt() ? "0":s.getStandard_cnt()) - Integer.parseInt("".equals(s.getReal_cnt()) ? "0":s.getReal_cnt())));
			export.setDriver_name(tmpDriver);
//			export.setDriver_tel(s.getDriver_tel());
			nofullAlarmList.add(export);
			
			
		}
		
		
		
		
		
		
        String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "SosAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			String timestr = start_time + "——" + end_time;
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("未满发车告警("+timestr+")");
			
			if (null == nofullAlarmList || nofullAlarmList.size()<1) {
				nofullAlarmList.add(new TqcAlarm());
			}

			excelExporter.putAutoExtendSheets("exportnofullalarm", 0, nofullAlarmList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出未满发车告警写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出未满发车告警写入Excel时出错:",e);
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

		// 设置下载文件属性
		String fileName = URLEncoder.encode("未满发车告警","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename="+ fileName +"-" + name + ".xls");
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
			log.error("导出紧急告警下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出紧急告警下载时出错:",e);
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
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
			log.info("导出紧急告警结束");
		}
		// 导出文件成功
		return null;
	}
	
	/**
	 * 非站开门告警数据导出
	 * 
	 * @return
	 */
	public String exportopendooralarm() throws Exception{
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			//String sortName = request.getParameter("sortname");
			//String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			//tqcalarm.setSortname(sortName);
			//tqcalarm.setSortorder(sortOrder);   //排序规则从前台获取
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setVehicle_code(vehicleCode);
			
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}
			totalCount = service.getCount("AlarmManage.getNotSiteAlarmCount", tqcalarm);
			allTqcAlarmList = service.getObjects("AlarmManage.getNotSiteAlarmInfos", tqcalarm);
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车站外开门告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车站外开门告警出错:", e);
			return ERROR;
		}
		
		List<TqcAlarm> nofullAlarmList= new ArrayList < TqcAlarm >();
		for (int i = 0; i < allTqcAlarmList.size(); i++) {
			TqcAlarm export=new TqcAlarm();
			TqcAlarm s = (TqcAlarm) allTqcAlarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			export.setVehicle_code(s.getVehicle_code());
			export.setVehicle_ln(s.getVehicle_ln());
			export.setAlarm_type_name(s.getAlarm_type_name());
			export.setOperate_flag("0".equals(s.getOperate_flag())? "未处理" : "已处理");
			export.setRoute_name(s.getRoute_name());
			export.setAlarm_date(s.getAlarm_date());
			//export.setZone_name(SearchGisAreaByCode.getZoneNameByPosition(s.getLatitude(),s.getLongitude()));
			export.setZone_name(s.getZone_name());
			export.setUpSite(s.getUpSite());
			export.setDownSite(s.getDownSite());
//			export.setSite_name(s.getSite_name());
			export.setDriver_name(tmpDriver);
//			export.setDriver_tel(s.getDriver_tel());
			
			nofullAlarmList.add(export);
			
		}	
        String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "SosAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			String timestr = start_time + "——" + end_time;
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("站外开门告警("+timestr+")");
			
			if (null == nofullAlarmList || nofullAlarmList.size()<1) {
				nofullAlarmList.add(new TqcAlarm());
			}

			excelExporter.putAutoExtendSheets("exportopendooralarm", 0, nofullAlarmList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出站外开门告警写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出站外开门告警写入Excel时出错:",e);
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

		// 设置下载文件属性
		String fileName = URLEncoder.encode("站外开门告警","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename="+ fileName +"-" + name + ".xls");
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
			log.error("导出站外开门告警下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出站外开门告警下载时出错:",e);
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
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
			log.info("导出站外开门告警结束");
		}
		// 导出文件成功
		return null;
	}
	
	/**
	 * 迟到告警数据导出
	 * 
	 * @return
	 */
	public String exportLatealarm() throws Exception{
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			//String sortName = request.getParameter("sortname");
			//String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			//tqcalarm.setSortname(sortName);
			//tqcalarm.setSortorder(sortOrder);//排序规则从前台获取，不需要这里定义
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setVehicle_code(vehicleCode);
			
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getTqcAlarmCount", tqcalarm);
			allTqcAlarmList = service.getObjects("AlarmManage.getLateAlarmInfos", tqcalarm);
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车迟车发车告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车迟车发车告警出错:", e);
			return ERROR;
		}
		
		List<TqcAlarm> nofullAlarmList= new ArrayList < TqcAlarm >();
		for (int i = 0; i < allTqcAlarmList.size(); i++) {
			TqcAlarm export=new TqcAlarm();
			TqcAlarm s = (TqcAlarm) allTqcAlarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			export.setVehicle_code(s.getVehicle_code());
			export.setVehicle_ln(s.getVehicle_ln());
			//export.setAlarm_type_name(s.getAlarm_type_name());
			export.setAlarm_type_name("迟到告警");
			export.setOperate_flag("0".equals(s.getOperate_flag())? "未处理" : "已处理");
			export.setAlarm_date(s.getAlarm_date());
			export.setRoute_name(s.getRoute_name());
			export.setSite_name(s.getSite_name());
			export.setTerminal_time(sdfMinute.format(sdf.parse(s.getTerminal_time())));
			export.setTime_dis(String.valueOf(((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse("08:00")).getTime())/(60*1000)));
			export.setDriver_name(tmpDriver);
//			export.setDriver_tel(s.getDriver_tel());
			nofullAlarmList.add(export);	
			
		}
		
		
		
		
		
		
        String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "SosAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			String timestr = start_time + "——" + end_time;
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("迟到告警("+timestr+")");
			
			if (null == nofullAlarmList || nofullAlarmList.size()<1) {
				nofullAlarmList.add(new TqcAlarm());
			}

			excelExporter.putAutoExtendSheets("exportlatealarm", 0, nofullAlarmList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出迟到告警写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出迟到告警写入Excel时出错:",e);
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

		// 设置下载文件属性
		String fileName = URLEncoder.encode("迟到告警","UTF8");
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename="+ fileName +"-" + name + ".xls");
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
			log.error("导出迟到告警下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出迟到告警下载时出错:",e);
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
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_EXPORT_ID);
			log.info("导出迟到告警结束");
		}
		// 导出文件成功
		return null;
	}

	
	/**
	 * 优化2.0泡泡入口
	 */
	public String getDetailAlarm(){
		HttpServletRequest request = (HttpServletRequest) ActionContext
		.getContext()
		.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		UserInfo user = getCurrentUser();
		//MDC.put("modulename", "[getalarm]");
		String vin = request.getParameter("vin");
		String alarmid = request.getParameter("alarmid");
		String alarmtime = request.getParameter("alarmtime");
		String alarmtypeid = request.getParameter("alarmtypeid");
		//String lon = request.getParameter("lon");
		//String lat = request.getParameter("lat");
		sourceid = request.getParameter("sourceid");
		log.info("[vin:"+vin+";alarmid:"+alarmid+";sourceid:"+sourceid+"]泡泡弹出页面开始");
		if (null == alarmmanage) {
			alarmmanage = new AlarmManage();
		}
		if("".equals(alarmid)||"null".equals(alarmid)){
			alarmmanage.setVehicle_vin(vin);
		}else{
			alarmmanage.setVehicle_vin(vin);
			alarmmanage.setAlarm_id(alarmid);
			alarmmanage.setAlarm_time(alarmtime);
			alarmmanage.setAlarm_type_id(alarmtypeid);
			alarmmanage.setEnterprise_id(user.getOrganizationID());
			
			//alarmmanage.setLatitude(lat);
			//alarmmanage.setLongitude(lon);
		}
		log.info("[vin:"+vin+";alarmid:"+alarmid+";sourceid:"+sourceid+"]泡泡弹出页面结束");
		return SUCCESS;
	}
	
	/**
	 * 点击列表处告警，获取泡泡页告警参数和条目
	 * @return
	 */
	public AlarmManage getFirstDetail(String alarmid,String vin,String alarmtime,String alarmtypeid,String enterpriseId){
		//MDC.put("modulename", "[gerfirstalarm]");
		log.info("[vin:"+vin+";alarmtime:"+alarmtime+"]获取一条告警开始");
		AlarmManage alarmmanage = new AlarmManage();
		alarmmanage.setVehicle_vin(vin);
		alarmmanage.setAlarm_id(alarmid);
		alarmmanage.setAlarm_time(alarmtime);
		alarmmanage.setAlarm_type_id(alarmtypeid);
		alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
		alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");

		AlarmManage ret = new AlarmManage();
		int totalCount = 0;
		int ftlyCount  = 0;
		try {
//			if("10".equals(alarmtypeid) || "01".equals(alarmtypeid) || "12".equals(alarmtypeid) || "10,12".equals(alarmtypeid)){
				Map<String, String> map = new HashMap<String, String>();
			
				map.put("vehicle_vin", vin);
				map.put("enterprise_id", enterpriseId);
				map.put("start_time", DateUtil.getPreNDay(-2));
				map.put("end_time", DateUtil.getCurrentDay() + " 23:59:59");
				map.put("entId", enterpriseId);
				map.put("isValid", "0");
				ftlyCount = service.getCount("ZsptFtlyinfoNew.getStealAlarmListPageCount1", map);
//			} else {
				totalCount = service.getCount("AlarmManage.newgetAlarmCount", alarmmanage);
//			}
				totalCount += ftlyCount;
		} catch (BusinessException e) {
			log.error("获取告警总数出错",e);
		}catch(Exception e){
			log.error("获取告警总数出错",e);
		}
		//ret.setAlarm_count(totalCount+"");

		try {
			ret = quertRet(ret,alarmtypeid,alarmmanage);
			if(null == ret){
				return getRecentAlarmInfo(vin, enterpriseId, "99");
			}
		} catch (Exception e1) {
			totalCount = 0;
			e1.printStackTrace();
		}
		
		//修正点击其他按钮后alarmid值不变的问题
		if(null==ret){
			ret = new AlarmManage();
			if(totalCount!=0){
				try{
					dayList = service.getObjectsByPage("AlarmManage.newgetBusAlarmInfos", alarmmanage, 0, 1);
					if (null != dayList && dayList.size() > 0) {
						ret = dayList.get(0);
					}
				}catch(Exception e){
					log.error("POP页面二次查询单条告警出错:", e);
					totalCount=0;
				}
			}
		}
		
		//偏转
		String lon = ret.getLongitude();
		String lat = ret.getLatitude();
		gpsUtil gpsUtil = new gpsUtil();
		String point = gpsUtil.getOneXY(lon, lat);
		if (point != null && point != "") {
			String[] p = point.split(",");
			ret.setLongitude(p[0].toString());
			ret.setLatitude(p[1].toString());
		}
		//偏转结束
		
		/*
		if (null == ret) {
				alarmmanage = new AlarmManage();
				alarmmanage.setVehicle_vin(vin);
				alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
				alarmmanage.setEnd_time(DateUtil.getCurrentDay()
						+ " 23:59:59");
				if (totalCount != 0) {
					try{
						dayList = service.getObjectsByPage(
								"AlarmManage.newgetBusAlarmInfos",
								alarmmanage, 0, 1);
					}catch(Exception e){
						log.error("POP页面通过VIN查询最近一条告警出错:", e);
					}
					
					if (null != dayList && dayList.size() > 0) {
						alarmmanage = dayList.get(0);
						lon = alarmmanage.getLongitude();
						lat = alarmmanage.getLatitude();
						gpsUtil gpsUtil = new gpsUtil();
						String point = gpsUtil.getOneXY(lon, lat);
						if (point != null && point != "") {
							String[] p = point.split(",");
							alarmmanage.setLongitude(p[0].toString());
							alarmmanage.setLatitude(p[1].toString());
						}
					}
				}
				//ret=new AlarmManage();
			}*/
		ret.setAlarm_count(totalCount + "");
		if("FFFF".equals(ret.getSpeeding())){
			ret.setSpeeding("无效");
		}else if(null==ret.getSpeeding()||"".equals(ret.getSpeeding())){
			
		}else{
			ret.setSpeeding(ret.getSpeeding()+"km/h");
		}
		log.info("获取最新一条告警结束");
		return ret;
	}
	
	
	
	private AlarmManage quertRet(AlarmManage ret, String alarmtypeid,AlarmManage alarmmanage) throws Exception{
		if ("1".equals(alarmtypeid)) {
			try{
				 ret = (AlarmManage) service.getObject("AlarmManage.getweimansingle",	alarmmanage);
				 ret.setRemain_cnt(String.valueOf(Integer.parseInt(ret.getStandard_cnt()) - Integer.parseInt(ret.getReal_cnt())));
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e);
				throw(e);
				//ret=null;
			}
		} else if ("2".equals(alarmtypeid)) {
			try{
				 ret = (AlarmManage) service.getObject("AlarmManage.gettiqiansingle",	alarmmanage);
				 ret.setEarly_time(String.valueOf((ret.getSend_time() == null || "".equals(ret.getSend_time().trim()) ? "无"  : ((sdfMinute.parse(ret.getSend_time())).getTime() - (sdfMinute.parse(ret.getTerminal_time().substring(11,16))).getTime())/(60*1000))));
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e);
				throw(e);
				//ret=null;
			}
		} else if ("3".equals(alarmtypeid)) {
			try{
				 ret = (AlarmManage) service.getObject("AlarmManage.getfeizhansingle",	alarmmanage);
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e);
				throw(e);
				//ret=null;
			}
		} else if ("4".equals(alarmtypeid)) {
			try{
				ret = (AlarmManage) service.getObject("AlarmManage.getchidaosingle2",	alarmmanage);//第二工厂
				if(ret==null){
					ret = (AlarmManage) service.getObject("AlarmManage.getchidaosingle1",	alarmmanage);//第二工厂
				}
				if(ret==null){
					ret = (AlarmManage) service.getObject("AlarmManage.getchidaosingle",	alarmmanage);//其他工厂
				}
				 //ret = (AlarmManage) service.getObject("AlarmManage.getchidaosingle",	alarmmanage);
				
				ret.setLater_config(ret.getLater_config() == null || "".equals(ret.getLater_config()) ? "08:00":ret.getLater_config());
				ret.setLate_time(String.valueOf(ret.getTerminal_time() == null || "".equals(ret.getTerminal_time()) ? "无"  : Math.abs(((sdfMinute.parse(ret.getLater_config())).getTime() - (sdfMinute.parse(ret.getTerminal_time().substring(11,16))).getTime())/(60*1000))));
				
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e); 
				throw(e);
				//ret=null;
			}
		} else if ("5".equals(alarmtypeid)) {
			try{
				 ret = (AlarmManage) service.getObject("AlarmManage.getyanchisingle",	alarmmanage);
				 ret.setDelay_time(String.valueOf(ret.getSend_time() == null || "".equals(ret.getSend_time().trim()) ? "无"  : Math.abs(((sdfMinute.parse(ret.getSend_time())).getTime() - (sdfMinute.parse(ret.getTerminal_time().substring(11,16))).getTime())/(60*1000))));
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e);
				throw(e);
				//ret=null;
			}
		} else if("010".equals(alarmtypeid) || "001".equals(alarmtypeid) || "12".equals(alarmtypeid) || "010,12".equals(alarmtypeid)){
			try{
				ret = (AlarmManage) service.getObject("AlarmManage.newgettqcalarmbyidandtimeForOil", alarmmanage);
				if(ret.getZonename() == null || "".equals(ret.getZonename())){
					ret.setZonename("定位无效 ");
				}
			} catch (Exception e){
				log.error("POP页面查询单条告警出错:",e);
				throw(e);
			}
		} else {
			try{
				Object o = service.getObject("AlarmManage.newgetalarmbyidandtime", alarmmanage);
				if(null != o){
					ret = (AlarmManage)o;
				}else{
					return null;
				}
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e);
				throw(e);
				//ret=null;
			}
		}
		return ret;
	}

	/**
	 * 2.0 泡泡入口bak
	 * 
	 * @return
	 */
	public String getDetailAlarmbak() {
		final String browseTitle = getText("alarmmanage.browse.title");
		//MDC.put("loginid", getloginuuid());
		//MDC.put("modulename", "[getalarm]");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			String vin = request.getParameter("vin");
			String alarmid = request.getParameter("alarmid");
			String alarmtime = request.getParameter("alarmtime");
			String alarmtypeid = request.getParameter("alarmtypeid");
			String lon = request.getParameter("lon");
			String lat = request.getParameter("lat");
			sourceid = request.getParameter("sourceid");
			log.info("[vin:"+vin+";alarmid:"+alarmid+";sourceid:"+sourceid+"]泡泡弹出页面开始");
			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}

			alarmmanage.setVehicle_vin(vin);
			alarmmanage.setAlarm_id(alarmid);
			alarmmanage.setAlarm_time(alarmtime);
			if("null".equals(alarmtime)){
				alarmid="";
			}
			// alarmmanage.setAlarm_type_id(alarmtype);

			alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
			alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			totalCount = service.getCount("AlarmManage.newgetAlarmCount",
					alarmmanage);
			if (null == alarmid || "null".equals(alarmid) || "".equals(alarmid)) {
				if (totalCount != 0) {
					dayList = service.getObjectsByPage(
							"AlarmManage.newgetBusAlarmInfos", alarmmanage, 0,
							1);
					if (null != dayList && dayList.size() > 0) {
						alarmmanage = dayList.get(0);
						alarmmanage.setAlarm_count(totalCount + "");
					} else {
						alarmmanage.setAlarm_count(totalCount + "");
					}
				} else {
					alarmmanage.setAlarm_count(totalCount + "");
					alarmmanage.setAlarm_type_name("");
					alarmmanage.setAlarm_time("");
				}
			} else {
				if ("73".equals(alarmtypeid) || "74".equals(alarmtypeid)
						|| "79".equals(alarmtypeid) || "80".equals(alarmtypeid)) {
					alarmmanage = (AlarmManage) service.getObject(
							"AlarmManage.newgetstualarmbyidandtime",
							alarmmanage);
					if (null == alarmmanage) {
						alarmmanage = new AlarmManage();
						alarmmanage.setVehicle_vin(vin);
						alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
						alarmmanage.setEnd_time(DateUtil.getCurrentDay()
								+ " 23:59:59");
						if (totalCount != 0) {
							dayList = service.getObjectsByPage(
									"AlarmManage.newgetBusAlarmInfos",
									alarmmanage, 0, 1);
							if (null != dayList && dayList.size() > 0) {
								alarmmanage = dayList.get(0);
								lon = alarmmanage.getLongitude();
								lat = alarmmanage.getLatitude();
								gpsUtil gpsUtil = new gpsUtil();
								String point = gpsUtil.getOneXY(lon, lat);
								if (point != null && point != "") {
									String[] p = point.split(",");
									alarmmanage.setLongitude(p[0].toString());
									alarmmanage.setLatitude(p[1].toString());
								}
							}
						}
					}else{
						lon = alarmmanage.getLongitude();
						lat = alarmmanage.getLatitude();
						gpsUtil gpsUtil = new gpsUtil();
						String point = gpsUtil.getOneXY(lon, lat);
						if (point != null && point != "") {
							String[] p = point.split(",");
							alarmmanage.setLongitude(p[0].toString());
							alarmmanage.setLatitude(p[1].toString());
						}
					}
				} else {
					alarmmanage = (AlarmManage) service.getObject(
							"AlarmManage.newgetalarmbyidandtime", alarmmanage);
					if (null == alarmmanage) {
						alarmmanage = new AlarmManage();
						alarmmanage.setVehicle_vin(vin);
						alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
						alarmmanage.setEnd_time(DateUtil.getCurrentDay()
								+ " 23:59:59");
						if (totalCount != 0) {
							dayList = service.getObjectsByPage(
									"AlarmManage.newgetBusAlarmInfos",
									alarmmanage, 0, 1);
							if (null != dayList && dayList.size() > 0) {
								alarmmanage = dayList.get(0);
								lon = alarmmanage.getLongitude();
								lat = alarmmanage.getLatitude();
								gpsUtil gpsUtil = new gpsUtil();
								String point = gpsUtil.getOneXY(lon, lat);
								if (point != null && point != "") {
									String[] p = point.split(",");
									alarmmanage.setLongitude(p[0].toString());
									alarmmanage.setLatitude(p[1].toString());
								}
							}
						}
					} else {
						alarmmanage.setLatitude(lat);
						alarmmanage.setLongitude(lon);
					}

				}
				alarmmanage.setAlarm_count(totalCount + "");
			}
			if("FFFF".equals(alarmmanage.getSpeeding())){
				alarmmanage.setSpeeding("无效");
			}else if(null==alarmmanage.getSpeeding()||"".equals(alarmmanage.getSpeeding())){
				
			}else{
				alarmmanage.setSpeeding(alarmmanage.getSpeeding()+"km/h");
			}

			log.info("泡泡弹出页面结束");

			/*
			 * // 设置操作描述 this.addOperationLog(formatLog(browseTitle, null)); //
			 * 设置操作类型 this.setOperationType(Constants.SELECT); // 设置所属应用系统
			 * this.setApplyId(Constants.XC_P_CODE); // 设置所属模块
			 * this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			 */
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("POP页面查询单条告警出错:", e);
			return ERROR;
		}catch(Exception e ){
			addActionError(getText(e.getMessage()));
			log.error("POP页面查询单条告警出错:", e);
			return ERROR;
		}
		return SUCCESS;

	}

	private Map<String, String> tabmap = new HashMap<String, String>();

	public Map<String, String> getTabmap() {
		return tabmap;
	}

	public void setTabmap(Map<String, String> tabmap) {
		this.tabmap = tabmap;
	}

	private String tabflag;

	public String getTabflag() {
		return tabflag;
	}

	public void setTabflag(String tabflag) {
		this.tabflag = tabflag;
	}

	private String alarmtabname;

	public String getAlarmtabname() {
		return alarmtabname;
	}

	public void setAlarmtabname(String alarmtabname) {
		this.alarmtabname = alarmtabname;
	}

	private String year;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String newalarm_type_id = "'40'";
	
	public String newveh_ln;

	public String getNewveh_ln() {
        return newveh_ln;
    }

    public void setNewveh_ln(String newveh_ln) {
        this.newveh_ln = newveh_ln;
    }

    /**
	 * 2.0 more rukou
	 * 
	 * @return
	 */
	public String newmoreAlarm() {
	    
	    if(null!=vin&&!"".equals(vin)&&!"null".equals(vin)){
	        try{
	            newveh_ln=(String)service.getObject("AlarmManage.newgetvehln", vin);
	        }catch(Exception e){
	            log.error("更多入口查询车牌出错:",e);
	        }
	    }
		/*if ("'09','10','13','25','26','64','65','66','67','68','69','70','71'".equals(newalarm_type_id)) {
			alarmtabname = "车辆故障";
			tabflag = "3";
		} else */
	    if("".equals(newalarm_type_id) || "'40'".equals(newalarm_type_id)) {
			alarmtabname = "紧急告警";
			tabflag = "1";
	    } else if ("'32'".equals(newalarm_type_id)) {
	    	alarmtabname = "超速告警";
	    	tabflag = "2";
		} else if ("'2'".equals(newalarm_type_id) || "'5'".equals(newalarm_type_id)) {
			alarmtabname = "非时发车告警";
			tabflag = "3";
		} else if ("'1'".equals(newalarm_type_id)) {
			alarmtabname = "未满发车告警";
			tabflag = "4";
		} else if ("'3'".equals(newalarm_type_id)) {
			alarmtabname = "站外开门告警";
			tabflag = "5";
		} else if ("'4'".equals(newalarm_type_id)) {
			alarmtabname = "迟到告警";
			tabflag = "6";
		} else if("'010','001'".equals(newalarm_type_id)||"'001'".equals(newalarm_type_id) || "'010'".equals(newalarm_type_id)){
			alarmtabname = "油量告警";
			tabflag = "7";
		} 
		return SUCCESS;
	}

	public String getNewalarm_type_id() {
		return newalarm_type_id;
	}

	public void setNewalarm_type_id(String newalarm_type_id) {
		this.newalarm_type_id = newalarm_type_id;
	}

	public String newotherAlarm() {
		start_time = DateUtil.getPreNDay(-2);
		end_time = DateUtil.getCurrentDay();
		if ("'40'".equals(alarmmanage.getAlarm_type_id())) {
			alarmtypemap = Constants.ALARM_TAB1_TYPE_MAP;
		} else if ("'32'".equals(alarmmanage.getAlarm_type_id())) {
			alarmtypemap = Constants.ALARM_TAB2_TYPE_MAP;
		} 
//		else {
//			alarmtypemap = Constants.ALARM_TAB3_TYPE_MAP;
//		}
		alarm_type_id = alarmmanage.getAlarm_type_id();
//		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP_NEW;
		deal_flag = "0";
		year = DateUtil.getYear();
		return SUCCESS;
	}

	public String newsosAlarm() {
		start_time = DateUtil.getPreNDay(-2);
		end_time = DateUtil.getCurrentDay();
		alarmtypemap = Constants.ALARM_TAB1_TYPE_MAP;
		alarm_type_id = alarmmanage.getAlarm_type_id();
		if (null == alarm_type_id || "".equals(alarm_type_id)) {
			alarm_type_id = "'40'";
		}
//		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP_NEW;
		deal_flag = "0";
		year = DateUtil.getYear();
		return SUCCESS;
	}

	/**
	 * 2.0 通勤车告警列表入口
	 * 
	 * @return
	 */
	public String newtqcAlarm() {
		HttpServletRequest request = (HttpServletRequest) ActionContext
		.getContext()
		.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String flag = request.getParameter("flag");
		start_time = DateUtil.getPreNDay(-2);
		end_time = DateUtil.getCurrentDay();
		deal_flag = "0";
		year = DateUtil.getYear();
		if(flag.equals("4")){
			alarmtypemap = Constants.ALARM_TAB6_TYPE_MAP_NEW;
			alarm_type_id = alarmmanage.getAlarm_type_id();
			if (null == alarm_type_id || "".equals(alarm_type_id)) {
				alarm_type_id = "'4'";
			}
//			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP_NEW;
		} else if(flag.equals("5")){
			alarmtypemap = Constants.ALARM_TAB3_TYPE_MAP_NEW;
			alarm_type_id = alarmmanage.getAlarm_type_id();
			if (null == alarm_type_id || "".equals(alarm_type_id)) {
				alarm_type_id = "'2'";
			}
//			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP_NEW;
		} else if(flag.equals("6")){
			alarmtypemap = Constants.ALARM_TAB5_TYPE_MAP_NEW;
			alarm_type_id = alarmmanage.getAlarm_type_id();
			if (null == alarm_type_id || "".equals(alarm_type_id)) {
				alarm_type_id = "'3'";
			}
//			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP_NEW;
		} else if(flag.equals("7")){
			alarmtypemap = Constants.ALARM_TAB4_TYPE_MAP_NEW;
			alarm_type_id = alarmmanage.getAlarm_type_id();
			if (null == alarm_type_id || "".equals(alarm_type_id)) {
				alarm_type_id = "'1'";
			}
//			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP_NEW;
		} else if(flag.equals("8")){
			alarmtypemap = Constants.ALARM_TAB8_TYPE_MAP_NEW;
			alarm_type_id = alarmmanage.getAlarm_type_id();
			if (null == alarm_type_id || "".equals(alarm_type_id)) {
				alarm_type_id = "";
			}
//			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
			dealflagmap = Constants.ALARM_DEAL_FLAG_MAP_NEW;
		}
//		start_time = DateUtil.getPreNDay(-2);
//		end_time = DateUtil.getCurrentDay();
//		alarmtypemap = Constants.STU_ALARM_TYPE_MAP;
//		alarm_type_id = alarmmanage.getAlarm_type_id();
//		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
//		deal_flag = "0";
//		year = DateUtil.getYear();
		return SUCCESS;
	}

	/**
	 * 2.0通勤车告警处理（迟到）
	 */
	public String newopenLateAlarmList() {
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			// 增加中文排序
			if (sortName.equals("vehicle_ln") || sortName.equals("route_id")|| sortName.equals("trip_id")) {
				sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			tqcalarm.setSortname(sortName);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setVehicle_code(SearchUtil.formatSpecialChar(vehicleCode));
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getTqcAlarmCount", tqcalarm);
			allTqcAlarmList = service.getObjectsByPage("AlarmManage.getLateAlarmInfos", tqcalarm, (Integer.parseInt(pageIndex) - 1)	* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			this.map = getLateOpenMorePagination(allTqcAlarmList, totalCount, pageIndex, rpNum);
			log.info("通勤车迟到告警转换MAP结束");
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车迟到告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车迟到告警出错:", e);
			return ERROR;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车迟到告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 通勤车告警转换MAP（迟到）
	 * 
	 * @param alarmList
	 * @param totalCountDay
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */	
	public Map getLateOpenMorePagination(List alarmList, int totalCountDay, String pageIndex, String rpNum) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		String later_time = "";
		for (int i = 0; i < alarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) alarmList.get(i);

			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			later_time = s.getLate_time();
			if(later_time == null || later_time.length() < 1) {
				later_time = "08:00";
			} else {
				later_time = later_time.substring(0,5);
			}
			
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] {
					s.getLatitude(),s.getLongitude(),4,s.getVehicle_vin(),s.getOperate_flag(),s.getAlarm_id(),
					s.getVehicle_code(),
					s.getVehicle_ln(),
					"迟到告警",
					"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getAlarm_date(),
					s.getRoute_name(),
					s.getSite_name(),
					//sdfMinute.format(sdf.parse(s.getTerminal_time())),
					s.getTerminal_time().toString(),
					((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse(later_time)).getTime())/(60*1000),
					tmpDriver,
					s.getDriver_tel(),
					s.getAlarm_id(),
					s.getOperate_name(),
					s.getOperate_desc()
					});
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}    
	
	/**
	 * 通勤车告警转换MAP（非时发车）
	 * 
	 * @param alarmList
	 * @param totalCountDay
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */	
	public Map getNofullOpenMorePagination(List alarmList, int totalCountDay, String pageIndex, String rpNum) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < alarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) alarmList.get(i);

			String lessTime = "";
			if("5".equals(s.getOperate_flag())){
				lessTime = s.getSend_time() == null || s.getSend_time().replace(" ", "").length() == 0 ||s.getTerminal_time().replace(" ","").length() == 0 ||  "".equals(s.getSend_time().trim()) ? "无"  : String.valueOf(Math.abs(((sdfMinute.parse(s.getSend_time())).getTime() - (sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime())/(60*1000)));
			} else {
				lessTime = s.getTerminal_time() == null || s.getTerminal_time().replace(" ", "").length() == 0  || s.getSend_time().replace(" ", "").length() == 0 || "".equals(s.getTerminal_time().trim()) ? "无" : String.valueOf(Math.abs(((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse(s.getSend_time())).getTime())/(60*1000)));
			}
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] {
					s.getLatitude(),s.getLongitude(),s.getAlarm_type(),s.getVehicle_vin(),s.getOperate_flag(),s.getAlarm_id(),
					s.getVehicle_code(),
					s.getVehicle_ln(),
					s.getAlarm_type_name(),"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getRoute_name(),
					s.getAlarm_date(),
					s.getSite_name(),
					s.getSend_time() == null || "".equals(s.getSend_time().trim()) ? "无" :s.getTerminal_time().substring(0,10) + " " + s.getSend_time() + ":00",
					s.getTerminal_time(),
					//s.getSend_time() == null || "".equals(s.getSend_time().trim()) ? "无"  : ((sdfMinute.parse(s.getTerminal_time().substring(11,16))).getTime() - (sdfMinute.parse(s.getSend_time())).getTime())/(60*1000),
					lessTime,
					Integer.parseInt("".equals(s.getStandard_cnt()) || null == s.getStandard_cnt()? "0":s.getStandard_cnt()) - Integer.parseInt("".equals(s.getReal_cnt()) ? "0":s.getReal_cnt()),
					tmpDriver,
					s.getDriver_tel(),
					s.getAlarm_id(),
					s.getOperate_name(),
					s.getOperate_desc()
					});
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}   	
	/**
	 * 通勤车告警转换MAP（未满发车）
	 * 
	 * @param alarmList
	 * @param totalCountDay
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */	
	public Map getNofullOpenMorePagination1(List alarmList, int totalCountDay, String pageIndex, String rpNum) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < alarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) alarmList.get(i);

			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}
			
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] {
					s.getLatitude(),s.getLongitude(),1,s.getVehicle_vin(),s.getOperate_flag(),s.getAlarm_id(),
					s.getVehicle_code(),
					s.getVehicle_ln(),
					s.getAlarm_type_name(),
					"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getAlarm_date(),
					s.getRoute_name(),
					s.getSite_name(),
					"".equals(s.getStandard_cnt()) ? "0":s.getStandard_cnt(),
					"".equals(s.getReal_cnt()) ? "0":s.getReal_cnt(),
				    Integer.parseInt("".equals(s.getStandard_cnt())|| null == s.getStandard_cnt() ? "0":s.getStandard_cnt()) - 
				    Integer.parseInt("".equals(s.getReal_cnt()) || null == s.getReal_cnt() ? "0":s.getReal_cnt()),
					tmpDriver,
					s.getDriver_tel(),
					s.getAlarm_id(),
					s.getOperate_name(),
					s.getOperate_desc()
					});
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}    
	
	/**
	 * 通勤车告警转换MAP（站外开门）
	 * 
	 * @param alarmList
	 * @param totalCountDay
	 * @param pageIndex
	 * @param rpNum
	 * @return
	 */	
	public Map getNotSiteOpenMorePagination(List alarmList, int totalCountDay, String pageIndex, String rpNum) throws Exception{
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < alarmList.size(); i++) {

			TqcAlarm s = (TqcAlarm) alarmList.get(i);
			
			String tmpDriver = "";
			if(null == s.getDriver_name() || s.getDriver_name().length() == 0){
				tmpDriver = "未登录";
			} else {
				tmpDriver = s.getDriver_name();
			}

			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getAlarm_id());
			cellMap.put("cell", new Object[] {
					s.getLatitude(),s.getLongitude(),3,s.getVehicle_vin(),s.getOperate_flag(),s.getAlarm_id(),
					s.getVehicle_code(),
					s.getVehicle_ln(),
					s.getAlarm_type_name(),
					"0".equals(s.getOperate_flag()) ? "未处理" : "已处理",
					s.getRoute_name(),
					s.getAlarm_date(),
//					earchGisAreaByCode.getZoneNameByPosition(s.getLongitude(),s.getLatitude()),
					(s.getZone_name()==null || "".equals(s.getZone_name())) ? "定位无效":s.getZone_name(),
					s.getUpSite(),
					s.getSite_name(), //最近站点。需求更改后不再使用。
					s.getDownSite(),
					tmpDriver,
					s.getDriver_tel(),
					s.getAlarm_id(),
					s.getOperate_name(),
					s.getOperate_desc()
					});
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}    
	/**
	 * 2.0通勤车告警处理（非时发车）
	 */
	public String newopenNofullAlarmList() {
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			// 增加中文排序
			if (sortName.equals("vehicle_ln") || sortName.equals("trip_id")) {
				sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			tqcalarm.setSortname(sortName);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setAlarm_type(this.alarm_type_id);
			tqcalarm.setVehicle_code(SearchUtil.formatSpecialChar(vehicleCode));
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getNofullAlarmCount", tqcalarm);
			allTqcAlarmList = service.getObjectsByPage("AlarmManage.getNofullAlarmInfos", tqcalarm, (Integer.parseInt(pageIndex) - 1)	* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			this.map = getNofullOpenMorePagination(allTqcAlarmList, totalCount, pageIndex, rpNum);
			log.info("通勤车未满发车告警转换MAP结束");
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车未满发车告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车未满发车告警出错:", e);
			return ERROR;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车未满发车告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}		
	/**
	 * 2.0通勤车告警处理（未满发车）
	 */
	public String newopenNofullAlarmList1() {
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			// 增加中文排序
			if (sortName.equals("vehicle_ln") || sortName.equals("trip_id")) {
				sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			tqcalarm.setSortname(sortName);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setVehicle_code(SearchUtil.formatSpecialChar(vehicleCode));
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getNofullAlarmCount1", tqcalarm);
			allTqcAlarmList = service.getObjectsByPage("AlarmManage.getNofullAlarmInfos1", tqcalarm, (Integer.parseInt(pageIndex) - 1)	* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			this.map = getNofullOpenMorePagination1(allTqcAlarmList, totalCount, pageIndex, rpNum);
			log.info("通勤车未满发车告警转换MAP结束");
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车未满发车告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车未满发车告警出错:", e);
			return ERROR;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车未满发车告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}	
	
	/**
	 * 2.0通勤车告警处理（站外开门）
	 */
	public String newopenNotSiteAlarmList() {
		final String browseTitle = getText("tqcalarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:" + start_time + ";end_time:" + end_time + ";alarm_type_id:" + 4 +";chooseorgid:"+chooseorgid+"]通勤车告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			if (null == tqcalarm) {
				tqcalarm = new TqcAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				tqcalarm.setOrganization_id(user.getOrganizationID());
			} else {
				tqcalarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			String vehicleCode = request.getParameter("vehicleCode");
			// 增加中文排序
			if (sortName.equals("vehicle_ln") || sortName.equals("trip_id")) {
				sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			tqcalarm.setSortname(sortName);
			tqcalarm.setSortorder(sortOrder);
			tqcalarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			tqcalarm.setStart_time(start_time);
			tqcalarm.setOperate_flag(deal_flag);
			tqcalarm.setVehicle_code(SearchUtil.formatSpecialChar(vehicleCode));
			
			if (null != end_time && !end_time.equals("")) {
				tqcalarm.setEnd_time(end_time + " 23:59:59");
			}

//			tqcalarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getNotSiteAlarmCount", tqcalarm);
			allTqcAlarmList = service.getObjectsByPage("AlarmManage.getNotSiteAlarmInfos", tqcalarm, (Integer.parseInt(pageIndex) - 1)	* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			this.map = getNotSiteOpenMorePagination(allTqcAlarmList, totalCount, pageIndex, rpNum);
			log.info("通勤车站外开门告警转换MAP结束");
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("查询通勤车站外开门告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车站外开门告警出错:", e);
			return ERROR;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			log.error("查询通勤车站外开门告警出错:", e);
			return ERROR;
		}
		return SUCCESS;
	}		
	/**
	 * 格式化日志信息
	 * 
	 * @param desc
	 * @param Object
	 * @return
	 */
	protected String formatLog(String desc, AlarmManage om) {
		StringBuffer sb = new StringBuffer();
		if (null != desc) {
			sb.append(desc);
		}
		if (null != om) {
			if (null != om.getAlarm_id()) {
				OperateLogFormator.format(sb, "refuel_id", om.getAlarm_id());
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
		return (UserInfo) ActionContext.getContext().getSession().get(
				Constants.USER_SESSION_KEY);
	}
	
	/*
	 * public Map getMap() { return map; } public void setMap(Map map) {
	 * this.map = map; } public Map getPagination(List dayList, int
	 * totalCountDay, String pageIndex) { List mapList = new ArrayList(); Map
	 * mapData = new LinkedHashMap(); for (int i = 0; i < dayList.size(); i++) {
	 * AlarmManage s = (AlarmManage) dayList.get(i); Map cellMap = new
	 * LinkedHashMap(); cellMap.put("id", s.getAlarm_id()); cellMap.put("cell",
	 * new Object[] {s.getVehicle_ln(), s.getVehicle_code(), s.getRoute_name(),
	 * s.getDriver_name(), s.getAlarm_type_name(), s.getSpeeding(),
	 * s.getAlarm_time(), s.getAlarm_end_time(), s.getKeeptime(),
	 * s.getDeal_flag(), s.getLongitude(), s.getLatitude(), s.getVehicle_vin()
	 * }); log.debug("============" + s.getVehicle_ln() + "," +
	 * s.getVehicle_code() + "," + s.getRoute_name() + "," + s.getDriver_name()
	 * + "," + s.getAlarm_type_name() + "," + s.getSpeeding() + "," +
	 * s.getAlarm_time() + "," + s.getAlarm_end_time() + "," + s.getKeeptime() +
	 * "," + s.getDeal_flag() + "," + s.getLongitude() + "," + s.getLatitude() +
	 * "," + s.getVehicle_vin()); mapList.add(cellMap); } mapData.put("page",
	 * pageIndex);// 从前台获取当前第page页 mapData.put("total", totalCountDay);//
	 * 从数据库获取总记录数 mapData.put("rows", mapList); if (null == alarmmanage) {
	 * alarmmanage = new AlarmManage(); } return mapData; }
	 */
	public String getAlarm_type_id() {
		return alarm_type_id;
	}
	
	public void setAlarm_type_id(String alarm_type_id) {
		this.alarm_type_id = alarm_type_id;
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
	
	/*
	 * public String getS_vehicle_ln() { return s_vehicle_ln; } public void
	 * setS_vehicle_ln(String s_vehicle_ln) { this.s_vehicle_ln = s_vehicle_ln;
	 * }
	 */
	
	/**
	 * 2.0 点击处理后执行的方法
	 * 获取最新一条告警。
	 */
	public AlarmManage getRecentAlarmInfo(String vin,String enterprise_id, String last_style) {
		//MDC.put("modulename", "[gerrecentalarm]");
		log.info("[vin:"+vin+"]获取最新一条告警开始");
		AlarmManage alarmmanage = new AlarmManage();
		alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
		alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
		alarmmanage.setVehicle_vin(vin);
		alarmmanage.setSortname("alarm_time");
		alarmmanage.setSortorder("desc");
		
		AlarmManage ret = new AlarmManage();
		int totalCount = 0;
		int ftlyCount = 0;
		Map<String, String> map = new HashMap<String, String>();
		try {
			totalCount = service.getCount("AlarmManage.newgetAlarmCount", alarmmanage);
			map.put("vehicle_vin", vin);
			map.put("enterprise_id", enterprise_id);
			map.put("sortname", "report_time");
			map.put("sortorder", "desc");
			map.put("start_time", DateUtil.getPreNDay(-2));
			map.put("end_time", DateUtil.getCurrentDay() + " 23:59:59");
			map.put("entId", enterprise_id);
			map.put("isValid", "0");
			ftlyCount = service.getCount("ZsptFtlyinfoNew.getStealAlarmListPageCount1", map);
			totalCount += ftlyCount;
		} catch (BusinessException e) {
			log.error("获取告警总数出错",e);
		}catch(Exception e){
			log.error("获取告警总数出错",e);
		}
		if (totalCount != 0) {
			try {
				String alarmid = "";
				String alarmtypeid = "";
				List<AlarmManage> list = service.getObjectsByPage("AlarmManage.newgetBusAlarmInfos", alarmmanage, 0, 1);
				List<ZsptFtlyInfo> oilAlarmsList = service.getObjectsByPage("ZsptFtlyinfoNew.getStealAlarmListPage1", map, 0, 1); 
				alarmmanage = new AlarmManage();
				if(null != list && list.size()>0 && null != oilAlarmsList && oilAlarmsList.size()>0){
					ret = list.get(0);
					ZsptFtlyInfo z = oilAlarmsList.get(0);
					if(sdf.parse(ret.getAlarm_time()).after(sdf.parse(z.getReportTimeString()))){
						alarmmanage.setAlarm_type_id(ret.getAlarm_type_id());
						alarmmanage.setAlarm_id(ret.getAlarm_id());
						alarmid = ret.getAlarm_id();
						alarmtypeid = ret.getAlarm_type_id();
					}else{
						alarmmanage.setAlarm_id(z.getFtylyIdNum());
						alarmmanage.setAlarm_type_id(z.getOilboxState());
						alarmid = z.getFtylyIdNum();
						alarmtypeid = z.getOilboxState();
					}
				}else if(null != list && list.size()>0){
					ret = list.get(0);
					alarmmanage.setAlarm_type_id(ret.getAlarm_type_id());
					alarmmanage.setAlarm_id(ret.getAlarm_id());
					alarmid = ret.getAlarm_id();
					alarmtypeid = ret.getAlarm_type_id();
				}else if(null != oilAlarmsList && oilAlarmsList.size()>0){
					ZsptFtlyInfo z = oilAlarmsList.get(0);
					alarmmanage.setAlarm_id(z.getFtylyIdNum());
					alarmmanage.setAlarm_type_id(z.getOilboxState());
					alarmid = z.getFtylyIdNum();
					alarmtypeid = z.getOilboxState();
					ret.setAlarm_address(z.getZonename());
					ret.setAlarm_id(z.getOilboxState());
					ret.setAdd_oill(z.getAddOill());
					ret.setAlarm_count(z.getAlarmCount());
					ret.setSpeeding(z.getSpeeding());
				}
				if(!"".equals(ret.getAlarm_id())&& null != ret.getAlarm_id()){
					ret = quertRet(ret,alarmtypeid,alarmmanage);
					ret.setAlarm_type_id(alarmtypeid);
					ret.setAlarm_count(totalCount + "");
					
					//偏转
					String lon = ret.getLongitude();
					String lat = ret.getLatitude();
					gpsUtil gpsUtil = new gpsUtil();
					String point = gpsUtil.getOneXY(lon, lat);
					if (point != null && point != "") {
						String[] p = point.split(",");
						ret.setLongitude(p[0].toString());
						ret.setLatitude(p[1].toString());
					}
					
					if("FFFF".equals(ret.getSpeeding())){
						ret.setSpeeding("无效");
					}else if(null==ret.getSpeeding()||"".equals(ret.getSpeeding())){
						
					}else{
						ret.setSpeeding(ret.getSpeeding()+"km/h");
					}
				}
			} catch (Exception e) {
				log.error("获取最新一条告警出错",e);
			}
		} else {
		}
		log.info("获取最新一条告警结束");
		return ret;
	}
	
	/**
	 * 获得当前操作LOGID
	 * @return
	 */
	private String getlogid() {
		return (String) ActionContext.getContext().getSession().get(
				Constants.LOG_USE_ID);
	}
	
	
}
