package com.yutong.axxc.parents.api.account;

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

public interface UserAPI {
	/**
	 * AccessToken更新处理.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param refeshToken
	 *            更新令牌信息.
	 * @return 令牌更新结果信息.
	 */
	@POST
	@Path(value = "refresh")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response tokenRefesh(@HeaderParam("access_token") String token,
			String req);

	/**
	 * 终端用户自动登录处理.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param version 
	 *            终端软件版本.
	 * @return 自动登录结果消息.
	 */
	@POST
	@Path(value = "autologin")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response autologin(@HeaderParam("access_token") String token); 
	/**
	 * 用户退出登录接口
	 * 
	 * @param token 令牌信息
	 * 
	 * @return 退出登录成功信息
	 */
	@POST
	@Path(value = "logout")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	String logout(@HeaderParam("access_token") String token);
	/**
	 * 用户旧密码验证.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param oldPwd
	 *            用户旧密码.
	 * @return 密码验证结果.
	 */
	@GET
	@Path(value = "check/{old_pwd}")
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response checkOldPwd(@PathParam("old_pwd") String oldPwd);

	/**
	 * 终端用户修改密码处理.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param modifyPwdInfo
	 *            密码修改信息.
	 * @return 密码修改结果.
	 */
	@PUT
	@Path(value = "pwd")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response modifyPwd(String modifyPwdInfo);
	/**
	 * 
	  * 函数介绍：修改家长信息
	  * 参数：
	  * 返回值：
	 */
	@POST
	@Path(value = "info")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public String saveAccount(String user);
}
