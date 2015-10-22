package com.neusoft.clw.sysmanage.datamanage.checkOilSet.action;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.PrivateCredentialPermission;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.chainsaw.Main;
import org.apache.struts2.ServletActionContext;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;
import org.omg.CORBA.PRIVATE_MEMBER;

import sun.print.resources.serviceui;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.UnicodeConverter;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.CheckInfo;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.CheckMonthInfo;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.CheckOilSetInfo;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.InsertCheckOilList;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.MonthStartEnd;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.TestList;
import com.neusoft.clw.sysmanage.datamanage.checkOilSet.service.CheckOilSetService;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

public class CheckOilSetAction extends PaginationAction {
	
	private CheckOilSetInfo checkOilSetInfo;
	private CheckInfo checkInfo;
	private CheckMonthInfo checkMonthInfo;
	private transient Service service;
	private transient  CheckOilSetService  checkOilSetService;
	
	private List<MonthStartEnd> checkMonthlist;
	
	private String checkYear;
	
	public String getCheckYear() {
		return checkYear;
	}


	public void setCheckYear(String checkYear) {
		this.checkYear = checkYear;
	}


	private List < CheckOilSetInfo > checkOilList;
	private Map map = new HashMap();
	private Map checkOilSetMap = new HashMap();
	private Map vehicleVinMap = new HashMap();
	private Map checkValueTimeMap = new HashMap();
	 private String message = null;
	 
	private MonthStartEnd monthStartEnd;
	 
	 /** 选择的文件 */
	    private File file;

	    /** 选择的文件类型 */
	    private String fileContentType;

	    /** 文件名 */
	    private String fileFileName;
	 
	 
	
	 /**
	     * 获得当前操作用户
	     * @return
	     */
	    private UserInfo getCurrentUser() {
	        return (UserInfo) ActionContext.getContext().getSession().get(
	                Constants.USER_SESSION_KEY);
	    }
	    
	    
	    /** 考核月度设置 */
	    public String initChcekMonth() {
	        return SUCCESS;
	    }
	    
	    
	    @SuppressWarnings("unchecked")
		public String selectCheckMonth() {
	    	HttpServletRequest request = (HttpServletRequest) ActionContext
	        .getContext()
	        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    	
	    	Map<String,String> maps = new HashMap<String,String>();
	    	String entId = this.getCurrentUser().getEntiID();
	    	String checkYear = request.getParameter("checkYear");
	    	
	    	maps.put("checkYear", checkYear);
	    	maps.put("entId", entId);
	    	
	    	try {
				checkMonthlist = checkOilSetService.getObjects("CheckOilSet.selectEntIdTime", maps);
			} catch (BusinessException e) {
				e.printStackTrace();
			}
			
			return SUCCESS;
	    }
	    
	    public String  setChcekMonth(){
	    	
	    	
	    	HttpServletRequest request = (HttpServletRequest) ActionContext
	        .getContext()
	        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);	    	
	    	
	    	 UserInfo user = getCurrentUser();
	    	
	    	String sameFlag=request.getParameter("sameFlag");	    	
	    	String checkYear=request.getParameter("checkYear");	 
	    	
	    	String[] startMonth=new String[12];
	    	String[] endMonth=new String[12];
	    	
