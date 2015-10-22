package com.yutong.clw.quartz.managers.cachemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.beans.cl.HarmDefBean;
import com.yutong.clw.dao.IHarmDefDAO;
import com.yutong.clw.utils.StringUtil;

public class HarmDefCacheManager {
	
	private Logger log = LoggerFactory.getLogger(HarmDefCacheManager.class);

	private final static String NAME = "<HarmDefCacheManager>";
	
	private static final HarmDefCacheManager harmdefCacheManager = new HarmDefCacheManager();
	
	private IHarmDefDAO harmdefDAO;
	
	private List<HarmDefBean> harmdefList;

	private Date lastIncremSyncDate;

	public static Map<String, HarmDefBean> harmdefMap;
	
	private HarmDefCacheManager() {
		harmdefList = new ArrayList<HarmDefBean>();
		harmdefMap = new HashMap<String, HarmDefBean>();
	}

	public static HarmDefCacheManager getInstance() {
		return harmdefCacheManager;
	}

	
	public void init(){
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[harmDefInit]");
		
		log.debug("<HarmDefCacheManager>开始加载不良驾驶信息缓存。。");
		/* 从数据库中查询出终端基本信息，并保存入缓存中 */
		harmdefList = harmdefDAO.getHarmDefInfo();
		if(harmdefList==null||harmdefList.size()<=0){
			log.debug(NAME+"从数据库未查出相关不良驾驶数据。。");
			log.debug(NAME+"加载不良驾驶数据缓存成功，但未有相关数据。。");
			return;
		}
		for(HarmDefBean harmdefBean:harmdefList){
			if(harmdefBean.getVin().indexOf(" ") == -1){
				addHarmDefIntoCache(harmdefBean);
			}else{
				log.info(NAME+","+harmdefBean.getVin()+"非法,不加载到不良驾驶缓存中！");
			}
//			log.info("####EGEAR_SPD:"+harmdefBean.getEgear_spd()+",EGEAR_RATIO:"+harmdefBean.getEgear_ratio()+",GEAR2_SPD:"+harmdefBean.getGear2_spd()
//					+"####Gear0_spd_l:"+harmdefBean.getGear0_spd_l()+",Gear0_spd_u:"+harmdefBean.getGear0_spd_u()+",Gear1_spd_l:"+harmdefBean.getGear1_spd_l()+",Gear1_spd_u:"+harmdefBean.getGear1_spd_u()
//					+"####Gear2_spd_l:"+harmdefBean.getGear2_spd_l()+",Gear2_spd_u:"+harmdefBean.getGear2_spd_u()+",Gear3_spd_l:"+harmdefBean.getGear3_spd_l()+",Gear3_spd_u:"+harmdefBean.getGear3_spd_u());
		}
		
		log.debug("********************harmdefMap:"+harmdefMap);
		
		log.info(NAME+"本次启动共加载" + harmdefMap.size() + "个不良驾驶信息");

		lastIncremSyncDate = new Date();

		log.debug(NAME+"加载不良驾驶信息的时间为：" + lastIncremSyncDate);

		log.debug(NAME+"不良驾驶信息加载完毕。");
	}
	
	/**
	 * 查看某不良驾驶信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String vin) {
		return harmdefMap.containsKey(vin);
	}

	/**
	 * 将某个不良驾驶信息加入缓存
	 * 
	 * @param vin,terminalBean
	 */
	public synchronized void addHarmDefIntoCache(HarmDefBean harmDefBean) {
		harmdefMap.put(harmDefBean.getVin(), harmDefBean);
		log.debug(NAME+"已将" + harmDefBean.getVin() + "的记录加入缓存！");
	}

	/**
	 * 从缓存中删除某个不良驾驶信息
	 * 
	 * @param vin
	 */
	public synchronized void delHarmDefFromCache(String vin) {
		log.debug(NAME+"开始从缓存中删除" + vin + "的缓存记录。");
		harmdefMap.remove(vin);
		log.debug(NAME+"从缓存中删除" + vin + "的缓存记录完毕！");
	}

	public HarmDefBean getValue(String key){
		return harmdefMap.get(key);
	}
	
	public Collection<HarmDefBean> getValues(){
		return harmdefMap.values();
	}
	
	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public void setHarmdefDAO(IHarmDefDAO harmdefDAO) {
		this.harmdefDAO = harmdefDAO;
	}
	
}
