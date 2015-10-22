package com.neusoft.parents.pushmessage.manager;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.parents.pushmessage.domain.PushMessageCls;
import com.yutong.axxc.xmpp.AndroidpnPusher;
import com.yutong.axxc.xmpp.IPusher;

public class NotificationManager {

	private   Logger log = LoggerFactory.getLogger(NotificationManager.class);        
	private static final String NAME = "<NotificationManager>";
	private static final NotificationManager nManager = new NotificationManager();

	
	//private String pushServerUrl="http://10.8.2.199:5333/Androidpn-tomcat/pushapi.do?action=send";
	//private IPusher puser=new AndroidpnPusher(pushServerUrl);
	private IPusher puser=new AndroidpnPusher(Config.props.getProperty("pushServerUrl"));
	private String apikey="0123456789";
	private String appid="anxin_tqc"; 
	
	private NotificationManager() {
	}

	public static NotificationManager getInstance() {
		return nManager;
	}
	
	public void push2SingleUser(String userid,String message)
	{
		try {
			puser.push2SingleUser(apikey, appid, userid, message);
		} catch (Exception e) {
			log.error(NAME+" push2SingleUser userid:"+userid+" message:"+message+" error:"+e.getMessage());
		}
	}
	
	public void push2MultiUsers(String[] userids,String message)
	{
		try {
			puser.push2MultiUsers(apikey, appid,userids, message);
		} catch (Exception e) {
			log.error(NAME+" push2MultiUsers userid:"+userids.length+" message:"+message+" error:"+e.getMessage());
		}
	}
	
	public void push2AllUsers(String message)
	{
		try {
			puser.push2AllUsers(apikey, appid, message);
		} catch (Exception e) {
			log.error(NAME+" push2MultiUsers message:"+message+" error:"+e.getMessage());
		}
	}
	
	
	public static void main(String[] args)
	{
	    
	    
        System.out.println("111111111111111111");
        PushMessageCls pmc=new PushMessageCls();
        pmc.setId(1);
        String msgJson="{}";
    
   
        
        PushMsgBuffer.getInstance().add(pmc);
         System.out.println("222222222222222");
	}
}
