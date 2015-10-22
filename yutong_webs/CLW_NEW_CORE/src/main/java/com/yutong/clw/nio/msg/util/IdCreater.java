/*******************************************************************************
 * @(#)IdCreater.java 2009-2-17
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.yutong.clw.nio.msg.util;

import java.util.UUID;

/**
 * @author <a href="mailto:zhangmq@neusoft.com">zhangmeiqiu </a>
 * @version $Revision 1.1 $ 2009-2-17 上午03:07:48
 */
public final class IdCreater {

    private static IdCreater idCreater = new IdCreater();

    public static IdCreater getInstance() {
        return idCreater;
    }

    private int idMsg = 0; 
    
    private static final int IDLENGTH = 3;

    private static final int IDMAXNUM = 999;


    /**
     * 得到MSG ID
     * @return
     */
    public synchronized String getId() {
        String id;
        id = TimeStamp.getInstance().formatTOyyMMddHHmmssSSS() + converToString(idMsg, IDLENGTH);
        idMsg++;
        if (idMsg == IDMAXNUM) {
            idMsg = 0;
        }
        return id;
    }

    private String converToString(int id, int length) {
        String intString = Integer.toString(id);
        for (int j = intString.length(); j < length; j++) {
            intString = "0" + intString;
        }
        return intString;
    }
    
    public static String converToString(String id, int length) {
        for (int j = id.length(); j < length; j++) {
            id = "0" + id;
        }
        return id;
    }
    
    public static String getUUid(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
}
