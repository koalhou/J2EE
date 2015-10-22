package com.neusoft.clw.yw.cs.sim.action;

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
import com.neusoft.clw.common.util.SearchUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.common.ds.CommonMapBean;
import com.neusoft.clw.yw.cs.sim.ds.BusinessAttri;
import com.neusoft.clw.yw.cs.sim.ds.SimInfo;
import com.neusoft.clw.yw.cs.sim.service.SimManageService;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelExporter;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * SIM卡管理action
 * @author JinPeng
 */
public class SimManageAction extends PaginationAction {

    private transient Service service;

    /** SIM卡管理service **/
    private transient SimManageService simManageService;

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

    /** 旧手机号 **/
    private String oldPhoneNumber = "";
    
    /** SIM卡信息列表 **/
    private List < SimInfo > simList = new ArrayList < SimInfo >();

    /** SIM卡详细信息 **/
    private SimInfo simInfo = new SimInfo();

    /** 运营商列表 **/
    private List < BusinessAttri > businessList = new ArrayList < BusinessAttri >();

    /** 基础消息类型列表List **/
    private List < CommonMapBean > apnTypeList = new ArrayList < CommonMapBean >();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("sim.manage.location"));

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("simNumber", SearchUtil.formatSpecialChar(simNumber));
            map.put("phoneNumber", SearchUtil.formatSpecialChar(phoneNumber));

            int totalCount = 0;
            totalCount = service.getCount("SimManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            simList = (List < SimInfo >) service.getObjectsByPage(
                    "SimManage.getSimInfos", map, pageObj.getStartOfPage(),
                    pageSize);

            if (simList != null && simList.size() == 0) {
                // 无SIM卡信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query sim infos error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query sim infos error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_CS_SIM_QUERY_MID);
            addOperationLog("查询SIM卡信息");
        }

        return SUCCESS;
    }

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

        if (apnTypeList.isEmpty()) {
            try {
                apnTypeList = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos",
                        Constants.APNTYPE_CODE);
            } catch (BusinessException e) {
                log.error(e.getMessage());
                return false;
            } catch (Exception e) {
                log.error(e.getMessage());
                return false;
            }
        }

        return true;
    }

    /**
     * 填加SIM卡信息页面初始化
     * @return
     */
    public String addSimBefore() {
        getBusinessInfos();

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 创建SIM卡信息
     * @return
     */
    public String addSim() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (simInfo != null) {
            // 设置创建用户
            simInfo.setCreater(currentUser.getUserID());
            // 设置主键值
            simInfo.setSimId(UUIDGenerator.getUUID());
        }

        try {
            // 创建SIM卡信息
            service.insert("SimManage.mergeSimInfos", simInfo);
            setMessage("sim.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT, ModuleId.CLW_M_CS_SIM_ADD_MID);
            addOperationLog("新建SIM卡信息");
        }

        return SUCCESS;
    }

    /**
     * 查询SIM卡信息
     * @return
     */
    public String querySimInfo() {
        if (simId == "" || simId == null) {
            return ERROR;
        } else {
            try {
                simInfo = (SimInfo) service.getObject(
                        "SimManage.getSimInfoById", simId);

                // 设置旧SIM卡号
                setSimOldNumber(simInfo.getSimCardNumber());
                // 设置旧手机号
                setOldPhoneNumber(simInfo.getCellPhone());
                getBusinessInfos();

                // 显示信息
                if (null != message) {
                    addActionError(getText(message));
                }

            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Query sim detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Query sim detail error:" + e.getMessage());
                return ERROR;
            }
        }
        return SUCCESS;
    }

    /**
     * 更新SIM卡信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (simInfo != null) {
            // 设置修改用户
            simInfo.setModifier(currentUser.getUserID());
            // 设置SIM卡主键值
            simInfo.setSimId(simId);
            // 设置旧SIM卡号值
            simInfo.setOldSimCardNumber(simOldNumber);
        }

        try {
            // 修改SIM卡信息
            simManageService.updateSimInfo(simInfo);
            setMessage("sim.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update sim info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update sim info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE, ModuleId.CLW_M_CS_SIM_MODIFY_MID);
            addOperationLog("修改SIM卡信息");
        }

        return SUCCESS;
    }

    /**
     * 删除SIM卡信息
     * @return
     */
    public String delete() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (simInfo != null) {
            // 设置修改用户
            simInfo.setModifier(currentUser.getUserID());
            // 设置SIM卡主键值
            simInfo.setSimId(simId);
        }

        try {
            int countNum = service.getCount("SimManage.getSIMRegisteredCount",
                    simId);

            if (countNum > 0) {
                setMessage("sim.delete.notpermission");
                log.error("The sim is registered");
                return ERROR;
            }

            // 删除SIM卡信息
            service.update("SimManage.updateSimValid", simInfo);
            setMessage("sim.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete sim error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE, ModuleId.CLW_M_CS_SIM_DELETE_MID);
            addOperationLog("删除SIM卡信息");
        }

        return SUCCESS;
    }

    /**
     * SIM卡信息导入页面初始化
     * @return
     */
    public String importSimBefore() {
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
    private List < SimInfo > formatSimInfos(List < SimInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < SimInfo > ret = new ArrayList < SimInfo >();

        for (SimInfo tmp : list) {
            if (tmp.getSimCardNumber() != null
                    && tmp.getSimCardNumber().length() == 0
                    && tmp.getIccidElectron() != null
                    && tmp.getIccidElectron().length() == 0
                    && tmp.getIccidPrint() != null
                    && tmp.getIccidPrint().length() == 0
                    && tmp.getCellPhone() != null
                    && tmp.getCellPhone().length() == 0
                    && tmp.getStartUseTime() != null
                    && tmp.getStartUseTime().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                // 主键
                tmp.setSimId(UUIDGenerator.getUUID());
                // 运营商ID
                tmp.setBusinessId(simInfo.getBusinessId());
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
     * 导入SIM卡信息
     * @return
     */
    public String importSim() {
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

        List < SimInfo > list = new ArrayList < SimInfo >();

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

            list = excelImplortor.getSheetData("importSim", 0);

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
        list = formatSimInfos(list);

        if (list.size() == 0) {
            getBusinessInfos();
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        } else {
            try {
                // 导入SIM卡信息
                simManageService.importSimInfos(list);
                setMessage("file.import.success");
            } catch (BusinessException e) {
                getBusinessInfos();
                addActionError(getText("info.db.error"));
                log.error("Import sim error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                getBusinessInfos();
                addActionError(getText("info.db.error"));
                log.error("Import sim error:" + e.getMessage());
                return ERROR;
            } finally {
                setOperationType(Constants.IMPORT,
                        ModuleId.CLW_M_CS_SIM_IMPORT_MID);
                addOperationLog("导入SIM卡信息");
            }
        }

        return SUCCESS;
    }

    /**
     * 导出SIM卡信息
     * @return
     */
    public String exportSim() {
        Map < String, String > map = new HashMap < String, String >();
        map.put("simNumber", simNumber);
        map.put("phoneNumber", phoneNumber);
        List < SimInfo > list = new ArrayList < SimInfo >();
        try {
            list = (List < SimInfo >) service.getObjects(
                    "SimManage.getSimInfos", map);
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        }

        String filePath = "";
        OutputStream outputStream = null;
        try {
            // String realPath =
            // ServletActionContext.getServletContext().getRealPath("/");
            // realPath = realPath.replace("\\", "/");
            // realPath = realPath + "exportfile/";

            filePath = "/tmp/" + UUIDGenerator.getUUID() + "Sim.xls";

            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            outputStream = new FileOutputStream(filePath);
            // 使用Excel导出器IEExcelExporter
            IEExcelExporter excelExporter = new IEExcelExporter(outputStream);
            excelExporter.setTitle("SIM卡信息");

            excelExporter.putAutoExtendSheets("exportSim", 0, list);
            // 将Excel写入到指定的流中
            excelExporter.write();
        } catch (FileNotFoundException e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
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
                .setHeader("Content-disposition", "attachment;filename=Sim.xls");
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
            log.error("Export sim error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("file.export.error");
            log.error("Export sim error:" + e.getMessage());
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
            setOperationType(Constants.EXPORT, ModuleId.CLW_M_CS_SIM_EXPORT_MID);
            addOperationLog("导出SIM卡信息");
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

    public SimManageService getSimManageService() {
        return simManageService;
    }

    public void setSimManageService(SimManageService simManageService) {
        this.simManageService = simManageService;
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

    public String getOldPhoneNumber() {
        return oldPhoneNumber;
    }

    public void setOldPhoneNumber(String oldPhoneNumber) {
        this.oldPhoneNumber = oldPhoneNumber;
    }

    public List < SimInfo > getSimList() {
        return simList;
    }

    public void setSimList(List < SimInfo > simList) {
        this.simList = simList;
    }

    public List < BusinessAttri > getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List < BusinessAttri > businessList) {
        this.businessList = businessList;
    }

    public SimInfo getSimInfo() {
        return simInfo;
    }

    public void setSimInfo(SimInfo simInfo) {
        this.simInfo = simInfo;
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

    public List < CommonMapBean > getApnTypeList() {
        return apnTypeList;
    }

    public void setApnTypeList(List < CommonMapBean > apnTypeList) {
        this.apnTypeList = apnTypeList;
    }

}
