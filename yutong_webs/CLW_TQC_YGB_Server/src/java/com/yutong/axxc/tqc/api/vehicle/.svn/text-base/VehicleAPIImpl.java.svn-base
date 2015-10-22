package com.yutong.axxc.tqc.api.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.entity.vehicle.Vehicle;
import com.yutong.axxc.tqc.entity.vehicle.VehicleReal;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.DistanceService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.service.SessionService;
import com.yutong.axxc.tqc.tools.JacksonUtils;

public class VehicleAPIImpl implements VehicleAPI {
	private Logger logger = LoggerFactory.getLogger(VehicleAPIImpl.class);

	@Context
	private MessageContext context;

	@Autowired
	private BaseService baseService;

	@Autowired
	private EtagService etagService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private DistanceService distanceService;

	@Override
	public Response getVehicleInfo(String token,String req) {
		if (!StringUtils.hasText(req)) {
			logger.error("参数为空");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		Map map = (Map) JacksonUtils.jsonToMapRuntimeException(req);
		String search_date = String.valueOf(map.get("search_date"));
		String area_type = String.valueOf(map.get("area_type"));
		String line_range = String.valueOf(map.get("line_range"));

		String line_ids = "";
		String display = "";
		if("1".equals(area_type) && "1".equals(line_range)){
			line_ids = "4177";
			display = "一厂厂内";
		}else if("2".equals(area_type) && "1".equals(line_range)){
			line_ids = "3531"; 
			display = "二厂厂内";
		}else if("3".equals(line_range)){
			line_ids = "3529";
			display = "厂间";
		}
		
		logger.info("终端要求获取[ " + display + " ]的车辆信息");

		Map<String, String> conditions = new HashMap<String, String>();
		
		conditions.put("line_ids", line_ids);
		conditions.put("search_date", search_date);
		
		List<Vehicle> vehicles = baseService.getList("Vehicle.getVehicleInfo", conditions);

		for(Vehicle v : vehicles){
			v.setArea_type(area_type);
			v.setLine_range(line_range);
		}
		
		if (vehicles.size()>0) {
//			String key = CachedCommon.CACHED_VEHICLE + ;
//			boolean b = etagService.put(key, CachedCommon.CACHED_MINTER_1D, key);
//			sessionService.updateSession(token, key);
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(vehicles))./*tag(key).*/build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@Override
	public Response getVehicleRealInfo(String req) {

		Map mapVehicle_ids = JacksonUtils.jsonToMapRuntimeException(req);
		List<String> list = (List<String>) mapVehicle_ids.get("vehicle_vins");

		logger.info("终端要求获取[ " + list + " ]的车辆实时信息");

		if (list == null || list.size() == 0) {
			logger.error("车辆实时信息异常,请求车辆为空");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		List<VehicleReal> vehicle=new ArrayList<VehicleReal>();
		for (String vin : list) {
//			vehicle.add(distanceService.getVehicleRealtimeInfo(vin));
			
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("vehicle_real_infos", vehicle);

		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

}
