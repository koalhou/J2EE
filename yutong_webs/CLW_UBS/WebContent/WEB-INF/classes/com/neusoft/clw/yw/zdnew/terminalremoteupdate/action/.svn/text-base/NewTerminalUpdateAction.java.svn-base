/*******************************************************************************
 * @(#)TerminalParamsAction.java 2012-6-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.zdnew.terminalremoteupdate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.terminalRomoteUpdate.ds.TerminalParamsInfo;
import com.neusoft.clw.yw.xj.terminalparam.action.SendCommandUtils;
import com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds.TerminalInfo;
import com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds.CarNumberInfo;
import com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds.EnterpriseTreeInfo;
import com.neusoft.clw.yw.zdnew.terminalremoteupdate.ds.UpdateUserInfo;
import com.neusoft.clw.yw.zdnew.terminalremoteupdate.service.NewTerminalUpdateService;
import com.opensymphony.xwork2.ActionContext;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
/**
 * 终端参数设置action
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-6-4 上午10:16:56
 */
public class NewTerminalUpdateAction extends PaginationAction {
    private transient Service service;
    private transient NewTerminalUpdateService newTerminalUpdateService;
    
    /** 页面提示信息 **/
    private String message = null;

    private List < EnterpriseTreeInfo > enterpriseInfo;

    private EnterpriseTreeInfo enterprise;
    
    private String name="";
   
    /** 车辆信息 **/
    private Map terminalMap = new HashMap();
    
    private String enterpriseId = "";
    
    private String host_hard_ver;
    private String host_firm_ver;
    private String connection_time;
    private String url_address;
    private String dial_peer_name;
    private String dial_peer_account;
    private String dial_password;
    private String ip_address;
    private String tcp_port;
    private String udp_port;
    List<UpdateUserInfo> userlist = new ArrayList();
    private String teminalList;
    private String vinList;
    private String simList;
    private String pID;
    
