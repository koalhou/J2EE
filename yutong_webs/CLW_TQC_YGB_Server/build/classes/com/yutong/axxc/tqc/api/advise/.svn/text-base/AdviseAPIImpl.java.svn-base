/**
 * @(#)AdviseServiceImpl.java 2013-3-25
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.api.advise;

import java.io.IOException;
import java.util.Map;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;

import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.UserInfoKey;
import com.yutong.axxc.tqc.entity.account.UserInfo;
import com.yutong.axxc.tqc.exception.common.ApplicationException;
import com.yutong.axxc.tqc.service.LogService;
import com.yutong.axxc.tqc.tools.JacksonUtils;


public class AdviseAPIImpl implements AdviseAPI {


	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(AdviseAPIImpl.class);


	@Context 
	private MessageContext context;
	@Autowired
	private LogService logServer;


	/**
	 * 终端用户问题反馈.
	 * 
	 * @param token 访问令牌信息.
	 *            
	 * @param adviseCont 终端用户反馈内容.
	 *            
	 * @return 终端用户问题处理结果信息.
	 */
	@Override
	public Response sendTtMsg(String adviseCont) {
		Map map=JacksonUtils.jsonToMapRuntimeException(adviseCont);
		String content=(String)map.get("content");
		
		if (!StringUtils.hasText(content)) {
			logger.error("用户反馈内容为空");
			throw new ApplicationException(ErrorConstant.ERROR10002,Response.Status.BAD_REQUEST);
					
		} else {
			String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
//			String userId ="43";
			
				// 向数据库中插入信息.
			UserInfo user=new UserInfo();
			user.setEmp_id(userId);
			BASE64Decoder decoder = new BASE64Decoder();
			try {
				user.setEmp_alias(String.valueOf(decoder.decodeBuffer(content)));
			} catch (IOException e) {
				logger.error("用户反馈内容解码失败{}",content);
				throw new ApplicationException(ErrorConstant.ERROR10002,
						Response.Status.BAD_REQUEST);
			}
			
			logger.info("用户{}提交评论",user);
			logServer.addAdviseInfo(user);
				return Response.ok()
						.header(HttpHeaders.CACHE_CONTROL, "no-store")
						.header("Pragma", "no-cache")
						.build();
			}
		
	}

}
