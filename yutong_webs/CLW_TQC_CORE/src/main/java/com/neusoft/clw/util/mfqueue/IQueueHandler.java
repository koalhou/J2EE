package com.neusoft.clw.util.mfqueue;

import com.neusoft.clw.util.mfqueue.exception.*;

public interface IQueueHandler {
    /**
     * 阻塞入队列
     * @param queueObject
     * @throws OutOfBlockSizeException
     */
    public void putElement(IQueueObject queueObject) throws OutOfBlockSizeException;

    /**
     * 非阻塞入队列
     * @param queueObject
     * @return:true 入列成功;false:入列失败
     * @throws OutOfBlockSizeException
     */
    public boolean putElementNoWait(IQueueObject queueObject) throws OutOfBlockSizeException;

    /**
     * 阻塞出队列
     * @param queueObject
     */
    public void getElement(IQueueObject queueObject);

    /**
     * 阻塞从队列中得到一个数据块
     * @return：一个数据块
     */
    public byte[] getElement();

    /**
     * 非阻塞出队列
     * @param queueObject
     * @return:true 出列成功;false:出列失败
     */
    public boolean getElementNoWait(IQueueObject queueObject);

    /**
     * 非阻塞从队列中得到一个数据块
     * @return:一个数据块
     */
    public byte[] getElementNoWait();

    public int getCurrentWindows();

    public void retSetCurrentWindows();

    public void destory();

    /**
     * 获取队列中已经使用的数据块
     * @return
     */
    public IQueue getBlocksIndex();

    /**
     * 获取队列中可使用数据块的索引
     * @return
     */
    public IQueue getNullBlocksIndex();
}
