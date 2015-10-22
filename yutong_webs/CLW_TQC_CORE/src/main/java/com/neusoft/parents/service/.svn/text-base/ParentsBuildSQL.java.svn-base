package com.neusoft.parents.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.spring.SpringBootStrap;
import com.neusoft.clw.vncs.inside.msg.content.Up_InfoContent;
import com.neusoft.parents.algorithm.domain.Station;
import com.neusoft.parents.algorithm.domain.VehicleStation;
import com.neusoft.parents.bean.BufBean;
import com.neusoft.parents.bean.News;
import com.neusoft.parents.bean.StuSiteNote;
import com.neusoft.parents.bean.VehicleReal;
import com.neusoft.parents.dao.IParentsDAO;
import com.neusoft.parents.pushmessage.domain.PushMessageCls;
import com.neusoft.parents.utils.StringUtils;

public class ParentsBuildSQL
{

    private static Logger log = LoggerFactory.getLogger(ParentsBuildSQL.class);

    @SuppressWarnings("unused")
    private IParentsDAO parentsDAO = null;

    public static final String num3 = "3";

    public static final ParentsBuildSQL buildSql = new ParentsBuildSQL();

    private ParentsBuildSQL()
    {
        parentsDAO = (IParentsDAO) SpringBootStrap.getInstance().getBean("parentsDAO");
    }

    public static ParentsBuildSQL getInstance()
    {
        return buildSql;
    }

