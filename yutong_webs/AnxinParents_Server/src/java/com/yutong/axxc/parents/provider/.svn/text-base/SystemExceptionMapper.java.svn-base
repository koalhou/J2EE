
package com.yutong.axxc.parents.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.axxc.parents.common.ErrorConstant;
import com.yutong.axxc.parents.common.HttpConstant;
import com.yutong.axxc.parents.common.ModCommonConstant;


@Provider
@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
public class SystemExceptionMapper implements
		ExceptionMapper<Exception> {

	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);

	@Override
	public Response toResponse(Exception e) {
		logger.error("发生未知异常", e);
		return Response.status(Response.Status.BAD_REQUEST )
				.entity(e.getMessage()).build();
	}
}
