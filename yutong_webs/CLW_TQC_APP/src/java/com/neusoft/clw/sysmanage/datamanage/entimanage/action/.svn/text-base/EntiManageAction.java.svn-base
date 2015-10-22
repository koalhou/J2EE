package com.neusoft.clw.sysmanage.datamanage.entimanage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.MouldId;
import com.neusoft.clw.common.util.OperateLogFormator;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.common.util.tree.TreeHtmlShow;
import com.neusoft.clw.sysmanage.datamanage.drivermanage.domain.DriverInfo;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseDataInfo;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseResInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.zonemanage.domain.ZoneXsInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * 企业管理类
 * @author JinPeng
 */
public class EntiManageAction extends PaginationAction {

    private transient Service service;

    private EnterpriseDataInfo enterpriseDataInfo = new EnterpriseDataInfo();

    private String userenid;

    /** 国家信息 **/
    private Map < String, String > countryInfosMap = new HashMap < String, String >();

    /** 省/直辖市信息 **/
    private Map < String, String > provinceInfosMap = new HashMap < String, String >();

    /** 市/县信息 **/
    private Map < String, String > cityInfosMap = new HashMap < String, String >();

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

    public String getUserenid() {
        return userenid;
    }

    public void setUserenid(String userenid) {
        this.userenid = userenid;
    }

