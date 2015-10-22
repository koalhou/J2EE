package com.neusoft.SchoolBus.vncs.manage;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.SchoolBus.vncs.dao.IXcSmsDAO;
import com.neusoft.SchoolBus.vncs.domain.EnterPriseBean;
import com.neusoft.SchoolBus.vncs.domain.RouteSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcSiteBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.domain.XcvsseBean;
import com.neusoft.SchoolBus.vncs.service.util.XCUtil;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.util.StringUtil;

public class SendxcmsmCommandManager {
	private Logger log = LoggerFactory.getLogger(SendxcmsmCommandManager.class);

	private static final String NAME = "<SendSmsCommandManager>";

	private static final SendxcmsmCommandManager sendxcmsmCommandManager = new SendxcmsmCommandManager();
	private Date lastIncremSyncDate;
	// private String lastSyncDate;
	private IXcSmsDAO sendXcSmsDAO;

	// private List<XcStuSmsBean> xcsmsList;
	// private static Map<String, List<XcStuSmsBean>> xcStuSmsMap;

	// public static Map<String, List<XcStuSmsVTBean>> xcStuSmsVTMap;
	// public static Map<String, List<XcStuSmsVTBean>> xcStuSmsImageMap;

	// private List<XcStudentBean> xcstudentList;
	// private static Map<String, XcStudentBean> xcstudentMap;
	//
	// private static Map<String, String> xcstudentidMap;
	// private List<XcSiteBean> xcsiteList;

	// private static Map<String, XcSiteBean> xcsiteMap;
	// private List<RouteSiteBean> xcroutesiteList;
	// private static Map<String, RouteSiteBean> xcroutesiteMap;

	// private List<XcvsseBean> xcvssList;
	// private static Map<String, XcvsseBean> xcvssMap;
	// private static Map<String, XcvsseBean> xcvssvtMap;

	// private List<EnterPriseBean> xcenterpriseList;
	// private static Map<String, EnterPriseBean> xcenterpriseMap;

	// 计时器，增减量加载2分钟执行一次，2小时执行一次全量加载，于是 count为指定值(数据库中可配置)的时候就进行一次全量加载
	// private static int count = 0;
	// // 如果每2小时一次执行的全量加载没有加载完成，job不进行启动
	// private static boolean bool = true;
	// // private static boolean stuidbool = true;
	// private static boolean site = true;

	private String systime;

	public static SendxcmsmCommandManager getInstance() {
		return sendxcmsmCommandManager;

	}

	private SendxcmsmCommandManager() {
		// xcsmsList = new ArrayList<XcStuSmsBean>();
		// xcStuSmsMap = new HashMap<String, List<XcStuSmsBean>>();

		// xcsiteList = new ArrayList<XcSiteBean>();
		// xcsiteMap = new HashMap<String, XcSiteBean>();

		// xcstudentList = new ArrayList<XcStudentBean>();
		// xcstudentMap = new HashMap<String, XcStudentBean>();
		// xcstudentidMap = new HashMap<String, String>();

		// xcroutesiteList = new ArrayList<RouteSiteBean>();
		// xcroutesiteMap = new HashMap<String, RouteSiteBean>();

		// xcvssList = new ArrayList<XcvsseBean>();
		// xcvssMap = new HashMap<String, XcvsseBean>();
		// xcvssvtMap = new HashMap<String, XcvsseBean>();
		// xcenterpriseList = new ArrayList<EnterPriseBean>();
		// xcenterpriseMap = new HashMap<String, EnterPriseBean>();

		// xcStuSmsVTMap = new HashMap<String, List<XcStuSmsVTBean>>();
		// xcStuSmsImageMap = new HashMap<String, List<XcStuSmsVTBean>>();

	}

	public void init() {
		systime = sendXcSmsDAO.getSysTime();
		xcstuSmsCache();
		SiteCache();
		xcstudentCache();
		// xcstudentIdCache();
		xcRouteSiteCache();
		xcVssCache();
		xcEnterpriseCache();
		// xcVssCacheVT();
	}

	/**
	 * 缓存学生短信配置信息
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private void xcstuSmsCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcstuSmsCacheInit]");
		if (Constant.stusmsload) {
			Constant.stusmsload = false;
			log.info(NAME + "开始增量加载学生短信配置缓存。。");
			if (Constant.upd_stusms_cache_time == null) {
				Constant.upd_stusms_cache_time = systime;
			}
			String key = "";
			List<XcStuSmsBean> xcsmsList = sendXcSmsDAO
					.getStuSMSParam(Constant.upd_stusms_cache_time, systime);
			Constant.upd_stusms_cache_time = systime;
			List<XcStuSmsBean> tempList = null;
			List keylist = null;
			XcStuSmsBean vb = null;
			Iterator it = xcsmsList.iterator();
			while (it.hasNext()) {
				vb = (XcStuSmsBean) it.next();
				keylist = new ArrayList<XcStuSmsBean>();
				log.debug(NAME+"@@@@,stu_id="+vb.getStu_id()+",event_type="+vb.getEvent_type());
				tempList = sendXcSmsDAO.getStuSMSParamByKey(vb.getStu_id(), vb.getEvent_type());
				Iterator<XcStuSmsBean> its = tempList.iterator();
				while (its.hasNext()) {
					XcStuSmsBean xcStuSmsBean = (XcStuSmsBean) its.next();
					keylist.add(xcStuSmsBean);
//					log.info(NAME+"####,stu_id:"+xcStuSmsBean.getStu_id()+",event_type:"+xcStuSmsBean.getEvent_type()+",cell_number:"+xcStuSmsBean.getCell_number()
//							+",relative_type:"+xcStuSmsBean.getRelative_type()+",relative_name:"+xcStuSmsBean.getRelative_name()+",end_time:"+xcStuSmsBean.getEnd_time()
//							+",parents_flag:"+xcStuSmsBean.getParents_flag());
					xcStuSmsBean = null;
				}
				key = vb.getStu_id()+vb.getEvent_type();
				log.debug(NAME+"$$$$,key="+key);
				SyncSmsValue(Constant.OFF, key, keylist);
				key = null;
//				if (keylist != null) {
//					keylist.clear();
//				}
//				if(Constant.xcStuSmsMap.containsKey(vb.getStu_id() + vb.getEvent_type())){
//					if(key!=null&&!key.equals("")&&keylist!=null&&keylist.size()>=0){
//						addXcStuSmsIntoCache(key, keylist);
//						key = null;
//						keylist = null;
//					}
//					String key1  = vb.getStu_id() + vb.getEvent_type();
//					keylist = Constant.xcStuSmsMap.get(key1);
//					keylist.add(vb);
//					addXcStuSmsIntoCache(key1, keylist);
//					keylist = null;
//				}else{
//					if (key.equals("")) {
//						key = vb.getStu_id() + vb.getEvent_type();
//						keylist = new ArrayList();
//						keylist.add(vb);
//					} else {
//						if ((vb.getStu_id() + vb.getEvent_type()).equals(key)) {
//							keylist.add(vb);
//						} else {
//							addXcStuSmsIntoCache(key, keylist);
//							key = vb.getStu_id() + vb.getEvent_type();
//							keylist = new ArrayList();
//							keylist.add(vb);
//						}
//					}
//					if (!it.hasNext()) {
//						addXcStuSmsIntoCache(key, keylist);
//					}
//				}
			}
			it = null;
			log.info(NAME + "本次启动共加载" + xcsmsList.size() + "个学生短信配置信息");
////			if (keylist != null) {
////				keylist.clear();
////			}
			xcsmsList=null;
			log.info(NAME + "学生短信配置信息加载完毕。");
			log.info(NAME + "当前学生短信配置信息缓存大小：" + Constant.xcStuSmsMap.size());
		}
		Constant.stusmsload = true;
//		Set<String> rsid = Constant.xcStuSmsMap.keySet();
//		Iterator it = rsid.iterator();
//		while (it.hasNext()) {
//			String id = (String) it.next();
//			List<XcStuSmsBean> list = Constant.xcStuSmsMap.get(id);
//			for(XcStuSmsBean bean:list){
//				log.info(NAME+"xcStuSmsMap缓存内容：：：：：stu_id:"+bean.getStu_id()+",event_type:"+bean.getEvent_type()+",cell_number:"+bean.getCell_number()
//						+",relative_type:"+bean.getRelative_type()+",relative_name:"+bean.getRelative_name()+",end_time:"+bean.getEnd_time()
//						+",parents_flag:"+bean.getParents_flag());
//			}
//		}
	}

	public void delSmsListFromCache(List<String> list) {
		for (String str : list) {
			Constant.xcStuSmsMap.remove(str);
		}

	}

	/**
	 * 删除学生短信配置信息缓存V2.0
	 * 
	 * @param
	 * @return
	 */
	// @SuppressWarnings("unused")
	// private void delSmsListFromCacheVT(List<String> list) {
	// for (String str : list) {
	// xcStuSmsVTMap.remove(str);
	// }
	//
	// }

