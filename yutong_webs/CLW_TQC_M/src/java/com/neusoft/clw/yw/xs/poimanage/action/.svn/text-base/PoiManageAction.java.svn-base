package com.neusoft.clw.yw.xs.poimanage.action;

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
import com.neusoft.clw.yw.xs.poimanage.ds.PoiInfo;
import com.neusoft.clw.yw.xs.poimanage.service.PoiManageService;
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;
import com.neusoft.ie.IEErrorMessage;
import com.neusoft.ie.IEExcelImporter;
import com.opensymphony.xwork2.ActionContext;

/**
 * 服务点管理action
 * @author JinPeng
 */
public class PoiManageAction extends PaginationAction {
    private transient Service service;

    private transient PoiManageService poiManageService;

    /** 提示信息 **/
    private String message = null;

    /** 服务点名称（查询条件） **/
    private String poiName = "";

    /** 服务点ID **/
    private String poiId = "";

    /** 服务点信息列表 **/
    private List < PoiInfo > poiList = new ArrayList < PoiInfo >();

    /** 服务点信息详细 **/
    private PoiInfo poiInfo = new PoiInfo();

    /** 服务网点代码(旧值) **/
    private String poiCodeOld = "";

    /** 网点类型信息 **/
    private Map < String, String > poiTypeMap = new HashMap < String, String >();

    /** 网点级别信息 **/
    private Map < String, String > poiLevelMap = new HashMap < String, String >();

    /** 服务类型信息 **/
    private Map < String, String > poiServiceMap = new HashMap < String, String >();

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /** 选择的文件 */
    private File file;

    /** 选择的文件类型 */
    private String fileContentType;

    /** 文件名 */
    private String fileFileName;

