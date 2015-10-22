package com.neusoft.clw.yw.xs.baseinfo.service.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.yw.xs.baseinfo.ds.BaseInfo;
import com.neusoft.clw.yw.xs.baseinfo.service.BaseInfoManageService;

/**
 * 基础信息管理service实现类
 * @author JinPeng
 */
public class BaseInfoManageServiceImpl extends ServiceImpl implements
        BaseInfoManageService {
    /** 导入基础信息 **/
    public void importBaseInfos(List < BaseInfo > list)
            throws BusinessException {
        insert("", list);
    }
}