	    	if(sameFlag.equals("0")){  
	    		String thisMonth = request.getParameter("thisMonth");
		    	String nextMonth = request.getParameter("nextMonth");
	    		
		    	startMonth[0] =checkYear.trim()+"-01-".trim()+thisMonth.trim();
		    	endMonth[0] = checkYear.trim()+"-02-".trim()+nextMonth.trim();
		    	
		    	startMonth[1] =checkYear.trim()+"-02-".trim()+thisMonth.trim();
		    	endMonth[1] = checkYear.trim()+"-03-".trim()+nextMonth.trim();
		    	
		    	startMonth[2] =checkYear.trim()+"-03-".trim()+thisMonth.trim();
		    	endMonth[2] = checkYear.trim()+"-04-".trim()+nextMonth.trim();
		    	
		    	startMonth[3] =checkYear.trim()+"-04-".trim()+thisMonth.trim();
		    	endMonth[3] = checkYear.trim()+"-05-".trim()+nextMonth.trim();
		    	
		    	startMonth[4] =checkYear.trim()+"-05-".trim()+thisMonth.trim();
		    	endMonth[4] = checkYear.trim()+"-06-".trim()+nextMonth.trim();
		    	
		    	startMonth[5] =checkYear.trim()+"-06-".trim()+thisMonth.trim();
		    	endMonth[5] = checkYear.trim()+"-07-".trim()+nextMonth.trim();
		    	
		    	startMonth[6] =checkYear.trim()+"-07-".trim()+thisMonth.trim();
		    	endMonth[6] = checkYear.trim()+"-08-".trim()+nextMonth.trim();
		    	
		    	startMonth[7] =checkYear.trim()+"-08-".trim()+thisMonth.trim();
		    	endMonth[7] = checkYear.trim()+"-09-".trim()+nextMonth.trim();
		    	
		    	startMonth[8] =checkYear.trim()+"-09-".trim()+thisMonth.trim();
		    	endMonth[8] = checkYear.trim()+"-10-".trim()+nextMonth.trim();
		    	
		    	startMonth[9] =checkYear.trim()+"-10-".trim()+thisMonth.trim();
		    	endMonth[9] = checkYear.trim()+"-11-".trim()+nextMonth.trim();
		    	
		    	startMonth[10] =checkYear.trim()+"-11-".trim()+thisMonth.trim();
		    	endMonth[10] = checkYear.trim()+"-12-".trim()+nextMonth.trim();
		    	
		    	startMonth[11] =checkYear.trim()+"-12-".trim()+thisMonth.trim();
		    	endMonth[11] = String.valueOf(Integer.parseInt(checkYear)+1).trim()+"-01-".trim()+nextMonth.trim();	
	    	}else{
	    		startMonth[0] = request.getParameter("begin1");
	    		endMonth[0] = request.getParameter("end1");
		    	
	    		startMonth[1] = request.getParameter("begin2");
	    		endMonth[1]= request.getParameter("end2");
		    	
		    	startMonth[2] = request.getParameter("begin3");
		    	endMonth[2]= request.getParameter("end3");
		    	
		    	startMonth[3] = request.getParameter("begin4");
		    	endMonth[3]= request.getParameter("end4");
		    	
		    	startMonth[4] = request.getParameter("begin5");
		    	endMonth[4]= request.getParameter("end5");
		    	
		    	startMonth[5] = request.getParameter("begin6");
		    	endMonth[5] = request.getParameter("end6");
		    	
		    	startMonth[6]= request.getParameter("begin7");
		    	endMonth[6] = request.getParameter("end7");
		    	
		    	startMonth[7] = request.getParameter("begin8");
		    	endMonth[7] = request.getParameter("end8");
		    	
		    	startMonth[8]= request.getParameter("begin9");
		    	endMonth[8] = request.getParameter("end9");
		    	
		    	startMonth[9] = request.getParameter("begin10");
		    	endMonth[9] = request.getParameter("end10");
		    	
		    	startMonth[10] = request.getParameter("begin11");
		    	endMonth[10] = request.getParameter("end11");
		    	
		    	startMonth[11] = request.getParameter("begin12");
		    	endMonth[11] = request.getParameter("end12");
		    	
	    	}
	    	int k=0;
	    	for(int i=1;i<=12;i++){
	    		monthStartEnd.setCheckTimeId(UUIDGenerator.getUUID());	    		
	    		monthStartEnd.setEnterpriseId(user.getEntiID());
	    		if(i<10){
	    			monthStartEnd.setCheckTimeCode(checkYear.trim()+"-0".trim()+i);
	    		}else{
	    			monthStartEnd.setCheckTimeCode(checkYear.trim()+"-".trim()+i);	    			
	    		}
	    		
	    		
	    		monthStartEnd.setStartTime(startMonth[i-1]);
	    		monthStartEnd.setEndTime(endMonth[i-1]);
	    		
	    		
	    		monthStartEnd.setCreater(user.getUserName());
	    		monthStartEnd.setModifier(user.getUserName());
	    		
	    		
	    		
	    		try{	    			
	    		k= service.getCount("CheckOilSet.selectCheckTime",monthStartEnd);	    		
	    		}catch (Exception e) {
	    			e.printStackTrace();
					// TODO: handle exception
				}
	    		
	    		if(k>0){
	    			try{	    
	    			service.update("CheckOilSet.updateCheckTime", monthStartEnd);
	    			}catch (Exception e) {
						// TODO: handle exception
					}
	    		}else{
	    		
	    		
	    			try{
	    				service.insert("CheckOilSet.insertCheckTime", monthStartEnd);
	    			
	    			}catch (Exception e) {
						// TODO: handle exception
					}
	    		
	    		}
	    			
	    	
	    		
	    	}
	    	
