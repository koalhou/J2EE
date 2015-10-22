package com.neusoft.SchoolBus.vncs.manage;

import java.text.ParseException;

import java.text.SimpleDateFormat;
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
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsVTBean;
import com.neusoft.SchoolBus.vncs.domain.XcStudentBean;
import com.neusoft.SchoolBus.vncs.domain.XcvsseBean;
import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.util.StringUtil;

public class SendxcmsmCommandManager_bak {
	private Logger log = LoggerFactory.getLogger(SendxcmsmCommandManager_bak.class);

	private static final String NAME = "<SendSmsCommandManager>";

	private static final SendxcmsmCommandManager_bak sendxcmsmCommandManager = new SendxcmsmCommandManager_bak();
	private Date lastIncremSyncDate;
	private String lastSyncDate;
	private IXcSmsDAO sendXcSmsDAO;
	private List<XcStuSmsBean> xcsmsList;
	public static Map<String, List<XcStuSmsBean>> xcStuSmsMap;

	private List<XcStuSmsVTBean> xcsmsVTList;
	public static Map<String, List<XcStuSmsVTBean>> xcStuSmsVTMap;
	public static Map<String, List<XcStuSmsVTBean>> xcStuSmsImageMap;

	private List<XcStudentBean> xcstudentList;
	public static Map<String, XcStudentBean> xcstudentMap;

	public static Map<String, String> xcstudentidMap;
	private List<XcSiteBean> xcsiteList;

	public static Map<String, XcSiteBean> xcsiteMap;
	private List<RouteSiteBean> xcroutesiteList;
	public static Map<String, RouteSiteBean> xcroutesiteMap;

	private List<XcvsseBean> xcvssList;
	public static Map<String, XcvsseBean> xcvssMap;
	public static Map<String, XcvsseBean> xcvssvtMap;
	
	private List<EnterPriseBean> xcenterpriseList;
	public static Map<String, EnterPriseBean> xcenterpriseMap;
	

	// 计时器，增减量加载2分钟执行一次，2小时执行一次全量加载，于是 count为指定值(数据库中可配置)的时候就进行一次全量加载
	private static int count = 0;
	// 如果每2小时一次执行的全量加载没有加载完成，job不进行启动
	private static boolean bool = true;
	private static boolean stuidbool = true;
	private static boolean site = true;

	public static SendxcmsmCommandManager_bak getInstance() {
		return sendxcmsmCommandManager;
	}

	private SendxcmsmCommandManager_bak() {
		xcsmsList = new ArrayList<XcStuSmsBean>();
		xcStuSmsMap = new HashMap<String, List<XcStuSmsBean>>();

		xcsiteList = new ArrayList<XcSiteBean>();
		xcsiteMap = new HashMap<String, XcSiteBean>();

		xcstudentList = new ArrayList<XcStudentBean>();
		xcstudentMap = new HashMap<String, XcStudentBean>();
		xcstudentidMap = new HashMap<String, String>();

		xcroutesiteList = new ArrayList<RouteSiteBean>();
		xcroutesiteMap = new HashMap<String, RouteSiteBean>();

		xcvssList = new ArrayList<XcvsseBean>();
		xcvssMap = new HashMap<String, XcvsseBean>();
		xcvssvtMap = new HashMap<String, XcvsseBean>();
		
		xcenterpriseList = new ArrayList<EnterPriseBean>();
		xcenterpriseMap = new HashMap<String, EnterPriseBean>();		

		xcsmsVTList = new ArrayList<XcStuSmsVTBean>();
		xcStuSmsVTMap = new HashMap<String, List<XcStuSmsVTBean>>();
		xcStuSmsImageMap = new HashMap<String, List<XcStuSmsVTBean>>();

	}

	public void init() {
		 xcstuSmsCache();
		 SiteCache();
		 xcstudentCache();
		 xcstudentIdCache();
		 xcRouteSiteCache();
		 xcVssCache();
		 xcEnterpriseCache();
		 xcVssCacheVT();
	}

