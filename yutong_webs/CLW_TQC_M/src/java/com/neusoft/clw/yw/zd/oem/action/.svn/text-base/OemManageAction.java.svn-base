package com.neusoft.clw.yw.zd.oem.action;

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
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;
import com.neusoft.clw.yw.zd.oem.ds.OemInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 终端厂家管理类
 * @author JinPeng
 */
public class OemManageAction extends PaginationAction {
    private transient Service service;

    /** 终端厂家名称（查询条件） **/
    private String oemName = "";

    /** 终端厂家ID **/
    private String oemId = "";

    /** 旧编码值（更新时判断） **/
    private String oemCodeOld = "";

    /** 终端厂家列表 **/
    private List < OemInfo > oemList = new ArrayList < OemInfo >();

    /** 终端厂家详细信息 **/
    private OemInfo oemInfo = new OemInfo();

    /** 企业类型编码 **/
    private Map < String, String > entiTypeMap = new HashMap < String, String >();

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /** 提示信息 **/
    private String message = null;

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
            if (oemInfo != null && oemInfo.getCountryId() != ""
                    && oemInfo.getCountryId() != null) {
                mapPar.put("zone_parent_id", oemInfo.getCountryId());

                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (oemInfo != null && oemInfo.getProvinceId() != ""
                    && oemInfo.getCountryId() != null) {
                mapPar.put("zone_parent_id", oemInfo.getProvinceId());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get geography info error:" + e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get geography info error:" + e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * 终端厂家管理页面初始化
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("oem.manage.location"));

        // 查询终端厂家信息
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("oemName", oemName);

            int totalCount = 0;
            totalCount = service.getCount("OemManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            oemList = (List < OemInfo >) service.getObjectsByPage(
                    "OemManage.getOemInfos", map, pageObj.getStartOfPage(),
                    pageSize);

            if (oemList != null && oemList.size() == 0) {
                // 无终端厂家信息
                addActionError(getText("common.no.data"));
                return ERROR;
            } else {
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query oem infos error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query oem infos error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_ZD_OEM_QUERY_MID);
            addOperationLog("查询终端厂家信息");
        }

        return SUCCESS;
    }

    /**
     * 查询终端厂家信息
     * @return
     */
    public String queryOem() {
        // 查询终端厂家信息
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("oemName", oemName);

            int totalCount = 0;
            totalCount = service.getCount("OemManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            oemList = (List < OemInfo >) service.getObjectsByPage(
                    "OemManage.getOemInfos", map, pageObj.getStartOfPage(),
                    pageSize);

            if (oemList != null && oemList.size() == 0) {
                // 无终端厂家信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query oem infos error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query oem infos error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT, ModuleId.CLW_M_ZD_OEM_QUERY_MID);
            addOperationLog("查询终端厂家信息");
        }
        return SUCCESS;
    }

    /**
     * 填加终端厂家信息之前，页面初始化
     * @return
     */
    public String addOemBefore() {
        if (entiTypeMap == null || entiTypeMap.size() == 0) {
            entiTypeMap = Constants.ENTI_TYPE_MAP;
        }

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
     * 填加终端厂家信息
     * @return
     */
    public String addOem() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (oemInfo != null) {
            // 设置创建用户
            oemInfo.setCreater(currentUser.getUserID());
            // 设置主键值
            oemInfo.setOemId(UUIDGenerator.getUUID());
        }

        try {
            // 创建终端厂家信息
            service.insert("OemManage.insertOem", oemInfo);
            setMessage("oem.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Inser oem info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Inser oem info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT, ModuleId.CLW_M_ZD_OEM_ADD_MID);
            addOperationLog("新建终端厂家信息");
        }

        return SUCCESS;
    }

    /**
     * 查询终端厂家信息
     * @return
     */
    public String queryOemInfo() {
        if (oemId == "" || oemId == null) {
            return ERROR;
        } else {
            try {
                // 查询终端厂家信息
                oemInfo = (OemInfo) service.getObject(
                        "OemManage.getOemInfoById", oemId);
                setOemCodeOld(oemInfo.getOemCode());

                if (entiTypeMap == null || entiTypeMap.size() == 0) {
                    entiTypeMap = Constants.ENTI_TYPE_MAP;
                }

                if (!getGeoInfos()) {
                    // 地理信息初始化失败时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }

            } catch (BusinessException e) {
                super.addActionError(getText("info.db.error"));
                setMessage("info.db.error");
                log.error("Query oem detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                super.addActionError(getText("info.db.error"));
                setMessage("info.db.error");
                log.error("Query oem detail error:" + e.getMessage());
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
     * 更新终端厂家信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (oemInfo != null) {
            // 设置修改用户
            oemInfo.setModifier(currentUser.getUserID());
            // 设置终端厂家ID值
            oemInfo.setOemId(oemId);
        }

        try {
            // 修改终端厂家信息
            service.update("OemManage.updateOemInfo", oemInfo);
            setMessage("oem.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update oem info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update oem info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE, ModuleId.CLW_M_ZD_OEM_MODIFY_MID);
            addOperationLog("修改终端厂家信息");
        }

        return SUCCESS;
    }

    /**
     * 删除终端厂家信息
     * @return
     */
    public String delete() {
        if (oemId == "" || oemId == null) {
            return ERROR;
        }

        try {
            int countNum = service.getCount("OemManage.getOemTypeCount", oemId);

            if (countNum > 0) {
                setMessage("oem.delete.notpermission");
                log.error("The oem is using");
                return ERROR;
            }

            // 删除终端厂家信息
            service.update("OemManage.updateOemValid", oemId);
            setMessage("oem.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete oem info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete oem info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE, ModuleId.CLW_M_ZD_OEM_DELETE_MID);
            addOperationLog("删除终端厂家信息");
        }

        return SUCCESS;
    }

    public Map < String, String > getEntiTypeMap() {
        return entiTypeMap;
    }

    public void setEntiTypeMap(Map < String, String > entiTypeMap) {
        this.entiTypeMap = entiTypeMap;
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

    public String getOemId() {
        return oemId;
    }

    public void setOemId(String oemId) {
        this.oemId = oemId;
    }

    public OemInfo getOemInfo() {
        return oemInfo;
    }

    public void setOemInfo(OemInfo oemInfo) {
        this.oemInfo = oemInfo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOemName() {
        return oemName;
    }

    public void setOemName(String oemName) {
        this.oemName = oemName;
    }

    public List < OemInfo > getOemList() {
        return oemList;
    }

    public void setOemList(List < OemInfo > oemList) {
        this.oemList = oemList;
    }

    /**
     * @return Returns the service.
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service The service to set.
     */
    public void setService(Service service) {
        this.service = service;
    }

    public String getOemCodeOld() {
        return oemCodeOld;
    }

    public void setOemCodeOld(String oemCodeOld) {
        this.oemCodeOld = oemCodeOld;
    }
}
