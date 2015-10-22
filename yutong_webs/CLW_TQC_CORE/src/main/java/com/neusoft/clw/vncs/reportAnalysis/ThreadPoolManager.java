package com.neusoft.clw.vncs.reportAnalysis;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {
    private static final ThreadPoolManager threadPoolManager = new ThreadPoolManager();

    private ThreadPoolExecutor threadPool;

    private ThreadPoolManager() {
    }

    public static ThreadPoolManager getInstance() {
        return threadPoolManager;
    }

    /**
     * 初始化线程池
     * @param corePoolSize 线程池维护线程的最少数量
     * @param maximumPoolSize 线程池维护线程的最大数量
     * @param keepAliveTime 线程池维护线程所允许的空闲时间（秒）
     * @param blockQueueCapacity 线程池缓冲队列大小
     */
    public synchronized void init(int corePoolSize, int maximumPoolSize, long keepAliveTime,int blockQueueCapacity) {
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(blockQueueCapacity),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
    
    /**
     * 获取线程池
     * @return
     */
    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    /**
     * 关闭线程池
     */
    public void shutdown(){
        threadPool.shutdown();
    }
}
