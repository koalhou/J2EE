package com.neusoft.clw.yw.ftyleNew.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.StudentInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.yw.ftly.ds.StealOilSmsInfo;
import com.neusoft.clw.yw.ftly.service.StealOilSmsService;
import com.opensymphony.xwork2.ActionContext;

public class StealOilSmsAction extends PaginationAction{
	protected Logger logger = Logger.getLogger(StealOilSmsAction.class);
	private transient Service service;
	private StealOilSmsService stealOilSmsService;
	private Map<String,Object> map = new HashMap<String,Object>();
	private StealOilSmsInfo stealOilSmsInfo;
	private List<StealOilSmsInfo> stealOilSmsList;
	List <UserInfo> staffList;
	private String update;
	private String message = null;//显示消息 
	private String errorMessage;//错误提示信息
	
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public StealOilSmsService getStealOilSmsService() {
		return stealOilSmsService;
	}
	public void setStealOilSmsService(StealOilSmsService stealOilSmsService) {
		this.stealOilSmsService = stealOilSmsService;
	}
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public StealOilSmsInfo getStealOilSmsInfo() {
		return stealOilSmsInfo;
	}
	public void setStealOilSmsInfo(StealOilSmsInfo stealOilSmsInfo) {
		this.stealOilSmsInfo = stealOilSmsInfo;
	}
	public List<StealOilSmsInfo> getStealOilSmsList() {
		return stealOilSmsList;
	}
	public void setStealOilSmsList(List<StealOilSmsInfo> stealOilSmsList) {
		this.stealOilSmsList = stealOilSmsList;
	}
	public List<UserInfo> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<UserInfo> staffList) {
		this.staffList = staffList;
	}
	public String getUpdate() {
		return update;
	}
	public void setUpdate(String update) {
		this.update = update;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	/**
	 * 页面跳转到stealoil_sms.jsp
	 * @return
	 */
	public String stealOilSms(){
		return SUCCESS;
	}
	
	/**
	 * 列表
	 * @return
	 */
	public String stealOilSmsList(){
        MDC.put("modulename", "[stealoilsms]");
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);;
        try {
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            if (StringUtils.isEmpty(pageIndex)) 
                pageIndex = "1";
            if (StringUtils.isEmpty(rpNum)) 
                rpNum = "10";
            stealOilSmsInfo.setSortname(sortName);
            stealOilSmsInfo.setSortorder(sortOrder);
            stealOilSmsInfo.setOrganization_id(user.getOrganizationID());
            int totalCount = service.getCount("StealOilSms.getStealOilSmsCount", stealOilSmsInfo);
            stealOilSmsList = service.getObjectsByPage("StealOilSms.getStealOilSmsInfos",stealOilSmsInfo, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            this.map = getPagination(stealOilSmsList, totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
			super.addActionError(getText("info.db.error"));
			log.error("Query registered vehicles error:" + e.getMessage(),e);
			return ERROR;
		} catch (Exception e) {
			super.addActionError(getText("info.db.error"));
			log.error("Query registered vehicles error:" + e.getMessage(),e);
			return ERROR;
		}finally{
			 stealOilSmsInfo.setStu_id("");
			 stealOilSmsInfo.setStu_name("");
			 stealOilSmsInfo.setTelephone("");
			 stealOilSmsInfo.setOrganization_id_s("");
	     }
        return SUCCESS;
	}
	
	 public Map<String,Object> getPagination(List<StealOilSmsInfo> stealOilSmsList, int totalCountDay, String pageIndex, String rpNum) {
	        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
	        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
	        try{
		        for (int i = 0; i < stealOilSmsList.size(); i++) {
		        	StealOilSmsInfo s = (StealOilSmsInfo) stealOilSmsList.get(i);
		        	String organization_id = s.getOrganization_id();
		        	String allName="";
		        	for(String org : organization_id.split(",")){
		        		List<String> orgNameList = (List<String>)service.getObjects("StealOilSms.getOrganizationName",org);
		        		if(orgNameList.size()== 1){//是叶子节点
		        			allName += orgNameList.get(0)+",";
		        		}
		        	}
		        	s.setOrganization_name(allName.substring(0, allName.length()-1));
		            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
		            cellMap.put("id", s.getStu_id());
		            cellMap.put("cell", new Object[] {null,
		                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
		                    s.getStu_name(),
		                    s.getTelephone(),
		                    s.getOrganization_name(),
		                    s.getStu_id(),
		                    s.getModifie_time()
		                    });
		            mapList.add(cellMap);
		        }
	        } catch (BusinessException e) {
				super.addActionError(getText("info.db.error"));
				log.error("Query registered vehicles error:" + e.getMessage(),e);
			} 
	        mapData.put("page", pageIndex);// 从前台获取当前第page页
	        mapData.put("total", totalCountDay);// 从数据库获取总记录数
	        mapData.put("rows", mapList);
	        return mapData;
	    }
	 
	 /**
	  * 添加页面跳转到stealOilSmsAdd.jsp
	  * @return
	  */
	 public String addStealOilSms(){
			return SUCCESS;
	}
	 
	 /**
	  * 选择人员页面跳转到showStudent,jsp
	  * @return
	  */
	 public String showStudent(){
		 return SUCCESS;
	 }
	 
	 /**
	  * 选择人员列表
	  * @return
	  */
	 public String showStudentList(){
		    final String vehTitle = "人员选择列表";
	        UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
	        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
	        String sortName = request.getParameter("sortname");
	        String sortOrder = request.getParameter("sortorder");
	        int totalCount = 0;
	        String rpNum = request.getParameter("rp");// 每页显示条数
			String pageIndex = request.getParameter("page");// 当前页码
	        try {  
	        	UserInfo	info = new UserInfo();
                info.setOrganizationID(user.getOrganizationID());
                info.setEntiID(user.getEntiID());
                totalCount = service.getCount("StealOilSms.getStaffCount", info);
                staffList = (List <UserInfo>) service.getObjectsByPage("StealOilSms.getStaffShow", info, (Integer
						.parseInt(pageIndex) - 1)
						* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
                this.map = getPaginationStu(staffList, totalCount, pageIndex);
	        } catch (BusinessException e) {
	            addActionError(getText(e.getMessage()));
	            log.error(vehTitle, e);
	            return ERROR;
	        }
	        return SUCCESS;
	 }
	 
	 public Map<String,Object> getPaginationStu(List<UserInfo> staffList, int totalCount, String pageIndex) {
	        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
	        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
	        for (int i = 0; i < staffList.size(); i++) {
	        	UserInfo s = (UserInfo) staffList.get(i);
	            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
	            cellMap.put("id", s.getUserID());
	            cellMap.put("cell", new Object[] {
	            		s.getUserName(), 
	            		s.getUserID(),
	                    s.getMoblie(),
	                    s.getRemarks()});
	            mapList.add(cellMap);
	        }
	        mapData.put("page", pageIndex);// 从前台获取当前第page页
	        mapData.put("total", totalCount);// 从数据库获取总记录数
	        mapData.put("rows", mapList);
	        return mapData;
	    }
	 
	 /**
	  * 检查电话号码唯一性
	  * @return
	  */
	 public void checkTelOnly(){
		 try{
			 HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
			 String telephone = request.getParameter("telephone");
			 String stu_id = request.getParameter("stu_id");
			 String type = request.getParameter("type") == null?"": request.getParameter("type");
			 Map<String,Object> map = new HashMap<String,Object>();
			 map.put("telephone", telephone);
			 map.put("stu_id", stu_id);
			 map.put("type", type);
			 int count = (Integer)service.getObject("StealOilSms.checkTel", map);
			 if(count > 0)
				 printWriter("error");
			 else
				 printWriter("success");
		 } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(e);
        }
	 }
	 
	 /**
	  * 添加短信设置
	  * @return
	  */
	 public String insertStealOilSms(){
		 MDC.put("modulename", "[stealoilsms]");
		 try{
			 UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
			 stealOilSmsInfo.setId(UUIDGenerator.getUUID32());
			 stealOilSmsInfo.setCreate_by(user.getUserID());
			 stealOilSmsInfo.setModifie_by(user.getUserID());
			 stealOilSmsInfo.setEnterprise_id(user.getOrganizationID());
			 stealOilSmsInfo.setValid_flag("0");
			 stealOilSmsService.insert(stealOilSmsInfo);
		 } catch (Exception e) {
	     		super.addActionError(getText("info.db.error"));
				log.error("delete stealoilsms error:" + e.getMessage(),e);
				return ERROR;
		 }finally{
			 stealOilSmsInfo.setStu_id("");
			 stealOilSmsInfo.setStu_name("");
			 stealOilSmsInfo.setTelephone("");
			 stealOilSmsInfo.setOrganization_id_s("");
			 setMessage("userinfo.create.success");  
	     }
		 return SUCCESS;
	 }
	 
	 /**
	  * 跳转到修改页面
	  * @return
	  */
	 public String editStealOilSms(){
		 try{
			 stealOilSmsInfo = (StealOilSmsInfo)service.getObject("StealOilSms.getStealOilSmsByStu",stealOilSmsInfo);
			 this.update = "update";
		 } catch (Exception e) {
	     	super.addActionError(getText("info.db.error"));
				log.error("edit stealoilsms error:" + e.getMessage(),e);
				return ERROR;
		 }
		 return SUCCESS;
	 }
	 
	 /**
	  * 修改设置
	  * @return
	  */
	 public String updateStealOilSms(){
		 try{
			 UserInfo user = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
			 stealOilSmsInfo.setId(UUIDGenerator.getUUID32());
			 stealOilSmsInfo.setStu_name(stealOilSmsInfo.getStu_name_old());
			 stealOilSmsInfo.setEnterprise_id(user.getOrganizationID());
			 stealOilSmsInfo.setCreate_by(user.getUserID());
			 stealOilSmsInfo.setModifie_by(user.getUserID());
			 stealOilSmsInfo.setValid_flag("0");
			 stealOilSmsService.update(stealOilSmsInfo);
		 } catch (Exception e) {
	     	super.addActionError(getText("info.db.error"));
				log.error("delete stealoilsms error:" + e.getMessage(),e);
				return ERROR;
		 }finally{
			 stealOilSmsInfo.setStu_id("");
			 stealOilSmsInfo.setStu_name("");
			 stealOilSmsInfo.setTelephone("");
			 stealOilSmsInfo.setOrganization_id_s("");
			 setMessage("userinfo.update.success");    
	     }
		 return SUCCESS;
	 }
	 
	 /**
	  * 删除设置
	  * @return
	  */
	 public String deleteStealOilSms(){
    	MDC.put("modulename", "[stealoilsms]");
        try {
            stealOilSmsService.batchDelete(stealOilSmsInfo);
        } catch (Exception e) {
        	super.addActionError(getText("info.db.error"));
			log.error("delete stealoilsms error:" + e.getMessage(),e);
			return ERROR;
        }finally{
        	 stealOilSmsInfo.setStu_id("");
			 stealOilSmsInfo.setStu_name("");
			 stealOilSmsInfo.setTelephone("");
			 stealOilSmsInfo.setOrganization_id_s("");
        	 setMessage("userinfo.del.success");  
        }
        return SUCCESS;
	 }
}
