package com.neusoft.clw.util.mfqueue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import com.neusoft.clw.util.mfqueue.config.ConfigManager;
import com.neusoft.clw.util.mfqueue.exception.ConfigurationManagerException;
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

public class QueueManager implements IQueueManager {

    private String fileName = null; // 队列管理器配置文件名

    private int blockSize; // 数据块大小

    private int maxBlocksPerQueue; // 队列管理器中允许每个队列最大数据块大小

    private int maxQueues; // 队列管理器中允许对坐队列个数

    private int maxWindows; // 队列管理器中允许每个队列的最大发送窗口大小

    @SuppressWarnings("unchecked")
	private Vector queuesDescripter = new Vector(); // 队列描述器集合

    @SuppressWarnings("unchecked")
	private Hashtable queuesIndex = new Hashtable(); // 队列描述器索引

    private static String LS = System.getProperty("line.separator"); // 系统换行符

    private int currentQueueIndex = 0; // 当前出列队列

    private int currentInQueueIndex = 0; // 当前入列队列

    public QueueManager() {
    }

    public int getBlockSize() {
        return blockSize;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * 根据队列配置文件初始化队列，并分配内存映射空间
     * @param fileName：队列配置文件名
     * @throws InitException
     */
    public void init(String fileName) throws InitException {
        this.fileName = fileName;
        queueDescInit();
        queueMallocInit();
    }

    /**
     * 添加一个队列配置到队列配置文件，并生成队列映像文件。
     * @param queueName 队列名
     * @param queueFileName 队列映像文件名
     * @param maxBlocks 队列最大块数
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public void addQueueDescripter(String queueName, int maxBlocks, int windows) throws Exception {
        String path = null;
        InputStream configFile = null;
        ConfigManager cfgmgr = null;
        QueueDescripter queueDesc = null;
        if (queuesDescripter.size() + 1 > maxQueues) {
            throw new InitException("添加队列超过最大队列数");
        }
        if (queueName == null || maxBlocks <= 0) {
            throw new InitException("添加队列参数错误");
        }

        if (queuesIndex.get(queueName) != null) {
            throw new InitException("添加的队列已经存在");
        }
        try {
            configFile = new FileInputStream(fileName);
        } catch (FileNotFoundException fnfe) {
            throw new InitException("系统配置文件不存在", fnfe);
        }
        try {
            cfgmgr = ConfigManager.getInstance(configFile);
        } catch (ConfigurationManagerException cme) {
            throw new InitException("读取系统配置文件异常", cme);
        }
        try {
            configFile.close();
        } catch (IOException ioe) {
        }
        cfgmgr.addCategory(queueName);
        cfgmgr.setProperty("MaxBlocks", Integer.toString(maxBlocks), queueName);
        cfgmgr.setProperty("Windows", Integer.toString(windows), queueName);
        path = cfgmgr.getProperty("Path", "", "QueueBase");
        blockSize = Integer.parseInt(cfgmgr.getProperty("BlockSize", "", "QueueBase"));
        try {
            cfgmgr.store(new File(fileName));
            queueDesc = new QueueDescripter();
            queueDesc.setPath(path);
            queueDesc.setBlockSize(blockSize);
            queueDesc.setQueueName(queueName);
            queueDesc.setFileName(queueName);
            queueDesc.setMaxBlocks(maxBlocks);
            queueDesc.setWindows(windows);
            queuesDescripter.add(queueDesc);
            queueDesc.makeQueue();
            queuesIndex.put(queueName, Integer.toString(queuesDescripter.size() - 1));
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 读取配置文件获取系统队列配置参数
     * @throws InitException
     */
    @SuppressWarnings("unchecked")
	private void queueDescInit() throws InitException {
        InputStream configFile = null;
        ConfigManager cfgmgr = null;
        String path = null;
        String categoryName = null;
        QueueDescripter queueDesc = null;
        Enumeration eu = null;
        int i = 0;

        try {
            configFile = new FileInputStream(fileName);
        } catch (FileNotFoundException fnfe) {
            throw new InitException("系统配置文件不存在", fnfe);
        }
        try {
            cfgmgr = ConfigManager.getInstance(configFile);
        } catch (ConfigurationManagerException cme) {
            throw new InitException("读取系统配置文件异常", cme);
        }
        try {
            eu = cfgmgr.getCategoryNames();
            categoryName = (String) eu.nextElement();
            path = cfgmgr.getProperty("Path", "", categoryName);
            blockSize = Integer.parseInt(cfgmgr.getProperty("BlockSize", "", categoryName));
            maxBlocksPerQueue = Integer.parseInt(cfgmgr.getProperty("MaxBlocksPerQueue", "",
                    categoryName));
            maxQueues = Integer.parseInt(cfgmgr.getProperty("MaxQueues", "", categoryName));
            maxWindows = Integer.parseInt(cfgmgr.getProperty("MaxWindows", "", categoryName));
            for (; eu.hasMoreElements();) {
                if ((i + 1) > maxQueues) {
                    throw new InitException("队列个数超出MaxQueues定义数");
                }
                categoryName = (String) eu.nextElement();
                queueDesc = new QueueDescripter();
                queueDesc.setPath(path);
                queueDesc.setBlockSize(blockSize);
                queueDesc.setQueueName(categoryName);
                queueDesc.setFileName(categoryName);
                queueDesc.setMaxBlocks(Integer.parseInt(cfgmgr.getProperty("MaxBlocks", "",
                        categoryName)));
                queueDesc.setWindows(Integer.parseInt(cfgmgr.getProperty("Windows", "",
                        categoryName)));
                if (queueDesc.getWindows() > maxWindows) {
                    throw new InitException("队列配置Windows超出MaxWindows");
                }
                queuesDescripter.add(queueDesc);
                queuesIndex.put(categoryName, Integer.toString(i));
                i++;
            }
        } catch (NumberFormatException e) {
            throw new InitException("配置文件中配置参数有误", e);
        }
        try {
            configFile.close();
        } catch (IOException ioe) {
        }
        configFile = null;
        cfgmgr = null;
        path = null;
        categoryName = null;
        queueDesc = null;
        eu = null;
    }

    /**
     * 为每个队列生成或读取内存映像文件
     * @throws InitException
     */
    private void queueMallocInit() throws InitException {
        int queues = queuesDescripter.size();
        QueueDescripter queueDesc;
        for (int i = 0; i < queues; i++) {
            queueDesc = (QueueDescripter) queuesDescripter.get(i);
            try {
                queueDesc.makeQueue();
            } catch (Exception e) {
                throw new InitException("生成文件内存映像异常", e);
            }
            queueDesc = null;
        }
    }

    /**
     * 从队列管理器中获取一个队列对象
     * @param queueName：队列名称
     * @return：队列对象
     * @throws QueueNotExistException
     */
    public IQueueHandler getQueueDescripter(String queueName) throws QueueNotExistException {
        if (queuesIndex.get(queueName) == null) {
            throw new QueueNotExistException("队列不存在");
        }
        int index = Integer.parseInt((String) queuesIndex.get(queueName));
        return (IQueueHandler) queuesDescripter.get(index);
    }

    /**
     * 向某一队列中插入一对象
     * @param queueName：队列名称
     * @param obj：插入对象
     * @throws QueueNotExistException
     */
    public void addElementToQueue(String queueName, IQueueObject obj)
            throws QueueNotExistException, OutOfBlockSizeException {
        ((IQueueHandler) getQueueDescripter(queueName)).putElement(obj);
    }

    /**
     * 向某一队列中插入一对象（阻塞方式）
     * @param queueName：队列名称
     * @param obj：插入对象
     * @throws QueueNotExistException
     */
    public boolean addElementToQueueNoWait(String queueName, IQueueObject obj)
            throws QueueNotExistException, OutOfBlockSizeException {
        return ((IQueueHandler) getQueueDescripter(queueName)).putElementNoWait(obj);
    }

    /**
     * 从某一队列中取出一对象
     * @param queueName：队列名称
     * @param obj：获取对象
     * @throws QueueNotExistException
     */
    public void getElementFromQueue(String queueName, IQueueObject obj)
            throws QueueNotExistException {
        ((IQueueHandler) getQueueDescripter(queueName)).getElement(obj);
    }

    /**
     * 从某消息队列中取出一数据块（阻塞方式）
     * @param queueName：消息队列名称
     * @return：数据块
     * @throws QueueNotExistException
     */
    public byte[] getElementFromQueue(String queueName) throws QueueNotExistException {
        return ((IQueueHandler) getQueueDescripter(queueName)).getElement();
    }

    /**
     * 从某一队列中取出一对象（阻塞方式）
     * @param queueName：队列名称
     * @param obj：获取对象
     * @throws QueueNotExistException
     */
    public boolean getElementFromQueueNoWait(String queueName, IQueueObject obj)
            throws QueueNotExistException {
        return ((IQueueHandler) getQueueDescripter(queueName)).getElementNoWait(obj);
    }

    /**
     * 从某消息队列中取出一数据块（非阻塞方式）
     * @param queueName：消息队列名称
     * @return：数据块
     * @throws QueueNotExistException
     */
    public byte[] getElementFromQueueNoWait(String queueName) throws QueueNotExistException {
        return ((IQueueHandler) getQueueDescripter(queueName)).getElementNoWait();
    }

    /**
     * 向由队列管理管理的分配的一个队列插入一对象（阻塞方式）
     * @param obj：对象
     * @throws OutOfBlockSizeException
     */
    public void addElement(IQueueObject obj) throws OutOfBlockSizeException, QueueNotExistException {
        inQueueSelector().putElement(obj);
    }

    /**
     * 向由队列管理管理的分配的一个队列插入一对象（阻塞方式）
     * @param obj：对象
     * @throws OutOfBlockSizeException
     */
    public boolean addElementNoWait(IQueueObject obj) throws OutOfBlockSizeException,
            QueueNotExistException {
        return inQueueSelector().putElementNoWait(obj);
    }

    /**
     * 从由队列管理管理的分配的一个队列中获取一对象（阻塞方式）
     * @param obj：对象
     */
    /*
     * public void getElement(IQueueObject obj) throws QueueNotExistException {
     * queueSelector().getElement(obj); }
     */

    /**
     * 从由队列管理管理的分配的一个队列中获取一对象（非阻塞方式）
     * @param obj：对象
     */
    public synchronized boolean getElementNoWait(IQueueObject obj) throws QueueNotExistException {
        int startIndex = 0;
        boolean ret;
        boolean round = false;
        startIndex = currentQueueIndex;
        IQueueHandler queueHandler = deQueueSelector();
        while (true) {
            if (!round) {
                if (currentQueueIndex >= startIndex) {
                    round = true;
                }
            }
            if (ret = queueHandler.getElementNoWait(obj)) {
                if (Debug.debug) {
                    System.out.println("出列成功,currentQueueIndex=" + currentQueueIndex);
                }
                break;
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ie) {

                }
                if (Debug.debug) {
                    System.out.println("出列失败，currentQueueIndex=" + currentQueueIndex
                            + ",currentWindows=" + queueHandler.getCurrentWindows() + ",round="
                            + round + ",startIndex=" + startIndex);
                }
                if (startIndex == currentQueueIndex && queueHandler.getCurrentWindows() == 0
                        && round) {
                    break;
                }
            }
            queueHandler = deQueueSelector();
        }
        return ret;
    }

