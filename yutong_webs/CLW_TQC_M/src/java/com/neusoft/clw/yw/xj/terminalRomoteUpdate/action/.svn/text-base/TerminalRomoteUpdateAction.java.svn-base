package com.neusoft.clw.yw.xj.terminalRomoteUpdate.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.terminalRomoteUpdate.ds.TerminalInfo;
import com.neusoft.clw.yw.xj.terminalRomoteUpdate.ds.TerminalParamsInfo;
import com.neusoft.clw.yw.xj.terminalparam.action.SendCommandUtils;
import com.opensymphony.xwork2.ActionContext;

public class TerminalRomoteUpdateAction extends PaginationAction{
    private transient Service service;
           
    /** 提示信息 **/
    private String message = null;
    
    private TerminalParamsInfo terminalParamsInfo;
    
    /** 终端实时状态信息 **/
    private List < TerminalInfo > terminalList = new ArrayList < TerminalInfo >();
    
    /** JSON 返回 注册终端信息MAP **/
    private Map map = new HashMap();
    
    private String vehicleLN;
    
    private String vehicleVin;
    
    private String enterprise_name;
    
    private String enterprise_code;
    
    private String teminalList;
    
    private String simList;
    
    private String lnList;
    
    private String  vinList;
    
    private String type;
             
	public String getEnterprise_name() {
		return enterprise_name;
	}

	public void setEnterprise_name(String enterprise_name) {
		this.enterprise_name = enterprise_name;
	}

	public String getEnterprise_code() {
		return enterprise_code;
	}

