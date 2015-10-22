package com.neusoft.clw.yw.cs.simflux.action;

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
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.cs.sim.ds.BusinessAttri;
import com.neusoft.clw.yw.cs.simflux.ds.SimFluxInfo;
import com.neusoft.clw.yw.cs.simflux.service.SimFluxManageService;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * SIM卡流量管理action
 * @author JinPeng
 */
public class SimFluxManageAction extends PaginationAction {
    private transient Service service;

    /** SIM卡流量管理service **/
    private transient SimFluxManageService simFluxManageService;

    /** 提示信息 **/
    private String message = null;

    /** SIM卡ID **/
    private String simId = "";

    /** SIM卡号 **/
    private String simNumber = "";

    /** 旧SIM卡号 **/
    private String simOldNumber = "";

    /** 手机号 **/
    private String phoneNumber = "";

    /** SIM卡流量详细信息 **/
    private SimFluxInfo simFluxInfo = new SimFluxInfo();

    /** 运营商列表 **/
    private List < BusinessAttri > businessList = new ArrayList < BusinessAttri >();

    /** SIM卡流量信息列表 **/
    private List < SimFluxInfo > simFluxList = new ArrayList < SimFluxInfo >();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    /**
     * 获取运营商列表
     * @return
     */
    private boolean getBusinessInfos() {
        if (businessList.isEmpty()) {
            try {
                businessList = service.getObjects("SimManage.getBusinessInfos",
                        null);
            } catch (BusinessException e) {
                log.error("Query business list error:" + e.getMessage());
                return false;
            } catch (Exception e) {
                log.error("Query business list error:" + e.getMessage());
                return false;
            }
        }
        return true;
    }

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("simflux.manage.location"));

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("simNumber", simNumber);
            map.put("phoneNumber", phoneNumber);

            int totalCount = 0;
            totalCount = service.getCount("SimFluxManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            simFluxList = (List < SimFluxInfo >) service.getObjectsByPage(
                    "SimFluxManage.getSimFluxInfos", map, pageObj
                            .getStartOfPage(), pageSize);

            if (simFluxList != null && simFluxList.size() == 0) {
                // 无SIM卡流量信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query sim flux error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query sim flux error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_CS_SIMFLUX_QUERY_MID);
            addOperationLog("查询SIM卡流量信息");
        }

        return SUCCESS;
    }

    /**
     * 填加SIM卡流量信息页面初始化
     * @return
     */
    public String addSimFluxBefore() {
        getBusinessInfos();

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 创建SIM卡流量信息
     * @return
     */
    public String addSimFlux() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (simFluxInfo != null) {
            // 设置创建用户
            simFluxInfo.setCreater(currentUser.getUserID());
            // 设置主键值
            simFluxInfo.setSimId(UUIDGenerator.getUUID());
        }

        try {
            // 创建SIM卡流量信息
            service.insert("SimFluxManage.insertSimFlux", simFluxInfo);
            setMessage("simflux.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Insert sim flux error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Insert sim flux error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_CS_SIMFLUX_ADD_MID);
            addOperationLog("新建SIM卡流量信息");
        }

        return SUCCESS;
    }

    /**
     * 查询SIM卡流量信息
     * @return
     */
    public String querySimFluxInfo() {
        if (simId == "" || simId == null) {
            return ERROR;
        } else {
            try {
                simFluxInfo = (SimFluxInfo) service.getObject(
                        "SimFluxManage.getSimFluxInfoById", simId);

                // 设置旧SIM卡号
                setSimOldNumber(simFluxInfo.getSimCardNumber());
                getBusinessInfos();

            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Query sim flux detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Query sim flux detail error:" + e.getMessage());
                return ERROR;
            }
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 更新SIM卡流量信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (simFluxInfo != null) {
            // 设置修改用户
            simFluxInfo.setModifier(currentUser.getUserID());
            // 设置SIM卡主键值
            simFluxInfo.setSimId(simId);
        }

        try {
            // 修改SIM卡流量信息
            service.update("SimFluxManage.updateSimFluxInfo", simFluxInfo);
            setMessage("simflux.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update sim flux error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update sim flux error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_CS_SIMFLUX_MODIFY_MID);
            addOperationLog("修改SIM卡流量信息");
        }

        return SUCCESS;
    }

    /**
     * 删除SIM卡流量信息
     * @return
     */
    public String delete() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (simFluxInfo != null) {
            // 设置修改用户
            simFluxInfo.setModifier(currentUser.getUserID());
            // 设置SIM卡主键值
            simFluxInfo.setSimId(simId);
        }

        try {
            // 删除SIM卡流量信息
            service.update("SimFluxManage.updateSimFluxValid", simFluxInfo);
            setMessage("simflux.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete sim flux error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete sim flux error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_CS_SIMFLUX_DELETE_MID);
            addOperationLog("删除SIM卡流量信息");
        }

        return SUCCESS;
    }

    /**
     * SIM卡流量信息导入页面初始化
     * @return
     */
    public String importSimFluxBefore() {
        getBusinessInfos();

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 获取文件内容错误
     * @return
     */
    private String getFileContentError(List < IEErrorMessage > list) {
        String errMsg = "";
        if (list.size() == 0) {
            return "";
        }
        for (IEErrorMessage tmp : list) {
            String msg = String.format("行:%s 列:%s 错误[%s]", tmp.getRow(), tmp
                    .getCol(), tmp.getMessage());
            errMsg = errMsg.concat(msg);
        }

        if (errMsg.length() > 150) {
            errMsg = errMsg.substring(0, 150);
            errMsg = errMsg.concat("......");
        }

        return errMsg;
    }

    /**
     * 获取文件内容列表
     * @return
     */
    private List < SimFluxInfo > formatSimFluxInfos(List < SimFluxInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < SimFluxInfo > ret = new ArrayList < SimFluxInfo >();

        for (SimFluxInfo tmp : list) {
            if (tmp.getSimCardNumber() != null
                    && tmp.getSimCardNumber().length() == 0
                    && tmp.getCellPhone() != null
                    && tmp.getCellPhone().length() == 0
                    && tmp.getFluxValue() != null
                    && tmp.getFluxValue().length() == 0
                    && tmp.getCallTime() != null
                    && tmp.getCallTime().length() == 0
                    && tmp.getCloseTime() != null
                    && tmp.getCloseTime().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                // 主键
                tmp.setSimId(UUIDGenerator.getUUID());
                // 运营商ID
                tmp.setBusinessId(simFluxInfo.getBusinessId());
                // 创建用户ID
                tmp.setCreater(currentUser.getUserID());
                // 修改用户ID
                tmp.setModifier(currentUser.getUserID());
                // 添加到list中
                ret.add(tmp);
            }
        }

        return ret;
    }

    /**
     * 导入SIM卡流量信息
     * @return
     */
    public String importSimFlux() {
        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            getBusinessInfos();
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            getBusinessInfos();
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }

        List < SimFluxInfo > list = new ArrayList < SimFluxInfo >();

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            IEExcelImporter excelImplortor = null;
            try {
                excelImplortor = new IEExcelImporter(fis);
            } catch (Exception e) {
                setMessage("file.import.error");
                log.error("Import file error:" + e.getMessage());
            }

            list = excelImplortor.getSheetData("importSimFlux", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                getBusinessInfos();
                addActionError(errMessage);
                return ERROR;
            }
        } catch (Exception e) {
            getBusinessInfos();
            setMessage(getText("file.import.error"));
            log.error("Import file error:" + e.getMessage());
            return ERROR;
        } finally {
            // 关闭流
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    ;
                }
            }
        }

        // 格式化数据
        list = formatSimFluxInfos(list);

        if (list.size() == 0) {
            getBusinessInfos();
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        } else {
            try {
                // 导入SIM卡流量信息
                simFluxManageService.importSimFluxInfos(list);
                setMessage("file.import.success");
            } catch (BusinessException e) {
                getBusinessInfos();
                addActionError(getText("info.db.error"));
                log.error("Import sim flux error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                getBusinessInfos();
                addActionError(getText("info.db.error"));
                log.error("Import sim flux error:" + e.getMessage());
                return ERROR;
            } finally {
                setOperationType(Constants.IMPORT,
                        ModuleId.CLW_M_CS_SIMFLUX_IMPORT_MID);
                addOperationLog("导入SIM卡流量信息");
            }
        }

        return SUCCESS;
    }

    /**
     * 导出SIM卡流量信息
     * @return
     */
    public String exportSimFlux() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("simNumber", simNumber);
        map.put("phoneNumber", phoneNumber);
        List < SimFluxInfo > list = new ArrayList < SimFluxInfo >();
        try {
            list = (List < SimFluxInfo >) service.getObjects(
                    "SimFluxManage.getSimFluxInfos", map);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export sim flux error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export sim flux error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID() + "SimFlux.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("SIM卡流量信息");

            excelExporter.putAutoExtendSheets("exportSimFlux", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export sim flux error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export sim flux error:" + e.getMessage());
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
                "attachment;filename=SimFlux.xls");
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
            log.error("Export sim flux error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export sim flux error:" + e.getMessage());
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
            setOperationType(Constants.EXPORT,
                    ModuleId.CLW_M_CS_SIMFLUX_EXPORT_MID);
            addOperationLog("导出SIM卡流量信息");
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSimId() {
        return simId;
    }

    public void setSimId(String simId) {
        this.simId = simId;
    }

    public String getSimNumber() {
        return simNumber;
    }

    public void setSimNumber(String simNumber) {
        this.simNumber = simNumber;
    }

    public String getSimOldNumber() {
        return simOldNumber;
    }

    public void setSimOldNumber(String simOldNumber) {
        this.simOldNumber = simOldNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public SimFluxInfo getSimFluxInfo() {
        return simFluxInfo;
    }

    public void setSimFluxInfo(SimFluxInfo simFluxInfo) {
        this.simFluxInfo = simFluxInfo;
    }

    public List < BusinessAttri > getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List < BusinessAttri > businessList) {
        this.businessList = businessList;
    }

    public List < SimFluxInfo > getSimFluxList() {
        return simFluxList;
    }

    public void setSimFluxList(List < SimFluxInfo > simFluxList) {
        this.simFluxList = simFluxList;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public SimFluxManageService getSimFluxManageService() {
        return simFluxManageService;
    }

    public void setSimFluxManageService(
            SimFluxManageService simFluxManageService) {
        this.simFluxManageService = simFluxManageService;
    }
}
