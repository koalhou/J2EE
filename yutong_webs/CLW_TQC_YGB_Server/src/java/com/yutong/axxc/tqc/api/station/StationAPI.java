package com.yutong.axxc.tqc.api.station;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;

public interface StationAPI
{
    /**
     * 获取厂外通勤线路信息
     * 
     * @param access_token 登录令牌
     * 
     * @param ETag 是否有更新标识
     * 
     * @param line_ids 线路ID集合
     * 
     * @return 线路信息
     */
    @POST
    @Path(value = "lineunionout")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getOutLinesByFilter(@HeaderParam("access_token") String token,String req);

    /**
     * 获取厂区范围线路信息
     * 
     * @param access_token 登录令牌
     * 
     * @param ETag 是否有更新标识
     * 
     * @param filter 过滤条件
     * 
     * @return 线路信息
     */
    @POST
    @Path(value = "lineunionin")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getInLinesByFilter(@HeaderParam("access_token") String token, String req);
    
    /**
     * 获取站点列表信息
     * 
     * @param ETag 是否更新标识
     * 
     * @return 站点列表信息
     * 
     */
    @POST
    @Path(value = "stations")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getSites(@HeaderParam("access_token") String token);
    
    /**
     * 获取站点区域信息接口
     * 
     * @param ETag 是否更新标识
     * 
     * @return 站点区域对应关系
     * 
     */
    @POST
    @Path(value = "areas")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getAreas(@HeaderParam("access_token") String token);
    
    /**
     * 获取站点提醒信息
     * 
     * @return 站点提醒信息
     */
    @POST
    @Path(value = "getremind")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getStationRemind(@HeaderParam("access_token") String token,String req);
    
    /**
     * 获取单条站点提醒信息
     * 
     * @return 站点提醒信息
     */
    @POST
    @Path(value = "remindsingle")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getStationRemindSingle(@HeaderParam("access_token") String token,String req);

    /**
     * 设置站点提醒
     * 
     */
    @POST
    @Path(value = "setremind")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response setStationRemind(String req);
    
    /**
     * 获取车辆行程信息
     * 
     * @param 厂区ID，线路类型
     * 
     * @return 车辆信息
     */
    @POST
    @Path(value = "vehicles")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getVehicleTripInfo(@HeaderParam("access_token") String token, String req);
 
    /**
     * 获取车辆实时信息
     * 
     * @param vehicle_id 车辆的vin
     * @return 车辆实时信息
     */
    @POST
    @Path(value = "realinfo")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getVehicleRealInfo(String req);    
    
    /**
     * 获取车辆司机信息
     * 
     * @param vehicle_id 车辆的vin
     * @return 车辆实时信息
     */
    @POST
    @Path(value = "driverinfo")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getVehicleDriverInfo(String req);

    /**
     * 获取车辆相对站点实时信息
     * 
     * @param site_ids 站点
     * 
     * @return 车辆站点实时对应信息
     */
    @POST
    @Path(value="position")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getVehicleSiteRealInfo(String req);
    	
    
    /**
     * 获取推荐站点接口
     * 
     */
    @POST
    @Path(value = "default")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getDefaultSites(@HeaderParam("access_token") String token, String req);
    
    /**
     * 获取收藏站点
     */
    
    @POST
    @Path(value = "getfaversites")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getFaversitesSites(@HeaderParam("access_token") String token, String req);
    
    /**
     * 设置收藏站点
     */
    
    @POST
    @Path(value = "setfaversites")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response setFaversitesSites(String req);
    
}
