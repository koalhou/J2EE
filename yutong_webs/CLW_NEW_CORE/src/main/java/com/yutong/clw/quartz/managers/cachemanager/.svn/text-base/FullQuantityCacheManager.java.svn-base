package com.yutong.clw.quartz.managers.cachemanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.beans.cl.TerminalBean;
import com.yutong.clw.beans.cl.VehicleBean;
import com.yutong.clw.beans.xc.EnterPriseBean;
import com.yutong.clw.beans.xc.RouteSiteBean;
import com.yutong.clw.beans.xc.XcSiteBean;
import com.yutong.clw.beans.xc.XcStuSmsBean;
import com.yutong.clw.beans.xc.XcStudentBean;
import com.yutong.clw.beans.xc.XcvsseBean;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.ITerminalDAO;
import com.yutong.clw.dao.IVehicleDAO;
import com.yutong.clw.dao.IXcSmsDAO;
import com.yutong.clw.quartz.managers.command.SendxcmsmCommandManager;
import com.yutong.clw.utils.XCUtil;

public class FullQuantityCacheManager {
	private Logger log = LoggerFactory
			.getLogger(FullQuantityCacheManager.class);

	private final static String NAME = "<FullQuantityCacheManager>";

	private static final FullQuantityCacheManager fullCacheManager = new FullQuantityCacheManager();

	private SendxcmsmCommandManager cache = SendxcmsmCommandManager.getInstance();
	
	private ITerminalDAO terminalDAO;
	
	private IVehicleDAO vehicleDAO;
	
	private IXcSmsDAO sendXcSmsDAO;

	private String systime;

	public static FullQuantityCacheManager getInstance() {
		return fullCacheManager;
	}

	public void setTerminalDAO(ITerminalDAO terminalDAO) {
		this.terminalDAO = terminalDAO;
	}

	public void setVehicleDAO(IVehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}
	
	public void setSendXcSmsDAO(IXcSmsDAO sendXcSmsDAO) {
		this.sendXcSmsDAO = sendXcSmsDAO;
	}

	public void init() throws InterruptedException {
		systime = terminalDAO.getSysTime();
		terminalCache();
		vehicleCache();
		siteCache();
		xcEnterpriseCache();
		//xcstuSmsCache();
		xcRouteSiteCache();
		//xcstudentCache();
		//xcVssCache();
		System.gc();
	}
	
