package com.neusoft.clw.yw.qx.rolemanage.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.yw.qx.rolemanage.service.RoleManageService;

public class RoleManageServiceImpl extends ServiceImpl implements
        RoleManageService {

    public void insertRolesStr(String role_name, String remark, String userID,
            String rolestr) throws BusinessException {

        String roleID = UUIDGenerator.getUUID();

        Map < String, Object > map = new HashMap < String, Object >(4);
        map.put("role_id", roleID);
        map.put("role_name", role_name);
        map.put("remark", remark);
        map.put("userID", userID);
        try {
            dao.insert("RoleManage.insertRoleInfo", map);
            roleManageDao.insertRoles("RoleManage.insertRoles", rolestr
                    .split(","), roleID);
        } catch (DataAccessIntegrityViolationException e) {
            throw new BusinessException(e);
        } catch (DataAccessException e) {
            throw new BusinessException(e);
        }
    }

    public void updateRolesStr(String role_id, String role_name, String remark,
            String userID, String rolestr) throws BusinessException {

        Map < String, Object > map = new HashMap < String, Object >(4);
        map.put("role_id", role_id);
        map.put("role_name", role_name);
        map.put("remark", remark);
        map.put("modifier", userID);
        try {
            dao.update("RoleManage.updateRoleInfo", map);
            dao.delete("RoleManage.delRoles", map);
            roleManageDao.insertRoles("RoleManage.insertRoles", rolestr
                    .split(","), role_id);
        } catch (DataAccessIntegrityViolationException e) {
            throw new BusinessException(e);
        } catch (DataAccessException e) {
            throw new BusinessException(e);
        }
    }

    public void deleteRolesStr(String role_id, String userID)
            throws BusinessException {

        Map < String, Object > map = new HashMap < String, Object >(2);
        map.put("role_id", role_id);
        map.put("vaset_user_id", userID);
        try {
            dao.update("RoleManage.updateDelRoleInfo", map);
            dao.delete("RoleManage.delRoles", map);
        } catch (DataAccessIntegrityViolationException e) {
            throw new BusinessException(e);
        } catch (DataAccessException e) {
            throw new BusinessException(e);
        }
    }

}
