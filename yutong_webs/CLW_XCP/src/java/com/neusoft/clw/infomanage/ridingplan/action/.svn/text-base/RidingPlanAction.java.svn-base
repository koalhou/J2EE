package com.neusoft.clw.infomanage.ridingplan.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.service.ridingplanservice.RidingPlanService;
import com.neusoft.clw.common.service.writegzpackservice.Writegzpackservice;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.infomanage.ridingplan.domain.RidingPlanInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.RidingReady;
import com.neusoft.clw.infomanage.ridingplan.domain.RouteInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.SiteInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.VehdriverInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.VehsichenInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.VssInfo;
import com.neusoft.clw.infomanage.ridingplan.domain.Vss_SiteInfo;
import com.neusoft.clw.infomanage.stewardmanage.domain.StewardInfo;
import com.neusoft.clw.infomationExport.SendRouteFile;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.opensymphony.xwork2.ActionContext;

/**
 * 乘车规划管理
 * @author
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */
public class RidingPlanAction extends PaginationAction {

    private List < RidingPlanInfo > ridingPlanList;

    private RidingPlanInfo ridingPlanInfo;

    private String vehicle_ln;

    private String chooseorgid;
    
    /** 显示消息 * */
    private String message = null;
    /**
     * 错误提示信息
     */
    private String errorMessage;
    
    private String user_org_id;
    
    private List < RouteInfo > routeList;
    
    private RouteInfo routeInfo;
    
    private SiteInfo siteInfo;
    
    private DriverInfo driverInfo;
    
    private StewardInfo sichenInfo;
    
    private List<SiteInfo> siteList;
    
    private List < VehcileInfo > vehcList;
    
    private List < DriverInfo > driverList;
    
    private List < StewardInfo > stewardList;
    
    private String vehicle_vin;
    
    private String vehicle_vin_old;
    
    private String driver_ids;
    
    private String steward_ids;
    
    private String siteid;
    
    private String route_id;
    
    private String upstudentids;
    
    private String downstudentids;
    
    private String studentids;
    
    private String flag;
    
    private RidingReady ridingReady;
    
    private String update;
                      
    private String vehiclevin;
    
    private String routename;
    
    //用于判断学生列表，展示上行还是下行
    private String studentListFlag;
    
    //用于判断选择是首条还是末条
    private String selectRow;
    
    //用于已选学生列表前的站点名称
    private String sitename;
    
    //上行不显示的学生
    private String upnotexist;
    //下行不显示的学生
    private String downnotexist;
    //上行删除已选的学生
    private String delupexistdata;
    //下行删除已选的学生
    private String deldownexistdata;
    
    private String iniDefaultPath;
	private String ip;
	private String port;
	private String username;
	private String userpass;
	private String iniDefaultPathFlag;
	private SendCommandClient sendCommandClient;
	private Writegzpackservice writegzpackservice;
	
	private String trip_id;
	
    public String getDelupexistdata() {
		return delupexistdata;
	}

	public void setDelupexistdata(String delupexistdata) {
		this.delupexistdata = delupexistdata;
	}

	public String getDeldownexistdata() {
		return deldownexistdata;
	}

	public void setDeldownexistdata(String deldownexistdata) {
		this.deldownexistdata = deldownexistdata;
	}

	public String getUpnotexist() {
		return upnotexist;
	}

	public void setUpnotexist(String upnotexist) {
		this.upnotexist = upnotexist;
	}

	public String getDownnotexist() {
		return downnotexist;
	}

	public void setDownnotexist(String downnotexist) {
		this.downnotexist = downnotexist;
	}

	public Writegzpackservice getWritegzpackservice() {
		return writegzpackservice;
	}

	public void setWritegzpackservice(Writegzpackservice writegzpackservice) {
		this.writegzpackservice = writegzpackservice;
	}

	public String getTrip_id() {
		return trip_id;
	}

	public void setTrip_id(String trip_id) {
		this.trip_id = trip_id;
	}

	public String getIniDefaultPath() {
		return iniDefaultPath;
	}

