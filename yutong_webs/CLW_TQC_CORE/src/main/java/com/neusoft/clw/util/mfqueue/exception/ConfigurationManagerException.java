package com.neusoft.clw.util.mfqueue.exception;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: neusoft
 * </p>
 * @author wang-wei@neusoft.com
 * @version 1.0
 */

@SuppressWarnings("serial")
public class ConfigurationManagerException extends Exception {

    public ConfigurationManagerException() {
        super();
    }

    public ConfigurationManagerException(String s) {
        super(s);
    }

    public ConfigurationManagerException(String s, Exception e) {
        super(s, e);
    }

    public ConfigurationManagerException(Exception e) {
        super(e);
    }

}
