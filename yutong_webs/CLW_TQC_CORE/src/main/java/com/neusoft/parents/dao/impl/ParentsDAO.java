package com.neusoft.parents.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.util.IdCreater;
import com.neusoft.parents.bean.NoticeBean;
import com.neusoft.parents.bean.Parents;
import com.neusoft.parents.bean.Rules;
import com.neusoft.parents.bean.Site;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.Students;
import com.neusoft.parents.bean.Vehicle;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.service.ParentsBuildSQL;
import com.neusoft.parents.sitenotemanager.SiteNoteManager;
import com.neusoft.tag.dao.support.AbstractDaoManager;
import com.neusoft.parents.bean.News;

public class ParentsDAO extends AbstractDaoManager implements IParentsDAO
{
	
	private static Logger log = LoggerFactory.getLogger(ParentsDAO.class);

    public String getSysTime()
    {
        String SELECT_SYS_TIME = "SELECT TO_CHAR(SYSDATE,'yymmddhh24miss') FROM DUAL";
        return (String) jdbcTemplate.queryForObject(SELECT_SYS_TIME, String.class);
    }
    
    public String getVehicleCode(String vehicle_vin)
    {
        String vehiclecode = " select t.vehicle_code from clw_cl_base_info_t t where t.vehicle_vin = ? and t.valid_flag = '0'";
        return (String) jdbcTemplate.queryForObject(vehiclecode,new String[] { vehicle_vin }, String.class);
    }


