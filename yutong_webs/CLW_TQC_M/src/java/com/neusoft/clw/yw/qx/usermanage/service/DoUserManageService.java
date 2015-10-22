package com.neusoft.clw.yw.qx.usermanage.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.qx.usermanage.ds.UserDetail;

/**
 * 用户管理service接口
 * @author JinPeng
 */
public interface DoUserManageService extends Service {
    /** 授权用户 **/
    void authorizationUser(List < UserDetail > list) throws BusinessException;
}
