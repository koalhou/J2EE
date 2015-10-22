package com.neusoft.clw.dispatch.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.0 $ 2011-12-20 3:21:42 PM
 */
public class RouteChartDWR extends BaseAction{

	/** service共通类 */
	private transient Service service;   
	
	public List<List<RouteChart>> getChart(String route_id,String treeType,Integer pageno, String vin, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		log.info("查询线路运行图,线路编号："+route_id+"——车牌号："+vin);
		
		List<RouteChart> result = null;
		List<String> routlint = null;
		List<List<RouteChart>> listlineroute = new ArrayList<List<RouteChart>>();
		String route_class = treeType.equals("")?"":treeType.equals("routeup")?"0":treeType.equals("routedown")?"1":"2";
		if(!"".equals(chanNullToEmpty(route_id))){
			RouteChart queryObj = new RouteChart();
			queryObj.setRoute_id(route_id);
			//这里总是查询所有车辆
			if(!"".equals(chanNullToEmpty(vin))){
				queryObj.setVIN(vin);
			}
			queryObj.setUser_organization_id(user_org_id);
			try {
				//这里写一个线路list 用来包含线路站点list
				//if(route_id.indexOf("ent")>=0) {
					//查询组织下线路 每次查询3条线路 分页
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("enterpriseId", user_org_id);
					map.put("route_id", StringUtil.toStringList(route_id));//route_id.substring(3, route_id.length())
					map.put("route_class", route_class);
					map.put("rowStart", (pageno-1)*3);
					map.put("rowEnd", pageno*3);
					
					routlint = service.getObjects("DispatchRouteChart.getlineInfobyentForChart", map);
					for(String str : routlint) {
						queryObj.setRoute_id(str);
						queryObj.setExe_date(DateUtil.getCurrentDay());
						result = service.getObjects("DispatchRouteChart.getInfoForChart", queryObj);
						listlineroute.add(result);
					}
//				} else {
//					result = service.getObjects("DispatchRouteChart.getInfoForChart", queryObj);
//					listlineroute.add(result);
//				}
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
	}
	public List<RouteChart> getoneChart(String route_id,String treeType,String date,Integer pageno, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		
		List<RouteChart> result = null;
		if(!"".equals(chanNullToEmpty(route_id))){
			try {
				Map<String,Object> mapv = new HashMap<String,Object>();
				mapv.put("exe_date", date);
				mapv.put("route_id",route_id);
				result = service.getObjects("DispatchRouteChart.getcarInfobyentForChart", mapv);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return result;
	}
	
	public List<List<RouteChart>> getcarrouteChart(Integer pageno, String vin,String date, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		
		List<RouteChart> result = null;
		List<RouteChart> routlint = null;
		List<List<RouteChart>> listlineroute = new ArrayList<List<RouteChart>>();
		if(!"".equals(chanNullToEmpty(vin))){
			RouteChart queryObj = new RouteChart();
			//这里总是查询所有车辆
			if(!"".equals(chanNullToEmpty(vin))){
				queryObj.setVIN(vin);
			}
			queryObj.setUser_organization_id(user_org_id);
			try {
				//查询车辆所属线路站点
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("vin", vin);
				map.put("exe_date", date);
				map.put("rowStart", (pageno-1)*3);
				map.put("rowEnd", pageno*3);
				routlint = service.getObjects("DispatchRouteChart.getlineInfobyvinForChart", map);
				for(RouteChart str : routlint) {
					queryObj.setRoute_id(str.getRoute_id());
					queryObj.setExe_date(date);
					result = service.getObjects("DispatchRouteChart.getInfoForChart", queryObj);
					for(RouteChart routec : result) {
						routec.setRoute_name(str.getRoute_name());
						routec.setRoute_class(str.getRoute_class());
					}
 					listlineroute.add(result);
				}
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
	}
	public Integer getcarcountrouteChart(Integer pageno, String vin,String date, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		
		Integer routlint = null;
		if(!"".equals(chanNullToEmpty(vin))){
			RouteChart queryObj = new RouteChart();
			//这里总是查询所有车辆
			if(!"".equals(chanNullToEmpty(vin))){
				queryObj.setVIN(vin);
			}
			queryObj.setUser_organization_id(user_org_id);
			try {
				//查询车辆所属线路站点
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("vin", vin);
				map.put("exe_date", date);
				map.put("rowStart", (pageno-1)*3);
				map.put("rowEnd", pageno*3);
				routlint = service.getCount("DispatchRouteChart.getlinecountInfobyvinForChart", map);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return routlint;
	}
	public List<List<RouteChart>> getChartCarList(String route_id,String treeType,Integer pageno, String time, String user_org_id) {
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		
		List<RouteChart> result = null;
		List<String> routlint = null;
		String route_class = treeType.equals("")?"":treeType.equals("routeup")?"0":treeType.equals("routedown")?"1":"2";
		List<List<RouteChart>> listlineroute = new ArrayList<List<RouteChart>>();
		if(!"".equals(chanNullToEmpty(route_id))){
			try {
				//这里写一个线路list 用来包含线路站点list
				//if(route_id.indexOf("ent")>=0) {
					//查询组织下线路 每次查询3条线路 分页
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("enterpriseId", user_org_id);
					map.put("route_id", StringUtil.toStringList(route_id));
					map.put("rowStart", (pageno-1)*3);
					map.put("rowEnd", (pageno)*3);
					map.put("route_class", route_class);
					routlint = service.getObjects("DispatchRouteChart.getlineInfobyentForChart", map);
					for(String str : routlint) {
						Map<String,Object> mapv = new HashMap<String,Object>();
						mapv.put("exe_date", time);
						mapv.put("route_id",str);
						result = service.getObjects("DispatchRouteChart.getcarInfobyentForChart", mapv);
						listlineroute.add(result);
					}
				/*} else {
					result = service.getObjects("DispatchRouteChart.getcarInfobyentForChart", mapv);
					listlineroute.add(result);
				}*/
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
	}
	public List<List<RouteChart>> getChartoneCarList(String vin,String time,String route_id, String user_org_id) {
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		
		List<RouteChart> result = null;
		List<Map<String,Object>> routlint = null;
		List<List<RouteChart>> listlineroute = new ArrayList<List<RouteChart>>();
		if(!"".equals(chanNullToEmpty(route_id))){
			Map<String,Object> mapv = new HashMap<String,Object>();
			mapv.put("route_id",route_id);
			mapv.put("exe_date", time);
			//mapv.put("vehicle_vin", vin);
			try {
				result = service.getObjects("DispatchRouteChart.getcarInfobyroute_idForChart", mapv);
				listlineroute.add(result);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
	}
	public Integer getLineSize(String route_id,String treeType,String vin, String user_org_id){
		log.info("getChart start");
		log.info("查询线路运行图,线路编号："+route_id+"——车牌号："+vin);
		
		Integer result = null;
		String route_class = treeType.equals("")?"":treeType.equals("routeup")?"0":treeType.equals("routedown")?"1":"2";
		if(!"".equals(chanNullToEmpty(route_id))){
			Map<String,Object> mapv = new HashMap<String,Object>();
			mapv.put("route_id",route_id);
			try {
				//这里写一个线路list 用来包含线路站点list
				/*if(route_id.indexOf("ent")>=0) {*/
					//查询组织下线路 每次查询3条线路 分页
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("enterpriseId", user_org_id);
					map.put("route_id", StringUtil.toStringList(route_id));
					map.put("route_class", route_class);
					result = service.getCount("DispatchRouteChart.getlineInfobyentForChartCount", map);
				/*} else {
					result = 1;
				}*/
			} catch (BusinessException e) {
				log.info("DispatchRouteChart.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return result;
	}
	
	public List<RouteChart> getRouteInfosByRoute(String route_id, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getRouteInfosByRoute start");
		log.info("通过线路查询 线路详细信息,线路编号："+route_id);
		
		List<RouteChart> result = null;
		if(route_id != null && !"".equals(route_id)){
			RouteChart queryObj = new RouteChart();
			queryObj.setRoute_id(route_id);
			queryObj.setUser_organization_id(user_org_id);
			try {
				result = service.getObjects("DispatchRouteChart.getRouteInfos", queryObj);
			} catch (BusinessException e) {
				log.info("DispatchRouteChart.getRouteInfos-result cause error:", e);
			}			
		}
		log.info("getRouteInfosByRoute end");
		return result;
	}
	
	public List<RouteChart> getVehByRoute(String route_id, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getVehByRoute start");
		log.info("通过线路查询 线路上的车,线路编号："+route_id);
		
		List<RouteChart> result = null;
		if(route_id != null && !"".equals(route_id)){
			RouteChart queryObj = new RouteChart();
			queryObj.setRoute_id(route_id);
			queryObj.setUser_organization_id(user_org_id);
			try {
				result = service.getObjects("DispatchRouteChart.getVehByRouteid", queryObj);
			} catch (BusinessException e) {
				log.info("DispatchRouteChart.getVehByRouteid-result cause error:", e);
			}
		}
		log.info("getVehByRoute end");
		return result;
	}
	
	public List<CarRunHistory> getIOinfosBySite(String route_id, String site_id, String vin, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getIOinfosBySite start");
		log.info("通过站点查询 此站点的进出站信息,线路编号："+route_id+",站点编号："+site_id+",VIN："+vin);
		
		List<CarRunHistory> result = null;
		if(route_id != null && !"".equals(route_id)){
			CarRunHistory queryObj = new CarRunHistory();
			queryObj.setRoute_id(new Long(route_id));
			queryObj.setSite_id(new Long(site_id));
			queryObj.setVIN(vin);
			queryObj.setUser_organization_id(user_org_id);
			try {
				result = service.getObjects("CarRunHistory.getTodayInfosBySite", queryObj);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getIOinfosBySite-result cause error:", e);
			}			
		}
		log.info("getIOinfosBySite end");
		return result;
	}
	
	/**
	 * 如果为null则转化为""
	 * @param strVar
	 * @return
	 */
	private String chanNullToEmpty(String strVar) {
		return strVar != null ? strVar : "";
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	} 

}
