package com.yutong.axxc.tqc.api.authorization;

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

import com.yutong.axxc.tqc.api.advise.AdviseAPIImpl;
import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.UserInfoKey;
import com.yutong.axxc.tqc.entity.account.UserInfo;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.AccountService;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.tools.JacksonUtils;

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
	
	@Override
	public 	Response list(String req) {
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//		String userId = "161";
		
		Map<String,String> map=(Map<String,String>)JacksonUtils.jsonToMapRuntimeException(req);
		String sid=map.get("cld_id");
		map.put("usr_id", userId);
		
		List<UserInfo> list=baseService.getList("Child.getAuthed", map);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("certigier_infos", list);
		
		String key=CachedCommon.CACHED_AUTH+userId;
		etagService.put(key, CachedCommon.CACHED_MINTER_7D, key);
				
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
	}
	
	@Override
	public Response	getToAuth (String account) {
		if(!StringUtils.hasText(account)){
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		UserInfo user=baseService.get("Child.getToAuth", account);
		if(user==null){
			throw new ApplicationException(ErrorConstant.ERROR10027,
					Response.Status.BAD_REQUEST);
		}
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//		String userId = "43";
		Map map=new HashMap();
		map.put("usr_id", userId);
		List<UserInfo> list=baseService.getList("Child.getAuthed", map);
		if(list!=null&&list.size()>0){
			for (UserInfo userInfo : list) {
//				if(userInfo.getId().equals(user.getId())){
//					throw new ApplicationException(ErrorConstant.ERROR10026, Response.Status.BAD_REQUEST);
//				}
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
		String usrID=(String)map.get("usr_id");
		String sid=(String)map.get("cld_id");
		String myID = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//		String myID = "161";
		
		logger.info("用户{}把{}授权予{}",myID,usrID,sid);
		if(!StringUtils.hasText(usrID)){
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		
		map.put("usr_id", myID);
		List<UserInfo> list=baseService.getList("Child.getAuthed", map);
		if(list!=null&&list.size()>0){
			for (UserInfo userInfo : list) {
//				if(userInfo.getId().equals(usrID)){
//					throw new ApplicationException(ErrorConstant.ERROR10026,
//							Response.Status.BAD_REQUEST);
//				}
			}
		}
		
		
		int ret=accountService.addRelation(myID,usrID,sid);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}
		//清除被授权用户的关联缓存
		etagService.del(CachedCommon.CACHED_CHILD_LIST+usrID);
		etagService.del(CachedCommon.CACHED_AUTH+usrID);
		return Response.ok().build();
	}

	@Override
	public 	Response del(String req) {
		Map map=JacksonUtils.jsonToMapRuntimeException(req);
		String otherUID=(String)map.get("usr_id");
		String sid=(String)map.get("cld_id");
		if(!StringUtils.hasText(otherUID)){
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		String myUID = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//		String myUID = "149";
		
		logger.info("用户{}从{}手中取消{}的授权",myUID,otherUID,sid);
		int ret=accountService.delRelation(myUID,otherUID,sid);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}
		//清除被授权用户的关联缓存
		etagService.del(CachedCommon.CACHED_CHILD_LIST+myUID);
		etagService.del(CachedCommon.CACHED_AUTH+myUID);
		return Response.ok().build();
	}

}
