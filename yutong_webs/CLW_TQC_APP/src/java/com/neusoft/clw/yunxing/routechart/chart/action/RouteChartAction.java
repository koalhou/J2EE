package com.neusoft.clw.yunxing.routechart.chart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.AlarmRecode;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
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

    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        user_org_id = getCurrentUser().getOrganizationID();
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
    public void route_add_car_info() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", routeChart.getRoute_id());
			map.put("vehicle_vin", routeChart.getVIN());
			map.put("send_condition", routeChart.getSend_condition());
			String send_time = routeChart.getSend_condition().equals("2")?routeChart.getSend_time():"";
			map.put("send_time", send_time);
			//map.put("send_time", routeChart.getSend_time());
			map.put("send_order", routeChart.getSend_order());
			map.put("week_seq", routeChart.getWeek_seq());
			map.put("driver_id", routeChart.getDriver_id());
			map.put("update_by", getCurrentUser().getUserID());
			map.put("out_flag", "");
			service.getObject("RouteChart.add_route_car_", map);
			
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
    public void patch_route_add_car_info() {
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("route_id", routeChart.getRoute_id());
			map.put("vehicle_vin", routeChart.getVIN());
			map.put("send_condition", routeChart.getSend_condition());
			String send_time = routeChart.getSend_condition().equals("2")?routeChart.getSend_time():"";
			map.put("send_time", send_time);
			//map.put("send_time", routeChart.getSend_time());
			map.put("send_order", routeChart.getSend_order());
			map.put("week_seq", routeChart.getWeek_seq());
			map.put("update_by", getCurrentUser().getUserID());
			map.put("out_flag", "");
			service.getObject("RouteChart.add_route_car_", map);
			
			if(map.get("out_flag").equals("-1"))
				printWriter("error");
			else if(map.get("out_flag").equals("1")){
				printWriter("车辆已存在！");
			} else {
				if(routeChart.getWeek_seq().equals("7"))
					printWriter("su"+map.get("out_flag"));
				else
					printWriter(map.get("out_flag"));
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			printWriter("error");
		}
	}
    public void route_add_car_info_p() {
		try {
			RouteChart chart = new RouteChart();
			chart.setRoute_id(routeChart.getRoute_id());
			chart.setVIN(routeChart.getVIN());
			chart.setSend_condition(routeChart.getSend_condition());
			String send_time = routeChart.getSend_condition().equals("2")?routeChart.getSend_time():"";
			chart.setSend_time(send_time);
			//chart.setSend_time(routeChart.getSend_time());
			chart.setSend_order(routeChart.getSend_order());
			chart.setWeek_seq(routeChart.getWeek_seq());
			chart.setUser_enterprise_id(getCurrentUser().getUserID());
			service.insert("RouteChart.add_route_car_info", chart);
			
			printWriter("success");
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
			map.put("week_seq", routeChart.getWeek_seq());
			map.put("out_flag", "");
			service.getObject("RouteChart.del_route_car_", map);
			
			if(map.get("out_flag").equals("-1"))
				printWriter("error");
			else if(map.get("out_flag").equals("1"))
				printWriter("车辆不存在！");
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
    
}