    /**
     * 获取队列描述器，如果当前队列描述器已经使用的窗口大小达到0则复原其发送窗口大小， 并取队列管理器中的下一个队列描述器。
     * @return
     * @throws QueueNotExistException
     */
    private IQueueHandler deQueueSelector() throws QueueNotExistException {
        IQueueHandler queueHandler = null;

        queueHandler = (IQueueHandler) queuesDescripter.get(currentQueueIndex);
        if (queueHandler.getCurrentWindows() == 0) {
            queueHandler.retSetCurrentWindows();
            if (Debug.debug) {
                System.out.println("队列(currentQueueIndex=" + currentQueueIndex
                        + ")的currentWindows=0," + "该队列释放发送令牌，并将令牌传送给下一个队列");
            }
            queueHandler = getNextQueueDescripter();
        }
        return queueHandler;
    }

    /**
     * 为入列对象选择一个队列
     * @return
     * @throws QueueNotExistException
     */
    private IQueueHandler inQueueSelector() throws QueueNotExistException {
        IQueueHandler queueHandler = null;
        currentInQueueIndex++;
        currentInQueueIndex = currentInQueueIndex % queuesDescripter.size();
        queueHandler = (IQueueHandler) queuesDescripter.get(currentInQueueIndex);
        return queueHandler;
    }

