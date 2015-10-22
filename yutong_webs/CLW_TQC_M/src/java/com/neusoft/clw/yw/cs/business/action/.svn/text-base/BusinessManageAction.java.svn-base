package com.neusoft.clw.yw.cs.business.action;

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
import com.neusoft.clw.yw.cs.business.ds.BusinessInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 运营商管理action理
 * @author JinPeng
 */
public class BusinessManageAction extends PaginationAction {
    private transient Service service;

    /** 提示信息 **/
    private String message = null;

    /** 运营商ID **/
    private String businessId = "";

    /** 运营商名称（查询条件） **/
    private String businessName = "";

    /** 运营商信息列表 **/
    private List < BusinessInfo > businessList = new ArrayList < BusinessInfo >();

    /** 运营商详细信息 **/
    private BusinessInfo businessInfo = new BusinessInfo();

    /** 企业类型编码 **/
    private Map < String, String > bizTypeMap = new HashMap < String, String >();

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /**
     * 获取运营商类型列表
     */
    private void getBizTypeList() {
        if (bizTypeMap == null || bizTypeMap.size() == 0) {
            bizTypeMap = Constants.ENTI_TYPE_MAP;
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
            if (businessInfo != null && businessInfo.getCountryId() != ""
                    && businessInfo.getCountryId() != null) {
                mapPar.put("zone_parent_id", businessInfo.getCountryId());

                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (businessInfo != null && businessInfo.getProvinceId() != ""
                    && businessInfo.getCountryId() != null) {
                mapPar.put("zone_parent_id", businessInfo.getProvinceId());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get geography infos error:" + e.getMessage());
            return false;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Get geography infos error:" + e.getMessage());
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
                getText("transfer.business.location"));

        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("businessName", businessName);

            int totalCount = 0;
            totalCount = service.getCount("BusinessManage.getCount", map);
            Page pageObj = new Page(page, totalCount, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);

            businessList = (List < BusinessInfo >) service.getObjectsByPage(
                    "BusinessManage.getBusinessInfos", map, pageObj
                            .getStartOfPage(), pageSize);

            if (businessList != null && businessList.size() == 0) {
                // 无运营商信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }

            // 显示操作成功信息
            if (null != message) {
                addActionMessage(getText(message));
            }

        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query business info error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error("Query business info error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_CS_BUSINESS_QUERY_MID);
            addOperationLog("查询运营商信息");
        }
        return SUCCESS;
    }

    /**
     * 填加运营商信息之前，页面初始化
     * @return
     */
    public String addBusinessBefore() {
        getBizTypeList();

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
     * 填加运营商信息
     * @return
     */
    public String addBusiness() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (businessInfo != null) {
            // 设置创建用户
            businessInfo.setCreater(currentUser.getUserID());
            // 设置主键值
            businessInfo.setBusinessId(UUIDGenerator.getUUID());
        }

        try {
            // 创建运营商信息
            service.insert("BusinessManage.insertBusiness", businessInfo);
            setMessage("business.create.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Insert business error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Insert business error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_CS_BUSINESS_ADD_MID);
            addOperationLog("新建运营商信息");
        }

        return SUCCESS;
    }

    /**
     * 查询运营商信息
     * @return
     */
    public String queryBusinessInfo() {
        if (businessId == "" || businessId == null) {
            return ERROR;
        } else {
            try {
                // 查询运营商信息
                businessInfo = (BusinessInfo) service.getObject(
                        "BusinessManage.getBusinessInfoById", businessId);
                getBizTypeList();

                if (!getGeoInfos()) {
                    // 地理信息初始化失败时
                    super.addActionError(getText("info.db.error"));
                    return ERROR;
                }

                // 显示信息
                if (null != message) {
                    addActionError(getText(message));
                }

            } catch (BusinessException e) {
                super.addActionError(getText("info.db.error"));
                setMessage("info.db.error");
                log.error("Query business detail error:" + e.getMessage());
                return ERROR;
            } catch (Exception e) {
                super.addActionError(getText("info.db.error"));
                setMessage("info.db.error");
                log.error("Query business detail error:" + e.getMessage());
                return ERROR;
            }
        }

        return SUCCESS;
    }

    /**
     * 更新运营商信息
     * @return
     */
    public String update() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        if (businessInfo != null) {
            // 设置修改用户
            businessInfo.setModifier(currentUser.getUserID());
            // 设置运营商ID值
            businessInfo.setBusinessId(businessId);
        }

        try {
            // 修改运营商信息
            service.update("BusinessManage.updateBusinessInfo", businessInfo);
            setMessage("business.update.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Update business error:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Update business error:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_CS_BUSINESS_MODIFY_MID);
            addOperationLog("修改运营商信息");
        }

        return SUCCESS;
    }

    /**
     * 删除运营商信息
     * @return
     */
    public String delete() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        if (businessInfo != null) {
            // 设置修改用户
            businessInfo.setModifier(currentUser.getUserID());
            // 设置主键值
            businessInfo.setBusinessId(businessId);
        }

        try {
            int countNum = service.getCount("BusinessManage.getBizUsedCount",
                    businessId);

            if (countNum > 0) {
                setMessage("business.delete.notpermission");
                log.error("The business is using");
                return ERROR;
            }

            // 删除运营商信息
            service.update("BusinessManage.updateBusinessValid", businessInfo);
            setMessage("business.delete.success");
        } catch (BusinessException e) {
            setMessage("info.db.error");
            log.error("Delete business error:" + e.getMessage());
            setBusinessId(businessId);
            return ERROR;
        } catch (Exception e) {
            setMessage("info.db.error");
            log.error("Delete business error:" + e.getMessage());
            setBusinessId(businessId);
            return ERROR;
        } finally {
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_CS_BUSINESS_DELETE_MID);
            addOperationLog("删除运营商信息");
        }

        return SUCCESS;
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

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List < BusinessInfo > getBusinessList() {
        return businessList;
    }

    public void setBusinessList(List < BusinessInfo > businessList) {
        this.businessList = businessList;
    }

    public BusinessInfo getBusinessInfo() {
        return businessInfo;
    }

    public void setBusinessInfo(BusinessInfo businessInfo) {
        this.businessInfo = businessInfo;
    }

    public Map < String, String > getBizTypeMap() {
        return bizTypeMap;
    }

    public void setBizTypeMap(Map < String, String > bizTypeMap) {
        this.bizTypeMap = bizTypeMap;
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

}
