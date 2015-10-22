package com.neusoft.clw.util.mfqueue;

import java.util.List;

import com.neusoft.clw.util.mfqueue.exception.InitException;
import com.neusoft.clw.util.mfqueue.exception.OutOfBlockSizeException;
import com.neusoft.clw.util.mfqueue.exception.QueueNotExistException;

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

public interface IQueueManager {
    /**
     * 根据队配置文件初始化队列管理器
     * @param queueManagerFileName
     * @throws InitException
     */
    public void init(String queueManagerFileName) throws InitException;

    /**
     * 释放队列管理器
     */
    public void destroy();

    /**
     * 获取队列基本数据块大小
     * @return
     */
    public int getBlockSize();

    /**
     * 向队列管理器中增加一个队列
     * @param queueName：队列名称
     * @param maxBlocks：队列数据块数
     * @throws Exception
     */
    public void addQueueDescripter(String queueName, int maxBlocks, int windows) throws Exception;

    /**
     * 获取队列管理器中的一个队列
     * @param queueName：队列名
     * @return
     * @throws QueueNotExistException
     */
    public IQueueHandler getQueueDescripter(String queueName) throws QueueNotExistException;

    /**
     * 向某一队列插入对象（阻塞方式）
     * @param queueName：对列名
     * @param obj：对象
     * @throws QueueNotExistException
     * @throws OutOfBlockSizeException
     */
    public void addElementToQueue(String queueName, IQueueObject obj)
            throws QueueNotExistException, OutOfBlockSizeException;

    /**
     * 向某一队列插入对象
     * @param queueName：对列名
     * @param obj：对象
     * @return
     * @throws QueueNotExistException
     * @throws OutOfBlockSizeException
     */
    public boolean addElementToQueueNoWait(String queueName, IQueueObject obj)
            throws QueueNotExistException, OutOfBlockSizeException;

    /**
     * 从某一队列中获取一对象（阻塞方式）
     * @param queueName：对列名
     * @param obj：对象
     * @throws QueueNotExistException
     */
    public void getElementFromQueue(String queueName, IQueueObject obj)
            throws QueueNotExistException;

    /**
     * 从某消息队列中取出一数据块（阻塞方式）
     * @param queueName：消息队列名称
     * @return：数据块
     * @throws QueueNotExistException
     */
    public byte[] getElementFromQueue(String queueName) throws QueueNotExistException;

    /**
     * 从某一队列中获取一对象
     * @param queueName：对列名
     * @param obj：对象
     * @return
     * @throws QueueNotExistException
     */
    public boolean getElementFromQueueNoWait(String queueName, IQueueObject obj)
            throws QueueNotExistException;

    /**
     * 从某消息队列中取出一数据块（非阻塞方式）
     * @param queueName：消息队列名称
     * @return：数据块
     * @throws QueueNotExistException
     */
    public byte[] getElementFromQueueNoWait(String queueName) throws QueueNotExistException;

    /**
     * 向由队列管理管理的分配的一个队列插入一对象（阻塞方式）
     * @param obj：对象
     * @throws OutOfBlockSizeException
     */
    public void addElement(IQueueObject obj) throws OutOfBlockSizeException, QueueNotExistException;

    /**
     * 向由队列管理管理的分配的一个队列插入一对象（阻塞方式）
     * @param obj：对象
     * @throws OutOfBlockSizeException
     */
    public boolean addElementNoWait(IQueueObject obj) throws OutOfBlockSizeException,
            QueueNotExistException;

    /**
     * 从由队列管理管理的分配的一个队列中获取一对象（阻塞方式）
     * @param obj：对象
     */
    // public void getElement(IQueueObject obj) throws QueueNotExistException;
    /**
     * 从由队列管理管理的分配的一个队列中获取一对象（阻塞方式）
     * @param obj：对象
     */
    public boolean getElementNoWait(IQueueObject obj) throws QueueNotExistException;

    public String toQuataString();

    // added by zhouchao at 20071010 for：实现队列监控功能
    public List < QueueQuotaInfo > getQueueQuotaInfos();
}
