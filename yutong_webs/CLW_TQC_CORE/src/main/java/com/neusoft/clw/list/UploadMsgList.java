package com.neusoft.clw.list;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.neusoft.clw.vncs.vg.msg.VgMsg;


/*******************************************************************************
 * @(#)UploadMsgList.java 2010-3-8
 *
 * Copyright 2010 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

/**
 * @author <a href="mailto:zhangmq@neusoft.com">zhangmeiqiu </a>
 * @version $Revision 1.1 $ 2010-3-8 上午08:45:19
 */
public final class UploadMsgList {
    private static final UploadMsgList UPLOADMSG_LIST = new UploadMsgList();

    private ConcurrentLinkedQueue<VgMsg> list = new ConcurrentLinkedQueue<VgMsg>();

    private UploadMsgList() {
       
    }

    public static UploadMsgList getInstance() {
        return UPLOADMSG_LIST;
    }

    public int size () {
        return list.size();
    }
    
    public boolean add(VgMsg up) {
        return list.add(up);
    }
    
    public VgMsg get() {
        return list.poll();
    }
}
