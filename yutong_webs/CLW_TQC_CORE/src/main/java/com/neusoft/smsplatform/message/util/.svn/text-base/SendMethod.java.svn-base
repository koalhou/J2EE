package com.neusoft.smsplatform.message.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.smsplatform.message.back.MessageBack;
import com.neusoft.smsplatform.message.back.MessageBackMap;

/**
 * 
 * @author <a href="mailto:chenqiong@neusoft.com">chenqiong</a>
 * 
 */
public class SendMethod {

	private static Logger log = LoggerFactory.getLogger(SendMethod.class);

	private static final String NAME = "<SendMethod>";
	
	public static String SendCommand(byte[] buf){
		String baddress = null;
//		if (Constant.array != null && Constant.array.size() > 0) {
		
		MessageBackMap map = MessageBackMap.getInstance();
		int i = 0;
//		int len = map.size();
		while (i < 3) {
			i++;
//			System.out.println(map.getBacklist());
			MessageBack back = map.getList();
			if(back==null){
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					log.error(NAME+"cycleSendCommand(buf) error:"+e.getMessage());
				}
				continue;
			}
			baddress = back.getAddress();
			if (back.getSmscommunicateService().isAvailable()) {
				try {
					back.getSmscommunicateService().send(buf);
				} catch (Exception e) {
					continue;
				}
				break;
			} 
			if (i >= 3) {
				log.info("<cycleSendCommand>无有效连接，发送失败");
				return null;
			}
		}
		return baddress;
	}
}