	/**
	 * 缓存学生短信配置信息
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void xcstuSmsCache() {
		String key = "";
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcstuSmsCacheInit]");
		xcsmsList = sendXcSmsDAO.getStuSMSParam();
		List keylist = null;
		if (Constant.sms) {
			log.debug(NAME + "开始加载学生短信配置全量缓存。。");
			Iterator it = xcsmsList.iterator();

			while (it.hasNext()) {
				XcStuSmsBean vb = (XcStuSmsBean) it.next();
				if (key.equals("")) {
					key = vb.getStu_id() + vb.getEvent_type();
					keylist = new ArrayList();
					keylist.add(vb);
				} else {

					if ((vb.getStu_id() + vb.getEvent_type()).equals(key)) {
						keylist.add(vb);
					} else {
						addXcStuSmsIntoCache(key, keylist);
						key = vb.getStu_id() + vb.getEvent_type();
						keylist = new ArrayList();
						keylist.add(vb);
					}
				}
				if (!it.hasNext()) {
					addXcStuSmsIntoCache(key, keylist);
				}
			}
			Constant.sms = false;
		} else {
			Iterator it = xcsmsList.iterator();
			int find = 0;
			if (xcStuSmsMap != null && xcStuSmsMap.size() > 0) {
				Set<String> rsid = xcStuSmsMap.keySet();
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
					delSmsListFromCache(list);
				}
				while (it.hasNext()) {
					XcStuSmsBean vb = (XcStuSmsBean) it.next();
					if (key.equals("")) {
						key = vb.getStu_id() + vb.getEvent_type();
						keylist = new ArrayList();
						keylist.add(vb);
					} else {

						if ((vb.getStu_id() + vb.getEvent_type()).equals(key)) {
							keylist.add(vb);
						} else {
							addXcStuSmsIntoCache(key, keylist);
							key = vb.getStu_id() + vb.getEvent_type();
							keylist = new ArrayList();
							keylist.add(vb);
						}
					}
					if (!it.hasNext()) {
						addXcStuSmsIntoCache(key, keylist);
					}
				}
			} else {
				while (it.hasNext()) {
					XcStuSmsBean vb = (XcStuSmsBean) it.next();
					if (key.equals("")) {
						key = vb.getStu_id() + vb.getEvent_type();
						keylist = new ArrayList();
						keylist.add(vb);
					} else {

						if ((vb.getStu_id() + vb.getEvent_type()).equals(key)) {
							keylist.add(vb);
						} else {
							addXcStuSmsIntoCache(key, keylist);
							key = vb.getStu_id() + vb.getEvent_type();
							keylist = new ArrayList();
							keylist.add(vb);
						}
					}
					if (!it.hasNext()) {
						addXcStuSmsIntoCache(key, keylist);
					}
				}
			}
			Constant.sms = false;
		}

		log.info(NAME + "本次启动共加载" + xcStuSmsMap.size() + "个学生短信配置信息");

		lastIncremSyncDate = new Date();
		log.debug(NAME + "加载学生短信配置的时间为：" + lastIncremSyncDate);
		log.debug(NAME + "学生短信配置信息加载完毕。");

		log.info(NAME + "当前学生短信配置信息缓存大小：" + xcStuSmsMap.size());
	}

	private void delSmsListFromCache(List<String> list) {
		for (String str : list) {
			xcStuSmsMap.remove(str);
		}

	}

	/**
	 * 删除学生短信配置信息缓存V2.0
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unused")
	private void delSmsListFromCacheVT(List<String> list) {
		for (String str : list) {
			xcStuSmsVTMap.remove(str);
		}

	}

	/**
	 * 缓存学生短信配置信息V2.0
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void xcstuSmsCacheVersionTwo() {
		String key = "";
		@SuppressWarnings("unused")
		int find = 0;
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcstuSmsVTCacheInit]");

		List keylist = null;

		int eventType = 0;
		if (Constant.sms) {
			xcsmsVTList = sendXcSmsDAO.getStuSMSParamVT();
			log.debug(NAME + "开始加载V2.0学生短信配置全量缓存。。");
			Iterator it = xcsmsVTList.iterator();
			// 全量
			while (it.hasNext()) {
				XcStuSmsVTBean vb = (XcStuSmsVTBean) it.next();
				for (int i = 0; i < 7; i++) {// 循环事件类型
					eventType = Integer.parseInt(vb.getEvent_type().substring(
							i, i + 1));// 得到类型
					if (eventType == 1) {// 如果类型为1，进行添加缓存操作
						vb.setEt(String.valueOf(i));
						key = vb.getStu_id() + vb.getEt();
						if (xcStuSmsImageMap != null
								&& xcStuSmsImageMap.size() > 0) {
							if (xcStuSmsImageMap.containsKey(key)) {
								keylist = xcStuSmsImageMap.get(key);
								keylist.add(vb);
							} else {
								keylist = new ArrayList();
								keylist.add(vb);
							}
						} else {
							keylist = new ArrayList();
							keylist.add(vb);
						}
						addXcStuSmsIntoCacheImage(key, keylist);
					}
				}

			}
			xcStuSmsVTMap.putAll(xcStuSmsImageMap);// 将临时缓存内容放入所用缓存
			xcStuSmsImageMap.clear();// 清空临时缓存

			Constant.sms = false;
		} else {
			xcsmsVTList = sendXcSmsDAO.getStuSMSParamVTAdd(lastSyncDate);

			Iterator it = xcsmsVTList.iterator();
			while (it.hasNext()) {

				if (xcStuSmsVTMap != null && xcStuSmsVTMap.size() > 0) {
					XcStuSmsVTBean vb = (XcStuSmsVTBean) it.next();

					if (vb.getValid_flag().equals("1")) {
						vb.setEvent_type("0000000000");
					}
					for (int i = 0; i < 7; i++) {// 循环事件类型
						eventType = Integer.parseInt(vb.getEvent_type()
								.substring(i, i + 1));// 得到类型
						vb.setEt(String.valueOf(i));
						key = vb.getStu_id() + vb.getEt();
						if (xcStuSmsVTMap.containsKey(key)) {
							keylist = xcStuSmsVTMap.get(key);
							if (eventType == 1) {
								keylist.add(vb);
								addXcStuSmsIntoCacheVersionTwo(key, keylist);
								for (int j = 0; j < keylist.size(); j++) {
									XcStuSmsVTBean xvb = (XcStuSmsVTBean) keylist
											.get(j);
									if (xvb.getCell_number().equals(
											vb.getCell_number())) {
										keylist.remove(xvb);
									}
								}

							}
							for (int j = 0; j < keylist.size(); j++) {
								XcStuSmsVTBean xvb = (XcStuSmsVTBean) keylist
										.get(j);
								if (eventType == 0) {
									keylist.remove(xvb);
								}
							}
						} else {
							if (eventType == 1) {
								keylist = new ArrayList();
								keylist.add(vb);
								addXcStuSmsIntoCacheVersionTwo(key, keylist);
							}
						}
					}

				} else {

					XcStuSmsVTBean vb = (XcStuSmsVTBean) it.next();
					for (int i = 0; i < 7; i++) {// 循环事件类型
						eventType = Integer.parseInt(vb.getEvent_type()
								.substring(i, i + 1));// 得到类型
						if (eventType == 1) {// 如果类型为1，进行添加缓存操作
							vb.setEt(String.valueOf(i));
							key = vb.getStu_id() + vb.getEt();
							if (xcStuSmsImageMap != null
									&& xcStuSmsImageMap.size() > 0) {
								if (xcStuSmsImageMap.containsKey(key)) {
									keylist = xcStuSmsImageMap.get(key);
									keylist.add(vb);
								} else {
									keylist = new ArrayList();
									keylist.add(vb);
								}
							} else {
								keylist = new ArrayList();
								keylist.add(vb);
							}
							addXcStuSmsIntoCacheImage(key, keylist);
						}
					}
					xcStuSmsVTMap.putAll(xcStuSmsImageMap);// 将临时缓存内容放入所用缓存
					xcStuSmsImageMap.clear();// 清空临时缓存
				}

			}
			Constant.sms = false;
		}

		log.info(NAME + "本次启动共加载" + xcStuSmsVTMap.size() + "个V2.0学生短信配置信息");

		lastIncremSyncDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		lastSyncDate = sdf.format(lastIncremSyncDate);

		log.debug(NAME + "加载V2.0学生短信配置的时间为：" + lastIncremSyncDate);
		log.debug(NAME + "V2.0学生短信配置信息加载完毕。");

		log.info(NAME + "当前V2.0学生短信配置信息缓存大小：" + xcStuSmsVTMap.size());
	}

	/**
	 * 缓存站点信息
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void SiteCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[SiteCacheInit]");
		xcsiteList = sendXcSmsDAO.getSiteParam();
		if (site) {
			log.debug(NAME + "开始加载站点全量缓存。。");
			Iterator it = xcsiteList.iterator();
			while (it.hasNext()) {
				XcSiteBean xsb = (XcSiteBean) it.next();
				if (xsb.getSite_id().indexOf(" ") == -1) {
					addXcSiteIntoCache(xsb);
				} else {
					log.info(NAME + "," + xsb.getSite_id() + "非法，不加载到站点缓存中");
				}
			}
			site = false;
		} else {
			Iterator it = xcsiteList.iterator();
			int find = 0;
			if (xcsiteMap != null && xcsiteMap.size() > 0) {
				Set<String> site = xcsiteMap.keySet();
				Iterator<String> its = site.iterator();
				List list = new ArrayList();
				while (its.hasNext()) {
					String stu_id = its.next();
					for (int i = 0; i < xcsiteList.size(); i++) {
						XcSiteBean sb = xcsiteList.get(i);
						if (!stu_id.equals(Constant.SITE + sb.getSite_id())) {
							find = 0;
						} else {
							find = 1;
							break;
						}
					}
					if (find == 0) {
						list.add(stu_id);
					}
				}
				if (list != null && list.size() > 0) {
					this.delSiteListFromCache(list);
				}
				while (it.hasNext()) {
					XcSiteBean vb = (XcSiteBean) it.next();
					if (vb.getSite_id().indexOf(" ") == -1) {
						addXcSiteIntoCache(vb);
					} else {
						log.info(NAME + "," + vb.getSite_id() + "非法，不加载到站点缓存中");
					}
				}
			} else {
				while (it.hasNext()) {
					XcSiteBean vb = (XcSiteBean) it.next();
					if (vb.getSite_id().indexOf(" ") == -1) {
						addXcSiteIntoCache(vb);
					} else {
						log.info(NAME + "," + vb.getSite_id() + "非法，不加载到站点缓存中");
					}
				}
			}
		}

		log.info(NAME + "本次启动共加载" + xcsiteMap.size() + "个站点信息");

		lastIncremSyncDate = new Date();
		log.debug(NAME + "加载站点信息的时间为：" + lastIncremSyncDate);
		log.debug(NAME + "站点信息加载完毕。");
		log.info(NAME + "当前站点信息缓存大小：" + xcsiteMap.size());
	}

	/**
	 * 缓存学生信息
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void xcstudentCache() {
		if (bool == true) {

			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
			MDC.put("modulename", "[studentInit]");
			if (Constant.isfirststudentload.equals("true")) {
				String systime = sendXcSmsDAO.getSysTime();
				Constant.upd_student_cache_time = systime;
				xcstudentList = sendXcSmsDAO.getStudentParam();
				if (xcstudentList != null && xcstudentList.size() > 0) {
					log.debug(NAME + "开始加载学生全量缓存。。");
					Iterator it = xcstudentList.iterator();
					while (it.hasNext()) {
						XcStudentBean sb = (XcStudentBean) it.next();
						if (sb.getStu_id().indexOf(" ") == -1) {
							addStudentIntoCache(sb);
						} else {
							log.info(NAME + "," + sb.getStu_id()
									+ "非法，不加载到学生缓存中");
						}
					}
					log
							.info(NAME + "本次启动共加载" + xcstudentMap.size()
									+ "个学生基本信息");
					Constant.isfirststudentload = "false";
				} else {
					return;
				}
			} else {
				if (count == Integer.parseInt(Config.props
						.getProperty("allstudengCacheTime"))) {
					bool = false;
					int find = 0;
					log.debug(NAME + "开始加载学生全量缓存。。");
					xcstudentList = sendXcSmsDAO.getStudentParam();
					if (xcstudentList != null && xcstudentList.size() > 0) {
						Iterator it = xcstudentList.iterator();
						while (it.hasNext()) {
							XcStudentBean sb = (XcStudentBean) it.next();
							addStudentIntoCache(sb);
//							addStudentIdIntoCache(sb);
						}
					}
					if (xcstudentMap != null && xcstudentMap.size() > 0) {
						Set set = xcstudentMap.keySet();
						Iterator itm = set.iterator();
						List list = new ArrayList();
						while (itm.hasNext()) {
							String stu_id = (String) itm.next();
							for (int i = 0; i < xcstudentList.size(); i++) {
								if (stu_id.indexOf(" ") != -1) {
									continue;
								}
								if (!stu_id.equals(Constant.STUDENT
										+ xcstudentList.get(i).getStu_id())) {
									find = 0;
								} else {
									find = 1;
									break;
								}
							}
							if (find == 0) {
								list.add(stu_id);
							}
						}
						if(list!=null&&list.size()>0){
							delStuFromList(list);	
						}
					}
					log.info(NAME + "本次启动共加载" + xcstudentMap.size()
									+ "个学生基本信息");

					// 2小时执行一次全量加载后，将计时器清0
					count = 0;
					bool = true;
				} else {
					count++;
					log.debug(NAME + "开始加载学生增减量缓存。。");
					/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
					xcstudentList = sendXcSmsDAO
							.getAllStudentParam(Constant.upd_student_cache_time);
					if (xcstudentMap != null && xcstudentMap.size() > 0) {
						Iterator it = xcstudentList.iterator();
						while (it.hasNext()) {
							XcStudentBean sb = (XcStudentBean) it.next();
							if (sb.getStu_id().indexOf(" ") == -1) {
								if (sb.getValid_flag().equals("1")) {
									if (checkExisted(Constant.STUDENT
											+ sb.getStu_id())) {
										delStuFromCache(Constant.STUDENT
												+ sb.getStu_id());
									}
								} else {
									addStudentIntoCache(sb);
								}
							} else {
								log.info(NAME + "," + sb.getStu_id()
										+ "非法，不加载到学生信息缓存中");
							}
						}
					} else {
						Iterator it = xcstudentList.iterator();
						while (it.hasNext()) {
							XcStudentBean sb = (XcStudentBean) it.next();
							if (sb.getStu_id().indexOf(" ") == -1) {
								addStudentIntoCache(sb);
							} else {
								log.info(NAME + "," + sb.getStu_id()
										+ "非法，不加载到学生信息缓存中");
							}
						}
					}
					log.info(NAME + "本次启动共加载" + xcstudentList.size()
							+ "个学生基本信息");

				}

				lastIncremSyncDate = new Date();

				log.debug(NAME + "加载学生基本信息的时间为：" + lastIncremSyncDate);

				log.debug(NAME + "学生信息加载完毕。");
			}

		}
		log.info(NAME + "当前学生信息缓存大小：" + xcstudentMap.size());
	}

	/**
	 * 缓存学生编号信息
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void xcstudentIdCache() {
		if (stuidbool == true) {
			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
			MDC.put("modulename", "[studentidInit]");
			String systime = sendXcSmsDAO.getSysTime();
			if (Constant.isfirststudentidload.equals("true")) {
				xcstudentList = sendXcSmsDAO.getStudentParam();
				if (xcstudentList != null && xcstudentList.size() > 0) {
					log.debug(NAME + "开始加载学生编号全量缓存。。");
					Iterator it = xcstudentList.iterator();
					while (it.hasNext()) {
						XcStudentBean sb = (XcStudentBean) it.next();
						if (sb.getStu_id().indexOf(" ") == -1) {
							addStudentIdIntoCache(sb);
						} else {
							log.info(NAME + "," + sb.getStu_id()
									+ "非法，不加载到学生编号缓存中");
						}
					}
					log.info(NAME + "本次启动共加载" + xcstudentidMap.size()
							+ "个学生编号基本信息");
					Constant.isfirststudentidload = "false";
				} else {
					return;
				}
			} else {
				if (count == Integer.parseInt(Config.props
						.getProperty("allstudengCacheTime"))) {
					stuidbool = false;
					int find = 0;
					log.debug(NAME + "开始加载学生编号全量缓存。。");
					xcstudentList = sendXcSmsDAO.getStudentParam();
					if (xcstudentList != null && xcstudentList.size() > 0) {
						Iterator it = xcstudentList.iterator();
						while (it.hasNext()) {
							XcStudentBean sb = (XcStudentBean) it.next();
							addStudentIdIntoCache(sb);
						}
					}
					if (xcstudentidMap != null && xcstudentidMap.size() > 0) {
						Set set = xcstudentidMap.keySet();
						Iterator itm = set.iterator();
						List list = new ArrayList();
						while (itm.hasNext()) {
							String stu_card_id = (String) itm.next();
							log.debug("ssdfadfadf:"+stu_card_id);
							for (int i = 0; i < xcstudentList.size(); i++) {
								if (stu_card_id.indexOf(" ") != -1) {
									continue;
								}
								if (!stu_card_id
										.equals(Constant.STUDENT
												+ xcstudentList.get(i)
														.getStu_card_id())) {
									find = 0;
								} else {
									find = 1;
									break;
								}
							}
							if (find == 0) {
								list.add(stu_card_id);
							}
						}
						if(list!=null&&list.size()>0){
							delStuidFromList(list);	
						}
					}
					log.info(NAME + "本次启动共加载" + xcstudentidMap.size()
							+ "个学生编号信息");

					// 2小时执行一次全量加载后，将计时器清0
					count = 0;
					stuidbool = true;
				} else {
					count++;
					log.debug(NAME + "开始加载学生增减量缓存。。");
					/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
					xcstudentList = sendXcSmsDAO
							.getAllStudentParam(Constant.upd_student_cache_time);
					if (xcstudentidMap != null && xcstudentidMap.size() > 0) {
						Iterator it = xcstudentList.iterator();
						while (it.hasNext()) {
							XcStudentBean sb = (XcStudentBean) it.next();
							if (sb.getStu_card_id().indexOf(" ") == -1) {
								if (sb.getValid_flag().equals("1")) {
									if (checkExistedStuId(Constant.STUDENT
											+ sb.getStu_card_id())) {
										delStuidFromCache(Constant.STUDENT
												+ sb.getStu_card_id());
									}
								} else {
									addStudentIdIntoCache(sb);
								}
							} else {
								log.info(NAME + "," + sb.getStu_card_id()
										+ "非法，不加载到学生编号缓存中");
							}
						}
					} else {
						Iterator it = xcstudentList.iterator();
						while (it.hasNext()) {
							XcStudentBean sb = (XcStudentBean) it.next();
							if (sb.getStu_card_id().indexOf(" ") == -1) {
								addStudentIdIntoCache(sb);
							} else {
								log.info(NAME + "," + sb.getStu_card_id()
										+ "非法，不加载到学生编号缓存中");
							}
						}
					}
					log.info(NAME + "本次启动共加载" + xcstudentList.size()
							+ "个学生编号信息");

				}
				
				lastIncremSyncDate = new Date();

				log.debug(NAME + "加载学生编号信息的时间为：" + lastIncremSyncDate);

				log.debug(NAME + "学生编号信息加载完毕。");
			}
			Constant.upd_student_cache_time = systime;
		}
		log.debug(NAME + "当前学生编号信息缓存：" + xcstudentidMap);
		log.info(NAME + "当前学生编号信息缓存大小：" + xcstudentidMap.size());
	}

	/**
	 * 缓存线路站点配置信息
	 * 
	 * @param
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("unchecked")
	public void xcRouteSiteCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcRouteSiteCacheInit]");
		xcroutesiteList = sendXcSmsDAO.getRouteSiteParam();
		if (Constant.sms) {
			log.debug(NAME + "开始加载线路站点信息全量缓存。。");
			Iterator it = xcroutesiteList.iterator();

			while (it.hasNext()) {
				RouteSiteBean rsb = (RouteSiteBean) it.next();
				if (rsb.getRoute_id().indexOf(" ") == -1) {
					addXcRouteSiteIntoCache(rsb);
				} else {
					log.info(NAME + "," + rsb.getRoute_id()
							+ "非法，不加载到线路站点关系缓存中");
				}
			}
			Constant.sms = false;
		} else {
			Iterator it = xcroutesiteList.iterator();
			int find = 0;
			if (xcroutesiteMap != null && xcroutesiteMap.size() > 0) {
				Set<String> rsid = xcroutesiteMap.keySet();
				Iterator<String> its = rsid.iterator();
				List list = new ArrayList();
				while (its.hasNext()) {
					String rs_id = its.next();
					for (int i = 0; i < xcroutesiteList.size(); i++) {
						RouteSiteBean rsb = xcroutesiteList.get(i);
						if (!rs_id.equals(rsb.getRoute_id() + rsb.getSite_id())) {
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
					delroutesiteListFromCache(list);
				}
				while (it.hasNext()) {
					RouteSiteBean rsb = (RouteSiteBean) it.next();
					if (rsb.getRoute_id().indexOf(" ") == -1) {
						addXcRouteSiteIntoCache(rsb);
					} else {
						log.info(NAME + "," + rsb.getRoute_id()
								+ "非法，不加载到线路站点关系缓存中");
					}
				}
			} else {
				while (it.hasNext()) {
					RouteSiteBean rsb = (RouteSiteBean) it.next();
					if (rsb.getRoute_id().indexOf(" ") == -1) {
						addXcRouteSiteIntoCache(rsb);
					} else {
						log.info(NAME + "," + rsb.getRoute_id()
								+ "非法，不加载到线路站点关系缓存中");
					}
				}
			}
		}
		log.info(NAME + "本次启动共加载" + xcroutesiteMap.size() + "个线路站点关系信息");

		lastIncremSyncDate = new Date();
		log.debug(NAME + "加载线路站点关系的时间为：" + lastIncremSyncDate);
		log.debug(NAME + "线路站点关系信息加载完毕。");

		log.info(NAME + "当前线路站点关系缓存大小：" + xcroutesiteMap.size());
	}

	private void delroutesiteListFromCache(List<String> list) {
		for (String str : list) {
			xcroutesiteMap.remove(str);
		}

	}

	/**
	 * 缓存站点订购信息
	 * 
	 * @param
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public void xcVssCache() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcVssCacheInit]");
		xcvssList = sendXcSmsDAO.getVssParam();
		if (Constant.sms) {
			log.debug(NAME + "开始加载站点订购信息全量缓存。。");
			Iterator it = xcvssList.iterator();

			while (it.hasNext()) {
				XcvsseBean xsb = (XcvsseBean) it.next();
				if (xsb.getVehicle_vin().indexOf(" ") == -1) {
					addVssIntoCache(xsb);
				} else {
					log.info(NAME + "," + xsb.getVehicle_vin()
							+ "非法，不加载到站点订购缓存中");
				}
			}
			Constant.sms = false;
		} else {
			Iterator it = xcvssList.iterator();
			int find = 0;
			if (xcvssMap != null && xcvssMap.size() > 0) {
				Set<String> rsid = xcvssMap.keySet();
				Iterator<String> its = rsid.iterator();
				List list = new ArrayList();
				while (its.hasNext()) {
					String rs_id = its.next();
					for (int i = 0; i < xcvssList.size(); i++) {
						XcvsseBean xsb = xcvssList.get(i);
						if (!rs_id.equals(xsb.getStudent_id()+xsb.getVss_state()+xsb.getSite_updown())) {
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
					delvssListFromCache(list);
				}
				while (it.hasNext()) {
					XcvsseBean xsb = (XcvsseBean) it.next();
					if (xsb.getVehicle_vin().indexOf(" ") == -1) {
						addVssIntoCache(xsb);
					} else {
						log.info(NAME + "," + xsb.getVehicle_vin()
								+ "非法，不加载到站点订购缓存中");
					}
				}
			} else {
				while (it.hasNext()) {
					XcvsseBean xsb = (XcvsseBean) it.next();
					if (xsb.getVehicle_vin().indexOf(" ") == -1) {
						addVssIntoCache(xsb);
					} else {
						log.info(NAME + "," + xsb.getVehicle_vin()
								+ "非法，不加载到站点订购缓存中");
					}
				}
			}
		}
		log.info(NAME + "本次启动共加载" + xcvssMap.size() + "个站点订购信息");

		lastIncremSyncDate = new Date();
		log.debug(NAME + "加载站点订购信息的时间为：" + lastIncremSyncDate);
		log.debug(NAME + "线路站点订购信息加载完毕。");

		log.info(NAME + "当前站点订购信息缓存大小：" + xcvssMap.size());
	}

	private void delvssListFromCache(List<String> list) {
		for (String str : list) {
			xcvssMap.remove(str);
		}

	}

	
	/**
	 * 缓存V2.0站点订购信息
	 * 
	 * @param
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public void xcVssCacheVT() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[xcVssCacheInit]");
		xcvssList = sendXcSmsDAO.getVssParam();
		if (Constant.sms) {
			log.debug(NAME + "开始加载V2.0站点订购信息全量缓存。。");
			Iterator it = xcvssList.iterator();
            while (it.hasNext()) {
				XcvsseBean xsb = (XcvsseBean) it.next();
				if (xsb.getVehicle_vin().indexOf(" ") == -1) {
					addVssIntoCacheVT(xsb);
				} else {
					log.info(NAME + "," + xsb.getVehicle_vin()
							+ "非法，不加载到V2.0站点订购缓存中");
				}
			}
			Constant.sms = false;
		} else {
			Iterator it = xcvssList.iterator();
			int find = 0;
			if (xcvssvtMap != null && xcvssvtMap.size() > 0) {
				Set<String> rsid = xcvssvtMap.keySet();
				Iterator<String> its = rsid.iterator();
				List list = new ArrayList();
				while (its.hasNext()) {
					String rs_id = its.next();
					for (int i = 0; i < xcvssList.size(); i++) {
						XcvsseBean xsb = xcvssList.get(i);
						if (!rs_id.equals(xsb.getStudent_id()+xsb.getRoute_id()+xsb.getVss_state()+xsb.getSite_updown())) {
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
					delvssListFromCacheVT(list);
				}
				while (it.hasNext()) {
					XcvsseBean xsb = (XcvsseBean) it.next();
					if (xsb.getVehicle_vin().indexOf(" ") == -1) {
						addVssIntoCacheVT(xsb);
					} else {
						log.info(NAME + "," + xsb.getVehicle_vin()
								+ "非法，不加载到V2.0站点订购缓存中");
					}
				}
			} else {
				while (it.hasNext()) {
					XcvsseBean xsb = (XcvsseBean) it.next();
					if (xsb.getVehicle_vin().indexOf(" ") == -1) {
						addVssIntoCacheVT(xsb);
					} else {
						log.info(NAME + "," + xsb.getVehicle_vin()
								+ "非法，不加载到V2.0站点订购缓存中");
					}
				}
			}
		}
		log.info(NAME + "本次启动共加载" + xcvssvtMap.size() + "个V2.0站点订购信息");

		lastIncremSyncDate = new Date();
		log.debug(NAME + "加载V2.0站点订购信息的时间为：" + lastIncremSyncDate);
		log.debug(NAME + "V2.0线路站点订购信息加载完毕。");

		log.info(NAME + "当前V2.0站点订购信息缓存大小：" + xcvssvtMap.size());
	}	


	private void delvssListFromCacheVT(List<String> list) {
		for (String str : list) {
			xcvssvtMap.remove(str);
		}

	}	
	
	/**
	 * 缓存企业订购短信信息
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void xcEnterpriseCache() {
		

			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
			MDC.put("modulename", "[enterpriseInit]");
			
				xcenterpriseList = sendXcSmsDAO.getEnterPriseParam();
				if (xcenterpriseList != null && xcenterpriseList.size() > 0) {
					log.debug(NAME + "开始加载企业订购短信全量缓存。。");
					Iterator it = xcenterpriseList.iterator();
					while (it.hasNext()) {
						EnterPriseBean epb = (EnterPriseBean) it.next();
						if (epb.getEnterprise_id().indexOf(" ") == -1) {
							addEnterpriseIntoCache(epb);
						} else {
							log.info(NAME + "," + epb.getEnterprise_id()
									+ "非法，不加载到企业订购短信缓存中");
						}
					}
				} 
			
					log.info(NAME + "本次启动共加载" + xcenterpriseList.size()
							+ "个企业订购短信信息");
                lastIncremSyncDate = new Date();

				log.debug(NAME + "加载企业订购短信信息的时间为：" + lastIncremSyncDate);

				log.debug(NAME + "企业订购短信信息加载完毕。");
			
		log.info(NAME + "当前企业订购短信信息缓存大小：" + xcenterpriseMap.size());
	}	
	

	/**
	 * 查看某学生信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExisted(String stuid) {
		return xcstudentMap.containsKey(stuid);
	}

	/**
	 * 查看某学生信息在缓存中是否存在
	 * 
	 * @param vin
	 * @return
	 */
	public boolean checkExistedStuId(String stucardid) {
		return xcstudentidMap.containsKey(stucardid);
	}

