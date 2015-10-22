package com.neusoft.clw.yw.qx.usermanage.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.qx.usermanage.ds.UserDetail;
import com.neusoft.clw.yw.qx.usermanage.service.DoUserManageService;

/**
 * 用户管理service实现类
 * @author JinPeng
 */
public class DoUserManageServiceImpl extends ServiceImpl implements
        DoUserManageService {
    /**
     * 授权用户
     */
    public void authorizationUser(List < UserDetail > list)
            throws BusinessException {
        // 删除原始用户角色关联信息
        delete("", list);
        // 填加用户角色关联信息
        insert("", list);
    }

}
