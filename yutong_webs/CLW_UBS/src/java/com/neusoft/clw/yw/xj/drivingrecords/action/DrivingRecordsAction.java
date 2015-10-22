package com.neusoft.clw.yw.xj.drivingrecords.action;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.drivingrecords.ds.DrivingRecords;
import com.neusoft.clw.yw.xj.drivingrecords.ds.Type;
import com.neusoft.clw.yw.xj.drivingrecords.ds.VehcileInfo;
import com.neusoft.clw.yw.xs.poimanage.action.GPSUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="jin.p@neusoft.com">Jinp</a>
 * @version Revision: 0.1 Date: Aug 17, 2011 09:18:14 PM
 */
public class DrivingRecordsAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    private DrivingRecords drivingRecords;

    private String yourTimeScale = "1";

    private List < DrivingRecords > drivingRecordsList;

    @SuppressWarnings("unchecked")
    private Map map = new HashMap();

    @SuppressWarnings("unchecked")
    private Map mapVeh = new HashMap();

    private String chooseorgid;
      
    private Alarm alarm;
    
    public Alarm getAlarm() {
		return alarm;
	}

	public void setAlarm(Alarm alarm) {
		this.alarm = alarm;
	}
    @SuppressWarnings("unchecked")
    public Map getMap() {
        return map;
    }

    @SuppressWarnings("unchecked")
    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMapVeh() {
        return mapVeh;
    }

    public void setMapVeh(Map mapVeh) {
        this.mapVeh = mapVeh;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public DrivingRecords getDrivingRecords() {
        return drivingRecords;
    }

    public void setDrivingRecords(DrivingRecords drivingRecords) {
        this.drivingRecords = drivingRecords;
    }

    public String getChooseorgid() {
        return chooseorgid;
    }

    public void setChooseorgid(String chooseorgid) {
        this.chooseorgid = chooseorgid;
    }

    public String getYourTimeScale() {
        return yourTimeScale;
    }

    public void setYourTimeScale(String yourTimeScale) {
        this.yourTimeScale = yourTimeScale;
    }

    /**
     * 页面初始化
     */
    public String execute() throws Exception {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("drivingrecords.location"));

        HttpServletRequest request = ServletActionContext.getRequest();
        if (this.drivingRecords == null) {
            this.drivingRecords = new DrivingRecords();
        }
        if (this.drivingRecords.getStart_time() == null|| this.drivingRecords.getEnd_time() == null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            final long HOUR = 3600000;// 1000 * 60 * 60;

            long dateTime = new Date().getTime();// + HOUR * 8;
            this.drivingRecords.setStart_time(sdf.format(dateTime - HOUR * 2));
            this.drivingRecords.setEnd_time(sdf.format(new Date(dateTime)));
        }
        request.setAttribute("typeIdMap", Type.TYPE_ID_MAP);
        request.setAttribute("timeScaleMap", Type.TIME_SCALE_MAP);
        return SUCCESS;
    }

    /**
     * 行车记录查询
     * @return
     */
    public String gpsInfoList() {
        log.info("****************行车记录Action处理开始*************");
        UserInfo user = getCurrentUser();
        int totalCountDay = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {
            if (null == drivingRecords) {
                drivingRecords = new DrivingRecords();
            }
            // 查询时段处理:按时段查询,本周,本月,本季度,本年
            processDate(yourTimeScale, request);
            // 对页面两次encodeURI的车牌号进行解码
            if (drivingRecords.getVehicle_ln() != null) {
                String vln = drivingRecords.getVehicle_ln();
                try {
                    drivingRecords.setVehicle_ln(java.net.URLDecoder.decode(
                            vln, "utf-8"));
                } catch (UnsupportedEncodingException e) {
                    log.error("url decoder error:", e);
                }
            }
            // 接收分页,排序信息
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            // 为null,非数字,则初始化
            /**
            if (rpNum == null || !rpNum.matches("^[0-9]*$")) {
                rpNum = "20";
            }
            if (pageIndex == null || !pageIndex.matches("^[0-9]*$")) {
                pageIndex = "1";
            }**/
            drivingRecords.setSortname(sortName);
            drivingRecords.setSortorder(sortOrder);
            if (user != null) {
                drivingRecords.setUser_id(user.getUserID());
            }
            if (drivingRecords != null && drivingRecords.getTypeId() != null
                    && !"".equals(drivingRecords.getTypeId())) {
                String getCountStr = "drivingRecords.get_count_by_partition"; //初始值就是通过分区查
                String getListStr = "";
                String typeId = drivingRecords.getTypeId();
                Map < String, Object > enmap = new HashMap < String, Object >(6);
                enmap.put("p_dayfrom", drivingRecords.getStart_time());
                enmap.put("p_dayto", drivingRecords.getEnd_time());
                enmap.put("p_vehicle_vin", drivingRecords.getVehicle_vin());
                enmap.put("p_alarm_type_id",null);
                enmap.put("p_count", null);
                if ("t01".equalsIgnoreCase(typeId)) {
                    //getCountStr = "drivingRecords.getGpsInfoCount";
                    getListStr = "drivingRecords.getGpsInfoList";
                    enmap.put("p_parttable", "CLW_YW_TERMINAL_RECORD_T");
                    enmap.put("p_partpreflag", "TERMINAL_RECORD_");
                    enmap.put("p_datestr","TERMINAL_TIME");
                }else if("t02".equalsIgnoreCase(typeId)){
                	 //getCountStr = "drivingRecords.getTerminalCount";
                	enmap.put("p_parttable", "CLW_YW_TERMINAL_RECORD_T");
                    enmap.put("p_partpreflag", "TERMINAL_RECORD_");
                    enmap.put("p_datestr","TERMINAL_TIME");
                    getListStr = "drivingRecords.getTerminalListForSpeed";
                } else if ("t03".equalsIgnoreCase(typeId)
                        || "t04".equalsIgnoreCase(typeId)
                        || "t05".equalsIgnoreCase(typeId)
                        || "t08".equalsIgnoreCase(typeId)
                        || "t09".equalsIgnoreCase(typeId)
                        || "t10".equalsIgnoreCase(typeId)
                        || "t12".equalsIgnoreCase(typeId)
                        || "t13".equalsIgnoreCase(typeId)) {
                    //getCountStr = "drivingRecords.getTerminalCount";
                	enmap.put("p_parttable", "CLW_YW_TERMINAL_RECORD_T");
                    enmap.put("p_partpreflag", "TERMINAL_RECORD_");
                    enmap.put("p_datestr","TERMINAL_TIME");
                    getListStr = "drivingRecords.getTerminalList";
                } else if ("t06".equalsIgnoreCase(typeId)
                        || "t07".equalsIgnoreCase(typeId)
                        || "t16".equalsIgnoreCase(typeId)
                        || "t20".equalsIgnoreCase(typeId)) {
                    enmap.put("p_parttable", "CLW_YW_SEC_DATA_T");
                    enmap.put("p_partpreflag", "SEC_DATA_");
                    enmap.put("p_datestr","TEMINAL_TIME");
                    //getCountStr = "drivingRecords.getSecDataCount";
                    getListStr = "drivingRecords.getSecDataList"; //            
                } else if ("t11".equalsIgnoreCase(typeId)
                        || "t17".equalsIgnoreCase(typeId)
                        || "t18".equalsIgnoreCase(typeId)) {
                    enmap.put("p_parttable", "CLW_YW_SEC_DATA_T");
                    enmap.put("p_partpreflag", "SEC_DATA_");
                    enmap.put("p_datestr","TEMINAL_TIME");
                    //getCountStr = "drivingRecords.getSecDataCount";
                    getListStr = "drivingRecords.getSecDataList"; //            
                } else if ("t14".equalsIgnoreCase(typeId)
                        || "t15".equalsIgnoreCase(typeId)
                        || "t19".equalsIgnoreCase(typeId)) {
                    enmap.put("p_parttable", "CLW_YW_MINUTE5_DATA_T");
                    enmap.put("p_partpreflag", "MINUTE5_DATA_");
                    enmap.put("p_datestr","TEMINAL_TIME");
                    //getCountStr = "drivingRecords.getMinute5DataCount";
                    getListStr = "drivingRecords.getMinute5DataList";
                } else if ("t21".equalsIgnoreCase(typeId)
                        || "t22".equalsIgnoreCase(typeId)
                        || "t23".equalsIgnoreCase(typeId)) {
                     enmap.put("p_parttable", "CLW_YW_MALARMD_T");
                     enmap.put("p_partpreflag", "MALARMD_");
                     enmap.put("p_datestr","TEMINAL_TIME");
                    if("t21".equalsIgnoreCase(typeId)){
                        enmap.put("p_alarm_type_id","48");
                        drivingRecords.setAlarm_type_id("48");
                    }
                    else if("t22".equalsIgnoreCase(typeId)){
                        enmap.put("p_alarm_type_id","47");
                        drivingRecords.setAlarm_type_id("47");
                    }
                    else if("t23".equalsIgnoreCase(typeId)){
                         enmap.put("p_alarm_type_id","32");
                        drivingRecords.setAlarm_type_id("32");
                    }
                    //getCountStr = "drivingRecords.getMalarmdCount";
                    getListStr = "drivingRecords.getMalarmdList";
                }
                if(StringUtils.isNotEmpty(drivingRecords.getStart_time())){
                	drivingRecords.setPartition_time(drivingRecords.getStart_time().substring(0, 10).replaceAll("-", ""));
                }              
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                log.info(typeId+"查询总数开始："+formatter.format(new Date()));
               
                service.getObject(getCountStr, enmap); 
                totalCountDay = (Integer)enmap.get("p_count");
             
                //totalCountDay = service.getCount(getCountStr, drivingRecords);  老写法
              
                log.info(typeId+"查询总数结束："+formatter.format(new Date()));
                log.info(typeId+"查询分页开始："+formatter.format(new Date()));
                drivingRecordsList = service.getObjectsByPage(getListStr,
                        drivingRecords, (Integer.parseInt(pageIndex) - 1)
                                * Integer.parseInt(rpNum), Integer
                                .parseInt(rpNum));
             // 转换map
                if (drivingRecordsList != null && drivingRecordsList.size() > 0) {
               	
                	log.info(typeId+"数据转换map开始："+formatter.format(new Date()));
                    this.map = getPagination(drivingRecordsList, drivingRecords,
                            totalCountDay, pageIndex);
                    log.info(typeId+"数据转换map结束："+formatter.format(new Date()));
                }
            }           
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error("行车记录查询异常");
            return ERROR;
        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error("行车记录查询异常");
            return ERROR;
        } finally {
            // 设置操作描述
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_DRIVING_RECORDS_QUERY_MID);
            addOperationLog("行车记录查询");
        }
        return SUCCESS;
    }

    /**
     * 根据时段,转换时间(还没有处理自定义时段的数据)
     * @param yourTimeScale 用户选择的时段还是自定义时段
     */
    private void processDate(String yourTimeScale, HttpServletRequest req) {
        if (this.drivingRecords == null) {
            this.drivingRecords = new DrivingRecords();
        }
        // 时段
        if ("0".equals(yourTimeScale)) {
            // week //month //quarter //year
            if ("aweek".equalsIgnoreCase(this.drivingRecords.getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil
                        .getCurrentWeekFirst());
                this.drivingRecords.setEnd_time(DateUtil.getCurrentWeekLast());

            } else if ("bmonth".equalsIgnoreCase(this.drivingRecords
                    .getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil.getMonthFirstDay());
                this.drivingRecords.setEnd_time(DateUtil.getMonthLastDay());

            } else if ("cquarter".equalsIgnoreCase(this.drivingRecords
                    .getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil
                        .getSeasonFirstDay(DateUtil.getSeason()));
                this.drivingRecords.setEnd_time(DateUtil
                        .getSeasonLastDay(DateUtil.getSeason()));

            } else if ("dyear".equalsIgnoreCase(this.drivingRecords
                    .getTimeScale())) {
                this.drivingRecords.setStart_time(DateUtil
                        .getCurrentYearFirst());
                this.drivingRecords.setEnd_time(DateUtil.getCurrentYearLast());
            }
            // 自定义时段
        } else if (yourTimeScale == null || "".equals(yourTimeScale)
                || "1".equals(yourTimeScale)) {
            String sdate = this.drivingRecords.getStart_time();
            String edate = this.drivingRecords.getEnd_time();
            if (sdate != null && !"".equals(sdate) && sdate.indexOf(":") == -1) {
                this.drivingRecords.setStart_time(sdate + " 00:00:00");
            }
            if (edate != null && !"".equals(edate) && edate.indexOf(":") == -1) {
                this.drivingRecords.setEnd_time(edate + " 23:59:59");
            }
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

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, DrivingRecords dr) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != dr) {
            if (null != dr.getVehicle_ln()) {
                OperateLogFormator.format(sb, "Vehicle ln", dr.getVehicle_ln());
            }
        }
        return sb.toString();
    }

    /**
     * FFFF转字空符串
     * @param s
     * @return
     */
    private String ffffToTemp(String s) {
        if (s == null || "FFFF".equalsIgnoreCase(s)) {
            return "";
        } else {
            return s;
        }
    }
   
    /**
     * 截取小数点后2位
     * @param s
     * @return
     */
    private String subNumberString(String s) {
    	String sub ="";
        if(StringUtils.isNotEmpty(s) && s.matches("^(-?\\d+)(\\.\\d+)?")){
    	DecimalFormat df1 = new DecimalFormat("0.00");
    	sub = df1.format(Double.parseDouble(s));
        }
    	return sub;
    }
    /**
     * 转换Map
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    @SuppressWarnings("unchecked")
    public Map getPagination(List drivingRecordsList,
            DrivingRecords drivingRecords, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        Map cellMap;
        for (int i = 0; i < drivingRecordsList.size(); i++) {

            DrivingRecords s = (DrivingRecords) drivingRecordsList.get(i);

            cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVehicle_vin());
            // 没有typeId(类型ID),就不应该有数据
            if (drivingRecords != null && drivingRecords.getTypeId() != null
                    && !"".equals(drivingRecords.getTypeId())) {
                // GPS信息CLW_YW_TERMINAL_RECORD _T
                if ("t01".equals(drivingRecords.getTypeId())) {
                    cellMap.put("id", UUIDGenerator.getUUID());
                    /*
                     * String url =
                     * "<a href=\"#\" onclick=\"javascript:choiceCar('" +
                     * s.getLongitude() + "','" + s.getLatitude() + "','" +
                     * s.getVehicle_vin() +
                     * "','');\" target=\"_blank\">查看地图</a>";
                     */
                    if ((s.getOverspeed_alert() != null && s
                            .getOverspeed_alert().equals("2"))
                            || (s.getFatigue_alert() != null && s
                                    .getFatigue_alert().equals("2"))
                            || (s.getGps_alert() != null && s.getGps_alert()
                                    .equals("2"))
                            || (s.getSos() != null && s.getSos().equals("2"))
                            || (s.getRapid_alert() != null && s
                                    .getRapid_alert().equals("2"))
                            || (s.getRegion_overspeed_alert() != null && s
                                    .getRegion_overspeed_alert().equals("2"))
                            || (s.getRegion_in_alert() != null && s
                                    .getRegion_in_alert().equals("2"))
                            || (s.getRegion_out_alert() != null && s
                                    .getRegion_out_alert().equals("2"))
                            || (s.getRegion_openclosedoor_alert() != null && s
                                    .getRegion_openclosedoor_alert()
                                    .equals("2"))) {
                        s.setColor("r");// 红色
                    } else {
                        s.setColor("b");
                    }
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getCreate_time(), ffffToTemp(s.getLongitude()),
                            ffffToTemp(s.getLatitude()),
                            diretionToStr(s.getDirection()),
                            subNumberString(ffffToTemp(s.getGps_speeding())), "",
                            ffffToTemp(s.getVehicle_vin()),
                            ffffToTemp(s.getDirection()),
                            s.getColor()});
                    // 速度CLW_YW_TERMINAL_RECORD _T////
                } else if ("t02".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getCreate_time(), 
                            subNumberString(ffffToTemp(s.getSpeeding())),
                            ffffToTemp(s.getEngine_rotate_speed()),
                            ffffToTemp(s.getMileage()) });
                    // 转速CLW_YW_TERMINAL_RECORD _T////
                } else if ("t03".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getCreate_time(),
                            ffffToTemp(s.getEngine_rotate_speed()),
                            subNumberString(ffffToTemp(s.getSpeeding())),
                            ffffToTemp(s.getMileage()) });
                    // 里程信息CLW_YW_TERMINAL_RECORD _T////
                } else if ("t04".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getCreate_time(), ffffToTemp(s.getMileage()),
                            ffffToTemp(s.getEngine_rotate_speed()),
                            subNumberString(ffffToTemp(s.getSpeeding())) });
                    // 开关量信息CLW_YW_TERMINAL_RECORD _T
                } else if ("t05".equals(drivingRecords.getTypeId())) {
                    String onof = s.getOn_off();
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getCreate_time(), splitOnOf(onof)[0],
                            splitOnOf(onof)[1], splitOnOf(onof)[2],
                            splitOnOf(onof)[3], splitOnOf(onof)[4],
                            splitOnOf(onof)[5], splitOnOf(onof)[6],
                            splitOnOf(onof)[7], splitOnOf(onof)[8],
                            splitOnOf(onof)[9], splitOnOf(onof)[10],
                            splitOnOf(onof)[11], splitOnOf(onof)[12],
                            splitOnOf(onof)[13], splitOnOf(onof)[14],
                            splitOnOf(onof)[15], splitOnOf(onof)[16],
                            splitOnOf(onof)[17], splitOnOf(onof)[18],
                            splitOnOf(onof)[19], splitOnOf(onof)[20],
                            splitOnOf(onof)[21], splitOnOf(onof)[22],
                            splitOnOf(onof)[23], splitOnOf(onof)[24],
                            splitOnOf(onof)[25], splitOnOf(onof)[26],
                            splitOnOf(onof)[27], splitOnOf(onof)[28],
                            splitOnOf(onof)[29], splitOnOf(onof)[30],
                            splitOnOf(onof)[31] });
                    // CAN-发动机转速
                } else if ("t06".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(),
                            ffffToTemp(s.getEngine_rotate_speed()) });
                    // CAN-扭矩(%)
                } else if ("t07".equals(drivingRecords.getTypeId())) {
                    double torque = 0;
                    DecimalFormat df = new DecimalFormat();
                    if (s.getTorque() != null && !"".equals(s.getTorque())
                            && s.getTorque().matches("^(-?\\d+)(\\.\\d+)?")) {
                        try {
                            torque = Double.valueOf(s.getTorque());
                            torque=Double.parseDouble(df.format(torque * 100));
//                            torque = torque * 100;
                        } catch (NumberFormatException nfe) {
                            log.error("转换CAN-扭矩为百分比出错");
                        }
                    }
                    cellMap.put("cell", new Object[] {
                            s.getTerminal_time(),
                            s.getWrite_time(),
                            ffffToTemp(s.getTorque()),
                            (s.getTorque() == null || "FFFF".equalsIgnoreCase(s
                                    .getTorque())) ? "" : torque });
                    // CAN-蓄电池电压CLW_YW_TERMINAL_RECORD _T
                } else if ("t08".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getCreate_time(),
                            ffffToTemp(s.getBattery_voltage()) });
                    // CAN-外部电压CLW_YW_TERMINAL_RECORD _T
                } else if ("t09".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell",
                            new Object[] {s.getTerminal_time(),
                                    s.getCreate_time(),
                                    ffffToTemp(s.getExt_voltage()) });
                    // CAN-供电状态CLW_YW_TERMINAL_RECORD _T
                } else if ("t10".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell",
                            new Object[] {s.getTerminal_time(),
                                    s.getCreate_time(),
                                    ffffToTemp(s.getPower_state()) });
                    // CAN-发动机油温
                } else if ("t11".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(),
                            ffffToTemp(s.getOil_temperature()) });
                    // CAN-发动机水温CLW_YW_TERMINAL_RECORD _T
                } else if ("t12".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell",
                            new Object[] {s.getTerminal_time(),
                                    s.getCreate_time(),
                                    ffffToTemp(s.getE_water_temp()) });
                    // CAN-机油压力CLW_YW_TERMINAL_RECORD _T
                } else if ("t13".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell",
                            new Object[] {s.getTerminal_time(),
                                    s.getCreate_time(),
                                    ffffToTemp(s.getOil_pressure()) });
                    // CAN-大气压力
                } else if ("t14".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell",
                            new Object[] {s.getTerminal_time(),
                                    s.getWrite_time(),
                                    ffffToTemp(s.getAir_pressure()) });
                    // CAN-进气温度
                } else if ("t15".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(),
                            ffffToTemp(s.getAir_inflow_tpr()) });
                    // CAN-车速
                } else if ("t16".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell",
                            new Object[] {s.getTerminal_time(),
                                    s.getWrite_time(),
                                    subNumberString(ffffToTemp(s.getVehicle_speed())) });
                    // CAN-冷却剂温度
                } else if ("t17".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(),
                            ffffToTemp(s.getColder_temperature()) });
                    // CAN-累计油耗
                } else if ("t18".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(), ffffToTemp(s.getOil_total()) });
                    // CAN-发动机运行时间
                } else if ("t19".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(),
                            ffffToTemp(s.getEnghrrev_t_e_h()) });
                    // CAN-瞬时油耗
                } else if ("t20".equals(drivingRecords.getTypeId())) {
                    cellMap
                            .put("cell", new Object[] {s.getTerminal_time(),
                                    s.getWrite_time(),
                                    ffffToTemp(s.getOil_instant()) });
                    // Extend-急减速
                } else if ("t21".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(), ffffToTemp(s.getAlarm_time()) });
                    // Extend-急加速
                } else if ("t22".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(), ffffToTemp(s.getAlarm_time()) });
                    // Extend-超速
                } else if ("t23".equals(drivingRecords.getTypeId())) {
                    cellMap.put("cell", new Object[] {s.getTerminal_time(),
                            s.getWrite_time(), ffffToTemp(s.getAlarm_time()),
                             subNumberString(ffffToTemp(s.getMaxspeed())) });
                }
            }
            mapList.add(cellMap);
        }
        // 从前台获取当前第page页
        mapData.put("page", pageIndex);
        // 从数据库获取总记录数
        mapData.put("total", totalCountDay);
        mapData.put("rows", mapList);
        return mapData;
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
        } else if (d >= 170 && d < 190) {
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

    /**
     * 分解开关量
     * @param onOf
     * @return
     */
    private char[] splitOnOf(String onOf) {
        char[] ss = new char[32];
        if (onOf == null || "".equals(onOf)) {
            return ss;
        }
        
        // 重置显示顺序
        for (int i = 0; i < onOf.length(); i++) {
            try {
                switch (i) {
                case 0:
                    // 17 ABS工作
                    ss[i] = onOf.charAt(17);
                    break;
                case 1:
                    // 8 SOS开关
                    ss[i] = onOf.charAt(8);
                    break;
                case 2:
                    // 12 点火
                    ss[i] = onOf.charAt(12);
                    break;
                case 3:
                    // 13 油压报警
                    ss[i] = onOf.charAt(13);
                    break;
                case 4:
                    // 14 制动气压报警
                    ss[i] = onOf.charAt(14);
                    break;
                case 5:
                    // 7 雾灯
                    ss[i] = onOf.charAt(7);
                    break;
                case 6:
                    // 6 远光灯信号
                    ss[i] = onOf.charAt(6);
                    break;
                case 7:
                    // 5 右转向灯信号
                    ss[i] = onOf.charAt(5);
                    break;
                case 8:
                    // 4 喇叭信号
                    ss[i] = onOf.charAt(4);
                    break;
                case 9:
                    // 3 左转向灯信号
                    ss[i] = onOf.charAt(3);
                    break;
                case 10:
                    // 2 倒档信号
                    ss[i] = onOf.charAt(2);
                    break;
                case 11:
                    // 1 近光灯信号
                    ss[i] = onOf.charAt(1);
                    break;
                case 12:
                    // 0 制动状态
                    ss[i] = onOf.charAt(0);
                    break;
                case 13:
                    // 15 严重故障
                    ss[i] = onOf.charAt(15);
                    break;
                case 14:
                    // 11 水位低报警
                    ss[i] = onOf.charAt(11);
                    break;
                case 15:
                    // 10 制动蹄片磨损报警
                    ss[i] = onOf.charAt(10);
                    break;
                case 16:
                    // 9 空滤堵塞报警
                    ss[i] = onOf.charAt(9);
                    break;
                case 17:
                    // 23 燃油警告
                    ss[i] = onOf.charAt(23);
                    break;
                case 18:
                    // 22 空调状态
                    ss[i] = onOf.charAt(22);
                    break;
                case 19:
                    // 21 空挡信号
                    ss[i] = onOf.charAt(21);
                    break;
                case 20:
                    // 20 前门信号
                    ss[i] = onOf.charAt(20);
                    break;
                case 21:
                    // 19 中门信号
                    ss[i] = onOf.charAt(19);
                    break;
                case 22:
                    // 18 缓速器工作
                    ss[i] = onOf.charAt(18);
                    break;
                case 23:
                    // 16 加热器工作
                    ss[i] = onOf.charAt(16);
                    break;
                case 24:
                    // 31 离合器状态
                    ss[i] = onOf.charAt(31);
                    break;
                case 25:
                    // 30 缓速器高温报警信号
                    ss[i] = onOf.charAt(30);
                    break;
                case 26:
                    // 29 仓温报警信号
                    ss[i] = onOf.charAt(29);
                    break;
                case 27:
                    // 28 机滤堵塞信号
                    ss[i] = onOf.charAt(28);
                    break;
                case 28:
                    // 27 燃油堵塞信号
                    ss[i] = onOf.charAt(27);
                    break;
                case 29:
                    // 26 机油温度报警信号
                    ss[i] = onOf.charAt(26);
                    break;
                case 30:
                    // 25 开关量31
                    ss[i] = onOf.charAt(25);
                    break;
                case 31:
                    // 24 开关量32
                    ss[i] = onOf.charAt(1);
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                // 得到的字符串不足32位
                log.error("开关量ON_OF不足32位,", e);
                break;
            }
        }
        return ss;
    }

    /**
     * 展示关联车辆列表-跳转页面
     */
    public String vehListSearch() {
        return SUCCESS;
    }

    /**
     * 列表车辆
     */
    public String vehList() {
        final String vehTitle = getText("oilinfo.veh.title");
        /** 车辆列表 **/
        List < VehcileInfo > vehcList;
        int totalCount = 0;
        UserInfo user = getCurrentUser();

        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        // 每页显示条数
        String rpNum = request.getParameter("rp");
        // 当前页码
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        String vehicle_ln = request.getParameter("vehicle_ln");
        try {
            VehcileInfo vehinfo = new VehcileInfo();
            vehinfo.setSortname(sortName);
            vehinfo.setSortorder(sortOrder);
            
            if (vehicle_ln != null) {
                vehinfo.setVehicle_ln(vehicle_ln);
            }
            
            totalCount = service.getCount("drivingRecords.getCountVeh", vehinfo);

            vehcList = (List < VehcileInfo >) service
                    .getObjectsByPage("drivingRecords.getInfosVeh", vehinfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.mapVeh = getPaginationVeh(vehcList, totalCount, pageIndex);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(vehTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPaginationVeh(List vehcList, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < vehcList.size(); i++) {

            VehcileInfo s = (VehcileInfo) vehcList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVehicle_vin());

            if(null == s.getVehicle_ln() || s.getVehicle_ln().length() == 0) {
                s.setVehicle_ln("暂无车牌");
            }
            
            cellMap.put("cell", new Object[] {s.getVehicle_ln(),
                    s.getVehicle_vin(), 
                    s.getEnterprise_name() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    public String gpsBrowse() {

        try {
            if (null == alarm) {
                alarm = new Alarm();
            }
            String vehln = (String) service.getObject("drivingRecords.getvehln", alarm
                    .getVehicle_vin());
            alarm.setVehicle_ln(vehln);
            log.info("fangxiang:" + alarm.getDirection());
            String lon = alarm.getLongitude();
            String lat = alarm.getLatitude();
            GPSUtil gpsUtil = new GPSUtil();
            String point = gpsUtil.getOneXY(lon, lat);
            if (point != null && point != "") {
                String[] p = point.split(",");
                alarm.setLongitude(p[0].toString());
                alarm.setLatitude(p[1].toString());
            }

            //service.update("GPS.updateDealFlag", alarm);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(e);
            return ERROR;
        }
        return SUCCESS;
    } 
}
