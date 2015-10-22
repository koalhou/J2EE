/**
 * @(#)PushRuleService.java 2013-4-8 Copyright 2013 Neusoft Group Ltd. All
 *                          rights reserved. Neusoft PROPRIETARY/CONFIDENTIAL.
 *                          Use is subject to license terms.
 */
package com.yutong.axxc.tqc.api.system;

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

import com.yutong.axxc.tqc.common.HttpConstant;

public interface SystemAPI {

	/**
	 * 意见建议反馈接口
	 * 
	 * @param content
	 *            反馈内容、
	 * 
	 * 
	 */
	@POST
	@Path(value = "advise")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response advise(String req);

	/**
	 * 获取意见及回复接口
	 * @param page_flag
	 * @param page_time
	 * @param req_num
	 * @param last_id
	 * 
	 * @param eTag
	 * 
	 * @return 公告列表
	 */
	@POST
	@Path(value = "advisereply")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getadvisereply(@HeaderParam("access_token") String token, String req);

	/**
	 * 获取公告概要接口
	 * @param news_type
	 * @param page_flag
	 * @param page_time
	 * @param req_num
	 * @param last_id
	 * 
	 * @param eTag
	 * 
	 * @return 公告列表
	 */
	@POST
	@Path(value = "newssummary")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getnewssummary(@HeaderParam("access_token") String token, String req);
	
	/**
	 * 获取公告详细接口
	 * @param news_type
	 * @param page_flag
	 * @param page_time
	 * @param req_num
	 * @param last_id
	 * 
	 * @param eTag
	 * 
	 * @return 公告列表
	 */
	@GET
	@Path(value = "newsdetail/{news_id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getnewsdetail(@HeaderParam("access_token") String token, @PathParam("news_id") String news_id);
	
	/**
	 * 获取个人推送规则服务
	 * 
	 * @param token
	 *            用户令牌
	 * @param eTag
	 *            个人推送规则标记
	 * @return 最新个人推送规则标记
	 */
	@POST
	@Path(value = "getpushrule")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getRule(@HeaderParam("access_token") String token);

	/**
	 * 设置个人推送规则服务
	 * 
	 * @param token 用户令牌
	 *            
	 * @param content 个人推送规则消息体
	 *            
	 * @return 最新个人推送规则标记
	 */
	@POST
	@Path(value = "setpushrule")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response setRule(String content);

	/**
	 * 用户旧密码验证接口
	 * 
	 * @param token 用户令牌
	 * 
	 * @return 
	 */
	@GET
	@Path(value = "check/{old_pwd}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response checkPassword(@PathParam("old_pwd") String old_pwd);	

	/**
	 * 用户修改密码接口
	 * 
	 * @param token 用户令牌
	 * 
	 * @return 
	 */
	@POST
	@Path(value = "pwd")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response changePassword(String req);	

}
