package com.neusoft.clw.safemanage.vehiclesafe.action;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.itsmanage.oilmanage.oilused.domain.OilManage;
import com.neusoft.clw.safemanage.vehiclesafe.domain.VehicleStatus;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */
@SuppressWarnings("serial")
public class VehicleStatusAction extends PaginationAction {

    private List < VehicleStatus > dayList;

    private VehicleStatus vehiclestatus;

    /**
     * 车辆状态-正常状态列表
     */
    private List < VehicleStatus > normalVehicleStatusList = new ArrayList < VehicleStatus >();

    /**
     * 车辆状态-异常状态列表
     */
    private List < VehicleStatus > abnormalVehicleStatusList = new ArrayList < VehicleStatus >();

    public List < VehicleStatus > getAbnormalVehicleStatusList() {
        return abnormalVehicleStatusList;
    }

    public void setAbnormalVehicleStatusList(
            List < VehicleStatus > abnormalVehicleStatusList) {
        this.abnormalVehicleStatusList = abnormalVehicleStatusList;
    }

    // 用户类型 -车主 -系统
    private String userType;

    public String getUserType() {
        return userType;
    }

    public List < VehicleStatus > getNormalVehicleStatusList() {
        return normalVehicleStatusList;
    }

    public void setNormalVehicleStatusList(
            List < VehicleStatus > normalVehicleStatusList) {
        this.normalVehicleStatusList = normalVehicleStatusList;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    private String vehicle_ln;

    private String start_time;

    private String end_time;

    private String time_list;

    private String month;

    private String quarter;

    private String year;

    private String chooseorgid;

    private String message = null;

    private String searchState;

    public String getSearchState() {
        return searchState;
    }

    public void setSearchState(String searchState) {
        this.searchState = searchState;
    }

    // add by jinp start
    private Map map = new HashMap();

    private String selectradio;

    public String getSelectradio() {
        return selectradio;
    }

    public void setSelectradio(String selectradio) {
        this.selectradio = selectradio;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    // add by jinp stop

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

    /** service共通类 */
    private transient Service service;

    public List < VehicleStatus > getDayList() {
        return dayList;
    }

    public void setDayList(List < VehicleStatus > dayList) {
        this.dayList = dayList;
    }

    public VehicleStatus getVehiclestatus() {
        return vehiclestatus;
    }

    public void setVehiclestatus(VehicleStatus vehiclestatus) {
        this.vehiclestatus = vehiclestatus;
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

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, OilManage om) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != om) {
            if (null != om.getRefuel_id()) {
                OperateLogFormator.format(sb, "refuel_id", om.getRefuel_id());
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
     * 2.0入口
     */
    public String newinit(){
    	year=DateUtil.getYear();
        return SUCCESS;
    }
    
    /**
     * 2.0列表
     */
    public String newgetVehicleStatusList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String browseTitle = getText("vehstatus.browse.title");
        log.info(browseTitle);
        UserInfo user = getCurrentUser();
        //String tree_script = "";
        int totalCount = 0;
        try {
            if (null == vehiclestatus) {
                vehiclestatus = new VehicleStatus();
            }

            if (null != chooseorgid && !"".equals(chooseorgid)) {
                vehiclestatus.setOrganization_id(chooseorgid);
            } else {
                vehiclestatus.setOrganization_id(user.getOrganizationID());
            }

            if (null != vehicle_ln && !"".equals(vehicle_ln)) {
                vehiclestatus.setVehicle_ln(SearchUtil.formatSpecialChar(vehicle_ln));
            }

            if (null!=searchState&&!"".equals(searchState)) {
                vehiclestatus.setSearchState(searchState);
            }

            // add by jinp start
            // 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
            if(sortName.equals("vehicle_ln"))
			{
				sortName="nlssort("+sortName+",'NLS_SORT=SCHINESE_PINYIN_M')";
			}
            vehiclestatus.setSortname(sortName);
            vehiclestatus.setSortorder(sortOrder);

            long btime = System.currentTimeMillis();
            // 获取车辆状态列表信息
            totalCount = service.getCount(
                    "VehicleStatus.newgetvehstatuscount", vehiclestatus);
            dayList = service
                    .getObjectsByPage("VehicleStatus.newgetvehstatuslist",
                            vehiclestatus, (Integer.parseInt(pageIndex) - 1)
                                    * Integer.parseInt(rpNum), Integer
                                    .parseInt(rpNum));
            long atime = System.currentTimeMillis();
            log.info("获取车辆状态原数据时间：" + (atime - btime));

            //long btime1 = System.currentTimeMillis();
            // 车辆状态处理
            this.map = newgetMorePagination(dayList, totalCount, pageIndex, rpNum);

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_VEHSTATUS_QUERY_ID);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    public Map newgetMorePagination(List dayList, int totalCountDay,
            String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < dayList.size(); i++) {

            VehicleStatus s = (VehicleStatus) dayList.get(i);
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVin());

            cellMap.put("cell", new Object[] {s.getVehicle_ln(),
                    s.getVin(), s.getOnline_flag(),s.getVehstat(),s.getTerminalTime(),
                    s.getYanzhongState(),s.getZuse(),
                    s.getHighTemp(),s.getCanggui(),s.getPOther()
                    });
            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
}
