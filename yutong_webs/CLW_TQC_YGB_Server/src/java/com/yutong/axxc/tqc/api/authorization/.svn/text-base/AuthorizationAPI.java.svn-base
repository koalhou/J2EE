package com.yutong.axxc.tqc.api.authorization;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;

public interface AuthorizationAPI {
	/**
	 * 
	  * 函数介绍：查询已授权
	  * 参数：
	  * 返回值：
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response list(String req);

	/**
	 * 
	 * 函数介绍：获取待授权家长
	 * 参数：
	 * 返回值：
	 */
	@GET
	@Path(value = "/{usr_account}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getToAuth(@PathParam("usr_account")String account);
	/**
	 * 
	  * 函数介绍：授权给家长
	  * 参数：
	  * 返回值：
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response add(String req);
	/**
	 * 
	  * 函数介绍：取消授权
	  * 参数：
	  * 返回值：
	 */
	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response del(String req);
}