    /**
     * 厂外推送   记录  厂外推送   保存记录到clw_tqc_emp_notelog_t表中
     * @param vr
     * @param sn
     * @return
     */
    public String buildInsertSiteNoteLog(VehicleReal vr,StuSiteNote sn)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = sdf.format(vr.getTerminal_time());
        StringBuffer sql = new StringBuffer();
        sql.append("insert into clw_tqc_ygb.clw_tqc_emp_notelog_t t(log_id, note_id, emp_id,trip_id, send_time,vehicle_vin, is_successful) values(");
        sql.append("clw_tqc_ygb.SEQ_SITE_NOTELOG.NEXTVAL,");
        sql.append("'" + sn.getNote_id() + "',");
        sql.append("'" + sn.getPar_id() + "',");
        sql.append("'" + sn.getTrip_id() + "',");
        sql.append("to_date('" + date1Str + "','yyyy-mm-dd hh24:mi:ss'),");
        sql.append("'" + sn.getVehicle_vin() + "',");
        //sql.append("to_date('" + date1Str + "','yyyy-mm-dd'),");
        sql.append("'1')");
        log.debug("<ParentsBuildSQL>插入站点提醒日志信息表:" + sql.toString());
        return sql.toString();
    }
    /**
     * 厂内推送    记录    厂内推送    保存记录到clw_tqc_emp_notelog_t表中
     * @param date
     * @param sn
     * @return
     */
    public String buildInsertSiteNoteLog(Date date,StuSiteNote sn)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = sdf.format(date);
        //log.info("22222222222222222222222222222222222222222222222222222222222222222222222222222222222"+date1Str);
        StringBuffer sql = new StringBuffer();
        sql.append("insert into clw_tqc_ygb.clw_tqc_emp_notelog_t t(log_id, note_id, emp_id,trip_id,site_id, send_time, is_successful) values(");
        sql.append("clw_tqc_ygb.SEQ_SITE_NOTELOG.NEXTVAL,");
        sql.append("'" + sn.getNote_id() + "',");
        sql.append("'" + sn.getPar_id() + "',");
        sql.append("'" + sn.getTrip_id() + "',");
        sql.append("'" + sn.getSite_id() + "',");
        sql.append("to_date('" + date1Str + "','yyyy-mm-dd hh24:mi:ss'),");
        //sql.append("to_date('" + date1Str + "','yyyy-mm-dd'),");
        sql.append("'1')");
        log.debug("<ParentsBuildSQL>插入站点提醒日志信息表:" + sql.toString());
        return sql.toString();
    }
	/**
	 * 新闻推送   更新公告表clw_tqc_gonggao_t ，将字段send_flag设置为1    1：表示发送，0：未发送
	 * @param eachnewsList
	 * @return
	 */
    public String buildUpdateNewsLog(News eachnewsList)
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" update  clw_tqc_gonggao_t t set t.send_flag = '1' ");
        sql.append(" where t.gonggao_id = '" + eachnewsList.getNews_id() + "' ");
        log.debug("<ParentsBuildSQL>更新新闻推送表:" + sql.toString());
        return sql.toString();
    }
    

    public String updateEmpNote()
    {
        StringBuffer sql = new StringBuffer();
        sql.append(" update  clw_tqc_ygb.CLW_TQC_EMP_SITE_NOTE_T t set t.valid_flag = '0' ");
        sql.append(" where t.ahead_often = '0000000' and valid_flag = '1' ");
        log.debug("<ParentsBuildSQL>清晨更新员工提醒表:" + sql.toString());
        return sql.toString();
    }


    public String buildInsertSiteSql(Station s, VehicleStation vs, int xcSiteId)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into CLW_XC_SITE_T (site_id, site_name, sichen_addr, site_longitude, site_latitude, ");
        sql.append("site_remark, enterprise_id, organization_id, creater, create_time, modifier, modify_time, valid_flag,control_station) values(");
        sql.append("'" + xcSiteId + "'");
        sql.append(",'站点'");
        sql.append(",''");
        sql.append(",'" + s.getLongitude() + "'");
        sql.append(",'" + s.getLatitude() + "'");
        sql.append(",'站点'");
        sql.append(",'" + vs.getEnterpriseId() + "'");
        sql.append(",'" + vs.getOrganizationid() + "'");
        sql.append(",'00000000-0000-0000-0000-000000000000'");
        sql.append(",sysdate");
        sql.append(",''");
        sql.append(",sysdate");
        sql.append(",'0'");
        sql.append(",'" + s.getDirection() + "'");
        sql.append(")");
        log.debug("<ParentsBuildSQL>插入站点信息表:" + sql.toString());
        return sql.toString();
    }

    public String buildInsertRouteSql(Date dateup1, Date dateup2, Date datedown1, Date datedown2, VehicleStation vs, int xcRouteId)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	String dateUp1Str = sdf.format(dateup1);
    	String dateUp2Str = sdf.format(dateup2);
    	String dateDown1Str = sdf.format(datedown1);
    	String dateDown2Str = sdf.format(datedown2);
    	
    	//nick:change date1 to string, if date is null insert null
        StringBuffer sql = new StringBuffer();
        sql.append("insert into CLW_XC_ROUTE_T (route_id, route_name, route_remark, route_incharge_person, route_phone,");
        sql.append("route_telphone, route_email, route_enterprise_id, route_organization_id, creater, create_time, modifier, ");
        sql.append(" modify_time, valid_flag, upstarttime, upendtime, downstarttime, downendtime) values(");
        sql.append("'" + xcRouteId + "'");
        sql.append(",'线路" + xcRouteId + "'");
        sql.append(",'线路" + xcRouteId + "'");
        sql.append(",'system'");
        sql.append(",''");
        sql.append(",''");
        sql.append(",''");
        sql.append(",'" + vs.getEnterpriseId() + "'");
        sql.append(",'" + vs.getOrganizationid() + "'");
        sql.append(",'00000000-0000-0000-0000-000000000000'");
        sql.append(",sysdate");
        sql.append(",''");
        sql.append(",sysdate");
        sql.append(",'0'");
        sql.append(",to_date('" + dateUp1Str + "','yyyy-mm-dd hh24:mi:ss')");
        sql.append(",to_date('" + dateUp2Str + "','yyyy-mm-dd hh24:mi:ss')");
        sql.append(",to_date('" + dateDown1Str + "','yyyy-mm-dd hh24:mi:ss')");
        sql.append(",to_date('" + dateDown2Str + "','yyyy-mm-dd hh24:mi:ss')");
        sql.append(")");
        log.debug("<ParentsBuildSQL>插入线路表:" + sql.toString());
        return sql.toString();
    }

    public String buildInsertRouteSiteSql(Station s, int xcSiteId, int xcRouteId, int order)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into CLW_XC_ROUTESITE_T (route_id, site_id, rs_order, site_updown, modify_time) values(");
        sql.append("'" + xcRouteId + "'");
        sql.append(",'" + xcSiteId + "'");
        sql.append("," + order);
        sql.append(",'" + s.getDirection() + "'");
        sql.append(",sysdate");
        sql.append(")");
        log.debug("<ParentsBuildSQL>插入站点线路信息表:" + sql.toString());
        return sql.toString();
    }

    public String buildInsertTripSql(VehicleStation vs, int xcTripId, int xcRouteId, int type)
    {
        StringBuffer sql = new StringBuffer();
        sql.append("insert into CLW_XC_TRIP_T (trip_id, route_id, type, crc, vehicle_vin, update_time, operate_time, valid_flag) values(");
        sql.append("'" + xcTripId + "'");
        sql.append(",'" + xcRouteId + "'");
        sql.append("," + type);
        sql.append(",''");
        sql.append(",'" + vs.getVin() + "'");
        sql.append(",sysdate");
        sql.append(",sysdate");
        sql.append(",'0'");
        sql.append(")");
        log.debug("<ParentsBuildSQL>插入行程信息表:" + sql.toString());
        return sql.toString();
    }
    public String buildInsertVssSiteSql(String vin,int xcSiteId, int xcRouteId,int xcTripId, Date planOutTime, Date planInTime)
    {
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    	String planOutTimeStr = sdf.format(planOutTime);
    	String planInTimeStr = sdf.format(planInTime);
    	
        StringBuffer sql = new StringBuffer();
        sql.append("insert into clw_xc_vss_site_t (vehicle_vin, route_id, site_id, plan_out_time, plan_in_time, trip_id) values(");
        sql.append("'" + vin+ "'");
        sql.append(",'" + xcRouteId + "'");
        sql.append(",'" + xcSiteId + "'");
        sql.append(",'" + planOutTimeStr + "'");
        sql.append(",'" + planInTimeStr + "'");
        sql.append(",'" + xcTripId + "'");
        sql.append(")");
        log.debug("<ParentsBuildSQL>插入行程信息表:" + sql.toString());
        return sql.toString();
    }

}
