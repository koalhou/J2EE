package com.yutong.axxc.parents.api.bind;

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

import com.yutong.axxc.parents.common.CachedCommon;
import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.entity.Child;
import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.AccountService;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.service.EtagService;
import com.yutong.axxc.parents.tools.JacksonUtils;
import com.yutong.axxc.parents.tools.ValidateUtil;

public class BindAPIImpl implements BindAPI {
	private static Logger logger = LoggerFactory
			.getLogger(BindAPIImpl.class);
	@Context
	private MessageContext context;

	@Autowired
	protected BaseService baseService;

	@Autowired
	private AccountService accountService;
	@Autowired
	private EtagService etagService;
	
	@Override
	public Response pushMsgBinding(
			String req) {
		return Response.ok().status(501).build();
	}

	/**
	 * 查询手机是否已绑定，
	 * 1.通过手机号和姓名查找到学生
	 * 2.查询学生是否有家长
	 */
	@Override
	public Response isBoundPhone(
			String req) {
		Map<String,String> map=(Map<String,String>)JacksonUtils.jsonToMapRuntimeException(req);
		String phone=map.get("phone");
		String name=map.get("name");
		
		if(!ValidateUtil.isPhone(phone)||!StringUtils.hasText(name)){
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		List<Child> children=baseService.getList("Child.getBoundChildren", map);
		Map resultMap=new HashMap();
		
		if(children==null||children.size()==0){
			logger.info("没有对应的学生:{} ,{}",phone,name);
			resultMap.put("verify_result", "1");
			return Response
					.ok()
					.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
		}
		
		for (Child child : children) {
				if(StringUtils.hasText(child.getPid())){
					logger.info("学生:{} 绑定到用户：{}",child.getId(), child.getPid());
					resultMap.put("verify_result", "1");
					return Response
							.ok()
							.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
				}
		}
		resultMap.put("verify_result", "0");
		
		return Response
				.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

	@Override
	public Response bindOtherPhone(
			String phone) {
		String userId = context.getHttpHeaders().getHeaderString("usr_id");
		logger.info("用户{}绑定{}下的学生", userId,phone);
		String result=accountService.bindPhone(userId,phone);
		if("1".equals(result)){
			UserInfo tmp=(UserInfo)etagService.get(CachedCommon.CACHED_ACCOUNT + userId);
			if(tmp!=null){
				boolean b=etagService.del(CachedCommon.CACHED_ACCOUNT + userId);
				logger.info("删除缓存：{},{}",CachedCommon.CACHED_ACCOUNT + userId,b);
			}
		}
		etagService.del(CachedCommon.CACHED_CHILD_LIST+userId);
		Map resultMap=new HashMap();
		resultMap.put("verify_result", result);
		return Response
				.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

}
