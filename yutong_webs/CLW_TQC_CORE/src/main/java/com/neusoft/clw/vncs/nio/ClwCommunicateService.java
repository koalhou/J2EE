/*******************************************************************************
 * @(#)TcpClient.java 2008-10-24
 *
 * Copyright 2008 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.nio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.exception.ConnUnavailableException;
import com.neusoft.clw.list.DoubleCircularList;
import com.neusoft.clw.vncs.back.Back;
import com.neusoft.clw.vncs.back.BackMap;
import com.neusoft.clw.vncs.dao.impl.ConfigParamDAO;
import com.neusoft.clw.vncs.dao.impl.MsgCfgDAO;
import com.neusoft.clw.vncs.dao.impl.SmsCfgDAO;

public final class ClwCommunicateService {

    private Logger log = LoggerFactory.getLogger(ClwCommunicateService.class);
    
    private static final String NAME = "<ClwCommunicateService>";

    private DoubleCircularList list = new DoubleCircularList();

    private String address;
    
    private ConfigParamDAO configParamDao;
    
    private MsgCfgDAO msgCfgDAO;
    
    private SmsCfgDAO smsCfgDAO;
    
    
    // private String count;
    
	public void setMsgCfgDAO(MsgCfgDAO msgCfgDAO) {
		this.msgCfgDAO = msgCfgDAO;
	}

	public SmsCfgDAO getSmsCfgDAO() {
		return smsCfgDAO;
	}

	public void setSmsCfgDAO(SmsCfgDAO smsCfgDAO) {
		this.smsCfgDAO = smsCfgDAO;
	}

	public void setConfigParamDao(ConfigParamDAO configParamDao) {
		this.configParamDao = configParamDao;
	}

	public void init() {
    	configParamDao.getConfigParamList();
    	smsCfgDAO.start();
    	configParamDao.start();
		msgCfgDAO.start();
        // address = LnjtConfig.BACK_ADDRESS;
        address = Config.props.getProperty("backAddress");
        // count = OtaConfig.CONNECTION_COUNT;
        try {
               start();
        } catch (Throwable t) {
            log.error(NAME+"CommunicateService Module start failed.", t);
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

    public CommunicateService getNextService() throws ConnUnavailableException {
        CommunicateService communicate = null;
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
//        LinkedList<Back> backlist = new LinkedList<Back>();
        for (int i=0;i<backAddress.length;i++) {
            String[] split = backAddress[i].split(":");
            if (split.length < 1) {
                log.error(NAME+ "backAddress:" + backAddress[i]
                        + " is invalid.");
                continue;
            }
            String ip = split[Back.IP_INDEX];
            int port = Integer.parseInt(split[Back.PORTINDEX]);
            Back back = new Back(ip, port);
            BackMap.getInstance().put(back.getIp(), back);
//            backlist.add(back);
            BackMap.getInstance().setList(back);
        }
//        BackMap.getInstance().setBacklist(backlist);
    }

	private void buildConnection() throws Exception {
        for (String backId : BackMap.getInstance().keySet()) {
            final Back back = BackMap.getInstance().get(backId);
            // for (int i = 0; i < getConnectionCount(); i++) {
            final CommunicateService communicateService = new CommunicateService(back.getIp(), back.getPort());
//            communicateService = new CommunicateService(back.getIp(), back.getPort());
            back.setCommunicateService(communicateService);
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
//                            Constant.array.add(back.getAddress());
                        } catch (Throwable e) {
                            log.error(NAME+ "reconnect " + back.getAddress() + "failed.");
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
                log.error(NAME+ "close "
                        + back.getAddress() + " connection failed.");
            }
        }
    }

    private void clearBackList() {
        BackMap.getInstance().clear();
    }
}
