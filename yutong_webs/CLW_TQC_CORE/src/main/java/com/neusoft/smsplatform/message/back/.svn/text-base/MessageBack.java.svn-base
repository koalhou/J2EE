package com.neusoft.smsplatform.message.back;

import com.neusoft.clw.list.ListObject;
import com.neusoft.smsplatform.message.nio.SmsCommunicateService;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2009-2-4 上午09:43:06
 */
public class MessageBack extends ListObject {

    private String ip;

    private int port;

    public static final int IP_INDEX = 0;

    public static final int PORTINDEX = 1;

    private SmsCommunicateService smscommunicateService;

    public MessageBack(String ip, int port) {
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

    public SmsCommunicateService getSmscommunicateService() {
		return smscommunicateService;
	}

	public void setSmscommunicateService(SmsCommunicateService smscommunicateService) {
		this.smscommunicateService = smscommunicateService;
	}

}
