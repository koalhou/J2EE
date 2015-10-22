
package com.neusoft.smsplatform.message.inside.msg;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 
 * @author chenqiong
 *
 */
public final class MessageInsideMsgStatusCode extends ConcurrentLinkedQueue<String> {

    private static final long serialVersionUID = -5614074991876594794L;

    public static final String STATUS_CODE_SUCCESS = "0";
    
    public static final String STATUS_USERNAME_ERROR = "1";

    public static final String STATUS_INVALID_DATA = "00000001";

    public static final String STATUS_INVALID_DATA_TYPE = "00000002";

    public static final String STATUS_INVALID_CENTER_NUMBER = "00000003";

    public static final String STATUS_INVALID_USER_NUMBER = "00000004";

    public static final String STATUS_PASS_ERROR = "2";

    public static final String STATUS_APP_REFUSE = "00000006";

    public static final String STATUS_LOGIN_REFUSE = "00000007";
    
    public static final String STATUS_CANCEL_REFUSE = "00000008";
    
    public static final String STATUS_IMG_FAILURE = "00000009";
    
    public static final String STATUS_CONTROL_FAILURE = "00000010";
    
    public static final String STATUS_LISTEN_FAILURE = "00000011";

    private static final MessageInsideMsgStatusCode STATUS = new MessageInsideMsgStatusCode();

    public static MessageInsideMsgStatusCode getInstance() {
        return STATUS;
    }

    private MessageInsideMsgStatusCode() {
        this.add(STATUS_CODE_SUCCESS);
        this.add(STATUS_INVALID_DATA);
        this.add(STATUS_INVALID_DATA_TYPE);
        this.add(STATUS_INVALID_CENTER_NUMBER);
        this.add(STATUS_INVALID_USER_NUMBER);
        this.add(STATUS_PASS_ERROR);
        this.add(STATUS_APP_REFUSE);
        this.add(STATUS_LOGIN_REFUSE);
        this.add(STATUS_CANCEL_REFUSE);
        this.add(STATUS_IMG_FAILURE);
        this.add(STATUS_CONTROL_FAILURE);
        this.add(STATUS_LISTEN_FAILURE);
    }
}
