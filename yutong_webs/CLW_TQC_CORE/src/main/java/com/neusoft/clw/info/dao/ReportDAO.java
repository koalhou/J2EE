package com.neusoft.clw.info.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.ParameterizedBeanPropertyRowMapper;

import com.neusoft.clw.info.bean.BaddrivingDate;
import com.neusoft.clw.info.bean.ClStatisticsBean;
import com.neusoft.clw.info.bean.ClwSecData;
import com.neusoft.clw.info.bean.DayReport;
import com.neusoft.clw.info.bean.HisCalRecord;
import com.neusoft.clw.info.bean.HisOverSpeed;
import com.neusoft.clw.info.bean.Minute5Data;
import com.neusoft.clw.info.bean.OnOffBean;
import com.neusoft.clw.info.bean.RapidBean;
import com.neusoft.clw.info.bean.RealTimeRecord;
import com.neusoft.clw.info.bean.RotateSpeed;
import com.neusoft.clw.info.bean.VehicHistoryTime;
import com.neusoft.clw.info.bean.VehicleSpeed;
import com.neusoft.clw.vncs.inside.msg.utils.DateUtil;

public class ReportDAO extends AbstractDaoManager {
	private Logger log = LoggerFactory.getLogger(ReportDAO.class);
	 // 校车异常乘车统计     
//	73	未在规定站点上车
//	74	未在规定站点下车
//	79	未刷卡上车
//	80	未刷卡下车
    @SuppressWarnings("unchecked")
	public void xcYcStatistics(String strVin, String strDate) throws DataAccessException {
    	
    	String s73="0",s74="0",s79="0",s80="0";
        String Sinsert="INSERT INTO CLW_XC_CL_STATISTICS_T (VEHICLE_VIN,NO_UP_NUM,NO_DOWN_NUM,NOFIX_UP_NUM,NOFIX_DOWN_NUM,BUSINESS_DAY)     "
      	  + "values( ?,?,?,?,?,to_date(?,'yyyy-mm-dd'))";

    	//先删除
	   @SuppressWarnings("unused")
	int num = jdbcTemplate.update("DELETE CLW_XC_CL_STATISTICS_T partition(CL_STATISTICS_"+SdateM(strDate)+")"
                + " where vehicle_vin='" + strVin + "' and to_char(BUSINESS_DAY,'YYYY-MM-DD')='"+strDate+"'");
    	String  YcStatistics = "select alarm_type_id,  count(alarm_type_id) count from clw_xc_st_check_record_t partition(ST_CHECK_RECORD_"
                + SdateM(strDate)+ ")" +" where alarm_type_id is not null and vehicle_vin=? group by alarm_type_id";
    	  List<ClStatisticsBean> rs = jdbcTemplate.query(YcStatistics, new Object[] { strVin }, ParameterizedBeanPropertyRowMapper.newInstance(ClStatisticsBean.class));	
    	  ClStatisticsBean bd;
          for(int i=0;i<rs.size();i++){
          	bd= rs.get(i);
          	if(bd.getALARM_TYPE_ID().equals("73")){
          		s73=bd.getCOUNT();
          		continue;
          	}
          	if(bd.getALARM_TYPE_ID().equals("74")){
          		s74=bd.getCOUNT();
          		continue;
          	}
          	if(bd.getALARM_TYPE_ID().equals("79")){
          		s79=bd.getCOUNT();
          		continue;
          	}
          	if(bd.getALARM_TYPE_ID().equals("80")){
          		s80=bd.getCOUNT();
          	}
            
          }      
          jdbcTemplate.update(Sinsert, new Object[] { strVin,s79,s80,s73,s74, strDate });
    }
	 // 校车上座率统计     
    @SuppressWarnings("unchecked")
	public void xcSzlStatistics(String strVin, String strDate) throws DataAccessException {
    	String ROUTE_ID="0"	;	// 	
    	String PLAN_UP_NUM="0"	;	// 			上行应上车人数总和
    	String PLAN_DOWN_NUM="0"	;	//			下行应上车人数总和
    	String REALITY_UP_NUM="0"	;	//			上行实际上车人数总和
    	String REALITY_DOWN_NUM="0";	//			下行实际上车人数总和
    	 String Sinsert="INSERT INTO CLW_XC_ROUTE_STATISTICS_T (VEHICLE_VIN,ROUTE_ID,PLAN_UP_NUM,PLAN_DOWN_NUM,REALITY_UP_NUM,REALITY_DOWN_NUM,BUSINESS_DAY) "
       	  + "values( ?,?,?,?,?,?,to_date(?,'yyyy-mm-dd'))";
        
    	//先删除
	   @SuppressWarnings("unused")
	int num = jdbcTemplate.update("DELETE CLW_XC_ROUTE_STATISTICS_T partition(ROUTE_STATISTICS_"+SdateM(strDate)+")"
                + " where vehicle_vin='" + strVin + "' and to_char(BUSINESS_DAY,'YYYY-MM-DD')='"+strDate+"'");
    	String  YcStatistics = " select route_id,   sum(case when site_updown='0' then nvl(reality_up_num,0) end) REALITY_UP_NUM," +
    			"sum(case when site_updown='0' then nvl(plan_up_num,0) end) PLAN_UP_NUM," +
    			"sum(case when site_updown='1' then nvl(reality_up_num,0) end) REALITY_DOWN_NUM," +
    			"sum(case when site_updown='1' then nvl(plan_up_num,0) end) PLAN_DOWN_NUM"+
    			" from CLW_XC_INOUTSITE_T  partition(INOUTSITE_"+Sdate(strDate)+")"+" where   vehicle_vin=? group by  (ROUTE_ID)";    //and to_char(TERMINAL_TIME,'YYYY-MM-DD')=? 
    	  List<ClStatisticsBean> rs = jdbcTemplate.query(YcStatistics, new Object[] { strVin }, ParameterizedBeanPropertyRowMapper.newInstance(ClStatisticsBean.class));	
    	  ClStatisticsBean bd;
          for(int i=0;i<rs.size();i++){
          	bd= rs.get(i);
          	ROUTE_ID=bd.getROUTE_ID();
      	    PLAN_UP_NUM=bd.getPLAN_UP_NUM();
      	    REALITY_UP_NUM=bd.getREALITY_UP_NUM();         	
      		PLAN_DOWN_NUM=bd.getPLAN_DOWN_NUM();
      		REALITY_DOWN_NUM=bd.getREALITY_DOWN_NUM();
      		if(ROUTE_ID.equals("")||ROUTE_ID==null){
      			log.info("报表校车上座率统计：ReportDAO:strVin:"+strVin+"线路ROUTE_ID为空不入库!");
      			continue;
      		}
      		if(PLAN_UP_NUM==null||PLAN_UP_NUM.equals("")){
      			PLAN_UP_NUM="0";
      		}
      		if(REALITY_UP_NUM==null||REALITY_UP_NUM.equals("")){
      			REALITY_UP_NUM="0";
      		}
      		if(PLAN_DOWN_NUM==null||PLAN_DOWN_NUM.equals("")){
      			PLAN_DOWN_NUM="0";
      		}
      		if(REALITY_DOWN_NUM==null||REALITY_DOWN_NUM.equals("")){
      			REALITY_DOWN_NUM="0";
      		}
      		
      		log.info("报表：ReportDAO:strVin:"+strVin+"ROUTE_ID:"+"PLAN_UP_NUM:"+PLAN_UP_NUM+"PLAN_DOWN_NUM:"+PLAN_DOWN_NUM+"REALITY_UP_NUM:"+REALITY_UP_NUM+"REALITY_DOWN_NUM"+REALITY_DOWN_NUM+"strDate:"+strDate);
      		 jdbcTemplate.update(Sinsert, new Object[] { strVin,ROUTE_ID,PLAN_UP_NUM,PLAN_DOWN_NUM,REALITY_UP_NUM,REALITY_DOWN_NUM, strDate });
          }
          
    }

    // 初始化日报表，CLW_YW_BADDRIVING_T删除并插入一条当天记录
    private String SQL_DELETE = "DELETE CLW_YW_BADDRIVING_T  ";

    private static final String SQL_INSERT = "INSERT INTO CLW_YW_BADDRIVING_T (BADDRIVING_ID,ROUTE_ID,VEHICLE_VIN,VEHICLE_ID,ENTERPRISE_ID,ORGANIZATION_ID,ALARM_DAY,BEGIN_TIME)     "
            + "select sys_guid(),ROUTE_ID,VEHICLE_VIN,VEHICLE_ID,ENTERPRISE_ID,ORGANIZATION_ID ,to_date(?,'yyyy-mm-dd'),sysdate from CLW_CL_BASE_INFO_T   "
            + "  where vehicle_vin=? and valid_flag = '0' and DEVICE_TYPE = '0'";

    // 初始化车辆状态报告中间表，CLW_YW_VEZTD_T删除并插入一条当天记录
    private String SQL_DELETE_VEZTD = "DELETE CLW_YW_VEZTD_T  ";

