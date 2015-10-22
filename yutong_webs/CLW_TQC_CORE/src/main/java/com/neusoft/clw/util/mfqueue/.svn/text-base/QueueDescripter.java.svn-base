package com.neusoft.clw.util.mfqueue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import com.neusoft.clw.util.mfqueue.exception.NotImplementIQueueObjectInterfaceException;
import com.neusoft.clw.util.mfqueue.exception.OutOfBlockSizeException;

public class QueueDescripter implements IQueueHandler {

    private static String LS = System.getProperty("line.separator"); // 系统换行符

    private String path; // 队列文件存放路径

    private int blockSize; // 队列数据块大小

    private String queueName; // 队列名称

    private String fileName; // 队列文件名

    private int maxBlocks; // 队列管理器允许的最大数据块大小

    private int windows; // 队列发送窗口大小

    private int currentWindows; // 当前发送窗口大小

    private ByteBuffer queue; // 队列文件映像缓冲

    private IQueue nullBlocksIndex = new Queue(); // 队列未使用数据块索引

    private IQueue blocksIndex = new Queue(); // 队列已经使用数据块索引

    @SuppressWarnings("unused")
	private int usedBlocks = 0; // 队列已经使用数据块大小

    public boolean isDestory = false; // 队列是否释放

    // 只有windows>0时该队列为群组队列系统的出队列受出列令牌及出列窗口大小控制
    private boolean groupQueue = false; // 队列中所有队列是否为一组共用发送窗口的队列

    public QueueDescripter() {
    }

    public void setWindows(int windows) {
        this.windows = windows;
        currentWindows = windows;
        if (windows > 0) {
            groupQueue = true;
        }
    }

    public int getWindows() {
        return windows;
    }

    public int getCurrentWindows() {
        return currentWindows;
    }

