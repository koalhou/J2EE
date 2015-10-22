package com.yutong.axxc.tqc.api.station;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.entity.site.Area;
import com.yutong.axxc.tqc.entity.site.Driver;
import com.yutong.axxc.tqc.entity.site.GPS;
import com.yutong.axxc.tqc.entity.site.LinePoint;
import com.yutong.axxc.tqc.entity.site.Remind;
import com.yutong.axxc.tqc.entity.site.Route;
import com.yutong.axxc.tqc.entity.site.Site;
import com.yutong.axxc.tqc.entity.vehicle.SiteVehicleReal;
import com.yutong.axxc.tqc.entity.vehicle.Vehicle;
import com.yutong.axxc.tqc.entity.vehicle.VehicleReal;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.DistanceService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.service.RemindService;
import com.yutong.axxc.tqc.service.SessionService;
import com.yutong.axxc.tqc.tools.JacksonUtils;
import com.yutong.axxc.tqc.tools.PropertiesTools;

public class StationAPIImpl implements StationAPI {
	
	private Logger logger = LoggerFactory.getLogger(StationAPIImpl.class);

	@Context
	private MessageContext context;

	@Autowired
	protected BaseService baseService;

	@Autowired
	private EtagService etagService;

	@Autowired
	private RemindService remindService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private DistanceService distanceService;
	
//	private static String FACTORY1_INSIDE_ROUND_LINE = "4371";
//
//	private static String FACTORY2_INSIDE_ROUND_LINE = "151";
//	
//	private static String FACTORY1_INSIDE_ZDJSNL_LINE = "4409";
//	
//	private static String FACTORY1_INSIDE_JSBL_SITE = "3708";
//	
//	private static String CHANG_JIAN_LINE_1_TO_2 = "288";
//	
//	private static String CHANG_JIAN_LINE_2_TO_1 = "284";
//
//	private static String DEFAULT_SITE_IDS = "('3585','3843','3761','4085')";
//	
//	private static String FACTORY1_ORGANIZATION = "b4d34bd6-4015-400e-992a-316e99724976";
//	
//	private static String FACTORY2_ORGANIZATION = "4f2d60b5-21f7-4954-91fe-169fe810d3d4";
	
