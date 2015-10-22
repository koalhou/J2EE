package com.neusoft.clw.itsmanage.oilmanage.baddriving.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.itsmanage.oilmanage.baddriving.domain.BadDrivDay;
import com.neusoft.clw.itsmanage.oilmanage.baddriving.domain.BadDriving;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="yugang-neu@neusoft.com">Gang.Yu</a>
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */
public class BadDrivingAction extends PaginationAction {

    private List < BadDriving > list;

    private List < BadDrivDay > dayList;

    private List < BadDrivDay > sumList;

    private BadDriving baddriving;

    private HashMap Params = new HashMap();

    private VehcileInfo vehcileInfo;

    private BadDrivDay baddrivday;
    
    private BadDriving baddetail;
    
    private String vehicle_vin;

    private String vehicle_ln;

    private String start_time;

    private String end_time;

    private String time_list;

    private String month;

    private String quarter;

    private String year;

    private String chooseorgid;

    private String selectradio;

    private String pageParam;

    private String rpParm;

    /**
     * 不良驾驶ID
     */
    private String alarm_type_id_eq;
    
    private String organization_id;
    /**
     * 过滤标识位-对全0数据进行过滤
     */
    private String fileterFlag;
    
    private Map map = new HashMap();

    private Map mapDetail = new HashMap();

    private String timeline;

    private String code_name;
    
    /** 显示消息 **/
    private String message = null;
    
    /** 车辆列表 **/
    private List < VehcileInfo > vehcList;
    
    /** service共通类 */
    private transient Service service;
    
    /**
     * 不良驾驶页面浏览
     * @return
     */