    public void retSetCurrentWindows() {
        this.currentWindows = windows;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getBlockSize() {
        return blockSize;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setMaxBlocks(int maxBlocks) {
        this.maxBlocks = maxBlocks;
    }

    public int getMaxBlocks() {
        return maxBlocks;
    }

    /**
     * 根据队列文件描述生成队列映像缓冲
     * @throws Exception
     */
    public void makeQueue() throws Exception {
        File file = new File(path + fileName);
        // //test for path
        // String test=System.getProperty("user.dir");
        // System.out.println(test);
        long fileSize;
        if (file.exists()) {
            fileSize = mappedFileToQueue();
            if (fileSize > blockSize * maxBlocks) {
                /*
                 * 存在的映像文件比实际定义的数据块大， 则设置数据块的大小为实际文件大小
                 */
                maxBlocks = (int) fileSize / blockSize;
            } else if (fileSize < blockSize * maxBlocks) {
                // 扩展数据文件
                backupAndExtendQueue(fileSize);
                createQueueMappedFile();
                mappedFileToQueue();
            }
            // search the queue to find bolcks which can be used,and which can not be used
            for (int i = 0; i < maxBlocks; i++) {
                queue.position(i * blockSize);
                if (queue.getInt() == 0x00) {
                    nullBlocksIndex.put(Integer.toString(i));
                } else {
                    blocksIndex.put(Integer.toString(i));
                }
            }
        } else {
            queue = ByteBuffer.allocate(blockSize * maxBlocks);
            try {
                createQueueMappedFile();
                // set all all blocks can be used
                for (int i = 0; i < maxBlocks; i++) {
                    nullBlocksIndex.put(Integer.toString(i));
                }
                mappedFileToQueue();
            } catch (Exception e) {
                throw e;
            }
        }
    }

    /**
     * 从映像文件中读内容到映像队列中
     * @return：映像文件长度
     * @throws FileNotFoundException
     * @throws IOException
     */
    private long mappedFileToQueue() throws FileNotFoundException, IOException {
        long fileSize;
        FileChannel rwCh = null;

        rwCh = new RandomAccessFile(path + fileName, "rw").getChannel();
        fileSize = rwCh.size();
        if (fileSize < blockSize * maxBlocks) {
            rwCh.close();
            return fileSize;
        }
        queue = rwCh.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
        rwCh.close();

        return fileSize;
    }

    /**
     * 创建一个队列映像文件，并将queue写入文件
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void createQueueMappedFile() throws FileNotFoundException, IOException {

        FileOutputStream fos = new FileOutputStream(path + fileName);
        FileChannel fChan = fos.getChannel();
        queue.position(0);
        fChan.write(queue);
        fos.close();
        fChan.close();
        fos = null;
        fChan = null;
    }

    /**
     * 存在的映像文件比实际定义数据块数小 将映射文件内容备份，按照数据块的大 小重新建立数据文件，并将备份内容写 回新生成的映射文件
     * @param fileSize：原映射文件大小
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void backupAndExtendQueue(long fileSize) throws FileNotFoundException, IOException {
        RandomAccessFile ft = new RandomAccessFile(path + fileName, "rw");
        byte[] bft = new byte[(int) fileSize];
        ft.read(bft);
        ft.close();
        File fileTemp = new File(path + fileName);
        fileTemp.delete();
        queue = ByteBuffer.allocate(blockSize * maxBlocks);
        queue.position(0);
        queue.put(bft);
    }

    /**
     * 获取队列所有数据块
     * @return
     */
    @SuppressWarnings("unused")
	private ByteBuffer getQueue() {
        return queue;
    }

    /**
     * 获取队列中可使用数据块的索引
     * @return
     */
    public IQueue getNullBlocksIndex() {
        return nullBlocksIndex;
    }

    /**
     * 获取队列中已经使用的数据块
     * @return
     */
    public IQueue getBlocksIndex() {
        return blocksIndex;
    }

    /**
     * 阻塞方式向队列中插入一条消息（该消息必须实现IQueueObject接口）
     * @throws NotImplementIQueueObjectInterfaceException
     */
    public synchronized void putElement(IQueueObject queueObject) throws OutOfBlockSizeException {
        int position;
        Object obj = null;
        // it should delete from nullBlocksIndex and add into blocksIndex
        if (queueObject != null) {
            if (queueObject.getBlockSize() > blockSize) {
                throw new OutOfBlockSizeException("超过最大数据块大小");
            }
            while (!isDestory) {
                if (nullBlocksIndex.size() == 0) {
                    try {
                        wait(100);
                    } catch (InterruptedException ie) {
                    }
                } else {
                    obj = nullBlocksIndex.get();
                    if (obj == null) {
                        continue;
                    }
                    position = Integer.parseInt((String) obj);
                    blocksIndex.put(Integer.toString(position));
                    queue.position(position * blockSize);
                    queue.put(queueObject.toByteBuffer().array());
                    notifyAll();
                    break;
                }
            }
        }
        obj = null;
    }

    /**
     * 从队列中以阻塞方式获取一个数据块
     * @return：数据块
     */
    public synchronized byte[] getElement() {
        int position;
        byte[] temp = new byte[blockSize];
        Object obj = null;
        while (!isDestory) {
            if (blocksIndex.size() == 0) {
                try {
                    wait(100);
                } catch (InterruptedException ie) {
                }
            }
            obj = blocksIndex.get();
            if (obj == null) {
                continue;
            }
            // it should delete from blocksIndex and add into nullBlocksIndex
            position = Integer.parseInt((String) obj);
            nullBlocksIndex.put(Integer.toString(position));
            queue.position(position * blockSize);
            queue.get(temp);
            // set the first byte to zero indentify this blocks has been free.
            queue.position(position * blockSize);
            queue.putInt(0);
            notifyAll();
            break;
        }
        return temp;
    }

    /**
     * 阻塞方式从队列中获取一条消息并从队列中删除该消息
     * @return
     */
    public synchronized void getElement(IQueueObject queueObject) {
        int position;
        byte[] temp = new byte[blockSize];
        Object obj = null;
        while (!isDestory) {
            if (blocksIndex.size() == 0) {
                try {
                    wait(1000);
                } catch (InterruptedException ie) {
                }
            }
            obj = blocksIndex.get();
            if (obj == null) {
                continue;
            }
            // it should delete from blocksIndex and add into nullBlocksIndex
            position = Integer.parseInt((String) obj);
            nullBlocksIndex.put(Integer.toString(position));
            queue.position(position * blockSize);
            queue.get(temp);
            // set the first byte to zero indentify this blocks has been free.
            queue.position(position * blockSize);
            queue.putInt(0);
            queueObject.toObject(temp);
            notifyAll();
            break;
        }
        temp = null;
        obj = null;
    }

    /**
     * 非阻塞方式向队列中插入一条消息（该消息必须实现IQueueObject接口）
     * @throws NotImplementIQueueObjectInterfaceException
     */
    public synchronized boolean putElementNoWait(IQueueObject queueObject)
            throws OutOfBlockSizeException {
        int position;
        boolean ret = false;
        Object obj;
        // it should delete from nullBlocksIndex and add into blocksIndex
        if (queueObject != null) {
            if (queueObject.getBlockSize() > blockSize) {
                throw new OutOfBlockSizeException("超过最大数据块大小");
            }
            if (nullBlocksIndex.size() != 0) {
                obj = nullBlocksIndex.get();
                if (obj == null) {
                    ret = false;
                } else {
                    position = Integer.parseInt((String) obj);
                    blocksIndex.put(Integer.toString(position));
                    queue.position(position * blockSize);
                    queue.put(queueObject.toByteBuffer().array());
                    ret = true;
                }
            }
        }
        obj = null;
        notifyAll();
        return ret;
    }

    /**
     * 非阻塞方式从队列中获取一数据块
     * @return：数据块
     */
    public synchronized byte[] getElementNoWait() {
        int position;
        boolean ret = false;
        byte[] temp = new byte[blockSize];
        Object obj = null;
        // it should delete from blocksIndex and add into nullBlocksIndex
        if (blocksIndex.size() != 0) {
            obj = blocksIndex.get();
            if (obj == null) {
                ret = false;
            } else {
                position = Integer.parseInt((String) obj);
                nullBlocksIndex.put(Integer.toString(position));
                queue.position(position * blockSize);
                queue.get(temp);
                // set the first byte to zero indentify this blocks has been free.
                queue.position(position * blockSize);
                queue.putInt(0);
                ret = true;
            }
        }
        notifyAll();
        if (groupQueue) {
            if (ret) {
                currentWindows--;
            } else {
                currentWindows = 0;
            }
        }
        if (ret) {
            return temp;
        } else {
            return null;
        }
    }

    /**
     * 非阻塞方式从队列中获取一条消息并从队列中删除该消息
     * @return
     */
    public synchronized boolean getElementNoWait(IQueueObject queueObject) {
        int position;
        boolean ret = false;
        byte[] temp = new byte[blockSize];
        Object obj = null;
        // it should delete from blocksIndex and add into nullBlocksIndex
        if (blocksIndex.size() != 0) {
            obj = blocksIndex.get();
            if (obj == null) {
                ret = false;
            } else {
                position = Integer.parseInt((String) obj);
                nullBlocksIndex.put(Integer.toString(position));
                queue.position(position * blockSize);
                queue.get(temp);
                // set the first byte to zero indentify this blocks has been free.
                queue.position(position * blockSize);
                queue.putInt(0);
                queueObject.toObject(temp);
                ret = true;
            }
        }
        temp = null;
        obj = null;
        notifyAll();
        if (groupQueue) {
            if (ret) {
                currentWindows--;
            } else {
                currentWindows = 0;
            }
        }
        return ret;
    }

    public void destory() {
        isDestory = true;
    }

    /**
     * 队列描述：对列名、文件名、数据块大小、已使用块数、未使用块数。
     * @return
     */
    public String toString() {
        StringBuffer desc = new StringBuffer(LS);
        desc.append("\t队列描述:");
        desc.append(LS);
        desc.append("\t队列名称\t\t= ");
        desc.append(this.queueName);
        desc.append(LS);
        desc.append("\t队列数据文件\t= ");
        desc.append(this.path);
        desc.append(this.fileName);
        desc.append(LS);
        desc.append("\t数据块大小\t\t= ");
        desc.append(this.blockSize);
        desc.append(LS);
        desc.append("\t窗口大小\t\t= ");
        desc.append(this.windows);
        desc.append(LS);
        desc.append("\t数据块个数\t\t= ");
        desc.append(this.maxBlocks);
        desc.append(LS);
        desc.append("\t未使用数据块个数\t= ");
        desc.append(this.nullBlocksIndex.size());
        desc.append(LS);
        desc.append("\t已使用数据块个数\t= ");
        desc.append(this.blocksIndex.size());
        return desc.toString();
    }

    public String toQuataString() {
        StringBuffer desc = new StringBuffer(LS);
        desc.append("\t队列名称\t\t= ");
        desc.append(this.queueName);
        desc.append(LS);
        desc.append("\t数据块个数\t\t= ");
        desc.append(this.maxBlocks);
        desc.append(LS);
        desc.append("\t未使用数据块个数\t= ");
        desc.append(this.nullBlocksIndex.size());
        desc.append(LS);
        desc.append("\t已使用数据块个数\t= ");
        desc.append(this.blocksIndex.size());
        return desc.toString();
    }

}
