package com.neusoft.clw.sysmanage.datamanage.routemanage.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.routemanageservice.RouteManageService;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.routemanage.domain.EnterpriseTreeInfo;
import com.neusoft.clw.sysmanage.datamanage.routemanage.domain.RouteInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

public class RoutemanageAction extends PaginationAction {
    /** service共通类 */
    private transient Service service;

    /** 显示数据list **/
    private List < RouteInfo > routeList;
    
    private List < EnterpriseTreeInfo > enterpriseInfo;
    
    /** 站点数据list **/
    private List < Site > stationList;

    /** 车辆列表 **/
    private List < VehcileInfo > vehcList;

    private String vehcileVin;

    private String vehicle_vin;

    private String carsIdNew;
    
    private String route_id;
    
    private String site_name;
    
    private String site_id;
    
    private String order_id;
    
    private String addSiteString;
    
    private String delSiteString;
    
    private String organization_id;
    
    //已删除的站点
    private String deleteStations;
    
    //已添加的站点
    private String addStations;

    private String upSiteStrings;
    
    private String downSiteStrings;
    
    public String getUpSiteStrings() {
        return upSiteStrings;
    }
    public void setUpSiteStrings(String upSiteStrings) {
        this.upSiteStrings = upSiteStrings;
    }
    public String getDownSiteStrings() {
        return downSiteStrings;
    }
    public void setDownSiteStrings(String downSiteStrings) {
        this.downSiteStrings = downSiteStrings;
    }

    /** 批量更新orgid */
    private transient RouteManageService routemanageservice;

    /** 检查线路编号唯一性 **/
    private String oldrouteCode;
    
    private Site site;
    
    private RouteInfo routeInfo;

    private String message = null;

    private final String FORBID = "forbid";
    
    
    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
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

