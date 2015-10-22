package com.neusoft.clw.yw.xs.baseinfo.service;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.yw.xs.baseinfo.ds.BaseInfo;

/**
 * 基础信息管理service接口
 * @author JinPeng
 */
public interface BaseInfoManageService {
    /** 导入基础信息 **/
    void importBaseInfos(List < BaseInfo > list) throws BusinessException;
}
