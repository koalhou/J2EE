package com.neusoft.smsplatform.message.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.smsplatform.message.bean.StuIdBean;
import com.neusoft.smsplatform.message.inside.msg.req.ReceiveStuInfoReq;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class UpdateDAO extends AbstractDaoManager {
	/**
	 * 更新企业的同步状态及时间
	 * @param enterprise_id
	 * @param result
	 * @return
	 */
	public String UpdateEnterprise_SyncFlag(String enterprise_id,String result) {
		String sql = "update CLW_JC_ENTERPRISE_T set sync_flag='"+result+"',sync_time=sysdate where enterprise_id = '"+enterprise_id+"'";
		return sql;
	}
	/**
	 * 更新学生的同步状态及时间
	 * @param stu_id
	 * @param result
	 * @return
	 */
	public String UpdateStu_SyncFlag(String stu_id,String result) {
		String sql = "update CLW_XC_STUDENT_T set sync_flag='"+result+"',sync_time=sysdate where stu_id = '"+Integer.parseInt(stu_id)+"'";
		return sql;
	}
	/**
	 * 添加或修改翔东短信订购关系
	 * @param stu_id
	 * @param phone
	 * @param type
	 * @param main
	 * @return
	 * @throws DataAccessException
	 */
	public int insertOrder(String stu_id,String phone,String type,String main) throws DataAccessException{
		String sql = "MERGE INTO CLW_XC_MESSAGE_ORDER_T CS USING DUAL " +
				"ON (CS.STU_ID = '"+Integer.parseInt(stu_id)+"' and CS.CELL_NUMBER='"+phone+"') WHEN MATCHED THEN update " +
				"SET VALID_FLAG = '0', MAIN_CELL_FLAG='"+main+"',MODIFY_TIME=sysdate WHEN NOT MATCHED THEN insert (STU_ID,CELL_NUMBER," +
				"RELATIVE_TYPE,EVENT_TYPE,ORDER_TIME,VALID_FLAG,MAIN_CELL_FLAG,MODIFY_TIME) VALUES('"+Integer.parseInt(stu_id)+"','"+phone+"','"+type+"','0000110000'," +
						"sysdate,'0','"+main+"',sysdate)";
		return jdbcTemplate.update(sql);
	}
	
	public int updateUnsubscribe(String stu_id) throws DataAccessException{
		String sql = "update CLW_XC_MESSAGE_ORDER_T set valid_flag='1',valid_time=sysdate,MODIFY_TIME=sysdate where stu_id ='"+Integer.parseInt(stu_id)+"'";
		return jdbcTemplate.update(sql);
	}
	
	public int insertChangePhone(String stu_id,String phone2){
		String sql =  "MERGE INTO CLW_XC_MESSAGE_ORDER_T CS USING DUAL " +
		"ON (CS.STU_ID = '"+Integer.parseInt(stu_id)+"' and CS.CELL_NUMBER='"+phone2+"') WHEN MATCHED THEN update " +
		"SET VALID_FLAG = '0',MODIFY_TIME=sysdate WHEN NOT MATCHED THEN insert (STU_ID,CELL_NUMBER," +
		"RELATIVE_TYPE,EVENT_TYPE,ORDER_TIME,VALID_FLAG,MAIN_CELL_FLAG,MODIFY_TIME) VALUES('"+Integer.parseInt(stu_id)+"','"+phone2+"','6','0000110000',sysdate,'0','1',sysdate)";
		return jdbcTemplate.update(sql);
	}
	
	public int insertNewChangePhone(String stu_id,String phone1,String phone2){
		String sql =  "MERGE INTO CLW_XC_MESSAGE_ORDER_T CS USING (select stu_id,cell_number,relative_type,event_type,order_time,valid_flag,main_cell_flag " +
				"from clw_xc_message_order_t where cell_number = '"+phone1+"' and stu_id = '"+Integer.parseInt(stu_id)+"') tt " +
    "ON (CS.STU_ID = '"+Integer.parseInt(stu_id)+"' and CS.CELL_NUMBER='"+phone2+"') WHEN MATCHED THEN update " +
    "SET VALID_FLAG = '0',MODIFY_TIME=sysdate WHEN NOT MATCHED THEN insert (STU_ID,CELL_NUMBER," +
    "RELATIVE_TYPE,EVENT_TYPE,ORDER_TIME,VALID_FLAG,MAIN_CELL_FLAG,MODIFY_TIME) VALUES('"+Integer.parseInt(stu_id)+"','"+phone2+"',tt.relative_type,tt.event_type,tt.order_time,'0',tt.main_cell_flag,sysdate)";
		System.out.println("sql:"+sql);
		return jdbcTemplate.update(sql);
	}
	
	public int updateChangePhoneValid(String stu_id,String phone1){
		String sql = "update CLW_XC_MESSAGE_ORDER_T set valid_flag='1',MODIFY_TIME=sysdate,VALID_TIME=sysdate where stu_id ='"+Integer.parseInt(stu_id)+"' and cell_number='"+phone1+"'";
		return jdbcTemplate.update(sql);
	}
	
	public int deleteChangePhone(String stu_id,String phone1){
		String sql = "update CLW_XC_MESSAGE_ORDER_T set valid_flag = '1',valid_time = sysdate,MODIFY_TIME=sysdate where stu_id ='"+Integer.parseInt(stu_id)+"' and cell_number='"+phone1+"'";
		return jdbcTemplate.update(sql);
	}
	
	public int dbMethod(String sql){
		return jdbcTemplate.update(sql);
	}
	
	public String updateCheckRecord(String id,String state, String string){
		String sql = "update CLW_XC_ST_CHECK_RECORD_T set MESG_FLAG = '"+state+"',SMS_FAIL_INFO = '"+string+"' where id = '"+id+"'";
		return sql;
	}
	
	public String updateSmsRecord(String id,String state, String string){
		String sql = "update CLW_YW_SMS_RECORD_T set STATE = '"+state+"',SMS_NUM = '"+state+"',SMS_FAIL_INFO = '"+string+"' where id = '"+id+"'";
//		System.out.println(sql);
		return sql;
	}
	
	public String insertStuInfo(ReceiveStuInfoReq req,int sequence) throws Exception{
		String sql = "insert into clw_xc_student_t (STU_ID, STU_CARD_ID, STU_CODE, STU_NAME,STU_SEX, STU_BIRTH, STU_ADDRESS, " +
				"STU_SCHOOL, STU_CLASS, STU_REMARK, TEACHER_TEL, RELATIVE_TEL, RELATIVE_TYPE, ENTERPRISE_ID, ORGANIZATION_ID, " +
				"CREATER,CREATE_TIME, VALID_FLAG,TEACHER_NAME, RELATIVE_NAME, STU_PROVINCE, STU_CITY, STU_DISTRICT) values ("+sequence+",'" +
				req.getStu_card_id() +"'"+
				",'"+req.getStu_code()+"','"+req.getStu_name()+"','"+req.getStu_sex()+"',to_date('"+req.getStu_birth()+"','yyyymmdd'),'"+req.getStu_address()+"'," +
				"'"+req.getStu_school()+"','"+req.getStu_class()+"','"+req.getStu_remark()+"','"+req.getTeacher_tel()+"','"+req.getRelative_tel()+"'" +
				",'"+req.getRelative_type()+"','"+req.getEnterprise_id()+"','"+req.getOrganization_id()+"','clwxc',sysdate,'0'" +
				",'"+req.getTeacher_name()+"','"+req.getRelative_name()+"','"+req.getStu_province()+"','"+req.getStu_city()+"','"+req.getStu_district()+"')";
//		System.out.println("sql:"+sql);
		return sql;
	}
	
	public String updateStuInfoByCardId(ReceiveStuInfoReq req){
		String sql = "update clw_xc_student_t set stu_code = '"+req.getStu_code()+"',STU_NAME = '"+req.getStu_name()+"',STU_SEX='"+req.getStu_sex()+"'," +
				"STU_BIRTH=to_date('"+req.getStu_birth()+"','yyyymmdd'),STU_ADDRESS='"+req.getStu_address()+"',STU_SCHOOL='"+req.getStu_school()+"'," +
				"STU_CLASS='"+req.getStu_class()+"',STU_REMARK='"+req.getStu_remark()+"',TEACHER_TEL='"+req.getTeacher_tel()+"',RELATIVE_TEL='"+req.getRelative_tel()+"'," +
				"RELATIVE_TYPE='"+req.getRelative_type()+"',ENTERPRISE_ID='"+req.getEnterprise_id()+"',ORGANIZATION_ID='"+req.getOrganization_id()+"'," +
				"MODIFIER='clwxc',MODIFY_TIME=sysdate,TEACHER_NAME='"+req.getTeacher_name()+"',RELATIVE_NAME='"+req.getRelative_name()+"'," +
				"STU_PROVINCE='"+req.getStu_province()+"',STU_CITY='"+req.getStu_city()+"',STU_DISTRICT='"+req.getStu_district()+"' " +
				"WHERE STU_ID = '"+req.getStu_id() +"'";
//		System.out.println(sql);
		return sql;
	}
	
	public String updateStuInfoByStuId(ReceiveStuInfoReq req,int sequence){
		String sql = "MERGE INTO CLW_XC_STUDENT_T CS USING DUAL ON (CS.STU_ID = "+Integer.parseInt(req.getStu_id())+" AND CS.VALID_FLAG = '0') WHEN MATCHED THEN UPDATE SET stu_card_id = '"+req.getStu_card_id()+"',stu_code = '"+req.getStu_code()+"'," +
				"STU_NAME = '"+req.getStu_name()+"',STU_SEX='"+req.getStu_sex()+"'," +
				"STU_BIRTH=to_date('"+req.getStu_birth()+"','yyyymmdd'),STU_ADDRESS='"+req.getStu_address()+"',STU_SCHOOL='"+req.getStu_school()+"'," +
				"STU_CLASS='"+req.getStu_class()+"',STU_REMARK='"+req.getStu_remark()+"',TEACHER_TEL='"+req.getTeacher_tel()+"',RELATIVE_TEL='"+req.getRelative_tel()+"'," +
				"RELATIVE_TYPE='"+req.getRelative_type()+"',ENTERPRISE_ID='"+req.getEnterprise_id()+"',ORGANIZATION_ID='"+req.getOrganization_id()+"'," +
				"MODIFIER='clwxc',MODIFY_TIME=sysdate,TEACHER_NAME='"+req.getTeacher_name()+"',RELATIVE_NAME='"+req.getRelative_name()+"'," +
				"STU_PROVINCE='"+req.getStu_province()+"',STU_CITY='"+req.getStu_city()+"',STU_DISTRICT='"+req.getStu_district()+"' " +
				"WHEN NOT MATCHED THEN INSERT (STU_ID, STU_CARD_ID, STU_CODE, STU_NAME,STU_SEX, STU_BIRTH, STU_ADDRESS, " +
				"STU_SCHOOL, STU_CLASS, STU_REMARK, TEACHER_TEL, RELATIVE_TEL, RELATIVE_TYPE, ENTERPRISE_ID, ORGANIZATION_ID, " +
				"CREATER,CREATE_TIME, VALID_FLAG,TEACHER_NAME, RELATIVE_NAME, STU_PROVINCE, STU_CITY, STU_DISTRICT) values ("+sequence+",'" +
				req.getStu_card_id() +"'"+
				",'"+req.getStu_code()+"','"+req.getStu_name()+"','"+req.getStu_sex()+"',to_date('"+req.getStu_birth()+"','yyyymmdd'),'"+req.getStu_address()+"'," +
				"'"+req.getStu_school()+"','"+req.getStu_class()+"','"+req.getStu_remark()+"','"+req.getTeacher_tel()+"','"+req.getRelative_tel()+"'" +
				",'"+req.getRelative_type()+"','"+req.getEnterprise_id()+"','"+req.getOrganization_id()+"','clwxc',sysdate,'0'" +
				",'"+req.getTeacher_name()+"','"+req.getRelative_name()+"','"+req.getStu_province()+"','"+req.getStu_city()+"','"+req.getStu_district()+"')";
//		System.out.println(sql);
		return sql;
	}
	
	public int selectOracleSequence() throws Exception{
		String sql = "select SEQ_XC_STUDENT_T.NEXTVAL from dual";
		return jdbcTemplate.queryForInt(sql);
	}
	
	@SuppressWarnings("unchecked")
	public List<StuIdBean> selectStuIdByCardId(String cardid){
		String sql = "SELECT STU_ID FROM CLW_XC_STUDENT_T WHERE STU_CARD_ID = '"+cardid+"' AND VALID_FLAG = '0'";
		return  (List<StuIdBean>) jdbcTemplate.query(sql,ParameterizedBeanPropertyRowMapper.newInstance(StuIdBean.class));
	}
	
	
}
