package com.yutong.clw.quartz.managers.cachemanager;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.yutong.clw.beans.cl.VehicleBean;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.ITerminalDAO;
import com.yutong.clw.dao.IVehicleDAO;
import com.yutong.clw.utils.StringUtil;

public class VehicleCacheManager {
	private Logger log = LoggerFactory.getLogger(VehicleCacheManager.class);

	private static final VehicleCacheManager vehicleCacheManager = new VehicleCacheManager();

	private static final String NAME = "<VehicleCacheManager>";

	private IVehicleDAO vehicleDAO;

	private ITerminalDAO terminalDAO;

	// private List<VehicleBean> vehicleList;

	private Date lastIncremSyncDate;

//	public static Map<String, VehicleBean> vehicleMap;

//	// 计时器，增减量加载2分钟执行一次，2小时执行一次全量加载，于是 count为指定值(数据库中可配置)的时候就进行一次全量加载
//	private static int count = 0;
//	// 如果每2小时一次执行的全量加载没有加载完成，job不进行启动
//	private static boolean bool = true;

	private VehicleCacheManager() {
		// vehicleList = new ArrayList<VehicleBean>();
//		vehicleMap = new HashMap<String, VehicleBean>();
	}

	public static VehicleCacheManager getInstance() {
		return vehicleCacheManager;
	}

	@SuppressWarnings("unchecked")
	public void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[vehicleInit]");
		if (Constant.vehicleload) {
			Constant.vehicleload = false;
			String systime = terminalDAO.getSysTime();
			if(Constant.upd_vehicle_cache_time ==null){
				Constant.upd_vehicle_cache_time = systime;
			}
			List<VehicleBean> vehicleList;
			VehicleBean vb = null;
			String vin = null;
			log.info(NAME + "开始加载车辆增减量缓存。。");
			/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
			vehicleList = vehicleDAO.getAllVehicleParam();
			Constant.upd_vehicle_cache_time = systime;
			if (Constant.vehicleMap != null && Constant.vehicleMap.size() > 0) {
				Iterator it = vehicleList.iterator();
				while (it.hasNext()) {
					vb = (VehicleBean) it.next();
					vin = vb.getVehicle_vin();
					if (vin.indexOf(" ") == -1) {
						if (vb.getValid_flag().equals("1")) {
							if (checkExisted(vin)) {
								delVehicleFromCache(vin);
							}
						} else {
							addVehicleIntoCache(vin,vb);
						}
					} else {
						log.info(NAME + "," + vin + "非法，不加载到车辆缓存中");
					}
				}
				it = null;
			} else {
				Iterator it = vehicleList.iterator();
				while (it.hasNext()) {
					vb = (VehicleBean) it.next();
					vin = vb.getVehicle_vin();
					if (vin.indexOf(" ") == -1) {
						addVehicleIntoCache(vin,vb);
					} else {
						log.info(NAME + "," + vin + "非法，不加载到车辆缓存中");
					}
					vb = null;
					vin = null;
				}
				it = null;
			}
			vehicleList.clear();
			log.info(NAME + "本次启动共加载" + vehicleList.size() + "个车辆基本信息");

			lastIncremSyncDate = new Date();

			log.debug(NAME + "加载车辆基本信息的时间为：" + lastIncremSyncDate);

			log.debug(NAME + "车辆信息加载完毕。");
			
			log.info(NAME + "当前车辆信息信息缓存大小：" + Constant.vehicleMap.size());
			Constant.vehicleload = true;
		}
	}

	public void delVehiclesFromCache(List<String> cidList) {
		log.debug(NAME + "开始从缓存中删除" + cidList.size() + "个车辆基本信息。");
		for (String str : cidList) {
			Constant.vehicleMap.remove(str);
		}
		log.debug(NAME + "从缓存中删除" + cidList.size() + "个车辆基本信息完毕！");
	}

	/**
	 * 查看某车辆信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String vin) {
		return Constant.vehicleMap.containsKey(vin);
	}

	/**
	 * 将某个车辆基本信息加入缓存
	 * 
	 * @param vin
	 *            ,terminalBean
	 */
	public synchronized void addVehicleIntoCache(String key,
			VehicleBean vehicleBean) {
		Constant.vehicleMap.put(key, vehicleBean);
		log.debug(NAME + "已将" + (key) + "的记录加入缓存！");
	}

	/**
	 * 从缓存中删除某个车辆基本信息
	 * 
	 * @param vin
	 */
	public synchronized void delVehicleFromCache(String vin) {
		log.debug(NAME + "开始从缓存中删除" + (vin) + "的缓存记录。");
		Constant.vehicleMap.remove(vin);
		log.debug(NAME + "从缓存中删除" + (vin) + "的缓存记录完毕！");
	}

	public VehicleBean getValue(String key) {
		if (Constant.vehicleload) {
			return SyncVehicleValue(Constant.ON, key, null);
		} else {
			return Constant.vehicleMap.get(key);
		}
	}

	public Collection<VehicleBean> getValues() {
		return Constant.vehicleMap.values();
	}

	public synchronized VehicleBean SyncVehicleValue(String type, String key,
			VehicleBean str) {
		if (type.equals(Constant.OFF)) {
			delVehicleFromCache(key);
			addVehicleIntoCache(key, str);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.vehicleMap.get(key);
		} else {
			log.error(NAME + "SyncStudentIdValue传入的类型错误");
			return null;
		}
	}

	public Date getLastIncremSyncDate() {
		return lastIncremSyncDate;
	}

	public void setvehicleDAO(IVehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	public void setTerminalDAO(ITerminalDAO terminalDAO) {
		this.terminalDAO = terminalDAO;
	}
}
