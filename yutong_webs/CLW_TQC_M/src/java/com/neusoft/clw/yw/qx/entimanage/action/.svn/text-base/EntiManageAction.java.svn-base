package com.neusoft.clw.yw.qx.entimanage.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
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
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.yw.qx.entimanage.ds.EnterpriseDataInfo;
import com.neusoft.clw.yw.qx.entimanage.ds.EnterpriseResInfo;
import com.neusoft.clw.yw.qx.security.ds.UserInfo;
import com.neusoft.clw.yw.xj.report.ds.OperationsInfo;
import com.neusoft.clw.yw.xs.baseinfo.ds.BaseInfo;
import com.neusoft.clw.yw.xs.zonemanage.ds.ZoneXsInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 企业管理类
 * @author JinPeng
 */
public class EntiManageAction extends PaginationAction {

    private String message;

    private File file;

    private String fileFileName;

    private transient Service service;

    private EnterpriseDataInfo enterpriseDataInfo = new EnterpriseDataInfo();

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

    /** 企业类型(取自参数配置) **/
    private Map < String, String > enterprise_type_cfgMap = new HashMap < String, String >();

    /** 企业级别(取自参数配置) **/
    private Map < String, String > enterprise_leve_cfgMap = new HashMap < String, String >();

    /** 企业性质(取自参数配置) **/
    private Map < String, String > enterprise_kind_cfgMap = new HashMap < String, String >();

    /** 币种(取自参数配置) **/
    private Map < String, String > money_kind_cfgMap = new HashMap < String, String >();

    /** 语言(取自参数配置) **/
    private Map < String, String > language_cfgMap = new HashMap < String, String >();

    private List < EnterpriseDataInfo > pageList = new ArrayList < EnterpriseDataInfo >();

    private String enterprise_code_serch;

    private String full_name_serch;

    private String enterprise_country_serch;

    private String enterprise_province_serch;

    private String enterprise_city_serch;

    private String ENTERPRISE_COUNTRY;

    private String ENTERPRISE_PROVINCE;

    private String ENTERPRISE_CITY;
    
    /** 模式编码 **/
    private Map < String, String > enterprise_model_map = new HashMap < String, String >();
    
    /** 短信网关编码 **/
    private Map < String, String > enterprise_getway_map = new HashMap < String, String >();    