    private static final String SQL_INSERT_VEZTD = "INSERT INTO CLW_YW_VEZTD_T (VEHD_ID,VEHICLE_VIN,ENTERPRISE_ID,ORGANIZATION_ID,LAST_TIME,VALID_FLAG )     "
            + "select sys_guid(),VEHICLE_VIN,nvl(ENTERPRISE_ID,''),nvl(ORGANIZATION_ID,''),to_date(?,'yyyy-mm-dd'),'1' from CLW_CL_BASE_INFO_T   "
            + "  where vehicle_vin=?  and valid_flag = '0' and DEVICE_TYPE = '0'";

    // 更新车辆状态表记录最后时间、总里程、总油耗、及差值
    private static final String SQL_VEZTD_OIL_MILEAGE = "UPDATE CLW_YW_VEZTD_T SET COUNT_MILEAGE=?,MILEAGE=?,COUNT_OIL_TOTAL=?,OIL_TOTAL=? , LAST_TIME=to_date(?,'yyyy-mm-dd hh24:mi:ss') ,OIL_TIME=?,VALID_FLAG='0'"
            + " WHERE VEHICLE_VIN=? AND TO_CHAR(LAST_TIME,'YYYY-MM-DD')=?";

    // 更新车辆状态表记录最后时间、总里程、总油耗、及差值
    public int updateVeztdOilMieage(String COUNT_MILEAGE, String MILEAGE, String COUNT_OIL_TOTAL,
            String OIL, String TERMINAL_TIME, String OIL_INSTANT, String vin, String date)
            throws DataAccessException {
        // int num=jdbcTemplate.update(SQL_OIL_MILEAGE+" where
        // to_char(alarm_day,'yyyy-mm-dd')='"+date+"' and vehicle_vin='"+vin+"'" );
        int num = jdbcTemplate.update(SQL_VEZTD_OIL_MILEAGE, new Object[] { COUNT_MILEAGE, MILEAGE,
                COUNT_OIL_TOTAL, OIL, TERMINAL_TIME, OIL_INSTANT, vin, date });
        return num;
    }

    // 更新车辆状态表由秒数据中分析出的值
    private static final String SQL_SEC_DATA = "UPDATE CLW_YW_BADDRIVING_T SET ECONOMIC_RUN_TIME=?,XINGCHE_TIME=?,DAISHU_TIME=? ,ENGINE_ROTATE_TIME=? "
            + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";

    public int updateSecData(DayReport dr, String strVin, String strDate)
            throws DataAccessException {
        int num = jdbcTemplate.update(SQL_SEC_DATA, new Object[] { dr.getECONOMIC_RUN_TIME(),
                dr.getXINGCHE_TIME(), dr.getDAISHU_TIME(), dr.getENGINE_ROTATE_TIME(), strVin,
                strDate });
        return num;
    }

    // 更新车辆状态表由开关量中分析出的值 

    private static final String SQL_ONOFF_DATA = "UPDATE CLW_YW_BADDRIVING_T SET JIARE_TIME=?,KONGTIAO_TIME=?,SHACHE_NUM=?"
            + " ,QIANMEN_NUM=?,ZHONGMEN_NUM=? ,CLUTCH_NUM=?,Reverse_NUM=?,Brake_NUM=?,Retarder_NUM=?,ABS_NUM=?"
            + ",Brake_shoe_num=?,Brake_shoe_time=?,Air_filter_clog_num=?,Air_filter_clog_time=? " 
            + ", QIANMEN_ERR_NUM=?,ZHONGMEN_ERR_NUM=?,OIL_PRESSURE_NUM=?" +
              ",GAS_PRESSURE_NUM=?,WATER_LEVEL_NUM=?,RETARDER_TP_HIGH_NUM=?" +
              ",STORAGE_TP_HIGH_NUM=?,OIL_FILTER_NUM=?" +
              ",COOL_LIQUID_TP_NUM=?,BATTERY_VOL_LOW_NUM=?,HEAT_SYSTEM_WORK_TIME=? "  ;          
          //  + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";

