package com.yutong.axxc.tqc.api.account;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.tqc.common.HttpConstant;

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
	 * 账号验证接口
	 * 
	 * @param account 账号
	 * 
	 * @return 验证结果信息
	 */
	@POST
	@Path(value = "accountcheck")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response verifyAccount(String account);
	
	
	/**
	 * 手机绑定接口
	 * 
	 *  @param phone 手机号
	 *  @param emp_code 员工号
	 *  
	 */
	@POST
	@Path(value = "phonebind")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	String phone_bind(@HeaderParam("access_token") String token, String bind_info);
	
	/**
	 * 手机验证码获取接口
	 * @param req
	 * @return
	 */
	@POST
	@Path(value="phonecodeget")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response send(String req);
	
	/**
	 * 终端用户登录处理.
	 * 
	 * @param loginInfo 登录信息JSON串
	 * @return 服务端登录结果信息或新版终端软件信息.
	 */
	@POST
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
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	public Response changePWD( String req);
	
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
	Response tokenRefesh(@HeaderParam("access_token") String token,	String req);

	/**
	 * 终端用户自动登录处理.
	 * 
	 * @param token 访问令牌信息.
	 * 
	 * @param version 终端软件版本.
	 * 
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
	Response logout(@HeaderParam("access_token") String token);
	
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
