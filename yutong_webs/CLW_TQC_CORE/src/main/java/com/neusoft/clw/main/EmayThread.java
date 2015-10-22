package com.neusoft.clw.main;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.emay.sdk.SDKClient;

public class EmayThread implements Runnable {
	private static final EmayThread thread = new EmayThread();
	
	public static EmayThread getInstance() {
        return thread;
    }
	
	public void run() {
		SDKClient.getClient().registEx(Config.props.getProperty("msgpassword"));
	}

}