    public int updateOnOffData(DayReport dr, String strVin, String strDate)
            throws DataAccessException {
    	String tmp="";
    	if (dr.getBRAKE_SHOE_LATEST()!=null){
    		tmp=tmp+" ,BRAKE_SHOE_LATEST=to_date('"+dr.getBRAKE_SHOE_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getOIL_PRESSURE_LATEST()!=null){
    		tmp=tmp+" ,OIL_PRESSURE_LATEST=to_date('"+dr.getOIL_PRESSURE_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getGAS_PRESSURE_LATEST()!=null){
    		tmp=tmp+" ,GAS_PRESSURE_LATEST=to_date('"+dr.getGAS_PRESSURE_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getWATER_LEVEL_LATEST()!=null){
    		tmp=tmp+" ,WATER_LEVEL_LATEST=to_date('"+dr.getWATER_LEVEL_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getRETARDER_TP_HIGH_LATEST()!=null){
    		tmp=tmp+" ,RETARDER_TP_HIGH_LATEST=to_date('"+dr.getRETARDER_TP_HIGH_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getSTORAGE_TP_HIGH_LATEST()!=null){
    		tmp=tmp+" ,STORAGE_TP_HIGH_LATEST=to_date('"+dr.getSTORAGE_TP_HIGH_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getOIL_FILTER_LATEST()!=null){
    		tmp=tmp+" ,OIL_FILTER_LATEST=to_date('"+dr.getOIL_FILTER_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getCOOL_LIQUID_TP_LATEST()!=null){
    		tmp=tmp+" ,COOL_LIQUID_TP_LATEST=to_date('"+dr.getCOOL_LIQUID_TP_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	if (dr.getBATTERY_VOL_LOW_LATEST()!=null){
    		tmp=tmp+" ,BATTERY_VOL_LOW_LATEST=to_date('"+dr.getBATTERY_VOL_LOW_LATEST()+"','yyyy-mm-dd hh24:mi:ss') ";
    	}
    	
        int num = jdbcTemplate.update(SQL_ONOFF_DATA +tmp +" WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?", new Object[] { dr.getJIARE_TIME(),
                dr.getKONGTIAO_TIME(), dr.getSHACHE_NUM(), dr.getQIANMEN_NUM(),
                dr.getZHONGMEN_NUM(), dr.getCLUTCH_NUM(), dr.getREVERSE_NUM(), dr.getBRAKE_NUM(),
                dr.getRETARDER_NUM(), dr.getABS_NUM(), dr.getBRAKE_SHOE_NUM(),
                dr.getBRAKE_SHOE_TIME(), dr.getAIR_FILTER_CLOG_NUM(), dr.getAIR_FILTER_CLOG_TIME(),               
                dr.getQIANMEN_ERR_NUM(),dr.getZHONGMEN_ERR_NUM(),dr.getOIL_PRESSURE_NUM(),                
                dr.getGAS_PRESSURE_NUM(),dr.getWATER_LEVEL_NUM(),dr.getRETARDER_TP_HIGH_NUM(),                
                dr.getSTORAGE_TP_HIGH_NUM(),dr.getOIL_FILTER_NUM(),
                dr.getCOOL_LIQUID_TP_NUM(),dr.getBATTERY_VOL_LOW_NUM(),dr.getHEAT_SYSTEM_WORK_TIME(),
                strVin, strDate });
        return num;

    }

    // 更新统计结束时间
    private static final String SQL_UPDATE_DATA = "UPDATE CLW_YW_BADDRIVING_T SET end_time=sysdate"
            + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";

    public int updateEndData(String strVin, String strDate) throws DataAccessException {
        int num = jdbcTemplate.update(SQL_UPDATE_DATA, new Object[] { strVin, strDate });
        return num;

    }

    // 更新日报表总里程、总油耗、及差值
    private static final String SQL_OIL_MILEAGE = "UPDATE CLW_YW_BADDRIVING_T SET COUNT_MILEAGE=?,MILEAGE=?,COUNT_OIL_TOTAL=?,OIL=? "
            + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";

    // 更新日报表总里程、总油耗、及差值
    public int updateOilMieage(String COUNT_MILEAGE, String MILEAGE, String COUNT_OIL_TOTAL,
            String OIL, String vin, String date) throws DataAccessException {
        int num = jdbcTemplate.update(SQL_OIL_MILEAGE, new Object[] { COUNT_MILEAGE, MILEAGE,
                COUNT_OIL_TOTAL, OIL, vin, date });
        return num;
    }

    // 更新日报表最高车速最高转速
    // 实时数据
    // private static final String SQL_MAXSPEED=" update CLW_YW_BADDRIVING_T set
    // (SPEED_MAX,RPM_MAX)=" +
    // "(select max(nvl(r.SPEEDING,0)),max(nvl(r.ENGINE_ROTATE_SPEED,0)) from
    // clw_yw_terminal_record_t r where r.vehicle_vin=? and to_char(r.terminal_time,'yyyy-mm-dd')=?
    // AND r.SPEEDING<>'FFFF' AND r.ENGINE_ROTATE_SPEED<>'FFFF') " +
    // " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=? ";
    // 秒数据
    // private static final String SQL_MAXSPEED=" update CLW_YW_BADDRIVING_T set
    // (SPEED_MAX,RPM_MAX)=" +
    // "(select max(nvl(r.VEHICLE_SPEED,0)),max(nvl(r.ENGINE_ROTATE_SPEED,0)) from clw_yw_sec_data_t
    // r where r.vehicle_vin=? and to_char(r.TEMINAL_TIME,'yyyy-mm-dd')=? AND
    // r.VEHICLE_SPEED<>'FFFF' AND r.ENGINE_ROTATE_SPEED<>'FFFF') " +
    // " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=? ";
    //
    // public int updateMaxSpeed(String vin,String date) {
    // int num=jdbcTemplate.update(SQL_MAXSPEED,new Object[] {vin, date,vin, date} );
    // return num;
    // }
    private static final String SQL_MAXSPEED = " update CLW_YW_BADDRIVING_T set  SPEED_MAX=?,RPM_MAX=? "
            + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=? ";

    public int updateMaxSpeed(String strVin, String strDate, float sPEEDMAX, float rPMMAX)
            throws DataAccessException {
        int num = jdbcTemplate.update(SQL_MAXSPEED, new Object[] { sPEEDMAX, rPMMAX, strVin,
                strDate });
        return num;
    }

    /**
     * 获取信息列表
     * @param reqBean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<RealTimeRecord> getRecord(String vin, String date) throws DataAccessException {
        // 获取实时信息表当天信息记录
//        String SQL_LIST = "select ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,CREATE_TIME,to_char(UTC_TIME,'yyyy-mm-dd hh24:mi:ss') UTC_TIME,to_char(TERMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TERMINAL_TIME,GPS_VALID,LATITUDE,LONGITUDE,ELEVATION,DIRECTION,GPS_SPEEDING,SPEEDING,ON_OFF,SOS,OVERSPEED_ALERT,FATIGUE_ALERT,GPS_ALERT,SHOW_SPEED_ALERT,DRIVER_ID,DRIVER_LICENSE,ENGINE_ROTATE_SPEED,MILEAGE,OIL_INSTANT,OIL_PRESSURE,TORQUE_PERCENT,FIRE_UP_STATE,POWER_STATE,BATTERY_VOLTAGE,GPS_STATE,EXT_VOLTAGE,IMG_PROCESS,OIL_TOTAL,E_WATER_TEMP,E_TORQUE,QUAD_ID_TYPE,ROUTE_INFO,MEG_RESP_ID,MEG_ID,MEG_TYPE,MEG_INFO,MEG_SEQ,RATIO,GEARS,EEC_APP,VEHICLE_SPEED,PULSE_MILEAGE "
//                + " from CLW_YW_TERMINAL_RECORD_T partition(TERMINAL_RECORD_"
//                + Sdate(date)
//                + ") where vehicle_vin=?      order by terminal_time asc  ";
    	
    	String SQL_LIST = "select TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,CREATE_TIME,to_char(TERMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TERMINAL_TIME,nvl(MILEAGE,'FFFF') MILEAGE,nvl(OIL_TOTAL,'FFFF') OIL_TOTAL,nvl(OIL_INSTANT,'FFFF') OIL_INSTANT" +
    			" from CLW_YW_TERMINAL_RECORD_T partition(TERMINAL_RECORD_"+ Sdate(date)
                + ") where vehicle_vin=?      order by terminal_time asc  ";
    	
        List<RealTimeRecord> realTimeRecord = jdbcTemplate.query(SQL_LIST, new Object[] { vin },
                ParameterizedBeanPropertyRowMapper.newInstance(RealTimeRecord.class));

        return realTimeRecord;
    }

    /**
     * 获取车联网秒数据列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ClwSecData> getClwSecRecord(String vin, String date) throws DataAccessException {
        // 获取车联网秒数据
        String SQL_CLWSECDATA = "select ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,to_char(WRITE_TIME,'yyyy-mm-dd hh24:mi:ss') WRITE_TIME,to_char(TEMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TEMINAL_TIME,SPEEDING as VEHICLE_SPEED,UNIT,ON_OFF_VALUE,LONGITUDE,LATITUDE,OIL_INSTANT,nvl(ENGINE_ROTATE_SPEED,0) ENGINE_ROTATE_SPEED,TORQUE,EEC_APP,nvl(VEHICLE_SPEED,0) as SPEEDING,RESERVED"
                + " from CLW_YW_SEC_DATA_T partition (SEC_DATA_"
                + Sdate(date)
                + ") where vehicle_vin=?    order by teminal_time asc";
        List<ClwSecData> clwSecData = jdbcTemplate.query(SQL_CLWSECDATA, new Object[] { vin },
                ParameterizedBeanPropertyRowMapper.newInstance(ClwSecData.class));
        return clwSecData;
    }

    /**
     * 获取车联急加速数据列表
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<RapidBean> getClwJjsRecord(String vin, String date) throws DataAccessException {
        // 获取急加速 急减速记录
        String SQL_CLWJJSDATA = "select ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER, to_char(WRITE_TIME,'yyyy-mm-dd hh24:mi:ss') WRITE_TIME,to_char(TEMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TEMINAL_TIME,SPEEDING,UNIT,ON_OFF_VALUE,LONGITUDE,LATITUDE"
                + " from CLW_YW_RAPID_RECORD_T  partition(RAPID_RECORD_"
                + Sdate(date)
                + ") where vehicle_vin=? and to_char(teminal_time,'yyyy-mm-dd') =?  order by teminal_time asc";
        List<RapidBean> clwJjsData = jdbcTemplate.query(SQL_CLWJJSDATA, new Object[] { vin, date },
                ParameterizedBeanPropertyRowMapper.newInstance(RapidBean.class));
        return clwJjsData;
    }

    /**
     * 获取五分钟信息列表
     * @param reqBean
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Minute5Data> get5MinuteRecord(String vin, String date) throws DataAccessException {

        // 获取五分钟记录
        String SQL_5MINUTEIST = "select ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,to_char(WRITE_TIME,'yyyy-mm-dd hh24:mi:ss') WRITE_TIME,to_char(TEMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TEMINAL_TIME,ENGHRREV_T_E_H,BATTERY_VOLTAGE,AIR_PRESSURE,AIR_INFLOW_TPR "
                + " from CLW_YW_MINUTE5_DATA_T  partition(MINUTE5_DATA_"
                + Sdate(date)
                + ")  where vehicle_vin=? and to_char(teminal_time,'yyyy-mm-dd') =? order by teminal_time asc";

        List<Minute5Data> minute5Data = jdbcTemplate.query(SQL_5MINUTEIST,
                new Object[] { vin, date }, ParameterizedBeanPropertyRowMapper
                        .newInstance(Minute5Data.class));

        return minute5Data;
    }

    /**
     * 获取补算信息列表
     * @param reqBean
     * @returnselect VEHICLE_VIN, ALARM_DAY from clw_yw_baddriving_t WHERE
     *               TO_CHAR(ALARM_DAY,'YYYY-MM-DD')='2011-07-07' AND (END_TIME IS NULL OR END_TIME
     *               ='') ;
     */
    @SuppressWarnings("unchecked")
    public List<BaddrivingDate> getBaddrivingDate(String date) throws DataAccessException {

        // 获取五分钟记录
        String SQL_BaddrivingDate = "select VEHICLE_VIN, to_char(ALARM_DAY,'yyyy-mm-dd')  ALARM_DAY  "
                + " from clw_yw_baddriving_t  partition(BADDRIVING_"
                + SdateM(date)
                + ") WHERE TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=? AND (END_TIME IS NULL OR END_TIME ='')";

        List<BaddrivingDate> baddrivingDate = jdbcTemplate.query(SQL_BaddrivingDate,
                new Object[] { date }, ParameterizedBeanPropertyRowMapper
                        .newInstance(BaddrivingDate.class));

        return baddrivingDate;
    }

    
    public int deletedetail(String vin, String date) throws DataAccessException {
        int num = jdbcTemplate.update("DELETE CLW_YW_MALARMD_T partition(malarmd_"+Sdate(date)+")"
                + " where vehicle_vin='" + vin + "'");
        return num;
    }
    //删除校车非法点火和超载明细
    public int deletexcdetail(String vin, String date) throws DataAccessException {
        int num = jdbcTemplate.update("DELETE CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+Sdate(date)+")"
                + " where vehicle_vin='" + vin + "' and EVENT_TYPE_ID in('72','54')" );
        return num;
    }
    public int deletevin(String vin, String date) throws DataAccessException {
    	//SQL_DELETE = DELETE CLW_YW_BADDRIVING_T
        int num = jdbcTemplate.update(SQL_DELETE + "where to_char(alarm_day,'yyyy-mm-dd')='" + date
                + "' and vehicle_vin='" + vin + "'");
        return num;
    }

    public int insertvin(String vin, String date) throws DataAccessException {
        // System.out.println(selPagedSql);
        int num = jdbcTemplate.update(SQL_INSERT, new Object[] { date, vin });
        return num;
    }

    public int deleteVeztd(String vin, String date) throws DataAccessException {

        int num = jdbcTemplate.update(SQL_DELETE_VEZTD + "where to_char(LAST_TIME,'yyyy-mm-dd')='"
                + date + "' and vehicle_vin='" + vin + "'");
        return num;
    }

