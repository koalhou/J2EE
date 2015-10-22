/**
 * 
 */
package com.yutong.axxc.parents.exception.common;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import com.yutong.axxc.parents.entity.common.ErrorBean;

/**
 */
public class ApplicationException extends WebApplicationException {

	/**
	 * serialVersionUID long
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 构造器ApplicationException(Throwable exp, final ErrorBean entity, final
	 * Response.Status status) 在有可捕获的异常时使用
	 * 
	 * @param exp
	 *            捕获的异常
	 * @param entity
	 *            返回给客户端的ErrorBean，会调用ErrorBean内的toString()方法，需要在该方法内提供具体的转换规则
	 * @param status
	 *            返回的HTTP状态码
	 */
	public ApplicationException(Throwable exp, final ErrorBean entity,
			final Response.Status status) {
		this(exp, entity.toString(), status);
	}

	/**
	 * 构造器ApplicationException(Throwable exp, final String errorMsg, final
	 * Response.Status status) 在有可捕获的异常时使用
	 * 
	 * @param exp
	 *            捕获的异常
	 * @param errorMsg
	 *            返回给客户端的信息
	 * @param status
	 *            返回的HTTP状态码
	 */
	public ApplicationException(Throwable exp, final String errorMsg,
			final Response.Status status) {
		this(exp, Response.status(status).entity(errorMsg).build());
	}

	/**
	 * 构造器ApplicationException(String expDes, final ErrorBean entity, final
	 * Response.Status status)
	 * 
	 * @param expDes
	 *            异常描述信息
	 * @param entity
	 *            返回给客户端的ErrorBean，会调用ErrorBean内的toString()方法，需要在该方法内提供具体的转换规则
	 * @param status
	 *            返回的HTTP状态码
	 */
	public ApplicationException(String expDes, final ErrorBean entity,
			final Response.Status status) {
		this(expDes, entity.toJson(), status);
	}

	/**
	 * 构造器ApplicationException(String expDes, final String errorMsg, final
	 * Response.Status status)
	 * 
	 * @param expDes
	 *            异常描述信息
	 * @param errorMsg
	 *            返回给客户端的信息
	 * @param status
	 *            返回的HTTP状态码
	 */
	public ApplicationException(String expDes, final String errorMsg,
			final Response.Status status) {
		this(expDes, Response.status(status).entity(errorMsg).build());
	}

	/**
	 * 构造器ApplicationException(String expDes, Response response)
	 * 
	 * @param expDes
	 *            异常描述信息
	 * @param response
	 *            应答消息Response
	 */
	public ApplicationException(String expDes, Response response) {
		this(new RuntimeException(expDes), response);
	}

	/**
	 * 构造器ApplicationException(Throwable exp, Response response)
	 * 
	 * @param exp
	 *            捕获的异常
	 * @param response
	 *            应答消息Response
	 */
	public ApplicationException(Throwable exp, Response response) {
		super(exp, response);
	}

	/**
	 * 构造器ApplicationException(ErrorBean entity, final Response.Status status)
	 * 
	 * @param entity
	 *            返回给客户端的信息
	 * @param status
	 *            返回的HTTP状态码
	 */
	public ApplicationException(ErrorBean entity, final Response.Status status) {
		this(entity.toString(), entity, status);
	}
}
