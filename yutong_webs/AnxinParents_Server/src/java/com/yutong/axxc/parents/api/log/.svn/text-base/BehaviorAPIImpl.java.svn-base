package com.yutong.axxc.parents.api.log;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.UserInfoKey;
import com.yutong.axxc.parents.entity.UserSeesion;
import com.yutong.axxc.parents.service.AccountService;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.LogService;
import com.yutong.axxc.parents.tools.JacksonUtils;


public class BehaviorAPIImpl implements BehaviorAPI {
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(BehaviorAPIImpl.class);

	@Autowired
	private LogService logServer;
	@Autowired
	private EtagService etagService;
	@Autowired
	private BaseService baseService;
	
	
	/**
	 * 用户行为信息上报接口.
	 * 
	 * @param behaviorInfo
	 *            终端用户行为信息.
	 * @return 行为结果上报结果.
	 */
	@Override
	public Response behavior(String token,String behaviorInfo) {
		//如果是登陆用户则获取用户id
		String userId=null ;
		String key=CachedCommon.CACHED_TOKEN_KEY+token;
		UserSeesion session=(UserSeesion)etagService.get(key);
		if(session==null){
			session = baseService.get("Account.getIDByToken", token);
		}
		if(session!=null){
			userId=session.getId();
		}
		
		Map map=JacksonUtils.jsonToMapRuntimeException(behaviorInfo);
		List list=(List)map.get("usr_behavior_infos");
		map.put("id", userId);
		logServer.saveBehavior(list,userId);

		return Response.ok().build();

	}
	
	
}
