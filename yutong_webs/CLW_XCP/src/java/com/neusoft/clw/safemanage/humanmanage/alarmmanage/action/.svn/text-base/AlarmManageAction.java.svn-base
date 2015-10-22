package com.neusoft.clw.safemanage.humanmanage.alarmmanage.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

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
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.PhotoRoute;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.StuAlarm;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
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

    private String mouldid;

	private String sourceid;

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	

	public String getMouldid() {
		return mouldid;
	}

	public void setMouldid(String mouldid) {
		this.mouldid = mouldid;
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

	private List<StuAlarm> stuAlarmList;

	private List<StuAlarm> allStuAlarmList;

	public List<StuAlarm> getAllStuAlarmList() {
		return allStuAlarmList;
	}

	public void setAllStuAlarmList(List<StuAlarm> allStuAlarmList) {
		this.allStuAlarmList = allStuAlarmList;
	}

	public StuAlarm getStualarm() {
		return stualarm;
	}

	public void setStualarm(StuAlarm stualarm) {
		this.stualarm = stualarm;
	}

	public List<StuAlarm> getStuAlarmList() {
		return stuAlarmList;
	}

	public void setStuAlarmList(List<StuAlarm> stuAlarmList) {
		this.stuAlarmList = stuAlarmList;
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
		gpsUtil gsputil = new gpsUtil();
		//dayList = gsputil.getAlarmPosition(dayList);
		dayList = gsputil.getAlarmAddress(dayList);
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
					s.getVehicle_ln(), s.getAlarm_type_name(),
					s.getAlarm_time(), s.getDriver_name(), s.getSichen_name(),
					s.getStu_num(),"", s.getAlarm_address() });
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
				alarmtypeid = "40,72";
			}
			alarmmanage.setAlarm_type_id(alarmtypeid);

			messageList = service.getObjectsByPage(
					"AlarmManage.getTAbVehAlarmInfos", alarmmanage, 0, 3);
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
					s.getLatitude(), s.getVehicle_vin(), s.getDeal_flag(),
					s.getAlarm_type_id(), s.getDirection(), "",
					s.getVehicle_ln(), s.getAlarm_type_name(),
					s.getAlarm_time(), "", s.getSpeeding(),
					s.getEngine_rotate_speed() , s.getMileage(),
					s.getAlarm_address(), "" });
			mapList.add(cellMap);

		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", 5);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}

	/**
	 * 获取TAB页学生告警
	 * 
	 * @return
	 */
	public String getTabStudentAlarm() {
		//MDC.put("modulename", "[tabstualarm]");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String alarmtypeid = request.getParameter("alarmtypeid");
		log.info("获取学生TAB数据开始:" + alarmtypeid);
		try {
			//UserInfo user = getCurrentUser();
			if (null == stualarm) {
				stualarm = new StuAlarm();
			}
			stualarm.setOrganization_id(orgid);
			stualarm.setAlarm_type_id(alarmtypeid);
			stualarm.setStart_time(DateUtil.getPreNDay(-2));
			stualarm.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
			stuAlarmList = service.getObjectsByPage(
					"AlarmManage.getTAbStuAlarmInfos", stualarm, 0, 3);
			map = getStuTabPagination(stuAlarmList);
		}
		catch (BusinessException e) {
			// TODO Auto-generated catch block
			log.error("获取学生TAB数据异常:", e);
			return ERROR;
			// stuAlarmList = null;
		} catch (Exception e) {
			log.error("获取学生TAB数据异常:", e);
			return ERROR;
		}

		log.info("获取学生TAB数据结束");
		return SUCCESS;
	}

	/**
	 * 获取学生TAB页的MAP
	 * 
	 * @return
	 */
	public Map getStuTabPagination(List stualarmList) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < stualarmList.size(); i++) {

			StuAlarm s = (StuAlarm) stualarmList.get(i);
			// s.setOperate_flag(Constants.ALARM_SYS_MAP.get(s.getOperate_flag()));
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getId());
			cellMap.put("cell", new Object[] { "", s.getVehicle_ln(),
					s.getRoute_name(), s.getSite_name(), s.getStu_name(),
					s.getStudent_code(), s.getStu_school(), s.getStu_class(),
					s.getAlarm_type_name(), s.getAlarm_type_id(),
					s.getOperate_flag(), s.getTerminal_time(), "",
					s.getLongitude(), s.getLatitude(), s.getVehicle_vin(),
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
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			if (null == chooseorgid || "".equals(chooseorgid)
					|| "undefined".equals(chooseorgid)) {
				alarmmanage.setOrganization_id(user.getOrganizationID());
			} else {
				alarmmanage.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			// 增加中文排序
			if (sortName.equals("vehicle_ln") || sortName.equals("driver_name")
					|| sortName.equals("sichen_name")) {
				sortName = "nlssort(" + sortName
						+ ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			alarmmanage.setSortname(sortName);
			alarmmanage.setSortorder(sortOrder);
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
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

			totalCount = service.getCount("AlarmManage.getBusAlarmCount",
					alarmmanage);
			dayList = service.getObjectsByPage("AlarmManage.getSosAlarmInfos",
					alarmmanage, (Integer.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));

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
		gpsUtil gsputil = new gpsUtil();
		//dayList = gsputil.getAlarmPosition(dayList);
		dayList = gsputil.getAlarmAddress(dayList);
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
					s.getLatitude(), s.getVehicle_vin(), s.getAlarm_type_id(),
					s.getDeal_flag(), s.getDirection(), s.getAlarm_id(),
					s.getVehicle_ln(), s.getAlarm_type_name(),s.getDeal_flag(),
					s.getAlarm_time(), s.getDriver_name(), s.getSichen_name(),
					s.getStu_num(),s.getAlarm_address(), "", s.getUser_name(),s.getOpeerate_desc() });
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
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {

			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			if (null == chooseorgid || "".equals(chooseorgid)
					|| "undefined".equals(chooseorgid)) {
				alarmmanage.setOrganization_id(user.getOrganizationID());
			} else {
				alarmmanage.setOrganization_id(chooseorgid);
			}

			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			alarmmanage.setSortname(sortName);
			alarmmanage.setSortorder(sortOrder);
			// 增加中文排序
			if (sortName.equals("vehicle_ln")) {
				sortName = "nlssort(" + sortName
						+ ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			//仅按时间排序
			/*
			if(sortName.equals("alarm_time")){
				SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
				Date startdate=formater.parse(start_time);
				Date enddate=formater.parse(end_time);
				Calendar  ca=Calendar.getInstance(); 
				while(startdate.compareTo(enddate)<=0){
					SimpleDateFormat formater2 = new SimpleDateFormat("yyyymmdd");
					if(sortOrder.equals("desc")){
						alarmmanage.getPartionList().add("ALERM_RECORD_"+formater2.format(enddate));
						ca.setTime(enddate);     
					    ca.add(ca.DATE,-1);//把startdate加上1天然后重新赋值给date      
					    enddate=ca.getTime();  
					}else{
						alarmmanage.getPartionList().add("ALERM_RECORD_"+formater2.format(startdate));
						ca.setTime(startdate);     
					    ca.add(ca.DATE,1);//把startdate加上1天然后重新赋值给date      
					    startdate=ca.getTime();  
					}
					 	
				}
				
			}*/
			
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
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
			log.info("更多车辆其他告警计算总数开始");
			totalCount = service.getCount("AlarmManage.getBusAlarmCount",
					alarmmanage);
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
					s.getVehicle_ln(), s.getAlarm_type_name(),s.getDeal_flag(),
					s.getAlarm_time(), s.getSpeeding(),
					s.getEngine_rotate_speed(), s.getMileage(),
					s.getAlarm_address(), "",s.getUser_name(),s.getOpeerate_desc() });
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
				returnvalue = alarmservice.updateAlarm(alarmmanage,
						sendCommandClient);
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
		//WebContext ctx = WebContextFactory.get(); 
		//List perUrlList=(ArrayList)ctx.getSession().getAttribute(Constants.PER_URL_LIST);
		String dealmouldid=Constants.ALARM_DEAL_ENABLE_MAP.get(alarmmanage.getAlarm_type_id());
		// 设置操作描述
		this.addOperationLog("解除告警");
		// 设置操作类型
		this.setOperationType(Constants.UPDATE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(dealmouldid);
		log.info("处理车辆告警结束");
		return returnvalue;
	}

	/**
	 * 解除学生告警
	 * 
	 * @param userid
	 * @param vin
	 * @param desc
	 * @param alarmtypeid
	 * @param alarmid
	 * @return
	 */
	public String postStuCommand(String userid, String vin, String desc,
			String alarmtypeid, String alarmid,String alarmtime) {
		//MDC.put("loginid", getloginuuid());
		//MDC.put("modulename", "[dealstualarm]");
		log.info("[alarmid:"+alarmid+";vin:"+vin+"]处理学生告警开始");
		String returnvalue = "";
		if (null == stualarm) {
			stualarm = new StuAlarm();
		}
		stualarm.setUser_id(userid);
		stualarm.setVehicle_vin(vin);
		stualarm.setOperate_desc(desc);
		stualarm.setId(alarmid);
		stualarm.setTerminal_time(alarmtime);
		try {
			service.update("AlarmManage.updateStuAlarmFlag", stualarm);
			returnvalue = "0";
		} catch (Exception e) {
			log.error("处理学生告警异常:", e);
			returnvalue = "7002";
		}
		//String dealmouldid=Constants.ALARM_DEAL_ENABLE_MAP.get(alarmtypeid);
		// 设置操作描述
		this.addOperationLog("解除告警");
		// 设置操作类型
		this.setOperationType(Constants.UPDATE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ALARMYC_UPDATE_ID);
		log.info("处理学生告警结束");
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
			alarmservice.updateList(moredeallist);
			// 设置操作描述
			this.addOperationLog("批量解除");
			// 设置操作类型
			this.setOperationType(Constants.UPDATE);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(mouldid);
			log.info("车辆告警批量处理结束");
		} catch (Exception e) {
			log.error("车辆批量处理告警出错:", e);
		}

	}

	/**
	 * 学生告警批量处理
	 */
	public void alarmstuMoreDeal() {
		//MDC.put("modulename", "[dealmorestualarm]");
		log.info("[alarmids:"+alarmids+"]违规乘车批量处理开始");
		String alarmid[] = alarmids.split(",");
		String dealflag[] = dealflags.split(",");
		String tertime[]=tertimes.split(",");
		List<StuAlarm> morestudeallist = new ArrayList<StuAlarm>();
		String usrid = getCurrentUser().getUserID();
		String batchid = UUIDGenerator.getUUID32();
		try {
			for (int i = 0; i < alarmid.length; i++) {
				StuAlarm stualarm = new StuAlarm();
				stualarm.setId(alarmid[i]);
				stualarm.setUser_id(usrid);
				stualarm.setOperate_type("2");
				stualarm.setOperate_flag("1");
				stualarm.setTerminal_time(tertime[i]);
				morestudeallist.add(stualarm);
			}
			alarmservice.updateStuList(morestudeallist);
			// 设置操作描述
			this.addOperationLog("批量解除");
			// 设置操作类型
			this.setOperationType(Constants.UPDATE);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(mouldid);
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
	public String exportSosalarm() {
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
			// alarmmanage.setSortname(sortName);
			// alarmmanage.setSortorder(sortOrder);
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			alarmmanage.setStart_time(start_time);
			alarmmanage.setDeal_flag(deal_flag);
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}

			alarmmanage.setAlarm_type_id(alarm_type_id);
			/*
			 * if (StringUtils.isEmpty(pageIndex)) { pageIndex = "1"; } if
			 * (StringUtils.isEmpty(rpNum)) { rpNum = "10"; } totalCount =
			 * service.getCount("AlarmManage.getBusAlarmCount", alarmmanage);
			 */
			dayList = service.getObjects("AlarmManage.expSosAlarmInfos",
					alarmmanage);
			log.info("导出紧急告警查询列表结束");
		} catch (BusinessException e) {
			log.error("导出紧急告警查询列表时出错:", e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出紧急告警查询列表时出错:", e);
			return ERROR;
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
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new AlarmManage());
			}

			excelExporter.putAutoExtendSheets("exportsosalarm", 0, dayList);
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
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition",
				"attachment;filename=SosAlarm-" + name + ".xls");
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
	 * 其他告警数据导出
	 * 
	 * @return
	 */
	public String exportOtheralarm() {
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
				+ alarm_type_id+";chooseorgid:"+chooseorgid+"]更多车辆其他告警导出开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String timestr = start_time + "——" + end_time;
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
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}
			alarmmanage.setAlarm_type_id(alarm_type_id);
			dayList = service.getObjects("AlarmManage.exportBusAlarmInfos",
					alarmmanage);
			log.info("更多车辆其他告警导出查询列表结束");
		} catch (BusinessException e) {
			if (null != alarm_type_id) {
				if (alarm_type_id.contains("32")
						|| alarm_type_id.contains("33")
						|| alarm_type_id.contains("54")) {
					log.error("违规驾驶告警导出查询列表出错:", e);
				} else {
					log.error("车辆故障告警导出查询列表出错:", e);
				}
			}
			return ERROR;
		} catch (Exception e) {
			if (null != alarm_type_id) {
				if (alarm_type_id.contains("32")
						|| alarm_type_id.contains("33")
						|| alarm_type_id.contains("54")) {
					log.error("违规驾驶告警导出查询列表出错:", e);
				} else {
					log.error("车辆故障告警导出查询列表出错:", e);
				}
			}
			return ERROR;
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
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new AlarmManage());
			}
			
			excelExporter.putAutoExtendSheets("exportotheralarm", 0, dayList);
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
		HttpServletResponse response = ServletActionContext.getResponse();
		String name = DateUtil.getCurrentDayTime();
		response.setHeader("Content-disposition", "attachment;filename=Alarm-"
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
				+ alarm_type_id+";chooseorgid:"+chooseorgid+"]更多学生违规告警导出开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String timestr = start_time + "——" + end_time;
		try {

			if (null == stualarm) {
				stualarm = new StuAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				stualarm.setOrganization_id(user.getOrganizationID());
			} else {
				stualarm.setOrganization_id(chooseorgid);
			}
			//stualarm.setOrganization_id(user.getOrganizationID());
			stualarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			stualarm.setStart_time(start_time);
			stualarm.setOperate_flag(deal_flag);
			if (null != end_time && !end_time.equals("")) {
				stualarm.setEnd_time(end_time + " 23:59:59");
			}
			if ("".equals(alarm_type_id) || null == alarm_type_id) {
				stualarm.setAlarm_type_id("73,74,79,80");
			} else {
				stualarm.setAlarm_type_id(alarm_type_id);
			}

			allStuAlarmList = service.getObjects(
					"AlarmManage.exportStuAlarmInfos", stualarm);
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
	 * 优化2.0泡泡入口
	 */
	public String getDetailAlarm(){
		HttpServletRequest request = (HttpServletRequest) ActionContext
		.getContext()
		.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
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
			//alarmmanage.setLatitude(lat);
			//alarmmanage.setLongitude(lon);
		}
		log.info("[vin:"+vin+";alarmid:"+alarmid+";sourceid:"+sourceid+"]泡泡弹出页面结束");
		return SUCCESS;
	}
	
	/**
	 * 点击列表处告警，首次执行的DWR方法
	 * @return
	 */
	public AlarmManage getFirstDetail(String alarmid,String vin,String alarmtime,String alarmtypeid){
		//MDC.put("modulename", "[gerfirstalarm]");
		log.info("[vin:"+vin+";alarmtime:"+alarmtime+"]获取一条告警开始");
		AlarmManage alarmmanage = new AlarmManage();
		alarmmanage.setVehicle_vin(vin);
		alarmmanage.setAlarm_id(alarmid);
		alarmmanage.setAlarm_time(alarmtime);
		alarmmanage.setAlarm_type_id(alarmtypeid);
		alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
		alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
		
		WebContext ctx = WebContextFactory.get(); 
		UserInfo us=(UserInfo)ctx.getSession().getAttribute(Constants.USER_SESSION_KEY);
		alarmmanage.setEn_mould(us.getEn_mould());
		
		List perUrlList=(ArrayList)ctx.getSession().getAttribute(Constants.PER_URL_LIST);

		AlarmManage ret = new AlarmManage();
		int totalCount = 0;
		try {
			totalCount = service.getCount("AlarmManage.newgetAlarmCount",
					alarmmanage);
		} catch (BusinessException e) {
			log.error("获取告警总数出错",e);
		}catch(Exception e){
			log.error("获取告警总数出错",e);
		}
		//ret.setAlarm_count(totalCount+"");

		if ("73".equals(alarmtypeid) || "74".equals(alarmtypeid)
				|| "79".equals(alarmtypeid) || "80".equals(alarmtypeid)) {
			try{
				 ret = (AlarmManage) service.getObject(
						"AlarmManage.newgetstualarmbyidandtime",
						alarmmanage);
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e);
				totalCount=0;
				//ret=null;
			}
		}else{
			try{
			ret = (AlarmManage) service.getObject(
					"AlarmManage.newgetalarmbyidandtime", alarmmanage);
			}catch(Exception e){
				log.error("POP页面查询单条告警出错:", e);
				totalCount=0;
				//ret=null;
			}
		}
		
		//修正点击其他按钮后alarmid值不变的问题
		if(null==ret){
			ret = new AlarmManage();
			if(totalCount!=0){
				try{
					dayList = service.getObjectsByPage(
							"AlarmManage.newgetBusAlarmInfos",
							alarmmanage, 0, 1);
					if (null != dayList && dayList.size() > 0) {
						ret = dayList.get(0);
					}
				}catch(Exception e){
					log.error("POP页面二次查询单条告警出错:", e);
					totalCount=0;
				}
			}
		}
		
		String dealmouldid=Constants.ALARM_DEAL_ENABLE_MAP.get(ret.getAlarm_type_id());
		if(perUrlList.contains(dealmouldid)){
			ret.setDeal_mouldid(dealmouldid);
		}else{
			ret.setDeal_mouldid("0");
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

	public String newalarm_type_id;
	
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
		if ("'09','10','13','25','26','64','65','66','67','68','69','70','71'".equals(newalarm_type_id)) {
			alarmtabname = "车辆故障";
			tabflag = "3";
		} else if ("'32'".equals(newalarm_type_id)) {
			alarmtabname = "超速告警";
			tabflag = "2";
		} else if ("'73','74','79','80'".equals(newalarm_type_id)) {
			alarmtabname = "异常乘车";
			tabflag = "4";
		} else {
			alarmtabname = "紧急告警";
			tabflag = "1";
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
		if ("'40','72'".equals(alarmmanage.getAlarm_type_id())) {
			alarmtypemap = Constants.ALARM_TAB1_TYPE_MAP;
		} else if ("'32'".equals(alarmmanage.getAlarm_type_id())) {
			alarmtypemap = Constants.ALARM_TAB2_TYPE_MAP;
		} else {
			alarmtypemap = Constants.ALARM_TAB3_TYPE_MAP;
		}
		alarm_type_id = alarmmanage.getAlarm_type_id();
		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
		deal_flag = "0";
		year = DateUtil.getYear();
		return SUCCESS;
	}

	public String newchaosuAlarm() {
		start_time = DateUtil.getPreNDay(-2);
		end_time = DateUtil.getCurrentDay();
		if ("'40','72'".equals(alarmmanage.getAlarm_type_id())) {
			alarmtypemap = Constants.ALARM_TAB1_TYPE_MAP;
		} else if ("'32'".equals(alarmmanage.getAlarm_type_id())) {
			alarmtypemap = Constants.ALARM_TAB2_TYPE_MAP;
		} else {
			alarmtypemap = Constants.ALARM_TAB3_TYPE_MAP;
		}
		alarm_type_id = alarmmanage.getAlarm_type_id();
		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
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
			alarm_type_id = "'40','72'";
		}
		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
		deal_flag = "0";
		year = DateUtil.getYear();
		return SUCCESS;
	}

	/**
	 * 2.0 更多学生列表入口
	 * 
	 * @return
	 */
	public String newstuAlarm() {
		start_time = DateUtil.getPreNDay(-2);
		end_time = DateUtil.getCurrentDay();
		alarmtypemap = Constants.STU_ALARM_TYPE_MAP;
		alarm_type_id = alarmmanage.getAlarm_type_id();
		dealflagmap = Constants.ALARM_DEAL_FLAG_MAP;
		deal_flag = "0";
		year = DateUtil.getYear();
		return SUCCESS;
	}

	/**
	 * 2.0更多学生违规显示 为不影响1.0而重写
	 */
	public String newopenStuAlarmList() {
		final String browseTitle = getText("stualarmmanage.browse.title");
		UserInfo user = getCurrentUser();
		int totalCount = 0;
		//MDC.put("modulename", "[stualarm]");
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:"
				+ start_time + ";end_time:" + end_time + ";alarm_type_id:"
				+ stu_alarm_type_id+";chooseorgid:"+chooseorgid+"]更多学生违规告警开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {

			if (null == stualarm) {
				stualarm = new StuAlarm();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				stualarm.setOrganization_id(user.getOrganizationID());
			} else {
				stualarm.setOrganization_id(chooseorgid);
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			// 增加中文排序
			if (sortName.equals("vehicle_ln") || sortName.equals("route_name")
					|| sortName.equals("site_name")
					|| sortName.equals("stu_name")
					|| sortName.equals("stu_school")
					|| sortName.equals("stu_class")) {
				sortName = "nlssort(" + sortName
						+ ",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
			stualarm.setSortname(sortName);
			stualarm.setSortorder(sortOrder);
			stualarm.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			stualarm.setStart_time(start_time);
			stualarm.setOperate_flag(deal_flag);
			if (null != end_time && !end_time.equals("")) {
				stualarm.setEnd_time(end_time + " 23:59:59");
			}

			stualarm.setAlarm_type_id(alarm_type_id);

			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}

			totalCount = service.getCount("AlarmManage.getStuAlarmCount",
					stualarm);
			allStuAlarmList = service.getObjectsByPage(
					"AlarmManage.getStuAlarmInfos", stualarm, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));

			this.map = getStuOpenMorePagination(allStuAlarmList, totalCount,
					pageIndex, rpNum);
			log.info("更多学生违规告警转换MAP结束");
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
			log.info("更多学生违规告警结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error("查询更多学生告警出错:", e);
			return ERROR;
		}catch(Exception e){
			addActionError(getText(e.getMessage()));
			log.error("查询更多学生告警出错:", e);
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
	 */
	public AlarmManage getRecentAlarmInfo(String vin) {
		//MDC.put("modulename", "[gerrecentalarm]");
		log.info("[vin:"+vin+"]获取最新一条告警开始");
		AlarmManage alarmmanage = new AlarmManage();
		alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
		alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
		alarmmanage.setVehicle_vin(vin);
		WebContext ctx = WebContextFactory.get(); 
		UserInfo us=(UserInfo)ctx.getSession().getAttribute(Constants.USER_SESSION_KEY);
		alarmmanage.setEn_mould(us.getEn_mould());
		
		List perUrlList=(ArrayList)ctx.getSession().getAttribute(Constants.PER_URL_LIST);
		
		AlarmManage ret = new AlarmManage();
		int totalCount = 0;
		try {
			totalCount = service.getCount("AlarmManage.newgetAlarmCount",
					alarmmanage);
		} catch (BusinessException e) {
			log.error("获取告警总数出错",e);
		}catch(Exception e){
			log.error("获取告警总数出错",e);
		}
		if (totalCount != 0) {
			try {
				List<AlarmManage> list = service.getObjectsByPage(
						"AlarmManage.newgetBusAlarmInfos", alarmmanage, 0, 1);
				if (null != list && list.size() > 0) {
					ret = list.get(0);
					// 进行坐标偏转
					String lon = ret.getLongitude();
					String lat = ret.getLatitude();
					gpsUtil gpsUtil = new gpsUtil();
					String point = gpsUtil.getOneXY(lon, lat);
					if (point != null && point != "") {
						String[] p = point.split(",");
						ret.setLongitude(p[0].toString());
						ret.setLatitude(p[1].toString());
					}
					ret.setAlarm_count(totalCount + "");
					String dealmouldid=Constants.ALARM_DEAL_ENABLE_MAP.get(ret.getAlarm_type_id());
					if(perUrlList.contains(dealmouldid)){
						ret.setDeal_mouldid(dealmouldid);
					}else{
						ret.setDeal_mouldid("0");
					}
				} else {
					ret.setAlarm_count(totalCount + "");
				}
			} catch (Exception e) {
				log.error("获取最新一条告警出错",e);
			}
		} else {
			ret.setAlarm_count(totalCount + "");
		}
		if("FFFF".equals(ret.getSpeeding())){
			ret.setSpeeding("无效");
		}else if(null==ret.getSpeeding()||"".equals(ret.getSpeeding())){
			
		}else{
			ret.setSpeeding(ret.getSpeeding()+"km/h");
		}
		log.info("获取最新一条告警结束");
		return ret;
	}
	
	public String openAlarmList(){
		return SUCCESS;
	}
	
	public String getRealTimeAlarm (){
		log.info("获取车辆实时超速信息开始!");
		UserInfo uin = this.getCurrentUser();
		int totalCount = 0;
		HttpServletRequest request = (HttpServletRequest) ActionContext
		.getContext()
		.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		Map<String,String> maps = new HashMap<String,String>();
		try {
			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			
			//alarmmanage.setEnterprise_id(uin.getEntiID());
			maps.put("enterprise_id", uin.getEntiID());
			
			Date date = new Date();
			String partition = "TERMINAL_RECORD_"+DateUtil.getDateTime("yyyyMMdd",date);
			maps.put("partition",partition);
			if (StringUtils.isEmpty(pageIndex)) {
				pageIndex = "1";
			}
			if (StringUtils.isEmpty(rpNum)) {
				rpNum = "10";
			}
			
			totalCount = service.getCount("AlarmManage.getVehicleAlarmListCount",
					maps);
			int totalPage = (totalCount + Integer.parseInt(rpNum) - 1)/Integer.parseInt(rpNum);
			if(Integer.parseInt(pageIndex) + 1 > totalPage){
				pageIndex = "1";
			}
			dayList = service.getObjectsByPage(
					"AlarmManage.getVehicleAlarmList", maps, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			
			gpsUtil gu = new gpsUtil();
			dayList = gu.getAlarmAddressProvince(dayList);
			
			map = getOpenAlarmPagination(dayList, totalCount, pageIndex, rpNum);
			
		} catch (Exception e) {
			log.error("获取车辆实时超速信息出错!",e);
			e.printStackTrace();
		}
		log.info("获取车辆实时超速信息结束!");
		return SUCCESS;
	}
	
	public Map getOpenAlarmPagination(List dayList, int totalCountDay,
			String pageIndex, String rpNum){
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < dayList.size(); i++) {

			AlarmManage s = (AlarmManage) dayList.get(i);
			String isAlarm = "";
			long speeding = null == s.getSpeeding() ? 0 : Long.valueOf(s.getSpeeding());
			long topSpeed = null == s.getTopSpeed() ? 0 : Long.valueOf(s.getTopSpeed());
			
			if(speeding >= topSpeed && topSpeed > 0){
				isAlarm = "超速";
			} else {
				isAlarm = "正常";
			}
			Map cellMap = new LinkedHashMap();
			String dvrStat = s.getDvr_stat();
			String online = dvrStat.equals("1")?"在线":"不在线";
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
			String terminalTime = s.getTerminal_time();
			if(null != s.getTerminal_time() && s.getTerminal_time().length() != 0){
				terminalTime = terminalTime.substring(0,19);
			} else {
				terminalTime = "";
			}
			
			cellMap.put("id", s.getVehicle_vin());
			cellMap.put("cell", new Object[] { s.getLongitude(),
					s.getLatitude(), s.getVehicle_vin(), s.getVehicle_ln(), 
					s.getRoute_name(), s.getSpeeding(), s.getTopSpeed(),
					terminalTime,s.getAlarm_address(), isAlarm,online});
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);
		return mapData;
	}
	
	 /**
     * 获得当前操作LOGID
     * @return
     */
    private String getlogid() {
        return (String) ActionContext.getContext().getSession().get(
                Constants.LOG_USE_ID);
    }
    
    
    
    /**
	 * 超速汇总数据导出
	 * 
	 * @return
	 */
	public String exportChaosuReport() {
		String exportTitle = getText("alarmmanage.chaosureport.export");
		List<AlarmManage> tempList;
		UserInfo user = getCurrentUser();
		log.info("[vehicle_ln:" + vehicle_ln + ";start_time:"
				+ start_time + ";end_time:" + end_time +";chooseorgid:"+chooseorgid+"]超速汇总记录导出开始");
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String timestr = start_time + "——" + end_time;
		try {
			if (null == alarmmanage) {
				alarmmanage = new AlarmManage();
			}
			if (null == chooseorgid || "".equals(chooseorgid)) {
				alarmmanage.setOrganization_id(user.getOrganizationID());
			} else {
				alarmmanage.setOrganization_id(chooseorgid);
			}
			alarmmanage.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
			alarmmanage.setStart_time(start_time);
			alarmmanage.setDeal_flag(deal_flag);
			if (null != end_time && !end_time.equals("")) {
				alarmmanage.setEnd_time(end_time + " 23:59:59");
			}
			alarmmanage.setAlarm_type_id(alarm_type_id);
			dayList = service.getObjects("AlarmManage.exportChaosuReport",alarmmanage);
			log.info("超速汇总记录导出查询列表结束");
		} catch (BusinessException e) {
			log.error("超速汇总记录导出查询列表出错:", e);
			return ERROR;
		} catch (Exception e) {
			log.error("超速汇总记录导出查询列表出错:", e);
			return ERROR;
		}
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "Chaosureport.xls";
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("超速汇总记录("+timestr+")");
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new AlarmManage());
			}
			excelExporter.putAutoExtendSheets("exportchaosureport", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("超速汇总记录导出生成EXCLE出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("超速汇总记录导出生成EXCLE出错:",e);
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
		response.setHeader("Content-disposition", "attachment;filename=ChaosuReport.xls");
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
			log.error("超速汇总记录下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("超速汇总记录下载时出错:",e);
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
			log.info("超速汇总记录导出结束");
		}
		// 导出文件成功
		return null;
	}
}
