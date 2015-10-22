/*******************************************************************************
 * @(#)INioHandler.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.mina.interfaces;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 上午10:57:49
 */
public interface INioHandler<E extends CommunicateService> {

    void connectionOpen(E nioService) throws Exception;

    void doMsg(E nioService, byte[] buf) throws Exception;

    void connectionClosed(E nioService) throws Exception;
}
