package com.yutong.clw.nio.mina.impl;

import com.yutong.clw.config.Config;
import com.yutong.clw.nio.mina.coding.ClwCodeFactory;
import com.yutong.clw.nio.mina.interfaces.CommunicateServiceAbstract;


public class CommunicateService extends CommunicateServiceAbstract {

	public CommunicateService(String ip, int port) throws Exception {
        super(ip, port);
    }

    @Override
    protected void setDecoder() {
        this.decoder = ClwCodeFactory.getInstance();
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
