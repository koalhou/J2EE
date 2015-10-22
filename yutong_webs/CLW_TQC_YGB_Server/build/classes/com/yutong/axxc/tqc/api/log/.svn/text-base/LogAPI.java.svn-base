package com.yutong.axxc.tqc.api.log;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;

public interface LogAPI {
	/**
	 * 异常信息收集接口
	 */
	@POST
	@Path(value = "errinfo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response errorreport(String req);
	
	/**
	 * 终端上传环境信息.
	 * 
	 * @param truntime 环境信息
	 *            
	 */
	@POST
	@Path(value = "envinfo")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response env(String info);
	
	/**
	 * 用户行为信息上报接口.
	 * 
	 * @param behaviorInfo 终端用户行为信息.
	 *            
	 * @return 行为结果上报结果.
	 */
	@POST
	@Path(value = "behavior")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response behavior(String behaviorInfo);
	
	/**
	 * 
	  * 函数介绍：加长使用时长
	  * 参数：
	  * 返回值：
	 */
	@POST
	@Path(value = "usertime")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response usertime(String behaviorInfo);
	
	/**
	 * 用户行为统计接口
	 * 
	 * @param token 用户令牌
	 * 
	 * @return 
	 */
	@POST
	@Path(value = "usage")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response usagecollect(String req);
}
