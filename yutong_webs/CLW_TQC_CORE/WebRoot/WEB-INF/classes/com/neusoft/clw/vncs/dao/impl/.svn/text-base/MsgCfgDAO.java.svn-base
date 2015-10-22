package com.neusoft.clw.vncs.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.configuration.Config;
import com.neusoft.clw.constant.Constant;
import com.neusoft.clw.info.dao.AbstractDaoManager;
import com.neusoft.clw.log.LogFormatter;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.ITerminalDAO;
import com.neusoft.clw.vncs.domain.EnterpriseMsgBean;
import com.neusoft.clw.vncs.domain.MsgCfgBean;
import com.neusoft.clw.vncs.domain.UserBean;
import com.neusoft.clw.vncs.job.DataSyncJob;
import com.neusoft.clw.vncs.util.StringUtil;

public class MsgCfgDAO extends AbstractDaoManager {

	private static Logger log = LoggerFactory.getLogger(MsgCfgDAO.class);

	private static MsgCfgDAO msgcfgDao = new MsgCfgDAO();
	
//	private CacheBean cb = new CacheBean();
	
	private ITerminalDAO terminalDAO = null;
	
	private Timer timer;

	private MsgCfgDaoTimerTask task;
	
//	private List<EnterpriseMsgBean> msgquotasList;

//	private Date lastIncremSyncDate;

	@SuppressWarnings("unchecked")
	public static Map<String, Map> msgquotasMap;

	// 计时器，增减量加载2分钟执行一次，2小时执行一次全量加载，于是 count为指定值(数据库中可配置)的时候就进行一次全量加载
	private static int count = 0;
	// 如果每2小时一次执行的全量加载没有加载完成，job不进行启动
	private static boolean bool = true;

//	private static final String SELECT_ALL_QUOTAS_SQL = "select enterprise_id,msg_num,to_char(MODIFY_TIME,'yymmddhh24miss') AS MODIFY_TIME," +
//			"valid_flag from clw_jc_enterprise_t where valid_flag = '0' and msg_num is not null" ;
	
//	private static final String SELECT_QUOTAS_SQL = "select t.enterprise_id,t.msg_num,to_char(MODIFY_TIME,'yymmddhh24miss') AS MODIFY_TIME from clw_jc_enterprise_t t" +
//			" where to_char(MODIFY_TIME,'yymmddhh24miss')<=(select to_char(sysdate,'yymmddhh24miss') from dual)" +
//			" and to_char(MODIFY_TIME,'yymmddhh24miss') >= ? ";

	@SuppressWarnings("unchecked")
	public List<MsgCfgBean> getAlarmMsg(String vin, String alarmtype) {
		String SELECT_MSG_ALARMTYPE_SQL = "select vehicle_vin,alarm_id,state,add_info,send_num as sendtime from CLW_YW_MSMCFG_T where VEHICLE_VIN = ? "
			+ "and alarm_id = ? and state = '0'";
		return jdbcTemplate.query(
				SELECT_MSG_ALARMTYPE_SQL, new String[] { vin, alarmtype },
				ParameterizedBeanPropertyRowMapper
						.newInstance(MsgCfgBean.class));
	}

	public UserBean getUserInfo(String vin) {
		String SELECT_EP_SQL = "select distinct CLW_CL_BASE_INFO_T.enterprise_id," +
		"CLW_CL_BASE_INFO_T.ORGANIZATION_ID,user_mobile from CLW_YW_MSMCFG_T,CLW_JC_USER_T,CLW_CL_BASE_INFO_T " +
		" where CLW_YW_MSMCFG_T.SMSMAN_ID = CLW_JC_USER_T.USER_ID and CLW_YW_MSMCFG_T.VEHICLE_VIN = CLW_CL_BASE_INFO_T.VEHICLE_VIN " +
		" CLW_CL_BASE_INFO_T.valid_flag = '0' " +
		" and CLW_CL_BASE_INFO_T.VEHICLE_VIN =?";
		return (UserBean) jdbcTemplate.queryForObject(SELECT_EP_SQL,
				new String[] { vin }, ParameterizedBeanPropertyRowMapper
						.newInstance(UserBean.class));
	}

	public String getEnterprise_id(String vin) {
		String SELECT_ENTERPRISE_VEHICLE_SQL = "select distinct t.enterprise_id from clw_jc_enterprise_t t,clw_cl_base_info_t e "
			+ "where t.enterprise_id = e.enterprise_id and e.vehicle_vin = ?";
		return (String) jdbcTemplate.queryForObject(
				SELECT_ENTERPRISE_VEHICLE_SQL, new String[] { vin },
				String.class);
	}
	
	public EnterpriseMsgBean getMsg_Quotas(){
		String SELECT_ALL_QUOTAS_SQL = "select enterprise_id,msg_num,to_char(MODIFY_TIME,'yymmddhh24miss') AS MODIFY_TIME," +
		"valid_flag from clw_jc_enterprise_t where valid_flag = '0' and msg_num is not null";
		return (EnterpriseMsgBean) jdbcTemplate.queryForObject(SELECT_ALL_QUOTAS_SQL, ParameterizedBeanPropertyRowMapper.newInstance(EnterpriseMsgBean.class));
	}
	
