package com.neusoft.clw.newenergy.newenergybattery.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.newenergy.newenergybattery.domain.EnergyBattery;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class NewEneryBatterAction  extends PaginationAction{
	
	/** service共通类 */
	private transient Service service;
	/** TAB也用的MAP */
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/** 显示数据list **/
    private List < EnergyBattery > batteryList;
    
    private List<EnergyBattery> dayList;
    
    private String startTime;
    
    private String endTime;
    
    private String routeName;
    
    private String vehicleVin;
    
    private String vehicleln;
    
    private String organization_id;
    
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
	
	/**
	 * 新能源CMS管理系统信息
	 * @return
	 */
	public String cmsInit(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	public String bmsInit(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	/**
	 * 新能源其它信息
	 * @return
	 */
	public String otherMsgInit(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	/**
	 * CMS管理系统信息查询 (电容)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getBatteryList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源CMS系统管理信息分页处理!");
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
        	
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
            if(null != vehicleln && vehicleln.length() != 0){
            	mpas.put("vehicleln", vehicleln);
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
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", user.getOrganizationID());
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
            
        	totalCount = service.getCount("energyBattery.energyBatteryCMSListCount", mpas);
            
        	batteryList = service.getObjectsByPage(
					"energyBattery.energyBatteryCMSList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	//gpsUtil gsputil = new gpsUtil();
        	//motorList = gsputil.getEnergyAddress(motorList);
        	
        	map = getPagination(batteryList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--CMS系统管理信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--CMS系统管理信息分页处理失败！");
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getPagination(List<EnergyBattery> batteryList, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < batteryList.size(); i++) {

        	EnergyBattery s = (EnergyBattery) batteryList.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getDataId());
            cellMap.put("cell", new Object[]{
            	s.getDataId(),s.getVehicleVin(),s.getLongitude(),s.getLatitude(),
            	s.getSpeed(),s.getRouteName(),s.getVehicleln(),s.getScapHighv(),
            	s.getScapLowv(),s.getScapvDiff(),s.getCapHighvNum(),
            	s.getCapLowvNum(),s.getScapTempHigh(),s.getScapAvgV() ,s.getSovervAlarmNum(),s.getScapTempHighNum(),
            	s.getScapTempLow(),s.getScapTempLowNum(),s.getTerminalTime()
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	/**
	 * BMS管理系统信息查询(电池)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getBatteryBMSList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源BMS系统管理信息分页处理!");
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
        	
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
            if(null != vehicleln && vehicleln.length() != 0){
            	mpas.put("vehicleln", vehicleln);
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
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", user.getOrganizationID());
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
            
        	totalCount = service.getCount("energyBattery.energyBatteryBMSListCount", mpas);
            
        	batteryList = service.getObjectsByPage(
					"energyBattery.energyBatteryBMSList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	//gpsUtil gsputil = new gpsUtil();
        	//motorList = gsputil.getEnergyAddress(motorList);
        	
        	map = getBMSPagination(batteryList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--BMS系统管理信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--BMS系统管理信息分页处理失败！");
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getBMSPagination(List<EnergyBattery> batteryList, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < batteryList.size(); i++) {

        	EnergyBattery s = (EnergyBattery) batteryList.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getDataId());
            cellMap.put("cell", new Object[]{
            	s.getDataId(),s.getVehicleVin(),s.getLongitude(),s.getLatitude(),
            	s.getSpeed(),s.getRouteName(),s.getVehicleln(),
            	s.getBatSoc(),s.getBatCurrent(),s.getBatVolTage(),s.getDischargeLimit(),
            	s.getChargeLimit(),s.getSbatvHigh(),s.getSbatvLow(),s.getSbatvDiff(),
            	s.getBatHighvNum(),s.getBatLowvNum(),s.getSbatTempHigh(),
            	s.getSovervAlarmNum(),s.getSbatTempHighNum(),s.getSbatTempLow(),
            	s.getSbatTempLowNum(),s.getTerminalTime()
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	
	/**
	 * 其它信息查询(电池)
	 * @return
	 */
	public String getBatteryOtherList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源其它信息信息分页处理!");
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
        	
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
            if(null != vehicleln && vehicleln.length() != 0){
            	mpas.put("vehicleln", vehicleln);
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
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", user.getOrganizationID());
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
            mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
            
        	totalCount = service.getCount("energyBattery.energyBatteryOtherListCount", mpas);
            
        	batteryList = service.getObjectsByPage(
					"energyBattery.energyBatteryOtherList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	//gpsUtil gsputil = new gpsUtil();
        	//motorList = gsputil.getEnergyAddress(motorList);
        	
        	map = getOtherPagination(batteryList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--其它信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--其它信息分页处理失败！");
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getOtherPagination(List<EnergyBattery> batteryList, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < batteryList.size(); i++) {

        	EnergyBattery s = (EnergyBattery) batteryList.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getDataId());
            cellMap.put("cell", new Object[]{
            	s.getDataId(),s.getVehicleVin(),s.getLongitude(),s.getLatitude(),
            	s.getSpeed(),s.getRouteName(),s.getVehicleln(),s.getGears(),
            	s.getClutchState(),s.getHandbrakeSignal(),s.getFootbrakeSignal(),
            	s.getOnState(),s.getAcceleratorpedalVoltage(),s.getBrakepedalVoltage(),
            	s.getDriverCommandTorque(),s.getETorque()==null?"-":s.getETorque(),
            	s.getOutaxleSpeed(),
            	s.getGearCount(),
            	s.getTerminalTime()
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	/**
	 * CMS管理系统信息查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportBatteryList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--CMS管理系统信息开始!");
		Map<String,Object> mpas = new HashMap<String,Object>();
		try{
			
			// 排序字段名
            String sortName = "terminal_time";
            // 升序OR降序
            String sortOrder = "desc";
			
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
            if(null != vehicleln && vehicleln.length() != 0){
            	mpas.put("vehicleln", vehicleln);
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
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", this.getCurrentUser().getOrganizationID());
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
	        mpas.put("sortname",sortName);
	    	mpas.put("sortorder", sortOrder);
	    	
	    	dayList = service.getObjects("energyBattery.energyBatteryCMSList", mpas);
	    	
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--CMS管理系统信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统查询--CMS管理系统信息失败!");
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyBatteryCMS.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("CMS管理系统信息("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyBattery());
			}

			excelExporter.putAutoExtendSheets("exportEnergyBatteryCMS", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出CMS管理系统信息写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出CMS管理系统信息写入Excel时出错:",e);
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
			String fileName = URLEncoder.encode("CMS管理系统信息","UTF8");
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
			log.error("导出CMS管理系统信息下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出CMS管理系统信息下载时出错:",e);
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
			
			log.info("导出CMS管理系统信息结束");
		}
		// 导出文件成功
		return null;
	}
	
	
	/**
	 * BMS管理系统信息查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportBatteryBMSList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--BMS管理系统信息开始!");
		Map<String,Object> mpas = new HashMap<String,Object>();
		try{
			
			// 排序字段名
            String sortName = "terminal_time";
            // 升序OR降序
            String sortOrder = "desc";
			
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
            if(null != vehicleln && vehicleln.length() != 0){
            	mpas.put("vehicleln", vehicleln);
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
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", this.getCurrentUser().getOrganizationID());
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
	        mpas.put("sortname",sortName);
	    	mpas.put("sortorder", sortOrder);
	    	
	    	dayList = service.getObjects("energyBattery.energyBatteryBMSList", mpas);
	    	
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--BMS管理系统信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统查询--BMS管理系统信息失败!");
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyBatteryBMS.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("BMS管理系统信息("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyBattery());
			}

			excelExporter.putAutoExtendSheets("exportEnergyBatteryBMS", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出BMS管理系统信息写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出BMS管理系统信息写入Excel时出错:",e);
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
			String fileName = URLEncoder.encode("BMS管理系统信息","UTF8");
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
			log.error("导出BMS管理系统信息下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出BMS管理系统信息下载时出错:",e);
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
			
			log.info("导出BMS管理系统信息结束");
		}
		// 导出文件成功
		return null;
	}
	
	
	/**
	 * 新能源其他信息查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportBatteryOtherList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--新能源其他信息开始!");
		Map<String,Object> mpas = new HashMap<String,Object>();
		try{
			
			// 排序字段名
            String sortName = "terminal_time";
            // 升序OR降序
            String sortOrder = "desc";
			
            if(null != vehicleVin && vehicleVin.length() != 0){
            	mpas.put("vehicleVin", vehicleVin);
            }
            if(null != vehicleln && vehicleln.length() != 0){
            	mpas.put("vehicleln", vehicleln);
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
			if(null != routeName && routeName.length() != 0 && !"--请选择--".equals(routeName)){
				mpas.put("routeName", routeName);
			}
			if(null != organization_id && organization_id.length() != 0){
				mpas.put("organization_id", organization_id);
			} else {
				mpas.put("organization_id", this.getCurrentUser().getOrganizationID());
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
	        mpas.put("sortname",sortName);
	    	mpas.put("sortorder", sortOrder);
	    	
	    	dayList = service.getObjects("energyBattery.energyBatteryOtherList", mpas);
	    	
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--新能源其他信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统查询--新能源其他信息失败!");
		}
		
		for(int i = 0,len=dayList.size();i < len;i++){
			EnergyBattery eb = (EnergyBattery)dayList.get(i);
			String gears = eb.getGears();
			String clutch_State = eb.getClutchState();
			String handbrake_Signal = eb.getHandbrakeSignal();
			String footbrake_Signal = eb.getFootbrakeSignal();
			String on_state = eb.getOnState();
			String acceleratorpedal_Voltage = eb.getAcceleratorpedalVoltage();
			String brakepedal_voltage = eb.getBrakepedalVoltage();
			if( gears.equals("000")){
				gears = gears+"(空档)";
			} else if(gears.equals("001")){
				gears = gears+"(前进)";
			} else if(gears.equals("010")){
				gears = gears+"(倒档)";
			} else if(gears.equals("011")){
				gears = gears+"(低速)";
			} else {
				gears = gears;
			}
			eb.setGears(gears);
			if( clutch_State.equals("01")){
				clutch_State = clutch_State+"(分离)";
			} else if(clutch_State.equals("10")){
				clutch_State = clutch_State+"(结合)";
			} else {
				clutch_State = clutch_State;
			}
			eb.setClutchState(clutch_State);
			if( handbrake_Signal.equals("00")){
				handbrake_Signal = handbrake_Signal+"(拉手刹)";
			} else if(handbrake_Signal.equals("01")){
				handbrake_Signal = handbrake_Signal+"(松手刹)";
			} else {
				handbrake_Signal = handbrake_Signal;
			}
			eb.setHandbrakeSignal(handbrake_Signal);
			if( footbrake_Signal.equals("00")){
				footbrake_Signal = footbrake_Signal+"(无信号)";
			} else if(footbrake_Signal.equals("01")){
				footbrake_Signal = footbrake_Signal+"(制动灯信号)";
			} else {
				footbrake_Signal = footbrake_Signal;
			}
			eb.setFootbrakeSignal(footbrake_Signal);
			if( on_state.equals("00")){
				on_state = on_state+"(无信号)";
			} else if(on_state.equals("001")){
				on_state = on_state+"(处于on火)";
			} else {
				on_state = on_state;
			}
			eb.setOnState(on_state);
//			if(acceleratorpedal_Voltage.equals("FF")){
//				acceleratorpedal_Voltage = acceleratorpedal_Voltage+"(无效)";
//			}
//			eb.setAcceleratorpedalVoltage(acceleratorpedal_Voltage);
//			if(brakepedal_voltage.equals("FF")){
//				brakepedal_voltage = brakepedal_voltage+"(无效)";
//			}
//			eb.setBrakepedalVoltage(brakepedal_voltage);
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyBatteryOther.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("新能源其他信息("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyBattery());
			}

			excelExporter.putAutoExtendSheets("exportEnergyBatteryOther", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出新能源其他信息写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出新能源其他信息写入Excel时出错:",e);
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
			String fileName = URLEncoder.encode("新能源其他信息","UTF8");
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
			log.error("导出新能源其他信息下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出新能源其他信息下载时出错:",e);
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
			
			log.info("导出新能源其他信息结束");
		}
		// 导出文件成功
		return null;
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
	
	public void setBatteryList(List<EnergyBattery> batteryList) {
		this.batteryList = batteryList;
	}
	public List<EnergyBattery> getDayList() {
		return dayList;
	}
	public void setDayList(List<EnergyBattery> dayList) {
		this.dayList = dayList;
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
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
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
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getCar_state() {
		return car_state;
	}
	public void setCar_state(String car_state) {
		this.car_state = car_state;
	}
	
	
	
}
