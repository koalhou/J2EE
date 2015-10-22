package com.yutong.axxc.tqc.api.news;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.entity.News;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.tools.JacksonUtils;
import com.yutong.axxc.tqc.tools.PropertiesTools;

public class NewsAPIImpl implements NewsAPI {
	private static Logger logger = LoggerFactory.getLogger(NewsAPIImpl.class);
	//
	private static String NEWS_IMG_URL = "news_img_url_target";

	@Context
	private MessageContext context;

	@Autowired
	private EtagService etagService;

	@Autowired
	protected BaseService baseService;

	@Override
	public Response getSegment(String req) {
		Map reqMap = JacksonUtils.jsonToMapRuntimeException(req);
		String size = (String) reqMap.get("num");
		String start_time = (String) reqMap.get("start_time");
		String end_time = (String) reqMap.get("end_time");
		String news_type = (String) reqMap.get("news_type");

		if ( StringUtils.hasText(news_type) &&StringUtils.hasText(size)) {
			if(!StringUtils.hasText(start_time)){
				reqMap.put("start_time", null);
			}
			if(!StringUtils.hasText(end_time)){
				reqMap.put("end_time", null);
			}
			List<News> list = baseService.getList("News.getSegment", reqMap);
			String key = CachedCommon.CACHED_NEWS_LIST + start_time + end_time
					+ size+news_type;
			etagService.put(key, CachedCommon.CACHED_MINTER_7D, key);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("news", list);
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
		}
		logger.error("请求参数异常{}", reqMap);
		throw new ApplicationException(ErrorConstant.ERROR10002,
				Response.Status.BAD_REQUEST);
	}

	@Override
	public Response getOne(String id) {
		if(StringUtils.hasText(id)){
			String key = CachedCommon.CACHED_NEWS_ONE + id;
			News news=(News)etagService.get(key);
			if(news==null){
				news = baseService.get("News.getOne", id);
				String host;
				try {
					host = PropertiesTools.readValue(
							ModCommonConstant.SYS_CONF_FILE,
							NEWS_IMG_URL);
//					news.setImgURL(host+news.getImgURL());
				} catch (IOException e) {
					logger.error("从配置文件" + ModCommonConstant.SYS_CONF_FILE_PATH
							+ "获取新闻图片地址时出错", e);
					throw new ApplicationException(ErrorConstant.ERROR90000,
							Response.Status.INTERNAL_SERVER_ERROR);
				}
			}
		
			etagService.put(key, CachedCommon.CACHED_MINTER_7D, news);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("new", news);
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
		}
		logger.error("请求参数异常{}", id);
		throw new ApplicationException(ErrorConstant.ERROR10002,
				Response.Status.BAD_REQUEST);
	}

}