	    	return SUCCESS;
	    }
	    
	  
		public String updateCheckInfo(){	    	
	    	
	    	HttpServletRequest request = (HttpServletRequest) ActionContext
	        .getContext()
	        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    	
	    	String vehicle_vin=request.getParameter("vehicle_vin");
	    	String checkTimeCode=request.getParameter("checkTimeCode");
	    	String value1 = request.getParameter("text1");
	    	String value2 = request.getParameter("text2");
	    	String value3 = request.getParameter("text3");
	    	String value4 = request.getParameter("text4");
	    	
	    	String value5 = request.getParameter("text5");
	    	String value6 = request.getParameter("text6");
	    	String value7 = request.getParameter("text7");
	    	String value8 = request.getParameter("text8");
	    	
	    	String value9 = request.getParameter("text9");
	    	String value10 = request.getParameter("text10");
	    	String value11 = request.getParameter("text11");
	    	String value12 = request.getParameter("text12");
	    	
	    	
	    	for(int i=1;i<13;i++){
	    		checkInfo.setVehicle_vin(vehicle_vin);
	    		if(i<10){
	    			checkInfo.setCheckTimeCode(checkTimeCode+"-0"+i);
	    		}else{
	    			checkInfo.setCheckTimeCode(checkTimeCode+"-"+i);
	    		}
	    		if(i==1){
	    			checkInfo.setCheckValue(value1);
	    		}else if(i==2){
	    			checkInfo.setCheckValue(value2);
	    		}else if(i==3){
	    			checkInfo.setCheckValue(value3);
	    		}else if(i==4){
	    			checkInfo.setCheckValue(value4);
	    		}else if(i==5){
	    			checkInfo.setCheckValue(value5);
	    		}else if(i==6){
	    			checkInfo.setCheckValue(value6);
	    		}else if(i==7){
	    			checkInfo.setCheckValue(value7);
	    		}else if(i==8){
	    			checkInfo.setCheckValue(value8);
	    		}else if(i==9){
	    			checkInfo.setCheckValue(value9);
	    		}else if(i==10){
	    			checkInfo.setCheckValue(value10);
	    		}else if(i==11){
	    			checkInfo.setCheckValue(value11);
	    		}else {
	    			checkInfo.setCheckValue(value12);
	    		}
	    		
	    		
	    		try{
		    		service.update("CheckOilSet.updateCheckInfo", checkInfo);
		    	}catch (Exception e) {
					return ERROR;
				}
	    		
	    	}
	    	
	    	
	    	/*
	    	checkOilSetInfo.setVehicle_vin(vehicle_vin);
	    	checkOilSetInfo.setCheckYear(checkTimeCode);
	    	checkOilSetInfo.setJanuary(value1);
	    	checkOilSetInfo.setFebruary(value2);
	    	checkOilSetInfo.setMarch(value3);
	    	checkOilSetInfo.setApril(value4);
	    	checkOilSetInfo.setMay(value5);
	    	checkOilSetInfo.setJune(value6);
	    	checkOilSetInfo.setJuly(value7);
	    	checkOilSetInfo.setAugust(value8);
	    	checkOilSetInfo.setSeptember(value9);
	    	checkOilSetInfo.setOctober(value10);
	    	checkOilSetInfo.setNovember(value11);
	    	checkOilSetInfo.setDecember(value12);
	    	*/
	    	
	    	
	    	
	    	
	    	return SUCCESS;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    /**
	     * 对单车考核油耗删除
	     * @return
	     */
	    public String  deleteCheckInfo() {
	    	
	    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    	String vehicle_vin = request.getParameter("vehicle_vin");
	    	checkInfo.setVehicle_vin(vehicle_vin);
	    	
	    	try{
	    		service.delete("CheckOilSet.deleteCheckByVin", checkInfo);
	    	}catch (Exception e) {
				return ERROR;
			}
	    	
	    	return SUCCESS;
	    }
	    
	    
	    /**
	     * 考核油耗时间和值页面初始化
	     * @return
	     */
	    public String  getCheckValue() {  
	    	HttpServletRequest request = (HttpServletRequest) ActionContext
	        .getContext()
	        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	    	String vehicle_vin = request.getParameter("vin"); 
	    	String checkYear = request.getParameter("checkYear"); 
	    	
	    	checkInfo.setVehicle_vin(vehicle_vin);
	    	checkInfo.setCheckTimeCode(checkYear);
	    	
	    	
	        return SUCCESS;
	    }
	    
	    
	   
	    public String getCheckValueTime(){
	    	HttpServletRequest request = ServletActionContext.getRequest();
	    	
	    	// 每页显示条数
	        String rpNum = request.getParameter("rp");
	        // 当前页码
	        String pageIndex = request.getParameter("page");
	        // 排序字段名
	        String sortName = request.getParameter("sortname");
	        // 升序OR降序
	        String sortOrder = request.getParameter("sortorder");
	        
	        String vehicle_vin = request.getParameter("vehicle_vin"); 
	    	String checkYear = request.getParameter("checkTimeCode"); 
	    	 
	        checkInfo.setSortName(sortName);
	        checkInfo.setSortOrder(sortOrder);
	    	checkInfo.setVehicle_vin(vehicle_vin);
	    	checkInfo.setCheckTimeCode(checkYear);
	   	 int totalCount = 0;
	   	try{
	   		 totalCount = service.getCount("CheckOilSet.getCheckValueTimeCount",checkInfo);            
	            checkOilList = (List < CheckOilSetInfo >) service.getObjectsByPage(
	                    "CheckOilSet.getCheckValueTimeList", checkInfo, (Integer
	                            .parseInt(pageIndex) - 1)
	                            * Integer.parseInt(rpNum), Integer
	                            .parseInt(rpNum));
	       	
	           
	   	}catch (Exception e) {
	   		setMessage("file.export.error");
	           log.error("Export student error:" + e.getMessage());
	           return ERROR;
			}
	       
	   	this.checkValueTimeMap=getCheckInfoPagination(checkOilList, totalCount, pageIndex);
	   	return SUCCESS;
	   
	    	
	    }
	    
	    
	    public Map getCheckInfoPagination(List list, int totalCountDay, String pageIndex) {
	        List mapList = new ArrayList();
	        Map mapData = new LinkedHashMap();
	        for (int i = 0; i < list.size(); i++) {

	        	CheckInfo s = (CheckInfo) list.get(i);

	            Map cellMap = new LinkedHashMap();            

	            cellMap.put("id", s.getCheckTimeCode());
	            cellMap.put("cell", new Object[] {
	            		s.getCheckTimeCode(),    
	            		s.getStart_time(),
	            		s.getEnd_time(),
	            		s.getCheckValue(),
	            		s.getVehicle_vin()       		
	                    });  

	            mapList.add(cellMap);

	        }

	        mapData.put("page", pageIndex);// 从前台获取当前第page页
	        mapData.put("total", totalCountDay);// 从数据库获取总记录数
	        mapData.put("rows", mapList);

	        return mapData;
	    }
	    
	    
	    
	    public static void main(String[] args){
	    	System.out.println(UnicodeConverter.toEncodedUnicode("设置考核月度成功！",true));
	    }  
	    
	    
    /**
     * 考核油耗设置页面初始化
     * @return
     */
    public String init() {
    	//HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	//String message = request.getParameter("message");
    	if (null != message) {
            addActionMessage(getText(message));
        }
    	if(this.getCurrentUser()==null)
    	{
    		return "error";
    	}
        return SUCCESS;  
    }
    /**
     * 考核油耗设置页面--添加数据
     * @return
     */
    public String add() { 
    	
    	/*******油量里程--造数据 ************
    	String vehicle_vin="ZZYT1234567899876";
		String vehicle_ln="豫A66666";
		String insert_date="";
		TestList dong=new TestList();
		dong.setVehicle_vin(vehicle_vin);
		dong.setVehicle_ln(vehicle_ln);
		
		
		
		
			for(int i=1;i<31;i++){
				dong.setInsert_id(UUIDGenerator.getUUID());
				if(i<10){
					insert_date="2013-06-0"+i;
					
				}else{
					insert_date="2013-06-"+i;
				}
				
					dong.setInsert_date(insert_date);
				
				
				dong.setOil(i+"70");
				dong.setMileage(i*2+"1");
				try{
					checkOilSetService.importCheckOilSetInfosTest(dong);
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		
    	***************************************/
    	
    	
    	
    	
    	
    	
        return SUCCESS;
    }

    public String  checkOilSetAddSubmit(){
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	final String editTitle = getText("vehcileinfo.update");
    	String sameFlag = request.getParameter("sameFlag"); 
    	String checkYear = request.getParameter("checkYear"); 
    	String vehicle_ln = request.getParameter("vehicle_ln");
    	//String checkYears = getCheckYear();
    	
    	
    	CheckOilSetInfo insertInfo=new CheckOilSetInfo();
    	
    	if(sameFlag.equals("0")||sameFlag=="0"){    		
        	String value = request.getParameter("checkValueSame"); 
        	insertInfo.setJanuary(value);
        	insertInfo.setFebruary(value);
        	insertInfo.setMarch(value);
        	insertInfo.setApril(value);
        	insertInfo.setMay(value);
        	insertInfo.setJune(value);
        	insertInfo.setJuly(value);
        	insertInfo.setAugust(value);
        	insertInfo.setSeptember(value);
        	insertInfo.setOctober(value);
        	insertInfo.setNovember(value);
        	insertInfo.setDecember(value);
        	
    	}else{
    		
        	String text1 = request.getParameter("text1"); 
        	String text2 = request.getParameter("text2"); 
        	String text3 = request.getParameter("text3"); 
        	String text4 = request.getParameter("text4"); 
        	String text5 = request.getParameter("text5"); 
        	String text6 = request.getParameter("text6"); 
        	String text7 = request.getParameter("text7"); 
        	String text8 = request.getParameter("text8"); 
        	String text9 = request.getParameter("text9"); 
        	String text10 = request.getParameter("text10"); 
        	String text11 = request.getParameter("text11"); 
        	String text12 = request.getParameter("text12"); 
        	
        	insertInfo.setJanuary(text1);
        	insertInfo.setFebruary(text2);
        	insertInfo.setMarch(text3);
        	insertInfo.setApril(text4);
        	insertInfo.setMay(text5);
        	insertInfo.setJune(text6);
        	insertInfo.setJuly(text7);
        	insertInfo.setAugust(text8);
        	insertInfo.setSeptember(text9);
        	insertInfo.setOctober(text10);
        	insertInfo.setNovember(text11);
        	insertInfo.setDecember(text12);
    	}
    
    	
    	
    	insertInfo.setVehicle_ln(vehicle_ln);
    	insertInfo.setVehicle_vin(vehicle_ln);
    	insertInfo.setCheckYear(checkYear);
    	
    	 List < CheckOilSetInfo > list = new ArrayList < CheckOilSetInfo >();
    	 list.add(insertInfo);
    	
    	 List<InsertCheckOilList> insertList = formatDriverInfos(list);
         try {
             // 导入考核油耗信息
         	for(int k=0;k<insertList.size();k++){
         		checkOilSetService.importCheckOilSetInfos(insertList.get(k));
         	}
         	setMessage("file.import.success");
         }
         	catch (Exception e) {
         		log.error(editTitle, e);
                addActionError(e.getMessage());
                return ERROR;
			}
         	setMessage("vehcileinfo.editsuccess.message");
            // 设置操作描述
            this.addOperationLog(formatLog(editTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.UPDATE);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_VEHMANAGE_UPDATE_ID); 
         	return SUCCESS;
         	 
    	
    	
    }
   
    
    
    
    private String formatLog(String editTitle, Object object) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
     * 查询未设置考核油耗的车辆
     * @return
     */
    
    
    public String selectVehicleLn(){
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
    	checkInfo.setCheckTimeCode(request.getParameter("checkYear"));
    	return SUCCESS;
    }
    
    public String getVehicleList(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	UserInfo user = getCurrentUser();
    	
    	// 每页显示条数
        String rpNum = request.getParameter("rp");
        // 当前页码
        String pageIndex = request.getParameter("page");
        // 排序字段名
        String sortName = request.getParameter("sortname");
        // 升序OR降序
        String sortOrder = request.getParameter("sortorder");
	   	int totalCount = 0;
	   	CheckOilSetInfo checkOilSetInfo=new CheckOilSetInfo();
	   	checkOilSetInfo.setCheckYear(request.getParameter("checkYear"));
	   	checkOilSetInfo.setVehicle_code(request.getParameter("vehicle_code"));
	   	checkOilSetInfo.setVehicle_ln(request.getParameter("vehicle_ln"));
	   	checkOilSetInfo.setVehicle_vin(request.getParameter("vehicle_vin"));
	   	checkOilSetInfo.setOrganization_id(user.getOrganizationID());
	   	checkOilSetInfo.setEnterpriseId(user.getEntiID());
	   	checkOilSetInfo.setSortName(sortName);
	   	checkOilSetInfo.setSortOrder(sortOrder);
	   	try{
	   		 totalCount = service.getCount("CheckOilSet.getVehicleVinCount",checkOilSetInfo);            
	            checkOilList = (List < CheckOilSetInfo >) service.getObjectsByPage(
	                    "CheckOilSet.getVehicleVin", checkOilSetInfo, (Integer
	                            .parseInt(pageIndex) - 1)
	                            * Integer.parseInt(rpNum), Integer
	                            .parseInt(rpNum));
	       	
	           
	   	}catch (Exception e) {
	   		setMessage("file.export.error");
	           log.error("Export student error:" + e.getMessage());
	           return ERROR;
			}
	       
	   	this.vehicleVinMap=getVehiclePagination(checkOilList, totalCount, pageIndex);
	   	return SUCCESS;
	   
	    	
	    }
    
    public Map getVehiclePagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

        	CheckOilSetInfo s = (CheckOilSetInfo) list.get(i);

            Map cellMap = new LinkedHashMap();            

            cellMap.put("id", s.getVehicle_vin());
            cellMap.put("cell", new Object[] {
            		s.getVehicle_code(),
            		s.getVehicle_ln(),    
            		s.getVehicle_vin()
                    });  

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    
    
    
    
    
    
    /**
     * 考核油耗设置页面--加载列表
     * @return
     */
    
    public String checkOilListDong(){
    	 HttpServletRequest request = ServletActionContext.getRequest();
    	 int totalCount = 0;
    	 UserInfo user = getCurrentUser();
    	 
    	 checkOilSetInfo.setOrganization_id(user.getOrganizationID());
    	 // 每页显示条数
         String rpNum = request.getParameter("rp");
         // 当前页码
         String pageIndex = request.getParameter("page");
         // 排序字段名
         String sortName = request.getParameter("sortname");
         // 升序OR降序
         String sortOrder = request.getParameter("sortorder");
         String vehicle_vin = request.getParameter("vehicle_vin");
         checkOilSetInfo.setSortName(sortName);
         checkOilSetInfo.setSortOrder(sortOrder);
         String checkYear=getCheckYear();
         checkOilSetInfo.setCheckYear(checkYear);
         checkOilSetInfo.setVehicle_code(request.getParameter("vehicle_code"));
         checkOilSetInfo.setVehicle_ln(request.getParameter("vehicle_ln"));
         checkOilSetInfo.setVehicle_vin(vehicle_vin);
         checkOilSetInfo.setVehicle_type(request.getParameter("vehicle_type"));
         
         
         //endtime= DateUtil.getCurrentDay();
         if(checkOilSetInfo.getCheckYear()==null||checkOilSetInfo.getCheckYear().equals("")){
        	 checkOilSetInfo.setCheckYear(DateUtil.getYear());
         }
 	
    	try{
    		 totalCount = service.getCount("CheckOilSet.getCheckOilCount",checkOilSetInfo);
             
             checkOilList = (List < CheckOilSetInfo >) service.getObjectsByPage(
                     "CheckOilSet.getCheckOilList", checkOilSetInfo, (Integer
                             .parseInt(pageIndex) - 1)
                             * Integer.parseInt(rpNum), Integer
                             .parseInt(rpNum));
        	
            
    	}catch (Exception e) {
    		setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
		}

    	this.checkOilSetMap=getPagination(checkOilList, totalCount, pageIndex);
    	return SUCCESS;
    }
    public Map getPagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

        	CheckOilSetInfo s = (CheckOilSetInfo) list.get(i);

            Map cellMap = new LinkedHashMap();            

            cellMap.put("id", s.getVehicle_vin());
            cellMap.put("cell", new Object[] {
            		null,                 //复选框位置，空出来
            		s.getVehicle_code(),
            		s.getVehicle_ln(),    
            		s.getVehicle_vin(),
            		s.getVehicle_type(),//座位数
            		//"2013", //考核年份
            		s.getCheckYear(),
                    s.getJanuary(),
                    s.getFebruary(),
                    s.getMarch(),
                    s.getApril(),
                    s.getMay(),
                    s.getJune(),
                    s.getJuly(),
                    s.getAugust(),
                    s.getSeptember(),
                    s.getOctober(),
                    s.getNovember(),
                    s.getDecember(),
                    s.getCreater(),
                    s.getCreate_time(),
                    s.getModifier(),
                    s.getModify_time()                    
                    });  

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    
    
    
    public String exportCheckOilSet() throws UnsupportedEncodingException{
        List < CheckOilSetInfo > list = new ArrayList < CheckOilSetInfo >();
        try {
           
            list = (List < CheckOilSetInfo >) service.getObjects(
                    "CheckOilSet.getCheckOilList", checkOilSetInfo);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export checkOilSetInfo error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export checkOilSetInfo error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "CheckOilSetInfo.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("考核百公里油耗设置");

            excelExporter.putAutoExtendSheets("checkOilSetExport", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
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
		String fileName=URLEncoder.encode("考核百公里油耗设置","UTF-8");
		
        HttpServletResponse response = ServletActionContext.getResponse();
        response
                .setHeader("Content-disposition", "attachment;filename="+fileName+"-" + DateUtil.getCurrentDayTime() + ".xls");
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
            log.error("Export driver error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export driver error:" + e.getMessage());
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
          
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_DRIVERMANAGE_EXPORT);
        }
        // 导出文件成功
        return null;
        
    }
    
    
    /**
     * 导入页面初始化
     * @return
     */
    public String importCheckOilSet() {
        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }
    /**
     * 获取文件内容错误
     * @return
     */
    private String getFileContentError(List < IEErrorMessage > list) {
        String errMsg = "";
        if (list.size() == 0) {
            return "";
        }
        for (IEErrorMessage tmp : list) {
            String msg = String.format("行:%s 列:%s 错误[%s]", tmp.getRow(), tmp
                    .getCol(), tmp.getMessage());
            errMsg = errMsg.concat(msg);
        }

        if (errMsg.length() > 150) {
            errMsg = errMsg.substring(0, 150);
            errMsg = errMsg.concat("......");
        }

        return errMsg;
    }
    
    /**
     * 转换为插库的列
     * @return
     */
    private List < InsertCheckOilList > formatDriverInfos(List < CheckOilSetInfo > list) {
        List <InsertCheckOilList> insertList = new ArrayList < InsertCheckOilList >();
        //InsertCheckOilList  inserObj=new InsertCheckOilList();
        
         
        for (int j=0;j<list.size();j++) { 	
        	
        	CheckOilSetInfo tmp=list.get(j);
            if (tmp.getVehicle_ln()!= null
                    && tmp.getVehicle_ln().length() == 0   
                    && tmp.getVehicle_vin()!= null
                    && tmp.getVehicle_vin().length() == 0
                    && tmp.getVehicle_type()!= null
                    && tmp.getVehicle_type().length() == 0    
                    && tmp.getCheckYear()!= null
                    && tmp.getCheckYear().length() == 0
                    && tmp.getJanuary()!= null
                    && tmp.getJanuary().length() == 0                    
                    && tmp.getFebruary()!= null
                    && tmp.getFebruary().length() == 0   
                    && tmp.getMarch()!= null
                    && tmp.getMarch().length() == 0
                    && tmp.getApril()!= null
                    && tmp.getApril().length() == 0
                    && tmp.getMay()!= null
                    && tmp.getMay().length() == 0
                    && tmp.getJune()!= null
                    && tmp.getJune().length() == 0
                    && tmp.getJuly()!= null
                    && tmp.getJuly().length() == 0
                    && tmp.getAugust()!= null
                    && tmp.getAugust().length() == 0
                    && tmp.getSeptember()!= null
                    && tmp.getSeptember().length() == 0
                    && tmp.getOctober()!= null
                    && tmp.getOctober().length() == 0
                    && tmp.getNovember()!= null
                    && tmp.getNovember().length() == 0                    
                    && tmp.getDecember()!= null
                    && tmp.getDecember().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                for(int i=1;i<=12;i++){
                	InsertCheckOilList  inserObj=new InsertCheckOilList();
                	 inserObj.setCheck_id(UUIDGenerator.getUUID());
                	 
                	 if(i<10){
                		 inserObj.setCheck_ime_code(tmp.getCheckYear().trim()+("-0").trim()+i);
                	 }else{
                		 inserObj.setCheck_ime_code(tmp.getCheckYear().trim()+"-".trim()+i);
                	 }
                	 
                	
                	 
                     if(i==1){
                    	 inserObj.setCheck_value(tmp.getJanuary());
                     }else if(i==2){
                    	 inserObj.setCheck_value(tmp.getFebruary());
                     }else if(i==3){
                    	 inserObj.setCheck_value(tmp.getMarch());
                     }else if(i==4){
                    	 inserObj.setCheck_value(tmp.getApril());
                     }else if(i==5){
                    	 inserObj.setCheck_value(tmp.getMay());
                     }else if(i==6){
                    	 inserObj.setCheck_value(tmp.getJune());
                     }else if(i==7){
                    	 inserObj.setCheck_value(tmp.getJuly());
                     }else if(i==8){
                    	 inserObj.setCheck_value(tmp.getAugust());
                     }else if(i==9){
                    	 inserObj.setCheck_value(tmp.getSeptember());
                     }else if(i==10){
                    	 inserObj.setCheck_value(tmp.getOctober());
                     }else if(i==11){
                    	 inserObj.setCheck_value(tmp.getNovember());
                     }else{
                    	 inserObj.setCheck_value(tmp.getDecember());
                     }
                     UserInfo user = getCurrentUser();
                     inserObj.setVehicle_vin(tmp.getVehicle_vin());
                     inserObj.setCreater(user.getUserName());
                     inserObj.setModifier(user.getUserName());
                     inserObj.setEnterpriseId(user.getEntiID());
                     
                     insertList.add(inserObj);
                }
                
                
            }
        }

        return insertList;
    }

    /**
     * 导入考核油耗信息
     * @return
     */
    public String importCheckOilInfo() {
        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }

        List < CheckOilSetInfo > list = new ArrayList < CheckOilSetInfo >();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            IEExcelImporter excelImplortor = null;
            try {
                excelImplortor = new IEExcelImporter(fis);
            } catch (Exception e) {
                setMessage("file.import.error");
                log.error("Import file error:" + e.getMessage());
            }
            
            list = excelImplortor.getSheetData("importCheckOilSetInfo", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                addActionError(errMessage);
                return ERROR;
            }
        } catch (Exception e) {
            setMessage(getText("file.import.error"));
            log.error("Import file error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

       

        if (list.size() == 0) {
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        } else {
        	 // 格式化数据        
            List<InsertCheckOilList> insertList = formatDriverInfos(list);
            try {
                // 导入考核油耗信息
            	for(int k=0;k<insertList.size();k++){
            		checkOilSetService.importCheckOilSetInfos(insertList.get(k));
            	}
                
            		
                setMessage("file.import.success");
            } catch (BusinessException e) {
                addActionError(getText("info.db.error"));
                log.error("Import driver error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                addActionError(getText("info.db.error"));
                log.error("Import driver error:" + e.getMessage());
                return ERROR;
            } finally {
                // 设置操作描述
              
                // 设置操作类型
                this.setOperationType(Constants.IMPORT);
                // 设置所属应用系统
                this.setApplyId(Constants.CLW_P_CODE);
                // 设置所属模块
                this.setModuleId(MouldId.XCP_DRIVERMANAGE_IMPORT);
            }
        }

        return SUCCESS;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	public CheckOilSetInfo getCheckOilSetInfo() {
		return checkOilSetInfo;
	}

	public void setCheckOilSetInfo(CheckOilSetInfo checkOilSetInfo) {
		this.checkOilSetInfo = checkOilSetInfo;
	}

	public List<CheckOilSetInfo> getCheckOilList() {
		return checkOilList;
	}

	public void setCheckOilList(List<CheckOilSetInfo> checkOilList) {
		this.checkOilList = checkOilList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map getCheckOilSetMap() {
		return checkOilSetMap;
	}

	public void setCheckOilSetMap(Map checkOilSetMap) {
		this.checkOilSetMap = checkOilSetMap;
	}

	
	
	
	
	
	
	
	
	
	public Map getVehicleVinMap() {
		return vehicleVinMap;
	}

	public void setVehicleVinMap(Map vehicleVinMap) {
		this.vehicleVinMap = vehicleVinMap;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public CheckOilSetService getCheckOilSetService() {
		return checkOilSetService;
	}

	public void setCheckOilSetService(CheckOilSetService checkOilSetService) {
		this.checkOilSetService = checkOilSetService;
	}



	public CheckInfo getCheckInfo() {
		return checkInfo;
	}



	public void setCheckInfo(CheckInfo checkInfo) {
		this.checkInfo = checkInfo;
	}



	public Map getCheckValueTimeMap() {
		return checkValueTimeMap;
	}



	public void setCheckValueTimeMap(Map checkValueTimeMap) {
		this.checkValueTimeMap = checkValueTimeMap;
	}


	public MonthStartEnd getMonthStartEnd() {
		return monthStartEnd;
	}


	public void setMonthStartEnd(MonthStartEnd monthStartEnd) {
		this.monthStartEnd = monthStartEnd;
	}


	public CheckMonthInfo getCheckMonthInfo() {
		return checkMonthInfo;
	}


	public void setCheckMonthInfo(CheckMonthInfo checkMonthInfo) {
		this.checkMonthInfo = checkMonthInfo;
	}


	public List<MonthStartEnd> getCheckMonthlist() {
		return checkMonthlist;
	}


	public void setCheckMonthlist(List<MonthStartEnd> checkMonthlist) {
		this.checkMonthlist = checkMonthlist;
	}    
   
	
	
	
}