	public void setIniDefaultPath(String iniDefaultPath) {
		this.iniDefaultPath = iniDefaultPath;
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

	public String getIniDefaultPathFlag() {
		return iniDefaultPathFlag;
	}

	public void setIniDefaultPathFlag(String iniDefaultPathFlag) {
		this.iniDefaultPathFlag = iniDefaultPathFlag;
	}

	public SendCommandClient getSendCommandClient() {
		return sendCommandClient;
	}

	public void setSendCommandClient(SendCommandClient sendCommandClient) {
		this.sendCommandClient = sendCommandClient;
	}

	public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getStudentListFlag() {
		return studentListFlag;
	}

	public void setStudentListFlag(String studentListFlag) {
		this.studentListFlag = studentListFlag;
	}

	public String getSelectRow() {
		return selectRow;
	}

	public void setSelectRow(String selectRow) {
		this.selectRow = selectRow;
	}

	public String getVehiclevin() {
		return vehiclevin;
	}

	public void setVehiclevin(String vehiclevin) {
		this.vehiclevin = vehiclevin;
	}

	public String getRoutename() {
		return routename;
	}

	public void setRoutename(String routename) {
		this.routename = routename;
	}

	public String getStudentids() {
		return studentids;
	}

	public void setStudentids(String studentids) {
		this.studentids = studentids;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getRoute_id() {
		return route_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
	}

	public String getVehicle_vin_old() {
		return vehicle_vin_old;
	}

	public void setVehicle_vin_old(String vehicle_vin_old) {
		this.vehicle_vin_old = vehicle_vin_old;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public StewardInfo getSichenInfo() {
		return sichenInfo;
	}

	public void setSichenInfo(StewardInfo sichenInfo) {
		this.sichenInfo = sichenInfo;
	}

	public RidingReady getRidingReady() {
		return ridingReady;
	}

	public void setRidingReady(RidingReady ridingReady) {
		this.ridingReady = ridingReady;
	}

	public String getDriver_ids() {
		return driver_ids;
	}

	public void setDriver_ids(String driver_ids) {
		this.driver_ids = driver_ids;
	}

	public String getSteward_ids() {
		return steward_ids;
	}

	public void setSteward_ids(String steward_ids) {
		this.steward_ids = steward_ids;
	}

	public DriverInfo getDriverInfo() {
		return driverInfo;
	}

	public void setDriverInfo(DriverInfo driverInfo) {
		this.driverInfo = driverInfo;
	}

	public StewardInfo getStewardInfo() {
		return sichenInfo;
	}

	public void setStewardInfo(StewardInfo stewardInfo) {
		this.sichenInfo = stewardInfo;
	}

	public List<DriverInfo> getDriverList() {
		return driverList;
	}

	public void setDriverList(List<DriverInfo> driverList) {
		this.driverList = driverList;
	}

	public List<StewardInfo> getStewardList() {
		return stewardList;
	}

	public void setStewardList(List<StewardInfo> stewardList) {
		this.stewardList = stewardList;
	}

	public List<VehcileInfo> getVehcList() {
		return vehcList;
	}

	public void setVehcList(List<VehcileInfo> vehcList) {
		this.vehcList = vehcList;
	}

	public String getVehicle_vin() {
		return vehicle_vin;
	}

	public void setVehicle_vin(String vehicle_vin) {
		this.vehicle_vin = vehicle_vin;
	}

	public String getUpstudentids() {
		return upstudentids;
	}

	public void setUpstudentids(String upstudentids) {
		this.upstudentids = upstudentids;
	}

	public String getDownstudentids() {
		return downstudentids;
	}

	public void setDownstudentids(String downstudentids) {
		this.downstudentids = downstudentids;
	}

	public String getSiteid() {
		return siteid;
	}

	public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setSiteid(String siteid) {
		this.siteid = siteid;
	}

    private Map map = new HashMap();

    /** service共通类 */
    private transient Service service;
    
    private RidingPlanService ridingplanservice;
    
    /**
     * 规划左侧树
     * @return
     */
    public String planReadyPage() {
        final String browseTitle = "乘车规划";
        /**
         * 处理跳转过来的错误信息以及所需的当前企业ID作为查询条件设置
         */
        if (errorMessage != null) {
            addActionError(getText(errorMessage));
        }
        MDC.put("modulename", "[rideplanning]");
        
        UserInfo user = getCurrentUser();
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        String ChooseEnterID_tree = "";
        log.debug("vehicle_ln:" + vehicle_ln);
        
        log.info("进入"+browseTitle);
    
        try {
            map.put("in_enterprise_id", user.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("GPS.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }

            if (null != chooseorgid && !"".equals(chooseorgid)) {
                Map < String, Object > enmap = new HashMap < String, Object >(5);
                enmap.put("in_enterprise_id", user.getOrganizationID());
                enmap.put("in_org_id", chooseorgid);
                enmap.put("out_flag", null);
                enmap.put("out_message", null);
                enmap.put("out_ref", null);
                service.getObject("VehicleManage.show_enterprise_id", enmap);
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
            } else {
                ChooseEnterID_tree = user.getOrganizationID();
            }
            if(message!=null){
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_RIDINGPLAN_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle+"异常", e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
            if (!"".equals(ChooseEnterID_tree)) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree", ChooseEnterID_tree);
            }
        }
        return SUCCESS;
    }
    
    /**
     * 乘车规划列表显示
     * @return
     */
    public String ridingPlanList() {
        final String browseTitle = "乘车规划";
        /**
         * 处理跳转过来的错误信息以及所需的当前企业ID作为查询条件设置
         */
        if (errorMessage != null) {
            addActionError(getText(errorMessage));
        }
        
    	MDC.put("modulename", "[rideplanning]");
    	
        UserInfo user = getCurrentUser();
        int totalCount = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {


            if (null == ridingPlanInfo) {
                ridingPlanInfo = new RidingPlanInfo();
            }
            
            if (null != chooseorgid && !"".equals(chooseorgid)) {
                ridingPlanInfo.setOrganizationId(chooseorgid);
            } else {
                ridingPlanInfo.setOrganizationId(user.getOrganizationID());
            }

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            ridingPlanInfo.setSortname(sortName);
            ridingPlanInfo.setSortorder(sortOrder);
            ridingPlanInfo.setVehicleLn(vehicle_ln);
            ridingPlanInfo.setVehicleVin(vehicle_vin);
            ridingPlanInfo.setRouteName(routename);

            if (StringUtils.isEmpty(pageIndex)) {
                pageIndex = "1";
            }
            if (StringUtils.isEmpty(rpNum)) {
                rpNum = "10";
            }
            
            log.info("[Organization_id:" + ridingPlanInfo.getOrganizationId() + ",routename:" + routename
            		+ ",vehicle_ln:" + vehicle_ln
        			+ "]:"+browseTitle+"开始");

            totalCount = service.getCount("RidingPlan.getRidingPlanCount", ridingPlanInfo);
            ridingPlanList = service.getObjectsByPage("RidingPlan.getRidingPlanInfos",
                    ridingPlanInfo, (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(ridingPlanList, totalCount, pageIndex, rpNum);
            if(message!=null){
            	addActionMessage(getText(message));
            }
            
            log.info(browseTitle+"结束");
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle+"异常", e);
            return ERROR;
        }
        return SUCCESS;
    }
    /**
     * 展示关联车辆列表
     */
    public String getCarList() {
		final String vehTitle = getText("oilinfo.veh.title");
		int totalCount = 0;
		UserInfo user = getCurrentUser();
    	MDC.put("modulename", "[rideplanning]");
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext()
				.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		// 每页显示条数
		String rpNum = request.getParameter("rp");
		// 当前页码
		String pageIndex = request.getParameter("page");
		String sortName = request.getParameter("sortname");
		String sortOrder = request.getParameter("sortorder");

		try {
			VehcileInfo vehinfo = new VehcileInfo();
			vehinfo.setOrganization_id(user.getOrganizationID());
			vehinfo.setSortname(sortName);
			vehinfo.setSortorder(sortOrder);

			if (vehicle_vin != "") {
				vehinfo.setVehicle_vin(vehicle_vin);
			}
			if (vehicle_ln != "") {
				vehinfo.setVehicle_ln(vehicle_ln);
			}
			
			log.info("[Organization_id:" + vehinfo.getOrganization_id() + ",vehicle_vin:" + vehicle_vin
	            		+ ",vehicle_ln:" + vehicle_ln+ "]:"+vehTitle+"开始");
			 
			totalCount = service.getCount("RidingPlan.getCountVeh", vehinfo);
			if (null != vehicle_vin) {
				vehinfo.setVehicle_vin(vehicle_vin);
			}
			vehcList = (List<VehcileInfo>) service
					.getObjectsByPage("RidingPlan.getInfosVeh", vehinfo, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			this.map = getVehcilePagination(vehcList, totalCount, pageIndex);
			log.info(vehTitle+"结束");
		} catch (BusinessException e) {
			addActionError(getText(e.getMessage()));
			log.error(vehTitle+"异常", e);
			return ERROR;
		}
		return SUCCESS;
	}

    /**
     * 左侧-线路信息列表
     * @return
     */
    public String getRouteList() {
        final String browseTitle = "乘车规划线路查询";
    	MDC.put("modulename", "[rideplanning]");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == routeInfo) {
                routeInfo = new RouteInfo();
            }
            if(user!=null){
                routeInfo.setRoute_enterprise_id(user.getEntiID());
                routeInfo.setRoute_organization_id(user.getOrganizationID());
            }
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            routeInfo.setSortname(sortName);
            routeInfo.setSortorder(sortOrder);
            
            log.info("[Organization_id:" + routeInfo.getRoute_organization_id()+ ",enterprise_id:" + routeInfo.getRoute_enterprise_id()
            		+ "]:"+browseTitle+"开始");

            totalCount = service.getCount("RidingPlan.getRouteCount", routeInfo);
            routeList = (List < RouteInfo >) service.getObjectsByPage(
                    "RidingPlan.getRouteInfos", routeInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            this.map = getRoutePagination(routeList, totalCount, pageIndex);// 转换map
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
            this.setModuleId(MouldId.XCP_RIDINGPLAN_QUERY);
            log.info(browseTitle+"结束");
        } catch (BusinessException e) {
            log.info(browseTitle+"异常", e);
            return ERROR;
        }
        return SUCCESS;
    }
    /**
     * 浏览驾驶员信息
     * @return
     */
    public String getDriver() {
        final String browseTitle = getText("dirver.browse.title");
    	MDC.put("modulename", "[rideplanning]");
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == driverInfo) {
                driverInfo = new DriverInfo();
            }
            UserInfo user = getCurrentUser();
            driverInfo.setEnterprise_id(user.getEntiID());
            driverInfo.setOrganization_id(user.getOrganizationID());

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            driverInfo.setSortname(sortName);
            driverInfo.setSortorder(sortOrder);
            
            log.info("[Organization_id:" + driverInfo.getOrganization_id()+ ",enterprise_id:" + driverInfo.getEnterprise_id()
            		+ "]:"+browseTitle+"开始");

            int totalCount = 0;
            totalCount = service.getCount("RidingPlan.getDriverCount", driverInfo);
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);
            driverList = (List < DriverInfo >) service.getObjectsByPage(
                    "RidingPlan.getDriverInfos", driverInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getDriverPagination(driverList, totalCount, pageIndex, rpNum);// 转换map

            if (driverList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_DRIVERMANAGE_QUERY);
            log.info(browseTitle+"结束");
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle+"异常", e);
            return ERROR;
        }
        return SUCCESS;
    }
    

    
    /**
     * 浏览司乘信息
     * @return
     */
    public String getSteward() {
        final String browseTitle = "浏览司乘信息";
    	MDC.put("modulename", "[rideplanning]");
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == sichenInfo) {
            	sichenInfo = new StewardInfo();
            }
            UserInfo user = getCurrentUser();
            sichenInfo.setEnterprise_id(user.getEntiID());
            sichenInfo.setOrganization_id(user.getOrganizationID());
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            sichenInfo.setSortname(sortName);
            sichenInfo.setSortorder(sortOrder);
            log.info("[Organization_id:" + sichenInfo.getOrganization_id()+ ",enterprise_id:" + sichenInfo.getEnterprise_id()
            		+ "]:"+browseTitle+"开始");
            int totalCount = 0;
            totalCount = service.getCount("RidingPlan.getStewardCount", sichenInfo);
            stewardList = (List < StewardInfo >) service.getObjectsByPage(
                    "RidingPlan.getStewardInfos", sichenInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getStewardPagination(stewardList, totalCount, pageIndex, rpNum);// 转换map

            if (stewardList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STEWARDMANAGE_QUERY);
            
            log.info(browseTitle+"结束");
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle+"异常", e);
            return ERROR;
        }
        return SUCCESS;
    }
   
    public Map getRoutePagination(List routeList, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < routeList.size(); i++) {
        	RouteInfo s = (RouteInfo) routeList.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getRoute_id()+"_"+s.getRoute_incharge_person());
            cellMap.put("cell", new Object[] {s.getRoute_id(),s.getRoute_id(),
                    s.getRoute_name(), s.getRoute_incharge_person()});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 站点信息列表
     * @return
     */
    public String getSiteList() {
        final String browseTitle = "站点列表查询";
    	MDC.put("modulename", "[rideplanning]");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == siteInfo) {
            	return SUCCESS;
            }
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            siteInfo.setSortname(sortName);
            siteInfo.setSortorder(sortOrder);
            log.info("[车辆VIN："+siteInfo.getVehicle_vin()+"；站点上下行状态："+siteInfo.getUpdownflag()+"；线路ID："+siteInfo.getRouteid()+ "]:"+browseTitle+"开始");
            totalCount = service.getCount("RidingPlan.getSiteCount", siteInfo);

            siteList = (List < SiteInfo>) service.getObjectsByPage(
                    "RidingPlan.getSiteInfo", siteInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getSitePagination(siteList, totalCount, pageIndex,siteInfo,rpNum);// 转换map

            if (0 == siteList.size()) {
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
            this.setModuleId(MouldId.XCP_RIDINGPLAN_QUERY);
            
            log.info(browseTitle+"结束");
        } catch (BusinessException e) {
            log.info(browseTitle+"异常", e);
            return ERROR;
        }
        return SUCCESS;
    }
    public Map getPagination(List ridingPlanList, int totalCountDay, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < ridingPlanList.size(); i++) {
            RidingPlanInfo s = (RidingPlanInfo) ridingPlanList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getTripID());

            cellMap.put("cell", new Object[] {null,
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getVehicleLn(),
                    s.getVehicleVin(),
                    s.getRouteId(),
                    s.getRouteName(),
                    s.getTripBegTime(),
                    s.getTripEndTime(),
                    s.getUpDownFlag(),
                    s.getStNum(),
                    s.getSiteNum(),
                    s.getDriverNames(),
                    s.getSichenNames(),
                    s.getEditTime(),
                    s.getTerTime()
                    });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    public Map getSitePagination(List siteList, int totalCount, String pageIndex,SiteInfo siteInfo,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < siteList.size(); i++) {
        	SiteInfo s = (SiteInfo) siteList.get(i);
        	String selectRow="";
			if(i==0){
				selectRow="first";
			}else if(i==siteList.size()-1){
				selectRow="last";
			}else{
				selectRow="0";
			}
            Map cellMap = new LinkedHashMap();
            String upids = s.getUp_stu_ids()==null?"none":s.getUp_stu_ids();
            String downids = s.getDown_stu_ids()==null?"none":s.getDown_stu_ids();
            cellMap.put("id", s.getSite_id());
            cellMap.put("cell", new Object[] {
					(i + 1) + (Integer.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), //序号
            		//s.getSite_id(),//站点ID
            		s.getSite_name(),//站点名称
            		initPlanComeTime(s.getSite_id(),s.getPlan_in_time()),//到站时间
                    initPlanLeaveTime(s.getSite_id(),s.getPlan_in_time(),s.getPlan_out_time()),//离站时间
            		//getUpDown(s.getSite_updown()),//上下行（BUGFREE853去掉）
                    s.getSichen_addr(), //站点地址
                    upids,//上车学生数
                    downids,//下车学生数
                    initUpStudentHtml(s.getSite_id(),s.getUp_stu_ids(),s.getUp_stu_names()),//上车学生数HTML
                    initDownStudentHtml(s.getSite_id(),s.getDown_stu_ids(),s.getDown_stu_names()),//下车学生数HTML
                    
                   
                    
                    selectStudentHtml(s.getSite_id(),s.getUp_stu_ids(),s.getDown_stu_ids(),selectRow),//关联学生
                    s.getSite_id()+";"+upids+";"+downids+";"+siteInfo.getUpdownflag()
                    });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getVehcilePagination(List vehcList, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < vehcList.size(); i++) {

            VehcileInfo s = (VehcileInfo) vehcList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVehicle_vin());

            cellMap.put("cell", new Object[] {s.getVehicle_ln(),
                    s.getVehicle_vin(), s.getVehicle_code(),
                    s.getShort_allname() });

            mapList.add(cellMap);

        } 
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getDriverPagination(List driverList, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < driverList.size(); i++) {
            DriverInfo s = (DriverInfo) driverList.get(i);
            Map cellMap = new LinkedHashMap();
            String flag = findDriver(s.getDriver_id());
            cellMap.put("id", s.getDriver_id());
            cellMap.put("cell", new Object[] {
            		s.getDriver_id(),
            		s.getDriver_id(),
            		s.getDriver_name(),
                    s.getDriver_card(),
                    s.getDriver_license(),
                    flag});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getStewardPagination(List list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            StewardInfo s = (StewardInfo) list.get(i);
            String flag = findSteward(s.getSteward_id());
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getSteward_id());
            cellMap.put("cell", new Object[] {
                    s.getSteward_id(),
                    s.getSteward_id(),
                    s.getSteward_name(),
                    s.getSteward_card(),
                    s.getSteward_ID_Card(),
                    flag});
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 做上行html代码  参数1站点ID  参数2 学生id列表  参数3 学生姓名列表
     * @param site_id
     * @return
     */
    private String initUpStudentHtml(String site_id,String upstu_ids,String upstu_names){ 
    	//该位置目前要显示学生数，所以upstu_names没用上，如果现实学生姓名列表，则需要它
    	String upids="0";
        if(upstu_ids!=null){
        	upids = upstu_ids.split(",").length+"";
        }else{
        	upstu_ids = "";
        }
    	String span = "<span style='text-align:left;margin-top: -4px;' id='upDIV_"+site_id+"'>"+upids+"</span>";
    	return "<a href =\"javascript:showStudent('"+site_id+"','"+upstu_ids+"','0');\">"+span+"</a>"; 
    }
    /**
     * 做下行html代码 参数1站点ID  参数2 学生id列表  参数3 学生姓名列表
     * @param stu_id
     * @return
     */
    private String initDownStudentHtml(String site_id,String downstu_ids,String downstu_names){
    	//该位置目前要显示学生数，所以upstu_names没用上，如果现实学生姓名列表，则需要它
    	String downids="0";
        if(downstu_ids!=null){
        	downids = downstu_ids.split(",").length+"";
        }else{
        	downstu_ids ="";	
        }
        String span = "<span style='text-align:left;margin-top: -4px;' id='downDIV_"+site_id+"'>"+downids+"</span>";
    	return "<a href =\"javascript:showStudent('"+site_id+"','"+downstu_ids+"','1');\">"+span+"</a>";   	
    }
    /**
     * 制作规划来的时间Html
     */
    private String initPlanComeTime(String site_id,String cometime){
    	Date date = new Date();
    	//modify by yg start
    	String now ="";
    	//String now = (date.getHours()<10?"0"+date.getHours():date.getHours())+":"+(date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes());  	
    	if(cometime!=null && !"".equals(cometime)){
    		now = cometime;
    	}
    	String time = "<input readonly='readonly' style='margin-top:-4px' id='come_"+site_id+"' class='Wdate' type='text' onfocus='WdatePicker({dateFmt:\"HH:mm\",autoPickDate:true,isShowClear:false,isShowToday:false})'value='"+now+"' />";
    	//modify by yg end
    	return time;
    }
    /**
     * 制作规划离开时间Html
     */
    private String initPlanLeaveTime(String site_id,String cometime,String leavetime){
    	Date date = new Date();
    	//modify by yg start
    	String now ="";
    	//String now = (date.getHours()<10?"0"+date.getHours():date.getHours())+":"+(date.getMinutes()<10?"0"+date.getMinutes():date.getMinutes());
    	if(leavetime!=null && !"".equals(leavetime)){
    		now = leavetime;
    		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
    		try {
    			java.util.Date leavetimeDate=df.parse(leavetime);
    			java.util.Date cometimeDate=df.parse(cometime);
    			long l=leavetimeDate.getTime()-cometimeDate.getTime();
    			now=Long.toString(l/(60*1000)) ;
    		} catch (ParseException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	String time = "<input style='margin-top:-4px' size='8' id='leave_"+site_id+"' type='text' value='"+now+"' onkeyup='checkInputValue(this)'/>（分）";
    	//modify by yg end
    	return time;
    }
    /**
     * 设置关联学生.
     */
    private String selectStudentHtml(String site_id,String upstu_ids,String downstu_ids,String selectRow){  
    	String up=upstu_ids==null?"":upstu_ids;
    	String down = downstu_ids==null?"":downstu_ids;
    	return "<a href =\"javascript:choiceStudent('"+site_id+"','"+up+"','"+down+"','"+selectRow+"');\">选择学生</a>";
    }
    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {

        return SUCCESS;
    }
    /**
     * 列表信息页面
     * @return
     */
    public String chooseDriverStart() {

        return SUCCESS;
    }
    /**
     * 列表信息页面
     * @return
     */
    public String chooseSichenStart() {

        return SUCCESS;
    }
    
    /**
     * 列表信息页面
     * @return
     */
    public String chooseCarStart() {

        return SUCCESS;
    }
    
    /**
     * 选择学生列表信息跳转页面
     * @return
     */
    public String chooseStudentStart() {
    	MDC.put("modulename", "[rideplanning]");
        try{
        	log.info("[siteid:"+siteid+ "]:选择学生列表跳转页面查询站点名称开始");
            sitename=(String)service.getObject("RidingPlan.getsitename",siteid);
            log.info("选择学生列表跳转页面查询站点名称结束");
        }catch(BusinessException e){
            log.error("选择学生列表跳转页面查询站点名称出错:",e);
        }
        if (null != message) {
            addActionMessage(getText(message));
        }   
        return SUCCESS;
    }
       
    /**
     * 树获取页面
     * @return
     */
    public String getTreeInit() {
        final String addBefTitle = getText("oilinfo.gettree.title");
        log.info(addBefTitle);
        UserInfo user = getCurrentUser();
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map.put("in_enterprise_id", user.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("GPS.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }
    public String ridingadd(){   	
    	MDC.put("modulename", "[rideplanning]");
        try {
        	LOG.info("新增乘车规划开始");
        	addRidingPlan(this.ridingReady); 
        	LOG.info("新增乘车规划结束");
        } catch (BusinessException e) {
            e.printStackTrace();
            addActionError(getText(e.getMessage()));
            return ERROR;
        } finally {
            // add by jinp start
            // 设置操作描述
            this.addOperationLog("乘车规划创建");
            // 设置操作类型
            this.setOperationType(Constants.INSERT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_RIDINGPLAN_MODIFY);
            // add by jinp stop
        }
        setMessage("userinfo.create.success");    	
    	return SUCCESS;
    }
    public String ridingupdate(){   	
    	MDC.put("modulename", "[rideplanning]");
        try {
        	LOG.info("修改乘车规划开始");
        	updateRidingPlan(this.ridingReady);    
        	LOG.info("修改乘车规划结束");
        } catch (BusinessException e) {
            e.printStackTrace();
            setErrorMessage(e.getMessage());
            return ERROR;
        } finally {
            // add by jinp start
            // 设置操作描述
            this.addOperationLog("乘车规划修改");
            // 设置操作类型
            this.setOperationType(Constants.UPDATE);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_RIDINGPLAN_ADD);
            // add by jinp stop
        }
        setMessage("userinfo.update.success");    	
    	return SUCCESS;
    }   
    public String deleteRidingPlan(){
        final String cancleTitle = getText("dirverinfo.delete");
    	MDC.put("modulename", "[rideplanning]");
        try {
        	UserInfo user = getCurrentUser();
        	map.put("route_id", ridingReady.getRoute_id());
            map.put("vehicle_vin",ridingReady.getVehicle_vin()); 
            map.put("trip_id",ridingReady.getTrip_id());
            map.put("type", ridingReady.getType());
            map.put("operateor", user.getUserID());
            LOG.info("删除乘车规划:参数 “route_id”："+ridingReady.getRoute_id());
            LOG.info("删除乘车规划:参数 “vehicle_vin”："+ridingReady.getVehicle_vin());
            LOG.info("删除乘车规划:参数 “trip_id”："+ridingReady.getTrip_id());
            LOG.info("删除乘车规划:参数 “type”："+ridingReady.getType());
            LOG.info("删除乘车规划:参数 “operateor”："+user.getUserID());
            String realPath = ServletActionContext.getServletContext().getRealPath(
            "/")+iniDefaultPath+ridingReady.getVehicle_vin()+ "/" ;    //家里測試路徑
    		
            String pathInfo = iniDefaultPath+ridingReady.getVehicle_vin()+ "/" ; //線網路徑
           
            String sendFilePath="";
           
            String targetFileName="";
            if(iniDefaultPathFlag.equals("0")){
            	sendFilePath="/"+ridingReady.getVehicle_vin()+"/";
            }else{
            	sendFilePath=pathInfo;
            }
           
    		String usedPath=pathInfo;
    		Map < String, Object > resultmap = new HashMap < String, Object >();
    		LOG.info(cancleTitle+"开始");
            try {
            	resultmap=ridingplanservice.batchDeletedRidingPlan(map,usedPath);
            	targetFileName=(String) resultmap.get("filename");
			} catch (DataAccessIntegrityViolationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String batch_id = UUIDGenerator.getUUID32();
	    	String msgid = UUIDGenerator.getUUID32();
	    	
	    	targetFileName=targetFileName.substring(targetFileName.lastIndexOf("/")+1)+".gz";
	    	String crc=(String) resultmap.get("crc");
	    	
	    	LOG.info(cancleTitle+"命令下发开始");
	    	
	    	String returnvalue = sendCommandClient.sendRouteNotice(ridingReady.getVehicle_vin(), user.getUserID(), msgid, batch_id,
	    			ip, port, username, userpass, 
	    			sendFilePath+ targetFileName, 
	    			crc);
	    	LOG.info("[returnvalue:" + returnvalue+"]"+cancleTitle+"命令下发结束");
	    	LOG.info(cancleTitle+"结束");

        } catch (BusinessException e) {
            log.error(cancleTitle+"异常", e);
            addActionError(getText(e.getMessage()));
            return ERROR;
        }
        setMessage("ridingplan.delete.success");
        // 设置操作描述
        this.addOperationLog(formatLog(cancleTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_RIDINGPLAN_DELETE);

        return SUCCESS;
    }
    public String updateRidingReady(){
    	MDC.put("modulename", "[rideplanning]");
        try {
        	map.put("route_id", ridingReady.getRoute_id());
            map.put("vehicle_vin",ridingReady.getVehicle_vin()); 
            map.put("trip_id",ridingReady.getTrip_id());
            LOG.info("修改乘车规划:参数 “route_id”："+ridingReady.getRoute_id());
            LOG.info("修改乘车规划:参数 “vehicle_vin”："+ridingReady.getVehicle_vin());
            LOG.info("修改乘车规划:参数 “trip_id”："+ridingReady.getTrip_id());
            ridingReady = (RidingReady)service.getObject("RidingPlan.searchRidingPlan",map);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            LOG.error("修改乘车规划异常",e);
            return ERROR;
        } 
    	return SUCCESS;
    }
    /**
     * 更新乘车规划
     * @param ridingReady
     * @throws BusinessException
     */
    private void updateRidingPlan(RidingReady ridingReady) throws BusinessException {
        /*查询正在修改的（车辆+线路）规划是否还存在，
         * 若无（车辆+线路）表明有人修改了此条规划，此次修改操作就不能再进行；
         * 若（车辆+线路）规划还存在，此次修改可以进行；
         * 针对CASE:线路1+车辆C1规划，用户A修改为线路1+车辆C2；用户B修改为线路1+车辆C3；
         * 不判断会出现两条车辆不同的重复规划
         */
    	
    	/*
         * 修改校车三期新需求，注释以下代码
         * mod by yg
         * */
        /*Map querymap = new HashMap();
        querymap.put("route_id", ridingReady.getRoute_id());
        querymap.put("vehicle_vin", ridingReady.getVehicle_vin_old());
        int totalCount = service.getCount("RidingPlan.getSomeVSSCount", querymap);
        if( totalCount > 0){
            List list = new ArrayList();
            Map < String, Object > map = new HashMap < String, Object >();
            map.put("route_id", ridingReady.getRoute_id());
            map.put("vehicle_vin",ridingReady.getVehicle_vin()); 
            LOG.info("修改乘车规划:参数 “route_id”："+ridingReady.getRoute_id());
            LOG.info("修改乘车规划:参数 “vehicle_vin”："+ridingReady.getVehicle_vin());
            LOG.info("修改乘车规划:参数 “Vehicle_vin_old”："+ridingReady.getVehicle_vin_old());
            //驾驶员列表
            List<VehdriverInfo> driverList = getVehdriverInfo(this.ridingReady);
            //司乘列表
            List<VehsichenInfo> sichenList = getVehsichenInfo(this.ridingReady);
            //乘车规划列表
            List<VssInfo> vssList = getVssInfo(this.ridingReady);
            //乘车规划计划出入站时间表
            List<Vss_SiteInfo>vss_site = getVss_SiteInfo(this.ridingReady);
            service.batchUpdateRidingPlan(map, driverList,sichenList,vssList,vss_site,ridingReady.getVehicle_vin_old());
        }else{
            throw new BusinessException("ridingplan.update.error");
        }*/
    	MDC.put("modulename", "[rideplanning]");
    	List list = new ArrayList();
    	UserInfo user = getCurrentUser();
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("route_id", ridingReady.getRoute_id());
        map.put("vehicle_vin",ridingReady.getVehicle_vin()); 
        map.put("route_status", ridingReady.getRoute_status());
        map.put("trip_id",ridingReady.getTrip_id());
        map.put("type", ridingReady.getType());
        map.put("operateor", user.getUserID());
        LOG.info("修改乘车规划:参数 “route_id”："+ridingReady.getRoute_id());
        LOG.info("修改乘车规划:参数 “vehicle_vin”："+ridingReady.getVehicle_vin());
        LOG.info("新增乘车规划:参数 “route_status”："+ridingReady.getRoute_status());
        LOG.info("修改乘车规划:参数 “Vehicle_vin_old”："+ridingReady.getVehicle_vin_old());
        LOG.info("修改乘车规划:参数 “trip_id”："+ridingReady.getTrip_id());
        LOG.info("修改乘车规划:参数 “type”："+ridingReady.getType());
        LOG.info("修改乘车规划:参数 “operateor”："+user.getUserID());
        
        //驾驶员列表
        List<VehdriverInfo> driverList =null;
        if(!"".equals(this.ridingReady.getDriver_ids())&&null!=this.ridingReady.getDriver_ids()){
           driverList = getVehdriverInfo(this.ridingReady);
        }
        
        //司乘列表
        List<VehsichenInfo> sichenList =null;
        if(!"".equals(this.ridingReady.getSteward_ids())&&null!=this.ridingReady.getSteward_ids()){
           sichenList = getVehsichenInfo(this.ridingReady);
        }
        
        //乘车规划列表
        List<VssInfo> vssList = getVssInfo(this.ridingReady);
        //乘车规划计划出入站时间表
        List<Vss_SiteInfo>vss_site = getVss_SiteInfo(this.ridingReady);
        
        //trip_crc=sendroutefileservice.sendRouteFile(ridingReady.getTrip_id());
        //map.put("crc", trip_crc);
        String realPath = ServletActionContext.getServletContext().getRealPath(
        "/")+iniDefaultPath+ridingReady.getVehicle_vin()+ "/" ;    //家里測試路徑
		
        String pathInfo = iniDefaultPath+ridingReady.getVehicle_vin()+ "/" ; //線網路徑
       
        String sendFilePath="";
       
        String targetFileName="";
        if(iniDefaultPathFlag.equals("0")){
        	sendFilePath="/"+ridingReady.getVehicle_vin()+"/";
        }else{
        	sendFilePath=pathInfo;
        }
       
		String usedPath=pathInfo;
		Map < String, Object > resultmap = new HashMap < String, Object >();
        try {
        	resultmap=ridingplanservice.batchUpdateRidingPlan(map, driverList,sichenList,vssList,vss_site,ridingReady.getVehicle_vin_old(),usedPath);
        	targetFileName=(String) resultmap.get("filename");
		} catch (DataAccessIntegrityViolationException e) {
			e.printStackTrace();
			LOG.error("修改乘车规划异常",e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			LOG.error("修改乘车规划异常",e);
		}
		if(!ridingReady.getVehicle_vin_old().equals(ridingReady.getVehicle_vin())){
			String batch_id = UUIDGenerator.getUUID32();
	    	String msgid = UUIDGenerator.getUUID32();
	    	
	    	String oldcrc=(String) resultmap.get("oldcrc");
	    	
	    	String targetOldFileName=targetFileName.substring(targetFileName.lastIndexOf("/")+1)+".gz";
	    	LOG.info("换车后的修改乘车规划命令下发开始");
	    	targetOldFileName=targetOldFileName.replace(ridingReady.getVehicle_vin(), ridingReady.getVehicle_vin_old());
	    	String oldusedPath=usedPath.replace(ridingReady.getVehicle_vin(), ridingReady.getVehicle_vin_old());
	    	String oldsendFilePath=sendFilePath.replace(ridingReady.getVehicle_vin(), ridingReady.getVehicle_vin_old());
	    	String returnoldvalue = sendCommandClient.sendRouteNotice(ridingReady.getVehicle_vin_old(), user.getUserID(), msgid, batch_id,
	    			ip, port, username, userpass, 
	    			oldsendFilePath+ targetOldFileName, 
	    			oldcrc);
	    	LOG.info("[returnoldvalue:" + returnoldvalue+"]:换车后的修改乘车规划命令下发结束");
		}
		
		String batch_id = UUIDGenerator.getUUID32();
    	String msgid = UUIDGenerator.getUUID32();
    	
    	targetFileName=targetFileName.substring(targetFileName.lastIndexOf("/")+1)+".gz";
    	String crc=(String) resultmap.get("crc");
    	LOG.info("修改乘车规划命令下发开始");
    	String returnvalue = sendCommandClient.sendRouteNotice(ridingReady.getVehicle_vin(), user.getUserID(), msgid, batch_id,
    			ip, port, username, userpass, 
    			sendFilePath+ targetFileName, 
    			crc);
    	LOG.info("[returnvalue:" + returnvalue+"]:修改乘车规划命令下发结束");
    }   
    
    private String doChecksum(String fileName) {
		long checksum = 0;
        try {
            CheckedInputStream cis = null;
            try {
                // Computer CRC32 checksum
                cis = new CheckedInputStream(
                        new FileInputStream(fileName), new CRC32());
            } catch (FileNotFoundException e) {
            	LOG.error("File not found.");
            }

            byte[] buf = new byte[128];
            while(cis.read(buf) >= 0) {
            }
            checksum = cis.getChecksum().getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(checksum);
    }
    /**
     * 添加乘车规划
     * @param ridingReady
     * @throws BusinessException
     */
    private void addRidingPlan(RidingReady ridingReady) throws BusinessException {
        List list = new ArrayList();
        UserInfo user = getCurrentUser();
    	MDC.put("modulename", "[rideplanning]");
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("route_id", ridingReady.getRoute_id());
        map.put("vehicle_vin",ridingReady.getVehicle_vin()); 
        map.put("type", ridingReady.getRoute_status());
        LOG.info("新增乘车规划:参数 “route_id”："+ridingReady.getRoute_id());
        LOG.info("新增乘车规划:参数 “vehicle_vin”："+ridingReady.getVehicle_vin());
        LOG.info("新增乘车规划:参数 “route_status”："+ridingReady.getRoute_status());
        String trip_id="";
        trip_id=(String) service.getObject("RidingPlan.getTripIDFromSEQ", null);
        LOG.info("新增乘车规划:生成行程ID “trip_id”："+trip_id);
        map.put("trip_id", trip_id);
        map.put("operateor", user.getUserID());
        this.ridingReady.setTrip_id(trip_id);
        
        //驾驶员列表
        List<VehdriverInfo> driverList =null;
        if(!"".equals(this.ridingReady.getDriver_ids())&&null!=this.ridingReady.getDriver_ids()){
           driverList = getVehdriverInfo(this.ridingReady);
        }
        
        //司乘列表
        List<VehsichenInfo> sichenList =null;
        if(!"".equals(this.ridingReady.getSteward_ids())&&null!=this.ridingReady.getSteward_ids()){
           sichenList = getVehsichenInfo(this.ridingReady);
        }
        
        //乘车规划列表
        List<VssInfo> vssList = getVssInfo(this.ridingReady);
        //乘车规划计划出入站时间表
        List<Vss_SiteInfo>vss_site = getVss_SiteInfo(this.ridingReady);
        try {
			ridingplanservice.batchAddRidingPlan(map, driverList,sichenList,vssList,vss_site);
		} catch (DataAccessIntegrityViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("新增乘车规划异常",e);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("新增乘车规划异常",e);
		}
    }
    /**
     * 获取驾驶员和车辆关系列表
     * @param ridingReady
     * @return
     */
    private List<VehdriverInfo> getVehdriverInfo(RidingReady ridingReady){
    	List<VehdriverInfo> list = new ArrayList<VehdriverInfo>();
    	String driver_ids[] = ridingReady.getDriver_ids().split(",");
    	String vehicle_vin = ridingReady.getVehicle_vin();
    	String trip_id=ridingReady.getTrip_id();
    	for (int i = 0; i < driver_ids.length; i++) {
    		VehdriverInfo info = new VehdriverInfo();
    		info.setDriver_id(driver_ids[i]);
    		info.setVehicle_vin(vehicle_vin);
    		info.setTrip_id(trip_id);
    		list.add(info);
		}
    	return list;
    }
    /**
     * 获取司乘和车辆关系列表
     * @param ridingReady
     * @return
     */
    private List<VehsichenInfo> getVehsichenInfo(RidingReady ridingReady){
    	List<VehsichenInfo> list = new ArrayList<VehsichenInfo>();
    	String Steward_id[]= ridingReady.getSteward_ids().split(",");
    	String vehicle_vin = ridingReady.getVehicle_vin();
    	String trip_id=ridingReady.getTrip_id();
    	for (int i = 0; i < Steward_id.length; i++) {
    		VehsichenInfo info = new VehsichenInfo();
    		info.setSteward_id(Steward_id[i]);
    		info.setVehicle_vin(vehicle_vin);
    		info.setTrip_id(trip_id);
    		list.add(info);
		}
    	return list;
    }
    /**
     * 获取乘车规划列表
     * @param ridingReady
     * @return
     */
    private List<VssInfo> getVssInfo(RidingReady ridingReady){
    	List<VssInfo> list = new ArrayList<VssInfo>();
    	String site_students[] = ridingReady.getSite_students().split(";");
    	for (int i = 0; i < site_students.length; i++) {
			String site_student = site_students[i];
			list.addAll(getSite_student(ridingReady,site_student));
		}
    	return list;
    }
    /**
     * 初步分解站点学生字符串，按；分隔多个
     * @param ridingReady
     * @return
     */
    private List<Vss_SiteInfo> getVss_SiteInfo(RidingReady ridingReady){
    	List<Vss_SiteInfo> list = new ArrayList<Vss_SiteInfo>();
    	String site_students[] = ridingReady.getSite_students().split(";");
    	for (int i = 0; i < site_students.length; i++) {
			String site_student = site_students[i];
			list.addAll(getSite_Time(ridingReady,site_student));
		}
    	return list;
    }
    /**
     * 根据上下车学生，制作乘车规划列表
     * @param ridingReady
     * @param site_student
     * @return
     */
    private List<VssInfo> getSite_student(RidingReady ridingReady,String site_student){
    	List<VssInfo> list = new ArrayList<VssInfo>();
    	String siteid = site_student.split("!")[0];
    	String upstudentids[]= {};
    	String downstudentids[]={};
    	if(!"none".equals(site_student.split("!")[1])){
    		upstudentids =  site_student.split("!")[1].split(",");
    	}
    	if(!"none".equals(site_student.split("!")[2])){
    		downstudentids = site_student.split("!")[2].split(",");
    	}   	
    	for (int i = 0; i < upstudentids.length; i++) {   		
    		VssInfo info = new VssInfo();
    		info.setVehicle_vin(ridingReady.getVehicle_vin());
    		info.setStudent_id(upstudentids[i]);
    		info.setRoute_id(ridingReady.getRoute_id());
    		info.setSite_id(siteid);
    		info.setVss_state("0");
    		info.setTrip_id(ridingReady.getTrip_id());
    		list.add(info);
		}
    	for (int j = 0; j < downstudentids.length; j++) {
    		VssInfo info = new VssInfo();
    		info.setVehicle_vin(ridingReady.getVehicle_vin());
    		info.setStudent_id(downstudentids[j]);
    		info.setRoute_id(ridingReady.getRoute_id());
    		info.setSite_id(siteid);
    		info.setVss_state("1");
    		info.setTrip_id(ridingReady.getTrip_id());
    		list.add(info);
		}	
    	return list;    	
    }
    /**
     * 解析学生站点字符串
     * @param ridingReady
     * @param site_student
     * @return List<Vss_SiteInfo>
     */
    private List<Vss_SiteInfo> getSite_Time(RidingReady ridingReady,String site_student){
    	List<Vss_SiteInfo> list = new ArrayList<Vss_SiteInfo>();
    	String siteid = site_student.split("!")[0];
	    String come = site_student.split("!")[3];
	    String leave = site_student.split("!")[4];
    	Vss_SiteInfo info = new Vss_SiteInfo();
    	info.setVehicle_vin(ridingReady.getVehicle_vin());
    	info.setRoute_id(ridingReady.getRoute_id());
    	info.setSite_id(siteid);
    	info.setPlan_in_time(come);
    	info.setPlan_out_time(leave);
    	info.setTrip_id(ridingReady.getTrip_id());
    	list.add(info);		
    	return list;    	
    }
    /**
     * 将上放学标志位换成中文
     * @param site_up_down
     * @return
     */
    private String getUpDown(String site_up_down){   	
    	return "0".equals(site_up_down)?"上学":"放学";
    }
    /**
     * 格式化日志信息
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
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
    
    public String getChooseorgid() {
        return chooseorgid;
    }

    public void setChooseorgid(String chooseorgid) {
        this.chooseorgid = chooseorgid;
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

    public List < RidingPlanInfo > getRidingPlanList() {
        return ridingPlanList;
    }

    public void setRidingPlanList(List < RidingPlanInfo > ridingPlanList) {
        this.ridingPlanList = ridingPlanList;
    }

    public RidingPlanInfo getRidingPlanInfo() {
        return ridingPlanInfo;
    }

    public void setRidingPlanInfo(RidingPlanInfo ridingPlanInfo) {
        this.ridingPlanInfo = ridingPlanInfo;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getUser_org_id() {
		return user_org_id;
	}

	public void setUser_org_id(String user_org_id) {
		this.user_org_id = user_org_id;
	}

	public RouteInfo getRouteInfo() {
		return routeInfo;
	}

	public void setRouteInfo(RouteInfo routeInfo) {
		this.routeInfo = routeInfo;
	}

	public void setRouteList(List<RouteInfo> routeList) {
		this.routeList = routeList;
	}

	public SiteInfo getSiteInfo() {
		return siteInfo;
	}

	public void setSiteInfo(SiteInfo siteInfo) {
		this.siteInfo = siteInfo;
	}

	public void setSiteList(List<SiteInfo> siteList) {
		this.siteList = siteList;
	}
	
	public RidingPlanService getRidingplanservice() {
		return ridingplanservice;
	}

	public void setRidingplanservice(RidingPlanService ridingplanservice) {
		this.ridingplanservice = ridingplanservice;
	}

	/**
     * 判断司机是否选中
     * @return
     */
    private String findDriver(String dri_id) {

		String flag = "0";
		if (StringUtils.isNotEmpty(driver_ids)) {
			String[] dri_ids = driver_ids.split(",");
			for (int i = 0; i < dri_ids.length; i++) {
               if(dri_ids[i].equals(dri_id)){
            	   flag="1";
            	   break;
               }
			}
		}
		return flag;
	}
	/**
     * 判断司乘是否选中
     * @return
     */
    private String findSteward(String ste_id) {

		String flag = "0";
		if (StringUtils.isNotEmpty(steward_ids)) {
			String[] ste_ids = steward_ids.split(",");
			for (int i = 0; i < ste_ids.length; i++) {
               if(ste_ids[i].equals(ste_id)){
            	   flag="1";
            	   break;
               }
			}
		}
		return flag;
	}
}
