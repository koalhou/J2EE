/*******************************************************************************
 * @(#)TerminalParamsAction.java 2012-6-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.tree.action;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.StringUtil;
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
    
    private String ftlyFlag = "";
   
    /**
     * 格式化企业信息
     */
    private void formatEnterpriseInfo() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("enterpriseId", currentUser.getOrganizationID());
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCarnumberByEnterprise", map);
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
     * 公车私用组织机构树
     */
    public String getCheckTreeNodes() {
    	MDC.put("modulename", "[cheliangMonitor-tree]");
    	log.info("组织树初始化");
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getCheckTreeNodes", currentUser.getOrganizationID());
            formatCheckEnterpriseInfo();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;

    }
    
    /**
     * 格式化企业信息
     */
    private void formatCheckEnterpriseInfo() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("enterpriseId", currentUser.getOrganizationID());
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCheckCarnumberByEnterprise", map);
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
        	treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.searchTreeNodesByCode", map);
        	if(null == treeNodesList || treeNodesList.size() == 0) {
	        	//treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.searchTreeNodesByName", map);
	        	
	        	//if(null == treeNodesList || treeNodesList.size() == 0) {
	        		// 按车牌查询无数据,查询组织
	        		treeNodesList = (List<TreeNodeInfo>) service.getObjects(
	                        "TreeData.searchTreeNodesByDivisionName", map);
	        	//}
        	}
            formatEnterpriseInfo();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 公车私用查询组织机构树
     */
    public String searchCheckTreeNodes() {
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
        	treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.searchCheckTreeNodesByCode", map);
        	if(null == treeNodesList || treeNodesList.size() == 0) {
	        	//treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.searchCheckTreeNodesByName", map);
	        	
	        	//if(null == treeNodesList || treeNodesList.size() == 0) {
	        		// 按车牌查询无数据,查询组织
	        		treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.searchCheckTreeNodesByDivisionName", map);
	        	//}
        	}
        	formatCheckEnterpriseInfo();
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
    private void formatEnterpriseInfoWithoutOnline1(String flag) {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	Map<String, String> map = new HashMap<String, String>();
        	if(null != ftlyFlag && ftlyFlag.length() > 0){
        		map.put("ftlyFlag", ftlyFlag);
        	}
        	map.put("enterpriseId", currentUser.getOrganizationID());
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCarnumberByEnterprise4Oil", map);
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(TreeNodesAttri tmp : carnumbers) {
                // 已分配车辆
            	carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            List<TreeNodeInfo> set = new ArrayList<TreeNodeInfo>();
            for(int i = 0; i < treeNodesList.size(); i++){
            	TreeNodeInfo singleInfo = treeNodesList.get(i);
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
//                	String sss = carNumberMap.get(singleInfo.getId());
//                	if(carNumberMap.get(singleInfo.getId()).equals("0/0") && "1".equals(flag)){
//                		set.add(singleInfo);
//                		continue;
//                	}
                    singleInfo.setName(String.format("%s(%s)", 
                    		                         singleInfo.getName(),
                    		                         carNumberMap.get(singleInfo.getId())));
                } 
            }
            treeNodesList.removeAll(set);
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    
    /**
     * 格式化企业信息
     */
    private void formatEnterpriseInfoWithoutOnline() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	Map<String, String> map = new HashMap<String, String>();
        	if(null != ftlyFlag && ftlyFlag.length() > 0){
        		map.put("ftlyFlag", ftlyFlag);
        	}
        	map.put("enterpriseId", currentUser.getOrganizationID());
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCarnumberByEnterprise", map);
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
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
        	
            List<TreeNodesAttri> routenumbers = (List < TreeNodesAttri >) service.getObjects("TreeData.getRouteNumberByEnterprise", currentUser.getOrganizationID());
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
     * 格式化无复选框组织线路树
     */
    private void formatOrganizationRouteInfo_exeline(String i) {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("value", currentUser.getOrganizationID());
        	map.put("route_class", i);
        	List<TreeNodesAttri> routenumbers;
        	//针对发车与客流统计   -1代表查询全部，其中要过滤厂内通勤
//        	if("-1".equals(i)){
//        		routenumbers = (List < TreeNodesAttri >) service.getObjects("TreeData.getRouteNumberByEnterprise_exeline1", map);
//        	}
        	//else{
        		routenumbers = (List < TreeNodesAttri >) service.getObjects("TreeData.getRouteNumberByEnterprise_exeline", map);
        	//}
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
     * （车辆调度中对车辆添加线路）组织中的线路数，过滤掉该车辆已经添加的线路
     */
    private void formatOrganizationRouteInfo_exeline1(String i,String date,String vin) {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("value", currentUser.getOrganizationID());
        	map.put("route_class", i);
        	map.put("exe_date", date);
        	map.put("vehicle_vin", vin);
        	List<TreeNodesAttri> routenumbers;
        	
        	routenumbers = (List < TreeNodesAttri >) service.getObjects("TreeData.getRouteNumberByEnterprise_exeline2", map);
        	
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
     * 无复选框组织线路树
     * @return
     */
    public String tqc_car_getRouteData() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
    	Map<String,String> map = new HashMap<String,String>();
    	String routeinfo = request.getParameter("routeinfo");
    	String routeclass = request.getParameter("routeclass");
        try {
        	if(routeinfo.length()>0) {
	        	if(routeinfo.equals(""))
	        		return SUCCESS;
	        	String[] strd = routeinfo.split(",");
	        	map.put("value", currentUser.getOrganizationID());
	        	map.put("exe_date", strd[0]);
	        	map.put("vehicle_vin", strd[1]);
	        	map.put("routeclass", routeclass);
	        	treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.tqc_car_getRouteData", map);
	        	formatOrganizationRouteInfo_exeline1(routeclass,strd[0],strd[1]);
        	}
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    /**
     * 无复选框组织线路树
     * @return
     */
    public String tqc_car_getRouteDatanores() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
    	HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
		String route_class = request.getParameter("route_class");
		String route_name = request.getParameter("route_name");
		if(null != route_name || "".equals(route_name))
			try {
				route_name = SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(route_name, "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        try {
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("value", currentUser.getOrganizationID());
        	map.put("route_class", route_class);
        	map.put("name", route_name);
        	//针对发车与客流统计   -1代表查询全部，其中要过滤厂内通勤
        	if("-1".equals(route_class)){
        		treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.getOrganizationRouteDatanores1", map);
        	}
        	else{
        		treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.getOrganizationRouteDatanores", map);
        	}
        	//formatOrganizationRouteInfo();
        	formatOrganizationRouteInfo_exeline(route_class);
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    public String tqcgetOrganizationRouteData() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);

    	Map<String,String> map = new HashMap<String,String>();
        try {
        	map.put("value", currentUser.getOrganizationID());
        	map.put("route_class", ServletActionContext.getRequest().getParameter("route_class"));
        	treeNodesList = (List<TreeNodeInfo>) service.getObjects("TreeData.tqcgetOrganizationRouteData", map);
            formatOrganizationRouteInfo_exeline(ServletActionContext.getRequest().getParameter("route_class"));
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
     * 查询无复选框组织线路树
     */
    public String tqcsearchRouteTree() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);

    	Map<String,String> map = new HashMap<String,String>();
    	
    	if(null==treeNodeInfo){
    		treeNodeInfo = new TreeNodeInfo();
    	}
    	try {
    		map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
    		map.put("route_class", ServletActionContext.getRequest().getParameter("route_class"));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects("TreeData.tqcsearchRouteTree", map);
        	if(treeNodesList==null || treeNodesList.size() == 0) {
        		treeNodesList = (List < TreeNodeInfo >) service.getObjects("TreeData.tqcsearchRouteTree_ent", map);
        	}
        	formatOrganizationRouteInfo_exeline(ServletActionContext.getRequest().getParameter("route_class"));
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
        	formatEnterpriseInfoWithoutOnlineRepare();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 格式化企业信息__维保管理
     */
    private void formatEnterpriseInfoWithoutOnlineRepare() {
        try {
        	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
        	Map<String, String> map = new HashMap<String, String>();
        	if(null != ftlyFlag && ftlyFlag.length() > 0){
        		map.put("ftlyFlag", ftlyFlag);
        	}
        	map.put("enterpriseId", currentUser.getOrganizationID());
            List<TreeNodesAttri> carnumbers = (List < TreeNodesAttri >) service.getObjects(
                    "TreeData.getCarnumberByEnterpriseRepare", map);
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
     * 无在线状态组织树__维保管理
     * @return
     */
    public String getRepareTreeNodesWithoutOnline() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getRepareTreeNodes", currentUser.getOrganizationID());
            formatEnterpriseInfoWithoutOnlineRepare();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 查询无在线状态组织树————维保管理
     * @return
     */
    public String searchRepareTreeNodesWithoutOnline() {
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
                    "TreeData.searchRepareTreeNodesByName", map);
        	
        	if(null == treeNodesList || treeNodesList.size() == 0) {
        		// 按车牌查询无数据,查询组织
        		treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                        "TreeData.searchRepareTreeNodesByDivisionName", map);
        	}
        	formatEnterpriseInfoWithoutOnline();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    
    
    /**
     * 含邮箱状态的组织树
     * @return
     */
    public String getTreeNodesWithOilState() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    	
    	HttpServletRequest request = (HttpServletRequest) ActionContext
		.getContext()
		.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	
    	Map<String,String> map = new HashMap<String,String>();
    	
    	String ftlyFlag = request.getParameter("ftlyFlag");
    	this.ftlyFlag = ftlyFlag;
    	
    	Calendar calendar = Calendar.getInstance();
		Date currentTime = calendar.getTime();
		
		SimpleDateFormat inputFormat = new SimpleDateFormat(
        "yyyyMMdd");
		String currTime = inputFormat.format(currentTime);
		System.out.println("FTLY_INFO_"+currTime);
		String partionTime = "FTLY_INFO_"+currTime;
    	
        try {
        	if(null != ftlyFlag && ftlyFlag.length() > 0){
        		map.put("ftlyFlag", ftlyFlag);
        	}
        	map.put("enterpriseId", currentUser.getOrganizationID());
        	map.put("entId", currentUser.getEntiID());
        	map.put("partionTime", partionTime);
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.getTreeNodes4Oil", map);
            formatEnterpriseInfoWithoutOnline1("0");
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    /**
     * 查询含邮箱状态的组织树
     * @return
     */
    public String searchTreeNodesWithOilState() {
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

    	HttpServletRequest request = (HttpServletRequest) ActionContext
		.getContext()
		.get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
    	
    	Map<String,String> map = new HashMap<String,String>();
    	
    	String ftlyFlag = request.getParameter("ftlyFlag");
    	this.ftlyFlag = ftlyFlag;
    	
    	Calendar calendar = Calendar.getInstance();
		Date currentTime = calendar.getTime();
		
		SimpleDateFormat inputFormat = new SimpleDateFormat(
        "yyyyMMdd");
		String currTime = inputFormat.format(currentTime);
		System.out.println("FTLY_INFO_"+currTime);
		String partionTime = "FTLY_INFO_"+currTime;
    	
    	if(null==treeNodeInfo){
    		treeNodeInfo = new TreeNodeInfo();
    	}
    	try {
    		if(null != ftlyFlag && ftlyFlag.length() > 0){
        		map.put("ftlyFlag", ftlyFlag);
        	}
    		map.put("enterpriseId", currentUser.getOrganizationID());
    		map.put("entId", currentUser.getEntiID());
    		map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
    		map.put("partionTime", partionTime);
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
        	treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                    "TreeData.searchTreeNodesByName4Oil", map);
        	
        	if(null == treeNodesList || treeNodesList.size() == 0) {
        		// 按车牌查询无数据,查询组织
        		treeNodesList = (List < TreeNodeInfo >) service.getObjects(
                        "TreeData.searchTreeNodesByDivisionName4Oil", map);
        	}
        	formatEnterpriseInfoWithoutOnline1("1");
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    
    
	public String findEnterSub(){
    	UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    	
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("enterpriseId", currentUser.getEntiID());
    	map.put("shortName", StringUtil.toStringList("十八里河厂区,新能源厂区"));
    	try {
			treeNodesList = (List < TreeNodeInfo >) service.getObjects(
			        "TreeData.getEnterSub", map);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
    	
    	return "success";
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
