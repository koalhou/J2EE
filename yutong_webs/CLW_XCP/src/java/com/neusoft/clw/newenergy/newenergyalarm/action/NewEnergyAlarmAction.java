package com.neusoft.clw.newenergy.newenergyalarm.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.newenergy.newenergyalarm.domain.EnergyAlarm;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class NewEnergyAlarmAction extends PaginationAction{

	/** service共通类 */
	private transient Service service;
	/** TAB也用的MAP */
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/** 显示数据list **/
    private List < EnergyAlarm > alarmList;
    
    private List<EnergyAlarm> dayList;
	
	private String vehicleVin;
	
	private String vehicleln;
	
	private String startTime;
	
	private String endTime;
	
	private String alarmMsg;
	
	private String routeName;
	
	private String organization_id;
	
	private String record;
	
	private String alarmId;
	//监控页面转向报警明细页面使用ID
	private String alarmKey;
	//判断进去页面是否默认查询
	private String isSearch;
	//时间戳
	private String timesPdate;
	//临时时间戳
	private String tmpTimes;
	
	private String car_state;

	/**
     * 获得当前操作LOGID
     * @return
     */
    private String getlogid() {
        return (String) ActionContext.getContext().getSession().get(
                Constants.LOG_USE_ID);
    }
	/**
	 * 获得当前操作用户
	 * 
	 * @return
	 */
	private UserInfo getCurrentUser() {
		return (UserInfo) ActionContext.getContext().getSession().get(
				Constants.USER_SESSION_KEY);
	}
	
	/* 初始化页面 */
	public String init(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		alarmKey = "";
		isSearch = "";
		alarmId = "";
		return SUCCESS;
	}
	
	public String monitorInit(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		if(null != timesPdate && timesPdate.length() != 0 && !timesPdate.equals(tmpTimes)){
			tmpTimes = timesPdate;
		} else {
			isSearch=null;
			alarmKey=null;
			alarmId=null;
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String energyAlarmList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源系统故障页面分页处理!");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        Map<String,Object> mpas = new HashMap<String,Object>();
		try {
			
			// 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
            // 是否监控页面列表
            String flag = request.getParameter("flag");
        	if(null != vehicleln && vehicleln.length() != 0){
        		mpas.put("vehicleln",vehicleln);
        	}
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
			if(null != startTime && startTime.length() != 0){
				mpas.put("startTime", startTime);
			} else {
				mpas.put("startTime", DateUtil.getPreNDay(-2) + " 00:00:00");
			}
			if(null != endTime && endTime.length() != 0){
				mpas.put("endTime", endTime);
			} else {
				mpas.put("endTime", DateUtil.getCurrentDay() + " 23:59:59");
			}
			if(null != alarmMsg && alarmMsg.length() != 0){
				mpas.put("alarmMsg", alarmMsg);
			}
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}		
			if(null != alarmId && alarmId.length() != 0){
				mpas.put("alarmId", alarmId);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", user.getOrganizationID());
			}
			if(null != flag && flag.length()!= 0){
				mpas.put("isused", flag);
			}
//			if(null != timesPdate && timesPdate.length() != 0 && !timesPdate.equals(tmpTimes)){
//				tmpTimes = timesPdate;
//			} else {
//				isSearch=null;
//				alarmKey=null;
//			}
			if(null != alarmKey && alarmKey.length() != 0){
				mpas.put("alarmKey", alarmKey);
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
			
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
            
        	totalCount = service.getCount("energyAlarm.energyAlarmListCount", mpas);
            
        	alarmList = service.getObjectsByPage(
					"energyAlarm.energyAlarmList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	gpsUtil gsputil = new gpsUtil();
        	alarmList = gsputil.getEnergyAlarmAddress(alarmList);
        	
        	map = getPagination(alarmList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统故障页面分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统故障页面分页处理失败！");
		}
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	public String energyAlarmTabList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源系统故障页面分页处理!");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        Map<String,Object> mpas = new HashMap<String,Object>();
		try {
			
			// 每页显示条数
            String rpNum = request.getParameter("rp");
            // 当前页码
            String pageIndex = request.getParameter("page");
            // 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
            // 是否监控页面列表
            String flag = request.getParameter("flag");
        	if(null != vehicleln && vehicleln.length() != 0){
        		mpas.put("vehicleln",vehicleln);
        	}
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
			if(null != startTime && startTime.length() != 0){
				mpas.put("startTime", startTime);
			} else {
				mpas.put("startTime", DateUtil.getPreNDay(-2) + " 00:00:00");
			}
			if(null != endTime && endTime.length() != 0){
				mpas.put("endTime", endTime);
			} else {
				mpas.put("endTime", DateUtil.getCurrentDay() + " 23:59:59");
			}
			if(null != alarmMsg && alarmMsg.length() != 0){
				mpas.put("alarmMsg", alarmMsg);
			}
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", user.getOrganizationID());
			}
			if(null != flag && flag.length()!= 0){
				mpas.put("isused", flag);
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
			
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
            
        	totalCount = service.getCount("energyAlarm.energyAlarmListCount", mpas);
            
        	alarmList = service.getObjectsByPage(
					"energyAlarm.energyAlarmList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	gpsUtil gsputil = new gpsUtil();
        	alarmList = gsputil.getEnergyAlarmAddress(alarmList);
        	
        	map = getPagination(alarmList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统故障页面分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统故障页面分页处理失败！");
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getPagination(List<EnergyAlarm> list, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {

        	EnergyAlarm s = (EnergyAlarm) list.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getAlarmId());
            
            cellMap.put("cell", new Object[] {s.getAlarmId(),s.getVehicleVin(),s.getVehicleln(),
            		s.getAlarmMsg(),s.getAlarmAddress(),s.getRouteName(),s.getAddress(),
            		s.getTerminalTime(),s.getRecord() });

            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	/**
	 * 报警处理意见
	 * @return
	 */
	public String processAlarm(){
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源系统--报警意见处理!");
		 Map<String,Object> mpas = new HashMap<String,Object>();
		 
		try {
			mpas.put("alarmId", alarmId);
			mpas.put("record", record);
			service.update("energyAlarm.processAlarm", mpas);			
			alarmId = "";
			log.info("logid:"+logid + "," + " 新能源系统--报警明细处理意见成功！");
		} catch (Exception e){
			e.printStackTrace();
			log.info("logid:"+logid + "," + " 新能源系统--报警明细处理意见失败！");
			return ERROR;
		}
		
		map.put("message", "success");
		return SUCCESS;
	}
	
	/**
	 * 报警查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportEnergyalarm() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--报警信息开始!");
		Map<String,Object> mpas = new HashMap<String,Object>();
		try{
			
			// 排序字段名
            String sortName = request.getParameter("sortname");
            // 升序OR降序
            String sortOrder = request.getParameter("sortorder");
            // 是否监控页面列表
            String flag = request.getParameter("flag");
            if(null != vehicleln && vehicleln.length() != 0){
        		mpas.put("vehicleln",vehicleln);
        	}
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
			if(null != startTime && startTime.length() != 0){
				mpas.put("startTime", startTime);
			} else {
				mpas.put("startTime", DateUtil.getPreNDay(-2) + " 00:00:00");
			}
			if(null != endTime && endTime.length() != 0){
				mpas.put("endTime", endTime);
			} else {
				mpas.put("endTime", DateUtil.getCurrentDay() + " 23:59:59");
			}
			if(null != alarmMsg && alarmMsg.length() != 0){
				mpas.put("alarmMsg", alarmMsg);
			}
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}		
			if(null != alarmId && alarmId.length() != 0){
				mpas.put("alarmId", alarmId);
			}
			if(null != alarmKey && alarmKey.length() != 0){
				mpas.put("alarmKey", alarmKey);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", this.getCurrentUser().getOrganizationID());
			}
			if(null != flag && flag.length()!= 0){
				mpas.put("isused", flag);
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
	        mpas.put("sortname",sortName);
	    	mpas.put("sortorder", sortOrder);
	    	
	    	dayList = service.getObjects("energyAlarm.energyAlarmList", mpas);
	    	/* 获取故障地址 */
        	gpsUtil gsputil = new gpsUtil();
        	dayList = gsputil.getEnergyAlarmAddress(dayList);
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--报警信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统查询--报警信息失败!");
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyAlarm.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("报警明细("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyAlarm());
			}

			excelExporter.putAutoExtendSheets("exportEnergyAlarm", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出报警明细写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出报警明细写入Excel时出错:",e);
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
		String name = DateUtil.getCurrentDayTime();

		FileInputStream fileInputStream = null;
		OutputStream out = null;
		try {
			String fileName = URLEncoder.encode("报警明细","UTF8");
			response.setHeader("Content-disposition",
					"attachment;filename="+fileName+"-" + name + ".xls");
			response.setContentType("application/msexcel; charset=\"utf-8\"");
			// 下载刚生成的文件
			fileInputStream = new FileInputStream(filePath);
			out = response.getOutputStream();
			int i = 0;
			while ((i = fileInputStream.read()) != -1) {
				out.write(i);
			}
		} catch (FileNotFoundException e) {
			log.error("导出报警明细下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出报警明细下载时出错:",e);
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
			
			log.info("导出报警明细结束");
		}
		// 导出文件成功
		return null;
	}
	
	
	public String openRouteWin(){
		
		return SUCCESS;
	}
	
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getVehicleVin() {
		return vehicleVin;
	}

	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}

	public String getVehicleln() {
		return vehicleln;
	}

	public void setVehicleln(String vehicleln) {
		this.vehicleln = vehicleln;
	}
    public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAlarmMsg() {
		return alarmMsg;
	}

	public void setAlarmMsg(String alarmMsg) {
		this.alarmMsg = alarmMsg;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public List<EnergyAlarm> getAlarmList() {
		return alarmList;
	}
	public void setAlarmList(List<EnergyAlarm> alarmList) {
		this.alarmList = alarmList;
	}
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public List<EnergyAlarm> getDayList() {
		return dayList;
	}
	public void setDayList(List<EnergyAlarm> dayList) {
		this.dayList = dayList;
	}
	public String getAlarmKey() {
		return alarmKey;
	}
	public void setAlarmKey(String alarmKey) {
		this.alarmKey = alarmKey;
	}
	public String getIsSearch() {
		return isSearch;
	}
	public void setIsSearch(String isSearch) {
		this.isSearch = isSearch;
	}
	
	public String getTimesPdate() {
		return timesPdate;
	}
	public void setTimesPdate(String timesPdate) {
		this.timesPdate = timesPdate;
	}
	public String getTmpTimes() {
		return tmpTimes;
	}
	public void setTmpTimes(String tmpTimes) {
		this.tmpTimes = tmpTimes;
	}
	public String getCar_state() {
		return car_state;
	}
	public void setCar_state(String car_state) {
		this.car_state = car_state;
	}
	
}
