package com.neusoft.clw.yw.sitemanager.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yw.sitemanager.ds.SitSet;
import com.neusoft.clw.yw.sitemanager.ds.Site;
import com.neusoft.clw.yw.sitemanager.ds.SiteAddOilConfig;
import com.neusoft.clw.yw.sitemanager.ds.TerminalViBean;
import com.neusoft.clw.yw.sitemanager.service.StationService;
import com.opensymphony.xwork2.ActionContext;

public class SiteManageAction extends PaginationAction {
	protected Logger logger = Logger.getLogger(SiteManageAction.class);
	
    public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}
    
    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

	private transient Service service;
	private List<Site> siteList;
	private String site_id;
	private Site site;
	private String message = null;

	private Map result = new HashMap();
	private String site_name;
	private String sichen_addr;
	private String organization_id;
	private String control_station;
	private final String FORBID = "forbid";

	private String lonlatStr = "";
	private String latitude;
	private String longitude;
	private String pointID;
	private Map map = new HashMap();
	private List strList;
	private transient StationService stationservice;
	private String stationIdList;
	private int rp;
	private int page;
	private String sortname;
	private String sortorder;
	List<TerminalViBean> newlist;
	private String site_id_checked;
	private String econtrol_station;
	private String esite_name;
	private String startTime;
	private String endTime;
	private String inout_flag;

	private String vehicle_vin;
	private String vehicle_ln;

	private SiteAddOilConfig siteConfig;

	private List<SiteAddOilConfig> oilConfigList;

	private Integer siteConfigId;
	private String addOilRate;
	private String startTimeQuantum;
	private String endTimeQuantum;
	private String lowOilValue;
	private String oilValueCheck;
	private String oilRateCheck;
	private String oilPrice;


	/**
	 * 初始化页面
	 * 
	 * @return
	 */
	public String readyPage() {
		if (this.message != null) {
			addActionMessage(getText(this.message));
		}
		UserInfo user = getCurrentUser();
		if (this.site == null) {
			this.site = new Site();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.add(5, 0);

		String todayStartDate = DateUtil.getMonthFirstDay();
		String todayEndDate = new SimpleDateFormat("yyyy-MM-dd")
				.format(calendar.getTime()) + " 23:59:59";

		this.site.setEnterprise_id(user.getEntiID());
		this.site.setOrganization_id(user.getOrganizationID());
		this.site.setStart_time(todayStartDate);
		this.site.setEnd_time(todayEndDate);
		return "success";
	}

	public String siteBrowse() {

		// String browseTitle = getText("stationmanage.browse.title");
		int totalCount = 0;
		UserInfo user = getCurrentUser();
		try {
			if (this.site == null) {
				this.site = new Site();
			}
			this.site.setEnterprise_id(user.getEntiID());
			this.site.setOrganization_id(user.getOrganizationID());

			String rpNum = getRequest().getParameter("rp");
			String pageIndex = getRequest().getParameter("page");
			String sortName = getRequest().getParameter("sortname");
			String sortOrder = getRequest().getParameter("sortorder");
			if("site_name".equals(sortName)){
				sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";
			} else {
				sortName = sortName;
			}
			//sortName = "nlssort(" + sortName + ",'NLS_SORT=SCHINESE_PINYIN_M')";

			 this.site.setSortname(sortName);
			 this.site.setSortorder(sortOrder);

			totalCount = this.service.getCount("SitManage.getCount",
					this.site);

			this.siteList = this.service
					.getObjectsByPage(
							"SitManage.getInfos",
							this.site,
							(Integer.parseInt(pageIndex) - 1)
									* Integer.parseInt(rpNum),
							Integer.parseInt(rpNum));

			this.map = getPagination(this.siteList, totalCount, pageIndex,
					rpNum);

			if (this.siteList.size() == 0) {
				addActionMessage(getText("nodata.list"));
			}
			if (this.message != null) {
				addActionMessage(getText(this.message));
			}

		} catch (BusinessException e) {
			logger.error("", e);
			return "error";
		}

		return "success";
	}

	/**
	 * 添加站点
	 * @return
	 */
	public String add() {
		
		this.site = new Site();
		
		String addTitle = getText("SitManage.add.info");
		try {
			String insertID = "";
			String orderID = "";
			UserInfo user = getCurrentUser();
			this.site.setCreater(user.getUserID());
			this.site.setEnterprise_id(user.getEntiID());
			this.site.setOrganization_id(user.getOrganizationID());
			
			this.site.setSite_name(this.site_name);
			this.site.setSite_latitude(this.latitude);
			this.site.setSite_longitude(this.longitude);
			

			if (this.pointID != null)
				insertID = this.stationservice.insertPointToStation(
						this.pointID, this.site);
			else {
				insertID = this.stationservice.insertStation(
						"SitManage.insertStationInfo", this.site);
			}
			this.site.setSite_id(insertID);

			/****** 添加加油站点关联车辆 ******/
			if(StringUtils.hasText(this.vehicle_vin)){
				String[] vinArr = this.vehicle_vin.split(",");
				String[] lnArr = this.vehicle_ln.split(",");
				int len = vinArr.length;
				
				List<SitSet> sitsetList = new ArrayList<SitSet>();
				for (int i = 0; i < len; i++) {
					SitSet sitset = new SitSet();
					sitset.setSit_id(this.site.getSite_id());
					sitset.setVehicle_ln(lnArr[i]);
					sitset.setVehicle_vin(vinArr[i]);
					sitset.setEnterprise_id(this.site.getEnterprise_id());
					sitset.setOrganization_id(this.site.getOrganization_id());
					sitsetList.add(sitset);
				}
				stationservice.insertSet("SitManage.insertSetInfo", sitsetList);
			}
			
			//获取排序号
			this.sortname = ("nlssort(" + this.sortname + ",'NLS_SORT=SCHINESE_PINYIN_M')");
			this.site.setSortname(this.sortname);
			this.site.setSortorder(this.sortorder);
			orderID = (String) this.service.getObject(
					"SitManage.getAddStationOrder", this.site);
			this.result.put("returns", "success");
			this.result.put("returnID", insertID);
			this.result.put("returnOrder", orderID);
		} catch (BusinessException e) {
			this.log.error(addTitle, e);
			addActionError(e.getMessage());
			this.result.put("returns", "error");
			return "error";
		} catch (DataAccessIntegrityViolationException e) {
			this.log.error(addTitle, e);
			addActionError(e.getMessage());
			this.result.put("returns", "error");
			return "error";
		} catch (DataAccessException e) {
			this.log.error(addTitle, e);
			addActionError(e.getMessage());
			this.result.put("returns", "error");
			return "error";
		}

		return "success";
	}

	/**
	 * 更新站点
	 * @return
	 */
	public String updateStation() {
		if (this.site == null) {
			this.site = new Site();
		}
		String editTitle = getText("SitManage.update.info");
		try {
			UserInfo user = getCurrentUser();
			this.site.setModifier(user.getUserID());
			this.site.setOrganization_id(user.getOrganizationID());
			this.site.setSite_name(this.site_name);
			this.site.setSite_latitude(this.latitude);
			this.site.setSite_longitude(this.longitude);
			this.site.setSite_id(this.site_id);
			this.service.update("SitManage.updateInfobyStationId",
					this.site);


			/****** 添加加油站点关联车辆 ******/
			try {
				int delint = this.stationservice.deletes(
						"SitManage.deleteSetInfo", this.site);
			} catch (DataAccessIntegrityViolationException e1) {
				logger.error("", e1);
			} catch (DataAccessException e1) {
				logger.error("", e1);
			}

			// 获取新修改的车辆VIN
			String[] vinArr = this.vehicle_vin.split(",");
			String[] lnArr = this.vehicle_ln.split(",");
			int len = vinArr.length;

			List<SitSet> sitsetList = new ArrayList<SitSet>();
			for (int i = 0; i < len; i++) {
				SitSet station = new SitSet();
				station.setSit_id(this.site.getSite_id());
				station.setVehicle_ln(lnArr[i]);
				station.setVehicle_vin(vinArr[i]);
				station.setEnterprise_id(user.getEntiID());
				station.setOrganization_id(user.getOrganizationID());
				sitsetList.add(station);
			}
			try {
				stationservice.insertSet("SitManage.insertSetInfo",
						sitsetList);
			} catch (DataAccessIntegrityViolationException e) {
				logger.error("", e);
			} catch (DataAccessException e) {
				logger.error("", e);
			}
			/******* 添加加油站点关联车辆 *******/

			this.result.put("returns", "success");
		} catch (BusinessException e) {
			this.log.error(editTitle, e);
			addActionError(e.getMessage());
			this.result.put("returns", "error");
			return "error";
		}

		return "success";
	}

	/**
	 * 逻辑删除站点
	 */
	public String deleteStation() {
		this.site = new Site();
		this.result.put("returns", "error");
		this.site.setSite_id(this.site_id);
		String cancleTitle = getText("stationmanage.deletebefore.title");
		try {
			UserInfo user = getCurrentUser();
			this.site.setCreater(user.getUserID());

			if(this.service.update("SitManage.deletebyStationById", this.site)==1){
				this.result.put("returns", "success");
			}
		} catch (BusinessException e) {
			this.log.error(cancleTitle, e);
			addActionError(e.getMessage());
			return "error";
		}
		setMessage("stationmanage.deletesuccess.message");

		return "success";
	}
	
	
	public Map getPagination(List siteList, int totalCount, String pageIndex,
			String rpNum) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < siteList.size(); i++) {
			Site s = (Site) siteList.get(i);
			
			//偏转
//    		String lon = s.getSite_longitude();
//    		String lat = s.getSite_latitude();
//    		gpsUtil gpsUtil = new gpsUtil();
//    		String point = gpsUtil.getOneXY(lon, lat);
//    		if (point != null && point != "") {
//    			String[] p = point.split(",");
//    			s.setSite_longitude(p[0].toString());
//    			s.setSite_latitude(p[1].toString());
//    		}
    		//偏转结束
			
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getSite_id());
			cellMap.put(
					"cell",
					new Object[] {
							Integer.valueOf(i + 1
									+ (Integer.parseInt(pageIndex) - 1)
									* Integer.parseInt(rpNum)),
							s.getSite_name(),
							// s.getControl_station(),
							s.getVehicleCount(), s.getSite_longitude(),
							s.getSite_latitude(), s.getSichen_addr(),
							s.getOrganization_id(), s.getOrganizationName() });
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);
		mapData.put("total", Integer.valueOf(totalCount));
		mapData.put("rows", mapList);

		return mapData;
	}

	 public String getStartTime() {
	 return this.startTime;
	 }
	
	 public void setStartTime(String startTime) {
	 this.startTime = startTime;
	 }
	
	 public String getEndTime() {
	 return this.endTime;
	 }
	
	 public void setEndTime(String endTime) {
	 this.endTime = endTime;
	 }
	
	 public String getInout_flag() {
	 return this.inout_flag;
	 }
	
	 public void setInout_flag(String inout_flag) {
	 this.inout_flag = inout_flag;
	 }
	
	public void getSingleLONLAT() {
		gpsUtil gpsutil = new gpsUtil();
		String lonlatString = "";

		long startTime = System.currentTimeMillis();

		lonlatString = gpsutil.getOneXY(this.longitude, this.latitude);

		if (lonlatString == null) {
			lonlatString = this.longitude + "," + this.latitude;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/plain");
		try {
			response.getWriter().write(lonlatString);
		} catch (IOException localIOException) {
			logger.error("", localIOException);
		}
		long endTime = System.currentTimeMillis();
		this.log.info("获取单个经纬度坐标偏转，用时：" + (endTime - startTime) + "ms");
	}

	/**
	 * 根据条件查询站点和坐标
	 * 参数：inout_flag  0-坐标点  1-站点  2-坐标点和站点
	 * @return
	 */
	public String getMapSearchList() {
		List<TerminalViBean> innewlist = new ArrayList<TerminalViBean>();
		UserInfo user = getCurrentUser();
		TerminalViBean tv = new TerminalViBean();
		tv.setORGANIZATION_ID(user.getOrganizationID());
		tv.setSTART_TIME(this.startTime);
		tv.setEND_TIME(this.endTime);

		if ((this.inout_flag != null) && (!"".equals(this.inout_flag))) {
			tv.setINOUT_FLAG(this.inout_flag);
		}
		long startTime1 = System.currentTimeMillis();
		try {
			innewlist = this.service.getObjects(
					"SitManage.getLong_Latitude_List", tv);  
		} catch (BusinessException e) {
			logger.error("", e);
		}
		long endTime1 = System.currentTimeMillis();
		this.log.info("根据右侧条件查询站点，获取数据库中信息，用时：" + (endTime1 - startTime1)
				+ "ms");

		long startTime2 = System.currentTimeMillis();
		gpsUtil gpsUtil = new gpsUtil();
		List<TerminalViBean> list = new ArrayList<TerminalViBean>();
		
		for(Iterator<TerminalViBean> it = innewlist.iterator(); it.hasNext();){
			TerminalViBean viBean = it.next();
			//偏转
    		String lon = viBean.getLONGITUDE();
    		String lat = viBean.getLATITUDE();
    		String point = gpsUtil.getOneXY(lon, lat);
    		if (point != null && point != "") {
    			String[] p = point.split(",");
    			viBean.setLONGITUDE(p[0].toString());
    			viBean.setLATITUDE(p[1].toString());
    		}
    		//偏转结束
    		list.add(viBean);
		}
		
		this.newlist = list;
//		this.newlist = gpsUtil.getpost(innewlist);

		long endTime2 = System.currentTimeMillis();
		this.log.info("根据右侧条件查询站点，获取批量经纬度坐标偏转，用时：" + (endTime2 - startTime2)
				+ "ms");
		return "success";
	}

	public String getLeftList() {
		List innewlist = new ArrayList();
		UserInfo user = getCurrentUser();
		try {
			if (this.site == null) {
				this.site = new Site();
			}
			TerminalViBean tv = new TerminalViBean();
			tv.setORGANIZATION_ID(user.getOrganizationID());
			if("site_name".equals(this.sortname)){
				tv.setSortname("nlssort(" + this.sortname
						+ ",'NLS_SORT=SCHINESE_PINYIN_M')");
			} else {
				tv.setSortname(this.sortname);
			}
			tv.setSortorder(this.sortorder);
			if (this.site_name != null) {
				tv.setSITE_NAME(this.site_name);
			}

			long startTime1 = System.currentTimeMillis();
			innewlist = this.service.getObjectsByPage(
					"SitManage.getLeftListInfos", tv, (this.page - 1)
							* this.rp, this.rp);
			long endTime1 = System.currentTimeMillis();
			this.log.info("根据左侧列表查询站点，获取数据库中信息，用时：" + (endTime1 - startTime1)
					+ "ms");
		} catch (BusinessException e) {
			logger.error("", e);
		}

		long startTime2 = System.currentTimeMillis();
		gpsUtil gpsUtil = new gpsUtil();
		
		List<TerminalViBean> list = new ArrayList<TerminalViBean>();
		
		for(Iterator<TerminalViBean> it = innewlist.iterator(); it.hasNext();){
			TerminalViBean viBean = it.next();
			//偏转
    		String lon = viBean.getLONGITUDE();
    		String lat = viBean.getLATITUDE();
    		String point = gpsUtil.getOneXY(lon, lat);
    		if (point != null && point != "") {
    			String[] p = point.split(",");
    			viBean.setLONGITUDE(p[0].toString());
    			viBean.setLATITUDE(p[1].toString());
    		}
    		//偏转结束
    		list.add(viBean);
		}
		
		this.newlist = list;

		long endTime2 = System.currentTimeMillis();
		this.log.info("根据左侧列表查询站点，获取批量经纬度坐标偏转，用时：" + (endTime2 - startTime2)
				+ "ms");

		return "success";
	}

	/**
	 * 
	 * 获取初始化站点的坐标  
	 * @return
	 */
	public String getCheckedLeftList() {
		List innewlist = new ArrayList();
		List inafterlist = new ArrayList();
		UserInfo user = getCurrentUser();
		try {
			if (this.site == null) {
				this.site = new Site();
			}
			TerminalViBean tv = new TerminalViBean();
			tv.setORGANIZATION_ID(user.getOrganizationID());
			tv.setSortname("nlssort(" + this.sortname
					+ ",'NLS_SORT=SCHINESE_PINYIN_M')");
			tv.setSortorder(this.sortorder);

			String[] sites = this.site_id_checked.split(",");
			if (!this.site_id_checked.equals("")) {
				String site_ids = "";
				for (int j = 0; j < sites.length; j++) {
					if ((sites.length > 1) && (sites.length != j + 1))
						site_ids = site_ids + "'" + sites[j] + "',";
					else {
						site_ids = site_ids + "'" + sites[j] + "'";
					}
				}
				tv.setCR_CONFIG_ID(this.site_id_checked);
			} else {
				tv.setCR_CONFIG_ID("'0000000000'");
			}
			if (this.site_name != null) {
				tv.setSITE_NAME(this.site_name);
			}
			if (this.control_station != null) {
				tv.setCONTROL_STATION(this.control_station);
			}

			long startTime1 = System.currentTimeMillis();

			tv.setPage(this.page);
			tv.setRp(this.rp);
			innewlist = this.service.getObjects(
					"SitManage.getCheckedLeftList", tv);

			long endTime1 = System.currentTimeMillis();
			this.log.info("根据左侧列表“CHECKBOX已选”查询站点，获取数据库中信息，用时："
					+ (endTime1 - startTime1) + "ms");
		} catch (BusinessException e) {
			logger.error("", e);
		}
		long startTime2 = System.currentTimeMillis();
		gpsUtil gpsUtil = new gpsUtil();
		
		List<TerminalViBean> list = new ArrayList<TerminalViBean>();
		
		for(Iterator<TerminalViBean> it = innewlist.iterator(); it.hasNext();){
			TerminalViBean viBean = it.next();
			//偏转
    		String lon = viBean.getLONGITUDE();
    		String lat = viBean.getLATITUDE();
    		String point = gpsUtil.getOneXY(lon, lat);
    		if (point != null && point != "") {
    			String[] p = point.split(",");
    			viBean.setLONGITUDE(p[0].toString());
    			viBean.setLATITUDE(p[1].toString());
    		}
    		//偏转结束
    		list.add(viBean);
		}
		
		this.newlist = list;
		
		long endTime2 = System.currentTimeMillis();
		this.log.info("根据左侧列表“CHECKBOX已选”查询站点，获取批量经纬度坐标偏转，用时："
				+ (endTime2 - startTime2) + "ms");

		return "success";
	}

	/** 设置迟到告警参数 **/
	public void setParameterLater() {
		Map<String,String> maps = new HashMap<String,String>();
		UserInfo user = getCurrentUser();
		
		SiteAddOilConfig siteConfigObj = new SiteAddOilConfig();
		siteConfigObj.setEnterprise_id(user.getEntiID());
		siteConfigObj.setOrganization_id(user.getOrganizationID());
		try{
			this.oilConfigList = this.stationservice.selectSiteConfig(
					"SitManage.selectSiteConfig", siteConfigObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String laterTime = this.getRequest().getParameter("laterTime");
		String laterTimeEnt = this.getRequest().getParameter("laterTimeEnt");
		String[] laterObj1 = laterTime.split("!");
		String[] laterObj2 = laterTimeEnt.split("!");
		if(null != this.oilConfigList && this.oilConfigList.size() > 0){
			try {
				for(int i = 0; i < 2; i++){
					siteConfigObj.setEnterprise_id(user.getEntiID());
					siteConfigObj.setOrganization_id(laterObj2[i]);
					siteConfigObj.setLaterConfig1(laterObj1[i]);
					this.stationservice.saveLaterConfig(siteConfigObj);
				}
			} catch (Exception e) {
				this.map.put("returnStr", "error");
				e.printStackTrace();
			}
			this.map.put("resultStr", "success");
		} 
//		else {
//			try {
//				for(int i = 0; i < 2; i++){
//					siteConfigObj.setEnterprise_id(user.getEntiID());
//					siteConfigObj.setOrganization_id(laterObj2[i]);
//					siteConfigObj.setLaterConfig1(laterObj1[i]);
//					this.stationservice.saveRateConfig(siteConfigObj);
//				}
//				this.result.put("resultStr", "success");
//			} catch (Exception e){
//				this.map.put("returnStr", "error");
//				e.printStackTrace();
//			}
//		}
	}
	
	
	/**
	 * 新增油量监控阀值和周期
	 * @return
	 */
	public String addOilTimerConfig() {
		UserInfo user = getCurrentUser();
		SiteAddOilConfig siteConfigObj = new SiteAddOilConfig();
		siteConfigObj.setEnterprise_id(user.getEntiID());
//		siteConfigObj.setOrganization_id(user.getOrganizationID());
		try {
			siteConfigObj.setAddOilRate(this.addOilRate);
			siteConfigObj.setStartTimeQuantum(this.startTimeQuantum);
			siteConfigObj.setEndTimeQuantum(this.endTimeQuantum);
			siteConfigObj.setLowOilValue(this.lowOilValue);
			siteConfigObj.setOilRateCheck(this.oilRateCheck);
			siteConfigObj.setOilValueCheck(this.oilValueCheck);
			this.stationservice.saveRateConfig(siteConfigObj);
			this.result.put("resultStr", "success");
		} catch (Exception e) {
			logger.error("", e);
			this.result.put("resultStr", "error");
			return "error";
		}
		return "success";
	}

	/**
	 * 废弃
	 * @return
	 */
	public String updateOilTimerConfig() {
		UserInfo user = getCurrentUser();
		SiteAddOilConfig siteConfigObj = new SiteAddOilConfig();
		siteConfigObj.setEnterprise_id(user.getEntiID());
		siteConfigObj.setSiteConfigId(this.siteConfigId);
//		siteConfigObj.setOrganization_id(user.getOrganizationID());
		try {
			List<SiteAddOilConfig> list = this.stationservice.selectSiteConfig(
					"SitManage.selectSiteConfig", siteConfigObj);
			SiteAddOilConfig config = list.get(0);
			config.setStartTimeQuantum(this.startTimeQuantum);
			config.setEndTimeQuantum(this.endTimeQuantum);
			config.setAddOilRate(this.addOilRate);
			this.stationservice.saveRateConfig(
					config);
			this.result.put("resultStr", "success");
		} catch (Exception e) {
			e.printStackTrace();
			this.result.put("resultStr", "error");
			return "error";
		}
		return "success";
	}

	/**
	 * 初始化页面，读取油量监控设置
	 * 
	 * @return
	 */
	public String selectOilTimerConfig() {
		UserInfo user = getCurrentUser();
		SiteAddOilConfig siteConfigObj = new SiteAddOilConfig();
		siteConfigObj.setEnterprise_id(user.getEntiID());
//		siteConfigObj.setOrganization_id(user.getOrganizationID());
//		siteConfigObj.setSiteConfigId(this.siteConfigId);4f2d60b5-21f7-4954-91fe-169fe810d3d4
		try {
			this.oilConfigList = this.stationservice.selectSiteConfig(
					"SitManage.selectSiteConfig", siteConfigObj);
		} catch (Exception e) {
			logger.error(e);
			return "error";
		}
		return "success";
	}	

	/**
	 * 更新低油阀值
	 * 
	 * @return
	 */
	public String updateLowerOil() {
		UserInfo user = getCurrentUser();
		SiteAddOilConfig siteConfigObj = new SiteAddOilConfig();
		siteConfigObj.setEnterprise_id(user.getEntiID());
//		siteConfigObj.setOrganization_id(user.getOrganizationID());
		try {
			siteConfigObj.setAddOilRate(this.addOilRate);
			siteConfigObj.setStartTimeQuantum(this.startTimeQuantum);
			siteConfigObj.setEndTimeQuantum(this.endTimeQuantum);
			siteConfigObj.setLowOilValue(this.lowOilValue);
			siteConfigObj.setOilRateCheck(this.oilRateCheck);
			siteConfigObj.setOilValueCheck(this.oilValueCheck);
			siteConfigObj.setOilPrice(Float.parseFloat(this.oilPrice));
			this.stationservice.saveLowConfig(siteConfigObj);
			this.result.put("resultStr", "success");
		} catch (Exception e) {
			logger.error("", e);
			this.result.put("resultStr", "error");
			return "error";
		}
		return "success";
	}
	/**
	 * 查询指定的站点信息，包含车牌号和车架号
	 * @return
	 */
	public String selectStationSet() {
		String vehicleln = "";
		String vehicleVin = "";
		try {
			List<SitSet> list = this.stationservice.selectStationSet(
					"SitManage.selectStatiionSet", this.site_id);
			for (SitSet sitset : list) {
				vehicleln = vehicleln + "," + sitset.getVehicle_ln();
				vehicleVin = vehicleVin + "," + sitset.getVehicle_vin();
			}
			this.map.put("vehicle_ln", vehicleln.replaceFirst(",", ""));
			this.map.put("vehicle_vin", vehicleVin.replaceFirst(",", ""));
			this.map.put("returnStr", "success");
		} catch (Exception e) {
			logger.error("", e);
			this.map.put("returnStr", "error");
			return "error";
		}
		return "success";
	}

	public String selectTreeWin() {
		this.vehicle_ln = getRequest().getParameter("vehicle_ln");
		this.vehicle_vin = getRequest().getParameter("vehicle_vin");
		try {
			this.vehicle_ln=new String(vehicle_ln.getBytes("iso-8859-1"),"UTF-8");
			this.vehicle_vin=new String(vehicle_vin.getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
		this.vehicle_ln =vehicle_ln.replaceAll("'", "");
		return "success";
	}


	public String getEcontrol_station() {
		return this.econtrol_station;
	}

	public void setEcontrol_station(String econtrol_station) {
		this.econtrol_station = econtrol_station;
	}

	public String getEsite_name() {
		return this.esite_name;
	}

	public void setEsite_name(String esite_name) {
		this.esite_name = esite_name;
	}

	public String getSite_id_checked() {
		return this.site_id_checked;
	}

	public void setSite_id_checked(String site_id_checked) {
		this.site_id_checked = site_id_checked;
	}

	public int getRp() {
		return this.rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSortname() {
		return this.sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return this.sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getStationIdList() {
		return this.stationIdList;
	}

	public void setStationIdList(String stationIdList) {
		this.stationIdList = stationIdList;
	}

	public StationService getStationservice() {
		return this.stationservice;
	}

	public void setStationservice(StationService stationservice) {
		this.stationservice = stationservice;
	}

	public String getPointID() {
		return this.pointID;
	}

	public void setPointID(String pointID) {
		this.pointID = pointID;
	}

	public String getSite_name() {
		return this.site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getSichen_addr() {
		return this.sichen_addr;
	}

	public void setSichen_addr(String sichen_addr) {
		this.sichen_addr = sichen_addr;
	}

	public String getOrganization_id() {
		return this.organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public String getControl_station() {
		return this.control_station;
	}

	public void setControl_station(String control_station) {
		this.control_station = control_station;
	}

	public Map getResult() {
		return this.result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public List getStrList() {
		return this.strList;
	}

	public void setStrList(List strList) {
		this.strList = strList;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLonlatStr() {
		return this.lonlatStr;
	}

	public void setLonlatStr(String lonlatStr) {
		this.lonlatStr = lonlatStr;
	}

	public Service getService() {
		return this.service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Site> getSiteList() {
		return this.siteList;
	}

	public void setSiteList(List<Site> siteList) {
		this.siteList = siteList;
	}

	public String getSite_id() {
		return this.site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public Site getSite() {
		return this.site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map getMap() {
		return this.map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}

	public List<TerminalViBean> getNewlist() {
		return newlist;
	}

	public void setNewlist(List<TerminalViBean> newlist) {
		this.newlist = newlist;
	}

	public List<SiteAddOilConfig> getOilConfigList() {
		return oilConfigList;
	}

	public void setOilConfigList(List<SiteAddOilConfig> oilConfigList) {
		this.oilConfigList = oilConfigList;
	}

	public SiteAddOilConfig getSiteConfig() {
		return siteConfig;
	}

	public void setSiteConfig(SiteAddOilConfig siteConfig) {
		this.siteConfig = siteConfig;
	}

	public Integer getSiteConfigId() {
		return siteConfigId;
	}

	public void setSiteConfigId(Integer siteConfigId) {
		this.siteConfigId = siteConfigId;
	}

	public String getAddOilRate() {
		return addOilRate;
	}

	public void setAddOilRate(String addOilRate) {
		this.addOilRate = addOilRate;
	}

	public String getStartTimeQuantum() {
		return startTimeQuantum;
	}

	public void setStartTimeQuantum(String startTimeQuantum) {
		this.startTimeQuantum = startTimeQuantum;
	}

	public String getEndTimeQuantum() {
		return endTimeQuantum;
	}

	public void setEndTimeQuantum(String endTimeQuantum) {
		this.endTimeQuantum = endTimeQuantum;
	}

	public String getLowOilValue() {
		return lowOilValue;
	}

	public void setLowOilValue(String lowOilValue) {
		this.lowOilValue = lowOilValue;
	}

	public String getOilValueCheck() {
		return oilValueCheck;
	}

	public void setOilValueCheck(String oilValueCheck) {
		this.oilValueCheck = oilValueCheck;
	}

	public String getOilRateCheck() {
		return oilRateCheck;
	}

	public void setOilRateCheck(String oilRateCheck) {
		this.oilRateCheck = oilRateCheck;
	}

	public String getOilPrice() {
		return oilPrice;
	}

	public void setOilPrice(String oilPrice) {
		this.oilPrice = oilPrice;
	}


}