    /**
     * 获取下一个队列描述器
     * @return
     * @throws QueueNotExistException
     */
    private IQueueHandler getNextQueueDescripter() throws QueueNotExistException {
        int size = queuesDescripter.size();
        if (size <= 0) {
            throw new QueueNotExistException("系统中未初始化任何可用队列");
        }
        currentQueueIndex++;
        currentQueueIndex = currentQueueIndex % size;
        return (IQueueHandler) queuesDescripter.get(currentQueueIndex);
    }

    /**
     * 释放队列内存映像空间
     */
    public void destroy() {
        queuesIndex = null;
    }

    /**
     * 描述队列管理器中个队列的基本情况：队列名、块大小、已使用块、未使用块。
     * @return
     */
    public String toString() {
        StringBuffer temp = new StringBuffer();
        temp.append(LS);
        temp.append("\t队列管理器描述：");
        temp.append(LS);
        temp.append("\t允许的最大队列数          \t= ");
        temp.append(maxQueues);
        temp.append(LS);
        temp.append("\t每个队列允许的最大数据块数\t= ");
        temp.append(maxBlocksPerQueue);
        temp.append(LS);
        temp.append("\t每个数据块的大小(字节)    \t= ");
        temp.append(blockSize);
        temp.append(LS);
        temp.append("\t每个队列的最大窗口    \t= ");
        temp.append(maxWindows);

        int size = queuesDescripter.size();
        temp.append(LS);
        temp.append("\t已创建队列数              \t= ");
        temp.append(size);

        QueueDescripter qd = null;
        for (int i = 0; i < size; i++) {
            qd = (QueueDescripter) queuesDescripter.get(i);
            temp.append(LS);
            temp.append(qd.toString());
        }
        return temp.toString();
    }

