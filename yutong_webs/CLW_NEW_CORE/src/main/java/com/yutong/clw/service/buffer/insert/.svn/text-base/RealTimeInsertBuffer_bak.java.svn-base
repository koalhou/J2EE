package com.yutong.clw.service.buffer.insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import com.yutong.clw.beans.cl.TerminalBean;
import com.yutong.clw.config.Config;
import com.yutong.clw.config.Constant;
import com.yutong.clw.dao.ICommonDAO;
import com.yutong.clw.nio.msg.value.Up_InfoContent;
import com.yutong.clw.quartz.managers.cachemanager.TerminalCacheManager;
import com.yutong.clw.service.BuildSQL;
import com.yutong.clw.service.buffer.error.ErrorBuffer;
import com.yutong.clw.sysboot.SpringBootStrap;
import com.yutong.clw.utils.AccountUtil;
import com.yutong.clw.utils.StringUtil;

public class RealTimeInsertBuffer_bak implements Runnable{

	private static Logger log = LoggerFactory.getLogger(RealTimeInsertBuffer_bak.class);

    private static final RealTimeInsertBuffer_bak dbBuffer = new RealTimeInsertBuffer_bak();
    
    private static final String NAME = "<RealTimeInsertBuffer>";

    private Queue<Up_InfoContent> sqlQueue;

    private ICommonDAO commonDAO;

    private InsertThreadPool pool = InsertThreadPool.instance();
    
    private boolean shutdownFlag = true; 

    private RealTimeInsertBuffer_bak() {
        sqlQueue = new LinkedList<Up_InfoContent>();
        commonDAO = (ICommonDAO) SpringBootStrap.getInstance().getBean("commonDAO");
        shutdownFlag = false;
    }

    public static RealTimeInsertBuffer_bak getInstance() {
        return dbBuffer;
    }

    /**
     * 向队列中加入一条sql
     * @param sql
     */
    public synchronized void add(Up_InfoContent sql) {
        sqlQueue.offer(sql);
    }

    /**
     * 向队列中加入一批sql
     * @param sqlList
     */
    public synchronized void addAll(List<Up_InfoContent> sqlList) {
        sqlQueue.addAll(sqlList);
    }

    /**
     * 向队列中加入一批sql
     * @param sqlList
     */
    public synchronized void add(List<Up_InfoContent> sqlArray) {
        for (Up_InfoContent sql : sqlArray) {
            sqlQueue.offer(sql);
        }
    }

    private static String sql = "insert into CLW_YW_TERMINAL_RECORD_T (ID, TERMINAL_ID, VEHICLE_VIN, SIM_CARD_NUMBER, "
						+ "CREATE_TIME, TERMINAL_TIME, GPS_VALID,UTC_TIME, LATITUDE, LONGITUDE, ELEVATION, DIRECTION, "
						+ "GPS_SPEEDING, SPEEDING, ON_OFF, SOS, OVERSPEED_ALERT, FATIGUE_ALERT, GPS_ALERT, "
						+ "SHOW_SPEED_ALERT, DRIVER_ID, DRIVER_LICENSE, ENGINE_ROTATE_SPEED, MILEAGE, OIL_INSTANT, "
						+ "OIL_PRESSURE, TORQUE_PERCENT, FIRE_UP_STATE, POWER_STATE, BATTERY_VOLTAGE, GPS_STATE, "
						+ "EXT_VOLTAGE, IMG_PROCESS, OIL_TOTAL, E_WATER_TEMP, E_TORQUE, QUAD_ID_TYPE, ROUTE_INFO, "
						+ "MEG_RESP_ID, MEG_ID, MEG_TYPE, MEG_INFO, MEG_SEQ, RATIO, GEARS, EEC_APP, VEHICLE_SPEED, "
						+ "PULSE_MILEAGE, ALARM_BASE_INFO,ALARM_EXT_INFO,STAT_INFO,VEH_EXT_INFO,E_RUN_TIME,"
						+ "OIL_TEMPERATURE,COLDER_TEMPERATURE,AIR_PRESSURE,AIR_INFLOW_TPR,ALERT_ID,OVERLOAD_FLAG,STU_NUM,SITE_ID,ROUTE_ID,INSTANCE_PERCENT"
						+ ",CURRENT_TEACHER,SPEED_SOURCE_SETTING,TRIP_ID,CHARACTER_OEFFICIENT_STATUS,DEVICE_FAULT_LIST) values(" 
						+ "?,?,?,?,sysdate,to_date(?,'yymmddhh24miss'),?,to_date(?,'yymmddhh24miss'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_number(?),to_number(?),?,?,?,to_number(?),?,?)";
    