	public void setEnterprise_code(String enterprise_code) {
		this.enterprise_code = enterprise_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getVinList() {
		return vinList;
	}

	public void setVinList(String vinList) {
		this.vinList = vinList;
	}

	public String getLnList() {
		return lnList;
	}

	public void setLnList(String lnList) {
		this.lnList = lnList;
	}

	public String getSimList() {
		return simList;
	}

	public void setSimList(String simList) {
		this.simList = simList;
	}

	public String getTeminalList() {
		return teminalList;
	}

	public void setTeminalList(String teminalList) {
		this.teminalList = teminalList;
	}

	public TerminalParamsInfo getTerminalParamsInfo() {
		return terminalParamsInfo;
	}

	public void setTerminalParamsInfo(TerminalParamsInfo terminalParamsInfo) {
		this.terminalParamsInfo = terminalParamsInfo;
	}

	public String getVehicleLN() {
		return vehicleLN;
	}

	public void setVehicleLN(String vehicleLN) {
		this.vehicleLN = vehicleLN;
	}

	public String getVehicleVin() {
		return vehicleVin;
	}

	public void setVehicleVin(String vehicleVin) {
		this.vehicleVin = vehicleVin;
	}

	public List<TerminalInfo> getTerminalList() {
		return terminalList;
	}

	public void setTerminalList(List<TerminalInfo> terminalList) {
		this.terminalList = terminalList;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

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

	/**
     * 初始化
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.update"));
        //从待选页面跳过来 type ==1
        if("1".equals(type) && vinList!=null && !"".equals(vinList)){
          map.put("vehicleVin",vinList);   
          type ="2";  //有选过车辆时，赋值为2
          try {
			terminalList = (List < TerminalInfo >)service.getObjects("terminalRomoteUpdate.getTerminalUpdateInfos", map);
			for(int i=0;i<terminalList.size();i++){
				if("".equals(lnList)||lnList==null){
					 vinList =terminalList.get(i).getVehicleVin();
					 teminalList = terminalList.get(i).getTerminalId();
					 simList = terminalList.get(i).getSimCardNumber();
					 lnList = terminalList.get(i).getVehicleLn()==null?"暂无车牌":terminalList.get(i).getVehicleLn();
			     }else{
			    	 vinList = vinList+","+terminalList.get(i).getVehicleVin();
					 teminalList =  teminalList+","+terminalList.get(i).getTerminalId();
					 simList =  simList+","+terminalList.get(i).getSimCardNumber();
					 lnList =  lnList+","+(terminalList.get(i).getVehicleLn()==null?"暂无车牌":terminalList.get(i).getVehicleLn());
				 }				
			 }         
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
        }
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 添加终端升级
     * @return
     */
    public String insert() {
    	
    	UserInfo user = getCurrentUser(); 
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.monitor.update"));
        List<TerminalParamsInfo> infos = new ArrayList<TerminalParamsInfo>();
        if(!"".equals(vinList) && !"".equals(teminalList) && !"".equals(simList)){
        	vinList = vinList.replace("'", "");
        	String [] vins = vinList.split(",");
        	String [] tls = teminalList.split(",");
        	String [] sims = simList.split(",");
        	//拼终端升级对象List
        	for (int i = 0; i < vins.length; i++) {
        		TerminalParamsInfo info  = new TerminalParamsInfo();
        		info.setUserId(user.getUserID());
        		info.setTerminalId(tls[i]);
        		info.setVehicleVin(vins[i]);
        		info.setHardver(terminalParamsInfo.getHardver()); 
        		info.setFirmver(terminalParamsInfo.getFirmver());
        		info.setTimePer(terminalParamsInfo.getTimePer()); 
        		info.setUrl(terminalParamsInfo.getUrl());
        		info.setMainapn(terminalParamsInfo.getMainapn()); 
        		info.setMainuser(terminalParamsInfo.getMainuser()); 
        		info.setMainpass(terminalParamsInfo.getMainpass()); 
        		info.setIp(terminalParamsInfo.getIp()); 
        		info.setTcpport(terminalParamsInfo.getTcpport()); 
        		info.setUdpport(terminalParamsInfo.getUdpport());
        		info.setSimCardNumber(sims[i]);
        		infos.add(info);
			}         
        }
        try{
           //调用升级函数
           updateTerminal(infos);
        }catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,ModuleId.CLW_M_XJ_TERMINAL_ROMOTE_UPDATE);
            addOperationLog("终端远程升级");
        }
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 页面初始化/查询
     * @return
     */
    public String browseTerminals() {
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
            map.put("vehicleLN",vehicleLN);           
            map.put("vehicleVin",vehicleVin);
            map.put("enterprise_name",enterprise_name);           
            map.put("enterprise_code",enterprise_code);
            
            int totalCount = 0;
            totalCount = service.getCount("terminalRomoteUpdate.getCount", map);
            terminalList = (List < TerminalInfo >) service
                    .getObjectsByPage(
                            "terminalRomoteUpdate.getTerminalRealTimeInfos", map,
                            (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (terminalList != null && terminalList.size() == 0) {
                // 无终端信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            this.map = getPagination(terminalList, totalCount, pageIndex, rpNum);
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

        return SUCCESS;
    }
    
    /**
     * 页面初始化/查询2
     * @return
     */
    public String browseTerminals2() {
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
            map.put("vehicleVin",vehicleVin);
            
            int totalCount = 0;
            totalCount = service.getCount("terminalRomoteUpdate.getTerminalUpdateCount", map);
            terminalList = (List < TerminalInfo >) service
                    .getObjectsByPage(
                            "terminalRomoteUpdate.getTerminalUpdateInfos", map,
                            (Integer.parseInt(pageIndex) - 1) * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (terminalList != null && terminalList.size() == 0) {
                // 无终端信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            this.map = getPagination2(terminalList, totalCount, pageIndex, rpNum);
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
                    terminalRealTimeInfo.getEnterpriseName()
                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    /**
     * 组装JSON数据
     * @param vehicleRegisterList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getPagination2(List list, int totalCount, String pageIndex,String rpNum) {
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
                    terminalRealTimeInfo.getSIM_SCCID()
                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
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
     *  发送升级信息
     */
     private void updateTerminal(List<TerminalParamsInfo> infos){
    	 
    	SendCommandUtils util = new SendCommandUtils();
    	for (int i = 0; i < infos.size(); i++) {
    		util.updateTerminalVersion(infos.get(i));    
		} 	
    	this.message = "terminal.romote.update";
     }
}
