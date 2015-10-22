package com.neusoft.clw.common.service.usermanageservice;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.exceptions.DataAccessException;
import com.neusoft.clw.common.exceptions.DataAccessIntegrityViolationException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;

public interface UserManageService extends Service {

    void batchUserAndRoleupdateList(UserInfo userinfo)
            throws BusinessException, DataAccessIntegrityViolationException,
            DataAccessException;

    public void modifyPersonalInfo(UserInfo userinfo) throws BusinessException,
            DataAccessIntegrityViolationException, DataAccessException;
}
