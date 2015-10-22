package com.neusoft.pushmessage.manage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.bean.MobileInfo;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.domain.PMRule;
import com.neusoft.clw.vncs.service.ActiveCoreService;
import com.neusoft.clw.vncs.util.StringUtil;
import com.neusoft.mobile.bean.MessageBean;
import com.neusoft.mobile.dao.impl.PushDAO;
import com.neusoft.mobile.manage.MobileInfoCacheManager;
import com.neusoft.mobile.manage.PMRuleCacheManager;
import com.neusoft.pushmessage.buffer.PushBean;
import com.neusoft.pushmessage.buffer.PushBuffer;
import com.neusoft.pushmessage.util.CopyBean;

public class PushManager {
	private   Logger log = LoggerFactory.getLogger(PushManager.class);        
	private static final String NAME = "<PushManager>";
	private static final PushManager qManager = new PushManager();
	public static boolean runflag = true;
	@SuppressWarnings("unused")
	private static String date = null;
	private static SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	private PushManager() {
	}

	public static PushManager getInstance() {
		return qManager;
	}
	
	public void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[pushInit]");
		boolean b = false;
		try {
			int coreActive = Integer.parseInt(Config.props
					.getProperty("core.active.time"));
			b = ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID, coreActive);
			if (!b) {
				log.info(NAME+"本机不是推送处理服务器。");
				return;
			}
			log.info(NAME+" 推送处理开始！runflag:" + runflag);
			if (runflag) {
				runflag = false;// 设置独占运行模式
				date = pathDf.format(new Date());
				PushDAO pushDAO = (PushDAO) SpringBootStrap.getInstance().getBean("pushDAO");
				PMRule rule = null;
//				List<PushBean> pushlist = null;
//				List<AlarmBean> alarmlist = pushDAO.getPushAlarmInfo();
//				if(alarmlist!=null&&alarmlist.size()>0){
//					pushlist = new ArrayList<PushBean>();
//					for(AlarmBean alarm:alarmlist){
//						rule = PMRuleCacheManager.getInstance().getValue(Constant.PMRULE+alarm.getUser_id());
//						if(rule!=null&&!rule.equals("")){
//							if(rule.getOn_off().equals("1")){
//								PushBean push = new PushBean();
//								push.setAlarmBean(alarm);
//								push.setType("1");
//								pushlist.add(push);
//							}
//						}
//					}
//					PushBuffer.getInstance().add(pushlist);
//					log.info(NAME+"告警信息已经存入推送队列，等待发送");
//				}else{
//					log.info(NAME+"未获取到推送失败的告警信息!");
//				}
//				
//				
//				List<PhotoBean> photolist = pushDAO.getPushPhotoInfo();	
//				if(photolist!=null&&photolist.size()>0){
//					pushlist = new ArrayList<PushBean>();
//					for(PhotoBean photo:photolist){
//						rule = PMRuleCacheManager.getInstance().getValue(Constant.PMRULE+photo.getUser_id());
//						if(rule!=null&&!rule.equals("")){
//							if(rule.getOn_off().equals("1")){
//								PushBean push = new PushBean();
//								push.setPhotoBean(photo);
//								push.setType("2");
//								pushlist.add(push);
//							}
//						}
//					}
//					PushBuffer.getInstance().add(pushlist);
//					log.info(NAME+"照片信息已经存入推送队列，等待发送");
//				}else{
//					log.info(NAME+"未获取到推送失败的照片信息!");
//				}
				
//				String systime = pushDAO.getSysTime();
//				ConfigParamDAO configParamDao = (ConfigParamDAO) SpringBootStrap.getInstance().getBean("configParamDao");
//				ConfigParamBean bean = configParamDao.getConfigParamBean("lastpushtime");
//				String time = bean.getParam_value();
//				if(time==null||time.equals("")){
//					time = systime;
//				}
//				QuartzUpdateSEQ(pushDAO, time, systime);
				//推送历史失败的消息
				OldMessage(pushDAO,rule);
				//推送新消息
				NewMessage(pushDAO, rule);
				
//				pushDAO.updateParamCfg(Constant.LASTPUSHTIME, systime);
				runflag = true;
			}
		}catch(Exception e) {
			runflag = true;
			log.error(NAME+"错误：" + e.toString());
			e.printStackTrace();
		}
		log.info(NAME+" 本次推送处理结束！ runflag:" + runflag);
	}
	
	
	private void OldMessage(PushDAO pushDAO, PMRule rule) {
		List<MessageBean> list = pushDAO.getPushMessageInfo();
//		List alist = new ArrayList();
		if(list!=null&&list.size()>0){
			for(MessageBean message:list){
				if(message.getMsg_type()==0){
					rule = PMRuleCacheManager.getInstance().getValue(message.getUser_id()+"01");
				}else if(message.getMsg_type()==1){
					rule = PMRuleCacheManager.getInstance().getValue(message.getUser_id()+"02");
				}
				if(rule!=null&&!rule.equals("")){
					if(rule.getOn_off().equals("1")){
						PushBean bean = new PushBean();
						message.setGrant_type("2");
						message.setVersion_id(Constant.VERSION_ID);
						message.setP_s(Config.props.getProperty("push_switch"));
//						message.setType("2");
						bean.setMessageBean(message);
						bean.setType("2");
//						alist.add(bean);
						PushBuffer.getInstance().add(bean);
					}else{
//						log.info("针对消息ID:"+message.getMsg_id()+",该用户"+message.getUser_id()+"推送开关未打开");
					}
				}else{
					log.info("针对消息ID:"+message.getMsg_id()+",该用户"+message.getUser_id()+"未设置推送配置");
				}
			}
			log.info(NAME+"政策法规推送失败信息已经存入推送队列，等待发送");
		}else{
			log.info(NAME+"没有政策法规推送失败信息需要推送");
		}
		list = null;
//		alist = null;
	}

	public PushBean messageBeanToPushBean(MobileInfo info,Long id,Long msg_index, long type) throws Exception{
		MessageBean mbean = new MessageBean();
		mbean.setUser_id(info.getUser_id())	;
		mbean.setClient_id(info.getClient_id());
		mbean.setMsg_id(id);
		mbean.setMsg_index(msg_index);
		mbean.setGrant_type("2");
		mbean.setVersion_id(Constant.VERSION_ID);
		mbean.setMsg_type(type);
		mbean.setP_s(Config.props.getProperty("push_switch"));
//		mbean.setType("1");
		MessageBean b = new MessageBean();
		CopyBean.CopyBeanToBean(mbean, b);
		PushBean pbean = new PushBean();
		pbean.setMessageBean(b);
		pbean.setType("2");
		return pbean;
	}
	
	public void QuartzUpdateSEQ(PushDAO pushDAO,String time,String systime){
		List<MessageBean> list = pushDAO.getNotPushMessageInfo(time, systime);
		if(list!=null&&list.size()>0){
			for(MessageBean index : list){
				pushDAO.updateMsg_index(index.getMsg_index());
			}
		}
	}
	
	public void NewMessage(PushDAO pushDAO,PMRule rule) throws Exception{
		List<MessageBean> alllist = pushDAO.getAllMessageInfo();
		if(alllist!=null&&alllist.size()>0){
			List<MobileInfo> mlist = (List<MobileInfo>) MobileInfoCacheManager.getInstance().SyncMobileInfoValue(Constant.ON, Constant.MOBILEINFO, null);
			if(mlist!=null&&mlist.size()>0){
				for(MessageBean bean:alllist){
//					pushDAO.updatePushPeople(mlist.size(), bean.getMsg_id());
					if(bean.getMsg_type()==0){
						selectMessage(bean, mlist, rule, "01");
					}else if(bean.getMsg_type()==1){
						selectMessage(bean, mlist, rule, "02");
					}else{
						log.info(NAME+"该信息类型错误！");
					}
					bean = null;
				}
			}else{
				log.info(NAME+"没有获取到用户信息");
			}
			mlist = null;
		}else{
			log.info(NAME+"没有获取到新闻或法规内容");
		}
		alllist = null;
	}
	
	private void selectMessage(MessageBean bean,List<MobileInfo> mlist,PMRule rule,String type) throws Exception{
		for(MobileInfo info : mlist){
			rule = PMRuleCacheManager.getInstance().getValue(info.getUser_id()+type);
			if(rule!=null&&!rule.equals("")){
				if(rule.getOn_off().equals("1")){
					if(info.getUser_msg_etag()==0){
						if(bean.getMsg_index()==0){
							continue;
						}
						PushBuffer.getInstance().add(messageBeanToPushBean(info,bean.getMsg_id(), bean.getMsg_index(),bean.getMsg_type()));
					}else{
						if((bean.getMsg_index() <= info.getUser_msg_etag())&&(!bean.getMsg_index().equals("0"))){
							continue;
						}else{
							PushBuffer.getInstance().add(messageBeanToPushBean(info,bean.getMsg_id(), bean.getMsg_index(),bean.getMsg_type()));
						}
					}
				}else{
//					log.info("针对消息ID:"+bean.getMsg_id()+",该用户"+info.getUser_id()+"推送开关未打开");
				}
			}else{
				log.info("针对消息ID:"+bean.getMsg_id()+",该用户"+info.getUser_id()+"未设置推送配置");
			}
			rule = null;
			info = null;
		}
	}
}
