package com.neusoft.clw.core.processor;

import java.util.concurrent.ConcurrentHashMap; 

public class CoreShareInfo {
	 public static  String  sessionId  = "";
	 public static  int  beatTime  = 9000;
     static long seq=0;
	 public static ConcurrentHashMap<String, String> sessionIdMap = new ConcurrentHashMap<String, String>();
	 @SuppressWarnings("unused")
	private void SetSidMap(String key ,String value) {
		 sessionIdMap.put(key, value);
	    
	    }
	 @SuppressWarnings("unused")
	private String GetSidMap(String key) {
	       
		    return sessionIdMap.get(key);
	    }
	  public  static synchronized   long   getSeq()   
	  {   
		  seq=seq+1;
	      return   seq;   
	  } 
}
