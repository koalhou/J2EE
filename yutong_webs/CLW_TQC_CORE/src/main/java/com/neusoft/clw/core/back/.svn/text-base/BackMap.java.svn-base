/*******************************************************************************
 * @(#)BackMap.java 2009-3-31
 *
 * Copyright 2009 Neusoft Group Ltd. All rights reserved.
 * Neusoft PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.neusoft.clw.core.back;

import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:pud@neusoft.com">pu dong </a>
 * @version $Revision 1.1 $ 2009-3-31 下午08:46:29
 */
public class BackMap extends ConcurrentHashMap<String, Back> {

    private static final long serialVersionUID = 5636658645097193353L;

    private static final Logger log = LoggerFactory.getLogger(BackMap.class);

    private static final BackMap backmap = new BackMap();

    public static BackMap getInstance() {
        return backmap;
    }

    @Override
    public Back get(Object backId) {
        return super.get(backId);
    }

    @Override
    public Back put(String backId, Back back) {
        log.info("the back " + back.getAddress() + " has been put into the back map.");
        return super.put(backId, back);
    }

    @Override
    public Back remove(Object key) {
        log.info("the back " + key + " has been removed from the back map.");
        return super.remove(key);
    }
}
