package test;

import java.net.UnknownHostException;
import java.text.ParseException;

import com.neusoft.tlm.memcache.process.MemcacheClientHandler;

public class TestMemcached {   
   public static void main(String[] args) throws ParseException, UnknownHostException {   
	   MemcacheClientHandler init = new MemcacheClientHandler();
		init.setMainUrl("192.168.47.80:11211");
		init.setBackupUrl("192.168.47.80:11211");
		init.setMaxcon("1");
		init.init();
	    init.insert("123", "ttttttttttt");
		System.out.println(init.getObject("123"));
//	   InetAddress addr = InetAddress.getLocalHost();
//	   String ip=addr.getHostAddress();//获得本机IP
//	   System.out.println(ip);
//	   ClassPathXmlApplicationContext cx = new ClassPathXmlApplicationContext("F:/work/clwspace/CLW_C/src/main/config/main.xml");
	   
   }   
}  
