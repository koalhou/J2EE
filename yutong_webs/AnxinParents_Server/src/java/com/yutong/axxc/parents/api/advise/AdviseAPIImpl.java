/**
 * @(#)AdviseServiceImpl.java 2013-3-25
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.api.advise;

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

import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.UserInfoKey;
import com.yutong.axxc.parents.entity.account.UserInfo;
import com.yutong.axxc.parents.exception.common.ApplicationException;
import com.yutong.axxc.parents.service.LogService;
import com.yutong.axxc.parents.tools.JacksonUtils;


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
	 * @param token
	 *            访问令牌信息.
	 * @param adviseCont
	 *            终端用户反馈内容.
	 * @return 终端用户问题处理结果信息.
	 */
	@Override
	public Response sendTtMsg(String adviseCont) {
		Map map=JacksonUtils.jsonToMapRuntimeException(adviseCont);
		String content=(String)map.get("content");
		
		if (!StringUtils.hasText(content)||content.trim().length()>99) {
			logger.error("用户反馈内容为空或超过100字符");
			throw new ApplicationException(ErrorConstant.ERROR10002,
					Response.Status.BAD_REQUEST);
		} else {
			String userId = context.getHttpHeaders().getHeaderString(UserInfoKey.USR_ID);
			
			UserInfo user=new UserInfo();
			user.setId(userId);
			user.setAlias(content.trim());
			
			logger.debug("用户{}提交评论",user);
			logServer.addAdviseInfo(user);
				return Response.ok()
						.header(HttpHeaders.CACHE_CONTROL, "no-store")
						.header("Pragma", "no-cache")
						.build();
			}
		
	}

}
