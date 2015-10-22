/**
 * @(#)Oauth.java 2013-3-24
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.api.email;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.common.HttpConstant;

/**
 * 邮件接口
 * @author wyg
 */
@Path(value = "/")
public interface EmailAPI {

	
	/**
	 * 发送邮件
	 * 
	 * @param email 
	 * 
	 * @return 
	 */
	@POST
	@Path(value = "/{usr_email}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response send(@PathParam("usr_email") String email);
	
}
