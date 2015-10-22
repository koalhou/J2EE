package com.neusoft.clw.yunxing.stu.stushuaka.action;

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

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.businessmanage.gpsmanage.gpsposition.action.gpsUtil;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.DateUtil;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.yunxing.routechart.chart.domain.RouteChart;
import com.neusoft.clw.yunxing.stu.stushuaka.domain.StuLocationInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class StushuakaAction extends PaginationAction {

    /** service共通类 */
    private transient Service service;

    /** 显示消息 * */
    private String message = null;

    private RouteChart queryObj;

    private RouteChart exportObj;

    private List < RouteChart > strideResult;

    private Map map = new HashMap();

    private Map stu_shuaka_map = new HashMap();

    private StuLocationInfo stuLocationInfo = new StuLocationInfo();
    
    public StuLocationInfo getStuLocationInfo() {
        return stuLocationInfo;
    }

    public void setStuLocationInfo(StuLocationInfo stuLocationInfo) {
        this.stuLocationInfo = stuLocationInfo;
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

    public Map getStu_shuaka_map() {
        return stu_shuaka_map;
    }

    public void setStu_shuaka_map(Map stu_shuaka_map) {
        this.stu_shuaka_map = stu_shuaka_map;
    }

    /**
     * 列表信息页面
     * @return
     */
    public String readyPage() {
        queryObj = new RouteChart();
        //queryObj.setBegTime(DateUtil.getCurrentDay() + " 00:00:00");
        //queryObj.setEndTime(DateUtil.getCurrentDay() + " 23:59:59");
        //当天时间 update by fanxy 修改为默认 本周第一天到现在的时间范围(与学生乘车记录统一)
        queryObj.setBegTime(DateUtil.getCurrentBeforeWeek());
        queryObj.setEndTime(DateUtil.getCurrentDay());
        //stu_shuaka_map.put("0", "上车刷卡");
        //stu_shuaka_map.put("1", "下车刷卡");
        return SUCCESS;
    }

    public String getRideInfo() {
        final String browseTitle = getText("menu2.xsshuaka");
        int totalCount = 0;
        UserInfo user = getCurrentUser();
        HttpServletRequest request = (HttpServletRequest) ActionContext
                .getContext()
                .get(org.apache.struts2.StrutsStatics.HTTP_REQUEST);

        try {
            if (null == queryObj) {
                queryObj = new RouteChart();
                //queryObj.setBegTime(DateUtil.getCurrentDay() + " 00:00:00");
                //queryObj.setEndTime(DateUtil.getCurrentDay() + " 23:59:59");
                queryObj.setBegTime(DateUtil.getCurrentBeforeWeek()+" 00:00:00");
                queryObj.setEndTime(DateUtil.getCurrentDay()+" 23:59:59");
            }
            queryObj.setUser_organization_id(user.getOrganizationID());
            
            //重新设置时间的格式 0-23:59:59
            String beg_time_s = queryObj.getBegTime() + " 00:00:00";
            String end_time_s = queryObj.getEndTime() + " 23:59:59";          
            queryObj.setBegTime(beg_time_s);
            queryObj.setEndTime(end_time_s);

            String rpNum = request.getParameter("rp");
            String pageIndex = request.getParameter("page");
            String sortName = request.getParameter("sortname");
            String sortOrder = request.getParameter("sortorder");
           
            //增加排序条件转换  ST_RIDE_FLAG-->VSS_FLAG
            if("ST_RIDE_FLAG".equals(sortName)){
                queryObj.setSortname("VSS_FLAG");
            }else{
                queryObj.setSortname(sortName);
            }           
            queryObj.setSortorder(sortOrder);

            totalCount = service.getCount("Stushuaka.getCountStushuakaList", queryObj);

            strideResult = (List < RouteChart >) service.getObjectsByPage(
                    "Stushuaka.getStuShuakaInfosList", queryObj, (Integer
                            .parseInt(pageIndex) - 1)
                            * Integer.parseInt(rpNum), Integer.parseInt(rpNum));

            this.map = getPagination(strideResult, totalCount, pageIndex);// 转换map

            if (0 == strideResult.size()) {
                this.addActionMessage(getText("nodata.list"));
            }
            if (null != message) {
                addActionMessage(getText(message));
            }
            // 设置操作描述
            this.addOperationLog(browseTitle);
            // 设置操作类型
            this.setOperationType(Constants.SELECT);
            // 设置所属应用系统
            this.setApplyId(Constants.XC_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUSHUAKA_QUREY_ID);
        } catch (BusinessException e) {
            log.info(browseTitle, e);
            return ERROR;
        }
        return SUCCESS;
    }

    public Map getPagination(List list, int totalCount, String pageIndex) {
        List mapList = new ArrayList();
        Map mapData = new LinkedHashMap();
        for (int i = 0; i < list.size(); i++) {
            RouteChart s = (RouteChart) list.get(i);
            Map cellMap = new LinkedHashMap();
            cellMap.put("id", s.getId());
            /*
             * cellMap.put("cell", new Object[] { s.getStu_name(),
             * s.getSt_ride_flag(), s.getStu_code(), s.getStu_school(),
             * s.getStu_class(), s.getRoute_name(), s.getVehicle_ln(),
             * s.getDriver_name(), s.getSichen_name(), s.getTerminal_time(),
             * s.getMesg_flag() });
             */
            cellMap.put("cell",
                    new Object[] {s.getStu_name(), s.getStu_code(),
                                  s.getSt_ride_flag(),s.getTerminal_time(), 
                            s.getStu_school(), s.getStu_class(),s.getVehicle_ln()});
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
    
    // add by jinp begin
    /**
     * 导出
     */
    public String exportStShuaka() {
        
        try {
            UserInfo user = getCurrentUser();
            if (null == queryObj) {
                queryObj = new RouteChart();
            }
            queryObj.setUser_organization_id(user.getOrganizationID());
            
            strideResult = (List < RouteChart >) service.getObjects(
                    "Stushuaka.getStuShuakaInfos", queryObj);
            
            if(strideResult != null && strideResult.size() > 0) {
                for(RouteChart routeChart : strideResult) {
                    if("0".equals(routeChart.getSt_ride_flag())){
                        routeChart.setSt_ride_flag("上车刷卡");
                    }else if("1".equals(routeChart.getSt_ride_flag())){
                        routeChart.setSt_ride_flag("下车刷卡");
                    }else{
                        routeChart.setSt_ride_flag("--");
                    }
                }
            }
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "stushuaka.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("学生刷卡记录");

            excelExporter.putAutoExtendSheets("exportStushuaka", 0, strideResult);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export error:" + e.getMessage());
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
                .setHeader("Content-disposition", "attachment;filename=Stushuaka_" + DateUtil.getCurrentDayTime() +  ".xls");
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
            log.error("Export error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export error:" + e.getMessage());
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
            this.addOperationLog("导出学生刷卡记录");
            // 设置操作类型
            this.setOperationType(Constants.EXPORT);
            // 设置所属应用系统
            this.setApplyId(Constants.CLW_P_CODE);
            // 设置所属模块
            this.setModuleId(MouldId.XCP_STUSHUAKA_EXP_ID);
        }
        // 导出文件成功
        return null;
    }
    
    /**
     * 显示位置信息
     * @return
     */
    public String showStuLocation() {
        try {
            stuLocationInfo = (StuLocationInfo) service.getObject("Stushuaka.getStuLocationById", queryObj.getId());
            if(null != stuLocationInfo && stuLocationInfo.getLat() != "" && stuLocationInfo.getLon() != "") {
                gpsUtil gpsUtil = new gpsUtil();
                String point = gpsUtil.getOneXY(stuLocationInfo.getLon(), stuLocationInfo.getLat());
                if (point != null && point != "") {
                    String[] p = point.split(",");
                    stuLocationInfo.setLon(p[0].toString());
                    stuLocationInfo.setLat(p[1].toString());
                }
            }
            /*
            if("0".equals(stuLocationInfo.getVssFlag())){
                stuLocationInfo.setVssFlag("上车刷卡");
            }else if("1".equals(stuLocationInfo.getVssFlag())){
                stuLocationInfo.setVssFlag("下车刷卡");
            }else{
                stuLocationInfo.setVssFlag("--");
            }*/
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export error:" + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    // add by jinp end
}
