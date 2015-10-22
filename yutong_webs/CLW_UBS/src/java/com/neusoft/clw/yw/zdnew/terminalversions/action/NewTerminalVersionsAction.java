/*******************************************************************************
 * @(#)TerminalParamsAction.java 2012-6-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.yw.zdnew.terminalversions.action;

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
import com.neusoft.clw.yw.zdnew.terminalversions.ds.TerminalVersionsInfo;
import com.opensymphony.xwork2.ActionContext;
/**
 * 终端版本信息action
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao </a>
 * @version $Revision 1.1 $ 2012-6-7 上午10:16:56
 */
public class NewTerminalVersionsAction extends PaginationAction {
    private transient Service service;

    /** 页面提示信息 **/
    private String message = null;
    
    private TerminalVersionsInfo queryObj;
    
    /** 版本信息 **/
    private Map versionMap = new HashMap();
    
    /**
     * 空action
     * @return
     */
    public String blankAction() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("terminalversions.location"));
        if (null != message) {
            addActionMessage(getText(message));
        }
        queryObj = new TerminalVersionsInfo();
        setOperationType(Constants.SELECT,
                ModuleId.CLW_M_ZDVERSIONS_MID);
        addOperationLog("查询终端版本信息");
        return SUCCESS;
    }
    
    /**
     * 组装JSON数据
     * @param versionList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getPagination(List versionList, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        for (int i = 0; i < versionList.size(); i++) {
            TerminalVersionsInfo versionInfo = (TerminalVersionsInfo) versionList.get(i);

//            if(null == versionInfo.getVehicle_ln() || versionInfo.getVehicle_ln().length() == 0) {
//                versionInfo.setVehicle_ln("暂无车牌");
//            }
            
            Map cellMap = new LinkedHashMap();

            cellMap.put("id", versionInfo.getTerminal_id());

            cellMap.put("cell", new Object[] {
                    versionInfo.getVehicle_ln(),versionInfo.getHardware_vehicle_ln(),
                    versionInfo.getVehicle_vin(),versionInfo.getHardware_vehicle_vin(),
                    versionInfo.getTerminal_id(),versionInfo.getHardware_terminal_id(),
                    versionInfo.getVeh_pai_color(),versionInfo.getHardware_veh_pai_color(),
                    versionInfo.getCellphone(),versionInfo.getSim_sccid(),
                    versionInfo.getHost_hard_ver(),versionInfo.getHost_firm_ver(),
                    versionInfo.getXianshi_hard_ver(),versionInfo.getXianshi_firm_ver(),
                    versionInfo.getDvr_hard_ver(),versionInfo.getDvr_firm_ver(),
                    versionInfo.getShepin_hard_ver(),versionInfo.getShepin_firm_ver(),
                    versionInfo.getTerminal_time()
                     });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 获取终端版本信息
     * @return
     */
    public String getVersionListByEnterpriseId() {
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
            if(null == queryObj) {
                queryObj = new TerminalVersionsInfo();
                queryObj.setEnterprise_id("111");
            }
            if(null == queryObj.getEnterprise_id() || "".equals(queryObj.getEnterprise_id())){
                queryObj.setEnterprise_id("111");
            }
            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);

            int totalCount = 0;
            totalCount = service.getCount("NewTerminalVersions.getTerminalVersionsCount", queryObj);

            List<TerminalVersionsInfo> versionList = service.getObjectsByPage(
                    "NewTerminalVersions.getTerminalVersionsById", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));


            // 显示提示信息
//            if (null != message) {
//                addActionMessage(getText(message));
//            }

            this.versionMap = getPagination(versionList, totalCount, pageIndex,rpNum);
            
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered terminal's version error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query registered terminal's version error:" + e.getMessage());
            return ERROR;
        } finally {
            addOperationLog("查询已注册终端版本信息");
        }
        
        return SUCCESS;
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

    public Map getVersionMap() {
        return versionMap;
    }

    public void setVersionMap(Map versionMap) {
        this.versionMap = versionMap;
    }

    public TerminalVersionsInfo getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(TerminalVersionsInfo queryObj) {
        this.queryObj = queryObj;
    }

}
