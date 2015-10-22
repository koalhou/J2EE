package com.yutong.clw.dao.xc;

import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.yutong.clw.beans.xc.EnterPriseBean;
import com.yutong.clw.beans.xc.RouteSiteBean;
import com.yutong.clw.beans.xc.XcSiteBean;
import com.yutong.clw.beans.xc.XcStuSmsBean;
import com.yutong.clw.beans.xc.XcStuSmsVTBean;
import com.yutong.clw.beans.xc.XcStudentBean;
import com.yutong.clw.beans.xc.XcvsseBean;
import com.yutong.clw.dao.IXcSmsDAO;
import com.yutong.clw.dao.analysis.AbstractDaoManager;

public class XcSmsDAO extends AbstractDaoManager implements IXcSmsDAO{
    
	//查询缓存所需的学生短信配置
	@SuppressWarnings("unchecked")
	public List<XcStuSmsBean> getStuSMSParam() {
		String SELECT_XC_SMS_PARAM = "SELECT STU_ID,EVENT_TYPE,CELL_NUMBER,RELATIVE_TYPE,RELATIVE_NAME,to_char(END_TIME, 'yyyy-mm-dd') AS END_TIME,PARENTS_FLAG " +
        "from CLW_XC_STUSMS_T where valid_flag = '0' ORDER BY STU_ID,EVENT_TYPE DESC";
		return jdbcTemplate.query(SELECT_XC_SMS_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcStuSmsBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<XcStuSmsBean> getStuSMSParam(String time,String systime) {
		String SELECT_XC_SMS_PARAM = "SELECT DISTINCT STU_ID,EVENT_TYPE " +
				"from CLW_XC_STUSMS_T WHERE CREATE_TIME <= to_date('"+systime+"','yymmddhh24miss') and CREATE_TIME > to_date('"+time+"','yymmddhh24miss')";
		return jdbcTemplate.query(SELECT_XC_SMS_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcStuSmsBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<XcStuSmsBean> getStuSMSParamByKey(String stu_id,String event_type) {
		String SELECT_XC_SMS_PARAM = "SELECT STU_ID,EVENT_TYPE,CELL_NUMBER,RELATIVE_TYPE,RELATIVE_NAME,to_char(END_TIME, 'yyyy-mm-dd') AS END_TIME,PARENTS_FLAG " +
        "from CLW_XC_STUSMS_T WHERE STU_ID = '"+stu_id+"' and event_type = '"+event_type+"' and valid_flag = '0'";
		System.out.println(SELECT_XC_SMS_PARAM);
		return jdbcTemplate.query(SELECT_XC_SMS_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcStuSmsBean.class));
	}
	
	//查询缓存所需的学生短信配置V2.0
	@SuppressWarnings("unchecked")
	public List<XcStuSmsVTBean> getStuSMSParamVT() {
		String SELECT_XC_SMSVT_PARAM = "SELECT STU_ID,CELL_NUMBER,EVENT_TYPE,RELATIVE_TYPE,to_char(ORDER_TIME, 'yyyy-mm-dd') AS ORDER_TIME,VALID_FLAG,to_char(VALID_TIME, 'yyyy-mm-dd') AS VALID_TIME " +
        "from CLW_XC_MESSAGE_ORDER_T WHERE VALID_FLAG ='0' ORDER BY STU_ID DESC";
		return jdbcTemplate.query(SELECT_XC_SMSVT_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcStuSmsVTBean.class));
	}	
	//查询缓存所需的企业信息
	@SuppressWarnings("unchecked")
	public List<EnterPriseBean> getEnterPriseParam() {
		String SELECT_ENTERPRISE_PARAM = "SELECT ENTERPRISE_ID,MSG_NUM,ENTERPRISE_SMGATEWAY from CLW_JC_ENTERPRISE_T";
		return jdbcTemplate.query(SELECT_ENTERPRISE_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(EnterPriseBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<EnterPriseBean> getEnterPriseParam(String systime) {
		String SELECT_ENTERPRISE_PARAM = "SELECT ENTERPRISE_ID,MSG_NUM,ENTERPRISE_SMGATEWAY,VALID_FLAG from CLW_JC_ENTERPRISE_T where MODIFY_TIME <=sysdate and MODIFY_TIME > to_date('"+systime+"','yymmddhh24miss')";
		return jdbcTemplate.query(SELECT_ENTERPRISE_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(EnterPriseBean.class));
	}
	//查询学生所属企业
	public String getEnterprise_By_Stu(String stucode) throws Exception{
		String SELECT_ENTERPRISE_VEHICLE_SQL = "select  t.enterprise_id from clw_xc_student_t t "
			+ "where VALID_FLAG='0' and t.stu_id = to_number(?)";
		return (String) jdbcTemplate.queryForObject(SELECT_ENTERPRISE_VEHICLE_SQL, new String[] { stucode },
				String.class);
	}
	//查询缓存所需的学生信息
	@SuppressWarnings("unchecked")
	public List<XcStudentBean> getStudentParam() {
		String SELECT_STUDENT_PARAM = "SELECT STU_ID,STU_CARD_ID,STU_NAME,ENTERPRISE_ID,ORGANIZATION_ID from CLW_XC_STUDENT_T WHERE VALID_FLAG ='0'";
		return jdbcTemplate.query(SELECT_STUDENT_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcStudentBean.class));
	}
	
	//查询缓存站点信息
	@SuppressWarnings("unchecked")
	public List<XcSiteBean> getSiteParam() {
		String SELECT_SITE_PARAM = "SELECT SITE_ID,SITE_NAME from CLW_XC_SITE_T WHERE VALID_FLAG ='0'";
		return jdbcTemplate.query(SELECT_SITE_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcSiteBean.class));
	}
	
	@SuppressWarnings("unchecked")
	public List<XcSiteBean> getSiteParam(String time) {
		String SELECT_SITE_PARAM = "SELECT SITE_ID,SITE_NAME,VALID_FLAG from CLW_XC_SITE_T WHERE MODIFY_TIME <=sysdate and MODIFY_TIME > to_date('"+time+"','yymmddhh24miss')";
		return jdbcTemplate.query(SELECT_SITE_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcSiteBean.class));
	}
	//查询数据库当前时间
	public String getSysTime(){
		String SELECT_SYS_TIME = "SELECT TO_CHAR(SYSDATE,'yymmddhh24miss') FROM DUAL";
		return (String) jdbcTemplate.queryForObject(SELECT_SYS_TIME, String.class);
	}
	//查询学生信息增量
	@SuppressWarnings("unchecked")
	public List<XcStudentBean> getAllStudentParam(String time) {
		String SELECT_ALLSTUDENT_SQL = "SELECT STU_ID,STU_NAME,STU_CARD_ID,ENTERPRISE_ID,ORGANIZATION_ID,VALID_FLAG FROM CLW_XC_STUDENT_T where MODIFY_TIME<=sysdate" +
		" and MODIFY_TIME >= to_date(?,'yymmddhh24miss')";
		return jdbcTemplate.query(SELECT_ALLSTUDENT_SQL,new String[] {time},ParameterizedBeanPropertyRowMapper.newInstance(XcStudentBean.class));
	}
	//查询线路站点关系表全量信息	
	@SuppressWarnings("unchecked")
	public List<RouteSiteBean> getRouteSiteParam() {
		String SELECT_ROUTESITE_PARAM = "SELECT SITE_ID,ROUTE_ID,SITE_UPDOWN from CLW_XC_ROUTESITE_T";
		return jdbcTemplate.query(SELECT_ROUTESITE_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(RouteSiteBean.class));
	}
	//查询站点订购表全量信息
	@SuppressWarnings("unchecked")
	public List<XcvsseBean> getVssParam() {
		String SELECT_VSS_PARAM = "SELECT DISTINCT T.VEHICLE_VIN,T.STUDENT_ID,T.ROUTE_ID,T.SITE_ID,T.VSS_STATE,R.SITE_UPDOWN,T.TRIP_ID " +
				"FROM CLW_XC_VSS_T T, CLW_XC_ROUTESITE_T R WHERE T.SITE_ID = R.SITE_ID and T.ROUTE_ID = R.ROUTE_ID";
		return jdbcTemplate.query(SELECT_VSS_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcvsseBean.class));
	}

	@SuppressWarnings("unchecked")
	public List<XcStuSmsVTBean> getStuSMSParamVTAdd(String lastIncremSyncDate) {
		String SELECT_XC_SMSVT_PARAM = "SELECT STU_ID,CELL_NUMBER,EVENT_TYPE,RELATIVE_TYPE,to_char(ORDER_TIME, 'yyyy-mm-dd') AS ORDER_TIME,VALID_FLAG,to_char(VALID_TIME, 'yyyy-mm-dd') AS VALID_TIME " +
        "from CLW_XC_MESSAGE_ORDER_T WHERE MODIFY_TIME>to_date('"+lastIncremSyncDate+"','yymmddhh24miss')   ORDER BY STU_ID DESC";
		return jdbcTemplate.query(SELECT_XC_SMSVT_PARAM,ParameterizedBeanPropertyRowMapper.newInstance(XcStuSmsVTBean.class));
	}	
}
