/*******************************************************************************
 * @(#)TcpClient.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.smsplatform.message.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.exception.ConnUnavailableException;
import com.neusoft.clw.list.DoubleCircularList;
import com.neusoft.smsplatform.configuration.MessageConfig;
import com.neusoft.smsplatform.message.back.MessageBack;
import com.neusoft.smsplatform.message.back.MessageBackMap;


public final class MessageCommunicateService {

    private Logger log = LoggerFactory.getLogger(MessageCommunicateService.class);
    
    private static final String NAME = "<MessageCommunicateService>";

    private DoubleCircularList list = new DoubleCircularList();

    private String address;

	public void init() {
        // address = LnjtConfig.BACK_ADDRESS;
        address = MessageConfig.props.getProperty("smsbackAddress");
        // count = OtaConfig.CONNECTION_COUNT;
        try {       
               start();
        } catch (Throwable t) {
            log.error(NAME+
                    "CommunicateService Module start failed.", t);
        }
    }

    public void destroy() throws Exception {
        closeConnection();
        clearBackList();
    }

    public void start() throws Exception {
        loadBack();
        buildConnection();
    }

    public SmsCommunicateService getNextService() throws ConnUnavailableException {
        SmsCommunicateService communicate = null;
        int size = list.getSize();
        boolean isAvailable = false;
        for (int i = 0; i < size; i++) {
            communicate = MessageBackMap.getInstance().get(list.get().getKey()).getSmscommunicateService();
            if (!communicate.isAvailable()) {
                continue;
            } else {
                isAvailable = true;
                break;
            }
        }
        if (!isAvailable) {
            throw new ConnUnavailableException("all the connection are unavaiable.");
        }
        return communicate;
    }

    private void loadBack() throws Exception {
        String[] backAddress = (address != null) ? address.split(";") : new String[] {};
        if (backAddress.length == 0) {
            throw new Exception("backAddress is invalid.");
        }
//        LinkedList<Back> backlist = new LinkedList<Back>();
        for (int i=0;i<backAddress.length;i++) {
            String[] split = backAddress[i].split(":");
            if (split.length < 1) {
                log.error(NAME+ "backAddress:" + backAddress[i]
                        + " is invalid.");
                continue;
            }
            String ip = split[MessageBack.IP_INDEX];
            int port = Integer.parseInt(split[MessageBack.PORTINDEX]);
            MessageBack back = new MessageBack(ip, port);
            MessageBackMap.getInstance().put(back.getIp(), back);
//            backlist.add(back);
            MessageBackMap.getInstance().setList(back);
        }
//        BackMap.getInstance().setBacklist(backlist);
    }

	private void buildConnection() throws Exception {
        for (String backId : MessageBackMap.getInstance().keySet()) {
            final MessageBack back = MessageBackMap.getInstance().get(backId);
            // for (int i = 0; i < getConnectionCount(); i++) {
            final SmsCommunicateService communicateService = new SmsCommunicateService(back.getIp(), back
                    .getPort());
//            communicateService = new CommunicateService(back.getIp(), back.getPort());
            back.setSmscommunicateService(communicateService);
            if (communicateService.connect()) {
                list.add(back);
//                Constant.array.add(back.getAddress());
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            communicateService.reconnect();
                            list.add(back);
                        } catch (Throwable e) {
                            log.error(NAME+ "reconnect "
                                    + back.getAddress() + "failed.");
                        }
                    }
                }.start();
            }
        }
    }

    // private int getConnectionCount() throws Exception {
    // int connectionCount = (count != null) ? Integer.parseInt(count) : -1;
    // if (connectionCount < 0) {
    // throw new Exception("connectionCount is invalid.");
    // }
    // return connectionCount;
    // }

    public void closeConnection() {
        for (String backId : MessageBackMap.getInstance().keySet()) {
            MessageBack back = MessageBackMap.getInstance().get(backId);
            try {
                back.getSmscommunicateService().close();
            } catch (Exception e) {
                log.error(NAME+ "close "
                        + back.getAddress() + " connection failed.");
            }
        }
    }

    private void clearBackList() {
        MessageBackMap.getInstance().clear();
    }
}
