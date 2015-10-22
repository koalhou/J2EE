/**
 * @(#)JacksonSerializeException.java 2013-4-8
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.neusoft.parents.utils;

/**
 * @author <a href="mailto:yi_liu@neusoft.com">yi_liu </a>
 * @version $Revision 1.0 $ 2013-4-8 上午10:48:52
 */
public class JacksonSerializeException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3130986529358944746L;

	/**
	 * 
	 */
	public JacksonSerializeException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public JacksonSerializeException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public JacksonSerializeException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public JacksonSerializeException(Throwable cause) {
		super(cause);
	}
}
