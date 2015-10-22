/*******************************************************************************
 * @(#)SmsManageServiceImpl.java 2012-3-14
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.service.smsmanageservice.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.smsmanageservice.SmsManageService;
import com.neusoft.clw.infomanage.smsmanage.domain.SmsInfo;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-3-14 下午04:24:52
 */
public class SmsManageServiceImpl extends ServiceImpl implements SmsManageService{

    /* (non-Javadoc)
     * @see com.neusoft.clw.common.service.smsmanageservice.SmsManageService#addMsgConfig(java.lang.String, com.neusoft.clw.infomanage.smsmanage.domain.SmsInfo)
     */
    public void addMsgConfig(SmsInfo smsInfo)
            throws BusinessException {
        insert("", smsInfo);        
    }

    
    public void importSmsInfos(List < SmsInfo > list)
            throws Exception {
    	for(SmsInfo sms : list)
    		insert("", sms);     
    }
}
