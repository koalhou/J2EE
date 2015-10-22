package com.neusoft.smsplatform.message.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.SchoolBus.vncs.dao.IXcSmsDAO;
import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.dao.ITerminalDAO;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;

public class MessageBuildSQL {

	private static Logger log = LoggerFactory.getLogger(MessageBuildSQL.class);

	@SuppressWarnings("unused")
	private IXcSmsDAO XcSmsDAO = null;
	@SuppressWarnings("unused")
	private ITerminalDAO terminalDAO = null;

	public static final String num3 = "3";

	public static final MessageBuildSQL buildSql = new MessageBuildSQL();

	private MessageBuildSQL() {
		XcSmsDAO = (IXcSmsDAO) SpringBootStrap.getInstance().getBean(
				"sendXcSmsDAO");

		terminalDAO = (ITerminalDAO) SpringBootStrap.getInstance().getBean(
				"terminalDAO");
	}

	public static MessageBuildSQL getInstance() {
		return buildSql;
	}

	/**
	 * 短信流水表发送短信insert sql
	 * 
	 * @param urt
	 * @return
	 */
	public String buildInsertSmsRecordSQL(Up_InfoContent urt,String event_type,String id) {
		StringBuffer sql = new StringBuffer();
		// UserBean ub = msgCfgDAO.getUserInfo(urt.getTerminalId());
		sql
				.append("insert into CLW_YW_SMS_RECORD_T(ID,VEHICLE_VIN,TEL,MSG,"
						+ "SEND_TAKE,SRC_ID,CREATE_TIME,"
						+ "ENTERPRISE_ID,ORGANIZATION_ID,STU_ID,"
						+ "EVENT_TYPE,FLAG,RELATIVE_TYPE,RELATIVE_NAME,PARENTS_FLAG,PICI_ID,MESSAGE_PROVIDER) values(");
		sql.append("'"+id+"'");
		sql.append(",'" + urt.getVehicle_vin() + "'");// 车辆VIN
		sql.append(",'" + urt.getTel() + "'");// 发送号码
		sql.append(",'" + urt.getMsg() + "'");// 信息内容
		sql.append(",'0'");// 发送类型，0发送，1接收

		if (urt.getSrc_id() != null && !urt.getSrc_id().equals("")) {
			sql.append(",'" + urt.getSrc_id() + "'");// SP代码
		} else {
			sql.append(",''");
		}

		sql.append(",sysdate");
		sql.append(",'" + urt.getEnterprise_id() + "'");
		sql.append(",'" + urt.getOrganization_id() + "'");
		sql.append("," + urt.getStu_id());
		sql.append(",'" + event_type + "'");
		sql.append(",'1'");
		sql.append(",'" + urt.getRelative_type() + "'");
		if(urt.getRelative_name()!=null&&!urt.getRelative_name().equals("")){
			sql.append(",'" + urt.getRelative_name() + "'");
		}else{
			sql.append(",''");
		}
		if (urt.getParents_flag() != null && !"".equals(urt.getParents_flag())) {
			sql.append(",'" + urt.getParents_flag() + "'");
		} else {
			sql.append(",''");
		}
		sql.append(",'" + urt.getPid()+ "'");
		sql.append(",'1'");
		sql.append(")");
		log.debug("<BuildSQL>短信流水记录数据insert sql:" + sql.toString());
		return sql.toString();
	}
}