    public String toQuataString() {
        StringBuffer temp = new StringBuffer();
        temp.append(LS);
        temp.append("\t队列管理器描述：");
        int size = queuesDescripter.size();
        QueueDescripter qd = null;
        for (int i = 0; i < size; i++) {
            qd = (QueueDescripter) queuesDescripter.get(i);
            temp.append(LS);
            temp.append(qd.toQuataString());
        }
        return temp.toString();
    }

    // added by zhouchao at 20071010 for：实现队列监控功能
    public List < QueueQuotaInfo > getQueueQuotaInfos() {
        List < QueueQuotaInfo > queueInfoList = new LinkedList < QueueQuotaInfo >();
        int size = queuesDescripter.size();
        QueueDescripter qd = null;
        for (int i = 0; i < size; i++) {
            qd = (QueueDescripter) queuesDescripter.get(i);
            QueueQuotaInfo queueQuotaInfo = new QueueQuotaInfo();
            queueQuotaInfo.setQueueName(qd.getQueueName());
            queueQuotaInfo.setMaxQuota(qd.getMaxBlocks());
            queueQuotaInfo.setNullBlocksQuota(qd.getNullBlocksIndex().size());
            queueQuotaInfo.setBlocksQuota(qd.getBlocksIndex().size());
            queueInfoList.add(queueQuotaInfo);
        }
        return queueInfoList;
    }

}
