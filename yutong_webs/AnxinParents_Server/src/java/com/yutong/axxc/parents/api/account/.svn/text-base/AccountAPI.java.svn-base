package com.yutong.axxc.parents.api.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface AccountAPI {

	/**
	 * 手机号码验证接口
	 * 
	 * @param phone 手机号
	 * 
	 * @return 验证结果信息
	 */
	@GET
	@Path(value = "phone/{usr_phone}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response verifyPhone(@PathParam("usr_phone") String phone);
	/**
	 * 家长邮箱验证接口
	 * 
	 * @param email 
	 * 
	 * @return 验证结果信息
	 */
	@GET
	@Path(value = "email/{usr_email}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response verifyEmail(@PathParam("usr_email") String email);
	/**
	 * 家长注册接口
	 * @return 结果信息
	 */
	@PUT
	@Path(value = "register")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response register(String registerInfo);
	/**
	 * 终端用户登录处理.
	 * 
	 * @param loginInfo
	 *            登录信息JSON串
	 * @return 服务端登录结果信息或新版终端软件信息.
	 */
	@PUT
	@Path(value = "login")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response login(String loginInfo);
	/**
	 * 用户忘记密码重置.
	 * 
	 */
	@POST
	@Path(value = "pwd")
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response forgetPWD( String req);
}
