package com.yutong.axxc.parents.api.authorization;

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

import com.yutong.axxc.parents.api.advise.AdviseAPIImpl;
import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.UserInfoKey;
import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.AccountService;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.service.SessionService;
import com.yutong.axxc.parents.tools.JacksonUtils;

public class AuthorizationAPIImpl implements AuthorizationAPI {
	private static Logger logger = LoggerFactory
			.getLogger(AuthorizationAPIImpl.class);
	
	@Context
	private MessageContext context;

	@Autowired
	protected BaseService baseService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EtagService etagService;
	
	@Autowired
	private SessionService sessionService;
	/**
	 * 查询对于某个学生的已授权用户
	 */
	@Override
	public 	Response list(String token,String req) {
		String myUID = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
		
		Map<String,String> map=(Map<String,String>)JacksonUtils.jsonToMapRuntimeException(req);
		String sid=map.get("cld_id");
		map.put("usr_id", myUID);
		
		List<UserInfo> list=baseService.getList("Child.getAuthed", map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("certigier_infos", list);
		
		String key=CachedCommon.CACHED_AUTH+myUID+"_"+sid;
		etagService.put(key, CachedCommon.CACHED_MINTER_1D, key);
		sessionService.updateSession(token, key);		
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
	}
	
	
	@Override
	public Response	getToAuth (String req) {
		Map<String,String> reqmap=(Map<String,String>)JacksonUtils.jsonToMapRuntimeException(req);
		String account=reqmap.get("account_name");
		String sid=reqmap.get("cld_id");
		
		if(!StringUtils.hasText(account)){
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		//1.用户是否存在
		UserInfo user=baseService.get("Child.getToAuth", account);
		if(user==null){
			throw new ApplicationException(ErrorConstant.ERROR10027,
					Response.Status.BAD_REQUEST);
		}
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
		Map map=new HashMap();
		map.put("usr_id", userId);
		if(StringUtils.hasText(sid)){
			map.put("cld_id", sid);
		}
		//2.用户是否已授权
		List<UserInfo> list=baseService.getList("Child.getAuthed", map);
		if(list!=null&&list.size()>0){
			for (UserInfo userInfo : list) {
				if(userInfo.getId().equals(user.getId())){
					throw new ApplicationException(ErrorConstant.ERROR10026,
							Response.Status.BAD_REQUEST);
				}
			}
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("usr_info", user);
		
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

	@Override
	public 	Response add(String req) {
		Map map=JacksonUtils.jsonToMapRuntimeException(req);
		String otherUID=(String)map.get("usr_id");
		String sid=(String)map.get("cld_id");
		String myID = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
		
		logger.info("用户{}把{}授权予{}",myID,otherUID,sid);
		if(!StringUtils.hasText(otherUID)){
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		map.put("usr_id", myID);
		//检查是否已授权
		List<UserInfo> list=baseService.getList("Child.getAuthed", map);
		if(list!=null&&list.size()>0){
			for (UserInfo userInfo : list) {
				if(userInfo.getId().equals(otherUID)){
					throw new ApplicationException(ErrorConstant.ERROR10026,
							Response.Status.BAD_REQUEST);
				}
			}
		}
		
		//授予权限
		int ret=accountService.addRelation(myID,otherUID,sid);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}
		//清除被授权用户的关联缓存
		etagService.del(CachedCommon.CACHED_CHILD_LIST+otherUID);
		etagService.del(CachedCommon.CACHED_AUTH+myID+"_"+sid);
		return Response.ok().build();
	}

	@Override
	public 	Response del(String req) {
		Map map=JacksonUtils.jsonToMapRuntimeException(req);
		String otherUID=(String)map.get("usr_id");
		String sid=(String)map.get("cld_id");
		if(!StringUtils.hasText(otherUID)||!StringUtils.hasText(sid)){
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		String myUID = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
		
		logger.info("用户{}从{}手中取消{}的授权",myUID,otherUID,sid);
		int ret=accountService.delRelation(myUID,otherUID,sid);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}
		//清除被授权用户的关联缓存
		etagService.del(CachedCommon.CACHED_CHILD_LIST+otherUID);
		etagService.del(CachedCommon.CACHED_AUTH+myUID+"_"+sid);
		return Response.ok().build();
	}

}
