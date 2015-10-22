package com.neusoft.clw.yunxing.car.ridealarm.action;

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

import org.apache.log4j.MDC;
import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;
/**
 * @author <a href="mailto:suyingtao@neusoft.com">suyingtao</a>
 * @version $Revision 1.0 $ 2012-3-16 3:21:42 PM
 */
public class RideAlarmSumAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示消息 * */
    private String message = null;
    
    private RouteChart queryObj;
    private RouteChart exportObj;
    private RouteChart detailObj;
    
    private List < RouteChart > analysisResult;
    
    private Map map = new HashMap();
    
    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        //MDC.put("loginid" ,getloginuuid());
        MDC.put("modulename","[ridealarm]");
        log.info("[进入(运营统计->异常乘车统计->列表信息页面)]");
        queryObj = new RouteChart();
        queryObj.setBegTime(DateUtil.getPreNDay(-2));
        queryObj.setEndTime(DateUtil.getPreDay());
        return SUCCESS;
    }
    
    public String getRideAlarmSum(){
        MDC.put("modulename","[ridealarm]");
        log.info("[异常乘车统计 获取列表信息开始]");
        final String browseTitle = getText("menu2.yccctj");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        try {
            if (null == queryObj) {
                queryObj = new RouteChart();
                queryObj.setUser_organization_id(user.getOrganizationID());
                queryObj.setTime_list("week");
            }else if(queryObj.getUser_organization_id() == null || "".equals(queryObj.getUser_organization_id())){
                queryObj.setUser_organization_id(user.getOrganizationID());
            }
            log.info("[异常乘车统计 查询操作用户的组织ID="+queryObj.getUser_organization_id()+"]");
            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            if(sortName == null || "".equals(sortName)){
                sortName = "NO_UP_NUM DESC, NO_DOWN_NUM DESC, NOFIX_UP_NUM DESC, NOFIX_DOWN_NUM DESC";
            }
            if (sortName.equals("VEHICLE_LN")) {
                sortName = "nlssort(" + sortName
                       + ",'NLS_SORT=SCHINESE_PINYIN_M')";
            }
            queryObj.setSortname(sortName);
            queryObj.setSortorder(sortOrder);

            log.info("[异常乘车统计 查询条件：(rpNum="+rpNum+")(pageIndex="+pageIndex+")(sortName="+sortName+")(sortOrder="+sortOrder+")]");
            log.info("[异常乘车统计 业务分支 : Re_flag="+queryObj.getRe_flag()+"(Re_flag为1时，按企业查询)]");
            // modify by jinp begin [Re_flag为1时，按企业查询]
            totalCount = service.getCount( "1".equals(queryObj.getRe_flag()) ? "RideAlarm.getCountRideAlarmSumInfos"
                                    : "RideAlarm.getCountRideAlarmSumInfosBySingleCar", queryObj);

            analysisResult = (List < RouteChart >) service.getObjectsByPage("1".equals(queryObj.getRe_flag()) ? 
                    "RideAlarm.getRideAlarmSumInfos" : "RideAlarm.getRideAlarmSumInfosBySingleCar", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            List sumList = (List < RouteChart >) service.getObjects("1".equals(queryObj.getRe_flag()) ?
                    "RideAlarm.getRideAlarmSumInfo" : "RideAlarm.getRideAlarmSumInfoBySingleCar", queryObj);
            // modify by jinp end
            log.info("[异常乘车统计 查询获取的数据总数：totalCount="+totalCount+"]");
            this.map = getPaginationForSum(analysisResult, sumList, totalCount, pageIndex);
            // 设置操作描述
            this.addOperationLog(browseTitle);
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_RIDE_ALARM_ST_QUR);
        } catch (BusinessException e) {
            log.info("[异常乘车统计 获取列表信息出现BusinessException]");
            log.error(browseTitle, e);
            return ERROR;
        }
        log.info("[异常乘车统计 获取列表信息结束]");
        return SUCCESS;
    }
    
    public Map getPaginationForSum(List list, List sumList, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            RouteChart s = (RouteChart) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getVIN());
            cellMap.put("cell", new Object[] {
                    s.getVehicle_ln(),
                    s.getNo_up_num(),s.getNo_down_num(),
                    s.getNofix_up_num(),s.getNofix_down_num(),
                    "",s.getOrganization_name(),s.getVIN()});
            mapList.add(cellMap);
        }
        //添加总计行
        RouteChart sumObj = (RouteChart) sumList.get(0);
        Map sumMap = new LinkedHashMap();
        sumMap.put("id", "sumid");
        sumMap.put("cell", new Object[] {
                getText("common.sum"),
                sumObj.getNo_up_num(),sumObj.getNo_down_num(),
                sumObj.getNofix_up_num(),sumObj.getNofix_down_num(),
                queryObj.getUser_organization_id(),"",""});
        mapList.add(sumMap);
        
        mapData.put("page", pageIndex);// 从前台获取当前第page页
        mapData.put("total", totalCount);// 从数据库获取总记录数
        mapData.put("rows", mapList);

        return mapData;
    }

    /**
     * 导出异常乘车统计
     * @return
     */
    public String exportRideAlarmSum() {
        //MDC.put("loginid" ,getloginuuid());
        MDC.put("modulename","[ridealarm]");
        log.info("[导出异常乘车统计开始]");
        String exportTitle = getText("menu2.yccctj");
        List < RouteChart > list = new ArrayList < RouteChart >();
        List < RouteChart > sumList = new ArrayList < RouteChart >();
        try {
            if(exportObj.getUser_organization_id() == null || "".equals(exportObj.getUser_organization_id())){
                log.info("[设置用户组织ID]");
                exportObj.setUser_organization_id(getCurrentUser().getOrganizationID());
            }            
            
            // modify by jinp begin
            list = (List < RouteChart >) service.getObjects("1".equals(exportObj.getRe_flag()) ? 
                    "RideAlarm.getRideAlarmSumInfos" : "RideAlarm.getRideAlarmSumInfosBySingleCar", exportObj);
            sumList = (List < RouteChart >) service.getObjects("1".equals(exportObj.getRe_flag()) ?
                    "RideAlarm.getRideAlarmSumInfo" : "RideAlarm.getRideAlarmSumInfoBySingleCar", exportObj);
            // modify by jinp end
            
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
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "RideAlarmSum.xls";

            log.info("[导出异常乘车统计:filePath="+filePath+"]");
            
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            
            String timeStr = exportObj.getTime_list();
            if("week".equals(timeStr)){
                timeStr = "本周";
            }else if("month".equals(timeStr)){
                timeStr = "本月";
            }else if("quarter".equals(timeStr)){
                timeStr = "本季度";
            }else if("year".equals(timeStr)){
                timeStr = "本年";
            }else{
                timeStr = exportObj.getBegTime()+"——"+exportObj.getEndTime();
            }
            
            log.info("[导出异常乘车统计:timeStr="+timeStr+"]");
            
            excelExporter.setTitle(exportTitle+"("+timeStr+")");
            if(list == null || list.size()<1){
                list.add(new RouteChart());
            }else{
                list.addAll(sumList);
            }
            excelExporter.putAutoExtendSheets("exportRideAlarmSum", 0, list);
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
        response.setHeader("Content-disposition", "attachment;filename=ride_alarm_statistics-"+DateUtil.getCurrentDayTime()+".xls");
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
            this.addOperationLog("异常乘车统计导出");
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_RIDE_ALARM_ST_EXP);
        }
        // 导出文件成功
        log.info("[导出异常乘车统计结束 导出文件成功]");
        return null;
    }
    
    public String ready(){
        //MDC.put("loginid" ,getloginuuid());
        MDC.put("modulename","[ridealarm]");
        log.info("[异常乘车统计->对页面两次encodeURI的车牌号进行解码开始]");
        // 对页面两次encodeURI的车牌号进行解码
        if (detailObj.getVehicle_ln() != null) {
            String vln = detailObj.getVehicle_ln();
            log.info("[异常乘车统计->vln="+vln+"]");
            try {
                detailObj.setVehicle_ln(java.net.URLDecoder.decode(
                        vln, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                log.error("url decoder error:", e);
            }
        }       
        log.info("[异常乘车统计->对页面两次encodeURI的车牌号进行解码完成]");
        return SUCCESS;
    }
    
    public String getRideAlarmDetail(){
        MDC.put("modulename","[ridealarm]");
        log.info("[异常乘车统计 获取详细统计信息开始]");
        final String browseTitle = getText("menu2.yccctj.detail");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        try {
            if (null == detailObj) {
                log.info("[异常乘车统计 null == detailObj 返回error]");
                return ERROR;
            }

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
            if (!sortName.equals("terminal_time")) {
                sortName = "nlssort(" + sortName
                       + ",'NLS_SORT=SCHINESE_PINYIN_M')";
            }
            detailObj.setSortname(sortName);
            detailObj.setSortorder(sortOrder);
            
            if(!"".equals(detailObj.getTime_list())){
            	detailObj.setBegTime("");
            	detailObj.setEndTime("");
            }

            log.info("[异常乘车统计 执行的查询SQL RideAlarm.getCountRideAlarmDetailInfos->totalCount]");
            totalCount = service.getCount("RideAlarm.getCountRideAlarmDetailInfos", detailObj);

            analysisResult = (List < RouteChart >) service.getObjectsByPage(
                    "RideAlarm.getRideAlarmDetailInfos", detailObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));
            log.info("[异常乘车统计 执行的查询SQL RideAlarm.getRideAlarmDetailInfos->analysisResult]");
            
            log.info("[异常乘车统计  获取详细信息的总数：totalCount="+totalCount+"]");

            this.map = getPaginationForDatail(analysisResult, totalCount, pageIndex);
            // 设置操作描述
            this.addOperationLog(browseTitle);
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_RIDE_ALARM_ST_QUR);
        } catch (BusinessException e) {
            log.info("[异常乘车统计 获取详细统计信息 出现BusinessException异常]");
            log.error(browseTitle, e);
            return ERROR;
        }
        log.info("[异常乘车统计 获取详细统计信息结束]");
        return SUCCESS;
    }
    
    public Map getPaginationForDatail(List list, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            RouteChart s = (RouteChart) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", i+1);
            /*cellMap.put("cell", new Object[] {
                    s.getVehicle_ln(),s.getAlarm_type_name(),
                    s.getStu_name(),s.getStu_school(),s.getStu_class(),
                    s.getRoute_name(),s.getSite_name(),s.getTerminal_time()});*/
            cellMap.put("cell", new Object[] {
                    s.getVehicle_ln(),
                    s.getSite_name(),
                    s.getRoute_name(),
                    s.getAlarm_type_name(),
                    s.getTerminal_time(),
                    (s.getDriver_name()==null?"未登录":s.getDriver_name()),
                    (s.getSichen_name()==null?"未登录":s.getSichen_name()),
                    s.getStu_name(),s.getStu_school(),s.getStu_class()
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


    public RouteChart getQueryObj() {
        return queryObj;
    }


    public void setQueryObj(RouteChart queryObj) {
        this.queryObj = queryObj;
    }


    public Map getMap() {
        return map;
    }


    public void setMap(Map map) {
        this.map = map;
    }


    public RouteChart getExportObj() {
        return exportObj;
    }


    public void setExportObj(RouteChart exportObj) {
        this.exportObj = exportObj;
    }


    public List<RouteChart> getAnalysisResult() {
        return analysisResult;
    }


    public void setAnalysisResult(List<RouteChart> analysisResult) {
        this.analysisResult = analysisResult;
    }


    public RouteChart getDetailObj() {
        return detailObj;
    }


    public void setDetailObj(RouteChart detailObj) {
        this.detailObj = detailObj;
    }

    public String iframeChoice() {
    	return SUCCESS;
    }
}
