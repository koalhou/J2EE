package com.neusoft.SchoolBus.vncs.manage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.SchoolBus.vncs.dao.IXcSmsDAO;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsBean;
import com.neusoft.SchoolBus.vncs.domain.XcStuSmsVTBean;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.vncs.util.StringUtil;

public class SmsOrderManager {
	private Logger log = LoggerFactory.getLogger(SmsOrderManager.class);

	private static final String NAME = "<smsOrderManager>";

	private static final SmsOrderManager smsOrderManager = new SmsOrderManager();
	private Date lastIncremSyncDate;
	private IXcSmsDAO sendXcSmsDAO;
	public static Map<String, List<XcStuSmsBean>> xcStuSmsMap;
	private String systime;
	private List<XcStuSmsVTBean> xcsmsVTList;
	public static Map<String, List<XcStuSmsVTBean>> xcStuSmsVTMap;
	public static Map<String, List<XcStuSmsVTBean>> xcStuSmsImageMap;


	private static boolean smsorder = true;

	public static SmsOrderManager getInstance() {
		return smsOrderManager;
	}

	private SmsOrderManager() {
		
		xcsmsVTList = new ArrayList<XcStuSmsVTBean>();
		xcStuSmsVTMap = new HashMap<String, List<XcStuSmsVTBean>>();
		xcStuSmsImageMap = new HashMap<String, List<XcStuSmsVTBean>>();

	}

	public void init() {
		 systime = sendXcSmsDAO.getSysTime();
         xcstuSmsCacheVersionTwo();
         
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
		System.out.println(Constant.upd_xd_cache_time);
		if (Constant.upd_xd_cache_time == null) {
			Constant.upd_xd_cache_time = systime;
		}
		System.out.println(Constant.upd_xd_cache_time);
		if (smsorder) {
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
			it = null;
			smsorder = false;
		} else {
			xcsmsVTList = sendXcSmsDAO.getStuSMSParamVTAdd(Constant.upd_xd_cache_time);

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
								for (int j = 0; j < keylist.size(); j++) {
									XcStuSmsVTBean xvb = (XcStuSmsVTBean) keylist
											.get(j);
									if (xvb.getCell_number().equals(
											vb.getCell_number())) {
										keylist.remove(xvb);
									}
								}
								keylist.add(vb);
								addXcStuSmsIntoCacheVersionTwo(key, keylist);	
							}
							for (int j = 0; j < keylist.size(); j++) {
								XcStuSmsVTBean xvb = (XcStuSmsVTBean) keylist
										.get(j);
								if (eventType == 0&&vb.getCell_number().equals(xvb.getCell_number())) {
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
			smsorder = false;
		}

		log.info(NAME + "本次启动共加载" + xcStuSmsVTMap.size() + "个V2.0学生短信配置信息");
		Constant.upd_xd_cache_time = systime;
		lastIncremSyncDate = new Date();

		log.debug(NAME + "加载V2.0学生短信配置的时间为：" + lastIncremSyncDate);
		log.debug(NAME + "V2.0学生短信配置信息加载完毕。");

		log.info(NAME + "当前V2.0学生短信配置信息缓存大小：" + xcStuSmsVTMap.size());
//		Set<String> rsid = xcStuSmsVTMap.keySet();
//		Iterator it = rsid.iterator();
//		while (it.hasNext()) {
//			String id = (String) it.next();
//			List<XcStuSmsVTBean> list = xcStuSmsVTMap.get(id);
//			for(XcStuSmsVTBean bean:list){
//				log.info(NAME+"xcStuSmsVTMap缓存内容：：：：key:"+id+",stu_id:"+bean.getStu_id()+",event_type:"+bean.getEvent_type()+",cell_number:"+bean.getCell_number()
//						+",relative_type:"+bean.getRelative_type());
//			}
//		}
	}



	

	

	



	// V2.0在缓存中添加学生短信配置信息
	public synchronized void addXcStuSmsIntoCacheVersionTwo(String key,
			List<XcStuSmsVTBean> smskeyList2) {
		xcStuSmsVTMap.put(key, smskeyList2);
		log.debug(NAME + "已将" + (smskeyList2.get(0).getStu_id())
				+ "的学生短信配置记录加入缓存！");
	}

	// V2.0在虚拟缓存中添加学生短信配置信息
	public synchronized void addXcStuSmsIntoCacheImage(String key,
			List<XcStuSmsVTBean> smskeyList2) {
		xcStuSmsImageMap.put(key, smskeyList2);
		log.debug(NAME + "已将"
				+ (smskeyList2.get(0).getStu_id() + smskeyList2.get(0).getEt())
				+ "的学生短信配置记录加入缓存！");
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

	public List<XcStuSmsVTBean> getXcSmsVTValue(String key) {
		return (List<XcStuSmsVTBean>) xcStuSmsVTMap.get(key);
	}

	public Collection<List<XcStuSmsVTBean>> getXcSmsVTValueValues() {
		return xcStuSmsVTMap.values();
	}

}