	private static int FIVE_MINUTES = 1000 * 60 * 5;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Override
	public Response getInLinesByFilter(String token,String req) {
		

		logger.debug("{}开始获取厂内通勤站点和线路信息请求");
		
		
		if (!StringUtils.hasText(req)) {
			logger.info("StationAPI ：lineunionin，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		String line_ids = "";
		boolean flag = false;
		if(req.indexOf("1100") > 0){
			line_ids += etagService.get("YGB_FACTORY1_INSIDE_ROUND_LINE") + "," + etagService.get("YGB_FACTORY1_INSIDE_ZDJSNL_LINE");
			flag = true;
		}
		if(req.indexOf("2100") > 0){
			if(flag){
				line_ids += "," + etagService.get("YGB_FACTORY2_INSIDE_ROUND_LINE");
			}else{
				flag = true;
				line_ids += etagService.get("YGB_FACTORY2_INSIDE_ROUND_LINE");
			}
		}
		if(req.indexOf("1001") > 0){
			if(flag){
				line_ids += "," + etagService.get("YGB_CHANG_JIAN_LINE_1_TO_2");
			}else{
				line_ids += etagService.get("YGB_CHANG_JIAN_LINE_1_TO_2");
				flag = true;
			}
		}
		if(req.indexOf("2001") > 0){
			if(flag){
				line_ids +=  "," + etagService.get("YGB_CHANG_JIAN_LINE_2_TO_1");
			}else{
				line_ids +=  etagService.get("YGB_CHANG_JIAN_LINE_2_TO_1");
			}
		}
		if(!flag){
			logger.info("StationAPI ：lineunionin，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		
		List<LinePoint> linePoints = baseService.getList("Station.getLinesByFilter", line_ids);
		
		Map<String, Route> lr = new HashMap<String, Route>();
		
		for(int i=0;i<linePoints.size();i++){
			LinePoint lp = linePoints.get(i);
			GPS gpsPoint = new GPS();
			gpsPoint.setLat(String.valueOf(lp.getLatitude()));
			gpsPoint.setLon(String.valueOf(lp.getLongitude()));
			if(lr.keySet().contains(String.valueOf(lp.getRoute_id()))){
				lr.get(String.valueOf(lp.getRoute_id())).getPoint_infos().add(gpsPoint);
			}else{
				Map<String, String> route_condition = new HashMap<String, String>();
				route_condition.put("route_id", String.valueOf(lp.getRoute_id()));
				route_condition.put("FACTORY1_INSIDE_ROUND_LINE", String.valueOf(etagService.get("YGB_FACTORY1_INSIDE_ROUND_LINE")));
				route_condition.put("FACTORY2_INSIDE_ROUND_LINE", String.valueOf(etagService.get("YGB_FACTORY2_INSIDE_ROUND_LINE")));
				route_condition.put("FACTORY1_INSIDE_ZDJSNL_LINE", String.valueOf(etagService.get("YGB_FACTORY1_INSIDE_ZDJSNL_LINE")));
				route_condition.put("CHANG_JIAN_LINE_1_TO_2", String.valueOf(etagService.get("YGB_CHANG_JIAN_LINE_1_TO_2")));
				route_condition.put("CHANG_JIAN_LINE_2_TO_1", String.valueOf(etagService.get("YGB_CHANG_JIAN_LINE_2_TO_1")));
				Route r = baseService.get("Station.getInRouteById", route_condition);
				
				r.setPoint_infos(new ArrayList<GPS>());
				r.setSite_infos(new ArrayList<Site>());
				r.getPoint_infos().add(gpsPoint);
				lr.put(String.valueOf(lp.getRoute_id()), r);
			}
			if(lp.getIs_site() != null){
				Map<String, String> conditions = new HashMap<String, String>();	
				conditions.put("emp_code", emp_code);
				conditions.put("site_id", lp.getIs_site());	
				List<Site> thisSite = baseService.getList("Station.getSiteById", conditions);
				Site s = thisSite.get(0);
				s.setSite_latitude(String.valueOf(lp.getLatitude()));
				s.setSite_longitude(String.valueOf(lp.getLongitude()));
				lr.get(String.valueOf(lp.getRoute_id())).getSite_infos().add(s);
			}
		}

		List<Route> results = new ArrayList<Route>();
//		for(String s : lr.keySet()){
//		}
		orderLineUnionIn(etagService.get("YGB_FACTORY2_INSIDE_ROUND_LINE"), lr, results);
		orderLineUnionIn(etagService.get("YGB_FACTORY1_INSIDE_ROUND_LINE"), lr, results);
		orderLineUnionIn(etagService.get("YGB_FACTORY1_INSIDE_ZDJSNL_LINE"), lr, results);
		orderLineUnionIn(etagService.get("YGB_CHANG_JIAN_LINE_1_TO_2"), lr, results);
		orderLineUnionIn(etagService.get("YGB_CHANG_JIAN_LINE_2_TO_1"), lr, results);
		
		String etag = CachedCommon.CACHED_ROUTE + line_ids;
		if (!etagService.put(etag, CachedCommon.CACHED_MINTER_1D, results)) {
			logger.error("缓存失败:{}", etag);
		}
		sessionService.updateSession(token, etag);
		
		if(results.size()>0){
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(results)).tag(etag).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
	
	
	private void orderLineUnionIn(Object s, Map<String, Route> lr, List<Route> results){
		if(lr.get(s) != null){
			for(int i=0;i< lr.get(s).getSite_infos().size();i++){
				if(i==0){
					lr.get(s).getSite_infos().get(i).setType("1");
				}else if(i==lr.get(s).getSite_infos().size() - 1){
					lr.get(s).getSite_infos().get(i).setType("2");
				}else{
					lr.get(s).getSite_infos().get(i).setType("3");
				}
			}
			results.add(lr.get(s));
		}
	}

	@Override
	public Response getOutLinesByFilter(String token, String req) {
		
		logger.debug("{}开始获取厂内通勤站点和线路信息请求");
		if (!StringUtils.hasText(req)) {
			logger.info("StationAPI ：lineunionout，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		List<?> list = JacksonUtils.jsonToListRuntimeException(req);
		String line_ids = "";
		for(Object s : list){
			line_ids += "'" + s + "',";
		}
		line_ids = line_ids.substring(0, line_ids.length()-1);
		
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		List<LinePoint> linePoints = baseService.getList("Station.getLinesByFilter", line_ids);
		
		Map<String, Route> lr = new HashMap<String, Route>();
		
		for(LinePoint lp : linePoints){
			GPS gpsPoint = new GPS();
			if(lp.getLatitude() != null ){
				gpsPoint.setLat(String.valueOf(lp.getLatitude()));
			}
			if(lp.getLongitude() != null){
				gpsPoint.setLon(String.valueOf(lp.getLongitude()));
			}
			if(lr.keySet().contains(String.valueOf(lp.getRoute_id()))){
				lr.get(String.valueOf(lp.getRoute_id())).getPoint_infos().add(gpsPoint);
			}else{
				Map<String, String> route_condition = new HashMap<String, String>();
				route_condition.put("route_id", String.valueOf(lp.getRoute_id()));
				Route r = baseService.get("Station.getOutRouteById", route_condition);
				
				r.setPoint_infos(new ArrayList<GPS>());
				r.setSite_infos(new ArrayList<Site>());
				if(null != lp.getLongitude() && null != lp.getLatitude()){
					r.getPoint_infos().add(gpsPoint);
				}
				lr.put(String.valueOf(lp.getRoute_id()), r);
			}
			if(lp.getIs_site() != null){
				Map<String, String> conditions = new HashMap<String, String>();	
				conditions.put("emp_code", emp_code);
				conditions.put("site_id", lp.getIs_site());	
				List<Site> thisSite = baseService.getList("Station.getSiteById", conditions);
				Site s = thisSite.get(0);
				s.setSite_latitude(String.valueOf(lp.getLatitude()));
				s.setSite_longitude(String.valueOf(lp.getLongitude()));
				lr.get(String.valueOf(lp.getRoute_id())).getSite_infos().add(s);
			}
		}

		List<Route> results = new ArrayList<Route>();
		for(String s : lr.keySet()){
			for(int i=0;i< lr.get(s).getSite_infos().size();i++){
				if(i==0){
					lr.get(s).getSite_infos().get(i).setType("1");
				}else if(i==lr.get(s).getSite_infos().size() - 1){
					lr.get(s).getSite_infos().get(i).setType("2");
				}else{
					lr.get(s).getSite_infos().get(i).setType("3");
				}
			}
			results.add(lr.get(s));
		}
		
		String etag = CachedCommon.CACHED_ROUTE + line_ids;
		
		if (!etagService.put(etag, CachedCommon.CACHED_MINTER_1D, results)) {
			logger.error("缓存失败:{}", etag);
		}
		sessionService.updateSession(token, etag);		
		if(results.size()>0){
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(results)).tag(etag).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}	
	
	@Override
	public Response getSites(String token) {
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("emp_code", emp_code);
		conditions.put("fac1", String.valueOf(etagService.get("YGB_FACTORY1_ORGANIZATION")));
		conditions.put("fac2", String.valueOf(etagService.get("YGB_FACTORY2_ORGANIZATION")));
		
		List<Site> sites = baseService.getList("Station.getSites",conditions);
		if(sites.size()>0){
			/**
			 * 厂区判断和早晚班判断优化
			 * 该部分功能放在SQL中实现，否则会出现多次链接数据库
			 */
//			for(int i=0;i<sites.size();i++){
//				boolean fac1 = false;
//				boolean fac2 = false;
//				boolean morning = false;
//				boolean evening = false;
//				List<Route> routes_by = baseService.getList("Station.getRouteBySiteId", sites.get(i).getId());
//				for(Route r : routes_by){
//					if(etagService.get("YGB_FACTORY1_ORGANIZATION").equals(r.getLine_range())){
//						fac1 = true;
//					}
//					if(etagService.get("YGB_FACTORY2_ORGANIZATION").equals(r.getLine_range())){
//						fac2 = true;
//					}
//					if("0".equals(r.getStatus_range())){
//						morning = true;
//					}
//					if("1".equals(r.getStatus_range())){
//						evening = true;
//					}
//				}
//				if(fac1 && fac2){
//					sites.get(i).setArea_type("-1");
//				}else if(fac2){
//					sites.get(i).setArea_type("2");
//				}else if(fac1){
//					sites.get(i).setArea_type("1");
//				}else{
//					sites.remove(i);
//					i--;
//					continue;
//				}
//				if(morning && evening){
//					sites.get(i).setStatus_range("-1");
//				}else if(morning){
//					sites.get(i).setStatus_range("0");
//				}else{
//					sites.get(i).setStatus_range("1");
//				}
//			}
			
			String etag = CachedCommon.CACHED_STATION + emp_code + "_all";
			
			if (!etagService.put(etag, CachedCommon.CACHED_MINTER_12H, sites)) {
				logger.error("缓存失败:{}", etag);
			}
			sessionService.updateSession(token, etag);
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(sites)).tag(etag).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@Override
	public Response getDefaultSites(String token, String req) {
		Map<?,?> map = (Map<?,?>)JacksonUtils.jsonToMapRuntimeException(req);
		Object area_type = map.get("area_type");
		
		if(null == area_type){
			logger.info("StationAPI ：lineunionout，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("emp_code", emp_code);
		conditions.put("site_ids", String.valueOf(etagService.get("YGB_DEFAULT_SITE_IDS")));
		conditions.put("fac1", String.valueOf(etagService.get("YGB_FACTORY1_ORGANIZATION")));
		conditions.put("fac2", String.valueOf(etagService.get("YGB_FACTORY2_ORGANIZATION")));
//		conditions.put("area_type", String.valueOf(area_type));
		
		List<Site> sites = baseService.getList("Station.getSites",conditions);
		List<Site> resultSites = new ArrayList<Site>();
		if(sites.size()>0){
			for(Site s :sites){
				if(s.getArea_type().equals(String.valueOf(area_type)) || "-1".equals(s.getArea_type())){
					s.setArea_type(String.valueOf(area_type));
					resultSites.add(s);
				}
			}
//			for(int i=0;i<sites.size();i++){
//				boolean fac1 = false;
//				boolean fac2 = false;
//				boolean morning = false;
//				boolean evening = false;
//				List<Route> routes_by = baseService.getList("Station.getRouteBySiteId", sites.get(i).getId());
//				for(Route r : routes_by){
//					if(String.valueOf(etagService.get("YGB_FACTORY1_ORGANIZATION")).equals(r.getLine_range())){
//						fac1 = true;
//					}
//					if(String.valueOf(etagService.get("YGB_FACTORY2_ORGANIZATION")).equals(r.getLine_range())){
//						fac2 = true;
//					}
//					if("0".equals(r.getStatus_range())){
//						morning = true;
//					}
//					if("1".equals(r.getStatus_range())){
//						evening = true;
//					}
//				}
//				if(fac1 && fac2){
//					sites.get(i).setArea_type("-1");
//				}else if(fac2 && "2".equals(String.valueOf(area_type))){
//					sites.get(i).setArea_type("2");
//				}else if(fac1 && "1".equals(String.valueOf(area_type))){
//					sites.get(i).setArea_type("1");
//				}else{
//					sites.remove(i);
//					i--;
//					continue;
//				}
//				if(morning && evening){
//					sites.get(i).setStatus_range("-1");
//				}else if(morning){
//					sites.get(i).setStatus_range("0");
//				}else{
//					sites.get(i).setStatus_range("1");
//				}
//			}
			
			String etag = CachedCommon.CACHED_STATION + emp_code + "_" + area_type + "_" + "default";
			
			if (!etagService.put(etag, CachedCommon.CACHED_MINTER_1D, resultSites)) {
				logger.error("缓存失败:{}", etag);
			}
			sessionService.updateSession(token, etag);
			
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultSites)).tag(etag).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@Override
	public Response getVehicleTripInfo(String token,String req) {
		Map<?,?> map = JacksonUtils.jsonToMapRuntimeException(req);
		Object search_date = map.get("search_date");
		Object line_ids_data = map.get("line_ids");
//		Object area_type = map.get("area_type");
//		Object line_range = map.get("line_range");
//		
		if (null == search_date || null == line_ids_data) {
			logger.info("StationAPI ：vehicles，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
//
//		String display = "";
//		if("1".equals(String.valueOf(area_type)) && "1".equals(String.valueOf(line_range))){
//			line_ids = String.valueOf(etagService.get("YGB_FACTORY1_INSIDE_ROUND_LINE"));
//			line_ids += "," + String.valueOf(etagService.get("YGB_FACTORY1_INSIDE_ZDJSNL_LINE"));
//			display = "一厂厂内，一厂直达技术南楼";
//		}/*else if("1".equals(String.valueOf(area_type)) && "0".equals(String.valueOf(line_range))){
//			display = "一厂直达技术南楼";
//		}*/else if("2".equals(String.valueOf(area_type)) && "1".equals(String.valueOf(line_range))){
//			line_ids = String.valueOf(etagService.get("YGB_FACTORY2_INSIDE_ROUND_LINE")); 
//			display = "二厂厂内";
//		}else if("3".equals(String.valueOf(line_range))){
//			line_ids = String.valueOf(etagService.get("YGB_CHANG_JIAN_LINE_1_TO_2")) + "," + String.valueOf(etagService.get("YGB_CHANG_JIAN_LINE_2_TO_1"));
//			display = "厂间";
//		}
		
//		logger.info("终端要求获取[ " + display + " ]的车辆信息");
//		String line_ids = String.valueOf(line_ids_data);
		String line_ids = "";
		for(String o : (List<String>)line_ids_data){
			line_ids += String.valueOf(o) + ",";
		}
		line_ids = line_ids.substring(0,line_ids.length() - 1);
		Map<String, String> conditions = new HashMap<String, String>();
		
		conditions.put("line_ids", line_ids); 
		conditions.put("search_date", String.valueOf(search_date));
		
		List<Vehicle> vehicles = baseService.getList("Vehicle.getVehicleTripInfo", conditions);

		for(Vehicle v : vehicles){
//			v.setArea_type(String.valueOf(area_type));
//			v.setLine_range(String.valueOf(line_range));
			if(v.getPlan_start_time() != null){
				v.setPlan_start_time(search_date + v.getPlan_start_time().replace(":", "") + "00");
			}
		}
		
		if (vehicles.size()>0) {
			String etag = CachedCommon.CACHED_VEHICLE + line_ids;
//			if(!etagService.put(etag, CachedCommon.CACHED_MINTER_20M, vehicles)){
//				logger.error("缓存失败:{}", etag);
//			}
//			sessionService.updateSession(token, etag);
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(vehicles)).tag(etag).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@Override
	public Response getVehicleRealInfo(String req) {

		List<Map<?, ?>> list = (List<Map<?, ?>>) JacksonUtils.jsonToListRuntimeException(req);
		List<VehicleReal> vehicleReals = new ArrayList<VehicleReal>();
		for(Map<?,?> map : list){
			logger.info("终端要求获取[ " + map.get("vehicle_vin") + " ]的车辆实时信息");
			
			Object vehicle_vin = map.get("vehicle_vin");
			Object line_id = map.get("line_id");
			
			if (map == null || map.size() == 0 || vehicle_vin == null || line_id == null) {
				logger.info("StationAPI ：getVehicleRealInfo，关键参数不足！");
				throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
			}
			
			VehicleReal vr = distanceService.getVehicleRealtimeInfo(String.valueOf(vehicle_vin), String.valueOf(line_id));
			if(null != vr){
				
				String status = "00";	
				try {
					if(null == vr.getUpdate_time()||(new Date()).getTime() - sdf.parse(vr.getUpdate_time()).getTime() > FIVE_MINUTES){
						status += "0";
					}else{
						status += "1";
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(vr.getSpeed() == null || "".equals(vr.getSpeed()) || Float.valueOf(vr.getSpeed()) == 0){
					status += "0";
				}else{
					status += "1";
				}
				if("1".equals(vr.getFire_up_state())){
					status += "1";
				}else{
					status += "0";
				}
				vr.setStatus(status);
				vehicleReals.add(vr);
			}
		}
		if(vehicleReals.size() != 0){
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(vehicleReals)).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();

	}

	@Override
	public Response getAreas(String token) {

		List<Area> areas = baseService.getList("Station.getAreas");
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
//		delSiteCache(emp_code);
		if(areas.size()>0){
			String etag = CachedCommon.CACHED_AREA + "all";
			if(!etagService.put(etag, CachedCommon.CACHED_MINTER_1D, areas)){
				logger.error("缓存失败:{}", etag);
			}
			sessionService.updateSession(token, etag);
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(areas)).tag(etag).build();
		}
		
		return Response.status(Response.Status.NO_CONTENT).build();		
	}

	@Override	
	public Response getVehicleSiteRealInfo(String req) {
		
		Map<?,?> map = JacksonUtils.jsonToMapRuntimeException(req);
		Object search_date = map.get("search_date");
		Object station_ids = map.get("station_ids");
		Object status_range = map.get("status_range"); 
		Object area_type = map.get("area_type");
		
		if(search_date == null || station_ids == null || status_range == null || area_type == null){
			logger.info("StationAPI ：getVehicleSiteRealInfo，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		List<SiteVehicleReal> svrs = new ArrayList<SiteVehicleReal>();
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		
		for(Object o : (List<?>)station_ids){
			String site_id = String.valueOf(o);
			SiteVehicleReal svr = new SiteVehicleReal();
			
			Map<String, String> site_conditions = new HashMap<String, String>();
			site_conditions.put("emp_code", emp_code);
			site_conditions.put("site_id", site_id);
			
			List<Site> thisSite = baseService.getList("Station.getSiteById", site_conditions);
			if(thisSite == null || thisSite.size()==0){
				logger.info("StationAPI ：getVehicleSiteRealInfo，查无此站点！");
//				throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
				return Response.status(Response.Status.NO_CONTENT).build();
			}
			Site s = thisSite.get(0);
			
			svr.setLine_range("2");
			svr.setStatus_range(String.valueOf(status_range));
			svr.setSite_id(site_id);
			svr.setBelong_area(s.getBelong_area_id());
			svr.setSite_name(s.getName());
			svr.setAlias(s.getAlias());
			svr.setGps_lat(s.getSite_latitude());
			svr.setGps_lon(s.getSite_longitude());
			svr.setStatus(s.getStatus());
			/**
			 * TO DO svr.setPlan_arrive_time("");
			 */
			svr.setFavorates(s.getFavorites());

			svr.setVehicles(new ArrayList<VehicleReal>());
			Map<String, String> trip_exe_conditions = new HashMap<String, String>();
			trip_exe_conditions.put("site_id", site_id);
			trip_exe_conditions.put("search_date", String.valueOf(search_date));
			trip_exe_conditions.put("status_range", String.valueOf(status_range));
			if("1".equals(String.valueOf(area_type))){
				trip_exe_conditions.put("area_type", String.valueOf(etagService.get("YGB_FACTORY1_ORGANIZATION")));
			}
			if("2".equals(String.valueOf(area_type))){
				trip_exe_conditions.put("area_type", String.valueOf(etagService.get("YGB_FACTORY2_ORGANIZATION")));
			}
			if(null != etagService.get("YGB_LINES_OPENED") && !"".equals(String.valueOf(etagService.get("YGB_LINES_OPENED")))){
				trip_exe_conditions.put("lines_opened", String.valueOf(etagService.get("YGB_LINES_OPENED")));
			}
			List<Map<String ,String>> trips = baseService.getList("Station.getTripsBySite", trip_exe_conditions);
			for(Map<String,String> trip : trips){
				trip.put("site_id", site_id);
				trip.put("emp_code", emp_code);
				trip.put("search_date", String.valueOf(search_date));
				trip.put("jsbl_site", String.valueOf(etagService.get("YGB_FACTORY1_INSIDE_JSBL_SITE")));
				trip.put("status_range", String.valueOf(status_range));
				//早班逻辑
				if("0".equals(String.valueOf(status_range))){
					if("1".equals(trip.get("STATUS"))){
						VehicleReal vr = baseService.get("Station.getSiteVehicleRealA", trip);
						vr.setVehicle_number(String.valueOf(trip.get("VEHICLE_CODE")));
						vr.setLine_id(String.valueOf(trip.get("LINE_ID")));
						if(vr.getRemind_type() != null){
							vr.setRemind_type(!"0".equals(vr.getValue1()) ? "1":"0");
						}
						vr.setRemind_value(vr.getValue1() != null && !"0".equals(vr.getValue1())? vr.getValue1(): vr.getValue2());
						if("0".equals(vr.getArrive_status())){
							Map<String, String> nearest_point = baseService.get("Station.getNearPoints", trip);
							if(null != nearest_point && null != nearest_point.get("STANDARD_TIME")){
								vr.setPlan_arrive_time(sdf.format(new Date((new Date()).getTime() + Long.valueOf(String.valueOf(nearest_point.get("STANDARD_TIME"))) * 1000 )));
							}else{
								vr.setPlan_arrive_time("0");
							}
						}else{
							vr.setPlan_arrive_time("0");
						}
						svr.getVehicles().add(vr);
					}else {
						VehicleReal vr = baseService.get("Station.getSiteVehicleRealB", trip);
						vr.setVehicle_number(String.valueOf(trip.get("VEHICLE_CODE")));
						vr.setLine_id(String.valueOf(trip.get("LINE_ID")));
						if(vr.getRemind_type() != null){
							vr.setRemind_type(!"0".equals(vr.getValue1()) ? "1":"0");
						}
						vr.setRemind_value(vr.getValue1() != null && !"0".equals(vr.getValue1())? vr.getValue1(): vr.getValue2());
						if("2".equals(trip.get("STATUS"))){
							vr.setArrive_status("2");
						}
						if("0".equals(trip.get("STATUS"))){
							vr.setArrive_status("0");
						}
						svr.getVehicles().add(vr);
					}
				}
				//晚班逻辑
				if("1".equals(String.valueOf(status_range))){
					VehicleReal vr = baseService.get("Station.getSiteVehicleRealC", trip);
					vr.setVehicle_number(String.valueOf(trip.get("VEHICLE_CODE")));
					vr.setLine_id(String.valueOf(trip.get("LINE_ID")));
					if(vr.getRemind_type() != null){
						vr.setRemind_type(!"0".equals(vr.getValue1()) ? "1":"0");
					}
					vr.setRemind_value(vr.getValue1() != null && !"0".equals(vr.getValue1())? vr.getValue1(): vr.getValue2());
					if("2".equals(trip.get("STATUS")) || "1".equals(vr.getArrive_status())){
						//已发车
						vr.setArrive_status("5");
					}else{
						//未发车
						vr.setArrive_status("3");
					}
					svr.getVehicles().add(vr);
				}
			}
			svrs.add(svr);
		}
//		logger.info("终端要求获取[ " +  + " ]的车辆实时信息");		
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(svrs))./*tag(key).*/build();
	}
	
	// 2.4.3 获取站点提醒信息接口
	@Override
	public Response getStationRemind(String token,String req) {
		logger.debug("开始获取站点提醒信息请求");
		Map<?,?> map = JacksonUtils.jsonToMapRuntimeException(req);
		if(map.get("area_type") == null ){
			logger.info("StationAPI ：getremind，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("emp_code", emp_code);
		conditions.put("area_type", String.valueOf(map.get("area_type")));
		
		List<Remind> list = baseService.getList("Station.getStationRemind", conditions);
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		resultMap.put("station_remind_infos", list);
		if (list != null && list.size() > 0) {
//			String etag = CachedCommon.CACHED_REMIND + sid + "_" + lineType;
//			if (!etagService.put(etag, 300, list)) {
//				logger.error("缓存失败:{}", etag);
//			}
//			sessionService.updateSession(token, etag);
			for(int i=0;i<list.size();i++){
				if("0".equals(list.get(i).getRemind_type())){
					list.get(i).setRemind_value(list.get(i).getTime());
				}else if("1".equals(list.get(i).getRemind_type())){
					list.get(i).setRemind_value(list.get(i).getDistance());
				}else {
					list.get(i).setRemind_value("0");
				}
			}
			
			String etag = CachedCommon.CACHED_REMIND + map.get("area_type") + "_" + emp_code;
			
			if(!etagService.put(etag, CachedCommon.CACHED_MINTER_30M, list)){
				logger.error("缓存失败:{}", etag);
			}
			sessionService.updateSession(token, etag);
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(list)).tag(etag).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}


	@Override
	public Response getStationRemindSingle(String token, String req) {
		logger.debug("开始获取站点提醒信息请求");
		Map<?,?> map = JacksonUtils.jsonToMapRuntimeException(req);
		if(map.get("area_type") == null || map.get("status_range") == null || map.get("station_id") == null){
			logger.info("StationAPI ：stationremindsingle，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");

		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("emp_code", emp_code);
		conditions.put("area_type", String.valueOf(map.get("area_type")));
		conditions.put("status_range", String.valueOf(map.get("status_range")));
		conditions.put("site_id", String.valueOf(map.get("station_id")));
		if(null != map.get("vehicle_vin")){
			conditions.put("vehicle_vin", String.valueOf(map.get("vehicle_vin")));
		}
		
		List<Remind> list = baseService.getList("Station.getStationRemindSingle", conditions);
		if (list != null && list.size() > 0) {
			if(list.get(0).getTime() != null && !"0".equals(list.get(0).getTime())){
				list.get(0).setRemind_value(list.get(0).getTime());
			}
			if(list.get(0).getDistance() != null && !"0".equals(list.get(0).getDistance())){
				list.get(0).setRemind_value(list.get(0).getDistance());
			}
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(list.get(0))).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
	
	// 2.4.4 设置站点提醒信息接口
	@Override
	public Response setStationRemind(String req) {
		List<Map<?,?>> maps = (List<Map<?,?>>)JacksonUtils.jsonToListRuntimeException(req);
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		for(Map<?,?> map : maps){
			Object note_id = map.get("id");
			Object remind_status = map.get("remind_status");
			if (remind_status == null)  {
				logger.info("StationAPI ：setremind，关键参数不足！");
				throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
			}
			if(note_id == null){
				if (!(remindService.saveRemind(map,emp_code) == 1)){
					logger.info("StationAPI ：setremind，提醒设置插库失败！");
					throw new ApplicationException(ErrorConstant.ERROR99999, Response.Status.BAD_REQUEST);
				}
			}else{
				if(!(remindService.updateRemind(map,emp_code) == 1)){
					logger.info("StationAPI ：setremind，提醒设置更新失败！");
					throw new ApplicationException(ErrorConstant.ERROR99999, Response.Status.BAD_REQUEST);
				}
			}
		}
		String etag1 = CachedCommon.CACHED_REMIND + "1" + "_" + emp_code;
		String etag2 = CachedCommon.CACHED_REMIND + "2" + "_" + emp_code;
		String etag3 = CachedCommon.CACHED_STATION + emp_code + "_all";
		etagService.del(etag1);
		etagService.del(etag2);
		etagService.del(etag3);
		return Response.ok().build();
	}


	@Override
	public Response getFaversitesSites(String token, String req) {
		Map<?,?> map = (Map<?,?>)JacksonUtils.jsonToMapRuntimeException(req);
		Object area_type = map.get("area_type");
		
		if(area_type == null ){
			logger.info("StationAPI ：getFaversitesSites，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("emp_code", emp_code);
//		conditions.put("area_type", String.valueOf(area_type));
		conditions.put("fac1", String.valueOf(etagService.get("YGB_FACTORY1_ORGANIZATION")));
		conditions.put("fac2", String.valueOf(etagService.get("YGB_FACTORY2_ORGANIZATION")));
		
		List<Site> sites = baseService.getList("Station.getSiteByEmpCode" , conditions);
		List<Site> resultSites = new ArrayList<Site>();
		if(sites.size()>0){
			for(Site s :sites){
				if(s.getArea_type().equals(String.valueOf(area_type)) || "-1".equals(s.getArea_type())){
					s.setArea_type(String.valueOf(area_type));
					resultSites.add(s);
				}
			}
//			int total = ;
//			for(int i=0;i < sites.size();i++){
//				boolean fac1 = false;
//				boolean fac2 = false;
//				boolean morning = false;
//				boolean evening = false;
//				List<Route> routes_by = baseService.getList("Station.getRouteBySiteId", sites.get(i).getId());
//				for(Route r : routes_by){
//					if(String.valueOf(etagService.get("YGB_FACTORY1_ORGANIZATION")).equals(r.getLine_range())){
//						fac1 = true;
//					}
//					if(String.valueOf(etagService.get("YGB_FACTORY2_ORGANIZATION")).equals(r.getLine_range())){
//						fac2 = true;
//					}
//					if("0".equals(r.getStatus_range())){
//						morning = true;
//					}
//					if("1".equals(r.getStatus_range())){
//						evening = true;
//					}
//				}
//				if(fac1 && fac2){
//					sites.get(i).setArea_type("-1");
//				}else if(fac2 && "2".equals(String.valueOf(area_type))){
//					sites.get(i).setArea_type("2");
//				}else if(fac1 && "1".equals(String.valueOf(area_type))){
//					sites.get(i).setArea_type("1");
//				}else{
//					sites.remove(i);
//					i--;
//					continue;
//				}
//				if(morning && evening){
//					sites.get(i).setStatus_range("-1");
//				}else if(morning){
//					sites.get(i).setStatus_range("0");
//				}else{
//					sites.get(i).setStatus_range("1");
//				}
//			}
			
			String etag = CachedCommon.CACHED_STATION + area_type + "_" + emp_code;
			
			if (!etagService.put(etag, CachedCommon.CACHED_MINTER_2H, sites)) {
				logger.error("缓存失败:{}", etag);
			}
			sessionService.updateSession(token, etag);
			
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultSites)).tag(etag).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	private void delSiteCache(String emp_code){
		String etag1 = CachedCommon.CACHED_STATION + "1" + "_" + emp_code;
		String etag2 = CachedCommon.CACHED_STATION + "2" + "_" + emp_code;
		String all = CachedCommon.CACHED_STATION + emp_code + "_all";
		String defaulter1 = CachedCommon.CACHED_STATION + emp_code + "_" + "1" + "_" + "default";
		String defaulter2 = CachedCommon.CACHED_STATION + emp_code + "_" + "2" + "_" + "default";
		etagService.del(etag1);
		etagService.del(etag2);
		etagService.del(all);
		etagService.del(defaulter1);
		etagService.del(defaulter2);
	}

	@Override
	public Response setFaversitesSites(String req) {
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		List<?> list = JacksonUtils.jsonToListRuntimeException(req);
		if(null == list || list.size() == 0){
			logger.info("StationAPI ：setFaversitesSites，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		for(Object o : list){
			Map<?, ?> m = (Map<?,?>)o;
			Object station_id = m.get("station_id");
			Object favorates = m.get("favorites");
			if(station_id == null || favorates == null){
				logger.info("StationAPI ：setFaversitesSites，关键参数不足！");
				throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
			}
			Map<String, String> conditions = new HashMap<String,String>();
			conditions.put("emp_code", emp_code);
			conditions.put("site_id", String.valueOf(station_id));
			if("1".equals(String.valueOf(favorates))){
				Site site = baseService.get("Station.getFavorSiteById",conditions);
				if(site == null){
					baseService.update("Station.insertFavorSites", conditions);
				}
			}else{
				baseService.delete("Station.deleteFavorSites", conditions);
			}
		}
		delSiteCache(emp_code);
		return Response.ok().build();
	}


	@Override
	public Response getVehicleDriverInfo(String req) {

		List<Map<?, ?>> list = (List<Map<?, ?>>) JacksonUtils.jsonToListRuntimeException(req);
		Driver dr = null;
		for(Map<?,?> map : list){
			logger.info("终端要求获取[ " + map.get("vehicle_vin") + " ]的车辆实时信息");
			
			Object vehicle_vin = map.get("vehicle_vin");
			Object line_id = map.get("line_id");
			
			if (map == null || map.size() == 0 || vehicle_vin == null || line_id == null) {
				logger.info("StationAPI ：getVehicleRealInfo，关键参数不足！");
				throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
			}
			
			Map<String, String>  conditions = new HashMap<String, String>();
			conditions.put("LINE_ID", String.valueOf(line_id));
			conditions.put("VEHICLE_VIN", String.valueOf(vehicle_vin));
			dr = (Driver)baseService.getList("Algorithm.getDriverByVehicleAndLine",conditions).get(0);
		}
		if(dr != null){
			String host = "";
			try {
				host = PropertiesTools.readValue(ModCommonConstant.SYS_CONF_FILE, "news_img_url_target");
				if(null != dr.getUrl()){
					File d = new File(host.replace("http://125.46.82.44:8030/version", "") + dr.getDriver_id() + "/");
					if(!d.exists()){
						d.mkdirs();
					}
					File f = new File(host.replace("http://125.46.82.44:8030/version", "") + dr.getDriver_id() + "/1.jpg");
					FileOutputStream os = new FileOutputStream(f);   
					os.write(dr.getPhotoContent());
					os.close();
				dr.setUrl(host + dr.getDriver_id() + "/1.jpg");
				}else{
					dr.setUrl("");
				}
				List<Driver> drivers = new ArrayList<Driver>();
				drivers.add(dr);
				return Response.ok().entity(JacksonUtils.toJsonRuntimeException(drivers)).build();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
