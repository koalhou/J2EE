package com.yutong.axxc.parents.api.log;

import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.common.UserInfoKey;
import com.yutong.axxc.parents.entity.env.EnvInfo;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.AccountService;
import com.yutong.axxc.parents.service.LogService;
import com.yutong.axxc.parents.tools.BeanUtil;
import com.yutong.axxc.parents.tools.JacksonUtils;


public class LogAPIImpl implements LogAPI {
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(LogAPIImpl.class);

	@Context 
	private MessageContext context;
	@Autowired
	private AccountService accountService;
	@Autowired
	private LogService logServer;
	
	@Override
	public Response env(String info) {
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);

		EnvInfo iEnvInfo = new EnvInfo();
		try {
			iEnvInfo = JacksonUtils.fromJson(info, EnvInfo.class);
			iEnvInfo.setUserId(userId);
			BeanUtil.checkObjectLegal(iEnvInfo);
		} catch (Exception e) {
			logger.error("本次上传信息不符合要求" + e.getMessage());
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		}
		// 终端信息入库
		int ret=logServer.saveEnvInfo(iEnvInfo);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024,
					Response.Status.BAD_REQUEST);
		}

		return Response.ok().build();
	}
	
	@Override
	public Response usertime(String behaviorInfo) {
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
		Map map=JacksonUtils.jsonToMapRuntimeException(behaviorInfo);
		List list=(List)map.get("used_duration_infos");
		map.put("id", userId);
		logServer.saveUsedTime(list,userId);

		return Response.ok().build();
	}
}
