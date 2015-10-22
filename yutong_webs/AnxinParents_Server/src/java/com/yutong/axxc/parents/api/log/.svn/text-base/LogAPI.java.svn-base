package com.yutong.axxc.parents.api.log;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface LogAPI {
	/**
	 * 终端上传环境信息.
	 * 
	 * @param truntime
	 *            环境信息
	 */
	@PUT
	@Path(value = "envinfo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response env(String info);

	
	/**
	 * 
	  * 函数介绍：加长使用时长
	  * 参数：
	  * 返回值：
	 */
	@PUT
	@Path(value = "usertime")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response usertime(String behaviorInfo);
}
