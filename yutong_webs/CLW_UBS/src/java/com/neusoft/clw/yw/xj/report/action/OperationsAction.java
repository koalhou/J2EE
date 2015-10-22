package com.neusoft.clw.yw.xj.report.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.cl.carbase.ds.CarBaseInfo;
import com.neusoft.clw.yw.cs.business.ds.BusinessInfo;
import com.neusoft.clw.yw.cs.sim.ds.SimInfo;
import com.neusoft.clw.yw.xj.report.ds.OperationsInfo;
import com.neusoft.clw.yw.xj.report.ds.OprationsReportSecond;
import com.neusoft.clw.yw.xj.statistic.ds.StatisticInfo;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class OperationsAction extends PaginationAction {
    private transient Service service;

    private String enterprise_code;

    private String full_name;

    private String vehicle_vin;

    private String terminal_id;

    private String sim_card_number;

    private String time_begin;

    private String time_end;

    private String expenterprise_code;

    /** 提示信息 **/
    private String message = null;

    private List < OperationsInfo > pageList = new ArrayList < OperationsInfo >();

    private List < OperationsInfo > pageListSum = new ArrayList < OperationsInfo >();

    private List < OprationsReportSecond > operInfo = new ArrayList < OprationsReportSecond >();

    private List < OprationsReportSecond > operSumInfo = new ArrayList < OprationsReportSecond >();

    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("operationsInfo.location"));

        try {

            Map < String, Object > map = new HashMap < String, Object >(2);
            map.put("enterprise_code", SqlStringUtil.getNull(enterprise_code));
            map.put("full_name", SqlStringUtil.getNull(full_name));
            int totalSize = service.getCount("OperationsReport.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < OperationsInfo >) service.getObjectsByPage(
                    "OperationsReport.getOperationsInfos", map, pageObj
                            .getStartOfPage(), pageSize));
            setPageListSum((List < OperationsInfo >) service.getObjects(
                    "OperationsReport.getOperationSumInfos", map));

        } catch (BusinessException e) {
            log.error("查询运营报表分析信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询运营报表分析信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_XJ_OPREPORT_MID);
            addOperationLog("查询运营报表分析信息");
        }
        return SUCCESS;
    }

    public String init_search() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("operationsInfo.location"));

        try {

            Map < String, Object > map = new HashMap < String, Object >(2);
            map.put("enterprise_code", SqlStringUtil.getNull(enterprise_code));
            map.put("full_name", SqlStringUtil.getNull(full_name));

            int totalSize = service.getCount("OperationsReport.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < OperationsInfo >) service.getObjectsByPage(
                    "OperationsReport.getOperationsInfos", map, pageObj
                            .getStartOfPage(), pageSize));
            setPageListSum((List < OperationsInfo >) service.getObjects(
                    "OperationsReport.getOperationSumInfos", map));
        } catch (BusinessException e) {
            log.error("查询运营报表分析信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询运营报表分析信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_OPREPORT_QUERY_MID);
            addOperationLog("查询运营报表分析信息");
        }
        return SUCCESS;
    }

    /**
     * 点击企业编码进入二级报表
     * @return
     */
    public String queryReportInfo() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("operationsInfo.location"));

        try {
            // 查询二级报表
            Map < String, Object > map = new HashMap < String, Object >(4);
            map.put("enterprise_code", SqlStringUtil.getNull(enterprise_code));
            map.put("vehicle_vin", SqlStringUtil.getNull(vehicle_vin));
            map.put("terminal_id", SqlStringUtil.getNull(terminal_id));
            map.put("sim_card_number", SqlStringUtil.getNull(sim_card_number));

            int totalSize = service.getCount(
                    "OperationsReport.getCountByEnterCode", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setOperInfo((List < OprationsReportSecond >) service
                    .getObjectsByPage(
                            "OperationsReport.getReportInfoByEnterCode", map,
                            pageObj.getStartOfPage(), pageSize));
            setOperSumInfo((List < OprationsReportSecond >) service.getObjects(
                    "OperationsReport.getReportInfoSumByEnterCode", map));

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query business detail error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query business detail error:" + e.getMessage());
            return ERROR;
        }

        return SUCCESS;
    }

    public String openBankChartReport() {

        return SUCCESS;
    }

    /**
     * 按天统计柱状图
     * @param vehicleVin
     * @param startTime
     * @return
     */
    public String getDayChart(String time_begin, String time_end,
            String enterprise_code, String vehicle_vin) {
        final String browseTitle = getText("oprationsReportSecond.info.dayChart");

        Map < String, Object > mapChart = new HashMap < String, Object >();
        mapChart.put("enterprise_code", SqlStringUtil.getNull(enterprise_code));
        mapChart.put("vehicle_vin", SqlStringUtil.getNull(vehicle_vin));
        mapChart.put("time_begin", SqlStringUtil.getNull(time_begin));
        mapChart.put("time_end", SqlStringUtil.getNull(time_end));
        String xmlStr = "";
        List < OprationsReportSecond > chartReport = new ArrayList < OprationsReportSecond >();
        try {
            /*
             * Date date1 = new
             * SimpleDateFormat("yyyy-mm-dd").parse(time_begin); Date date2 =
             * new SimpleDateFormat("yyyy-mm-dd").parse(time_end); int day =
             * (int) ((date1.getTime()-date2.getTime())/(24*60*60*1000));
             */

            chartReport = (List < OprationsReportSecond >) service.getObjects(
                    "OperationsReport.getDayReportChart", mapChart);
            xmlStr = "<chart numdivlines='9' " + "showValues='0' "
                    + "showAlternateVGridColor='1' "
                    + "numVisiblePlot='7'><categories>";

            for (OprationsReportSecond info : chartReport) {

                if (info.getTerminal_time() != null) {
                    xmlStr = xmlStr + "<category label='"
                            + info.getTerminal_time() + "' /> ";
                }
            }
            xmlStr = xmlStr
                    + "</categories><dataset color='588526' anchorBorderColor='588526'>";
            for (OprationsReportSecond info : chartReport) {

                if (info.getTerminal_time() != null) {

                    xmlStr = xmlStr + "<set value='" + info.getHis_datasum()
                            + "' />";

                }
            }
            xmlStr = xmlStr + "</dataset></chart>";

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return null;
        }
        return xmlStr;

    }

    /**
     * 按小时统计柱状图
     * @param vehicleVin
     * @param startTime
     * @return
     */
    public String getHourChart(String reportDate, String enterprise_code,
            String vehicle_vin) {
        final String browseTitle = getText("oprationsReportSecond.info.dayChart");

        Map < String, Object > mapChart = new HashMap < String, Object >();
        mapChart.put("enterprise_code", SqlStringUtil.getNull(enterprise_code));
        mapChart.put("vehicle_vin", SqlStringUtil.getNull(vehicle_vin));
        mapChart.put("reportDate", SqlStringUtil.getNull(reportDate));
        String xmlStr = "";
        List < OprationsReportSecond > chartReport = new ArrayList < OprationsReportSecond >();
        try {
            /*
             * Date date1 = new
             * SimpleDateFormat("yyyy-mm-dd").parse(time_begin); Date date2 =
             * new SimpleDateFormat("yyyy-mm-dd").parse(time_end); int day =
             * (int) ((date1.getTime()-date2.getTime())/(24*60*60*1000));
             */

            chartReport = (List < OprationsReportSecond >) service.getObjects(
                    "OperationsReport.getHourReportChart", mapChart);
            xmlStr = "<chart showValues='1'   " + "baseFontSize='10' "
                    + "outCnvBaseFontSize='12' " + "decimals='0' "
                    + "formatNumberScale='0'>";

            for (OprationsReportSecond info : chartReport) {

                if (info.getTerminal_time() != null) {
                    xmlStr = xmlStr + "<set label='" + info.getTerminal_time()
                            + "" + getText("oprationsChart.hour") + "' value='"
                            + info.getHis_datasum() + "' color='588526' />";
                }
            }

            xmlStr = xmlStr + "</chart>";

        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return null;
        }
        return xmlStr;

    }

    /**
     * 导出运营报表分析
     * @return
     */
    public String exportFirstReport() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("enterprise_code", SqlStringUtil.getNull(enterprise_code));
        map.put("full_name", SqlStringUtil.getNull(full_name));
        List < OperationsInfo > list = new ArrayList < OperationsInfo >();
        try {
            list = (List < OperationsInfo >) service.getObjects(
                    "OperationsReport.getOperationsInfos", map);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID() + "report.xls";

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("运营报表分析");

            excelExporter.putAutoExtendSheets("exportFirstReport", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
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
        response.setHeader("Content-disposition",
                "attachment;filename=report.xls");
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
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
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
            setOperationType(Constants.EXPORT,
                    ModuleId.CLW_M_XJ_OPREPORT_FIRSTEXPORT_MID);
            addOperationLog("导出运营报表分析");
        }
        // 导出文件成功
        return null;
    }

    /**
     * 导出运营报表分析
     * @return
     */
    public String exportSecondReport() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("enterprise_code", SqlStringUtil.getNull(expenterprise_code));
        map.put("vehicle_vin", SqlStringUtil.getNull(vehicle_vin));
        map.put("terminal_id", SqlStringUtil.getNull(terminal_id));
        map.put("sim_card_number", SqlStringUtil.getNull(sim_card_number));
        List < OprationsReportSecond > list = new ArrayList < OprationsReportSecond >();
        try {
            list = (List < OprationsReportSecond >) service.getObjects(
                    "OperationsReport.getReportInfoByEnterCode", map);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID() + "report.xls";

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("运营报表分析");

            excelExporter.putAutoExtendSheets("exportSecondReport", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
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
        response.setHeader("Content-disposition",
                "attachment;filename=report.xls");
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
            log.error("Export report error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export report error:" + e.getMessage());
            return ERROR;
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
            setOperationType(Constants.EXPORT,
                    ModuleId.CLW_M_XJ_OPREPORT_SECONDEXPORT_MID);
            addOperationLog("导出运营报表分析");
        }
        // 导出文件成功
        return null;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getExpenterprise_code() {
        return expenterprise_code;
    }

    public void setExpenterprise_code(String expenterprise_code) {
        this.expenterprise_code = expenterprise_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime_begin() {
        return time_begin;
    }

    public void setTime_begin(String time_begin) {
        this.time_begin = time_begin;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public List < OperationsInfo > getPageList() {
        return pageList;
    }

    public void setPageList(List < OperationsInfo > pageList) {
        this.pageList = pageList;
    }

    public List < OperationsInfo > getPageListSum() {
        return pageListSum;
    }

    public void setPageListSum(List < OperationsInfo > pageListSum) {
        this.pageListSum = pageListSum;
    }

    public String getEnterprise_code() {
        return enterprise_code;
    }

    public void setEnterprise_code(String enterprise_code) {
        this.enterprise_code = enterprise_code;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public List < OprationsReportSecond > getOperInfo() {
        return operInfo;
    }

    public void setOperInfo(List < OprationsReportSecond > operInfo) {
        this.operInfo = operInfo;
    }

    public List < OprationsReportSecond > getOperSumInfo() {
        return operSumInfo;
    }

    public void setOperSumInfo(List < OprationsReportSecond > operSumInfo) {
        this.operSumInfo = operSumInfo;
    }

    public String getVehicle_vin() {
        return vehicle_vin;
    }

    public void setVehicle_vin(String vehicle_vin) {
        this.vehicle_vin = vehicle_vin;
    }

    public String getTerminal_id() {
        return terminal_id;
    }

    public void setTerminal_id(String terminal_id) {
        this.terminal_id = terminal_id;
    }

    public String getSim_card_number() {
        return sim_card_number;
    }

    public void setSim_card_number(String sim_card_number) {
        this.sim_card_number = sim_card_number;
    }

}
