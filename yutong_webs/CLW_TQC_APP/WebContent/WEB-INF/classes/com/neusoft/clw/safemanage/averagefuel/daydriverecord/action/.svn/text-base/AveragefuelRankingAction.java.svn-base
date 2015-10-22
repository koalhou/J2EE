package com.neusoft.clw.safemanage.averagefuel.daydriverecord.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import org.springframework.transaction.jta.UserTransactionAdapter;

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
import com.neusoft.clw.itsmanage.oilmanage.oilused.domain.OilUsed;
import com.neusoft.clw.itsmanage.oilmanage.oilused.domain.OilUsedExport;
import com.neusoft.clw.safemanage.averagefuel.ranking.domain.AveragefuelRanking;
import com.neusoft.clw.safemanage.humanmanage.baddriving.domain.HumanBadDriving;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.clw.safemanage.averagefuel.ranking.domain.rankingExport;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunTreeNode;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunTreeNodeAttri;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunTreeNode;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunTreeNode;

public class AveragefuelRankingAction extends PaginationAction {

	/* 刘俊安添加，油量排行页面，现改为发车与客流统计 */
	private transient Service service;

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	private String message = null;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private String pageParam;

	public String getPageParam() {
		return pageParam;
	}

	public void setPageParam(String pageParam) {
		this.pageParam = pageParam;
	}

	// 页面进入时，查询默认开始时间
	private String begintime;

	public String getBegintime() {
		return begintime;
	}

	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	// 页面进入时，查询默认结束时间
	private String endtime;

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	private int re_flag; // 组织还是车辆，0：组织，1：车辆

	public int getRe_flag() {
		return re_flag;
	}

	public void setRe_flag(int re_flag) {
		this.re_flag = re_flag;
	}

	private String VIN;

	public String getVIN() {
		return VIN;
	}

	public void setVIN(String VIN) {
		this.VIN = VIN;
	}

	private List<CarRunTreeNode> treeNodesList;

	public List<CarRunTreeNode> getTreeNodesList() {
		return treeNodesList;
	}

	public void setTreeNodesList(List<CarRunTreeNode> treeNodesList) {
		this.treeNodesList = treeNodesList;
	}

	private String name = "";

	private CarRunTreeNode carRunTreeNode;

	public CarRunTreeNode getCarRunTreeNode() {
		return carRunTreeNode;
	}

	public void setCarRunTreeNode(CarRunTreeNode carRunTreeNode) {
		this.carRunTreeNode = carRunTreeNode;
	}

	/**
	 * 格式化企业信息
	 */
	private void formatEnterpriseInfoWithoutOnline() {
		try {
			if (treeNodesList != null && treeNodesList.size() == 0) {
				return;
			}
			UserInfo currentUser = (UserInfo) ActionContext.getContext()
					.getSession().get(Constants.USER_SESSION_KEY);

			List<CarRunTreeNodeAttri> carnumbers = (List<CarRunTreeNodeAttri>) service
					.getObjects("CarRunHistory.getCarnumberByEnterprise",
							currentUser.getOrganizationID());
			// 注册车辆信息map
			Map<String, String> carNumberMap = new HashMap<String, String>();

			for (CarRunTreeNodeAttri tmp : carnumbers) {
				// 已分配车辆
				carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
			}

			for (CarRunTreeNode singleInfo : treeNodesList) {
				if (carNumberMap.get(singleInfo.getId()) != null
						&& carNumberMap.get(singleInfo.getId()).length() > 0) {
					singleInfo.setName(String.format("%s(%s)",
							singleInfo.getName(),
							carNumberMap.get(singleInfo.getId())));
				}
			}

		} catch (BusinessException e) {
			log.error("Get car numbers error:" + e.getMessage());
		}
	}

	private VehcileInfo vehinfo;

	public VehcileInfo getVehinfo() {
		return vehinfo;
	}

	public void setVehinfo(VehcileInfo vehinfo) {
		this.vehinfo = vehinfo;
	}

	private List<VehcileInfo> vehcList;

	public List<VehcileInfo> getVehcList() {
		return vehcList;
	}

	public void setVehcList(List<VehcileInfo> vehcList) {
		this.vehcList = vehcList;
	}

