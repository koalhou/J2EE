package com.neusoft.clw.common.serviec.usermanageserviec.impl;

import java.util.ArrayList;
import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.usermanageservice.UserManageService;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserRoleInfo;

public class UserManageServiceImpl extends ServiceImpl implements
        UserManageService {

    public void batchUserAndRoleupdateList(UserInfo userinfo)
            throws BusinessException, DataAccessIntegrityViolationException,
            DataAccessException {
        // TODO Auto-generated method stub
        List list = new ArrayList();
        // 批量删除角色
        list.add(userinfo);
        dao.batchUserAndRoleDeleteList("User.bachtDeleteUserAndRoleInfo", list);

        // 批量更新角色
        list.clear();
        if (userinfo.getRoleId() != null && !userinfo.getRoleId().equals("")) {
            String[] id = userinfo.getRoleId().split(",");
            System.out.println(id.length);

            for (int i = 0; i < id.length; i++) {
                UserRoleInfo ui = new UserRoleInfo();
                ui.setUSER_ID(userinfo.getUserID());
                String rid = id[i];
                ui.setROLE_ID(rid);
                ui.setENTERPRISE_ID("");
                ui.setCREATER(userinfo.getModifier());
                list.add(ui);
            }
            dao.batchUserAndRoleupdateList("User.bacthupdateUserAndRoleInfo",
                    list);
        }

        // 更新用户表
        dao.update("User.updateUserInfo", userinfo);

    }

    public void modifyPersonalInfo(UserInfo userinfo) throws BusinessException,
            DataAccessIntegrityViolationException, DataAccessException {
        // 更新用户表
        dao.update("User.modifyPersonalInfo", userinfo);
    }

}
