package com.neusoft.clw.yw.xj.operatingreport.action;

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
import com.neusoft.clw.yw.cl.vehicleRegister.ds.VehicleRegisterInfo;
import com.neusoft.clw.yw.xj.operatingreport.ds.TotalReportInfo;
import com.neusoft.clw.yw.xj.operatingreport.ds.UnnormalVehicleInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="mailto:jin.p@neusoft.com">Jinp </a>
 * @version $Revision 1.1 $ 2011-9-6 上午08:41:58
 */
public class OperatingReportAction extends PaginationAction {
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 报表总计值 **/
    private TotalReportInfo totalReportInfo = new TotalReportInfo();
    
    /** 不正常车辆列表 **/
    private List<UnnormalVehicleInfo> unnormalList = new ArrayList<UnnormalVehicleInfo>();
    
    /** JSON 返回 运营报表统计MAP **/
    private Map operatingMap = new HashMap();
    
    /** JSON 返回 不正常车辆MAP **/
    private Map unnormalCarsMap = new HashMap();
    
    /**
     * 初始化
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("operationsInfo.location"));
        if (null != message) {
            addActionMessage(getText(message));
        }
        return SUCCESS;
    }
    
    /**
     * 组装统计数据
     * @param totalReportInfo
     * @return
     */
    public Map createOperatingMap(TotalReportInfo totalReportInfo) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();

        Map cellMap = new LinkedHashMap();

        cellMap.put("id", "operatingId");

        cellMap.put("cell", new Object[] {totalReportInfo.getEnterpriseCnt(),
                totalReportInfo.getRegisteredVehicleCnt(),
                totalReportInfo.getCurrentOnlineCnt(),
                totalReportInfo.getOnlineRate(),
                totalReportInfo.getOfflineCnt(),
                totalReportInfo.getNormalCnt(),
                totalReportInfo.getUnnormalCnt() });

        mapList.add(cellMap);

        mapData.put("page", 1);// 从前台获取当前第page页
        mapData.put("total", 1);
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 获取报表统计信息
     */
    public String getOperatingTotal() {
        try {
            // 获取报表统计信息
            totalReportInfo = (TotalReportInfo) service.getObject("OperatingReport.getOperatingInfos", null);
            this.operatingMap = createOperatingMap(totalReportInfo);
            
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query operating record error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query operating record error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_OPREPORT_QUERY_MID);
            addOperationLog("查询运营报表统计值");
        }
        return SUCCESS;
    }
    
    /**
     * 组装不正常车辆数据
     * @param unnormalCarList
     * @param totalCount
     * @param pageIndex
     * @param rpNum
     * @return
     */
    public Map getUnnormalCars(List unnormalCarList, int totalCount, String pageIndex,String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        
        for (int i = 0; i < unnormalCarList.size(); i++) {
            UnnormalVehicleInfo unnormalVehicleInfo = (UnnormalVehicleInfo) unnormalCarList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", unnormalVehicleInfo.getVehicleId());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum), 
                    unnormalVehicleInfo.getSalesOrder(),
                    unnormalVehicleInfo.getVehicleVin(),
                    unnormalVehicleInfo.getOilWarn(),
                    unnormalVehicleInfo.getOilInstant(),
                    unnormalVehicleInfo.getFrontGate(),
                    unnormalVehicleInfo.getGpsState()
                     });

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 查询不正常车辆
     * @return
     */
    public String queryUnnormalCars() {
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
            
            int totalCount = 0;
            totalCount = service.getCount("OperatingReport.getUnnormalCarsCount", null);

            unnormalList = service.getObjectsByPage(
                    "OperatingReport.getUnnormalVehicle", map, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            if (unnormalList != null && unnormalList.size() == 0) {
                // 无信息
                addActionMessage(getText("common.no.data"));
            }

            this.unnormalCarsMap = getUnnormalCars(unnormalList, totalCount, pageIndex,rpNum);
            
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query unnormal vehicles error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query unnormal vehicles error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_OPREPORT_QUERY_MID);
            addOperationLog("查询非正常车辆");
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

    public TotalReportInfo getTotalReportInfo() {
        return totalReportInfo;
    }

    public void setTotalReportInfo(TotalReportInfo totalReportInfo) {
        this.totalReportInfo = totalReportInfo;
    }

    public Map getOperatingMap() {
        return operatingMap;
    }

    public void setOperatingMap(Map operatingMap) {
        this.operatingMap = operatingMap;
    }

    public List < UnnormalVehicleInfo > getUnnormalList() {
        return unnormalList;
    }

    public void setUnnormalList(List < UnnormalVehicleInfo > unnormalList) {
        this.unnormalList = unnormalList;
    }

    public Map getUnnormalCarsMap() {
        return unnormalCarsMap;
    }

    public void setUnnormalCarsMap(Map unnormalCarsMap) {
        this.unnormalCarsMap = unnormalCarsMap;
    }
}