	/**
	 * 无在线状态组织树
	 * 
	 * @return
	 */
	public String getTreeNodesWithoutOnline() {
		UserInfo currentUser = (UserInfo) ActionContext.getContext()
				.getSession().get(Constants.USER_SESSION_KEY);

		try {
			treeNodesList = (List<CarRunTreeNode>) service.getObjects(
					"CarRunHistory.getTreeNodes",
					currentUser.getOrganizationID());
			formatEnterpriseInfoWithoutOnline();

		} catch (BusinessException e) {
			log.error("Get enterprise tree error:" + e.getMessage());
		}

		return SUCCESS;
	}

	/**
	 * 查询无在线状态组织树
	 * 
	 * @return
	 */
	public String searchTreeNodesWithoutOnline() {
		UserInfo currentUser = (UserInfo) ActionContext.getContext()
				.getSession().get(Constants.USER_SESSION_KEY);

		Map<String, String> map = new HashMap<String, String>();

		if (null == carRunTreeNode) {
			carRunTreeNode = new CarRunTreeNode();
		}
		try {
			map.put("enterpriseId", currentUser.getOrganizationID());
			map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder
					.decode(name, "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
		try {
			treeNodesList = (List<CarRunTreeNode>) service.getObjects(
					"CarRunHistory.searchTreeNodesByName", map);
			if (null == treeNodesList || treeNodesList.size() == 0) {
				// 按车牌查询无数据,查询组织
				treeNodesList = (List<CarRunTreeNode>) service.getObjects(
						"CarRunHistory.searchTreeNodesByDivisionName", map);
			}
			formatEnterpriseInfoWithoutOnline();
		} catch (BusinessException e) {
			log.error("Get enterprise tree error:" + e.getMessage());
		}

		return SUCCESS;
	}

	private void formatPlanEnterpriseInfoWithoutOnline() {
		try {
			if (treeNodesList != null && treeNodesList.size() == 0) {
				return;
			}

			UserInfo currentUser = (UserInfo) ActionContext.getContext()
					.getSession().get(Constants.USER_SESSION_KEY);

			List<CarRunTreeNodeAttri> carnumbers = (List<CarRunTreeNodeAttri>) service
					.getObjects("CarRunHistory.getPlanCarnumberByEnterprise",
							currentUser.getOrganizationID());
			// 注册车辆信息map
			Map<String, String> carNumberMap = new HashMap<String, String>();

			for (CarRunTreeNodeAttri tmp : carnumbers) {
				// 已分配车辆
				carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
			}

			for (CarRunTreeNode singleInfo : treeNodesList) {
				if (carNumberMap.get(singleInfo.getId()) != null
						&& carNumberMap.get(singleInfo.getId()).length() > 0) {
					singleInfo.setName(String.format("%s(%s)",
							singleInfo.getName(),
							carNumberMap.get(singleInfo.getId())));
				}
			}

		} catch (BusinessException e) {
			log.error("Get car numbers error:" + e.getMessage());
		}
	}

	// 设置的CarRunHistory对象，作为载体来处理JSP传来的数据。查询、导出等功能
	private CarRunHistory queryObj;

	public CarRunHistory getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(CarRunHistory queryObj) {
		this.queryObj = queryObj;
	}

	private String user_org_id;

	public String getUser_org_id() {
		return user_org_id;
	}

	public void setUser_org_id(String user_org_id) {
		this.user_org_id = user_org_id;
	}

	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession()
				.get(Constants.USER_SESSION_KEY);
	}

	private Map map = new HashMap();

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	private List<CarRunHistory> historyList;

	public List<CarRunHistory> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<CarRunHistory> historyList) {
		this.historyList = historyList;
	}

	private List<CarRunHistory> totalList;

	public List<CarRunHistory> getTotalList() {
		return totalList;
	}

	public void setTotalList(List<CarRunHistory> totalList) {
		this.totalList = totalList;
	}

	public String readyPage() {
		queryObj = new CarRunHistory();
		queryObj.setQueryTime(DateUtil.getCurrentDay());
		user_org_id = getCurrentUser().getOrganizationID();
		return SUCCESS;
	}

