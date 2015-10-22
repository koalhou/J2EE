package com.yutong.axxc.parents.api.station;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface StationAPI
{
    /**
     * 获取学生线路信息
     * 
     * @param cld_id 学生ID
     * @return 线路信息
     */
    @POST
    @Path(value = "line")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getRoute(@HeaderParam("access_token") String token,String req);

    /**
     * 获取学生站点信息
     * 
     * @param cld_id 学生ID
     * @return 线路信息
     */
    @POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getStuStation(@HeaderParam("access_token") String token, String req);

    /**
     * 获取学生站点提醒信息
     * 
     * @param cld_id 学生ID
     * @return 站点提醒信息
     */
    @POST
    @Path(value = "remind")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getStuStationRemind(@HeaderParam("access_token") String token,String req);

    /**
     * 设置学生站点提醒
     * 
     */
    @PUT
    @Path(value = "remind")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response setStuStationRemind(String req);
    /**
     * 获取最新提醒信息接口
     */
    @POST
    @Path(value = "msg/remind")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
    Response getLastRemind(String req);
}
