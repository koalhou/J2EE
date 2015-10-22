package com.neusoft.clw.yw.xj.statistic.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
import com.neusoft.clw.yw.xj.statistic.ds.StatisticInfo;
import com.neusoft.ie.IECsvExporter;
import com.neusoft.ie.IEExcelExporter;
import com.opensymphony.xwork2.ActionContext;

public class StatisticAction extends PaginationAction {
    private transient Service service;

    private String apply_id;

    private String time_begin;

    private String time_end;

    private List < StatisticInfo > pageList = new ArrayList < StatisticInfo >();

    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("statistic.location"));

        try {

            Map < String, Object > map = new HashMap < String, Object >(3);
            map.put("apply_id", SqlStringUtil.getNull(apply_id));
            map.put("time_begin", SqlStringUtil.getNull(time_begin));
            map.put("time_end", SqlStringUtil.getNull(time_end));

            int totalSize = service.getCount("StatisticIbatis.getCount", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < StatisticInfo >) service.getObjectsByPage(
                    "StatisticIbatis.selectStatisticInfo", map, pageObj
                            .getStartOfPage(), pageSize));

        } catch (BusinessException e) {
            log.error("查询访问统计信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询访问统计信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_STATISTIC_QUERY_MID);
            addOperationLog("查询访问统计信息");
        }
        return SUCCESS;
    }

    public String init_log() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("system.log.location"));

        try {

            Map < String, Object > map = new HashMap < String, Object >(3);
            map.put("apply_id", SqlStringUtil.getNull(apply_id));
            map.put("time_begin", SqlStringUtil.getNull(time_begin));
            map.put("time_end", SqlStringUtil.getNull(time_end));

            int totalSize = service
                    .getCount("StatisticIbatis.getCountLog", map);
            if (totalSize == 0) {
                super.addActionError(getText("common.no.data"));
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < StatisticInfo >) service.getObjectsByPage(
                    "StatisticIbatis.selectStatisticInfoLog", map, pageObj
                            .getStartOfPage(), pageSize));

        } catch (BusinessException e) {
            log.error("查询访问统计信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询访问统计信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XJ_SYSLOG_QUERY_MID);
            addOperationLog("查询系统日志信息");
        }
        return SUCCESS;
    }
    
    /**
     * 导出日志信息
     * @return
     */
    /**
    public String exportLog() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("apply_id", SqlStringUtil.getNull(apply_id));
        map.put("time_begin", SqlStringUtil.getNull(time_begin));
        map.put("time_end", SqlStringUtil.getNull(time_end));
        
        List < StatisticInfo > list = new ArrayList < StatisticInfo >();
        try {
            list = (List < StatisticInfo >) service.getObjects(
                    "StatisticIbatis.selectStatisticInfoLog", map);
        } catch (BusinessException e) {
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "SystemLog.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("日志信息");

            excelExporter.putAutoExtendSheets("exportLog", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export log error:" + e.getMessage());
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
                .setHeader("Content-disposition", "attachment;filename=SystemLog.xls");
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
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export log error:" + e.getMessage());
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
            // TODO
            setOperationType(Constants.EXPORT, ModuleId.CLW_M_CS_SIM_EXPORT_MID);
            addOperationLog("导出日志信息");
        }
        // 导出文件成功
        return null;
    }**/

    public String exportLog() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("apply_id", SqlStringUtil.getNull(apply_id));
        map.put("time_begin", SqlStringUtil.getNull(time_begin));
        map.put("time_end", SqlStringUtil.getNull(time_end));
        
        List < StatisticInfo > list = new ArrayList < StatisticInfo >();
        try {
            list = (List < StatisticInfo >) service.getObjects(
                    "StatisticIbatis.selectStatisticInfoLog", map);
        } catch (BusinessException e) {
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            filePath = "/tmp/" + UUIDGenerator.getUUID() + "SystemLog.csv";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            
            IECsvExporter<StatisticInfo> logExporter = new IECsvExporter<StatisticInfo>();
            
            // 根据ie-config.xml中<csvs/>中配置的一个<csv/>中的描述写入字符串中
            // 如何处理字符串就按具体的需求了
            String csvData = logExporter.getCSVData("exportLogCsv", list);
            outputStream.write(csvData.getBytes());
            outputStream.flush();
            
        } catch (FileNotFoundException e) {
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export log error:" + e.getMessage());
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
                .setHeader("Content-disposition", "attachment;filename=SystemLog.csv");
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
            log.error("Export log error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("Export log error:" + e.getMessage());
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
            // TODO
            setOperationType(Constants.EXPORT, ModuleId.CLW_M_CS_SIM_EXPORT_MID);
            addOperationLog("导出日志信息");
        }
        // 导出文件成功
        return null;
    }
    
    public List < StatisticInfo > getPageList() {
        return pageList;
    }

    public void setPageList(List < StatisticInfo > pageList) {
        this.pageList = pageList;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
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

}