    public int insertVeztd(String vin, String date) throws DataAccessException {
        // System.out.println(selPagedSql);
        int num = jdbcTemplate.update(SQL_INSERT_VEZTD, new Object[] { date, vin });
        return num;
    }

    // 生成告警明细
    private static final String SQL_INSERT_MALARMD = "INSERT INTO CLW_YW_MALARMD_T (MALARMD_ID,ALARM_TYPE_ID,VEHICLE_VIN,TEMINAL_TIME,WRITE_TIME,ALARM_START_TIME,"
            + "ALARM_END_TIME, ALARM_TIME ,ALARM_START_SPEED,ALARM_START_RPM,ALARM_START_LATITUDE,ALARM_START_LONGITUDE)     "
            + "values( sys_guid(),?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),sysdate,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?)";

    public int insertCcdsMx(ClwSecData ccdsEnd, ClwSecData ccdsBegin, long cxTime, String type)
            throws DataAccessException {
        // System.out.println("::111111111111111111111111");
        int num = jdbcTemplate.update(SQL_INSERT_MALARMD, new Object[] { type,
                ccdsBegin.getVEHICLE_VIN(), ccdsBegin.getTEMINAL_TIME(),
                ccdsBegin.getTEMINAL_TIME(), ccdsEnd.getTEMINAL_TIME(), cxTime,
                ccdsBegin.getSPEEDING(), ccdsBegin.getENGINE_ROTATE_SPEED(),
                ccdsBegin.getLATITUDE(), ccdsBegin.getLONGITUDE() });
        return num;
    }

    public int insertCcds(RealTimeRecord ccdsEnd, RealTimeRecord ccdsBegin, float cxTime,
            String type) throws DataAccessException {
        // System.out.println("::22222222222222211111111111111111111111"+type+":"+ccdsBegin.getVEHICLE_VIN()+":"+ccdsBegin.getTERMINAL_TIME()+":"+ccdsBegin.getTERMINAL_TIME()+":"+ccdsEnd.getTERMINAL_TIME()+":"+
        // cxTime+ccdsBegin.getSPEEDING()+":"+ccdsBegin.getENGINE_ROTATE_SPEED()+":"+ccdsBegin.getLATITUDE()+":"+ccdsBegin.getLONGITUDE());
        int num = jdbcTemplate.update(SQL_INSERT_MALARMD, new Object[] { type,
                ccdsBegin.getVEHICLE_VIN(), ccdsBegin.getTERMINAL_TIME(),
                ccdsBegin.getTERMINAL_TIME(), ccdsEnd.getTERMINAL_TIME(), cxTime,
                ccdsBegin.getSPEEDING(), ccdsBegin.getENGINE_ROTATE_SPEED(),
                ccdsBegin.getLATITUDE(), ccdsBegin.getLONGITUDE() });
        return num;
    }

    // 生成疲劳驾驶明细
    // private static final String SQL_INSERT_PLDETAIL = "INSERT INTO CLW_YW_MALARMD_T
    // (MALARMD_ID,ALARM_TYPE_ID,VEHICLE_VIN,TEMINAL_TIME,WRITE_TIME,ALARM_START_TIME,"
    // + "ALARM_END_TIME,
    // ALARM_TIME,ALARM_START_SPEED,ALARM_START_RPM,ALARM_START_LATITUDE,ALARM_START_LONGITUDE) "
    // + "values( sys_guid(),?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),sysdate,to_date(?,'yyyy-mm-dd
    // hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?)";
    //
    // public int makePldetail(String strVin, String strDate, RealTimeRecord plPerv,
    // RealTimeRecord plNonce, long cxTime) throws DataAccessException {
    // // System.out.println("::33333333333333333111111111111");
    // int num = jdbcTemplate.update(SQL_INSERT_PLDETAIL, new Object[] { 33, strVin,
    // plPerv.getTERMINAL_TIME(), plPerv.getTERMINAL_TIME(), plNonce.getTERMINAL_TIME(),
    // cxTime, plPerv.getSPEEDING(), plPerv.getENGINE_ROTATE_SPEED(),
    // plPerv.getLATITUDE(), plPerv.getLONGITUDE() });
    // return num;
    // }

    private static final String SQL_INSERT_PLDETAIL = "INSERT INTO CLW_YW_MALARMD_T (MALARMD_ID,ALARM_TYPE_ID,VEHICLE_VIN,TEMINAL_TIME,WRITE_TIME,ALARM_START_TIME,"
            + "ALARM_END_TIME, ALARM_TIME,ALARM_START_SPEED,ALARM_START_RPM,ALARM_START_LATITUDE,ALARM_START_LONGITUDE)     "
            + "values( sys_guid(),?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),sysdate,to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?,?,?)";

    /**
     * 保存一条疲劳驾驶详细记录
     * @param strVin 车辆VIN
     * @param strDate 疲劳驾驶日期
     * @param plPerv 疲劳驾驶报警开的详细源数据
     * @param plEndTime 疲劳驾驶报警关的时间
     * @param cxTime 本次疲劳驾驶时长（单位：秒）
     * @return 插入结果
     * @throws DataAccessException
     */
    public int makePldetail(String strVin, String strDate, RealTimeRecord plPerv, Date plEndTime,
            long cxTime) throws DataAccessException {
        int num = jdbcTemplate.update(SQL_INSERT_PLDETAIL, new Object[] { 33, strVin,
                plPerv.getTERMINAL_TIME(), plPerv.getTERMINAL_TIME(), plEndTime, cxTime,
                plPerv.getSPEEDING(), plPerv.getENGINE_ROTATE_SPEED(), plPerv.getLATITUDE(),
                plPerv.getLONGITUDE() });
        return num;
    }

    // 生成急加速及减速明细
    private static final String SQL_INSERT_JJSDETAIL = "INSERT INTO CLW_YW_MALARMD_T (MALARMD_ID,ALARM_TYPE_ID,VEHICLE_VIN,TEMINAL_TIME,WRITE_TIME,ALARM_START_TIME,"
            + "ALARM_END_TIME, ALARM_TIME,ALARM_START_SPEED,ALARM_START_LATITUDE,ALARM_START_LONGITUDE)     "
            + "values( sys_guid(),?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'),sysdate,to_date(?,'yyyy-mm-dd hh24:mi:ss'),to_date(?,'yyyy-mm-dd hh24:mi:ss'),?,?,?,?)";

    public int makerapiddetail(String strDate, String type, RapidBean perv, RapidBean nonce,
            long cxTime) throws DataAccessException {
        // System.out.println("::4444444444444444444441111111");
        int num = jdbcTemplate.update(SQL_INSERT_JJSDETAIL, new Object[] { type,
                perv.getVEHICLE_VIN(), perv.getTEMINAL_TIME(), perv.getTEMINAL_TIME(),
                nonce.getTEMINAL_TIME(), cxTime, perv.getSPEEDING(), perv.getLATITUDE(),
                perv.getLONGITUDE() });
        return num;
    }
    // 生成超载和非法点火明细    

