package com.neusoft.clw.yw.zdnew.ftlyparam.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.monitor.ds.SendCommandInfo;
import com.neusoft.clw.yw.zdnew.ftlyparam.ds.FtlyParamInfo;
import com.neusoft.clw.yw.zdnew.terminalparams.ds.EnterpriseTreeInfo;
import com.neusoft.clw.yw.zdnew.terminalparams.ds.XCTerminalParamsInfo;
import com.opensymphony.xwork2.ActionContext;

public class FtlyParamAction extends PaginationAction{
	private transient Service service;
    /** 页面提示信息 **/
    private String message = null;
    private List < EnterpriseTreeInfo > enterpriseInfo;
    private EnterpriseTreeInfo enterprise;
    private String name="";
    /** 车辆信息 **/
    private Map vehicleMap = new HashMap();
    /** 查询企业ID **/
    private String enterpriseId = "";
    /** 查询企业名称 **/
    private String enterpriseName = "";
    /** 查询车牌 **/
    private String vehicleLnQuery = "";
    /** 参数设置信息 **/
    private FtlyParamInfo ftlyParamInfo = new FtlyParamInfo();
    private String carIdList = "";
    private String currentpage = "";
    private String currentpageSize = "";
    /** 当前排序字段 **/
    private String currentsortname = "";
    /** 当前排序方式 **/
    private String currentsortorder = "";
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
	public List<EnterpriseTreeInfo> getEnterpriseInfo() {
		return enterpriseInfo;
	}
	public void setEnterpriseInfo(List<EnterpriseTreeInfo> enterpriseInfo) {
		this.enterpriseInfo = enterpriseInfo;
	}
	public EnterpriseTreeInfo getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(EnterpriseTreeInfo enterprise) {
		this.enterprise = enterprise;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map getVehicleMap() {
		return vehicleMap;
	}
	public void setVehicleMap(Map vehicleMap) {
		this.vehicleMap = vehicleMap;
	}
	public String getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	public String getVehicleLnQuery() {
		return vehicleLnQuery;
	}
	public void setVehicleLnQuery(String vehicleLnQuery) {
		this.vehicleLnQuery = vehicleLnQuery;
	}
	public FtlyParamInfo getFtlyParamInfo() {
		return ftlyParamInfo;
	}
	public void setFtlyParamInfo(FtlyParamInfo ftlyParamInfo) {
		this.ftlyParamInfo = ftlyParamInfo;
	}
	public String getCarIdList() {
		return carIdList;
	}
	public void setCarIdList(String carIdList) {
		this.carIdList = carIdList;
	}
	public String getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}
	public String getCurrentpageSize() {
		return currentpageSize;
	}
	public void setCurrentpageSize(String currentpageSize) {
		this.currentpageSize = currentpageSize;
	}
	public String getCurrentsortname() {
		return currentsortname;
	}
	public void setCurrentsortname(String currentsortname) {
		this.currentsortname = currentsortname;
	}
	public String getCurrentsortorder() {
		return currentsortorder;
	}
	public void setCurrentsortorder(String currentsortorder) {
		this.currentsortorder = currentsortorder;
	}
    
