package com.neusoft.clw.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.clw.constant.Constant;
import com.neusoft.tlm.memcache.process.MemcacheClientHandler;

public class MemcacheThread extends Thread implements Runnable {
	private String path;
	private ClassPathXmlApplicationContext cx = null;
	public MemcacheThread(String path){
		this.path = path;
	}
	
	public void run() {		
		//是否启动memcache
		if(Constant.isstartMemcache.equals("1")){
			cx = new ClassPathXmlApplicationContext(path);
			Constant.setMemcachedClient((MemcacheClientHandler) cx.getBean("cacheClient"));
		}
	}
}
