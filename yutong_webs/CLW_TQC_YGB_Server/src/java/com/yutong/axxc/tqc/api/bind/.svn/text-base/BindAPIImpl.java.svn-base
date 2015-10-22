package com.yutong.axxc.tqc.api.bind;

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
import com.yutong.axxc.tqc.entity.Child;
import com.yutong.axxc.tqc.entity.account.UserInfo;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.AccountService;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.service.EtagService;
import com.yutong.axxc.tqc.tools.JacksonUtils;
import com.yutong.axxc.tqc.tools.ValidateUtil;

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
		logger.info("接收到理推送消息绑定处理请求信息");

		String userId = context.getHttpHeaders().getHeaderString("usr_id");
//		String userId = "43";
		Map map=JacksonUtils.jsonToMapRuntimeException(req);
		String clientid=(String)map.get("client_id");
		
		logger.info("{}绑定到{}",userId,clientid);
		if (!org.springframework.util.StringUtils.hasText(clientid)) {
			logger.error("客户端同步clientid内容为空");
			return Response.status(Response.Status.BAD_REQUEST)
					.entity(ErrorConstant.ERROR10002.toJson())
					.header("Content-Type", "application/json;charset=UTF-8")
					.build();
		} else {
			logger.info("客户端同步clientid:" + clientid);
			int ret=accountService.bindClient(userId, clientid);
			if (ret==0) {
				logger.error("绑定失败，请检查参数");
				throw new ApplicationException(ErrorConstant.ERROR10024,
						Response.Status.BAD_REQUEST);
			}

			return Response.ok().build();
		}
	}

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
		
		if(children!=null&&children.size()>0){
			for (Child child : children) {
				if(StringUtils.hasText(child.getPid())){
					logger.info("学生:{} 绑定到用户：{}",child.getId(), child.getPid());
					resultMap.put("verify_result", "1");
					return Response
							.ok()
							.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
				}
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
//		String userId = "42";
		logger.info("用户{}绑定{}下的学生", userId,phone);
		String result=accountService.bindPhone(userId,phone);
		if("1".equals(result)){
			UserInfo tmp=(UserInfo)etagService.get(CachedCommon.CACHED_ACCOUNT + userId);
			if(tmp!=null){
				boolean b=etagService.del(CachedCommon.CACHED_ACCOUNT + userId);
				logger.info("删除缓存：{},{}",CachedCommon.CACHED_ACCOUNT + userId,b);
			}
		}
		Map resultMap=new HashMap();
		resultMap.put("verify_result", result);
		return Response
				.ok()
				.entity(JacksonUtils.toJsonRuntimeException(resultMap)).build();
	}

}
