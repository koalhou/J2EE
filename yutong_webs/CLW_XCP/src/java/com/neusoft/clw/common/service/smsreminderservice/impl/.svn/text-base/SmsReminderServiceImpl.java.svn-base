/*******************************************************************************
 * @(#)SmsReminderServiceImpl.java 2012-7-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.service.smsreminderservice.impl;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.impl.ServiceImpl;
import com.neusoft.clw.common.service.smsreminderservice.SmsReminderService;
import com.neusoft.clw.infomanage.smsreminder.domain.MessageInfo;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-7-4 下午04:03:41
 */
public class SmsReminderServiceImpl extends ServiceImpl implements SmsReminderService{

    public void smsReminderConfig(List < MessageInfo > list)
            throws BusinessException {
        update("", list);   
    }

}
