
package com.yutong.axxc.tqc.provider;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.axxc.tqc.common.ErrorConstant;
import com.yutong.axxc.tqc.common.HttpConstant;
import com.yutong.axxc.tqc.common.ModCommonConstant;
import com.yutong.axxc.tqc.exception.common.JacksonSerializeException;


@Provider
@Produces({ HttpConstant.MEDIATYPE_JSON_UTF_8 })
public class JacksonSerializeExceptionMapper implements
		ExceptionMapper<JacksonSerializeException> {

	/**
	 * 日志记录器.
	 */
	private static Logger logger = LoggerFactory
			.getLogger(ModCommonConstant.LOGGER_NAME);

	@Override
	public Response toResponse(JacksonSerializeException e) {
		logger.error("将实体内容序列化为JSON字符串时出错", e);
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(ErrorConstant.ERROR90000.toJson()).build();
	}
}
