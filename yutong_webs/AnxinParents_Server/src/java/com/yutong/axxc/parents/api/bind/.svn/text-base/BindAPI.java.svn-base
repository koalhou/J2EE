package com.yutong.axxc.parents.api.bind;

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

@Path(value = "/")
public interface BindAPI {
	/**
	 * 推送消息绑定处理.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param clientid
	 *            终端唯一标识.
	 * @return 推送消息绑定处理结果消息.
	 */
	@PUT
	@Path(value = "msgpush")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response pushMsgBinding(
			String req);
	/**
	 * 
	  * 函数介绍：乘车协议手机验证接口
	  * 参数：
	  * 返回值：
	 */
	@POST
	@Path(value = "student/verify")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response isBoundPhone(String req);
	
	/**
	 * 
	  * 函数介绍：家长与学生绑定接口
	  * 参数：
	  * 返回值：
	 */
	@PUT
	@Path(value = "student/{phone}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response bindOtherPhone(@PathParam("phone") String phone);
}
