package com.yutong.axxc.parents.api.station;

import java.util.ArrayList;
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

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.entity.distance.DistanceMsg;
import com.yutong.axxc.parents.entity.site.GPS;
import com.yutong.axxc.parents.entity.site.Remind;
import com.yutong.axxc.parents.entity.site.Route;
import com.yutong.axxc.parents.entity.site.Station;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.DistanceService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.RemindService;
import com.yutong.axxc.parents.service.SessionService;
import com.yutong.axxc.parents.tools.JacksonUtils;

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

	// 2.4.1 获取学生路线信息接口
	@Override
	public Response getRoute(String token,String req) {
		Map map = JacksonUtils.jsonToMapRuntimeException(req);
		String sid = (String) map.get("cld_id");
		String type = (String) map.get("line_type");

		if (!StringUtils.hasText(type) || !StringUtils.hasText(sid)) {
			logger.error("参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		List<Route> routeList = baseService.getList("Station.getRouteByStudent", map);
		List<GPS> list=new ArrayList<GPS>();
		if(routeList!=null&&routeList.size()>0){
			Route route=routeList.get(0);
			list = baseService.getList("Station.getGPS4Route", route);
			route.setPoints(list);
			String etag = CachedCommon.CACHED_ROUTE + sid+"_"+type;
			if (!etagService.put(etag, CachedCommon.CACHED_MINTER_2H, etag)) {
				logger.error("缓存失败:{}", etag);
			}
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(route))
					.tag(etag).build();
		}

		return Response.status(Status.NO_CONTENT).build();
	}

	// 2.4.2 获取学生站点信息接口
	@Override
	public Response getStuStation(String token,String req) {
		Map map = JacksonUtils.jsonToMapRuntimeException(req);
		String sid = (String) map.get("cld_id");
		String type = (String) map.get("line_type");
		logger.debug("开始获取学生站点信息请求");

		if (!StringUtils.hasText(type) || !StringUtils.hasText(sid)) {
			logger.error("参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		Route route = baseService.get("Station.getRouteByStudent", map);
		if(route==null||route.getTripId()==null){
			return Response.status(Status.NO_CONTENT).build();
		}
		map.put("trip_id", route.getTripId());
		List<Station> tmp = baseService.getList("Station.getSpeStation", map);
		List<Station> list = baseService
				.getList("Station.getStuStation", route);
		if (tmp != null && tmp.size() > 0) {

			for (Station station : list) {
				if (tmp.get(0) != null
						&& station.getSiteId().equals(tmp.get(0).getSiteId())) {
					station.setSiteType(tmp.get(0).getSiteType());
				}
				if (tmp.get(1) != null
						&& station.getSiteId().equals(tmp.get(1).getSiteId())) {
					station.setSiteType(tmp.get(1).getSiteType());
				}
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("trip_id", route.getTripId());
		resultMap.put("line_id", route.getRouteId());
		resultMap.put("station_infos", list);

		String etag = CachedCommon.CACHED_STATION + sid+"_"+type;
		if (!etagService.put(etag, CachedCommon.CACHED_MINTER_2H, list)) {
			logger.error("缓存失败:{}", etag);
		}
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap))
				.tag(etag).build();
	}

	// 2.4.3 获取站点提醒信息接口
	@Override
	public Response getStuStationRemind(String token,String req) {
		 String usrId = context.getHttpHeaders().getHeaderString("usr_id");
		Map map = JacksonUtils.jsonToMapRuntimeException(req);
		String sid = (String) map.get("cld_id");
		String lineType = (String) map.get("line_type");
		logger.debug("开始获取学生站点信息请求");

		if (!StringUtils.hasText(lineType) || !StringUtils.hasText(sid)) {
			logger.error("参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}

		List<Remind> list = baseService.getList("Station.getStuStationRemind",
				map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("station_remind_infos", list);
		if (list != null && list.size() > 0) {
			String etag = CachedCommon.CACHED_REMIND + usrId+ "_" +sid + "_" + lineType;
			if (!etagService.put(etag, CachedCommon.CACHED_MINTER_1D, list)) {
				logger.error("缓存失败:{}", etag);
			}
			sessionService.updateSession(token, etag);
			return Response.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap))
					.tag(etag).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}

	// 2.4.4 设置站点提醒信息接口
	@Override
	public Response setStuStationRemind(String req) {
		String usrId = context.getHttpHeaders().getHeaderString("usr_id");
		Map map = (Map<String, String>) JacksonUtils
				.jsonToMapRuntimeException(req);
		String cld_id = (String) map.get("cld_id");
		String station_id = (String) map.get("station_id");
		Map map2 = (Map) map.get("station_remind_info");
		String remind_type = (String) map2.get("remind_type");
		String remind_value = (String) map2.get("remind_value");
		String remind_alias = (String) map2.get("remind_alias");
		String valid = (String) map2.get("valid");
		
		if (!StringUtils.hasText(valid)||!StringUtils.hasText(cld_id) || !StringUtils.hasText(remind_type)
				|| !StringUtils.hasText(remind_value)
				|| !StringUtils.hasText(station_id)) {
			logger.error("参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		Remind remind = new Remind();
		remind.setChildId(cld_id);
		remind.setRemindAlias(remind_alias);
		remind.setRemindType(remind_type);
		remind.setRemindValue(remind_value);
		remind.setSiteId(station_id);
		remind.setValid(valid);
		logger.info("设置提醒{}",remind);
		int ret=remindService.saveRemind(remind);
		
		etagService.del(CachedCommon.CACHED_REMIND + usrId+ "_" + cld_id + "_0");
		etagService.del(CachedCommon.CACHED_REMIND + usrId+ "_" + cld_id + "_1");
		
		if(ret==1){
			return Response.ok().build();
		}
		logger.error("保存站点设置失败");
		throw new ApplicationException(ErrorConstant.ERROR10024,
				Response.Status.BAD_REQUEST);
	}

	@Override
	public Response getLastRemind(String req) {
		Map map = (Map<String, String>) JacksonUtils
				.jsonToMapRuntimeException(req);
		String cld_id = (String) map.get("cld_id");
		
		if(!StringUtils.hasText(cld_id)){
			logger.error("参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}

		DistanceMsg msg=distanceService.getDistance(cld_id);
		if(msg!=null){
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(msg)).build();
		}
		return Response.status(Status.NO_CONTENT).build();
	}
}
