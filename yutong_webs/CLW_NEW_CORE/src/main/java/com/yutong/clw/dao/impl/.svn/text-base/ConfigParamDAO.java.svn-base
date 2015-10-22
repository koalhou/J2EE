package com.yutong.clw.dao.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.yutong.clw.beans.cl.ConfigParamBean;
import com.yutong.clw.config.Config;
import com.yutong.clw.config.Constant;
import com.yutong.clw.quartz.DataSyncJob;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.LogFormatter;

public class ConfigParamDAO extends AbstractDaoManager {
	private Logger log = LoggerFactory.getLogger(ConfigParamDAO.class);
	private static ConfigParamDAO configParamDao = new ConfigParamDAO();

	private static final String NAME = "ConfigParamDao";

	private Timer timer;

	private ConfigParamDaoTimerTask task;

	public static ConfigParamDAO getInstance() {
		if (configParamDao == null) {
			configParamDao = new ConfigParamDAO();
		}

		return configParamDao;
	}
	
	@SuppressWarnings("unchecked")
	public void getConfigParamList() {
		String GET_CONFIG_PARAM_LIST = "select PARAM_NAME,PARAM_VALUE,REMARK from CLW_JC_PARAM_CFG_T";
		List rows = jdbcTemplate.queryForList(GET_CONFIG_PARAM_LIST);
		if (rows != null & rows.size() > 0) {
			Iterator it = rows.iterator();

			while (it.hasNext()) {

				Map userMap = (Map) it.next();
				ConfigParamBean cb = new ConfigParamBean();
				cb.setParam_name(userMap.get("PARAM_NAME").toString());
				cb.setParam_value(userMap.get("PARAM_VALUE").toString());
				cb.setRemark(userMap.get("REMARK") == null ? null : userMap
						.get("REMARK").toString());

				log.debug("configparam:" + cb.getParam_name() + "::"
						+ cb.getParam_value() + "::" + cb.getRemark());

				log.debug("---------------");

				Config.props.setProperty(cb.getParam_name(), cb
						.getParam_value());
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List selectLoginName(String name) {
		String SELECT_SYS_PARAM_CONFIG_LOGINNAME = "select s.PARAM_NAME,s.PARAM_VALUE from CLW_JC_PARAM_CFG_T s where s.PARAM_NAME = ?";
//		System.out.println("+++++++++++++++++++++++++");
		List list = jdbcTemplate.queryForList(
				SELECT_SYS_PARAM_CONFIG_LOGINNAME, new Object[] { name });
//		System.out.println("+++++++++++++++++++++++++" + list);

		return list;
	}

	public void start() {
		timer = new Timer("ConfigParamDaoTimer");
		task = new ConfigParamDaoTimerTask();
		long interval = Long.parseLong(Config.props
				.getProperty("configparamtimer"));
		timer.schedule(task, 60 * Constant.SECOND, interval * Constant.SECOND);
		log.info(LogFormatter.formatMsg(NAME,
				"start the config param timer task."));
		// getNewReconnectExpList();
	}

	class ConfigParamDaoTimerTask extends TimerTask {

		@Override
		public void run() {
			try {
				synchronized (this) {

					getConfigParamList();
//					更新xc数据同步定时器时间
					if (SpringBootStrap.getInstance().isInit()) {
						DataSyncJob job = (DataSyncJob) SpringBootStrap.getInstance().getBean("dataSyncJob");
						job.updateTriggersInterval();
					} else {
						log.info(LogFormatter.formatMsg(NAME,
								"spring context未初始化完成，本次不执行定时器时间更新操作！"));
					}
				}
			} catch (Throwable t) {
				// cancel();
				log.error(LogFormatter.formatMsg(NAME,
						"ConfigParamDaoTimerTask has datebase problem."), t);
			}
		}
	}
}
