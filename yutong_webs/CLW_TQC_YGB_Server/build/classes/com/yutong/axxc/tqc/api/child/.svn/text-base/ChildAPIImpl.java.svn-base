package com.yutong.axxc.tqc.api.child;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.yutong.axxc.tqc.api.bind.BindAPIImpl;
import com.yutong.axxc.tqc.common.CachedCommon;
import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.UserInfoKey;
import com.yutong.axxc.tqc.entity.Child;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.AccountService;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.DistanceService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.tools.JacksonUtils;

public class ChildAPIImpl implements ChildAPI {
	private static Logger logger = LoggerFactory
			.getLogger(ChildAPIImpl.class);
	@Context
	private MessageContext context;

	@Autowired
	protected BaseService baseService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private EtagService etagService;
	
	@Autowired
	private DistanceService distanceService;
	
	@Override
	public  Response listChild() {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String userId = context.getHttpHeaders().getHeaderString("usr_id");
//		String userId = "161";
		//TODO 查询默认的乘车信息
		List<Child> list= baseService.getList("Child.getChildren", userId);
		String key=CachedCommon.CACHED_CHILD_LIST+userId;
		boolean b=etagService.put(key, CachedCommon.CACHED_MINTER_1D, key);
		
		resultMap.put("child_infos", list);
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
	}

	@Override
	public Response single(String id) {
		String userId = context.getHttpHeaders().getHeaderString("usr_id");
//		String userId = "43";
		Child child=new Child();
		child.setId(id);
		child.setPid(userId);
		if(StringUtils.hasText(id)){
			List<Child> list= baseService.getList("Child.getChildrenInfo", child);
			Map<String, Object> resultMap = new HashMap<String, Object>();
			String key=CachedCommon.CACHED_CHILD+id;
			boolean b=etagService.put(key, CachedCommon.CACHED_MINTER_7D, key);
			
			resultMap.put("child_custom_info", list);
			return Response.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap)).tag(key).build();
		}else{
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
	}

	@Override
	public Response save(String req) {
//		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
		String userId = "43";
		Map reqMap=JacksonUtils.jsonToMapRuntimeException(req);
		Map chMap=(Map)reqMap.get("child_custom_info");
		Child child=new Child();
		child.setAlias((String)chMap.get("cld_alias"));
		child.setId((String)chMap.get("cld_id"));
		child.setColor((String)chMap.get("cld_color"));
		child.setPhoto((String)chMap.get("cld_photo"));
		child.setBg((String)chMap.get("cld_bg"));
		child.setAudio((String)chMap.get("cld_audio"));
		child.setPid(userId);
		
		logger.info("用户{}修改学生{}的信息", userId,child);
		int ret=accountService.saveChildInfo(child);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}
		//删除缓存
		String key=CachedCommon.CACHED_CHILD+child.getId();
		etagService.del(key);
		key=CachedCommon.CACHED_CHILD_LIST+userId;
		etagService.del(key);
		return Response.ok().build();
	}

	@Override
	public Response setRelation(String req) {
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//		String userId = "43";
		Child ch=JacksonUtils.fromJsonRuntimeException(req, Child.class);
		ch.setPid(userId);
		logger.info("设置用户{}和学生{}的关系", userId,ch);
		int ret=accountService.setRelation(ch);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}
		//删除缓存
		String key=CachedCommon.CACHED_CHILD+ch.getId();
		etagService.del(key);
		key=CachedCommon.CACHED_CHILD_LIST+userId;
		etagService.del(key);
		return Response.ok().build();
	}

	@Override
	public Response getStatus(String id) {
		String status=baseService.get("Child.getSatus", id);
		Map resultMap=new HashMap();
		resultMap.put("status", distanceService.getStudentState(id));
		return Response.ok().entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}



}
