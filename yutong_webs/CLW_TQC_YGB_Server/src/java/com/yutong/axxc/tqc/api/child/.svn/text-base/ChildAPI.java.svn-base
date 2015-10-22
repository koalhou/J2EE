package com.yutong.axxc.tqc.api.child;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;

public interface ChildAPI {
	/**
	 * 
	  * 函数介绍：获取学生列表
	  * 参数：
	  * 返回值：
	 */
	@GET
	@Path(value = "list")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response listChild();

	/**
	 * 
	  * 函数介绍：获取单个学生信息
	  * 参数：
	  * 返回值：
	 */
	@GET
	@Path(value = "/custom/{cld_id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response single(@PathParam("cld_id") String id);
	/**
	 * 
	  * 函数介绍：设置学生信息
	  * 参数：
	  * 返回值：
	 */
	@PUT
	@Path(value = "/custom")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response save(String req);
	/**
	 * 
	 * 函数介绍：设置学生信息
	 * 参数：
	 * 返回值：
	 */
	@PUT
	@Path(value = "/salutation")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response setRelation(String req);
	/**
	 * 
	 * 函数介绍：获取学生的最新状态
	 * 参数：
	 * 返回值：
	 */
	@GET
	@Path(value = "status/{cld_id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response getStatus(@PathParam("cld_id") String id);
}
