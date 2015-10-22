package com.yutong.clw.quartz.managers;

import java.util.List;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yutong.clw.beans.xc.EnterpriseInfoBean;
import com.yutong.clw.beans.xc.StuInfoBean;
import com.yutong.clw.config.Config;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.sms.SmsSyncDAO;
import com.yutong.clw.service.ActiveCoreService;
import com.yutong.clw.service.buffer.sync.SyncBuffer;
import com.yutong.clw.service.buffer.sync.SyncClass;
import com.yutong.clw.utils.StringUtil;

/**
 * 定时向翔东同步企业、学生信息quartz
 * @author chenqiong
 *
 */
public class SyncManager {

	private Logger log = LoggerFactory.getLogger(SyncManager.class);

	private static final String NAME = "<SyncManager>";
	
	private static final SyncManager syncManager = new SyncManager();

	public static boolean sendflag=true;

	
//	private List<EnterpriseInfoBean> eninfoList;
//	private List<StuInfoBean> stuinfoList;
//	
//	private List<EnterpriseInfoBean> eninfo_errorList;
//	private List<StuInfoBean> stuinfo_errorList;
	
	private SmsSyncDAO smssyncdao;
	
	

	public void setSmssyncdao(SmsSyncDAO smssyncdao) {
		this.smssyncdao = smssyncdao;
	}

	private SyncManager() {
	 
	}

	public static SyncManager getInstance() {
		return syncManager;
	}

