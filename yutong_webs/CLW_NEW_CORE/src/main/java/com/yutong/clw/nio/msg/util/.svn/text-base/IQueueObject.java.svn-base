package com.yutong.clw.nio.msg.util;

import java.nio.ByteBuffer;

/**
 * when a oject want to be put into the Memory File Queue,the object must implements the
 * IQueueObject interface.
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

public interface IQueueObject {

    /**
     * convert the object to byte array
     * @return object's byte array
     */
    public ByteBuffer toByteBuffer();

    /**
     * parse the byte array to object's properties
     * @param objByteArray :byte array ,it is ended by 0x00
     */
    public void toObject(byte[] objByteArray);

    public void reset();

    public int getBlockSize();
}
