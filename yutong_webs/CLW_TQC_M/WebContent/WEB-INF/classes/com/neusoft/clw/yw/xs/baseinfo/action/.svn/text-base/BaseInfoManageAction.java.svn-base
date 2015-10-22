package com.neusoft.clw.yw.xs.baseinfo.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.page.ds.Page;
import com.neusoft.clw.common.util.page.ds.PageHelper;
import com.neusoft.clw.yw.common.ds.CommonMapBean;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.baseinfo.ds.BaseInfo;
import com.neusoft.clw.yw.xs.baseinfo.service.BaseInfoManageService;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 基础信息管理action
 * @author JinPeng
 */
public class BaseInfoManageAction extends PaginationAction {
    private transient Service service;

    /** 基础信息管理service **/
    private transient BaseInfoManageService baseInfoManageService;

    /** 提示信息 **/
    private String message = null;

    /** 信息类型（查询条件） **/
    private String codeType = "";

    /** 编码名称（查询条件） **/
    private String codeNameQuery = "";

    /** 信息名称 **/
    private String codeName = "";

    /** 原始编码ID **/
    private String codeIdOld = "";

    /** 原始编码类型 **/
    private String codeTypeOld = "";

    /** 信息类型ID **/
    private String defId = "";

    /** 基础信息类型列表 **/
    private List < BaseInfo > baseInfoList = new ArrayList < BaseInfo >();

    /** 基础信息类型详细 **/
    private BaseInfo baseInfo = new BaseInfo();

    /** 基础消息类型列表Map **/
    private Map < String, String > baseTypeMap = new HashMap < String, String >();

    /** 品牌信息列表 **/
    private Map < String, String > brandMap = new HashMap < String, String >();

