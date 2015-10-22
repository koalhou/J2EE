package com.neusoft.clw.vncs.nio;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.nio.client.TcpNioService;


public class CommunicateService extends TcpNioService {

	public CommunicateService(String ip, int port) throws Exception {
        super(ip, port);
    }

    @Override
    protected void setDecoder() {
        this.decoder = ClwDecoder.getInstance();
    }

    @Override
    protected void setNioHandler() {
        this.nioHandler = new ClwCommunicateHandler(this);
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
