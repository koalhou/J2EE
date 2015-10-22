/**
 * @(#)AdviseService.java 2013-3-25
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.yutong.axxc.parents.api.advise;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;

import com.yutong.axxc.parents.common.HttpConstant;

/**
 * @author wyg
 */

public interface AdviseAPI {

	/**
	 * 终端用户问题反馈.
	 * 
	 * @param token
	 *            访问令牌信息.
	 * @param adviseCont
	 *            终端用户反馈内容.
	 * @return 终端用户问题处理结果信息.
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
	Response sendTtMsg(String adviseCont);

}
