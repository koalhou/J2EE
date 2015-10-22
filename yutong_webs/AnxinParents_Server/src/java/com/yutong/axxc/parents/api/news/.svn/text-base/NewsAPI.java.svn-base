package com.yutong.axxc.parents.api.news;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

public interface NewsAPI {
	
	/**
	 * 
	  * 函数介绍：获取新闻摘要列表
	  * 参数：时间 条数
	  * 返回值：
	 */
	@POST
	@Path(value = "summary")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getSegment(String req);
	
	/**
	 * 
	  * 函数介绍：获取新闻详情
	  * 参数：新闻ID
	  * 返回值：
	 */
	@GET
	@Path(value = "detail/{news_id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response getOne(@PathParam("news_id") String id);
}