	@SuppressWarnings("unchecked")
	private void xcstuSmsCache() throws InterruptedException {
		while(!Constant.stusmsload){
			log.info(NAME+"亿美短信配置信息增量缓存正在加载，等待1000毫秒");
			Thread.sleep(1000);
		}
		if (Constant.stusmsload) {
			Constant.stusmsload = false;
			log.info(NAME + "开始加载学生短信配置缓存。。");
			String key = "";
			List<XcStuSmsBean> xcsmsList = sendXcSmsDAO.getStuSMSParam();
			Constant.upd_stusms_cache_time = systime;
			List keylist = null;
			XcStuSmsBean vb = null;
			Iterator it = xcsmsList.iterator();
			int find = 0;
			if (Constant.xcStuSmsMap != null && Constant.xcStuSmsMap.size() > 0) {
				while (it.hasNext()) {
					vb = (XcStuSmsBean) it.next();
					if (key.equals("")) {
						key = vb.getStu_id() + vb.getEvent_type();
						keylist = new ArrayList();
						keylist.add(vb);
					} else {
						if ((vb.getStu_id() + vb.getEvent_type()).equals(key)) {
							keylist.add(vb);
						} else {
							cache.SyncSmsValue(Constant.OFF,key, keylist);
							key = vb.getStu_id() + vb.getEvent_type();
							keylist = new ArrayList();
							keylist.add(vb);
						}
					}
					if (!it.hasNext()) {
						cache.SyncSmsValue(Constant.OFF,key, keylist);
					}
				}
				Set<String> rsid = Constant.xcStuSmsMap.keySet();
				Iterator<String> its = rsid.iterator();
				List list = new ArrayList();
				while (its.hasNext()) {
					String rs_id = its.next();
					for (int i = 0; i < xcsmsList.size(); i++) {
						XcStuSmsBean xcb = xcsmsList.get(i);
						if (!rs_id
								.equals(xcb.getStu_id() + xcb.getEvent_type())) {
							find = 0;
						} else {
							find = 1;
							break;
						}
					}
					if (find == 0) {
						list.add(rs_id);
					}
				}
				if (list != null && list.size() > 0) {
					cache.delSmsListFromCache(list);
				}
				rsid = null;
			} else {
				while (it.hasNext()) {
					vb = (XcStuSmsBean) it.next();
					if (key.equals("")) {
						key = vb.getStu_id() + vb.getEvent_type();
						keylist = new ArrayList();
						keylist.add(vb);
					} else {

						if ((vb.getStu_id() + vb.getEvent_type()).equals(key)) {
							keylist.add(vb);
						} else {
							cache.addXcStuSmsIntoCache(key, keylist);
							key = vb.getStu_id() + vb.getEvent_type();
							keylist = new ArrayList();
							keylist.add(vb);
						}
					}
					if (!it.hasNext()) {
						cache.addXcStuSmsIntoCache(key, keylist);
					}
				}
			}
			it = null;
//			if(keylist!=null){
//				keylist.clear();
//			}
			xcsmsList.clear();
		}
		log.info(NAME + "学生短信配置信息加载完毕。");

		log.info(NAME + "当前学生短信配置信息缓存大小：" + Constant.xcStuSmsMap.size());
		Constant.stusmsload = true;
//		Set<String> rsid = Constant.xcStuSmsMap.keySet();
//		Iterator it = rsid.iterator();
//		while (it.hasNext()) {
//			String id = (String) it.next();
//			List<XcStuSmsBean> list = Constant.xcStuSmsMap.get(id);
//			for(XcStuSmsBean bean:list){
//				log.info(NAME+"stu_id:"+bean.getStu_id()+",event_type:"+bean.getEvent_type()+",cell_number:"+bean.getCell_number()
//						+",relative_type:"+bean.getRelative_type()+",relative_name:"+bean.getRelative_name()+",end_time:"+bean.getEnd_time()
//						+",parents_flag:"+bean.getParents_flag());
//			}
//		}
	}
	
	@SuppressWarnings("unchecked")
	private void xcRouteSiteCache() throws InterruptedException {
		while(!Constant.routesiteload){
			log.info(NAME+"线路站点信息增量缓存正在加载，等待1000毫秒");		
			Thread.sleep(1000);
		}
		Constant.routesiteload = false;
			log.info(NAME + "开始加载线路站点信息全量缓存。。");
			List<RouteSiteBean> xcroutesiteList = sendXcSmsDAO.getRouteSiteParam();
			StringBuffer buffer = new StringBuffer();
			if (Constant.xcroutesiteMap != null && Constant.xcroutesiteMap.size() > 0) {	
				Map<String,String> map = new HashMap<String, String>();
				for (RouteSiteBean rsb : xcroutesiteList) {
					buffer.append(rsb.getRoute_id());
					buffer.append(rsb.getSite_id());
					if (buffer.toString().indexOf(" ")==-1) {
						cache.SyncRouteSiteValue(Constant.OFF,buffer.toString(),rsb);
						map.put(buffer.toString(), "");
					} else {
						log.info(NAME + "," + buffer.toString()	+ "非法，不加载到线路站点关系缓存中");
					}
					buffer.delete(0, buffer.length());
				}
				Set<String> rsid = Constant.xcroutesiteMap.keySet();
				Iterator<String> its = rsid.iterator();
				List list = new ArrayList();
				while (its.hasNext()) {
					buffer.append(its.next());
					if (!map.containsKey(buffer.toString())) {
						list.add(buffer.toString());
					}
					buffer.delete(0, buffer.length());
				}
				if (list != null && list.size() > 0) {
					cache.delroutesiteListFromCache(list);
				}
				its = null;
			} else {
				for (RouteSiteBean rsb : xcroutesiteList) {
					buffer.append(rsb.getRoute_id());
					buffer.append(rsb.getSite_id());
					if (buffer.toString().indexOf(" ") == -1) {
						cache.addXcRouteSiteIntoCache(buffer.toString(),rsb);
					} else {
						log.info(NAME + "," +buffer.toString()
								+ "非法，不加载到线路站点关系缓存中");
					}
					buffer.delete(0, buffer.length());
				}
			}
		xcroutesiteList.clear();
		log.info(NAME + "线路站点关系信息加载完毕。");
		log.info(NAME + "当前线路站点关系缓存大小：" + Constant.xcroutesiteMap.size());
		Constant.routesiteload = true;
	}
	