	public void init() {
		MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
		MDC.put("modulename", "[SyncInit]");
		 try {
			 boolean b = ActiveCoreService.getInstance().getMainCore(Constant.CORE_ID, Integer.parseInt(Config.props.getProperty("core.active.time")));
			//判断是否是主核心
			if(b){
			 if(sendflag){
				 sendflag=false;//设置独占运行模式 
				 /* 从数据库中查询出企业信息，并放入短信同步队列 */
				 log.info("企业信息同步到短信平台处理开始");
				 smssyncdao.updateSync("clw_jc_enterprise_t");
				 List<EnterpriseInfoBean> eninfoList = smssyncdao.getEnterpriseInfo();
//				 setEnterpriseToBuffer(eninfoList);
				 log.info("企业信息已经存入队列，等待发送");
				 log.info("企业信息同步到短信平台处理结束");
				 Thread.sleep(Long.valueOf(Config.props.getProperty("synctimeinterval"))*1000);
				 log.info("学生信息同步到短信平台处理开始");
				 smssyncdao.updateSync("clw_xc_student_t");
				 /* 从数据库中查询出学生信息，并放入短信同步队列 */
				 List<StuInfoBean> stuinfoList = smssyncdao.getStuInfo();
//				 setStuToBuffer(stuinfoList);
				 log.info("学生信息已经存入队列，等待发送");
				 log.info("学生信息同步到短信平台处理结束");
				 Thread.sleep(Long.valueOf(Config.props.getProperty("sucandfailtimeinterval"))*1000);
				 //将同步失败的信息重新发送，仅一次
				 log.info("同步失败的企业信息再次同步短信平台处理开始");
				 List<EnterpriseInfoBean> eninfo_errorList = smssyncdao.getEnterpriseInfo_syncError();
//				 setEnterpriseToBuffer(eninfo_errorList);
				 log.info("同步失败的企业信息已经存入队列，等待发送");
				 log.info("同步失败的企业信息再次同步短信平台处理结束");
				 Thread.sleep(Long.valueOf(Config.props.getProperty("synctimeinterval"))*1000);
				 log.info("同步失败的学生信息再次同步短信平台处理开始");
				 List<StuInfoBean> stuinfo_errorList = smssyncdao.getStuInfo_syncError();
//				 setStuToBuffer(stuinfo_errorList);
				 log.info("同步失败的学生信息已经存入队列，等待发送");
				 log.info("同步失败的学生信息再次同步短信平台处理结束");
			     sendflag =true; 
				 }else{//独占结束
					 log.info(" ！！！！！！！！！！短信同步发送进行中！！！！！！！！！！！");
					 return;
				 } 		
			}else{
				log.info(NAME+"当前核心不为主核心，不进行短信同步发送");
			}
		 } catch (Exception e) {
			 sendflag =true; 
			 log.error(NAME+",syncManager发送命令错误："+e.getMessage());		
			e.printStackTrace();
		}
 
		 
	}
	
//	/**
//	 * 将企业信息放入短信同步队列中等待发送
//	 */
//	public void setEnterpriseToBuffer(List<EnterpriseInfoBean> list){
//		SyncClass sync = null;
//		SyncEnterpriseInfoReq enreq = null;
//		String seq = null;
////		List<SyncClass> syncList = new ArrayList<SyncClass>();
//		for(EnterpriseInfoBean bean:list){
//			sync = new SyncClass();
//			enreq = new SyncEnterpriseInfoReq();
//			seq = SmsCommonMsgUtils.getSeq();
//			//set企业同步请求值 拼装报文
//			enreq.setSeqLength(seq);
//			enreq.setEnterprise_id(bean.getEnterprise_id());
//			enreq.setEnterprise_code(bean.getEnterprise_code());
//			enreq.setFull_name(bean.getFull_name());
//			enreq.setShort_name(bean.getShort_name());
////			System.out.println(bean.getShort_name()+","+enreq.getShort_name());
//			enreq.setParent_id(bean.getParent_id());
//			enreq.setEnterprise_country(bean.getEnterprise_country());
//			enreq.setEnterprise_province(bean.getEnterprise_province());
//			enreq.setEnterprise_city(bean.getEnterprise_city());
//			enreq.setEnterprise_desc(bean.getEnterprise_desc());
//			enreq.setAddress(bean.getAddress());
//			enreq.setContact_p(bean.getContact_p());
//			enreq.setContact_tel(bean.getContact_tel());
//			enreq.setValid_flag(bean.getValid_flag());
//			//set队列
//			sync.setSeq(seq);
//			sync.setCommand(SyncEnterpriseInfoReq.COMMAND);
//			sync.setId(bean.getEnterprise_id());
//			sync.setReq(enreq.toString());
//			sync.setTotallen(SyncEnterpriseInfoReq.total);
////			syncList.add(sync);
//			SyncBuffer.getInstance().add(sync);
//		}
////		SyncBuffer.getInstance().add(syncList);
//	}
//
//	public void setStuToBuffer(List<StuInfoBean> list){
//		SyncClass sync = null;
//		SyncStuInfoReq stureq = null;
//		String seq = null;
////		List<SyncClass> syncList = new ArrayList<SyncClass>();
//		for(StuInfoBean bean : list){
//			sync = new SyncClass();
//			stureq = new SyncStuInfoReq();
//			seq = SmsCommonMsgUtils.getSeq();
//			//set学生同步请求值 拼装报文
//			stureq.setSeqLength(seq);
//			stureq.setStu_id(bean.getStu_id());
//			stureq.setStu_card_id(bean.getStu_card_id());
//			stureq.setStu_code(bean.getStu_code());
//			stureq.setStu_name(bean.getStu_name());
//			stureq.setStu_sex(bean.getStu_sex());
//			stureq.setStu_birth(bean.getStu_birth());
//			stureq.setStu_address(bean.getStu_address());
//			stureq.setStu_province(bean.getStu_province());
//			stureq.setStu_city(bean.getStu_city());
//			stureq.setStu_district(bean.getStu_district());
//			stureq.setStu_school(bean.getStu_school());
//			stureq.setStu_class(bean.getStu_class());
//			stureq.setStu_remark(bean.getStu_remark());
//			stureq.setTeacher_name(bean.getTeacher_name());
//			stureq.setTeacher_tel(bean.getTeacher_tel());
//			stureq.setRelative_name(bean.getRelative_name());
//			stureq.setRelative_tel(bean.getRelative_tel());
//			stureq.setRelative_type(bean.getRelative_type());
//			stureq.setEnterprise_id(bean.getEnterprise_id());
//			stureq.setOrganization_id(bean.getOrganization_id());
//			stureq.setValid_flag(bean.getValid_flag());
//			//set队列
//			sync.setSeq(seq);
//			sync.setCommand(SyncStuInfoReq.COMMAND);
//			sync.setId(bean.getStu_id());
//			sync.setReq(stureq.toString());
//			sync.setTotallen(SyncStuInfoReq.total);
////			syncList.add(sync);
//			SyncBuffer.getInstance().add(sync);
//		}
////		SyncBuffer.getInstance().add(syncList);
//	}

	 
}