    public String badDrivList() {
        final String browseTitle = getText("baddirve.browseCar.title");
    	MDC.put("modulename", "[baddriving]");
        UserInfo user = getCurrentUser();
        int totalCountDay = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        if (null == baddrivday) {
        	baddrivday = new BadDrivDay();
        	baddrivday.setOrganization_id(user.getOrganizationID());
        	baddrivday.setTime_list("week");
        }else if(baddrivday.getOrganization_id() == null || "".equals(baddrivday.getOrganization_id())){
        	baddrivday.setOrganization_id(user.getOrganizationID());
        }

        if (null != baddrivday.getVehicle_vin() && !"".equals(baddrivday.getVehicle_vin())) {
            baddrivday.setVehicle_ln(SearchUtil.formatSpecialChar(baddrivday.getVehicle_vin()));
        }
        
        String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

        baddrivday.setSortname(sortName);
        baddrivday.setSortorder(sortOrder);
        
    	log.info("[Organization_id:" + baddrivday.getOrganization_id() + ",Time_list:" + baddrivday.getTime_list()
    			+ ",Vehicle_ln:" + baddrivday.getVehicle_ln() +"]:"+browseTitle+"开始");
        try {

            totalCountDay = service.getCount(
                    "BadDriving.getBadDrivDayCount", baddrivday);
            dayList = service.getObjectsByPage(
                    "BadDriving.getBadDrivDayList", baddrivday, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer
                            .parseInt(rpNum));
            sumList = service.getObjects("BadDriving.getBadDrivDayListSum",
                    baddrivday);

            this.map = getPagination(dayList, sumList, totalCountDay, pageIndex);
            if (dayList.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }

            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_BADDRIVING_QUERY_ID);
            log.info(browseTitle+"结束");
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle+"异常", e);
            return ERROR;
        } 
        return SUCCESS;
    }

    public String blankAction() {
    	MDC.put("modulename", "[baddriving]");
        try {
            start_time=DateUtil.getPreNDay(-2);
            end_time = DateUtil.getPreDay(); 
            log.info("[start_time:" + start_time + ",end_time:" + end_time+"]:进入不良驾驶页面");
        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error("不良驾驶页面异常", e);
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * 节油详细信息
     */
    public String loadEditAjaxData() {
        final String editBefTitle = getText("baddetail.browse.title");
    	MDC.put("modulename", "[baddriving]");
        int totalCountDay = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        try {

        	UserInfo user = getCurrentUser();
            Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
            calendar.add(Calendar.DATE, -1);    //得到前一天
            String  yestedayDate  = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
            yestedayDate +=" 23:59:59";
            
            if (null == baddetail) {
            	baddetail = new BadDriving();
            	baddetail.setOrganization_id(user.getOrganizationID());
            	baddetail.setTime_list("week");
            }else{ 
            	if(organization_id == null || "".equals(organization_id)){
            		baddetail.setOrganization_id(user.getOrganizationID());
            	}else{
            		baddetail.setOrganization_id(organization_id);
            	}
            }
            
            if (!"".equals(baddetail.getTime_list())) {
                if ("month".equals(baddetail.getTime_list())) {
                	baddetail.setAlarm_start_time(DateUtil.getMonthFirstDay());
                	
                	int comNum = (int) getCompareDate(DateUtil
                            .getMonthFirstDay(), yestedayDate);
                     
                    if(comNum>=0){
                    	baddetail.setAlarm_end_time(yestedayDate);
                    }else{
                    	baddetail.setAlarm_end_time(DateUtil
                                .getMonthFirstDay());
                    }
                    //DateUtil.getMonthLastDay()
                } else if ("quarter".equals(baddetail.getTime_list())) {
                    int i = DateUtil.getSeason();
                    baddetail.setAlarm_start_time(DateUtil
                            .getSeasonFirstDay(i));

                    int comNum = (int) getCompareDate(DateUtil
                            .getSeasonFirstDay(i), yestedayDate);
                     
                    if(comNum>=0){
                    	baddetail.setAlarm_end_time(yestedayDate);
                    }else{
                    	baddetail.setAlarm_end_time(DateUtil
                                .getSeasonFirstDay(i));
                    }
                    //DateUtil.getSeasonLastDay(i)
                } else if ("year".equals(baddetail.getTime_list())) {
                	baddetail.setAlarm_start_time(DateUtil
                            .getCurrentYearFirst());
                	
                	int comNum = (int) getCompareDate(DateUtil
                            .getCurrentYearFirst(), yestedayDate);
                     
                    if(comNum>=0){
                    	baddetail.setAlarm_end_time(yestedayDate);
                    }else{
                    	baddetail.setAlarm_end_time(DateUtil
                                .getCurrentYearFirst());
                    }
                    
                    //DateUtil.getCurrentYearLast()
                } else if ("week".equals(baddetail.getTime_list())) {
                	baddetail.setAlarm_start_time(DateUtil
                            .getCurrentWeekFirst());
                	
                	int comNum = (int) getCompareDate(DateUtil
                            .getCurrentWeekFirst(), yestedayDate);
                     
                    if(comNum>=0){
                    	baddetail.setAlarm_end_time(yestedayDate);
                    }else{
                    	baddetail.setAlarm_end_time(DateUtil
                                .getCurrentWeekFirst());
                    }
                	
                    //DateUtil.getCurrentWeekLast()
                }
            } else {
                if (!"".equals(baddetail.getAlarm_start_time()) && !"".equals(baddetail.getAlarm_end_time())) {
                	baddetail.setAlarm_start_time(baddetail.getAlarm_start_time() + " 00:00:00");
                    
                    int comNum = (int) getCompareDate(baddetail.getAlarm_end_time(), yestedayDate);
                    
                    if(comNum>=0){
                    	baddetail.setAlarm_end_time(baddetail.getAlarm_end_time() + " 23:59:59");
                    }else{
                    	baddetail.setAlarm_end_time(yestedayDate);
                    }
                }
                
            }
            
            baddetail.setVehicle_vin(vehicle_vin);
            // add by jinp stop
            baddetail.setAlarm_type_id(alarm_type_id_eq);
            if(vehicle_ln!=null&&!"".equals(vehicle_ln)){
            	baddetail.setVehicle_ln(vehicle_ln);
            }
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");

            baddetail.setSortname(sortName);
            baddetail.setSortorder(sortOrder);
            
            log.info("[Organization_id:" + baddetail.getOrganization_id() + ",Time_list:" + baddetail.getTime_list()
            		+ ",start_time:" + baddetail.getAlarm_start_time()+ ",end_time:" + baddetail.getAlarm_end_time()
        			+ ",vehicle_vin:" + vehicle_vin +"]:"+editBefTitle+"开始");
            totalCountDay = service.getCount("BadDriving.getBadDrivCount",
            		baddetail);

            list = service.getObjectsByPage("BadDriving.getBadDrivList",
            		baddetail, (Integer.parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.mapDetail = getDetailPagination(list, totalCountDay, pageIndex);
            // 设置操作描述
            this.addOperationLog(formatLog(editBefTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_BADDRIVING_QUERY_ID);
            log.info(editBefTitle+"结束");
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(editBefTitle+"异常", e);
            return ERROR;
        }
        if (list.size() == 0) {
            addActionMessage(getText("nodata.list"));
        }
        return SUCCESS;
    }

    /**
     * 时间比较方法
     * @param startDate
     * @param endDate
     * @return
     */
    private long getCompareDate(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = formatter.parse(startDate);
            Date date2 = formatter.parse(endDate);

            long l = date2.getTime() - date1.getTime();

            long d = l / (24 * 60 * 60 * 1000);
            return d;
        } catch (Exception e) {
            log.error("time Compare error:", e);
            return 0;
        }

    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, BadDrivDay bd) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != bd) {
            if (null != bd.getBaddriving_id()) {
                OperateLogFormator.format(sb, "baddriving_id", bd
                        .getBaddriving_id());
            }
        }
        return sb.toString();
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public Map getPagination(List dayList, List sumList, int totalCountDay,
            String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        Map sumMap = new LinkedHashMap();

        for (int i = 0; i < dayList.size(); i++) {

            BadDrivDay s = (BadDrivDay) dayList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVehicle_vin());

            cellMap.put("cell", new Object[] {s.getVehicle_ln(),
                    s.getVehicle_vin(), s.getVehicle_code(), s.getRoute_name(),
                    s.getDriver_name(), s.getSpeeding_num(),
                    s.getSpeeding_time(), s.getRpm_num(), s.getRpm_time(),
                    "0".equals(s.getGear_glide_num())?"--":s.getGear_glide_num(), 
                    "00:00:00".equals(s.getGear_glide_time())?"--":s.getGear_glide_time(),
                    s.getLong_idle_num(), s.getLong_idle_time(),
                    s.getUrgent_speed_num(), s.getAir_condition_time(),s.getEngineRotateTime(),
                    s.getShort_allname(), s.getEconomic_run_per() });
            mapList.add(cellMap);
        }
        BadDrivDay s = (BadDrivDay) sumList.get(0);
        sumMap.put("id", "sumid");
        sumMap.put("cell", new Object[] {null, null, null, null, null,
                s.getSpeeding_num(), s.getSpeeding_time(), s.getRpm_num(),
                s.getRpm_time(),
                "0".equals(s.getGear_glide_num())?"--":s.getGear_glide_num(), 
                "00:00:00".equals(s.getGear_glide_time())?"--":s.getGear_glide_time(),
                s.getLong_idle_num(), s.getLong_idle_time(),
                s.getUrgent_speed_num(), s.getAir_condition_time(),s.getEngineRotateTime(), null,
                s.getEconomic_run_per() });

        mapList.add(sumMap);
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
    }

    public Map getDetailPagination(List list, int totalCountDay,
            String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            BadDriving s = (BadDriving) list.get(i);

            Map < String, Object > cellMap = new LinkedHashMap();

            cellMap.put("id", UUID.randomUUID().toString());

            cellMap
                    .put("cell", new Object[] {s.getVehicle_ln(),
                            s.getVehicle_vin(), s.getRoute_name(),
                            s.getDriver_name(), s.getAlarm_type_name(),
                            s.getAlarm_start_time(), s.getAlarm_end_time(),
                            s.getAlarm_time(), s.getAlarm_start_speed(),
                            s.getAlarm_start_rpm(),
                            s.getAlarm_start_latitude(),
                            s.getAlarm_start_longitude() });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
    }

    /**
     * 按车辆导出
     * @return
     */
    public String exportCar() {
        String modelTitle = getText("baddrive.export.title");
     	MDC.put("modulename", "[baddriving]");
     	 
        if (null == baddrivday) {
            baddrivday = new BadDrivDay();
        }
        
        String timeStr = baddrivday.getTime_list();
        if("week".equals(timeStr)){
            timeStr = "本周";
        }else if("month".equals(timeStr)){
            timeStr = "本月";
        }else if("quarter".equals(timeStr)){
            timeStr = "本季度";
        }else if("year".equals(timeStr)){
            timeStr = "本年";
        }else{
            timeStr = baddrivday.getStart_time()+"——"+baddrivday.getEnd_time();
        }
        
        UserInfo user = getCurrentUser();
        
        if (null == baddrivday) {
        	baddrivday = new BadDrivDay();
        	baddrivday.setOrganization_id(user.getOrganizationID());
        	baddrivday.setTime_list("week");
        }else if(baddrivday.getOrganization_id() == null || "".equals(baddrivday.getOrganization_id())){
        	baddrivday.setOrganization_id(user.getOrganizationID());
        }
        
        log.info("[Organization_id:" + baddrivday.getOrganization_id() + ",Time_list:" + baddrivday.getTime_list()
         		+ "]:"+modelTitle+"开始");
        try {
            if ("3".equals(getCurrentUser().getUserType())) {
                baddrivday.setUser_id(getCurrentUser().getUserID());
                dayList = service.getObjects("BadDriving.getownerBadList",
                        baddrivday);
                sumList = service.getObjects("BadDriving.getownerBadListSum",
                        baddrivday);
            } else {
                dayList = service.getObjects("BadDriving.getBadDrivDayList",
                        baddrivday);
                sumList = service.getObjects("BadDriving.getBadDrivDayListSum",
                        baddrivday);
            }
            if (dayList.size() > 0) {
                for (int i = 0; i < sumList.size(); i++) {
                    BadDrivDay baddrivdayTemp = sumList.get(i);
                    baddrivdayTemp.setVehicle_ln(getText("common.sum"));
                    dayList.add(baddrivdayTemp);
                }
            }
           
        } catch (BusinessException e) {
            log.error(modelTitle+"异常",e);
            return ERROR;
        } catch (Exception e) {
        	log.error(modelTitle+"异常",e);
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "BadDriv.xls";

            // add by jinp start
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            // addd by jinp stop

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("不良驾驶记录 "+"("+timeStr+")");

            if(dayList == null || dayList.size()<1){
            	dayList.add(new BadDrivDay());
            }
            
            excelExporter.putAutoExtendSheets("exportbadDriveCar", 0, dayList);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
        	log.error(modelTitle+"异常",e);
            return ERROR;
        } catch (Exception e) {
        	log.error(modelTitle+"异常",e);
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
        
		SimpleDateFormat pathDf = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar = Calendar.getInstance();
		String datetimestr=pathDf.format(calendar.getTime());

        // 设置下载文件属性
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("Content-disposition",
                "attachment;filename=BadDriving-"+datetimestr+".xls");
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
        	log.error(modelTitle+"异常",e);
            return ERROR;
        } catch (Exception e) {
        	log.error(modelTitle+"异常",e);
            return ERROR;
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
            this.addOperationLog(formatLog(modelTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_BADDRIVING_EXPORT_ID);
        }
        log.info(modelTitle+"结束");
        // 导出文件成功
        return null;
    }
    
    public String getOrganization_id() {
		return organization_id;
	}

	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}

    public String getAlarm_type_id_eq() {
        return alarm_type_id_eq;
    }

    public void setAlarm_type_id_eq(String alarm_type_id_eq) {
        this.alarm_type_id_eq = alarm_type_id_eq;
    }

    public String getFileterFlag() {
        return fileterFlag;
    }

    public void setFileterFlag(String fileterFlag) {
        this.fileterFlag = fileterFlag;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }

    public List < BadDrivDay > getSumList() {
        return sumList;
    }

    public void setSumList(List < BadDrivDay > sumList) {
        this.sumList = sumList;
    }

    public String getPageParam() {
        return pageParam;
    }

    public void setPageParam(String pageParam) {
        this.pageParam = pageParam;
    }

    public String getRpParm() {
        return rpParm;
    }

    public void setRpParm(String rpParm) {
        this.rpParm = rpParm;
    }

    public Map getMapDetail() {
        return mapDetail;
    }

    public void setMapDetail(Map mapDetail) {
        this.mapDetail = mapDetail;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getSelectradio() {
        return selectradio;
    }

    public void setSelectradio(String selectradio) {
        this.selectradio = selectradio;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HashMap getParams() {
        return Params;
    }

    public String getChooseorgid() {
        return chooseorgid;
    }

    public void setChooseorgid(String chooseorgid) {
        this.chooseorgid = chooseorgid;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getTime_list() {
        return time_list;
    }

    public void setTime_list(String time_list) {
        this.time_list = time_list;
    }

    public String getVehicle_ln() {
        return vehicle_ln;
    }

    public void setVehicle_ln(String vehicle_ln) {
        this.vehicle_ln = vehicle_ln;
    }

    public List < BadDrivDay > getDayList() {
        return dayList;
    }

    public void setDayList(List < BadDrivDay > dayList) {
        this.dayList = dayList;
    }

    public VehcileInfo getVehcileInfo() {
        return vehcileInfo;
    }

    public void setVehcileInfo(VehcileInfo vehcileInfo) {
        this.vehcileInfo = vehcileInfo;
    }

    public BadDrivDay getBaddrivday() {
        return baddrivday;
    }

    public void setBaddrivday(BadDrivDay baddrivday) {
        this.baddrivday = baddrivday;
    }

    public List < VehcileInfo > getVehcList() {
        return vehcList;
    }

    public void setVehcList(List < VehcileInfo > vehcList) {
        this.vehcList = vehcList;
    }

    public List < BadDriving > getList() {

        return list;
    }

    public void setList(List < BadDriving > list) {
        this.list = list;
    }

    public BadDriving getBaddriving() {
        return baddriving;
    }

    public void setBaddriving(BadDriving baddriving) {
        this.baddriving = baddriving;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

	public BadDriving getBaddetail() {
		return baddetail;
	}

	public void setBaddetail(BadDriving baddetail) {
		this.baddetail = baddetail;
	}
}
