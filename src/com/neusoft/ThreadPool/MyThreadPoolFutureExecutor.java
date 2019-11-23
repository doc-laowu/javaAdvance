package com.neusoft.ThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * 无返回值的任务提交了就完事，主线程并不Care它到底有没有执行完，并不关心它是不是抛出异常，主线程Just提交线程到线程池中，其余什么都不管。
 *
 * 有返回值的任务就不一样了，主线程首先要提交任务到线程池中，它需要使用到任务执行的结果，所以它必须等待任务执行完毕才能拿到任务执行的结果。
 */
public class MyThreadPoolFutureExecutor extends MyThreadPoolExecutor implements FutureExecutor, Executor {

    public MyThreadPoolFutureExecutor(String name, int coreSize, int maxSize, BlockingQueue<Runnable> taskQueue, RejectPolicy rejectPolicy) {
        super(name, coreSize, maxSize, taskQueue, rejectPolicy);
    }

    @Override
    public <T> Future<T> submit(Callable<T> task) {

        // 包装成需要获取返回值的任务
        FutureTask<T> futureTask = new FutureTask<>(task);
        // 使用原来的执行能力
        execute(futureTask);

        // 返回将来的任务，只需要返回其get返回值的能力即可
        // 所以这里返回的是Future而不是FutureTask类型
        return futureTask;
    }
}