	@SuppressWarnings("unchecked")
	private void xcVssCache() throws InterruptedException {
		while(!Constant.vssload){
			log.info(NAME+"站点订购信息增量缓存正在加载，等待1000毫秒");
			Thread.sleep(1000);
		}
		if (Constant.vssload) {
			Constant.vssload = false;
			log.info(NAME + "开始加载站点订购信息缓存。。");
			List<XcvsseBean> xcvssList = sendXcSmsDAO.getVssParam();
			if (Constant.xcvssMap != null && Constant.xcvssMap.size() > 0) {
				Map<String,String> map = new HashMap<String,String>();
				for (XcvsseBean xsb : xcvssList) {
					if (xsb.getStudent_id().indexOf(" ") == -1) {
						cache.SyncVssValue(Constant.OFF, XCUtil.xcVssKey(xsb), xsb);
						map.put(XCUtil.xcVssKey(xsb), "");
					} else {
						log.info(NAME + "," + xsb.getVehicle_vin()
								+ "非法，不加载到站点订购缓存中");
					}
					xsb = null;
				}
				Set<String> rsid = Constant.xcvssMap.keySet();
				Iterator<String> its = rsid.iterator();
				List list = new ArrayList();
				String rs_id = null;
				while (its.hasNext()) {
					rs_id = its.next();
					if(!map.containsKey(rs_id)){
						list.add(rs_id);
					}
					rs_id = null;
				}
				if (list != null && list.size() > 0) {
					cache.delvssListFromCache(list);
				}
				its = null;
				list.clear();
			} else {
				for (XcvsseBean xsb : xcvssList) {
					if (xsb.getStudent_id().indexOf(" ") == -1) {					
						cache.addVssIntoCache(XCUtil.xcVssKey(xsb), xsb);
					} else {
						log.info(NAME + "," + xsb.getVehicle_vin()
								+ "非法，不加载到站点订购缓存中");
					}
					xsb = null;
				}
			}
			xcvssList=null;
		}
		log.info(NAME + "线路站点订购信息加载完毕。");
		log.info(NAME + "当前站点订购信息缓存大小：" + Constant.xcvssMap.size());
		Constant.vssload = true;
//		Set<String> rsid = Constant.xcvssMap.keySet();
//		Iterator it = rsid.iterator();
//		while (it.hasNext()) {
//			String id = (String) it.next();
//			XcvsseBean bean = Constant.xcvssMap.get(id);
//			log.info(NAME+"xcvssMap：：：：：key:"+ id + ",VEHICLE_VIN:"+bean.getVehicle_vin()+",STUDENT_ID:"+bean.getStudent_id()
//					+",ROUTE_ID:"+bean.getRoute_id()+",SITE_ID:"+bean.getSite_id()+",VSS_STATE:"+bean.getVss_state()+",SITE_UPDOWN:"+bean.getSite_updown()
//					+",TRIP_ID:"+bean.getTrip_id());
//		}
	}

