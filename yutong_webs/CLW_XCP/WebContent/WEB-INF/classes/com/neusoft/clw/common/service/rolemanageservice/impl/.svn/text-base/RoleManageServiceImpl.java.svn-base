package com.neusoft.clw.common.service.rolemanageservice.impl;

import java.util.HashMap;
import java.util.Map;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.rolemanageservice.RoleManageService;
import com.neusoft.clw.common.util.UUIDGenerator;
import com.neusoft.clw.sysmanage.datamanage.rolemanage.dao.RoleManageDao;

public class RoleManageServiceImpl extends ServiceImpl implements
        RoleManageService {

    protected RoleManageDao roleManageDao;

    public RoleManageDao getRoleManageDao() {
        return roleManageDao;
    }

    public void setRoleManageDao(RoleManageDao roleManageDao) {
        this.roleManageDao = roleManageDao;
    }

    public void insertRolesStr(String role_name, String remark, String userID,
            String rolestr, String enterid) throws BusinessException {

        String roleID = UUIDGenerator.getUUID();

        Map < String, Object > map = new HashMap < String, Object >(4);
        map.put("role_id", roleID);
        map.put("role_name", role_name);
        map.put("remark", remark);
        map.put("userID", userID);
        map.put("enterprise_id", enterid);
        try {
            dao.insert("Role.saveRole", map);
            roleManageDao.insertRoles("Role.saveRoleRight", rolestr.split(","),
                    roleID, enterid);
        } catch (DataAccessIntegrityViolationException e) {
            throw new BusinessException(e);
        } catch (DataAccessException e) {
            throw new BusinessException(e);
        }
    }

    public void updateRolesStr(String role_id, String role_name, String remark,
            String userID, String rolestr, String enterid)
            throws BusinessException {

        Map < String, Object > map = new HashMap < String, Object >(4);
        map.put("role_id", role_id);
        map.put("role_name", role_name);
        map.put("remark", remark);
        map.put("modifier", userID);
        map.put("enterprise_id", enterid);
        try {
            dao.update("Role.update_r", map);
            dao.delete("Role.deleteRoleRight", map);
            roleManageDao.insertRoles("Role.saveRoleRight", rolestr.split(","),
                    role_id, enterid);
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
            dao.update("Role.deleteRole", map);
            dao.delete("Role.deleteRoleRight", map);
        } catch (DataAccessIntegrityViolationException e) {
            throw new BusinessException(e);
        } catch (DataAccessException e) {
            throw new BusinessException(e);
        }
    }

}
