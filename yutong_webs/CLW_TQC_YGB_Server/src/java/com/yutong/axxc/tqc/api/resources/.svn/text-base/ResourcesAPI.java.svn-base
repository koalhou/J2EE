package com.yutong.axxc.tqc.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;

public interface ResourcesAPI {
	
	/**
	 * 终端获取资源API
	 * 
	 * @param token 用户token
	 * 
	 * @param type 资源类型
	 * 
	 * @param resId 资源ID
	 * 
	 * @return
	 */
	@GET
	@Path(value = "{res_id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response getResource(@PathParam("res_id") String resId);
	
	/**
	 * 
	  * 函数介绍：资源上传接口
	  * 参数：
	  * 返回值：
	 */
	@POST
	@Path(value = "/putresources")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response setResource(String req);
}