	private void xcEnterpriseCache() throws InterruptedException {
		while(!Constant.enterprise_smgatewayload){
			log.info(NAME+"企业模式信息增量缓存正在加载，等待1000毫秒");
			Thread.sleep(1000);
		}
		Constant.enterprise_smgatewayload = false;
		log.info(NAME + "开始加载企业模式信息全量缓存。。");
		List<EnterPriseBean> xcenterpriseList = sendXcSmsDAO.getEnterPriseParam();
		Constant.upd_moshi_cache_time = systime;
		StringBuffer buffer = new StringBuffer();
		if(Constant.isfirstmoshiload){
			if (xcenterpriseList != null && xcenterpriseList.size() > 0) {
				for (EnterPriseBean epb : xcenterpriseList) {
					buffer.append(epb.getEnterprise_id());
					if (buffer.toString().indexOf(" ") == -1) {
						buffer.append(Constant.ENTERPRISE);
						cache.addEnterpriseIntoCache(buffer.toString(),epb);
					} else {
						log.info(NAME + "," + buffer.toString()	+ "非法，不加载到企业模式缓存中");
					}
					buffer.delete(0, buffer.length());
					epb = null;
				}
			}
		}else{
			if (xcenterpriseList != null && xcenterpriseList.size() > 0) {
				Map<String, String> map = new HashMap<String, String>();
				for (EnterPriseBean epb : xcenterpriseList) {
					buffer.append(epb.getEnterprise_id());
					if (buffer.toString().indexOf(" ") == -1) {
						buffer.append(Constant.ENTERPRISE);
						cache.SyncEnterpriseValue(Constant.OFF,buffer.toString(),epb);
						map.put(buffer.toString(), "");
					} else {
						log.info(NAME + "," + buffer.toString()	+ "非法，不加载到企业模式缓存中");
					}
					buffer.delete(0, buffer.length());
					epb = null;
				}
				Set<String> key = Constant.xcenterpriseMap.keySet();
				Iterator<String> its = key.iterator();
				List<String> list = new ArrayList<String>();
				while (its.hasNext()) {
					buffer.append(its.next());
					if (!map.containsKey(buffer.toString())) {
						list.add(buffer.toString());
					}
					buffer.delete(0, buffer.length());
				}
				if (list != null && list.size() > 0) {
					cache.delEnterpriseListFromCache(list);
				}
				list.clear();
			}
		}
		xcenterpriseList.clear();
		
		log.info(NAME + "企业模式信息加载完毕。");
		log.info(NAME + "当前企业模式信息缓存大小：" + Constant.xcenterpriseMap.size());
		Constant.enterprise_smgatewayload = true;
	}

	private void siteCache() throws InterruptedException {
		while(!Constant.siteload){
			log.info(NAME+"站点信息增量缓存正在加载，等待1000毫秒");
			Thread.sleep(1000);
		}
		if (Constant.siteload) {
			Constant.siteload = false;
			log.info(NAME + "开始加载站点全量缓存。。");
			List<XcSiteBean> xcsiteList = sendXcSmsDAO.getSiteParam();
			Constant.upd_site_cache_time = systime;
			SendxcmsmCommandManager cache = SendxcmsmCommandManager.getInstance();
			StringBuffer buffer = new StringBuffer();
			if(Constant.isfirstsiteload){
				for (XcSiteBean vb : xcsiteList) {
					buffer.append(vb.getSite_id());
					if (buffer.toString().indexOf(" ") == -1) {
						buffer.delete(0, buffer.length());
						buffer.append(Constant.SITE);
						buffer.append(vb.getSite_id());
						cache.addXcSiteIntoCache(buffer.toString(),vb);
					} else {
						log.info(NAME + "," + buffer.toString() + "非法，不加载到站点缓存中");
					}
					buffer.delete(0, buffer.length());
					vb = null;
				}
			} else {
				Map<String, String> map = new HashMap<String, String>();
				for (XcSiteBean vb : xcsiteList) {
					buffer.append(vb.getSite_id());
					if (buffer.toString().indexOf(" ") == -1) {
						buffer.delete(0, buffer.length());
						buffer.append(Constant.SITE);
						buffer.append(vb.getSite_id());
						cache.SyncSiteValue(Constant.OFF, buffer.toString(), vb);
						map.put(buffer.toString(),"");
					} else {
						log.info(NAME + "," + buffer.toString() + "非法，不加载到站点缓存中");
					}
					buffer.delete(0, buffer.length());
					vb = null;
				}
				Set<String> site = Constant.xcsiteMap.keySet();
				Iterator<String> its = site.iterator();
				List<String> list = new ArrayList<String>();
				String key =null;
				while (its.hasNext()) {
					key = its.next();
					if (!map.containsKey(key)) {
						list.add(key);
					}
					key = null;
				}
				if (list != null && list.size() > 0) {
					cache.delSiteListFromCache(list);
				}
				list.clear();
				site = null;
			}
			xcsiteList.clear();
		}
		log.info(NAME + "站点信息加载完毕。");
		log.info(NAME + "当前站点信息缓存大小：" + Constant.xcsiteMap.size());
		Constant.siteload = true;
	}
	
