package com.neusoft.clw.infomanage.sitemanage.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.stationservice.StationService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;
import com.neusoft.clw.sysmanage.datamanage.routemanage.domain.RouteInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class SiteManageAction extends PaginationAction {
	/** service共通类 */
	private transient Service service;

	/** 显示数据list **/
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
	private String switchsite;
	private String areaid;
	/**
	 * 列表信息页面
	 * 
	 * @return
	 */
	public String readyPage() {
		if (null != message) {
			addActionMessage(getText(message));
		}
		UserInfo user = getCurrentUser();
		if (null == site) {
			site = new Site();
		}
		if(switchsite==null)
			switchsite = "0";
		else
			switchsite = "1";
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, 0); // 得到前一天
		//calendar.add(Calendar.MONTH, -1);// 得到前一月
		String todayStartDate = DateUtil.getMonthFirstDay();
		String todayEndDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar
				.getTime()) + " 23:59:59";

		site.setEnterprise_id(user.getEntiID());
		site.setOrganization_id(user.getOrganizationID());
		site.setStart_time(todayStartDate);
		site.setEnd_time(todayEndDate);
		return SUCCESS;
	}

	/**
	 * 查看站点列表
	 * 
	 * @return
	 */
	public String siteBrowse() {
		final String browseTitle = getText("stationmanage.browse.title");
		int totalCount = 0;
		UserInfo user = getCurrentUser();
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

		try {
			if (null == site) {
				site = new Site();
			}
			site.setEnterprise_id(user.getEntiID());
			site.setOrganization_id(user.getOrganizationID());

			String rpNum = request.getParameter("rp");
			String pageIndex = request.getParameter("page");
			String sortName = request.getParameter("sortname");
			String sortOrder = request.getParameter("sortorder");
			
			//增加中文排序 add by fanxy
			 sortName = "nlssort(" + sortName+ ",'NLS_SORT=SCHINESE_PINYIN_M')";

			site.setSortname(sortName);
			site.setSortorder(sortOrder);

			totalCount = service.getCount("StationManage.getCount", site);

			siteList = (List<Site>) service.getObjectsByPage(
					"StationManage.getInfos", site, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));

			this.map = getPagination(siteList, totalCount, pageIndex, rpNum);// 转换map

			if (0 == siteList.size()) {
				this.addActionMessage(getText("nodata.list"));
			}
			if (null != message) {
				addActionMessage(getText(message));
			}
			// 设置操作描述
			this.addOperationLog(formatLog(browseTitle, null));
			// 设置操作类型
			this.setOperationType(Constants.SELECT);
			// 设置所属应用系统
			this.setApplyId(Constants.XC_P_CODE);
			// 设置所属模块
			this.setModuleId(MouldId.XCP_SITEMANAGE_QUERY);
		} catch (BusinessException e) {
			return ERROR;
		}

		return SUCCESS;
	}

	/**
	 * 站点线路
	 * 
	 * @return
	 */

	public String add() {
		if (null == site) {
			site = new Site();
		}
		final String addTitle = getText("stationmanage.add.info");
		try {
			String insertID="";
			String orderID="";
			UserInfo user = getCurrentUser();
			site.setCreater(user.getUserID());
			site.setEnterprise_id(user.getEntiID());
			site.setOrganization_id(user.getEntiID());
			site.setSite_name(site_name);
			site.setControl_station(control_station);
			site.setSichen_addr(sichen_addr);
			site.setSite_latitude(latitude);
			site.setSite_longitude(longitude);
			sortname = "nlssort(" + sortname+ ",'NLS_SORT=SCHINESE_PINYIN_M')";
			site.setSortname(sortname);
			site.setSortorder(sortorder);
			site.setEorganization_id(user.getOrganizationID());
			site.setEcontrol_station(econtrol_station);
			site.setEsite_name(esite_name);
			site.setSite_id(site_id);
//			if(pointID!=null){
//				insertID=stationservice.insertPointToStation(pointID, site);
			if(site.getSite_id()!=null){
				//insertID=stationservice.insertPointToStation(pointID, site);
				service.update("StationManage.updateInfobyLeftStation", site);
				insertID=stationservice.insertStation("StationManage.insertStationInfo", site);
			}else{
				insertID=stationservice.insertStation("StationManage.insertStationInfo", site);
			}
			orderID=(String) service.getObject("StationManage.getAddStationOrder",site);
			result.put("returns", "success");
			result.put("returnID", insertID);
			result.put("returnOrder", orderID);
			
		} catch (BusinessException e) {
			log.error(addTitle, e);
			addActionError(e.getMessage());
			result.put("returns", "error");
			return ERROR;
		}catch (DataAccessIntegrityViolationException e) {
			log.error(addTitle, e);
			addActionError(e.getMessage());
			result.put("returns", "error");
			return ERROR;
		} catch (DataAccessException e) {
			log.error(addTitle, e);
			addActionError(e.getMessage());
			result.put("returns", "error");
			return ERROR;
		}
		// 设置操作描述
		this.addOperationLog(formatLog(addTitle, null));
		// 设置操作类型
		this.setOperationType(Constants.INSERT);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_SITEMANAGE_ADD);
		return SUCCESS;
	}

	/**
	 * 修改站点
	 * 
	 * @return
	 */
	public String updateStation() {
		if (null == site) {
			site = new Site();
		}
		final String editTitle = getText("stationmanage.update.info");
		try {
			UserInfo user = getCurrentUser();
			site.setModifier(user.getUserID());			
			site.setSite_name(site_name);			
			site.setSichen_addr(sichen_addr);
			site.setSite_latitude(latitude);
			site.setSite_longitude(longitude);
			site.setSite_id(site_id);
			
			//int value = service.getCount("StationManage.infobyStationIdInRoute", site_id);
			
			//if(value==0) {
				result.put("returns", "success");
				service.update("StationManage.updateInfobyStationId", site);
			//} else {
				result.put("returns", "allready");
			//}
		} catch (BusinessException e) {
			log.error(editTitle, e);
			addActionError(e.getMessage());
			result.put("returns", "error");
			return ERROR;
		}
		// 设置操作描述
		this.addOperationLog(formatLog(editTitle, null));
		// 设置操作类型
		this.setOperationType(Constants.UPDATE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_SITEMANAGE_MODIFY);
		return SUCCESS;
	}

	/**
	 * 删除站点
	 * 
	 * @return
	 */
	public String deleteStation() {
		if (null == site) {
			site = new Site();
		}
		site.setSite_id(site_id);
		final String cancleTitle = getText("stationmanage.deletebefore.title");
		try {
			UserInfo user = getCurrentUser();
			site.setVaset_user_id(user.getUserID());
			int i = service.getCount("StationManage.getStaCount", site);
			if (i > 0) {
				result.put("returns", "forbid");
				return FORBID;
			} else {
				service.update("StationManage.deletebyStationId", site);
				result.put("returns", "success");
			}
		} catch (BusinessException e) {
			log.error(cancleTitle, e);
			addActionError(e.getMessage());
			result.put("returns", "error");
			return ERROR;
		}
		setMessage("stationmanage.deletesuccess.message");
		// 设置操作描述
		this.addOperationLog(formatLog(cancleTitle, null));
		// 设置操作类型
		this.setOperationType(Constants.DELETE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_SITEMANAGE_DELETE);
		return SUCCESS;
	}
	
	/**
	 * 批量删除站点
	 * 
	 * @return
	 */
	public String deleteBatchStation() {
		if (null == site) {
			site = new Site();
		}
		site.setSite_id(stationIdList);
		final String cancleTitle = getText("stationmanage.deletebefore.title");
		try {
			UserInfo user = getCurrentUser();
			site.setVaset_user_id(user.getUserID());
			int i = service.getCount("StationManage.getStaCount", site);
			if (i > 0) {
				result.put("returns", "forbid");
				return FORBID;
			} else {
				service.update("StationManage.deletebyStationIdList", site);
				result.put("returns", "success");
			}
		} catch (BusinessException e) {
			log.error(cancleTitle, e);
			addActionError(e.getMessage());
			result.put("returns", "error");
			return ERROR;
		}
		setMessage("stationmanage.deletesuccess.message");
		// 设置操作描述
		this.addOperationLog(formatLog(cancleTitle, null));
		// 设置操作类型
		this.setOperationType(Constants.DELETE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_SITEMANAGE_DELETE);
		return SUCCESS;
	}

	private String formatIds(String regions) {
		String ret = "";
		String[] strs = regions.split(",");
		for (int i = 0; i < strs.length; i++) {
			String tmp = strs[i];
			if (null != tmp && tmp.length() > 0) {
				tmp = tmp.substring(1, tmp.length() - 1);
				strs[i] = tmp;
			}
		}
		for (int i = 0; i < strs.length; i++) {
			if (ret == "") {
				ret = ret.concat(strs[i]);
			} else {
				ret = ret.concat(",").concat(strs[i]);
			}
		}
		return ret;
	}

	/**
	 * 格式化日志信息
	 * 
	 * @param desc
	 * @param Object
	 * @return
	 */
	protected String formatLog(String desc, RouteInfo routeObj) {
		StringBuffer sb = new StringBuffer();
		if (null != desc) {
			sb.append(desc);
		}
		if (null != routeObj) {
			if (null != routeObj.getRoute_id()) {
				OperateLogFormator
						.format(sb, "routeid", routeObj.getRoute_id());
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

	/**
	 * 转换Map
	 * 
	 * @param oilusedList
	 * @param totalCountDay
	 * @param pageIndex
	 * @return
	 */

	public Map getPagination(List siteList, int totalCount, String pageIndex,
			String rpNum) {
		List mapList = new ArrayList();
		Map mapData = new LinkedHashMap();
		for (int i = 0; i < siteList.size(); i++) {
			Site s = (Site) siteList.get(i);
			Map cellMap = new LinkedHashMap();
			cellMap.put("id", s.getSite_id());
			cellMap.put("cell", new Object[] {
					(i + 1) + (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(rpNum), 				
					s.getSite_name() == null || "null".equals(s.getSite_name()) ? "" : s.getSite_name(),	
					s.getSite_longitude(),
					s.getSite_latitude(),
					s.getSichen_addr() == null || "null".equals(s.getSichen_addr()) ? "" : s.getSichen_addr(),
					s.getOrganization_id(),
					s.getOrganizationName(),					
					"",
					s.getSite_id()
					});
			mapList.add(cellMap);
		}
		mapData.put("page", pageIndex);// 从前台获取当前第page页
		mapData.put("total", totalCount);// 从数据库获取总记录数
		mapData.put("rows", mapList);

		return mapData;
	}

	/**
	 * 取得真实经纬度坐标
	 * */
	public String getLONLATString(String longitude, String latitude) {
		gpsUtil gpsutil = new gpsUtil();
		String lonlatString = "";
		// 偏转经纬度信息
		lonlatString = gpsutil.getOneXY(longitude, latitude);
		return lonlatString;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getInout_flag() {
		return inout_flag;
	}

	public void setInout_flag(String inout_flag) {
		this.inout_flag = inout_flag;
	}
	
	/**
	 * 取得单元素真实经纬度坐标
	 * */
public static void main(String[] args) {
	gpsUtil gpsutil = new gpsUtil();
	System.out.println(gpsutil.getOneXY("113.68535166666666","34.68983"));
}
	public void getSingleLONLAT() {
		gpsUtil gpsutil = new gpsUtil();
		String lonlatString = "";
		
		long startTime=System.currentTimeMillis();   //获取开始时间
		// 偏转经纬度信息
		lonlatString = gpsutil.getOneXY(longitude, latitude);
		
		if(lonlatString==null){
			lonlatString=longitude+","+latitude;
		}
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        try {
            response.getWriter().write(lonlatString);
        } catch (IOException ioException) {
            ;
        }
		long endTime=System.currentTimeMillis(); //获取结束时间
		log.info("获取单个经纬度坐标偏转，用时："+(endTime-startTime)+"ms");
	}

	public String getMapSearchList(){
		List<TerminalViBean> innewlist = new ArrayList();
		UserInfo user = getCurrentUser();
		TerminalViBean tv = new TerminalViBean();
		tv.setORGANIZATION_ID(user.getOrganizationID());
		tv.setSTART_TIME(startTime);
		tv.setEND_TIME(endTime);
		
		if(inout_flag!=null && !"".equals(inout_flag)){
			tv.setINOUT_FLAG(inout_flag);
		}
		long startTime1=System.currentTimeMillis();   //获取开始时间
		try {
			innewlist = service.getObjects("StationManage.getLong_Latitude_List",
					tv);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		long endTime1=System.currentTimeMillis(); //获取结束时间
		log.info("根据右侧条件查询站点，获取数据库中信息，用时："+(endTime1-startTime1)+"ms");
		
		long startTime2=System.currentTimeMillis();   //获取开始时间
		gpsUtil gpsUtil = new gpsUtil();
		newlist = gpsUtil.getpost(innewlist);
		
		long endTime2=System.currentTimeMillis(); //获取结束时间
		log.info("根据右侧条件查询站点，获取批量经纬度坐标偏转，用时："+(endTime2-startTime2)+"ms");
		return SUCCESS;
	}
	
	public String getLeftList(){
		List<TerminalViBean> innewlist = new ArrayList();
		UserInfo user = getCurrentUser();
		try {
			if (null == site) {
				site = new Site();
			}
			TerminalViBean tv = new TerminalViBean();
			tv.setORGANIZATION_ID(user.getOrganizationID());
			tv.setSortname("nlssort(" + sortname+ ",'NLS_SORT=SCHINESE_PINYIN_M')");
			tv.setSortorder(sortorder);
			if(null!=site_name){
				tv.setSITE_NAME(site_name);
			}
			if(null!=control_station){
				tv.setCONTROL_STATION(control_station);
			}
			
			long startTime1=System.currentTimeMillis();   //获取开始时间
			innewlist = service.getObjectsByPage(
					"StationManage.getLeftListInfos", tv, (page - 1)
							* rp, rp);
			long endTime1=System.currentTimeMillis(); //获取结束时间
			log.info("根据左侧列表查询站点，获取数据库中信息，用时："+(endTime1-startTime1)+"ms");
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		
		
		long startTime2=System.currentTimeMillis();   //获取开始时间
		gpsUtil gpsUtil = new gpsUtil();
		newlist = gpsUtil.getpost(innewlist);
		
		long endTime2=System.currentTimeMillis(); //获取结束时间
		log.info("根据左侧列表查询站点，获取批量经纬度坐标偏转，用时："+(endTime2-startTime2)+"ms");
		
		return SUCCESS;
	}
	
	public String getCheckedLeftList(){
		List<TerminalViBean> innewlist = new ArrayList();
		List<TerminalViBean> inafterlist = new ArrayList<TerminalViBean>();  
		UserInfo user = getCurrentUser();
		try {
			if (null == site) {
				site = new Site();
			}
			TerminalViBean tv = new TerminalViBean();
			tv.setORGANIZATION_ID(user.getOrganizationID());
			tv.setSortname("nlssort(" + sortname+ ",'NLS_SORT=SCHINESE_PINYIN_M')");
			tv.setSortorder(sortorder);
			
			String [] sites = site_id_checked.split(",");
			if(!site_id_checked.equals("")){
				String site_ids="";
				for(int j=0;j<sites.length;j++){
					if(sites.length>1&&sites.length!=j+1){
						site_ids+="'"+sites[j]+"',";
					}else{
						site_ids+="'"+sites[j]+"'";
					}
				}
				tv.setCR_CONFIG_ID(site_id_checked);
			}else{
				
				tv.setCR_CONFIG_ID("'0000000000'");
			}
			if(null!=site_name){
				tv.setSITE_NAME(site_name);
			}
			if(null!=control_station){
				tv.setCONTROL_STATION(control_station);
			}
			
			long startTime1=System.currentTimeMillis();   //获取开始时间
			
			tv.setPage(page);
			tv.setRp(rp);
			innewlist = service.getObjects(
					"StationManage.getCheckedLeftList", tv);
			/*Iterator<TerminalViBean> iter = innewlist.iterator();
			while(iter.hasNext()){
	        	if(site_id_checked.indexOf(iter.next().getID())>-1){
	        		System.out.println("same=========="+iter.next().getID()+","+iter.next().getDIRECTION());
	        		//inafterlist.add(iter.next());
	        		innewlist.remove(iter.next());
				}
			}*/
			long endTime1=System.currentTimeMillis(); //获取结束时间
			log.info("根据左侧列表“CHECKBOX已选”查询站点，获取数据库中信息，用时："+(endTime1-startTime1)+"ms");
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		long startTime2=System.currentTimeMillis();   //获取开始时间
		gpsUtil gpsUtil = new gpsUtil();
		newlist = gpsUtil.getpost(innewlist);
		long endTime2=System.currentTimeMillis(); //获取结束时间
		log.info("根据左侧列表“CHECKBOX已选”查询站点，获取批量经纬度坐标偏转，用时："+(endTime2-startTime2)+"ms");
		
		return SUCCESS;
	}

	/**
	 * 删除采集站点
	 * 
	 * @return
	 */
	public void deleteCollectionByID() {
		final String cancleTitle = getText("stationcollection.deletebefore.title");
		try {
			TerminalViBean tv = new TerminalViBean();
			tv.setID(site_id);
			service.update("StationManage.deletebyCollection_ID", tv);
		} catch (BusinessException e) {
			log.error(cancleTitle, e);
			addActionError(e.getMessage());
		}
		// 设置操作描述
		this.addOperationLog(formatLog(cancleTitle, null));
		// 设置操作类型
		this.setOperationType(Constants.DELETE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_STATIONCOLLECTION_DELETE);
	}
	
	/**
	 * 修改采集站点
	 * 
	 * @return
	 */
	public void updateCollectionByID() {
		
		final String cancleTitle = getText("stationcollection.update.title");
		try {
			TerminalViBean tv = new TerminalViBean();
			tv.setID(site_id);
			tv.setLONGITUDE(longitude);
			tv.setLATITUDE(latitude);
			service.update("StationManage.updatebyCollection_ID", tv);
		} catch (BusinessException e) {
			log.error(cancleTitle, e);
			addActionError(e.getMessage());
		}
		// 设置操作描述
		this.addOperationLog(formatLog(cancleTitle, null));
		// 设置操作类型
		this.setOperationType(Constants.UPDATE);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_STATIONCOLLECTION_ADD);

	}
	
	/**
	 * 判断站点是否关联线路
	 * add by chengjin
	 */
	public void getStationFlag(){
		MDC.put("[modulename]", "[getstationflag]");
		log.info("[site_id:"+site_id+"]获取站点是否关联线路开始");
		String  stationflag= "no";
		HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        try{
        	 int i = service.getCount("StationManage.getflagCount", site_id);
        	 if (i > 0) {
        		 stationflag="yes";
     		} else {
     			stationflag="no";
     		}
        }catch(Exception e){
        	log.error("获取站点是否关联线路查询时出错:",e);
        }

        
        try {
            response.getWriter().write(stationflag);
        } catch (Exception e) {
        	log.error("获取站点是否关联线路写流时出错:",e);
        }
		log.info("获取站点是否关联线路结束");
	}
	
	public String getEcontrol_station() {
		return econtrol_station;
	}

	public void setEcontrol_station(String econtrol_station) {
		this.econtrol_station = econtrol_station;
	}

	public String getEsite_name() {
		return esite_name;
	}

	public void setEsite_name(String esite_name) {
		this.esite_name = esite_name;
	}

	public String getSite_id_checked() {
		return site_id_checked;
	}

	public void setSite_id_checked(String site_id_checked) {
		this.site_id_checked = site_id_checked;
	}

	public List<TerminalViBean> getNewlist() {
		return newlist;
	}

	public void setNewlist(List<TerminalViBean> newlist) {
		this.newlist = newlist;
	}

	public int getRp() {
		return rp;
	}

	public void setRp(int rp) {
		this.rp = rp;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getStationIdList() {
		return stationIdList;
	}

	public void setStationIdList(String stationIdList) {
		this.stationIdList = stationIdList;
	}

	public StationService getStationservice() {
		return stationservice;
	}

	public void setStationservice(StationService stationservice) {
		this.stationservice = stationservice;
	}

	public String getPointID() {
		return pointID;
	}

	public void setPointID(String pointID) {
		this.pointID = pointID;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getSichen_addr() {
		return sichen_addr;
	}

	public void setSichen_addr(String sichen_addr) {
		this.sichen_addr = sichen_addr;
	}

	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public String getControl_station() {
		return control_station;
	}

	public void setControl_station(String control_station) {
		this.control_station = control_station;
	}

	public Map getResult() {
		return result;
	}

	public void setResult(Map result) {
		this.result = result;
	}

	public List getStrList() {
		return strList;
	}

	public void setStrList(List strList) {
		this.strList = strList;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLonlatStr() {
		return lonlatStr;
	}

	public void setLonlatStr(String lonlatStr) {
		this.lonlatStr = lonlatStr;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public List<Site> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<Site> siteList) {
		this.siteList = siteList;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	public String getSwitchsite() {
		return switchsite;
	}

	public void setSwitchsite(String switchsite) {
		this.switchsite = switchsite;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}
	
}
