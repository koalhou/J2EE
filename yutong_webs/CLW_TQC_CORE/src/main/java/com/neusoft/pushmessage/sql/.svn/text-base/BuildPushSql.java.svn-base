package com.neusoft.pushmessage.sql;

import com.neusoft.mobile.bean.AlarmBean;
import com.neusoft.mobile.bean.MessageBean;
import com.neusoft.mobile.bean.PhotoBean;

public class BuildPushSql {
//	private static Logger log = LoggerFactory.getLogger(BuildPushSql.class);
	public static final BuildPushSql buildSql = new BuildPushSql();
	public static BuildPushSql getInstance() {
		return buildSql;
	}
	
	public String mergePushPhoto(PhotoBean photo, String state){
		StringBuffer sql = new StringBuffer();
		sql.append("MERGE INTO CLW_M_PUSH_PHOTO_RECORD_T CS USING DUAL"
				+ " ON (CS.PHOTO_ID = '" +photo.getPhoto_id()+ "' and CS.USER_ID='"+photo.getUser_id()+"')");
		sql.append(" WHEN MATCHED THEN");
		sql.append(" UPDATE");
		sql.append(" SET CS.PUSH_COUNT=CS.PUSH_COUNT+1,");
//		sql.append("CS.PUSH_TIME = sysdate,");
		sql.append("CS.PUSH_STATE = '"+state+"',");
		sql.append("CS.MODIFY_TIME = sysdate,");
		sql.append("CS.PUSH_SWITCH = '"+photo.getP_s()+"'");
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT");
		sql.append("(PHOTO_ID,USER_ID,CLIENT_ID,PUSH_COUNT,PUSH_STATE,PUSH_TIME,CREATE_TIME,VEHICLE_VIN,PUSH_SWITCH)");
		sql.append(" VALUES(");

		sql.append("'" +photo.getPhoto_id() + "'");
		sql.append(",'"+photo.getUser_id()+"'");
		sql.append(",'"+photo.getClient_id()+"'");
		sql.append(",0");
		sql.append(",'"+state+"'");
		sql.append(",sysdate");
		sql.append(",sysdate");
		sql.append(",'"+photo.getVehicle_vin()+"'");
		sql.append(",'"+photo.getP_s()+"'");
		sql.append(")");
//		System.out.println("mergePushPhoto:"+sql.toString());
		return sql.toString();
	}
	
	public String mergePushMsg(MessageBean message,String state){
		StringBuffer sql = new StringBuffer();
		sql.append("MERGE INTO CLW_M_PUSH_MESSAGE_RECORD_T CS USING DUAL"
				+ " ON (CS.MSG_ID = '" +message.getMsg_id()+ "' and CS.USER_ID='"+message.getUser_id()+"')");
		sql.append(" WHEN MATCHED THEN");
		sql.append(" UPDATE");
		sql.append(" SET CS.PUSH_COUNT=CS.PUSH_COUNT+1,");
		sql.append("CS.PUSH_AGAIN_TIME = sysdate,");
		sql.append("CS.PUSH_STATE = '"+state+"',");
		sql.append("CS.PUSH_SWITCH='"+message.getP_s()+"'");
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT");
		sql.append("(USER_ID,MSG_ID,CLIENT_ID,PUSH_COUNT,PUSH_STATE,PUSH_TIME,MSG_INDEX,MSG_TYPE,PUSH_AGAIN_TIME,PUSH_SWITCH)");
		sql.append(" VALUES(");

		sql.append("'" + message.getUser_id() + "'");
		sql.append(",'"+message.getMsg_id()+"'");
		sql.append(",'"+message.getClient_id()+"'");
		sql.append(",0");
		sql.append(",'"+state+"'");
		sql.append(",sysdate");
		sql.append(",'"+message.getMsg_index()+"'");
		sql.append(",'"+message.getMsg_type()+"'");
		sql.append(",sysdate");
		sql.append(",'"+message.getP_s()+"'");
		sql.append(")");
//		System.out.println("mergePushMsg:"+sql.toString());
		return sql.toString();
	}
	
