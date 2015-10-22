package com.yutong.axxc.tqc.api.log;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.UserInfoKey;
import com.yutong.axxc.tqc.entity.env.EnvInfo;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.LogService;
import com.yutong.axxc.tqc.tools.BeanUtil;
import com.yutong.axxc.tqc.tools.JacksonUtils;


public class LogAPIImpl implements LogAPI {
	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory.getLogger(LogAPIImpl.class);

	@Context 
	private MessageContext context;
	@Autowired
	private LogService logServer;
	
	
	@Override
	public Response errorreport(String req) {
		Map<?,?> map=JacksonUtils.jsonToMapRuntimeException(req);
		Object env_info = map.get("env_info");
		Object error_code = map.get("error_code");
		Object error_desc = map.get("error_desc");
		Object error_time = map.get("error_time");
		
		if(null == env_info || null == error_code || null == error_desc || null == error_time){
			logger.info("LogAPI ：errreport，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		Map<?,?> env_map = (Map<String, String>) env_info;
		
		String app_version = (String)env_map.get("app_version");
		String os_name = (String)env_map.get("os_name");
		String os_version = (String)env_map.get("os_version");
		
		Map<String, String> error_conditions = new HashMap<String, String>();
		error_conditions.put("emp_code", emp_code);
		error_conditions.put("error_code", String.valueOf(error_code));
		error_conditions.put("error_desc", String.valueOf(error_desc));
		error_conditions.put("error_time", String.valueOf(error_time));
		error_conditions.put("app_version", app_version);
		error_conditions.put("os_name", os_name);
		error_conditions.put("os_version", os_version);
		
		if(1 == logServer.saveErrorInfo(error_conditions)){
			return Response.ok().build();	
		}
		throw new ApplicationException(ErrorConstant.ERROR10024, Response.Status.BAD_REQUEST);
	}
	
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
			throw new ApplicationException(ErrorConstant.ERROR10002, Response.Status.BAD_REQUEST);
		}
		// 终端信息入库
		int ret=logServer.saveEnvInfo(iEnvInfo);
		if(ret==0){
			throw new ApplicationException(ErrorConstant.ERROR10024, Response.Status.BAD_REQUEST);
		}
		return Response.ok().build();
	}
	/**
	 * 用户行为信息上报接口.
	 * 
	 * @param behaviorInfo 终端用户行为信息.
	 *            
	 * @return 行为结果上报结果.
	 */
	@Override
	public Response behavior(String behaviorInfo) {
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//		String userId = "43";
		Map map=JacksonUtils.jsonToMapRuntimeException(behaviorInfo);
		List list=(List)map.get("usr_behavior_infos");
		map.put("id", userId);
		logServer.saveBehavior(list,userId);

		return Response.ok().build();

	}
	
	@Override
	public Response usertime(String behaviorInfo) {
		String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//		String userId = "43";
		Map map=JacksonUtils.jsonToMapRuntimeException(behaviorInfo);
		List list=(List)map.get("used_duration_infos");
		map.put("id", userId);
		logServer.saveUsedTime(list,userId);

		return Response.ok().build();
	}

	@Override
	public Response usagecollect(String req) {
		String emp_code = context.getHttpHeaders().getHeaderString("usr_id");
		Map<?,?> map = (Map<?,?>)JacksonUtils.jsonToMapRuntimeException(req);
		if(map.get("module_son_id") == null){
			logger.info("SystemAPI ：changepwd，关键参数不足！");
			throw new ApplicationException(ErrorConstant.ERROR10001, Response.Status.BAD_REQUEST);
		}
		logServer.saveUsage((Map<String, String>) map,emp_code);
		return Response.ok().build();
	}

}
