package com.neusoft.mobile.manage;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.domain.PMRule;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.mobile.dao.IPMRuleDAO;

public class PMRuleCacheManager {

	private Logger log = LoggerFactory.getLogger(PMRuleCacheManager.class);
	
	private final static String NAME = "<PMRuleCacheManager>";
	
	private static final PMRuleCacheManager tsRuleCacheManager = new PMRuleCacheManager();
	
//	private List<AppConfigBean> appconfigList;

	private Date lastIncremSyncDate;

	private IPMRuleDAO pmRuleDAO;

	public static Map<String, PMRule> pmRuleMap;

	private PMRuleCacheManager() {
		pmRuleMap = new HashMap<String, PMRule>();
	}

	public static PMRuleCacheManager getInstance() {
		return tsRuleCacheManager;
	}
 
	@SuppressWarnings("unchecked")
	public synchronized void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[PMRuleInit]");
		log.info("<TuiSongRuleCacheManager>开始加载推送规则信息缓存。。");
		if (Constant.pmRuleload) {
			log.debug("<TuiSongRuleCacheManager>1");
			Constant.pmRuleload = false;
			String systime = pmRuleDAO.getSysTime();
			log.debug("<TuiSongRuleCacheManager>2");
			if(Constant.upd_pmrule_cache_time == null){
				Constant.upd_pmrule_cache_time = systime;
			}
			log.info("<TuiSongRuleCacheManager>3");
			/* 从数据库中查询出终端基本信息，并保存入缓存中 */
			List<PMRule> list = pmRuleDAO.getPartPMRule(Constant.upd_pmrule_cache_time);
			log.debug("<TuiSongRuleCacheManager>4");
			if(list==null||list.size()<=0){
				log.info(NAME+"从数据库未查出相关推送规则数据。。");
				log.info(NAME+"加载推送规则数据缓存成功，但未有相关数据。。");
//				return;
			}else{
				log.debug("<TuiSongRuleCacheManager>5");
				PMRule pmrule = null;
				if (pmRuleMap != null && pmRuleMap.size() > 0) {
					log.debug("<TuiSongRuleCacheManager>5-1");
					Iterator it = list.iterator();
					while (it.hasNext()) {
						log.debug("<TuiSongRuleCacheManager>5-2");
						pmrule =  (PMRule) it.next();
						String key = pmrule.getUser_id() + pmrule.getPm_rule_id();
						log.debug("<TuiSongRuleCacheManager>5-3:"+key);
						if (pmrule.getValid_flag().equals("1")) {
							if (checkExisted(key)) {
								delPMRuleFromCache(key);
							}
							log.debug("<TuiSongRuleCacheManager>5-4");
						} else {
							addPMRuleIntoCache(key, pmrule);
							log.debug("<TuiSongRuleCacheManager>5-5");
						}	
						pmrule = null;
						key = null;
						log.debug("<TuiSongRuleCacheManager>5-6");
					}
					it = null;
					log.debug("<TuiSongRuleCacheManager>5-7");
				} else {
					Iterator it = list.iterator();
					while (it.hasNext()) {
						log.debug("<TuiSongRuleCacheManager>5-8");
						pmrule =  (PMRule) it.next();
						String key = pmrule.getUser_id() + pmrule.getPm_rule_id();
						log.debug("<TuiSongRuleCacheManager>5-9:"+key);
						addPMRuleIntoCache(key, pmrule);
						pmrule = null;
						key = null;
						log.debug("<TuiSongRuleCacheManager>5-10");
					}
					it = null;
					log.debug("<TuiSongRuleCacheManager>5-11");
				}
				log.info(NAME+"数据库中共有" + pmRuleMap.size() + "个推送规则信息");
	
				lastIncremSyncDate = new Date();
				log.debug("<TuiSongRuleCacheManager>6");
				log.debug(NAME+"初始化推送规则的时间为：" + lastIncremSyncDate);
				lastIncremSyncDate = null;
				list.clear();
				list = null;
				log.info(NAME+"推送规则缓存加载完毕。");
				log.debug(NAME+"推送规则缓存大小："+pmRuleMap.size());
				log.debug("<TuiSongRuleCacheManager>7");
			}
		}else{
			log.debug("<TuiSongRuleCacheManager>8");
		}
		Constant.pmRuleload = true;
		log.info("<TuiSongRuleCacheManager>pmrule:"+Constant.pmRuleload);
		log.debug("<TuiSongRuleCacheManager>9");
	}

	/**
	 * 查看某推送规则在缓存中是否存在
	 * 
	 * @param appid
	 * @return
	 */
	public boolean checkExisted(String key) {
		return pmRuleMap.containsKey(key);
	}

	/**
	 * 将推送规则加入缓存
	 * 
	 * @param cidList
	 */
	public synchronized void addPMRuleIntoCache(String key,PMRule pmRule) {
		pmRuleMap.put(key, pmRule);
		log.debug(NAME+"已将推送规则"+key+"加入缓存！");
	}
	
	public PMRule getValue(String key){
		return pmRuleMap.get(key);
	}
	
	public Collection<PMRule> getValues(){
		return pmRuleMap.values();
	}
	
	/**
	 * 从缓存中删除一个推送规则
	 * 
	 * @param cidList
	 */
	public synchronized void delPMRuleFromCache(String key) {
		log.debug(NAME+"开始从缓存中删除推送规则"+(key));
		pmRuleMap.remove(key);
		log.debug(NAME+"从缓存中删除推送规则"+(key)+"完毕！");
	}


	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public void setPmRuleDAO(IPMRuleDAO pmRuleDAO) {
		this.pmRuleDAO = pmRuleDAO;
	}
	
	public synchronized PMRule SyncPMRuleValue(String type, String key,
			PMRule str) {
		if (type.equals(Constant.OFF)) {
			delPMRuleFromCache(key);
			addPMRuleIntoCache(key, str);
			return null;
		} else if (type.equals(Constant.ON)) {
			return pmRuleMap.get(key);
		} else {
			log.error(NAME + "SyncPMRuleValue传入的类型错误");
			return null;
		}
	}

	public void delPMRuleFromCache(List<String> list) {
		for(String pm:list){
			pmRuleMap.remove(pm);
		}
	}
}
