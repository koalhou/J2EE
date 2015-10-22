package com.neusoft.clw.yunxing.routechart.chart.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.zdnew.terminalparams.ds.VehicleInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.opensymphony.xwork2.ActionContext;
/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.0 $ 2011-12-20 3:21:42 PM
 */
public class RouteChartDWR extends BaseAction{

	/** service共通类 */
	private transient Service service;   
	
	public List<List<RouteChart>> getChart(String route_id,Integer pageno, String vin, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		log.info("查询线路运行图,线路编号："+route_id+"——车牌号："+vin);
		
		List<RouteChart> result = null;
		List<List<RouteChart>> listlineroute = new ArrayList<List<RouteChart>>();
		if(!"".equals(chanNullToEmpty(route_id))){
			RouteChart queryObj = new RouteChart();
			queryObj.setRoute_id(route_id);
			//这里总是查询所有车辆
			if(!"".equals(chanNullToEmpty(vin))){
				queryObj.setVIN(vin);
			}
			queryObj.setUser_organization_id(user_org_id);
			try {
				result = service.getObjects("RouteChart.getInfoForChart", queryObj);
				listlineroute.add(result);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
	}
	public List<List<RouteChart>> getChartCarList(String route_id,Integer pageno, String vin, String user_org_id) {
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		log.info("查询线路运行图,线路编号："+route_id+"——车牌号："+vin);
		
		List<RouteChart> result = null;
		List<List<RouteChart>> listlineroute = new ArrayList<List<RouteChart>>();
		if(!"".equals(chanNullToEmpty(route_id))){
			Map<String,Object> mapv = new HashMap<String,Object>();
			mapv.put("route_id",route_id);
			try {
				result = service.getObjects("RouteChart.getcarInfobyentForChart", mapv);
				listlineroute.add(result);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
	}
	public List<List<RouteChart>> getChartCarListReflash(String route_id,Integer seq_week, String vin, String user_org_id) {
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		log.info("查询线路运行图,线路编号："+route_id+"——车牌号："+vin);
		
		List<RouteChart> result = null;
		List<List<RouteChart>> listlineroute = new ArrayList<List<RouteChart>>();
		if(!"".equals(chanNullToEmpty(route_id))){
			Map<String,Object> mapv = new HashMap<String,Object>();
			mapv.put("route_id",route_id);
			mapv.put("seq_week",seq_week);
			try {
				result = service.getObjects("RouteChart.getcarInfobyentForChartRe", mapv);
				listlineroute.add(result);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getChartByCar-result cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
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
				result = service.getObjects("RouteChart.getRouteInfos", queryObj);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getRouteInfosByRoute-result cause error:", e);
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
				result = service.getObjects("RouteChart.getVehByRouteid", queryObj);
			} catch (BusinessException e) {
				log.info("RouteChartDWR.getVehByRouteid-result cause error:", e);
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
	
	public void getDelAllCarbyroute_id(String route_id) {
		Map<String,Object> mapv = new HashMap<String,Object>();
		mapv.put("route_id",route_id);
		try {
			service.delete("RouteChart.delcarbyroute_id", mapv);
		} catch (BusinessException e) {
			log.info("RouteChartDWR.getChartByCar-result cause error:", e);
		}
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
