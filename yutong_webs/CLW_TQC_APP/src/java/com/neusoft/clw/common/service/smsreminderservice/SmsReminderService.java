/*******************************************************************************
 * @(#)SmsReminderService.java 2012-7-4
 *
 * Copyright 2012 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.common.service.smsreminderservice;

import java.util.List;

import com.neusoft.clw.common.exceptions.BusinessException;
import com.neusoft.clw.common.service.Service;
import com.neusoft.clw.infomanage.smsreminder.domain.MessageInfo;

/**
 * @author <a href="mailto:jin.p@neusoft.com">JinPeng </a>
 * @version $Revision 1.1 $ 2012-7-4 下午04:01:54
 */
public interface SmsReminderService extends Service{
    void smsReminderConfig(List<MessageInfo> list) throws BusinessException;
}