	/**
	 * 添加页面初始化地图加载站点
	 * 
	 * @param
	 * @return
	 */
	public List<TerminalViBean> getMapList(String startTime, String endTime,String inout_flag,String control_station,String addstationids,String delstationids ) {
		List<TerminalViBean> newlist = new ArrayList();
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		// 取得session对象实例
		UserInfo user = (UserInfo) request.getSession().getAttribute(
				Constants.USER_SESSION_KEY);

		TerminalViBean tv = new TerminalViBean();
		tv.setORGANIZATION_ID(user.getOrganizationID());
		tv.setSTART_TIME(startTime);
		tv.setEND_TIME(endTime);
		tv.setCONTROL_STATION(control_station);
		tv.setAddstationids(addstationids);
		tv.setDelstationids(delstationids);
		
		if(inout_flag!=null && !"".equals(inout_flag)){
			tv.setSTATION_ADDR(inout_flag);
		}
		try {
			newlist = service.getObjects("RouteManage.getStationInfo",
					tv);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		gpsUtil gpsUtil = new gpsUtil();
		newlist = gpsUtil.getpost(newlist);
		return newlist;
	}
	
	/**
	 * 添加页面初始化地图加载站点
	 * 
	 * @param
	 * @return
	 */
	public List<TerminalViBean> getMapEditList(String startTime, String endTime,String inout_flag,String route_id,String control_station,String site_flag,String addstationids,String delstationids ) {
		List<TerminalViBean> newlist = new ArrayList();
		WebContext ctx = WebContextFactory.get();
		HttpServletRequest request = ctx.getHttpServletRequest();
		// 取得session对象实例
		UserInfo user = (UserInfo) request.getSession().getAttribute(
				Constants.USER_SESSION_KEY);

		TerminalViBean tv = new TerminalViBean();
		tv.setORGANIZATION_ID(user.getOrganizationID());
		tv.setSTART_TIME(startTime);
		tv.setEND_TIME(endTime);
		tv.setROUTE_ID(route_id);
		tv.setCONTROL_STATION(control_station);
		tv.setAddstationids(addstationids);
		tv.setDelstationids(delstationids);
		
		if(inout_flag!=null && !"".equals(inout_flag)){
			tv.setSTATION_ADDR(inout_flag);
		}
		if(site_flag!=null && !"".equals(site_flag)){
			tv.setSITE_FLAG(site_flag);
		}
		try {
			newlist = service.getObjects("RouteManage.getEditStationInfo",
					tv);
		} catch (BusinessException e) {
			e.printStackTrace();
		}

		gpsUtil gpsUtil = new gpsUtil();
		newlist = gpsUtil.getpost(newlist);
		return newlist;
	}

    /**
     * 查看线路列表
     * @return
     */
    public String routeBrowse() {
        final String browseTitle = getText("routeinfo.browse.title");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        try {
            if (null == routeInfo) {
                routeInfo = new RouteInfo();
            }
            routeInfo.setRoute_enterprise_id(user.getEntiID());
            routeInfo.setRoute_organization_id(user.getOrganizationID());

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            routeInfo.setSortname(sortName);
            routeInfo.setSortorder(sortOrder);

            totalCount = service.getCount("RouteManage.getCount", routeInfo);
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);

            routeList = (List < RouteInfo >) service.getObjectsByPage(
                    "RouteManage.getInfos", routeInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	
            this.map = getPagination(routeList, totalCount, pageIndex);// 转换map

            if (0 == routeList.size()) {
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
            this.setModuleId(MouldId.YTP_ROUTEMANAGE_QUREY_ID);
        } catch (BusinessException e) {
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 线路添加页面
     * @return
     */
    public String addBefore() {
        UserInfo user = getCurrentUser();
		if (null == site) {
			site = new Site();
		}
		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendar.add(Calendar.DATE, 0); // 得到前一天
		Calendar calendarweek = Calendar.getInstance();// 此时打印它获取的是系统当前时间
		calendarweek.add(Calendar.DATE, -7); // 得到前一周
		String todayStartDate = new SimpleDateFormat("yyyy-MM-dd").format(calendarweek
				.getTime()) + " 00:00:00";
		String todayEndDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar
				.getTime()) + " 23:59:59";
		site.setEnterprise_id(user.getEntiID());
		site.setOrganization_id(user.getOrganizationID());
		site.setStart_time(todayStartDate);
		site.setEnd_time(todayEndDate);
        return SUCCESS;
    }

    /**
     * 树获取页面
     * @return
     */
    public String getTreeInit() {
        final String addBefTitle = getText("route.gettree.title");
        log.info(addBefTitle);
        String tree_script = "";
        String ChooseEnterID_tree = "";
        try {

            Map < String, Object > map = new HashMap < String, Object >(4);
            /* 显示树 */
            UserInfo user = getCurrentUser();
            map.put("in_enterprise_id", user.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("VehicleManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
            if (null != routeInfo) {
                if (null != routeInfo.getRoute_organization_id()) {
                    Map < String, Object > enmap = new HashMap < String, Object >(
                            5);
                    enmap.put("in_enterprise_id", user.getOrganizationID());
                    enmap
                            .put("in_org_id", routeInfo
                                    .getRoute_organization_id());
                    enmap.put("out_flag", null);
                    enmap.put("out_message", null);
                    enmap.put("out_ref", null);
                    service
                            .getObject("VehicleManage.show_enterprise_id",
                                    enmap);
                    if ("0".equals(enmap.get("out_flag"))) {
                        ArrayList < VehcileInfo > enallid = (ArrayList < VehcileInfo >) enmap
                                .get("out_ref");
                        StringBuffer enid = new StringBuffer("");
                        for (int i = 0; i < enallid.size(); i++) {
                            VehcileInfo veinfo = enallid.get(i);
                            enid.append(veinfo.getEnterprise_id());
                            if (i < (enallid.size() - 1)) {
                                enid.append("|");
                            }
                        }
                        ChooseEnterID_tree = enid.toString();
                    }
                }
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
            ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                    ChooseEnterID_tree);
        }
        return SUCCESS;
    }

    /*
     * 添加线路
     */

    public String add() {
        if (null == routeInfo) {
            return addBefore();
        }
        if (null == site) {
        	site = new Site();
        }
        final String addTitle = getText("routeinfo.add.info");
        try {
            UserInfo user = getCurrentUser();
            routeInfo.setCreater(user.getUserID());
            routeInfo.setRoute_enterprise_id(user.getEntiID());
            //service.insert("RouteManage.insertrouteInfo", routeInfo);
            site.setSite_up_string(routeInfo.getSite_up_string());
            site.setSite_down_string(routeInfo.getSite_down_string());
            routemanageservice.addRouteMap(routeInfo,site);
        } catch (BusinessException e) {
            log.error(addTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("routeinfo.addsuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_ROUTEMANAGE_ADD_ID);
        return SUCCESS;
    }

    /**
     * 修改线路页面
     */
    public String editBefore() {
        final String editBefTitle = getText("route.editbefore.title");
        log.info(editBefTitle);
        try {
            if (null != routeInfo) {
                routeInfo = (RouteInfo) service.getObject(
                        "RouteManage.getRouteInfo", routeInfo);
                oldrouteCode = routeInfo.getRoute_code();
                if (null == routeInfo) {
                    setMessage("info.data.notexsist");
                    return ERROR;
                }
            }
            if (null == site) {
    			site = new Site();
    		}
            UserInfo user = getCurrentUser();
    		Calendar calendar = Calendar.getInstance();// 此时打印它获取的是系统当前时间
    		calendar.add(Calendar.DATE, 0); // 得到前一天
    		Calendar calendarweek = Calendar.getInstance();// 此时打印它获取的是系统当前时间
    		calendarweek.add(Calendar.DATE, -7); // 得到前一周
    		String todayStartDate = new SimpleDateFormat("yyyy-MM-dd").format(calendarweek
    				.getTime()) + " 00:00:00";
    		String todayEndDate = new SimpleDateFormat("yyyy-MM-dd").format(calendar
    				.getTime()) + " 23:59:59";
    		site.setEnterprise_id(user.getEntiID());
    		site.setOrganization_id(user.getOrganizationID());
    		site.setStart_time(todayStartDate);
    		site.setEnd_time(todayEndDate);

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 修改线路
     */
    public String updateRoute() {
        if (null == routeInfo) {
            return editBefore();
        }
        if (null == site) {
        	site = new Site();
        }
        final String editTitle = getText("routeinfo.update");
        try {
            UserInfo user = getCurrentUser();
            routeInfo.setModifier(user.getUserID());
            //service.update("RouteManage.updatebyRouteid", routeInfo);
            
            site.setSite_up_string(routeInfo.getSite_up_string());
            site.setSite_down_string(routeInfo.getSite_down_string());
            routemanageservice.editRouteMap(routeInfo,site);
            
        } catch (BusinessException e) {
            log.error(editTitle, e);
            addActionError("线路修改失败！");
            return ERROR;
        } 
        setMessage("routeinfo.editsuccess.message");
        // 设置操作描述
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_ROUTEMANAGE_UPDATE_ID);
        return SUCCESS;
    }

    /**
     * 删除线路
     */
    public String deleteRoute() {
        if (null == routeInfo) {
            return editBefore();
        }
        final String cancleTitle = getText("routeinfo.delete");
        try {
            UserInfo user = getCurrentUser();
            routeInfo.setVaset_user_id(user.getUserID());
            int i = service.getCount("RouteManage.getvehCount", routeInfo);
            if (i > 0) {
                setMessage("routeinfo.delete.nopermission");
                return FORBID;
            } else {
            	routemanageservice.deleteRouteStation(routeInfo);
            }
        } catch (BusinessException e) {
            log.error(cancleTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("routeinfo.delete.success");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_VEHMANAGE_CANCLE_ID);

        return SUCCESS;

    }
    
    /**
     * 组织机构树
     */
    public String getOrganizationTree() {
    	 UserInfo user = getCurrentUser();
    	 EnterpriseTreeInfo enterprise=new EnterpriseTreeInfo();
    	 enterprise.setId(user.getOrganizationID());
    	 
         	 try {
				enterpriseInfo = (List < EnterpriseTreeInfo >) service.getObjects(
				         "RouteManage.getOrganizationTreeData", enterprise);
			} catch (BusinessException e) {
				e.printStackTrace();
			}        	

             return SUCCESS;

    }

    /**
     * 已选上行站点列表
     */
    public String getUpStation() {
        try {
        	UserInfo user = getCurrentUser();
        	Site siteInfo = new Site();
        	siteInfo.setEnterprise_id(user.getEntiID());
        	siteInfo.setRoute_id(route_id);
        	siteInfo.setAddStations(addStations);
        	siteInfo.setDeleteStations(deleteStations);
        	
        	if(upSiteStrings != null && upSiteStrings.length() > 0) {
        	    siteInfo.setSite_up_string(upSiteStrings);
        	    List<Site> ret = (List < Site >) service.getObjects(
                        "RouteManage.getSelectedUpStationInfos", siteInfo);
        	    
        	    Map<String, Site> siteMap = new HashMap<String, Site>();
        	    for(Site tmp : ret) {
        	        siteMap.put(tmp.getSite_id(), tmp);
        	    }
        	    
        	    String[] siteArray = upSiteStrings.split(",");
        	    if(stationList == null) {
        	        stationList = new ArrayList<Site>();
        	    }
        	    for(int i = 0; i < siteArray.length; i++) {
        	        stationList.add(siteMap.get(siteArray[i]));
        	    }
        	    
        	} else {
        	    stationList = (List < Site >) service.getObjects(
                        "RouteManage.getUpStationInfos", siteInfo);
        	}
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * 已选下行站点列表
     */
    public String getDownStation() {
        try {
        	UserInfo user = getCurrentUser();
        	Site siteInfo = new Site();
        	siteInfo.setEnterprise_id(user.getEntiID());
        	siteInfo.setRoute_id(route_id);
        	siteInfo.setAddStations(addStations);
        	siteInfo.setDeleteStations(deleteStations);
        	
        	if(downSiteStrings != null && downSiteStrings.length() > 0) {
                siteInfo.setSite_down_string(downSiteStrings);
                List<Site> ret = (List < Site >) service.getObjects(
                        "RouteManage.getSelectedDownStationInfos", siteInfo);
                
                Map<String, Site> siteMap = new HashMap<String, Site>();
                for(Site tmp : ret) {
                    siteMap.put(tmp.getSite_id(), tmp);
                }
                
                String[] siteArray = downSiteStrings.split(",");
                if(stationList == null) {
                    stationList = new ArrayList<Site>();
                }
                for(int i = 0; i < siteArray.length; i++) {
                    stationList.add(siteMap.get(siteArray[i]));
                }
                
            } else {
                stationList = (List < Site >) service.getObjects(
                        "RouteManage.getDownStationInfos", siteInfo);
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            return ERROR;
        }

        return SUCCESS;
    }
    
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPaginationList(List vehcList, int totalCount) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < vehcList.size(); i++) {

            VehcileInfo s = (VehcileInfo) vehcList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVehicle_vin());

            cellMap.put("cell", new Object[] {null, s.getVehicle_vin(),
                    s.getVehicle_ln(), s.getVehicle_code(),
                    s.getShort_allname(), s.getVehicle_id(), s.getRoute_id() });

            mapList.add(cellMap);

        }

        mapData.put("page", "1");// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }


    /**
     * 判断线路编码唯一性
     * @param
     * @return
     */
    public boolean checkRouteCodeUnique(String routeCode, String enid) {
        boolean ret = false;

        try {
            RouteInfo routeInfo = new RouteInfo();
            routeInfo.setRoute_code(routeCode);
            routeInfo.setRoute_enterprise_id(enid);
            int cnt = service.getCount("RouteManage.getCountforroutecode",
                    routeInfo);
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("终端号查询DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
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
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    /**
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */

    public Map getPagination(List routeList, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < routeList.size(); i++) {
            RouteInfo s = (RouteInfo) routeList.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getRoute_id());
            cellMap.put("cell", new Object[] {s.getRoute_name(), s.getRoute_incharge_person(),
                    s.getRoute_phone(), s.getUpsitenum(),s.getDownsitenum(),null,
                    s.getRoute_remark()});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    public String getUserRouteFind(){
    	UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        try {
            if (null == routeInfo) {
                routeInfo = new RouteInfo();
            }
            routeInfo.setRoute_enterprise_id(user.getEntiID());
            routeInfo.setRoute_organization_id(user.getOrganizationID());

            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            routeInfo.setSortname(sortName);
            routeInfo.setSortorder(sortOrder);
         

            routeList = (List < RouteInfo >) service.getObjects(
                    "RouteManage.getInfos", routeInfo);
        	this.map.put("message", "success");
            this.map.put("list", routeList);
        } catch (BusinessException e) {
            return ERROR;
        }

        return SUCCESS;
    }
    
    public String getDeleteStations() {
		return deleteStations;
	}

	public void setDeleteStations(String deleteStations) {
		this.deleteStations = deleteStations;
	}

	public String getAddStations() {
		return addStations;
	}

	public void setAddStations(String addStations) {
		this.addStations = addStations;
	}

	private Map map = new HashMap();

    public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	/**
     * @return the map
     */
    public Map getMap() {
        return map;
    }

    /**
     * @param map the map to set
     */
    public void setMap(Map map) {
        this.map = map;
    }
    
	public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

	public List<EnterpriseTreeInfo> getEnterpriseInfo() {
		return enterpriseInfo;
	}

	public void setEnterpriseInfo(List<EnterpriseTreeInfo> enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}

	public String getAddSiteString() {
		return addSiteString;
	}

	public void setAddSiteString(String addSiteString) {
		this.addSiteString = addSiteString;
	}

	public String getDelSiteString() {
		return delSiteString;
	}

	public void setDelSiteString(String delSiteString) {
		this.delSiteString = delSiteString;
	}

	public String getSite_id() {
		return site_id;
	}

	public void setSite_id(String site_id) {
		this.site_id = site_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public List<Site> getStationList() {
		return stationList;
	}

	public void setStationList(List<Site> stationList) {
		this.stationList = stationList;
	}

	public String getOldrouteCode() {
        return oldrouteCode;
    }

    public void setOldrouteCode(String oldrouteCode) {
        this.oldrouteCode = oldrouteCode;
    }

    public RouteManageService getRoutemanageservice() {
        return routemanageservice;
    }

    public void setRoutemanageservice(RouteManageService routemanageservice) {
        this.routemanageservice = routemanageservice;
    }

    public String getCarsIdNew() {
        return carsIdNew;
    }

    public void setCarsIdNew(String carsIdNew) {
        this.carsIdNew = carsIdNew;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getVehcileVin() {
        return vehcileVin;
    }

    public void setVehcileVin(String vehcileVin) {
        this.vehcileVin = vehcileVin;
    }

    public List < VehcileInfo > getVehcList() {
        return vehcList;
    }

    public void setVehcList(List < VehcileInfo > vehcList) {
        this.vehcList = vehcList;
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

    public List < RouteInfo > getRouteList() {
        return routeList;
    }

    public void setRouteList(List < RouteInfo > routeList) {
        this.routeList = routeList;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }
    
    public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getSite_name() {
		return site_name;
	}

	public void setSite_name(String site_name) {
		this.site_name = site_name;
	}
}