    class ExeSqlRunner implements Runnable {
        private List<Up_InfoContent> sqls;

        public ExeSqlRunner(List<Up_InfoContent> sqls) {
            this.sqls = sqls;
        }

        public void run() {
            try {
                log.info(NAME+"开始将终端流水缓冲队列中的" + sqls.size() + "个sql入库");
                BatchPreparedStatementSetter pss = new BatchPreparedStatementSetter() {
                	 TerminalCacheManager cache = TerminalCacheManager.getInstance();
                     TerminalBean bean = null;
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Up_InfoContent urt = sqls.get(i);
						bean = cache.getValue(urt.getTerminalId());
						if(bean!=null){
							ps.setString(1, urt.getId());
							ps.setString(2, bean.getTerminal_id());
							ps.setString(3, urt.getTerminalId());
							ps.setString(4, bean.getSim_card_number());
							ps.setString(5, urt.getTerminal_time());
							ps.setString(6, (urt.getGps_valid() != null && !urt.getGps_valid().equals(""))?urt.getGps_valid():"");
							ps.setString(7, (urt.getUtc_time()!=null&&!urt.getUtc_time().equals("")&& urt.getGps_valid().equals("1"))?urt.getUtc_time():"");
							ps.setString(8, (urt.getLatitude() != null && !urt.getLatitude().equals(""))?urt.getLatitude():"");
							ps.setString(9, (urt.getLongitude() != null && !urt.getLongitude().equals(""))?urt.getLongitude():"");
							ps.setString(10, (urt.getElevation() != null && !urt.getElevation().equals(""))?urt.getElevation():"");
							ps.setString(11, (urt.getDirection() != null && !urt.getDirection().equals(""))?urt.getDirection():"");
							if (urt.getGps_valid() != null && !urt.getGps_valid().equals("")
									&& urt.getGps_valid().equals("1")) {
								if (urt.getGps_speeding() != null
										&& !urt.getGps_speeding().equals("")) {
									if(!urt.getGps_speeding().equals("FFFF")){
										ps.setString(12, (!urt.getGps_speeding().trim().equals("0.0")?urt.getGps_speeding().trim():"0"));
									}else{
										ps.setString(12,"0");
									}
								} else {
									ps.setString(12,"0");
								}
							}else{
								ps.setString(12,"0");
							}
							
							ps.setString(13, (urt.getSpeeding() != null && !urt.getSpeeding().equals(""))?urt.getSpeeding():"");
							ps.setString(14, (urt.getOn_off() != null && !urt.getOn_off().equals(""))?urt.getOn_off():"");
							ps.setString(15, (urt.getSos() != null && !urt.getSos().equals(""))?urt.getSos():"");
							ps.setString(16, (urt.getOverspeed_alert() != null && !urt.getOverspeed_alert().equals(""))?urt.getOverspeed_alert():"");
							ps.setString(17, (urt.getFatigue_alert() != null && !urt.getFatigue_alert().equals(""))?urt.getFatigue_alert():"");
							ps.setString(18, (urt.getGps_alert() != null && !urt.getGps_alert().equals(""))?urt.getGps_alert():"");
							ps.setString(19, (urt.getShow_speed_alert() != null	&& !urt.getShow_speed_alert().equals(""))?urt.getShow_speed_alert():"");
							if(urt.getDriver_id()!=null&&!urt.getDriver_id().equals("")){
								if(!urt.getDriver_id().equals("FFFFFFFF")){
									ps.setString(20, String.valueOf(Integer.parseInt(urt.getDriver_id(),16)));
								}else{
									ps.setString(20,Constant.F4);
								}
							}else{
								ps.setString(20,"");
							}
							ps.setString(21, (urt.getDriver_license() != null && !urt.getDriver_license().equals(""))?urt.getDriver_license():"");
							ps.setString(22, (urt.getEngine_rotate_speed() != null	&& !urt.getEngine_rotate_speed().equals(""))?urt.getEngine_rotate_speed():"");
							ps.setString(23, (urt.getMileage() != null && !urt.getMileage().equals(""))?urt.getMileage():"");
							ps.setString(24, (urt.getOil_instant() != null && !urt.getOil_instant().equals(""))?urt.getOil_instant():"");
							ps.setString(25, (urt.getOil_pressure() != null && !urt.getOil_pressure().equals(""))?urt.getOil_pressure():"");
							ps.setString(26, (urt.getTorque_percent() != null && !urt.getTorque_percent().equals(""))?urt.getTorque_percent():"");
							ps.setString(27, (urt.getFire_up_state() != null && !urt.getFire_up_state().equals(""))?urt.getFire_up_state():"");
							ps.setString(28, (urt.getPower_state() != null && !urt.getPower_state().equals(""))?urt.getPower_state():"");
							ps.setString(29, (urt.getBattery_voltage() != null && !urt.getBattery_voltage().equals(""))?urt.getBattery_voltage():"");
							ps.setString(30, (urt.getGps_state() != null && !urt.getGps_state().equals(""))?urt.getGps_state():"");
							ps.setString(31, (urt.getExt_voltage() != null && !urt.getExt_voltage().equals(""))?urt.getExt_voltage():"");
							ps.setString(32, (urt.getImg_process() != null && !urt.getImg_process().equals(""))?urt.getImg_process():"");
							ps.setString(33, (urt.getOil_total() != null && !urt.getOil_total().equals(""))?urt.getOil_total():"");
							ps.setString(34, (urt.getE_water_temp() != null && !urt.getE_water_temp().equals(""))?urt.getE_water_temp():"");
							ps.setString(35, (urt.getE_torque() != null && !urt.getE_torque().equals(""))?urt.getE_torque():"");
							ps.setString(36, (urt.getQuad_id_type() != null && !urt.getQuad_id_type().equals(""))?urt.getQuad_id_type():"");
							ps.setString(37, (urt.getRoute_info() != null && !urt.getRoute_info().equals(""))?urt.getRoute_info():"");
							ps.setString(38, (urt.getMeg_resp_id() != null && !urt.getMeg_resp_id().equals(""))?urt.getMeg_resp_id():"");
							ps.setString(39, (urt.getMeg_id() != null && !urt.getMeg_id().equals(""))?urt.getMeg_id():"");
							ps.setString(40, (urt.getMeg_type() != null && !urt.getMeg_type().equals(""))?urt.getMeg_type():"");
							ps.setString(41, (urt.getMeg_info() != null && !urt.getMeg_info().equals(""))?urt.getMeg_info():"");
							ps.setString(42, (urt.getMeg_seq() != null && !urt.getMeg_seq().equals(""))?urt.getMeg_seq():"");
							if (urt.getSpeeding() != null && !urt.getSpeeding().equals("")
									&& !urt.getSpeeding().equals(Constant.F4)) {
								String ratio = AccountUtil.accountRatio(urt.getTerminalId(), urt
										.getSpeeding(), urt.getEngine_rotate_speed());
								String gears = AccountUtil.accountGears(urt.getTerminalId(), urt
										.getSpeeding(), urt.getEngine_rotate_speed());
								if (ratio != null && !ratio.equals("")) {
									ps.setString(43, ratio);
								} else {
									ps.setString(43,"");
								}
								if (gears != null && !gears.equals("")) {
									if (gears.equals("12")) {
										gears = "R";
									}
									ps.setString(44, gears);
								} else {
									ps.setString(44, "");
								}
							} else {
								if (urt.getVin_speed() != null && !urt.getVin_speed().equals("")
										&& !urt.getVin_speed().equals(Constant.F4)) {
									String ratio = AccountUtil.accountRatio(urt.getTerminalId(),
											urt.getVin_speed(), urt.getEngine_rotate_speed());
									String gears = AccountUtil.accountGears(urt.getTerminalId(),
											urt.getVin_speed(), urt.getEngine_rotate_speed());
									if (ratio != null && !ratio.equals("")) {
										ps.setString(43, ratio);
									} else {
										ps.setString(43,"");
									}
									if (gears != null && !gears.equals("")) {
										if (gears.equals("12")) {
											gears = "R";
										}
										ps.setString(44, gears);
									} else {
										ps.setString(44, "");
									}
								} else {
									ps.setString(43,"");
									ps.setString(44, "");
								}
							}
							ps.setString(45, (urt.getEcc_app() != null && !urt.getEcc_app().equals(""))?urt.getEcc_app():"");
							ps.setString(46, (urt.getVin_speed() != null && !urt.getVin_speed().equals(""))?urt.getVin_speed():"");
							ps.setString(47, (urt.getPulse_mileage() != null && !urt.getPulse_mileage().equals(""))?urt.getPulse_mileage():"");
							ps.setString(48, (urt.getAlarm_state() != null && !urt.getAlarm_state().equals(""))?urt.getAlarm_state():"");
							ps.setString(49, (urt.getXcstate() != null && !urt.getXcstate().equals(""))?urt.getXcstate():"");
							ps.setString(50, (urt.getStatus_bit() != null && !urt.getStatus_bit().equals("")) ?urt.getStatus_bit():"");
							ps.setString(51, (urt.getXcononff() != null && !urt.getXcononff().equals(""))?urt.getXcononff():"");
							ps.setString(52, (urt.getEnginetime() != null && !urt.getEnginetime().equals(""))?urt.getEnginetime():"");
							ps.setString(53, (urt.getEngineoiltemperature() != null && !urt.getEngineoiltemperature().equals(""))?urt.getEngineoiltemperature():"");
							ps.setString(54, (urt.getEnginecoolanttemperature() != null	&& !urt.getEnginecoolanttemperature().equals(""))?urt.getEnginecoolanttemperature():"");
							ps.setString(55, (urt.getAirinlettemperature() != null && !urt.getAirinlettemperature().equals(""))?urt.getAirinlettemperature():"");
							ps.setString(56, (urt.getBarometricpressure() != null && !urt.getBarometricpressure().equals(""))?urt.getBarometricpressure():"");
							ps.setString(57, (urt.getAlarm_seq()!=null&&!urt.getAlarm_seq().equals(""))?urt.getAlarm_seq():"");
							if(Constant.isstartMemcache.equals("1")&&Constant.getMemcachedClient().connectState()){
								Object overload = (Object)Constant.getMemcachedClient().getObject(urt.getTerminalId()+Constant.OVERLOAD);
								if(overload!=null&&!overload.equals("")){
									ps.setString(58, (String)overload);
								}else{
									ps.setString(58, "");
								}
							}else{
								String overload = (String)Constant.ytbsendcmdMap.get(urt.getTerminalId()+Constant.OVERLOAD);
								if(overload!=null&&!overload.equals("")){
									ps.setString(58,overload);
								}else{
									ps.setString(58, "");
								}
							}
							ps.setString(59, (urt.getStu_num()!=null&&!urt.getStu_num().equals(""))?String.valueOf(Integer.parseInt(urt.getStu_num(),16)):"");
							ps.setString(60, (urt.getSite_id()!=null&&!urt.getSite_id().equals("")&&!urt.getSite_id().equals("FFFFFFFF"))?String.valueOf(Integer.parseInt(urt.getSite_id(), 16)):"");
							ps.setString(61, (urt.getRoute_id()!=null&&!urt.getRoute_id().equals("")&&!urt.getRoute_id().equals("FFFFFFFF"))?String.valueOf(Integer.parseInt(urt.getRoute_id(), 16)):"");
							ps.setString(62, (urt.getDrivingper()!=null&&!urt.getDrivingper().equals(""))?String.valueOf(Integer.parseInt(urt.getDrivingper(),16)):"");
							if(urt.getCur_tea()!=null&&!urt.getCur_tea().equals("")){
								if(!urt.getCur_tea().equals("FFFFFFFF")){
									ps.setString(63,String.valueOf(Integer.parseInt(urt.getCur_tea(),16)));
								}else{
									ps.setString(63,Constant.F4);
								}
							}else{
								ps.setString(63,"");
							}
							ps.setString(64, (urt.getSpeed_source_setting()!=null&&!urt.getSpeed_source_setting().equals(""))?String.valueOf(Integer.parseInt(urt.getSpeed_source_setting(), 16)):"");
							ps.setString(65, (urt.getTrip_id()!=null&&!urt.getTrip_id().equals("")&&!urt.getTrip_id().equals("FFFFFFFF"))?String.valueOf(Integer.parseInt(urt.getTrip_id(),16)):"");
							ps.setString(66, (urt.getCharacter_oeffocient_status()!=null&&!urt.getCharacter_oeffocient_status().equals(""))?urt.getCharacter_oeffocient_status():"");
							ps.setString(67, (urt.getDevice_default_list()!=null&&!urt.getDevice_default_list().equals(""))?urt.getDevice_default_list():"");
						}
					}
					
					public int getBatchSize() {
						return sqls.size();
					}
				};
                // 批量入库
                commonDAO.batchUpdate(sql,pss);
                log.info(NAME+"已成功将终端流水缓冲队列中的" + sqls.size() + "个sql入库！");
            } catch (DataAccessException e) {
                log.error(NAME+"终端流水缓冲队列批量入库时出现数据库异常："+e);
                for(Up_InfoContent urt:sqls) {
                	ErrorBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUp_RealTimeSql(urt, urt.getId()));
                }
                e.printStackTrace();
            } catch (Exception e) {
                log.error(NAME+"终端流水缓冲队列批量入库时出现系统异常："+e);
                for(Up_InfoContent urt:sqls) {
                	ErrorBuffer.getInstance().add(BuildSQL.getInstance().buildInsertUp_RealTimeSql(urt, urt.getId()));
                }
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("static-access")
	public void run() {
        MDC.put("session", "[" + StringUtil.getLogRadomStr() + "]");
        MDC.put("modulename", "[DBBuf]");
        while(!shutdownFlag){
        	List<Up_InfoContent> sqls = getSqlsFromQueue();
            if (null == sqls || 0 == sqls.size()) {
                log.debug(NAME+"终端流水缓冲队列中暂时没有数据！");
                try { 
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDBQueueIsNull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"终端流水缓冲队列处理在休眠时出现异常，" + e);
                }
                continue;
            }
            ExeSqlRunner runner = new ExeSqlRunner(sqls);
            if (!pool.start(runner, pool.HIGH_PRIORITY)) {
                log.info(NAME+"用于执行终端流水数据入库的线程池已满！将该批待执行sql重新放入缓存中，并休眠"
                        + Config.props.getProperty("sleepTimeWhenDBThreadPoolFull") + "毫秒!");
                add(sqls);
                try {
                    Thread.sleep(Long.parseLong(Config.props.getProperty("sleepTimeWhenDBThreadPoolFull")));
                } catch (InterruptedException e) {
                    log.error(NAME+"终端流水缓冲队列处理在休眠时出现异常，" + e);
                }
            }else{
//            	log.info(NAME+"用于执行DB数据入库的线程正常启动！");
            }
            log.warn(NAME+"当前入库队列大小为:" + sqlQueue.size());
        }      
//        log.warn(NAME+"数据库缓冲队列处理终止！");
    }

    private List<Up_InfoContent> getSqlsFromQueue() {
    	
        int curQueueSize = sqlQueue.size();
        if (curQueueSize > 0) {
            int count = 0;
            if (curQueueSize <= Integer.parseInt(Config.props.getProperty("countOfExeSqlPerTime"))) { // 不够或刚够一页数据
                count = curQueueSize;
            } else { //多于一页数据
                count = Integer.parseInt(Config.props.getProperty("countOfExeSqlPerTime"));
            }
            List<Up_InfoContent> sqls = new ArrayList<Up_InfoContent>();
            
            synchronized (this) {
                for (int i = 0; i < count; i++) {
                    sqls.add(sqlQueue.poll());
                }
            }
            
            log.info(NAME+"从终端流水缓冲队列中取出" + count + "个sql语句。");
            return sqls;

        } else {
            log.debug(NAME+"终端流水缓冲队列目前为空！");
            return null;
        }
    }

    public void shutdown() {
        log.info("<sqlQueue>开始执行线程池关闭操作");
        shutdownFlag = true;
        
        pool.shutdown();
        log.info("<sqlQueue>sqlQueue","线程池关闭结束！");
    }

    
    
}
