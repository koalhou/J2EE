/*******************************************************************************
 * @(#)TcpClient.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.core.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.core.back.Back;
import com.neusoft.clw.core.back.BackMap;
import com.neusoft.clw.exception.ConnUnavailableException;
import com.neusoft.clw.list.DoubleCircularList;
import com.neusoft.clw.log.LogFormatter;

/**
 * build connection with the backs
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2008-10-24 下午01:23:02
 */
public final class CoreCommunicateService {

    private Logger log = LoggerFactory.getLogger(CoreCommunicateService.class);

    private DoubleCircularList list = new DoubleCircularList();

    private String address;

    // private String count;

    public void init() {
        // address = LnjtConfig.BACK_ADDRESS;
        address = Config.props.getProperty("backAddress");
        // count = OtaConfig.CONNECTION_COUNT;
        try {       
               start();
        } catch (Throwable t) {
            log.error(LogFormatter.formatMsg("TagCommunicateService",
                    "Lnjt CommunicateService Module start failed."), t);
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

    public NioCommunicateService getNextService() throws ConnUnavailableException {
        NioCommunicateService communicate = null;
        int size = list.getSize();
        boolean isAvailable = false;
        for (int i = 0; i < size; i++) {
            communicate = BackMap.getInstance().get(list.get().getKey()).getCommunicateService();
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
        for (String str : backAddress) {
            String[] split = str.split(":");
            if (split.length < 1) {
                log.error(LogFormatter.formatMsg("TagCommunicateService", "backAddress:" + str
                        + " is invalid."));
                continue;
            }
            String ip = split[Back.IP_INDEX];
            int port = Integer.parseInt(split[Back.PORTINDEX]);
            Back back = new Back(ip, port);
            BackMap.getInstance().put(back.getAddress(), back);
        }
    }

    private void buildConnection() throws Exception {
        for (String backId : BackMap.getInstance().keySet()) {
            final Back back = BackMap.getInstance().get(backId);
            // for (int i = 0; i < getConnectionCount(); i++) {
            final NioCommunicateService communicateService = new NioCommunicateService(back.getIp(), back
                    .getPort());
            back.setCommunicateService(communicateService);
            if (communicateService.connect()) {
                list.add(back);
            } else {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            communicateService.reconnect();
                            list.add(back);
                        } catch (Throwable e) {
                            log.error(LogFormatter.formatMsg("TagCommunicateService", "reconnect "
                                    + back.getAddress() + "failed."));
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

    private void closeConnection() {
        for (String backId : BackMap.getInstance().keySet()) {
            Back back = BackMap.getInstance().get(backId);
            try {
                back.getCommunicateService().close();
            } catch (Exception e) {
                log.error(LogFormatter.formatMsg("TagCommunicateService", "close "
                        + back.getAddress() + " connection failed."));
            }
        }
    }

    private void clearBackList() {
        BackMap.getInstance().clear();
    }
}