	@SuppressWarnings("unchecked")
	private MsgCfgDAO() {
//		terminalDAO = (ITerminalDAO) SpringBootStrap.getInstance().getBean("terminalDAO");
//		msgquotasList = new ArrayList<EnterpriseMsgBean>();
		msgquotasMap = new HashMap<String, Map>();
	}
	
	
	
	public void setTerminalDAO(ITerminalDAO terminalDAO) {
		this.terminalDAO = terminalDAO;
	}

	public static MsgCfgDAO getInstance() {
		if (msgcfgDao == null) {
			msgcfgDao = new MsgCfgDAO();
		}

		return msgcfgDao;
	}
	
	@SuppressWarnings("unchecked")
	public void getMsgQuotasList() {
		if (bool == true) {
			String systime = terminalDAO.getSysTime();
			List<EnterpriseMsgBean> msgquotasList;
			EnterpriseMsgBean tb = null;
			// System.out.println(Constant.vehicleMap.get("11111").getType_r());
			MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
			MDC.put("modulename", "[msgQuotasInit]");
			if (Constant.isfirstmsgnumload.equals("true")) {
				String SELECT_ALL_QUOTAS_SQL = "select enterprise_id,msg_num,to_char(MODIFY_TIME,'yymmddhh24miss') AS MODIFY_TIME," +
				"valid_flag from clw_jc_enterprise_t where valid_flag = '0' and msg_num is not null";
				msgquotasList = jdbcTemplate.query(SELECT_ALL_QUOTAS_SQL,ParameterizedBeanPropertyRowMapper.newInstance(EnterpriseMsgBean.class));
				if (msgquotasList != null && msgquotasList.size() > 0) {
					log.info(LogFormatter.formatMsg("MsgCfgDAO","开始加载企业短信配额全量缓存。。"));
					Iterator it = msgquotasList.iterator();
					while (it.hasNext()) {
						tb = (EnterpriseMsgBean) it.next();
						addMsgQuotasIntoCache(tb);
					}
					log.info(LogFormatter.formatMsg("MsgCfgDAO","本次启动共加载" + msgquotasList.size() + "个企业短信配额信息"));
					Constant.isfirstmsgnumload = "false";
				}
			} else {
				if (count == Integer.parseInt(Config.props
						.getProperty("allMsgQuotasCacheTime"))) {
					bool = false;
					log.info(LogFormatter.formatMsg("MsgCfgDAO","开始加载企业短信配额全量缓存。。"));
					String SELECT_ALL_QUOTAS_SQL = "select enterprise_id,msg_num,to_char(MODIFY_TIME,'yymmddhh24miss') AS MODIFY_TIME," +
					"valid_flag from clw_jc_enterprise_t where valid_flag = '0' and msg_num is not null";
					msgquotasList = jdbcTemplate.query(SELECT_ALL_QUOTAS_SQL,ParameterizedBeanPropertyRowMapper.newInstance(EnterpriseMsgBean.class));
					Map<String, String> map = new HashMap<String, String>();
					if (msgquotasList != null && msgquotasList.size() > 0) {
						Iterator it = msgquotasList.iterator();
						while (it.hasNext()) {
							tb = (EnterpriseMsgBean) it.next();
							addMsgQuotasIntoCache(tb);
							map.put(tb.getEnterprise_id(), "");
						}
					}
					if (msgquotasMap != null && msgquotasMap.size() > 0) {
						Set set = msgquotasMap.keySet();
						Iterator itm = set.iterator();
						List list = new ArrayList();
						while (itm.hasNext()) {
							String enterprise_id = (String) itm.next();
							if (!map.containsKey(enterprise_id)) {
								list.add(enterprise_id);
							}
						}
						if (list != null && list.size() > 0) {
							delMsgQuotasFromCache(list);
						}
					}
					log.info(LogFormatter.formatMsg("MsgCfgDAO","本次启动共加载" + msgquotasMap.size() + "个企业短信配额信息"));
					// 2小时执行一次全量加载后，将计时器清0
					count = 0;
					bool = true;
				} else {
					count++;
					log.info(LogFormatter.formatMsg("MsgCfgDAO","开始加载企业短信配额增减量缓存。。"));
					/* 从数据库中查询出车辆区域基本信息，并保存入缓存中 */
					String SELECT_QUOTAS_SQL = "select t.enterprise_id,t.msg_num,to_char(MODIFY_TIME,'yymmddhh24miss') AS MODIFY_TIME from clw_jc_enterprise_t t" +
					" where MODIFY_TIME<=sysdate and MODIFY_TIME >= to_date(?,'yymmddhh24miss') ";
					msgquotasList = jdbcTemplate.query(SELECT_QUOTAS_SQL, new String[]{Constant.upd_msg_cache_time}, ParameterizedBeanPropertyRowMapper.newInstance(EnterpriseMsgBean.class));
					if (msgquotasMap != null && msgquotasMap.size() > 0) {
						Iterator it = msgquotasList.iterator();
						while (it.hasNext()) {
							tb = (EnterpriseMsgBean) it.next();
							if (tb.getValid_flag().equals("1")) {
								if (checkExisted(tb.getEnterprise_id())) {
									delVehicleFromCache(tb.getEnterprise_id());
								}
							} else {
								addMsgQuotasIntoCache(tb);
							}
						}
					} else {
						Iterator it = msgquotasList.iterator();
						while (it.hasNext()) {
							tb = (EnterpriseMsgBean) it.next();
							addMsgQuotasIntoCache(tb);
						}
					}
					log.info(LogFormatter.formatMsg("MsgCfgDAO","本次启动共加载" + msgquotasList.size() + "个企业短信配额信息"));
				}
				if(msgquotasList!=null){
					msgquotasList.clear();
				}

				log.info(LogFormatter.formatMsg("MsgCfgDAO","企业短信配额加载完毕。"));
			}
			tb = null;
			msgquotasList.clear();
			Constant.upd_msg_cache_time = systime;
		}	
	}

