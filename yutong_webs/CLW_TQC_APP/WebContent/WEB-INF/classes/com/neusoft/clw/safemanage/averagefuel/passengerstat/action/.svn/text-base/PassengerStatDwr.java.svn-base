package com.neusoft.clw.safemanage.averagefuel.passengerstat.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.MDC;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.StringUtil;

public class PassengerStatDwr  extends BaseAction {
	/** service共通类 */
	private transient Service service;
	
	
	
	public String getEnterpreseId(String x){
		InputStream is = Constants.class.getResourceAsStream("/enterprise.properties");
	     Properties prop = new Properties();
	     try {
	         prop.load(is);
	     } catch (FileNotFoundException e) {
	       System.out.print("获取组织ID出错");
	     } catch (IOException e) {
	     	System.out.print("获取组织ID出错");
	     }

      if(x.equals("0")){
    	  return   (String)prop.get("yutong_enterprise_id");
      } 
      if(x.equals("1")){
    	  return   (String)prop.get("yichang_enterprise_id");
      }
      if(x.equals("2")){
    	  return   (String)prop.get("erchang_enterprise_id");
      }
	return "";
	}
	
	
	
	

	@SuppressWarnings("unchecked")
	public List<List<HashMap<String, String>>> getChart(String route_id,String treeType,Integer pageno, String begintime, String endtime, String user_org_id){
		MDC.put("modulename", "[lineMonitor]");
		log.info("getChart start");
		log.info("查询线路运行图,线路编号："+route_id);
		
		List<HashMap<String, String>> result = null;
		List<String> routlint = null;
		List<List<HashMap<String, String>>> listlineroute = new ArrayList<List<HashMap<String, String>>>();
		String route_class = treeType.equals("")?"":treeType.equals("routeup")?"0":treeType.equals("routedown")?"1":"2";
		if(!"".equals(route_id) && route_id != null){
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("user_organization_id", user_org_id);
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
					
					routlint = service.getObjects("PassengerStat.getlineInfobyentForChart", map);
					for(String str : routlint) {
						paramMap.put("route_id", str);
						paramMap.put("exe_date", DateUtil.getCurrentDay());
						paramMap.put("begintime", begintime);
						paramMap.put("endtime", endtime);
						result = service.getObjects("PassengerStat.getInfoForChart", paramMap);
						listlineroute.add(result);
					}
			} catch (BusinessException e) {
				log.info("PassengerStat cause error:", e);
			}
		}
		log.info("getChart end");
		return listlineroute;
	}
	@SuppressWarnings("unchecked")
	public List<HashMap<String, String>> getLineChart(String route_id,String site_id, String begin_time, String end_time){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Map<String, String> pMap = new HashMap<String, String>();
			pMap.put("route_id", route_id);
			pMap.put("site_id", site_id);
			pMap.put("begin_time", begin_time);
			pMap.put("end_time", end_time);
			result = service.getObjects("PassengerStat.getLineChart", pMap);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 需求变更后：堆积图
	 * @param route_id
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public List<HashMap<String, String>> getStackChart(String route_id, String begin_time, String end_time, String showVacationDetail){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Map<String, String> pMap = new HashMap<String, String>();
			pMap.put("route_id", route_id);
			pMap.put("begin_time", begin_time);
			pMap.put("end_time", end_time);
			pMap.put("showVacationDetail", showVacationDetail);
			result = service.getObjects("PassengerStat.getPassengerStatStackChart", pMap);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获得节假日信息
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public List<HashMap<String, String>> getHolidayInfo( String begin_time, String end_time){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Map<String, String> pMap = new HashMap<String, String>();			
			pMap.put("begin_time", begin_time);
			pMap.put("end_time", end_time);			
			result = service.getObjects("PassengerStat.getHolidayInfo", pMap);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 需求变更后：首页折线图
	 * @param route_id
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public List<HashMap<String, String>> getLineChartFirstPage(String yutong_enterprise_id, String yichang_enterprise_id, String erchang_enterprise_id, String begin_time, String end_time, String showVacation, String route_class){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Map<String, String> pMap = new HashMap<String, String>();
			pMap.put("yutong_enterprise_id",getEnterpreseId("0"));
			pMap.put("yichang_enterprise_id",getEnterpreseId("1"));
			pMap.put("erchang_enterprise_id",getEnterpreseId("2"));
			pMap.put("begin_time", begin_time);
			pMap.put("end_time", end_time);
			pMap.put("showVacation", showVacation);
			pMap.put("route_class", route_class);
			result = service.getObjects("PassengerStat.getLineChartFirstPage", pMap);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 需求变更后：首页折线图下面的统计数据
	 * @param route_id
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public List<HashMap<String, String>> getDataByOrganization(String yutong_enterprise_id, String yichang_enterprise_id, String erchang_enterprise_id, String begin_time, String end_time, String showVacation, String route_class){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Map<String, String> pMap = new HashMap<String, String>();
			pMap.put("yutong_enterprise_id",getEnterpreseId("0"));
			pMap.put("yichang_enterprise_id",getEnterpreseId("1"));
			pMap.put("erchang_enterprise_id",getEnterpreseId("2"));
			pMap.put("begin_time", begin_time);
			pMap.put("end_time", end_time);
			pMap.put("showVacation", showVacation);
			pMap.put("route_class", route_class);
			result = service.getObjects("PassengerStat.getDataByOrganization", pMap);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 线路详情页面图表下面的统计数据，根据线路id
	 * @param route_id
	 * @param begin_time
	 * @param end_time
	 * @return
	 */
	public List<HashMap<String, String>> getDataByRoute(String yutong_enterprise_id,String route_id, String begin_time, String end_time, String showVacationDetail){
		List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
		try {
			Map<String, String> pMap = new HashMap<String, String>();
			pMap.put("yutong_enterprise_id",getEnterpreseId("0"));
			pMap.put("route_id", route_id);
			pMap.put("begin_time", begin_time);
			pMap.put("end_time", end_time);
			pMap.put("showVacationDetail", showVacationDetail);			
			result = service.getObjects("PassengerStat.getDataByRoute", pMap);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return result;
	}
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	} 
}