	public String mergePushAlarm(AlarmBean alarm,String state){
		StringBuffer sql = new StringBuffer();
		sql.append("MERGE INTO CLW_M_PUSH_ALARM_RECORD_T CS USING DUAL"
				+ " ON (CS.ALARM_ID = '" +alarm.getAlarm_id()+ "' and CS.USER_ID='"+alarm.getUser_id()+"')");
		sql.append(" WHEN MATCHED THEN");
		sql.append(" UPDATE");
		sql.append(" SET CS.ALARM_END_TIME = to_date('" + alarm.getAlarm_end() + "','yymmddhh24miss'),");
		sql.append("CS.MODIFY_TIME = sysdate,");
		sql.append("CS.PUSH_COUNT=CS.PUSH_COUNT+1,");
//		sql.append("CS.PUSH_TIME = sysdate,");
		sql.append("CS.PUSH_STATE='"+state+"'," );	
		sql.append("CS.PUSH_SWITCH='"+alarm.getP_s()+"'");
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT");
		sql.append("(CS.ALARM_ID,CS.USER_ID,CS.ALARM_TYPE_ID,CS.ALARM_CONT,CS.ALARM_TIME,CS.ALARM_END_TIME,CS.CONFIRM_TIME,CS.CONFIRM_USER_ID,CS.OPEERATE_DESC" +
				",CS.VEHICLE_VIN,CS.VEHICLE_LN,CS.DRIVER_NAME,CS.DEAL_FLAG,CS.ORGANIZATION_ID,CS.CLIENT_ID,CS.PUSH_STATE," 
				+ "CS.PUSH_TIME,CS.PUSH_COUNT,CS.CREATE_TIME,PUSH_SWITCH) ");
		sql.append("VALUES(");

		sql.append("'" + alarm.getAlarm_id() + "'");
		sql.append(",'"+alarm.getUser_id()+"'");
		sql.append(",'"+alarm.getAlarm_type()+"'");
		if(alarm.getAlarm_cont()!=null&&!alarm.getAlarm_cont().equals("")){
			sql.append(",'"+alarm.getAlarm_cont()+"'");
		}else{
			sql.append(",''");
		}
		sql.append(",to_date('" + alarm.getAlarm_time() + "','yymmddhh24miss')");
		if(alarm.getAlarm_end()!=null&&!alarm.getAlarm_end().equals("")){
			sql.append(",to_date('" + alarm.getAlarm_end() + "','yymmddhh24miss')");
		}else{
			sql.append(",''");
		}
		if (alarm.getConf_time() != null && !alarm.getConf_time().equals("")) {
			sql.append(",'" + alarm.getConf_time()+"'");
		} else {
			sql.append(",''");
		}
		if (alarm.getConf_usr_id() != null && !alarm.getConf_usr_id().equals("")) {
			sql.append(",'" + alarm.getConf_usr_id()+"'");
		} else {
			sql.append(",''");
		}
		if (alarm.getConf_info() != null && !alarm.getConf_info().equals("")) {
			sql.append(",'" + alarm.getConf_info()+"'");
		} else {
			sql.append(",''");
		}
		if (alarm.getVin() != null && !alarm.getVin().equals("")) {
			sql.append(",'" + alarm.getVin()+"'");
		} else {
			sql.append(",''");
		}
		if (alarm.getLn() != null && !alarm.getLn().equals("")) {
			sql.append(",'" + alarm.getLn()+"'");
		} else {
			sql.append(",''");
		}
		if (alarm.getDriver_name() != null && !alarm.getDriver_name().equals("")) {
			sql.append(",'" + alarm.getDriver_name()+"'");
		} else {
			sql.append(",''");
		}
		if (alarm.getDeal_flag() != null && !alarm.getDeal_flag().equals("")) {
			sql.append(",'" + alarm.getDeal_flag()+"'");
		} else {
			sql.append(",''");
		}
		if (alarm.getOrga_id() != null && !alarm.getOrga_id().equals("")) {
			sql.append(",'" + alarm.getOrga_id()+"'");
		} else {
			sql.append(",''");
		}
		sql.append(",'"+alarm.getClient_id()+"'");
		sql.append(",'"+state+"'");	
		sql.append(",sysdate");
		sql.append(",0");
		sql.append(",sysdate");
		sql.append(",'"+alarm.getP_s()+"'");
		sql.append(")");
//		System.out.println("mergePushAlarm:"+sql.toString());
		return sql.toString();
	}
	
	public String updatePushMsgState(String user_id, String result, String client_id, long msg_id){
		StringBuffer sb = new StringBuffer();
		sb.append("update CLW_M_PUSH_MESSAGE_RECORD_T set ");
		sb.append("client_id = '"+client_id+"'");
		sb.append(",PUSH_STATE='"+result+"'");
//		sb.append(",PUSH_TIME=sysdate");
		sb.append(",push_count=push_count+1");
		sb.append(" where user_id = '"+user_id+"'");
		sb.append(" and msg_id = "+msg_id);
//		System.out.println("updatePushMsgState:"+sb.toString());
		return sb.toString();
	}
	
	public String updatePushPhotoState(String user_id,String result,String client_id,String photo_id){
		StringBuffer sb = new StringBuffer();
		sb.append("update CLW_M_PUSH_PHOTO_RECORD_T set ");
		sb.append("client_id = '"+client_id+"'");
		sb.append(",PUSH_STATE='"+result+"'");
//		sb.append(",PUSH_TIME=sysdate");
		sb.append(",push_count=push_count+1");
		sb.append(",modify_time=sysdate");
		sb.append(" where user_id = '"+user_id+"'");
		sb.append(" and photo_id = '"+photo_id+"'");
//		System.out.println("updatePushPhotoState:"+sb.toString());
		return sb.toString();
	}
	
	
	public String updatePushAlarmState(String user_id,String result,String client_id,String alarm_id){
		StringBuffer sb = new StringBuffer();
		sb.append("update CLW_M_PUSH_ALARM_RECORD_T set ");
		sb.append("client_id = '"+client_id+"'");
		sb.append(",PUSH_STATE='"+result+"'");	
		sb.append(",push_count=push_count+1");
		sb.append(",modify_time=sysdate");
		sb.append(" where user_id = '"+user_id+"'");
		sb.append(" and alarm_id = '"+alarm_id+"'");
//		System.out.println("updatePushAlarmState:"+sb.toString());
		return sb.toString();
	}
	
	public String updateNum(long msg_id){
		StringBuffer sb = new StringBuffer();
		sb.append("update CLW_M_PUSH_MESSAGE_T set ");
		sb.append("COMP_NUM=COMP_NUM+1");
		sb.append(" where msg_id = "+msg_id);
		return sb.toString();
	}
}