    /** 基础消息类型列表List **/
    private List < CommonMapBean > typeList = new ArrayList < CommonMapBean >();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    /**
     * 获取基础消息类型列表
     */
    private void getBaseTypeList() {
        if (typeList.isEmpty()) {
            try {
                typeList = service.getObjects(
                        "CommonBaseInfo.getBaseInfoTypes",
                        Constants.BASEINFO_CODE);
                for (CommonMapBean commonMapBean : typeList) {
                    baseTypeMap.put(commonMapBean.getCodeId(), commonMapBean
                            .getCodeName());
                }
            } catch (BusinessException e) {
                super.addActionError(getText("info.db.error"));
                log.error(e.getMessage());
                return;
            } catch (Exception e) {
                super.addActionError(getText("info.db.error"));
                log.error(e.getMessage());
                return;
            }
        }
    }

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("base.info.location"));

        getBaseTypeList();

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("codeType", codeType);
            map.put("codeNameQuery", codeNameQuery);

            int totalCount = 0;
            totalCount = service.getCount("BaseInfoManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            baseInfoList = (List < BaseInfo >) service.getObjectsByPage(
                    "BaseInfoManage.getBaseInfos", map, pageObj
                            .getStartOfPage(), pageSize);

            if (baseInfoList != null && baseInfoList.size() == 0) {
                // 无基础数据信息
                addActionError(getText("common.no.data"));
                return ERROR;
            } else {
                for (BaseInfo baseInfo : baseInfoList) {
                    // 获取基础信息类型名
                    baseInfo.setCodeTypeName(baseTypeMap.get(baseInfo
                            .getCodeType()));
                }
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query base info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query base info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_XS_CODE_BASEINFO_QUERY_MID);
            addOperationLog("查询基础信息");

            if (codeType != null && codeType.length() > 0) {
                setCodeName(baseTypeMap.get(codeType));
            } else {
                setCodeName(getText("base.info"));
            }
        }

        return SUCCESS;
    }

    /**
     * 填加基础信息初始化
     * @return
     */
    public String addBaseInfoBefore() {
        getBaseTypeList();

        if (null == baseInfo) {
            baseInfo = new BaseInfo();
        }

        baseInfo.setCodeType(codeType);

        if (Constants.VEHICLE_TYPE_CODE.equals(codeType)
                || Constants.ENGINE_TYPE_CODE.equals(codeType)) {
            // 判断是否为车型与发动机型号填加
            try {
                String typeQuery = "";
                if (Constants.VEHICLE_TYPE_CODE.equals(codeType)) {
                    typeQuery = Constants.VEHICLE_BRAND_CODE;
                } else {
                    typeQuery = Constants.ENGINE_BRAND_CODE;
                }

                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseIdInfos", typeQuery);
                for (CommonMapBean commonMapBean : list) {
                    brandMap.put(commonMapBean.getCodeId(), commonMapBean
                            .getCodeName());
                }
            } catch (BusinessException e) {
                ;
            }
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 填加基础信息
     * @return
     */
    public String addBaseInfo() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (baseInfo != null) {
            // 设置创建用户
            baseInfo.setCreater(currentUser.getUserID());
            // 设置主键值
            baseInfo.setDefId(UUIDGenerator.getUUID());
        }

        try {
            // 创建基础信息
            service.insert("BaseInfoManage.insertBaseInfo", baseInfo);
            setMessage("baseinfo.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_XS_CODE_BASEINFO_ADD_MID);
            addOperationLog("新建基础信息");
            setCodeType(baseInfo.getCodeType());
        }

        return SUCCESS;
    }

    /**
     * 根据codeId查询基础信息
     * @return
     */
    public String queryBaseInfo() {
        if (defId == "" || defId == null) {
            return ERROR;
        } else {
            try {
                // 查询基础信息
                baseInfo = (BaseInfo) service.getObject(
                        "BaseInfoManage.getBaseInfoById", defId);
                getBaseTypeList();
                setCodeIdOld(baseInfo.getCodeId());
                setCodeTypeOld(baseInfo.getCodeType());

                if (Constants.VEHICLE_TYPE_CODE.equals(baseInfo.getCodeType())
                        || Constants.ENGINE_TYPE_CODE.equals(baseInfo
                                .getCodeType())) {
                    // 判断是否为车型与发动机型号填加
                    String typeQuery = "";
                    if (Constants.VEHICLE_TYPE_CODE.equals(baseInfo
                            .getCodeType())) {
                        typeQuery = Constants.VEHICLE_BRAND_CODE;
                    } else {
                        typeQuery = Constants.ENGINE_BRAND_CODE;
                    }

                    List < CommonMapBean > list = service.getObjects(
                            "CommonBaseInfo.getCommonBaseIdInfos", typeQuery);
                    for (CommonMapBean commonMapBean : list) {
                        brandMap.put(commonMapBean.getCodeId(), commonMapBean
                                .getCodeName());
                    }
                }

            } catch (BusinessException e) {
                setMessage("info.db.error");
                log.error("Query base info detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                setMessage("info.db.error");
                log.error("Query base info detail error:" + e.getMessage());
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
     * 更新基础信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (baseInfo != null) {
            // 设置修改用户
            baseInfo.setModifier(currentUser.getUserID());
            // 设置主键
            baseInfo.setDefId(defId);
        }

        try {
            // 修改基础信息
            service.update("BaseInfoManage.updateBaseInfo", baseInfo);
            setMessage("baseinfo.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update base info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update base info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_CODE_BASEINFO_MODIFY_MID);
            addOperationLog("修改基础信息");
            setCodeType(baseInfo.getCodeType());
        }

        return SUCCESS;
    }

    /**
     * 删除基础信息
     * @return
     */
    public String delete() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (baseInfo != null) {
            // 设置修改用户
            baseInfo.setModifier(currentUser.getUserID());
            // 设置主键
            baseInfo.setDefId(defId);
        }

        try {
            // 删除基础信息
            service.update("BaseInfoManage.updateBaseInfoValid", baseInfo);
            setMessage("baseinfo.delete.success");
        } catch (BusinessException e) {
            log.error("Delete base info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } catch (Exception e) {
            log.error("Delete base info error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_XS_CODE_BASEINFO_DELETE_MID);
            addOperationLog("删除基础信息");
        }

        return SUCCESS;
    }

    /**
     * 信息导入页面初始化
     * @return
     */
    public String importBaseInfoBefore() {
        getBaseTypeList();

        if (null == baseInfo) {
            baseInfo = new BaseInfo();
        }

        baseInfo.setCodeType(codeType);

        if (Constants.VEHICLE_TYPE_CODE.equals(codeType)
                || Constants.ENGINE_TYPE_CODE.equals(codeType)) {
            // 判断是否为车型与发动机型号填加
            try {
                String typeQuery = "";
                if (Constants.VEHICLE_TYPE_CODE.equals(codeType)) {
                    typeQuery = Constants.VEHICLE_BRAND_CODE;
                } else {
                    typeQuery = Constants.ENGINE_BRAND_CODE;
                }

                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseIdInfos", typeQuery);
                for (CommonMapBean commonMapBean : list) {
                    brandMap.put(commonMapBean.getCodeId(), commonMapBean
                            .getCodeName());
                }
            } catch (BusinessException e) {
                ;
            }
        }

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
    private List < BaseInfo > formatBaseInfos(List < BaseInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < BaseInfo > ret = new ArrayList < BaseInfo >();

        for (BaseInfo tmp : list) {
            if (tmp.getCodeId() != null && tmp.getCodeId().length() == 0
                    && tmp.getCodeName() != null
                    && tmp.getCodeName().length() == 0
                    && tmp.getCodeLevel() != null
                    && tmp.getCodeLevel().length() == 0
                    && tmp.getRemark() != null && tmp.getRemark().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                // 主键
                tmp.setDefId(UUIDGenerator.getUUID());
                // 品牌ID
                tmp.setParentId(baseInfo.getParentId());
                // 类型
                tmp.setCodeType(baseInfo.getCodeType());
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

    private void getInfos() {
        getBaseTypeList();

        if (Constants.VEHICLE_TYPE_CODE.equals(baseInfo.getCodeType())
                || Constants.ENGINE_TYPE_CODE.equals(baseInfo.getCodeType())) {
            // 判断是否为车型与发动机型号填加
            try {
                String typeQuery = "";
                if (Constants.VEHICLE_TYPE_CODE.equals(baseInfo.getCodeType())) {
                    typeQuery = Constants.VEHICLE_BRAND_CODE;
                } else {
                    typeQuery = Constants.ENGINE_BRAND_CODE;
                }

                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseIdInfos", typeQuery);
                for (CommonMapBean commonMapBean : list) {
                    brandMap.put(commonMapBean.getCodeId(), commonMapBean
                            .getCodeName());
                }
            } catch (BusinessException e) {
                ;
            }
        }
    }

    /**
     * 导入基础信息
     * @return
     */
    public String importBaseInfo() {
        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            getInfos();
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            getInfos();
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }

        List < BaseInfo > list = new ArrayList < BaseInfo >();

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

            list = excelImplortor.getSheetData("importBaseInfo", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                getInfos();
                addActionError(errMessage);
                return ERROR;
            }
        } catch (Exception e) {
            getInfos();
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
        list = formatBaseInfos(list);

        if (list.size() == 0) {
            getInfos();
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        } else {
            try {
                // 导入基础信息
                baseInfoManageService.importBaseInfos(list);
                setMessage("file.import.success");
            } catch (BusinessException e) {
                getInfos();
                addActionError(getText("info.db.error"));
                log.error("Import sim error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                getInfos();
                addActionError(getText("info.db.error"));
                log.error("Import sim error:" + e.getMessage());
                return ERROR;
            } finally {
                // setOperationType(Constants.IMPORT,
                // ModuleId.CLW_M_CS_SIM_IMPORT_MID);
                // addOperationLog("导入SIM卡信息");
            }
        }
        setCodeType(baseInfo.getCodeType());
        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public BaseInfoManageService getBaseInfoManageService() {
        return baseInfoManageService;
    }

    public void setBaseInfoManageService(
            BaseInfoManageService baseInfoManageService) {
        this.baseInfoManageService = baseInfoManageService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeNameQuery() {
        return codeNameQuery;
    }

    public void setCodeNameQuery(String codeNameQuery) {
        this.codeNameQuery = codeNameQuery;
    }

    public String getCodeIdOld() {
        return codeIdOld;
    }

    public void setCodeIdOld(String codeIdOld) {
        this.codeIdOld = codeIdOld;
    }

    public String getCodeTypeOld() {
        return codeTypeOld;
    }

    public void setCodeTypeOld(String codeTypeOld) {
        this.codeTypeOld = codeTypeOld;
    }

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public List < BaseInfo > getBaseInfoList() {
        return baseInfoList;
    }

    public void setBaseInfoList(List < BaseInfo > baseInfoList) {
        this.baseInfoList = baseInfoList;
    }

    public BaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(BaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public Map < String, String > getBaseTypeMap() {
        return baseTypeMap;
    }

    public void setBaseTypeMap(Map < String, String > baseTypeMap) {
        this.baseTypeMap = baseTypeMap;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public Map < String, String > getBrandMap() {
        return brandMap;
    }

    public void setBrandMap(Map < String, String > brandMap) {
        this.brandMap = brandMap;
    }

    public List < CommonMapBean > getTypeList() {
        return typeList;
    }

    public void setTypeList(List < CommonMapBean > typeList) {
        this.typeList = typeList;
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

}
