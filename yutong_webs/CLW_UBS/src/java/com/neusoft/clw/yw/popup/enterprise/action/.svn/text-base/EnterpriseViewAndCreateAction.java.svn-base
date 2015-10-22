package com.neusoft.clw.yw.popup.enterprise.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.ModuleId;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.yw.popup.enterprise.ds.EnterprisePopupInfo;
import com.neusoft.clw.yw.qx.entimanage.ds.EnterpriseDataInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 企业popup列表页action
 * @author JinPeng
 */
public class EnterpriseViewAndCreateAction extends BaseAction {
    private transient Service service;

    /** 企业名称（查询条件） **/
    private String enterpriseNameQuery = "";

    private String enterpriseCodeQuery = "";

    private String enterpriseCode = "";

    private String enterpriseName = "";

    private String enterpriseShortName = "";

    private String enterpriseMessageNum = "";

    private String countryId = "";

    private String provinceId = "";

    private String cityId = "";

    /** 提示信息 **/
    private String message = null;

    /** 企业显示标记：1为不显示宇通杯企业 **/
    private String flag = "";

    /** 企业信息列表 **/
    private List < EnterprisePopupInfo > enterpriseList = new ArrayList < EnterprisePopupInfo >();

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /**
     * 页面初始化/查询
     * @return
     */
    public String init() {
        try {
            Map < String, String > map = new HashMap < String, String >();
            map.put("enterpriseName", enterpriseNameQuery);
            map.put("enterpriseCode", enterpriseCodeQuery);
            map.put("flag", flag);

            if ("2".equals(flag)) {
                enterpriseList = (List < EnterprisePopupInfo >) service
                        .getObjects("EnterpriseBrowse.getAllEnterpriseInfos",
                                map);
            } else {
                enterpriseList = (List < EnterprisePopupInfo >) service
                        .getObjects("EnterpriseBrowse.getEnterpriseInfos", map);
            }

            if (enterpriseList != null && enterpriseList.size() == 0) {
                // 无企业信息
                addActionError(getText("common.no.data"));
                return ERROR;
            }
        } catch (BusinessException e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        } catch (Exception e) {
            super.addActionError(getText("info.db.error"));
            log.error(e.getMessage());
            return ERROR;
        }

        return SUCCESS;
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

    /** 新建企业初始化 **/
    public String enterpriseViewCreate() {
        if (!getGeoInfos()) {
            // 地理信息初始化失败时
            super.addActionError(getText("info.db.error"));
            return ERROR;
        }

        return SUCCESS;
    }

    /** 新建企业 **/
    public void createEnterpriseView() {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setCharacterEncoding("UTF-8");

        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();
        enterInfo.setParent_id("111");
        enterInfo.setEnterprise_id(UUIDGenerator.getUUID());
        enterInfo.setEnterprise_code(this.enterpriseCode);
        enterInfo.setFull_name(this.enterpriseName);
        enterInfo.setShort_name(this.enterpriseShortName);
        enterInfo.setEnterprise_country(this.countryId);
        enterInfo.setEnterprise_province(this.provinceId);
        enterInfo.setEnterprise_city(this.cityId);
        enterInfo.setMsg_num(this.enterpriseMessageNum);
        enterInfo.setCreater(currentUser.getUserID());
        enterInfo.setIsused("0");

        // 处理结果
        String ret = "";
        
        try {
            service.getObject("EntiManage.add_enterprise", enterInfo);
            // 新增成功
            if ("0".equals(enterInfo.getOut_flag())) {
                // 增加新默认用户
                Map < String, Object > mapdefuser = new HashMap < String, Object >(
                        7);
                mapdefuser.put("in_user_id", UUIDGenerator.getUUID());
                mapdefuser.put("in_user_country", enterInfo
                        .getEnterprise_country());
                mapdefuser.put("in_user_province", enterInfo
                        .getEnterprise_province());
                mapdefuser.put("in_user_city", enterInfo.getEnterprise_city());
                mapdefuser
                        .put("in_enterprise_id", enterInfo.getEnterprise_id());
                mapdefuser.put("out_flag", null);
                mapdefuser.put("out_message", null);

                service.getObject("EntiManage.add_def_user", mapdefuser);
                ret = "success";
            } else {
                log.error("新建企业信息:新建存储过程执行返回失败：" + enterInfo.getOut_flag());
                ret = "error";
            }
        } catch (BusinessException e) {
            log.error("新建企业信息:" + e.getMessage());
            ret = "error";
        } catch (Exception e) {
            log.error("新建企业信息:" + e.getMessage());
            ret = "error";
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_QX_ENTERPRISE_ADD_MID);
            addOperationLog("新建企业信息");
            try {
                response.getWriter().write(ret);
            } catch (IOException ioException) {
            }
        }
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

    public List < EnterprisePopupInfo > getEnterpriseList() {
        return enterpriseList;
    }

    public void setEnterpriseList(List < EnterprisePopupInfo > enterpriseList) {
        this.enterpriseList = enterpriseList;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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

    public String getEnterpriseNameQuery() {
        return enterpriseNameQuery;
    }

    public void setEnterpriseNameQuery(String enterpriseNameQuery) {
        this.enterpriseNameQuery = enterpriseNameQuery;
    }

    public String getEnterpriseCodeQuery() {
        return enterpriseCodeQuery;
    }

    public void setEnterpriseCodeQuery(String enterpriseCodeQuery) {
        this.enterpriseCodeQuery = enterpriseCodeQuery;
    }

    public String getEnterpriseCode() {
        return enterpriseCode;
    }

    public void setEnterpriseCode(String enterpriseCode) {
        this.enterpriseCode = enterpriseCode;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseShortName() {
        return enterpriseShortName;
    }

    public void setEnterpriseShortName(String enterpriseShortName) {
        this.enterpriseShortName = enterpriseShortName;
    }

    public String getEnterpriseMessageNum() {
        return enterpriseMessageNum;
    }

    public void setEnterpriseMessageNum(String enterpriseMessageNum) {
        this.enterpriseMessageNum = enterpriseMessageNum;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

}
