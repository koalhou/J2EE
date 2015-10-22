package com.neusoft.clw.runtimeset.action;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.runtimeset.domain.RuntimeSet;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.tree.domain.TreeNodeInfo;
import com.opensymphony.xwork2.ActionContext;

public class RuntimeSetAction extends PaginationAction{
	/** service共通类 */
    private transient Service service;
    
    private Map<String, Object> map = new HashMap<String, Object>();
    
    private String org_id;//组织机构ID
    
    private RuntimeSet runtime=new RuntimeSet();
    
    private List < TreeNodeInfo > treeNodesList;
    private TreeNodeInfo treeNodeInfo;
    
    private String message;
    
    
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public RuntimeSet getRuntime() {
		return runtime;
	}

	public void setRuntime(RuntimeSet runtime) {
		this.runtime = runtime;
	}

	public TreeNodeInfo getTreeNodeInfo() {
		return treeNodeInfo;
	}

	public void setTreeNodeInfo(TreeNodeInfo treeNodeInfo) {
		this.treeNodeInfo = treeNodeInfo;
	}

	public List<TreeNodeInfo> getTreeNodesList() {
		return treeNodesList;
	}

	public void setTreeNodesList(List<TreeNodeInfo> treeNodesList) {
		this.treeNodesList = treeNodesList;
	}

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String org_id) {
		this.org_id = org_id;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}
	
    /**
     * 从菜单进入页面
     */
	public String readyPage(){
		//新增或修改成功提示信息
		if(this.message != null){
			try {
				this.message=new String(this.message.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				this.log.error(e.getMessage());
			}
			this.addActionMessage(this.message);
		}
		return SUCCESS;
	}
	/**
	 * 车辆运行时间列表
	 */
	public String runtimeList(){
		//根据传过来的查询参数查询列表数据
		String browseTitle="车辆运行时间列表";
		UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
		
		HttpServletRequest request = getCurrentRequest();
		
		try {
			//分页
			String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            Map<String,String> searchMap=new HashMap<String,String>();
            if("".equals(org_id)){
            	org_id =  "'"+currentUser.getOrganizationID()+"'";
            }
            searchMap.put("org_id", org_id);
            searchMap.put("sortName", sortName);
            searchMap.put("sortOrder", sortOrder);
            int totalCount = 0;
            totalCount=this.service.getCount("RuntimeSetManage.getRuntimeSetCount", org_id);
			List<RuntimeSet> list=this.service.getObjectsByPage("RuntimeSetManage.getRuntimeSetList", searchMap, (Integer.parseInt(pageIndex) - 1)*Integer.parseInt(rpNum), Integer.parseInt(rpNum));
			//添加列表为空提示
			if (list.size() == 0) {
                addActionMessage(getText("nodata.list"));
            }else{
            	this.map = getPagination(list, totalCount, pageIndex, rpNum);// 转换map
            }
			
		} catch (BusinessException e) {
			this.log.error("车辆运行时间列表查询出错", e);
		}
		// 设置操作描述
		this.addOperationLog(browseTitle);
		// 设置操作类型
		this.setOperationType(Constants.SELECT);
		// 设置所属应用系统
		this.setApplyId(Constants.XC_P_CODE);
		// 设置所属模块
		this.setModuleId(MouldId.XCP_ALARM_QUERY_ID);
		return SUCCESS;
	}
	/**
     * 得到当前请求对象
     */
    private HttpServletRequest getCurrentRequest(){
    	return (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
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
     * 查询组织树
     * @return
     */
    public String searchTreeNodes(){
    	
    	log.info("组织树查询");
    	UserInfo currentUser = getCurrentUser();
    	HttpServletRequest request = getCurrentRequest();
    	String search_name=request.getParameter("sname");
    	Map<String,String> map = new HashMap<String,String>();
        try {
        	map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(search_name, "utf-8")));
    		
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "RuntimeSetManage.getOrgTree", map);
        	
        } catch (UnsupportedEncodingException e) {
        	log.error("serial search tree error:" + e.getMessage());
		}catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        } 
        
    	return SUCCESS;
    }
    
    
    /**
     * 查询组织树
     * @return
     */
    public String searchTreeNodesNoSelectes(){
    	
    	log.info("组织树查询");
    	UserInfo currentUser = getCurrentUser();
    	HttpServletRequest request = getCurrentRequest();
    	String search_name=request.getParameter("sname");
    	Map<String,String> map = new HashMap<String,String>();
        try {
        	map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(search_name, "utf-8")));
    		
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "RuntimeSetManage.getOrgTreeNoSelected", map);
        	
        } catch (UnsupportedEncodingException e) {
        	log.error("serial search tree error:" + e.getMessage());
		}catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        } 
        
    	return SUCCESS;
    }
    
    /**
     * 进入添加页面
     */
    public String addPage(){
    	return SUCCESS;
    }
    /**
     * 进入组织树页面
     */
    public String orgTreePage(){
    	return SUCCESS;
    }
    /**
     * 提交
     */
    public String addRuntime(){
    	try {
    		UserInfo currentUser = getCurrentUser();
    		this.runtime.setOperator(currentUser.getUserID());
    		//this.runtime.setStatus("1");
			this.service.insert("RuntimeSetManage.addRuntime", runtime);
		} catch (BusinessException e) {
			log.error("addRuntimeSet error",e);
			this.addActionError("新增失败!");
			return ERROR;
		}
		//this.addActionMessage("新增成功!");
		this.message="新增成功!";
    	return SUCCESS;
    }
    /**
     * 进入修改页面
     */
    public String updatePage(){
    	try {
    		HttpServletRequest request = getCurrentRequest();
    		String time_id=request.getParameter("id");
    		runtime=(RuntimeSet)this.service.getObject("RuntimeSetManage.getRuntimeById",time_id);
		} catch (BusinessException e) {
			log.error("addRuntimeSet error",e);
			return ERROR;
		}
    	return SUCCESS;
    }
    /**
     * 修改
     */
    public String updateRuntime(){
    	try {
    		UserInfo currentUser = getCurrentUser();
    		this.runtime.setOperator(currentUser.getUserID());
			this.service.update("RuntimeSetManage.updateRuntimeById", this.runtime);
		} catch (BusinessException e) {
			log.error("update runtimeSet error",e);
			this.addActionError("修改失败!");
			return ERROR;
		}
		//this.addActionMessage("修改成功!");
		this.message="修改成功!";
    	return SUCCESS;
    }
    /**
     * 修改状态
     */
    public String updateStatus(){
    	try {
    		UserInfo currentUser = getCurrentUser();
    		this.runtime.setOperator(currentUser.getUserID());
			this.service.update("RuntimeSetManage.updateRuntimeStatus", this.runtime);
			this.printWriter("success");
		} catch (BusinessException e) {
			log.error("update runtimeSet statues error",e);
			this.printWriter("error");
		}
    	return null;
    }
    /**
     * 根据组织ID查询一套时间运行设置信息
     */
    public void getRuntimeByOrgId(){
    	try {
    		HttpServletRequest request = getCurrentRequest();
    		String org_id=request.getParameter("id");
			int count=this.service.getCount("RuntimeSetManage.getRuntimeByOrgId",org_id);
			if(count>0){
				this.printWriter("success");
			}
		} catch (BusinessException e) {
			log.error("根据组织ID查询一套时间运行设置信息出错",e);
			this.printWriter("error");
		}
		this.printWriter("error");
    }
	/**
     * 转换Map
     * @param list
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map<String,Object> getPagination(List<RuntimeSet> list, int totalCount, String pageIndex, String rpNum) {
        List<Object> mapList = new ArrayList<Object>();
        Map<String,Object> mapData = new HashMap<String,Object>();
        for (int i = 0; i < list.size(); i++) {
        	RuntimeSet info=list.get(i);
            Map<String,Object> cellMap = new LinkedHashMap<String,Object>();
            cellMap.put("id", info.getTime_id());
            cellMap.put("cell", new Object[] {
            		info.getOrg_name(),
            		info.getStart_time()+","+info.getEnd_time(),
            		info.getEffect_often(),
            		info.getRemark(),
            		info.getStatus(),
            		info.getOper_name(),
            		info.getModify_time(),
            		info.getTime_id()
            });
            mapList.add(cellMap);
        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
}
