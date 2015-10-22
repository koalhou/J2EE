/*******************************************************************************
 * @(#)AndroidDownloadAction.java 2013-6-24
 *
 * Copyright 2013 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.android.action;

import com.neusoft.clw.android.domain.RegionManageInfo;
import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.common.util.Constants;
import com.neusoft.clw.common.util.page.action.PaginationAction;
import com.neusoft.clw.sysmanage.datamanage.usermanage.domain.UserInfo;
import com.opensymphony.xwork2.ActionContext;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2013-6-24 下午03:01:27
 */
public class AndroidDownloadAction extends PaginationAction{
    /** service共通类 */
    private transient Service service;
    
    /** 区域经理信息bean **/
    private RegionManageInfo regionManageInfo = new RegionManageInfo();
    
    /**
     * 获得当前操作用户
     * @return
     */
    private UserInfo getCurrentUser() {
        return (UserInfo) ActionContext.getContext().getSession().get(
                Constants.USER_SESSION_KEY);
        
    }
    
    /**
     * 初始化
     * @return
     */
    public String init() {
        UserInfo user = getCurrentUser();
        try {
            String isMobileAllow = (String) service.getObject("AndroidDownload.getMobileAllow", user.getUserID());
            if("0".equals(isMobileAllow)) {
                regionManageInfo = (RegionManageInfo) service.getObject("AndroidDownload.getRegionManagerInfos", user.getUserID());
            }
            
            if(null == regionManageInfo) {
                regionManageInfo = new RegionManageInfo();
                regionManageInfo.setIsMobileAllow(isMobileAllow);
            }
        } catch (BusinessException e) {
            ;
        }
        return SUCCESS;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public RegionManageInfo getRegionManageInfo() {
        return regionManageInfo;
    }

    public void setRegionManageInfo(RegionManageInfo regionManageInfo) {
        this.regionManageInfo = regionManageInfo;
    }
    
    
}