    private String vehicle_ln;
    private String xianshi_firm_ver;
    private String SHEPIN_firm_ver;
    /**
     * 空action
     * @return
     */
    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("terminalupdate.location"));

        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 终端升级初始化
     * @return
     */
    public String updateInit() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("terminalupdate.location"));
        HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        
        String rpNum = "";
        String pageIndex = "";

        pageIndex = request.getParameter("page");
        rpNum = request.getParameter("rp");
        
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

          try {

              Map < String, String > map = new HashMap < String, String >();
              map.put("sortname", sortName);
              map.put("sortorder", sortOrder);
              if(null == enterpriseId || enterpriseId.length() == 0) {
                  map.put("enterpriseId", "111");
              } else {
                  map.put("enterpriseId", enterpriseId);
              }
              
              if(null != vehicle_ln) {
                  map.put("vehicle_ln", vehicle_ln);
              } 
              if(null != host_firm_ver) {
                  map.put("host_firm_ver", host_firm_ver);
              }
              if(null != xianshi_firm_ver) {
                  map.put("xianshi_firm_ver", xianshi_firm_ver);
              }
              if(null != SHEPIN_firm_ver) {
                  map.put("SHEPIN_firm_ver", SHEPIN_firm_ver);
              }
              
              int totalCount = 0;
              totalCount = service.getCount("NewTerminalUpdate.getTerminalUpdateCount", map);

              List < TerminalInfo > terminalinfo = service.getObjectsByPage(
                      "NewTerminalUpdate.getTerminalUpdateInfos", map, (Integer
                              .parseInt(pageIndex) - 1)
                              * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

              this.terminalMap = getPagination(terminalinfo, totalCount, pageIndex,rpNum);
              
         
          } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
          } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
          } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_TERMINAL_ROMOTE_UPDATE);
            addOperationLog("终端远程升级");
          }
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    
    public String getUpdateInfo(){
		UserInfo currentUser = (UserInfo) ActionContext.getContext()
        .getSession().get(Constants.USER_SESSION_KEY);
		
		try {
			UpdateUserInfo uui = new UpdateUserInfo();
			uui.setUser_id(currentUser.getUserID());
			userlist = service.getObjects("NewTerminalUpdate.getUpdateUserInfo", uui);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
    
    /**
     * 添加终端升级
     * @return
     */
    public void insert() {
    	UserInfo user = (UserInfo) ActionContext.getContext()
        .getSession().get(Constants.USER_SESSION_KEY);
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.update"));
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");
        UpdateUserInfo uui = new UpdateUserInfo();
        try{
        	uui.setUser_id(user.getUserID());
        	uui.setHost_hard_ver(host_hard_ver); 
        	uui.setHost_firm_ver(host_firm_ver);
        	uui.setConnection_time(connection_time); 
        	uui.setUrl_address(url_address);
        	uui.setDial_peer_name(dial_peer_name); 
        	uui.setDial_peer_account(dial_peer_account); 
        	uui.setDial_password(dial_password); 
        	uui.setIp_address(ip_address); 
        	uui.setTcp_port(tcp_port); 
        	uui.setUdp_port(udp_port);
        	if(!"".equals(pID)&&!"".equals(vinList) && !"".equals(teminalList) && !"".equals(simList)){
 	        	String [] pls = pID.replace("'", "").split(",");
 	        	String [] vins = vinList.replace("'", "").split(",");
 	        	String [] tls = teminalList.replace("'", "").split(",");
 	        	String [] sims = simList.replace("'", "").split(",");
 	        	uui.setPIdArray(pls);
 	        	uui.setVinArray(vins);
 	        	uui.setTeminalArray(tls);
 	        	uui.setSimArray(sims);
 	        }
        	
        	newTerminalUpdateService.insertUpdateInfo(uui);
        	this.message = "terminal.romote.update";
        }catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            this.message = "terminal.romote.fail";
        } finally {
            setOperationType(Constants.SELECT,ModuleId.CLW_M_XJ_TERMINAL_ROMOTE_UPDATE);
            addOperationLog("终端远程升级");
        }
        if (null != message) {
            addActionMessage(getText(message));
        }
        
        try {
            response.getWriter().write(message);
        } catch (IOException ioException) {
            ;
        }
    }
    
    /**
     *  发送升级信息
     */
     private void updateTerminal(List<TerminalParamsInfo> infos){
    	 
    	SendCommandUtils util = new SendCommandUtils();
    	for (int i = 0; i < infos.size(); i++) {
    		String messageId = UUIDGenerator.getUUID32();
    		
    		util.updateTerminalNewVersion(infos.get(i),messageId);    
		} 	
    	this.message = "terminal.romote.update";
     }

    /**
     * 格式化企业信息
     */
    private void formatEnterpriseInfo() {
        try {
            List<CarNumberInfo> carnumbers = (List < CarNumberInfo >) service.getObjects(
                    "NewTerminalParams.getCarnumberByEnterprise", "");
            // 注册车辆信息map
            Map<String, String> enterpriseMap = new HashMap<String, String>();
            
            // 已分配车辆map
            Map<String, String> organizationMap = new HashMap<String, String>();
            
            int totalNumber = 0 ;
            
            for(CarNumberInfo tmp : carnumbers) {
                if("ENTERPRISE".equals(tmp.getFlag())) {
                    // 注册车辆
                    // 车辆总数
                    totalNumber = totalNumber + Integer.parseInt(tmp.getCarnum());
                    enterpriseMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
                } else {
                    // 已分配车辆
                    organizationMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
                }
            }
            
            for(EnterpriseTreeInfo singleInfo : enterpriseInfo) {
                if("111".equals(singleInfo.getId())) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),String.valueOf(totalNumber)));
                } else if(organizationMap.get(singleInfo.getId()) != null && organizationMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),organizationMap.get(singleInfo.getId())));
                } else if(enterpriseMap.get(singleInfo.getId()) != null && enterpriseMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", singleInfo.getName(),enterpriseMap.get(singleInfo.getId())));
                }
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    /**
     * 组织机构树
     */
    public String getOrganizationTree() {
        String enterpriseId = "111";
        EnterpriseTreeInfo enterprise = new EnterpriseTreeInfo();

        try {
            enterpriseInfo = (List < EnterpriseTreeInfo >) service.getObjects(
                    "NewTerminalParams.getOrganizationTreeData", enterpriseId);
            formatEnterpriseInfo();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;

    }
    
    /**
     * 查询组织机构树
     */
    public String searchOrganizationTreeData() {
    	if(null==enterprise){
    		enterprise = new EnterpriseTreeInfo();
    	}
    	try {
			enterprise.setName(java.net.URLDecoder.decode(name, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			log.error("decode error:" + e1.getMessage());
		}
        try {
            enterpriseInfo = (List < EnterpriseTreeInfo >) service.getObjects(
                    "NewTerminalParams.searchOrganizationTreeData", enterprise);
            formatEnterpriseInfo();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    

    /**
     * 组装JSON数据
     * @param vehicleRegisterList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getPagination(List list, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        for (int i = 0; i < list.size(); i++) {
            TerminalInfo terminalRealTimeInfo = (TerminalInfo) list.get(i);

            if(null == terminalRealTimeInfo.getVehicleLn() || terminalRealTimeInfo.getVehicleLn().length() == 0) {
                terminalRealTimeInfo.setVehicleLn("暂无车牌");
            }
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", terminalRealTimeInfo.getTerminalId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), 
                    terminalRealTimeInfo.getVehicleLn(),
                    terminalRealTimeInfo.getVehicleVin(),
                    terminalRealTimeInfo.getTerminalCode(),
                    terminalRealTimeInfo.getSimCardNumber(),
                    terminalRealTimeInfo.getCellPhone(),
                    terminalRealTimeInfo.getEnterpriseCode(),
                    terminalRealTimeInfo.getEnterpriseName(),
                    terminalRealTimeInfo.getHost_hard_ver(),
                    terminalRealTimeInfo.getHost_firm_ver(),
                    terminalRealTimeInfo.getXianshi_hard_ver(),
                    terminalRealTimeInfo.getXianshi_firm_ver(),
                    terminalRealTimeInfo.getDVR_hard_ver(),
                    terminalRealTimeInfo.getDVR_firm_ver(),
                    terminalRealTimeInfo.getSHEPIN_hard_ver(),
                    terminalRealTimeInfo.getSHEPIN_firm_ver(),
                    terminalRealTimeInfo.getSIM_SCCID(),
                    null==terminalRealTimeInfo.getUPDATE_VERSION()?"null":terminalRealTimeInfo.getUPDATE_VERSION(),
                    terminalRealTimeInfo.getOPERATE_TIME(),
                    null==terminalRealTimeInfo.getDEAL_STATE()?"null":terminalRealTimeInfo.getDEAL_STATE()
                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
	public String getVehicle_ln() {
		return vehicle_ln;
	}

	public void setVehicle_ln(String vehicle_ln) {
		this.vehicle_ln = vehicle_ln;
	}

	public String getXianshi_firm_ver() {
		return xianshi_firm_ver;
	}

	public void setXianshi_firm_ver(String xianshi_firm_ver) {
		this.xianshi_firm_ver = xianshi_firm_ver;
	}

	public String getSHEPIN_firm_ver() {
		return SHEPIN_firm_ver;
	}

	public void setSHEPIN_firm_ver(String shepin_firm_ver) {
		SHEPIN_firm_ver = shepin_firm_ver;
	}

	public NewTerminalUpdateService getNewTerminalUpdateService() {
		return newTerminalUpdateService;
	}

	public void setNewTerminalUpdateService(
			NewTerminalUpdateService newTerminalUpdateService) {
		this.newTerminalUpdateService = newTerminalUpdateService;
	}

	public String getTeminalList() {
		return teminalList;
	}

	public void setTeminalList(String teminalList) {
		this.teminalList = teminalList;
	}

	public String getVinList() {
		return vinList;
	}

	public void setVinList(String vinList) {
		this.vinList = vinList;
	}

	public String getSimList() {
		return simList;
	}

	public void setSimList(String simList) {
		this.simList = simList;
	}

	public String getPID() {
		return pID;
	}

	public void setPID(String pid) {
		pID = pid;
	}

	public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
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

    public List < EnterpriseTreeInfo > getEnterpriseInfo() {
        return enterpriseInfo;
    }

    public void setEnterpriseInfo(List < EnterpriseTreeInfo > enterpriseInfo) {
        this.enterpriseInfo = enterpriseInfo;
    }
	public EnterpriseTreeInfo getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(EnterpriseTreeInfo enterprise) {
		this.enterprise = enterprise;
	}

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

	public Map getTerminalMap() {
		return terminalMap;
	}

	public void setTerminalMap(Map terminalMap) {
		this.terminalMap = terminalMap;
	}

	public String getHost_hard_ver() {
		return host_hard_ver;
	}

	public void setHost_hard_ver(String host_hard_ver) {
		this.host_hard_ver = host_hard_ver;
	}

	public String getHost_firm_ver() {
		return host_firm_ver;
	}

	public void setHost_firm_ver(String host_firm_ver) {
		this.host_firm_ver = host_firm_ver;
	}

	public String getConnection_time() {
		return connection_time;
	}

	public void setConnection_time(String connection_time) {
		this.connection_time = connection_time;
	}

	public String getUrl_address() {
		return url_address;
	}

	public void setUrl_address(String url_address) {
		this.url_address = url_address;
	}

	public String getDial_peer_name() {
		return dial_peer_name;
	}

	public void setDial_peer_name(String dial_peer_name) {
		this.dial_peer_name = dial_peer_name;
	}

	public String getDial_peer_account() {
		return dial_peer_account;
	}

	public void setDial_peer_account(String dial_peer_account) {
		this.dial_peer_account = dial_peer_account;
	}

	public String getDial_password() {
		return dial_password;
	}

	public void setDial_password(String dial_password) {
		this.dial_password = dial_password;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getTcp_port() {
		return tcp_port;
	}

	public void setTcp_port(String tcp_port) {
		this.tcp_port = tcp_port;
	}

	public String getUdp_port() {
		return udp_port;
	}

	public void setUdp_port(String udp_port) {
		this.udp_port = udp_port;
	}

	public List<UpdateUserInfo> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<UpdateUserInfo> userlist) {
		this.userlist = userlist;
	}

}