	/**
	 * 缓存学生短信配置信息V2.0
	 * 
	 * @param
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	// public void xcstuSmsCacheVersionTwo() {
	// String key = "";
	// List<XcStuSmsVTBean> xcsmsVTList;
	// MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
	// MDC.put("modulename", "[xcstuSmsVTCacheInit]");
	//
	// List keylist = null;
	//
	// int eventType = 0;
	// if (Constant.sms) {
	// xcsmsVTList = sendXcSmsDAO.getStuSMSParamVT();
	// log.debug(NAME + "开始加载V2.0学生短信配置全量缓存。。");
	// Iterator it = xcsmsVTList.iterator();
	// // 全量
	// while (it.hasNext()) {
	// XcStuSmsVTBean vb = (XcStuSmsVTBean) it.next();
	// for (int i = 0; i < 7; i++) {// 循环事件类型
	// eventType = Integer.parseInt(vb.getEvent_type().substring(
	// i, i + 1));// 得到类型
	// if (eventType == 1) {// 如果类型为1，进行添加缓存操作
	// vb.setEt(String.valueOf(i));
	// key = vb.getStu_id() + vb.getEt();
	// if (xcStuSmsImageMap != null
	// && xcStuSmsImageMap.size() > 0) {
	// if (xcStuSmsImageMap.containsKey(key)) {
	// keylist = xcStuSmsImageMap.get(key);
	// keylist.add(vb);
	// } else {
	// keylist = new ArrayList();
	// keylist.add(vb);
	// }
	// } else {
	// keylist = new ArrayList();
	// keylist.add(vb);
	// }
	// addXcStuSmsIntoCacheImage(key, keylist);
	// }
	// }
	//
	// }
	// xcStuSmsVTMap.putAll(xcStuSmsImageMap);// 将临时缓存内容放入所用缓存
	// xcStuSmsImageMap.clear();// 清空临时缓存
	//
	// Constant.sms = false;
	// } else {
	// xcsmsVTList = sendXcSmsDAO.getStuSMSParamVTAdd(lastSyncDate);
	//
	// Iterator it = xcsmsVTList.iterator();
	// while (it.hasNext()) {
	//
	// if (xcStuSmsVTMap != null && xcStuSmsVTMap.size() > 0) {
	// XcStuSmsVTBean vb = (XcStuSmsVTBean) it.next();
	//
	// if (vb.getValid_flag().equals("1")) {
	// vb.setEvent_type("0000000000");
	// }
	// for (int i = 0; i < 7; i++) {// 循环事件类型
	// eventType = Integer.parseInt(vb.getEvent_type()
	// .substring(i, i + 1));// 得到类型
	// vb.setEt(String.valueOf(i));
	// key = vb.getStu_id() + vb.getEt();
	// if (xcStuSmsVTMap.containsKey(key)) {
	// keylist = xcStuSmsVTMap.get(key);
	// if (eventType == 1) {
	// keylist.add(vb);
	// addXcStuSmsIntoCacheVersionTwo(key, keylist);
	// for (int j = 0; j < keylist.size(); j++) {
	// XcStuSmsVTBean xvb = (XcStuSmsVTBean) keylist
	// .get(j);
	// if (xvb.getCell_number().equals(
	// vb.getCell_number())) {
	// keylist.remove(xvb);
	// }
	// }
	//
	// }
	// for (int j = 0; j < keylist.size(); j++) {
	// XcStuSmsVTBean xvb = (XcStuSmsVTBean) keylist
	// .get(j);
	// if (eventType == 0) {
	// keylist.remove(xvb);
	// }
	// }
	// } else {
	// if (eventType == 1) {
	// keylist = new ArrayList();
	// keylist.add(vb);
	// addXcStuSmsIntoCacheVersionTwo(key, keylist);
	// }
	// }
	// }
	//
	// } else {
	//
	// XcStuSmsVTBean vb = (XcStuSmsVTBean) it.next();
	// for (int i = 0; i < 7; i++) {// 循环事件类型
	// eventType = Integer.parseInt(vb.getEvent_type()
	// .substring(i, i + 1));// 得到类型
	// if (eventType == 1) {// 如果类型为1，进行添加缓存操作
	// vb.setEt(String.valueOf(i));
	// key = vb.getStu_id() + vb.getEt();
	// if (xcStuSmsImageMap != null
	// && xcStuSmsImageMap.size() > 0) {
	// if (xcStuSmsImageMap.containsKey(key)) {
	// keylist = xcStuSmsImageMap.get(key);
	// keylist.add(vb);
	// } else {
	// keylist = new ArrayList();
	// keylist.add(vb);
	// }
	// } else {
	// keylist = new ArrayList();
	// keylist.add(vb);
	// }
	// addXcStuSmsIntoCacheImage(key, keylist);
	// }
	// }
	// xcStuSmsVTMap.putAll(xcStuSmsImageMap);// 将临时缓存内容放入所用缓存
	// xcStuSmsImageMap.clear();// 清空临时缓存
	// }
	//
	// }
	// Constant.sms = false;
	// }
	//
	// log.info(NAME + "本次启动共加载" + xcStuSmsVTMap.size() + "个V2.0学生短信配置信息");
	//
	// lastIncremSyncDate = new Date();
	// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// lastSyncDate = sdf.format(lastIncremSyncDate);
	//
	// log.debug(NAME + "加载V2.0学生短信配置的时间为：" + lastIncremSyncDate);
	// log.debug(NAME + "V2.0学生短信配置信息加载完毕。");
	//
	// log.info(NAME + "当前V2.0学生短信配置信息缓存大小：" + xcStuSmsVTMap.size());
	// }

	/**
	 * 缓存站点信息
	 * 
	 * @param
	 * @return
	 */
	private void SiteCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[SiteCacheInit]");
		if (Constant.siteload) {
			log.debug(NAME + "开始加载站点增量缓存");
			if (Constant.upd_site_cache_time == null) {
				Constant.upd_site_cache_time = systime;
			}
			List<XcSiteBean> xcsiteList = sendXcSmsDAO
					.getSiteParam(Constant.upd_site_cache_time);
			Constant.upd_site_cache_time = systime;
			StringBuffer buffer = new StringBuffer();
			if (xcsiteList != null && xcsiteList.size() > 0) {
				for (XcSiteBean vb : xcsiteList) {
					buffer.append(vb.getValid_flag());
					if (buffer.toString().equals("1")) {
						buffer.delete(0, buffer.length());
						buffer.append(Constant.SITE);
						buffer.append(vb.getSite_id());
						delSiteFromCache(buffer.toString());
					} else {
						buffer.append(vb.getSite_id());
						if (buffer.toString().indexOf(" ") == -1) {
							buffer.delete(0, buffer.length());
							buffer.append(Constant.SITE);
							buffer.append(vb.getSite_id());
							addXcSiteIntoCache(buffer.toString(), vb);
						} else {
							log.info(NAME + "," + buffer.toString()
									+ "非法，不加载到站点缓存中");
						}
					}
					vb = null;
					buffer.delete(0, buffer.length());
				}
			} else {
				for (XcSiteBean vb : xcsiteList) {
					buffer.append(vb.getSite_id());
					if (buffer.toString().indexOf(" ") == -1) {
						buffer.delete(0, buffer.length());
						buffer.append(Constant.SITE);
						buffer.append(vb.getSite_id());
						addXcSiteIntoCache(buffer.toString(), vb);
					} else {
						log.info(NAME + "," + buffer.toString()
								+ "非法，不加载到站点缓存中");
					}
					vb = null;
				}
			}
			log.info(NAME + "本次启动共加载" + xcsiteList.size() + "个站点信息");
			xcsiteList.clear();
		}
		lastIncremSyncDate = new Date();
		log.debug(NAME + "加载站点信息的时间为：" + lastIncremSyncDate);
		log.info(NAME + "站点信息加载完毕。");
		log.info(NAME + "当前站点信息缓存大小：" + Constant.xcsiteMap.size());

	}

	/**
	 * 缓存学生信息
	 * 
	 * @param
	 * @return
	 */
	private void xcstudentCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[studentInit]");
		if (Constant.studentload) {
			Constant.studentload = false;
			log.info(NAME + "开始加载学生基本信息增量缓存");
			if (Constant.upd_student_cache_time == null) {
				Constant.upd_student_cache_time = systime;
			}
			List<XcStudentBean> xcstudentList = sendXcSmsDAO
					.getAllStudentParam(Constant.upd_student_cache_time);
			Constant.upd_student_cache_time = systime;
			/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
			if (xcstudentList != null && xcstudentList.size() > 0) {
				incstudentCache(xcstudentList);
				incstudentidCache(xcstudentList);
			} else {
				log.info(NAME + "该时间段没有学生信息更新");
			}

			lastIncremSyncDate = new Date();

			log.debug(NAME + "加载学生基本信息的时间为：" + lastIncremSyncDate);

			log.info(NAME + "本次启动共加载" + xcstudentList.size() + "个学生基本信息");
			log.info(NAME + "本次启动共加载" + xcstudentList.size() + "个学生编号信息");
			xcstudentList.clear();
			log.info(NAME + "当前学生信息缓存大小：" + Constant.xcstudentMap.size());
			log.info(NAME + "学生信息加载完毕。");
			log.info(NAME + "当前学生编号信息缓存大小：" + Constant.xcstudentidMap.size());
			log.info(NAME + "学生编号信息加载完毕。");
		}
		Constant.studentload = true;
	}

	@SuppressWarnings("unchecked")
	public void allstudentidCache1(Map<String, String> stucardidmap) {
		if (Constant.xcstudentidMap != null
				&& Constant.xcstudentidMap.size() > 0) {
			Set set = Constant.xcstudentidMap.keySet();
			Iterator itm = set.iterator();
			List<String> list = new ArrayList<String>();
			String stu_card_id = null;
			while (itm.hasNext()) {
				stu_card_id = (String) itm.next();
				if (!stucardidmap.containsKey(stu_card_id)) {
					list.add(stu_card_id);
				}
			}
			if (list != null && list.size() > 0) {
				delStuidFromList(list);
			}
			list.clear();
		}

	}

	@SuppressWarnings("unchecked")
	public void allstudentCache1(Map<String, String> stuidmap) {
		if (Constant.xcstudentMap != null && Constant.xcstudentMap.size() > 0) {
			Set set = Constant.xcstudentMap.keySet();
			Iterator itm = set.iterator();
			List list = new ArrayList();
			String key = null;
			while (itm.hasNext()) {
				key = (String) itm.next();
				if (!stuidmap.containsKey(key)) {
					list.add(key);
				}
			}
			if (list != null && list.size() > 0) {
				delStuFromList(list);
			}
			list.clear();
		}
	}

	public void allstudentidCache(XcStudentBean sb, StringBuffer buffer,
			Map<String, String> stucardidmap) {
		buffer.append(sb.getStu_card_id());
		if (buffer.toString().indexOf(" ") == -1) {
			buffer.delete(0, buffer.length());
			buffer.append(Constant.STUDENT);
			buffer.append(sb.getStu_card_id());
			// addStudentIdIntoCache(buffer.toString(), sb.getStu_card_id());
			SyncStudentIdValue(Constant.OFF, buffer.toString(), sb.getStu_id());
			stucardidmap.put(buffer.toString(), "");
		} else {
			log.info(NAME + "," + buffer.toString() + "非法，不加载到学生编号缓存中");
		}
		buffer.delete(0, buffer.length());
	}

	public void allstudentCache(XcStudentBean sb, StringBuffer buffer,
			Map<String, String> stuidmap) {
		buffer.append(sb.getStu_id());
		if (buffer.toString().indexOf(" ") == -1) {
			buffer.delete(0, buffer.length());
			buffer.append(Constant.STUDENT);
			buffer.append(sb.getStu_id());
			SyncStudentValue(Constant.OFF, buffer.toString(), sb);
			stuidmap.put(buffer.toString(), "");
		} else {
			log.info(NAME + "," + sb.getStu_id() + "非法，不加载到学生缓存中");
		}
		buffer.delete(0, buffer.length());
	}

	public void allstudentCache(XcStudentBean sb, StringBuffer buffer) {
		buffer.append(sb.getStu_id());
		if (buffer.toString().indexOf(" ") == -1) {
			buffer.delete(0, buffer.length());
			buffer.append(Constant.STUDENT);
			buffer.append(sb.getStu_id());
			SyncStudentValue(Constant.OFF, buffer.toString(), sb);
		} else {
			log.info(NAME + "," + sb.getStu_id() + "非法，不加载到学生缓存中");
		}
		buffer.delete(0, buffer.length());
	}

	// @SuppressWarnings("unchecked")''
	// private void allstudentCache1(List<XcStudentBean> xcstudentList) {
	// // log.info(NAME + "开始加载学生全量缓存。。");
	// int find = 0;
	// StringBuffer buffer = new StringBuffer();
	// // if (xcstudentList != null && xcstudentList.size() > 0) {
	// // for (XcStudentBean sb : xcstudentList) {
	// // buffer.append(Constant.STUDENT);
	// // buffer.append(sb.getStu_id());
	// // SyncStudentValue(Constant.OFF, buffer.toString(), sb);
	// // buffer.delete(0, buffer.length());
	// // sb = null;
	// // }
	// // }
	//		
	// if (xcstudentMap != null && xcstudentMap.size() > 0) {
	// Set set = xcstudentMap.keySet();
	// Iterator itm = set.iterator();
	// List list = new ArrayList();
	// while (itm.hasNext()) {
	// String stu_id = (String) itm.next();
	// for (XcStudentBean sb : xcstudentList) {
	// if (stu_id.indexOf(" ") != -1) {
	// continue;
	// }
	// buffer.append(Constant.STUDENT);
	// buffer.append(sb.getStu_id());
	// if (!stu_id.equals(buffer.toString())) {
	// find = 0;
	// } else {
	// find = 1;
	// sb = null;
	// break;
	// }
	// buffer.delete(0, buffer.length());
	// sb = null;
	// }
	// if (find == 0) {
	// list.add(stu_id);
	// }
	// stu_id = null;
	// }
	// if (list != null && list.size() > 0) {
	// delStuFromList(list);
	// }
	// itm = null;
	// set = null;
	// list = null;
	// }
	// log.info(NAME + "本次启动共加载" + xcstudentMap.size() + "个学生基本信息");
	// log.info(NAME + "当前学生信息缓存大小：" + xcstudentMap.size());
	// log.info(NAME + "学生信息加载完毕。");
	// xcstudentList = null;
	// }

	private void incstudentCache(List<XcStudentBean> xcstudentList) {
		log.info(NAME + "开始加载学生增量缓存。。");
		StringBuffer buffer = new StringBuffer();
		if (Constant.xcstudentMap != null && Constant.xcstudentMap.size() > 0) {
			for (XcStudentBean sb : xcstudentList) {
				buffer.append(sb.getStu_id());
				if (sb.getStu_id().indexOf(" ") == -1) {
					buffer.delete(0, buffer.length());
					buffer.append(Constant.STUDENT);
					buffer.append(sb.getStu_id());
					if (checkExisted(buffer.toString())) {
						if (sb.getValid_flag().equals("1")) {
							delStuFromCache(buffer.toString());
							buffer.delete(0, buffer.length());
							continue;
						} else {
							SyncStudentValue(Constant.OFF, buffer.toString(),
									sb);
						}
					}else{
						SyncStudentValue(Constant.OFF, buffer.toString(),
								sb);
					}
				} else {
					log.info(NAME + "," + buffer.toString() + "非法，不加载到学生信息缓存中");
				}
				buffer.delete(0, buffer.length());
				sb = null;
			}
		} else {
			for (XcStudentBean sb : xcstudentList) {
				buffer.append(sb.getStu_id());
				if (sb.getStu_id().indexOf(" ") == -1) {
					buffer.delete(0, buffer.length());
					buffer.append(Constant.STUDENT);
					buffer.append(sb.getStu_id());
					SyncStudentValue(Constant.OFF, buffer.toString(), sb);
				} else {
					log.info(NAME + "," + buffer.toString() + "非法，不加载到学生信息缓存中");
				}
				buffer.delete(0, buffer.length());
				sb = null;
			}
		}
	}

	public void allstudentidCache(XcStudentBean sb, StringBuffer buffer) {
		// if (xcstudentList != null && xcstudentList.size() > 0) {
		// log.info(NAME + "开始加载学生编号全量缓存。。");
		// StringBuffer buffer = new StringBuffer();
		// for (XcStudentBean sb : xcstudentList) {
		buffer.append(sb.getStu_card_id());
		if (buffer.toString().indexOf(" ") == -1) {
			buffer.delete(0, buffer.length());
			buffer.append(Constant.STUDENT);
			buffer.append(sb.getStu_card_id());
			// addStudentIdIntoCache(buffer.toString(), sb.getStu_card_id());
			SyncStudentIdValue(Constant.OFF, buffer.toString(), sb
					.getStu_id());
		} else {
			log.info(NAME + "," + buffer.toString() + "非法，不加载到学生编号缓存中");
		}
		buffer.delete(0, buffer.length());
		// sb = null;
		// }
		// log.info(NAME + "本次启动共加载" + xcstudentList.size() + "个学生编号信息");
		// log.info(NAME + "当前学生编号信息缓存大小：" + xcstudentidMap.size());
		// log.info(NAME + "学生编号信息加载完毕。");
		// Constant.isfirststudentidload = "false";
		// } else {
		// return;
		// }
		// xcstudentList = null;
	}

	// @SuppressWarnings("unchecked")
	// private void allstudentidCache1(List<XcStudentBean> xcstudentList) {
	// // log.info(NAME + "开始加载学生编号全量缓存。。");
	// int find = 0;
	// StringBuffer buffer = new StringBuffer();;
	// // if (xcstudentList != null && xcstudentList.size() > 0) {
	// // for (XcStudentBean sb : xcstudentList) {
	// // buffer.append(Constant.STUDENT);
	// // buffer.append(sb.getStu_card_id());
	// // SyncStudentIdValue(Constant.OFF, buffer.toString(),
	// sb.getStu_card_id());
	// // buffer.delete(0, buffer.length());
	// // sb = null;
	// // }
	// // }
	//
	// if (xcstudentidMap != null && xcstudentidMap.size() > 0) {
	// Set set = xcstudentidMap.keySet();
	// Iterator itm = set.iterator();
	// List list = new ArrayList();
	// while (itm.hasNext()) {
	// String stu_card_id = (String) itm.next();
	// for (XcStudentBean sb : xcstudentList) {
	// if (stu_card_id.indexOf(" ") != -1) {
	// continue;
	// }
	// buffer.append(Constant.STUDENT);
	// buffer.append(sb.getStu_card_id());
	// if (!stu_card_id.equals(buffer.toString())) {
	// find = 0;
	// } else {
	// find = 1;
	// sb = null;
	// break;
	// }
	// buffer.delete(0, buffer.length());
	// sb = null;
	// }
	// if (find == 0) {
	// list.add(stu_card_id);
	// }
	// stu_card_id = null;
	// }
	// if (list != null && list.size() > 0) {
	// delStuidFromList(list);
	// }
	// itm = null;
	// set = null;
	// list = null;
	// }
	// // log.info(NAME + "本次启动共加载" + xcstudentList.size() + "个学生编号信息");
	// // log.info(NAME + "当前学生编号信息缓存大小：" + xcstudentidMap.size());
	// // log.info(NAME + "学生编号信息加载完毕。");
	// // xcstudentList = null;
	// }

	private void incstudentidCache(List<XcStudentBean> xcstudentList) {
		log.info(NAME + "开始加载学生增减量缓存。。");
		StringBuffer buffer = new StringBuffer();
		if (Constant.xcstudentidMap != null
				&& Constant.xcstudentidMap.size() > 0) {
			for (XcStudentBean sb : xcstudentList) {
				buffer.append(sb.getStu_card_id());
				if (buffer.toString().indexOf(" ") == -1) {
					buffer.delete(0, buffer.length());
					buffer.append(Constant.STUDENT);
					buffer.append(sb.getStu_card_id());
					if (checkExistedStuId(buffer.toString())) {
						if (sb.getValid_flag().equals("1")) {
							delStuidFromCache(buffer.toString());
							buffer.delete(0, buffer.length());
							continue;
						} else {
							SyncStudentIdValue(Constant.OFF, buffer.toString(),
									sb.getStu_id());
						}
					}else {
						SyncStudentIdValue(Constant.OFF, buffer.toString(),
								sb.getStu_id());
					}
				} else {
					log.info(NAME + "," + buffer.toString() + "非法，不加载到学生编号缓存中");
				}
				buffer.delete(0, buffer.length());
				sb = null;
			}
		} else {
			for (XcStudentBean sb : xcstudentList) {
				buffer.append(sb.getStu_card_id());
				if (sb.getStu_card_id().indexOf(" ") == -1) {
					buffer.delete(0, buffer.length());
					buffer.append(Constant.STUDENT);
					buffer.append(sb.getStu_card_id());
					SyncStudentIdValue(Constant.OFF, buffer.toString(), sb
							.getStu_id());
				} else {
					log.info(NAME + "," + sb.getStu_id()
							+ "非法，不加载到学生编号缓存中");
				}
				buffer.delete(0, buffer.length());
				sb = null;
			}
		}
		log.info(NAME + "本次启动共加载" + xcstudentList.size() + "个学生编号信息");
		log.info(NAME + "当前学生编号信息缓存大小：" + Constant.xcstudentidMap.size());
		log.info(NAME + "学生编号信息加载完毕。");
		xcstudentList = null;
	}

	/**
	 * 缓存学生编号信息
	 * 
	 * @param
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	// public void xcstudentIdCache() {
	// if (stuidbool == true) {
	// MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
	// MDC.put("modulename", "[studentidInit]");
	// String systime = sendXcSmsDAO.getSysTime();
	// if (Constant.isfirststudentidload.equals("true")) {
	// xcstudentList = sendXcSmsDAO.getStudentParam();
	// if (xcstudentList != null && xcstudentList.size() > 0) {
	// log.debug(NAME + "开始加载学生编号全量缓存。。");
	// Iterator it = xcstudentList.iterator();
	// while (it.hasNext()) {
	// XcStudentBean sb = (XcStudentBean) it.next();
	// if (sb.getStu_id().indexOf(" ") == -1) {
	// addStudentIdIntoCache(sb);
	// } else {
	// log.info(NAME + "," + sb.getStu_id()
	// + "非法，不加载到学生编号缓存中");
	// }
	// }
	// log.info(NAME + "本次启动共加载" + xcstudentidMap.size()
	// + "个学生编号基本信息");
	// Constant.isfirststudentidload = "false";
	// } else {
	// return;
	// }
	// } else {
	// if (count == Integer.parseInt(Config.props
	// .getProperty("allstudengCacheTime"))) {
	// stuidbool = false;
	// int find = 0;
	// log.debug(NAME + "开始加载学生编号全量缓存。。");
	// xcstudentList = sendXcSmsDAO.getStudentParam();
	// if (xcstudentList != null && xcstudentList.size() > 0) {
	// Iterator it = xcstudentList.iterator();
	// while (it.hasNext()) {
	// XcStudentBean sb = (XcStudentBean) it.next();
	// addStudentIdIntoCache(sb);
	// }
	// }
	// if (xcstudentidMap != null && xcstudentidMap.size() > 0) {
	// Set set = xcstudentidMap.keySet();
	// Iterator itm = set.iterator();
	// List list = new ArrayList();
	// while (itm.hasNext()) {
	// String stu_card_id = (String) itm.next();
	// log.debug("ssdfadfadf:"+stu_card_id);
	// for (int i = 0; i < xcstudentList.size(); i++) {
	// if (stu_card_id.indexOf(" ") != -1) {
	// continue;
	// }
	// if (!stu_card_id
	// .equals(Constant.STUDENT
	// + xcstudentList.get(i)
	// .getStu_card_id())) {
	// find = 0;
	// } else {
	// find = 1;
	// break;
	// }
	// }
	// if (find == 0) {
	// list.add(stu_card_id);
	// }
	// }
	// if(list!=null&&list.size()>0){
	// delStuidFromList(list);
	// }
	// }
	// log.info(NAME + "本次启动共加载" + xcstudentidMap.size()
	// + "个学生编号信息");
	//
	// // 2小时执行一次全量加载后，将计时器清0
	// count = 0;
	// stuidbool = true;
	// } else {
	// count++;
	// log.debug(NAME + "开始加载学生增减量缓存。。");
	// /* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
	// xcstudentList = sendXcSmsDAO
	// .getAllStudentParam(Constant.upd_student_cache_time);
	// if (xcstudentidMap != null && xcstudentidMap.size() > 0) {
	// Iterator it = xcstudentList.iterator();
	// while (it.hasNext()) {
	// XcStudentBean sb = (XcStudentBean) it.next();
	// if (sb.getStu_card_id().indexOf(" ") == -1) {
	// if (sb.getValid_flag().equals("1")) {
	// if (checkExistedStuId(Constant.STUDENT
	// + sb.getStu_card_id())) {
	// delStuidFromCache(Constant.STUDENT
	// + sb.getStu_card_id());
	// }
	// } else {
	// addStudentIdIntoCache(sb);
	// }
	// } else {
	// log.info(NAME + "," + sb.getStu_card_id()
	// + "非法，不加载到学生编号缓存中");
	// }
	// }
	// } else {
	// Iterator it = xcstudentList.iterator();
	// while (it.hasNext()) {
	// XcStudentBean sb = (XcStudentBean) it.next();
	// if (sb.getStu_card_id().indexOf(" ") == -1) {
	// addStudentIdIntoCache(sb);
	// } else {
	// log.info(NAME + "," + sb.getStu_card_id()
	// + "非法，不加载到学生编号缓存中");
	// }
	// }
	// }
	// log.info(NAME + "本次启动共加载" + xcstudentList.size()
	// + "个学生编号信息");
	//
	// }
	//				
	// lastIncremSyncDate = new Date();
	//
	// log.debug(NAME + "加载学生编号信息的时间为：" + lastIncremSyncDate);
	//
	// log.debug(NAME + "学生编号信息加载完毕。");
	// }
	// Constant.upd_student_cache_time = systime;
	// }
	// log.debug(NAME + "当前学生编号信息缓存：" + xcstudentidMap);
	// log.info(NAME + "当前学生编号信息缓存大小：" + xcstudentidMap.size());
	// }

	/**
	 * 缓存线路站点配置信息
	 * 
	 * @param
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	private void xcRouteSiteCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcRouteSiteCacheInit]");
		if (Constant.routesiteload) {
			Constant.routesiteload = false;
			log.info(NAME + "开始加载线路站点信息全量缓存。。");
			List<RouteSiteBean> xcroutesiteList = sendXcSmsDAO
					.getRouteSiteParam();
			StringBuffer buffer = new StringBuffer();
			if (Constant.xcroutesiteMap != null
					&& Constant.xcroutesiteMap.size() > 0) {
				Map<String, String> map = new HashMap<String, String>();
				for (RouteSiteBean rsb : xcroutesiteList) {
					buffer.append(rsb.getRoute_id());
					buffer.append(rsb.getSite_id());
					if (buffer.toString().indexOf(" ") == -1) {
						SyncRouteSiteValue(Constant.OFF, buffer.toString(), rsb);
						map.put(buffer.toString(), "");
					} else {
						log.info(NAME + "," + buffer.toString()
								+ "非法，不加载到线路站点关系缓存中");
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
					delroutesiteListFromCache(list);
				}
				its = null;
			} else {
				for (RouteSiteBean rsb : xcroutesiteList) {
					buffer.append(rsb.getRoute_id());
					buffer.append(rsb.getSite_id());
					if (buffer.toString().indexOf(" ") == -1) {
						addXcRouteSiteIntoCache(buffer.toString(), rsb);
					} else {
						log.info(NAME + "," + buffer.toString()
								+ "非法，不加载到线路站点关系缓存中");
					}
					buffer.delete(0, buffer.length());
				}
			}
			xcroutesiteList.clear();
			log.info(NAME + "线路站点关系信息加载完毕。");
			log.info(NAME + "当前线路站点关系缓存大小：" + Constant.xcroutesiteMap.size());
		}
		Constant.routesiteload = true;
	}

	public void delroutesiteListFromCache(List<String> list) {
		for (String str : list) {
			Constant.xcroutesiteMap.remove(str);
		}

	}

	private void delroutesiteFromCache(String key) {
		Constant.xcroutesiteMap.remove(key);
	}
	
	/**
	 * 缓存站点订购信息
	 * 
	 * @param
	 * @return
	 */

	@SuppressWarnings( { "unchecked"})
	private void xcVssCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcVssCacheInit]");
		if (Constant.vssload) {
			Constant.vssload = false;
			log.info(NAME + "开始加载站点订购信息缓存。。");
			List<XcvsseBean> xcvssList = sendXcSmsDAO.getVssParam();
			if (Constant.xcvssMap != null && Constant.xcvssMap.size() > 0) {
				Map<String,String> map = new HashMap<String,String>();
				for (XcvsseBean xsb : xcvssList) {
					if (xsb.getStudent_id().indexOf(" ") == -1) {
						SyncVssValue(Constant.OFF, XCUtil.xcVssKey(xsb), xsb);
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
					delvssListFromCache(list);
				}
				its = null;
				list.clear();
			} else {
				for (XcvsseBean xsb : xcvssList) {
					if (xsb.getStudent_id().indexOf(" ") == -1) {					
						addVssIntoCache(XCUtil.xcVssKey(xsb), xsb);
					} else {
						log.info(NAME + "," + xsb.getVehicle_vin()
								+ "非法，不加载到站点订购缓存中");
					}
					xsb = null;
				}
			}
			xcvssList.clear();
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

	// @SuppressWarnings("unchecked")
	// private void xcVssCache() {
	// MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
	// MDC.put("modulename", "[xcVssCacheInit]");
	// List<XcvsseBean> xcvssList = sendXcSmsDAO.getVssParam();
	// if (Constant.sms) {
	// log.info(NAME + "开始加载站点订购信息全量缓存。。");
	// for (XcvsseBean xsb : xcvssList) {
	// if (xsb.getVehicle_vin().indexOf(" ") == -1) {
	// StringBuffer buffer = new StringBuffer();
	// buffer.append(xsb.getStudent_id());
	// buffer.append(xsb.getRoute_id());
	// buffer.append(xsb.getVss_state());
	// buffer.append(xsb.getSite_updown());
	// if (checkVssVt(buffer.toString())) {
	// delvssvtFromCache(buffer.toString());
	// }
	// addVssIntoCacheVT(buffer.toString(), xsb);
	// } else {
	// log.info(NAME + "," + xsb.getVehicle_vin()
	// + "非法，不加载到站点订购缓存中");
	// }
	// xsb = null;
	// }
	// Constant.sms = false;
	// } else {
	// int find = 0;
	// if (xcvssMap != null && xcvssMap.size() > 0) {
	// Set<String> rsid = xcvssMap.keySet();
	// Iterator<String> its = rsid.iterator();
	// List list = new ArrayList();
	// while (its.hasNext()) {
	// String rs_id = its.next();
	// for (XcvsseBean xsb : xcvssList) {
	// if (!rs_id.equals(xsb.getStudent_id()
	// + xsb.getVss_state() + xsb.getSite_updown())) {
	// find = 0;
	// } else {
	// find = 1;
	// xsb = null;
	// break;
	// }
	// xsb = null;
	// }
	// if (find == 0) {
	// list.add(rs_id);
	// }
	// rs_id = null;
	// }
	// if (list != null && list.size() > 0) {
	// delvssListFromCache(list);
	// delvssListFromCacheVT(list);
	// }
	// StringBuffer buffer = new StringBuffer();
	// for (XcvsseBean xsb : xcvssList) {
	// if (xsb.getVehicle_vin().indexOf(" ") == -1) {
	// buffer.append(xsb.getStudent_id());
	// buffer.append(xsb.getVss_state());
	// buffer.append(xsb.getSite_updown());
	// if (checkVss(buffer.toString())) {
	// delvssFromCache(buffer.toString());
	// }
	// addVssIntoCache(buffer.toString(), xsb);
	// buffer.append(xsb.getRoute_id());
	// if (checkVssVt(buffer.toString())) {
	// delvssvtFromCache(buffer.toString());
	// }
	// addVssIntoCacheVT(buffer.toString(), xsb);
	// } else {
	// log.info(NAME + "," + xsb.getVehicle_vin()
	// + "非法，不加载到站点订购缓存中");
	// }
	// }
	// its = null;
	// rsid = null;
	// } else {
	// StringBuffer buffer = new StringBuffer();
	// for (XcvsseBean xsb : xcvssList) {
	// if (xsb.getVehicle_vin().indexOf(" ") == -1) {
	// buffer.append(xsb.getStudent_id());
	// buffer.append(xsb.getRoute_id());
	// buffer.append(xsb.getVss_state());
	// buffer.append(xsb.getSite_updown());
	// if (checkVssVt(buffer.toString())) {
	// delvssvtFromCache(buffer.toString());
	// }
	// addVssIntoCacheVT(buffer.toString(), xsb);
	// } else {
	// log.info(NAME + "," + xsb.getVehicle_vin()
	// + "非法，不加载到站点订购缓存中");
	// }
	// xsb = null;
	// }
	// }
	// }
	// xcvssList = null;
	// log.info(NAME + "本次启动共加载" + xcvssMap.size() + "个站点订购信息");
	//
	// lastIncremSyncDate = new Date();
	// log.debug(NAME + "加载站点订购信息的时间为：" + lastIncremSyncDate);
	// lastIncremSyncDate = null;
	// log.info(NAME + "线路站点订购信息加载完毕。");
	//
	// log.info(NAME + "当前站点订购信息缓存大小：" + xcvssMap.size());
	// }

	public synchronized void delvssListFromCache(List<String> list) {
		for (String str : list) {
			Constant.xcvssMap.remove(str);
		}
	}

	private synchronized void delvssFromCache(String key) {
		Constant.xcvssMap.remove(key);
	}

	// private void delvssvtFromCache(String key) {
	// xcvssvtMap.remove(key);
	// }

	/**
	 * 缓存V2.0站点订购信息
	 * 
	 * @param
	 * @return
	 */

	// @SuppressWarnings("unchecked")
	// public void xcVssCacheVT() {
	// MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
	// MDC.put("modulename", "[xcVssCacheInit]");
	// xcvssList = sendXcSmsDAO.getVssParam();
	// if (Constant.sms) {
	// log.debug(NAME + "开始加载V2.0站点订购信息全量缓存。。");
	// Iterator it = xcvssList.iterator();
	// while (it.hasNext()) {
	// XcvsseBean xsb = (XcvsseBean) it.next();
	// if (xsb.getVehicle_vin().indexOf(" ") == -1) {
	// addVssIntoCacheVT(xsb);
	// } else {
	// log.info(NAME + "," + xsb.getVehicle_vin()
	// + "非法，不加载到V2.0站点订购缓存中");
	// }
	// }
	// Constant.sms = false;
	// } else {
	// Iterator it = xcvssList.iterator();
	// int find = 0;
	// if (xcvssvtMap != null && xcvssvtMap.size() > 0) {
	// Set<String> rsid = xcvssvtMap.keySet();
	// Iterator<String> its = rsid.iterator();
	// List list = new ArrayList();
	// while (its.hasNext()) {
	// String rs_id = its.next();
	// for (int i = 0; i < xcvssList.size(); i++) {
	// XcvsseBean xsb = xcvssList.get(i);
	// if
	// (!rs_id.equals(xsb.getStudent_id()+xsb.getRoute_id()+xsb.getVss_state()+xsb.getSite_updown()))
	// {
	// find = 0;
	// } else {
	// find = 1;
	// break;
	// }
	// }
	// if (find == 0) {
	// list.add(rs_id);
	// }
	// }
	// if (list != null && list.size() > 0) {
	// delvssListFromCacheVT(list);
	// }
	// while (it.hasNext()) {
	// XcvsseBean xsb = (XcvsseBean) it.next();
	// if (xsb.getVehicle_vin().indexOf(" ") == -1) {
	// addVssIntoCacheVT(xsb);
	// } else {
	// log.info(NAME + "," + xsb.getVehicle_vin()
	// + "非法，不加载到V2.0站点订购缓存中");
	// }
	// }
	// } else {
	// while (it.hasNext()) {
	// XcvsseBean xsb = (XcvsseBean) it.next();
	// if (xsb.getVehicle_vin().indexOf(" ") == -1) {
	// addVssIntoCacheVT(xsb);
	// } else {
	// log.info(NAME + "," + xsb.getVehicle_vin()
	// + "非法，不加载到V2.0站点订购缓存中");
	// }
	// }
	// }
	// }
	// log.info(NAME + "本次启动共加载" + xcvssvtMap.size() + "个V2.0站点订购信息");
	//
	// lastIncremSyncDate = new Date();
	// log.debug(NAME + "加载V2.0站点订购信息的时间为：" + lastIncremSyncDate);
	// log.debug(NAME + "V2.0线路站点订购信息加载完毕。");
	//
	// log.info(NAME + "当前V2.0站点订购信息缓存大小：" + xcvssvtMap.size());
	// }

	// private void delvssListFromCacheVT(List<String> list) {
	// for (String str : list) {
	// xcvssvtMap.remove(str);
	// }
	//
	// }

	/**
	 * 缓存企业订购短信信息
	 * 
	 * @param
	 * @return
	 */
	public void xcEnterpriseCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[enterpriseInit]");
		if (Constant.enterprise_smgatewayload) {
			Constant.enterprise_smgatewayload = false;
			log.info(NAME + "开始加载企业模式增量缓存。。");
			if (Constant.upd_moshi_cache_time == null) {
				Constant.upd_moshi_cache_time = systime;
			}
			List<EnterPriseBean> xcenterpriseList = sendXcSmsDAO
					.getEnterPriseParam(Constant.upd_moshi_cache_time);
			Constant.upd_moshi_cache_time = systime;
			if (xcenterpriseList != null && xcenterpriseList.size() > 0) {
				StringBuffer buffer = new StringBuffer();
				for (EnterPriseBean epb : xcenterpriseList) {
					buffer.append(epb.getValid_flag());
					if (buffer.toString().equals("1")) {
						buffer.delete(0, buffer.length());
						buffer.append(epb.getEnterprise_id());
						buffer.append(Constant.ENTERPRISE);
						delEnterpriseFromCache(buffer.toString());
					} else {
						buffer.delete(0, buffer.length());
						buffer.append(epb.getEnterprise_id());
						if (buffer.toString().indexOf(" ") == -1) {
							buffer.append(Constant.ENTERPRISE);
							addEnterpriseIntoCache(buffer.toString(), epb);
						} else {
							log.info(NAME + "," + buffer.toString()
									+ "非法，不加载到企业模式缓存中");
						}
					}
					buffer.delete(0, buffer.length());
					epb = null;
				}
			}
			log.info(NAME + "本次启动共加载" + xcenterpriseList.size() + "个企业模式信息");
			xcenterpriseList.clear();
			lastIncremSyncDate = new Date();

			log.debug(NAME + "加载企业企业模式信息的时间为：" + lastIncremSyncDate);
		}

		log.info(NAME + "企业模式信息加载完毕。");
		lastIncremSyncDate = null;
		log.info(NAME + "当前企业模式信息缓存大小：" + Constant.xcenterpriseMap.size());
		Constant.enterprise_smgatewayload = true;
	}

	/**
	 * 查看某学生信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String stuid) {
		return Constant.xcstudentMap.containsKey(stuid);
	}

	/**
	 * 查看某学生信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExistedStuId(String stucardid) {
		return Constant.xcstudentidMap.containsKey(stucardid);
	}

	public boolean checkRouteSite(String key) {
		return Constant.xcroutesiteMap.containsKey(key);
	}

	public boolean checkVss(String key) {
		return Constant.xcvssMap.containsKey(key);
	}

	// public boolean checkVssVt(String key) {
	// return xcvssvtMap.containsKey(key);
	// }

	/**
	 * 从缓存中删除某个学生编号基本信息
	 * 
	 * @param vin
	 */
	public synchronized void delStuidFromCache(String stucardid) {
		log.debug(NAME + "开始从缓存中删除" + (stucardid) + "的缓存记录。");
		Constant.xcstudentidMap.remove(stucardid);
		log.debug(NAME + "从缓存中删除" + (stucardid) + "的缓存记录完毕！");
	}

	public synchronized void delStuidFromList(List<String> stucardid) {
		log.debug(NAME + "开始从缓存中删除学生编号缓存记录。");
		for (String str : stucardid) {
			Constant.xcstudentidMap.remove(str);
		}
		log.debug(NAME + "从缓存中删除学生编号缓存记录完毕！");
	}

	/**
	 * 从缓存中删除某个学生基本信息
	 * 
	 * @param vin
	 */
	public synchronized void delStuFromCache(String stuid) {
		log.debug(NAME + "开始从缓存中删除" + (stuid) + "的缓存记录。");
		Constant.xcstudentMap.remove(stuid);
		log.debug(NAME + "从缓存中删除" + (stuid) + "的缓存记录完毕！");
	}

	public synchronized void delStuFromList(List<String> stuid) {
		log.debug(NAME + "开始从缓存中删除学生信息缓存记录。");
		for (String str : stuid) {
			Constant.xcstudentMap.remove(str);
		}
		log.debug(NAME + "从缓存中删除学生信息缓存记录完毕！");
	}

	/**
	 * 从缓存中删除某个车辆基本信息
	 * 
	 * @param vin
	 */
	public synchronized void delSmsFromCache(String stuid) {
		log.debug(NAME + "开始从缓存中删除" + (stuid) + "的缓存记录。");
		Constant.xcStuSmsMap.remove(stuid);
		log.debug(NAME+"xcStuSmsMap:"+Constant.xcStuSmsMap.get(stuid));
		log.debug(NAME + "从缓存中删除" + (stuid) + "的缓存记录完毕！");
	}

	public synchronized void delSiteListFromCache(List<String> list) {
		for (String str : list) {
			Constant.xcsiteMap.remove(str);
		}
	}

	public synchronized void delSiteFromCache(String siteid) {
		log.debug(NAME + "开始从缓存中删除" + (siteid) + "的缓存记录。");
		Constant.xcsiteMap.remove(siteid);
		log.debug(NAME + "从缓存中删除" + (siteid) + "的缓存记录完毕！");
	}

	// 在内存中添加学生信息
	public void addStudentIntoCache(String key, XcStudentBean sb) {
		Constant.xcstudentMap.put(key, sb);
		log.debug(NAME + "已将" + (key) + "的学生记录加入缓存！");
	}

	// 在内存中添加学生编号、卡号信息
	public void addStudentIdIntoCache(String key, String str) {
		Constant.xcstudentidMap.put(key, str);
		log.debug(NAME + "已将" + (key) + "的学生编号记录加入缓存！");
	}

	// 在缓存中添加学生短信配置信息
	public synchronized void addXcStuSmsIntoCache(String key,
			List<XcStuSmsBean> xcsmsList2) {
		Constant.xcStuSmsMap.put(key, xcsmsList2);
		log.debug(NAME+"key:"+key+",addXcStuSmsIntoCache:"+Constant.xcStuSmsMap.get(key));
		log.debug(NAME + "已将" + (xcsmsList2.get(0).getStu_id())
				+ "的学生短信配置记录加入缓存！");
	}

	// V2.0在缓存中添加学生短信配置信息
	// public synchronized void addXcStuSmsIntoCacheVersionTwo(String key,
	// List<XcStuSmsVTBean> smskeyList2) {
	// xcStuSmsVTMap.put(key, smskeyList2);
	// log.info(NAME + "已将" + (smskeyList2.get(0).getStu_id())
	// + "的学生短信配置记录加入缓存！");
	// }

	// V2.0在虚拟缓存中添加学生短信配置信息
	// public synchronized void addXcStuSmsIntoCacheImage(String key,
	// List<XcStuSmsVTBean> smskeyList2) {
	// xcStuSmsImageMap.put(key, smskeyList2);
	// log.info(NAME + "已将"
	// + (smskeyList2.get(0).getStu_id() + smskeyList2.get(0).getEt())
	// + "的学生短信配置记录加入缓存！");
	// }

	// 在缓存中添加站点信息
	public synchronized void addXcSiteIntoCache(String key,
			XcSiteBean xcSiteBean) {
		Constant.xcsiteMap.put(key, xcSiteBean);
		log.debug(NAME + "已将" + (key) + "的站点记录加入缓存！");
	}

	// 在缓存中线路站点关系表信息
	public synchronized void addXcRouteSiteIntoCache(String key,
			RouteSiteBean routeSiteBean) {
		Constant.xcroutesiteMap.put(key, routeSiteBean);
		log.debug(NAME + "已将" + (routeSiteBean.getRoute_id())
				+ "的线路站点关系记录加入缓存！");
	}

	// 在缓存加入站点订购关系信息
	// public synchronized void addVssIntoCacheVT(String string,
	// XcvsseBean xcvsseBean) {
	// xcvssvtMap.put(string, xcvsseBean);
	// log.debug(NAME + "已将" + (string) + "的线路站点关系记录加入缓存！");
	// }

	public synchronized XcvsseBean SyncVssValue(String type, String key,
			XcvsseBean sb) {
		if (type.equals(Constant.OFF)) {
			delvssFromCache(key);
			addVssIntoCache(key, sb);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.xcvssMap.get(key);
		} else {
			log.error(NAME + "SyncVssValue传入的类型错误");
			return null;
		}
	}

	// 在缓存加入站点订购关系信息
	public synchronized void addVssIntoCache(String string,
			XcvsseBean xcvsseBean) {
		Constant.xcvssMap.put(string, xcvsseBean);
		log.debug(NAME + "已将" + (string) + "的模式2线路站点关系记录加入缓存！");
	}

	public synchronized void addEnterpriseIntoCache(String key,
			EnterPriseBean epb) {
		Constant.xcenterpriseMap.put(key, epb);
		log.debug(NAME + "已将" + (key) + "的企业短信配置记录加入缓存！");
	}

	public IXcSmsDAO getSendXcSmsDAO() {
		return sendXcSmsDAO;
	}

	public void setSendXcSmsDAO(IXcSmsDAO sendXcSmsDAO) {
		this.sendXcSmsDAO = sendXcSmsDAO;
	}

	public synchronized List<XcStuSmsBean> SyncSmsValue(String type,
			String key, List<XcStuSmsBean> list) {
		if (type.equals(Constant.OFF)) {
			delSmsFromCache(key);
			if(list!=null&&list.size()>0){
				addXcStuSmsIntoCache(key, list);
			}
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.xcStuSmsMap.get(key);
		} else {
			log.error(NAME + "SyncSmsValue传入的类型错误");
			return null;
		}
	}

	public List<XcStuSmsBean> getValue(String key) {
		if (Constant.stusmsload) {
			return SyncSmsValue(Constant.ON, key, null);
		} else {
			return (List<XcStuSmsBean>) Constant.xcStuSmsMap.get(key);
		}
	}

	public Collection<List<XcStuSmsBean>> getValues() {
		return Constant.xcStuSmsMap.values();
	}

	public XcStudentBean getStudentValue(String key) {
		if (!Constant.studentload) {
			return SyncStudentValue(Constant.ON, key, null);
		} else {
			return Constant.xcstudentMap.get(key);
		}
	}

	private synchronized XcStudentBean SyncStudentValue(String type,
			String key, XcStudentBean sb) {
		if (type.equals(Constant.OFF)) {
			delStuFromCache(key);
			addStudentIntoCache(key, sb);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.xcstudentMap.get(key);
		} else {
			log.error(NAME + "SyncStudentValue传入的类型错误");
			return null;
		}
	}

	private synchronized String SyncStudentIdValue(String type, String key, String str) {
		if (type.equals(Constant.OFF)) {
			delStuidFromCache(key);
			addStudentIdIntoCache(key, str);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.xcstudentidMap.get(key);
		} else {
			log.error(NAME + "SyncStudentIdValue传入的类型错误");
			return null;
		}
	}

	public Collection<XcStudentBean> getStudentValues() {
		return Constant.xcstudentMap.values();
	}

	public synchronized XcSiteBean SyncSiteValue(String type, String key,
			XcSiteBean sb) {
		if (type.equals(Constant.OFF)) {
			delSiteFromCache(key);
			addXcSiteIntoCache(key, sb);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.xcsiteMap.get(key);
		} else {
			log.error(NAME + "SyncSiteValue传入的类型错误");
			return null;
		}
	}

	public XcSiteBean getSiteValue(String key) {
		if (!Constant.siteload) {
			return SyncSiteValue(Constant.ON, key, null);
		} else {
			return Constant.xcsiteMap.get(key);
		}
	}

	public Collection<XcSiteBean> getSiteValues() {
		return Constant.xcsiteMap.values();
	}

	public synchronized RouteSiteBean SyncRouteSiteValue(String type,
			String key, RouteSiteBean sb) {
		if (type.equals(Constant.OFF)) {
			delroutesiteFromCache(key);
			addXcRouteSiteIntoCache(key, sb);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.xcroutesiteMap.get(key);
		} else {
			log.error(NAME + "SyncRouteSiteValue传入的类型错误");
			return null;
		}
	}

	public RouteSiteBean getRouteSiteValue(String key) {
		if (!Constant.routesiteload) {
			return SyncRouteSiteValue(Constant.ON, key, null);
		} else {
			return Constant.xcroutesiteMap.get(key);
		}
	}

	public Collection<RouteSiteBean> getRouteSiteValues() {
		return Constant.xcroutesiteMap.values();
	}

	public XcvsseBean getVssValue(String key) {
		if (!Constant.vssload) {
			return SyncVssValue(Constant.ON, key, null);
		} else {
			return Constant.xcvssMap.get(key);
		}
	}

	public Collection<XcvsseBean> getVssValues() {
		return Constant.xcvssMap.values();
	}

	// public XcvsseBean getVssValueVt(String key) {
	// return xcvssvtMap.get(key);
	// }
	//
	// public Collection<XcvsseBean> getVssValueVt() {
	// return xcvssvtMap.values();
	// }

	public String getStudentIdValue(String key) {
		// log.debug(NAME + "error xcstudentidMap=" + xcstudentidMap);
		if (!Constant.studentload) {
			return SyncStudentIdValue(Constant.ON, key, null);
		} else {
			return Constant.xcstudentidMap.get(key);
		}

	}

	public Collection<String> getStudentIdValues() {
		return Constant.xcstudentidMap.values();
	}

	// public List<XcStuSmsVTBean> getXcSmsVTValue(String key) {
	// return (List<XcStuSmsVTBean>) xcStuSmsVTMap.get(key);
	// }
	//
	// public Collection<List<XcStuSmsVTBean>> getXcSmsVTValueValues() {
	// return xcStuSmsVTMap.values();
	// }
	// 企业短信配置缓存

	public synchronized void delEnterpriseFromCache(String key) {
		log.debug(NAME + "开始从缓存中删除" + (key) + "的缓存记录。");
		Constant.xcenterpriseMap.remove(key);
		log.debug(NAME + "从缓存中删除" + (key) + "的缓存记录完毕！");
	}

	public synchronized void delEnterpriseListFromCache(List<String> list) {
		for (String key : list) {
			Constant.xcenterpriseMap.remove(key);
		}
	}

	public EnterPriseBean getXcEntepriseValue(String key) {
		if (!Constant.enterprise_smgatewayload) {
			return SyncEnterpriseValue(Constant.ON, key, null);
		} else {
			return Constant.xcenterpriseMap.get(key);
		}
	}

	public synchronized EnterPriseBean SyncEnterpriseValue(String type,
			String key, EnterPriseBean sb) {
		if (type.equals(Constant.OFF)) {
			delEnterpriseFromCache(key);
			addEnterpriseIntoCache(key, sb);
			return null;
		} else if (type.equals(Constant.ON)) {
			return Constant.xcenterpriseMap.get(key);
		} else {
			log.error(NAME + "SyncEnterpriseValue传入的类型错误");
			return null;
		}
	}

	public Collection<EnterPriseBean> getXcEntepriseValues() {
		return Constant.xcenterpriseMap.values();
	}
}