    /**
     * 获取网点类型信息
     */
    private void getPoiTypeInfos() {
        if (poiTypeMap != null && poiTypeMap.size() == 0) {
            try {
                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos", "POITYPE");
                for (CommonMapBean commonMapBean : list) {
                    poiTypeMap.put(commonMapBean.getCodeId(), commonMapBean
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
     * 获取网点级别信息
     */
    private void getPoiLevelInfos() {
        if (poiLevelMap != null && poiLevelMap.size() == 0) {
            try {
                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos", "POILEVEL");
                for (CommonMapBean commonMapBean : list) {
                    poiLevelMap.put(commonMapBean.getCodeId(), commonMapBean
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
     * 获取网点服务信息
     */
    private void getPoiServiceInfos() {
        if (poiServiceMap != null && poiServiceMap.size() == 0) {
            try {
                List < CommonMapBean > list = service.getObjects(
                        "CommonBaseInfo.getCommonBaseInfos", "POISERVICETYPE");
                for (CommonMapBean commonMapBean : list) {
                    poiServiceMap.put(commonMapBean.getCodeId(), commonMapBean
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
     * 获取地理信息
     * @return
     */
    private boolean getGeoInfos() {
        // 地理信息列表
        List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
        Map < String, Object > mapPar = new HashMap < String, Object >(1);

        try {
            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市
            if (poiInfo != null && poiInfo.getCountryId() != ""
                    && poiInfo.getCountryId() != null) {
                mapPar.put("zone_parent_id", poiInfo.getCountryId());

                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (poiInfo != null && poiInfo.getProvinceId() != ""
                    && poiInfo.getProvinceId() != null) {
                mapPar.put("zone_parent_id", poiInfo.getProvinceId());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {

        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("poi.location"));

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("poiName", poiName);

            int totalCount = 0;
            totalCount = service.getCount("PoiManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            poiList = (List < PoiInfo >) service.getObjectsByPage(
                    "PoiManage.getPoiInfos", map, pageObj.getStartOfPage(),
                    pageSize);

            if (poiList != null && poiList.size() == 0) {
                // 无服务点信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query poi infos error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query poi infos error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_XS_POI_QUERY_MID);
            addOperationLog("查询服务点信息");
        }

        return SUCCESS;
    }

    /**
     * 创建服务点初始化
     * @return
     */
    public String addPoiInfoBefore() {
        // 获取网点类型
        getPoiTypeInfos();
        // 获取网点级别
        getPoiLevelInfos();
        // 获取服务类型
        getPoiServiceInfos();

        if (!getGeoInfos()) {
            // 地理信息初始化失败时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        // 显示信息
        if (null != message) {
            addActionError(getText(message));
        }

        return SUCCESS;
    }

    /**
     * 添加服务点信息
     * @return
     */
    public String addPoiInfo() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // 设置创建用户
        poiInfo.setCreater(currentUser.getUserID());
        // 设置主键值
        poiInfo.setPoiId(UUIDGenerator.getUUID());

        try {
            // 创建服务点信息
            service.insert("PoiManage.insertPoiInfo", poiInfo);
            setMessage("poi.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Insert poi error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Insert poi error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT, ModuleId.CLW_M_XS_POI_ADD_MID);
            addOperationLog("新建服务点信息");
        }

        return SUCCESS;
    }

    /**
     * 根据ID查询服务点信息
     * @return
     */
    public String queryPoiInfoById() {
        // 获取网点类型
        getPoiTypeInfos();
        // 获取网点级别
        getPoiLevelInfos();
        // 获取服务类型
        getPoiServiceInfos();

        try {
            // 查询服务点信息
            poiInfo = (PoiInfo) service.getObject("PoiManage.getPoiDetail",
                    poiId);

            setPoiCodeOld(poiInfo.getPoiCode());

            if (!getGeoInfos()) {
                // 地理信息初始化失败时
                super.addActionError(getText("info.db.error"));
                return ERROR;
            }

        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Query poi detail error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Query poi detail error:" + e.getMessage());
            return ERROR;
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
        if (poiInfo != null) {
            // 设置修改用户
            poiInfo.setModifier(currentUser.getUserID());
            // 设置主键
            poiInfo.setPoiId(poiId);
        }

        try {
            // 修改基础信息
            service.update("PoiManage.updatePoiInfo", poiInfo);
            setMessage("poi.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update poi error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update poi error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE, ModuleId.CLW_M_XS_POI_MODIFY_MID);
            addOperationLog("修改服务点信息");
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
        if (poiInfo != null) {
            // 设置修改用户
            poiInfo.setModifier(currentUser.getUserID());
            // 设置主键
            poiInfo.setPoiId(poiId);
        }

        try {
            // 删除基础信息
            service.update("PoiManage.updatePoiInfoValid", poiInfo);
            setMessage("poi.delete.success");
        } catch (BusinessException e) {
            log.error("Delete poi error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } catch (Exception e) {
            log.error("Delete poi error:" + e.getMessage());
            setMessage("info.db.error");
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE, ModuleId.CLW_M_XS_POI_DELETE_MID);
            addOperationLog("删除服务点信息");
        }

        return SUCCESS;
    }

    /**
     * 服务点有效
     * @return
     */
    public String poiStartUse() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // 修改用户ID
        poiInfo.setModifier(currentUser.getUserID());
        // 服务点有效
        poiInfo.setValidFlag("0");
        // 服务点ID
        poiInfo.setPoiId(poiId);

        try {
            // 用户启用
            service.update("PoiManage.setPoiStatus", poiInfo);
            setMessage("poi.valid.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE, ModuleId.CLW_M_XS_POI_VALID_MID);
            addOperationLog("服务点有效");
        }

        return SUCCESS;
    }

    /**
     * 服务点无效
     * @return
     */
    public String poiStopUse() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        // 修改用户ID
        poiInfo.setModifier(currentUser.getUserID());
        // 服务点无效
        poiInfo.setValidFlag("2");
        // 服务点ID
        poiInfo.setPoiId(poiId);

        try {
            // 用户启用
            service.update("PoiManage.setPoiStatus", poiInfo);
            setMessage("poi.valid.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error(e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_XS_POI_INVALID_MID);
            addOperationLog("服务点无效");
        }

        return SUCCESS;
    }

    /**
     * 导入服务点信息初始化
     * @return
     */
    public String importPoiBefore() {

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
    private List < PoiInfo > formatPoiInfos(List < PoiInfo > list) {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        List < PoiInfo > ret = new ArrayList < PoiInfo >();

        for (PoiInfo tmp : list) {
            if (tmp.getPoiCode() != null && tmp.getPoiCode().length() == 0
                    && tmp.getPoiName() != null
                    && tmp.getPoiName().length() == 0
                    && tmp.getPoiType() != null
                    && tmp.getPoiType().length() == 0
                    && tmp.getPoiLevel() != null
                    && tmp.getPoiLevel().length() == 0
                    && tmp.getPoiServiceType() != null
                    && tmp.getPoiServiceType().length() == 0
                    && tmp.getCountryId() != null
                    && tmp.getCountryId().length() == 0
                    && tmp.getProvinceId() != null
                    && tmp.getProvinceId().length() == 0
                    && tmp.getCityId() != null && tmp.getCityId().length() == 0
                    && tmp.getPoiLon() != null && tmp.getPoiLon().length() == 0
                    && tmp.getPoiLat() != null && tmp.getPoiLat().length() == 0
                    && tmp.getConcateTel() != null
                    && tmp.getConcateTel().length() == 0
                    && tmp.getConcateFax() != null
                    && tmp.getConcateFax().length() == 0
                    && tmp.getPoiAddress() != null
                    && tmp.getPoiAddress().length() == 0
                    && tmp.getConcatePos() != null
                    && tmp.getConcatePos().length() == 0
                    && tmp.getEmail() != null && tmp.getEmail().length() == 0
                    && tmp.getWebsite() != null
                    && tmp.getWebsite().length() == 0
                    && tmp.getConcatePerson() != null
                    && tmp.getConcatePerson().length() == 0
                    && tmp.getRemark() != null && tmp.getRemark().length() == 0) {
                // 文件行数据为空
                continue;
            } else {
                // 主键
                tmp.setPoiId(UUIDGenerator.getUUID());
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
     * 导入服务点信息
     * @return
     */
    public String importPoi() {

        if (null == file || null == fileContentType || null == fileFileName
                || fileFileName.length() < 3) {
            log.info(getText("file.not.exist"));
            addActionError(getText("file.not.exist"));
            return ERROR;
        } else if (!"xls".equals(fileFileName.substring(
                fileFileName.length() - 3, fileFileName.length()))) {
            // 判断是否是excel类型文件
            addActionError(getText("file.type.err"));
            log.info(getText("file.type.err"));
            return ERROR;
        }

        List < PoiInfo > list = new ArrayList < PoiInfo >();

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

            list = excelImplortor.getSheetData("importPoi", 0);

            String errMessage = getFileContentError(excelImplortor
                    .getErrorMessage());

            if (errMessage.length() > 0) {
                addActionError(errMessage);
                return ERROR;
            }
        } catch (Exception e) {
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
        list = formatPoiInfos(list);

        if (list.size() == 0) {
            // 文件中无内容
            addActionMessage(getText("file.content.empty"));
            return ERROR;
        } else {
            try {
                GPSUtil gpsUtil = new GPSUtil();
                // 偏转经纬度信息
                list = gpsUtil.getpost(list);
                // 导入SIM卡流量信息
                poiManageService.importPoiInfos(list);
                setMessage("file.import.success");
            } catch (BusinessException e) {
                addActionError(getText("info.db.error"));
                log.error("Import poi infos error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                addActionError(getText("info.db.error"));
                log.error("Import poi infos error:" + e.getMessage());
                return ERROR;
            } finally {
                setOperationType(Constants.IMPORT,
                        ModuleId.CLW_M_XS_POI_IMPORT_MID);
                addOperationLog("导入服务点信息");
            }
        }
        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public PoiManageService getPoiManageService() {
        return poiManageService;
    }

    public void setPoiManageService(PoiManageService poiManageService) {
        this.poiManageService = poiManageService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getPoiId() {
        return poiId;
    }

    public void setPoiId(String poiId) {
        this.poiId = poiId;
    }

    public List < PoiInfo > getPoiList() {
        return poiList;
    }

    public void setPoiList(List < PoiInfo > poiList) {
        this.poiList = poiList;
    }

    public PoiInfo getPoiInfo() {
        return poiInfo;
    }

    public void setPoiInfo(PoiInfo poiInfo) {
        this.poiInfo = poiInfo;
    }

    public Map < String, String > getPoiTypeMap() {
        return poiTypeMap;
    }

    public void setPoiTypeMap(Map < String, String > poiTypeMap) {
        this.poiTypeMap = poiTypeMap;
    }

    public Map < String, String > getPoiLevelMap() {
        return poiLevelMap;
    }

    public void setPoiLevelMap(Map < String, String > poiLevelMap) {
        this.poiLevelMap = poiLevelMap;
    }

    public Map < String, String > getPoiServiceMap() {
        return poiServiceMap;
    }

    public void setPoiServiceMap(Map < String, String > poiServiceMap) {
        this.poiServiceMap = poiServiceMap;
    }

    public Map < String, String > getCountryInfosMap() {
        return countryInfosMap;
    }

    public void setCountryInfosMap(Map < String, String > countryInfosMap) {
        this.countryInfosMap = countryInfosMap;
    }

    public Map < String, String > getProvinceInfosMap() {
        return provinceInfosMap;
    }

    public void setProvinceInfosMap(Map < String, String > provinceInfosMap) {
        this.provinceInfosMap = provinceInfosMap;
    }

    public Map < String, String > getCityInfosMap() {
        return cityInfosMap;
    }

    public void setCityInfosMap(Map < String, String > cityInfosMap) {
        this.cityInfosMap = cityInfosMap;
    }

    public String getPoiCodeOld() {
        return poiCodeOld;
    }

    public void setPoiCodeOld(String poiCodeOld) {
        this.poiCodeOld = poiCodeOld;
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