    public List<Rules> getRulesInfo()
    {
        String sql = "select T.RULE_ID,T.USER_ID,T.STU_ID,T.PM_RULE_ID,T.ON_OFF,T.FLAG,T.VALID_FLAG from CLW_JZ_PUSHRULE_T T where T.On_Off='1'";
        return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(Rules.class));
    }


    public List<VehicleReal> getVehicleRealInfo()
    {
        //String sql = "SELECT T1.VEHICLE_VIN,T1.LATITUDE,T1.LONGITUDE,T1.DIRECTION,T1.Terminal_Time,t3.vehicle_ln,t3.vehicle_code,t1.stat_info,decode(t1.SPEED_SOURCE_SETTING,'0',t1.speeding,t1.gps_speeding) speed "
          //      + "FROM CLW_JC_TERMINAL_T T1 inner join CLW_JC_ENTERPRISE_T T2 on T2.Enterprise_Id=T1.Enterprise_Id inner join clw_cl_base_info_t t3 on t3.vehicle_vin=t1.vehicle_vin ";  //WHERE  T2.ENTERPRISE_MODEL IN ('2', '3')";
    	String sql = " SELECT T1.VEHICLE_VIN,T1.LATITUDE,T1.LONGITUDE,T1.DIRECTION,T1.Terminal_Time,t3.vehicle_ln,t3.vehicle_code,t1.stat_info,decode(t1.SPEED_SOURCE_SETTING, '0', t1.speeding, t1.gps_speeding) speed,m.trip_id,m.route_id " +
    			" FROM CLW_JC_TERMINAL_T T1,CLW_JC_ENTERPRISE_T T2,clw_cl_base_info_t t3, " +
    			" (select t.vehicle_vin,t4.trip_id, t4.route_id, t4.status " +
    			" from CLW_JC_TERMINAL_T t,tqc_trip_execute t4 " +
    			" where t.vehicle_vin = t4.vehicle_vin " +
    			" and t4.exe_date = trunc(sysdate, 'dd') " +
    			" and t4.status = 1) m " +
    			" where  T1.Enterprise_Id = T2.Enterprise_Id  " +
    			" and  t1.vehicle_vin = t3.vehicle_vin " +
    			" and t3.valid_flag = '0' " +
    			" and t1.vehicle_vin = m.vehicle_vin(+) ";  //WHERE  T2.ENTERPRISE_MODEL IN ('2', '3')";
        return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(VehicleReal.class));
    }

    public VehicleReal getVehicleRealByVin(String vin)
    {
        //String sql = "SELECT T1.VEHICLE_VIN,T1.LATITUDE,T1.LONGITUDE,T1.DIRECTION,T1.Terminal_Time,t3.vehicle_ln,t1.stat_info "
                //+ "FROM CLW_JC_TERMINAL_T T1 inner join CLW_JC_ENTERPRISE_T T2 on T2.Enterprise_Id=T1.Enterprise_Id inner join clw_cl_base_info_t t3 on t3.vehicle_vin=t1.vehicle_vin  WHERE  T2.ENTERPRISE_MODEL IN ('2', '3') and t1.vehicle_vin=?";
    	String sql = "SELECT T1.VEHICLE_VIN,T1.LATITUDE,T1.LONGITUDE,T1.DIRECTION,T1.Terminal_Time,decode(t1.SPEED_SOURCE_SETTING,'0',t1.speeding,t1.gps_speeding) speed,t3.vehicle_ln,t3.vehicle_code,t1.stat_info "
            + " FROM CLW_JC_TERMINAL_T T1 inner join CLW_JC_ENTERPRISE_T T2 on T2.Enterprise_Id=T1.Enterprise_Id inner join clw_cl_base_info_t t3 on t3.vehicle_vin=t1.vehicle_vin  WHERE t1.vehicle_vin=? and t3.valid_flag = '0' ";
    	return (VehicleReal) jdbcTemplate.queryForObject(sql, new String[] { vin }, ParameterizedBeanPropertyRowMapper.newInstance(VehicleReal.class));
    }
    /**
     * Test测试用的数据    后期删除掉
     * @param vin
     * @return
     */
    public List<VehicleReal> getTestVin(int flag)
    {
        //String sql = "SELECT T1.VEHICLE_VIN,T1.LATITUDE,T1.LONGITUDE,T1.DIRECTION,T1.Terminal_Time,t3.vehicle_ln,t1.stat_info "
                //+ "FROM CLW_JC_TERMINAL_T T1 inner join CLW_JC_ENTERPRISE_T T2 on T2.Enterprise_Id=T1.Enterprise_Id inner join clw_cl_base_info_t t3 on t3.vehicle_vin=t1.vehicle_vin  WHERE  T2.ENTERPRISE_MODEL IN ('2', '3') and t1.vehicle_vin=?";
    	String sql = "  SELECT T1.VEHICLE_VIN,T1.LATITUDE,T1.LONGITUDE,T1.DIRECTION,T1.Terminal_Time, " +
		" decode(t1.SPEED_SOURCE_SETTING,'0',t1.speeding,t1.gps_speeding) speed,t3.vehicle_ln,t3.vehicle_code,t.trip_id,t1.stat_info " +
		" FROM CLW_JC_TERMINAL_T T1,TQC_TRIP_EXECUTE t,CLW_JC_ENTERPRISE_T T2, clw_cl_base_info_t t3,clw_xc_route_t tt  " +
		" WHERE t1.vehicle_vin = t.vehicle_vin " +
		" and t.route_id = tt.route_id " +
		" and T2.Enterprise_Id = T1.Enterprise_Id " +
		" and t3.vehicle_vin = t1.vehicle_vin " +
		" and t3.valid_flag = '0' " +
		" and t.exe_date = trunc(sysdate, 'DD') " +
		" and ((0 = ? and  tt.route_class = 2 ) or (1 = ? and tt.route_class <> 2 )) ";
    	return  jdbcTemplate.query(sql, new Object[] { flag, flag}, ParameterizedBeanPropertyRowMapper.newInstance(VehicleReal.class));
    }
    
    public List<VehicleReal> getVehicleVin()
    {
    	String sql = "SELECT distinct (T.VEHICLE_VIN) vehicle_vin " +
    			" FROM CLW_JC_TERMINAL_T T, TQC_TRIP_EXECUTE t1,clw_cl_base_info_t t2 " +
    			" where t.vehicle_vin = t1.vehicle_vin " +
    			" and t.vehicle_vin = t2.vehicle_vin " +
    			" and t2.valid_flag = '0' " +
    			" and t1.exe_date = trunc(sysdate, 'DD') ";
    	
    	return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(VehicleReal.class));
    }
    
    

    public List<News> getNewsList(){
    	String sql = "select t.gonggao_id news_id,t.gonggao_title news_title,t.gonggao_summary news_summary,t.gonggao_date news_date,t.gonggao_type news_type " +
    			" from CLW_TQC_GONGGAO_T t " +
    			" where t.gonggao_date >= trunc(sysdate, 'DD') " +
    			" and t.send_flag <> '1' ";
  
    	return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(News.class));
    }
    
    public List<News> getEmpCode(){
    	String sql = " select t.emp_code from clw_tqc_ygb.CLW_TQC_EMP_T t where t.ygb_tel is not null and substr(t.push_rule ,3,1)='1' ";
  
    	return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(News.class));
    }
    
    public List<StuSiteNote> getStuSiteNote(int weekDay)
    {

    	String sql = " select * from " +
    	" (select mm.route_id,mm.route_name,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin, " +
    	" m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,m.belong_area,m.note_calc_type remind_type,mm.route_class status_range,1 remind_range  " +
		" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n," +
		" (select t.route_id,t2.route_name,t.site_id,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_class,t2.route_organization_id " +
		" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2 " +
		" where t.route_id = t1.route_id " +
		" and t.route_id = t2.route_id " +
		//" and t.route_id in (523,760,700) " +    //推广线路，全面推广时，要把这些取消
		" and t1.valid_flag = '0' " +
		" and t2.valid_flag = '0' " +
		" and t1.exe_date = trunc(sysdate, 'DD')) mm " +
		" where m.site_id = mm.site_id " +
		" and m.emp_code = n.emp_code " +
		" and (m.up_down = mm.route_class or m.up_down = '-1') " +       //早班晚班区分开，同时 排除厂内通勤
		" and m.vehicle_vin = mm.vehicle_vin  " +
		" and substr(n.push_rule ,7,1)='1' " + 
		" and  (substr(m.ahead_often ,?,1)='1' or m.ahead_often = '0000000') " +  //加入星期几重复判断，如果是1：提醒，其他的均不提醒
		" and m.note_calc_type <> 2 " +     
		" and m.valid_flag = '1' " +
		" and  mm.route_organization_id in ( " +
		" select enterprise_id from CLW_JC_ENTERPRISE_T, zspt_later_param ad " +
		" where left_num >= (select left_num from CLW_JC_ENTERPRISE_T  " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area ) " +
		" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T  " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area ))" +
		" union all " +
		" select mm.route_id,mm.route_name,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin, " +
		" m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,m.belong_area,m.note_calc_type remind_type,mm.route_class status_range,0 remind_range " +
		" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n , " +
		" (select t.route_id,t2.route_name,t.site_id,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_class,t2.route_organization_id " +
		" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2 " +
		" where t.route_id = t1.route_id " +
		" and t.route_id = t2.route_id " +
		//" and t.route_id in (523,760,700) " +    //推广线路，全面推广时，要把这些取消
		" and t1.valid_flag = '0' " +
		" and t2.valid_flag = '0' " +
		" and t1.exe_date = trunc(sysdate, 'DD')) mm  " +
		" where m.site_id = mm.site_id " +
		" and m.emp_code = n.emp_code " +
		" and (m.up_down = mm.route_class or m.up_down = '-1') " +        //早班晚班区分开，同时 排除厂内通勤
		" and m.vehicle_vin is null " +
		" and substr(n.push_rule ,7,1)='1' " + 
		" and  (substr(m.ahead_often ,?,1)='1' or m.ahead_often = '0000000') " +   //加入星期几重复判断，如果是1：提醒，其他的均不提醒
		" and m.note_calc_type <> 2 " +   
		" and m.valid_flag = '1' " +
		" and (to_date(m.no_remind_date,'yyyy-mm-dd') <> trunc(sysdate, 'DD') or m.no_remind_date is null ) " +
		" and  mm.route_organization_id in (select enterprise_id from CLW_JC_ENTERPRISE_T, zspt_later_param ad " +
		" where left_num >= (select left_num from CLW_JC_ENTERPRISE_T " +
		" where enterprise_id = ad.organization_id " +
		" and ad.organization_type = m.belong_area ) " +
		" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area )) ) aa " +
		
		" where  not exists (  select 1 from clw_tqc_ygb.clw_tqc_emp_notelog_t t  " +
		" where aa.emp_code = t.emp_id   " +
		" and  aa.note_id =  t.note_id   " +
		" and  aa.trip_id = t.trip_id  " +
		" and t.send_time >= trunc(sysdate, 'DD'))" ;
    	
        return jdbcTemplate.query(sql, new Object[] {weekDay,weekDay}, ParameterizedBeanPropertyRowMapper.newInstance(StuSiteNote.class));
    }
    
    public List<StuSiteNote> delVinTrip()
    {
    	
    	String sql = " select  t.vehicle_vin, t.trip_id " +
    			" from tqc_trip_execute t  " +
    			" where  t.valid_flag = '0'  " +
    			" and t.exe_date = trunc(sysdate-1, 'DD') " ;
    	
        return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(StuSiteNote.class));
    }
    /**
     * 通过员工订购信息来获取车辆的VIN和行程
     */
    public List<StuSiteNote> getVinTrip(int weekDay)
    {   	
    	//String sql = " select distinct aa.vehicle_vin,aa.trip_id  from " +
    	String sql = " select *  from " +
    	" (select mm.route_id,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin, " +
    	" m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,m.belong_area,m.note_calc_type remind_type,mm.route_class status_range,1 remind_range  " +
		" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n," +
		" (select t.route_id,t.site_id,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_class,t2.route_organization_id " +
		" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2 " +
		" where t.route_id = t1.route_id " +
		" and t.route_id = t2.route_id " +
		//" and t.route_id in (523,760,700) " +    //推广线路，全面推广时，要把这些取消
		" and t1.valid_flag = '0' " +
		" and t2.valid_flag = '0' " +
		" and t1.exe_date = trunc(sysdate, 'DD')) mm " +
		" where m.site_id = mm.site_id " +
		" and m.emp_code = n.emp_code " +
		" and (m.up_down = mm.route_class or m.up_down = '-1') " +       //早班晚班区分开，同时 排除厂内通勤
		" and m.vehicle_vin = mm.vehicle_vin  " +
		" and substr(n.push_rule ,7,1)='1' " + 
		" and  (substr(m.ahead_often ,?,1)='1' or m.ahead_often = '0000000') " +  //加入星期几重复判断，如果是1：提醒，其他的均不提醒
		" and m.note_calc_type <> 2 " +     
		" and m.valid_flag = '1' " +
		" and  mm.route_organization_id in ( " +
		" select enterprise_id from CLW_JC_ENTERPRISE_T, zspt_later_param ad " +
		" where left_num >= (select left_num from CLW_JC_ENTERPRISE_T  " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area ) " +
		" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T  " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area ))" +
		" union all " +
		" select mm.route_id,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin, " +
		" m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,m.belong_area,m.note_calc_type remind_type,mm.route_class status_range,0 remind_range " +
		" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n , " +
		" (select t.route_id,t.site_id,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_class,t2.route_organization_id " +
		" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2 " +
		" where t.route_id = t1.route_id " +
		" and t.route_id = t2.route_id " +
		//" and t.route_id in (523,760,700) " +    //推广线路，全面推广时，要把这些取消
		" and t1.valid_flag = '0' " +
		" and t2.valid_flag = '0' " +
		" and t1.exe_date = trunc(sysdate, 'DD')) mm  " +
		" where m.site_id = mm.site_id " +
		" and m.emp_code = n.emp_code " +
		" and (m.up_down = mm.route_class or m.up_down = '-1') " +        //早班晚班区分开，同时 排除厂内通勤
		" and m.vehicle_vin is null " +
		" and substr(n.push_rule ,7,1)='1' " + 
		" and  (substr(m.ahead_often ,?,1)='1' or m.ahead_often = '0000000') " +   //加入星期几重复判断，如果是1：提醒，其他的均不提醒
		" and m.note_calc_type <> 2 " +   
		" and m.valid_flag = '1' " +
		" and (to_date(m.no_remind_date,'yyyy-mm-dd') <> trunc(sysdate, 'DD') or m.no_remind_date is null ) " +
		" and  mm.route_organization_id in (select enterprise_id from CLW_JC_ENTERPRISE_T, zspt_later_param ad " +
		" where left_num >= (select left_num from CLW_JC_ENTERPRISE_T " +
		" where enterprise_id = ad.organization_id " +
		" and ad.organization_type = m.belong_area ) " +
		" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area )) ) aa " +
		
		" where  not exists (  select 1 from clw_tqc_ygb.clw_tqc_emp_notelog_t t  " +
		" where aa.emp_code = t.emp_id   " +
		" and  aa.note_id =  t.note_id   " +
		" and aa.vehicle_vin = t.vehicle_vin " +
		" and  aa.trip_id = t.trip_id  " +
		" and t.send_time >= trunc(sysdate, 'DD')) " +
		" order by aa.vehicle_vin asc,aa.trip_id asc" ;
    	
        return jdbcTemplate.query(sql, new Object[] {weekDay,weekDay}, ParameterizedBeanPropertyRowMapper.newInstance(StuSiteNote.class));
    }
   
    /**
     * 厂外提醒  根据车辆vin和行程获取员工提醒订购信息
     */
    
   public List<StuSiteNote> getVinTripList(String vin,String trip,int weekDay)
     {
	   String sql = " select * from " +
	   " (select mm.route_id,mm.route_name,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin, " +
	   " m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,m.belong_area,m.note_calc_type remind_type,mm.route_class status_range,1 remind_range  " +
		" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n," +
		" (select t.route_id,t2.route_name,t.site_id,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_class,t2.route_organization_id " +
		" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2 " +
		" where t.route_id = t1.route_id " +
		" and t.route_id = t2.route_id " +
		//" and t.route_id in (523,760,700) " +    //推广线路，全面推广时，要把这些取消
		" and t1.valid_flag = '0' " +
		" and t2.valid_flag = '0' " +
		" and t1.exe_date = trunc(sysdate, 'DD')) mm " +
		" where m.site_id = mm.site_id " +
		" and m.emp_code = n.emp_code " +
		" and (m.up_down = mm.route_class or m.up_down = '-1') " +       //早班晚班区分开，同时 排除厂内通勤
		" and m.vehicle_vin = mm.vehicle_vin  " +
		" and substr(n.push_rule ,7,1)='1' " + 
		" and  (substr(m.ahead_often ,?,1)='1' or m.ahead_often = '0000000') " +  //加入星期几重复判断，如果是1：提醒，其他的均不提醒
		" and m.note_calc_type <> 2 " +     
		" and m.valid_flag = '1' " +
		" and  mm.route_organization_id in ( " +
		" select enterprise_id from CLW_JC_ENTERPRISE_T, zspt_later_param ad " +
		" where left_num >= (select left_num from CLW_JC_ENTERPRISE_T  " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area ) " +
		" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T  " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area ))" +
		" union all " +
		" select mm.route_id,mm.route_name,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin, " +
		" m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,m.belong_area,m.note_calc_type remind_type,mm.route_class status_range,0 remind_range " +
		" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n , " +
		" (select t.route_id,t2.route_name,t.site_id,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_class,t2.route_organization_id " +
		" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2 " +
		" where t.route_id = t1.route_id " +
		" and t.route_id = t2.route_id " +
		//" and t.route_id in (523,760,700) " +    //推广线路，全面推广时，要把这些取消
		" and t1.valid_flag = '0' " +
		" and t2.valid_flag = '0' " +
		" and t1.exe_date = trunc(sysdate, 'DD')) mm  " +
		" where m.site_id = mm.site_id " +
		" and m.emp_code = n.emp_code " +
		" and (m.up_down = mm.route_class or m.up_down = '-1') " +        //早班晚班区分开，同时 排除厂内通勤
		" and m.vehicle_vin is null " +
		" and substr(n.push_rule ,7,1)='1' " + 
		" and  (substr(m.ahead_often ,?,1)='1' or m.ahead_often = '0000000') " +   //加入星期几重复判断，如果是1：提醒，其他的均不提醒
		" and m.note_calc_type <> 2 " +   
		" and m.valid_flag = '1' " +
		" and (to_date(m.no_remind_date,'yyyy-mm-dd') <> trunc(sysdate, 'DD') or m.no_remind_date is null ) " +
		" and  mm.route_organization_id in (select enterprise_id from CLW_JC_ENTERPRISE_T, zspt_later_param ad " +
		" where left_num >= (select left_num from CLW_JC_ENTERPRISE_T " +
		" where enterprise_id = ad.organization_id " +
		" and ad.organization_type = m.belong_area ) " +
		" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T " +
		" where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area )) ) aa " +
		
		" where  aa.vehicle_vin = ?  " +
		" and aa.trip_id = ?  " +
		" and not exists (  select 1 from clw_tqc_ygb.clw_tqc_emp_notelog_t t  " +
		" where aa.emp_code = t.emp_id   " +
		" and  aa.note_id =  t.note_id   " +
		" and  aa.trip_id = t.trip_id  " +
		" and  t.vehicle_vin = ? " +
		" and t.send_time >= trunc(sysdate, 'DD'))" ;

    	
        return jdbcTemplate.query(sql, new Object[] {weekDay,weekDay,vin,trip,vin}, ParameterizedBeanPropertyRowMapper.newInstance(StuSiteNote.class));
    }
   
   
   /**
    * 厂内获取下一站
    */
   public List<Site> getNextSite(String routeId,String siteId){
   	String sql = " select rs1.site_id site_id " +
   			" from clw_xc_routesite_t rs1, " +
   			" (select rs.route_id, rs.site_id, rs.rs_order " +
   			" from clw_xc_routesite_t rs " +
   			" where rs.route_id = ? " +
   			" and rs.site_id = ? ) rs, " +
   			" (select max(rs.rs_order) max_order " +
   			" from clw_xc_routesite_t rs " +
   			" where rs.route_id = ?) max " +
   			" where rs.rs_order != max.max_order " +
   			" and rs1.route_id = rs.route_id " +
   			" and rs1.rs_order = rs.rs_order + 1 " +
   			" union all " +
   			" select rs.site_id site_id " +
   			" from (select rs.route_id, rs.site_id, rs.rs_order " +
   			" from clw_xc_routesite_t rs " +
   			" where rs.route_id = ? " +
   			" and rs.site_id = ? ) rs, " +
   			" (select min(rs.rs_order) min_order " +
   			" from clw_xc_routesite_t rs " +
   			" where rs.route_id = ?) min, " +
   			" (select max(rs.rs_order) max_order " +
   			" from clw_xc_routesite_t rs " +
   			" where rs.route_id = ?) max " +
   			" where rs.rs_order != max.max_order " +
   			" and rs.rs_order = min.min_order ";
   	
       return jdbcTemplate.query(sql, new Object[] { routeId,siteId,routeId,routeId,siteId,routeId,routeId  }, ParameterizedBeanPropertyRowMapper.newInstance(Site.class));
   }
   
   
   
    /**
     * 厂内到站提醒 sql
     */
    public List<StuSiteNote> getEmpSiteNote(String tripeId,String siteId){
    	String sql = " select mm.route_id,mm.route_name,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin,m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,mm.site_remark stu_alias,m.belong_area,m.note_calc_type remind_type " +
    			" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n , " +
    			" (select t.route_id,t2.route_name,t.site_id,t3.site_remark,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_organization_id " +
    			" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2,clw_xc_site_t t3  " +
    			" where t.route_id = t1.route_id " +
    			" and t.route_id = t2.route_id " +
    			" and t.site_id = t3.site_id " +
    			" and t1.trip_id = ? " +
    			" and t.site_id = ? " +
    			" and t1.valid_flag = '0' " +
    			" and t2.valid_flag = '0' " +
    			" and t2.route_class = 2 " +
    			" and t1.exe_date = trunc(sysdate, 'DD')) mm " +
    			" where m.site_id = mm.site_id " +
    			" and m.emp_code = n.emp_code " +
    			//" and (m.vehicle_vin = mm.vehicle_vin or m.vehicle_vin is null) " +   //厂内到站提醒，设置的本来就没有车辆VIN
    			
    			" and substr(n.push_rule ,5,1)='1' " + 
    			" and m.note_calc_type = 2 " +
    			" and m.valid_flag = '1' " +
    			//" and (to_date(m.no_remind_date,'yyyy-mm-dd') <> trunc(sysdate, 'DD') or m.no_remind_date is null ) " +   //加入是否继续提醒的判断
    			" and mm.route_organization_id in " +
    			" (select enterprise_id from CLW_JC_ENTERPRISE_T, " +
    			" zspt_later_param ad where left_num >= (select left_num from CLW_JC_ENTERPRISE_T where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area) " +
    			" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area))";
    	
        return jdbcTemplate.query(sql, new Object[] { tripeId,siteId }, ParameterizedBeanPropertyRowMapper.newInstance(StuSiteNote.class));
    }
    
    /**
     * 测试  厂内到站提醒 sql
     */
    public List<StuSiteNote> getTestSiteNote(){
    	String sql = " select mm.route_id,mm.route_name,mm.trip_id,m.site_id,m.emp_code,m.longitude,m.latitude,mm.vehicle_vin,m.AHEAD_NOTE_TIME,m.AHEAD_NOTE_MILEAGE,m.emp_code par_id,m.note_id,m.site_name,mm.site_remark stu_alias,m.belong_area,m.note_calc_type remind_type " +
    			" from clw_tqc_ygb.clw_tqc_emp_site_note_t m , clw_tqc_ygb.CLW_TQC_EMP_T n , " +
    			" (select t.route_id,t2.route_name,t.site_id,t3.site_remark,t.rs_order,t1.trip_id,t1.vehicle_vin,t1.start_time,t2.route_organization_id " +
    			" from CLW_XC_ROUTESITE_T t, tqc_trip_execute t1, clw_xc_route_t t2,clw_xc_site_t t3  " +
    			" where t.route_id = t1.route_id " +
    			" and t.route_id = t2.route_id " +
    			" and t.site_id = t3.site_id " +
    			//" and t1.trip_id = ? " +
    			//" and t.site_id = ? " +
    			" and t1.valid_flag = '0' " +
    			" and t2.valid_flag = '0' " +
    			" and t2.route_class = 2 " +
    			" and t2.route_id in(4371,151,4409,635,642) " +
    			" and t1.exe_date = trunc(sysdate, 'DD')) mm " +
    			" where m.site_id = mm.site_id " +
    			" and m.emp_code = n.emp_code " +
    			//" and (m.vehicle_vin = mm.vehicle_vin or m.vehicle_vin is null) " +   //厂内到站提醒，设置的本来就没有车辆VIN
    			
    			" and substr(n.push_rule ,5,1)='1' " + 
    			" and m.note_calc_type = 2 " +
    			" and m.valid_flag = '1' " +
    			//" and (to_date(m.no_remind_date,'yyyy-mm-dd') <> trunc(sysdate, 'DD') or m.no_remind_date is null ) " +   //加入是否继续提醒的判断
    			" and mm.route_organization_id in " +
    			" (select enterprise_id from CLW_JC_ENTERPRISE_T, " +
    			" zspt_later_param ad where left_num >= (select left_num from CLW_JC_ENTERPRISE_T where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area) " +
    			" and right_num <= (select right_num from CLW_JC_ENTERPRISE_T where enterprise_id = ad.organization_id and ad.organization_type = m.belong_area))";
    	
        return jdbcTemplate.query(sql, new Object[] {  }, ParameterizedBeanPropertyRowMapper.newInstance(StuSiteNote.class));
    }
    public int getIsOrNotPushCount(String note_id)
    {
        String sql = "select count(1) from CLW_TQC_YGB.CLW_TQC_EMP_NOTELOG_T  t where t.note_id = ?  ";
        int i = jdbcTemplate.queryForInt(sql,new Object[] { note_id });
        return i;
    }
    public int getPushCheckRecordID()
    {
        String sql = "select CLW_TQC_YGB.SEQ_TQC_YGB_PUSH.NEXTVAL from dual";
        int i = jdbcTemplate.queryForInt(sql);
        return i;
    }
    
 


    public List<Rules> getUserPushRule(String userId,String empCode)
    {
        //String sql = "select T.RULE_ID,T.USER_ID,T.STU_ID,T.PM_RULE_ID,T.ON_OFF,T.FLAG,T.VALID_FLAG from CLW_JZ_PUSHRULE_T  T where T.On_Off='1' and T.USER_ID=? and t.stu_id=?";
		String sql = "select T.RULE_ID,T.USER_ID,T.STU_ID,T.PM_RULE_ID,T.ON_OFF,T.FLAG,T.VALID_FLAG from clw_tqc_ygb.CLW_TQC_PUSHRULE_T T where T.On_Off = '1'  and t.emp_code = ?";
        return jdbcTemplate.query(sql, new Object[] { empCode }, ParameterizedBeanPropertyRowMapper.newInstance(Rules.class));
    }


    public List getSiteNoteLog(String accountId, String noteId,String tripId, Date date)
    {

        //String sql = "select * from clw_jz_site_notelog_t t where t.account_id=? and t.note_id=? and t.send_time=?";
    	String sql = "select * from clw_tqc_ygb.clw_tqc_emp_notelog_t t where t.emp_id=? and t.note_id=? and t.trip_id=? and t.send_time >= trunc(sysdate, 'DD')";
        return jdbcTemplate.query(sql, new Object[] { accountId, noteId,tripId }, ParameterizedBeanPropertyRowMapper.newInstance(Rules.class));
    }


    public Vehicle getVehicleByStuId(int stuId)
    {
        String sql = "select b.Vehicle_Vin vin, B.VEHICLE_LN ln,B.ENTERPRISE_ID enterpriseid,B.ORGANIZATION_ID organizationid from (select Vehicle_Vin from "
                + "(select Vehicle_Vin,modify_time from CLW_XC_VSS_T where STUDENT_ID = ? order by modify_time desc) where rownum = 1) v inner join CLW_CL_BASE_INFO_T b on v.vehicle_vin = b.vehicle_vin where b.valid_flag = '0' ";
        return (Vehicle) this.jdbcTemplate.queryForObject(sql, new Object[] { stuId }, ParameterizedBeanPropertyRowMapper.newInstance(Vehicle.class));
    }

    @SuppressWarnings("unchecked")
    public List<Site> getSitesByStuId(int stuId)
    {
        try
        {
            String sql = "select s.site_id,S.SITE_LATITUDE,S.SITE_LONGITUDE,S.SITE_REMARK,S.SITE_NAME,S.CONTROL_STATION, rs.rs_order from clw_xc_vss_t v inner join clw_xc_site_t s on v.site_id = s.site_id inner join clw_xc_routesite_T rs on rs.site_id = s.site_Id and rs.route_id = v.route_id where v.student_id = ? and s.valid_flag = 0 order by CONTROL_STATION, rs.rs_order";
            return (List<Site>) jdbcTemplate.query(sql, new Object[] { stuId }, ParameterizedBeanPropertyRowMapper.newInstance(Site.class));
        }
        catch (Exception e)
        {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public List<Site> getSitesByStuId(int stuId, int controlStation)
    {
        try
        {
            String sql = "select s.site_id,S.SITE_LATITUDE,S.SITE_LONGITUDE,S.SITE_REMARK,S.SITE_NAME,S.CONTROL_STATION, rs.rs_order from clw_xc_vss_t v inner join clw_xc_site_t s on v.site_id = s.site_id inner join clw_xc_routesite_T rs on rs.site_id = s.site_Id and rs.route_id = v.route_id where v.student_id = ? and s.CONTROL_STATION = ? and s.valid_flag = 0 order by CONTROL_STATION, rs.rs_order";
            return (List<Site>) jdbcTemplate.query(sql, new Object[] { stuId, controlStation }, ParameterizedBeanPropertyRowMapper.newInstance(Site.class));
        }
        catch (Exception e)
        {
            return null;
        }
    }


    public int updateAllStuState()
    {
        String sql = "update clw_jz_student_t set stu_state='0' ";
        return this.jdbcTemplate.update(sql);
    } 

    public int updateStuStateById(String stuId,String state)
    {
        String sql = "update clw_jz_student_t set stu_state='"+state+"' where stu_id=?";
        return this.jdbcTemplate.update(sql,new Object[] { stuId });
    }
    
    
    
    //刘俊安
//    public int deletePreLineByRouteAndSite(String route_id, String site_id) throws DataAccessException {
//        int num = jdbcTemplate.update(" DELETE CLW_TQC_VEHICLE_NOTE_BASE_T where  route_id = ' " +  route_id 
//                + "' and site_id='" + site_id + "'");
//        return num;
//    }
    
  public int deletePreLineByRouteAndSite() throws DataAccessException {
  int num = jdbcTemplate.update(" DELETE CLW_TQC_VEHICLE_NOTE_BASE_T ");
  return num;
}
    
    public int insertPreLineByRouteAndSite(String route_id, String site_id,float mile){
		String sql = "insert into CLW_TQC_VEHICLE_NOTE_BASE_T   " +
				" select SEQ_POINT_ID.NEXTVAL point_id,t.route_id,t.longitude,t.latitude,aa.mileage - t.mileage mileage,'' standard_time,aa.is_site site_id " +
				" from CLW_TQC_EMP_PRELINE_T t, " +
				//获得 相应线路 相应站点的 标准距离和标准时间
				" (select t.note_id,t.route_id, t.is_site,t.longitude,t.latitude,t.mileage,t.standard_time " +
				" from CLW_TQC_EMP_PRELINE_T t " +
				" where t.route_id = ?  " +
				" and t.is_site = ?  and rownum = 1 ) aa " +
				" where t.route_id = aa.route_id " +
				" and t.mileage < aa.mileage + 0.0002  - ?  " +
				" and t.mileage > aa.mileage - 0.0002 - ?  " +
				" and rownum = 1 ";
		
		//log.info("按距离插入预运行线路轨迹表"+ sql + "---" + route_id + "---" + site_id + "---" + mile);
		return jdbcTemplate.update(sql,  new Object[]{route_id,site_id,mile,mile} );
	}
    
//    public int insertPreLineByTime(String route_id, String site_id,int time){
//		String sql = " insert into CLW_TQC_VEHICLE_NOTE_BASE_T  " +
//				" select SEQ_POINT_ID.NEXTVAL point_id,t.route_id,t.longitude,t.latitude,'' mileage,aa.standard_time-t.standard_time standard_time,aa.is_site site_id " +
//				" from CLW_TQC_EMP_PRELINE_T t, " +
//				//获得 相应线路 相应站点的 标准距离和标准时间
//				" (select t.note_id,t.route_id,t.is_site,t.longitude,t.latitude,t.mileage,t.standard_time " +
//				" from CLW_TQC_EMP_PRELINE_T t " +
//				" where t.route_id = ? " +
//				" and t.is_site = ？ and rownum = 1) aa " +
//				" where t.route_id = aa.route_id " +
//				" and t.standard_time <= aa.standard_time " +
//				" and t.standard_time >= aa.standard_time - ? " +
//				" order by t.standard_time asc ";
//		
//		log.info("按时间插入预运行线路轨迹表"+ sql + "---" + route_id + "---" + site_id + "---" + time);
//		return jdbcTemplate.update(sql,  new Object[]{route_id,site_id,time} );
//    }
    
    public int insertPreLineByTime(NoticeBean bean){
		String sql = " insert into CLW_TQC_VEHICLE_NOTE_BASE_T (POINT_ID,ROUTE_ID,LONGITUDE,LATITUDE,MILEAGE,STANDARD_TIME,SITE_ID) " +
				" values(SEQ_POINT_ID.NEXTVAL, ?,?,?,'',?,?) ";
		
		//log.info("按时间插入预运行线路轨迹表"+ sql + "---" + bean.getRoute_id() + "---" + bean.getLongitude() + "---" + bean.getStandard_time());
		return jdbcTemplate.update(sql,  new Object[]{bean.getRoute_id(),bean.getLongitude(),bean.getLatitude(),bean.getStandard_time(),bean.getSite_id()} );
    }
	
    public List<NoticeBean> getGroupLineByTime(String route_id, String site_id,int time) {
		
		String sql = " select t.route_id,t.longitude,t.latitude,aa.standard_time-t.standard_time standard_time,aa.is_site site_id " +
				" from CLW_TQC_EMP_PRELINE_T t, " +
				//获得 相应线路 相应站点的 标准距离和标准时间
				" (select t.note_id,t.route_id,t.is_site,t.longitude,t.latitude,t.mileage,t.standard_time " +
				" from CLW_TQC_EMP_PRELINE_T t " +
				" where t.route_id = '" + route_id+"' " +
				" and t.is_site = '" + site_id + "' and rownum = 1) aa " +
				" where t.route_id = aa.route_id " +
				" and t.standard_time <= aa.standard_time " +
				" and t.standard_time >= aa.standard_time - " + time + 
				" order by t.standard_time asc ";
		
		log.info("按时间插入预运行线路轨迹表"+ sql  + "---" + route_id + "---" + site_id + "---" + time);
		
        return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(NoticeBean.class));
	}

	public List<NoticeBean> getGroupRouteSite() {
		
		String sql = " select t.route_id,t.is_site site_id from CLW_TQC_EMP_PRELINE_T t where  t.is_site is not null group by t.route_id,t.is_site ";
		
        return jdbcTemplate.query(sql, new Object[] {}, ParameterizedBeanPropertyRowMapper.newInstance(NoticeBean.class));
	}
    
}
