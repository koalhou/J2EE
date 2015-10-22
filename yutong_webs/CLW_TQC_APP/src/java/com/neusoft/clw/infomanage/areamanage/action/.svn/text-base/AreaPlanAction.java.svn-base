package com.neusoft.clw.infomanage.areamanage.action;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.infomanage.areamanage.domain.AreaInfo;
import com.neusoft.clw.infomanage.sitemanage.domain.Site;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 乘车规划管理
 * @author
 * @version Revision: 0.1 Date: Mar 25, 2011 2:01:14 PM
 */
public class AreaPlanAction extends PaginationAction {
	private transient Service service; //service共通类
	private String errorMessage;//错误提示信息
	private Map<String,Object> map = new HashMap<String,Object>();
	private List<Site> leftList = null;
	private List<Site> rightList = null;
	private List<AreaInfo> titleList = null;
	private Map < String, String > areaInfosMap = new HashMap < String, String >();
	private String[] site_ids;
	private String areaId;
	private String areaName;
	private String remark;
	private String message;
	
	private String max_longitude;
	private String max_latitude;
	private String min_latitude;
	private String min_longitude;
	private AreaInfo areaInfo;
    /**
     * 规划左侧树
     * @return
     */
    public String readyPage() {
        /**
         * 处理跳转过来的错误信息以及所需的当前企业ID作为查询条件设置
         */
        if (errorMessage != null) {
            addActionError(getText(errorMessage));
        }
        if(message!=null){
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    public String addareaready() {
        /**
         * 处理跳转过来的错误信息以及所需的当前企业ID作为查询条件设置
         */
        if (errorMessage != null) {
            addActionError(getText(errorMessage));
        }
        /*try {
        	Map<String,Object> mapData = new LinkedHashMap<String,Object>();
			titleList = service.getObjects("areaPlan.getAreaNameList", mapData);
			for (AreaInfo areaInfo : titleList) {
	            areaInfosMap.put(areaInfo.getAreaId(), areaInfo.getAreaName());
	        }
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        return SUCCESS;
    }
    public String updateareaReady() {
        if (errorMessage != null) {
            addActionError(getText(errorMessage));
        }
//        Map<String, Object> enmap = new HashMap<String, Object>();
//        enmap.put("areaId", areaInfo.getAreaId());
        try {
        	areaInfo = (AreaInfo) service.getObject("areaPlan.updateAreaInfo", areaInfo.getAreaId());
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return SUCCESS;
    }
    /**
     * 查询大区与站点关联信息
     * 
     * */
    public String areaPlanList() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	UserInfo user = getCurrentUser();
    	int totalCount = 0;
    	
    	String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
    	
    	Map<String, Object> enmap = new HashMap<String, Object>();
        enmap.put("sortname",sortName);
        enmap.put("sortorder",sortOrder);
        enmap.put("rowStart",(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
        enmap.put("rowEnd",(Integer.parseInt(pageIndex))*Integer.parseInt(rpNum));

        enmap.put("areaname",request.getParameter("areaname"));
        List<AreaInfo> areaPlanList = new ArrayList<AreaInfo>();
        try {
        	totalCount = service.getCount("areaPlan.getAreaListCountByParam", enmap);
        	areaPlanList = (List<AreaInfo>) service.getObjects("areaPlan.getAreaListByParam", enmap);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.map = getPagination(areaPlanList, totalCount, pageIndex, rpNum);
    	return SUCCESS;
    }
    public Map<String,Object> getPagination(List<AreaInfo> ridingPlanList, int totalCountDay, String pageIndex, String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        for (int i = 0; i < ridingPlanList.size(); i++) {
        	AreaInfo s = (AreaInfo) ridingPlanList.get(i);

            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();

            cellMap.put("id", s.getAreaId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getAreaName(),
                    s.getSitenum(),
                    s.getRemark(),
                    s.getCreateTime(),
                    s.getAreaId(),
                    s.getAreaId()
                    });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 规划左侧树
     * @return
     */
    public String addsiteareareadyPage() {
    	final String addBefTitle = "站点分配大区";
        log.info(addBefTitle);
        UserInfo user = getCurrentUser();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        try {
        	titleList = service.getObjects("areaPlan.getAreaNameList", mapData);
        	for (AreaInfo areaInfo : titleList) {
                areaInfosMap.put(areaInfo.getAreaId(), areaInfo.getAreaName());
            }
            leftList = service.getObjects("StationManage.getStationNoAreaInfo", mapData);
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(addBefTitle, e);
            return ERROR;
        } finally {
        }
        return SUCCESS;
    }
    
    /**
     * 分配站点
     * @return
     */
    public String add() {
        final String addTitle = getText("vehcileinfo.add.info");
        log.info(addTitle);
        StringBuffer sbuffer = new StringBuffer();
        try {
        	if(site_ids != null) {
	            String[] selectveh = site_ids;
	            for(int i = 0; i<selectveh.length;i++) {
	            	if(i!=selectveh.length-1) {
	            		sbuffer.append(selectveh[i]).append(",");
	            	} else {
	            		sbuffer.append(selectveh[i]);
	            	}
	            }
        	}
            Map<String,Object> mapData = new LinkedHashMap<String,Object>();
            //对应的大区内的站点批量更新
            mapData.put("area_id", areaId);
            mapData.put("site_ids", sbuffer.toString());
        	
            service.getObject("areaPlan.updatesite_area", mapData);
        } catch (BusinessException e) {
            log.error(addTitle, e);
            this.setErrorMessage("siteareainfo.adderror.message");
            return ERROR;
        }
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.YTP_VEHMANAGE_INSERT_ID);
        this.setMessage("siteareainfo.addsuccess.message");
        return SUCCESS;
    }
    /**
     * 添加区域
     * */
    public String area_add() {
//    	Map<String,Object> mapData = new LinkedHashMap<String,Object>();
//    	mapData.put("areaid", UUIDGenerator.getUUID());
//    	mapData.put("areaname", areaName);
//    	mapData.put("remark", remark);
//    	mapData.put("max_lon", this.max_longitude);
//    	mapData.put("max_lat", this.max_latitude);
//    	mapData.put("min_lon", this.min_longitude);
//    	mapData.put("min_lat", this.min_latitude);
//    	mapData.put("province", "");
//    	mapData.put("city", "");
//    	mapData.put("userid", getCurrentUser().getUserID());
    	try {
    		int i = checkAreaName(areaName);
    		if(i==0) {
    			service.insert("areaPlan.addarea", areaInfo);
    			this.setMessage("areainfo.addsuccess.message");
    	    	return SUCCESS;
    		} else {
    			this.setMessage("areainfo.savesuccess.message");
    	    	return SUCCESS;
    		}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.setErrorMessage("areainfo.adderror.message");
			return ERROR;
		}
		
    }
    public void getarea_site() {
    	StringBuffer sbuffer = new StringBuffer();
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        try {
        	mapData.put("areaid", request.getParameter("areaid"));
        	leftList = service.getObjects("StationManage.getStationNoAreaInfo", mapData);
        	rightList = service.getObjects("StationManage.getStationAreaInfo", mapData);
        	
        	sbuffer.append("[{leftv:[");
        	for(int i=0;i<leftList.size();i++) {
        		if(i!=leftList.size()-1)
        			sbuffer.append("{siteid:").append(leftList.get(i).getSite_id()).append(",sitename:'").append(SearchUtil.formatSpecialChar(leftList.get(i).getSite_name())).append("'},");
        		else
        			sbuffer.append("{siteid:").append(leftList.get(i).getSite_id()).append(",sitename:'").append(SearchUtil.formatSpecialChar(leftList.get(i).getSite_name())).append("'}");
        	}
        	sbuffer.append("],rightv:[");
        	for(int i=0;i<rightList.size();i++) {
        		if(i!=rightList.size()-1)
        			sbuffer.append("{siteid:").append(rightList.get(i).getSite_id()).append(",sitename:'").append(SearchUtil.formatSpecialChar(rightList.get(i).getSite_name())).append("'},");
        		else
        			sbuffer.append("{siteid:").append(rightList.get(i).getSite_id()).append(",sitename:'").append(SearchUtil.formatSpecialChar(rightList.get(i).getSite_name())).append("'}");
        	}
        	sbuffer.append("]}]");
        	printWriter(sbuffer.toString());
        } catch (BusinessException e) {
        	e.printStackTrace();
        }
    }
    public void getsiteinarea() {
    	StringBuffer sbuffer = new StringBuffer();
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        try {
        	mapData.put("areaid", request.getParameter("areaid"));
        	titleList = service.getObjects("areaPlan.selectsite_byareaid", mapData);
        	gpsUtil gpsu = new gpsUtil();
        	titleList = gpsu.checkLonLat(titleList);
        	
        	sbuffer.append("[{sites:[");
        	for(int i=0;i<titleList.size();i++) {
        		if(i!=titleList.size()-1)
        			sbuffer.append("{siteid:").append(titleList.get(i).getSiteId()).append(",sitename:'").append(SearchUtil.formatSpecialChar(titleList.get(i).getSiteName())).append("',lon:'").append(titleList.get(i).getSitelon()).append("',lat:'").append(titleList.get(i).getSitelat()).append("'},");
        		else
        			sbuffer.append("{siteid:").append(titleList.get(i).getSiteId()).append(",sitename:'").append(SearchUtil.formatSpecialChar(titleList.get(i).getSiteName())).append("',lon:'").append(titleList.get(i).getSitelon()).append("',lat:'").append(titleList.get(i).getSitelat()).append("'}");
        	}
        	sbuffer.append("]}]");
        	
        	printWriter(sbuffer.toString());
        } catch (BusinessException e) {
        	e.printStackTrace();
        }
    }
    public void deleteareaPlan() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	String areaid = request.getParameter("areaid");
    	Map<String,Object> mapData = new LinkedHashMap<String,Object>();
    	mapData.put("areaid", areaid);
    	try {
    		service.update("areaPlan.update_areasitebyid", mapData);
			int i = service.delete("areaPlan.del_area", mapData);
			printWriter(String.valueOf(i));
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public int checkAreaName(String name) {
    	Map<String,Object> mapData = new LinkedHashMap<String,Object>();
    	mapData.put("areaname", name);
    	try {
			return (Integer) service.getObject("areaPlan.select_areabyname", mapData);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    }
    public String area_update() {
    	try {
    		service.update("areaPlan.update_areabyid", areaInfo);
    		this.setMessage("areaupdateinfo.addsuccess.message");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.setMessage("error");
		}
    	return SUCCESS;
    }
    //站点区域合并查询
    public String areaSiteList() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	Map<String,Object> mapData = new LinkedHashMap<String,Object>();
    	mapData.put("areaname", request.getParameter("areaName"));
    	int totalCount = 0;
    	
    	String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

        mapData.put("sortname",sortName);
        mapData.put("sortorder",sortOrder);
        mapData.put("rowStart",(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum));
        mapData.put("rowEnd",(Integer.parseInt(pageIndex))*Integer.parseInt(rpNum));
    	try {
    		totalCount = service.getCount("areaPlan.select_areacountbyname", mapData);
    		List<AreaInfo> areasitel = service.getObjects("areaPlan.select_areainfobyname", mapData);
    		this.map = getPaginationSite(areasitel, totalCount, pageIndex, rpNum);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
    }
    public Map<String,Object> getPaginationSite(List<AreaInfo> ridingPlanList, int totalCountDay, String pageIndex, String rpNum) {
        List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
        Map<String,Object> mapData = new LinkedHashMap<String,Object>();
        for (int i = 0; i < ridingPlanList.size(); i++) {
        	AreaInfo s = (AreaInfo) ridingPlanList.get(i);

            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();

            cellMap.put("id", s.getAreaId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getAreaName(),
                    s.getAreapoint(),
                    s.getAreaId()
                    });

            mapList.add(cellMap);

        }

        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCountDay);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	/**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }
	public Map<String, Object> getMap() {
		return map;
	}
	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public List<Site> getLeftList() {
		return leftList;
	}
	public void setLeftList(List<Site> leftList) {
		this.leftList = leftList;
	}
	public List<AreaInfo> getTitleList() {
		return titleList;
	}
	public void setTitleList(List<AreaInfo> titleList) {
		this.titleList = titleList;
	}
	public Map<String, String> getAreaInfosMap() {
		return areaInfosMap;
	}
	public void setAreaInfosMap(Map<String, String> areaInfosMap) {
		this.areaInfosMap = areaInfosMap;
	}
	public String[] getSite_ids() {
		return site_ids;
	}
	public void setSite_ids(String[] site_ids) {
		this.site_ids = site_ids;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMax_longitude() {
		return max_longitude;
	}
	public void setMax_longitude(String max_longitude) {
		this.max_longitude = max_longitude;
	}
	public String getMax_latitude() {
		return max_latitude;
	}
	public void setMax_latitude(String max_latitude) {
		this.max_latitude = max_latitude;
	}
	public String getMin_latitude() {
		return min_latitude;
	}
	public void setMin_latitude(String min_latitude) {
		this.min_latitude = min_latitude;
	}
	public String getMin_longitude() {
		return min_longitude;
	}
	public void setMin_longitude(String min_longitude) {
		this.min_longitude = min_longitude;
	}
	public AreaInfo getAreaInfo() {
		return areaInfo;
	}
	public void setAreaInfo(AreaInfo areaInfo) {
		this.areaInfo = areaInfo;
	}
    
}