	/**
	 * 从缓存中删除某个学生编号基本信息
	 * 
	 * @param vin
	 */
	public synchronized void delStuidFromCache(String stucardid) {
		log.debug(NAME + "开始从缓存中删除" + (stucardid) + "的缓存记录。");
		xcstudentidMap.remove(stucardid);
		log.debug(NAME + "从缓存中删除" + (stucardid) + "的缓存记录完毕！");
	}
	
	public synchronized void delStuidFromList(List<String> stucardid) {
		log.debug(NAME + "开始从缓存中删除学生编号缓存记录。");
		for(String str :stucardid){
			xcstudentidMap.remove(str);
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
		xcstudentMap.remove(stuid);
		log.debug(NAME + "从缓存中删除" + (stuid) + "的缓存记录完毕！");
	}

	public synchronized void delStuFromList(List<String> stuid) {
		log.debug(NAME + "开始从缓存中删除学生信息缓存记录。");
		for(String str :stuid){
			xcstudentMap.remove(str);
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
		xcStuSmsMap.remove(stuid);
		log.debug(NAME + "从缓存中删除" + (stuid) + "的缓存记录完毕！");
	}

	public synchronized void delSiteListFromCache(List<String> list) {
		for (String str : list) {
			xcsiteMap.remove(str);
		}
	}

	public synchronized void delSiteFromCache(String siteid) {
		log.debug(NAME + "开始从缓存中删除" + (siteid) + "的缓存记录。");
		xcsiteMap.remove(siteid);
		log.debug(NAME + "从缓存中删除" + (siteid) + "的缓存记录完毕！");
	}

	// 在内存中添加学生信息
	private void addStudentIntoCache(XcStudentBean sb) {
		xcstudentMap.put(Constant.STUDENT + sb.getStu_id(), sb);
		log.debug(NAME + "已将" + (Constant.STUDENT + sb.getStu_id())
				+ "的学生记录加入缓存！");
	}

	// 在内存中添加学生编号、卡号信息
	private void addStudentIdIntoCache(XcStudentBean sb) {
		xcstudentidMap.put(Constant.STUDENT + sb.getStu_card_id(), sb
				.getStu_id());
		log.debug(NAME + "已将" + (Constant.STUDENT + sb.getStu_card_id())
				+ "的学生编号记录加入缓存！");
	}

	// 在缓存中添加学生短信配置信息
	public synchronized void addXcStuSmsIntoCache(String key,
			List<XcStuSmsBean> xcsmsList2) {
		xcStuSmsMap.put(key, xcsmsList2);
		log.debug(NAME + "已将" + (xcsmsList2.get(0).getStu_id())
				+ "的学生短信配置记录加入缓存！");
	}

	// V2.0在缓存中添加学生短信配置信息
	public synchronized void addXcStuSmsIntoCacheVersionTwo(String key,
			List<XcStuSmsVTBean> smskeyList2) {
		xcStuSmsVTMap.put(key, smskeyList2);
		log.info(NAME + "已将" + (smskeyList2.get(0).getStu_id())
				+ "的学生短信配置记录加入缓存！");
	}

	// V2.0在虚拟缓存中添加学生短信配置信息
	public synchronized void addXcStuSmsIntoCacheImage(String key,
			List<XcStuSmsVTBean> smskeyList2) {
		xcStuSmsImageMap.put(key, smskeyList2);
		log.info(NAME + "已将"
				+ (smskeyList2.get(0).getStu_id() + smskeyList2.get(0).getEt())
				+ "的学生短信配置记录加入缓存！");
	}

	// 在缓存中添加站点信息
	public synchronized void addXcSiteIntoCache(XcSiteBean xcSiteBean) {
		xcsiteMap.put(Constant.SITE + xcSiteBean.getSite_id(), xcSiteBean);
		log.debug(NAME + "已将" + (Constant.SITE + xcSiteBean.getSite_id())
				+ "的站点记录加入缓存！");
	}

	// 在缓存中线路站点关系表信息
	public synchronized void addXcRouteSiteIntoCache(RouteSiteBean routeSiteBean) {
		xcroutesiteMap.put(routeSiteBean.getRoute_id()
				+ routeSiteBean.getSite_id(), routeSiteBean);
		log.debug(NAME + "已将" + (routeSiteBean.getRoute_id())
				+ "的线路站点关系记录加入缓存！");
	}

	// 在缓存加入站点订购关系信息
	public synchronized void addVssIntoCacheVT(XcvsseBean xcvsseBean) {
		xcvssvtMap.put(xcvsseBean.getStudent_id() + xcvsseBean.getRoute_id()+ xcvsseBean.getVss_state()
				+ xcvsseBean.getSite_updown(), xcvsseBean);
		log.debug(NAME + "已将" + (xcvsseBean.getStudent_id())
						+ "的线路站点关系记录加入缓存！");
	}
	
	// 在缓存加入站点订购关系信息
	public synchronized void addVssIntoCache(XcvsseBean xcvsseBean) {
		xcvssMap.put(xcvsseBean.getStudent_id()+ xcvsseBean.getVss_state()
				+ xcvsseBean.getSite_updown(), xcvsseBean);
		log.debug(NAME + "已将" + (xcvsseBean.getStudent_id())
						+ "的模式2线路站点关系记录加入缓存！");
	}	
	
	private void addEnterpriseIntoCache(EnterPriseBean epb) {
		xcenterpriseMap.put(epb.getEnterprise_id(), epb);
		log.debug(NAME + "已将" + (epb.getEnterprise_id())
						+ "的企业短信配置记录加入缓存！");		
	}
	public IXcSmsDAO getSendXcSmsDAO() {
		return sendXcSmsDAO;
	}

	public void setSendXcSmsDAO(IXcSmsDAO sendXcSmsDAO) {
		this.sendXcSmsDAO = sendXcSmsDAO;
	}

	public List<XcStuSmsBean> getValue(String key) {
		return (List<XcStuSmsBean>) xcStuSmsMap.get(key);
	}

	public Collection<List<XcStuSmsBean>> getValues() {
		return xcStuSmsMap.values();
	}



	public XcStudentBean getStudentValue(String key) {
		return xcstudentMap.get(key);
	}

	public Collection<XcStudentBean> getStudentValues() {
		return xcstudentMap.values();
	}

	public XcSiteBean getSiteValue(String key) {
		return xcsiteMap.get(key);
	}

	public Collection<XcSiteBean> getSiteValues() {
		return xcsiteMap.values();
	}

	public RouteSiteBean getRouteSiteValue(String key) {
		return xcroutesiteMap.get(key);
	}

	public Collection<RouteSiteBean> getRouteSiteValues() {
		return xcroutesiteMap.values();
	}

	public XcvsseBean getVssValue(String key) {
		return xcvssMap.get(key);
	}

	public Collection<XcvsseBean> getVssValues() {
		return xcvssMap.values();
	}

	public XcvsseBean getVssValueVt(String key) {
		return xcvssvtMap.get(key);
	}

	public Collection<XcvsseBean> getVssValueVt() {
		return xcvssvtMap.values();
	}	
	
	public String getStudentIdValue(String key) {
		log.debug(NAME+"error xcstudentidMap="+xcstudentidMap);
		return xcstudentidMap.get(key);
	}

	public Collection<String> getStudentIdValues() {
		return xcstudentidMap.values();
	}

	public List<XcStuSmsVTBean> getXcSmsVTValue(String key) {
		return (List<XcStuSmsVTBean>) xcStuSmsVTMap.get(key);
	}

	public Collection<List<XcStuSmsVTBean>> getXcSmsVTValueValues() {
		return xcStuSmsVTMap.values();
	}
	//企业短信配置缓存	
	public EnterPriseBean getXcEntepriseValue(String key) {
		return  xcenterpriseMap.get(key);
	}

	public Collection<EnterPriseBean> getXcEntepriseValues() {
		return xcenterpriseMap.values();
	}

	

}
