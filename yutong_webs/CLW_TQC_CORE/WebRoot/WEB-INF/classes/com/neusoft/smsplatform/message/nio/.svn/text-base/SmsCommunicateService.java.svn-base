package com.neusoft.smsplatform.message.nio;

import com.neusoft.smsplatform.configuration.MessageConfig;
import com.neusoft.smsplatform.nio.client.SmsTcpNioService;




public class SmsCommunicateService extends SmsTcpNioService {

	public SmsCommunicateService(String ip, int port) throws Exception {
        super(ip, port);
    }

    @Override
    protected void setDecoder() {
        this.decoder = MessageDecoder.getInstance();
    }

    @Override
    protected void setNioHandler() {
        this.nioHandler = new MessageCommunicateHandler(this);
    }

    @Override
    protected void setProcessorNum() {
        this.processorNum = Integer.parseInt(MessageConfig.props.getProperty("smsprocessorNum"));
    }

    @Override
    protected void setReconnectInterval() {
        this.reconnectInterval = Integer.parseInt(MessageConfig.props.getProperty("smsreconnectInterval"));
    }

    @Override
    protected void setThreadPoolSize() {
        this.threadPoolSize = Integer.parseInt(MessageConfig.props.getProperty("smsthreadPoolSize"));
    }
}
