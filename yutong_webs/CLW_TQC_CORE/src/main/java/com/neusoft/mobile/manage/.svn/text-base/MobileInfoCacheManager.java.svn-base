package com.neusoft.mobile.manage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.MobileInfo;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.mobile.dao.IMobileInfoDAO;

public class MobileInfoCacheManager {
private Logger log = LoggerFactory.getLogger(MobileInfoCacheManager.class);
	
	private final static String NAME = "<MobileInfoCacheManager>";
	
	private static final MobileInfoCacheManager mobileinfoCacheManager = new MobileInfoCacheManager();

	private Date lastIncremSyncDate;

	private IMobileInfoDAO mobileinfoDAO;
	
	public static Map<String, List<MobileInfo>> infoMap;

	private MobileInfoCacheManager() {
		infoMap = new HashMap<String, List<MobileInfo>>();
	}

	public static MobileInfoCacheManager getInstance() {
		return mobileinfoCacheManager;
	}
 
	public synchronized void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[mobileinfoInit]");

		log.info("<MobileInfoCacheManager>开始初始化手机客户端信息缓存。。");
		/* 从数据库中查询出手机客户端信息，并保存入缓存中 */
		List<MobileInfo> list = mobileinfoDAO.getMobileInfoList();
		if(list==null||list.size()<=0){
			log.debug(NAME+"从数据库未查出相关手机客户端信息。。");
			log.debug(NAME+"加载手机客户端信息缓存成功，但未有相关数据。。");
			return;
		}
		if(Constant.isfirstmobileload.equals("true")){
			addMobileInfoIntoCache(list);
			Constant.isfirstmobileload = "false";
		}else{
			SyncMobileInfoValue(Constant.OFF,Constant.MOBILEINFO,list);
		}
		
		lastIncremSyncDate = new Date();

		log.debug(NAME+"初始化应用配置的时间为：" + lastIncremSyncDate);
		lastIncremSyncDate = null;
		list = null;
		log.info(NAME+"手机客户端信息缓存初始化完毕。");
	}

	/**
	 * 将手机客户端信息加入缓存
	 * 
	 * @param cidList
	 */
	public synchronized void addMobileInfoIntoCache(List<MobileInfo> list) {
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			Constant.getMemcachedClient().insert(Constant.MOBILEINFO, list);
		}
		infoMap.put(Constant.MOBILEINFO, list);
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<MobileInfo> getValue(String key){
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			Object o = Constant.getMemcachedClient().getObject(key);
			if(o!=null&&!o.equals("")){
				return (List<MobileInfo>) o;
			}else{
				return null;
			}
		}else{
			return infoMap.get(key);
		}
	}
//	public synchronized Collection<List<MobileInfo>> getValues(){
//		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
//			return Constant.getMemcachedClient().getObject(key);
//		}else{
//			return infoMap.values();
////		}
//	}
	public synchronized List<MobileInfo> SyncMobileInfoValue(String type, String key, List<MobileInfo> list) {
		if (type.equals(Constant.OFF)) {
			deleteMobileInfo(key);
			addMobileInfoIntoCache(list);
			return null;
		} else if (type.equals(Constant.ON)) {
			return getValue(key);
		} else {
			log.error(NAME + "SyncMobileInfoValue传入的类型错误");
			return null;
		}
	}
	
	public void deleteMobileInfo(String key){
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			Constant.getMemcachedClient().delete(key);
		}
		infoMap.remove(key);
	}
	
	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public void setMobileinfoDAO(IMobileInfoDAO mobileinfoDAO) {
		this.mobileinfoDAO = mobileinfoDAO;
	}
}
