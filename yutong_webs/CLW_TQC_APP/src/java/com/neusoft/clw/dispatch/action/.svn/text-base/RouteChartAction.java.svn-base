package com.neusoft.clw.dispatch.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.writegzpackservice.Writegzpackservice;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.opensymphony.xwork2.ActionContext;
/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.0 $ 2011-12-20 3:21:42 PM
 */
public class RouteChartAction extends PaginationAction {

	/** service共通类 */
	private transient Service service;

	/** 显示消息 * */
	private String message = null;
	
	private String user_org_id;
	private String chart_tab_flag;
	private String chart_tab_name;
	private String sysdate;
	
	private RouteChart routeInfo;
	
	private TerminalViBean terminalViBean;
	
	private CarRunHistory queryObj;
	
	private List < RouteChart > routeList;

	private Map map = new HashMap();
	private RouteChart routeChart;

	public TerminalViBean getTerminalViBean() {
		return terminalViBean;
	}

	public void setTerminalViBean(TerminalViBean terminalViBean) {
		this.terminalViBean = terminalViBean;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}   
	
	public RouteChart getRouteInfo() {
		return routeInfo;
	}

	public void setRouteInfo(RouteChart routeInfo) {
		this.routeInfo = routeInfo;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getUser_org_id() {
		return user_org_id;
	}

	public void setUser_org_id(String user_org_id) {
		this.user_org_id = user_org_id;
	}

	public List<RouteChart> getRouteList() {
		return routeList;
	}

	public void setRouteList(List<RouteChart> routeList) {
		this.routeList = routeList;
	}

	public String getChart_tab_flag() {
		return chart_tab_flag;
	}

	public void setChart_tab_flag(String chart_tab_flag) {
		this.chart_tab_flag = chart_tab_flag;
	}

	public String getChart_tab_name() {
		return chart_tab_name;
	}

	public void setChart_tab_name(String chart_tab_name) {
		this.chart_tab_name = chart_tab_name;
	}
	private String begTime;

	public String getBegTime() {
		return begTime;
	}

	public void setBegTime(String begTime) {
		this.begTime = begTime;
	}
	private String endTime;


	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * 列表信息页面
	 * @return
	 */
	public String readyPage() {
		if (null != message) {
			addActionMessage(getText(message));
		}
		sysdate = DateUtil.getCurrentDay();
		user_org_id = getCurrentUser().getOrganizationID();
		begTime=DateUtil.getCurrentDay();
		endTime=DateUtil.getCurrentDay();
		//queryObj=new CarRunHistory();
		//queryObj.setBegintime(beginTime);
		if(0 == DateUtil.getCurrentMeridiem()){
			chart_tab_flag = "0";
			chart_tab_name = "上学";
		}else{
			chart_tab_flag = "1";
			chart_tab_name = "放学";
		}
		
		return SUCCESS;
	}

	/**
	 * 左侧-线路信息列表
	 * @return
	 */
	public String getLeftRouteList() {
		MDC.put("modulename", "[lineMonitor]");
		log.info("getLeftRouteList start");
		final String browseTitle = getText("menu2.routechart");
		int totalCount = 0;
		UserInfo user = getCurrentUser();
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

		try {
			if (null == routeInfo) {
				routeInfo = new RouteChart();
			}
			routeInfo.setRoute_organization_id(user.getOrganizationID());

			//String rpNum = request.getParameter("rp");
			//String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");

			routeInfo.setSortname(sortName);
			routeInfo.setSortorder(sortOrder);

			//totalCount = service.getCount("RouteChart.getRouteCount", routeInfo);

			routeList = (List < RouteChart >) service.getObjects("RouteChart.getRouteInfos", routeInfo);

			this.map = getPagination(routeList);// 转换map

			if (0 == routeList.size()) {
				this.addActionMessage(getText("nodata.list"));
			}
			if (null != message) {
				addActionMessage(getText(message));
			}
			// 设置操作描述
			this.addOperationLog(browseTitle);
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ROUTECHART_ID);
		} catch (BusinessException e) {
			log.info(browseTitle, e);
			log.info("getLeftRouteList error end");
			return ERROR;
		}
		log.info("getLeftRouteList end");
		return SUCCESS;
	}
	/**
	 * 左侧-线路信息列表
	 * @return
	 */
	public String getRoute_info_List() {
		MDC.put("modulename", "[lineMonitor]");
		log.info("getLeftRouteList start");
		final String browseTitle = getText("menu2.routechart");
		int totalCount = 0;
		UserInfo user = getCurrentUser();
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("vehicle_code", request.getParameter("vehicle_code"));
			map.put("vehicle_vin", request.getParameter("vehicle_vin"));
			map.put("begTime", request.getParameter("begTime")+" 00:00:00");
			map.put("endTime", request.getParameter("endTime")+" 23:59:59");

			List<String> str = (List<String>) ActionContext.getContext().getSession().get(Constants.PER_URL_LIST);
			String search_zb = "";
			String search_wb = "";
			String search_cn = "";
			if(str.contains("111_3_5_4_7")) {
				search_zb+="'0'";
			}
			if(str.contains("111_3_5_4_8")) {
				search_cn+="'2'";
			}
			if(str.contains("111_3_5_4_9")) {
				search_wb+="'1'";
			}
			String route_class = request.getParameter("route_class")==null?"":
				request.getParameter("route_class").equals("-1")?"":request.getParameter("route_class");
	        if( null == route_class || "".equals(route_class) ){
	        	if(str.contains("111_3_5_3_5")){
		        	//routeInfo.setRoute_class("0");
		        	if(str.contains("111_3_5_3_6")){
			        	//routeInfo.setRoute_class("0");
			        	if(str.contains("111_3_5_3_7")){
			        		route_class="'0','1','2'";
				        }else{
				        	route_class="'0','1'";
				        }
			        }else{
			        	if(str.contains("111_3_5_3_7")){
			        		route_class="'0','2'";
				        }else{
				        	route_class="'0'";
				        }
			        }
		        }else {

		        	//routeInfo.setRoute_class("0");
		        	if(str.contains("111_3_5_3_6")){
			        	//routeInfo.setRoute_class("0");
			        	if(str.contains("111_3_5_3_7")){
			        		route_class="'1','2'";
				        }else{
				        	route_class="'1'";
				        }
			        }else{
			        	if(str.contains("111_3_5_3_7")){
			        		route_class="'2'";
				        }else{
				        	route_class="'m'";
				        }
			        }
				}
	        }
			map.put("route_class", route_class);
			
			map.put("route_id", request.getParameter("route_id"));
			if(request.getParameter("route_res")!=null&&request.getParameter("route_res").length()>0) {
				if(request.getParameter("route_res").equals("-1"))        //查询失败
					map.put("route_res_null", "-1");
				else if(request.getParameter("route_res").equals("2")){
					map.put("route_res_ing", "-1");                      //查询正在下发信息
				} else
					map.put("route_res", request.getParameter("route_res").equals("0")?"":request.getParameter("route_res"));  //0：查询全部，1：查询发送成功
			}
			map.put("organizationID",user.getOrganizationID());
			
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");

			map.put("sortname",sortName);
			map.put("sortorder",sortOrder);

			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			
			totalCount = service.getCount("DispatchRouteChart.getexe_comondInfoscount", map);
			if(totalCount>0)
				routeList = service.getObjectsByPage("DispatchRouteChart.getexe_comondInfos",map, (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			else
				routeList = new ArrayList<RouteChart>();

			this.map = getPagination(routeList, totalCount, pageIndex, rpNum);

			if (0 == routeList.size()) {
				this.addActionMessage(getText("nodata.list"));
			}
			if (null != message) {
				addActionMessage(getText(message));
			}
			// 设置操作描述
			this.addOperationLog(browseTitle);
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_ROUTECHART_ID);
		} catch (BusinessException e) {
			log.info(browseTitle, e);
			log.info("getLeftRouteList error end");
			return ERROR;
		}
		log.info("getLeftRouteList end");
		return SUCCESS;
	}
	public void route_add_car_info() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", routeChart.getRoute_id());
			map.put("vehicle_vin", routeChart.getVIN());
			map.put("send_condition", routeChart.getSend_condition());
			
