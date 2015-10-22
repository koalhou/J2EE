package com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.apache.log4j.//MDC;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.Alarm;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.MsgBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.PhotoRoutewayBean;
import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.domain.TerminalViBean;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.itsmanage.oilmanage.carrun.domain.RunOilRecord;
import com.neusoft.clw.itsmanage.oilmanage.oilused.domain.OilManage;
import com.neusoft.clw.safemanage.humanmanage.alarmmanage.domain.StuAlarm;
import com.neusoft.clw.safemanage.personsafe.photomonitor.domain.PhotoMonitorInfo;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.sendcommand.service.SendCommandClient;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.datamanage.zonemanage.domain.ZoneXsInfo;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class GPSAction extends PaginationAction {
	
	private static String CHELIANGTAB = "cheliang";
	private static String XIANLUTAB = "xianlu";
	private static String SELECTTAB = "select";
	private static String THREADTAB = "thread";

    /** service共通类 */
    private transient Service service;

    private String message = null;

    private VehcileInfo vehcileInfo;

    private Alarm alarm;

    private List < Alarm > alarmList;

    private String refuel_id;

    private String vin;

    private String lookflag;

    private String lngX;

    private String latY;
    
    private String line_start_time;

    private String line_end_time;
    private String timetab_time;

    private String route_id;
    
    private String stu_id;
    
    private String optpageid;
    //告警需要的参数BEGIN
    private String alarmid;
    private String alarmtypeid;
    private String alarmtime;
    private String sourceid;
    
    private String day;
    private String selectDriveingNode;
    private String load_alarm_event;
    
    
    

    public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getSelectDriveingNode() {
		return selectDriveingNode;
	}

	public void setSelectDriveingNode(String selectDriveingNode) {
		this.selectDriveingNode = selectDriveingNode;
	}

	public String getLoad_alarm_event() {
		return load_alarm_event;
	}

	public void setLoad_alarm_event(String load_alarm_event) {
		this.load_alarm_event = load_alarm_event;
	}

	public String getSourceid() {
		return sourceid;
	}

	public void setSourceid(String sourceid) {
		this.sourceid = sourceid;
	}

	public String getAlarmtypeid() {
		return alarmtypeid;
	}

	public void setAlarmtypeid(String alarmtypeid) {
		this.alarmtypeid = alarmtypeid;
	}

	public String getAlarmtime() {
		return alarmtime;
	}

	public void setAlarmtime(String alarmtime) {
		this.alarmtime = alarmtime;
	}

	public String getAlarmid() {
		return alarmid;
	}

	public void setAlarmid(String alarmid) {
		this.alarmid = alarmid;
	}
	
	

	private Map photomap = new HashMap();

    

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

   

    SendCommandClient sendCommandClient;

    private TerminalViBean terminalViBean;
    TerminalViBean terminalViBeanV;

    private Map GpsAlermMap = new HashMap();

    private Map MsgMap = new HashMap();

    /** 车辆列表 **/
    private List < TerminalViBean > vehcList;
    
    private String videoID;

   

    /**
     * GPS车辆告警页面浏览
     * @return
     */
    public String gpsPostion() {

        //MDC.put("modulename", "[cheliangMonitor]");

        log.info("进入车辆监控首页");
        log.info("进入车辆监控首页return");
        return SUCCESS;
    }
    
    public String getFrameObj(){
    	
    	return SUCCESS;
    }
    public String getFrameObj2(){
    	log.info("getFrameObj2");
    	
    	return SUCCESS;
    }
    
    public String getiFramechooseObj(){
    	//MDC.put("modulename", "[cheliangMonitor]");
    	log.info("popiframe 中转");
    	log.info("popiframe 中转 return");
    	//System.out.println(alarmid);
    	return SUCCESS;
    }
    
    public String getTopFrameObj(){
    	
    	log.info("getTopFrameObj's vin:" + vin);
    	Map < String, Object > map = new HashMap < String, Object >();
        map.put("VEHICLE_VIN", vin);
        try {
            
            TerminalViBean list = (TerminalViBean) service.getObject(
                    "GPSNEW.getTipInfoByVin", map);

            if (list != null) {
                // TipInfoBean tb = new TipInfoBean();
            	AlarmRecode ar = new AlarmRecode();
            	if(checkACCandTime(list.getSTAT_INFO(),list.getTERMINAL_TIME())){
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
            			list.setVEH_EXT_INFO("0");
            		}
            		
            		
            	}
            	else{
            		list.setColor("g");
            		list.setVEH_EXT_INFO("0");
            	}


                terminalViBean = list;

            } else {
                return null;
            }
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            return null;
        }
    	
    	return SUCCESS;
    }
    

    /**
     * 获取地理信息
     * @return
     */
    private boolean getGeoInfos() {
        UserInfo loginuserInfo = getCurrentUser();
        // 地理信息列表
        List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
        Map < String, Object > mapPar = new HashMap < String, Object >(1);

        try {
            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市

            // if (loginuserInfo != null && loginuserInfo.getCountyID() != ""
            // && loginuserInfo.getCountyID() != null) {
            mapPar.put("zone_parent_id", "111");

            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }
            // }
            // 市/县
            if (loginuserInfo != null && loginuserInfo.getProvinceID() != ""
                    && loginuserInfo.getProvinceID() != null) {
                mapPar.put("zone_parent_id", loginuserInfo.getProvinceID());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

 

   

    /*
     * 获取json表格下告警数据
     */
    public String GpsAlarmInfo() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        // 用于取得flexigrid传入的参数

        // String rpNum = request.getParameter("rp"); //每页显示条数

        // String pageIndex = request.getParameter("page"); //当前页码

        String sortName = request.getParameter("sortname"); // 排序字段名
        String sortOrder = request.getParameter("sortorder"); // 升序OR降序
        UserInfo user = getCurrentUser();
        if (null == alarm) {
            alarm = new Alarm();
        }
        // alarm.setEnterprise_id(user.getEntiID());
        alarm.setOrganization_id(user.getOrganizationID());
        alarm.setSortname(sortName); // domain类中设置排序字段名
        alarm.setSortorder(sortOrder); // domain类中设置升序OR降序
        try {
            if ("3".equals(user.getUserType())) {
                alarm.setUserid(user.getUserID());
                alarmList = service.getObjectsByPage(
                        "GPS.getAlarmInfosforchezhu", alarm, 0, 6);
            } else {
                alarmList = service.getObjectsByPage("GPS.getAlarmInfos",
                        alarm, 0, 6);
            }
            log.info("alarm length:" + alarmList.size());
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            alarmList = null;
        }

        GpsAlermMap = getPagination(alarmList);
        log.info("info: " + GpsAlermMap.get("rows"));

        return SUCCESS;
    }

 

    /**
     * 方向转换
     * @param direction
     * @return
     */
    private String diretionToStr(String direction) {
        if (direction == null || "".equals(direction)
                || !direction.matches("^(-?\\d+)(\\.\\d+)?")) {
            return "";
        }
        // 转换成数值
        double d = 0.0;
        try {
            d = Double.valueOf(direction);
        } catch (NumberFormatException nfe) {
            log.error("转换方向时出错,", nfe);
        }
        if ((d >= 0 && d < 10) || (d >= 350 && d <= 360)) {
            return "北";
        } else if (d >= 10 && d < 80) {
            return "东北";
        } else if (d >= 80 && d < 100) {
            return "东";
        } else if (d >= 100 && d < 170) {
            return "东南";
        } else if (d >= 180 && d < 190) {
            return "南";
        } else if (d >= 190 && d < 260) {
            return "西南";
        } else if (d >= 260 && d < 280) {
            return "西";
        } else if (d >= 280 && d < 350) {
            return "西北";
        } else {
            return "北";
        }
    }

    // 组装json表格数据
    public Map getPagination(List dayList) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < dayList.size(); i++) {

            Alarm s = (Alarm) dayList.get(i);
            s.setDeal_flag(Constants.ALARM_SYS_MAP.get(s.getDeal_flag()));

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getAlarm_id());

            cellMap.put("cell", new Object[] {"", s.getVehicle_ln(),
                    s.getAlarm_type_name(), s.getAlarm_type_comments(),
                    s.getSpeeding(), s.getAlarm_time(), s.getDeal_flag(),
                    s.getEngine_rotate_speed(), s.getMileage(),
                    diretionToStr(s.getDirection()), "", s.getLongitude(),
                    s.getLatitude(), s.getVehicle_vin() });

            mapList.add(cellMap);

        }
        if (null == alarm) {
            alarm = new Alarm();
        }

        mapData.put("page", 1);// 从前台获取当前第page页
        mapData.put("total", 5);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    public String gpsBrowse() {

        try {
            if (null == alarm) {
                alarm = new Alarm();
            }
            String vehln = (String) service.getObject("GPS.getvehln", alarm
                    .getVehicle_vin());
            alarm.setVehicle_ln(vehln);

            String lon = alarm.getLongitude();
            String lat = alarm.getLatitude();
            gpsUtil gpsUtil = new gpsUtil();
            String point = gpsUtil.getOneXY(lon, lat);
            if (point != null && point != "") {
                String[] p = point.split(",");
                alarm.setLongitude(p[0].toString());
                alarm.setLatitude(p[1].toString());
            }

            service.update("GPS.updateDealFlag", alarm);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(e);
            return ERROR;
        }
        return SUCCESS;
    }

    public String gpsBrowse2() {

        try {
            if (null == alarm) {
                alarm = new Alarm();
            }
            String vehln = (String) service.getObject("GPS.getvehln", alarm
                    .getVehicle_vin());
            alarm.setVehicle_ln(vehln);

            String lon = alarm.getLongitude();
            String lat = alarm.getLatitude();
            gpsUtil gpsUtil = new gpsUtil();
            String point = gpsUtil.getOneXY(lon, lat);
            if (point != null && point != "") {
                String[] p = point.split(",");
                alarm.setLongitude(p[0].toString());
                alarm.setLatitude(p[1].toString());
            }

            // service.update("GPS.updateDealFlag", alarm);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(e);
            return ERROR;
        }
        return SUCCESS;
    }

  

    /**
     * 轨迹回放
     * @return
     */
    public String getbitlook() {
    	//MDC.put("modulename", "[cheliangMonitor-guiji]");
    	log.info("进入轨迹回放");
        final String browseTitle = getText("gps.bitlook.title");
        try {
            log.info("bitlook..." + route_id);
            log.info("vin:" + vin);
            log.info("lookflag:" + lookflag);
            log.info("lon:" + lngX + ",lat:" + latY);

            Calendar calendar = Calendar.getInstance();
            // long nowtime = calendar.getTimeInMillis();
            Date currentTime = calendar.getTime();
            SimpleDateFormat inputFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            line_end_time = inputFormat.format(currentTime);
            long start = currentTime.getTime();
            currentTime.setTime(start - 2 * 60 * 60 * 1000);
            line_start_time = inputFormat.format(currentTime);
            
            SimpleDateFormat inputFormat2 = new SimpleDateFormat(
            "yyyy-MM-dd");
            timetab_time = inputFormat2.format(currentTime);

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_GPS_BITLOOK);

        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            log.info("进入轨迹回放 error return");
            return ERROR;
        }
        log.info("进入轨迹回放 return");
        return SUCCESS;
    }
    
    /**
     * 轨迹打印
     * @return
     */
    public String getbitlookprint(){
    	//idinfo 格式 TRIP_ID + "-" + VIN + "-" + START_TIME+"-" + END_TIME;
    	log.info("getbitlookprint start");
    	
    	String model = terminalViBean.getSelectModel();
    	String vvin = terminalViBean.getVEHICLE_VIN();
    	String tripid = terminalViBean.getTRIP_ID();
    	String alarmload = terminalViBean.getLoad_alarm_event();
    	String stime = terminalViBean.getSTART_TIME();
    	String etime = terminalViBean.getEND_TIME();
    	String pointIndex = terminalViBean.getPointIndex();
    	
    	String dirname = terminalViBean.getDRIVER_NAME() != null ? terminalViBean.getDRIVER_NAME() : "";
		try {
			dirname = SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(dirname, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Date sDate = sdf.parse(stime);
	        Date eDate = sdf.parse(etime);
	        boolean flag = eDate.before(sDate);
	        if(flag){
	        	Calendar   calendar = new GregorianCalendar(); 
	            calendar.setTime(eDate); 
	        	calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
	        	eDate=calendar.getTime();   //这个时间就是日期往后推一天的结果 
	        	etime = sdf.format(eDate);
	        } 
        }catch(Exception ee){
        	ee.printStackTrace();
        }
        
    	if(model.equals("3")){
    		try {
	    		//String startp = stime.substring(0, 10);
	
	            // String pTime = StringdateToStringdate(startp);
	            
	    		Map < String, Object > map = new HashMap < String, Object >();
	    		map.put("vehicle_vin", vvin);
	    		map.put("trip_id", tripid);
	    		map.put("start_time", stime);
	    		map.put("end_time", etime);
	    		//map.put("partitionp", "INOUTSITE_"+pTime);
    		
	    		if(stime != null ){
	    			List<TerminalViBean> tvlist =  null;
	    			tvlist= service.getObjects("GPSNEW.getPrintGageInfo", map);
					
	    			
	    			if(tvlist !=null && tvlist.size()>0){
	    				
	    				TerminalViBean tv = tvlist.get(tvlist.size()-1);
	    				
	    				terminalViBean.setVEHICLE_VIN(tv.getVEHICLE_VIN());
	    				terminalViBean.setVEHICLE_LN(tv.getVEHICLE_LN());
	    				terminalViBean.setDRIVER_ID(tv.getDRIVER_ID());
	    				terminalViBean.setDRIVER_NAME(tv.getDRIVER_NAME());
	    				terminalViBean.setROUTE_ID(tv.getROUTE_ID());
	    				terminalViBean.setROUTE_NAME(tv.getROUTE_NAME());
	    				terminalViBean.setTRIP_ID(tv.getTRIP_ID());
	    			}
	    		}
	    		terminalViBean.setSelectModel(model);
    			terminalViBean.setTRIP_ID(tripid);
    			terminalViBean.setLoad_alarm_event(alarmload);
    			terminalViBean.setSTART_TIME(stime);
    			terminalViBean.setEND_TIME(etime);
    			terminalViBean.setPointIndex(pointIndex);
    			
    			
   			 
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}else if(model.equals("2")){
    		Map < String, Object > map = new HashMap < String, Object >();
    		map.put("VEHICLE_VIN", vvin);
    		try {
				terminalViBean = (TerminalViBean) service.getObject("GPSNEW.getTipInfoByVin", map);
				terminalViBean.setSelectModel(model);
    			terminalViBean.setTRIP_ID(tripid);
    			terminalViBean.setLoad_alarm_event(alarmload);
    			terminalViBean.setSTART_TIME(stime);
    			terminalViBean.setEND_TIME(etime);
    			terminalViBean.setPointIndex(pointIndex);
    			terminalViBean.setDRIVER_NAME(dirname);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}else if(model.equals("1")){
    		Map < String, Object > map = new HashMap < String, Object >();
    		map.put("VEHICLE_VIN", vvin);
    		try {
    			
				terminalViBean = (TerminalViBean) service.getObject("GPSNEW.getTipInfoByVin", map);
				terminalViBean.setSelectModel(model);
    			terminalViBean.setTRIP_ID(tripid);
    			terminalViBean.setLoad_alarm_event(alarmload);
    			terminalViBean.setSTART_TIME(stime);
    			terminalViBean.setEND_TIME(etime);
    			terminalViBean.setPointIndex(pointIndex);
    			terminalViBean.setDRIVER_NAME(dirname);
    		} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    		
    		
    		
    		
    	
    	return SUCCESS;
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
    
    /**
     * 孩子轨迹回放
     * @return
     */
    public String getchildbitlook() {
        final String browseTitle = getText("gps.bitlook.title");
        try {
            log.info("Childbitlook..." + route_id);
            log.info("vin:" + vin);
            log.info("lookflag:" + lookflag);
            log.info("lon:" + lngX + ",lat:" + latY);
            log.info("stu_id:"+ stu_id);

            Calendar calendar = Calendar.getInstance();
            // long nowtime = calendar.getTimeInMillis();
            Date currentTime = calendar.getTime();
            SimpleDateFormat inputFormat = new SimpleDateFormat(
                    "yyyy-MM-dd HH:mm:ss");
            line_end_time = inputFormat.format(currentTime);
            long start = currentTime.getTime();
            currentTime.setTime(start - 2 * 60 * 60 * 1000);
            line_start_time = inputFormat.format(currentTime);

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_GPS_BITLOOK);

        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 重点监控
     * @return
     */
    public String getrealtimelook() {
    	//MDC.put("modulename", "[cheliangMonitor-zhongdian]");
        log.info("进入重点监控:" + vin);
        final String browseTitle = getText("gps.realtimelook.title");
        try {
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_GPS_REALTIMELOOK);

        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            log.info("进入重点监控  error return");
            return ERROR;
        }
        log.info("进入重点监控 return");
        return SUCCESS;
    }

    /**
     * 周边设施
     * @return
     */
    public String getfacilitieslook() {
        log.info("周边设施：" + lngX + "," + latY);

        final String browseTitle = getText("gps.facilitieslook.title");
        try {
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_GPS_FACILITIESLOOK);

        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 周边服务点
     * @return
     */
    public String getSevicepointlook() {
        log.info("周边服务：" + lngX + "," + latY);
        final String browseTitle = getText("gps.Sevicepointlook.title");
        try {
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_GPS_SEVICEPOINTLOOK);

        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 下发消息
     * @return
     */
    public String getSendMsg() {
    	//MDC.put("modulename", "[cheliangMonitor-xiaoxi]");
    	log.info("进入消息下发");
        log.info("getSendMsg's vin:" + vin);
        //log.info("消息位子：" + lngX + "," + latY);
        final String browseTitle = getText("gps.SendMsg.title");
        /*
         * gpsUtil gpsUtil = new gpsUtil(); String xy =
         * gpsUtil.getOneXY(lngX,latY); String[] XY = xy.split(",");
         * setLon(XY[0]); setLat(XY[1]);
         */

        Map map = new HashMap();
        map.put("VEHICLE_VIN", vin);
        try {
            terminalViBean = (TerminalViBean) service.getObject(
                    "GPSNEW.getRealVehcileByVin", map);

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_GPS_SENDMSG);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            log.info("进入消息下发 error return");
            return ERROR;
            // photoSysMap.put("0","--");
        }
        log.info("进入消息下发 return");
        return SUCCESS;
    }

    /**
     * 批量消息
     * @return
     */
    public String SendMsgS() {
    	//MDC.put("modulename", "[cheliangMonitor-piliangxiaoxi]");
    	
        log.info("进入批量消息 vin:" + vin);
        // log.info("视频：" + lngX + "," + latY);
        final String browseTitle = getText("gps.SendMsg.title");
        log.info("进入批量消息 retrun");
        return SUCCESS;
    }

    /**
     * 视频监控
     * @return
     */
    public String Video_monitor() {
    	//MDC.put("modulename", "[cheliangMonitor-shipin]");
    	log.info("进入视频 监控" + vin);
        
        //log.info("视频：" + lngX + "," + latY);
        final String browseTitle = getText("gps.SendMsg.title");
        
        videoID = "";
        TerminalViBean tv = new TerminalViBean();
        tv.setVEHICLE_VIN(vin);
        this.setTerminalViBean(tv);
        try {
        	//terminalViBean = (TerminalViBean) service.getObject("GPS.getVideoByVIN", terminalViBean);
        	//增加查询出车牌号
        	terminalViBean = (TerminalViBean) service.getObject("GPS.newgetVideoByVIN", terminalViBean);
            if (terminalViBean != null) {
                log.info("GPS VIDEO_ID:" + terminalViBean.getVIDEO_ID());
                videoID = terminalViBean.getVIDEO_ID();
               
            }
        } catch (BusinessException e) {
            log.error("query GPS-CLW_JC_TERMINAL_T catch error:" + e);
        }

        // 设置操作描述
        this.addOperationLog(formatLog(browseTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_GPS_SHIPIN);
        
        
        
        log.info("进入视频 监控 return");
        return SUCCESS;
    }
    
    private String selfheight;
    
    public String getSelfheight() {
		return selfheight;
	}

	public void setSelfheight(String selfheight) {
		this.selfheight = selfheight;
	}

	/**
     * 视频回放
     * @return
     */
    public String Replay_Video_monitor() {
    	//MDC.put("modulename", "[cheliangMonitor-shipinhuifang]");
    	log.info("进入视频回放 vin:" + vin);
        //log.info("视频：" + lngX + "," + latY);
        final String browseTitle = getText("gps.SendMsg.title");
        Calendar calendar = Calendar.getInstance();
        // long nowtime = calendar.getTimeInMillis();
        Date currentTime = calendar.getTime();
        SimpleDateFormat inputFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        line_end_time = inputFormat.format(currentTime);
        //long start = currentTime.getTime();
        //currentTime.setTime(start - 2 * 60 * 60 * 1000);
        //line_start_time = inputFormat.format(currentTime);
        
        videoID = "";
        TerminalViBean tv = new TerminalViBean();
        tv.setVEHICLE_VIN(vin);
        this.setTerminalViBean(tv);
        
        int flag = 0;
        
        Map map = new HashMap();
        map.put("VEHICLE_VIN", vin);
        
        try {
        	terminalViBeanV = (TerminalViBean) service.getObject("GPS.getVideoByVIN", terminalViBean);
            if (terminalViBeanV != null) {
                log.info("GPS VIDEO_ID:" + terminalViBeanV.getVIDEO_ID());
                videoID = terminalViBeanV.getVIDEO_ID();
                terminalViBeanV.setSelfheight(selfheight);
            }
            
            String vehln = (String) service.getObject("GPS.getvehln", vin);
            terminalViBeanV.setVEHICLE_LN(vehln);
            
            terminalViBean = (TerminalViBean) service.getObject(
                    "GPSNEW.getRealVehcileByVin", map);

            List < PhotoRoutewayBean > list = service.getObjects(
                    "GPS.getPhotoRoutteway", map);

            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getROUTEWAY_NO() != null
                            && !list.get(i).getROUTEWAY_NO().equals("")) {
                        list.get(i).setROUTEWAY_NAME(
                                list.get(i).getROUTEWAY_NO() + "--"
                                        + list.get(i).getROUTEWAY_NAME());
                        photoSysMap.add(list.get(i));
                        flag++;
                    }

                }

                
            }
        } catch (BusinessException e) {
            log.error("query GPS-CLW_JC_TERMINAL_T catch error:" + e);
        }

        // 设置操作描述
        this.addOperationLog(formatLog(browseTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_GPS_SHIPIN);
        log.info("进入视频回放 return");
        return SUCCESS;
    }

    public TerminalViBean getTerminalViBeanV() {
		return terminalViBeanV;
	}

	public void setTerminalViBeanV(TerminalViBean terminalViBeanV) {
		this.terminalViBeanV = terminalViBeanV;
	}

	/**
     * 语音监听
     * @return
     */
    public String Audio_monitor() {
        log.info("监听 vin:" + vin);
        log.info("监听位子：" + lngX + "," + latY);
        final String browseTitle = getText("gps.SendMsg.title");

        // 设置操作描述
        this.addOperationLog(formatLog(browseTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_GPS_JINATING);

        return SUCCESS;
    }

    /**
     * 拍照
     * @return
     */
    public String getSendPhoto() {
    	//MDC.put("modulename", "[cheliangMonitor-paizhao]");
    	log.info("进入拍照");
    	
        final String browseTitle = getText("gps.SendPhoto.title");
        int flag = 0;
        log.info("getSendPhoto's vin:" + vin);
        Map map = new HashMap();
        map.put("VEHICLE_VIN", vin);

        try {
            terminalViBean = (TerminalViBean) service.getObject(
                    "GPSNEW.getRealVehcileByVin", map);

            List < PhotoRoutewayBean > list = service.getObjects(
                    "GPS.getPhotoRoutteway", map);

            if (list != null && list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getROUTEWAY_NO() != null
                            && !list.get(i).getROUTEWAY_NO().equals("")) {
                        list.get(i).setROUTEWAY_NAME(
                                list.get(i).getROUTEWAY_NO() + "--"
                                        + list.get(i).getROUTEWAY_NAME());
                        photoSysMap.add(list.get(i));
                        flag++;
                    }

                }

                if (flag <= 0) {

                }
            }

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_GPS_SENDPHOTO);

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            // e.printStackTrace();
            // photoSysMap.put("0","--");
            log.info("进入拍照 error return");
            return ERROR;
        }
        log.info("进入拍照 return");
        return SUCCESS;
    }
    
    public String getSendPhotoList(){
    	//MDC.put("modulename", "[cheliangMonitor-piliangpaizhao]");
    	log.info("批量拍照vin：" + vin);
    	
    	log.info("批量拍照 return");
    	return SUCCESS;
    }

   

    // 组装json表格数据
    public Map getPaginationPhoto(List dayList) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < dayList.size(); i++) {

            PhotoMonitorInfo s = (PhotoMonitorInfo) dayList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getRow_num());

            cellMap.put("cell", new Object[] {s.getVehicle_ln(),
                    s.getVehicle_code(), s.getOperate_time(),
                    s.getChanle_code(), s.getUser_name(), s.getPhoto_file(),
                    s.getSend_result(), s.getVehicle_vin() });

            mapList.add(cellMap);

        }

        mapData.put("page", 0);// 从前台获取当前第page页
        mapData.put("total", 5);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    /*
     * 下行消息列表
     */
    public String downManageList() {
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        // System.out.println("getFivePhoto's vin:" + vin);

        String sortName = request.getParameter("sortname"); // 排序字段名
        String sortOrder = request.getParameter("sortorder"); // 升序OR降序
        String vin = request.getParameter("vin");
        log.info("1234:" + vin);
        Map map = new HashMap();
        map.put("VEHICLE_VIN", vin);
        map.put("sortName", sortName);
        map.put("sortOrder", sortOrder);

        List < MsgBean > MsgList = null;
        try {

            MsgList = service.getObjectsByPage("GPSNEW.DownMessageList", map,
                    0, 5);

            // MsgMap = getPaginationMsg(MsgList);

        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        MsgMap = getPaginationMsg(MsgList);
        return SUCCESS;
    }

    // 组装json表格数据
    public Map getPaginationMsg(List dayList) {
        List mapList = new ArrayList();
        Map MsgMap = new LinkedHashMap();
        for (int i = 0; i < dayList.size(); i++) {

            MsgBean s = (MsgBean) dayList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", i);

            cellMap.put("cell", new Object[] {s.getVEHICLE_LN(),
                    s.getUser_name(), s.getOperate_time(), s.getDeal_state(),
                    s.getRemark() });

            mapList.add(cellMap);

        }

        MsgMap.put("page", 1);// 从前台获取当前第page页
        MsgMap.put("total", 3);// 从数据库获取总记录数
        MsgMap.put("rows", mapList);

        return MsgMap;
    }
    
    private List < StuAlarm > stuAlarmList;
    /**
     * 获取TAB页学生告警
     * @return
     */
    public String getTabStudentAlarm() {
    	
    	log.info("家长页面加载孩子告警数据");
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String alarmtypeid = request.getParameter("alarmtypeid");
        String userid = request.getParameter("userid");
        //UserInfo user = getCurrentUser();
        Map < String, Object > map = new HashMap < String, Object >();
        map.put("alarm_type_id", alarmtypeid);
        map.put("PR_USERID", userid);
        
        try {

            stuAlarmList = service.getObjectsByPage(
                    "AlarmManage.getTAbStuAlarmInfosByUserid", map, 0, 3);
            log.info("家长页面加载孩子告警数据"+ stuAlarmList.size());
            //log.info("alarm length:" + stuAlarmList.size());
        } catch (BusinessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            stuAlarmList = null;
        }

        GpsAlermMap = getStuTabPagination(stuAlarmList);
        //log.info("info: " + map.get("rows"));

        return SUCCESS;
    }
    
    /**
     * 获取学生TAB页的MAP
     * @return
     */
    public Map getStuTabPagination(List stualarmList) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < stualarmList.size(); i++) {

            StuAlarm s = (StuAlarm) stualarmList.get(i);
            // s.setOperate_flag(Constants.ALARM_SYS_MAP.get(s.getOperate_flag()));
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getId());
            cellMap.put("cell", new Object[] {"", s.getVehicle_ln(),
                    s.getSite_name(), s.getStu_card_id(),
                    s.getAlarm_type_name(), s.getAlarm_type_comments(),
                    s.getOperate_flag(), s.getTerminal_time(), "",
                    s.getLongitude(), s.getLatitude(), s.getVehicle_vin(),
                    s.getOperate_flag() });
            mapList.add(cellMap);
        }
        mapData.put("page", 1);// 从前台获取当前第page页
        mapData.put("total", 5);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    public String xingcheNote(){
    	//MDC.put("modulename", "[cheliangMonitor-xingche]");
    	return SUCCESS;
    }

    /**
     * 树获取页面
     * @return
     */
    public String getTreeInit() {
        final String addBefTitle = getText("oilinfo.gettree.title");
        log.info(addBefTitle);
        UserInfo user = getCurrentUser();
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map.put("in_enterprise_id", user.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("GPS.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    public List < Alarm > getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List < Alarm > alarmList) {
        this.alarmList = alarmList;
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, OilManage om) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != om) {
            if (null != om.getRefuel_id()) {
                OperateLogFormator.format(sb, "refuel_id", om.getRefuel_id());
            }
        }
        return sb.toString();
    }
    
    /** 显示消息 * */
    private String ridemessage = null;
    
    private RouteChart queryObj;
    
    private RouteChart exportObj;
    
    private List < RouteChart > strideResult;
    
    private Map ridemap = new HashMap();
    
    private Map vinmap = new HashMap();
    private Map rorlist = new HashMap();
    private Map videoFilelist = new HashMap();
    
    public String readyPage() {
        queryObj = new RouteChart();
        queryObj.setBegTime(DateUtil.getCurrentDay()+" 00:00:00");
        queryObj.setEndTime(DateUtil.getCurrentDay()+" 23:59:59");
        queryObj.setUSERID(getCurrentUser().getUserID());
        //queryObj.setSt_ride_flag("22");
        return SUCCESS;
    }
    
    public String getRideInfo(){
        final String browseTitle = getText("menu2.xsccjl");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        try {
            if (null == queryObj) {
                queryObj = new RouteChart();
                queryObj.setBegTime(DateUtil.getCurrentDay()+" 00:00:00");
                queryObj.setEndTime(DateUtil.getCurrentDay()+" 23:59:59");  
                queryObj.setUSERID(getCurrentUser().getUserID());
                queryObj.setSt_ride_flag("22");
            }
            queryObj.setUser_organization_id(user.getOrganizationID());
            
            log.info(queryObj.getSt_ride_flag());
            if(queryObj.getSt_ride_flag().equals("22")){
            	queryObj.setVSS_FLAG(null);
            	queryObj.setSITE_FLAG(null);
            }else if(queryObj.getSt_ride_flag().equals("00")){
            	queryObj.setSITE_FLAG("0");
            	queryObj.setVSS_FLAG("0");
            }else if(queryObj.getSt_ride_flag().equals("01")){
            	queryObj.setSITE_FLAG("0");
            	queryObj.setVSS_FLAG("1");
            }else if(queryObj.getSt_ride_flag().equals("10")){
            	queryObj.setSITE_FLAG("1");
            	queryObj.setVSS_FLAG("0");
            }else if(queryObj.getSt_ride_flag().equals("11")){
            	queryObj.setSITE_FLAG("1");
            	queryObj.setVSS_FLAG("1");
            }

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);

            totalCount = service.getCount("GPSNEW.getCountStuRideInfos", queryObj);

            strideResult = (List < RouteChart >) service.getObjectsByPage(
                    "GPSNEW.getStuRideInfos", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.ridemap = getridePagination(strideResult, totalCount, pageIndex);// 转换map

            if (0 == strideResult.size()) {
                this.addActionMessage(getText("nodata.list"));
            }
            if (null != ridemessage) {
                addActionMessage(getText(ridemessage));
            }
            // 设置操作描述
            this.addOperationLog(browseTitle);
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STU_RIDE_QUR);
        } catch (BusinessException e) {
            log.info(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    public Map getridePagination(List list, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            RouteChart s = (RouteChart) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getId());
            cellMap.put("cell", new Object[] {
                    s.getStu_name(),
                    s.getStu_code(),
                    s.getStu_school(),
                    s.getStu_class(),
                    s.getRoute_name(),
                    s.getVehicle_ln(),
                    s.getDriver_name(),
                    s.getSichen_name(),
                    s.getTerminal_time(),
                    s.getSt_ride_flag(),
                    s.getMesg_flag()
                    });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 导出学生乘车记录
     * @return
     */
    public String exportStRide() {
        String exportTitle = getText("menu2.xsccjl");
        List < RouteChart > list = new ArrayList < RouteChart >();
        try {
            exportObj.setUser_organization_id(getCurrentUser().getOrganizationID());
            list = (List < RouteChart >) service.getObjects(
                    "StuRide.exportStuRideInfos", exportObj);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "StRide.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle(exportTitle);

            excelExporter.putAutoExtendSheets("exportStRide", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 设置下载文件属性
        HttpServletResponse response = ServletActionContext.getResponse();
        response
                .setHeader("Content-disposition", "attachment;filename=student_ride_info.xls");
        response.setContentType("application/msexcel; charset=\"utf-8\"");

        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return null;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
            // 设置操作描述
            this.addOperationLog("学生乘车记录导出");
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STU_RIDE_EXP);
        }
        // 导出文件成功
        return null;
    }
    
    /**
     * 新式表格过去车辆列表
     * @return
     */
    public String getVinInfoList(){
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	
    	String sortName = request.getParameter("sortname"); // 排序字段名
        String sortOrder = request.getParameter("sortorder"); // 升序OR降序

    	List data = null;
    	log.info(terminalViBean.getENTERPRISE_ID()+","+terminalViBean.getVEHICLE_LN());
    	
    	if(terminalViBean.getSelecttype().equals(CHELIANGTAB)){
    		terminalViBean.setSortname(sortName);
    		terminalViBean.setSortorder(sortOrder);
    		
    		if(terminalViBean.getVEHICLE_LN() != null && !terminalViBean.getVEHICLE_LN().equals("")){
            	terminalViBean.setVEHICLE_VIN(SearchUtil.formatSpecialChar(terminalViBean.getVEHICLE_LN()));
                terminalViBean.setCELLPHONE(SearchUtil.formatSpecialChar(terminalViBean.getVEHICLE_LN()));
                terminalViBean.setVEHICLE_LN(SearchUtil.formatSpecialChar(terminalViBean.getVEHICLE_LN()));
                try {
    				data = service.getObjects("GPSNEW.getInfos", terminalViBean);
    				
    				
    				if(data == null || data.size() == 0){
    					log.info("进行企业模糊查车");
    					terminalViBean.setENTER_SHORT_NAME(terminalViBean.getVEHICLE_LN());
    					data = service.getObjects("GPSNEW.getInfosByLikeORGANIZATION_ID", terminalViBean);
    				}
    				
    				vinmap = getLeftVinListPagination(data,data.size(),1);
    				
    			} catch (BusinessException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else{
            	try {
    				data = service.getObjects("GPSNEW.getInfos", terminalViBean);
    				
    				vinmap = getLeftVinListPagination(data,data.size(),1);
    				
    			} catch (BusinessException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            	
            }
    	}
    	// 线路标签
    	if(terminalViBean.getSelecttype().equals(XIANLUTAB)){
    		
    		if(terminalViBean.getRoute_ids() != null && !terminalViBean.getRoute_ids().equals("") 
    				&& !terminalViBean.getRoute_ids().equals("undefined")){
    			
    			terminalViBean.setRoute_ids(terminalViBean.getRoute_ids().substring(0, terminalViBean.getRoute_ids().length() - 1));
    			Map map = new HashMap < String, Object >();
    			log.info(terminalViBean.getRoute_ids());
                map.put("ENTERPRISE_ID", terminalViBean.getENTERPRISE_ID());
                map.put("ROUTE_IDS", terminalViBean.getRoute_ids());
                map.put("sortname", sortName);
                map.put("sortorder", sortOrder);
                
                try {
					data = service.getObjects("GPSNEW.SelectVbyLinsList", map);
					vinmap = getLeftVinListPagination(data,data.size(),1);
					
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}else{
    			try {
					Map < String, Object > map = new HashMap < String, Object >();
					map.put("ROUTE_NAME", SearchUtil.formatSpecialChar(terminalViBean.getROUTE_NAME()));
			        map.put("ORGANIZATION_ID", terminalViBean.getENTERPRISE_ID());
			        map.put("sortname", sortName);
	                map.put("sortorder", sortOrder);
					data = service.getObjects("GPS.getVehcileByRouteNameNew", map);
					
					vinmap = getLeftVinListPagination(data,data.size(),1);
				} catch (BusinessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    		
    	}
    	// 搜索标签
    	if(terminalViBean.getSelecttype().equals(SELECTTAB)){
    		terminalViBean.setSortname(sortName);
    		terminalViBean.setSortorder(sortOrder);
    		terminalViBean.setVEHICLE_VIN(SearchUtil.formatSpecialChar(terminalViBean.getVEHICLE_VIN()));
    		terminalViBean.setVEHICLE_LN(SearchUtil.formatSpecialChar(terminalViBean.getVEHICLE_LN()));
    		terminalViBean.setCELLPHONE(SearchUtil.formatSpecialChar(terminalViBean.getCELLPHONE()));
    		try {
				data = service.getObjects("GPSNEW.getSelectDInfos", terminalViBean);
				
				vinmap = getLeftVinListPagination(data,data.size(),1);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	// 5分钟循环列表
    	if(terminalViBean.getSelecttype().equals(THREADTAB)){
    		
    		
    		Map < String, Object > map = new HashMap < String, Object >();
			map.put("VEHICLE_VINS", terminalViBean.getVEHICLE_VIN());
			map.put("sortname", sortName);
            map.put("sortorder", sortOrder);
	        //map.put("ENTERPRISE_ID", terminalViBean.getENTERPRISE_ID());
    		
    		try {
				data = service.getObjects("GPSNEW.getVehcileByVinFiveMinite", map);
				
				vinmap = getLeftVinListPagination(data,data.size(),1);
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
    	
        //log.info("getVinInfoList's 车辆数：" + data.size());
       
    	
    	return SUCCESS;
    	
    }
    
    private Map getLeftVinListPagination(List list, int totalCount, int pageIndex) {
		// TODO Auto-generated method stub
    	List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        List openList = new ArrayList();
        List closeList = new ArrayList();
        List dvropenList = new ArrayList();
        List dvrcloseList = new ArrayList();
        
        AlarmRecode ar = new AlarmRecode();
        // 车辆在线排序
        for(int i = 0; i < list.size(); i++){
        	TerminalViBean s = new TerminalViBean();
        	s = (TerminalViBean) list.get(i);
        	if(checkACCandTime(s.getSTAT_INFO(),s.getTERMINAL_TIME())){
        		s.setColor("b");
        		if(s.getVEH_EXT_INFO() !=null && !s.getVEH_EXT_INFO().equals("")){
        			if(ar.dvr_info(s.getVEH_EXT_INFO()).equals("1")){
        				s.setVEH_EXT_INFO("1");
        			}
        			else{
        				s.setVEH_EXT_INFO("0");
        			}
        		}
        		else{
        			s.setVEH_EXT_INFO("0");
        		}
        		
        		openList.add(s);
        	}
        	else{
        		s.setColor("g");
        		s.setVEH_EXT_INFO("0");
        		closeList.add(s);
        	}
        	
        }
     // 车辆dvr在线排序
        for(int i = 0; i < openList.size(); i++){
        	TerminalViBean s = new TerminalViBean();
        	s = (TerminalViBean) list.get(i);
        	if(s.getVEH_EXT_INFO().equals("1")){
        		dvropenList.add(s);
        	}
        	else{
        		dvrcloseList.add(s);
        	}	
        }
        
        
        // 整体车辆排序，在线，dvr可用
        for (int i = 0; i < dvropenList.size(); i++) {
        	TerminalViBean s = (TerminalViBean) openList.get(i);
        	
            Map cellMap = new LinkedHashMap();
            
            cellMap.put("id", s.getVEHICLE_VIN());
            cellMap.put("cell", new Object[] {
            		s.getVEHICLE_VIN(),
                    s.getVEHICLE_LN(),
                    s.getColor(),
                    s.getVEH_EXT_INFO(),
                    s.getVEHICLE_VIN()
                    });
            mapList.add(cellMap);
        }
        for (int i = 0; i < dvrcloseList.size(); i++) {
        	TerminalViBean s = (TerminalViBean) openList.get(i);
        	
            Map cellMap = new LinkedHashMap();
            
            cellMap.put("id", s.getVEHICLE_VIN());
            cellMap.put("cell", new Object[] {
            		s.getVEHICLE_VIN(),
                    s.getVEHICLE_LN(),
                    s.getColor(),
                    s.getVEH_EXT_INFO(),
                    s.getVEHICLE_VIN()
                    });
            mapList.add(cellMap);
        }
        for (int i = 0; i < closeList.size(); i++) {
        	TerminalViBean s = (TerminalViBean) closeList.get(i);
        	
            Map cellMap = new LinkedHashMap();
            
            cellMap.put("id", s.getVEHICLE_VIN());
            cellMap.put("cell", new Object[] {
            		s.getVEHICLE_VIN(),
                    s.getVEHICLE_LN(),
                    s.getColor(),
                    s.getVEH_EXT_INFO(),
                    s.getVEHICLE_VIN()
                    });
            mapList.add(cellMap);
        }
        
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
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
     * 轨迹回放中行车记录查询
     * @param organization_id
     * @param vin
     * @param StrateTime
     * @param EndTime
     * @return
     */
    public String tabNodeSelectDwr(){
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	log.info("tabNodeSelectDwr is run");
    	
    	List data = null;
    	
    	String StartTime = terminalViBean.getSTART_TIME()+" 00:00:00";
    	String EndTime = terminalViBean.getSTART_TIME()+" 23:59:59";
    	terminalViBean.setSTART_TIME(StartTime);
    	terminalViBean.setEND_TIME(EndTime);
    	
    	//Map map = new HashMap < String, Object >();
    	//map.put("organization_id", organization_id);
    	//map.put("str_vin", vin);
    	//map.put("StrateTime", StrateTime);
    	//map.put("EndTime", EndTime);
    	
    	try {
    		data = service.getObjects("GPSNEW.getRunOils", terminalViBean);
			
			log.info("tabNodeSelectDwr's :"+ rorlist.size());
			rorlist = tabNodeSelectDwrPagination(data,data.size(),1);
			//return rorlist;
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
		}
		return SUCCESS;
    }
    
    private Map tabNodeSelectDwrPagination(List list, int totalCount, int pageIndex) {
    	List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
     // 整体车辆排序，在线，dvr可用
        for (int i = 0; i < list.size(); i++) {
        	RunOilRecord s = (RunOilRecord) list.get(i);
        	
            Map cellMap = new LinkedHashMap();
            
            cellMap.put("id", s.getID());
            cellMap.put("cell", new Object[] {
            		s.getOn_date(),
                    s.getOff_date(),
                    s.getMileage()
                    });
            mapList.add(cellMap);
        }
        
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 视频文件加载
     * @return
     */
    public String videoFileload(){
    	log.info("视频列表加载");
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	String sortName = request.getParameter("sortname"); // 排序字段名
        String sortOrder = request.getParameter("sortorder"); // 升序OR降序
        
        terminalViBean.setSortname(sortName);
		terminalViBean.setSortorder(sortOrder);
		String videolist = terminalViBean.getVideoFilelist();
		List list = new ArrayList();
		if(videolist != null && !videolist.equals("") && !videolist.equals("undefined")){
			String [] vlist = videolist.split("~");
			for(int i = 0; i < vlist.length; i++){
				if(vlist[i] !=null && !vlist[i].equals("")){
					//String[] vallist = vlist[i].split(",");
					String v = vlist[i].toString();
					list.add(v);
				}
			}
		}
		log.info("视频列表长度："+list.size());
		videoFilelist = videoFilePagination(list,list.size(),1,terminalViBean.getVEHICLE_VIN());
    	return SUCCESS;
    }
    private Map videoFilePagination(List list, int totalCount, int pageIndex,String flag) {
    	List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        // 整体车辆排序，在线，dvr可用
        if(flag.equals("HI")){
        	for (int i = 0; i < list.size(); i++) {
            	String[] strlist = ((String) list.get(i)).split(",");
            	String FileName = strlist[0];
            	String starttime = strlist[1];
            	String endtime = strlist[2];
            	String FileSize = strlist[3];
            	String FileType = strlist[4];
            	String wayno = strlist[5];
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = null;
                Date d2 = null;
                try {
                    d = inputFormat.parse(starttime);
                    d2 = inputFormat.parse(endtime);
                } catch (ParseException e) {
                    d = null;
                    d2 = null;
                }
                //long date2 = d.getTime();// Date.parse(new
                double  value = (d2.getTime()-d.getTime())/1000/60;
                //Math.rint(value);
                Map cellMap = new LinkedHashMap();
                cellMap.put("id", i+1);
                cellMap.put("cell", new Object[] {i+1,
                		FileName,
                		starttime.substring(11),
                		endtime.substring(11),
                		(int)Math.rint(value)+"分",
                		wayno,
                		FileSize,
                		"",
                		"",
                		"",
                		"",
                		"",
                		"",
                		flag,
                		wayno
                        });
                mapList.add(cellMap);
            }
        }
        if(flag.equals("DA")){
        	for (int i = 0; i < list.size(); i++) {
            	String[] strlist = ((String) list.get(i)).split(",");
            	String name = strlist[0];
            	String beginTime = strlist[1];
            	String endTime = strlist[2];
            	String sourceType = strlist[3];
            	String recordType = strlist[4];
            	String recordLen = strlist[5];
            	String planId = strlist[6];
            	String ssId = strlist[7];
            	String diskId = strlist[8];
            	String fileHandle = strlist[9];
            	String way = strlist[10];
            	//name+","+beginTime+","+endTime+","+sourceType+","+recordType+","+recordLen+","+planId+","+ssId+","+diskId+","+fileHandle+ways[k]"~";
                //System.out.println(name+","+beginTime+","+endTime+","+sourceType+","+recordType+","+recordLen+","+planId+","+ssId+","+diskId+","+fileHandle+","+way);
                SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date d = null;
                Date d2 = null;
                try {
                    d = inputFormat.parse(beginTime);
                    d2 = inputFormat.parse(endTime);
                } catch (ParseException e) {
                    d = null;
                    d2 = null;
                }
                //long date2 = d.getTime();// Date.parse(new
                double  value = (d2.getTime()-d.getTime())/1000/60;
                //Math.rint(value);
                Map cellMap = new LinkedHashMap();
                cellMap.put("id", i+1);
                cellMap.put("cell", new Object[] {i+1,
                		name,
                		beginTime.substring(11),
                		endTime.substring(11),
                		(int)Math.rint(value)+"分",
                		way,
                		recordLen,
                		sourceType,
                		recordType,
                		planId,
                		ssId,
                		diskId,
                		fileHandle,
                		flag,
                		way
                        });
                mapList.add(cellMap);
            }
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
    }
    
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLookflag() {
        return lookflag;
    }

    public void setLookflag(String lookflag) {
        this.lookflag = lookflag;
    }

    public SendCommandClient getSendCommandClient() {
        return sendCommandClient;
    }

    public void setSendCommandClient(SendCommandClient sendCommandClient) {
        this.sendCommandClient = sendCommandClient;
    }

    public String getLine_start_time() {
        return line_start_time;
    }

    public void setLine_start_time(String line_start_time) {
        this.line_start_time = line_start_time;
    }

    public String getLine_end_time() {
        return line_end_time;
    }

    public void setLine_end_time(String line_end_time) {
        this.line_end_time = line_end_time;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String routi_id) {
        this.route_id = routi_id;
    }

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}
	public Map getPhotomap() {
        return photomap;
    }

    public void setPhotomap(Map photomap) {
        this.photomap = photomap;
    }
    public Map < String, String > getCountryInfosMap() {
        return countryInfosMap;
    }

    public void setCountryInfosMap(Map < String, String > countryInfosMap) {
        this.countryInfosMap = countryInfosMap;
    }

    public Map < String, String > getProvinceInfosMap() {
        return provinceInfosMap;
    }

    public void setProvinceInfosMap(Map < String, String > provinceInfosMap) {
        this.provinceInfosMap = provinceInfosMap;
    }

    public Map < String, String > getCityInfosMap() {
        return cityInfosMap;
    }

    public void setCityInfosMap(Map < String, String > cityInfosMap) {
        this.cityInfosMap = cityInfosMap;
    }

    public String getLon() {
        return lngX;
    }

    public void setLon(String lngX) {
        this.lngX = lngX;
    }

    public String getLat() {
        return latY;
    }

    public void setLat(String latY) {
        this.latY = latY;
    }

    private List < PhotoRoutewayBean > photoSysMap = new ArrayList();

    public List < PhotoRoutewayBean > getPhotoSysMap() {
        return photoSysMap;
    }

    public void setPhotoSysMap(List < PhotoRoutewayBean > photoSysMap) {
        this.photoSysMap = photoSysMap;
    }
    public TerminalViBean getTerminalViBean() {
        return terminalViBean;
    }

    public void setTerminalViBean(TerminalViBean terminalViBean) {
        this.terminalViBean = terminalViBean;
    }
    public List < TerminalViBean > getVehcList() {
        return vehcList;
    }

    public void setVehcList(List < TerminalViBean > vehcList) {
        this.vehcList = vehcList;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }
    
    public Map getMsgMap() {
        return MsgMap;
    }

    public void setMsgMap(Map msgMap) {
        MsgMap = msgMap;
    }
    public Map getGpsAlermMap() {
        return GpsAlermMap;
    }

    public void setGpsAlermMap(Map gpsAlermMap) {
        GpsAlermMap = gpsAlermMap;
    }
    public List < StuAlarm > getStuAlarmList() {
        return stuAlarmList;
    }

    public void setStuAlarmList(List < StuAlarm > stuAlarmList) {
        this.stuAlarmList = stuAlarmList;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

	public String getOptpageid() {
		return optpageid;
	}

	public void setOptpageid(String optpageid) {
		this.optpageid = optpageid;
	}

	public String getRidemessage() {
		return ridemessage;
	}

	public void setRidemessage(String ridemessage) {
		this.ridemessage = ridemessage;
	}

	public RouteChart getQueryObj() {
		return queryObj;
	}

	public void setQueryObj(RouteChart queryObj) {
		this.queryObj = queryObj;
	}

	public RouteChart getExportObj() {
		return exportObj;
	}

	public void setExportObj(RouteChart exportObj) {
		this.exportObj = exportObj;
	}

	public List<RouteChart> getStrideResult() {
		return strideResult;
	}

	public void setStrideResult(List<RouteChart> strideResult) {
		this.strideResult = strideResult;
	}

	public Map getRidemap() {
		return ridemap;
	}

	public void setRidemap(Map ridemap) {
		this.ridemap = ridemap;
	}

	public Map getVinmap() {
		return vinmap;
	}

	public void setVinmap(Map vinmap) {
		this.vinmap = vinmap;
	}

	public String getTimetab_time() {
		return timetab_time;
	}

	public void setTimetab_time(String timetab_time) {
		this.timetab_time = timetab_time;
	}

	public Map getRorlist() {
		return rorlist;
	}

	public void setRorlist(Map rorlist) {
		this.rorlist = rorlist;
	}

	public Map getVideoFilelist() {
		return videoFilelist;
	}

	public void setVideoFilelist(Map videoFilelist) {
		this.videoFilelist = videoFilelist;
	}
    
}
