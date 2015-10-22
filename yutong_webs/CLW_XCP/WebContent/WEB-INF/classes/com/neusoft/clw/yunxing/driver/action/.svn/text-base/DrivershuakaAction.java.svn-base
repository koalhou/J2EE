package com.neusoft.clw.yunxing.driver.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.driver.domain.DriverStatInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class DrivershuakaAction  extends PaginationAction {
    private transient Service service;
    private String message = null;
    private DriverStatInfo queryObj;
    private DriverStatInfo detailObj;
    private DriverStatInfo exportObj;
    private DriverStatInfo driverStatInfo;
    private Map<String,Object> map;
    
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
	public DriverStatInfo getQueryObj() {
		return queryObj;
	}
	public void setQueryObj(DriverStatInfo queryObj) {
		this.queryObj = queryObj;
	}
	public DriverStatInfo getDetailObj() {
		return detailObj;
	}
	public void setDetailObj(DriverStatInfo detailObj) {
		this.detailObj = detailObj;
	}
	public DriverStatInfo getExportObj() {
		return exportObj;
	}
	public void setExportObj(DriverStatInfo exportObj) {
		this.exportObj = exportObj;
	}
	public DriverStatInfo getDriverStatInfo() {
		return driverStatInfo;
	}
	public void setDriverStatInfo(DriverStatInfo driverStatInfo) {
		this.driverStatInfo = driverStatInfo;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	/**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        return SUCCESS;
    }

    /**
     * 转向时长页面
     * @return
     */
    public String showDriveDurationPage() {
        queryObj = new DriverStatInfo();
        queryObj.setBegTime(DateUtil.getPreNDay(-7));
        queryObj.setEndTime(DateUtil.getPreDay());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        queryObj.setMonth(sdf.format(cal.getTime()));
        return SUCCESS;
    }
    
    /**
     * 查询时长列表
     * @return
     */
    public String getDriverDurationList(){
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	String searchTimeType = request.getParameter("searchTimeType");
    	String driverIds = request.getParameter("driverIds");
    	DriverStatInfo info = new DriverStatInfo();
	    String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        info.setSortname(sortName);
        info.setSortorder(sortOrder);
        try{
	    	if("1".equals(searchTimeType)){//按时段查询
	    		String start_time = request.getParameter("start_time");
	        	String end_time = request.getParameter("end_time");
	    		info.setBegTime(start_time+" 00:00:00");
	    		info.setEndTime(end_time+" 23:59:59");
	    	}else{
	    		String month = request.getParameter("month");
	    	    String first = month+"-01";
	    		Calendar cal = Calendar.getInstance();
	    		cal.set(Calendar.YEAR,Integer.parseInt(month.substring(0,4)));
	    		cal.set(Calendar.MONTH, Integer.parseInt(month.substring(5,7)));
	    		cal.set(Calendar.DAY_OF_MONTH, 1);
	    		cal.add(Calendar.DAY_OF_MONTH, -1);
	    		info.setBegTime(first+" 00:00:00");
	    		info.setEndTime(month+"-"+cal.get(Calendar.DAY_OF_MONTH)+" 23:59:59");
	    	}
	    	String searchIds = "";
	    	if(driverIds.length() > 0){
		    	 String []Ids = driverIds.split(",");// 字符串转字符数组  
		         for(String id : Ids){
		        	 searchIds = searchIds+"'"+id+"',";
		         }
		    	 info.setDriverId(searchIds.substring(0, searchIds.length()-1));
	    	 }
	    	 UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
	    	 info.setEnterpriseId(user.getOrganizationID());
	    	 int totalCount = service.getCount("Drivershuaka.getDriverDurationCount", info);
	         List<DriverStatInfo > list = service.getObjectsByPage("Drivershuaka.getDriverDurationList",
	        		 info, (Integer.parseInt(pageIndex) - 1)
	                         * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
	
	         this.map = getDriverDurationPagination(list,totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
            log.info("司机刷卡记录查询异常", e);
            return ERROR;
        }
    	 return SUCCESS;
    }
    
    public Map<String,Object> getDriverDurationPagination(List<DriverStatInfo> list,int totalCount, String pageIndex, String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        try{
	        for (int i = 0; i < list.size(); i++) {
        		Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
        		DriverStatInfo s = list.get(i);
	            cellMap.put("id", s.getDriverId());
	            cellMap.put("cell", new Object[] {
	                    s.getDriverId(),
	                    s.getDriverName(),
	                    s.getDriverCardId(),
	                    s.getDriverDuration(),
	                    s.getDriverMileage()
	                    });
	            mapList.add(cellMap);
	        }
        } catch (Exception e) {
            log.info("司机刷卡记录查询异常", e);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
    }
    
    /**
     * 按时长导出
     * @return
     */
    public String exportDuration(){
    	 String exportTitle = "驾驶员驾驶时长记录";
         List <DriverStatInfo> list = new ArrayList <DriverStatInfo>();
         try {
        	 if("1".equals(exportObj.getSearchTimeType())){//按时段查询
 	    		String start_time = exportObj.getBegTime();
 	        	String end_time = exportObj.getEndTime();
 	        	exportObj.setBegTime(start_time+" 00:00:00");
 	        	exportObj.setEndTime(end_time+" 23:59:59");
 	    	}else{
 	    		String month = exportObj.getMonth();
 	    	    String first = month+"-01";
 	    		Calendar cal = Calendar.getInstance();
 	    		cal.set(Calendar.YEAR,Integer.parseInt(month.substring(0,4)));
 	    		cal.set(Calendar.MONTH, Integer.parseInt(month.substring(5,7)));
 	    		cal.set(Calendar.DAY_OF_MONTH, 1);
 	    		cal.add(Calendar.DAY_OF_MONTH, -1);
 	    		exportObj.setBegTime(first+" 00:00:00");
 	    		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
 	    		exportObj.setEndTime(month+"-"+cal.get(Calendar.DAY_OF_MONTH)+" 23:59:59");
 	    	}
        	String driverIds = exportObj.getDriverId();
 	    	String searchIds = "";
 	    	if(driverIds.length() > 0){
 		    	 String []Ids = driverIds.split(",");// 字符串转字符数组  
 		         for(String id : Ids){
 		        	 searchIds = searchIds+"'"+id+"',";
 		         }
 		        exportObj.setDriverId(searchIds.substring(0, searchIds.length()-1));
 	    	 }
 	    	 UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
 	    	 exportObj.setEnterpriseId(user.getOrganizationID());
             list = (List <DriverStatInfo>) service.getObjects("Drivershuaka.getDriverDurationList", exportObj);
         } catch (BusinessException e) {
             setMessage("info.db.error");
             log.error(exportTitle+"Export Data error:" + e.getMessage());
             return ERROR;
         } catch (Exception e) {
         	e.printStackTrace();
             setMessage("info.db.error");
             log.error(exportTitle+"Export Data error:" + e.getMessage());
             return ERROR;
         }
         String filePath = "";
         OutputStream outputStream = null;
         try {
             filePath = "/tmp/" + UUIDGenerator.getUUID() + "DriverDuration.xls";
             File file = new File(filePath);
             if (!file.getParentFile().exists()) {
                 file.getParentFile().mkdirs();
             }
             outputStream = new FileOutputStream(filePath);
             IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
             if("1".equals(exportObj.getSearchTimeType()))//按时段查询
            	 excelExporter.setTitle(exportTitle+"("+exportObj.getBegTime().substring(0,10)+"——"+exportObj.getEndTime().substring(0,10)+")");
             else
            	 excelExporter.setTitle(exportTitle+"("+exportObj.getMonth()+")");
             if (null == list || list.size() < 1) {
             	list.add(new DriverStatInfo());
 			 }
             excelExporter.putAutoExtendSheets("exportDriverDuration", 0, list);
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
         response.setHeader("Content-disposition", "attachment;filename=driver_duartion_info-"+DateUtil.getCurrentDayTime()+".xls");
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
         }
         // 导出文件成功
         return null;
    }
    
    /**
     * 转向详情页面
     * @return
     */
    public String showDriverDurationDetailPage() {
    	try{
	    	detailObj= new DriverStatInfo();
	    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    	String id = request.getParameter("id");
	    	String name = request.getParameter("name");
	    	name = SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8"));
	    	String cardid = request.getParameter("cardid");
	    	String duration = request.getParameter("duration");
	    	duration = SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(duration, "utf-8"));
	    	String mileage = request.getParameter("mileage");
	    	detailObj.setDriverId(id);
	    	detailObj.setDriverName(name);
	    	detailObj.setDriverCardId(cardid);
	    	detailObj.setDriverDuration(duration);
	    	detailObj.setDriverMileage(mileage);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        return SUCCESS;
    }
    
    /**
     * 查询时长详情列表
     * @return
     */
    public String getDriverDurationDetailList(){
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	String searchTimeType = request.getParameter("searchTimeType");
    	String driverId = request.getParameter("driverId");
    	DriverStatInfo info = new DriverStatInfo();
		String rpNum = request.getParameter("rp");
	    String pageIndex = request.getParameter("page");
	    String sortName = request.getParameter("sortname");
	    String sortOrder = request.getParameter("sortorder");
	    info.setSortname(sortName);
	    info.setSortorder(sortOrder);
        try{
	    	if("1".equals(searchTimeType)){//按时段查询
	    		String start_time = request.getParameter("start_time");
	        	String end_time = request.getParameter("end_time");
	    		info.setBegTime(start_time+" 00:00:00");
	    		info.setEndTime(end_time+" 23:59:59");
	    	}else{
	    		String month = request.getParameter("month");
	    	    String first = month+"-01";
	    		Calendar cal = Calendar.getInstance();
	    		cal.set(Calendar.YEAR,Integer.parseInt(month.substring(0,4)));
	    		cal.set(Calendar.MONTH, Integer.parseInt(month.substring(5,7)));
	    		cal.set(Calendar.DAY_OF_MONTH, 1);
	    		cal.add(Calendar.DAY_OF_MONTH, -1);
	    		info.setBegTime(first+" 00:00:00");
	    		info.setEndTime(month+"-"+cal.get(Calendar.DAY_OF_MONTH)+" 23:59:59");
	    	}
		     info.setDriverId(driverId);
	    	 UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
	    	 info.setEnterpriseId(user.getOrganizationID());
	    	 int totalCount = service.getCount("Drivershuaka.getDriverDurationDetailCount", info);
	    	 List<DriverStatInfo > list = service.getObjectsByPage("Drivershuaka.getDriverDurationDetailList",
	        		 info, (Integer.parseInt(pageIndex) - 1)
	                         * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
	
	         this.map = getDetailPagination(list,totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
            log.info("司机驾驶详情记录查询异常", e);
            return ERROR;
        }
    	return SUCCESS;
    }
    
    public Map<String,Object> getDetailPagination(List<DriverStatInfo> list, int totalCount, String pageIndex,String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        try{
	        for (int i = 0; i < list.size(); i++) {
        		Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
        		DriverStatInfo s = list.get(i);
	            cellMap.put("vehicleLn", s.getVehicleLn());
	            cellMap.put("cell", new Object[] {
	                    s.getVehicleLn(),
	                    s.getOnDate(),
	                    s.getOffDate(),
	                    s.getDriverDuration(),
	                    s.getDriverMileage()
	                    });
	            mapList.add(cellMap);
	        }
        } catch (Exception e) {
            log.info("司机刷卡记录查询异常", e);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
    }
    
    /**
     * 转向记录页面
     * @return
     */
    public String showRecordPage() {
        queryObj = new DriverStatInfo();
        queryObj.setBegTime(DateUtil.getPreNDay(-7));
        queryObj.setEndTime(DateUtil.getPreDay());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        queryObj.setMonth(sdf.format(cal.getTime()));
        return SUCCESS;
    }
    
    /**
     * 查询刷卡记录列表
     * @return
     */
    public String getDriverRecordList(){
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	String searchTimeType = request.getParameter("searchTimeType");
    	String driverIds = request.getParameter("driverIds");
    	DriverStatInfo info = new DriverStatInfo();
	    String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        String state = request.getParameter("state");
        String ln = request.getParameter("ln");
        info.setSortname(sortName);
        info.setSortorder(sortOrder);
        info.setState(state);
        info.setVehicleLn(ln);
        try{
	    	if("1".equals(searchTimeType)){//按时段查询
	    		String start_time = request.getParameter("start_time");
	        	String end_time = request.getParameter("end_time");
	    		info.setBegTime(start_time+" 00:00:00");
	    		info.setEndTime(end_time+" 23:59:59");
	    	}else{
	    		String month = request.getParameter("month");
	    	    String first = month+"-01";
	    		Calendar cal = Calendar.getInstance();
	    		cal.set(Calendar.YEAR,Integer.parseInt(month.substring(0,4)));
	    		cal.set(Calendar.MONTH, Integer.parseInt(month.substring(5,7)));
	    		cal.set(Calendar.DAY_OF_MONTH, 1);
	    		cal.add(Calendar.DAY_OF_MONTH, -1);
	    		info.setBegTime(first+" 00:00:00");
	    		info.setEndTime(month+"-"+cal.get(Calendar.DAY_OF_MONTH)+" 23:59:59");
	    	}
	    	String searchIds = "";
	    	if(driverIds.length() > 0){
		    	 String []Ids = driverIds.split(",");// 字符串转字符数组  
		         for(String id : Ids){
		        	 searchIds = searchIds+"'"+id+"',";
		         }
		    	 info.setDriverId(searchIds.substring(0, searchIds.length()-1));
	    	 }
	    	 UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
	    	 info.setEnterpriseId(user.getOrganizationID());
	    	 int totalCount = service.getCount("Drivershuaka.getDriverRecordCount", info);
	         List<DriverStatInfo > list = service.getObjectsByPage("Drivershuaka.getDriverRecordList",
	        		 info, (Integer.parseInt(pageIndex) - 1)
	                         * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
	
	         this.map = getDriverRecordPagination(list,totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
            log.info("司机刷卡记录查询异常", e);
            return ERROR;
        }
    	 return SUCCESS;
    }
    
    public Map<String,Object> getDriverRecordPagination(List<DriverStatInfo> list,int totalCount, String pageIndex, String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        try{
	        for (int i = 0; i < list.size(); i++) {
        		Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
        		DriverStatInfo s = list.get(i);
	            cellMap.put("id", s.getDriverId());
	            cellMap.put("cell", new Object[] {
	                    s.getDriverName(),
	                    s.getDriverCardId(),
	                    s.getState(),
	                    s.getSwipeTime(),
	                    s.getVehicleLn()
	                    });
	            mapList.add(cellMap);
	        }
        } catch (Exception e) {
            log.info("司机刷卡记录查询异常", e);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        return mapData;
    }
    
    
    /**
     * 按记录导出
     * @return
     */
    public String exportRecord(){
    	 String exportTitle = "驾驶员刷卡记录";
         List <DriverStatInfo> list = new ArrayList <DriverStatInfo>();
         try {
        	 if("1".equals(exportObj.getSearchTimeType())){//按时段查询
 	    		String start_time = exportObj.getBegTime();
 	        	String end_time = exportObj.getEndTime();
 	        	exportObj.setBegTime(start_time+" 00:00:00");
 	        	exportObj.setEndTime(end_time+" 23:59:59");
 	    	}else{
 	    		String month = exportObj.getMonth();
 	    	    String first = month+"-01";
 	    		Calendar cal = Calendar.getInstance();
 	    		cal.set(Calendar.YEAR,Integer.parseInt(month.substring(0,4)));
 	    		cal.set(Calendar.MONTH, Integer.parseInt(month.substring(5,7)));
 	    		cal.set(Calendar.DAY_OF_MONTH, 1);
 	    		cal.add(Calendar.DAY_OF_MONTH, -1);
 	    		exportObj.setBegTime(first+" 00:00:00");
 	    		System.out.println(cal.get(Calendar.DAY_OF_MONTH));
 	    		exportObj.setEndTime(month+"-"+cal.get(Calendar.DAY_OF_MONTH)+" 23:59:59");
 	    	}
        	String driverIds = exportObj.getDriverId();
 	    	String searchIds = "";
 	    	if(driverIds.length() > 0){
 		    	 String []Ids = driverIds.split(",");// 字符串转字符数组  
 		         for(String id : Ids){
 		        	 searchIds = searchIds+"'"+id+"',";
 		         }
 		        exportObj.setDriverId(searchIds.substring(0, searchIds.length()-1));
 	    	 }
 	    	 UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
 	    	 exportObj.setEnterpriseId(user.getOrganizationID());
 	    	 if("1".equals(exportObj.getState()))
 	    		exportObj.setState("UP");
 	        else if("2".equals(exportObj.getState()))
 	        	exportObj.setState("DOWN");
             list = (List <DriverStatInfo>) service.getObjects("Drivershuaka.getDriverRecordList", exportObj);
         } catch (BusinessException e) {
             setMessage("info.db.error");
             log.error(exportTitle+"Export Data error:" + e.getMessage());
             return ERROR;
         } catch (Exception e) {
         	e.printStackTrace();
             setMessage("info.db.error");
             log.error(exportTitle+"Export Data error:" + e.getMessage());
             return ERROR;
         }
         String filePath = "";
         OutputStream outputStream = null;
         try {
             filePath = "/tmp/" + UUIDGenerator.getUUID() + "DriverShuaka.xls";
             File file = new File(filePath);
             if (!file.getParentFile().exists()) {
                 file.getParentFile().mkdirs();
             }
             outputStream = new FileOutputStream(filePath);
             IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
             if("1".equals(exportObj.getSearchTimeType()))//按时段查询
            	 excelExporter.setTitle(exportTitle+"("+exportObj.getBegTime().substring(0,10)+"——"+exportObj.getEndTime().substring(0,10)+")");
             else
            	 excelExporter.setTitle(exportTitle+"("+exportObj.getMonth()+")");
             if (null == list || list.size() < 1) {
              	list.add(new DriverStatInfo());
  			 }
             excelExporter.putAutoExtendSheets("exportDriverShuaka", 0, list);
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
         response.setHeader("Content-disposition", "attachment;filename=driver_shuaka_info-"+DateUtil.getCurrentDayTime()+".xls");
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
         }
         // 导出文件成功
         return null;
    }
    
}