	 public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("ftlyparams.location"));

        if( null == ftlyParamInfo) {
        	ftlyParamInfo = new FtlyParamInfo();
        }
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
	 
	 	/**
	     * 设置当前参数信息
	     * @return
	     */
	    public String setFtlyParams() {
	        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);
	        int failCnt = 0;
	        try {
	            List<XCTerminalParamsInfo> vehicleInfos = service.getObjects("NewTerminalParams.getVehicleInfos", formateVehicleList()); 
	            // 设置操作人
	            ftlyParamInfo.setCreater(currentUser.getUserID());
	            for(XCTerminalParamsInfo tmp : vehicleInfos) {
	                if(null != tmp.getVehicleVin() && tmp.getVehicleVin().length() > 0) {
	                    // 设置VIN号
	                	ftlyParamInfo.setVin(tmp.getVehicleVin());
	                    // 设置SIM卡号
	                	ftlyParamInfo.setSimCardNumber(tmp.getSimCardNumber());
	                }
	                FtlySendCommondUtils sendCommandUtils = new FtlySendCommondUtils();
	                // 设置终端参数
	                SendCommandInfo sendCommandInfo = sendCommandUtils.getSendCommand(ftlyParamInfo,"1");
	                service.insert("TerminalMonitor.insertSendCommandInfo",sendCommandInfo);
	            }
	        } catch (Exception e) {
	        	failCnt ++ ;
	            log.error("Query terminal params error:" + e.getMessage());
	            addActionError(getText("params.set.error"));
	            return ERROR;
	        } finally {
	            setOperationType(Constants.SELECT,
	                    ModuleId.CLW_M_XJ_TERMINAL_SETPARAM_MID);
	            addOperationLog("设置油量监控参数信息");
	        }
	        if(failCnt > 0) {
                addActionError(String.valueOf(failCnt) + "台车下发失败，请重新发送！");
            } else {
                addActionMessage("油量监控参数设置命令已下发！");
            }
	        return SUCCESS;
	    }
	    
	    /**
	     * 单车查询终端参数
	     * @return
	     */
	    public String queryFtlyParams() {
	        UserInfo currentUser = (UserInfo) ActionContext.getContext()
	                .getSession().get(Constants.USER_SESSION_KEY);
	        try {
	            // 下发终端参数查询命令
	        	FtlyParamInfo queryParamsBean = new FtlyParamInfo();
	            List<XCTerminalParamsInfo> vehicleInfos = service.getObjects("NewTerminalParams.getVehicleInfos", formateVehicleList()); 
	            // 设置操作人
	            queryParamsBean.setCreater(currentUser.getUserID());
	            for(XCTerminalParamsInfo tmp : vehicleInfos) {
	                if(null != tmp.getVehicleVin() && tmp.getVehicleVin().length() > 0) {
	                    // 设置VIN号
	                    queryParamsBean.setVin(tmp.getVehicleVin());
	                    // 设置SIM卡号
	                    queryParamsBean.setSimCardNumber(tmp.getSimCardNumber());
	                }
	            }
	            // 删除旧数据
	            service.delete("ftlyParam.deleteParamsByVehicleId", carIdList);
	            FtlySendCommondUtils sendCommandUtils = new FtlySendCommondUtils();
	            SendCommandInfo sendCommandInfo = sendCommandUtils.getSendCommand(queryParamsBean,"2");
                service.insert("TerminalMonitor.insertSendCommandInfo",sendCommandInfo);
	            addActionMessage("参数查询命令已下发！请点击刷新按钮以获得最新数据。");
	            ftlyParamInfo = new FtlyParamInfo();
	        } catch (Exception e) {
	            log.error("Query terminal params error:" + e.getMessage());
	            addActionError(getText("params.query.error"));
	            return ERROR;
	        } finally {
	            setOperationType(Constants.SELECT,
	                    ModuleId.CLW_M_XJ_TERMINAL_PARAMQUERY_MID);
	            addOperationLog("查询最新终端参数信息");
	        }
	        return SUCCESS;
	    }
	    
	    /**
	     * 单车刷新终端参数
	     * @return
	     */
	    public String refreshFtlyParams() {
	        try {
	            // 查询当前终端参数信息
	        	ftlyParamInfo = (FtlyParamInfo) service.getObject("ftlyParam.getParamsByVehicleId", carIdList);
	            if(null == ftlyParamInfo) {
	                addActionMessage("尚未获取到该油量监控参数信息！");
	            } else {
	                addActionMessage("油量监控参数信息已刷新！");
	            }
	        } catch (Exception e) {
	            log.error("Query terminal params error:" + e.getMessage());
	            addActionError(getText("params.query.error"));
	            return ERROR;
	        } finally {
	            setOperationType(Constants.SELECT,
	                    ModuleId.CLW_M_XJ_TERMINAL_PARAMQUERY_MID);
	            addOperationLog("查询最新终端参数信息");
	        }
	        return SUCCESS;
	    }
	    
	    /**
	     * 格式化车辆查询条件
	     * @return
	     */
	    private String formateVehicleList() {
	        String ret = "";
	        if (null != carIdList && carIdList.length() > 0) {
	            String[] carList = carIdList.split(",");
	            for (int i = 0; i < carList.length; i++) {
	                if (null != carList[i] && carList[i].length() > 0) {
	                    if (ret == "") {
	                        ret = "'" + carList[i] + "'";
	                    } else {
	                        ret = ret + "," + "'" + carList[i] + "'";
	                    }
	                }
	            }
	        }
	        return ret;
	    }
    
}
