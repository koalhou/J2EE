/*******************************************************************************
 * @(#)CompetitionLiveAction.java 2011-3-14
 *
 * Copyright 2011 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.directwebremoting.WebContextFactory;
import org.springframework.util.StringUtils;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.Alarm;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.Enter_Info_bean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.MsgBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.PhotoInfoBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.ServerPointBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.SiteBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TabCount;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalPointInfoBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.XCRouteBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.XS_CHECK_RECORD_Bean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.itsmanage.oilmanage.carrun.domain.RunOilRecord;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.AlarmManage;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.1 $ 2011-3-14 下午02:28:52
 */
public class GpsDwrData extends PaginationAction {

    protected final Logger log = Logger.getLogger(getClass());

    SendCommandClient sendCommandClient;

    private Service service;

    private VehcileInfo vehcileInfo;

    /** 车辆列表 **/
    private List < VehcileInfo > vehcList;

    private Alarm alarm;

    private List < Alarm > alarmList;

    private TerminalViBean terminalViBean;

    private ServerPointBean serverPointBean;

    private UserInfo userInfo;

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public ServerPointBean getServerPointBean() {
        return serverPointBean;
    }

    public void setServerPointBean(ServerPointBean serverPointBean) {
        this.serverPointBean = serverPointBean;
    }

    public TerminalViBean getTerminalViBean() {
        return terminalViBean;
    }

    public void setTerminalViBean(TerminalViBean terminalViBean) {
        this.terminalViBean = terminalViBean;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    public List < Alarm > getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List < Alarm > alarmList) {
        this.alarmList = alarmList;
    }

    public VehcileInfo getVehcileInfo() {
        return vehcileInfo;
    }

    public void setVehcileInfo(VehcileInfo vehcileInfo) {
        this.vehcileInfo = vehcileInfo;
    }

    public List < VehcileInfo > getVehcList() {
        return vehcList;
    }

    public void setVehcList(List < VehcileInfo > vehcList) {
        this.vehcList = vehcList;
    }

    /**
     *pageNo 页码，从 1 开始 pageSize 每页的记录数
     */
    public Map getVehcileList(String enterId, String userid, int pageNo,
            int pageSize) throws Exception {
        Integer recordCount = 0;
        List data = null;
        // 获得总记录数
        TerminalViBean terminalViBean = new TerminalViBean();

        if (enterId != null && !enterId.equals("")) {
            terminalViBean.setENTERPRISE_ID(enterId);

            data = service.getObjects("GPSNEW.getInfos", terminalViBean);
            log.info("getVehcileList's 车辆数：" + data.size());
        }

        // gpsUtil gpsUtil = new gpsUtil();
        // data = gpsUtil.getpost(data);

        Map resultMap = new HashMap < String, Object >();
        resultMap.put("data", data);
        // resultMap.put("recordCount", recordCount);
        return resultMap;
    }

    /**
     * 实时页面搜索
     * @param ln
     * @param sim
     * @param vin
     * @param entid
     * @return
     */
    public Map getSelectDInfoList(String ln, String sim, String vin,
            String entid) {
        // 获得总记录数
        TerminalViBean terminalViBean = new TerminalViBean();
        terminalViBean.setENTERPRISE_ID(entid);
        terminalViBean.setVEHICLE_LN(ln);
        terminalViBean.setVEHICLE_VIN(vin);
        terminalViBean.setCELLPHONE(sim);

        Integer recordCount = 0;
        List data = null;
        try {

            data = service.getObjects("GPSNEW.getSelectDInfos", terminalViBean);
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            data = null;
        }
        log.info("getSelectDInfoList 's data length:" + data.size());
        // gpsUtil gpsUtil = new gpsUtil();
        // data = gpsUtil.getpost(data);

        Map resultMap = new HashMap < String, Object >();
        resultMap.put("data", data);
        // resultMap.put("recordCount", recordCount);
        return resultMap;
    }

