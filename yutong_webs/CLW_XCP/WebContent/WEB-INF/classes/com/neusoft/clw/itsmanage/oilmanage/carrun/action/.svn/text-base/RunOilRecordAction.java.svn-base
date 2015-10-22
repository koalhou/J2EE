package com.neusoft.clw.itsmanage.oilmanage.carrun.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.itsmanage.oilmanage.carrun.domain.RunOilRecord;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;
/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.0 $ 2012-7-6 1:21:42 PM
 */
public class RunOilRecordAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示消息 * */
    private String message = null;
    
    private String user_org_id;

    
    private List < RunOilRecord > runOilList;
    
    private RunOilRecord queryObj;
    
    private RunOilRecord exportObj;
    
    private Map map = new HashMap();
    
    private String vin;
    
    private String line_start_time;
    
    private String line_end_time;
    
    private String lookflag;
    
    private String route_id;
    
    private String begTime;
    
    private String endTime;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLine_start_time() {
        return line_start_time;
    }

    public void setLine_start_time(String line_start_time) {
        this.line_start_time = line_start_time;
    }

    public String getLine_end_time() {
        return line_end_time;
    }

    public void setLine_end_time(String line_end_time) {
        this.line_end_time = line_end_time;
    }

    public String getLookflag() {
        return lookflag;
    }

    public void setLookflag(String lookflag) {
        this.lookflag = lookflag;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

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

    public List<RunOilRecord> getRunOilList() {
        return runOilList;
    }

    public void setRunOilList(List<RunOilRecord> runOilList) {
        this.runOilList = runOilList;
    }

    public RunOilRecord getQueryObj() {
        return queryObj;
    }

    public void setQueryObj(RunOilRecord queryObj) {
        this.queryObj = queryObj;
    }

    public RunOilRecord getExportObj() {
        return exportObj;
    }

    public void setExportObj(RunOilRecord exportObj) {
        this.exportObj = exportObj;
    }
    

    public String getBegTime() {
        return begTime;
    }

    public void setBegTime(String begTime) {
        this.begTime = begTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
    	MDC.put("modulename", "[drivingRecord]");
    	log.info("readyPage start");
        queryObj = new RunOilRecord();
        queryObj.setQueryTime(DateUtil.getCurrentDay());
        user_org_id = getCurrentUser().getOrganizationID();
        log.info("readyPage end");
        return SUCCESS;
    }
    
    public String getRunOilRecords(){
    	MDC.put("modulename", "[drivingRecord]");
    	log.info("getRunOilRecords start");
        String browseTitle = getText("menu2.rxcjl");
        if (null == queryObj) {
            queryObj = new RunOilRecord();
            queryObj.setQueryTime(DateUtil.getCurrentDay());
            queryObj.setOrganization_id(getCurrentUser().getOrganizationID());
        }
        int totalCount = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");

        try {
            if (sortName.equals("VEHICLE_LN")) {
                sortName = "NLSSORT(" + sortName
                       + ",'NLS_SORT=SCHINESE_PINYIN_M')";
            }else if (sortName.equals("SPD_OIL") || sortName.equals("OIL") || sortName.equals("MILEAGE")){
                sortName = "TO_NUMBER(" + sortName + ")";
            }
            
            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);

            totalCount = service.getCount("RunOilRecord.getCountRunOils", queryObj);

            runOilList = (List < RunOilRecord >) service.getObjectsByPage(
                    "RunOilRecord.getRunOils", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer
                            .parseInt(rpNum));

            this.map = getHistoryPagination(runOilList, totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
            log.info(browseTitle, e);
            log.info("getRunOilRecords error end");
            return ERROR;
        }
        // 设置操作描述
        this.addOperationLog(browseTitle);
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_RUN_OIL_RECORD_QUR); 
        log.info("getRunOilRecords end");
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
    	MDC.put("modulename", "[drivingRecord]");
    	log.info("getHistoryPagination start");
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {

            RunOilRecord s = (RunOilRecord) list.get(i);

            Map cellMap = new LinkedHashMap();

            cellMap.put("id", s.getID());

            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getID(),
                    s.getVehicle_vin(),
                    s.getOrganization_name(),
            		s.getVehicle_ln(),
            		s.getOn_date(),
            		s.getOff_date(),
            		s.getSpd_oil(),
            		s.getOil(),
            		s.getMileage()
                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        log.info("getHistoryPagination end");
        return mapData;
    }
    
    public String getGPSpath(){     
        return SUCCESS;
    }
    
    /**
     * 导出日行车记录
     * @return
     */
    public String exportRunOilRecord() {
    	MDC.put("modulename", "[drivingRecord]");
    	log.info("exportRunOilRecord start");
        String exportTitle = getText("menu2.rxcjl");
        List < RunOilRecord > list = new ArrayList < RunOilRecord >();
        if (null == exportObj) {
            exportObj = new RunOilRecord();
            exportObj.setQueryTime(DateUtil.getCurrentDay());
            exportObj.setOrganization_id(getCurrentUser().getOrganizationID());
        }
        try {
            
            list = (List < RunOilRecord >) service.getObjects(
                    "RunOilRecord.getRunOils", exportObj);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            log.info("exportRunOilRecord error end");
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            log.info("exportRunOilRecord error end");
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "RunOilRecord.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle(exportTitle+"("+exportObj.getQueryTime()+")");
            if(list == null || list.size()<1){
                list.add(new RunOilRecord());
            }
            excelExporter.putAutoExtendSheets("exportRunOilRecord", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            log.info("exportRunOilRecord error end");
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error(exportTitle+"Export Data error:" + e.getMessage());
            log.info("exportRunOilRecord error end");
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
                .setHeader("Content-disposition", "attachment;filename=day_run_record-"+DateUtil.getCurrentDayTime()+".xls");
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
            log.info("exportRunOilRecord error end");
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export student error:" + e.getMessage());
            log.info("exportRunOilRecord error end");
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
            this.addOperationLog("日行车记录导出");
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_RUN_OIL_RECORD_EXP);
        }
        // 导出文件成功
        log.info("exportRunOilRecord  end");
        return null;
    }
    
    public String forSuccessForward(){     
        return SUCCESS;
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
    
    
    /**
     * 空调/加热器信息页面
     * @return
     */
    public String showAirDetailPage() {
    	MDC.put("modulename", "[AirDetailRecords]");
    	log.info("showDetailPage start");
        log.info("showDetailPage end");
        return SUCCESS;
    }
    
    public String getAirDetailRecords(){
    	MDC.put("modulename", "[AirDetailRecords]");
    	String browseTitle = getText("menu2.rxcjl");
    	log.info("getAirDetailRecords start");
        int totalCount = 0;
        HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);
        String rpNum = request.getParameter("rp");
        String pageIndex = request.getParameter("page");
        String sortName = request.getParameter("sortname");
        String sortOrder = request.getParameter("sortorder");
        try {
            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);
            totalCount = service.getCount("RunOilRecord.getCountAirDetail", queryObj);
            runOilList = (List < RunOilRecord >) service.getObjectsByPage(
                    "RunOilRecord.getAirDetail", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer
                            .parseInt(rpNum));
            this.map = getAirDetailPagination(runOilList, totalCount, pageIndex, rpNum);
        } catch (BusinessException e) {
            log.info("AirDetailRecords error end");
            return ERROR;
        }
        // 设置操作描述
        this.addOperationLog(browseTitle);
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.CLW_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_RUN_OIL_RECORD_QUR); 
        log.info("getRunOilRecords end");
        return SUCCESS;
    }
    
    /**
     * 转换为Map对象
     * @param dayList
     * @param totalCountDay
     * @param pageIndex
     * @return
     */
    public Map getAirDetailPagination(List list, int totalCount, String pageIndex, String rpNum) {
    	MDC.put("modulename", "[drivingRecord]");
    	log.info("getHistoryPagination start");
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            RunOilRecord s = (RunOilRecord) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getID());
            cellMap.put("cell", new Object[] {
                    (i + 1)+(Integer.parseInt(pageIndex)-1)*Integer.parseInt(rpNum),
                    s.getEvent_id(),
                    s.getEvent_type(),
            		s.getOn_date(),
            		s.getOff_date(),
            		s.getDuration()
                    });
            mapList.add(cellMap);

        }
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);
        log.info("getHistoryPagination end");
        return mapData;
    }
    
}
