package com.neusoft.smsplatform.nio.client;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.mina.common.IoHandlerAdapter;
import org.apache.mina.common.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.log.LogFormatter;
import com.neusoft.smsplatform.configuration.MessageConfig;



public abstract class SmsAbstractNioHandler<E extends ISmsCommunicateService> implements ISmsNioHandler<E> {

    private Logger log = LoggerFactory.getLogger(SmsAbstractNioHandler.class);
    
    private static final String NAME = "AbstractNioHandler";

    private E nioService;

    public SmsAbstractNioHandler(E nioService) {
        this.nioService = nioService;
    }

    private final Adapter adapter = new Adapter();

    @SuppressWarnings("unchecked")
    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(Integer.parseInt(MessageConfig.props
            .getProperty("smscorePoolSize")), Integer
            .parseInt(MessageConfig.props.getProperty("smsmaxPoolSize")), Long.parseLong(MessageConfig.props
            .getProperty("smskeepAliveTime")), TimeUnit.SECONDS, new ArrayBlockingQueue(Integer
            .parseInt(MessageConfig.props.getProperty("smsqueueSize"))), new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            log.error(LogFormatter.formatMsg(NAME, "threadPool queue is full."));
        }
    });

    class Adapter extends IoHandlerAdapter {

        @Override
        public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
            try {
                super.exceptionCaught(session, cause);
                log.error(LogFormatter.formatMsg(NAME, "the session with "
                        + nioService.getRemoteAddress() + " occurs exception, it will be closed."),
                        cause);
                session.close();
            } catch (Throwable t) {
                log.error(LogFormatter.formatMsg(NAME, ""), t);
            }
        }

        @Override
        public void messageReceived(IoSession session, final Object message) throws Exception {
            super.messageReceived(session, message);
            threadPool.execute(new Runnable() {
                public void run() {
                    try {
                        doMsg(nioService, (byte[]) message);
                    } catch (Throwable t) {
                        log.error(LogFormatter.formatMsg(NAME,
                                "deal with message failed."), t);
                    }
                }
            });
        }

        @Override
        public void sessionClosed(IoSession session) throws Exception {
            try {
                super.sessionClosed(session);
                nioService.setAvailable(false);
                connectionClosed(nioService);
                log.info(LogFormatter.formatMsg(NAME, "the session with "
                        + nioService.getRemoteAddress() + " is closed."));
            } catch (Throwable t) {
                log.error(LogFormatter.formatMsg(NAME, "the session with "
                        + nioService.getRemoteAddress() + " closed failed."), t);
            }
        }

        @Override
        public void sessionOpened(IoSession session) throws Exception {
            try {
	                super.sessionOpened(session);
	                nioService.setSession(session);
	                nioService.setAvailable(true);
	                connectionOpen(nioService);
	                log.info(LogFormatter.formatMsg(NAME, "the session with "
	                        + nioService.getRemoteAddress() + " is open."));
            } catch (Throwable t) {
                log.error(LogFormatter.formatMsg(NAME, "the session with "
                        + nioService.getRemoteAddress() + " open failed."), t);
            }
        }
    }

    /**
     * @return Returns the handler.
     */
    public Adapter getAdapter() {
        return adapter;
    }

    /**
     * @return Returns the nioService.
     */
    public final E getNioService() {
        return nioService;
    }
}
