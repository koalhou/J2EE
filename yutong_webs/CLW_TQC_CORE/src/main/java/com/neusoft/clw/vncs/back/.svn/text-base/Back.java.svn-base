/*******************************************************************************
 * @(#)Back.java 2009-2-4
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.vncs.back;

import com.neusoft.clw.list.ListObject;
import com.neusoft.clw.vncs.nio.CommunicateService;

//import com.neusoft.tag.ota.communicate.CommunicateService;
//import com.neusoft.tag.ota.list.ListObject;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2009-2-4 上午09:43:06
 */
public class Back extends ListObject {

    private String ip;

    private int port;

    public static final int IP_INDEX = 0;

    public static final int PORTINDEX = 1;

    private CommunicateService communicateService;

    public Back(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.setKey(getAddress());
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return ip + ":" + port;
    }

    public void setCommunicateService(CommunicateService communicateService) {
        this.communicateService = communicateService;
    }

    public CommunicateService getCommunicateService() {
        return communicateService;
    }

}
