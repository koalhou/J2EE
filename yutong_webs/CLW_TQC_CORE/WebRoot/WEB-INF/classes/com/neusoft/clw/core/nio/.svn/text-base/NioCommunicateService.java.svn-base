package com.neusoft.clw.core.nio;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.nio.client.TcpNioService;


public class NioCommunicateService extends TcpNioService {

    public NioCommunicateService(String ip, int port) throws Exception {
        super(ip, port);
    }

    @Override
    protected void setDecoder() {
        this.decoder = CoreDecoder.getInstance();
    }

    @Override
    protected void setNioHandler() {
        this.nioHandler = new CoreCommunicateHandler(this);
    }

    @Override
    protected void setProcessorNum() {
        this.processorNum = Integer.parseInt(Config.props.getProperty("processorNum"));
    }

    @Override
    protected void setReconnectInterval() {
        this.reconnectInterval = Integer.parseInt(Config.props.getProperty("reconnectInterval"));
    }

    @Override
    protected void setThreadPoolSize() {
        this.threadPoolSize = Integer.parseInt(Config.props.getProperty("threadPoolSize"));
    }
}