    /**
     * 企业管理页面初始化
     * @return
     */
    public String init() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("enterprise.location"));

        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map.put("in_enterprise_id", "111");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            } else {
                log.error("企业信息初始化:存储过程返回异常：" + map.get("out_flag"));
                return ERROR;
            }
            if (null != message) {
                super.addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            log.error("企业信息初始化:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("企业信息初始化:" + e.getMessage());
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    public String loadDate() {
        ActionContext.getContext().getSession().put(Constants.CURRENT_LOCATION,
                getText("enterprise.location"));

        try {
            /*
             * 回显省市信息部分
             */
            List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
            Map < String, Object > mapPar = new HashMap < String, Object >(1);

            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市
            if (enterprise_country_serch != null
                    && !"".equals(enterprise_country_serch)) {
                mapPar.put("zone_parent_id", enterprise_country_serch);
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (enterprise_province_serch != null
                    && !"".equals(enterprise_province_serch)) {
                mapPar.put("zone_parent_id", enterprise_province_serch);
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }

            Map < String, Object > map = new HashMap < String, Object >(2);
            map.put("enterprise_code", SqlStringUtil
                    .getNull(enterprise_code_serch));
            map.put("full_name", SqlStringUtil.getNull(full_name_serch));
            map.put("enterprise_country", SqlStringUtil
                    .getNull(enterprise_country_serch));
            map.put("enterprise_province", SqlStringUtil
                    .getNull(enterprise_province_serch));
            map.put("enterprise_city", SqlStringUtil
                    .getNull(enterprise_city_serch));

            int totalSize = service.getCount("EntiManage.getEnterprise_Count",
                    map);
            if (totalSize == 0) {
                setMessage("common.no.data");
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < EnterpriseDataInfo >) service.getObjectsByPage(
                    "EntiManage.getEnterprise_list", map, pageObj
                            .getStartOfPage(), pageSize));
            if (null != message) {
                super.addActionMessage(getText(message));
            }
        } catch (BusinessException e) {
            log.error("查询企业信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            log.error("查询企业信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.SELECT,
                    ModuleId.CLW_M_QX_ENTERPRISE_QUERY_MID);
            addOperationLog("查询企业信息");
        }
        return SUCCESS;
    }

    public String dodel() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);
        HttpServletRequest request = ServletActionContext.getRequest();
        Map < String, Object > map_del = new HashMap < String, Object >(4);
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map_del.put("in_enterprise_id", request
                    .getParameter("ENTERPRISE_ID"));
            map_del.put("in_vaset_user_id", currentUser.getUserID());
            map_del.put("out_flag", null);
            map_del.put("out_message", null);
            service.delete("EntiManage.del_enterprise", map_del);

            map.put("in_enterprise_id", "111");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }

            /*
             * 回显省市信息部分
             */
            List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
            Map < String, Object > mapPar = new HashMap < String, Object >(1);

            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市
            if (enterprise_country_serch != null
                    && !"".equals(enterprise_country_serch)) {
                mapPar.put("zone_parent_id", enterprise_country_serch);
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (enterprise_province_serch != null
                    && !"".equals(enterprise_province_serch)) {
                mapPar.put("zone_parent_id", enterprise_province_serch);
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }

            Map < String, Object > map2 = new HashMap < String, Object >(2);
            map2.put("enterprise_code", SqlStringUtil
                    .getNull(enterprise_code_serch));
            map2.put("full_name", SqlStringUtil.getNull(full_name_serch));
            map2.put("enterprise_country", SqlStringUtil
                    .getNull(enterprise_country_serch));
            map2.put("enterprise_province", SqlStringUtil
                    .getNull(enterprise_province_serch));
            map2.put("enterprise_city", SqlStringUtil
                    .getNull(enterprise_city_serch));

            int totalSize = service.getCount("EntiManage.getEnterprise_Count",
                    map2);
            if (totalSize == 0) {
                setMessage("common.no.data");
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < EnterpriseDataInfo >) service.getObjectsByPage(
                    "EntiManage.getEnterprise_list", map2, pageObj
                            .getStartOfPage(), pageSize));

            if ("0".equals(map_del.get("out_flag"))) {
                setMessage("enterprise.delete.success");

            } else if ("1".equals(map_del.get("out_flag"))) {
                setMessage("enterprise.delete.error1");
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("2".equals(map_del.get("out_flag"))) {
                setMessage("enterprise.delete.error2");
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("3".equals(map_del.get("out_flag"))) {
                setMessage("enterprise.delete.error3");
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("4".equals(map_del.get("out_flag"))) {
                setMessage("enterprise.delete.error4");
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("5".equals(map_del.get("out_flag"))) {
                setMessage("enterprise.delete.error5");
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("6".equals(map_del.get("out_flag"))) {
                setMessage("enterprise.delete.error6");
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else {
                setMessage("enterprise.delete.error");
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            }
        } catch (BusinessException e) {
            setMessage("enterprise.delete.error");
            log.error("删除企业信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("enterprise.delete.error");
            log.error("删除企业信息:" + e.getMessage());
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
            setOperationType(Constants.DELETE,
                    ModuleId.CLW_M_QX_ENTERPRISE_DELETE_MID);
            addOperationLog("删除企业信息");
        }

        return SUCCESS;
    }

    public String gotoadd() {
        HttpServletRequest request = ServletActionContext.getRequest();

        // System.out.println(request.getParameter("ChooseEnterID_tree"));
        /*
         * sql
         */
        ActionContext.getContext().getSession().put("ChooseEnterID_tree", "");
        List < BaseInfo > list = new ArrayList < BaseInfo >();
        Map < String, Object > map = new HashMap < String, Object >(4);
        map.put("in_enterprise_id", "111");
        map.put("out_flag", null);
        map.put("out_message", null);
        map.put("out_ref", null);
        String tree_script = "";
        
        //企业模式
        if (enterprise_model_map != null && enterprise_model_map.size() == 0) {
            enterprise_model_map = Constants.ENTI_MODEL_MAP;
        }
        //短信网关
        if (enterprise_getway_map != null && enterprise_getway_map.size() == 0) {
            enterprise_getway_map = Constants.ENTI_GETWAY_MAP;
        }
        
        try {

            list = service.getObjects("EntiManage.getEnterprise_type", null);
            for (BaseInfo baseinfo : list) {
                enterprise_type_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }

            list = service.getObjects("EntiManage.getEnterprise_leve", null);
            for (BaseInfo baseinfo : list) {
                enterprise_leve_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("EntiManage.getEnterprise_kind", null);
            for (BaseInfo baseinfo : list) {
                enterprise_kind_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("EntiManage.getMoney_kind", null);
            for (BaseInfo baseinfo : list) {
                money_kind_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list = service.getObjects("EntiManage.getLanguage_kind", null);
            for (BaseInfo baseinfo : list) {
                language_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }

            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            } else {
                log.error("企业信息初始化:存储过程返回异常：" + map.get("out_flag"));
                return ERROR;
            }

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        return SUCCESS;
    }

    public String gotoedit() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String enterId = request.getParameter("ChooseEnterID_edit");
        Map < String, Object > mapedit = new HashMap < String, Object >(1);
        mapedit.put("enterId", enterId);

        ActionContext.getContext().getSession().put("ChooseEnterID_tree", "");

        Map < String, Object > map = new HashMap < String, Object >(4);
        map.put("in_enterprise_id", "111");
        map.put("out_flag", null);
        map.put("out_message", null);
        map.put("out_ref", null);
        String tree_script = "";

        Map < String, Object > mapChoose = new HashMap < String, Object >(4);
        mapChoose.put("in_enterprise_id", enterId);
        mapChoose.put("out_result", "");
        mapChoose.put("out_flag", null);
        mapChoose.put("out_message", null);
        
        //企业模式
        if (enterprise_model_map != null && enterprise_model_map.size() == 0) {
            enterprise_model_map = Constants.ENTI_MODEL_MAP;
        }
        //短信网关
        if (enterprise_getway_map != null && enterprise_getway_map.size() == 0) {
            enterprise_getway_map = Constants.ENTI_GETWAY_MAP;
        }
        try {
            enterpriseDataInfo = (EnterpriseDataInfo) service.getObject(
                    "EntiManage.enterInfo", mapedit);
            enterpriseDataInfo.setEnterprise_id(enterId);

            service.getObject("EntiManage.show_edit_choose", mapChoose);

            if ("0".equals(mapChoose.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree", mapChoose.get("out_result"));
            }

            /*
             * 回显省市信息部分
             */
            List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
            Map < String, Object > mapPar = new HashMap < String, Object >(1);

            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市
            if (enterpriseDataInfo.getEnterprise_country() != null
                    && !"".equals(enterpriseDataInfo.getEnterprise_country())) {
                mapPar.put("zone_parent_id", enterpriseDataInfo
                        .getEnterprise_country());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (enterpriseDataInfo.getEnterprise_province() != null
                    && !"".equals(enterpriseDataInfo.getEnterprise_province())) {
                mapPar.put("zone_parent_id", enterpriseDataInfo
                        .getEnterprise_province());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            List < BaseInfo > list2 = new ArrayList < BaseInfo >();
            list2 = service.getObjects("EntiManage.getEnterprise_type", null);
            for (BaseInfo baseinfo : list2) {
                enterprise_type_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }

            list2 = service.getObjects("EntiManage.getEnterprise_leve", null);
            for (BaseInfo baseinfo : list2) {
                enterprise_leve_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list2 = service.getObjects("EntiManage.getEnterprise_kind", null);
            for (BaseInfo baseinfo : list2) {
                enterprise_kind_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list2 = service.getObjects("EntiManage.getMoney_kind", null);
            for (BaseInfo baseinfo : list2) {
                money_kind_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            list2 = service.getObjects("EntiManage.getLanguage_kind", null);
            for (BaseInfo baseinfo : list2) {
                language_cfgMap.put(baseinfo.getCodeId(), baseinfo
                        .getCodeName());
            }
            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            } else {
                log.error("企业信息初始化:存储过程返回异常：" + map.get("out_flag"));
                return ERROR;
            }
        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }

        return SUCCESS;
    }

    public String doadd() {
        java.io.InputStream is = null;
        java.io.OutputStream os = null;
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        HttpServletRequest request = ServletActionContext.getRequest();
        ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                request.getParameter("ChooseEnterID_tree"));

        EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();
        enterInfo.setParent_id(request.getParameter("PARENT_ID"));
        enterInfo.setEnterprise_id(UUIDGenerator.getUUID());
        enterInfo.setEnterprise_code(request.getParameter("ENTERPRISE_CODE"));
        enterInfo.setFull_name(request.getParameter("FULL_NAME"));
        enterInfo.setShort_name(request.getParameter("SHORT_NAME"));
        enterInfo.setEnterprise_country(request
                .getParameter("ENTERPRISE_COUNTRY"));
        enterInfo.setEnterprise_province(request
                .getParameter("ENTERPRISE_PROVINCE"));
        enterInfo.setEnterprise_city(request.getParameter("ENTERPRISE_CITY"));
        // enterInfo.setEnterprise_type(request.getParameter("ENTERPRISE_TYPE"));
        enterInfo.setEnterprise_desc(request.getParameter("ENTERPRISE_DESC"));
        enterInfo.setAddress(request.getParameter("ADDRESS"));
        enterInfo.setEmail(request.getParameter("EMAIL"));
        enterInfo.setPostcode(request.getParameter("POSTCODE"));
        enterInfo.setContact_p(request.getParameter("CONTACT_P"));
        enterInfo.setContact_tel(request.getParameter("CONTACT_TEL"));
        enterInfo.setMsg_num(request.getParameter("MSG_NUM"));
        enterInfo.setCreater(currentUser.getUserID());
        enterInfo.setNetaddress(request.getParameter("NETADDRESS"));
        enterInfo.setEnterprise_type_cfg(request
                .getParameter("ENTERPRISE_TYPE_CFG"));
        enterInfo.setEnterprise_leve_cfg(request
                .getParameter("ENTERPRISE_LEVE_CFG"));
        enterInfo.setEnterprise_kind_cfg(request
                .getParameter("ENTERPRISE_KIND_CFG"));
        enterInfo.setMoney_kind_cfg(request.getParameter("MONEY_KIND_CFG"));
        enterInfo.setLanguage_cfg(request.getParameter("LANGUAGE_CFG"));
        enterInfo.setIsused(request.getParameter("ISUSED"));
        enterInfo.setFax(request.getParameter("FAX"));
        enterInfo.setEnterprise_model(request.getParameter("ENTERPRISE_MODEL"));
        enterInfo.setEnterprise_getway(request.getParameter("ENTERPRISE_GETWAY"));

        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            if (file != null) {
                if (file.length() / 1024 > 512) {
                    setMessage("enterprise.file.error.length");
                    log.error("新建企业信息:LOGO图片过大超过512kb");
                    return ERROR;
                }
                if (!imgeCheck(file)) {
                    setMessage("enterprise.file.error.heightwidth");
                    log.error("新建企业信息:图片长宽不符合要求,请选择其它图片");
                    return ERROR;
                }
                is = new java.io.FileInputStream(file);
                // String realPath = ServletActionContext.getServletContext()
                // .getRealPath("/");

                String logoPath = "/opt/m2mfile/ftp/xclogo/"
                        + enterInfo.getEnterprise_id() + ".jpg";
                os = new java.io.FileOutputStream(logoPath);
                byte buffer[] = new byte[8192];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    os.write(buffer, 0, count);
                }
                log.info("logoPath:" + logoPath);
                enterInfo.setImg_path(logoPath);

            }

            service.getObject("EntiManage.add_enterprise", enterInfo);

            map.put("in_enterprise_id", "111");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
                ActionContext.getContext().getSession().put("tree_script",
                        tree_script);
            } else {
                // setMessage(getText("enterprise.create.error"));
                log.error("新建企业信息:查询存储过程执行返回失败：" + map.get("out_flag"));
                // return ERROR;
            }
            /*
             * 回显省市信息部分
             */
            List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
            Map < String, Object > mapPar = new HashMap < String, Object >(1);

            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市
            if (enterprise_country_serch != null
                    && !"".equals(enterprise_country_serch)) {
                mapPar.put("zone_parent_id", enterprise_country_serch);
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (enterprise_province_serch != null
                    && !"".equals(enterprise_province_serch)) {
                mapPar.put("zone_parent_id", enterprise_province_serch);
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }

            Map < String, Object > map2 = new HashMap < String, Object >(2);
            map2.put("enterprise_code", SqlStringUtil
                    .getNull(enterprise_code_serch));
            map2.put("full_name", SqlStringUtil.getNull(full_name_serch));
            map2.put("enterprise_country", SqlStringUtil
                    .getNull(enterprise_country_serch));
            map2.put("enterprise_province", SqlStringUtil
                    .getNull(enterprise_province_serch));
            map2.put("enterprise_city", SqlStringUtil
                    .getNull(enterprise_city_serch));

            int totalSize = service.getCount("EntiManage.getEnterprise_Count",
                    map2);
            if (totalSize == 0) {
                setMessage("common.no.data");
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < EnterpriseDataInfo >) service.getObjectsByPage(
                    "EntiManage.getEnterprise_list", map2, pageObj
                            .getStartOfPage(), pageSize));
            // 新增成功
            if ("0".equals(enterInfo.getOut_flag())) {
                // 增加新默认用户
                if ("111".equals(request.getParameter("PARENT_ID"))) {
                    Map < String, Object > mapdefuser = new HashMap < String, Object >(
                            7);
                    mapdefuser.put("in_user_id", UUIDGenerator.getUUID());
                    mapdefuser.put("in_user_country", enterInfo
                            .getEnterprise_country());
                    mapdefuser.put("in_user_province", enterInfo
                            .getEnterprise_province());
                    mapdefuser.put("in_user_city", enterInfo
                            .getEnterprise_city());
                    mapdefuser.put("in_enterprise_id", enterInfo
                            .getEnterprise_id());
                    mapdefuser.put("out_flag", null);
                    mapdefuser.put("out_message", null);

                    service.getObject("EntiManage.add_def_user", mapdefuser);
                    if ("0".equals(mapdefuser.get("out_flag"))) {
                        setMessage("enterprise.create.success1");
                    } else {
                        setMessage("enterprise.create.success2");
                    }
                } else {
                    setMessage("enterprise.create.success");
                }
            } else {
                setMessage("enterprise.create.error");
                log.error("新建企业信息:新建存储过程执行返回失败：" + enterInfo.getOut_flag());
                return ERROR;
            }

        } catch (BusinessException e) {
            setMessage("enterprise.create.error");
            log.error("新建企业信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("enterprise.create.error");
            log.error("新建企业信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.INSERT,
                    ModuleId.CLW_M_QX_ENTERPRISE_ADD_MID);
            addOperationLog("新建企业信息");
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }

        return SUCCESS;
    }

    public String doedit() {
        UserInfo currentUser = (UserInfo) ActionContext.getContext()
                .getSession().get(Constants.USER_SESSION_KEY);

        HttpServletRequest request = ServletActionContext.getRequest();
        ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                request.getParameter("ChooseEnterID_tree"));

        EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();
        enterInfo.setEnterprise_id(request.getParameter("ENTERPRISE_ID"));
        // enterInfo.setEnterprise_code(request.getParameter("ENTERPRISE_CODE"));
        enterInfo.setFull_name(request.getParameter("FULL_NAME"));
        enterInfo.setShort_name(request.getParameter("SHORT_NAME"));
        enterInfo.setEnterprise_country(enterpriseDataInfo
                .getEnterprise_country());
        enterInfo.setEnterprise_province(enterpriseDataInfo
                .getEnterprise_province());
        enterInfo.setEnterprise_city(enterpriseDataInfo.getEnterprise_city());
        // enterInfo.setEnterprise_type(request.getParameter("ENTERPRISE_TYPE"));
        enterInfo.setEnterprise_desc(request.getParameter("ENTERPRISE_DESC"));
        enterInfo.setAddress(request.getParameter("ADDRESS"));
        enterInfo.setEmail(request.getParameter("EMAIL"));
        enterInfo.setPostcode(request.getParameter("POSTCODE"));
        enterInfo.setContact_p(request.getParameter("CONTACT_P"));
        enterInfo.setContact_tel(request.getParameter("CONTACT_TEL"));
        enterInfo.setMsg_num(request.getParameter("MSG_NUM"));
        enterInfo.setModifier(currentUser.getUserID());
        enterInfo.setNetaddress(request.getParameter("NETADDRESS"));

        enterInfo.setEnterprise_type_cfg(enterpriseDataInfo
                .getEnterprise_type_cfg());
        enterInfo.setEnterprise_leve_cfg(enterpriseDataInfo
                .getEnterprise_leve_cfg());
        enterInfo.setEnterprise_kind_cfg(enterpriseDataInfo
                .getEnterprise_kind_cfg());
        enterInfo.setMoney_kind_cfg(enterpriseDataInfo.getMoney_kind_cfg());
        enterInfo.setLanguage_cfg(enterpriseDataInfo.getLanguage_cfg());
        enterInfo.setIsused(request.getParameter("ISUSED"));
        enterInfo.setFax(request.getParameter("FAX"));
        enterInfo.setEnterprise_model(enterpriseDataInfo.getEnterprise_model());
        enterInfo.setEnterprise_getway(enterpriseDataInfo.getEnterprise_getway());

        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {

            service.update("EntiManage.updateenterInfo", enterInfo);

            map.put("in_enterprise_id", "111");
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
                ActionContext.getContext().getSession().put("tree_script",
                        tree_script);
            }

            Map < String, Object > map2 = new HashMap < String, Object >(2);
            map2.put("enterprise_code", SqlStringUtil
                    .getNull(enterprise_code_serch));
            map2.put("full_name", SqlStringUtil.getNull(full_name_serch));
            map2.put("enterprise_country", SqlStringUtil
                    .getNull(enterprise_country_serch));
            map2.put("enterprise_province", SqlStringUtil
                    .getNull(enterprise_province_serch));
            map2.put("enterprise_city", SqlStringUtil
                    .getNull(enterprise_city_serch));

            int totalSize = service.getCount("EntiManage.getEnterprise_Count",
                    map2);
            if (totalSize == 0) {
                setMessage("common.no.data");
            }
            Page pageObj = new Page(page, totalSize, pageSize, url, param);
            this.pageBar = PageHelper.getPageBar(pageObj);
            setPageList((List < EnterpriseDataInfo >) service.getObjectsByPage(
                    "EntiManage.getEnterprise_list", map2, pageObj
                            .getStartOfPage(), pageSize));

            /*
             * 回显省市信息部分
             */
            List < ZoneXsInfo > list = new ArrayList < ZoneXsInfo >();
            Map < String, Object > mapPar = new HashMap < String, Object >(1);

            // 国家
            mapPar.put("zone_parent_id", null);
            list = service.getObjects("ZoneManage.getSelectList", mapPar);
            for (ZoneXsInfo zoneXsInfo : list) {
                countryInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                        .getZone_name());
            }

            // 省/直辖市
            if (enterpriseDataInfo.getEnterprise_country() != null
                    && !"".equals(enterpriseDataInfo.getEnterprise_country())) {
                mapPar.put("zone_parent_id", enterpriseDataInfo
                        .getEnterprise_country());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    provinceInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }
            // 市/县
            if (enterpriseDataInfo.getEnterprise_province() != null
                    && !"".equals(enterpriseDataInfo.getEnterprise_province())) {
                mapPar.put("zone_parent_id", enterpriseDataInfo
                        .getEnterprise_province());
                list = service.getObjects("ZoneManage.getSelectList", mapPar);
                for (ZoneXsInfo zoneXsInfo : list) {
                    cityInfosMap.put(zoneXsInfo.getZone_id(), zoneXsInfo
                            .getZone_name());
                }
            }

        } catch (BusinessException e) {
            setMessage("enterprise.update.error");
            log.error("修改企业信息:" + e.getMessage());
            return ERROR;
        } catch (Exception e) {
            setMessage("enterprise.update.error");
            log.error("修改企业信息:" + e.getMessage());
            return ERROR;
        } finally {
            setOperationType(Constants.UPDATE,
                    ModuleId.CLW_M_QX_ENTERPRISE_MODIFY_MID);
            addOperationLog("修改企业信息");
        }
        setMessage("enterprise.update.success");
        return SUCCESS;
    }

    public void ajax_entiinfo() {

        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        String enterId = request.getParameter("enterId");
        Map < String, Object > map = new HashMap < String, Object >(1);
        map.put("enterId", enterId);
        try {
            response.setContentType("text/html;charset=utf-8");
            PrintWriter outs = response.getWriter();
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            EnterpriseDataInfo enterInfo = (EnterpriseDataInfo) service
                    .getObject("EntiManage.enterInfo", map);
            StringBuffer tempb = new StringBuffer("msg:");
            tempb
                    .append(
                            SqlStringUtil.getNoNull(enterInfo
                                    .getEnterprise_code()))
                    .append("|")
                    .append(SqlStringUtil.getNoNull(enterInfo.getFull_name()))
                    .append("|")
                    .append(SqlStringUtil.getNoNull(enterInfo.getShort_name()))
                    .append("|")
                    .append(
                            SqlStringUtil.getNoNull(enterInfo
                                    .getEnterprise_country()))
                    .append("|")
                    .append(
                            SqlStringUtil.getNoNull(enterInfo
                                    .getEnterprise_province()))
                    .append("|")
                    .append(
                            SqlStringUtil.getNoNull(enterInfo
                                    .getEnterprise_city()))
                    .append("|")
                    .append(
                            SqlStringUtil.getNoNull(enterInfo
                                    .getEnterprise_type()))
                    .append("|")
                    .append(SqlStringUtil.getNoNull(enterInfo.getAddress()))
                    .append("|")
                    .append(SqlStringUtil.getNoNull(enterInfo.getEmail()))
                    .append("|")
                    .append(SqlStringUtil.getNoNull(enterInfo.getPostcode()))
                    .append("|")
                    .append(SqlStringUtil.getNoNull(enterInfo.getContact_p()))
                    .append("|")
                    .append(SqlStringUtil.getNoNull(enterInfo.getContact_tel()))
                    .append("|").append(
                            SqlStringUtil.getNoNull(enterInfo.getMsg_num()))
                    .append("|").append(
                            SqlStringUtil.getNoNull(enterInfo
                                    .getEnterprise_desc()));
            outs.print(tempb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BusinessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean imgeCheck(File img) {
        BufferedImage image;
        try {
            image = ImageIO.read(img);

            int srcH = image.getHeight(null);
            int srcW = image.getWidth(null);

            if (srcH > 98) {
                return false;
            }
            if (srcW > 335) {
                return false;
            }

        } catch (IOException e) {
            log.error(e.getMessage());
            addActionError(e.getMessage());
            return false;
        }
        return true;
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

    public String getENTERPRISE_COUNTRY() {
        return ENTERPRISE_COUNTRY;
    }

    public void setENTERPRISE_COUNTRY(String enterprise_country) {
        ENTERPRISE_COUNTRY = enterprise_country;
    }

    public String getENTERPRISE_PROVINCE() {
        return ENTERPRISE_PROVINCE;
    }

    public void setENTERPRISE_PROVINCE(String enterprise_province) {
        ENTERPRISE_PROVINCE = enterprise_province;
    }

    public String getENTERPRISE_CITY() {
        return ENTERPRISE_CITY;
    }

    public void setENTERPRISE_CITY(String enterprise_city) {
        ENTERPRISE_CITY = enterprise_city;
    }

    public List < EnterpriseDataInfo > getPageList() {
        return pageList;
    }

    public void setPageList(List < EnterpriseDataInfo > pageList) {
        this.pageList = pageList;
    }

    public void setEnterpriseDataInfo(EnterpriseDataInfo enterpriseDataInfo) {
        this.enterpriseDataInfo = enterpriseDataInfo;
    }

    public EnterpriseDataInfo getEnterpriseDataInfo() {
        return enterpriseDataInfo;
    }

    public void setCountryInfosMap(Map < String, String > countryInfosMap) {
        this.countryInfosMap = countryInfosMap;
    }

    public Map < String, String > getCountryInfosMap() {
        return countryInfosMap;
    }

    public void setProvinceInfosMap(Map < String, String > provinceInfosMap) {
        this.provinceInfosMap = provinceInfosMap;
    }

    public Map < String, String > getProvinceInfosMap() {
        return provinceInfosMap;
    }

    public void setCityInfosMap(Map < String, String > cityInfosMap) {
        this.cityInfosMap = cityInfosMap;
    }

    public Map < String, String > getCityInfosMap() {
        return cityInfosMap;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setEnterprise_type_cfgMap(
            Map < String, String > enterprise_type_cfgMap) {
        this.enterprise_type_cfgMap = enterprise_type_cfgMap;
    }

    public Map < String, String > getEnterprise_type_cfgMap() {
        return enterprise_type_cfgMap;
    }

    public void setEnterprise_leve_cfgMap(
            Map < String, String > enterprise_leve_cfgMap) {
        this.enterprise_leve_cfgMap = enterprise_leve_cfgMap;
    }

    public Map < String, String > getEnterprise_leve_cfgMap() {
        return enterprise_leve_cfgMap;
    }

    public void setEnterprise_kind_cfgMap(
            Map < String, String > enterprise_kind_cfgMap) {
        this.enterprise_kind_cfgMap = enterprise_kind_cfgMap;
    }

    public Map < String, String > getEnterprise_kind_cfgMap() {
        return enterprise_kind_cfgMap;
    }

    public void setMoney_kind_cfgMap(Map < String, String > money_kind_cfgMap) {
        this.money_kind_cfgMap = money_kind_cfgMap;
    }

    public Map < String, String > getMoney_kind_cfgMap() {
        return money_kind_cfgMap;
    }

    public void setLanguage_cfgMap(Map < String, String > language_cfgMap) {
        this.language_cfgMap = language_cfgMap;
    }

    public Map < String, String > getLanguage_cfgMap() {
        return language_cfgMap;
    }

    public String getEnterprise_code_serch() {
        return enterprise_code_serch;
    }

    public void setEnterprise_code_serch(String enterprise_code_serch) {
        this.enterprise_code_serch = enterprise_code_serch;
    }

    public String getFull_name_serch() {
        return full_name_serch;
    }

    public void setFull_name_serch(String full_name_serch) {
        this.full_name_serch = full_name_serch;
    }

    public String getEnterprise_country_serch() {
        return enterprise_country_serch;
    }

    public void setEnterprise_country_serch(String enterprise_country_serch) {
        this.enterprise_country_serch = enterprise_country_serch;
    }

    public String getEnterprise_city_serch() {
        return enterprise_city_serch;
    }

    public void setEnterprise_city_serch(String enterprise_city_serch) {
        this.enterprise_city_serch = enterprise_city_serch;
    }

    public String getEnterprise_province_serch() {
        return enterprise_province_serch;
    }

    public void setEnterprise_province_serch(String enterprise_province_serch) {
        this.enterprise_province_serch = enterprise_province_serch;
    }

    public Map<String, String> getEnterprise_model_map() {
        return enterprise_model_map;
    }

    public void setEnterprise_model_map(Map<String, String> enterprise_model_map) {
        this.enterprise_model_map = enterprise_model_map;
    }

    public Map<String, String> getEnterprise_getway_map() {
        return enterprise_getway_map;
    }

    public void setEnterprise_getway_map(Map<String, String> enterprise_getway_map) {
        this.enterprise_getway_map = enterprise_getway_map;
    }

}