	public void delVehicleFromCache(String enterpriseId) {
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			Constant.getMemcachedClient().delete(enterpriseId);
		}
		msgquotasMap.remove(enterpriseId);
	}

	public boolean checkExisted(String enterprise_id) {
		if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
			Object obj = Constant.getMemcachedClient().getObject(enterprise_id);
			if(obj!=null &&!obj.equals("")){
				return true;
			}else{
				return false;
			}
		}else{
			return msgquotasMap.containsKey(enterprise_id);
		}
	}

//	@SuppressWarnings("unchecked")
//	public static void setQuotasCache(EnterpriseMsgBean msgbean,String send_num) throws ParseException{
//		Map map = new HashMap();
//		map.put("msg_num", msgbean.getMsg_num());
//		map.put("send_num", send_num);
//		map.put("current_month", DateUtil.getCurrentMonth(DateUtil.changeStringTo12Date(terminalDAO.getSysTime())));
//		if(Constant.isstartMemcache.equals("1")
//			&&Constant.getMemcachedClient().connectState()){
//			Constant.getMemcachedClient().insert(msgbean.getEnterprise_id(), map);
//			log.info("msg_map:"+Constant.getMemcachedClient().getObject(msgbean.getEnterprise_id()));
//		}else{
//			Constant.msgMap.put(msgbean.getEnterprise_id(), map);
//		}
//	}
	
	public void delMsgQuotasFromCache(List<String> list) {
		for (String str:list) {
			if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
				Constant.getMemcachedClient().delete(str);
			}
			msgquotasMap.remove(str);
		}
	}

	@SuppressWarnings("unchecked")
	public void addMsgQuotasIntoCache(EnterpriseMsgBean msgbean) {
		Map map = new HashMap();
		map.put("msg_num", msgbean.getMsg_num());
//		cb.setKey(msgbean.getEnterprise_id());
//		cb.setValue(map);
//		map.put("send_num", send_num);
//		map.put("current_month", DateUtil.getCurrentMonth(DateUtil.changeStringTo12Date(terminalDAO.getSysTime())));
		if(Constant.isstartMemcache.equals("1")
			&&Constant.getMemcachedClient().connectState()){
			Constant.getMemcachedClient().insert(msgbean.getEnterprise_id(), map);
//			log.debug("msg_map:"+Constant.getMemcachedClient().getObject(msgbean.getEnterprise_id()));
		}
//		CacheBuffer.getInstance().add(cb);
//		Constant.msgMap.put(msgbean.getEnterprise_id(), map);
	}

	public void start() {
		timer = new Timer("MsgCfgDaoTimer");
		task = new MsgCfgDaoTimerTask();
		long interval = Long.parseLong(Config.props
				.getProperty("msgcfgtimer"));
		timer.schedule(task, 60 * Constant.SECOND, interval * Constant.SECOND);
		log.info(LogFormatter.formatMsg("MsgCfgDAO",
				"start the msg param timer task."));
		// getNewReconnectExpList();
	}

	class MsgCfgDaoTimerTask extends TimerTask {

		@Override
		public void run() {
			try {
				synchronized (this) {

					getMsgQuotasList();

					// 更新VNCS数据同步定时器时间
					if (SpringBootStrap.getInstance().isInit()) {
						DataSyncJob job = (DataSyncJob) SpringBootStrap
								.getInstance().getBean("dataSyncJob");
						job.updateTriggersInterval();
					} else {
						log.info(LogFormatter.formatMsg("MsgCfgDAO",
								"spring context未初始化完成，本次不执行定时器时间更新操作！"));
					}
				}
			} catch (Throwable t) {
				// cancel();
				log.error(LogFormatter.formatMsg("MsgCfgDAO",
						"MsgCfgDaoTimerTask has datebase problem."), t);
			}
		}
	}
}
