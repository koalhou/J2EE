
package com.yutong.axxc.parents.api.email;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.MessageContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yutong.axxc.parents.common.ModCommonConstant;
import com.yutong.axxc.parents.entity.common.Verify;
import com.yutong.axxc.parents.service.BaseService;
import com.yutong.axxc.parents.tools.JacksonUtils;

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
	 * 发送email
	 */
	@Override
	public Response send(String email) {
		return Response.ok().status(501).build();
	}


}
