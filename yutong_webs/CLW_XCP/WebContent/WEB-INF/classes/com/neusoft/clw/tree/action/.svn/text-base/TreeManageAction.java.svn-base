/*******************************************************************************
 * @(#)TerminalParamsAction.java 2012-6-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.tree.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.MDC;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.tree.domain.TreeNodeInfo;
import com.neusoft.clw.tree.domain.TreeNodesAttri;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端参数设置action
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-6-4 上午10:16:56
 */
public class TreeManageAction extends PaginationAction {
    private transient Service service;

    private List < TreeNodeInfo > treeNodesList;

    private TreeNodeInfo treeNodeInfo;
    
    private String name="";
   
    /**
     * 格式化企业信息
     */
    private void formatEnterpriseInfo() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCarnumberByEnterprise", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(TreeNodesAttri tmp : carnumbers) {
                // 已分配车辆
            	carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(TreeNodeInfo singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),carNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    /**
     * 组织机构树
     */
    public String getTreeNodes() {
    	MDC.put("modulename", "[cheliangMonitor-tree]");
    	log.info("组织树初始化");
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getTreeNodes", currentUser.getOrganizationID());
            formatEnterpriseInfo();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;

    }
    
    /**
     * 查询组织机构树
     */
    public String searchTreeNodes() {
    	MDC.put("modulename", "[cheliangMonitor-tree]");
    	log.info("组织树查询和刷新");
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

    	Map<String,String> map = new HashMap<String,String>();
    	
    	if(null==treeNodeInfo){
    		treeNodeInfo = new TreeNodeInfo();
    	}
    	try {
    		map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.searchTreeNodesByName", map);
        	
        	if(null == treeNodesList || treeNodesList.size() == 0) {
        		// 按车牌查询无数据,查询组织
        		treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                        "TreeData.searchTreeNodesByDivisionName", map);
        	}
            formatEnterpriseInfo();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 获取组织树
     * @return
     */
    public String getOrganizationTreeData() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getOrganizationTreeData", currentUser.getOrganizationID());
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;

    }
    
    /**
     * 线路数字
     */
    private void formatRouteEnterpriseInfo() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getRouteCarnumberByEnterprise", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(TreeNodesAttri tmp : carnumbers) {
                // 已分配车辆
            	carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(TreeNodeInfo singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && 
                   carNumberMap.get(singleInfo.getId()).length() > 0 && 
                   !currentUser.getOrganizationID().equals(singleInfo.getId())) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),carNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    
    /**
     * 获取线路树
     * @return
     */
    public String getRouteTreeNodes() {
    	MDC.put("modulename", "[cheliangMonitor-tree]");
    	log.info("线路树初始化");
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getRouteTreeNodes", currentUser.getOrganizationID());
        	formatRouteEnterpriseInfo();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 查询线路树
     * @return
     */
    public String searchRouteTreeNodes() {
    	MDC.put("modulename", "[cheliangMonitor]");
    	log.info("线路树查询和刷新");
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

    	Map<String,String> map = new HashMap<String,String>();
    	
    	if(null==treeNodeInfo){
    		treeNodeInfo = new TreeNodeInfo();
    	}
    	try {
    		map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.searchRouteTreeNodes", map);
        	
        	if(treeNodesList.size() == 1) {
        		// 按车牌查询无数据,查询线路
        		treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                        "TreeData.searchTreeNodesByRouteName", map);
        	}
        	if(treeNodesList.size() == 1) {
        		return null;
        	} else {
        		formatRouteEnterpriseInfo();
        	}
        	
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }
        
    	return SUCCESS;
    }
    
    /**
     * 格式化企业信息
     */
    private void formatEnterpriseInfoWithoutOnline() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCarnumberByEnterprise", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(TreeNodesAttri tmp : carnumbers) {
                // 已分配车辆
            	carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(TreeNodeInfo singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", 
                    		                         singleInfo.getName(),
                    		                         carNumberMap.get(singleInfo.getId()).split("/")[1]));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    /**
     * 格式化无复选框组织线路树
     */
    private void formatOrganizationRouteInfo() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	
            List<TreeNodesAttri> routenumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getRouteNumberByEnterprise", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> routeNumberMap = new HashMap<String, String>();
            
            for(TreeNodesAttri tmp : routenumbers) {
                // 已分配车辆
            	routeNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(TreeNodeInfo singleInfo : treeNodesList) {
                if(routeNumberMap.get(singleInfo.getId()) != null && routeNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),routeNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    /**
     * 无复选框组织线路树
     * @return
     */
    public String getOrganizationRouteData() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getOrganizationRouteData", currentUser.getOrganizationID());
            formatOrganizationRouteInfo();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 查询无复选框组织线路树
     */
    public String searchRouteTreeByRouteName() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

    	Map<String,String> map = new HashMap<String,String>();
    	
    	if(null==treeNodeInfo){
    		treeNodeInfo = new TreeNodeInfo();
    	}
    	try {
    		map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.searchRouteTreeByRouteName", map);
        	
        	formatOrganizationRouteInfo();
        } catch (BusinessException e) {
            log.error("Search route tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 无在线状态组织树
     * @return
     */
    public String getTreeNodesWithoutOnline() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getTreeNodes", currentUser.getOrganizationID());
            formatEnterpriseInfoWithoutOnline();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 查询无在线状态组织树
     * @return
     */
    public String searchTreeNodesWithoutOnline() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

    	Map<String,String> map = new HashMap<String,String>();
    	
    	if(null==treeNodeInfo){
    		treeNodeInfo = new TreeNodeInfo();
    	}
    	try {
    		map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.searchTreeNodesByName", map);
        	
        	if(null == treeNodesList || treeNodesList.size() == 0) {
        		// 按车牌查询无数据,查询组织
        		treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                        "TreeData.searchTreeNodesByDivisionName", map);
        	}
        	formatEnterpriseInfoWithoutOnline();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    
    /**
     * 查询驾驶员组织树
     * @return
     */
    public String searchTreeNodesWithDriver() {
    	try {
	    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
	    	Map<String,String> map = new HashMap<String,String>();
	    	map.put("enterpriseId", currentUser.getOrganizationID());
			map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getTreeNodesWithDriver", map);
        } catch (Exception e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }
        return SUCCESS;
    }

    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

	public List<TreeNodeInfo> getTreeNodesList() {
		return treeNodesList;
	}

	public void setTreeNodesList(List<TreeNodeInfo> treeNodesList) {
		this.treeNodesList = treeNodesList;
	}

	public TreeNodeInfo getTreeNodeInfo() {
		return treeNodeInfo;
	}

	public void setTreeNodeInfo(TreeNodeInfo treeNodeInfo) {
		this.treeNodeInfo = treeNodeInfo;
	}
}
