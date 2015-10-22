package com.neusoft.clw.yw.qx.entimanage.action;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.yw.qx.entimanage.ds.EnterpriseDataInfo;

public class EntiManageDwr extends BaseAction {
    private transient Service service;

    public EnterpriseDataInfo getEnterpriseDataInfoToShow(String enterId) {
        Map < String, Object > map = new HashMap < String, Object >(1);
        map.put("enterId", enterId);
        EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();

        try {
            enterInfo = (EnterpriseDataInfo) service.getObject(
                    "EntiManage.enterInfoForDwr", map);

            enterInfo.setEnterprise_country((String) service.getObject(
                    "ZoneManage.getZoneName", SqlStringUtil.getNull(enterInfo
                            .getEnterprise_country())));

            enterInfo.setEnterprise_province((String) service.getObject(
                    "ZoneManage.getZoneName", SqlStringUtil.getNull(enterInfo
                            .getEnterprise_province())));

            enterInfo.setEnterprise_city((String) service.getObject(
                    "ZoneManage.getZoneName", SqlStringUtil.getNull(enterInfo
                            .getEnterprise_city())));

        } catch (BusinessException e) {
            log.error("企业详细信息查询DWR异常发生，异常原因：" + e.getMessage());
        } catch (Exception e) {
            log.error("企业详细信息查询DWR异常发生，异常原因：" + e.getMessage());
        }
        enterInfo.setEnterprise_code(SqlStringUtil.getNoNull(enterInfo
                .getEnterprise_code()));
        enterInfo.setEnterprise_desc(SqlStringUtil.getNoNull(enterInfo
                .getEnterprise_desc()));
        enterInfo.setAddress(SqlStringUtil.getNoNull(enterInfo.getAddress()));
        enterInfo.setEmail(SqlStringUtil.getNoNull(enterInfo.getEmail()));
        enterInfo.setPostcode(SqlStringUtil.getNoNull(enterInfo.getPostcode()));
        enterInfo.setContact_p(SqlStringUtil
                .getNoNull(enterInfo.getContact_p()));
        enterInfo.setContact_tel(SqlStringUtil.getNoNull(enterInfo
                .getContact_tel()));
        enterInfo.setMsg_num(SqlStringUtil.getNoNull(enterInfo.getMsg_num()));
        enterInfo.setParent_id(SqlStringUtil
                .getNoNull(enterInfo.getParent_id()));
        enterInfo.setNetaddress(SqlStringUtil.getNoNull(enterInfo
                .getNetaddress()));
        enterInfo.setEnterprise_type_cfg(SqlStringUtil.getNoNull(enterInfo
                .getEnterprise_type_cfg()));
        enterInfo.setEnterprise_leve_cfg(SqlStringUtil.getNoNull(enterInfo
                .getEnterprise_leve_cfg()));
        enterInfo.setEnterprise_kind_cfg(SqlStringUtil.getNoNull(enterInfo
                .getEnterprise_kind_cfg()));
        enterInfo.setMoney_kind_cfg(SqlStringUtil.getNoNull(enterInfo
                .getMoney_kind_cfg()));
        enterInfo.setLanguage_cfg(SqlStringUtil.getNoNull(enterInfo
                .getLanguage_cfg()));
        enterInfo.setFax(SqlStringUtil.getNoNull(enterInfo.getFax()));

        return enterInfo;
    }

    public int getEnterpriseCarsNumsToShow(String enterId) {
        int i = 0;

        try {
            i = service.getCount("EntiManage.CarsNums", enterId);
        } catch (BusinessException e) {
            log.error("分配车辆数查询DWR异常发生，异常原因：" + e.getMessage());
        } catch (Exception e) {
            log.error("分配车辆数查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return i;
    }

    public boolean checkCODE(String code) {
        try {
            int num = service.getCount("EntiManage.getCodeCount", code);
            if (num == 0) {
                return true;
            } else {
                return false;
            }

        } catch (BusinessException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

    public EnterpriseDataInfo getEnterpriseModleByID(String enterId) {
        Map < String, Object > map = new HashMap < String, Object >(1);
        map.put("enterId", enterId);
        EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();

        try {
            enterInfo = (EnterpriseDataInfo) service.getObject(
                    "EntiManage.enterModelForDwr", map);

        } catch (BusinessException e) {
            log.error("企业详细信息-企业模式查询DWR异常发生，异常原因：" + e.getMessage());
        } catch (Exception e) {
            log.error("企业详细信息-企业模式查询DWR异常发生，异常原因：" + e.getMessage());
        }

        return enterInfo;
    }
}
