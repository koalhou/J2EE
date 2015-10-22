package com.neusoft.clw.vncs.cachemanager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.dao.impl.RidingPlanDAO;
import com.neusoft.clw.vncs.domain.RidingPlanBean;
import com.neusoft.clw.vncs.util.StringUtil;

public class RidingPlanCacheManager {
	private Logger log = LoggerFactory.getLogger(RidingPlanCacheManager.class);
	private final static String NAME = "<RidingPlanCacheManager>";
	private static final RidingPlanCacheManager ridingPlanCacheManager = new RidingPlanCacheManager();
	private RidingPlanDAO ridingPlanDAO;
	private Date lastIncremSyncDate;
	private static Map<String, List<RidingPlanBean>> ridingPlanMap;

	private RidingPlanCacheManager() {
		ridingPlanMap = new HashMap<String, List<RidingPlanBean>>();
	}
	public static RidingPlanCacheManager getInstance() {
		return ridingPlanCacheManager;
	}
	public RidingPlanDAO getRidingPlanDAO() {
		return ridingPlanDAO;
	}
	public void setRidingPlanDAO(RidingPlanDAO ridingPlanDAO) {
		this.ridingPlanDAO = ridingPlanDAO;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[ridingPlanInit]");
		log.info(NAME + "开始加载员工乘车规划全量缓存。。");
		List<RidingPlanBean> ridingPlanBeanList = ridingPlanDAO.getBaseRidingPlanInfo();
		if (Constant.isfirstridingplanload){//第一次加载
			addRidingPlanIntoCache("taRidingPlan",ridingPlanBeanList);
			Constant.isfirstridingplanload =false;
		}else{
			delRidingPlanFromCache("taRidingPlan");
			addRidingPlanIntoCache("taRidingPlan",ridingPlanBeanList);
		}
		log.info(NAME + "本次启动共加载" + ridingPlanBeanList.size() + "个员工乘车规划基本增减量信息");
		lastIncremSyncDate = new Date();
		log.debug(NAME + "加载员工乘车规划基本信息的时间为：" + lastIncremSyncDate);
		lastIncremSyncDate = null;
		ridingPlanBeanList.clear();
		ridingPlanBeanList = null;
		log.debug(NAME + "员工乘车规划信息加载完毕。");
		log.info(NAME + "当前员工乘车规划信息缓存大小：" + RidingPlanCacheManager.getInstance().getValue("taRidingPlan").size());
	}

	/**
	 * 查看某员工乘车规划信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String stuCardId) {
		return ridingPlanMap.containsKey(stuCardId);
	}

	/**
	 * 将某个员工乘车规划基本信息加入缓存
	 * 
	 * @param vin
	 *            ,ridingPlanBean
	 */
	public synchronized void addRidingPlanIntoCache(String key,List<RidingPlanBean> list) {
		if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState()){
            Constant.getMemcachedClient().insert(key, list);
        }
		ridingPlanMap.put(key, list);
		log.debug(NAME + "已将" + key + "的记录加入缓存！");
	}

	/**
	 * 从缓存中删除某个员工乘车规划基本信息
	 * 
	 * @param vin
	 */
	public synchronized void delRidingPlanFromCache(String key) {
		log.debug(NAME + "开始从缓存中删除" + key + "的缓存记录。");
		if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState())
            Constant.getMemcachedClient().delete(key);
	    ridingPlanMap.remove(key);
		log.debug(NAME + "从缓存中删除" + key + "的缓存记录完毕！");
	}

	public List<RidingPlanBean> getValue(String key) {
	  if (Constant.isstartMemcache.equals("1") && Constant.getMemcachedClient().connectState()){
            Object o = Constant.getMemcachedClient().getObject(key);
            if (o != null && !o.equals("")){
                return (List<RidingPlanBean>) o;
            }else{
                return null;
            }
        } else{
            return ridingPlanMap.get(key);
        }
	}

}
