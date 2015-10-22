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

import com.yutong.clw.beans.cl.Vehicle_RegionBean;
import com.yutong.clw.dao.ITerminalDAO;
import com.yutong.clw.dao.IVehicle_RegionDAO;
import com.yutong.clw.utils.StringUtil;

public class Vehicle_RegionCacheManager {

	private Logger log = LoggerFactory.getLogger(Vehicle_RegionCacheManager.class);
	
	private final static String NAME = "<Vehicle_RegionCacheManager>";
	
	private static final Vehicle_RegionCacheManager vehicle_regionCacheManager = new Vehicle_RegionCacheManager();

	private IVehicle_RegionDAO vehicle_regionDAO;

	@SuppressWarnings("unused")
	private ITerminalDAO terminalDAO;

	private List<Vehicle_RegionBean> vehicle_regionList;

	private Date lastIncremSyncDate;

	private static Map<String, Vehicle_RegionBean> vehicle_regionMap;

//	// 计时器，增减量加载2分钟执行一次，2小时执行一次全量加载，于是 count为指定值(数据库中可配置)的时候就进行一次全量加载
//	private static int count = 0;
//	// 如果每2小时一次执行的全量加载没有加载完成，job不进行启动
//	private static boolean bool = true;

	private Vehicle_RegionCacheManager() {
		vehicle_regionList = new ArrayList<Vehicle_RegionBean>();
		vehicle_regionMap = new HashMap<String, Vehicle_RegionBean>();
	}

	public static Vehicle_RegionCacheManager getInstance() {
		return vehicle_regionCacheManager;
	}

	public void init() {
//		if (bool == true) {
//			String systime = terminalDAO.getSysTime();
			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
			MDC.put("modulename", "[vehicle_regionInit]");
//			if (Constant.isfirstvehicle_regionload.equals("true")) {
				log.debug(NAME+"开始加载车辆区域全量缓存。。");
				/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
				vehicle_regionList = vehicle_regionDAO.getVRList();
				for (Vehicle_RegionBean vehicle_regionBean : vehicle_regionList) {
					if(vehicle_regionBean.getVehicle_vin().indexOf(" ")==-1){
					   addVehicle_RegionIntoCache(vehicle_regionBean);
					}else{
						log.info(NAME+","+vehicle_regionBean.getVehicle_vin()+"非法，不加载到车辆区域缓存中");
					}
				}
				log.info(NAME+"本次启动共加载" + vehicle_regionMap.size() + "个车辆区域基本信息");
//				Constant.isfirstvehicle_regionload = "false";
//			} else {
//				if (count == Integer.parseInt(Config.props
//						.getProperty("allvehicle_regionCacheTime"))) {
//					bool = false;
//					int find = 0;
//					log.debug(NAME+"开始加载车辆区域全量缓存。。");
//					vehicle_regionList = vehicle_regionDAO.getVRList();
//					if (vehicle_regionList != null && vehicle_regionList.size() > 0) {
//						Iterator it = vehicle_regionList.iterator();
//						while (it.hasNext()) {
//							Vehicle_RegionBean vehicle_regionBean = (Vehicle_RegionBean) it.next();
//							if(vehicle_regionBean.getVehicle_vin().indexOf(" ")==-1){
//								addVehicle_RegionIntoCache(vehicle_regionBean);
//							}else{
//								log.info(NAME+","+vehicle_regionBean.getVehicle_vin()+"非法，不加载到车辆区域缓存中");
//							}
//						}
//					}
//					if (vehicle_regionMap != null && vehicle_regionMap.size() > 0) {
//						Set set = vehicle_regionMap.keySet();
//						Iterator itm = set.iterator();
//						List list = new ArrayList();
//						while (itm.hasNext()) {
//							String key = (String) itm.next();
//							for (int i = 0; i < vehicle_regionList.size(); i++) {
//								if(key.indexOf(" ")!=-1){
//									continue;
//								}
//								if (!key.equals(vehicle_regionList.get(i).getVehicle_vin()+vehicle_regionList.get(i).getRegion_id())) {
//									find = 0;
//								} else {
//									find = 1;
//									break;
//								}
//							}
//							if (find == 0) {
//								list.add(key);
//							}
//						}
//						if (list != null && list.size() > 0) {
//							delVehicle_RegionsFromCache(list);
//						}
//					}
//					log.info(NAME+"本次启动共加载" + vehicle_regionMap.size() + "个车辆区域基本信息");
//					// 2小时执行一次全量加载后，将计时器清0
//					count = 0;
//					bool = true;
//				} else {
//					count++;
//					log.debug(NAME+"开始加载车辆区域增减量缓存。。");
//					/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
//					vehicle_regionList = vehicle_regionDAO
//							.getAllVRList(Constant.upd_vr_cache_time);
//					if (vehicle_regionList == null
//							|| vehicle_regionList.size() <= 0) {
//						// log.info("从数据库未查出相关车辆区域数据。。");
//						log.debug(NAME+"加载车辆区域缓存成功，但未有相关数据。。");
//						return;
//					}
//					if (vehicle_regionMap != null
//							&& vehicle_regionMap.size() > 0) {
//						for (Vehicle_RegionBean vehicle_regionBean : vehicle_regionList) {
//							if(vehicle_regionBean.getVehicle_vin().indexOf(" ")==-1){
//								if (vehicle_regionBean.getValid_flag().equals("1")
//										|| vehicle_regionBean.getGroup_valid_flag()
//												.equals("1")
//										|| vehicle_regionBean.getVehicle_relate()
//												.equals("0")) {
//									if (checkExisted(vehicle_regionBean
//											.getVehicle_vin(), vehicle_regionBean
//											.getRegion_id())) {
//										delVehicle_RegionFromCache(
//												vehicle_regionBean.getVehicle_vin(),
//												vehicle_regionBean.getRegion_id());
//									}
//								} else {
//									addVehicle_RegionIntoCache(vehicle_regionBean);
//								}
//							}else{
//								log.info(NAME+","+vehicle_regionBean.getVehicle_vin()+"非法，不加载到车辆区域缓存中");
//							}
//						}
//					} else {
//						for (Vehicle_RegionBean vehicle_regionBean : vehicle_regionList) {
//							if(vehicle_regionBean.getVehicle_vin().indexOf(" ")==-1){
//								addVehicle_RegionIntoCache(vehicle_regionBean);
//							}else{
//								log.info(NAME+","+vehicle_regionBean.getVehicle_vin()+"非法，不加载到车辆区域缓存中");
//							}
//						}
//					}
//				}
//				log.info(NAME+"本次启动共加载" + vehicle_regionList.size() + "个车辆区域基本信息");
//			}

			lastIncremSyncDate = new Date();

//			Constant.upd_vr_cache_time = systime;

			log.debug(NAME+"加载车辆区域基本信息的时间为：" + lastIncremSyncDate);

			log.debug(NAME+"车辆区域信息加载完毕。");
//		}
		log.info(NAME+"当前车辆区域信息缓存大小："+vehicle_regionMap.size());
	}

