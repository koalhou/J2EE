package com.neusoft.clw.main;

import static com.neusoft.tag.core.log.LogFormatter.formatMsg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neusoft.clw.util.mfqueue.IQueueManager;
import com.neusoft.clw.util.mfqueue.QueueManager;
import com.neusoft.clw.util.mfqueue.exception.OutOfBlockSizeException;
import com.neusoft.clw.util.mfqueue.exception.QueueNotExistException;
import com.neusoft.clw.vncs.vg.msg.VgMsg;


/*******************************************************************************
 * @(#)QueueLoad.java 2010-3-15
 *
 * Copyright 2010 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

/**
 * @author <a href="mailto:zhangmq@neusoft.com">zhangmeiqiu </a>
 * @version $Revision 1.1 $ 2010-3-15 上午10:28:16
 */
public final class Queue {

    private static Logger LOG = LoggerFactory.getLogger(Queue.class);
    
    private static final Queue QUEUE = new Queue();
    
    private boolean isInit = false;

    private Queue() {
    }

    private IQueueManager apQueueManager = new QueueManager();


    public void queueInit() {
        try {
            apQueueManager.init(System.getProperty("user.dir") + System.getProperty("file.separator")
                    + "config" + System.getProperty("file.separator") + "Queue-config.xml");
            
            isInit = true;

        } catch (Throwable t) {
            LOG.error(formatMsg("Load Queue", "ThrowableException when init queue manager!"), t);
        }
    }

    public void queueDestroy() {

        apQueueManager.destroy();
    }
   
    public static Queue getInstance() {
        return QUEUE;
    }
    
    public boolean add(VgMsg vg) throws QueueNotExistException, OutOfBlockSizeException {
        return apQueueManager.addElementToQueueNoWait("APQueue", vg);
    }
    
    public VgMsg get() throws QueueNotExistException{
        VgMsg vg = new VgMsg(apQueueManager.getBlockSize());
        
        if (apQueueManager.getElementFromQueueNoWait("APQueue", vg)) {
            return vg;
        } else {
            return null;
        }
    }
    
    public int getBlockSize() {
        return apQueueManager.getBlockSize();
    }
    
    public int size() throws QueueNotExistException {
        return apQueueManager.getQueueDescripter("APQueue").getNullBlocksIndex().size();
    }

	public boolean isInit() {
		return isInit;
	}
}
