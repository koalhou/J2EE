package com.neusoft.clw.vncs.cachemanager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.dao.impl.DriverVehicleDAO;
import com.neusoft.clw.vncs.domain.DriverVehicleBean;
import com.neusoft.clw.vncs.util.StringUtil;

public class DriverVehicleCacheManager {

	private Logger log = LoggerFactory.getLogger(DriverVehicleCacheManager.class);
	
	private final static String NAME = "<DriverVehicleCacheManager>";
	
	private static final DriverVehicleCacheManager driverVehicleCacheManager = new DriverVehicleCacheManager();
	
	private DriverVehicleDAO driverVehicleDAO;

	private List<DriverVehicleBean> driverVehicleList;

	private Date lastIncremSyncDate;

	private static Map<String, DriverVehicleBean> driverVehicleMap;

//	// 计时器，增减量加载2分钟执行一次，2小时执行一次全量加载，于是 count为指定值(数据库中可配置)的时候就进行一次全量加载
//	private static int count = 0;
//	// 如果每2小时一次执行的全量加载没有加载完成，job不进行启动
//	private static boolean bool = true;

	private DriverVehicleCacheManager() {
		driverVehicleList = new ArrayList<DriverVehicleBean>();
		driverVehicleMap = new HashMap<String, DriverVehicleBean>();
	}

	public static DriverVehicleCacheManager getInstance() {
		return driverVehicleCacheManager;
	}

	public void init() {
			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
			MDC.put("modulename", "[vehicle_regionInit]");
			log.debug(NAME+"开始加载车辆司机关系全量缓存。。");
			/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
			driverVehicleList = driverVehicleDAO.getDriverVehicle();
			for (DriverVehicleBean driverVehicleBean : driverVehicleList) {
				if(driverVehicleBean.getVehicle_vin().indexOf(" ")==-1){
				   adddriverVehicleIntoCache(driverVehicleBean);
				}else{
					log.info(NAME+","+driverVehicleBean.getVehicle_vin()+"非法，不加载到车辆司机关系缓存中");
				}
			}
			log.info(NAME+"本次启动共加载" + driverVehicleMap.size() + "个车辆司机关系基本信息");
//				

			lastIncremSyncDate = new Date();

//			Constant.upd_vr_cache_time = systime;

			log.debug(NAME+"加载车辆司机关系基本信息的时间为：" + lastIncremSyncDate);

			log.debug(NAME+"车辆司机关系信息加载完毕。");
//		}
		log.info(NAME+"当前车辆司机关系信息缓存大小："+driverVehicleMap.size());
	}

	@SuppressWarnings("unused")
	private void delVehicle_RegionsFromCache(List<String> cidList) {
		log.debug(NAME+"开始从缓存中删除" + cidList.size() + "个车辆区域基本信息。");
		for (String str : cidList) {
			driverVehicleMap.remove(str);
		}
		log.debug(NAME+"从缓存中删除" + cidList.size() + "个车辆区域基本信息完毕！");
	}

	/**
	 * 查看某车辆区域信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String vin, String driver_id) {
		return driverVehicleMap.containsKey(vin + driver_id);
	}

	/**
	 * 将某个车辆区域基本信息加入缓存
	 * 
	 * @param vin
	 *            ,terminalBean
	 */
	public void adddriverVehicleIntoCache(
			DriverVehicleBean driverVehicleBean) {
		driverVehicleMap.put(driverVehicleBean.getVehicle_vin()
				+ Constant.DRIVER, driverVehicleBean);
		log.debug(NAME+"已将"
				+ (driverVehicleBean.getVehicle_vin() + Constant.DRIVER) + "的记录加入缓存！");
	}

	/**
	 * 从缓存中删除某个车辆区域基本信息
	 * 
	 * @param vin
	 */
	public void delVehicle_RegionFromCache(String vin,
			String driver_id) {
		log.debug(NAME+"开始从缓存中删除" + (vin + driver_id) + "的缓存记录。");
		driverVehicleMap.remove(vin + driver_id);
		log.debug(NAME+"从缓存中删除" + (vin + driver_id) + "的缓存记录完毕！");
	}

	public DriverVehicleBean getValue(String key) {
		return driverVehicleMap.get(key);
	}

	public Collection<DriverVehicleBean> getValues() {
		return driverVehicleMap.values();
	}

	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public DriverVehicleDAO getDriverVehicleDAO() {
		return driverVehicleDAO;
	}

	public void setDriverVehicleDAO(DriverVehicleDAO driverVehicleDAO) {
		this.driverVehicleDAO = driverVehicleDAO;
	}
	
}
