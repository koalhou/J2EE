package com.neusoft.smsplatform.message.dao;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.smsplatform.message.domain.EnterpriseInfoBean;
import com.neusoft.smsplatform.message.domain.StuInfoBean;
import com.neusoft.tag.dao.support.AbstractDaoManager;

public class SmsSyncDAO extends AbstractDaoManager {
	
	@SuppressWarnings("unchecked")
	public List<StuInfoBean> getStuInfo(){
		String sql = "select stu_id,stu_card_id,stu_code,stu_name,stu_sex,to_char(stu_birth,'yyyymmdd'),stu_address,stu_province,stu_city,stu_district,stu_school,stu_class,stu_remark,teacher_name,teacher_tel,relative_name,relative_tel,relative_type,enterprise_id,organization_id,valid_flag from clw_xc_student_t";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StuInfoBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<EnterpriseInfoBean> getEnterpriseInfo(){
		String sql = "select enterprise_id,enterprise_code,full_name,short_name,parent_id,enterprise_country,enterprise_province,enterprise_city,enterprise_desc,address,contact_p,contact_tel,valid_flag from clw_jc_enterprise_t";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(EnterpriseInfoBean.class));
	}
	
	
	@SuppressWarnings("unchecked")
	public List<StuInfoBean> getStuInfo_syncError(){
		String sql = "select stu_id,stu_card_id,stu_code,stu_name,stu_sex,to_char(stu_birth,'yyyymmdd'),stu_address,stu_province,stu_city,stu_district,stu_school,stu_class,stu_remark,teacher_name,teacher_tel,relative_name,relative_tel,relative_type,enterprise_id,organization_id,valid_flag from clw_xc_student_t where sync_flag <> '0' or sync_flag is null";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(StuInfoBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<EnterpriseInfoBean> getEnterpriseInfo_syncError(){
		String sql = "select enterprise_id,enterprise_code,full_name,short_name,parent_id,enterprise_country,enterprise_province,enterprise_city,enterprise_desc,address,contact_p,contact_tel,valid_flag from clw_jc_enterprise_t where sync_flag <> '0' or sync_flag is null";
		return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(EnterpriseInfoBean.class));
	}
	
	public int updateSync(String table){
		String sql = "update "+ table +" set sync_flag='',sync_time = ''";
		return jdbcTemplate.update(sql);
	}
}