	public String rankAction() {
		// MDC.put("modulename", "[illegaldriving]");
		if (StringUtils.isEmpty(endtime)) {
			endtime = DateUtil.getCurrentDay();
		}
		if (StringUtils.isEmpty(begintime)) {
			// begintime=DateUtil.getMonthFirstDay1();//这一月的第一天
			begintime = DateUtil.getCurrentDay();
		}

		return "success";
	}

	// 获取表单的内容：访问SQLMap，获取表单，通过getHistoryPagination()方法对返回数据处理，返回到前台页面
	public String getDriveRecordList() {
		String browseTitle = getText("menu2.clyxrz");

		int totalCount = 0;
		UserInfo user = getCurrentUser();
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String rpNum = request.getParameter("rp");
		String pageIndex = request.getParameter("page");
		String sortName = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");

		
		
		if (queryObj.getUser_organization_id() == null
				|| "".equals(queryObj.getUser_organization_id())) {
			queryObj.setUser_organization_id(user.getOrganizationID());
		}
		if (this.begintime != null && !"".equals(this.begintime)) {
			queryObj.setBegintime(this.begintime + " 00:00:00");
		} else {
			queryObj.setBegintime(DateUtil.getCurrentDay() + " 00:00:00");
		}
		if (this.endtime != null && !"".equals(this.endtime)) {
			queryObj.setEndtime(this.endtime + " 23:59:59");
		} else {
			queryObj.setEndtime(DateUtil.getCurrentDay() + " 23:59:59");
		}
		queryObj.setSortname(sortName);
		queryObj.setSortorder(sortOrder);


		
		try {

			totalCount = service.getCount("Daydriverecord.getCountDriveRecord",
					queryObj);

			historyList = (List<CarRunHistory>) service
					.getObjectsByPage("Daydriverecord.getDriveRecordInfos",
							queryObj, (Integer.parseInt(pageIndex) - 1)
									* Integer.parseInt(rpNum),
							Integer.parseInt(rpNum));
			totalList = service.getObjects("Daydriverecord.getSumList",
					queryObj);

			this.map = getHistoryPagination(historyList, totalCount, totalList,
					pageIndex, rpNum);
		} catch (BusinessException e) {
			log.info(browseTitle, e);
			return ERROR;
		}
		// 设置操作描述
		this.addOperationLog(browseTitle);
		// 设置操作类型
		this.setOperationType(Constants.SELECT);
		// 设置所属应用系统
		this.setApplyId(Constants.CLW_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_CAR_RUN_HISTORY_QUR);
		return SUCCESS;
	}

