package com.neusoft.mobile.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.info.bean.MobileInfo;
import com.neusoft.mobile.bean.AlarmBean;
import com.neusoft.mobile.bean.MessageBean;
import com.neusoft.mobile.bean.PhotoBean;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class PushDAO extends AbstractDaoManager{
	@SuppressWarnings("unchecked")
	public List<AlarmBean> getPushAlarmInfo(){
		String sql = "select user_id,client_id,alarm_type_id,alarm_type_name,alarm_time,alarm_end_time,driver_id,driver_name,driver_phone,sichen_id," +
				"sichen_name,sichen_phone,route_name,trip_name,speed,vehicle_vin,alarm_id from CLW_M_PUSH_ALARM_RECORD_T where push_state <> '1' or push_state <> '3' and push_count <6";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(AlarmBean.class));
	}
	@SuppressWarnings("unchecked")
	public List<PhotoBean> getPushPhotoInfo(){
		String sql = "select user_id,client_id,photo_id,vehicle_vin from CLW_M_PUSH_PHOTO_RECORD_T where push_state <> '1' and push_count <3";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(PhotoBean.class));
	}
	@SuppressWarnings("unchecked")
	public List<MessageBean> getPushMessageInfo(){
		String sql = "select user_id,client_id,msg_id,msg_index,MSG_TYPE from CLW_M_PUSH_MESSAGE_RECORD_T where push_state = '2' and push_count <3";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(MessageBean.class));
	}
	@SuppressWarnings("unchecked")
	public List<MessageBean> getNotPushMessageInfo(String time,String systime){
		String sql ="select msg_index from CLW_M_PUSH_MESSAGE_T where msg_status = '1' and end_time >sysdate " +
				"and publish_time <= to_date('"+systime+"','yyyy-mm-dd hh24:mi:ss') and publish_time > to_date('"+time+"','yyyy-mm-dd hh24:mi:ss') " +
						"order by msg_id asc";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(MessageBean.class));
	}
	@SuppressWarnings("unchecked")
	public List<MessageBean> getAllMessageInfo(){
		String sql ="select msg_id,msg_index,MSG_TYPE from CLW_M_PUSH_MESSAGE_T where msg_status = '1' and end_time >sysdate  order by msg_id asc";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(MessageBean.class));
	}
	@SuppressWarnings("unchecked")
	public List<MobileInfo> getMobileInfo(){
		String sql = "select user_id,client_id,USER_MSG_ETAG from CLW_M_USR_INFO_T";
		return jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(MobileInfo.class));
	}
	
	public int updateMsg_index(Long msg_index){
		String sql = "update CLW_M_PUSH_MESSAGE_T set msg_index = SEQ_INDEX_M_PUSH_MESSAGE_T.NEXTVAL where msg_index = "+msg_index;
		return jdbcTemplate.update(sql);
	}
	
	public int updateParamCfg(String name,String value){
		String sql = "update clw_jc_param_cfg_t set param_value ='"+value+"' where param_name = '"+name+"'";
		return jdbcTemplate.update(sql);
	}
	
	public String getSysTime(){
		String sql = "select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual";
		return (String) jdbcTemplate.queryForObject(sql, String.class);
	}
	
	public int updatePushPeople(long people,long msg_id){
		String sql = "update  CLW_M_PUSH_MESSAGE_T set USER_NUM = "+people+" where msg_id = "+msg_id;
		return jdbcTemplate.update(sql);
	}
	
	public int updatePushNum(long num,String user_id){
		String sql = "update  CLW_M_USER_INFO_T set USER_MSG_ETAG = "+num+" where user_id = '"+user_id+"' and USER_MSG_ETAG<"+num	;
//		System.out.println(sql);
		return jdbcTemplate.update(sql);
	}
	
	public int merge(String sql){
		return jdbcTemplate.update(sql);
	}
}