    /**
     * 根据车牌号查询车辆信息 实时监控车辆查询调用了此方法
     * @param ln
     * @param orid
     * @param utype
     * @param userid
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map getVehcileByLn(String ln, String orid, int pageNo, int pageSize) {

        // 获得总记录数
        TerminalViBean terminalViBean = new TerminalViBean();
        terminalViBean.setENTERPRISE_ID(orid);
        terminalViBean.setVEHICLE_LN(ln);
        terminalViBean.setVEHICLE_VIN(ln);
        terminalViBean.setCELLPHONE(ln);

        Integer recordCount = 0;
        List data = null;
        try {

            data = service.getObjects("GPSNEW.getInfos", terminalViBean);
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            data = null;
        }
        log.info("getVehcileByLn 's data length:" + data.size());
        // gpsUtil gpsUtil = new gpsUtil();
        // data = gpsUtil.getpost(data);

        Map resultMap = new HashMap < String, Object >();
        resultMap.put("data", data);
        // resultMap.put("recordCount", recordCount);
        return resultMap;

    }

    /**
     * 监控孩子页面加载孩子位置
     * @param userid
     * @return
     */
    public List < TerminalViBean > getChildGpsInfo(String userid) {
        log.info("监控孩子信息加载：" + userid);

        List < TerminalViBean > data = null;
        // 获得总记录数
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("USER_ID", userid);

        try {
            data = service.getObjects("GPSNEW.getChildInfoByPid", map);

            if (data != null && data.size() > 0) {
                List < TerminalViBean > newlist = new ArrayList();
                for (int i = 0; i < data.size(); i++) {

                    TerminalViBean tv = data.get(i);

                    if (tv.getTERMINAL_TIME() == null
                            || tv.getTERMINAL_TIME().equals("")) {

                        continue;
                    } else if (tv.getLONGITUDE() == null
                            || tv.getLONGITUDE().equals("")
                            || tv.getLONGITUDE().equals("FFFF")
                            || tv.getLATITUDE() == null
                            || tv.getLATITUDE().equals("")
                            || tv.getLATITUDE().equals("FFFF")) {

                        continue;
                    }

                    else if (tv.getSTAT_INFO() != null
                            && !tv.getSTAT_INFO().equals("")) {

                        AlarmRecode ar = new AlarmRecode();
                        String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!acc.equals("0")
                                && !timeBound(tv.getTERMINAL_TIME())
                                || (acc.equals("0") && !timeBound30(tv
                                        .getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getVSS_FLAG() != null
                                && !tv.getVSS_FLAG().equals("")) {
                            if (tv.getVSS_FLAG().equals("1")
                                    || tv.getVSS_FLAG().equals("2")) {
                                tv.setColor("g");// 灰色
                            }
                        } else if (tv.getALARM_ID() != null
                                && !tv.getALARM_ID().equals("")) {
                            if (tv.getALARM_ID().equals("73")
                                    || tv.getALARM_ID().equals("74")
                                    || tv.getALARM_ID().equals("79")
                                    || tv.getALARM_ID().equals("80")) {
                                tv.setColor("r");// 红色
                            }
                        }
                    } else {
                        AlarmRecode ar = new AlarmRecode();
                        // String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!timeBound(tv.getTERMINAL_TIME())
                                || (!timeBound30(tv.getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getVSS_FLAG() != null
                                && !tv.getVSS_FLAG().equals("")) {
                            if (tv.getVSS_FLAG().equals("1")
                                    || tv.getVSS_FLAG().equals("2")) {
                                tv.setColor("g");// 灰色
                            }
                        } else if (tv.getALARM_ID() != null
                                && !tv.getALARM_ID().equals("")) {
                            if (tv.getALARM_ID().equals("73")
                                    || tv.getALARM_ID().equals("74")
                                    || tv.getALARM_ID().equals("79")
                                    || tv.getALARM_ID().equals("80")) {
                                tv.setColor("r");// 红色
                            }
                        }
                    }

                    newlist.add(tv);

                }

                long time3 = System.currentTimeMillis();
                gpsUtil gpsUtil = new gpsUtil();
                newlist = gpsUtil.getpost(newlist);
                long time4 = System.currentTimeMillis();
                log.info("偏移耗时：" + (time4 - time3));

                return newlist;

            }

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        log.info("车辆孩子数：" + data.size());

        return data;

    }

    /**
     * 根据vin查车辆信息 单选车辆标点时调用此方法
     * @param vin
     * @return
     */

    public List < TerminalViBean > getVehcileByVin(String vin) {
        if (vin.trim().endsWith(",")) {

            vin = vin.substring(0, vin.length() - 1);
            log.info("批量VIN：" + vin);
        }
    	

        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        try {
        	//获取企业的告警设置，没有则为私有
        	HttpSession session = WebContextFactory.get().getSession();
        	UserInfo user = (UserInfo)session.getAttribute(Constants.USER_SESSION_KEY);
        	String redAids=(String) service.getObject("AlarmManage.listShow", user.getEntiID());
        	if(!StringUtils.hasText(redAids)){
        		redAids="40,32,10,67,66,09,13,65,64,25,69,70,71,26,68";
        	}
            // List < TerminalViBean > list =

            List < TerminalViBean > list = service.getObjects(
                    "GPSNEW.getOneVehcileByVin", map);
            long time5 = System.currentTimeMillis();
            if (list != null && list.size() > 0) {

                List < TerminalViBean > newlist = new ArrayList();

                for (int i = 0; i < list.size(); i++) {

                    TerminalViBean tv = list.get(i);
                    if (tv.getTERMINAL_TIME() == null
                            || tv.getTERMINAL_TIME().equals("")) {

                        continue;
                    } else if (tv.getLONGITUDE() == null
                            || tv.getLONGITUDE().equals("")
                            || tv.getLONGITUDE().equals("FFFF")
                            || tv.getLATITUDE() == null
                            || tv.getLATITUDE().equals("")
                            || tv.getLATITUDE().equals("FFFF")) {

                        continue;
                    } else if (tv.getSTAT_INFO() != null
                            && !tv.getSTAT_INFO().equals("")) {

                        AlarmRecode ar = new AlarmRecode();
                        String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!acc.equals("0")
                                && !timeBound(tv.getTERMINAL_TIME())
                                || (acc.equals("0") && !timeBound30(tv
                                        .getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getALARM_BASE_INFO() != null
                                && !tv.getALARM_BASE_INFO().equals("")) {

                            if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } else if (tv.getALARM_EXT_INFO() != null
                                && !tv.getALARM_EXT_INFO().equals("")) {
                            if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } 
                        
                        if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                            tv.setSPEEDING(tv.getGPS_SPEEDING());
                        }
                    } else {
                        AlarmRecode ar = new AlarmRecode();
                        // String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!timeBound(tv.getTERMINAL_TIME())
                                || (!timeBound30(tv.getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getALARM_BASE_INFO() != null
                                && !tv.getALARM_BASE_INFO().equals("")) {

                            if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } else if (tv.getALARM_EXT_INFO() != null
                                && !tv.getALARM_EXT_INFO().equals("")) {
                            if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } 
                        if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                            tv.setSPEEDING(tv.getGPS_SPEEDING());
                        }
                    }

                    newlist.add(tv);

                }
                long time6 = System.currentTimeMillis();
                log.info("数据整理耗时：" + (time6 - time5) + "整理后数据长度："
                        + newlist.size());

                long time3 = System.currentTimeMillis();
                gpsUtil gpsUtil = new gpsUtil();
                newlist = gpsUtil.getpost(newlist);
                long time4 = System.currentTimeMillis();
                log.info("偏移耗时：" + (time4 - time3));

                long time7 = System.currentTimeMillis();
                List showlist = new ArrayList();
                for (int i = 0; i < newlist.size(); i++) {
                    TerminalViBean tvb = newlist.get(i);
                    TerminalPointInfoBean tb = new TerminalPointInfoBean();
                    tb.setVEHICLE_VIN(tvb.getVEHICLE_VIN());
                    tb.setVEHICLE_LN(tvb.getVEHICLE_LN());
                    tb.setLONGITUDE(tvb.getLONGITUDE());
                    tb.setLATITUDE(tvb.getLATITUDE());
                    tb.setColor(tvb.getColor());
                    tb.setDIRECTION(tvb.getDIRECTION());
                    tb.setSPEEDING(tvb.getSPEEDING());
                    showlist.add(tb);
                }
                long time8 = System.currentTimeMillis();
                log.info("这里新数据对象耗时：" + (time8 - time7));
                return showlist;
            } else {
                return null;
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 单车实时监控获取数据
     * @param vin
     * @return
     */
    public List < TerminalViBean > getRealVehcileByVin(String vin) {
        if (vin.trim().endsWith(",")) {

            vin = vin.substring(0, vin.length() - 1);
            log.info("重点监控VIN：" + vin);
        }
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        try {
        	//获取企业的告警设置，没有则为私有
        	HttpSession session = WebContextFactory.get().getSession();
        	UserInfo user = (UserInfo)session.getAttribute(Constants.USER_SESSION_KEY);
        	String redAids=(String) service.getObject("AlarmManage.listShow", user.getEntiID());
        	if(!StringUtils.hasText(redAids)){
        		redAids="40,32,10,67,66,09,13,65,64,25,69,70,71,26,68";
        	}
        	
            TerminalViBean tt = (TerminalViBean) service.getObject(
                    "GPSNEW.getRealVehcileByVin", map);
            // List<TerminalViBean> list =
            // service.getObjects("GPSNEW.getOneVehcileByVin", map);

            if (tt != null) {

                List < TerminalViBean > newlist = new ArrayList();

                TerminalViBean tv = tt;
                if (tv.getTERMINAL_TIME() == null
                        || tv.getTERMINAL_TIME().equals("")) {

                    return null;
                } else if (tv.getLONGITUDE() == null
                        || tv.getLONGITUDE().equals("")
                        || tv.getLONGITUDE().equals("FFFF")
                        || tv.getLATITUDE() == null
                        || tv.getLATITUDE().equals("")
                        || tv.getLATITUDE().equals("FFFF")) {

                    return null;
                } else if (tv.getSTAT_INFO() != null
                        && !tv.getSTAT_INFO().equals("")) {

                    AlarmRecode ar = new AlarmRecode();
                    String acc = ar.stat_info(tv.getSTAT_INFO());

                    if (!acc.equals("0")
                            && !timeBound(tv.getTERMINAL_TIME())
                            || (acc.equals("0") && !timeBound30(tv
                                    .getTERMINAL_TIME()))) {
                        // log.info("时间输出："+ tv.getTERMINAL_TIME());//2011-07-13
                        // 10:45:41.0
                        tv.setColor("g");// 灰色
                    } else if (tv.getALARM_BASE_INFO() != null
                            && !tv.getALARM_BASE_INFO().equals("")) {

                        if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                .equals("")) {
                        	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                        		tv.setColor("r");// 红色
                        	}
                        }
                    } else if (tv.getALARM_EXT_INFO() != null
                            && !tv.getALARM_EXT_INFO().equals("")) {
                        if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                .equals("")) {
                        	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                        		tv.setColor("r");// 红色
                        	}
                        }
                    } 
                    
                    if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                        tv.setSPEEDING(tv.getGPS_SPEEDING());
                    }
                    tv.setDingwei_stat(ar.dingwei_stat_info(tv.getSTAT_INFO()));
                    tv.setSTAT_INFO(ar.stat_info(tv.getSTAT_INFO()));
                } else {
                    AlarmRecode ar = new AlarmRecode();
                    // String acc = ar.stat_info(tv.getSTAT_INFO());

                    if (!timeBound(tv.getTERMINAL_TIME())) {
                        // || (!timeBound30(tv.getTERMINAL_TIME()))) {
                        // log.info("时间输出："+ tv.getTERMINAL_TIME());//2011-07-13
                        // 10:45:41.0
                        tv.setColor("g");// 灰色
                    } else if (tv.getALARM_BASE_INFO() != null
                            && !tv.getALARM_BASE_INFO().equals("")) {

                        if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                .equals("")) {
                        	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                        		tv.setColor("r");// 红色
                        	}
                        }
                    } else if (tv.getALARM_EXT_INFO() != null
                            && !tv.getALARM_EXT_INFO().equals("")) {
                        if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                .equals("")) {
                        	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                        		tv.setColor("r");// 红色
                        	}
                        }
                    }
                    if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                        tv.setSPEEDING(tv.getGPS_SPEEDING());
                    }
                    tv.setDingwei_stat("1");
                    tv.setSTAT_INFO("1");
                }
                newlist.add(tv);

                long time3 = System.currentTimeMillis();
                gpsUtil gpsUtil = new gpsUtil();
                newlist = gpsUtil.getpost(newlist);
                long time4 = System.currentTimeMillis();
                log.info("偏移耗时：" + (time4 - time3));

                return newlist;
            } else {
                return null;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据批量vin进行批量查车 车辆全选时调用了此方法 页面定时刷新车辆位置时调用了此方法
     * @param vins
     * @return
     */
    public List < TerminalViBean > getVehcileByVinS(String vins) {
        //MDC.put("modulename", "[cheliangMonitor]");
        log.info("getVehcileByVinS start");
        log.info("批量选车VIN：" + vins);

        if (vins.trim().endsWith(",")) {

            vins = vins.substring(0, vins.length() - 1);
            log.info("批量VIN：" + vins);
        }
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VINS", vins);

        try {
        	//获取企业的告警设置，没有则为私有
        	HttpSession session = WebContextFactory.get().getSession();
        	UserInfo user = (UserInfo)session.getAttribute(Constants.USER_SESSION_KEY);
        	String redAids=(String) service.getObject("AlarmManage.listShow", user.getEntiID());
        	if(!StringUtils.hasText(redAids)){
        		redAids="40,32,10,67,66,09,13,65,64,25,69,70,71,26,68";
        	}
        	
            long time1 = System.currentTimeMillis();
            // List < TerminalViBean > list =

            List < TerminalViBean > list = service.getObjects(
                    "GPSNEW.getVehcileByVinSelectAll", map);
            long time2 = System.currentTimeMillis();
            log.info("返回结果长度：" + list.size() + "；耗时：" + (time2 - time1));

            long time5 = System.currentTimeMillis();
            if (list != null && list.size() > 0) {

                List < TerminalViBean > newlist = new ArrayList();

                for (int i = 0; i < list.size(); i++) {

                    TerminalViBean tv = list.get(i);
                    if (tv.getTERMINAL_TIME() == null
                            || tv.getTERMINAL_TIME().equals("")) {

                        continue;
                    } else if (tv.getLONGITUDE() == null
                            || tv.getLONGITUDE().equals("")
                            || tv.getLONGITUDE().equals("FFFF")
                            || tv.getLATITUDE() == null
                            || tv.getLATITUDE().equals("")
                            || tv.getLATITUDE().equals("FFFF")) {

                        continue;
                    } else if (tv.getSTAT_INFO() != null
                            && !tv.getSTAT_INFO().equals("")) {

                        AlarmRecode ar = new AlarmRecode();
                        String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!acc.equals("0")
                                && !timeBound(tv.getTERMINAL_TIME())
                                || (acc.equals("0") && !timeBound30(tv
                                        .getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getALARM_BASE_INFO() != null
                                && !tv.getALARM_BASE_INFO().equals("")) {

                            if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } else if (tv.getALARM_EXT_INFO() != null
                                && !tv.getALARM_EXT_INFO().equals("")) {
                            if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } 
                        
                        if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                            tv.setSPEEDING(tv.getGPS_SPEEDING());
                        }
                    } else {

                        AlarmRecode ar = new AlarmRecode();
                        // String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!timeBound(tv.getTERMINAL_TIME())
                                || (!timeBound30(tv.getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getALARM_BASE_INFO() != null
                                && !tv.getALARM_BASE_INFO().equals("")) {

                            if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } else if (tv.getALARM_EXT_INFO() != null
                                && !tv.getALARM_EXT_INFO().equals("")) {
                            if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } 
                        if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                            tv.setSPEEDING(tv.getGPS_SPEEDING());
                        }
                    }

                    newlist.add(tv);

                }
                long time6 = System.currentTimeMillis();
                log.info("数据整理耗时：" + (time6 - time5) + "整理后数据长度："
                        + newlist.size());

                long time3 = System.currentTimeMillis();
                gpsUtil gpsUtil = new gpsUtil();
                newlist = gpsUtil.getpost(newlist);
                long time4 = System.currentTimeMillis();
                log.info("偏移耗时：" + (time4 - time3));

                long time7 = System.currentTimeMillis();
                List showlist = new ArrayList();
                for (int i = 0; i < newlist.size(); i++) {
                    TerminalViBean tvb = newlist.get(i);
                    TerminalPointInfoBean tb = new TerminalPointInfoBean();
                    tb.setVEHICLE_VIN(tvb.getVEHICLE_VIN());
                    tb.setVEHICLE_LN(tvb.getVEHICLE_LN());
                    tb.setLONGITUDE(tvb.getLONGITUDE());
                    tb.setLATITUDE(tvb.getLATITUDE());
                    tb.setColor(tvb.getColor());
                    tb.setDIRECTION(tvb.getDIRECTION());
                    tb.setSPEEDING(tvb.getSPEEDING());
                    showlist.add(tb);
                }
                long time8 = System.currentTimeMillis();
                log.info("这里新数据对象耗时：" + (time8 - time7));
                log.info("getVehcileByVinS end");
                return showlist;
            } else {
                log.info("getVehcileByVinS end");
                return null;
            }
            
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.info("getVehcileByVinS error end");
            return null;
        }

    }

    /**
     * 加载气球信息
     */
    public List < TerminalViBean > returnTipInfo(String vin) {
        //MDC.put("modulename", "[cheliangMonitor]");
        log.info("returnTipInfo start");
        log.info("vin OldTIP气球信息加载:" + vin);
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        try {
            // List<TerminalViBean> list = service.getObjects(
            // "GPSNEW.getTipInfoByVin", map);
            // TerminalViBean tv = list.get(0);
            TerminalViBean list = (TerminalViBean) service.getObject(
                    "GPSNEW.getTipInfoByVin", map);
            AlarmRecode ar = new AlarmRecode();
            if (list != null) {
                //设置空调、加热器状态
                if(list.getVEH_EXT_INFO() !=null && !list.getVEH_EXT_INFO().equals("")){
                     char no22=list.getVEH_EXT_INFO().charAt(22); //空调状态
                     char no18=list.getVEH_EXT_INFO().charAt(18); //加热器状态
                     if("1".equals(""+no18))
                         list.setHeater_state("1");
                     else
                         list.setHeater_state("0");
                     
                     if("1".equals(""+no22))
                         list.setAir_state("1");
                     else
                         list.setAir_state("0");
                }
                // TipInfoBean tb = new TipInfoBean();
                if(checkACCandTime(list.getSTAT_INFO(),list.getTERMINAL_TIME())){

                    //2013-11-20 添加判断dvr在线状态 根据此状态判断btn及视频播放
                    if(list.getVEH_EXT_INFO() !=null && !list.getVEH_EXT_INFO().equals("")){
                        if(ar.dvr_info(list.getVEH_EXT_INFO()).equals("1")){//dvr状态为在线
                            list.setDvrState("1");
                        }
                        else{
                            list.setDvrState("0");
                        }
                    }else
                        list.setDvrState("0");
                    
                    list.setColor("b");
                    if(list.getVEH_EXT_INFO() !=null && !list.getVEH_EXT_INFO().equals("")){
                        if(ar.dvr_info(list.getVEH_EXT_INFO()).equals("1")){
                            list.setVEH_EXT_INFO("1");
                        }
                        else{
                            list.setVEH_EXT_INFO("0");
                        }
                    }
                    else{
                        list.setVEH_EXT_INFO("1");
                    }
                    
                    //if (list.getSTAT_INFO() != null && !list.getSTAT_INFO().equals("")) {
                        if (ar.dingwei_stat_info(list.getSTAT_INFO()).equals("0")) {
                            list.setDingwei_stat("0");

                        } else {
                            list.setDingwei_stat("1");
                        }
                        
                        if (ar.stat_info(list.getSTAT_INFO()).equals("0")) {
                            list.setSTAT_INFO("0");
                        } else {
                            list.setSTAT_INFO("1");
                        }
                        
                        
                    //}
                    //else{
                    //  list.setSTAT_INFO("1");
                    //  list.setDingwei_stat("1");
                    //}
                    
                }
                else{
                    list.setColor("g");
                    list.setVEH_EXT_INFO("0");
                    list.setDingwei_stat(ar.dingwei_stat_info(list.getSTAT_INFO()));
                    list.setSTAT_INFO(ar.stat_info(list.getSTAT_INFO()));
                    list.setDvrState("0");
                    
                }
                if(list.getSPEED_SOURCE_SETTING() != null && list.getSPEED_SOURCE_SETTING().equals("1")){
                    list.setSPEEDING(list.getGPS_SPEEDING());
                }
                
                /*if (list.getSTAT_INFO() != null
                        && !list.getSTAT_INFO().equals("")) {
                    
                    String stat_info = list.getSTAT_INFO();
                    
                    if (ar.stat_info(stat_info).equals("0")) {
                        list.setSTAT_INFO("0");
                    } else {
                        list.setSTAT_INFO("1");
                    }

                    if (ar.dingwei_stat_info(stat_info).equals("0")) {
                        list.setDingwei_stat("0");

                    } else {
                        list.setDingwei_stat("1");
                    }
                    
                    if(ar.dvr_info(list.getVEH_EXT_INFO()).equals("1")){
                        list.setVEH_EXT_INFO("1");
                    }
                    else{
                        list.setVEH_EXT_INFO("0");
                    }
                } else {
                    list.setSTAT_INFO("1");
                    list.setDingwei_stat("1");
                    list.setVEH_EXT_INFO("0");
                }*/

                List < TerminalViBean > l = new ArrayList();
                l.add(list);
                log.info("returnTipInfo end");
                return l;

            } else {
                log.info("returnTipInfo end");
                return null;
            }

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            log.info("returnTipInfo error end");
            return null;
        }
    }
    
    /**
     * 新的异步加载tip气泡窗体信息
     * @param vin
     * @param lon 地图偏转后坐标
     * @param lat
     * @return
     */
    public List < TerminalViBean > returnTipInfoNew(String vin,String lon,String lat) {
        //MDC.put("modulename", "[cheliangMonitor-TipInfo]");
        log.info("returnTipInfoNew start");
        log.info("vin:" + vin);
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        try {
            
            TerminalViBean list = (TerminalViBean) service.getObject(
                    "GPSNEW.getTipInfoByVin", map);
            AlarmRecode ar = new AlarmRecode();
            if (list != null) {
                //空调状态/加热器状态
                if(list.getVEH_EXT_INFO() !=null && !list.getVEH_EXT_INFO().equals("")){
                     char no22=list.getVEH_EXT_INFO().charAt(22); //空调状态
                     char no18=list.getVEH_EXT_INFO().charAt(18); //加热器状态
                     if("1".equals(""+no18))
                         list.setHeater_state("1");
                     else
                         list.setHeater_state("0");
                     
                     if("1".equals(""+no22))
                         list.setAir_state("1");
                     else
                         list.setAir_state("0");
                 }
                if(checkACCandTime(list.getSTAT_INFO(),list.getTERMINAL_TIME())){
                    //2013-11-20 添加判断dvr在线状态 根据此状态判断btn及视频播放
                    if(list.getVEH_EXT_INFO() !=null && !list.getVEH_EXT_INFO().equals("")){
                        if(ar.dvr_info(list.getVEH_EXT_INFO()).equals("1")){//dvr状态为在线 
                            list.setDvrState("1");
                        }
                        else{
                            list.setDvrState("0");
                        }
                    }else
                        list.setDvrState("0");
                    list.setColor("b");
                        if(list.getVEH_EXT_INFO() !=null && !list.getVEH_EXT_INFO().equals("")){
                            if(ar.dvr_info(list.getVEH_EXT_INFO()).equals("1")){
                                list.setVEH_EXT_INFO("1");
                            }
                            else{
                                list.setVEH_EXT_INFO("0");
                            }
                        }else{
                              list.setVEH_EXT_INFO("1");
                        }
                    
                        if (ar.dingwei_stat_info(list.getSTAT_INFO()).equals("0")) {
                            list.setDingwei_stat("0");

                        } else {
                            list.setDingwei_stat("1");
                        }
                        
                        if (ar.stat_info(list.getSTAT_INFO()).equals("0")) {
                            list.setSTAT_INFO("0");
                        } else {
                            list.setSTAT_INFO("1");
                        }
                    
                }
                else{
                    list.setColor("g");
                    list.setVEH_EXT_INFO("0");
                    list.setDingwei_stat(ar.dingwei_stat_info(list.getSTAT_INFO()));
                    list.setSTAT_INFO(ar.stat_info(list.getSTAT_INFO()));
                    list.setDvrState("0");
                }
                
                if(list.getSPEED_SOURCE_SETTING() != null && list.getSPEED_SOURCE_SETTING().equals("1")){
                    list.setSPEEDING(list.getGPS_SPEEDING());
                }
                    
                list.setLONGITUDE(lon);
                list.setLATITUDE(lat);
                
                List < TerminalViBean > l = new ArrayList();
                l.add(list);
                log.info("returnTipInfoNew end");
                return l;

            } else {
                log.info("returnTipInfoNew end");
                return null;
            }

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            log.info("returnTipInfoNew error end");
            return null;
        }
    }
    
    /**
     * 判断车辆是否在线
     * @param acc
     * @param time
     * @return
     */
    private boolean checkACCandTime(String stat,String time){
        
        
        if (stat != null && !stat.equals("")) {

            AlarmRecode ar = new AlarmRecode();
            String acc = ar.stat_info(stat);

            if ((!acc.equals("0")
                    && !timeBound(time))
                    || (acc.equals("0") && !timeBound30(time))) {
                // log.info("时间输出："+
                // tv.getTERMINAL_TIME());//2011-07-13
                // 10:45:41.0
                return false;// 灰色
            } 
            
        } else {

            AlarmRecode ar = new AlarmRecode();
            // String acc = ar.stat_info(tv.getSTAT_INFO());

            if (!timeBound(time) || (!timeBound30(time))) {
                // log.info("时间输出："+
                // tv.getTERMINAL_TIME());//2011-07-13
                // 10:45:41.0
                return false;// 灰色
            } 
        }
        
        return true;
        
    }

    /**
     * 加载孩子气球信息
     */
    public List < TerminalViBean > returnChildTipInfo(String vin) {
        log.info("vin:" + vin);
        String[] obj = vin.split(",");

        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", obj[0]);
        map.put("STU_ID", obj[1]);
        try {

            TerminalViBean list = (TerminalViBean) service.getObject(
                    "GPSNEW.getChildTipByPid", map);

            List < TerminalViBean > l = new ArrayList();
            l.add(list);
            return l;

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            return null;
        }
    }

 // 计算时间差
    private boolean timeBound240s(String ttime) {
        if (ttime == null || ttime == "") {
            return false;
        }
        long nowtime = System.currentTimeMillis();
        // alert("2--"+nowtime);
        // var nowdate1 = Date.parse(new Date(nowtime.replace(/-/g, "/")));
        // alert("3--"+nowtime);
        SimpleDateFormat inputFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = inputFormat.parse(ttime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            d = null;
        }

        long date2 = d.getTime();// Date.parse(new
        // Date(timeoptval(ttime).replace(/-/g, "/")));
        // alert("4--"+date2);
        if ((nowtime - date2) / 1000 >= 300) {
            return false;
        }
        return true;

    }
    
    // 计算时间差
    private boolean timeBound(String ttime) {
        if (ttime == null || ttime == "") {
            return false;
        }
        long nowtime = System.currentTimeMillis();
        // alert("2--"+nowtime);
        // var nowdate1 = Date.parse(new Date(nowtime.replace(/-/g, "/")));
        // alert("3--"+nowtime);
        SimpleDateFormat inputFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = inputFormat.parse(ttime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            d = null;
        }

        long date2 = d.getTime();// Date.parse(new
        // Date(timeoptval(ttime).replace(/-/g, "/")));
        // alert("4--"+date2);

        if ((nowtime - date2) / 1000 >= 300) {
            return false;
        }
        return true;

    }

    /**
     * 时间范围30分钟
     * @param ttime
     * @return
     */
    private boolean timeBound30(String ttime) {
        if (ttime == null || ttime == "") {
            return false;
        }
        long nowtime = System.currentTimeMillis();
        // alert("2--"+nowtime);
        // var nowdate1 = Date.parse(new Date(nowtime.replace(/-/g, "/")));
        // alert("3--"+nowtime);
        SimpleDateFormat inputFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = inputFormat.parse(ttime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            d = null;
        }

        long date2 = d.getTime();// Date.parse(new
        // Date(timeoptval(ttime).replace(/-/g, "/")));
        // alert("4--"+date2);

        if ((nowtime - date2) / 1000 >= 1800) {
            return false;
        }
        return true;

    }

    /**
     * 线路查车
     * @param routename 线路名
     * @param orid 组织id
     * @param pageNo
     * @param pageSize
     * @return
     */

    public Map < String, Object > getVehcileByRouteName(String routename,
            String orid, int pageNo, int pageSize) {
        log.info("线路查车");
        log.info("组织id：" + orid);
        // 获得总记录数
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("ROUTE_NAME", routename);
        map.put("ORGANIZATION_ID", orid);
        Integer recordCount = 0;
        List < TerminalViBean > data = new ArrayList();
        try {
            // recordCount = (Integer)

            // 总页数
            int pageCount = (int) Math.ceil(recordCount.doubleValue()
                    / pageSize);
            log.info("总页数：" + pageCount);
            // pageNO 超界则取接近的边界值
            pageNo = (pageNo > pageCount) ? pageCount : (pageNo < 1 ? 1
                    : pageNo);
            int skip = (pageNo - 1) * pageSize;
            log.info("skip:" + skip);
            // TerminalViBean vehcileInfo = new TerminalViBean();

            data = service.getObjects("GPS.getVehcileByRouteNameNew", map);
            log.info("data.length:" + data.size());
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            data = null;
        }

        gpsUtil gpsUtil = new gpsUtil();
        data = gpsUtil.getpost(data);

        Map < String, Object > resultMap = new HashMap < String, Object >();
        resultMap.put("data", data);
        resultMap.put("recordCount", recordCount);
        return resultMap;

    }

    /**
     * 查询范围内的点
     * @param minx
     * @param miny
     * @param maxx
     * @param maxy
     * @return
     */

    @SuppressWarnings("unchecked")
    public List < ServerPointBean > getSevicepointlook(String minx,
            String miny, String maxx, String maxy) {

        ServerPointBean serverPointBean = new ServerPointBean();
        serverPointBean.setMIN_X(minx);
        serverPointBean.setMIN_Y(miny);
        serverPointBean.setMAX_X(maxx);
        serverPointBean.setMAX_Y(maxy);

        try {
            List < ServerPointBean > data = service.getObjects(
                    "GPS.getSevicepointlook", serverPointBean);

            if (data != null && data.size() > 0) {
                // System.out.println(data[0].getPOI_NAME());
                return data;
            } else {
                return null;
            }

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 报警列表
     */
    public Map alarmList(String enterprise) throws Exception {
        Alarm alarm = new Alarm();
        List data = null;
        alarm.setEnterprise_id(enterprise);
        data = service.getObjects("GPS.getAlarmInfos", alarm);
        Map resultMap = new HashMap < String, Object >();
        resultMap.put("data", data);
        return resultMap;
    }

    // add by jinp start
    /**
     * 去掉重复数据
     * @return
     */
    private List < TerminalViBean > trimMultiData(List < TerminalViBean > list) {

        if (list.isEmpty()) {
            return list;
        }

        Map < String, String > map = new HashMap < String, String >();

        List < TerminalViBean > pointsList = new ArrayList < TerminalViBean >();

        pointsList.add(list.get(0));

        map.put(list.get(0).getLATITUDE(), list.get(0).getLONGITUDE());

        for (int i = 0; i < list.size(); i++) {
            TerminalViBean temp = list.get(i);
            if (map.get(temp.getLATITUDE()) != null
                    && map.get(temp.getLATITUDE()).length() > 0) {
                // 存在重复点
                continue;
            } else {
                pointsList.add(temp);
                map.put(temp.getLATITUDE(), temp.getLONGITUDE());
            }
        }

        return pointsList;

    }

    private static final int POINT_MAX = 1500;

    /**
     * 路径关键点获取
     * @param list
     * @return
     */
    private List < TerminalViBean > calcKeypoints(
            List < TerminalViBean > pointsList) {

        List < TerminalViBean > ret = new ArrayList < TerminalViBean >();

        int len = pointsList.size();

        if (len > 0) {
            int len1 = pointsList.size();
            // 抽样采点（POINT_MAX抽取）
            for (int i = 0; i < len1;) {
                if (i == 0) {
                    TerminalViBean data = pointsList.get(0);
                    ret.add(data);
                    i = i
                            + ((double) len1 / POINT_MAX > 1 ? (int) Math
                                    .ceil((double) len1 / POINT_MAX) : 1);
                    continue;
                } else {
                    TerminalViBean data = pointsList.get(i);
                    ret.add(data);
                    i = i
                            + ((double) len1 / POINT_MAX > 1 ? (int) Math
                                    .ceil((double) len1 / POINT_MAX) : 1);

                    if (i >= len1) {
                        if ((i - ((double) len1 / POINT_MAX > 1 ? (int) Math
                                .ceil((double) len1 / POINT_MAX) : 1)) == (len1 - 1)) {
                            break;
                        }
                        TerminalViBean last = pointsList.get(len1 - 1);
                        ret.add(last);
                        break;
                    }
                }
            }
        }
        return ret;
    }

    // add by jinp end

    // 加载学生刷卡信息
    public List < XS_CHECK_RECORD_Bean > getXS_Check_record(String vin,
            String stuid, String starttime, String endtime) {
        log.info("getXS_Check_record-vin:" + vin + ";stuid:" + stuid
                + ";start:" + starttime + ";end:" + endtime);
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        map.put("STU_ID", stuid);
        // map.put("ARRATABNAME", "TERMINAL_RECORD_" + tabTime);
        map.put("STARTTIME", starttime);
        map.put("ENDTIME", endtime);

        List < XS_CHECK_RECORD_Bean > list = null;

        try {
            list = service.getObjects("GPSNEW.getChildCheckErrorByPid", map);
            log.info(list.size());
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return list;
    }

    /**
     * 轨迹回访
     */
    public Map getVehcileLineList(String vin, String starttime, String endtime) {
        //MDC.put("modulename", "[cheliangMonitor-guji]");
        log.info("getVehcileLineList start");
        log.info("轨迹点集合查询");
        log.info("vin:" + vin + ";start:" + starttime + ";end:" + endtime);

        String start = starttime.substring(0, 10);
        String end = endtime.substring(0, 10);
        log.info("start:" + start + ";end:" + end);
        try{
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date sDate = sdf.parse(starttime);
        Date eDate = sdf.parse(endtime);
        boolean flag = eDate.before(sDate);
        if(flag){
            Calendar   calendar = new GregorianCalendar(); 
            calendar.setTime(eDate); 
            calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
            eDate=calendar.getTime();   //这个时间就是日期往后推一天的结果 
            endtime = sdf.format(eDate);
        } 
        }catch(Exception ee){
            ee.printStackTrace();
        }
        // if (start.equals(end)) {
        Map map = new HashMap < String, Object >();

        String tabTime = StringdateToStringdate(start);

        log.info("tabTime:" + tabTime);
        map.put("VEHICLE_VIN", vin);
        map.put("ARRATABNAME", "TERMINAL_RECORD_" + tabTime);
        map.put("STARTTIME", starttime);
        map.put("ENDTIME", endtime);

        List < TerminalViBean > list = null;
        try {

            list = service.getObjects("GPSNEW.getTerminalRecordByVin", map);

            // 过滤重复的点
            // list = trimMultiData(list);

            log.info("bitlook info length:" + list.size());

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long time5 = System.currentTimeMillis();

        List < TerminalViBean > newlist = new ArrayList();
        // List<TerminalViBean> errorlist = new ArrayList();
        if (list != null && list.size() > 0) {
            AlarmRecode ar = new AlarmRecode();
            String stat_door = "0";
            for (int i = 0; i < list.size(); i++) {

                TerminalViBean tv = list.get(i);
                // TerminalViBean ev = new TerminalViBean();
                // ev = ObjectToObject(tv,ev);

                // ev = tv;

                if (tv.getTERMINAL_TIME() == null
                        || tv.getTERMINAL_TIME().equals("")) {

                    continue;
                } else if (tv.getLONGITUDE() == null
                        || tv.getLONGITUDE().equals("")
                        || tv.getLONGITUDE().equals("FFFF")
                        || tv.getLATITUDE() == null
                        || tv.getLATITUDE().equals("")
                        || tv.getLATITUDE().equals("FFFF")) {

                    continue;
                }
                
                
                if (tv.getSTAT_INFO() != null && !tv.getSTAT_INFO().equals("")) {

                    String stat = ar.dingwei_stat_info(tv.getSTAT_INFO());
                    String fire = ar.stat_info(tv.getSTAT_INFO());
                    String door = ar.stat_info_door(tv.getSTAT_INFO());
                    
                    
                    if((door.equals("1")) && (stat_door.equals("0")) ){
                        tv.setColor("r");
                        tv.setALARM_TYPE_NAME("开门");
                        stat_door = door;
                        tv.setStat_info_door("1");
                    }else{
                        stat_door = door;
                        tv.setStat_info_door("0");
                    }

                    
                   
                    if (!stat.equals("0")) {
                        tv.setDingwei_stat("1");
                    } else {
                        tv.setDingwei_stat("0");
                    }

                    if (fire.equals("0")) {
                        tv.setSTAT_INFO("0");// guan
                    } else {
                        tv.setSTAT_INFO("1");// kai
                    }
                    
                    
                    
                } else {
                    tv.setDingwei_stat("1");
                    tv.setSTAT_INFO("1");
                }
         
                log.info(tv.getColor());
                newlist.add(tv);

            }
        }

        gpsUtil gpsUtil = new gpsUtil();//**************
        log.info("vin:" + vin+"====newlist gps start =====");
        newlist = gpsUtil.getpost(newlist);//**********
        log.info("vin:" + vin+"====newlist gps end =====");
        // errorlist = gpsUtil.getpost(errorlist);

        log.info("bitlook opt info length:" + newlist.size());
        // log.info("bitlook opt errorlist length:" + errorlist.size());

        //加载告警信息
        List < TerminalViBean > errlist = null;
        try {
            log.info("vin:" + vin+"====alarmList start =====");
            errlist = service.getObjects("GPSNEW.getGuiJiAlarmList", map);
            log.info("vin:" + vin+"====alarmList end =====:" + errlist.size());
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("vin:" + vin+"====alarm gps start =====");
        errlist = gpsUtil.getpost(errlist);
        log.info("vin:" + vin+"====alarm gps end =====");
        Map resultMap = new HashMap < String, Object >();
        resultMap.put("data", newlist);
        resultMap.put("errdata", errlist);
        // resultMap.put("dataE", errorlist);
        log.info("getVehcileLineList end");
        return resultMap;
        // }
        /*
         * else { String starttime1 = starttime; String endtime1 = start +
         * " 23:59:59"; String tabtime1 = "TERMINAL_RECORD_" +
         * StringdateToStringdate(start); String starttime2 = end + " 00:00:00";
         * String endtime2 = endtime; String tabtime2 = "TERMINAL_RECORD_" +
         * StringdateToStringdate(end); Map map = new HashMap<String, Object>();
         * map.put("VEHICLE_VIN", vin); map.put("ARRATABNAME1", tabtime1);
         * map.put("STARTTIME1", starttime1); map.put("ENDTIME1", endtime1);
         * map.put("ARRATABNAME2", tabtime2); map.put("STARTTIME2", starttime2);
         * map.put("ENDTIME2", endtime2); List<TerminalViBean> list = null; try
         * { list =
         * service.getObjects("GPSNEW.getTerminalRecordUnionByVin",map); //
         * 过滤重复的点 list = trimMultiData(list); log.info("bitlook info length:" +
         * list.size()); } catch (BusinessException e) { // TODO Auto-generated
         * catch block e.printStackTrace(); return null; } List<TerminalViBean>
         * newlist = new ArrayList(); if (list != null && list.size() > 0) {
         * AlarmRecode ar = new AlarmRecode(); for (int i = 0; i < list.size();
         * i++) { TerminalViBean tv = list.get(i); if (tv.getTERMINAL_TIME() ==
         * null || tv.getTERMINAL_TIME().equals("")) { continue; } else if
         * (tv.getLONGITUDE() == null || tv.getLONGITUDE().equals("") ||
         * tv.getLONGITUDE().equals("FFFF") || tv.getLATITUDE() == null ||
         * tv.getLATITUDE().equals("") || tv.getLATITUDE().equals("FFFF")) {
         * continue; } else if (tv.getALARM_BASE_INFO() != null &&
         * !tv.getALARM_BASE_INFO().equals("")) {
         * if(!ar.recode_alarm_base(tv.getALARM_BASE_INFO()).equals("")){
         * tv.setColor("r");// 红色 } }else if(tv.getALARM_EXT_INFO() != null &&
         * !tv.getALARM_EXT_INFO().equals("")){
         * if(!ar.recode_alarm_ext(tv.getALARM_EXT_INFO()).equals("")){
         * tv.setColor("r");// 红色 } }else if(tv.getOVERLOAD_FLAG() != null &&
         * !tv.getOVERLOAD_FLAG().equals("")){
         * if(tv.getOVERLOAD_FLAG().equals("1")){ tv.setColor("r");// 红色 } }
         * newlist.add(tv); } } long time6 = System.currentTimeMillis(); gpsUtil
         * gpsUtil = new gpsUtil(); newlist = gpsUtil.getpost(newlist);
         * log.info("bitlook opt info length:" + newlist.size()); return
         * newlist; }
         */

    }

    private TerminalViBean ObjectToObject(TerminalViBean a, TerminalViBean b) {
        // vin
        b.setVEHICLE_VIN(a.getVEHICLE_VIN());
        // 车辆编码
        b.setVEHICLE_ID(a.getVEHICLE_ID());
        // 车辆内部编码
        b.setVEHICLE_CODE(a.getVEHICLE_CODE());
        // 所属组织结构
        b.setORGANIZATION_ID(a.getORGANIZATION_ID());
        // 所属企业
        b.setENTERPRISE_ID(a.getENTERPRISE_ID());
        // 车牌号
        b.setVEHICLE_LN(a.getVEHICLE_LN());
        //
        b.setUSER_ID(a.getUSER_ID());
        //
        b.setCR_CONFIG_ID(a.getCR_CONFIG_ID());
        // 发动机号
        b.setENGINE_NUMBER(a.getENGINE_NUMBER());
        // 发动机型号
        b.setENGINE_TYPE(a.getENGINE_TYPE());
        // 线路编码
        b.setROUTE_ID(a.getROUTE_ID());
        // 车身颜色
        b.setVEHICLE_COLOR(a.getVEHICLE_COLOR());
        // 载客限员
        b.setLIMITE_NUMBER(a.getLIMITE_NUMBER());
        b.setLATITUDE(a.getLATITUDE());
        b.setLONGITUDE(a.getLONGITUDE());
        b.setSPEEDING(a.getSPEEDING());
        b.setDIRECTION(a.getDIRECTION());
        b.setTERMINAL_TIME(a.getTERMINAL_TIME());// TERMINAL_TIME;
        b.setID(a.getID());// ID;
        b.setOIL_INSTANT(a.getOIL_INSTANT());// OIL_INSTANT;
        b.setENGINE_ROTATE_SPEED(a.getENGINE_ROTATE_SPEED());// ENGINE_ROTATE_SPEED;
        // 驾驶员编号
        b.setDRIVER_ID(a.getDRIVER_ID());// DRIVER_ID;
        // 基本报警状态位
        b.setALARM_BASE_INFO(a.getALARM_BASE_INFO());// ALARM_BASE_INFO;
        // 扩展报警状态位
        b.setALARM_EXT_INFO(a.getALARM_EXT_INFO());// ALARM_EXT_INFO;
        //
        b.setINOUT_FLAG(a.getINOUT_FLAG());// INOUT_FLAG;
        b.setOVERLOAD_FLAG(a.getOVERLOAD_FLAG());// OVERLOAD_FLAG;
        // 车辆状态位码
        b.setSTAT_INFO(a.getSTAT_INFO());// STAT_INFO;
        // 站点编码
        b.setSITE_ID(a.getSITE_ID());// SITE_ID;
        // 当前学生人数
        b.setSTU_NUM(a.getSTU_NUM());// STU_NUM;
        b.setColor(a.getColor());// color = "b";// 标点颜色
        b.setGpsIsExc(true);// gpsIsExc;
        b.setVIDEO_ID(a.getVIDEO_ID());// VIDEO_ID;
        b.setENTER_SHORT_NAME(a.getENTER_SHORT_NAME());// ENTER_SHORT_NAME;
        // 司机名称
        b.setDRIVER_NAME(a.getDRIVER_NAME());// DRIVER_NAME;
        // 线路名称
        b.setROUTE_NAME(a.getROUTE_NAME());// ROUTE_NAME;

        /** 学生刷卡信息 **/
        // 学生编码
        b.setSTU_ID(a.getSTU_ID());// STU_ID;
        // 站点编码
        b.setCHILD_SITE_ID(a.getCHILD_SITE_ID());// CHILD_SITE_ID;
        // 状态：0-上学；1-放学
        b.setSITE_FLAG(a.getSITE_FLAG());// SITE_FLAG;
        // 学生乘车状态：0-上车；1-下车
        b.setVSS_FLAG(a.getVSS_FLAG());// VSS_FLAG;
        // 异常告警ID
        b.setALARM_ID(a.getALARM_ID());// ALARM_ID;
        // 已下车学生人数
        b.setST_DOWM_NUM(a.getST_DOWM_NUM());// ST_DOWM_NUM;
        // 已上车学生人数
        b.setST_UP_NUM(a.getST_UP_NUM());// ST_UP_NUM;
        // 当前车内学生人数
        b.setST_NUM(a.getST_NUM());// ST_NUM ;
        b.setST_LATITUDE(a.getST_LATITUDE());// ST_LATITUDE;
        b.setST_LONGITUDE(a.getST_LONGITUDE());// ST_LONGITUDE;

        /** 学生基本信息 **/
        // 学生卡
        b.setSTU_CARD_ID(a.getSTU_CARD_ID());// STU_CARD_ID;
        // 学号
        b.setSTU_CODE(a.getSTU_CODE());// STU_CODE;
        // 学生姓名
        b.setSTU_NAME(a.getSTU_NAME());// STU_NAME;
        // 性别 0:男 1:女
        b.setSTU_SEX(a.getSTU_SEX());// STU_SEX;
        // 出生年月
        b.setSTU_BIRTH(a.getSTU_BIRTH());// STU_BIRTH;
        // 地址
        b.setSTU_ADDRESS(a.getSTU_ADDRESS());// STU_ADDRESS;
        // 所读学校
        b.setSTU_SCHOOL(a.getSTU_SCHOOL());// STU_SCHOOL;
        // 所在班级
        b.setSTU_CLASS(a.getSTU_CLASS());// STU_CLASS;
        // 备注
        b.setSTU_REMARK(a.getSTU_REMARK());// STU_REMARK;
        // 班主任姓名
        b.setTEACHER_NAME(a.getTEACHER_NAME());// TEACHER_NAME;
        // 班主任联系方式
        b.setTEACHER_TEL(a.getTEACHER_TEL());// TEACHER_TEL;
        // 家长姓名
        b.setRELATIVE_NAME(a.getRELATIVE_NAME());// RELATIVE_NAME;
        // 家长联系方式
        b.setRELATIVE_TEL(a.getRELATIVE_TEL());// RELATIVE_TEL;

        return b;
    }

    private String StringdateToStringdate(String time) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = inputFormat.parse(time);
        } catch (ParseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        SimpleDateFormat inputFormat2 = new SimpleDateFormat("yyyyMMdd");
        String tabTime = inputFormat2.format(d);
        return tabTime;
    }

    /*
     * 视频调用，根据车辆的VIN号查询出视频Id
     */
    public TerminalViBean getVideoCode(String VIN) {
        TerminalViBean tv = new TerminalViBean();
        tv.setVEHICLE_VIN(VIN);// 设置VIN
        try {
            tv = (TerminalViBean) service.getObject("GPS.getVideoByVIN", tv);
            // 打日志
            if (tv != null) {
                log.info("GPS VEHICLE_VIN: " + tv.getVEHICLE_VIN());
                log.info("GPS VIDEO_ID:" + tv.getVIDEO_ID());
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            log.error("query GPS-CLW_JC_TERMINAL_T catch error:" + e);
        }
        return tv;
    }

    /**
     * 线路列表加载时调用此法发 加载线路列表
     */
    public List < XCRouteBean > SelectLinsList(String entid) {
        Map map = new HashMap < String, Object >();

        map.put("route_enterprise_id", entid);

        List < XCRouteBean > list = null;
        try {
            list = service.getObjects("GPSNEW.SelectLinsList", map);
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return list;

    }

    /**
     * 根据线路集合查询车辆
     */
    public Map SelectVInLinId(String lineids, String entid) {
        if (lineids != null && !lineids.equals("")) {
            log.info("线路id集合：" + lineids);

            lineids = lineids.substring(0, lineids.length() - 1);

            Map map = new HashMap < String, Object >();

            map.put("ENTERPRISE_ID", entid);
            map.put("ROUTE_IDS", lineids);
            List < TerminalViBean > list = null;

            try {
                list = service.getObjects("GPSNEW.SelectVbyLinsList", map);
                log.info("线路查车长度：" + list.size());
            } catch (BusinessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
            Map resultMap = new HashMap < String, Object >();
            resultMap.put("data", list);
            return resultMap;

        } else {
            return null;
        }

    }

    public int test() {
        log.info("this moted is run........");
        return 0;

    }

    /**
     * 通用在线与否判断
     * @param vin
     * @param msgid
     * @return
     */
    public String isOnoff(String vin, String msgid) {
        String returnvalue = Constants.IS_ON_LINE;
        log.info("msgid:" + msgid + ";vin:" + vin + ";判断在线情况开始");
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        try {
            TerminalViBean tt = (TerminalViBean) service.getObject(
                    "GPSNEW.getRealVehcileByVin", map);
            if (tt != null) {
                TerminalViBean tv = tt;
                if (tv.getTERMINAL_TIME() == null
                        || tv.getTERMINAL_TIME().equals("")) {
                    returnvalue = Constants.IS_OFF_LINE;
                } else {
                    if (tv.getSTAT_INFO() != null
                            && !tv.getSTAT_INFO().equals("")) {
                        AlarmRecode ar = new AlarmRecode();
                        String acc = ar.stat_info(tv.getSTAT_INFO());
                        if (!acc.equals("0")
                                && !timeBound(tv.getTERMINAL_TIME())
                                || (acc.equals("0") && !timeBound30(tv
                                        .getTERMINAL_TIME()))) {
                            returnvalue = Constants.IS_OFF_LINE;
                        }
                    } else {
                        // 为空情况默认点火状态为开
                        if (!timeBound(tv.getTERMINAL_TIME())) {
                            returnvalue = Constants.IS_OFF_LINE;
                        } else {
                            returnvalue = Constants.IS_ON_LINE;
                        }
                    }
                }
            } else {
                returnvalue = Constants.IS_OFF_LINE;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            returnvalue = Constants.IS_OFF_LINE;
            log.error("msgid:" + msgid + ";returnvalue:" + returnvalue
                    + ";判断点火状态出错:", e);
        }
        log.info("msgid:" + msgid + ";vin:" + vin + ";returnvalue:"
                + returnvalue + ",判断在线情况结束");
        return returnvalue;
    }

    /**
     * 是否下发拍照指令判断
     * @param vin
     * @param batch_id
     * @return
     */
    public String isOnOffPhoto(String vin, String batch_id) {
        String returnvalue = Constants.IS_ON_LINE;
        log.info("batch_id:" + batch_id + ";vin:" + vin + ";判断在线情况开始");
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        try {
            TerminalViBean tt = (TerminalViBean) service.getObject(
                    "GPSNEW.getRealVehcileByVin", map);
            // 判断是否粗存在数据
            if (tt != null) {
                TerminalViBean tv = tt;
                if (tv.getTERMINAL_TIME() == null
                        || tv.getTERMINAL_TIME().equals("")) {
                    // 判断时间是否为空，为空不在线
                    returnvalue = Constants.IS_OFF_LINE;
                } else {
                    // 判断状态位是否为空
                    if (tv.getSTAT_INFO() != null
                            && !tv.getSTAT_INFO().equals("")) {
                        AlarmRecode ar = new AlarmRecode();
                        String acc = ar.stat_info(tv.getSTAT_INFO());
                        if (!acc.equals("0")
                                && !timeBound(tv.getTERMINAL_TIME())
                                || (acc.equals("0") && !timeBound30(tv
                                        .getTERMINAL_TIME()))) {
                            returnvalue = Constants.IS_OFF_LINE;
                        } else {
                            // 状态位非空,且在线,判断点火状态
                            if ("1".equals(acc)) {
                                // 点火状态为开，返回200
                                returnvalue = Constants.IS_ON_LINE;
                            } else {
                                // 点火状态为关，返回8000
                                returnvalue = Constants.IS_OFF_LINE_PHOTO;
                            }
                        }
                    } else {
                        // 状态位为空，默认点火状态为开
                        if (!timeBound(tv.getTERMINAL_TIME())) {
                            //5分钟不报数据为不在线
                            returnvalue = Constants.IS_OFF_LINE;
                        } else {
                            returnvalue = Constants.IS_ON_LINE;
                        }

                    }
                }
            } else {
                // 数据不存在返回不在线
                returnvalue = Constants.IS_OFF_LINE;
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            returnvalue = Constants.IS_OFF_LINE;
            log.error("batch_id:" + batch_id + ";returnvalue:" + returnvalue
                    + ";判断点火状态出错:", e);
        }
        log.info("batch_id:" + batch_id + ";vin:" + vin + ";returnvalue:"
                + returnvalue + ",判断在线情况结束");
        return returnvalue;

    }

    /**
     * 下发消息
     * @param vin
     * @param info 消息内容
     * @param userid 用户ID
     * @param msgtype 消息类型
     * @return
     */
    public String postSendMsg(String vin, String info, String userid,
            String msgtype) {
        //MDC.put("modulename", "[cheliangMonitor-xiaoxi]");
        log.info("postSendMsg start");
        log.info("下发消息");
        String msgid = UUIDGenerator.getUUID32();
        log.info("msgid:" + msgid + ";vin:" + vin + ";info:" + info
                + ";userid:" + userid + ";msgtype:" + msgtype);
        String returnvalue = isOnoff(vin, msgid);
        /*
         * String msgtypes[]=msgtype.split(","); int k=0; for (int
         * i=0;i<msgtypes.length;i++){ k=k^Integer.parseInt(msgtypes[i], 2); }
         * msgtype=Integer.toBinaryString(k);
         */
        if (Constants.IS_ON_LINE.equals(returnvalue)) {
            returnvalue = sendCommandClient.sendSMSCommand(vin, info, userid,
                    msgtype, msgid, msgid);
        }
        log.info("msgid:" + msgid + ";returnvalue:" + returnvalue);
        log.info("postSendMsg end");
        return returnvalue;
        // sendCommandClient.sendPhotoCommand(vehicle_vin, channel_number,
        // pixel, image_quality, userid);
    }

    /**
     * 批量下发消息
     * @param vin
     * @param info
     * @param userid
     * @param msgtype
     * @return
     */
    public String postSendMsgS(String vin, String info, String userid,
            String msgtype) {
        //MDC.put("modulename", "[cheliangMonitor-piliangxiaoxi]");
        log.info("postSendMsgS start");
        log.info("批量消息指令下发");
        String returnvalue = "";
        Map map = new HashMap < String, Object >();
        // String[] vins = vin.split(",");
        /*
         * String msgtypes[] = msgtype.split(","); int k = 0; for (int i = 0; i
         * < msgtypes.length; i++) { k = k ^ Integer.parseInt(msgtypes[i], 2); }
         * msgtype = Integer.toBinaryString(k);
         */
        if (vin.endsWith(",")) {
            vin = vin.substring(0, vin.length() - 1);
        }
        vin = vin.replace(",", "','");
        log.info("postSendMsgS's vin:" + vin);
        map.put("VEHICLE_VINS", vin);
        List < TerminalViBean > list = null;
        try {
            list = service.getObjects("GPSNEW.getVinAndLnInfo", map);
            if (list != null && list.size() > 0) {
                String batch_id = UUIDGenerator.getUUID32();

                for (int i = 0; i < list.size(); i++) {
                    TerminalViBean tv = list.get(i);

                    String msgid = UUIDGenerator.getUUID32();
                    String ssv = isOnoff(tv.getVEHICLE_VIN(), msgid);
                    log.info("msgid:" + msgid + ";vin:" + tv.getVEHICLE_VIN()
                            + ";info:" + info + ";userid:" + userid);
                    if(Constants.IS_ON_LINE.equals(ssv)){
                        returnvalue += tv.getVEHICLE_LN()
                        + "-"
                        + sendCommandClient.sendSMSCommand(tv
                                .getVEHICLE_VIN(), info, userid, msgtype,
                                msgid, batch_id) + ",";
                    }
                    else{
                        returnvalue += tv.getVEHICLE_LN()
                        + "-"
                        + ssv + ",";
                    }
                    
                    log.info("msgid:" + msgid + ";returnvalue:" + returnvalue);

                }
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            log.info(e.getMessage());

        }

        log.info(returnvalue);
        log.info("postSendMsgS end");
        return returnvalue;
        // sendCommandClient.sendPhotoCommand(vehicle_vin, channel_number,
        // pixel, image_quality, userid);
    }

    /**
     * 加载该车最近的3张照片信息
     * @param vin
     * @return
     */
    public List < PhotoInfoBean > initSendPhotoInfo(String vin) {
        //MDC.put("modulename", "[cheliangMonitor-paizhao]");
        log.info("initSendPhotoInfo start");
        log.info("加载照片信息");
        Map map = new HashMap < String, Object >();

        map.put("VEHICLE_VIN", vin);

        List < PhotoInfoBean > list = null;

        try {
            list = service.getObjects("GPSNEW.getPhotoInfoByVIN", map);
            
           // service.getObjectsByPage("GPSNEW.getPhotoInfoByVIN", map, skipResults, maxResults)
            
            log.info("加载照片"+list.size());
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.info("initSendPhotoInfo error end");
            return null;
        }
        log.info("initSendPhotoInfo end");
        return list;
    }

    public boolean SavePhotoInfo(String id, String flag, String remark,String flag_user,String vin,String enterpriseid) {
        //MDC.put("modulename", "[cheliangMonitor-paizhao]");
        log.info("SavePhotoInfo start");
        log.info("保存照片信息");
        Map map = new HashMap();
        map.put("PHOTO_ID", id);
        map.put("PHOTO_FALG", flag);
        map.put("PHOTO_DESC", remark);
        map.put("FLAG_USER", flag_user);

        try {
            int i = service.update("GPSNEW.updatePhotoInfo", map);
            
            if("1".equals(flag)) {
                SendCommandClient sendCommandClient = new SendCommandClient();
                sendCommandClient.sendHttpPhotoFlagXML(flag_user, enterpriseid, vin, id);
            }
            
            if (i > 0) return true;
            log.info("SavePhotoInfo end");
            return false;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.info("SavePhotoInfo error end");
            return false;
        }

    }

    /**
     * 单车车辆拍照
     * @param vehicle_vin
     * @param no 通道号
     * @param pixel 像素
     * @param image_quality 图片质量
     * @param userid 用户ID
     * @param brightness 亮度
     * @param contrast 对比度
     * @param saturation 饱和度
     * @param color 色度
     * @return
     */
    public String postSendPhoto(String vehicle_vin, String no, String pixel,
            String image_quality, String userid, String brightness,
            String contrast, String saturation, String color) {
        //MDC.put("modulename", "[cheliangMonitor-paizhao]");
        log.info("postSendPhoto start");
        log.info("下发拍照指令");
        String batch_id = UUIDGenerator.getUUID32();
        log.info("batch_id:" + batch_id + ";vin:" + vehicle_vin + ";no:" + no
                + ";userid:" + userid + ";pixel:" + pixel + ";image_quality:"
                + image_quality + ";brightness:" + brightness + ";contrast:"
                + contrast + ";saturation:" + saturation + ";color:" + color);

        String returnvalue = isOnOffPhoto(vehicle_vin, batch_id);
        if (Constants.IS_ON_LINE.equals(returnvalue)) {
            String[] str = null;
            if (no.endsWith(",")) {
                String s = no.substring(0, no.length() - 1);
                log.info("拍照通道号：" + s);
                str = s.split(",");
            } else {
                str = no.split(",");
            }

            returnvalue = "";

            for (int i = 0; i < str.length; i++) {
                String submsgid = UUIDGenerator.getUUID32();
                log.info("batch_id:" + batch_id + ",submsgid:" + submsgid);
                String recode = sendCommandClient.sendPhotoCommand(vehicle_vin,
                        str[i], pixel, image_quality, userid, brightness,
                        contrast, saturation, color, submsgid, batch_id);
                log.info("batch_id:" + batch_id + ",submsgid:" + submsgid
                        + ",recode:" + recode);
                returnvalue += str[i] + "-" + recode + ",";
            }
        }
        log.info("batch_id:" + batch_id + ";returnvalue:" + returnvalue);
        log.info("postSendPhoto end");
        return returnvalue;
    }

    /**
     * 多车车辆拍照
     * @param vehicle_vin
     * @param no 通道号
     * @param pixel 像素
     * @param image_quality 图片质量
     * @param userid 用户ID
     * @param brightness 亮度
     * @param contrast 对比度
     * @param saturation 饱和度
     * @param color 色度
     * @return
     */
    public String postSendPhotoList(String vehicle_vin, String no,
            String pixel, String image_quality, String userid,
            String brightness, String contrast, String saturation, String color) {
        //MDC.put("modulename", "[cheliangMonitor-piliangpaizhao]");
        log.info("postSendPhotoList start");
        log.info("批量拍照指令下发");
        String returnvalue = "";
        String batch_id = UUIDGenerator.getUUID32();
        log.info("批量拍照batch_id:" + batch_id + ";vin:" + vehicle_vin + ";no:"
                + no + ";userid:" + userid + ";pixel:" + pixel
                + ";image_quality:" + image_quality + ";brightness:"
                + brightness + ";contrast:" + contrast + ";saturation:"
                + saturation + ";color:" + color);
        // String recode = "7000";
        Map map = new HashMap < String, Object >();

        if (vehicle_vin.endsWith(",")) {
            vehicle_vin = vehicle_vin.substring(0, vehicle_vin.length() - 1);
        }
        vehicle_vin = vehicle_vin.replace(",", "','");
        log.info("postSendPhotoList's vin:" + vehicle_vin);
        map.put("VEHICLE_VINS", vehicle_vin);

        String[] str = null;
        if (no.endsWith(",")) {
            String s = no.substring(0, no.length() - 1);
            log.info("批量拍照通道号：" + s);
            str = s.split(",");
        } else {
            str = no.split(",");
        }
        List < TerminalViBean > list = null;
        try {
            list = service.getObjects("GPSNEW.getVinAndLnInfo", map);
            if (list != null && list.size() > 0) {

                for (int j = 0; j < list.size(); j++) {
                    TerminalViBean tv = list.get(j);

                    for (int i = 0; i < str.length; i++) {
                        String submsgid = UUIDGenerator.getUUID32();
                        log.info("batch_id:" + batch_id + ",submsgid:"
                                + submsgid);
                        String onoff = isOnOffPhoto(tv.getVEHICLE_VIN(), batch_id);
                        if (Constants.IS_ON_LINE.equals(onoff)){
                            
                             String recode = sendCommandClient.sendPhotoCommand(tv
                                     .getVEHICLE_VIN(), str[i], pixel,
                                     image_quality, userid, brightness, contrast,
                                     saturation, color, submsgid, batch_id);
                             log.info("batch_id:" + batch_id + ",submsgid:"
                                     + submsgid + ",recode:" + recode);
                             returnvalue += tv.getVEHICLE_LN() + "-" + str[i] + "-"
                                     + recode + ",";
                        }
                        else{
                            returnvalue += tv.getVEHICLE_LN() + "-" + str[i] + "-"
                            + onoff + ",";
                        }
                       
                    }

                }
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            log.info(e.getMessage());

        }

        /*
         * for (int i = 0; i < str.length; i++) { String submsgid =
         * UUIDGenerator.getUUID32(); log.info("batch_id:" + batch_id +
         * ",submsgid:" + submsgid); String recode =
         * sendCommandClient.sendPhotoCommand(vehicle_vin, str[i], pixel,
         * image_quality, userid, brightness, contrast, saturation, color,
         * submsgid, batch_id); log.info("batch_id:" + batch_id + ",submsgid:" +
         * submsgid + ",recode:" + recode); returnvalue += str[i] + "-" + recode
         * + ","; }
         */

        log.info("batch_id:" + batch_id + ";returnvalue:" + returnvalue);
        log.info("postSendPhotoList end");
        return returnvalue;
    }

    /**
     * 监听
     * @param vehicle_vin
     * @param phonenum 电话号码
     * @param userid
     * @return
     */
    public String postSendPhone(String vehicle_vin, String phonenum,
            String userid) {
        String msgid = UUIDGenerator.getUUID32();
        log.info("msgid:" + msgid + ";vin:" + vehicle_vin + ";phonenum:"
                + phonenum + ";userid:" + userid);
        String returnvalue = isOnoff(vehicle_vin, msgid);
        if (Constants.IS_ON_LINE.equals(returnvalue)) {
            returnvalue = sendCommandClient.sendPhoneCommand(phonenum,
                    vehicle_vin, userid, msgid);
        }
        log.info("msgid:" + msgid + ";returnvalue:" + returnvalue);
        return returnvalue;
    }

    /**
     * 重点监控
     * @param vehicle_vin
     * @param userid
     * @param interval 间隔
     * @param duration 持续时间
     * @return
     */

    public String postKeyMonitor(String vehicle_vin, String userid,
            String interval, String duration) {
        String batch_id = UUIDGenerator.getUUID32();
        String msgid = UUIDGenerator.getUUID32();
        log.info("msgid:" + msgid + ";vin:" + vehicle_vin + ";interval:"
                + interval + ";userid:" + userid + ";duration:" + duration);
        String returnvalue = isOnoff(vehicle_vin, msgid);
        if (Constants.IS_ON_LINE.equals(returnvalue)) {
            returnvalue = sendCommandClient.sendKeyMonitorCommand(vehicle_vin,
                    userid, interval, duration, msgid, batch_id);
        }
        log.info("msgid:" + msgid + ";returnvalue:" + returnvalue);
        return returnvalue;
    }

    /**
     * 获取TAB各项COUNT值
     * @param orgid
     * @return
     */
    public String getTabCount(String orgid) {
        //MDC.put("modulename", "[cheliangMonitor]");
        log.info("getTabCount start");
        log.info("统计告警数");
        StringBuffer countstr = new StringBuffer();
        try {
            AlarmManage alarmmanage = new AlarmManage();
            alarmmanage.setStart_time(DateUtil.getPreNDay(-2));
            alarmmanage.setEnd_time(DateUtil.getCurrentDay() + " 23:59:59");
            alarmmanage.setOrganization_id(orgid);
            TabCount tabcount = (TabCount) service.getObject(
                    "AlarmManage.getTabAlarmCount", alarmmanage);
            countstr.append(tabcount.getTab1count());
            countstr.append(",");
            countstr.append(tabcount.getTab2count());
            countstr.append(",");
            countstr.append(tabcount.getTab3count());
            countstr.append(",");
            countstr.append(tabcount.getTab4count());
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("startTime", DateUtil.getPreNDay(-2));
//            map.put("endTime", DateUtil.getCurrentDay() + " 23:59");
//            map.put("isused", "1");
//            map.put("organization_id", orgid);
//            Integer energyCount = service.getCount("energyAlarm.tabCount", map);
//            countstr.append(",");
//            countstr.append(energyCount.toString());
        } catch (Exception e) {

        }
        log.info("getTabCount end");
        return countstr.toString();
    }
    
    /**
     * 优化TAB-COUNT,暂时不用
     * @param orgid
     * @return
     */
    
    public String getTabCountnew(String orgid){
        String tabcount="0,0,0,0";
        try{
            //AlarmManage alarmmanage = new AlarmManage();
            //alarmmanage.setStart_time(DateUtil.getCurrentBeforeWeek());
            //alarmmanage.setEnd_time(DateUtil.getCurrentDay());
            Map < String, Object > map = new HashMap < String, Object >(5);
            map.put("in_org_id", orgid);
            map.put("in_starttime", DateUtil.getPreNDay(-2));
            map.put("in_endtime", DateUtil.getCurrentDay());
            map.put("out_message", null);
            map.put("out_count", null);
            service.getObject("AlarmManage.show_tab_alarmcount", map);
            if(SUCCESS.equals(map.get("out_message"))){
                tabcount=(String)map.get("out_count");
            }else{
                log.error("获取TAB数量出错,"+map.get("out_message"));
            }
        }catch(Exception e){
            log.error("获取TAB数量出错:",e);
        }
        return tabcount;
    }

    public String getChildTabCount(String userid) {
        log.info("家长的孩子告警统计 方法：" + userid);

        Map < String, Object > map = new HashMap < String, Object >();
        map.put("PR_USERID", userid);

        String count = "0";
        try {
            count = (String) service.getObject(
                    "GPSNEW.getChildCheckErrorCount", map);
            log.info("家长的孩子告警统计：" + count);
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "0";
        }

        return count;

    }

    /**
     * 加载轨迹回放时线路信息
     */
    public List < SiteBean > initBitSite(String lineid) {
        //MDC.put("modulename", "[cheliangMonitor-guiji]");
        log.info("initBitSite start");
        log.info("轨迹回放线路站点信息：" + lineid);
        Map map = new HashMap < String, Object >();

        if (lineid == null || lineid.equals("") || lineid.equals("null")) {
            return null;
        } else {
            map.put("ROUTE_ID", lineid);
            // map.put("ROUTE_IDS", "");
            List < SiteBean > list = null;

            try {
                list = service.getObjects("GPSNEW.getSiteByLine", map);
                log.info("线路查车长度：" + list.size());
            } catch (BusinessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }

            if (list != null && list.size() > 0) {
                List < TerminalViBean > objlist = new ArrayList < TerminalViBean >();

                for (int i = 0; i < list.size(); i++) {
                    TerminalViBean tv = new TerminalViBean();
                    SiteBean sb = list.get(i);
                    tv.setLATITUDE(sb.getSITE_LATITUDE());
                    tv.setLONGITUDE(sb.getSITE_LONGITUDE());
                    objlist.add(tv);
                }

                if (objlist != null && objlist.size() > 0) {
                    gpsUtil gpsUtil = new gpsUtil();
                    objlist = gpsUtil.getpost(objlist);
                    for (int i = 0; i < objlist.size(); i++) {

                        TerminalViBean ttv = objlist.get(i);
                        list.get(i).setSITE_LATITUDE(ttv.getLATITUDE());

                        list.get(i).setSITE_LONGITUDE(ttv.getLONGITUDE());

                    }

                }
            }
            // Map resultMap = new HashMap<String, Object>();
            // resultMap.put("data", list);
            log.info("initBitSite end");
            return list;

        }

    }
    
    public List<Enter_Info_bean> getEnterInfoList(String enterid){
        //MDC.put("modulename", "[cheliangMonitor-xiaoxi]");
        log.info("getEnterInfoList start");
        log.info("加载预设消息列表");
        log.info("getEnterInfoList:" + enterid);
        Map map = new HashMap < String, Object >();
        map.put("ENTERPRIST_ID", enterid);
        
        List<Enter_Info_bean> list= null;
        try {
            list = service.getObjects("GPSNEW.getEnter_Info_bean_List", map);
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("getEnterInfoList's length:" + list.size());
        log.info("getEnterInfoList end");
        return list;
    }
    
    public Enter_Info_bean getEnterInfoOne(String id){
        //MDC.put("modulename", "[cheliangMonitor-xiaoxi]");
        log.info("getEnterInfoOne start");
        log.info("加载预设消息内容");
        log.info("getEnterInfoOne:" + id);
        
        Map map = new HashMap < String, Object >();
        map.put("INFO_ID", id);
        
        Enter_Info_bean list= null;
        try {
            list = (Enter_Info_bean) service.getObject("GPSNEW.getEnter_Info_bean_One", map);
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //log.info("getEnterInfoOne's length:" + list.size());
        log.info("getEnterInfoOne end");
        return list;
    }
    
    public String addEnterInfo(String entid,String userid,String msg){
        //MDC.put("modulename", "[cheliangMonitor-xiaoxi]");
        log.info("addEnterInfo start");
        log.info("添加预设消息");
        String msgid = UUIDGenerator.getUUID32();
        Enter_Info_bean eib = new Enter_Info_bean();
        eib.setINFO_ID(msgid);
        eib.setINFO_TITLE(msg);
        eib.setINFO_REMARK(msg);
        eib.setENTERPRIST_ID(entid);
        eib.setCREATE_USER(userid);
        //eib.setCREATE_TIME(create_time)
        try {
            Integer i = (Integer) service.getObject("GPSNEW.selectinsertInfo_title", eib);
            if(i.intValue()>0){
                return "2";
            }
            else{
                service.insert("GPSNEW.insertEnter_Info", eib);
            }
            
            
            
            
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.info("addEnterInfo error end");
            return "1";
        }
        log.info("addEnterInfo end");
        return "0";
        
    }
    
    public int deleteEnterInfo(String infoid){
        //MDC.put("modulename", "[cheliangMonitor-xiaoxi]");
        log.info("deleteEnterInfo start");
        log.info("删除预设消息");
        Enter_Info_bean eib = new Enter_Info_bean();
        eib.setINFO_ID(infoid);
        
        try {
            int i = service.delete("GPSNEW.deleteEnter_Info", eib);
            
            log.info("deleteEnterInfo's :"+ i);
            log.info("deleteEnterInfo end");
            return i;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.info("deleteEnterInfo error end");
            return 0;
        }
    }
    
    
    /**
     * 轨迹回放中行车记录查询
     * @param organization_id
     * @param vin
     * @param StrateTime
     * @param EndTime
     * @return
     */
    public List<RunOilRecord> tabNodeSelectDwr(String organization_id,String vin,String Time){
        //MDC.put("modulename", "[cheliangMonitor-guiji]");
        log.info("tabNodeSelectDwr start");
        log.info("轨迹回放中行车记录查询");
        String StrateTime = Time+" 00:00:00";
        String EndTime = Time+" 23:59:59";
        
        Map map = new HashMap < String, Object >();
        map.put("ENTERPRISE_ID", organization_id);
        map.put("VEHICLE_VIN", vin);
        map.put("START_TIME", StrateTime);
        map.put("END_TIME", EndTime);
        
        try {
            List<RunOilRecord> rorlist = service.getObjects("GPSNEW.getRunOils", map);
            
            log.info("tabNodeSelectDwr's :"+ rorlist.size());
            log.info("tabNodeSelectDwr end");
            return rorlist;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.info("tabNodeSelectDwr error end");
            return null;
        }
    }
    
    
    /**
     * 行驶线路趟数列表
     * @param vin 
     * @param time 日期
     * @return
     */
    public List<TerminalViBean> tabRunLineDwr(String vin,String time){
        
        Map map = new HashMap < String, Object >();

        String tabTime = StringdateToStringdate(time);

        log.info("tabTime:" + tabTime);
        map.put("vehicle_vin", vin);
        map.put("partition", "INOUTSITE_" + tabTime);
        
        try {
            List<TerminalViBean> sitelist = service.getObjects("GPSNEW.getRunLineSite", map);
            
            if(sitelist != null && sitelist.size() > 0){
                
                List<TerminalViBean> list = new ArrayList();
                TerminalViBean tv = new TerminalViBean();
                
                for(int i = 0 ; i < sitelist.size(); i++){
                    
                    if(i == 0){// 将第一条数据付给全局对象
                        
                        tv.setVEHICLE_VIN(vin);
                        tv.setSTART_TIME(sitelist.get(i).getTERMINAL_TIME());
                        tv.setTRIP_ID(sitelist.get(i).getTRIP_ID());
                        tv.setROUTE_ID(sitelist.get(i).getROUTE_ID());
                        tv.setROUTE_NAME(sitelist.get(i).getROUTE_NAME());
                        tv.setSITE_ID(sitelist.get(i).getSITE_ID());
                        
                        
                    }else if(i == sitelist.size()-1){// 最后一条数据时完成闭环处理
                        
                        if(sitelist.get(i).getTRIP_ID().equals(tv.getTRIP_ID())){
                            if(sitelist.get(i).getSITE_ID().equals(tv.getSITE_ID())){
                                tv.setEND_TIME(sitelist.get(i-1).getTERMINAL_TIME());
                                // 生成一趟的完整信息
                                TerminalViBean tv2 = new TerminalViBean();
                                tv2.setVEHICLE_VIN(tv.getVEHICLE_VIN());
                                tv2.setSTART_TIME(tv.getSTART_TIME());
                                tv2.setTRIP_ID(tv.getTRIP_ID());
                                tv2.setROUTE_ID(tv.getROUTE_ID());
                                tv2.setROUTE_NAME(tv.getROUTE_NAME());
                                tv2.setEND_TIME(tv.getEND_TIME());
                                list.add(tv2);
                                // 更新全局对象
                                tv.setVEHICLE_VIN(vin);
                                tv.setSTART_TIME(sitelist.get(i).getTERMINAL_TIME());
                                tv.setTRIP_ID(sitelist.get(i).getTRIP_ID());
                                tv.setROUTE_ID(sitelist.get(i).getROUTE_ID());
                                tv.setROUTE_NAME(sitelist.get(i).getROUTE_NAME());
                                tv.setEND_TIME(sitelist.get(i).getTERMINAL_TIME());
                                tv.setSITE_ID(sitelist.get(i).getSITE_ID());
                                list.add(tv);
                            }else{// 
                                tv.setEND_TIME(sitelist.get(i).getTERMINAL_TIME());
                                // 生成一趟的完整信息
                                TerminalViBean tv2 = new TerminalViBean();
                                tv2.setVEHICLE_VIN(tv.getVEHICLE_VIN());
                                tv2.setSTART_TIME(tv.getSTART_TIME());
                                tv2.setTRIP_ID(tv.getTRIP_ID());
                                tv2.setROUTE_ID(tv.getROUTE_ID());
                                tv2.setROUTE_NAME(tv.getROUTE_NAME());
                                tv2.setEND_TIME(tv.getEND_TIME());
                                list.add(tv2);
                            }
                        }else{// 处理最后一天行程变化的数据
                            tv.setEND_TIME(sitelist.get(i-1).getTERMINAL_TIME());
                            
                            TerminalViBean tv2 = new TerminalViBean();
                            tv2.setVEHICLE_VIN(tv.getVEHICLE_VIN());
                            tv2.setSTART_TIME(tv.getSTART_TIME());
                            tv2.setTRIP_ID(tv.getTRIP_ID());
                            tv2.setROUTE_ID(tv.getROUTE_ID());
                            tv2.setROUTE_NAME(tv.getROUTE_NAME());
                            tv2.setEND_TIME(tv.getEND_TIME());
                            list.add(tv2);
                            
                            tv.setVEHICLE_VIN(vin);
                            tv.setSTART_TIME(sitelist.get(i).getTERMINAL_TIME());
                            tv.setTRIP_ID(sitelist.get(i).getTRIP_ID());
                            tv.setROUTE_ID(sitelist.get(i).getROUTE_ID());
                            tv.setROUTE_NAME(sitelist.get(i).getROUTE_NAME());
                            tv.setEND_TIME(sitelist.get(i).getTERMINAL_TIME());
                            tv.setSITE_ID(sitelist.get(i).getSITE_ID());
                            list.add(tv);
                        }
                        
                    }else{
                        // 判断行程是否一致
                        if(sitelist.get(i).getTRIP_ID().equals(tv.getTRIP_ID())){
                            
                            if(sitelist.get(i).getSITE_ID().equals(tv.getSITE_ID())){
                                
                                tv.setEND_TIME(sitelist.get(i-1).getTERMINAL_TIME());
                                // 生成一趟的完整信息
                                TerminalViBean tv2 = new TerminalViBean();
                                tv2.setVEHICLE_VIN(tv.getVEHICLE_VIN());
                                tv2.setSTART_TIME(tv.getSTART_TIME());
                                tv2.setTRIP_ID(tv.getTRIP_ID());
                                tv2.setROUTE_ID(tv.getROUTE_ID());
                                tv2.setROUTE_NAME(tv.getROUTE_NAME());
                                tv2.setEND_TIME(tv.getEND_TIME());
                                list.add(tv2);
                                // 更新全局对象
                                tv.setVEHICLE_VIN(vin);
                                tv.setSTART_TIME(sitelist.get(i).getTERMINAL_TIME());
                                tv.setTRIP_ID(sitelist.get(i).getTRIP_ID());
                                tv.setROUTE_ID(sitelist.get(i).getROUTE_ID());
                                tv.setROUTE_NAME(sitelist.get(i).getROUTE_NAME());
                                tv.setSITE_ID(sitelist.get(i).getSITE_ID());
                            }
                            
                        }else{ // 行程发生变化完成一趟行驶
                            tv.setEND_TIME(sitelist.get(i-1).getTERMINAL_TIME());
                            TerminalViBean tv2 = new TerminalViBean();
                            tv2.setVEHICLE_VIN(tv.getVEHICLE_VIN());
                            tv2.setSTART_TIME(tv.getSTART_TIME());
                            tv2.setTRIP_ID(tv.getTRIP_ID());
                            tv2.setROUTE_ID(tv.getROUTE_ID());
                            tv2.setROUTE_NAME(tv.getROUTE_NAME());
                            tv2.setEND_TIME(tv.getEND_TIME());
                            list.add(tv2);
                            tv.setVEHICLE_VIN(vin);
                            tv.setSTART_TIME(sitelist.get(i).getTERMINAL_TIME());
                            tv.setTRIP_ID(sitelist.get(i).getTRIP_ID());
                            tv.setROUTE_ID(sitelist.get(i).getROUTE_ID());
                            tv.setROUTE_NAME(sitelist.get(i).getROUTE_NAME());
                            tv.setSITE_ID(sitelist.get(i).getSITE_ID());
                        }
                    }
                }
                
                if(list != null && list.size() > 0){
                    List<TerminalViBean> list2 = new ArrayList();
                    for(int i = list.size(); i > 0 ; i--){
                        list2.add(list.get(i-1));
                    }
                    return list2;
                }
                
                return list;
                
            }else{
                
                return null;
            }
            
            
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
        
    }
    
    /**
     * 获取行驶线路站点信息
     * @param idinfo
     * @param day
     * @return
     */
    public List <TerminalViBean> getRunLineSiteList(String idinfo,String day){
        //idinfo 格式 TRIP_ID + "-" + VIN + "-" + START_TIME+"-" + END_TIME;
        String[] infos = idinfo.split("-");
        String tripid = infos[0];
        String vin = infos[1];
        String starttime = infos[2];
        String endtime = infos[3];
        
        
        
        Map map = new HashMap < String, Object >();
        String tabTime = StringdateToStringdate(day);
        log.info("tabTime:" + tabTime);
        map.put("vehicle_vin", vin);
        map.put("partition", "INOUTSITE_" + tabTime);
        map.put("trip_id", tripid);
        map.put("start_time", day+" " + starttime);
        map.put("end_time", day + " " + endtime);
        
        
        List<TerminalViBean> sitelist = null;
        try {
            sitelist = service.getObjects("GPSNEW.getRunLineSiteList", map);
            
            gpsUtil gpsUtil = new gpsUtil();
            sitelist = gpsUtil.getpost(sitelist);
            
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return sitelist;
    }
    
    
    /**
     * 
     * @param idinfo
     * @param day
     * @return
     */
    public List <TerminalViBean> getPrintGageInfo(String idinfo,String day){
        
        return null;
    }
    /*
     * 下行消息列表展示
     */
    public List < MsgBean > downManageList(String vin) {
       
        //MDC.put("modulename", "[cheliangMonitor-xiaoxi]");
        log.info("downManageList start");
        log.info("下行消息列表展示:" + vin);
        
        Calendar calendar = Calendar.getInstance();
        // long nowtime = calendar.getTimeInMillis();
        Date currentTime = calendar.getTime();
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String line_end_time = inputFormat.format(currentTime);
        long start = currentTime.getTime();
        currentTime.setTime(start - 7*24 * 60 * 60 * 1000);
        String line_start_time = inputFormat.format(currentTime);
        
        Map map = new HashMap();
        map.put("VEHICLE_VIN", vin);
        map.put("line_end_time", line_end_time);
        map.put("line_start_time", line_start_time);

        List < MsgBean > MsgList = null;
        try {

            MsgList = service.getObjects("GPSNEW.DownMessageList", map);

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        log.info("downManageList end");
        return MsgList;
    }

    /**
     * 线路列表加载时调用此法发 加载线路列表
     */
    public String getalarmName(String id) {
        Map map = new HashMap < String, Object >();

        map.put("ALARM_TYPE_ID", id);

        
        try {
            String name = (String) service.getObject("GPSNEW.getalarmNmae", map);
            log.info("alarm name 's:" + name);
            return name;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public SendCommandClient getSendCommandClient() {
        return sendCommandClient;
    }

    public void setSendCommandClient(SendCommandClient sendCommandClient) {
        this.sendCommandClient = sendCommandClient;
    }

    /**
     * 本方法对“车辆监控--车辆筛选模块”进行查询---add by chenwf
     * @param vins
     * @return
     */
    public List < TerminalViBean > getVehcileBySel(String stype,String  vins ,String organization_id) {
          Map < String, Object > map = new HashMap < String, Object >();    
        List < TerminalViBean > list =null;
        map.put("stype", stype);
        map.put("organization_id",organization_id );
        map.put("VEHICLE_VINS", vins);
        try { 
        	//获取企业的告警设置，没有则为私有
        	HttpSession session = WebContextFactory.get().getSession();
        	UserInfo user = (UserInfo)session.getAttribute(Constants.USER_SESSION_KEY);
        	String redAids=(String) service.getObject("AlarmManage.listShow", user.getEntiID());
        	if(!StringUtils.hasText(redAids)){
        		redAids="40,32,10,67,66,09,13,65,64,25,69,70,71,26,68";
        	}
        	
             list = service.getObjects("GPSNEW.getVehcileBySelAll", map);
            if (list != null && list.size() > 0) {

                List < TerminalViBean > newlist = new ArrayList();

                for (int i = 0; i < list.size(); i++) {
                	TerminalViBean tv = list.get(i);
                    if (tv.getTERMINAL_TIME() == null
                            || tv.getTERMINAL_TIME().equals("")) {

                        continue;
                    } else if (tv.getLONGITUDE() == null
                            || tv.getLONGITUDE().equals("")
                            || tv.getLONGITUDE().equals("FFFF")
                            || tv.getLATITUDE() == null
                            || tv.getLATITUDE().equals("")
                            || tv.getLATITUDE().equals("FFFF")) {

                        continue;
                    } else if (tv.getSTAT_INFO() != null
                            && !tv.getSTAT_INFO().equals("")) {

                        AlarmRecode ar = new AlarmRecode();
                        String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!acc.equals("0")
                                && !timeBound(tv.getTERMINAL_TIME())
                                || (acc.equals("0") && !timeBound30(tv
                                        .getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getALARM_BASE_INFO() != null
                                && !tv.getALARM_BASE_INFO().equals("")) {

                            if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } else if (tv.getALARM_EXT_INFO() != null
                                && !tv.getALARM_EXT_INFO().equals("")) {
                            if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } 
                        
                        if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                            tv.setSPEEDING(tv.getGPS_SPEEDING());
                        }
                    } else {

                        AlarmRecode ar = new AlarmRecode();
                        // String acc = ar.stat_info(tv.getSTAT_INFO());

                        if (!timeBound(tv.getTERMINAL_TIME())
                                || (!timeBound30(tv.getTERMINAL_TIME()))) {
                            // log.info("时间输出："+
                            // tv.getTERMINAL_TIME());//2011-07-13
                            // 10:45:41.0
                            tv.setColor("g");// 灰色
                        } else if (tv.getALARM_BASE_INFO() != null
                                && !tv.getALARM_BASE_INFO().equals("")) {

                            if (!ar.recode_alarm_base(tv.getALARM_BASE_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_base(tv.getALARM_BASE_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } else if (tv.getALARM_EXT_INFO() != null
                                && !tv.getALARM_EXT_INFO().equals("")) {
                            if (!ar.recode_alarm_ext(tv.getALARM_EXT_INFO())
                                    .equals("")) {
                            	if(markRed(ar.recode_alarm_ext(tv.getALARM_EXT_INFO()),redAids)){
                            		tv.setColor("r");// 红色
                            	}
                            }
                        } 
                        if(tv.getSPEED_SOURCE_SETTING() != null && tv.getSPEED_SOURCE_SETTING().equals("1")){
                            tv.setSPEEDING(tv.getGPS_SPEEDING());
                        }
                    }

                    newlist.add(tv);

                }
                gpsUtil gpsUtil = new gpsUtil();
                newlist = gpsUtil.getpost(newlist);

               /* List showlist = new ArrayList();
                for (int i = 0; i < newlist.size(); i++) {
                    TerminalViBean tvb = newlist.get(i);
                    TerminalPointInfoBean tb = new TerminalPointInfoBean();
                    tb.setVEHICLE_VIN(tvb.getVEHICLE_VIN());
                    tb.setVehicle_code(tvb.getVehicle_code());
                    tb.setVEHICLE_LN(tvb.getVEHICLE_LN());
                    tb.setLONGITUDE(tvb.getLONGITUDE());
                    tb.setLATITUDE(tvb.getLATITUDE());
                    tb.setColor(tvb.getColor());
                    tb.setDIRECTION(tvb.getDIRECTION());
                    tb.setSPEEDING(tvb.getSPEEDING());
                    showlist.add(tb);
                }
                log.info("getVehcileByVinS end");
                return showlist;*/
                return newlist;
            }
            return null;
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.info("getVehcileBySel error end");
            return null;
        }finally{
            //mapVin.clear();
            
        }
    }
    
    //检查告警类型是否需要红色显示,只要有一个匹配则返回true
    private boolean markRed(String aids,String redAids) throws BusinessException{
    	String[] my=aids.split(",");
    	String[] arr=redAids.split(",");
    	List<String> list=new ArrayList<String>();
    	for (String string : arr) {
			list.add(string);
		}
    	for (String string : my) {
			if(list.contains(string)){
				return true;
			}
		}
    	return false;
    	
    	
    }
    
}