	public Map getHistoryPagination(List list, int totalCount, List totalList,
			String pageIndex, String rpNum) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		Map sumMap = new LinkedHashMap();
		DecimalFormat decimalformat = new DecimalFormat("0.0");
		// int runTime_total = 0;
		// int idleTime_total = 0;
		// double total_mileage = 0.0;
		for (int i = 0; i < list.size(); i++) {

			CarRunHistory s = (CarRunHistory) list.get(i);
			// ("0".equals(s.getRoute_class())?"上班":"下班"),
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getId());
			cellMap.put(
					"cell",
					new Object[] {
							// (i +
							// 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
							s.getVehicle_code(),
							s.getVehicle_ln(),
							// (s.getDriver_name()==null?"未登录":s.getDriver_name()),
							s.getStart_time(),
							s.getEnd_time(),
							s.getRun_time(),
							// s.getIdle_time(),
							decimalformat.format(s.getSpd_oil()),
							decimalformat.format(s.getTotal_mileage()),
							s.getVIN() });
			mapList.add(cellMap);
			// runTime_total += Integer.parseInt(s.getRun_time());
			// idleTime_total += Integer.parseInt(s.getIdle_time());
			// total_mileage +=
			// Double.parseDouble(decimalformat.format(s.getTotal_mileage()));
		}
		// 在最下面添加一行，隐形在前台table，作为该行的总和
		if (totalList.size() > 0) {
			CarRunHistory s = (CarRunHistory) totalList.get(0);
			sumMap.put("id", "sumid");
			sumMap.put(
					"cell",
					new Object[] { null, null, null, null,
							// decimalformat.format(s.getSpd_oil() == null ? 0 :
							// s.getSpd_oil()),
							// decimalformat.format(s.getTotal_oil() == null ? 0
							// : s.getTotal_oil()),
							// runTime_total,
							s.getRun_time(),
							// idleTime_total,
							// s.getIdle_time(),
							// decimalformat.format(total_mileage)
							decimalformat.format(s.getSpd_oil()),
							decimalformat.format(s.getTotal_mileage() == null ? 0
									: s.getTotal_mileage()) });
		} else {
			sumMap.put("id", "sumid");
			sumMap.put("cell", new Object[] { null, null, null, null, "0",
					// "0",
					"0.0", "0.0" });
		}
		mapList.add(sumMap);

		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCount);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;

	}

	/**
	 * 得到当前请求对象
	 */
	private HttpServletRequest getCurrentRequest() {
		return (HttpServletRequest) ActionContext.getContext().get(
				org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	}

	/**
	 * 显示地图轨迹页面
	 */

	public String showMapPage() {
		HttpServletRequest request = getCurrentRequest();
		// 告警id
		// String id=request.getParameter("id");
		try {
			// 根据告警ID查询一条告警信息
			// tripPatch=(TripPatch)this.service.getObject("TripPatchManage.getTripPatchById",
			// id);

			if (null == queryObj) {
				queryObj = new CarRunHistory();
			}
			queryObj.setVIN(request.getParameter("vin"));
			// try {
			// queryObj.setStart_time(
			// SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(request.getParameter("start_time"),
			// "utf-8")));
			// queryObj.setEnd_time(
			// SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(request.getParameter("end_time"),
			// "utf-8")));
			// } catch (UnsupportedEncodingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			queryObj.setStart_time(request.getParameter("start_time"));
			queryObj.setEnd_time(request.getParameter("end_time"));
			String organ = (String) this.service.getObject(
					"Daydriverecord.getOrgan", queryObj);
			queryObj.setOrganization_name(organ);

		} catch (Exception e) {
			this.log.error("查询组织异常", e);
		}
		return SUCCESS;
	}

	/**
	 * 加载iframe页面（此页面通过iframe.src加载地图轨迹页面） 通过iframe的目的：完全卸载加载的地图
	 */
	public String loadIframe() {
		HttpServletRequest request = getCurrentRequest();
		// String start_time1 = null;
		// String end_time1 = null;
		// try {
		// start_time1 =
		// SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(request.getParameter("start_time"),
		// "utf-8"));
		// end_time1 =
		// SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(request.getParameter("end_time"),
		// "utf-8"));
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// }
		request.setAttribute("vin", request.getParameter("vin"));
		request.setAttribute("start_time", request.getParameter("start_time"));
		request.setAttribute("end_time", request.getParameter("end_time"));

		return SUCCESS;
	}

	private AveragefuelRanking baddrivday;

	public AveragefuelRanking getBaddrivday() {
		return baddrivday;
	}

	public void setBaddrivday(AveragefuelRanking baddrivday) {
		this.baddrivday = baddrivday;
	}

	private List<AveragefuelRanking> dayList;

	public List<AveragefuelRanking> getDayList() {
		return dayList;
	}

	public void setDayList(List<AveragefuelRanking> dayList) {
		this.dayList = dayList;
	}

	private List<AveragefuelRanking> sumList;

	public List<AveragefuelRanking> getSumList() {
		return sumList;
	}

	public void setSumList(List<AveragefuelRanking> sumList) {
		this.sumList = sumList;

	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	private String vehicle_vin;

	// 导出表格
	// 导出表格必须是String格式，其中有强制转换的部分代码
	public String exportDriverecord() throws UnsupportedEncodingException {	

		String modelTitle = getText("humanbaddrive.export.title");
		// MDC.put("modulename", "[illegaldriving]");
		UserInfo user = getCurrentUser();
		if (null == queryObj) {
			queryObj = new CarRunHistory();
		}
		if (queryObj.getUser_organization_id() == null
				|| "".equals(queryObj.getUser_organization_id())) {
			queryObj.setUser_organization_id(user.getOrganizationID());
		}
		if (queryObj.getUser_organization_id() == null
				|| "".equals(queryObj.getUser_organization_id())) {
			queryObj.setUser_organization_id(user.getOrganizationID());
		}
		if (this.begintime != null && !"".equals(this.begintime)) {
			queryObj.setBegintime(this.begintime + " 00:00:00");
		} else {
			queryObj.setBegintime(DateUtil.getCurrentDay() + " 00:00:00");
		}
		if (this.endtime != null && !"".equals(this.endtime)) {
			queryObj.setEndtime(this.endtime + " 23:59:59");
		} else {
			queryObj.setEndtime(DateUtil.getCurrentDay() + " 23:59:59");
		}
		// queryObj.setBegintime(getBegintime());//获取页面上查询的开始时间
		// queryObj.setEndtime(getEndtime());//获取结束时间

		List<CarRunHistory> list = new ArrayList<CarRunHistory>();

		// 查询SQLMap，车辆VIN还没有传过去
		try {

			list = (List<CarRunHistory>) service.getObjects(
					"Daydriverecord.getDriveRecordInfos", queryObj);
			totalList = service.getObjects("Daydriverecord.getSumList",
					queryObj);
		} catch (BusinessException e) {
			setMessage("info.db.error");
			// log.error(exportTitle+"Export Data error:" + e.getMessage());
			return ERROR;
		} catch (Exception e) {
			setMessage("info.db.error");
			// log.error(exportTitle+"Export Data error:" + e.getMessage());
			return ERROR;
		}

		List<rankingExport> exportlist = new ArrayList<rankingExport>();
		DecimalFormat decimalformat = new DecimalFormat("0.0");
		for (int i = 0; i < list.size(); i++) {
			rankingExport oilexport = new rankingExport();
			CarRunHistory oilused = list.get(i);
			oilexport.setVehicle_code(oilused.getVehicle_code());
			oilexport.setVehicle_ln(oilused.getVehicle_ln());
			// oilexport.setDriver_name(oilused.getDriver_name()==null?"未登录":oilused.getDriver_name());

			oilexport.setStart_time(oilused.getStart_time());
			oilexport.setEnd_time(oilused.getEnd_time());

			oilexport.setRun_time(oilused.getRun_time());
			// oilexport.setIdle_time(oilused.getIdle_time());
			// 强制数据类型转换
			oilexport.setSpd_oil(decimalformat.format(oilused.getSpd_oil()));
			oilexport.setTotal_mileage(decimalformat.format(oilused
					.getTotal_mileage()));
			exportlist.add(oilexport);
		}

		if (exportlist.size() > 0) {
			// for (int j = 0; j < totalList.size(); j++) {
			rankingExport oilexport = new rankingExport();
			CarRunHistory oilused = totalList.get(0);
			oilexport.setVehicle_code(getText("common.sum"));
			oilexport.setVehicle_ln("/");
			// oilexport.setDriver_name("/");
			oilexport.setStart_time("/");
			oilexport.setEnd_time("/");

			oilexport.setRun_time(oilused.getRun_time());
			// oilexport.setIdle_time(oilused.getIdle_time());
			oilexport.setSpd_oil(decimalformat.format(oilused.getSpd_oil()));
			// 强制数据类型转换
			oilexport.setTotal_mileage(decimalformat.format(oilused
					.getTotal_mileage()));

			exportlist.add(oilexport);
			// }
		}
		String filePath = "";
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "HumanBadDrive.xls";

			// add by liuja start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by liuja stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("行车记录(" + this.begintime + "~"
					+ this.endtime +
					// timeStr+
					")");
			if (exportlist == null || exportlist.size() < 1) {
				exportlist.add(new rankingExport());
			}

			excelExporter.putAutoExtendSheets("exportDriveRunRecord", 0,
					exportlist);
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
		String fileName = URLEncoder.encode("行车记录", "UTF-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setHeader("Content-disposition", "attachment;filename="
				+ fileName + "-" + DateUtil.getCurrentDayTime() + ".xls");
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
			// this.addOperationLog(formatLog(modelTitle, null));
			// 设置操作描述
			this.addOperationLog("行车记录导出");
			// 设置操作类型
			this.setOperationType(Constants.EXPORT);
			// 设置所属应用系统
			this.setApplyId(Constants.CLW_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_CAR_RUN_HISTORY_EXP);
		}
		log.info(modelTitle + "结束");
		// 导出文件成功
		return null;
	}

	/**
	 * 格式化日志信息
	 * 
	 * @param desc
	 * @param Object
	 * @return
	 */
	protected String formatLog(String desc, AveragefuelRanking badday) {
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

}
