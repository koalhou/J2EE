package com.yutong.axxc.parents.api.vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.neusoft.parents.bean.VehicleReal;
import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.entity.vehicle.Vehicle;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.DistanceService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.SessionService;
import com.yutong.axxc.parents.tools.JacksonUtils;

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
	public Response getVehicleInfo(String token,String vehicle_vin) {
		logger.info("终端要求获取[ " + vehicle_vin + " ]的车辆信息");

		if (!org.springframework.util.StringUtils.hasText(vehicle_vin)) {
			logger.error("车辆信息异常，终端上传的车辆ID为NULL");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		vehicle_vin = StringUtils.strip(vehicle_vin);
		Vehicle vehicle = baseService.get("Vehicle.getVehicleInfo", vehicle_vin);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("vehicle_info", vehicle);
		if (vehicle != null) {
			String key = CachedCommon.CACHED_VEHICLE + vehicle_vin;
			boolean b = etagService.put(key, CachedCommon.CACHED_MINTER_2H, key);
			return Response.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap))
					.tag(key).build();
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
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		List<VehicleReal> vehicle=new ArrayList<VehicleReal>();
		for (String vin : list) {
			vehicle.add(distanceService.getVehicleRealtimeInfo(vin));
			
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("vehicle_real_infos", vehicle);

		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

}