	private void xcstudentCache() throws InterruptedException {
		while(!Constant.studentload) {
			log.info(NAME+"学生信息增量缓存正在加载，等待1000毫秒");
			Thread.sleep(1000);
		}
		Constant.studentload = false;
		List<XcStudentBean> xcstudentList = sendXcSmsDAO.getStudentParam();
		Constant.upd_student_cache_time = systime;
		SendxcmsmCommandManager cache = SendxcmsmCommandManager.getInstance();
		StringBuffer buffer = new StringBuffer();
		if (Constant.isfirststudentload.equals("true")) {
			log.info(NAME + "开始加载学生相关信息全量缓存。。");
			
			if (xcstudentList != null && xcstudentList.size() > 0) {
				for (XcStudentBean sb : xcstudentList) {
					cache.allstudentCache(sb,buffer);
					cache.allstudentidCache(sb,buffer);
					sb = null;
				}
			} else {
				log.info(NAME+"没有找到学生相关信息");
			}
			Constant.isfirststudentload = "false";
		}else{
			if (xcstudentList != null && xcstudentList.size() > 0) {
				Map<String, String> stuidmap = new HashMap<String, String>();
				Map<String, String> stucardidmap = new HashMap<String, String>();
//				StringBuffer buffer = new StringBuffer();
				for (XcStudentBean sb : xcstudentList) {
					cache.allstudentCache(sb,buffer,stuidmap);
					cache.allstudentidCache(sb,buffer,stucardidmap);
					sb = null;
				}
				if(stuidmap!=null&&stuidmap.size()>0){
					cache.allstudentCache1(stuidmap);
				}
				if(stucardidmap!=null&&stucardidmap.size()>0){
					cache.allstudentidCache1(stucardidmap);
				}
				stuidmap.clear();
				stucardidmap.clear();
			}else{
				log.info(NAME+"未获得相关学生信息!");
			}
		}
		xcstudentList.clear();
		log.info(NAME + "当前学生信息缓存大小：" + Constant.xcstudentMap.size());
		log.info(NAME + "学生信息加载完毕。");
		log.info(NAME + "当前学生编号信息缓存大小：" + Constant.xcstudentidMap.size());
		log.info(NAME + "学生编号信息加载完毕。");
		Constant.studentload = true;
	}

