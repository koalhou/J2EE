package com.yutong.axxc.parents.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface ResourcesAPI {
	/**
	 * 终端获取资源API
	 * 
	 * @param resId
	 *            资源ID
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
	  * 参数：资源文件的后缀和资源的BASE64字符串
	  * 返回值：
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response setResource(
			 String req);
}
