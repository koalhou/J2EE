package com.yutong.axxc.tqc.api.msg;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;

public interface MsgAPI {
	/**
	 * 获取气象信息
	 * 
	 * @param region
	 *            用户所在城市信息，例如：郑州(真实信息使用uri转义)
	 * @return 天气信息
	 */
	@GET
	@Path(value = "weather/{region}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getWeather(@PathParam("region") String region);
	/**
	 * 
	  * 函数介绍：打卡记录
	  * 参数：
	  * 返回值：
	 */
	@POST
	@Path(value = "card")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response getPunch(String req);
	
	/**
	 * 
	  * 函数介绍：乘坐车辆信息
	  * 参数：
	  * 返回值：
	 */
	@GET
	@Path(value = "riding/{vehicle_id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response getVehicle(@PathParam("vehicle_id") String vin);
	
	/**
	 * 
	  * 函数介绍：历史消息
	  * 参数：
	  * 返回值：
	 */
	@POST
	@Path(value = "history")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response getHistory(String req);
}
