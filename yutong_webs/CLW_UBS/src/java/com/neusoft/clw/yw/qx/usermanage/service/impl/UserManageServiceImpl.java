package com.neusoft.clw.yw.qx.usermanage.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.common.util.md5.MD5digest;
import com.neusoft.clw.yw.cs.sim.ds.SimInfo;
import com.neusoft.clw.yw.qx.usermanage.ds.UserDetail;
import com.neusoft.clw.yw.qx.usermanage.service.UserManageService;

/**
 * 用户管理service实现类
 * @author JinPeng
 */
public class UserManageServiceImpl extends ServiceImpl implements
        UserManageService {

    /**
     * 填加用户
     */
    public void insertUser(UserDetail userDetail) throws BusinessException {
        // 判断是否为车主用户
        if (Constants.CLW_U_P_CODE.equals(userDetail.getUserType())) {
            // 车主用户无企业信息
            userDetail.setEntipriseId(null);
        } else if (Constants.CLW_U_YTB_CODE.equals(userDetail.getUserType())) {
            // 宇通杯用户，企业ID值为固定值
            userDetail.setEntipriseId(Constants.YTCUP_ENTI_CODE);
        }
        // 设置用户主键
        userDetail.setUserid(UUIDGenerator.getUUID());
        // MD5加密用户密码
        userDetail.setPassword(MD5digest.generatePassword(userDetail
                .getPassword()));

        // 填加用户表信息
        insert("UserManage.insertUser", userDetail);
        // 填加用户角色关联信息
        // insert("UserManage.insertUserRole", userDetail);
    }

    /**
     * 修改用户
     */
    public void updateUser(UserDetail userDetail) throws BusinessException {
        // 判断是否为车主用户
        if (Constants.CLW_U_P_CODE.equals(userDetail.getUserType())) {
            // 车主用户无企业信息
            userDetail.setEntipriseId(null);
        } else if (Constants.CLW_U_YTB_CODE.equals(userDetail.getUserType())) {
            // 宇通杯用户，企业ID值为固定值
            userDetail.setEntipriseId(Constants.YTCUP_ENTI_CODE);
        }
        // 更新用户信息
        update("UserManage.updateUser", userDetail);
        // 删除原始用户角色关联信息
        // delete("UserManage.deleteUserRole", userDetail);
        // 填加用户角色关联信息
        // insert("UserManage.insertUserRole", userDetail);
    }

    /**
     * 删除用户
     */
    public void delUser(UserDetail userDetail) throws BusinessException {
        // 更新用户信息为无效状态
        update("UserManage.updateUserValid", userDetail);
        // 删除用户角色关联关系
        delete("UserManage.delUserRole", userDetail);
    }
}
