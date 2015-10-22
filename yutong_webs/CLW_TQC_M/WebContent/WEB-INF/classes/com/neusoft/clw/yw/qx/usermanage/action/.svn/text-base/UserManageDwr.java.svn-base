package com.neusoft.clw.yw.qx.usermanage.action;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.action.BaseAction;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;

/**
 * 用户管理DWR
 * @author JinPeng
 */
public class UserManageDwr extends BaseAction {
    private transient Service service;

    /**
     * 判断登陆用户名唯一性
     * @param loginName 登陆名
     * @param userType 用户类型
     * @param enterpriseId 企业ID
     * @return
     */
    public boolean checkLoginNameUnique(String loginName, String userType,
            String enterpriseId) {
        boolean ret = false;
        Map < String, String > map = new HashMap < String, String >();

        if (null != loginName && loginName.equals(Constants.USER_ADMIN_NAME)
                && null != enterpriseId
                && enterpriseId.equals(Constants.YUTONG_ID)) {
            // 判断是否为超级管理员用户
            return true;
        }

        try {
            map.put("loginName", loginName);
            map.put("userType", userType);
            if (Constants.CLW_U_M_CODE.equals(userType)) {
                // 管理系统用户
                map.put("enterpriseId", null);
            } else if(Constants.CLW_U_PE_CODE.equals(userType)) {
                // 车联网系统企业用户
                map.put("enterpriseId", enterpriseId);
            } else if (Constants.CLW_U_PE_CODE.equals(userType)) {
                // 宇通杯系统用户
                map.put("enterpriseId", Constants.YTCUP_ENTI_CODE);
            } else if (Constants.CLW_XCP_CODE.equals(userType)) {
                // 校车应用用户
                map.put("enterpriseId", Constants.YTCUP_ENTI_CODE);
            }

            // 获得用户个数
            int cnt = service.getCount("UserManage.getUserCount", map);
            if (cnt == 0) {
                return false;
            } else {
                return true;
            }
        } catch (BusinessException e) {
            log.error("用户登陆名查询DWR异常发生，异常原因：" + e.getMessage());
        }

        return ret;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
