package com.neusoft.clw.newenergy.newenergyVersion.action;

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
import com.neusoft.clw.newenergy.newenergyVersion.domain.EnergyVersion;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class NewEnergyVersionAction extends PaginationAction{
	/** service共通类 */
	private transient Service service;
	/** TAB也用的MAP */
	private Map<String, Object> map = new HashMap<String, Object>();
	
	/** 显示数据list **/
    private List < EnergyVersion > versionList;
    
    private List<EnergyVersion> dayList;
    
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
	
	
	public String init(){
		startTime=DateUtil.getPreNDay(-7) + " 00:00";
		endTime = DateUtil.getCurrentDay() + " 23:59";
		return SUCCESS;
	}
	
	
	/**
	 * CMS管理系统信息查询 (电容)
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findVersionList(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 新能源硬软件版本信息分页处理!");
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
            
        	totalCount = service.getCount("energyVersion.energyVersionListCount", mpas);
            
        	versionList = service.getObjectsByPage(
					"energyVersion.energyVersionList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	
        	map = getPagination(versionList,totalCount,pageIndex);
        	
			log.info("logid:" + logid + "," + "新能源系统--硬软件版本信息分页处理成功！");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + "新能源系统--硬软件版本信息分页处理失败！");
		}
		return SUCCESS;
	}
	
	public Map<String, Object> getPagination(List<EnergyVersion> versionList, int totalCountDay, String pageIndex) {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        Map<String, Object> mapData = new LinkedHashMap<String, Object>();
        for (int i = 0; i < versionList.size(); i++) {

        	EnergyVersion s = (EnergyVersion) versionList.get(i);

            Map<String, Object> cellMap = new LinkedHashMap<String, Object>();

            cellMap.put("id", s.getVersionId());
            cellMap.put("cell", new Object[]{
            	s.getVersionId(),s.getVehicleVin(),s.getRouteName(),s.getVehicleln(),
            	s.getAddress(),s.getSoftVersion(),s.getSoftDate(),s.getHardWareVersion(),
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
	 * 硬软件版本信息查询导出
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String exportVersionList() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String logid = getlogid();
		log.info("logid:" + logid + "," + " 导出新能源系统--硬软件版本信息开始!");
		Map<String,Object> mpas = new HashMap<String,Object>();
		try{
			
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
				mpas.put("organization_id", this.getCurrentUser().getOrganizationID());
			}
			if(null != car_state && car_state.length() != 0){
				mpas.put("car_state", car_state);
			}
			
	        mpas.put("sortname",sortName);
	    	mpas.put("sortorder", sortOrder);
	    	
	    	dayList = service.getObjects("energyVersion.energyVersionList", mpas);
	    	
	    	log.info("logid:" + logid + "," + " 导出新能源系统查询--硬软件版本信息成功!");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("logid:" + logid + "," + " 导出新能源系统查询--硬软件版本信息失败!");
		}
		
		String filePath = "";
		String timestr = startTime + "——" + endTime;
		OutputStream outputStream = null;
		try {
			filePath = "/tmp/" + UUIDGenerator.getUUID() + "energyVersion.xls";

			// add by jinp start
			File file = new File(filePath);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			// addd by jinp stop

			outputStream = new FileOutputStream(filePath);
			// 使用Excel导出器IEExcelExporter
			IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
			excelExporter.setTitle("硬软件版本信息("+timestr+")");
			
			if (null == dayList || dayList.size() < 1) {
				dayList.add(new EnergyVersion());
			}

			excelExporter.putAutoExtendSheets("exportEnergyVersion", 0, dayList);
			// 将Excel写入到指定的流中
			excelExporter.write();
		} catch (FileNotFoundException e) {
			log.error("导出硬软件版本信息写入Excel时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出硬软件版本信息写入Excel时出错:",e);
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
			String fileName = URLEncoder.encode("硬软件版本信息","UTF8");
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
			log.error("导出硬软件版本信息下载时出错:",e);
			return ERROR;
		} catch (Exception e) {
			log.error("导出硬软件版本信息下载时出错:",e);
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
			
			log.info("导出硬软件版本信息结束");
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
	public List<EnergyVersion> getVersionList() {
		return versionList;
	}
	public void setVersionList(List<EnergyVersion> versionList) {
		this.versionList = versionList;
	}
	public List<EnergyVersion> getDayList() {
		return dayList;
	}
	public void setDayList(List<EnergyVersion> dayList) {
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