			String send_time = routeChart.getSend_condition().equals("2")?routeChart.getSend_time():"";
			map.put("send_time", send_time);
			map.put("send_order", routeChart.getSend_order());
			map.put("update_by", getCurrentUser().getUserID());
			map.put("exe_date", routeChart.getExe_date());
			map.put("start_time", routeChart.getStart_time());
			map.put("end_time", routeChart.getEnd_time());
			map.put("add_flag", routeChart.getAdd_flag());
			map.put("week_seq", StringUtil.stringdatebackweek(routeChart.getExe_date())==0?7:StringUtil.stringdatebackweek(routeChart.getExe_date()));
			map.put("driver_id", routeChart.getDriver_id());
			map.put("out_flag", "");
			service.getObject("DispatchRouteChart.add_route_car_", map);
			
			//生成执行
			if(routeChart.getAdd_flag().equals("1")) {
				Map<String, Object> mapmake = new HashMap<String, Object>();
				mapmake.put("route_id", routeChart.getRoute_id());
				mapmake.put("start_time", routeChart.getStart_time());
				mapmake.put("end_time", routeChart.getEnd_time());
				mapmake.put("out_flag", "");
				service.getObject("SendCommand.send_commond_make_n", mapmake);
				
				/*Map<String, Object> mapup = new HashMap<String, Object>();
				mapup.put("route_id", routeChart.getRoute_id());
				mapup.put("p_date", routeChart.getExe_date());
				mapup.put("send_condition", routeChart.getSend_condition());
				mapup.put("vin_code", routeChart.getVIN());
				service.getObject("DispatchRouteChart.updateold_route_car_", mapup);*/
			}
			if(map.get("out_flag").equals("-1"))
				printWriter("error");
			else if(map.get("out_flag").equals("1")){
				printWriter("车辆已存在！");
			} else
				printWriter("su"+map.get("out_flag"));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter("error");
		}
	}
	public void route_add_line_info() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", routeChart.getRoute_id());
			map.put("vehicle_vin", routeChart.getVIN());
			map.put("send_condition", routeChart.getSend_condition());
			String send_time = routeChart.getSend_condition().equals("2")?routeChart.getSend_time():"";
			map.put("send_time", send_time);
			//map.put("send_time", routeChart.getSend_time());
			map.put("update_by", getCurrentUser().getUserID());
			map.put("exe_date", routeChart.getExe_date());
			map.put("start_time", routeChart.getStart_time());
			map.put("end_time", routeChart.getEnd_time());
			map.put("add_flag", routeChart.getAdd_flag());
			map.put("week_seq", StringUtil.stringdatebackweek(routeChart.getExe_date())==0?7:StringUtil.stringdatebackweek(routeChart.getExe_date()));
			map.put("out_flag", "");
			service.getObject("DispatchRouteChart.add_route_line_", map);
			
			if(map.get("out_flag").equals("-1"))
				printWriter("error");
			else if(map.get("out_flag").equals("1")){
				printWriter("车辆已存在！");
			} else {
				Map<String,Object> mapv = new HashMap<String,Object>();
				mapv.put("exe_date", routeChart.getExe_date());
				mapv.put("vehicle_vin", routeChart.getVIN());
				mapv.put("route_id", routeChart.getRoute_id());
				int vv = service.getCount("DispatchRouteChart.getlineInfobyvinForChart_pagec", mapv);
				
				printWriter("su"+vv);
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter("error");
		}
	}
	public void route_del_car_info() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", routeChart.getRoute_id());
			map.put("trip_id", routeChart.getTrip_id());
			map.put("vehicle_vin", routeChart.getVIN());
			map.put("send_order", routeChart.getSend_order());
			map.put("exe_date", routeChart.getExe_date());
			map.put("trip_group", routeChart.getTrip_group());
			map.put("week_seq", StringUtil.stringdatebackweek(routeChart.getExe_date())==0?7:StringUtil.stringdatebackweek(routeChart.getExe_date()));
			map.put("out_flag", "");
			
			//routeChart = (RouteChart) service.getObject("DispatchRouteChart.getdelvehicleinExe", map);
			/*service.getObject("DispatchRouteChart.del_route_car_", map);
			
			//判断是否车辆已下发，如果下发重新下发，如果未下发则直接删除
			//if(routeChart.getUpdate_time()!=null&&routeChart.getUpdate_time().length()>0)
				//sendRouteFile(routeChart.getVIN(),routeChart.getTrip_id(),routeChart.getExe_date());
			if(map.get("out_flag").equals("-1"))
				printWriter("error");
			else if(map.get("out_flag").equals("1"))
				printWriter("run");
			else
				printWriter("success");*/
			//如果是长期的
			if(routeChart.getAdd_flag().equals("0")) {
				service.update("DispatchRouteChart.routecarnouse", routeChart.getTrip_id());
			}else {
				service.update("DispatchRouteChart.routecarnouse_temp", map);
			}
			printWriter("success");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter("error");
		}
	}
	public void route_del_car_info_nosend() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", routeChart.getRoute_id());
			map.put("trip_id", routeChart.getTrip_id());
			map.put("vehicle_vin", routeChart.getVIN());
			map.put("add_flag", routeChart.getAdd_flag());
			map.put("send_order", routeChart.getSend_order());
			map.put("trip_group", routeChart.getTrip_group());
			map.put("exe_date", routeChart.getExe_date());
			map.put("week_seq", StringUtil.stringdatebackweek(routeChart.getExe_date())==0?7:StringUtil.stringdatebackweek(routeChart.getExe_date()));
			map.put("out_flag", "");
			service.getObject("DispatchRouteChart.del_route_bygroup_", map);//del_route_car_
			//sendRouteFile(routeChart.getVIN(),routeChart.getTrip_id(),routeChart.getExe_date(),routeChart.getSend_order());
			//,iniDefaultPathFlag,writegzpackservice,
			//sendCommandClient,iniDefaultPath,ip,port,username,userpass
			if(map.get("out_flag").equals("-1"))
				printWriter("error");
			else if(map.get("out_flag").equals("1"))
				printWriter("run");
			else
				printWriter("success");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter("error");
		}
	}
	/**
	 * 转换Map
	 * @param oilusedList
	 * @param totalCountDay
	 * @param pageIndex
	 * @return
	 */

	public Map getPagination(List routeList) {
		MDC.put("modulename", "[lineMonitor]");
		log.info("getPagination start");
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < routeList.size(); i++) {
			RouteChart s = (RouteChart) routeList.get(i);
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getRoute_id());
			cellMap.put("cell", new Object[] {s.getRoute_id(),
					s.getRoute_name(), s.getRoute_incharge_person(),
					i});
			mapList.add(cellMap);
		}
		mapData.put("page", "1");// 从前台获取当前第page页
		mapData.put("total", routeList.size());// 从数据库获取总记录数
		mapData.put("rows", mapList);
		log.info("getPagination end");
		return mapData;
	}
	public Map getPagination(List<RouteChart> routeChart, int totalCountDay, String pageIndex, String rpNum) {
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Map<String,Object> mapData = new LinkedHashMap<String,Object>();
		for (int i = 0; i < routeChart.size(); i++) {
			RouteChart s = (RouteChart) routeChart.get(i);

			Map<String,Object> cellMap = new LinkedHashMap<String,Object>();

			cellMap.put("id", s.getTrip_id());
			String msgres = "";
			if(s.getMesg_flag()!=null&&s.getMesg_flag().length()>0) {
				msgres = s.getMesg_flag().equals("1")?"成功":s.getMesg_flag().equals("-1")?"失败":"正在下发";
			} else {
				msgres = "失败";
			}
			cellMap.put("cell", new Object[] {
					(i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
					s.getExe_date(),
					s.getVehicle_code(),
					s.getVehicle_ln(),
					s.getRoute_class(),
					s.getRoute_name(),
					s.getSite_updown(),
					s.getSend_condition(),
					s.getSend_order(),
					s.getOperate_time(),
					s.getUpdate_time(),
					msgres
		  });

			mapList.add(cellMap);

		}

		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCountDay);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}
	public String getCarList() {
		return SUCCESS;
	}
	
	
	public String getStuListBySite(){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getStuListBySite start");
		String browseTitle = getText("menu2.routechart");
		if (null == routeInfo) {
			return SUCCESS;
		}
		int totalCount = 0;
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		//String rpNum = request.getParameter("rp");
		//String pageIndex = request.getParameter("page");
		String sortName = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");
		
		if (sortName.equals("STU_NAME") || sortName.equals("STU_SCHOOL") || sortName.equals("STU_CLASS") || sortName.equals("VSS_FLAG")) {
			sortName = "nlssort(" + sortName
				   + ",'NLS_SORT=SCHINESE_PINYIN_M')";
		}	   
		
		try {
			routeInfo.setSortname(sortName);
			routeInfo.setSortorder(sortOrder);
			if(routeInfo.getVIN() != null && !"".equals(routeInfo.getVIN())){
				routeInfo.setBegTime(DateUtil.getCurrentDay()+" "+routeInfo.getBegTime());
				routeInfo.setEndTime(DateUtil.getCurrentDay()+" "+routeInfo.getEndTime());				
			}
			
			List stuList = (List < RouteChart >) service.getObjects("RouteChart.getStuListBySite", routeInfo);
			
			/*
			totalCount = service.getCount("RouteChart.getCountStuListBySite", routeInfo);

			List stuList = (List < RouteChart >) service.getObjectsByPage(
					"RouteChart.getStuListBySite", routeInfo, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer
							.parseInt(rpNum));
			*/
			this.map = getStuPagination(stuList);
		} catch (BusinessException e) {
			log.info(browseTitle, e);
			log.info("getStuListBySite error end");
			return ERROR;
		}
		/*
		// 设置操作描述
		this.addOperationLog(browseTitle);
		// 设置操作类型
		this.setOperationType(Constants.SELECT);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ROUTECHART_ID);
		*/	  
		log.info("getStuListBySite end");
		return SUCCESS;
	}
	public Map getStuPagination(List list) { 
		MDC.put("modulename", "[lineMonitor]");
		log.info("getStuPagination start");
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < list.size(); i++) {

			RouteChart s = (RouteChart) list.get(i);

			Map cellMap = new LinkedHashMap();

			cellMap.put("id", i);

			cellMap.put("cell", new Object[] {s.getStu_name(),s.getVSS_FLAG(),
					s.getStu_code(),s.getStu_school(),s.getStu_class(),
					s.getStu_teacher_tel(),s.getStu_relative_tel()});
			mapList.add(cellMap);

		}
		mapData.put("page", 1);// 从前台获取当前第page页
		mapData.put("total", list.size());// 从数据库获取总记录数
		mapData.put("rows", mapList);
		log.info("getStuPagination end");
		return mapData;
	}
	public void makedispatchdata() {
		try {
			/*Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", routeInfo.getRoute_id());
			map.put("week_seq", StringUtil.stringdatebackweek(DateUtil.getCurrentDay())==0?7:StringUtil.stringdatebackweek(DateUtil.getCurrentDay()));
			
			List<RouteChart> list = service.getObjects("DispatchRouteChart.getroutecarinfobytime", map);
			map.put("exe_date", DateUtil.getCurrentDay());
			List<RouteChart> dislist = service.getObjects("DispatchRouteChart.getdispatchroutecar", map);
			
			service.delete("DispatchRouteChart.delroutecar_dis", map);
			
			for(RouteChart chart:list) {
				chart.setExe_date(DateUtil.getCurrentDay());
				chart.setAdd_flag("0");
				chart.setValid_days("0");
				service.insert("DispatchRouteChart.insertdispatchcarInfo", chart);
			}
			
			for(RouteChart chart:dislist) {
				Map<String,Object> mapv = new HashMap<String,Object>();
				mapv.put("route_id", chart.getRoute_id());
				mapv.put("vehicle_vin", chart.getVIN());
				mapv.put("send_condition", chart.getSend_condition());
				mapv.put("send_time", chart.getSend_time());
				mapv.put("update_by", getCurrentUser().getUserID());
				mapv.put("exe_date", DateUtil.getCurrentDay());
				mapv.put("send_order", chart.getSend_order());
				mapv.put("start_time", chart.getStart_time());
				mapv.put("end_time", chart.getEnd_time());
				mapv.put("add_flag", chart.getAdd_flag());
				mapv.put("week_seq", StringUtil.stringdatebackweek(DateUtil.getCurrentDay()));
				mapv.put("out_flag", "");
				service.getObject("DispatchRouteChart.add_route_car_", mapv);
			}*/
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("exe_date", DateUtil.getPreNDay(1));
			service.getObject("DispatchRouteChart.make_route_car_t", map);
			printWriter("success");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter("error");
		}
	}

	/**
	 * 获得当前操作用户
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession().get(
				Constants.USER_SESSION_KEY);
	}

	public CarRunHistory getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(CarRunHistory queryObj) {
		this.queryObj = queryObj;
	}

	public RouteChart getRouteChart() {
		return routeChart;
	}

	public void setRouteChart(RouteChart routeChart) {
		this.routeChart = routeChart;
	}
	private Writegzpackservice writegzpackservice;
	private SendCommandClient sendCommandClient;
	private String iniDefaultPath;
	private String iniDefaultPathFlag;
	private String ip;
	private String port;
	private String username;
	private String userpass;
	///删除时调用下发命令重新生成文件
	public void sendRouteFile(String vin,String trip,String exe_date){
		//,String iniDefaultPathFlag,Writegzpackservice writegzpackservice,
		//SendCommandClient sendCommandClient,String iniDefaultPath,String ip,String port,String username,String userpass
		try {
			UserInfo user = getCurrentUser();
			
			String batch_id = UUIDGenerator.getUUID32();
			Map<String, Object> resultmap = new HashMap<String, Object>();
			
	        String pathInfo = iniDefaultPath+vin+ "/" ; //線網路徑
	        
	        String sendFilePath="";
	        
	        String targetFileName="";
	        
	        if(iniDefaultPathFlag.equals("0")){
	        	sendFilePath="/"+vin+"/";
	        }else{
	        	sendFilePath=pathInfo;
	        }
	        
			String usedPath=pathInfo;
			
			try {
				resultmap=writegzpackservice.getPackPath(vin,trip,usedPath,vin,exe_date);
				targetFileName=(String) resultmap.get("filename");
			} catch (DataAccessIntegrityViolationException e) {
				e.printStackTrace();
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			targetFileName=targetFileName.substring(targetFileName.lastIndexOf("/")+1)+".gz";
			
	    	String msgid = UUIDGenerator.getUUID32();
	    	String crc=(String) resultmap.get("crc");
	    	String returnvalue = sendCommandClient.sendRouteNotice(vin, user.getUserID(), msgid, batch_id,ip, port, username, userpass, 
	    			sendFilePath+ targetFileName , 
	    			crc);
	    	log.info("returnvalue:" + returnvalue);
		} catch (BusinessException e) {
			log.error("下发异常", e);
			printWriter("下发异常");
		}
		printWriter("success");
	}
	
	public void route_exe_car_back() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", request.getParameter("route_id"));
			map.put("exe_date", request.getParameter("exe_date"));
			service.getObject("DispatchRouteChart.tqc_trip_exe_back_", map);
			printWriter("success");
		} catch (BusinessException e) {
			printWriter("error");
			log.info("getStuListBySite error end");
		}
	}
	public void route_exe_car_totemp() {
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", request.getParameter("route_id"));
			map.put("exe_date", request.getParameter("exe_date"));
			//判断时间如果大于当前时间	先赋值crc	下发前台做过判断	下发时间只可能 大于等于当前时间
//			if(DateUtil.getCurrentDay().equals(request.getParameter("exe_date")))
//				service.getObject("DispatchRouteChart.tqc_trip_exe_totemp_", map);
//			else {
				List<RouteChart> routec = service.getObjects("DispatchRouteChart.getdelroutecarlist", map);
				for(RouteChart rc:routec) {
					Map<String,Object> mapdel = new HashMap<String,Object>();
					mapdel.put("route_id", rc.getRoute_id());
					mapdel.put("trip_id", rc.getTrip_id());
					mapdel.put("vehicle_vin", rc.getVIN()); 
					mapdel.put("send_order", rc.getSend_order());
					mapdel.put("exe_date", rc.getExe_date());
					mapdel.put("week_seq", StringUtil.stringdatebackweek(request.getParameter("exe_date"))==0?7:StringUtil.stringdatebackweek(request.getParameter("exe_date")));
					mapdel.put("out_flag", "");
					service.getObject("DispatchRouteChart.del_route_car_", mapdel);
				}
				if(DateUtil.getCurrentDay().equals(request.getParameter("exe_date")))
					service.update("DispatchRouteChart.tqc_trip_exe_update_crc_today", map);
				else
					service.update("DispatchRouteChart.tqc_trip_exe_update_crc_ntoday", map);
				
				//今日往后的数据写入
				map.put("exe_date", DateUtil.getCurrentDay());
				service.getObject("DispatchRouteChart.tqc_trip_exe_totemp_", map);
//			}
			printWriter("success");
		} catch (BusinessException e) {
			printWriter("error");
			log.info("getStuListBySite error end");
		}
	}
	public Writegzpackservice getWritegzpackservice() {
		return writegzpackservice;
	}

	public void setWritegzpackservice(Writegzpackservice writegzpackservice) {
		this.writegzpackservice = writegzpackservice;
	}

	public SendCommandClient getSendCommandClient() {
		return sendCommandClient;
	}

	public void setSendCommandClient(SendCommandClient sendCommandClient) {
		this.sendCommandClient = sendCommandClient;
	}

	public String getIniDefaultPath() {
		return iniDefaultPath;
	}

	public void setIniDefaultPath(String iniDefaultPath) {
		this.iniDefaultPath = iniDefaultPath;
	}

	public String getIniDefaultPathFlag() {
		return iniDefaultPathFlag;
	}

	public void setIniDefaultPathFlag(String iniDefaultPathFlag) {
		this.iniDefaultPathFlag = iniDefaultPathFlag;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpass() {
		return userpass;
	}

	public void setUserpass(String userpass) {
		this.userpass = userpass;
	}

	public String getSysdate() {
		return sysdate;
	}

	public void setSysdate(String sysdate) {
		this.sysdate = sysdate;
	}
	
}