    /**
     * 企业管理页面初始化
     * @return
     */
    public String init() {
        final String browseTitle = getText("enti.browse.title");
        log.info(browseTitle);
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            UserInfo currentUser = getCurrentUser();
            userenid = currentUser.getEntiID();
            map.put("in_enterprise_id", currentUser.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
        } catch (BusinessException e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        } catch (Exception e) {
            addActionError(getText(e.getMessage()));
            log.error(browseTitle, e);
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        // 设置操作描述
        this.addOperationLog(formatLog(browseTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.SELECT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_ENTI_ADD_ID);
        return SUCCESS;
    }

    public String dodel() {
        final String delTitle = getText("enterprise.info.del");
        log.info(delTitle);
        UserInfo currentUser = getCurrentUser();
        HttpServletRequest request = ServletActionContext.getRequest();
        Map < String, Object > map_del = new HashMap < String, Object >(4);
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            map_del.put("in_enterprise_id", request
                    .getParameter("ChooseEnterID_edit"));
            map_del.put("in_vaset_user_id", currentUser.getUserID());
            map_del.put("out_flag", null);
            map_del.put("out_message", null);

            service.delete("EntiManage.del_enterprise", map_del);
            // 展示树
            map.put("in_enterprise_id", currentUser.getOrganizationID());
            map.put("out_flag", null);
            map.put("out_message", null);
            map.put("out_ref", null);
            service.getObject("EntiManage.show_enterprise_tree", map);
            if ("0".equals(map.get("out_flag"))) {
                ArrayList < EnterpriseResInfo > res = (ArrayList < EnterpriseResInfo >) map
                        .get("out_ref");
                tree_script = TreeHtmlShow.getEnterpriseAllClick(res);
            }
            if ("0".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree", currentUser.getOrganizationID());
            } else if ("1".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error1"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("2".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error2"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("3".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error3"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("4".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error4"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("5".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error5"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("6".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error6"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if ("7".equals(map_del.get("out_flag"))) {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error7"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            } else if("8".equals(map_del.get("out_flag"))){
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error8"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            }else if("9".equals(map_del.get("out_flag"))){
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error9"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            }else if("10".equals(map_del.get("out_flag"))){
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error10"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            }else {
                ActionContext.getContext().getSession().put(
                        "ChooseEnterID_tree",
                        request.getParameter("ChooseEnterID_tree"));
                super.addActionError(getText("enterprise.delete.error"));
                log.error("删除企业信息:存储过程执行返回失败：" + map_del.get("out_flag")
                        + "|message=" + map_del.get("out_message"));
                return ERROR;
            }
        } catch (BusinessException e) {
            log.error(delTitle, e);
            super.addActionError(getText("enterprise.delete.error"));
            return ERROR;
        } finally {
            ActionContext.getContext().getSession().put("tree_script",
                    tree_script);
        }
        // EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();
        // enterInfo.setEnterprise_id( request
        // .getParameter("ChooseEnterID_edit"));
        super.addActionMessage(getText("enterprise.delete.success"));
        // 设置操作描述
        // this.addOperationLog(formatLog(delTitle, enterInfo));
        this.addOperationLog(formatLog(delTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.DELETE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_ENTI_DEL_ID);
        return SUCCESS;
    }

    public String gotoadd() {
        HttpServletRequest request = ServletActionContext.getRequest();
        ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                request.getParameter("ChooseEnterID_tree"));
        String enterId = request.getParameter("ChooseEnterID_edit");
        Map < String, Object > map = new HashMap < String, Object >(1);
        map.put("enterId", enterId);
        try {
            enterpriseDataInfo = (EnterpriseDataInfo) service.getObject(
                    "EntiManage.enterInfo", map);
            enterpriseDataInfo.setEnterprise_id(enterId);

        } catch (BusinessException e) {
            return ERROR;
        } catch (Exception e) {
            return ERROR;
        }
        return SUCCESS;
    }

    public String gotoedit() {

        HttpServletRequest request = ServletActionContext.getRequest();
        ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                request.getParameter("ChooseEnterID_tree"));
        String enterId = request.getParameter("ChooseEnterID_edit");
        Map < String, Object > map = new HashMap < String, Object >(1);
        map.put("enterId", enterId);
        try {
            enterpriseDataInfo = (EnterpriseDataInfo) service.getObject(
                    "EntiManage.enterInfo", map);
            // System.out.println(enterpriseDataInfo.getFull_name());
            enterpriseDataInfo.setEnterprise_id(enterId);

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

            return ERROR;
        }

        return SUCCESS;
    }

    public String doadd() {
        final String addTitle = getText("enterprise.info.create");
        log.info(addTitle);
        UserInfo currentUser = getCurrentUser();

        HttpServletRequest request = ServletActionContext.getRequest();
        ActionContext.getContext().getSession().put("ChooseEnterID_tree",
                request.getParameter("ChooseEnterID_tree"));
        EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();
        enterInfo.setParent_id(request.getParameter("PARENT_ID"));
        enterInfo.setEnterprise_id(UUIDGenerator.getUUID());
        enterInfo.setFull_name(request.getParameter("FULL_NAME"));
        enterInfo.setShort_name(request.getParameter("SHORT_NAME"));
        enterInfo.setEnterprise_country(request
                .getParameter("ENTERPRISE_COUNTRY"));
        enterInfo.setEnterprise_province(request
                .getParameter("ENTERPRISE_PROVINCE"));
        enterInfo.setEnterprise_city(request.getParameter("ENTERPRISE_CITY"));
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
        enterInfo.setIsused("0");
        enterInfo.setFax(request.getParameter("FAX"));
        enterInfo.setEn_mould(currentUser.getEn_mould());
        enterInfo.setEn_gateway(currentUser.getEn_gateway());
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {
            service.getObject("EntiManage.add_enterprise", enterInfo);
            if (!"0".equals(enterInfo.getOut_flag())) {
                super.addActionError(getText("enterprise.create.error"));
                log.error("新建企业信息:新建存储过程执行返回失败：" + enterInfo.getOut_flag()
                        + ",父ID:" + enterInfo.getParent_id());
                return ERROR;
            }
            map.put("in_enterprise_id", currentUser.getOrganizationID());
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
        } catch (BusinessException e) {
            log.error(addTitle, e);
            super.addActionError(getText("enterprise.create.error"));
            return ERROR;
        }
        super.addActionMessage(getText("enterprise.create.success"));
        // 设置操作描述
        // this.addOperationLog(formatLog(addTitle, enterInfo));
        this.addOperationLog(formatLog(addTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.INSERT);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_ENTI_ADD_ID);
        return SUCCESS;
    }

    public String doedit() {
        final String editTitle = getText("enterprise.info.alter");
        log.info(editTitle);
        UserInfo currentUser = getCurrentUser();

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
        Map < String, Object > map = new HashMap < String, Object >(4);
        String tree_script = "";
        try {

            service.update("EntiManage.updateenterInfo", enterInfo);

            map.put("in_enterprise_id", currentUser.getOrganizationID());
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
        } catch (BusinessException e) {
            log.error(editTitle, e);
            super.addActionError(getText("enterprise.update.error"));
            return ERROR;
        }
        super.addActionMessage(getText("enterprise.update.success"));
        // 设置操作描述
        // this.addOperationLog(formatLog(editTitle, enterInfo));
        this.addOperationLog(formatLog(editTitle, null));
        // 设置操作类型
        this.setOperationType(Constants.UPDATE);
        // 设置所属应用系统
        this.setApplyId(Constants.XC_P_CODE);
        // 设置所属模块
        this.setModuleId(MouldId.XCP_ENTI_UPDATE_ID);
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
        }
    }

    /**
     * 格式化日志信息
     * @param desc
     * @param Object
     * @return
     */
    protected String formatLog(String desc, EnterpriseDataInfo entiObj) {
        StringBuffer sb = new StringBuffer();
        if (null != desc) {
            sb.append(desc);
        }
        if (null != entiObj) {
            if (null != entiObj.getEnterprise_id()) {
                OperateLogFormator.format(sb, "enid", entiObj
                        .getEnterprise_id());
            }
        }
        return sb.toString();
    }

    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
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

    public void setEnterpriseDataInfo(EnterpriseDataInfo enterpriseDataInfo) {
        this.enterpriseDataInfo = enterpriseDataInfo;
    }

    public EnterpriseDataInfo getEnterpriseDataInfo() {
        return enterpriseDataInfo;
    }
}
