package com.neusoft.clw.sysmanage.datamanage.photographmanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.googlecode.jsonplugin.JSONUtil;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.StringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.photographmanage.domain.PhotoGraphSet;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.opensymphony.xwork2.ActionContext;

public class PhotoGraphAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示数据list **/
    private List < PhotoGraphSet > vehcList;
    
    private List <PhotoGraphSet> pgsList = new ArrayList<PhotoGraphSet>();

    /**
     * 车辆VIN
     */
    private String vehicleVin;
    
    private String isOperator;
    
    private String weeks;
    
    private String message = null;

    private String organization_id;
    
    private String rangeObjStr;
    
    private String pointObjStr;
    
    private String setIds;
    
      
    public String getIsOperator() {
		return isOperator;
	}

	public void setIsOperator(String isOperator) {
		this.isOperator = isOperator;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<PhotoGraphSet> getPgsList() {
		return pgsList;
	}

	public void setPgsList(List<PhotoGraphSet> pgsList) {
		this.pgsList = pgsList;
	}

	public String getRangeObjStr() {
		return rangeObjStr;
	}

	public void setRangeObjStr(String rangeObjStr) {
		this.rangeObjStr = rangeObjStr;
	}

	public String getPointObjStr() {
		return pointObjStr;
	}

	public void setPointObjStr(String pointObjStr) {
		this.pointObjStr = pointObjStr;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	public String getVehicleVin() {
		return vehicleVin;
	}

	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}

	public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    private Map map = new HashMap();

	public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }


    public List < PhotoGraphSet > getVehcList() {
        return vehcList;
    }

    public void setVehcList(List < PhotoGraphSet > vehcList) {
        this.vehcList = vehcList;
    }

    /**
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }
    
    

    public String getSetIds() {
		return setIds;
	}

	public void setSetIds(String setIds) {
		this.setIds = setIds;
	}

	/**
     * 页面初始化
     * @return
     */
    public String init() {
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    public String initSet(){
    	
    	return SUCCESS;
    }

    /**
     * 定时拍照页面浏览车辆
     * @return
     */
    public String vehiclebrowse() {
        HttpServletRequest request = ServletActionContext.getRequest();
        final String browseTitle = "";
        String logid = getlogid();
        log.info("logid:" + logid + "," + " 定时拍照设置车辆页面浏览!");
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
            	mpas.put("vehicleVin", StringUtil.toStringList(vehicleVin));
            }
            if(null != weeks && weeks.length() != 0){
            	mpas.put("weeks", StringUtil.toStringList(weeks));
            }
        	mpas.put("sortname",sortName);
        	mpas.put("sortorder", sortOrder);
        	
        	totalCount = service.getCount("photoGrapSet.findVehicleListCount", mpas);
        	
        	vehcList = service.getObjectsByPage(
					"photoGrapSet.findVehicleList", mpas, (Integer
							.parseInt(pageIndex) - 1)
							* Integer.parseInt(rpNum), Integer.parseInt(rpNum));
        	
        	map = getPagination(vehcList,totalCount,pageIndex);
        	
            if (0 == vehcList.size()) {
                this.addActionMessage(getText("nodata.list"));
            }
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.YTP_VEHMANAGE_QUREY_ID);
        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error("logid:" + logid + "," + browseTitle, e);
            return ERROR;
        }

        return SUCCESS;
    }
    
    public Map getPagination(List list, int totalCountDay, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

        	PhotoGraphSet s = (PhotoGraphSet) list.get(i);

            Map cellMap = new LinkedHashMap();

            if (null == s.getShortFullName()
                    || s.getShortFullName().length() == 0) {
                s.setShortFullName(getText("vehcileinfo.status.one"));
            }

            cellMap.put("id", s.getVehicleVin());

            cellMap.put("cell", new Object[] {s.getVehicleVin(),s.getEnterpriseId(),
            		s.getOrganizationId(),s.getIsSet(),s.getVehicleln(),s.getFullName(),
            		s.getShortFullName(),s.getVehbrand(),s.getLimitNumber() });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    public String findSignVehicleSet(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String logid = getlogid();
        log.info("logid:" + logid + "," + "车辆拍照设置查询!");
        Map<String,Object> mpas = new HashMap<String,Object>();
    	try {
//    		if(null != weeks && weeks.length() > 0){
//    			mpas.put("weeks",  weeks));
//    		}
    		if(null != vehicleVin && vehicleVin.length() > 0){
    			mpas.put("vehicleVin",  StringUtil.toStringList(vehicleVin));
    		}
    		
    		vehcList = service.getObjects("photoGrapSet.findSignPhotoSet", mpas);
    		map.put("list", vehcList);
    		map.put("listCount", vehcList.size());
    	} catch (Exception e) {
    		addActionError(getText(e.getMessage()));
            log.error("车辆定时拍照设置查询出错！", e);
            e.printStackTrace();
            return ERROR;
    	}
    	return SUCCESS;
    }
    
    public String addPhotoSet(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String logid = getlogid();
    	UserInfo users = getCurrentUser();
        log.info("logid:" + logid + "," + "车辆拍照设置!");
        Map<String,Object> mpas = new HashMap<String,Object>();
        
    	try {
    		mpas.put("vehicleVin", StringUtil.toStringList(vehicleVin));
    		//删除旧的拍照设置
    		service.delete("photoGrapSet.deletePhotoSet", mpas);
    		
    		String[] vins = vehicleVin.split(",");
    		
    		for(int k = 0,vinLen = vins.length; k < vinLen; k++){
    			
    			String vin = vins[k];
    			if(null != rangeObjStr && rangeObjStr.length() > 0){
		    		//范围拍照设置
		    		String[] objStr = this.rangeObjStr.split("!");
		    		for(int i = 0,len = objStr.length;i < len; i++){
		    			String[] phObj = objStr[i].split("#");
		    			
		    			String[] weeks = phObj[0].split(",");
		    			String startTime = phObj[1];
		    			String endTime = phObj[2];
		    			int interval = Integer.parseInt(phObj[3]);
		    			for(int j = 0,weekLen = weeks.length; j < weekLen; j++){
		    				PhotoGraphSet pgs = new PhotoGraphSet();
		    				pgs.setSetId(UUIDGenerator.getUUID().replace("-", ""));
		    				pgs.setVehicleVin(vin);
		    				pgs.setWeek(weeks[j]);
		    				pgs.setBeginTime(startTime);
		    				pgs.setEndTime(endTime);
		    				pgs.setInterval(String.valueOf(interval*60));
		    				pgs.setType("1");
		    				pgs.setCreater(users.getUserID());
		    				pgsList.add(pgs);
		    			}
		    			
		    		}
    			}
	    		
    			if(null != pointObjStr && pointObjStr.length() > 0){
		    		//定点拍照设置
		    		String[] objPointStr = this.pointObjStr.split("!");
		    		for(int i = 0,len = objPointStr.length;i < len; i++){
		    			String[] phObj = objPointStr[i].split("#");
		    			
		    			String[] weeks = phObj[0].split(",");
		    			String startTime = phObj[1];
		    			String endTime = phObj[2];
		    			for(int j = 0,weekLen = weeks.length; j < weekLen; j++){
		    				PhotoGraphSet pgs = new PhotoGraphSet();
		    				pgs.setSetId(UUIDGenerator.getUUID().replace("-", ""));
		    				pgs.setVehicleVin(vin);
		    				pgs.setWeek(weeks[j]);
		    				pgs.setBeginTime(startTime);
		    				pgs.setEndTime(endTime);
		    				pgs.setInterval("0");
		    				pgs.setType("2");
		    				pgs.setCreater(users.getUserID());
		    				pgsList.add(pgs);
		    			}
		    			
		    		}
    			}
    		}
    		service.addBatchPhotoSet(pgsList);
    		map.put("message", "success");
    	} catch (Exception e) {
    		addActionError(getText(e.getMessage()));
            log.error("车辆定时拍照设置查询出错！", e);
            e.printStackTrace();
            map.put("message", "error");
            return ERROR;
    	}
    	return SUCCESS;                
    }
    
    public String deletePhotoSet(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String logid = getlogid();
    	UserInfo users = getCurrentUser();
        log.info("logid:" + logid + "," + "车辆拍照设置删除!");
        Map<String,Object> mpas = new HashMap<String,Object>();
    	try {
    		mpas.put("vehicleVin", StringUtil.toStringList(vehicleVin));
    		//删除旧的拍照设置
    		service.delete("photoGrapSet.deletePhotoSet", mpas);
    		map.put("message", "success");
    	} catch (Exception e) {
    		addActionError(getText(e.getMessage()));
            log.error("车辆定时拍照设置删除出错！", e);
            e.printStackTrace();
            map.put("message", "error");
            return ERROR;
    	}
    	return SUCCESS;   
    }
    
    public String deleteSetIds(){
    	HttpServletRequest request = ServletActionContext.getRequest();
    	String logid = getlogid();
    	UserInfo users = getCurrentUser();
        log.info("logid:" + logid + "," + "车辆拍照设置删除单条!");
        Map<String,Object> mpas = new HashMap<String,Object>();
    	try {
    		mpas.put("setIds", StringUtil.toStringList(setIds));
    		//删除旧的拍照设置
    		service.delete("photoGrapSet.deleteSetIds", mpas);
    		map.put("message", "success");
    	} catch (Exception e) {
    		addActionError(getText(e.getMessage()));
            log.error("车辆定时拍照设置单条删除出错！", e);
            e.printStackTrace();
            map.put("message", "error");
            return ERROR;
    	}
    	return SUCCESS;   
    }
    

    private String formatIds(String regions) {
        String ret = "";
        String[] strs = regions.split(",");
        for (int i = 0; i < strs.length; i++) {
            String tmp = strs[i];
            if (null != tmp && tmp.length() > 0) {
                tmp = tmp.substring(1, tmp.length() - 1);
                strs[i] = tmp;
            }
        }
        for (int i = 0; i < strs.length; i++) {
            if (ret == "") {
                ret = ret.concat(strs[i]);
            } else {
                ret = ret.concat(",").concat(strs[i]);
            }
        }
        return ret;
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
     * 获得当前操作LOGID
     * @return
     */
    private String getlogid() {
        return (String) ActionContext.getContext().getSession().get(
                Constants.LOG_USE_ID);
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, VehcileInfo vehObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != vehObj) {
            if (null != vehObj.getVehicle_id()) {
                OperateLogFormator.format(sb, "vehicleid", vehObj
                        .getVehicle_id());
            }
        }
        return sb.toString();
    }

}