	@SuppressWarnings("unchecked")
	private void vehicleCache() throws InterruptedException {
		while(!Constant.vehicleload) {
			log.info(NAME+"车辆信息增量缓存正在加载，等待1000毫秒");
			Thread.sleep(1000);
		}
		Constant.vehicleload = false;
		log.info(NAME+"开始加载车辆缓存。。");
		List<VehicleBean> vehicleList =vehicleDAO.getVehicleParam();
		Constant.upd_vehicle_cache_time = systime;
		VehicleCacheManager cache = VehicleCacheManager.getInstance();
		VehicleBean vb = null;
		String vehicle_vin = null;
		if (Constant.isfirstvehicleload.equals("true")) {
			if (vehicleList != null && vehicleList.size() > 0) {
				Iterator it = vehicleList.iterator();
				while (it.hasNext()) {
					vb = (VehicleBean) it.next();
					vehicle_vin = vb.getVehicle_vin();
					if(vehicle_vin.indexOf(" ") == -1){
						cache.addVehicleIntoCache(vehicle_vin,vb);
					}else{
						log.info(NAME+","+vehicle_vin+"非法，不加载到车辆缓存中");
					}
				}
				it = null;
				Constant.isfirstvehicleload = "false";
			} 
		}else{
			if (vehicleList != null && vehicleList.size() > 0) {
				Map map = new HashMap<String, String>();
				Iterator it = vehicleList.iterator();
				while (it.hasNext()) {
					vb = (VehicleBean) it.next();
					vehicle_vin =vb.getVehicle_vin();
					cache.SyncVehicleValue(Constant.OFF, vehicle_vin, vb);
					map.put(vehicle_vin, "");
					vb = null;
					vehicle_vin = null;
				}
				it = null;
				
				
				if (Constant.vehicleMap != null && Constant.vehicleMap.size() > 0) {
					Set set = Constant.vehicleMap.keySet();
					Iterator itm = set.iterator();
					List list = new ArrayList();
					while (itm.hasNext()) {
						String vin = (String) itm.next();
						if(!map.containsKey(vin)){
							list.add(vin);
						}
					}
					if (list != null && list.size() > 0) {
						cache.delVehiclesFromCache(list);
					}
					list.clear();
					itm = null;
					set = null;
				}
			}
		}
		vehicleList.clear();
		log.info(NAME+"当前车辆基本信息" + Constant.vehicleMap.size());
		Constant.vehicleload = true;

	}

	@SuppressWarnings("unchecked")
	private void terminalCache() throws InterruptedException {
		while(!Constant.terminalload) {
			log.info(NAME+"终端信息增量缓存正在加载，等待1000毫秒");
			Thread.sleep(1000);
		}
		Constant.terminalload = false;
		List<TerminalBean> terminalList = terminalDAO.getAllBaseTerminalInfo();
		Constant.upd_terminal_cache_time = systime;
		TerminalBean tb = null;
		String vehicle_vin = null;
		TerminalCacheManager terminal = TerminalCacheManager.getInstance();
		if (Constant.isfirstvihicle_terminalload.equals("true")) {
			/* 从数据库中查询出终端基本信息，并保存入缓存中 */
			log.debug(NAME + "初次加载终端全量缓存。。");
			if (terminalList != null && terminalList.size() > 0) {
				Iterator it = terminalList.iterator();
				while (it.hasNext()) {
					tb = (TerminalBean) it.next();
					vehicle_vin = tb.getVehicle_vin();
					if (vehicle_vin.indexOf(" ") == -1) {
						terminal.addTerminalIntoCache(vehicle_vin, tb);
					} else {
						log.info(NAME + "," + vehicle_vin + "非法，不加载到终端信息缓存中");
					}
					tb = null;
					vehicle_vin = null;
				}
				it = null;
			}
		} else {
			log.debug(NAME + "开始加载终端全量缓存。。");
			Map map = new HashMap<String, String>();
			if (terminalList != null && terminalList.size() > 0) {
				Iterator it = terminalList.iterator();
				while (it.hasNext()) {
					tb = (TerminalBean) it.next();
					vehicle_vin = tb.getVehicle_vin();
					if (vehicle_vin.indexOf(" ") == -1) {
						terminal.SyncTerminalValue(Constant.OFF, vehicle_vin, tb);
						map.put(vehicle_vin, "");
					} else {
						log.info(NAME + "," + vehicle_vin + "非法，不加载到学生缓存中");
					}
					vehicle_vin = null;
				}
				it = null;
				
				if (Constant.terminalMap != null && Constant.terminalMap.size() > 0) {
					Set set = Constant.terminalMap.keySet();
					Iterator itm = set.iterator();
					List list = new ArrayList();
					String vin = null;
					while (itm.hasNext()) {
						vin = (String) itm.next();
						if (!map.containsKey(vin)) {
							list.add(vin);
						}
						vin = null;
					}
					if (list != null && list.size() > 0) {
						terminal.delTerminalsFromCache(list);
					}
					list.clear();
					itm = null;
				}
			}
		}
		log.info(NAME + "当前终端信息缓存大小：" + Constant.terminalMap.size());
		terminalList.clear();
		Constant.isfirstvihicle_terminalload = "false";
		Constant.terminalload = true;
	}
}
