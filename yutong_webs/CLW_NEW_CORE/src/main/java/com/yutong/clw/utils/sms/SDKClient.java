package com.yutong.clw.utils.sms;
import cn.emay.sdk.client.api.Client;

import com.yutong.clw.config.Config;


public class SDKClient {
	private static Client client=null;
	private SDKClient(){
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public synchronized static Client getClient(){
//		ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
		if(client==null){
			try {
				System.out.println("=========client启动==========");
				client=new Client(Config.props.getProperty("softwareSerialNo"),Config.props.getProperty("msgkey"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	public static void main(String str[]){
		SDKClient.getClient();
	}
}
