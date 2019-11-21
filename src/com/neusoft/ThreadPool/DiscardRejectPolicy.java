package com.neusoft.ThreadPool;

public class DiscardRejectPolicy implements RejectPolicy {

    @Override
    public void reject(Runnable task, MyThreadPoolExecutor myThreadPoolExecutor) {
        // do nothing
        System.out.println("discard one task");
    }
}