	@SuppressWarnings("unused")
	private void delVehicle_RegionsFromCache(List<String> cidList) {
		log.debug(NAME+"开始从缓存中删除" + cidList.size() + "个车辆区域基本信息。");
		for (String str : cidList) {
			vehicle_regionMap.remove(str);
		}
		log.debug(NAME+"从缓存中删除" + cidList.size() + "个车辆区域基本信息完毕！");
	}

	/**
	 * 查看某车辆区域信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String vin, String region_id) {
		return vehicle_regionMap.containsKey(vin + region_id);
	}

	/**
	 * 将某个车辆区域基本信息加入缓存
	 * 
	 * @param vin
	 *            ,terminalBean
	 */
	public void addVehicle_RegionIntoCache(
			Vehicle_RegionBean vehicle_regionBean) {
		vehicle_regionMap.put(vehicle_regionBean.getVehicle_vin()
				+ vehicle_regionBean.getRegion_id(), vehicle_regionBean);
		log.debug(NAME+"已将"
				+ (vehicle_regionBean.getVehicle_vin() + vehicle_regionBean
						.getRegion_id()) + "的记录加入缓存！");
	}

	/**
	 * 从缓存中删除某个车辆区域基本信息
	 * 
	 * @param vin
	 */
	public void delVehicle_RegionFromCache(String vin,
			String region_id) {
		log.debug(NAME+"开始从缓存中删除" + (vin + region_id) + "的缓存记录。");
		vehicle_regionMap.remove(vin + region_id);
		log.debug(NAME+"从缓存中删除" + (vin + region_id) + "的缓存记录完毕！");
	}

	public Vehicle_RegionBean getValue(String key) {
		return vehicle_regionMap.get(key);
	}

	public Collection<Vehicle_RegionBean> getValues() {
		return vehicle_regionMap.values();
	}

	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public void setVehicle_regionDAO(IVehicle_RegionDAO vehicle_regionDAO) {
		this.vehicle_regionDAO = vehicle_regionDAO;
	}

	public void setTerminalDAO(ITerminalDAO terminalDAO) {
		this.terminalDAO = terminalDAO;
	}
}
