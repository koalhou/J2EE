package com.neusoft.clw.sysmanage.datamanage.entimanage.action;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.SqlStringUtil;
import com.neusoft.clw.sysmanage.datamanage.entimanage.domain.EnterpriseDataInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

public class EntiManageDwr extends BaseAction {
    private transient Service service;

    public EnterpriseDataInfo getEnterpriseDataInfoToShow(String enterId) {
        Map < String, Object > map = new HashMap < String, Object >(1);
        map.put("enterId", enterId);
        EnterpriseDataInfo enterInfo = new EnterpriseDataInfo();

        try {
            enterInfo = (EnterpriseDataInfo) service.getObject(
                    "EntiManage.enterInfo", map);
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

        return enterInfo;
    }

    public int getEnterpriseCarsNumsToShow(String enterId) {
        int i = 0;

        try {
            i = service.getCount("EntiManage.CarsNums", enterId);
        } catch (BusinessException e) {
            log.error("非配车辆数查询DWR异常发生，异常原因：" + e.getMessage());
        } catch (Exception e) {
            log.error("非配车辆数查询DWR异常发生，异常原因：" + e.getMessage());
        }
        return i;
    }

    /**
     * 效验企业组织名称是否存在
     * @param enterpriseName 企业名称
     * @return 存在结果 TRUE OR FALSE
     */
    public boolean getSingleEnterpriseName(String enterpriseName, String entId,
            String parentId) {
        // 创建参数对象-企业数据类
        EnterpriseDataInfo edi = new EnterpriseDataInfo();
        // 设置企业名
        edi.setFull_name(enterpriseName);

        edi.setEnterprise_id(entId);

        if (parentId == null && entId != null) {
            try {
                String pId = (String) service.getObject(
                        "EntiManage.getPareIdByOrgId", edi);
                edi.setParent_id(pId);
            } catch (BusinessException e) {
                // TODO Auto-generated catch block
                LOG.error("check enterpirse name unique catch:", e);
                e.printStackTrace();
            }
        } else {
            edi.setParent_id(parentId);
        }

        try {
            int resNum = service
                    .getCount("EntiManage.checkEntiNameUnique", edi);

            if (resNum != 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            LOG.error("check enterprise name unique catch:" + e);
            e.printStackTrace();
            return false;
        }
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Service getService() {
        return service;
    }

}
