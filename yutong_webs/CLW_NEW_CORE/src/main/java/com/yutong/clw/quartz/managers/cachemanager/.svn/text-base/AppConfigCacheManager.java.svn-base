package com.yutong.clw.quartz.managers.cachemanager;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.beans.cl.AppConfigBean;
import com.yutong.clw.dao.IAppConfigDAO;
import com.yutong.clw.utils.StringUtil;

public class AppConfigCacheManager {

	private Logger log = LoggerFactory.getLogger(AppConfigCacheManager.class);
	
	private final static String NAME = "<AppConfigCacheManager>";
	
	private static final AppConfigCacheManager appConfigCacheManager = new AppConfigCacheManager();
	
//	private List<AppConfigBean> appconfigList;

	private Date lastIncremSyncDate;

	private IAppConfigDAO appconfigDAO;

	public static Map<String, AppConfigBean> appConfigMap;

	private AppConfigCacheManager() {
//		appconfigList = new ArrayList<AppConfigBean>();
		appConfigMap = new HashMap<String, AppConfigBean>();
	}

	public static AppConfigCacheManager getInstance() {
		return appConfigCacheManager;
	}
 
	public synchronized void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[appConfigInit]");

		log.info("<AppConfigCacheManager>开始初始化应用配置信息缓存。。");
		/* 从数据库中查询出终端基本信息，并保存入缓存中 */
		List<AppConfigBean> appconfigList = appconfigDAO.getAllAppConfigList();
		if(appconfigList==null||appconfigList.size()<=0){
			log.debug(NAME+"从数据库未查出相关应用配置数据。。");
			log.debug(NAME+"加载应用配置数据缓存成功，但未有相关数据。。");
			return;
		}
		for(AppConfigBean appConfigBean:appconfigList){
			addAppConfigIntoCache(appConfigBean);
		}

		log.info(NAME+"数据库中共有" + appConfigMap.size() + "个应用配置信息");

		lastIncremSyncDate = new Date();

		log.debug(NAME+"初始化应用配置的时间为：" + lastIncremSyncDate);
		lastIncremSyncDate = null;
		appconfigList = null;
		log.info(NAME+"应用配置缓存初始化完毕。");
		log.debug(NAME+"应用配置缓存大小："+appConfigMap.size());
	}

	/**
	 * 查看某应用信息在缓存中是否存在
	 * 
	 * @param appid
	 * @return
	 */
	public boolean checkExisted(String appid) {
		return appConfigMap.containsKey(appid);
	}

	/**
	 * 将应用信息加入缓存
	 * 
	 * @param cidList
	 */
	public synchronized void addAppConfigIntoCache(AppConfigBean appConfigBean) {
		appConfigMap.put(appConfigBean.getApp_id(), appConfigBean);
		log.debug(NAME+"已将应用信息"+appConfigBean.getApp_id()+"加入缓存！");
	}
	
	public AppConfigBean getValue(String key){
		return appConfigMap.get(key);
	}
	
	public Collection<AppConfigBean> getValues(){
		return appConfigMap.values();
	}
	
	/**
	 * 从缓存中删除一个应用信息
	 * 
	 * @param cidList
	 */
	public synchronized void delAppConfigFromCache(String app_id) {
		log.debug(NAME+"开始从缓存中删除应用"+(app_id));
		appConfigMap.remove(app_id);
		log.debug(NAME+"从缓存中删除应用"+(app_id)+"完毕！");
	}


	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public void setAppconfigDAO(IAppConfigDAO appconfigDAO) {
		this.appconfigDAO = appconfigDAO;
	}
}
