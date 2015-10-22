package com.neusoft.clw.sysmanage.datamanage.wxmanage.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.sysmanage.datamanage.wxmanage.domain.AnnouncementInfo;
import com.opensymphony.xwork2.ActionContext;

public class AnnouncementAction extends PaginationAction{
	 /** service共通类 */
    private transient Service service;
    /** 显示数据list **/
    private List <AnnouncementInfo> announcementList;
    private AnnouncementInfo announcementInfo;
    /** 显示消息 **/
    private String message = null;
    private Map<String,Object> map = new HashMap<String,Object>();
    //查询条件
    private String author;
    private String start_time;
    private String end_time;
    //车辆列表
    private String vehicle_ln= "";
    private List<VehcileInfo> vehicleList;
    
    public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public List<AnnouncementInfo> getAnnouncementList() {
		return announcementList;
	}
	public void setAnnouncementList(List<AnnouncementInfo> announcementList) {
		this.announcementList = announcementList;
	}
	public AnnouncementInfo getAnnouncementInfo() {
		return announcementInfo;
	}
	public void setAnnouncementInfo(AnnouncementInfo announcementInfo) {
		this.announcementInfo = announcementInfo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public String getVehicle_ln() {
		return vehicle_ln;
	}
	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}
	public List<VehcileInfo> getVehicleList() {
		return vehicleList;
	}
	public void setVehicleList(List<VehcileInfo> vehicleList) {
		this.vehicleList = vehicleList;
	}
	/**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
    	start_time =DateUtil.getPreNDay(-29);
    	end_time = DateUtil.getCurrentDay();
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 浏览公告信息
     * @return
     */
    public String announcementBrowse() {
        final String browseTitle = getText("wx.announcement.browse.title");
        log.info(browseTitle);
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        UserInfo user = getCurrentUser();
        try {
            if (null == announcementInfo) {
            	announcementInfo = new AnnouncementInfo();
            }
            announcementInfo.setGonggao_author(author);
            announcementInfo.setStart_time(start_time+" 00:00:00");
            announcementInfo.setEnd_time(end_time+" 23:59:59");
            announcementInfo.setEnterprise_id(user.getOrganizationID());
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            announcementInfo.setSortname(sortName);
            announcementInfo.setSortorder(sortOrder);
            int totalCount = 0;
            totalCount = service.getCount("WxManage.getCount", announcementInfo);
            // Page pageObj = new Page(page, totalCount, pageSize, url, param);
            // this.pageBar = PageHelper.getPageBar(pageObj);
            announcementList = (List <AnnouncementInfo>) service.getObjectsByPage(
                    "WxManage.getInfos", announcementInfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            this.map = getPagination(announcementList, totalCount, pageIndex, rpNum);// 转换map
//            if (announcementList.size() == 0) {
//                addActionMessage(getText("nodata.list"));
//            }
            // 用于添加或者删除时显示消息
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(formatLog(browseTitle, null));
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
//            this.setModuleId(MouldId.XCP_DRIVERMANAGE_QUERY);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }
    
    /**
     * 转换Map
     * @param oilusedList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getPagination(List<AnnouncementInfo> announcementList, int totalCount, String pageIndex, String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        try{
	        for (int i = 0; i < announcementList.size(); i++) {
	        	AnnouncementInfo s = (AnnouncementInfo) announcementList.get(i);
	        	String [] vehTmp = new String[0];
	        	int wxUserCount=0;
	        	if(s.getGonggao_veh() != null && !"".equals(s.getGonggao_veh())){
	        		vehTmp = s.getGonggao_veh().split(",");
	        		String veh= "'"+s.getGonggao_veh().replaceAll(",", "','")+"'";
	        		wxUserCount = (Integer)service.getObject("WxManage.getWxUserCount", veh);
	        	}
	            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
	            cellMap.put("id", s.getGonggao_id());
	            cellMap.put("cell", new Object[] {
	                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
	                    s.getGonggao_title(),
	                    s.getUser_name(),
	                    s.getGonggao_date(),
	                    vehTmp.length,
	                    wxUserCount});
	            mapList.add(cellMap);
	        }
	        mapData.put("page", pageIndex);// 从前台获取当前第page页
	        mapData.put("total", totalCount);// 从数据库获取总记录数
	        mapData.put("rows", mapList);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
        }
        return mapData;
    }
    
    public String addBefore() {
        return SUCCESS;
    }
    
    public String addAnnouncement() {
        if (null == announcementInfo) {
            return addBefore();
        }
        final String addTitle = getText("announcement.add.info");
        log.info(addTitle);
        try {
            UserInfo user = getCurrentUser();
            announcementInfo.setGonggao_author(user.getUserID());
            announcementInfo.setGonggao_id(UUIDGenerator.getUUID());
            announcementInfo.setEnterprise_id(user.getOrganizationID());
            service.insert("WxManage.insertannouncementInfo", announcementInfo);
        } catch (BusinessException e) {
            log.error(addTitle, e);
            addActionError(e.getMessage());
            return ERROR;
        }
        setMessage("announcement.addsuccess.message");
        
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
//        this.setModuleId(MouldId.XCP_DRIVERMANAGE_ADD);
        return SUCCESS;
    }
    
    public String queryVehicle(){
    	try {
    		UserInfo user = getCurrentUser();
    		VehcileInfo vehinfo = new VehcileInfo();
			vehinfo.setOrganization_id(user.getOrganizationID());
			if (vehicle_ln != null && vehicle_ln != "") {
				vehinfo.setVehicle_ln(vehicle_ln.trim());
			}
            vehicleList = (List<VehcileInfo>)service.getObjects("WxManage.getInfosVeh", vehinfo);
            if (vehicleList != null && vehicleList.size() == 0) {
                // 无用户信息
                addActionError(getText("nodata.list"));
                return ERROR;
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        }
    	return SUCCESS;
    }
    
    public String showDetail(){
    	try{
    		announcementInfo=(AnnouncementInfo)service.getObject("WxManage.getGonggaoInfoById", announcementInfo);
    	}catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        }
    	return SUCCESS;
    }
    
    public String showVehicleDetail(){
    	try{
    		AnnouncementInfo info=(AnnouncementInfo)service.getObject("WxManage.getGonggaoInfoById", announcementInfo);
    		String vins = info.getGonggao_veh();
    		if(vins != null && !"".equals(vins)){
    			String vinStr="";
    			String [] tmp =vins.split(",");
	    		for(String v : tmp){
	    			vinStr += "'"+v+"',";
	    		}
	    		if(!"".equals(vinStr))
	    			vinStr = vinStr.substring(0,vinStr.length()-1);
	    		UserInfo user = getCurrentUser();
	    		VehcileInfo vehinfo = new VehcileInfo();
				vehinfo.setOrganization_id(user.getOrganizationID());
				if (vehicle_ln != null && vehicle_ln != "") {
					vehinfo.setVehicle_ln(vehicle_ln.trim());
				}
				vehinfo.setVehicle_vin(vinStr);
	    		vehicleList = (List<VehcileInfo>)service.getObjects("WxManage.getInfosVeh", vehinfo);
    		}
    	}catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        }
    	return SUCCESS;
    }
    
    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, AnnouncementInfo announcementInfoObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != announcementInfoObj) {
            if (null != announcementInfoObj.getGonggao_id()) {
                OperateLogFormator.format(sb, "gonggao_id", announcementInfoObj
                        .getGonggao_id());
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
}
