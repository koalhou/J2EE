/**
 * @(#)OauthServiceImpl.java 2013-3-24
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.tqc.api.email;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.entity.common.Verify;
import com.yutong.axxc.tqc.service.BaseService;
import com.yutong.axxc.tqc.tools.JacksonUtils;

/**
 * @author wyg
 */

public class EmailAPIImpl implements EmailAPI {

	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(EmailAPIImpl.class);
	@Context
	private MessageContext context;
	
	@Autowired
	protected BaseService baseService;
	
	
	/**
	 * 验证email
	 */
	@Override
	public Response send(String email) {
		Integer ret=baseService.get("Account.verifyEmail", email);
		
		Verify ver=new Verify();
		ver.setVerify_result(ret);
		return Response.ok()
				.entity(JacksonUtils.toJsonRuntimeException(ver))
				.build();
	}


}
