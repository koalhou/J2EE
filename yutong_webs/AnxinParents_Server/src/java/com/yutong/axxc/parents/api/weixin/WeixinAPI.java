package com.yutong.axxc.parents.api.weixin;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface WeixinAPI
{

    /**
     * 微信鉴权接口
     * 
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return 车辆信息
     */
    @GET
    @Path(value = "start/{signature}/{timestamp}/{nonce}/{echostr}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getStart(@PathParam("signature") String signature,@PathParam("timestamp") String timestamp,@PathParam("nonce") String nonce,@PathParam("echostr") String echostr);

    /**
     * 获取车辆信息
     * 
     * @param vehicle_id 车辆的vin
     * @return 车辆信息
     */
    @GET
    @Path(value = "{vehicle_id}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getVehicleInfo(@HeaderParam("access_token") String token, @PathParam("vehicle_id") String vehicle_id);
 
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
    Response getVehicleRealInfo( String req);

    


}
