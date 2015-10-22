package com.neusoft.clw.yunxing.car.runhistory.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.vehiclemanage.domain.VehcileInfo;
import com.neusoft.clw.tree.domain.TreeNodeInfo;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunHistory;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunTreeNode;
import com.neusoft.clw.yunxing.car.runhistory.domain.CarRunTreeNodeAttri;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;
/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.0 $ 2012-3-15 3:21:42 PM
 */
public class CarRunHistoryAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示消息 * */
    private String message = null;
    
    private String user_org_id;
    
    private VehcileInfo vehinfo;
    
    private List < VehcileInfo > vehcList;
    
    private List < CarRunHistory > historyList;
    
    private CarRunHistory queryObj;
    
    private CarRunHistory exportObj;
    
    private Map map = new HashMap();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }   


    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getUser_org_id() {
        return user_org_id;
    }

    public void setUser_org_id(String user_org_id) {
        this.user_org_id = user_org_id;
    }

    public VehcileInfo getVehinfo() {
        return vehinfo;
    }

    public void setVehinfo(VehcileInfo vehinfo) {
        this.vehinfo = vehinfo;
    }

    public List<VehcileInfo> getVehcList() {
        return vehcList;
    }

    public void setVehcList(List<VehcileInfo> vehcList) {
        this.vehcList = vehcList;
    }

    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        queryObj = new CarRunHistory();
        queryObj.setQueryTime(DateUtil.getCurrentDay());
        user_org_id = getCurrentUser().getOrganizationID();
        return SUCCESS;
    }

    /**
     * 左侧下方-车辆信息列表
     * @return
     */
    public String getSelectCarList() {
        final String browseTitle = getText("menu2.clyxrz");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

        try {
            if (null == vehinfo) {
                vehinfo = new VehcileInfo();
            }
            if("".equals(strToEmpty(vehinfo.getOrganization_id()))){
                vehinfo.setOrganization_id(user.getOrganizationID());
            }
            vehinfo.setSortname(sortName);
            vehinfo.setSortorder(sortOrder);

            totalCount = service.getCount("CarRunHistory.getCountVehInfos", vehinfo);

            vehcList = (List < VehcileInfo >) service.getObjectsByPage(
                    "CarRunHistory.getVehInfos", vehinfo, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer
                            .parseInt(rpNum));

            this.map = getPagination(vehcList, totalCount, pageIndex);
        } catch (BusinessException e) {
            log.info(browseTitle, e);
            return ERROR;
        }
        /*
        // 设置操作描述
        this.addOperationLog(browseTitle);
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_CAR_RUN_HISTORY_QUR);
        */
        return SUCCESS;
    }
    
    
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getPagination(List vehcList, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < vehcList.size(); i++) {

            VehcileInfo s = (VehcileInfo) vehcList.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getVehicle_vin());

            cellMap.put("cell", new Object[] {s.getVehicle_vin(),s.getVehicle_ln(),
                    ("0".equals(s.getRoute_name())?"未分配":"已分配"),
                    i});

            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    public String getRunHistoryList(){
        String browseTitle = getText("menu2.clyxrz");
        if (null == queryObj || "".equals(strToEmpty(queryObj.getVIN()))) {
            return SUCCESS;
        }
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

        try {
            if (sortName.equals("ROUTE_NAME") || sortName.equals("site_name") || sortName.equals("driver_name") || sortName.equals("sichen_name")) {
                sortName = "nlssort(" + sortName
                       + ",'NLS_SORT=SCHINESE_PINYIN_M')";
            }
            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);

            totalCount = service.getCount("CarRunHistory.getCountHistoryInfos", queryObj);

            historyList = (List < CarRunHistory >) service.getObjectsByPage(
                    "CarRunHistory.getHistoryInfos", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer
                            .parseInt(rpNum));

            this.map = getHistoryPagination(historyList, totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
            log.info(browseTitle, e);
            return ERROR;
        }
        // 设置操作描述
        this.addOperationLog(browseTitle);
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_CAR_RUN_HISTORY_QUR);       
        return SUCCESS;
    }
    
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getHistoryPagination(List list, int totalCount, String pageIndex, String rpNum) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            CarRunHistory s = (CarRunHistory) list.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getId());

            /*cellMap.put("cell", new Object[] {
            		s.getSite_name(),
            		s.getSite_updown(),
                    s.getDriver_name(),
                    s.getSichen_name(),
                    s.getPlanInTime(),
                    s.getRealityInTime(),
                    s.getInOffset(),
                    s.getPlanOutTime(),
                    s.getRealityOutTime(),
                    s.getOutOffset(),
                    s.getBindUpNum(),
                    s.getUpNum(),
                    s.getUpOffset(),
                    s.getBindDownNum(),
                    s.getDownNum(),
                    s.getDownOffset(),
                    s.getSt_num()});*/
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getRoute_name(),
            		s.getRealityInTime(),
            		s.getSite_name(),
            		s.getSite_updown(),
            		s.getStopingtime(),
            		s.getInoutremark(),
            		s.getBindUpNum(),
            		s.getUpNum(),
            		s.getBindDownNum(),
                    s.getDownNum(),
                    s.getQjia_num(),
                    (s.getDriver_name()==null?"未登录":s.getDriver_name()),
                    (s.getSichen_name()==null?"未登录":s.getSichen_name())

                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }
    
    /**
     * 导出车辆运行日志
     * @return
     */
    public String exportRunHistory() {
        String exportTitle = getText("menu2.clyxrz");
        List < CarRunHistory > list = new ArrayList < CarRunHistory >();
        try {
            
            list = (List < CarRunHistory >) service.getObjects(
                    "CarRunHistory.getHistoryInfos", exportObj);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "RunHistory.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle(exportTitle+"("+exportObj.getQueryTime()+")");

            if(list == null || list.size()<1){
                list.add(new CarRunHistory());
            }
            excelExporter.putAutoExtendSheets("exportRunHistory", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 设置下载文件属性
        HttpServletResponse response = ServletActionContext.getResponse();
        response
                .setHeader("Content-disposition", "attachment;filename=car_run_info-"+DateUtil.getCurrentDayTime()+".xls");
        response.setContentType("application/msexcel; charset=\"utf-8\"");

        FileInputStream fileInputStream = null;
        OutputStream out = null;
        try {
            // 下载刚生成的文件
            fileInputStream = new FileInputStream(filePath);
            out = response.getOutputStream();
            int i = 0;
            while ((i = fileInputStream.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            return null;
        } finally {
            // 关闭流
            if (null != fileInputStream) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    ;
                }
            }
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    ;
                }
            }
            // 设置操作描述
            this.addOperationLog("车辆运行日志导出");
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_CAR_RUN_HISTORY_EXP);
        }
        // 导出文件成功
        return null;
    }    


    /**
     * 如果为null则转化为""
     * @param strVar
     * @return
     */
    private String strToEmpty(String strVar) {
        return (strVar != null && !"".equals(strVar) && !"FFFF".equals(strVar)) ? strVar
                : "";
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
    }

    public CarRunHistory getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(CarRunHistory queryObj) {
        this.queryObj = queryObj;
    }

    public List<CarRunHistory> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<CarRunHistory> historyList) {
        this.historyList = historyList;
    }

    public CarRunHistory getExportObj() {
        return exportObj;
    }

    public void setExportObj(CarRunHistory exportObj) {
        this.exportObj = exportObj;
    }
    
    // add by jinp begin
    private List < CarRunTreeNode > treeNodesList;
    
    private CarRunTreeNode carRunTreeNode;
    
    private String name="";
    
    public List < CarRunTreeNode > getTreeNodesList() {
        return treeNodesList;
    }

    public void setTreeNodesList(List < CarRunTreeNode > treeNodesList) {
        this.treeNodesList = treeNodesList;
    }

    public CarRunTreeNode getCarRunTreeNode() {
        return carRunTreeNode;
    }

    public void setCarRunTreeNode(CarRunTreeNode carRunTreeNode) {
        this.carRunTreeNode = carRunTreeNode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    
    /**
     * 无在线状态组织树
     * @return
     */
    
    public String getTreeNodesWithoutOnline() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {        	
            treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                    "CarRunHistory.getTreeNodes", currentUser.getOrganizationID());
            formatEnterpriseInfoWithoutOnline();
            
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
            if(treeNodesList != null && treeNodesList.size() == 0) {
                return;
            }
            UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
            
            List<CarRunTreeNodeAttri> carnumbers = (List < CarRunTreeNodeAttri >) service.getObjects(
                    "CarRunHistory.getCarnumberByEnterprise", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(CarRunTreeNodeAttri tmp : carnumbers) {
                // 已分配车辆
                carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(CarRunTreeNode singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", 
                                                     singleInfo.getName(),
                                                     carNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
   
    
   
    
    /**
     * 查询无在线状态组织树
     * @return
     */
    public String searchTreeNodesWithoutOnline() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        Map<String,String> map = new HashMap<String,String>();
        
        if(null==carRunTreeNode){
            carRunTreeNode = new CarRunTreeNode();
        }
        try {
        	map.put("enterpriseId", currentUser.getOrganizationID());
            map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
        	
        	if(SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")).equals("外租")) {
        		treeNodesList = (List < CarRunTreeNode >) service.getObjects("CarRunHistory.searchTreeNodesByCode_noself", map);
        	} else {
	        	treeNodesList = (List < CarRunTreeNode >) service.getObjects("CarRunHistory.searchTreeNodesByCode", map);
        	}
            if(null == treeNodesList || treeNodesList.size() == 0) {
                // 按车牌查询无数据,查询组织
                treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                            "CarRunHistory.searchTreeNodesByDivisionName", map);
            }
            formatEnterpriseInfoWithoutOnline();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return SUCCESS;
    }
    
    
    
    
    /**
     * 无在线状态组织树--维保管理
     * @return
     */
    public String getTreeNodesWithoutOnlineRepare() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {        	
            treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                    "CarRunHistory.getRepareTreeNodes", currentUser.getOrganizationID());
            formatEnterpriseInfoWithoutOnlineRepare();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }

    /**
     * 格式化企业信息--维保管理
     */
    private void formatEnterpriseInfoWithoutOnlineRepare() {
        try {
            if(treeNodesList != null && treeNodesList.size() == 0) {
                return;
            }
            UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
            
            List<CarRunTreeNodeAttri> carnumbers = (List < CarRunTreeNodeAttri >) service.getObjects(
                    "CarRunHistory.getCarnumberByEnterpriseRepare", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(CarRunTreeNodeAttri tmp : carnumbers) {
                // 已分配车辆
                carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(CarRunTreeNode singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", 
                                                     singleInfo.getName(),
                                                     carNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    
    
    /**
     * 查询无在线状态组织树__维保管理
     * @return
     */
    public String searchTreeNodesWithoutOnlineRepare() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        Map<String,String> map = new HashMap<String,String>();
        
        if(null==carRunTreeNode){
            carRunTreeNode = new CarRunTreeNode();
        }
        try {
        	map.put("enterpriseId", currentUser.getOrganizationID());
            map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
        	
        	if(SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")).equals("外租")) {
        		treeNodesList = (List < CarRunTreeNode >) service.getObjects("CarRunHistory.searchTreeNodesByCode_noselfRepare", map);
        	} else {
	        	treeNodesList = (List < CarRunTreeNode >) service.getObjects("CarRunHistory.searchTreeNodesByCodeRepare", map);
        	}
            if(null == treeNodesList || treeNodesList.size() == 0) {
                // 按车牌查询无数据,查询组织
                treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                            "CarRunHistory.searchTreeNodesByDivisionNameRepare", map);
            }
            formatEnterpriseInfoWithoutOnlineRepare();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return SUCCESS;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void formatPlanEnterpriseInfoWithoutOnline() {
        try {
            if(treeNodesList != null && treeNodesList.size() == 0) {
                return;
            }
            
            UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
            
            List<CarRunTreeNodeAttri> carnumbers = (List < CarRunTreeNodeAttri >) service.getObjects(
                    "CarRunHistory.getPlanCarnumberByEnterprise", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(CarRunTreeNodeAttri tmp : carnumbers) {
                // 已分配车辆
                carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(CarRunTreeNode singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", 
                                                     singleInfo.getName(),
                                                     carNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    private void formatPlanEnterpriseInfoWithoutOnline_exe() {
        try {
            if(treeNodesList != null && treeNodesList.size() == 0) {
                return;
            }
            
            UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                    Constants.USER_SESSION_KEY);
            
            List<CarRunTreeNodeAttri> carnumbers = (List < CarRunTreeNodeAttri >) service.getObjects(
                    "CarRunHistory.getPlanCarnumberByEnterprise_exe", currentUser.getOrganizationID());
            // 注册车辆信息map
            Map<String, String> carNumberMap = new HashMap<String, String>();
            
            for(CarRunTreeNodeAttri tmp : carnumbers) {
                // 已分配车辆
                carNumberMap.put(tmp.getEnterpriseId(), tmp.getCarnum());
            }
            
            for(CarRunTreeNode singleInfo : treeNodesList) {
                if(carNumberMap.get(singleInfo.getId()) != null && carNumberMap.get(singleInfo.getId()).length() > 0) {
                    singleInfo.setName(String.format("%s(%s)", 
                                                     singleInfo.getName(),
                                                     carNumberMap.get(singleInfo.getId())));
                } 
            }
            
        } catch (BusinessException e) {
            log.error("Get car numbers error:" + e.getMessage());
        }
    }
    /**
     * 过滤规划车辆
     * @return
     */
    public String getPlannedTreeNodes() {
        
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        try {
            treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                    "CarRunHistory.getPlannedTreeNodes", currentUser.getOrganizationID());
            formatPlanEnterpriseInfoWithoutOnline();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }
        
        return SUCCESS;
    }
    /**
     * 过滤规划车辆
     * @return
     */
    public String getPlannedTreeNodes_exe() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);

        try {
        	Map<String,Object> param = new HashMap<String,Object>();
        	param.put("value", currentUser.getOrganizationID());
        	if(request.getParameter("time")==null||request.getParameter("time").length()<1)
        		param.put("exe_date", DateUtil.getCurrentDay());
        	else
        		param.put("exe_date", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(request.getParameter("time"), "utf-8")));
            treeNodesList = (List < CarRunTreeNode >) service.getObjects("CarRunHistory.getPlannedTreeNodes_exe", param);
            formatPlanEnterpriseInfoWithoutOnline_exe();
            
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return SUCCESS;
    }
    
    /**
     * 查询规划车辆
     * @return
     */
    public String searchPlannedTreeNodesWithoutOnline() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);

        Map<String,String> map = new HashMap<String,String>();
        
        if(null==carRunTreeNode){
            carRunTreeNode = new CarRunTreeNode();
        }
        try {
            map.put("enterpriseId", currentUser.getOrganizationID());
            map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
        } catch (UnsupportedEncodingException e1) {
            log.error("decode error:" + e1.getMessage());
        }
        try {
            treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                    "CarRunHistory.searchPlannedTreeNodesByName", map);
            
            if(null == treeNodesList || treeNodesList.size() == 0) {
                // 是否有组织名称匹配
                int ret = service.getCount("CarRunHistory.getPlannedDivisionCount", map);
                if(ret > 0) {
                    // 按车牌查询无数据,查询组织
                    treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                            "CarRunHistory.searchPlannedTreeNodesByDivisionName", map);
                } else {
                    return SUCCESS;
                }
            }
            formatPlanEnterpriseInfoWithoutOnline();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    /**
     * 查询规划车辆
     * @return
     */
    public String searchPlannedTreeNodesWithoutl_exe() {
    	HttpServletRequest request = (HttpServletRequest) ActionContext
        .getContext()
        .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        UserInfo currentUser = (UserInfo) ActionContext.getContext().getSession().get(Constants.USER_SESSION_KEY);

        Map<String,String> map = new HashMap<String,String>();
        
        if(null==carRunTreeNode){
            carRunTreeNode = new CarRunTreeNode();
        }
        try {
            map.put("enterpriseId", currentUser.getOrganizationID());
            map.put("name", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(name, "utf-8")));
            
            if(request.getParameter("time")==null||request.getParameter("time").length()<1)
            	map.put("exe_date", DateUtil.getCurrentDay());
        	else
        		map.put("exe_date", SearchUtil.formatSpecialChar(java.net.URLDecoder.decode(request.getParameter("time"), "utf-8")));
        } catch (UnsupportedEncodingException e1) {
            log.error("decode error:" + e1.getMessage());
        }
        try {
            treeNodesList = (List < CarRunTreeNode >) service.getObjects(
                    "CarRunHistory.searchPlannedTreeNodesByName_exe", map);
            
            if(null == treeNodesList || treeNodesList.size() == 0) {
                // 是否有组织名称匹配
                int ret = service.getCount("CarRunHistory.getPlannedDivisionCount_exe", map);
                if(ret > 0) {
                    // 按车牌查询无数据,查询组织
                    treeNodesList = (List <CarRunTreeNode>) service.getObjects(
                            "CarRunHistory.searchPlannedTreeNodes_exe", map);
                } else {
                    return SUCCESS;
                }
            }
            formatPlanEnterpriseInfoWithoutOnline_exe();
        } catch (BusinessException e) {
            log.error("Get enterprise tree error:" + e.getMessage());
        }

        return SUCCESS;
    }
    // add by jinp end
}
