package com.neusoft.clw.newenergy.newEnergyMotor.action;

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

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.newenergy.newEnergyMotor.domain.EnergyMotor;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class NewEnergyMotorAction extends PaginationAction {

	/** service共通类 */
	private transient Service service;
	/** TAB也用的MAP */
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/** 显示数据list **/
    private List < EnergyMotor > motorList;
    
    private List<EnergyMotor> dayList;
    
    private String vehicleln;
	
	private String vehicleVin;
	
	private String startTime;
	
	private String endTime;
	
	private String organization_id;
	
	private String routeName;
	
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
	
	public String toIframe(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	public String init(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	public String isgInit(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	public String bmsInit(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	public String tipFlashData(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String vin = request.getParameter("vehicleVin");
		Map<String,Object> mpas = new HashMap<String,Object>();
		mpas.put("vehicleVin", vin);
		try {
			List list = service.getObjects("energyMotor.addTipFlashData", mpas);
			map.put("message", "success");
			map.put("list", list);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 主电机控制器状态信息查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getMontorControlList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源系统--主电机控制器状态信息分页处理!");
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
            
        	totalCount = service.getCount("energyMotor.energyMotorControlListCount", mpas);
            
        	motorList = service.getObjectsByPage(
					"energyMotor.energyMotorControlList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	//gpsUtil gsputil = new gpsUtil();
        	//motorList = gsputil.getEnergyAddress(motorList);
        	
        	map = getPagination(motorList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--主电机控制器状态信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统----主电机控制器状态信息分页处理失败！");
		}
		return SUCCESS;
	}
	
	/**
	 * ISG电机状态信息查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getMontorISGList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源系统--ISG电机状态信息分页处理!");
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
            
        	totalCount = service.getCount("energyMotor.energyMotorISGListCount", mpas);
            
        	motorList = service.getObjectsByPage(
					"energyMotor.energyMotorISGList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	//gpsUtil gsputil = new gpsUtil();
        	//motorList = gsputil.getEnergyAddress(motorList);
        	
        	map = getISGPagination(motorList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--ISG电机状态信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--ISG电机状态信息分页处理失败！");
		}
		return SUCCESS;
	}
	
	
	/**
	 * BMS和CMS绝缘电阻信息查询
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getMontorBMSList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源系统--BMS和CMS绝缘电阻信息分页处理!");
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
            
        	totalCount = service.getCount("energyMotor.energyMotorBMSListCount", mpas);
            
        	motorList = service.getObjectsByPage(
					"energyMotor.energyMotorBMSList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	/* 获取故障地址 */
        	//gpsUtil gsputil = new gpsUtil();
        	//motorList = gsputil.getEnergyAddress(motorList);
        	
        	map = getBMSPagination(motorList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--BMS和CMS绝缘电阻信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--BMS和CMS绝缘电阻信息分页处理失败！");
		}
		return SUCCESS;
	}
	
	
	public Map<String, Object> getPagination(List<EnergyMotor> list, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {

        	EnergyMotor s = (EnergyMotor) list.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getDataId());
            cellMap.put("cell", new Object[]{
            	s.getDataId(),s.getVehicleVin(),s.getLongitude(),s.getLatitude(),
            	s.getSpeed(),s.getRouteName(),s.getVehicleln(),s.getMainMode(),
            	s.getMainTorqueOut(),s.getMainRatote(),s.getMainTemp(),
            	s.getMainControllerTemp(),s.getMainControllerC(),s.getMainControllerV(),
            	s.getMainControllerTorqueIn(),s.getTerminalTime()
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	public Map<String, Object> getISGPagination(List<EnergyMotor> list, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {

        	EnergyMotor s = (EnergyMotor) list.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getDataId());
            cellMap.put("cell", new Object[]{
            	s.getDataId(),s.getVehicleVin(),s.getLongitude(),s.getLatitude(),
            	s.getSpeed(),s.getRouteName(),s.getVehicleln(),s.getIsgMode(),
            	s.getIsgTorqueOut(),s.getIsgRatote(),s.getIsgTemp(),
            	s.getIsgControllerTemp(),s.getIsgControllerC(),s.getIsgControllerV(),
            	s.getIsgControllerTroqueIn(),s.getTerminalTime()
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	
	public Map<String, Object> getBMSPagination(List<EnergyMotor> list, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < list.size(); i++) {

        	EnergyMotor s = (EnergyMotor) list.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getDataId());
            cellMap.put("cell", new Object[]{
            	s.getDataId(),s.getVehicleVin(),s.getLongitude(),s.getLatitude(),
            	s.getSpeed(),s.getRouteName(),s.getVehicleln(),s.getBatResistance(),
            	s.getBatResistanceA(),s.getBatResistanceN(),s.getCapResistance(),
            	s.getCapResistanceA(),s.getCapResistanceN(),s.getTerminalTime()
            });
            mapList.add(cellMap);
        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
	}
	
	
	/**
	 * 主电机控制器状态信息查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportMontorControl() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--主电机控制器状态信息开始!");
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
	    	
	    	dayList = service.getObjects("energyMotor.energyMotorControlList", mpas);
	    	
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--主电机控制器状态信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统--主电机控制器状态信息失败!");
		}
		for(int i = 0,len = dayList.size(); i < len;i++){
			EnergyMotor em = (EnergyMotor)dayList.get(i);
			String record = em.getMainMode();
			if( record.equals("100")){
				record = record+"(复位)";
			} else if(record.equals("001")){
				record = record+"(驱动)";
			} else if(record.equals("010")){
				record = record+"(发电)";
			} else if(record.equals("011")){
				record = record+"(待机)";
			} else if(record.equals("101")){
				record = record+"高压急断";
			} else if(record.equals("110")){
				record = record+"已停机";
			} else {
				record = record;
			}
			em.setMainMode(record);
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyMotorController.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("主电机控制器状态信息("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyMotor());
			}

			excelExporter.putAutoExtendSheets("exportEnergyMotorController", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出主电机控制器状态信息写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出主电机控制器状态信息写入Excel时出错:",e);
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
			String fileName = URLEncoder.encode("主电机控制器状态信息","UTF8");
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
			log.error("导出主电机控制器状态信息下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出主电机控制器状态信息下载时出错:",e);
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
			
			log.info("导出主电机控制器状态信息结束");
		}
		// 导出文件成功
		return null;
	}
	
	/**
	 * ISG电机状态信息查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportMontorISG() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--ISG电机状态信息开始!");
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
	    	
	    	dayList = service.getObjects("energyMotor.energyMotorISGList", mpas);
	    	
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--ISG电机状态信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统查询--ISG电机状态信息失败!");
		}
		
		for(int i = 0,len = dayList.size();i < len;i++){
			EnergyMotor em = (EnergyMotor)dayList.get(i);
			String record = em.getIsgMode();
			if( record.equals("100")){
				record = record+"(复位)";
			} else if(record.equals("001")){
				record = record+"(驱动)";
			} else if(record.equals("010")){
				record = record+"(发电)";
			} else if(record.equals("011")){
				record = record+"(待机)";
			} else if(record.equals("110")){
				record = record+"已停机";
			} else {
				record = record;
			}
			em.setIsgMode(record);
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyMotorISG.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("ISG电机状态信息("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyMotor());
			}

			excelExporter.putAutoExtendSheets("exportEnergyMotorISG", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出ISG电机状态信息写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出ISG电机状态信息写入Excel时出错:",e);
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
			String fileName = URLEncoder.encode("ISG电机状态信息","UTF8");
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
			log.error("导出ISG电机状态信息下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出ISG电机控制器状态信息下载时出错:",e);
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
			
			log.info("导出ISG电机状态信息结束");
		}
		// 导出文件成功
		return null;
	}
	
	
	/**
	 * BMS和CMS绝缘电阻信息查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportMontorBMS() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--BMS和CMS绝缘电阻信息开始!");
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
	    	
	    	dayList = service.getObjects("energyMotor.energyMotorBMSList", mpas);
	    	
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--BMS和CMS绝缘电阻信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统查询--BMS和CMS绝缘电阻信息失败!");
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyMotorISG.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("BMS和CMS绝缘电阻信息("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyMotor());
			}

			excelExporter.putAutoExtendSheets("exportEnergyMotorBMS", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出BMS和CMS绝缘电阻信息写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出BMS和CMS绝缘电阻信息写入Excel时出错:",e);
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
			String fileName = URLEncoder.encode("BMS和CMS绝缘电阻信息","UTF8");
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
			log.error("导出BMS和CMS绝缘电阻信息下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出BMS和CMS绝缘电阻信息信息下载时出错:",e);
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
			
			log.info("导出BMS和CMS绝缘电阻信息结束");
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
	public String getVehicleVin() {
		return vehicleVin;
	}
	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
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
	public String getOrganization_id() {
		return organization_id;
	}
	public void setOrganization_id(String organization_id) {
		this.organization_id = organization_id;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public List<EnergyMotor> getMotorList() {
		return motorList;
	}
	public void setMotorList(List<EnergyMotor> motorList) {
		this.motorList = motorList;
	}
	public List<EnergyMotor> getDayList() {
		return dayList;
	}
	public void setDayList(List<EnergyMotor> dayList) {
		this.dayList = dayList;
	}
	public String getVehicleln() {
		return vehicleln;
	}
	public void setVehicleln(String vehicleln) {
		this.vehicleln = vehicleln;
	}
	public String getCar_state() {
		return car_state;
	}
	public void setCar_state(String car_state) {
		this.car_state = car_state;
	}
}