    public int makczdetail(String strVin,String strDate) throws DataAccessException {
    	 String SQL_INSERT_CZDETAIL = "INSERT INTO CLW_XC_BADEVENT_T (EVENT_ID,EVENT_TYPE_ID,EVENT_START_LATITUDE,EVENT_START_LONGITUDE,EVENT_START_TIME," +
		"EVENT_END_LATITUDE,EVENT_END_LONGITUDE,EVENT_END_TIME,CREATE_TIME,VEHICLE_VIN," +
		"DRIVER_ID,EVENT_TIME,ROUTE_ID,STU_NUM,LOAD_NUM)" +
		"select  ALARM_ID,ALARM_TYPE_ID,LATITUDE,LONGITUDE,ALARM_TIME," +
		"END_LATITUDE,END_LONGITUDE,ALARM_END_TIME,sysdate,VEHICLE_VIN," +
		"DRIVER_ID,(ALARM_END_TIME-ALARM_TIME)*24*60*60,ROUTE_ID,STU_NUM,LOAD_NUM " +
		"from CLW_YW_ALERM_RECORD_T partition(ALERM_RECORD_"+ Sdate(strDate)+") where VEHICLE_VIN =? and ALARM_TYPE_ID in ('72','54')";
        int num = jdbcTemplate.update(SQL_INSERT_CZDETAIL, new Object[] {strVin});
        return num;
    }
    // 统计明细生成告警汇总信息    
    public int updateCsdsDataXc(String strVin, String strDate, String type)
            throws DataAccessException {
        int num = 0;        
        if (type.equals("72")) {
          	 // 超载-校车
               String SQL_SUM_DETAIL_CZ = "UPDATE CLW_YW_BADDRIVING_T SET OVER_LOADING_NUM=(SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?)"                     
                      + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
              num = jdbcTemplate.update(SQL_SUM_DETAIL_CZ, new Object[] { strVin,  type, strVin, strDate });
          } else if (type.equals("54")) {
       	 // 非法点火-校车
        	  String SQL_SUM_DETAIL_DH = "UPDATE CLW_YW_BADDRIVING_T SET ILLEGAL_ON_FIRE=(SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?)"                     
              + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
        	  num = jdbcTemplate.update(SQL_SUM_DETAIL_DH, new Object[] { strVin,  type, strVin, strDate });
       }  else if (type.equals("50")) {
        	 // 超长怠速计算-校车
             String SQL_SUM_DETAIL = "UPDATE CLW_YW_BADDRIVING_T SET LONG_IDLE_NUM=(SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?),"
                    + "LONG_IDLE_TIME=(SELECT SUM(EVENT_END_TIME-EVENT_START_TIME)*24*60*60  FROM CLW_XC_BADEVENT_T  partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and EVENT_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL, new Object[] { strVin,  type, strVin, type, strVin, strDate });
        }  else if (type.equals("81")) {
       	 //非经济区运行-校车
            String SQL_SUM_DETAIL_KT = "UPDATE CLW_YW_BADDRIVING_T SET "
                     + "ECONOMIC_RUN_TIME=(SELECT SUM(EVENT_END_TIME-EVENT_START_TIME)*24*60*60  FROM CLW_XC_BADEVENT_T  partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and EVENT_TYPE_ID=? ) "
                     + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
             num = jdbcTemplate.update(SQL_SUM_DETAIL_KT, new Object[] { strVin,  type, strVin, strDate });
         }
          else if (type.equals("51")) {
        	 // 怠速空调计算-校车
           String SQL_SUM_DETAIL_KT = "UPDATE CLW_YW_BADDRIVING_T SET AIR_CONDITION_NUM=(SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?),"
                    + "AIR_CONDITION_TIME=(SELECT SUM(EVENT_END_TIME-EVENT_START_TIME)*24*60*60  FROM CLW_XC_BADEVENT_T  partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and EVENT_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_KT, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("32")) {
        		// 超速次数，超速时间--校车
        	  String SQL_SUM_DETAIL_CS = "UPDATE CLW_YW_BADDRIVING_T SET SPEEDING_NUM=(SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?),"
              + "SPEEDING_TIME=(SELECT SUM(EVENT_END_TIME-EVENT_START_TIME)*24*60*60  FROM CLW_XC_BADEVENT_T  partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and EVENT_TYPE_ID=? ) "
              + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_CS, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("33")) {
        	   // 疲劳驾驶-校车
            // FATIGUE_DRIVING_NUM 疲劳驾驶次数 实时告警表
            // FATIGUE_DRIVING_TIME 疲劳驾驶时间
             String SQL_SUM_DETAIL_PLJS = "UPDATE CLW_YW_BADDRIVING_T SET FATIGUE_DRIVING_NUM=(SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?),"
                    + "FATIGUE_DRIVING_TIME=(SELECT SUM(EVENT_END_TIME-EVENT_START_TIME)*24*60*60  FROM CLW_XC_BADEVENT_T  partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and EVENT_TYPE_ID=?) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_PLJS, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("49")) {
        	 // 超转 RPM_NUM 超转次数 RPM_TIME 超转持续时间-校车
          String SQL_SUM_DETAIL_CZ = "UPDATE CLW_YW_BADDRIVING_T SET RPM_NUM=        (SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?),"
                    + "RPM_TIME=(SELECT SUM(EVENT_END_TIME-EVENT_START_TIME)*24*60*60  FROM CLW_XC_BADEVENT_T  partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and EVENT_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_CZ, new Object[] { strVin,  type,
                    strVin, type, strVin, strDate });
        } else if (type.equals("45")) {
        	  // GEAR_WRONG_NUM 起步档位不当次数 GEAR_WRONG_TIME 起步档位不当持续时间
            String SQL_SUM_DETAIL_DWBD = "UPDATE CLW_YW_BADDRIVING_T SET GEAR_WRONG_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "GEAR_WRONG_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_DWBD, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("46")) {
        	 // GEAR_GLIDE_NUM 空档滑行次数// GEAR_GLIDE_TIME 空档滑行持续时间-校车
          String SQL_SUM_DETAIL_KD = "UPDATE CLW_YW_BADDRIVING_T SET GEAR_GLIDE_NUM=(SELECT COUNT(*) FROM CLW_XC_BADEVENT_T partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and EVENT_TYPE_ID=?),"
                    + "GEAR_GLIDE_TIME=(SELECT SUM(EVENT_END_TIME-EVENT_START_TIME)*24*60*60  FROM CLW_XC_BADEVENT_T  partition(XC_BADEVENT_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and EVENT_TYPE_ID=?) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_KD, new Object[] { strVin,  type,
                    strVin, type, strVin, strDate });
        } else if (type.equals("47")) {
        	 // URGENT_SPEED_NUM 急超速次数 URGENT_SPEED_TIME 急超速持续时间
           String SQL_SUM_DETAIL_JJS47 = "UPDATE CLW_YW_BADDRIVING_T SET URGENT_SPEED_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T   partition(MALARMD_"+ Sdate(strDate)+")  WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "URGENT_SPEED_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_JJS47, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("48")) {
        	// URGENT_LOWDOWN_NUM 急减速次数 URGENT_LOWDOWN_TIME 急减速持续时间
           String SQL_SUM_DETAIL_JJS48 = "UPDATE CLW_YW_BADDRIVING_T SET URGENT_LOWDOWN_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "URGENT_LOWDOWN_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_JJS48, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        }
        return num;
    }
    // 统计明细生成告警汇总信息    
    public int updateCsdsData(String strVin, String strDate, String type)
            throws DataAccessException {
        int num = 0;
        if (type.equals("50")) {
        	 // 超长怠速计算
             String SQL_SUM_DETAIL = "UPDATE CLW_YW_BADDRIVING_T SET LONG_IDLE_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and ALARM_TYPE_ID=?),"
                    + "LONG_IDLE_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL, new Object[] { strVin,  type, strVin,
                     type, strVin, strDate });
        } else if (type.equals("51")) {
        	 // 怠速空调计算
           String SQL_SUM_DETAIL_KT = "UPDATE CLW_YW_BADDRIVING_T SET AIR_CONDITION_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "AIR_CONDITION_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_KT, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("32")) {
        	// 超速次数，超速时间
             String SQL_SUM_DETAIL_CS = "UPDATE CLW_YW_BADDRIVING_T SET SPEEDING_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and ALARM_TYPE_ID=?),"
                    + "SPEEDING_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
              num = jdbcTemplate.update(SQL_SUM_DETAIL_CS, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("33")) {
        	   // 疲劳驾驶
            // FATIGUE_DRIVING_NUM 疲劳驾驶次数 实时告警表
            // FATIGUE_DRIVING_TIME 疲劳驾驶时间
             String SQL_SUM_DETAIL_PLJS = "UPDATE CLW_YW_BADDRIVING_T SET FATIGUE_DRIVING_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T   partition(MALARMD_"+ Sdate(strDate)+")  WHERE   VEHICLE_VIN=?  and ALARM_TYPE_ID=?),"
                    + "FATIGUE_DRIVING_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T   partition(MALARMD_"+ Sdate(strDate)+")  WHERE   VEHICLE_VIN=?  and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_PLJS, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("49")) {
        	 // 超转 RPM_NUM 超转次数 RPM_TIME 超转持续时间
          String SQL_SUM_DETAIL_CZ = "UPDATE CLW_YW_BADDRIVING_T SET RPM_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+")  WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "RPM_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+")  WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_CZ, new Object[] { strVin,  type,
                    strVin, type, strVin, strDate });
        } else if (type.equals("45")) {
        	  // GEAR_WRONG_NUM 起步档位不当次数 GEAR_WRONG_TIME 起步档位不当持续时间
            String SQL_SUM_DETAIL_DWBD = "UPDATE CLW_YW_BADDRIVING_T SET GEAR_WRONG_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "GEAR_WRONG_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?  and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_DWBD, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("46")) {
        	 // GEAR_GLIDE_NUM 空档滑行次数// GEAR_GLIDE_TIME 空档滑行持续时间
          String SQL_SUM_DETAIL_KD = "UPDATE CLW_YW_BADDRIVING_T SET GEAR_GLIDE_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T WHERE   partition(MALARMD_"+ Sdate(strDate)+")   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "GEAR_GLIDE_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+")  WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_KD, new Object[] { strVin,  type,
                    strVin, type, strVin, strDate });
        } else if (type.equals("47")) {
        	 // URGENT_SPEED_NUM 急超速次数 URGENT_SPEED_TIME 急超速持续时间
           String SQL_SUM_DETAIL_JJS47 = "UPDATE CLW_YW_BADDRIVING_T SET URGENT_SPEED_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T   partition(MALARMD_"+ Sdate(strDate)+")  WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "URGENT_SPEED_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_JJS47, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        } else if (type.equals("48")) {
        	// URGENT_LOWDOWN_NUM 急减速次数 URGENT_LOWDOWN_TIME 急减速持续时间
           String SQL_SUM_DETAIL_JJS48 = "UPDATE CLW_YW_BADDRIVING_T SET URGENT_LOWDOWN_NUM=(SELECT COUNT(*) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=?),"
                    + "URGENT_LOWDOWN_TIME=(SELECT SUM(NVL(ALARM_TIME,0)) FROM CLW_YW_MALARMD_T  partition(MALARMD_"+ Sdate(strDate)+") WHERE   VEHICLE_VIN=?   and ALARM_TYPE_ID=? ) "
                    + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";
            num = jdbcTemplate.update(SQL_SUM_DETAIL_JJS48, new Object[] { strVin,  type,
                    strVin,  type, strVin, strDate });
        }
        return num;
    }

    // 更新 SPEEDING_OIL本日超速下油耗 ，SPEEDING_MILEAGE本日超速下行驶里程
    private static final String SQL_CS_OIL_MILEAGE = "UPDATE CLW_YW_BADDRIVING_T SET SPEEDING_OIL=?,SPEEDING_MILEAGE=?  "
            + " WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";

    public int updateCsOilMileage(String strVin, String strDate, float sPEEDINGOIL,
            float sPEEDINGMILEAGE) throws DataAccessException {
        int num = jdbcTemplate.update(SQL_CS_OIL_MILEAGE, new Object[] { sPEEDINGOIL,
                sPEEDINGMILEAGE, strVin, strDate });
        return num;
    }

    /**
     * 参数配置表操作，获取报表统计日期，更新报表统计日期，获取延迟天数
     * @return
     */
    //	
    // select
    // to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')-(to_date(t.param_value,'yyyy-mm-dd') +1)
    // delay from clw_jc_param_cfg_t t where t.param_name='reportBuildTime'
    // update clw_jc_param_cfg_t set param_value=to_char(to_date( param_value,'yyyy-mm-dd')
    // +1,'yyyy-mm-dd') where param_name='reportBuildTime'
    public String getReportDate() throws DataAccessException {
        String rDate = (String) jdbcTemplate
                .queryForObject(
                        "select to_char(to_date(t.param_value,'yyyy-mm-dd') +1,'yyyy-mm-dd') vdate from clw_jc_param_cfg_t t where t.param_name='reportBuildTime'",
                        String.class);
        return rDate;
    }

    public int getDelay() throws DataAccessException {
        int delay = jdbcTemplate
                .queryForInt("select to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')-(to_date(t.param_value,'yyyy-mm-dd') +1) delay from clw_jc_param_cfg_t t where t.param_name='reportBuildTime'");
        return delay;
    }

    public int setReportDate() throws DataAccessException {
        int delay = jdbcTemplate
                .update("update clw_jc_param_cfg_t set param_value=to_char(to_date( param_value,'yyyy-mm-dd') +1,'yyyy-mm-dd') where  param_name='reportBuildTime'");
        return delay;
    }

    // 开关量列表
    @SuppressWarnings("unchecked")
    public List<OnOffBean> getClwOnOffRecord(String strVin, String strDate)
            throws DataAccessException {
        String SQL_CLWONOFFDATA = "select TERMINAL_ID ,VEHICLE_VIN,SIM_CARD_NUMBER,to_char(WRITE_TIME,'yyyy-mm-dd hh24:mi:ss') WRITE_TIME,to_char(TERMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TERMINAL_TIME,AFTER_VALUE,PRE_VALUE,SPEED,DOOR_STATE"
                + " from CLW_YW_ON_OF_RECORD_T where vehicle_vin=? and to_char(terminal_time,'yyyy-mm-dd') =?  order by terminal_time asc";
        List<OnOffBean> onOffBean = jdbcTemplate.query(SQL_CLWONOFFDATA, new Object[] { strVin,
                strDate }, ParameterizedBeanPropertyRowMapper.newInstance(OnOffBean.class));
        return onOffBean;
    }

    public String Sdate(String sdate) throws DataAccessException {
        return sdate.substring(0, 4) + sdate.substring(5, 7) + sdate.substring(8, 10);
    }

    public String SdateM(String sdate) throws DataAccessException {
        return sdate.substring(0, 4) + sdate.substring(5, 7);
    }

    // 车速中间表
    public int deleteVS(String vin, String date) throws DataAccessException {
        int num = jdbcTemplate.update("DELETE CLW_YW_VEHICLE_SPEED_T "
                + " where  to_char(TERMINAL_DAY,'yyyy-mm-dd')='" + date + "' and vehicle_vin='"
                + vin + "'");
        return num;
    }

    // 车速中间表
    public int insertVS(VehicleSpeed v_s, String vin, String date) throws DataAccessException {
        String vssql = "insert into CLW_YW_VEHICLE_SPEED_T (SPEED_ID,VEHICLE_VIN,TERMINAL_DAY,CREATE_TIME,SPEEDING_0,SPEEDING_0_TIME,SPEEDING_0_10,SPEEDING_0_10_TIME,SPEEDING_10_20,SPEEDING_10_20_TIME,SPEEDING_20_30,SPEEDING_20_30_TIME,SPEEDING_30_40,SPEEDING_30_40_TIME,SPEEDING_40_50,SPEEDING_40_50_TIME,SPEEDING_50_60,SPEEDING_50_60_TIME,SPEEDING_60_70,SPEEDING_60_70_TIME,SPEEDING_70_80,SPEEDING_70_80_TIME,SPEEDING_80_90,SPEEDING_80_90_TIME,SPEEDING_90_100,SPEEDING_90_100_TIME,SPEEDING_100_110,SPEEDING_100_110_TIME,SPEEDING_110_120,SPEEDING_110_120_TIME,SPEEDING_120_130,SPEEDING_120_130_TIME,SPEEDING_130_140,SPEEDING_130_140_TIME,SPEEDING_140_150,SPEEDING_140_150_TIME,SPEEDING_150_160,SPEEDING_150_160_TIME,SPEEDING_160_170,SPEEDING_160_170_TIME,SPEEDING_170_180,SPEEDING_170_180_TIME,SPEEDING_180_190,SPEEDING_180_190_TIME,SPEEDING_190_200,SPEEDING_190_200_TIME,SPEEDING_MAX,SPEEDING_MAX_TIME,MAX_SPEEDING,MIN_SPEEDING) "
                + "values(sys_guid(),?,to_date(?,'yyyy-mm-dd'),sysdate,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        int num = jdbcTemplate.update(vssql, new Object[] { vin, date, v_s.getSPEEDING_0(),
                v_s.getSPEEDING_0_TIME(), v_s.getSPEEDING_0_10(), v_s.getSPEEDING_0_10_TIME(),
                v_s.getSPEEDING_10_20(), v_s.getSPEEDING_10_20_TIME(), v_s.getSPEEDING_20_30(),
                v_s.getSPEEDING_20_30_TIME(), v_s.getSPEEDING_30_40(),
                v_s.getSPEEDING_30_40_TIME(), v_s.getSPEEDING_40_50(),
                v_s.getSPEEDING_40_50_TIME(), v_s.getSPEEDING_50_60(),
                v_s.getSPEEDING_50_60_TIME(), v_s.getSPEEDING_60_70(),
                v_s.getSPEEDING_60_70_TIME(), v_s.getSPEEDING_70_80(),
                v_s.getSPEEDING_70_80_TIME(), v_s.getSPEEDING_80_90(),
                v_s.getSPEEDING_80_90_TIME(), v_s.getSPEEDING_90_100(),
                v_s.getSPEEDING_90_100_TIME(), v_s.getSPEEDING_100_110(),
                v_s.getSPEEDING_100_110_TIME(), v_s.getSPEEDING_110_120(),
                v_s.getSPEEDING_110_120_TIME(), v_s.getSPEEDING_120_130(),
                v_s.getSPEEDING_120_130_TIME(), v_s.getSPEEDING_130_140(),
                v_s.getSPEEDING_130_140_TIME(), v_s.getSPEEDING_140_150(),
                v_s.getSPEEDING_140_150_TIME(), v_s.getSPEEDING_150_160(),
                v_s.getSPEEDING_150_160_TIME(), v_s.getSPEEDING_160_170(),
                v_s.getSPEEDING_160_170_TIME(), v_s.getSPEEDING_170_180(),
                v_s.getSPEEDING_170_180_TIME(), v_s.getSPEEDING_180_190(),
                v_s.getSPEEDING_180_190_TIME(), v_s.getSPEEDING_190_200(),
                v_s.getSPEEDING_190_200_TIME(), v_s.getSPEEDING_MAX(), v_s.getSPEEDING_MAX_TIME(),
                v_s.getMAX_SPEEDING(), v_s.getMIN_SPEEDING() });
        return num;
    }

    // 转速中间表
    public int deleteRS(String vin, String date) throws DataAccessException {
        int num = jdbcTemplate.update("DELETE CLW_YW_ROTATE_SPEED_T "
                + " where  to_char(TERMINAL_DAY,'yyyy-mm-dd')='" + date + "' and vehicle_vin='"
                + vin + "'");
        return num;
    }

    // 转速中间表
    public int insertRS(RotateSpeed r_s, String vin, String date) throws DataAccessException {
        String vssql = "insert into CLW_YW_ROTATE_SPEED_T (ROTATE_ID,VEHICLE_VIN,TERMINAL_DAY,CREATE_TIME,ROTATE_SPEED_0,ROTATE_SPEED_0_TIME,ROTATE_SPEED_0_100,ROTATE_SPEED_0_100_TIME,ROTATE_SPEED_100_200,ROTATE_SPEED_100_200_TIME,ROTATE_SPEED_200_300,ROTATE_SPEED_200_300_TIME,ROTATE_SPEED_300_400,ROTATE_SPEED_300_400_TIME,ROTATE_SPEED_400_500,ROTATE_SPEED_400_500_TIME,ROTATE_SPEED_500_600,ROTATE_SPEED_500_600_TIME,ROTATE_SPEED_600_700,ROTATE_SPEED_600_700_TIME,ROTATE_SPEED_700_800,ROTATE_SPEED_700_800_TIME,ROTATE_SPEED_800_900,ROTATE_SPEED_800_900_TIME,ROTATE_SPEED_900_1000,ROTATE_SPEED_900_1000_TIME,ROTATE_SPEED_1000_1100,ROTATE_SPEED_1000_1100_TIME,ROTATE_SPEED_1100_1200,ROTATE_SPEED_1100_1200_TIME,ROTATE_SPEED_1200_1300,ROTATE_SPEED_1200_1300_TIME,ROTATE_SPEED_1300_1400,ROTATE_SPEED_1300_1400_TIME,ROTATE_SPEED_1400_1500,ROTATE_SPEED_1400_1500_TIME,ROTATE_SPEED_1500_1600,ROTATE_SPEED_1500_1600_TIME,ROTATE_SPEED_1600_1700,ROTATE_SPEED_1600_1700_TIME,ROTATE_SPEED_1700_1800,ROTATE_SPEED_1700_1800_TIME,ROTATE_SPEED_1800_1900,ROTATE_SPEED_1800_1900_TIME,ROTATE_SPEED_1900_2000,ROTATE_SPEED_1900_2000_TIME,ROTATE_SPEED_2000_2100,ROTATE_SPEED_2000_2100_TIME,ROTATE_SPEED_2100_2200,ROTATE_SPEED_2100_2200_TIME,ROTATE_SPEED_2200_2300,ROTATE_SPEED_2200_2300_TIME,ROTATE_SPEED_2300_2400,ROTATE_SPEED_2300_2400_TIME,ROTATE_SPEED_2400_2500,ROTATE_SPEED_2400_2500_TIME,ROTATE_SPEED_2500_2600,ROTATE_SPEED_2500_2600_TIME,ROTATE_SPEED_2600_2700,ROTATE_SPEED_2600_2700_TIME,ROTATE_SPEED_2700_2800,ROTATE_SPEED_2700_2800_TIME,ROTATE_SPEED_2800_2900,ROTATE_SPEED_2800_2900_TIME,ROTATE_SPEED_2900_3000,ROTATE_SPEED_2900_3000_TIME,ROTATE_SPEED_MAX,ROTATE_SPEED_MAX_TIME,MIN_ROTATE_SPEED,MAX_ROTATE_SPEED,PERCENT_60_80_FUHELV) "
                + "values(sys_guid(),?,to_date(?,'yyyy-mm-dd'),sysdate,?,?,?,?,?,?,?,?,?,?,   ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?,?,?,? ,?,?,?,?,?,?,?)";
        int num = jdbcTemplate.update(vssql, new Object[] { vin, date, r_s.getROTATE_SPEED_0(),
                r_s.getROTATE_SPEED_0_TIME(), r_s.getROTATE_SPEED_0_100(),
                r_s.getROTATE_SPEED_0_100_TIME(), r_s.getROTATE_SPEED_100_200(),
                r_s.getROTATE_SPEED_100_200_TIME(), r_s.getROTATE_SPEED_200_300(),
                r_s.getROTATE_SPEED_200_300_TIME(), r_s.getROTATE_SPEED_300_400(),
                r_s.getROTATE_SPEED_300_400_TIME(), r_s.getROTATE_SPEED_400_500(),
                r_s.getROTATE_SPEED_400_500_TIME(), r_s.getROTATE_SPEED_500_600(),
                r_s.getROTATE_SPEED_500_600_TIME(), r_s.getROTATE_SPEED_600_700(),
                r_s.getROTATE_SPEED_600_700_TIME(), r_s.getROTATE_SPEED_700_800(),
                r_s.getROTATE_SPEED_700_800_TIME(), r_s.getROTATE_SPEED_800_900(),
                r_s.getROTATE_SPEED_800_900_TIME(), r_s.getROTATE_SPEED_900_1000(),
                r_s.getROTATE_SPEED_900_1000_TIME(), r_s.getROTATE_SPEED_1000_1100(),
                r_s.getROTATE_SPEED_1000_1100_TIME(), r_s.getROTATE_SPEED_1100_1200(),
                r_s.getROTATE_SPEED_1100_1200_TIME(), r_s.getROTATE_SPEED_1200_1300(),
                r_s.getROTATE_SPEED_1200_1300_TIME(), r_s.getROTATE_SPEED_1300_1400(),
                r_s.getROTATE_SPEED_1300_1400_TIME(), r_s.getROTATE_SPEED_1400_1500(),
                r_s.getROTATE_SPEED_1400_1500_TIME(), r_s.getROTATE_SPEED_1500_1600(),
                r_s.getROTATE_SPEED_1500_1600_TIME(), r_s.getROTATE_SPEED_1600_1700(),
                r_s.getROTATE_SPEED_1600_1700_TIME(), r_s.getROTATE_SPEED_1700_1800(),
                r_s.getROTATE_SPEED_1700_1800_TIME(), r_s.getROTATE_SPEED_1800_1900(),
                r_s.getROTATE_SPEED_1800_1900_TIME(), r_s.getROTATE_SPEED_1900_2000(),
                r_s.getROTATE_SPEED_1900_2000_TIME(), r_s.getROTATE_SPEED_2000_2100(),
                r_s.getROTATE_SPEED_2000_2100_TIME(), r_s.getROTATE_SPEED_2100_2200(),
                r_s.getROTATE_SPEED_2100_2200_TIME(), r_s.getROTATE_SPEED_2200_2300(),
                r_s.getROTATE_SPEED_2200_2300_TIME(), r_s.getROTATE_SPEED_2300_2400(),
                r_s.getROTATE_SPEED_2300_2400_TIME(), r_s.getROTATE_SPEED_2400_2500(),
                r_s.getROTATE_SPEED_2400_2500_TIME(), r_s.getROTATE_SPEED_2500_2600(),
                r_s.getROTATE_SPEED_2500_2600_TIME(), r_s.getROTATE_SPEED_2600_2700(),
                r_s.getROTATE_SPEED_2600_2700_TIME(), r_s.getROTATE_SPEED_2700_2800(),
                r_s.getROTATE_SPEED_2700_2800_TIME(), r_s.getROTATE_SPEED_2800_2900(),
                r_s.getROTATE_SPEED_2800_2900_TIME(), r_s.getROTATE_SPEED_2900_3000(),
                r_s.getROTATE_SPEED_2900_3000_TIME(), r_s.getROTATE_SPEED_MAX(),
                r_s.getROTATE_SPEED_MAX_TIME(), r_s.getMIN_ROTATE_SPEED(),
                r_s.getMAX_ROTATE_SPEED(), r_s.getPERCENT_60_80_FUHELV() });
        return num;
    }

    private static final String SQL_GET_HISTORY_TIME_BY_ID = "select SEC_DATA_TIME,MIN1_DATA_TIME,MIN5_DATA_TIME,LOGIN_DATA_TIME,"
            + "OVERSPEED_DATA_TIME,ONOFF_DATA_TIME,RAPID_DATE_TIME,FATIGUE_TIME from clw_yw_histroy_time_t where vehicle_vin=?";

    private static final String SQL_INSERT_FAG_HISTORY_TIME = "INSERT INTO clw_yw_histroy_time_t(vehicle_vin,FATIGUE_TIME)"
            + " VALUES (?,?)";

    private static final String SQL_UPDATE_FAG_TIME_OF_HISTORY = "UPDATE clw_yw_histroy_time_t SET FATIGUE_TIME=? WHERE vehicle_vin=?";

    private static final String SQL_UPDATE_FAG_BADDRIVING = "UPDATE CLW_YW_BADDRIVING_T SET FATIGUE_DRIVING_NUM=?,"
            + "FATIGUE_DRIVING_TIME=? WHERE VEHICLE_VIN=? AND TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=?";

    private static final String SQL_FATIGUETIME_BY_DATE = "select END_TIME,vehicle_vin from CLW_YW_HIS_CAL_RECORD_T where vehicle_vin=? " +
    		" and type='0' and CAL_DATE=?";

    private static final String SQL_INSERT_FAG_HIS_CAL_RECORD = "INSERT INTO CLW_YW_HIS_CAL_RECORD_T(vehicle_vin,CAL_DATE,END_TIME,TYPE)"
            + " VALUES (?,?,?,?)";

    private static final String SQL_UPDATE_FAG_HIS_CAL_RECORD = "UPDATE CLW_YW_HIS_CAL_RECORD_T SET END_TIME=? WHERE vehicle_vin=? " +
    		" and type='0' and CAL_DATE=?";

    /**
     * 通过ID（车辆VIN）查询历史中间表中的该车记录
     * @param vehicVin 车辆VIN
     * @return 历史中间表中的该车记录
     */
    @SuppressWarnings("unchecked")
    public VehicHistoryTime getHistoryTimeById(String vehicVin) {
        List<VehicHistoryTime> list = jdbcTemplate.query(SQL_GET_HISTORY_TIME_BY_ID,
                new Object[] { vehicVin }, ParameterizedBeanPropertyRowMapper
                        .newInstance(VehicHistoryTime.class));
        if (null == list || 0 == list.size()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 插入或更新一条车辆的疲劳驾驶判断时间
     * @param vehicVin 车辆VIN
     * @param timeBean 疲劳驾驶判断时间
     */
    public void insertOrUpdateHistoryTime(String vehicVin, Date fagTime) {
        VehicHistoryTime bean = getHistoryTimeById(vehicVin);
        if (null == bean) {
            insertHistoryTime(vehicVin, fagTime);
        } else {
            jdbcTemplate.update(SQL_UPDATE_FAG_TIME_OF_HISTORY, new Object[] { fagTime, vehicVin });
        }
    }

    /**
     * 插入某车辆的历史判断时间记录
     * @param vehicVin 车辆VIN
     * @param timeBean 疲劳驾驶判断时间
     */
    public void insertHistoryTime(String vehicVin, Date fagTime) {
        jdbcTemplate.update(SQL_INSERT_FAG_HISTORY_TIME, new Object[] { vehicVin, fagTime });
    }

    /**
     * 获取特定时间前的终端流水信息列表
     * @param strVin 车辆VIN
     * @param partDate 分区日期
     * @param dateByDiscreHours 特定时间
     * @return 特定时间前的终端流水信息列表
     */
    @SuppressWarnings("unchecked")
    public List<RealTimeRecord> getRecordList(String strVin, Date partDate, Date dateByDiscreHours) {
//        String SQL_GET_VEHIC_RECORD_LIST = "select ID,TERMINAL_ID,VEHICLE_VIN,SIM_CARD_NUMBER,CREATE_TIME,"
//                + "to_char(UTC_TIME,'yyyy-mm-dd hh24:mi:ss') UTC_TIME,to_char(TERMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TERMINAL_TIME,"
//                + "GPS_VALID,LATITUDE,LONGITUDE,ELEVATION,DIRECTION,GPS_SPEEDING,SPEEDING,ON_OFF,SOS,OVERSPEED_ALERT,FATIGUE_ALERT,"
//                + "GPS_ALERT,SHOW_SPEED_ALERT,DRIVER_ID,DRIVER_LICENSE,ENGINE_ROTATE_SPEED,MILEAGE,OIL_INSTANT,OIL_PRESSURE,"
//                + "TORQUE_PERCENT,FIRE_UP_STATE,POWER_STATE,BATTERY_VOLTAGE,GPS_STATE,EXT_VOLTAGE,IMG_PROCESS,OIL_TOTAL,E_WATER_TEMP,"
//                + "E_TORQUE,QUAD_ID_TYPE,ROUTE_INFO,MEG_RESP_ID,MEG_ID,MEG_TYPE,MEG_INFO,MEG_SEQ,RATIO,GEARS,EEC_APP,"
//                + "VEHICLE_SPEED,PULSE_MILEAGE "
//                + " from CLW_YW_TERMINAL_RECORD_T partition(TERMINAL_RECORD_"
//                + DateUtil.changeDateTo8String(partDate)
//                + ") where TERMINAL_TIME<=? and vehicle_vin=?      order by terminal_time asc  ";
        String SQL_GET_VEHIC_RECORD_LIST = "select VEHICLE_VIN,to_char(TERMINAL_TIME,'yyyy-mm-dd hh24:mi:ss') TERMINAL_TIME,"
            + "LATITUDE,LONGITUDE,SPEEDING,ENGINE_ROTATE_SPEED,FATIGUE_ALERT"
            + " from CLW_YW_TERMINAL_RECORD_T partition(TERMINAL_RECORD_"
            + DateUtil.changeDateTo8String(partDate)
            + ") where TERMINAL_TIME<=? and vehicle_vin=?      order by terminal_time asc  ";

        List<RealTimeRecord> list = jdbcTemplate.query(SQL_GET_VEHIC_RECORD_LIST, new Object[] {
                dateByDiscreHours, strVin }, ParameterizedBeanPropertyRowMapper
                .newInstance(RealTimeRecord.class));
        return list;
    }
    
    /**
     * 更新某辆车某天的疲劳驾驶统计信息
     * @param strVin 车辆VIN
     * @param date 日期
     * @param totalFagDriveNum 疲劳驾驶总次数
     * @param totalFagDriveTime 疲劳驾驶总时间
     * @return 更新结果
     */
    public int updateFagDriveSyncData(String strVin, String date, int totalFagDriveNum,
            long totalFagDriveTime) {
        return jdbcTemplate.update(SQL_UPDATE_FAG_BADDRIVING, new Object[] { totalFagDriveNum,
                totalFagDriveTime, strVin, date });
    }

    /**
     * 获取某车机某天的疲劳驾驶结束时间
     * @param strVin 车辆VIN
     * @param date 日期
     * @return 疲劳驾驶结束时间
     */
    @SuppressWarnings("unchecked")
    public HisCalRecord getVehicFatigueTimeByDate(String strVin, String date) {
        List<HisCalRecord> list = jdbcTemplate.query(SQL_FATIGUETIME_BY_DATE, new Object[] {
                strVin, date }, ParameterizedBeanPropertyRowMapper.newInstance(HisCalRecord.class));
        if (null == list || 0 == list.size()) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 插入或更新一条疲劳驾驶算法历史记录
     * @param strVin
     * @param strDate
     * @param fatigueTime
     */
    public void insertOrUpdateHisCalRecord(String strVin, String strDate, Date fatigueTime) {
        HisCalRecord bean = getVehicFatigueTimeByDate(strVin, strDate);
        if (null == bean) {
            jdbcTemplate.update(SQL_INSERT_FAG_HIS_CAL_RECORD, new Object[] { strVin, strDate,fatigueTime,"0" });
        } else {
            jdbcTemplate.update(SQL_UPDATE_FAG_HIS_CAL_RECORD, new Object[] { fatigueTime, strVin,strDate });
        }
    }
    
    @SuppressWarnings("unchecked")
	public List<HisOverSpeed> getHisOverSpeed(String strVin,String strDate){
    	String sql = "select t.vehicle_vin,t.speeding,t.driver_id,t.start_time,t.stop_time," +
    			"t.type from clw_yw_overspeed_record_t partition(overspeed_"+strDate+") t " +
    			"where t.vehicle_vin="+strVin;
    	return jdbcTemplate.query(sql, ParameterizedBeanPropertyRowMapper.newInstance(HisOverSpeed.class) );
    }
    
    public void insertOverSpeedInfo(String strVin, String strDate, String string){
    	String sql = "INSERT INTO CLW_YW_MALARMD_T (MALARMD_ID,ALARM_TYPE_ID,VEHICLE_VIN,TEMINAL_TIME," +
    			"WRITE_TIME,ALARM_START_TIME,ALARM_END_TIME, ALARM_TIME,Driver_Id,Maxspeed,RESERVRD1) " +
    			"select f.id,'"+string+"',f.vehicle_vin,f.teminal_time,f.write_time,f.start_time,f.stop_time," +
    			"(f.stop_time-f.start_time)*24*60*60,f.driver_id,f.speeding,f.type from " +
    			"CLW_YW_OVERSPEED_RECORD_T f where to_char(f.teminal_time,'yyyy-mm-dd') = '"+strDate+"' " +
    			"and f.vehicle_vin = '" +strVin+"' and (f.stop_time-f.start_time)*24*60*60 >0";
    	jdbcTemplate.update(sql);
    }
	@SuppressWarnings("unchecked")
    public HashMap getBaddrivingEnd(String date) throws DataAccessException {

        // 获取五分钟记录
        String SQL_BaddrivingDate = "select VEHICLE_VIN, to_char(ALARM_DAY,'yyyy-mm-dd')  ALARM_DAY  "
                + " from clw_yw_baddriving_t  partition(BADDRIVING_"
                + SdateM(date)
                + ") WHERE TO_CHAR(ALARM_DAY,'YYYY-MM-DD')=? AND (END_TIME is not NULL)";

        List<BaddrivingDate> baddrivingDate = jdbcTemplate.query(SQL_BaddrivingDate,
                new Object[] { date }, ParameterizedBeanPropertyRowMapper
                        .newInstance(BaddrivingDate.class));	
        HashMap bmap = new HashMap();
        BaddrivingDate bd;
        for(int i=0;i<baddrivingDate.size();i++){
        	bd= baddrivingDate.get(i);
        	bmap.put(bd.getVEHICLE_VIN(), bd);
        }
        return bmap;
    }  
}
