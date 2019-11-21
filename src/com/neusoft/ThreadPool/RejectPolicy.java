package com.neusoft.ThreadPool;

/**
 * 自定义的拒绝策略接口
 */
public interface RejectPolicy {

    void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor);

}
