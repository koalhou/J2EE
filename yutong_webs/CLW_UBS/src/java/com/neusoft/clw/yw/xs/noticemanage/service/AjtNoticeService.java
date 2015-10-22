package com.neusoft.clw.yw.xs.noticemanage.service;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.yw.xs.noticemanage.domain.AjtNotice;

/**
 * 企业公告service
 */
public interface AjtNoticeService extends Service {
    // 创建企业公告
    void insertEnterpriseNotice(String statment,AjtNotice ajtNotice)
            throws BusinessException;
    // 编辑企业公告
    void updateEnterpriseNotice(String statment,AjtNotice ajtNotice)
            throws BusinessException;
}
