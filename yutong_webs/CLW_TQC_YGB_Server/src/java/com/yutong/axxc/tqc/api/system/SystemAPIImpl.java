package com.yutong.axxc.tqc.api.system;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.yutong.axxc.tqc.entity.Advises;
import com.yutong.axxc.tqc.entity.News;
import com.yutong.axxc.tqc.entity.account.UserInfo;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.service.SessionService;
import com.yutong.axxc.tqc.tools.JacksonUtils;

public class SystemAPIImpl implements SystemAPI {

	@Context
	private MessageContext context;

	@Autowired
	private BaseService baseService;
	@Autowired
	protected EtagService etagService;
	@Autowired
	private SessionService sessionService;
	private Logger logger = LoggerFactory.getLogger(SystemAPIImpl.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Override
	public Response advise(String req) {
		Map<?,?> map = JacksonUtils.jsonToMapRuntimeException(req);
		Object content = map.get("content");
		Object msg_id = map.get("msg_id");
		if (null == content || msg_id == null) {
			logger.info("SystemAPI ： advice，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		
		Map<String, String> conditions = new HashMap<String, String>();
		
		conditions.put("emp_code", emp_code);
		conditions.put("content",String.valueOf(content));
		conditions.put("msg_id",String.valueOf(msg_id));
		
		baseService.update("System.insertAdvices", conditions);
		return Response.ok().build();
	}

	@Override
	public Response getnewssummary(String token, String req) {
		Map<?,?> map = JacksonUtils.jsonToMapRuntimeException(req);
		Object news_type = map.get("news_type");
		Object page_flag = map.get("page_flag");
		Object page_time = map.get("page_time");
		Object req_num = map.get("req_num");
		Object last_id = map.get("last_id");
		
		if(news_type == null || page_flag == null || page_time == null || req_num == null || last_id == null){
			logger.info("SystemAPI ： newssummary，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		result.put("timestamp", sdf.format(new Date()));
		
		int count = Integer.parseInt(String.valueOf(req_num));
		
		List<News> news = baseService.getList("System.getNewsSummary",news_type);
		
		boolean isNeeded = false;
		boolean isExtra = false;
		int top = 0;
		int buttom = 0;
		List<News> resultNews = new ArrayList<News>();
		for(int i=0;i<news.size();i++){
			if("0".equals(String.valueOf(page_flag)) || "1".equals(String.valueOf(page_flag))){
				//if(news.get(i).getGonggao_id().equals(String.valueOf(Integer.parseInt(String.valueOf(last_id)) - 1)) || "0".equals(String.valueOf(last_id))){
				//edit by liuja 客户端公告功能向下翻页，加载更多公告
			    if(Integer.parseInt(news.get(i).getGonggao_id()) <= Integer.parseInt((String.valueOf(Integer.parseInt(String.valueOf(last_id)) - 1))) || "0".equals(String.valueOf(last_id))){
					isNeeded = true;
				}
			}else{
				if(Integer.parseInt(news.get(i).getGonggao_id()) <= (Integer.parseInt(String.valueOf(last_id)) - 1 + count) || "0".equals(String.valueOf(last_id))){
					isNeeded = true;
				}
			}
			if(!isNeeded){
				top ++;
			}
			if(isNeeded){
				if(resultNews.size()<count){
					resultNews.add(news.get(i));
				}else{
					isExtra = true;
				}
			}
			if(isExtra){
				buttom ++ ;
			}
		}
		if("0".equals(String.valueOf(page_flag)) || "1".equals(String.valueOf(page_flag))){
			result.put("hasnext",buttom > 0 ? "1":"0");
		}else {
			result.put("hasnext", top > 0 ? "1":"0");
		}
		result.put("totalnum", news.size());
		result.put("infos", resultNews);
		
		String etag = CachedCommon.CACHED_NEWS_LIST + page_flag + "_" + req_num + "_" + last_id;
		
		if (!etagService.put(etag, CachedCommon.CACHED_MINTER_5M, result)) {
			logger.error("缓存失败:{}", etag);
		}
		sessionService.updateSession(token, etag);
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(result))./*tag(key).*/build();
	}

	@Override
	public Response getadvisereply(String token, String req) {
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		List<Advises> advises = baseService.getList("System.getAdvises",emp_code);
		
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(advises))./*tag(key).*/build();
	}
	
	
	@Override
	public Response getnewsdetail(String token, String news_id) {
		if(!StringUtils.hasText(news_id)) {
			logger.info("SystemAPI ：newsdetail，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		News news = baseService.get("System.getNewsDetail", news_id);
		news.setImage_res(new ArrayList<String>());
//		news.setImage_url(new ArrayList<String>());
		if(news.getPhoto1()!=null){
			//news.getImage_res().add("1");
			news.getImage_res().add(news.getPhoto1());
			//news.getImage_url().add(news.getPhoto1());
		}
		if(news.getPhoto2()!=null){
			news.getImage_res().add("2");
//			news.getImage_url().add(news.getPhoto2());
		}
		if(news.getPhoto3()!=null){
			news.getImage_res().add("3");
//			news.getImage_url().add(news.getPhoto3());
		}
		if(news.getPhoto4()!=null){
			news.getImage_res().add("4");
//			news.getImage_url().add(news.getPhoto4());
		}
		if(news.getPhoto5()!=null){
			news.getImage_res().add("5");
//			news.getImage_url().add(news.getPhoto5());
		}
		
		String etag = CachedCommon.CACHED_NEWS_ONE + news_id;
		
		if (!etagService.put(etag, CachedCommon.CACHED_MINTER_1D, news)) {
			logger.error("缓存失败:{}", etag);
		}
		sessionService.updateSession(token, etag);		
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(news)).tag(etag).build();
	}

	/**
	 * 获取个人推送规则服务
	 * 
	 */
	@Override
	public Response getRule(String token) {
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		
		logger.debug("获取用户推送规则列表");
		UserInfo userRule = baseService.get("System.getPushRules",	emp_code);

		if (userRule != null) {
			
//			String key = CachedCommon.CACHED_RULE + usrId;
//			etagService.put(key, CachedCommon.CACHED_MINTER_7D, key);

			List<Map<String ,String>> resultList = new ArrayList<Map<String, String>>();
			String rule = userRule.getPush_rule();
			Map<String, String> map1 = new HashMap<String, String>(); 
			map1.put("emp_code", emp_code);
			map1.put("rule_id", "1");	
			map1.put("on_off", rule.substring(2,3));
			map1.put("flag", rule.substring(3,4));
			resultList.add(map1);
			Map<String, String> map2 = new HashMap<String, String>(); 
			map2.put("emp_code", emp_code);
			map2.put("rule_id", "2");
			map2.put("on_off", rule.substring(4,5));
			map2.put("flag", rule.substring(5,6));
			resultList.add(map2);
			Map<String, String> map3 = new HashMap<String, String>(); 
			map3.put("emp_code", emp_code);
			map3.put("rule_id", "3");
			map3.put("on_off", rule.substring(6,7));
			map3.put("flag", rule.substring(7,8));
			resultList.add(map3);
			
			String etag = CachedCommon.CACHED_RULE + emp_code;
			if (!etagService.put(etag, CachedCommon.CACHED_MINTER_12H, resultList)) {
				logger.error("缓存失败:{}", etag);
			}
			sessionService.updateSession(token, etag);				
			return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultList)).tag(etag).build();
		}
		return Response.noContent().build();
	}

	/**
	 * 设置个人推送规则服务
	 */
	@Override
	public Response setRule(String content) {
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		logger.debug("处理设置个人推送规则请求");
		List<Map<String, String>> request = (List<Map<String, String>>) JacksonUtils.jsonToListRuntimeException(content);
		
		if(request == null || request.size() == 0){
			logger.info("SystemAPI ：setpushrule，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		
		UserInfo userRule = baseService.get("System.getPushRules",	emp_code);
		
		String rules_now = userRule.getPush_rule();
		for(Map<String, String> tm :request){
			
			if(tm.get("rule_id") == null || tm.get("on_off") == null){
				logger.info("SystemAPI ：setpushrule，关键参数不足！");
				throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
			}
			String rule_id = String.valueOf(tm.get("rule_id"));
			String on_off = String.valueOf(tm.get("on_off"));
			
			if("1".equals(rule_id)){
				rules_now = rules_now.substring(0,2) + on_off + rules_now.substring(3);
			}else if("2".equals(rule_id)){
				rules_now = rules_now.substring(0,4) + on_off + rules_now.substring(5);
			}else if("3".equals(rule_id)){
				rules_now = rules_now.substring(0,6) + on_off + rules_now.substring(7);
			}else{
				throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
			}
			
			Map<String, String> conditions  = new HashMap<String ,String>();
			conditions.put("emp_code", emp_code);
			conditions.put("push_rule", rules_now);
			int ret = baseService.update("System.updateEmpRules", conditions);
			logger.info("更新规则{},[{}]", content, ret);
		}
//
		String key = CachedCommon.CACHED_RULE + emp_code;
		etagService.del(key);
		
		return Response.ok().build();
	}

	@Override
	public Response checkPassword(String old_pwd) {
		
		if(null == old_pwd || "".equals(old_pwd)){
			logger.info("SystemAPI ：checkpwd，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("emp_code", emp_code);
		conditions.put("password", old_pwd);
		List<UserInfo>  usrInfoList= baseService.getList("System.checkPwd",conditions);
		
		
		if(usrInfoList.size() == 0){
			logger.error("SystemAPI ：checkpwd，旧密码验证失败");
			throw new ApplicationException(ErrorConstant.ERROR10030, Response.Status.BAD_REQUEST);
		}
		
		return Response.ok().build();
	}

	@Override
	public Response changePassword(String req) {
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		Map<?,?> map = (Map<?,?>)JacksonUtils.jsonToMapRuntimeException(req);
		if(map.get("old_pwd") == null || map.get("new_pwd") == null){
			logger.info("SystemAPI ：changepwd，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		Map<String, String> conditions = new HashMap<String, String>();
		conditions.put("old_pwd", String.valueOf(map.get("old_pwd")));
		conditions.put("new_pwd", String.valueOf(map.get("new_pwd")));
		conditions.put("emp_code", emp_code);
		
		
		int i = baseService.update("System.changePwd", conditions);
		if(i == 1){
			return Response.ok().build();
		}
		logger.error("SystemAPI ：checkpwd，修改密码失败");
		throw new ApplicationException(ErrorConstant.ERROR10030, Response.Status.BAD_REQUEST);
	}

}
