/*******************************************************************************
 * @(#)UUIDGenerator.java 2009-5-25
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

package com.neusoft.clw.common.util;

import java.util.UUID;

/**
 * <p>
 * Description: UUID生成器
 * </p>
 * @author lihaiyan<a href="mailto:lihaiyan@neusoft.com">haiyan.li </a>
 * @version $Revision 1.0 $ 2009-5-25 下午06:12:36
 */
public final class UUIDGenerator {

    private UUIDGenerator() {
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID() {
        String uuidString = UUID.randomUUID().toString();
        return uuidString;
    }

    /**
     * 获得一个32位UUID
     * @return String UUID
     */
    public static String getUUID32() {
        String uuidString = UUID.randomUUID().toString().replaceAll("-", "");
        return uuidString;
    }

}
