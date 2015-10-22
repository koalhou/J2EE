package com.neusoft.clw.common.service.rolemanageservice;

import com.neusoft.clw.common.exceptions.BusinessException;

public interface RoleManageService {

    void insertRolesStr(String role_name, String remark, String userID,
            String rolestr, String enterid) throws BusinessException;

    void updateRolesStr(String role_id, String role_name, String remark,
            String userID, String rolestr, String enterid)
            throws BusinessException;

    void deleteRolesStr(String role_id, String userID) throws BusinessException;

}
