package com.yutong.clw.quartz.managers.cachemanager;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.beans.cl.TerminalBean;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.ITerminalDAO;
import com.yutong.clw.utils.StringUtil;

public class TerminalCacheManager {

	private Logger log = LoggerFactory.getLogger(TerminalCacheManager.class);

	private final static String NAME = "<TerminalCacheManager>";

	private static final TerminalCacheManager terminalCacheManager = new TerminalCacheManager();

	private ITerminalDAO terminalDAO;

	// private List<TerminalBean> terminalList;

	private Date lastIncremSyncDate;

	// 计时器，增减量加载2分钟执行一次，2小时执行一次全量加载，于是 count为指定值(数据库中可配置)的时候就进行一次全量加载
	// private static int count = 0;
	// 如果每2小时一次执行的全量加载没有加载完成，job不进行启动
	// private static boolean bool = true;

	// public static Map<String, TerminalBean> terminalMap;

	private TerminalCacheManager() {
		// terminalList = new ArrayList<TerminalBean>();
		// terminalMap = new HashMap<String, TerminalBean>();
	}

	public static TerminalCacheManager getInstance() {
		return terminalCacheManager;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[terminalInit]");
		if (Constant.terminalload) {
			Constant.terminalload = false;
			String systime = terminalDAO.getSysTime();
			if(Constant.upd_terminal_cache_time == null){
				Constant.upd_terminal_cache_time = systime;
			}
			List<TerminalBean> terminalList = null;
			TerminalBean tb = null;
			String vin = null;
			log.info(NAME + "开始加载终端增减量缓存。。");
			/* 从数据库中查询出终端基本信息，并保存入缓存中 */
			terminalList = terminalDAO.getBaseTerminalInfo();
			Constant.upd_terminal_cache_time = systime;
			if (Constant.terminalMap != null && Constant.terminalMap.size() > 0) {
				Iterator it = terminalList.iterator();
				while (it.hasNext()) {
					tb = (TerminalBean) it.next();
					vin = tb.getVehicle_vin();
					if (vin.indexOf(" ") == -1) {
						if (tb.getValid_flag().equals("1")) {
							if (checkExisted(vin)) {
								delTerminalFromCache(vin);
							}
						} else {
							addTerminalIntoCache(vin,tb);
						}
					} else {
						log.info(NAME + "," + vin+ "非法，不加载到终端信息缓存中");
					}
					tb = null;
					vin = null;
				}
				it = null;
			} else {
				Iterator it = terminalList.iterator();
				while (it.hasNext()) {
					tb = (TerminalBean) it.next();
					vin = tb.getVehicle_vin();
					if (vin.indexOf(" ") == -1) {
						addTerminalIntoCache(vin,tb);
					} else {
						log.info(NAME + "," + vin + "非法，不加载到终端信息缓存中");
					}
					tb = null;
					vin = null;
				}
				it = null;
			}
			log.info(NAME + "本次启动共加载" + terminalList.size() + "个终端基本增减量信息");
			terminalList.clear();
			
			lastIncremSyncDate = new Date();

			log.debug(NAME + "加载终端基本信息的时间为：" + lastIncremSyncDate);
			log.debug(NAME + "终端信息加载完毕。");
			log.info(NAME + "当前终端信息缓存大小：" + Constant.terminalMap.size());
		}
		Constant.terminalload = true;
	}

	/**
	 * 查看某终端信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String vin) {
		return Constant.terminalMap.containsKey(vin);
	}

	/**
	 * 将某个终端基本信息加入缓存
	 * 
	 * @param vin
	 *            ,terminalBean
	 */
	public synchronized void addTerminalIntoCache(String key,
			TerminalBean terminalBean) {
		Constant.terminalMap.put(key, terminalBean);
		log.debug(NAME + "已将" + key + "的记录加入缓存！");
	}

	/**
	 * 从缓存中删除某个终端基本信息
	 * 
	 * @param vin
	 */
	public synchronized void delTerminalFromCache(String vin) {
		log.debug(NAME + "开始从缓存中删除" + vin + "的缓存记录。");
		Constant.terminalMap.remove(vin);
		log.debug(NAME + "从缓存中删除" + vin + "的缓存记录完毕！");
	}

	/**
	 * 从缓存中删除一批终端基本信息
	 * 
	 * @param corpid
	 */
	public synchronized void delTerminalsFromCache(List<String> cidList) {
		log.debug(NAME + "开始从缓存中删除" + cidList.size() + "个终端基本信息。");
		for (String str : cidList) {
			Constant.terminalMap.remove(str);
		}
		log.debug(NAME + "从缓存中删除" + cidList.size() + "个终端基本信息完毕！");
	}

	public synchronized TerminalBean SyncTerminalValue(String type, String key,
			TerminalBean str) {
		if (type.equals(Constant.OFF)) {
			delTerminalFromCache(key);
			addTerminalIntoCache(key, str);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.terminalMap.get(key);
		} else {
			log.error(NAME + "SyncStudentIdValue传入的类型错误");
			return null;
		}
	}

	public TerminalBean getValue(String key) {
		if (Constant.terminalload) {
			return SyncTerminalValue(Constant.ON, key, null);
		} else {
			return Constant.terminalMap.get(key);
		}
	}

	public Collection<TerminalBean> getValues() {
		return Constant.terminalMap.values();
	}

	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public void setTerminalDAO(ITerminalDAO terminalDAO) {
		this.terminalDAO = terminalDAO;
	}
}
