package com.yutong.clw.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.beans.cl.ConfigParamBean;
import com.yutong.clw.config.Config;
import com.yutong.clw.config.Constant;
import com.yutong.clw.quartz.DataSyncJob;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.LogFormatter;
import com.yutong.clw.utils.sms.SDKClient;

public class SmsCfgDAO extends AbstractDaoManager{
	private static Logger log = LoggerFactory.getLogger(SmsCfgDAO.class);
	
	private static SmsCfgDAO smscfgDao = new SmsCfgDAO();
	
	private Timer timer;
	
	private SmsCfgDaoTimerTask task;

	private static final String msgphone = "msgphone_old";

	private static final String msgfixed = "msgfixed_old";

	private static final String msgcontact = "msgcontact_old";

	private static final String msgcompany = "msgcompany_old";

	private static final String softwareSerialNo = "softwareSerialNo_old";

	private static final String msgpassword = "msgpassword_old";
	
	private static final String msgkey = "msgkey_old";
	
	private static final String msgmail = "msgmail_old";
	
	private static final String msgfax = "msgfax_old";
	
	private static final String msgaddress = "msgaddress_old";
	
	private static final String msgzipcode = "msgzipcode_old";
	
//	private static final String core02 = "core02";
	
	public static SmsCfgDAO getInstance() {
		if (smscfgDao == null) {
			smscfgDao = new SmsCfgDAO();
		}

		return smscfgDao;
	}
	
	@SuppressWarnings("unchecked")
	private void getSmsCfgList(){
		Map map = new HashMap();
		String SELECT_SMS_INFO_SQL = "select t.param_name,t.param_value from clw_jc_param_cfg_t t " +
		"where t.param_name = 'softwareSerialNo' or t.param_name = 'msgkey' " +
		"or t.param_name = 'msgpassword' or t.param_name = 'msgcompany' " +
		"or t.param_name = 'msgcontact' or t.param_name = 'msgfixed' " +
		"or t.param_name = 'msgphone' or t.param_name = 'msgmail' " +
		"or t.param_name = 'msgfax' or t.param_name = 'msgaddress' " +
		"or t.param_name = 'msgzipcode' ";
//		if(Constant.CORE_ID.equals(core02)){
			List<ConfigParamBean> list = jdbcTemplate.query(SELECT_SMS_INFO_SQL	, ParameterizedBeanPropertyRowMapper.newInstance(ConfigParamBean.class));
			if(list!=null&&list.size()>0){
				for(ConfigParamBean cfg : list){
					map.put(cfg.getParam_name()+"_old", cfg.getParam_value());
				}
				if(!map.get(msgkey).equals(Config.props.getProperty("msgkey"))
					||!map.get(msgpassword).equals(Config.props.getProperty("msgpassword"))
					||!map.get(softwareSerialNo).equals(Config.props.getProperty("softwareSerialNo"))){
					SDKClient.getClient().registEx(Config.props.getProperty("softwareSerialNo"));
					map.put(msgkey, Config.props.getProperty("msgkey"));
					map.put(msgpassword, Config.props.getProperty("msgpassword"));
					map.put(softwareSerialNo, Config.props.getProperty("softwareSerialNo"));
				}
				if(!map.get(msgcompany).equals(Config.props.getProperty("msgcompany"))
					||!map.get(msgcontact).equals(Config.props.getProperty("msgcontact"))
					||!map.get(msgfixed).equals(Config.props.getProperty("msgfixed"))
					||!map.get(msgphone).equals(Config.props.getProperty("msgphone"))
					||!map.get(msgmail).equals(Config.props.getProperty("msgmail"))
					||!map.get(msgfax).equals(Config.props.getProperty("msgfax"))
					||!map.get(msgaddress).equals(Config.props.getProperty("msgaddress"))
					||!map.get(msgzipcode).equals(Config.props.getProperty("msgzipcode"))){
					SDKClient.getClient().registDetailInfo(Config.props.getProperty("msgcompany"), Config.props.getProperty("msgcontact"), 
							Config.props.getProperty("msgfixed"), Config.props.getProperty("msgphone"), Config.props.getProperty("msgmail"),
							Config.props.getProperty("msgfax"), Config.props.getProperty("msgaddress"), Config.props.getProperty("msgzipcode"));
				}
			}else{
				log.error(LogFormatter.formatMsg("SmsCfgDAO", "短信配置信息不全，发生错误！"));
			}
//		}else{
//			log.info(LogFormatter.formatMsg("SmsCfgDAO", "当前核心不进行短信重新注册！"));
//		}
	}
	
	public void start() {
		timer = new Timer("SmsCfgDaoTimer");
		task = new SmsCfgDaoTimerTask();
		long interval = Long.parseLong(Config.props
				.getProperty("smscfgtimer"));
		timer.schedule(task, 60 * Constant.SECOND, interval * Constant.SECOND);
		log.info(LogFormatter.formatMsg("SmsCfgDAO",
				"start the sms param timer task."));
		// getNewReconnectExpList();
	}
	
	class SmsCfgDaoTimerTask extends TimerTask {

		@Override
		public void run() {
			try {
				synchronized (this) {

					getSmsCfgList();

					// 更新VNCS数据同步定时器时间
					if (SpringBootStrap.getInstance().isInit()) {
						DataSyncJob job = (DataSyncJob) SpringBootStrap
								.getInstance().getBean("dataSyncJob");
						job.updateTriggersInterval();
					} else {
						log.info(LogFormatter.formatMsg("SmsCfgDAO",
								"spring context未初始化完成，本次不执行定时器时间更新操作！"));
					}
				}
			} catch (Throwable t) {
				// cancel();
				log.error(LogFormatter.formatMsg("SmsCfgDAO",
						"SmsCfgDaoTimerTask has datebase problem."), t);
			}
		}
	}
}
