package com.neusoft.smsplatform.nio.client;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 上午10:57:49
 */
public interface ISmsNioHandler<E extends ISmsCommunicateService> {

    void connectionOpen(E nioService) throws Exception;

    void doMsg(E nioService, byte[] buf) throws Exception;

    void connectionClosed(E nioService) throws Exception;
}
